package com.mettl.loyalty.service;

import com.mettl.loyalty.domain.LoyaltyType;

public class LoyaltyProgramService {
	
	public int computeLoyaltyPointsByLoyaltyTypeAndSpending(LoyaltyType loyaltyType, double purchaseAmount){
		if(loyaltyType.getPurchaseFactor() != 0){
			return loyaltyType.getPointFactor() * (int)(purchaseAmount/loyaltyType.getPurchaseFactor());
		}
		return 0;
	}
}
