package com.src.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.src.controller.BankConfig;
import com.src.model.Credentials;
import com.src.model.Customer;

@Component
public interface BankService {

	ApplicationContext context = new AnnotationConfigApplicationContext(BankConfig.class);
	BankServiceImpl bankImpl = context.getBean(BankServiceImpl.class);

	public void customer();

	public ResponseEntity addCustomer(@RequestBody Customer customer);

	public int login(@PathVariable long accountNo, @PathVariable String password);

	public int deposit(@PathVariable int amt, @RequestBody Credentials credentials);

	public int withdraw(@PathVariable int amt, @RequestBody Credentials credentials);

	public float showBalance(@RequestBody Credentials credentials);

	public float fundTransfer(@PathVariable int amt, @PathVariable long toAccountNo,
			@RequestBody Credentials credentials);

	public ArrayList<Customer> getAllCustomers();
}
