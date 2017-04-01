package com.ailk.ims.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ailk.common.virtualtime.VirtualDate;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMSException;

/**
 * @Description: 定义日期相关的公用方法
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2
 * @Date 2012-10-22 zengxr cache the format template and default expire date
 */
public class DateUtil
{
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS_ORACLE = "yyyymmddHH24miss";
    public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String DATE_FORMAT_YYYYMMDDHH = "yyyyMMddHH";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";
    public static final String DATE_FORMAT_YYYY = "yyyy";
    public static final String DATE_FORMAT_EN_A_YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_EN_A_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";
    public static final String DATE_FORMAT_EN_A_YYYYMMDDHH = "yyyy/MM/dd HH";
    public static final String DATE_FORMAT_EN_A_YYYYMMDD = "yyyy/MM/dd";
    public static final String DATE_FORMAT_EN_A_YYYYMM = "yyyy/MM";
    public static final String DATE_FORMAT_EN_A_YYYY = "yyyy";
    public static final String DATE_FORMAT_EN_B_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_EN_B_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_EN_B_YYYYMMDDHH = "yyyy-MM-dd HH";
    public static final String DATE_FORMAT_EN_B_YYYYMMDD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_EN_B_YYYYMM = "yyyy-MM";
    public static final String DATE_FORMAT_EN_B_YYYY = "yyyy";
    public static final String DATE_FORMAT_CN_YYYYMMDDHHMMSS = "yyyy'年'MM'月'dd'日' HH'时'mm'分'ss'秒'";
    public static final String DATE_FORMAT_CN_YYYYMMDDHHMM = "yyyy'年'MM'月'dd'日' HH'时'mm'分'";
    public static final String DATE_FORMAT_CN_YYYYMMDDHH = "yyyy'年'MM'月'dd'日' HH'时'";
    public static final String DATE_FORMAT_CN_YYYYMMDD = "yyyy'年'MM'月'dd'日'";
    public static final String DATE_FORMAT_CN_YYYYMM = "yyyy'年'MM'月'";
    public static final String DATE_FORMAT_CN_YYYY = "yyyy'年'";
    public static final int DATE = Calendar.DATE;
    public static final int MONTH = Calendar.MONTH;
    public static final int YEAR = Calendar.YEAR;
    public static final int HOUR_OF_DAY = Calendar.HOUR_OF_DAY;
    public static final int MINUTE = Calendar.MINUTE;
    public static final int SECOND = Calendar.SECOND;
    public static String MATCH_YEAR_4 = "[0-9]{4}";
    public static String MATCH_YEAR_2 = "[0-9]{2}";
    public static String MATCH_MONTH = "(0[0-9]|1[0-2])";
    public static String MATCH_DAY = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])";
    public static String MATCH_HOUR = "([0-1][0-9]|2[0-3])";
    public static String MATCH_MINUTES = "[0-5][0-9]";
    public static String MATCH_SECOND = "[0-5][0-9]";
    private static ImsLogger imsLogger=new ImsLogger(DateUtil.class);
    
    private static Map<String, Object> innerMap = new HashMap<String, Object>();
    
    public static void main(String[] args) throws IMSException
    {
//        System.out.println(getBetweenDays("2012-4-5","2013-4-24"));
    }
    
    public static int getMonth(Date date){
    	 Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         return calendar.get(DateUtil.MONTH);
    }
    
    
    /**
     * 2008-09-13 zengxr 增加获取当前时间的方法，统一使用该接口方便以后转换实现方式
     * 
     * @return
     */
    public static Date currentDate()
    {
        return new VirtualDate();
    }
    /**
     * 得到当前日期字符串,根据传入的格式返回
     * 
     * @return
     */
    public static String currentString(String format)
    {
        Date date = currentDate();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
    /**
     * 得到当前日期字符串,yyyymmdd格式
     * 
     * @return
     */
    public static String currentString8()
    {
        Date date = currentDate();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        return formatter.format(date);
    }
    
    /**
     * @author yanchuan 获取此 Date 对象表示的月份中的某一天 2012-10-11
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * @author yanchuan 获取此Date对象表示的周中的某一天 2012-10-11
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 得到当前日期字符串,yyyymmddhhmmss格式
     * 
     * @return
     */
    public static String currentString14()
    {
        Date date = currentDate();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS);
        return formatter.format(date);
    }
    /**
     * 得到当前日期字符串,yyyy-mm-dd hh:mm:ss
     * 
     * @return
     */
    public static String currentString19()
    {
        Date date = currentDate();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
        return formatter.format(date);
    }
    
    public static String getFormateDate(int date) throws IMSException
    {
        Date newDate = DateUtil.getFormatDate(CommonUtil.int2String(date), DateUtil.DATE_FORMAT_YYYYMMDD);
        return DateUtil.getFormatDate(newDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
    }
    
    
    public static String cvtDateStrForShow(String dateStr)
    {
        String formatStr = "yyyyMMdd";
        if (dateStr == null || dateStr.trim().equals(""))
        {
            return "";
        }
        switch (dateStr.trim().length())
        {
        case 6:
            formatStr = "yyMMdd";
            break;
        case 8:
            formatStr = "yyyyMMdd";
            break;
        case 10:
            if (dateStr.indexOf("-") == -1)
            {
                formatStr = "yyyy/MM/dd";
            }
            else
            {
                formatStr = "yyyy-MM-dd";
            }
            break;
        case 11:
            if (dateStr.getBytes().length == 14)
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
            if (dateStr.indexOf("-") == -1)
            {
                formatStr = "yyyy/MM/dd HH:mm:ss";
            }
            else
            {
                formatStr = "yyyy-MM-dd HH:mm:ss";
            }
            break;
        default:
            return dateStr.trim();
        }
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(dateStr));
            formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return formatter.format(calendar.getTime());
        }
        catch (Exception e)
        {
            imsLogger.debug("转换日期字符串格式时出错;" + e.getMessage());
            return "";
        }
    }

    /**
     * 得到当前日期和时间
     * 
     * @return
     *//*
    public static String getCurrDateTime()
    {
        Date date = currentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(date);
    }

    // print the log infomation.
    public static void log(Object obj)
    {
        System.out.print("出错信息: ");
        imsLogger.debug(obj.toString());
    }

    public static String getCurrTime()
    {
        Date date = currentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        return formatter.format(date);
    }

    public static String getCurrTimeYYYYMMDD()
    {
        Date date = currentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }

    public static String getCurrTimeYYYYMMDDHHMMSS()
    {
        Date date = currentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(date);
    }*/

    public static Date getCutoffNow(int cutOffType, int cutOffNum)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate());
        cal.add(cutOffType, cutOffNum);
        return cal.getTime();
    }

    public static Date getCutoffDate(int cutOffType, int cutOffNum, Date pos)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(pos);
        cal.add(cutOffType, cutOffNum);
        return cal.getTime();
    }

    public static String getCutoffNow(int cutOffType, int cutOffNum, String format)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate());
        cal.add(cutOffType, cutOffNum);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(cal.getTime());
    }

    public static String getCutoffDate(int cutOffType, int cutOffNum, Date pos, String format)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(pos);
        cal.add(cutOffType, cutOffNum);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(cal.getTime());
    }

    public static String formatDate(Date date, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 得到指定日期后指定的天数后的日期
     * 
     * @param strDate String 指定日期，格式:yyyMMdd
     * @param nDelayDays int 指定天数,
     * @return String 指定日期，格式:yyyMMdd
     */
    public static String getDelayDayStr(String strDate, int nDelayDays)
    {
        if (strDate.length() != 8)
            return "";
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            formatter.setLenient(false);
            Date date = formatter.parse(strDate + "000000");
            Date datetmp = new Date(date.getTime() - nDelayDays * 24 * 60 * 60 * 1000);
            return formatter.format(datetmp).substring(0, 8);
        }
        catch (Exception e)
        {
            imsLogger.debug("日期操作出错:" + e.getMessage());
            return "";
        }
    }

    /**
     * 得到指定日期后指定的月数后的月份
     * 
     * @param strMon String 指定月份 格式:yyyyMM
     * @param nDelayDays int 指定月份数
     * @return String 指定月份数 格式:yyyyMM
     */
    public static String getDelayMonStr(String strMon, int nDelayDays)
    {
        if (strMon.length() != 6)
        {
            return "";
        }
        if (nDelayDays < 0)
        {
            imsLogger.debug("Common.getDelayMonStr()不能输入负数的天数");
            return "";
        }
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            formatter.setLenient(false);
            Date date = formatter.parse(strMon + "01000000");
            Date datetmp = new Date(date.getTime() - nDelayDays * 24 * 60 * 60 * 1000);
            return formatter.format(datetmp).substring(0, 6);
        }
        catch (Exception e)
        {
            imsLogger.debug("月份操作出错:" + e.getMessage());
            return "";
        }
    }

    public static String Now()
    {
        Date date = currentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(date);
    }

    /**
     * 根据日期模式，把字符串型得日期转换程JAVA的日期
     * 
     * @param dateStr 日期
     * @param format 模式
     * @return java型的日期
     */
    public static Date getFormatDate(String dateStr, String format) throws IMSException
    {
        // DATE_FORMAT_YYYYMMDDHHMMSS
        Date date = null;
        if ((dateStr == null || "".equals(dateStr)) || (format == null || "".equals(format)))
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try
        {
            date = sdf.parse(dateStr);
        }
        catch (ParseException pe)
        {
            throw new IMSException(pe.getMessage());
        }
        return date;
    }

    /**
     * 默认使用比较常用的YYYYMMDDHHMMSS模式来转换日期
     * 
     * @return
     * @throws IMSException
     * 2012-10-22 zengxr cache the format template and default expire date
     */
    public static String getFormatDate(Date date, String formatStr) throws IMSException
    {
        try
        {   
            if(date==null){
                return "";
            }
//            SimpleDateFormat sdf = (SimpleDateFormat)innerMap.get(formatStr);
//            if(sdf == null){
//                sdf = new SimpleDateFormat(formatStr);
//                innerMap.put(formatStr, sdf);
//            }
            SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
            String dateStr = sdf.format(date);
            String defaultExpire = (String)innerMap.get(SysCodeDefine.busi.BUSI_DEFAULT_EXPIRE_DATE);
            if(defaultExpire == null){
                defaultExpire = SysUtil.getString(SysCodeDefine.busi.BUSI_DEFAULT_EXPIRE_DATE);
                innerMap.put(SysCodeDefine.busi.BUSI_DEFAULT_EXPIRE_DATE, defaultExpire);
            }
            if(dateStr.equals(defaultExpire))
                dateStr = "";
            return dateStr;
        }
        catch (Exception ex)
        {
            imsLogger.error(ex, ex);
            throw new IMSException("fail to format date : [date=" + date + ";format=" + formatStr + "]");
        }
    }

    /**
     * 默认使用比较常用的YYYYMMDDHHMMSS模式来转换日期
     * 
     * @param dateStr
     * @return
     * @throws IMSException
     */
    public static Date getFormatDate(String dateStr) throws IMSException
    {
        return getFormatDate(dateStr, DATE_FORMAT_YYYYMMDDHHMMSS);
    }

    public static int getHoursBeforNow(String dataStr) throws IMSException
    {
        Date date = getFormatDate(dataStr, DATE_FORMAT_YYYYMMDDHHMMSS);
        Date now = currentDate();
        long mul = now.getTime() - date.getTime();
        int hours = (int) (mul / (1000 * 60 * 60));
        return hours;
    }

    public static int getMunitesBeforNow(String dataStr) throws IMSException
    {
        Date date = getFormatDate(dataStr, DATE_FORMAT_YYYYMMDDHHMMSS);
        Date now = currentDate();
        long mul = now.getTime() - date.getTime();
        int minutes = (int) (mul / (1000 * 60));
        return minutes;
    }

    /**
     * 对输入的日期字符串进行格式化.
     * 
     * @param strDate 需要进行格式化的日期，格式为前面定义的 DATE_FORMAT_YYYYMMDDHHMMSS
     * @return 经过格式化后的字符串
     * @throws IMSException
     */
    public static Date getFormattedDate(String strDate) throws IMSException
    {
        String formatStr = "yyyyMMdd";
        if (strDate == null || strDate.trim().equals(""))
        {
            return null;
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
                return null;
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
        default:
            throw new IMSException("invalid date format : " + strDate);
        }
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            return formatter.parse(strDate);
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            imsLogger.debug("转换日期字符串格式时出错;" + e.getMessage());
            return null;
        }
    }

    /**
     * 对输入的日期字符串进行格式化.
     * 
     * @param strDate 需要进行格式化的日期，格式为前面定义的 DATE_FORMAT_YYYYMMDDHHMMSS
     * @param strFormatTo 指定采用何种格式进行格式化操作
     * @return 经过格式化后的字符串
     */
    public static String getFormattedDate(String strDate, String strFormatTo)
    {
        String formatStr = "yyyyMMdd";
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
        default:
            return strDate.trim();
        }
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(strDate));
            formatter = new SimpleDateFormat(strFormatTo);
            return formatter.format(calendar.getTime());
        }
        catch (Exception e)
        {
            imsLogger.debug("转换日期字符串格式时出错;" + e.getMessage());
            return "";
        }
    }

    /**
     * 得到指定日期 yyyyMMddHHmmss 后 指定秒的数据
     * 
     * @param strNowDay
     * @param nDelaySeconds
     * @return
     */
    public static String getDelaySecondStr(String strNowDay, int nDelaySeconds)
    {
        if (strNowDay.length() != 14)
        {
            return "";
        }
        if (nDelaySeconds < 0)
        {
            imsLogger.debug("Common.getDelayMonStr()不能输入负数的天数");
            return "";
        }
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            formatter.setLenient(false);
            Date date = formatter.parse(strNowDay);
            Date datetmp = new Date(date.getTime() + nDelaySeconds * 1000);
            return formatter.format(datetmp);
        }
        catch (Exception e)
        {
            imsLogger.debug("操作出错:" + e.getMessage());
            return "";
        }
    }

    public static int getNowField(int field)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate());
        return cal.get(field);
    }

    public static int getDateField(Date date, int field)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(field);
    }

    /**
     * 获取偏移时间 传入格式:-mm:ss or -ss 前面必须要有符号
     * 
     * @param date
     * @param trewingStr
     * @return
     */
    public static String getSkewingTimeStr(Date date, String trewingStr)
    {
        Date skewingDate = getSkewingTime(date, trewingStr);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try
        {
            return formatter.format(skewingDate);
        }
        catch (Exception e)
        {
            return formatter.format(date);
        }
    }

    /**
     * @param dateStr
     * @param trewingStr
     * @return
     * @throws IMSException
     */
    public static String getSkewingTime(String dateStr, String trewingStr) throws IMSException
    {
        Date date = getFormatDate(dateStr, "yyyyMMddHHmmss");
        return getSkewingTimeStr(date, trewingStr);
    }

    /**
     * 获取偏移时间 传入格式:-mm:ss or -ss 前面必须要有符号
     * 
     * @param date
     * @param trewingStr
     * @return
     */
    public static Date getSkewingTime(Date date, String trewingStr)
    {
        if (trewingStr == null)
            return null;
        trewingStr = trewingStr.trim();
        String tmp = "";
        boolean isDesc = false;
        int minute = 0, second = 0;
        if (trewingStr.indexOf('-') >= 0)
            isDesc = true;
        // 去掉前面的符号
        trewingStr = trewingStr.substring(1, trewingStr.length());
        if (trewingStr.indexOf(':') >= 0)
        {
            tmp = trewingStr.substring(0, trewingStr.indexOf(':'));
            minute = Integer.parseInt(tmp);
            tmp = trewingStr.substring(trewingStr.indexOf(':') + 1);
            second = Integer.parseInt(tmp);
        }
        else
        {
            second = Integer.parseInt(trewingStr);
        }
        long trewing = (minute * 60 + second) * 1000;
        if (isDesc)
        {
            trewing = -trewing;
        }
        Date datetmp = new Date(date.getTime() + trewing);
        return datetmp;
    }

    public static Date getFirstDateAtMonth(Date date)
    {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        // time.getMaximum(field)
        time.set(Calendar.DATE, time.getActualMinimum(Calendar.DAY_OF_MONTH));
        return time.getTime();
    }

    /*
     * 指定时间延后指定小时后的时间
     */
    public static Date getDateDelayHours(Date date, int hour)
    {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.set(Calendar.HOUR_OF_DAY, (time.get(Calendar.HOUR_OF_DAY) + hour));
        return time.getTime();
    }

    public static Date getLastDateAtMonth(Date date)
    {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        // time.getMaximum(field)
        time.set(Calendar.DATE, time.getActualMaximum(Calendar.DAY_OF_MONTH));
        return time.getTime();
    }

    public static Timestamp getObdDate(String timestr) throws IMSException
    {
        if (timestr == null || timestr.equals(""))
            return null;
        return new Timestamp(getFormattedDate(timestr).getTime());
    }

    public static Timestamp getObdDate(Date time)
    {
        if (time == null)
            return null;
        return new Timestamp(time.getTime());
    }

    public static String getObdDateString(Timestamp obdDate) throws IMSException
    {
        if(obdDate==null){
            return null;
        }
        return DateUtil.getFormatDate(new Date(obdDate.getTime()), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
    }

    public static String getObdDateString(Timestamp obdDate, String format) throws IMSException
    {
        return DateUtil.getFormatDate(new Date(obdDate.getTime()), format);
    }

    /**
     * 把 YYYY/MM/DD格式的日期转换成 yyyymmdd型的数字日期
     * 
     * @param strDate String
     * @return int
     */
    public static int getSpecDate(String strDate)
    {
        String year = strDate.substring(0, strDate.indexOf("/"));
        String month = strDate.substring(strDate.indexOf("/") + 1, strDate.lastIndexOf("/"));
        String day = strDate.substring(strDate.lastIndexOf("/") + 1);
        int date = Integer.parseInt(year) * 10000 + Integer.parseInt(month) * 100 + Integer.parseInt(day);
        return date;
    }

    public static String[] getYMs(String sInitY, String sInitM, String sEndY, String sEndM) throws IMSException
    {
        int initM = Integer.parseInt(sInitM);
        int initY = Integer.parseInt(sInitY);
        int endM = Integer.parseInt(sEndM);
        int endY = Integer.parseInt(sEndY);
        String[] YMs = null;
        int totalM = (endY - initY) * 12 + (endM - initM) + 1;
        YMs = new String[totalM];
        // int month=initM;
        // int year=initY;
        // int count=0;
        Date initDate = getFormattedDate(sInitY + sInitM);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < totalM; i++)
        {

            cal.setTime(initDate);
            cal.add(DateUtil.MONTH, i);
            SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYYMM);
            YMs[i] = "_" + formatter.format(cal.getTime());
            // if((month+i)>12)
            // {
            // year=year+1;
            // month=1;
            // totalM=totalM-i;
            // i=0;
            // YMs[count] = "_"+year+(StringUtil.lfill(""+month,'0',2));
            // }else
            // {
            //
            // YMs[count] = "_"+year+(StringUtil.lfill(""+(month+i),'0',2));
            // }
            // count++;
        }
        return YMs;
    }

    /**
     * 得到指定日期 yyyyMMddHHmmss 前后 指定天的数据
     * 
     * @param strNowDay
     * @param nDelaySeconds
     * @return
     */
    public static String getSpecDayStr(String strNowDay, int nDelayDays)
    {
        if (strNowDay.length() != 14)
        {
            strNowDay = getFormattedDate(strNowDay, DATE_FORMAT_YYYYMMDDHHMMSS);
        }

        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS);
            formatter.setLenient(false);
            Date date = formatter.parse(strNowDay);
            Date datetmp = new Date(date.getTime() + nDelayDays * 24 * 60 * 60 * 1000);
            return formatter.format(datetmp);
        }
        catch (Exception e)
        {
            imsLogger.debug("操作出错:" + e.getMessage());
            return "";
        }
    }

    
    
    
    /**
     * 日期时间合法性校验 by qxd 注意是严格校验的那种 类似20081921(yyyyMMdd)将会被判断为错误。 (我使用标准指令格式化代码，但跟已有代码排版不一致，尽请谅解:)
     * 
     * @param srcData
     * @param formatStr
     * @return
     */
    public static boolean checkDate(String srcData, String formatStr)
    {
        if (srcData == null || "".equals(srcData))
        {
            return false;
        }
        try
        {
            SimpleDateFormat formate = new SimpleDateFormat(formatStr);
            formate.setLenient(false);
            formate.parse(srcData);
            return true;
        }
        catch (Exception e)
        {
            imsLogger.error("日期格式解析发生错误！", e);
            return false;
        }
    }

    public static Date checkForNull(Date checked, Date instead)
    {
        if (checked == null)
        {
            return instead;
        }
        return checked;
    }

    public static Date checkForNull(String checked, String instead) throws IMSException
    {
        if (checked == null)
        {
            return getFormattedDate(instead);
        }
        return getFormattedDate(checked);
    }

    public static Date checkForNull(Date checked)
    {
        if (checked == null)
        {
            return currentDate();
        }
        return checked;
    }

    public static Date checkForNull(String checked) throws IMSException
    {
        if (CommonUtil.isEmpty(checked))
        {
            return currentDate();
        }
        return getFormattedDate(checked);
    }

    /**
     * 日期转为秒数(日期的毫秒数除1000)
     */
    public static int toSecond(Date date)
    {
        if (date == null)
        {
            return 0;
        }
        long second = date.getTime() / 1000;
        return (second > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) second);
    }

    /**
     * 日期转为秒数(日期的毫秒数除1000)
     */
    public static int toSecond(String dateStr)
    {
        Date date = DateUtil.getFormattedDate(dateStr);
        if (date == null)
        {
            return 0;
        }
        long second = date.getTime() / 1000;
        return (second > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) second);
    }

    public static Date UTCToDate(long utc)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(utc * 1000);
        return calendar.getTime();
    }

    public static String UTCToString(long utc)
    {
        return DateUtil.getFormatDate(UTCToDate(utc), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
    }

    /**
     * @Description: 获取特定偏移量的日期
     */
    public static Date offsetDate(Date date, int type, int amount)
    {
        Calendar calendar = Calendar.getInstance();
        // fix bug ; for offset date from current time;
        calendar.setTime(date);
        calendar.add(type, amount);
        return calendar.getTime();
    }

    /**
     * @Description: 计算两日期的间隔天数
     * @Date 2012-4-5 tangjl5 修改计算天数的算法 
     */
    public static Long getBetweenDays(Date startDate, Date endDate)
    {
        Long days = 0L;
        
        DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
        Calendar c_b = Calendar.getInstance();
        Calendar c_e = Calendar.getInstance();
        String begin = null;
        String end = null;
        boolean flag = false;
        if (startDate.after(endDate))
        {
            begin = formatDate(endDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
            end = formatDate(startDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
            flag = true;
        }
        else
        {
            begin = formatDate(startDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
            end = formatDate(endDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
        }
        
        try{
         c_b.setTime(df.parse(begin));
         c_e.setTime(df.parse(end));
         
         while(c_b.before(c_e)){
          days++;
          c_b.add(Calendar.DAY_OF_YEAR, 1);
         }
         
        }catch(ParseException pe){
            imsLogger.error("日期格式解析发生错误！", pe);
        }
        
        if (flag)
            return -days;
        return days; 
    }
    
    /**
     * @Description: 计算两日期的大小
     * @Date 2012-05-21 wangdw5
     */
    public static boolean compare2Day(Date startDate, Date endDate)
    {
//        DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
        Calendar c_b = Calendar.getInstance();
        Calendar c_e = Calendar.getInstance();
        boolean flag = false;
        
         c_b.setTime(startDate);
         c_e.setTime(endDate);
         
         if(c_b.before(c_e)){
             flag = true;
         }
         
        
        return flag; 
    }

    /**
     * 时分表设置为0<BR>
     * wangjt 2011-10-10
     */
    public static Date dayBegin(Date date)
    {
        return jef.tools.DateUtils.dayBegin(date);
        // if (date == null)
        // {
        // return null;
        // }
        // Calendar newDate = Calendar.getInstance();
        // newDate.setTime(date);
        // newDate.set(Calendar.HOUR_OF_DAY, 0);
        // newDate.set(Calendar.MINUTE, 0);
        // newDate.set(Calendar.SECOND, 0);
        // newDate.set(Calendar.MILLISECOND, 0);
        //
        // return newDate.getTime();
    }
    /**
     * 时分秒设置为23:59:59
     * caohm5 2012-05-28
     */
    public static Date dayEnd(Date date)
    {
        return jef.tools.DateUtils.dayEnd(date);
        // if (date == null)
        // {
        // return null;
        // }
        // Calendar newDate = Calendar.getInstance();
        // newDate.setTime(date);
        // newDate.set(Calendar.HOUR_OF_DAY, 23);
        // newDate.set(Calendar.MINUTE, 59);
        // newDate.set(Calendar.SECOND, 59);
        //
        // return newDate.getTime();
    }
    
    /**
     * date转timestamp
     * luojb 2012-3-26
     * @param date
     * @return
     */
    public static Timestamp date2Timestamp(Date date)
    {
        return date == null ? null : new Timestamp(date.getTime());
    }
}
