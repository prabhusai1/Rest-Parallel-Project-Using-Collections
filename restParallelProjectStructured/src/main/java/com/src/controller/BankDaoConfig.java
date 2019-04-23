package com.src.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.src.dao.BankDAO;
import com.src.dao.BankDAOImpl;

@Configuration
public class BankDaoConfig {
	
	@Bean(name = "bankDao")
	public BankDAO bankDao() {
		return new BankDAOImpl();
	}
}
