package com.example.springjpademo;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {

    @Autowired
    CustomJasyptEncryptor customJasyptEncryptor;

    public String encryptString(String plainText){
        StringEncryptor encryptor = customJasyptEncryptor.stringEncryptor();
        return encryptor.encrypt(plainText);
    }

    public String decryptString(String encryptedString){
        StringEncryptor encryptor = customJasyptEncryptor.stringEncryptor();
        return encryptor.decrypt(encryptedString);
    }
}
