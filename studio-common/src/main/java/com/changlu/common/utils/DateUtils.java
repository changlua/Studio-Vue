package com.changlu.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author ruoyi
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 增加 LocalDateTime ==> Date
     */
    public static Date toDate(LocalDateTime temporalAccessor)
    {
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 增加 LocalDate ==> Date
     */
    public static Date toDate(LocalDate temporalAccessor)
    {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 加上指定时长
     * @param date 目标日期
     * @param type 类型，通过使用Calendar.YEAR，选择减去的类型
     * @param num 正整数
     * @return
     */
    public static Date addDate(Date date, int type, int num){
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(type, num);//instance.add(Calendar.YEAR, 3);
        return instance.getTime();
    }

    /**
     * 减去指定时长
     * @param date 目标日期
     * @param type 类型，通过使用Calendar.YEAR，选择减去的类型
     * @param num 正整数
     * @return
     */
    public static Date minusDate(Date date, int type, int num){
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(type, -num);//instance.add(Calendar.YEAR, -3);
        return instance.getTime();
    }

}
