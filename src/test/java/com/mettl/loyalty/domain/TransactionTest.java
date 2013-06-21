package com.mettl.loyalty.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mettl.loyalty.exception.LoyaltyProgramException;

public class TransactionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=LoyaltyProgramException.class)
	public void shouldConstructTransactionThrowExceptionForWrongTransactionInfo() throws LoyaltyProgramException {
		new Transaction(null,null,-1);
	}
	
	@Test
	public void shouldConstructTransaction() throws LoyaltyProgramException {
		Long transactionId = 23456789L;
		Transaction transaction = new Transaction(transactionId, new Date(),1000);
		assertEquals(transactionId, transaction.getTransactionId());
	}

}
