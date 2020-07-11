package com.cloud.consumer.utils;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MAIL_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String MAIL_DATE_DT_PART_FORMAT = "yyyyMMdd";
    public static final String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //判断date1在当前时间之前
    public static boolean isBeforNowDate(String date) {
        return isDate1BeforOrAfterDate2(true, date, null);
    }

    //判断date1在当前时间之后
    public static boolean isAfterNowDate(String date) {
        return isDate1BeforOrAfterDate2(false, date, null);
    }

    //判断date1在date2之前
    public static boolean isDate1BeforDate2(String date1, String date2) {
        return isDate1BeforOrAfterDate2(true, date1, date2);
    }

    //判断date1在date2之后
    public static boolean isDate1AfterDate2(String date1, String date2) {
        return isDate1BeforOrAfterDate2(false, date1, date2);
    }

    public static boolean isDate1BeforOrAfterDate2(boolean isBefore, String date1, String date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(MAIL_DATE_DT_PART_FORMAT);
        //data2为空时取当前时间
        String nowDate = StringUtils.isBlank(date2) ? dateFormat.format(new Date()) : date2;
        try {
            Date d1 = dateFormat.parse(date1);
            Date d2 = dateFormat.parse(nowDate);
            //比较date1是否在当前时间或者date2日期之前，date1在当前时间或者date2日期之前
            if (isBefore && d1.before(d2)) {
                return true;
            }
            // //比较date1是否在当前时间或者date2日期之后，date1在当前时间或者date2日期之后
            if (isBefore && d1.after(d2)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    //
    public static boolean isBeforOrEqualNowDate(String date) {
        return isDate1EqualDate2(true, date, null);
    }

    //
    public static boolean isAfterOrEqualNowDate(String date) {
        return isDate1EqualDate2(false, date, null);
    }

    //
    public static boolean isDate1BeforOrEqualDate2(String date1, String date2) {
        return isDate1EqualDate2(true, date1, date2);
    }

    //
    public static boolean isDate1AfterOrEqualDate2(String date1, String date2) {
        return isDate1EqualDate2(false, date1, date2);
    }

    //判断时间与当前时间是否一致
    public static boolean isEqualNowDate(String date1, String date2) {
        return isEqualDate(date1, null);
    }

    //比较两个时间是否一致
    public static boolean isEqualDate(String date1, String date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(MAIL_DATE_DT_PART_FORMAT);
        String nowDate = StringUtils.isBlank(date2) ? dateFormat.format(new Date()) : date2;
        try {
            Date d1 = dateFormat.parse(date1);
            Date d2 = dateFormat.parse(nowDate);
            return d1.equals(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    //比较两个时间是相等或者在之前或之后
    public static boolean isDate1EqualDate2(boolean isBefore, String date1, String date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(MAIL_DATE_DT_PART_FORMAT);
        //data2为空时取当前时间
        String nowDate = StringUtils.isBlank(date2) ? dateFormat.format(new Date()) : date2;
        try {
            Date d1 = dateFormat.parse(date1);
            Date d2 = dateFormat.parse(nowDate);
            //比较date1是否在当前时间或者date2日期之前，date1在当前时间或者date2日期之前(或相等)
            if (isBefore && (d1.before(d2) || d1.equals(d2))) {
                return true;
            }
            // //比较date1是否在当前时间或者date2日期之后，date1在当前时间或者date2日期之后(或相等)
            if (isBefore && (d1.after(d2) || d1.equals(d2))) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    //获取当前时间
    public static String getTheTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HHmmss");
        String str = format.format(date);
        return str;
    }


}
