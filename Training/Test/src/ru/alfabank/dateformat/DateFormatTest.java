package ru.alfabank.dateformat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss.SSS");
        String strDate = sdf.format(new Date());
        System.out.println(strDate);
    }
}
