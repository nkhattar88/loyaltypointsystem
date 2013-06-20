package com.mettl.loyalty.domain;

import java.util.Set;
import java.util.TreeSet;

import com.mettl.loyalty.comparator.TransactionDateComparator;
import com.mettl.loyalty.exception.LoyaltyProgramException;
import com.mettl.loyalty.utils.StringUtils;

public class Customer {

	private static final int DEFAULT_LOYALTY_POINTS = 100;
	private String name = StringUtils.BLANK;
	private String email = StringUtils.BLANK;
	private LoyaltyType loyaltyType;
	private Long loyaltyCardNumber;
	private int loyaltyPoints;
	private double currentYearTotal = 0.0;
	
	private Set<Transaction> transactions = new TreeSet<Transaction>(new TransactionDateComparator());

	public Customer(Long loyaltyCardNumber) throws LoyaltyProgramException {
		if(loyaltyCardNumber == null){
			throw new LoyaltyProgramException("Loyalty Card Number cannot be null");
		}
		this.loyaltyCardNumber = loyaltyCardNumber;
		addDefaultPoints();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LoyaltyType getLoyaltyType() {
		return loyaltyType;
	}

	public void setLoyaltyType(LoyaltyType loyaltyType) {
		this.loyaltyType = loyaltyType;
	}

	public Long getLoyaltyCardNumber() {
		return loyaltyCardNumber;
	}

	public void setLoyaltyCardNumber(Long loyaltyCardNumber) {
		this.loyaltyCardNumber = loyaltyCardNumber;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public double getCurrentYearTotal() {
		return currentYearTotal;
	}

	public void setCurrentYearTotal(double currentYearTotal) {
		this.currentYearTotal = currentYearTotal;
	}

	private void addDefaultPoints() {
		loyaltyPoints += DEFAULT_LOYALTY_POINTS;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", loyaltyType="
				+ loyaltyType + ", loyaltyCardNumber=" + loyaltyCardNumber
				+ ", loyaltyPoints=" + loyaltyPoints + ", currentYearTotal="
				+ currentYearTotal + ", transactions=" + transactions + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Customer && this.loyaltyCardNumber != null && this.loyaltyCardNumber.equals(((Customer) obj).getLoyaltyCardNumber())){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.loyaltyCardNumber.hashCode();
	}
}
