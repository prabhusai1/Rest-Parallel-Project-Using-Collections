package com.src.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

	@Bean
	public Validation transController() {
		return new Validation();
	}
}
