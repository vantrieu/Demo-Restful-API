package com.demoapi.demorestfulapi.exceptions;

import java.security.PublicKey;

public class ResourceNotFoundException extends Exception {
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
