package com.ailk.ims.component.billcycle;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import jef.tools.DateFormats;
import jef.tools.DateUtils.TimeUnit;
import jef.tools.support.ThreadLocal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ailk.common.virtualtime.VirtualDate;

/**
 * XXX wangjt: 帐管的日期类，后续整合之后需要废弃
 * 
 * @Description: 日期辅助类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author lindx
 * @Date 2011-5-19
 */
public abstract class DateUtils
{
    private static final Log logger = LogFactory.getLog(DateUtils.class);

    public static ThreadLocal<Calendar> defaultCalender = new ThreadLocal<Calendar>()
    {
        @Override
        protected Calendar initialValue()
        {
            return Calendar.getInstance();
        }
    };

    /**
     * 每个时间单位的毫秒数
     */
    private static final long MS_IN_DAY = 86400000L;

    // 日期时间全格式 24小时制
    /**
     * 24小时制日期时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";// DATE_TIME_CS

    /**
     * 24小时制日期时间格式 yyyyMMddHHmmss
     */
    public static String DATETIME_FORMAT2 = "yyyyMMddHHmmss";// DATE_TIME_SHORT_14

    /**
     * 24小时制时期时间一天开始 yyyy-MM-dd 00:00:00
     */
    public static String DATE_ZEROTIME_FORMAT = "yyyy-MM-dd 00:00:00";

    public static final ThreadLocal<DateFormat> TH_DATE_ZEROTIME_FORMAT = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat(DATE_ZEROTIME_FORMAT);
        }
    };

    /**
     * 24小时制日期时间一天开始 yyyyMMdd000000
     */
    public static String DATE_ZEROTIME_FORMAT2 = "yyyyMMdd000000";

    public static final ThreadLocal<DateFormat> TH_DATE_ZEROTIME_FORMAT2 = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat(DATE_ZEROTIME_FORMAT2);
        }
    };

    /**
     * 24小时制日期时间一天末 yyyy-MM-dd 23:59:59
     */
    public static String DATE_FULLTIME_FORMAT = "yyyy-MM-dd 23:59:59";

    public static final ThreadLocal<DateFormat> TH_DATE_FULLTIME_FORMAT = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat(DATE_FULLTIME_FORMAT);
        }
    };

    /**
     * 24小时制日期时间一天末 yyyyMMdd235959
     */
    public static String DATE_FULLTIME_FORMAT2 = "yyyyMMdd235959";

    public static final ThreadLocal<DateFormat> TH_DATE_FULLTIME_FORMAT2 = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat(DATE_FULLTIME_FORMAT2);
        }
    };

    // 日期时间全格式 12小时制
    /**
     * 12小时制日期时间格式 yyyy-MM-dd hh:mm:ss
     */
    public static String DATETIME12_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static final ThreadLocal<DateFormat> TH_DATETIME12_FORMAT = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat(DATETIME12_FORMAT);
        }
    };

    /**
     * 12小时制日期时间格式 yyyyMMddhhmmss
     */
    public static String DATETIME12_FORMAT2 = "yyyyMMddhhmmss";// DATE_TIME_SHORT_14

    // 日期全格式
    /**
     * 日期格式yyyy-MM-dd
     */
    public static String DATE_FORMAT = "yyyy-MM-dd";// DateFormats.DATE_CS
    /**
     * 日期格式 yyyyMMdd
     */
    public static String DATE_FORMAT2 = "yyyyMMdd";// DateFormats.DATE_SHORT

    // 年月
    /**
     * 年月 yyyy-MM
     */
    public static String YEAR_MONTH_FORMAT = "yyyy-MM";

    public static final ThreadLocal<DateFormat> TH_YEAR_MONTH_FORMAT = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat(YEAR_MONTH_FORMAT);
        }
    };

    /**
     * 年月yyyyMM
     */
    public static String YEAR_MONTH_FORMAT2 = "yyyyMM";// YAER_MONTH

    // 某年某月的第一天 added by zhao hong
    /**
     * 某月的第一天 yyyy-MM-01
     */
    public static String YEAR_MONTH_FIRSTDAY = "yyyy-MM-01";

    public static final ThreadLocal<DateFormat> TH_YEAR_MONTH_FIRSTDAY = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat(YEAR_MONTH_FIRSTDAY);
        }
    };

    // 年、月、日 added by 崔建琪 2007-11-23
    /**
     * 4位年
     */
    public static String YEAR_FORMAT = "yyyy";
    /**
     * 2位月
     */
    public static String MONTH_FORMAT = "MM";
    /**
     * 2位天
     */
    public static String DAY_FORMAT = "dd";

    // 时间全格式 24小时制
    /**
     * 24小时制时间 时分秒 冒号分隔 HH:mm:ss
     */
    public static String TIME_FORMAT = "HH:mm:ss";
    /**
     * 24小时制时间 时分秒 无分隔 HHmmss
     */
    public static String TIME_FORMAT2 = "HHmmss";

    // 时间全格式 12小时制
    /**
     * 12小时制 时分秒 冒号分隔 hh:mm:ss
     */
    // public static String TIME12_FORMAT = "hh:mm:ss";
    /**
     * 12小时制 时分秒 无分隔 hhmmss
     */
    // public static String TIME12_FORMAT2 = "hhmmss";

    // 营业cs版本日期时间格式
    /**
     * 24小时制 年月日时分秒 yyyy/MM/dd HH:mm:ss
     */
    public static String DATETIME_SLASH_FORMAT = "yyyy/MM/dd HH:mm:ss";

    public static final ThreadLocal<DateFormat> TH_DATETIME_SLASH_FORMAT = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat(DATETIME_SLASH_FORMAT);
        }
    };

    /**
     * 24小时制 年月日 yyyy/MM/dd
     */
    public static String DATE_SLASH_FORMAT = "yyyy/MM/dd";

    public static final ThreadLocal<DateFormat> TH_DATE_SLASH_FORMAT = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat(DATETIME_SLASH_FORMAT);
        }
    };

    /**
     * 不能实例化
     */
    private DateUtils()
    {
    }

    /**
     * 对输入的日期字符串进行格式化,如果输入的是0000/00/00 00:00:00则返回空串.
     * 
     * @param strDate String 需要进行格式化的日期字符串
     * @param strFormatTo String 要转换的日期格式
     * @return String 经过格式化后的字符串
     */

    private static String getFormattedDate(String strDate, String strFormatTo)
    {
        if (strDate == null || strDate.trim().equals(""))
        {

            return "";

        }

        strDate = strDate.replace('/', '-');

        strFormatTo = strFormatTo.replace('/', '-');

        if (strDate.equals("0000-00-00 00:00:00") || strDate.equals("1800-01-01 00:00:00"))
        {

            return "";

        }

        String formatStr = strFormatTo; // "yyyyMMdd";
        if (strDate == null || strDate.trim().equals(""))
        {
            return "";
        }

        switch (strDate.trim().length())
        {

        case 6:

            if (strDate.substring(0, 1).equals("0"))
            {
                formatStr = "yyMMdd";
            }
            else
            {
                formatStr = "yyyyMM";
            }

            break;

        case 8:
            formatStr = "yyyyMMdd";
            break;

        case 10:
            if (strDate.indexOf("-") == -1)
            {
                formatStr = "yyyy/MM/dd";
            }
            else
            {
                formatStr = "yyyy-MM-dd";
            }

            break;
        case 11:
            if (strDate.getBytes().length == 14)
            {
                formatStr = "yyyy年MM月dd日";
            }
            else
            {
                return "";
            }

        case 14:
            formatStr = "yyyyMMddHHmmss";
            break;

        case 19:
            if (strDate.indexOf("-") == -1)
            {
                formatStr = "yyyy/MM/dd HH:mm:ss";
            }
            else
            {
                formatStr = "yyyy-MM-dd HH:mm:ss";
            }
            break;

        case 21:
            if (strDate.indexOf("-") == -1)
            {
                formatStr = "yyyy/MM/dd HH:mm:ss.S";
            }
            else
            {
                formatStr = "yyyy-MM-dd HH:mm:ss.S";
            }
            break;
        default:
            return strDate.trim();
        }

        try
        {

            DateFormat formatter = getDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(strDate));
            formatter = getDateFormat(strFormatTo);
            return formatter.format(calendar.getTime());
        }
        catch (Exception e)
        {
            logger.warn("转换日期字符串格式时出错;" + e.getMessage());
            return "";
        }
    }

    /**
     * 获取format
     * 
     * @param format
     */
    private static DateFormat getDateFormat(String format)
    {
        if (DATETIME_FORMAT.equals(format))
        {
            return DateFormats.DATE_TIME_CS.get();
        }
        else if (DATETIME_FORMAT2.equals(format))
        {
            return DateFormats.DATE_TIME_SHORT_14.get();
        }
        else if (DATE_ZEROTIME_FORMAT.equals(format))
        {
            return TH_DATE_ZEROTIME_FORMAT.get();
        }
        else if (DATE_ZEROTIME_FORMAT2.equals(format))
        {
            return TH_DATE_ZEROTIME_FORMAT2.get();
        }
        else if (DATE_FULLTIME_FORMAT.equals(format))
        {
            return TH_DATE_FULLTIME_FORMAT.get();
        }
        else if (DATE_FULLTIME_FORMAT2.equals(format))
        {
            return TH_DATE_FULLTIME_FORMAT2.get();
        }
        else if (DATETIME12_FORMAT.equals(format))
        {
            return TH_DATETIME12_FORMAT.get();
        }
        else if (DATETIME12_FORMAT2.equals(format))
        {
            return DateFormats.DATE_TIME_SHORT_14.get();
        }
        else if (DATE_FORMAT.equals(format))
        {
            return DateFormats.DATE_CS.get();
        }
        else if (DATE_FORMAT2.equals(format))
        {
            return DateFormats.DATE_SHORT.get();
        }
        else if (YEAR_MONTH_FORMAT.equals(format))
        {
            return TH_YEAR_MONTH_FORMAT.get();
        }
        else if (YEAR_MONTH_FORMAT2.equals(format))
        {
            return DateFormats.YAER_MONTH.get();
        }
        else if (YEAR_MONTH_FIRSTDAY.equals(format))
        {
            return TH_YEAR_MONTH_FIRSTDAY.get();
        }
        else if (DATETIME_SLASH_FORMAT.equals(format))
        {
            return TH_DATETIME_SLASH_FORMAT.get();
        }
        else if (DATE_SLASH_FORMAT.equals(format))
        {
            return TH_DATE_SLASH_FORMAT.get();
        }

        return new SimpleDateFormat(format);
    }

    /**
     * 根据传入的日期字符串转换成相应的日期对象，如果字符串为空或不符合日期格式，则返回当前时间。
     * 
     * @param strDate String 日期字符串
     * @return java.sql.Timestamp 日期对象
     */
    public static Date getDateByString(String strDate)
    {

        if (strDate == null || strDate.trim().equals(""))
        {
            return getCurrentDate();
        }

        try
        {

            strDate = getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss") + ".000000000";

            return java.sql.Timestamp.valueOf(strDate);

        }
        catch (Exception ex)
        {

            logger.warn("getDateByString exception: " + ex.getMessage());
            return new java.sql.Timestamp(System.currentTimeMillis());
        }

    }

    /**
     * 返回当前时间<br/>
     * 这里返回当前服务器的虚拟时间
     * 
     * @return 当前时间
     */
    public static Date getCurrentDate()
    {
        return new VirtualDate();
    }

    /**
     * 增加
     * 
     * @param aDate 指定时间
     * @param delta 要变更的量，可以为负数，
     * @param timeUnit delta参数的时间单位
     * @return 返回变更后的日期
     * @see TimeUnit
     * @see VirtualDate
     */
    public static Date addMonths(Date aDate, int delta)
    {
        Date newDate = org.apache.commons.lang.time.DateUtils.addMonths(aDate, delta);
        return new Date(newDate.getTime());
    }

    /**
     * 这个转化函数会对日期日期字符串进行尝试 首先用 {@link #DATE_FORMAT} 尝试能不能转换 如果不行 使用格式 {@link DATE_SLASH_FORMAT} 格式进行尝试 字符转化成日期
     * 
     * @param inputDate 输入日期
     * @return
     */
    public static Date stringToDate(String inputDate)
    {
        if (inputDate == null || inputDate.equalsIgnoreCase(""))
            return null;
        DateFormat sdf = getDateFormat(DATE_FORMAT);
        Date date = null;
        try
        {
            date = sdf.parse(inputDate, new ParsePosition(0));
            if (date == null)
            {
                sdf = getDateFormat(DATE_FORMAT2);// DateFormats.DATE_SHORT.get().format(date)
                date = sdf.parse(inputDate, new ParsePosition(0));
            }
        }
        catch (Exception ex)
        {
            DateFormat sdfs = getDateFormat(DATE_SLASH_FORMAT);
            date = sdfs.parse(inputDate, new ParsePosition(0));
            return date;
        }
        return date;
    }

    /**
     * 在原有的时间上加上某个值
     * 
     * @param aDate 指定时间
     * @param days 要变更的量，可以为负数，
     * @return 返回变更后的日期
     * @see VirtualDate
     */
    public static Date addDays(Date aDate, long days)
    {
        long timeInMs = aDate.getTime() + days * MS_IN_DAY;
        return new Date(timeInMs);
    }

    /**
     * 在原有的时间上加上某个值
     * 
     * @param aDate 指定时间
     * @param delta 要变更的量，可以为负数，
     * @param timeUnit delta参数的时间单位
     * @return 返回变更后的日期
     * @see TimeUnit
     * @see VirtualDate
     */
    public static Date addYears(Date aDate, int delta)
    {
        Date newDate = org.apache.commons.lang.time.DateUtils.addYears(aDate, delta);
        return new Date(newDate.getTime());
    }

    /**
     * 计算两个时间相隔的月数
     * 
     * @param date1 时间点1
     * @param date2 时间点2
     * @return date2 -date1 的月数查
     */
    public static int monthsBetween(Date date1, Date date2)
    {
        @SuppressWarnings("deprecation")
        int year = date2.getYear() - date1.getYear();
        int monthsbetween = 0;
        @SuppressWarnings("deprecation")
        int month2 = date2.getMonth();
        @SuppressWarnings("deprecation")
        int month1 = date1.getMonth();
        if (year > 0)
        {
            if (year == 1)
            {
                monthsbetween = 12 - month1 + month2;
            }
            else
            {
                monthsbetween = 12 * (year - 1) + month2;
            }
        }
        else if (year == 0)
        {
            monthsbetween = month2 - month1;
        }
        else
        {
            return -1;
        }
        return monthsbetween;
    }

    /**
     * 计算两个时间相隔的月数
     * 
     * @param date1 时间点1
     * @param date2 时间点2
     * @return date2 -date1 的年数查
     */
    public static int yearBetween(Date date1, Date date2)
    {
        @SuppressWarnings("deprecation")
        int year = date2.getYear() - date1.getYear();
        return year;
    }

    /**
     * 计算两个时间相隔的天数,不足一天不算一天
     * 
     * @param date1 时间点1
     * @param date2 时间点2
     * @return date2 -date1 的天数查
     */
    public static long daysBetween(Date date1, Date date2)
    {

        return (date2.getTime() - date1.getTime()) / MS_IN_DAY;
    }
}
