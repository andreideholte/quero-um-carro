package com.desafio.api.quc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = {"com.desafio.api.quc"})
@EnableMongoRepositories(basePackages = "com.desafio.api.quc.repository")
@SpringBootApplication
public class QueroUmCarroApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(QueroUmCarroApplication.class, args);
	}
}
