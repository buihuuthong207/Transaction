package com.thong.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thong.dao.BankDAO;
import com.thong.exception.AccountNotFoundException;
import com.thong.exception.InsufficientBalanceException;
import com.thong.model.Account;

public class BankDAOImpl implements BankDAO {

	private final String UPDATE_ACCOUNT_SQL = "UPDATE citi_bank set account_balance = ? WHERE account_no=?";
	@Override
	public void withdraw(Connection connection,Account fromAccount, Account toAccount, Double amount) throws SQLException, AccountNotFoundException, InsufficientBalanceException {
		Account fromAccountFromDb = getCurrentAccountDetail(connection, fromAccount.getAccountNumber());
		if(fromAccountFromDb !=null){
			if(fromAccountFromDb.getAccountBalance()<amount){
				throw new InsufficientBalanceException("Insufficient funds");
			}
		}else{
			throw new AccountNotFoundException("Source account doesn't exist..");
		}
		
		PreparedStatement ps = connection.prepareStatement(UPDATE_ACCOUNT_SQL);
		Double updateBalance = fromAccountFromDb.getAccountBalance()-amount;
		
		ps.setDouble(1, updateBalance);
		ps.setLong(2, fromAccount.getAccountNumber());
		
		int executeUpdate = ps.executeUpdate();
		if(executeUpdate == 1){
			System.out.println("Amount $"+amount+" is transferred from account:"+fromAccount.getAccountNumber()+" to account:"+toAccount.getAccountNumber());
		}
	}

	@Override
	public void deposit(Connection connection,Account fromAccount, Account toAccount, Double amount) throws SQLException, AccountNotFoundException {
		Account toAccountFromDb = getCurrentAccountDetail(connection, toAccount.getAccountNumber());
		if(toAccountFromDb == null)
			throw new AccountNotFoundException("Destination account doesn't exist..");
	
		PreparedStatement ps = connection.prepareStatement(UPDATE_ACCOUNT_SQL);
		Double updateBalance = toAccountFromDb.getAccountBalance()+amount;
		
		ps.setDouble(1, updateBalance);
		ps.setLong(2, toAccount.getAccountNumber());
		
		int executeUpdate = ps.executeUpdate();
		if(executeUpdate == 1){
			System.out.println("Amount $"+amount+" is deposited in account:"+toAccount.getAccountNumber());
		}
	}
	
	private Account getCurrentAccountDetail(Connection connection, Long fromAccountNumber) throws SQLException{
		String GET_ACCOUNT_SQL="SELECT *FROM citi_bank WHERE account_no=?";
		try(PreparedStatement ps=connection.prepareStatement(GET_ACCOUNT_SQL)){
			ps.setLong(1, fromAccountNumber);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Account dBaccount = new Account();
				dBaccount.setAccountNumber(rs.getLong("account_no"));
				dBaccount.setAccountBalance(rs.getDouble("account_balance"));
				dBaccount.setAccountHolderName(rs.getString("account_holder_name"));
				dBaccount.setAccountType(rs.getString("account_type"));
				return dBaccount;
			}
		}
		return null;
	}
}
