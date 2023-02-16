package com.ltp.Store.exception;

public class UserNotFoundWithIdException extends RuntimeException {
    public UserNotFoundWithIdException(Long id) {
        super("This user id " + id + " does not exist");
    }
}
