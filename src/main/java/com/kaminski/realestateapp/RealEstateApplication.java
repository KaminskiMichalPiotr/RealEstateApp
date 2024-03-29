package com.kaminski.realestateapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RealEstateApplication {


    public static void main(String[] args) {
        SpringApplication.run(RealEstateApplication.class, args);
    }

}
