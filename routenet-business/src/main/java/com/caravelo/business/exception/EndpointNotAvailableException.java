package com.caravelo.business.exception;

public class EndpointNotAvailableException extends RuntimeException {

	public EndpointNotAvailableException(String message) {
		super(message);
	}

	public EndpointNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}
}
