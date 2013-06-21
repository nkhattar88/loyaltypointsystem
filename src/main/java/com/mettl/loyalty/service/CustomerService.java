package com.mettl.loyalty.service;

import com.mettl.loyalty.domain.Customer;
import com.mettl.loyalty.domain.Transaction;
import com.mettl.loyalty.exception.LoyaltyProgramException;

public interface CustomerService {

	Customer fetchCustomerByLoyalityCardNumber(Long loyaltyCardNumber) throws LoyaltyProgramException;
	
	boolean addTransaction(Long custLoyaltyCardNumber, Transaction transaction);
}
