package com.mettl.loyalty.domain;

import java.util.Arrays;
import java.util.Comparator;

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
	
	public static LoyaltyType computeBySpending(double spending){
		LoyaltyType type = null;
		LoyaltyType[] values = LoyaltyType.values();
		Arrays.sort(values, new LoyaltyLimitComparator());
		for(LoyaltyType loyaltyType : values){
			if(spending > loyaltyType.getMinLimit()){
				type = loyaltyType;	
			}
		}
		return type;
	}
	
	private static class LoyaltyLimitComparator implements Comparator<LoyaltyType>{

		public int compare(LoyaltyType o1, LoyaltyType o2) {
			return o1.getMinLimit() > o2.getMinLimit() ? 1:-1;
		}
		
	}
}
