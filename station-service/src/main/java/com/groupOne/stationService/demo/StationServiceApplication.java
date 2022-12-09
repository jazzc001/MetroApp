package com.groupOne.stationService.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.groupOne.stationService")
@EntityScan(basePackages = "com.groupOne.stationService.entity")
@EnableJpaRepositories(basePackages = "com.groupOne.stationService.persistence")
public class StationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationServiceApplication.class, args);
	}

}
