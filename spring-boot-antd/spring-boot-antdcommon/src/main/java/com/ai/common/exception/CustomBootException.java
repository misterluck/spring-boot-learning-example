package com.ai.common.exception;

public class CustomBootException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomBootException(String message){
		super(message);
	}
	
	public CustomBootException(Throwable cause)
	{
		super(cause);
	}
	
	public CustomBootException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
