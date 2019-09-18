package com.uniyaz.mobiltif.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ONGUC on 25.02.2018.
 */

public class DateUtils {

    public static Date getConvertedDateFromString(String stringDate) {
        SimpleDateFormat dateFormat = getSimpleDateFormat();

        Date date = null;
        if (stringDate != null) {
            try {
                date = dateFormat.parse(stringDate);
            } catch (ParseException e) {
                date = null;
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String getConvertedStringFromDate(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = getSimpleDateFormat();
            return dateFormat.format(date);
        }
        return null;
    }

    public static String getNewDateFormat(String firsDateFormat) {
        if (firsDateFormat != null && !"".equals(firsDateFormat)) {
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");  //yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
            try {
                Date date = simpleDateFormat1.parse(firsDateFormat);
                return DateUtils.getConvertedStringFromDate(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }
}
