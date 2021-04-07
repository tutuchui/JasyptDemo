package com.example.springjpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class SpringjpademoApplication {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringjpademoApplication.class, args);
        MyDB db = context.getBean(MyDB.class);
    }


}
