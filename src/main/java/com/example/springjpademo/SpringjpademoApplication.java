package com.example.springjpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class SpringjpademoApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringjpademoApplication.class, args);
    }


}
