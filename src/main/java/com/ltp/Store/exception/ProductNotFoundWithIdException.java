package com.ltp.Store.exception;

public class ProductNotFoundWithIdException extends RuntimeException {
    public ProductNotFoundWithIdException(Long productId) {
        super("This product id " + productId + " does not exist!");
    }
}
