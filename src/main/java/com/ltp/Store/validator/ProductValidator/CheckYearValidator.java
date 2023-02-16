package com.ltp.Store.validator.ProductValidator;

import java.util.Calendar;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckYearValidator implements ConstraintValidator<CheckYear, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        int thatYear = 0;
        thatYear += value;

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int thisYear = calendar.getWeekYear();

        return thatYear <= thisYear;

    }

}
