package com.dicipline.SystemUser.Services.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("resource not found Id:"+id);
	}
}
