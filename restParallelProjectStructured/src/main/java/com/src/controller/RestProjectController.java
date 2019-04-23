package com.src.controller;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.src.dao.BankDAOImpl;
import com.src.model.Credentials;
import com.src.model.Customer;
import com.src.service.BankOperations;
import com.src.service.BankService;
import com.src.service.BankServiceImpl;

@RestController
public class RestProjectController {

	Customer cust = new Customer();
	ArrayList<Customer> custList = new ArrayList<Customer>();

	Random random = new Random();

	ApplicationContext context = new AnnotationConfigApplicationContext(BankConfig.class);
	BankService bank = context.getBean(BankService.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void customer() {
		bank.customer();

	}
	
	@RequestMapping(value = "/getAllCust", method = RequestMethod.GET)
	public ArrayList<Customer> getAllCustomers() {
		custList = bank.getAllCustomers();
		return custList;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity addCustomer(@RequestBody Customer customer) {

		bank.addCustomer(customer);

		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/login/{accountNo}/{password}")
	public String login(@PathVariable long accountNo, @PathVariable String password) {

		int c = 0;
		c = bank.login(accountNo, password);

		if (c == 1)
			return "Successfully logged in";
		else
			return "Sorry....try again.....";
	}

	@RequestMapping(value = "/deposit/{amt}", method = RequestMethod.PUT)
	public int deposit(@PathVariable int amt, @RequestBody Credentials credentials) {
		int c = 0, balance = 0;

		balance = bank.deposit(amt, credentials);

		if (balance > 0) {
			return balance;
		} else {
			return 0;
		}
	}

	@RequestMapping(value = "/withdraw/{amt}", method = RequestMethod.PUT)
	public int withdraw(@PathVariable int amt, @RequestBody Credentials credentials) {
		int c = 0, balance = 0;
		balance = bank.withdraw(amt, credentials);

		if (balance > 0) {
			return balance;
		} else {
			return 0;
		}
	}

	@RequestMapping(value = "/showBal", method = RequestMethod.POST)
	public float showBalance(@RequestBody Credentials credentials) {
		int c = 0;
		float balance = 0;
		balance = bank.showBalance(credentials);
		c = 1;

		if (balance > 0) {
			return balance;
		} else {
			return 0;
		}
	}

	@RequestMapping(value = "/transfer/{amt}/{toAccountNo}", method = RequestMethod.PUT)
	public float fundTransfer(@PathVariable int amt, @PathVariable long toAccountNo,
			@RequestBody Credentials credentials) {
		int c = 0;
		float balance = 0;
		balance = bank.fundTransfer(amt, toAccountNo, credentials);

		if (balance > 0) {
			return balance;
		} else {
			return 0;
		}
	}
}