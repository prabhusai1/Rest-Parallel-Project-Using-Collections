package com.src.model;

import org.springframework.stereotype.Component;

@Component
public class Transaction {
	private int transactionId;
	private long fromAccountNo;
	private long toAccountNo;
	private int amounTransaferred;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public long getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public long getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(long toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public int getAmounTransaferred() {
		return amounTransaferred;
	}

	public void setAmounTransaferred(int amounTransaferred) {
		this.amounTransaferred = amounTransaferred;
	}

}
