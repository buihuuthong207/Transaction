package com.thong.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.thong.dao.BankDAO;
import com.thong.dao.impl.BankDAOImpl;
import com.thong.exception.AccountNotFoundException;
import com.thong.exception.InsufficientBalanceException;
import com.thong.model.Account;
import com.thong.util.DBUtil;

public class BankServiceImpl implements BankService{

	BankDAO bankDAO = new BankDAOImpl();
	@Override
	public void fundTransfer(Account fromAccount, Account toAccount, Double amount) throws SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		try{
			connection.setAutoCommit(false);
			
			bankDAO.withdraw(connection,fromAccount, toAccount, amount);
			bankDAO.deposit(connection,fromAccount, toAccount, amount);
			
			connection.commit();

		} catch (Exception e) {
			if(connection != null){
				connection.rollback();
			}
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			if(connection != null){
				connection.close();
			}
		}
	}

	
	//Without handling transaction
/*	@Override
	public void fundTransfer(Account fromAccount, Account toAccount, Double amount) throws SQLException, AccountNotFoundException, InsufficientBalanceException {
		Connection connection = DBUtil.getConnection();
		bankDAO.withdraw(connection, fromAccount, toAccount, amount);
		bankDAO.deposit(connection, fromAccount, toAccount, amount);
	}*/
}
