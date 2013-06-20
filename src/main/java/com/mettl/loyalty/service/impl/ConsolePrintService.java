package com.mettl.loyalty.service.impl;

import java.util.List;

import com.mettl.loyalty.domain.Customer;
import com.mettl.loyalty.domain.Transaction;
import com.mettl.loyalty.repository.CustomerRepository;
import com.mettl.loyalty.service.PrintService;

public class ConsolePrintService implements PrintService{

	private CustomerRepository customerRepository;
	
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void printLoyaltyProgramCustomers() {
		List<Customer> loyaltyProgramCustomers = customerRepository.findAll();
		for(Customer customer : loyaltyProgramCustomers){
			printCustomerInformation(customer);
		}
	}

	private void printCustomerInformation(Customer customer) {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer Name:").append(customer.getName());
		builder.append("\n");
		builder.append("Customer Email:").append(customer.getEmail());
		builder.append("\n");
		builder.append("Loyalty Points:").append(customer.getLoyaltyPoints());
		builder.append("\n");
		builder.append("Customer Class:").append(customer.getLoyaltyType());
		builder.append("\n");
		builder.append("Transactions :");
		builder.append("\n");
		for(Transaction transaction : customer.getTransactions()){
			builder.append(transaction.getPurchaseDate()).append("   ").append(transaction.getTransactionId()).append("   ").append(transaction.getPurchaseAmount());
			builder.append("   ").append(transaction.getPointsEarned()).append("\n");
		}
		builder.append("_________________________________________________________________________________________________________").append("\n");
		System.out.println(builder.toString());
	}

}
