package com.mettl.loyalty.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mettl.loyalty.domain.Customer;
import com.mettl.loyalty.domain.Transaction;
import com.mettl.loyalty.exception.LoyaltyProgramException;

public class CustomerRepositoryTest {

	CustomerRepository customerRepository;
	private static final Long correctLoyaltyCardNumber = 11001L;
	private static Customer customer = null;
	private static final Long incorrectLoyaltyCardNumber = 11002L;
	private static final Long correctTransactionId = 1234567L;
	
	@Before
	public void setUp() throws LoyaltyProgramException{
		customerRepository = new CustomerRepository();
		customer = new Customer(correctLoyaltyCardNumber);
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer);
		customerRepository.setCustomers(customers);
	}

	@After
	public void tearDown(){
	}

	@Test(expected=LoyaltyProgramException.class)
	public void shouldFindByLoyaltyCardNumberThrowsExceptionForNullCardNumber() throws LoyaltyProgramException {
		customerRepository.findByLoyaltyCardNumber(null);
	}

	@Test
	public void shouldFindByLoyaltyCardNumberReturnsNullForIncorrectCardNumber() throws LoyaltyProgramException {
		assertNull(customerRepository.findByLoyaltyCardNumber(11002L));
	}
	
	@Test
	public void shouldFindByLoyaltyCardNumberReturnsCorrectResult() throws LoyaltyProgramException {
		Customer customer = customerRepository.findByLoyaltyCardNumber(correctLoyaltyCardNumber);
		assertNotNull(customer);
		assertEquals(correctLoyaltyCardNumber, customer.getLoyaltyCardNumber());
	}
	
	@Test
	public void shouldAddCustomerReturnsFalseForDuplicateCardNumber() throws LoyaltyProgramException{
		assertFalse(customerRepository.addCustomer(customer));
	}
	
	@Test
	public void shouldAddCustomerReturnsTrue() throws LoyaltyProgramException{
		assertTrue(customerRepository.addCustomer(new Customer(11002L)));
	}
	
	@Test(expected=LoyaltyProgramException.class)
	public void shouldAddTransactionThrowsExceptionForNullCardNumber() throws LoyaltyProgramException{
		customerRepository.addTransaction(null, new Transaction(correctTransactionId, new Date(), 1000));
	}
	
	@Test
	public void shouldAddTransactionReturnsFalseForIncorrectCardNumber() throws LoyaltyProgramException{
		assertFalse(customerRepository.addTransaction(incorrectLoyaltyCardNumber, new Transaction(correctTransactionId, new Date(), 1000)));
	}
	
	@Test
	public void shouldAddTransactionReturnsTrue() throws LoyaltyProgramException{
		double spending = customer.getCurrentYearTotal();
		int points = customer.getLoyaltyPoints();
		double transactionAmount = 1000;
		int transactionPoints = 11;
		assertTrue(customerRepository.addTransaction(customer.getLoyaltyCardNumber(), new Transaction(correctTransactionId, new Date(),transactionAmount, transactionPoints)));
		assertEquals(points + transactionPoints, customer.getLoyaltyPoints());
		assertEquals(String.valueOf((spending + transactionAmount)), String.valueOf(customer.getCurrentYearTotal()));
	}
	
	@Test
	public void shouldAddTransactionReturnsFalseForDuplicateTransactionId() throws LoyaltyProgramException{
		assertTrue(customerRepository.addTransaction(customer.getLoyaltyCardNumber(), new Transaction(correctTransactionId, new Date(),1000, 10)));
		assertFalse(customerRepository.addTransaction(correctLoyaltyCardNumber, new Transaction(correctTransactionId, new Date(), 2000, 20)));
	}

}
