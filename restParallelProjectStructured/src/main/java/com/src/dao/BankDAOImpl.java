package com.src.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.src.model.Credentials;
import com.src.model.Customer;
import com.src.service.Validation;
import com.src.service.ValidationConfig;

@Component
public class BankDAOImpl implements BankDAO {

	Customer cust = new Customer();
	ArrayList<Customer> custList = new ArrayList<Customer>();

	Random random = new Random();

	public BankDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public void customer() {
		cust.setAadharNo(705331994557L);
		cust.setAddress("hyderabad");
		cust.setBalance(3000);
		cust.setEmailId("kothaprabhusai1998@gmail.com");
		cust.setFirstName("prabhusai");
		cust.setLastName("kotha");
		cust.setMobileNo(8790321879L);
		cust.setPancardNo("ABCDE1234F");
		cust.setPassword("kps@1234");
		cust.setAccountNo(random.nextInt(999999999));

		custList.add(cust);
	}

	public ResponseEntity addCustomer(@RequestBody Customer customer) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ValidationConfig.class);
		Validation valid = context.getBean(Validation.class);

		customer.setAccountNo(random.nextInt(999999999));

		boolean isAdharValid = valid.aadharValidate(customer.getAadharNo());
		boolean isEmailValid = valid.emailValidate(customer.getEmailId());
		boolean isPasswordValid = valid.password(customer.getPassword());
		boolean isPanValid = valid.panValidate(customer.getPancardNo());
		boolean isMobileValid = valid.mobileValidate(customer.getMobileNo());

		if (isAdharValid && isEmailValid && isPasswordValid && isPanValid && isMobileValid)
			custList.add(customer);

		return new ResponseEntity(HttpStatus.OK);
	}

	public Customer getCustomerById(@PathVariable long accountNo) {
		Customer customer = new Customer();

		for (Customer custo : custList) {
			if (custo.getAccountNo() == accountNo) {
				customer.setAadharNo(custo.getAadharNo());
				customer.setAddress(custo.getAddress());
				customer.setBalance(custo.getBalance());
				customer.setEmailId(custo.getEmailId());
				customer.setFirstName(custo.getFirstName());
				customer.setLastName(custo.getLastName());
				customer.setMobileNo(custo.getMobileNo());
				customer.setPancardNo(custo.getPancardNo());
				customer.setPassword(custo.getPassword());
				customer.setAccountNo(custo.getAccountNo());
				break;
			}
		}
		return customer;
	}

	public ResponseEntity delStudent(@PathVariable int accountNo) {

		for (Customer customer : custList) {
			if (customer.getAccountNo() == accountNo) {
				custList.remove(customer);
				break;
			}
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	public ResponseEntity updateStudent(@PathVariable int accountNo, @RequestBody Customer customer) {

		for (Customer custo : custList) {
			if (custo.getAccountNo() == accountNo) {
				custo.setAadharNo(customer.getAadharNo());
				custo.setAddress(customer.getAddress());
				custo.setBalance(customer.getBalance());
				custo.setEmailId(customer.getEmailId());
				custo.setFirstName(customer.getFirstName());
				custo.setLastName(customer.getLastName());
				custo.setMobileNo(customer.getMobileNo());
				custo.setPancardNo(customer.getPancardNo());
				custo.setPassword(customer.getPassword());
				break;
			}
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return custList;
	}

	public int login(@PathVariable long accountNo, @PathVariable String password) {

		// TransactionController transController =
		//
		int c = 0;

		for (Customer customer : custList) {
			if ((customer.getAccountNo() == accountNo) && (customer.getPassword().equals(password))) {
				// transController.printSome(custList, cust);
				c = 1;
				break;
			}
		}

		if (c == 1)
			return 1;
		else
			return 0;
	}

	public int deposit(@PathVariable int amt, @RequestBody Credentials credentials) {
		int c = 0, balance = 0;

		for (Customer validateCustomer : custList) {
			if ((validateCustomer.getAccountNo() == credentials.getAccountNo())
					&& ((validateCustomer.getPassword()).equals(credentials.getPassword()))) {
				c = 1;
				balance = (int) (validateCustomer.getBalance() + amt);
				validateCustomer.setBalance(balance);
				break;
			}
		}

		if (c == 1) {
			return balance;
		} else {
			return 0;
		}
	}

	public int withdraw(@PathVariable int amt, @RequestBody Credentials credentials) {
		int c = 0, balance = 0;
		for (Customer validateCustomer : custList) {
			if ((validateCustomer.getAccountNo() == credentials.getAccountNo())
					&& ((validateCustomer.getPassword()).equals(credentials.getPassword()))) {
				if (validateCustomer.getBalance() > amt) {
					balance = (int) (validateCustomer.getBalance() - amt);
					validateCustomer.setBalance(balance);
					c = 1;
					break;
				}
			}
		}

		if (c == 1) {
			return balance;
		} else {
			return 0;
		}
	}

	public float showBalance(@RequestBody Credentials credentials) {
		int c = 0;
		float balance = 0;
		System.out.println(credentials.getAccountNo());
		for (Customer validateCustomer : custList) {
			if ((validateCustomer.getAccountNo() == credentials.getAccountNo())
					&& ((validateCustomer.getPassword()).equals(credentials.getPassword()))) {
				c = 1;
				balance = validateCustomer.getBalance();
				break;
			}
		}

		if (c == 1) {
			return balance;
		} else {
			return 0;
		}
	}

	public float fundTransfer(@PathVariable int amt, @PathVariable long toAccountNo,
			@RequestBody Credentials credentials) {
		int c = 0;
		float balance = 0;
		for (Customer checkCustomer : custList) {
			if (checkCustomer.getAccountNo() == toAccountNo) {

				for (Customer validateCustomer : custList) {
					if ((validateCustomer.getAccountNo() == credentials.getAccountNo())
							&& ((validateCustomer.getPassword()).equals(credentials.getPassword()))) {
						if (validateCustomer.getBalance() > amt) {
							balance = validateCustomer.getBalance() - amt;
							validateCustomer.setBalance(balance);

							float add = checkCustomer.getBalance() + amt;
							checkCustomer.setBalance(add);
							c = 2;
							break;
						}
					}
				}
			}
		}

		if (c == 2) {
			return balance;
		} else {
			return 0;
		}
	}

}
