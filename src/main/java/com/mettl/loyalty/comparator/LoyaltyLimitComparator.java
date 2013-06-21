package com.mettl.loyalty.comparator;

import java.util.Comparator;

import com.mettl.loyalty.domain.LoyaltyType;

public class LoyaltyLimitComparator implements Comparator<LoyaltyType>{
	public int compare(LoyaltyType o1, LoyaltyType o2) {
		return o1.getMinLimit() < o2.getMinLimit() ? 1:-1;
	}
}
