package com.ltp.Store.exception;

public class OrderNotFoundWithIdException extends RuntimeException {
    public OrderNotFoundWithIdException(Long id) {
        super("This order id " + id + " does not exist!");
    }
}
