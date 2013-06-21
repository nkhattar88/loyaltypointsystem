package com.mettl.loyalty.repository;

import java.util.ArrayList;
import java.util.List;

import com.mettl.loyalty.domain.Customer;
import com.mettl.loyalty.domain.Transaction;
import com.mettl.loyalty.exception.LoyaltyProgramException;

public class CustomerRepository {

	private List<Customer> customers = new ArrayList<Customer>();

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer findByLoyaltyCardNumber(Long loyaltyCardNumber) throws LoyaltyProgramException {
		int index = customers.indexOf(new Customer(loyaltyCardNumber));
		if(index > -1){
			return customers.get(index);
		}
		return null;
	}

	public boolean addCustomer(Customer customer) {
		if(customers.contains(customer)){
			return false;
		}
		return customers.add(customer);
	}

	public boolean addTransaction(Long loyaltyCardNumber, Transaction transaction) throws LoyaltyProgramException {
		Customer customer = findByLoyaltyCardNumber(loyaltyCardNumber);
		if(customer == null || !customer.getTransactions().add(transaction)){
			return false;
		}
		customer.setLoyaltyPoints(customer.getLoyaltyPoints() + transaction.getPointsEarned());
		customer.setCurrentYearTotal(customer.getCurrentYearTotal() + transaction.getPurchaseAmount());
		return true;
	}

	public List<Customer> findAll() {
		//TODO : create a copy of All Customers and it's Transactions;
		return customers;
	}

}
