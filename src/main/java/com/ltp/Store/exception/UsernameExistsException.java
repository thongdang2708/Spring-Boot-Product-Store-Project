package com.ltp.Store.exception;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String exceptionText) {
        super("Failed to register as " + exceptionText);
    }
}
