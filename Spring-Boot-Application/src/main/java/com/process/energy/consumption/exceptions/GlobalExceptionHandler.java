package com.process.energy.consumption.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

/**GlobalExceptionHandler handles all the exceptions in Energy Consumption application.
 * @author GAGAN
 *
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal Server error occured")
	@ExceptionHandler(NullPointerException.class)
	public void handleNullPointerException(){
		log.error("Null Pointer handler executed");
	}

}
