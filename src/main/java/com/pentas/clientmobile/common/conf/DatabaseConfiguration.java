package com.pentas.clientmobile.common.conf;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@PropertySource({ "classpath:properties/datasource.properties" })
public class DatabaseConfiguration {

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "datasource.hikari")
    public DataSource dataSource() throws SQLException {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        return dataSource;
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSession") SqlSessionFactory db1SqlSessionFactory) {
        return new SqlSessionTemplate(db1SqlSessionFactory);
    }

    @Bean(name = "sqlSession")
    public SqlSessionFactory SqlSessionFactory(ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/**/*_SQL.xml"));
        sqlSessionFactoryBean
                .setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
