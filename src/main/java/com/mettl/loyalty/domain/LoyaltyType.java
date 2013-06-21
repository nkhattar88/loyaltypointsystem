package com.mettl.loyalty.domain;

public enum LoyaltyType {
	
	GOLD(25,500,50000), SILVER(2,100,25000), NORMAL(1,100,0);
	
	private LoyaltyType(int pointFactor, double purchaseFactor,double minLimit) {
		this.pointFactor = pointFactor;
		this.purchaseFactor = purchaseFactor;
		this.minLimit = minLimit;
	}
	
	private int pointFactor;
	private double purchaseFactor;
	private double minLimit;
	
	
	public int getPointFactor() {
		return pointFactor;
	}

	public double getPurchaseFactor() {
		return purchaseFactor;
	}

	public double getMinLimit() {
		return minLimit;
	}
}
