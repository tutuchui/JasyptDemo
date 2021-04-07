package com.example.springjpademo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class DBConfig {
    private ArrayList<CountryDBConfig> countryDBDetails;

    public ArrayList<CountryDBConfig> getCountryDBDetails() {
        return countryDBDetails;
    }

    public void setCountryDBDetails(ArrayList<CountryDBConfig> countryDBDetails) {
        this.countryDBDetails = countryDBDetails;
    }
}

class CountryDBConfig{
    String countryCode;
    String url;
    String username;
    String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "CountryDBConfig{" +
                "countryCode='" + countryCode + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
