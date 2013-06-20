package com.mettl.loyalty.service;

import com.mettl.loyalty.domain.Customer;
import com.mettl.loyalty.domain.Transaction;

public interface CustomerService {

	Customer findByLoyaltyCardNumber(Long loyalityCardNumber);

	boolean addNewCustomer(Customer customer);

	boolean addTransaction(Long custLoyaltyCardNumber, Transaction transaction);
}
