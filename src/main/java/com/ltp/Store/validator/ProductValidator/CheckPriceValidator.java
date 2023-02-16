package com.ltp.Store.validator.ProductValidator;

import com.ltp.Store.entity.Product;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPriceValidator implements ConstraintValidator<CheckPrice, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {

        return value >= 0;
    }
}
