package com.mettl.loyalty.domain;

import com.mettl.loyalty.utils.StringUtils;

public class Customer {

	private Long id;
	private String name;
	private String email;
	private LoyaltyType loyaltyType = LoyaltyType.NONE;
	private String loyaltyCardNumber = StringUtils.BLANK;
	private int loyaltyPoints = 0;
	private double currentYearTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLoyaltyCardNumber() {
		return loyaltyCardNumber;
	}

	public void setLoyaltyCardNumber(String loyaltyCardNumber) {
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

	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", loyaltyType="
				+ loyaltyType + ", loyaltyCardNumber=" + loyaltyCardNumber
				+ ", loyaltyPoints=" + loyaltyPoints + "]";
	}

}
