package com.thong.client;

import java.sql.SQLException;

import com.thong.exception.AccountNotFoundException;
import com.thong.exception.InsufficientBalanceException;
import com.thong.model.Account;
import com.thong.service.BankService;
import com.thong.service.BankServiceImpl;

public class ClientTest {

	public static void main(String[] args) {
		
		BankService bankService = new BankServiceImpl();
		
		Double transferAmout = 1000.00;
		Account fromAccount = new Account();
		fromAccount.setAccountNumber(9876835861L);
		
		Account toAccount = new Account();
		toAccount.setAccountNumber(9876598791L);
		
		try {
			bankService.fundTransfer(fromAccount, toAccount, transferAmout);
		} catch (SQLException | AccountNotFoundException | InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		}
	}

}
