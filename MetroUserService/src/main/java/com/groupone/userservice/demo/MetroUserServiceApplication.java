package com.groupone.userservice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.groupone.userservice")
@EntityScan(basePackages = "com.groupone.userservice.entity")
@EnableJpaRepositories(basePackages = "com.groupone.userservice.persistence")
public class MetroUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetroUserServiceApplication.class, args);
	}

}
