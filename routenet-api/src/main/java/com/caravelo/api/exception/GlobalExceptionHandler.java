package com.caravelo.api.exception;

import com.caravelo.business.exception.RouteNotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Global exception handler to manage the error responses on the API
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		return new ResponseEntity<>(new JsonResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Manage the Exception when a route not found. We can set 500 if we don't want to provide so much information to the request
	 * @param e original exception
	 * @return
	 */
	@ExceptionHandler(value = RouteNotFoundException.class)
	public ResponseEntity<?> handleRouteNotFoundExceptionException(Exception e) {
		return new ResponseEntity<>(new JsonResponse(e.getMessage()), HttpStatus.NOT_FOUND);
	}

	/**
	 * Json response for the exceptions
	 */
	@Data
	private static class JsonResponse {
		String message;


		public JsonResponse(String message) {
			super();
			this.message = message;
		}

	}
}
