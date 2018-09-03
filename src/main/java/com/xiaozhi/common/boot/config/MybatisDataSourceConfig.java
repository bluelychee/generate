package com.xiaozhi.common.boot.config;

import com.xiaozhi.common.interceptor.LogTimeInterceptor;
import com.xiaozhi.common.interceptor.PagerInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Create by lizhihui on 2018/8/9
 */
@Configuration
@MapperScan(basePackages = "com.xiaozhi", sqlSessionTemplateRef = "mybatisMasterSqlSessionTemplate")
public class MybatisDataSourceConfig {

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /*@Bean(name = "mybatisMasterDataSource")
    @ConfigurationProperties(prefix = "datasource")
    public DataSource mybatisMasterDataSource() {
//        return new DriverManagerDataSource("jdbc:mysql://localhost:3306/test","root","root");
        return DataSourceBuilder.create().build();
    }*/

    @Bean(name = "mybatisMasterSqlSessionFactory")
    public SqlSessionFactory mybatisMasterSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com.mybatis.mapper/*.xml"));
        // 设置MyBatis分页插件
        bean.setPlugins(new Interceptor[]{pagerInterceptor(), logTimeInterceptor()});
        return bean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mScannerConfigurer = new MapperScannerConfigurer();
        mScannerConfigurer.setSqlSessionFactoryBeanName("mybatisMasterSqlSessionFactory");
        //mScannerConfigurer.setBasePackage("com.my.boot.test.entity");
        //mScannerConfigurer.setBasePackage("com.my.boot.test.mapper");
        mScannerConfigurer.setBasePackage("com.xiaozhi");
        return mScannerConfigurer;
    }

    @Bean(name = "mybatisMasterTransactionManager")
    public DataSourceTransactionManager mybatisMasterTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mybatisMasterSqlSessionTemplate")
    public SqlSessionTemplate mybatisMasterSqlSessionTemplate(@Qualifier("mybatisMasterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PagerInterceptor pagerInterceptor() {
        PagerInterceptor pageInterceptor = new PagerInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("pageSqlId", ".*ListPage.*");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Bean
    public LogTimeInterceptor logTimeInterceptor() {
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("pageSqlId", ".*ListPage.*");
        //pageInterceptor.setProperties(properties);
        return new LogTimeInterceptor();
    }

}