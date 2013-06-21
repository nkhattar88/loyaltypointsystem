package com.mettl.loyalty.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.mettl.loyalty.domain.Customer;
import com.mettl.loyalty.domain.LoyaltyType;
import com.mettl.loyalty.domain.Transaction;
import com.mettl.loyalty.domain.dto.TransactionDTO;
import com.mettl.loyalty.exception.LoyaltyProgramException;
import com.mettl.loyalty.service.CustomerService;
import com.mettl.loyalty.service.LoyaltyProgramService;
import com.mettl.loyalty.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{

	private static final Logger LOGGER = Logger.getLogger(TransactionServiceImpl.class);
	
	private CustomerService customerService;
	
	private LoyaltyProgramService loyaltyProgramService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setLoyaltyProgramService(LoyaltyProgramService loyaltyProgramService) {
		this.loyaltyProgramService = loyaltyProgramService;
	}

	public void processTransactions(List<TransactionDTO> transactionDTOs) throws LoyaltyProgramException {
		if(transactionDTOs == null || transactionDTOs.isEmpty()){
			throw new LoyaltyProgramException("No Transactions to process");
		}
		
		for(TransactionDTO transactionDTO : transactionDTOs){
			try {
				
				Long loyaltyCardNumber = transactionDTO.getLoyalityCardNumber();
				if(loyaltyCardNumber == null){
					LOGGER.error("No LoyaltyCardNumber found for Transaction with Id:"+transactionDTO.getTransactionId());
					continue;
				}
				Customer customer = customerService.fetchCustomerByLoyalityCardNumber(loyaltyCardNumber);
				if(customer == null){
					LOGGER.error("No Customer fetched with LoyalityCardNumber:"+loyaltyCardNumber);
					continue;
				}
				
				double purchaseAmount = transactionDTO.getPurchaseAmount();
				LoyaltyType loyaltyType = loyaltyProgramService.computeBySpending(customer.getCurrentYearTotal() + purchaseAmount);
				
				Transaction transaction = createTransaction(loyaltyCardNumber, loyaltyType, transactionDTO);
				if(transaction == null){
					continue;
				}
				LOGGER.info("New Transaction with Id :"+transaction.getTransactionId()+" added to Customer with Loyalty Card Number"+loyaltyCardNumber);
				customer.setLoyaltyType(loyaltyType);
				updateCustomerInfo(transactionDTO, customer);
			} catch (Exception e) {
				LOGGER.error("Exception occurred while processing transaction:"+transactionDTO+". Stacktrace : "+e);
			}
		}
	}

	private void updateCustomerInfo(TransactionDTO transactionDTO, Customer customer) {
		if(StringUtils.isNotBlank(transactionDTO.getUserName())){
			customer.setName(transactionDTO.getUserName());
		}
		if(StringUtils.isNotBlank(transactionDTO.getUserEmail())){
			customer.setEmail(transactionDTO.getUserEmail());
		}
	}
	
	private Transaction createTransaction(Long loyaltyCardNumber, LoyaltyType loyaltyType, TransactionDTO transactionDTO){
		int loyaltyPoints = loyaltyProgramService.computeLoyaltyPointsByLoyaltyTypeAndSpending(loyaltyType, transactionDTO.getPurchaseAmount());
		Transaction transaction = new Transaction(transactionDTO.getTransactionId(),transactionDTO.getPurchaseDate(),transactionDTO.getPurchaseAmount(),loyaltyPoints);
		if(!customerService.addTransaction(loyaltyCardNumber,transaction)){
			return null;
		}
		return transaction;
	}

}
