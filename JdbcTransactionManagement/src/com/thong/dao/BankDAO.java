package com.thong.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.thong.exception.AccountNotFoundException;
import com.thong.exception.InsufficientBalanceException;
import com.thong.model.Account;

public interface BankDAO {

	public void withdraw(Connection connection,Account fromAccount,Account toAccount,Double amount) throws SQLException, AccountNotFoundException, InsufficientBalanceException;
	public void deposit(Connection connection,Account fromAccount,Account toAccount,Double amount)throws SQLException, AccountNotFoundException;
}
