package com.group8.backspace.presentation.util;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateParser {
    private DateTime date;

    public DateParser(DateTime date) {
        setDate(date);
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date.toDate());
    }
}
