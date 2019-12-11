package com.kaminski.realestateapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RealEstateApplication {

	public static void main(String[] args) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		bCryptPasswordEncoder.encode("admin");
		System.out.println("Haslo!!! " + bCryptPasswordEncoder.encode("admin"));
		SpringApplication.run(RealEstateApplication.class, args);
	}

}
