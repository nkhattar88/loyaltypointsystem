package com.mettl.loyalty.domain.dto;

import java.util.Date;

public class TransactionDTO {

	private Long transactionId;
	private Date purchaseDate;
	private double purchaseAmount;
	private Long loyalityCardNumber;
	private String userName;
	private String userEmail;

	public TransactionDTO(Long transactionId, Date purchaseDate,
			double purchaseAmount) {
		this.transactionId = transactionId;
		this.purchaseDate = purchaseDate;
		this.purchaseAmount = purchaseAmount;
	}

	public Long getLoyalityCardNumber() {
		return loyalityCardNumber;
	}

	public void setLoyalityCardNumber(Long loyalityCardNumber) {
		this.loyalityCardNumber = loyalityCardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}
}
