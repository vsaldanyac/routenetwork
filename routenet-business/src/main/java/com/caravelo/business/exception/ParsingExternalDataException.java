package com.caravelo.business.exception;

public class ParsingExternalDataException extends RuntimeException {

	public ParsingExternalDataException(String message) {
		super(message);
	}

	public ParsingExternalDataException(String message, Throwable cause) {
		super(message, cause);
	}
}
