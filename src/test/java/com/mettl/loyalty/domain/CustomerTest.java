package com.mettl.loyalty.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mettl.loyalty.exception.LoyaltyProgramException;
import com.mettl.loyalty.util.LoyaltyProgramConstants;

public class CustomerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=LoyaltyProgramException.class)
	public void shouldConstructCustomerThrowExceptionForNullLoyaltyCardNumber() throws LoyaltyProgramException {
		new Customer(null);
	}
	
	@Test
	public void shouldConstructCustomer() throws LoyaltyProgramException {
		Long loyaltyCardNumber = 11001L;
		Customer customer = new Customer(loyaltyCardNumber);
		assertEquals(loyaltyCardNumber, customer.getLoyaltyCardNumber());
		assertEquals(LoyaltyProgramConstants.NEW_CUSTOMER_LOYALTY_POINTS,customer.getLoyaltyPoints());
	}

}
