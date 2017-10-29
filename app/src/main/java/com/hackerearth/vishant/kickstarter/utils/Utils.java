package com.hackerearth.vishant.kickstarter.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vishant on 8/13/2017.
 */

public class Utils {

    public static final String BaseUrl = "https://www.kickstarter.com/";
    public static final String BaseHost = "www.kickstarter.com";

    public static Date getDateFromString(String date) {
        String[] strings = date.split("T");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        String day = strings[0];
        String time = strings[1].split("-")[0];
        Date d = null;
        try {
            d = simpleDateFormat.parse(day + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static int getDayDiff(Date d1, Date d2) {
        Date current_time = new Date(System.currentTimeMillis());
        long diff = d1.getTime() - d2.getTime();
        return (int) ((((diff / 1000) / 60) / 60) / 24);
    }

    public static String getUrl(String url) {
        return (BaseUrl + url);
    }
}
