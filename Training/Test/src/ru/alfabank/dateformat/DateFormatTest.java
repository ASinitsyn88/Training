package ru.alfabank.dateformat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {

    public static void main(String[] args) {

        Date date = null;
        String strDate = 2016 + "." + "8" + ".02";
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
