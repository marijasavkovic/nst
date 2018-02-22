package com.master.nst.sheard.validation;

import com.master.nst.sheard.errors.Error;

import java.util.Date;

public class DateValidator {

    public static Error dateInPast(Date date, String columnName) {
        if(date == null){
            return new Error(columnName + " must be in the past!");
        }

        if (!date.before(new Date())) {
            return new Error(columnName + " must be in the past!");
        }

        return null;
    }
}
