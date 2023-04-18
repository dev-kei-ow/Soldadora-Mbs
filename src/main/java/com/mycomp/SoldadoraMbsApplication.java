package com.mycomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class SoldadoraMbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoldadoraMbsApplication.class, args);
	}

	@Bean
	public SpringDataDialect dataDialect() {
		return new SpringDataDialect();
	}

}
