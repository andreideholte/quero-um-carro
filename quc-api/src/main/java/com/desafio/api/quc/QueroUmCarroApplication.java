package com.desafio.api.quc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = {"com.desafio.api.quc"})
@EnableAutoConfiguration
@EnableMongoRepositories
@SpringBootApplication
public class QueroUmCarroApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueroUmCarroApplication.class, args);
	}
}
