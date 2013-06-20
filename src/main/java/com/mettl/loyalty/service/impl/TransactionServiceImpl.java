package com.mettl.loyalty.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.mettl.loyalty.domain.dto.TransactionDTO;
import com.mettl.loyalty.exception.LoyaltyProgramException;
import com.mettl.loyalty.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{

	private static final Logger LOGGER = Logger.getLogger(TransactionServiceImpl.class);
	
	public void processTransactions(List<TransactionDTO> transactionDTOs) throws LoyaltyProgramException {
		if(transactionDTOs == null || transactionDTOs.isEmpty()){
			throw new LoyaltyProgramException("No Transactions to process");
		}
		
		for(TransactionDTO transactionDTO : transactionDTOs){
			try {
				
			} catch (Exception e) {
				LOGGER.error("Exception occurred while processing transaction:"+transactionDTO+". Stacktrace : "+e);
			}
		}
	}

}
