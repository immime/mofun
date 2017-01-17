package com.mofun.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mofun.constant.SqlConstant;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by runmain on 12/22/2016.
 */
@Configuration
@PropertySource(value = "classpath:jdbc.properties")
@EnableTransactionManagement
@MapperScan("com.mofun.dao")
public class PostgresConfig {

    @Value("${postgres.jdbc.driveClass}")
    private String dirver;
    @Value("${postgres.jdbc.url}")
    private String url;
    @Value("${postgres.jdbc.username}")
    private String username;
    @Value("${postgres.jdbc.password}")
    private String password;
    @Value("${postgres.jdbc.maxActive}")
    private Integer maxActive;
    @Value("${postgres.jdbc.initialSize}")
    private Integer initialSize;
    @Value("${postgres.jdbc.maxWait}")
    private Long maxWait;
    @Value("${postgres.jdbc.minIdle}")
    private Integer minIdle;
    @Value("${postgres.jdbc.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${postgres.jdbc.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${postgres.jdbc.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${postgres.jdbc.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${postgres.jdbc.testOnReturn}")
    private Boolean testOnReturn;

    @Bean
//    @ConditionalOnMissingBean(name = "sqlSessionFactory")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dirver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        dataSource.setMinIdle(minIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setValidationQuery(" select now(); ");
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        //添加插件
//        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = SqlConstant.TRANSACTION_MANAGER_NAME)
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
