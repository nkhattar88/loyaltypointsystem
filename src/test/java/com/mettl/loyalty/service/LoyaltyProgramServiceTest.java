package com.mettl.loyalty.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mettl.loyalty.domain.LoyaltyType;

public class LoyaltyProgramServiceTest {

	LoyaltyProgramService loyaltyProgramService;
	
	@Before
	public void setUp(){
		loyaltyProgramService = new LoyaltyProgramService();
	}

	@After
	public void tearDown(){
		loyaltyProgramService = null;
	}

	@Test
	public void shouldComputeLoyaltyTypeBySpendingReturnCorrectResult() {
		
		double goldClassPurchase = LoyaltyType.GOLD.getMinLimit() + 1000;
		double silverClassPurchase = LoyaltyType.SILVER.getMinLimit() + 1000;
		double normalClassPurchase = LoyaltyType.NORMAL.getMinLimit() + 1000;
		double noClassPurchase = 0;
		
		LoyaltyType loyaltyType = loyaltyProgramService.computeLoyaltyTypeBySpending(normalClassPurchase);
		assertEquals(LoyaltyType.NORMAL, loyaltyType);
		
		loyaltyType = loyaltyProgramService.computeLoyaltyTypeBySpending(silverClassPurchase);
		assertEquals(LoyaltyType.SILVER, loyaltyType);
		
		loyaltyType = loyaltyProgramService.computeLoyaltyTypeBySpending(goldClassPurchase);
		assertEquals(LoyaltyType.GOLD, loyaltyType);
		
		loyaltyType = loyaltyProgramService.computeLoyaltyTypeBySpending(noClassPurchase);
		assertNull(loyaltyType);
	}
	
	@Test
	public void shouldComputePointsReturnsCorrectResult(){

		double purchaseAmount = 1000;
		LoyaltyType normalLoyaltyType = LoyaltyType.NORMAL;
		int expectedPoints = normalLoyaltyType.getPointFactor() * (int)(purchaseAmount/normalLoyaltyType.getPurchaseFactor());
		assertEquals(expectedPoints, loyaltyProgramService.computeLoyaltyPointsByLoyaltyTypeAndSpending(normalLoyaltyType, purchaseAmount));
	
	}

}
