package com.xiaozhi.common.interceptor;

import com.xiaozhi.common.util.ReflectUtil;
import com.xiaozhi.common.util.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

import javax.xml.bind.PropertyException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * mybatis 分页拦截器
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PagePlugin implements Interceptor {
    private static final Logger logger = Logger.getLogger(PagePlugin.class.getName());
    private String dialect = "";    //数据库方言
    private String pageSqlId = ""; //mapper.xml中需要拦截的ID(正则匹配)

    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectUtil.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getValueByFieldName(delegate, "mappedStatement");
            PreparedStatement countStmt = null;
            ResultSet rs = null;
            //System.out.println("id="+mappedStatement.getId());
            //System.out.println("pageSqlId="+pageSqlId);
            if (mappedStatement.getId().matches(pageSqlId)) { //拦截需要分页的SQL
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();//分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject尚未实例化！");
                } else {
                    try {
                        Page page = null;
                        if (parameterObject instanceof Page) {    //参数就是Page实体
                            page = (Page) parameterObject;
                            // page.setTotalResult(count);
                        } else {    //参数为某个实体，该实体拥有Page属性
                            Field pageField = ReflectUtil.getFieldByFieldName(parameterObject, "page");
                            if (pageField != null) {
                                page = (Page) ReflectUtil.getValueByFieldName(parameterObject, "page");
                                if (page == null)
                                    page = new Page();
                                //  page.setTotalResult(count);
                                ReflectUtil.setValueByFieldName(parameterObject, "page", page); //通过反射，对实体对象设置分页对象
                            } else {
                                throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在 page 属性！");
                            }
                        }
                        Connection connection = (Connection) ivk.getArgs()[0];
                        String sql = boundSql.getSql();
                        //  long start = System.currentTimeMillis();
                       /* String upperSql = sql.toUpperCase();
                        String countSql = ""; //记录统计
                        if(mappedStatement.getId().equals("com.qftx.tsm.cust.dao.ResCustInfoMapper.findClearPoolListPage")){
                        	int startIndex = upperSql.indexOf("FROM");
                        	String count_sql = sql.substring(startIndex,sql.length());
                        	countSql = "select count(0) "+count_sql;
                        }else{
                            countStmt = connection.prepareStatement(countSql);
                             }*/
                        if (page.getCurrentPage() < 2) {
                            String countSql = "select count(0) from (" + sql + ")  tmp_count"; //记录统计
                            int len = sql.toLowerCase().indexOf("from");
                            int len1 = sql.toLowerCase().indexOf("union");
                            if (len != -1 && len1 < 0) {
                                countSql = "select count(0) " + sql.substring(len);
                                len = countSql.toLowerCase().indexOf("order by");
                                if (len != -1) {
                                    countSql = countSql.substring(0, len);
                                }
                            }
                            countStmt = connection.prepareStatement(countSql);
                            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
                            // 以下代码防止  mybatis foreach 失效
                            Field metaParamsField = ReflectUtil.getFieldByFieldName(boundSql, "metaParameters");
                            if (metaParamsField != null) {
                                MetaObject mo = (MetaObject) ReflectUtil.getValueByFieldName(boundSql, "metaParameters");
                                ReflectUtil.setValueByFieldName(countBS, "metaParameters", mo);
                            }

                            setParameters(countStmt, mappedStatement, countBS, parameterObject);
                            rs = countStmt.executeQuery();
                            int count = 0;
                            if (rs.next()) {
                                count = rs.getInt(1);
                            }
                            //  logger.debug("执行sql时间=" + (System.currentTimeMillis() - start)+"ms");
                            //System.out.println(count);
                            if (page != null) page.setTotalResult(count);
                        }
                        String pageSql = generatePageSql(sql, page);
                        ReflectUtil.setValueByFieldName(boundSql, "sql", pageSql); //将分页sql语句反射回BoundSql.
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在 page 属性！");
                    } finally {
                        if (rs != null) {
                            rs.clearWarnings();
                            rs.close();
                        }
                        if (countStmt != null) {
                            countStmt.clearParameters();
                            countStmt.close();
                        }
                    }

                }
            }
        }
        return ivk.proceed();
    }

    /**
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
     *
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    /**
     * 根据数据库方言，生成特定的分页sql
     *
     * @param sql
     * @param page
     * @return String
     */
    private String generatePageSql(String sql, Page page) {
        if (page != null && !StringUtils.isEmpty(dialect)) {
            StringBuffer pageSql = new StringBuffer();
            if ("mysql".equals(dialect)) {
                pageSql.append(sql);
                pageSql.append(" limit " + page.getCurrentResult() + "," + page.getShowCount());
            } else if ("oracle".equals(dialect)) {
                pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
                pageSql.append(sql);
                pageSql.append(")  tmp_tb where ROWNUM<=");
                pageSql.append(page.getCurrentResult() + page.getShowCount());
                pageSql.append(") where row_id>");
                pageSql.append(page.getCurrentResult());
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if (StringUtils.isEmpty(dialect)) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
        pageSqlId = p.getProperty("pageSqlId");
        if (StringUtils.isEmpty(pageSqlId)) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                //
            }
        }
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getPageSqlId() {
        return pageSqlId;
    }

    public void setPageSqlId(String pageSqlId) {
        this.pageSqlId = pageSqlId;
    }

}
