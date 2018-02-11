package com.master.nst.sheard.validation;

import com.master.nst.sheard.exception.ValidationException;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateValidator {

    public static void dateInPast(Date date, String columnName) {
        Assert.notNull(date, columnName.toUpperCase() +" cannot be null!");

        if (date != null && !date.before(new Date())) {
            throw new ValidationException(columnName + " must be in the past!");
        }
    }
}
