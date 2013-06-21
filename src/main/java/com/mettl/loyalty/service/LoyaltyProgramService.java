package com.mettl.loyalty.service;

import java.util.Arrays;

import com.mettl.loyalty.comparator.LoyaltyLimitComparator;
import com.mettl.loyalty.domain.LoyaltyType;

public class LoyaltyProgramService {
	
	public LoyaltyType computeLoyaltyTypeBySpending(double spending){
		LoyaltyType type = null;
		LoyaltyType[] values = LoyaltyType.values();
		Arrays.sort(values, new LoyaltyLimitComparator());
		for(LoyaltyType loyaltyType : values){
			if(spending > loyaltyType.getMinLimit()){
				type = loyaltyType;	
				break;
			}
		}
		return type;
	}
	
	public int computeLoyaltyPointsByLoyaltyTypeAndSpending(LoyaltyType loyaltyType, double purchaseAmount){
		if(loyaltyType.getPurchaseFactor() != 0){
			return loyaltyType.getPointFactor() * (int)(purchaseAmount/loyaltyType.getPurchaseFactor());
		}
		return 0;
	}
	
}
