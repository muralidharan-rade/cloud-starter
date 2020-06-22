package com.nativejava.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.BAD_REQUEST)
public class UserInputNotValidException extends RuntimeException {

	public UserInputNotValidException(String message) {
		super(message);
	}

}
