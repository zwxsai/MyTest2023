package com.example.mytest2023.helper;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Created by 钟文祥 on 2019/2/19.
 * Describer:时间 辅助类   yyyyMMddHHmmssSSS
 */
public class TimeHelper2 {
    public static final String dateFormat = "yyyy-MM-dd HH:mm:ss:SSS";

    /** 当前时间 */
    public static String currentTime(String dateFormat, boolean regular) {
        return data2Time(dateFormat, LocalDateTime.now(), regular);
    }

    /**
     * @param dateFormat
     * @param millSec
     * @param regular 是否变为文件名格式
     * @return
     */
    public static String long2Time(String dateFormat, Long millSec, boolean regular) {
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(millSec), ZoneId
                .systemDefault());
        return data2Time(dateFormat, date, regular);
    }

    public static String data2Time(String dateFormat, LocalDateTime date, boolean regular) {
        //自定义格式化
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(dateFormat);
        if (regular) {
            return FileHelper.toRegularFileName(sdf.format(date));
        }
        return sdf.format(date);
    }


    /** 比较日期早晚 */
    public static int compare_date(String date1, String date2, String dateFormat) {
//        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDateTime dt1 = LocalDateTime.parse(date1, df);
            LocalDateTime dt2 = LocalDateTime.parse(date2, df);
            return compare_date(dt1, dt2);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /** 比较日期早晚 */
    public static int compare_date(LocalDateTime date1, LocalDateTime date2) {
        if (date1.isAfter(date2)) {
            System.out.println("dt1 在dt2后");
            return 1;
        } else if (date1.isBefore(date2)) {
            System.out.println("dt1在dt2前");
            return -1;
        } else {
            return 0;
        }
    }


    /** 两时间相差 */
    public static String differ_date(LocalDateTime date1, LocalDateTime date2, String
            returnDateFormat, boolean regular) {

        long differ = date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() -
                date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return long2Time(returnDateFormat, differ, regular);
    }

    /** 两时间相差 */
    public static String differ_date(String date1, String date2, String dateFormat, String
            returnDateFormat, boolean regular) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);

//        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            LocalDateTime dt1 = LocalDateTime.parse(date1, df);
            LocalDateTime dt2 = LocalDateTime.parse(date2, df);

//            Date dt1 = df.parse(date1);
//            Date dt2 = df.parse(date2);
            return differ_date(dt1, dt2, returnDateFormat, regular);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "";
    }

    //获取时间前后的时间
    public static String addTime(int field, int expiredIn, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        //这里用的是分钟，也可以用小时，或者是天...
//        Calendar.MINUTE
        cal.set(field, expiredIn); //当前分钟的前后分钟，
        //格式化指定形式的时间
        String currentTime = new SimpleDateFormat(dateFormat).format(cal.getTime());//获取到完整的时间
        return currentTime;
    }
}
