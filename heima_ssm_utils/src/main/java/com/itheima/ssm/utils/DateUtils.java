package com.itheima.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 */
public class DateUtils {

    /**
     * 日期转换为String
     * @return
     */
    public static String date2String(Date date , String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    /**
     * String转换为日期
     * @return
     */
    public static Date string2Date(String str , String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }

}
