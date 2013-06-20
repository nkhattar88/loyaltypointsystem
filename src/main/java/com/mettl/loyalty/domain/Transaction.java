package com.mettl.loyalty.domain;

import java.util.Date;

public class Transaction {

	private Long id;
	private Date purchaseDate;
	private double amount = 0.0;
	private int pointsEarned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", purchaseDate=" + purchaseDate
				+ ", amount=" + amount + ", pointsEarned=" + pointsEarned + "]";
	}

}
