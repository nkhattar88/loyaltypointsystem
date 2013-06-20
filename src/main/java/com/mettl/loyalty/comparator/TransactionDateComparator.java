package com.mettl.loyalty.comparator;

import java.util.Comparator;

import com.mettl.loyalty.domain.Transaction;

public class TransactionDateComparator implements Comparator<Transaction> {

	public int compare(Transaction o1, Transaction o2) {
		return o1.getPurchaseDate().compareTo(o2.getPurchaseDate());
	}
	

}
