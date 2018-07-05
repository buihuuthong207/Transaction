package com.thong.exception;

public class AccountNotFoundException extends Exception{
	private static final long serialVersionUID = -7562695630517823112L;

	public AccountNotFoundException(String message) {
		super(message);
	}
}
