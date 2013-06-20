package com.mettl.loyalty.driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mettl.loyalty.domain.dto.TransactionDTO;
import com.mettl.loyalty.exception.LoyaltyProgramException;
import com.mettl.loyalty.repository.CustomerRepository;
import com.mettl.loyalty.service.LoyaltyProgramService;
import com.mettl.loyalty.service.impl.ConsolePrintService;
import com.mettl.loyalty.service.impl.CustomerServiceImpl;
import com.mettl.loyalty.service.impl.TransactionServiceImpl;

public class MainDriver {
	
	public static void main(String[] args) {
		
		TransactionServiceImpl transactionService = new TransactionServiceImpl();
		CustomerServiceImpl customerService = new CustomerServiceImpl();
		LoyaltyProgramService loyaltyProgramService = new LoyaltyProgramService();
		ConsolePrintService consolePrintService = new ConsolePrintService();
		CustomerRepository customerRepository = new CustomerRepository();
		
		transactionService.setCustomerService(customerService);
		transactionService.setLoyaltyProgramService(loyaltyProgramService);
		customerService.setCustomerRepository(customerRepository);
		consolePrintService.setCustomerRepository(customerRepository);
		
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		transactionDTOs.add(buildTransactionDTO("Abhay", "abhay@demo.com", 11001L, 7402, "22-06-2012 11:23", 2348723L));
		transactionDTOs.add(buildTransactionDTO(null, null, null, 5000, "22-06-2012 13:48", 2348724L));
		transactionDTOs.add(buildTransactionDTO("Anant", "anant@mettl.com", 11002L, 74000, "22-07-2012 11:23", 2348725L));
		transactionDTOs.add(buildTransactionDTO("Abhay1", "abhay@mettl.com", 11001L, 15000, "26-08-2012 11:23", 2348727L));
		transactionDTOs.add(buildTransactionDTO(null, null, 11002L, 7000, "22-09-2012 11:23", 2348729L));
		transactionDTOs.add(buildTransactionDTO("Ashish", "ashish@demo.com", 11005L, 26000, "22-10-2012 11:23", 2348759L));
		
		try {
			transactionService.processTransactions(transactionDTOs);
		} catch (LoyaltyProgramException e) {
			e.printStackTrace();
		}
		
		consolePrintService.printLoyaltyProgramCustomers();
		
	}

	private static TransactionDTO buildTransactionDTO(String name, String email, Long cardNumber, double purchaseAmnt, String purchaseDateStr, Long transactionId) {
		
		Date purchaseDate = null;
		try {
			purchaseDate = new SimpleDateFormat("dd-MM-yyyy mm:hh").parse(purchaseDateStr);
		} catch (ParseException e) {
		}
		TransactionDTO dto = new TransactionDTO(transactionId, purchaseDate, purchaseAmnt);
		dto.setUserName(name);
		dto.setUserEmail(email);
		dto.setLoyalityCardNumber(cardNumber);
		return dto;
		
		
	}
}
