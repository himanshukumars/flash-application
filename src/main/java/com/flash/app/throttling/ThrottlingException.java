package com.flash.app.throttling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS, reason = "TOO MANY REQUESTS")
public class ThrottlingException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
