package com.caravelo.api.exception;

import com.caravelo.business.exception.RouteNotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		return new ResponseEntity<>(new JsonResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = RouteNotFoundException.class)
	public ResponseEntity<?> handleRouteNotFoundExceptionException(Exception e) {
		return new ResponseEntity<>(new JsonResponse(e.getMessage()), HttpStatus.NOT_FOUND);
	}

	@Data
	private static class JsonResponse {
		String message;


		public JsonResponse(String message) {
			super();
			this.message = message;
		}

	}
}
