package com.nativejava.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nativejava.user.UserInputNotValidException;
import com.nativejava.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ExceptionResponseBean exceptionBean = new ExceptionResponseBean(new Date(), ex.getMessage(),
				ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return new ResponseEntity(exceptionBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request)
			throws Exception {
		ExceptionResponseBean exceptionBean = new ExceptionResponseBean(new Date(), ex.getMessage(),
				ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity(exceptionBean, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserInputNotValidException.class)
	public final ResponseEntity<Object> handleUserInputNotValidException(UserInputNotValidException ex,
			WebRequest request) throws Exception {
		ExceptionResponseBean exceptionBean = new ExceptionResponseBean(new Date(), ex.getMessage(),
				ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity(exceptionBean, HttpStatus.BAD_REQUEST);
	}

}
