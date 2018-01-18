package com.framework.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private Throwable throwable;
	
	public ValidationException(){}

	public ValidationException(String message){
		super(message);
		this.message = message;
	}
	
	public ValidationException(String message, Throwable throwable) {
		super(message, throwable);
		this.throwable = throwable;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
