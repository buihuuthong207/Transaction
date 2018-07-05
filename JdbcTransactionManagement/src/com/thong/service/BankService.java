package com.thong.service;

import java.sql.SQLException;

import com.thong.exception.AccountNotFoundException;
import com.thong.exception.InsufficientBalanceException;
import com.thong.model.Account;

public interface BankService {

	public void fundTransfer(Account fromAccount,Account toAccount,Double amount) throws SQLException, AccountNotFoundException, InsufficientBalanceException;
}
