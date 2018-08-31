package com.xiaozhi.common.interceptor;

/**
 * User: bxl
 * Date: 2015/12/1
 * Time: 13:59
 */

import com.xiaozhi.common.util.ReflectUtil;
import com.xiaozhi.common.util.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
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
import java.sql.*;
import java.util.List;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class PagerInterceptor implements Interceptor {
    private final Logger logger = Logger.getLogger(PagerInterceptor.class.getName());
    private String dialect = "";
    private String pageSqlId = "";
    private ThreadLocal<Pager> localPage = new ThreadLocal<Pager>();

    public Object intercept(Invocation ivk) throws Throwable {

        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectUtil.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getValueByFieldName(delegate, "mappedStatement");
            PreparedStatement countStmt = null;
            ResultSet rs = null;
            //System.out.println("id="+mappedStatement.getId());
            //System.out.println("pageSqlId="+pageSqlId);
            if (mappedStatement.getId().matches(pageSqlId)) { //������Ҫ��ҳ��SQL
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                //  System.out.println(">>>>>1111>>>" + parameterObject.getClass());
                if (parameterObject != null && parameterObject instanceof Pager) {
                    if (parameterObject == null) {
                        throw new NullPointerException("parameterObject is null");
                    } else {
                        try {
                            Connection connection = (Connection) ivk.getArgs()[0];
                            String sql = boundSql.getSql();
                            String countSql = "select count(0) from (" + sql + ")  tmp_count";
                            Pager page = (Pager) parameterObject;
                            localPage.set(page);
                            // System.out.println("======" + ((TsmRecordCallBean) page.getItem()).getOrgId());
                            //if (page.getCurrentPage() < 2) {
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
                            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), page);
                            Field metaParamsField = ReflectUtil.getFieldByFieldName(boundSql, "metaParameters");
                            if (metaParamsField != null) {
                                MetaObject mo = (MetaObject) ReflectUtil.getValueByFieldName(boundSql, "metaParameters");
                                ReflectUtil.setValueByFieldName(countBS, "metaParameters", mo);
                            }
                            setParameters(countStmt, mappedStatement, countBS, page);
                            rs = countStmt.executeQuery();
                            int count = 0;
                            if (rs.next()) {
                                count = rs.getInt(1);
                            }
                            page.setTotalRecord(count);
                            Object rowCount = ReflectUtil.getFieldByFieldName(boundSql, "rowCount");
                            if (rowCount != null) ReflectUtil.setValueByFieldName(boundSql, "rowCount", count);
                            //}
                            String pageSql = generatePageSql(sql, page);
                            ReflectUtil.setValueByFieldName(boundSql, "sql", pageSql);
                        } catch (Exception e) {
                            throw new NoSuchFieldException(parameterObject.getClass().getName() + " is not pager");
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
        } else if (ivk.getTarget() instanceof ResultSetHandler) {
            Object result = ivk.proceed();
            // logger.debug("list=" + JSON.toJSONString(result));
            Pager page = localPage.get();
            if (page != null) {
                page.setList((List) result);
                localPage.remove();
                // logger.debug(">>>>>>>>>>>>" + localPage.get());
            }
            return result;
        }
        return ivk.proceed();
    }

    /**
     * ��SQL����(?)��ֵ,�ο�org.apache.ibatis.executor.parameter.DefaultParameterHandler
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

    private String generatePageSql(String sql, Pager page) {
        if (page != null && !StringUtils.isEmpty(dialect)) {
            StringBuffer pageSql = new StringBuffer();
            if ("mysql".equals(dialect)) {
                pageSql.append(sql);
                pageSql.append(" limit " + page.getIntPosition() + "," + page.getPageSize());
            } else if ("oracle".equals(dialect)) {
                pageSql.append("select * from (select tmp_tb.*,rownum row_id from (");
                pageSql.append(sql);
                pageSql.append(")  tmp_tb where rownum<=");
                pageSql.append(page.getIntPosition() + page.getPageSize());
                pageSql.append(") where row_id>");
                pageSql.append(page.getIntPosition());
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
                e.printStackTrace();
            }
        }
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }


    public void setPageSqlId(String pageSqlId) {
        this.pageSqlId = pageSqlId;
    }
}
