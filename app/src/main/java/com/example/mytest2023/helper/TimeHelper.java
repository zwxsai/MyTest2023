package com.example.mytest2023.helper;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 钟文祥 on 2019/2/19.
 * Describer:时间 辅助类   yyyyMMddHHmmssSSS
 */
public class TimeHelper {
    public static final String dateFormat = "yyyyMMddHHmmssSSS";

    public static String currentTime() {
        return currentTime("yyyy/MM/dd/ HH:mm:ss:SSS", false);
    }

    /** 当前时间 */
    public static String currentTime(String dateFormat, boolean regular) {
        return data2Time(dateFormat, new Date(), regular);
    }

    /** 获取当前时间戳 */
    public static long currentTime2Unix() {
        return new Date().getTime();
    }

    /** 字符串转date */
    public static Date time2Date(String dateFormat, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * @param dateFormat
     * @param millSec
     * @param regular 是否变为文件名格式
     * @return
     */
    public static String long2Time(String dateFormat, Long millSec, boolean regular) {
        Date date = new Date(millSec);
        return data2Time(dateFormat, date, regular);
    }

    public static String data2Time(String dateFormat, Date date, boolean regular) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        if (regular) {
            return FileHelper.toRegularFileName(sdf.format(date));
        }
        return sdf.format(date);
    }


    /** 比较日期早晚 */
    public static int compare_date(String date1, String date2, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            return compare_date(dt1, dt2);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /** 比较日期早晚 */
    public static int compare_date(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            System.out.println("dt1 在dt2后");
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            System.out.println("dt1在dt2前");
            return -1;
        } else {
            return 0;
        }
    }


    /** 两时间相差 秒 */
    public static int differ_date(Date date1, Date date2) {
        long differ = date1.getTime() - date2.getTime();
        Log.e("TesterActivity", "differ_date: " + differ);
        return (int) differ / 1000;
    }

    /** 两时间相差 秒 */
    public static int differ_date(String date1, String date2, String dateFormat) {

        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            return differ_date(dt1, dt2);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    //获取时间前后的时间
    public static String addTime(int field, int expiredIn, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        //这里用的是分钟，也可以用小时，或者是天...
        cal.set(field, expiredIn); //当前分钟的前后分钟，
        //格式化指定形式的时间
        String currentTime = new SimpleDateFormat(dateFormat).format(cal.getTime());//获取到完整的时间
        return currentTime;
    }

}
