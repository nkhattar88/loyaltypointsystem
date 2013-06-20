package com.mettl.loyalty.domain.dto;

import java.util.Date;

import com.mettl.loyalty.utils.StringUtils;

public class TransactionDTO {

	private Long transactionId;
	private Date purchaseDate;
	private double purchaseAmount;
	private String loyalityCardNumber = StringUtils.BLANK;
	private String userName = StringUtils.BLANK;
	private String userEmail = StringUtils.BLANK;
	
	public TransactionDTO(Long transactionId, Date purchaseDate, double purchaseAmount) {
		this.transactionId = transactionId;
		this.purchaseDate = purchaseDate;
		this.purchaseAmount = purchaseAmount;
	}

	public String getLoyalityCardNumber() {
		return loyalityCardNumber;
	}

	public void setLoyalityCardNumber(String loyalityCardNumber) {
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
