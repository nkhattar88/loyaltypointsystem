package com.mettl.loyalty.domain;

public enum LoyaltyType {

	NEW(100,null), GOLD(25,500), SILVER(2,100), NORMAL(1,100), NONE(0,null) ;
	
	private LoyaltyType(int points, Integer purchaseFactor) {
		this.points = points;
		this.purchaseFactor = purchaseFactor;
	}
	
	private int points;
	private Integer purchaseFactor;
	
	public int getPoints() {
		return points;
	}
}
