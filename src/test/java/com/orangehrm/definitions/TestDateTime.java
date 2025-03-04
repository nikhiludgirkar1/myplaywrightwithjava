package com.orangehrm.definitions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestDateTime {
    public static void main(String[] args) throws ParseException {
        String expectedDateTime = "4/6/2023, 11:43";
        long currentDateTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy, h:mm", Locale.ENGLISH);
        Date date = simpleDateFormat.parse(expectedDateTime.trim());
        long expectedDateTime2 = date.getTime();
        long minutes = 454545489;
        System.out.println(currentDateTime - expectedDateTime2 < minutes * 60000);

    }
}
