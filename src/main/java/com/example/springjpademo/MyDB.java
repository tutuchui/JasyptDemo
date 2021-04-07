package com.example.springjpademo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Configuration
public class MyDB {
    @Autowired
    DBConfig dbConfig;

    @Value("${dbDetails.driverClassName}")
    String driverClassName;

    @Bean
    public DataSource getMyDataSource(){
        CountryDBConfig local = dbConfig.getCountryDBDetails().get(0);
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(local.getUrl());
        config.setUsername(local.getUsername());
        config.setPassword(local.getPassword());
        config.setDriverClassName(driverClassName);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(600000);
        return new HikariDataSource(config);
    }

    @Bean(name = "LocalJdbcTemplate")
    public JdbcTemplate localTemplate() {
        return new JdbcTemplate(getMyDataSource());
    }
}
