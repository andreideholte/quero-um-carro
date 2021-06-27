package com.desafio.api.quc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.desafio.api.quc"})
@SpringBootApplication
public class QueroUmCarroApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueroUmCarroApplication.class, args);
	}
}
