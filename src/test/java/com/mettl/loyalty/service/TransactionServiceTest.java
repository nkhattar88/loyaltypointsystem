package com.mettl.loyalty.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mettl.loyalty.domain.Customer;
import com.mettl.loyalty.domain.LoyaltyType;
import com.mettl.loyalty.domain.Transaction;
import com.mettl.loyalty.domain.dto.TransactionDTO;
import com.mettl.loyalty.exception.LoyaltyProgramException;
import com.mettl.loyalty.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	TransactionServiceImpl transactionService;
	
	@Mock
	CustomerService customerService;
	
	@Mock
	LoyaltyProgramService loyaltyProgramService;
	
	@Before
	public void setUp() throws Exception {
		transactionService = new TransactionServiceImpl();
		transactionService.setCustomerService(customerService);
		transactionService.setLoyaltyProgramService(loyaltyProgramService);
	}

	@After
	public void tearDown() throws Exception {
		transactionService = null;
	}

	@Test(expected=LoyaltyProgramException.class)
	public void shouldProcessTransactionsThrowErrorForEmptyDTOs() throws LoyaltyProgramException {
		transactionService.processTransactions(new ArrayList<TransactionDTO>());
	}

	@Test
	public void shouldProcessTransactionsDoNothing() throws LoyaltyProgramException{
		transactionService.processTransactions(Arrays.asList(new TransactionDTO(1234567L, new Date(),1000)));
		
		when(customerService.fetchCustomerByLoyalityCardNumber(anyLong())).thenReturn(null);
		TransactionDTO transactionDTO = new TransactionDTO(1234567L, new Date(),1000);
		transactionDTO.setLoyalityCardNumber(11000L);
		transactionService.processTransactions(Arrays.asList(transactionDTO));
		verify(customerService).fetchCustomerByLoyalityCardNumber(anyLong());
	}
	
	@Test
	public void shouldProcessTransactionReturnSuccess() throws LoyaltyProgramException{
		
		Long loyaltyCardNumber = 11000L;
		when(customerService.fetchCustomerByLoyalityCardNumber(anyLong())).thenReturn(new Customer(loyaltyCardNumber));
		when(loyaltyProgramService.computeLoyaltyTypeBySpending(anyDouble())).thenReturn(LoyaltyType.NORMAL);
		when(loyaltyProgramService.computeLoyaltyPointsByLoyaltyTypeAndSpending(any(LoyaltyType.class),anyDouble())).thenReturn(10);
		when(customerService.addTransaction(anyLong(), any(Transaction.class))).thenReturn(true);
		
		TransactionDTO transactionDTO = new TransactionDTO(1234567L, new Date(),1000);
		transactionDTO.setLoyalityCardNumber(loyaltyCardNumber);
		transactionService.processTransactions(Arrays.asList(transactionDTO));
		
		verify(customerService).addTransaction(anyLong(), any(Transaction.class));
		verify(loyaltyProgramService).computeLoyaltyPointsByLoyaltyTypeAndSpending(any(LoyaltyType.class),anyDouble());
		verify(loyaltyProgramService).computeLoyaltyTypeBySpending(anyDouble());
		verify(customerService).fetchCustomerByLoyalityCardNumber(anyLong());
	}

}
