package com.src.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.src.controller.BankDaoConfig;
import com.src.dao.BankDAO;
import com.src.model.Credentials;
import com.src.model.Customer;

@Component
public class BankServiceImpl implements BankService {

	ApplicationContext context = new AnnotationConfigApplicationContext(BankDaoConfig.class);
	BankDAO bankDao = context.getBean(BankDAO.class);

	public void printxyz() {
		System.out.println("came here");
	}

	public BankServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public void customer() {
		bankDao.customer();
	}

	public ResponseEntity addCustomer(@RequestBody Customer customer) {
		bankDao.addCustomer(customer);
		return new ResponseEntity(HttpStatus.OK);
	}

	public int login(@PathVariable long accountNo, @PathVariable String password) {
		int c = 0;
		c = bankDao.login(accountNo, password);
		if (c == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	public int deposit(@PathVariable int amt, @RequestBody Credentials credentials) {
		int balance = 0;
		balance = bankDao.deposit(amt, credentials);
		return balance;

	}

	public int withdraw(@PathVariable int amt, @RequestBody Credentials credentials) {
		int balance = 0;
		balance = bankDao.withdraw(amt, credentials);
		return balance;
	}

	public float showBalance(@RequestBody Credentials credentials) {
		float balance = 0;
		balance = bankDao.showBalance(credentials);
		return balance;
	}

	public float fundTransfer(@PathVariable int amt, @PathVariable long toAccountNo,
			@RequestBody Credentials credentials) {
		float balance = 0;
		balance = bankDao.fundTransfer(amt, toAccountNo, credentials);
		return balance;
	}

	@Override
	public ArrayList<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return (ArrayList<Customer>) bankDao.getAllCustomers();
	}

}
