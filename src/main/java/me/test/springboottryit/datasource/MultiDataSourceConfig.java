package me.test.springboottryit.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class MultiDataSourceConfig {

    @Configuration
    @MapperScan(
        basePackages = "me.test.springboottryit.datasource.dao1",
        sqlSessionFactoryRef = "ds1SqlSessionFactory"
    )
    @PropertySource(value = "classpath:multi-datasource/ds.properties")
    public static class Datasource1Config {

        @Bean(name = "ds1")
        @Primary
        @ConfigurationProperties(prefix = "spring.datasource.druid.ds1")
        public DataSource dataSource() {
            return DruidDataSourceBuilder.create().build();
        }

        @Bean(name = "ds1TransactionManager")
        @Primary
        public DataSourceTransactionManager dataSourceTransactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

        @Bean(name = "ds1SqlSessionFactory")
        @Primary
        public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds1") DataSource dataSource) throws Exception {
            final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
//            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mysql/*.xml"));
            return sessionFactory.getObject();
        }
    }

    @Configuration
    @MapperScan(
        basePackages = "me.test.springboottryit.datasource.dao2",
        sqlSessionFactoryRef = "ds2SqlSessionFactory"
    )
    @PropertySource(value = "classpath:multi-datasource/ds.properties")
    public static class Datasource2Config {

        @Bean(name = "ds2")
        @ConfigurationProperties("spring.datasource.druid.ds2")
        public DataSource dataSource() {
            return DruidDataSourceBuilder.create().build();
        }

        @Bean(name = "ds2TransactionManager")
        public DataSourceTransactionManager dataSourceTransactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

        @Bean(name = "ds2SqlSessionFactory")
        public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds2") DataSource dataSource) throws Exception {
            final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
//            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mysql/*.xml"));
            return sessionFactory.getObject();
        }
    }

}
