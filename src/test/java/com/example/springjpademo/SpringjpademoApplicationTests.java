package com.example.springjpademo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringjpademoApplicationTests {
    @Autowired
    EncryptUtil util;

    @Test
    void EncryptorTest() {
        System.out.println(util.encryptString("db_username"));
        System.out.println(util.encryptString("db_password"));
    }

}
