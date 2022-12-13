package com.groupone.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.groupone")
@EntityScan(basePackages = "com.groupone.entity")
@EnableJpaRepositories(basePackages = "com.groupone.persistence")
public class UserJourneyMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserJourneyMvcApplication.class, args);
	}

	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}

}
