package com.master.nst.sheard.validation;

import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateValidator {

    public static List<Error> dateInFuture(LocalDate date, String columnName) {
        Assert.notNull(date, columnName.toUpperCase() +" cannot be null!");

        List<Error> errors = new ArrayList<>();

        if (date != null && !date.isBefore(LocalDate.now())) {
            errors.add(new Error(columnName.toUpperCase()+" must be in the future!"));
            return errors;
        }

        return errors;
    }
}
