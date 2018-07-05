package com.thong.exception;

public class InsufficientBalanceException extends Exception{
	private static final long serialVersionUID = -7562695630517823112L;

	public InsufficientBalanceException(String message) {
		super(message);
	}
}
