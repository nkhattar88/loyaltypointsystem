package com.mettl.loyalty.service;

import java.util.List;

import com.mettl.loyalty.domain.dto.TransactionDTO;
import com.mettl.loyalty.exception.LoyaltyProgramException;

public interface TransactionService {

	void processTransactions(List<TransactionDTO> transactionDTOs) throws LoyaltyProgramException;
}
