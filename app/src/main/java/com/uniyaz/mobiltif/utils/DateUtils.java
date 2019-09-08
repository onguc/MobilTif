package com.uniyaz.mobiltif.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ONGUC on 25.02.2018.
 */

public class DateUtils {

    public static Date getConvertedDateFromString(String stringDate){
        SimpleDateFormat dateFormat=getSimpleDateFormat();

        Date date=null;
        if(stringDate!=null){
            try {
                date=dateFormat.parse(stringDate);
            } catch (ParseException e) {
                date=null;
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String getConvertedStringFromDate(Date date){
        String stringDate=null;
        SimpleDateFormat dateFormat=getSimpleDateFormat();
        if(date!=null){
            stringDate=dateFormat.format(date);
        }
        return stringDate;
    }

    private static SimpleDateFormat getSimpleDateFormat(){
        return new SimpleDateFormat("dd.MM.yyyy");
    }
}
