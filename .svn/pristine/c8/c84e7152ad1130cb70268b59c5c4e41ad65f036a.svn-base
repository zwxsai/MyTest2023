package com.example.mytest2023.helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by 钟文祥 on 2020/2/25.
 * Describer:
 */
public class StringHelper {

    /**
     * 秒 转hh:mm:ss
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99) return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    /** 个位补零 */
    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) retStr = "0" + Integer.toString(i);
        else retStr = "" + i;
        return retStr;
    }


    /** 返回正规的文件名 */
    public static String toRegularFileName(String fileName) {
        return fileName.replace(" ", "_").replace(":", "");
    }

    /** 保留6位 */
    public static double retain6(double num) {
        String result = String.format("%.6f", num);
        return Double.valueOf(result);
    }

    /** 保留1位 */
    public static double retain1(double num) {
        String result = String.format("%.1f", num);
        return Double.valueOf(result);
    }

    //url编码
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getEncoding(String str) {
        String encode;

        encode = "UTF-16";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "ASCII";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return "字符串<< " + str + " >>中仅由数字和英文字母组成，无法识别其编码格式";
            }
        } catch (Exception ex) {
        }

        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        /*
         *......待完善
         */

        return "未识别编码格式";
    }


    public static String getCPUABI() {
        String CPUABI = "";
        try {
            String os_cpuabi = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec
                    ("getprop ro.product.cpu.abi").getInputStream())).readLine();
            if (os_cpuabi.contains("x86")) {
                CPUABI = "x86";
            } else if (os_cpuabi.contains("armeabi-v7a")) {
                CPUABI = "armeabi-v7a";
            } else if (os_cpuabi.contains("arm64-v8a")) {
                CPUABI = "arm64-v8a";
            } else {
                CPUABI = "armeabi";
            }
        } catch (Exception e) {
            CPUABI = "armeabi";
        }
        return CPUABI;
    }

    /***
     * 补0
     * @param num
     * @param len
     * @param prev 是否前面补0
     * @return
     */
    public static String zeroFormat(Integer num, int len, boolean prev) {
        int l = num.toString().length();
        StringBuffer sb = new StringBuffer();
        if (!prev)
            sb.append(num);//后补
        for (int i = 0; i < len - l; i++)
            sb.append("0");
        if (prev)
            sb.append(num);//前补
        return sb.toString();
    }


    /** 截断输出日志 */
    public static void loge(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }


}
