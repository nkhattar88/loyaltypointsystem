package com.mettl.loyalty.domain;

import java.util.Date;

import com.mettl.loyalty.exception.LoyaltyProgramException;

public class Transaction {

	private Long transactionId;
	private Date purchaseDate;
	private double purchaseAmount = 0.0;
	private int pointsEarned;

	public Transaction(Long transactionId) throws LoyaltyProgramException {
		if(transactionId == null){
			throw new LoyaltyProgramException("Transaction Id cannot be null");
		}
		this.transactionId = transactionId;
	}
	
	public Transaction(Long transactionId, Date purchaseDate,
			double purchaseAmount, int loyaltyPoints) {
		this.transactionId = transactionId;
		this.purchaseDate = purchaseDate;
		this.purchaseAmount = purchaseAmount;
		this.pointsEarned = loyaltyPoints;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId
				+ ", purchaseDate=" + purchaseDate + ", purchaseAmount="
				+ purchaseAmount + ", pointsEarned=" + pointsEarned + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Transaction && this.transactionId != null && this.transactionId.equals(((Transaction) obj).getTransactionId())){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.transactionId.hashCode();
	}
	
}
