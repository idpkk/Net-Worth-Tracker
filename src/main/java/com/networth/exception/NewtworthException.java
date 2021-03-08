package com.networth.exception;

public class NewtworthException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public NewtworthException(String message) {
		this.message = message;
	}

}
