package com.dicipline.SystemUser.Services.Exceptions;

public class DataErrorException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataErrorException(String msg) {
		super(msg);
	}
}
