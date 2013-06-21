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
	
	public Customer fetchCustomerByLoyalityCardNumber(Long loyaltyCardNumber) throws LoyaltyProgramException{
		Customer customer = findByLoyaltyCardNumber(loyaltyCardNumber);
		if(customer == null){
			customer = new Customer(loyaltyCardNumber);
			if(!customerRepository.addCustomer(customer)){
				return null;
			}
			LOGGER.info("New Customer added with Loyalty Card Number"+loyaltyCardNumber);
		}
		return customer;
	}
	
	private Customer findByLoyaltyCardNumber(Long loyaltyCardNumber){
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
}
