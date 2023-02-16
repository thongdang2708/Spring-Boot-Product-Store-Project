package com.ltp.Store.exception;

public class RoleNotFoundWithDescriptionException extends RuntimeException {
    public RoleNotFoundWithDescriptionException(String exceptionText) {
        super(exceptionText);
    }
}
