package com.src.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.src.dao.BankDAO;
import com.src.dao.BankDAOImpl;
import com.src.service.BankOperations;
import com.src.service.BankService;
import com.src.service.BankServiceImpl;

@Configuration
public class BankConfig {

	@Bean(name = "bank")
	public BankService bank() {
		return new BankServiceImpl();
	}

	

//	@Bean(name = "bankDao")
//	public BankDAO bankDao() {
//		return new BankDAOImpl();
//	}

	
}
