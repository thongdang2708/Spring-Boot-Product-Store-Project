package com.ltp.Store.exception;

public class UserNotFoundWithUsername extends RuntimeException {
    public UserNotFoundWithUsername(String username) {
        super("This username not found!");
    }
}
