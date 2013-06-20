package com.mettl.loyalty.service.impl;

import org.apache.log4j.Logger;

import com.mettl.loyalty.domain.Customer;
import com.mettl.loyalty.domain.Transaction;
import com.mettl.loyalty.exception.LoyaltyProgramException;
import com.mettl.loyalty.repository.CustomerRepository;
import com.mettl.loyalty.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{

	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);
	
	private CustomerRepository customerRepository;
	
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer findByLoyaltyCardNumber(Long loyaltyCardNumber){
		if(loyaltyCardNumber == null){
			return null;
		}
		try {
			return customerRepository.findByLoyaltyCardNumber(loyaltyCardNumber);
		} catch (LoyaltyProgramException e) {
			LOGGER.error(e);
			return null;
		}
	}
	
	public boolean addNewCustomer(Customer customer){
		if(customer == null){
			LOGGER.error("Cannot Save a null object of Customer");
			return false;
		}
		return customerRepository.addCustomer(customer);
	}
	
	public boolean addTransaction(Long custLoyaltyCardNumber, Transaction transaction) {
		if(custLoyaltyCardNumber == null){
			LOGGER.error("Cannot add a object of Transaction to Customer with null Loyalty Card Number");
			return false;
		}
		if(transaction == null){
			LOGGER.error("Cannot add a null object of Transaction to Customer with LoyaltyCardNumber:"+custLoyaltyCardNumber);
			return false;
		}
		try {
			return customerRepository.addTransaction(custLoyaltyCardNumber, transaction);
		} catch (LoyaltyProgramException e) {
			LOGGER.error(e);
			return false;
		}
	}
}
