package com.mettl.loyalty.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mettl.loyalty.domain.Customer;
import com.mettl.loyalty.domain.Transaction;
import com.mettl.loyalty.exception.LoyaltyProgramException;
import com.mettl.loyalty.repository.CustomerRepository;
import com.mettl.loyalty.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	CustomerServiceImpl customerService;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Before
	public void setUp() throws Exception {
		customerService = new CustomerServiceImpl();
		customerService.setCustomerRepository(customerRepository);
	}

	@After
	public void tearDown(){
		customerService = null;
	}

	@Test
	public void shouldAddTransactionReturnsFalseForNullCardNumber() {
		assertFalse(customerService.addTransaction(null, null));
	}

	@Test
	public void shouldAddTransactionReturnsFalseForNullTransaction() {
		assertFalse(customerService.addTransaction(11001L, null));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldAddTransactionReturnsFalseForExceptionOccurred() throws LoyaltyProgramException {
		when(customerRepository.addTransaction(anyLong(),any(Transaction.class))).thenThrow(LoyaltyProgramException.class);
		assertFalse(customerService.addTransaction(11001L,new Transaction(2200L, new Date(),1000)));
		verify(customerRepository).addTransaction(anyLong(), any(Transaction.class));
	}
	
	@Test
	public void shouldAddTransactionReturnsTrue() throws LoyaltyProgramException {
		when(customerRepository.addTransaction(anyLong(),any(Transaction.class))).thenReturn(true);
		assertTrue(customerService.addTransaction(11001L,new Transaction(2200L, new Date(),1000)));
		verify(customerRepository).addTransaction(anyLong(), any(Transaction.class));
	}
	
	@Test(expected=LoyaltyProgramException.class)
	public void shouldFetchCustomerThrowsErrorForNullCardNumber() throws LoyaltyProgramException{
		assertNull(customerService.fetchCustomerByLoyalityCardNumber(null));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldFetchCustomerReturnsNullForExceptionOccurred() throws LoyaltyProgramException{
		when(customerRepository.findByLoyaltyCardNumber(anyLong())).thenThrow(LoyaltyProgramException.class);
		when(customerRepository.addCustomer(any(Customer.class))).thenReturn(false);
		assertNull(customerService.fetchCustomerByLoyalityCardNumber(1100L));
		verify(customerRepository).addCustomer(any(Customer.class));
		verify(customerRepository).findByLoyaltyCardNumber(anyLong());
	}
	
	@Test
	public void shouldFetchCustomerReturnsCorrectForCorrectLoyaltyCardNumber() throws LoyaltyProgramException{
		Long loyaltyCardNumber = 1100L;
		Customer customer = new Customer(loyaltyCardNumber);
		when(customerRepository.findByLoyaltyCardNumber(loyaltyCardNumber)).thenReturn(customer);
		assertEquals(customer,customerService.fetchCustomerByLoyalityCardNumber(loyaltyCardNumber));
		verify(customerRepository).findByLoyaltyCardNumber(loyaltyCardNumber);
	}
	
	@Test
	public void shouldFetchCustomerReturnsCorrectForIncorrectCardNumber() throws LoyaltyProgramException{
		Long loyaltyCardNumber = 1100L;
		when(customerRepository.findByLoyaltyCardNumber(loyaltyCardNumber)).thenReturn(null);
		when(customerRepository.addCustomer(any(Customer.class))).thenReturn(true);
		Customer customer = customerService.fetchCustomerByLoyalityCardNumber(loyaltyCardNumber);
		assertNotNull(customer);
		assertEquals(loyaltyCardNumber,customer.getLoyaltyCardNumber());
		verify(customerRepository).addCustomer(any(Customer.class));
		verify(customerRepository).findByLoyaltyCardNumber(loyaltyCardNumber);
	}
	

}
