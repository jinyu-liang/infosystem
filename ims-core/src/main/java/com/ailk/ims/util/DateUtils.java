package com.ailk.ims.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import jef.tools.DateFormats;
import jef.tools.DateUtils.TimeUnit;
import jef.tools.support.ThreadLocal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ailk.common.virtualtime.VirtualDate;
import com.ailk.common.virtualtime.VirtualTime;

/**
 * @Description: 从帐管迁移过来
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author lindx
 * @Date 2011-5-19
 */
public abstract class DateUtils {
    private static final Log logger = LogFactory.getLog(DateUtils.class);
    
    public static ThreadLocal<Calendar> defaultCalender = new ThreadLocal<Calendar>(){
        @Override
        protected Calendar initialValue() {
            return Calendar.getInstance();
        }
    };
    
    /**
     * 每个时间单位的毫秒数
     */
    private static final long MS_IN_DAY = 86400000L; 
    private static final long MS_IN_HOUR = 3600000L;
    private static final long MS_IN_MINUTE = 60000L;
    private static final long MS_IN_SECOND = 1000L;
    
    private static final int DAYS_OF_YEAR = 365;
    
  //日期时间全格式 24小时制
    /**
     *  24小时制日期时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static String        DATETIME_FORMAT       = "yyyy-MM-dd HH:mm:ss";//DATE_TIME_CS
    
    /**
     *  24小时制日期时间格式  yyyyMMddHHmmss
     */
    public static String        DATETIME_FORMAT2      = "yyyyMMddHHmmss";//DATE_TIME_SHORT_14
    
    /**
     *  24小时制时期时间一天开始 yyyy-MM-dd 00:00:00
     */
    public static String        DATE_ZEROTIME_FORMAT  = "yyyy-MM-dd 00:00:00";
    
    public static final ThreadLocal<DateFormat> TH_DATE_ZEROTIME_FORMAT = new ThreadLocal<DateFormat>(){
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat(DATE_ZEROTIME_FORMAT);
    	}
    };

    /**
     * 24小时制日期时间一天开始  yyyyMMdd000000
     */
    public static String        DATE_ZEROTIME_FORMAT2 = "yyyyMMdd000000";
    
    public static final ThreadLocal<DateFormat> TH_DATE_ZEROTIME_FORMAT2 = new ThreadLocal<DateFormat>(){
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat(DATE_ZEROTIME_FORMAT2);
    	}
    };    
    
    /**
     *  24小时制日期时间一天末  yyyy-MM-dd 23:59:59
     */
    public static String        DATE_FULLTIME_FORMAT  = "yyyy-MM-dd 23:59:59";
    
    public static final ThreadLocal<DateFormat> TH_DATE_FULLTIME_FORMAT = new ThreadLocal<DateFormat>(){
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat(DATE_FULLTIME_FORMAT);
    	}
    };     
    
    /**
     *  24小时制日期时间一天末 yyyyMMdd235959
     */
    public static String        DATE_FULLTIME_FORMAT2 = "yyyyMMdd235959";
    
    public static final ThreadLocal<DateFormat> TH_DATE_FULLTIME_FORMAT2 = new ThreadLocal<DateFormat>(){
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat(DATE_FULLTIME_FORMAT2);
    	}
    };      
    
    //日期时间全格式 12小时制
    /**
     * 12小时制日期时间格式 yyyy-MM-dd hh:mm:ss
     */
    public static String        DATETIME12_FORMAT       = "yyyy-MM-dd hh:mm:ss";
    
    public static final ThreadLocal<DateFormat> TH_DATETIME12_FORMAT = new ThreadLocal<DateFormat>(){
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat(DATETIME12_FORMAT);
    	}
    };     
    
    /**
     * 12小时制日期时间格式 yyyyMMddhhmmss
     */
    public static String        DATETIME12_FORMAT2      = "yyyyMMddhhmmss";//DATE_TIME_SHORT_14

    //日期全格式
    /**
     * 日期格式yyyy-MM-dd 
     */
    public static String        DATE_FORMAT           = "yyyy-MM-dd";//DateFormats.DATE_CS
    /**
     * 日期格式 yyyyMMdd
     */
    public static String        DATE_FORMAT2          = "yyyyMMdd";//DateFormats.DATE_SHORT

    //年月
    /**
     * 年月 yyyy-MM
     */
    public static String        YEAR_MONTH_FORMAT     = "yyyy-MM";
    
    public static final ThreadLocal<DateFormat> TH_YEAR_MONTH_FORMAT = new ThreadLocal<DateFormat>(){
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat(YEAR_MONTH_FORMAT);
    	}
    }; 
    
    /**
     * 年月yyyyMM
     */
    public static String        YEAR_MONTH_FORMAT2    = "yyyyMM";//YAER_MONTH
    
    //某年某月的第一天 added by zhao hong 
    /**
     * 某月的第一天 yyyy-MM-01
     */
    public static String        YEAR_MONTH_FIRSTDAY = "yyyy-MM-01"; 
    
    public static final ThreadLocal<DateFormat> TH_YEAR_MONTH_FIRSTDAY = new ThreadLocal<DateFormat>(){
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat(YEAR_MONTH_FIRSTDAY);
    	}
    };     
    
    //年、月、日                     added by 崔建琪 2007-11-23
    /**
     * 4位年
     */
    public static String        YEAR_FORMAT           = "yyyy";
    /**
     * 2位月
     */
    public static String        MONTH_FORMAT          = "MM";
    /**
     * 2位天
     */
    public static String        DAY_FORMAT            = "dd";

    //时间全格式 24小时制
    /**
     *  24小时制时间 时分秒 冒号分隔  HH:mm:ss
     */
    public static String        TIME_FORMAT           = "HH:mm:ss";
    /**
     *  24小时制时间 时分秒 无分隔  HHmmss
     */
    public static String        TIME_FORMAT2          = "HHmmss";
    
    //时间全格式 12小时制
    /**
     *  12小时制  时分秒 冒号分隔  hh:mm:ss
     */
//    public static String        TIME12_FORMAT           = "hh:mm:ss";
    /**
     * 12小时制  时分秒 无分隔  hhmmss
     */
//    public static String        TIME12_FORMAT2          = "hhmmss";

    //营业cs版本日期时间格式
    /**
     * 24小时制 年月日时分秒 yyyy/MM/dd HH:mm:ss
     */
    public static String        DATETIME_SLASH_FORMAT = "yyyy/MM/dd HH:mm:ss";
    
    public static final ThreadLocal<DateFormat> TH_DATETIME_SLASH_FORMAT = new ThreadLocal<DateFormat>(){
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat(DATETIME_SLASH_FORMAT);
    	}
    };       
    
    /**
     * 24小时制 年月日 yyyy/MM/dd
     */
    public static String        DATE_SLASH_FORMAT     = "yyyy/MM/dd";
    
    public static final ThreadLocal<DateFormat> TH_DATE_SLASH_FORMAT = new ThreadLocal<DateFormat>(){
    	protected DateFormat initialValue() {
    		return new SimpleDateFormat(DATETIME_SLASH_FORMAT);
    	}
    };      
    
	/**
	 * 不能实例化
	 */
	private DateUtils(){
	}
	
	/**
	 * 返回当前时间<br/>
	 * 这里返回当前服务器的虚拟时间
	 * @return 当前时间
	 */
	public static Date getCurrentDate(){
		return new VirtualDate();
	}
	
	/**
	 * 用毫秒表示的时间
	 * <br/>
	 * 这里返回当前服务器的虚拟时间
	 * @return 毫秒数表示的虚拟时间值
	 */
	public static long currentTimeMillis(){
		return VirtualTime.getVirtualTime();
	}
	
	/**
	 * abm的日期是 yyyymmddhhmmss
	 * @param date
	 * @return abmDate
	 */
	public static long toAbmFormat(Date date){
	    if(date == null) return 0;
	    String llStr = cvtFormattedDate(date,DateUtils.DATETIME_FORMAT2);
	    return Long.valueOf(llStr);
	}
	
	/**
	 * abm的日期是 yyyymmdd
	 * @param date
	 * @return abmDate
	 */
	public static int toAbmFormatInt(Date date){
		String llStr = DateFormats.DATE_SHORT.get().format(date);
	    return Integer.valueOf(llStr);
	}
	
	/**
	 * 从abm日期转化为日期对象
	 * @param abmDate  yyyymmddhhmmss 形的时间
	 * @return 日期对象
	 */
	public static Date fromAbmFormat(long abmDate){
		try {
			return DateFormats.DATE_TIME_SHORT_14.get().parse(String.valueOf(abmDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 日期转换函数，暂时先这样，后面有时间做进一步封装
	 * @param dateStr
	 * @return
	 */
	public static Date strToDate(String dateStr){
		logger.debug("dateStr:");
		logger.debug(dateStr);
		if(dateStr==null || dateStr.length()==0){
			return null;
		}
		try {
			return DateFormats.DATE_TIME_CS.get().parse(dateStr);
		} catch (Exception e) {
			logger.error(e,e);
			return null;
		}
	}	
	
	/**
	 * 从abm日期转化为日期对象
	 * @param abmDate  yyyymmddhhmmss 形的时间
	 * @return 日期对象
	 */
	private static Date fastAbmFormat2(long abmDate){
	    long yyyy = abmDate / 10000000000L; // 年
	    abmDate   = abmDate % (yyyy ==0 ?1 : yyyy * 10000000000L);
	    long MM   = abmDate / 100000000L; // 月
	    abmDate   = abmDate % (MM ==0 ?1 : MM * 100000000L);
	    long dd   = abmDate / 1000000L;   // 日
	    abmDate   = abmDate % (dd ==0 ?1 : dd * 1000000L);
	    long hh   = abmDate / 10000L;      //小时
	    abmDate   = abmDate % (hh ==0 ?1 : hh * 10000L);
	    long mm   = abmDate / 100L;       // 分钟
	    long ss   = abmDate % (mm ==0 ?1 : mm * 100L);         // 秒
	    Calendar cal = defaultCalender.get();
	    //cal.clear();
	    cal.set(Calendar.MILLISECOND, 0);
	    cal.set((int)yyyy, (int)MM-1, (int)dd, (int)hh, (int)mm, (int)ss);
	    return cal.getTime();
	}
	
	/**
	 * 从泰历日期转化为日期对象
	 * @param  Date形的时间
	 * @return 日期格式dd-MM-yyyy对象
	 */
	public static String thDateToString(Date inputDate){        
		return DateFormats.DATE_SHORT.get().format(inputDate);
	}
	
	/**
	 * 从日期对象转化为泰历日期
	 * @param  Date形的时间
	 * @return 日期格式yyyyMMdd对象
	 */
	public static Date thDateToString(String inputDate){
		try {
			Date thDate = DateFormats.DATE_SHORT.get().parse(inputDate);
			return thDate;
		} catch (Exception e) {
			logger.error(e,e);
		}
		return null;
	}
	
	/**
	 * 从日期转化为日期对象
	 * @param  Date形的时间
	 * @return 日期格式dd-MM-yyyy对象
	 */
	public static String dateToString(Date inputDate){    
		return DateFormats.DATE_SHORT.get().format(inputDate);
	}
	/**
	 * 从日期转化为日期对象
	 * @param  Date形的时间
	 * @return 日期格式dd-MM-yyyy对象
	 */
	public static String dateToStringtwo(Date inputDate){        
		return DateFormats.DATE_TIME_SHORT_14.get().format(inputDate);
	}
	
	/**
	 * 从日期转化为日期对象
	 * @param  String形的时间
	 * @return 日期格式dd-MM-yyyy对象
	 */
	public static Date thStringToDate(String inputDate){        
        if (inputDate == null || inputDate.equalsIgnoreCase(""))
            return null;
        DateFormat sdf = getDateFormat(DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(inputDate, new ParsePosition(0));
        }
        catch (Exception ex) {
            sdf = getDateFormat(DATE_SLASH_FORMAT);
            date = sdf.parse(inputDate, new ParsePosition(0));
            return date;
        }
        return date;
	}
	
	/**
	 * 从泰历日期转化为日期对象
	 * @param  String形的时间
	 * @return 日期格式dd-MM-yyyy对象
	 */
	public static String thStringToString(String inputDate){
		Date date = getDateByString(inputDate);
		String strDate = thDateToString(date);
		strDate = strDate.substring(2,6);
		return strDate;
	}
	
	/**
	 * 从泰历日期转化为帐务月
	 * @param  String形的时间yyMM
	 * @return 日期格式：yyyyMM
	 */
	public static String thStringToMonth(String inputDate){
		inputDate = "25" + inputDate + "01";
		Date date = thDateToString(inputDate);
		String strMonth = cvtFormattedDate(date, YEAR_MONTH_FORMAT2);
		return strMonth;
	}
	
	/**
	 * 从日期转化为日期对象
	 * @param  String形的时间
	 * @return 日期格式dd-MM-yyyy对象
	 */
	public static String stringToString(String inputDate){
		Date date = getDateByString(inputDate);
		String strDate = dateToString(date);
		return strDate;
	}
	/**
	 * 计算两个时间相隔的月数
	 * @param date1 时间点1
	 * @param date2 时间点2
	 * @return date2 - date1 的月数查
	 * @Description 之前方法有缺陷计算月份在跨年情况下有误 2013-6-25 modify
	 */
	 @SuppressWarnings("deprecation")
	public static int monthsBetween(Date date1 ,Date date2){
		 
		if(date1.getTime() <= date2.getTime()){
			return date2.getMonth() - date1.getMonth() + (date2.getYear() - date1.getYear())*12;
		}else{
			return date1.getMonth() - date2.getMonth() + (date1.getYear() - date2.getYear())*12;
		}
	}
	 
	/**
	 * 计算两个时间相隔的月数
	 * @param date1 时间点1
	 * @param date2 时间点2
	 * @return date2 -date1 的年数查
	 */
	public static int yearBetween(Date date1 ,Date date2){
	   @SuppressWarnings("deprecation")
	   int year=date2.getYear()-date1.getYear();
		return year;
	}
	
	
	/**
	 * 计算两个时间相隔的天数,不足一天不算一天
	 * @param date1 时间点1
	 * @param date2 时间点2
	 * @return date2 -date1 的天数查
	 */
	public static long daysBetween(Date date1 ,Date date2){

	    return (date2.getTime() - date1.getTime()) / MS_IN_DAY;
	}
	
	
    /**
     * 根据传入的日期字符串转换成相应的日期对象，如果字符串为空或不符合日期格式，则返回当前时间。
     * 
     * @param strDate
     *            String 日期字符串
     * @return java.sql.Timestamp 日期对象
     */

    public static Date getDateByString(String strDate) {

        if (strDate == null || strDate.trim().equals("")) {
            return getCurrentDate();
        }

        try {

            strDate = getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss")
                    + ".000000000";

            return java.sql.Timestamp.valueOf(strDate);

        } catch (Exception ex) {

            logger.warn("getDateByString exception: " + ex.getMessage());
            return new java.sql.Timestamp(System.currentTimeMillis());
        }

    }
    
    /**
     * 对输入的日期字符串进行格式化,如果输入的是0000/00/00 00:00:00则返回空串.
     * 
     * @param strDate
     *            String 需要进行格式化的日期字符串
     * @param strFormatTo
     *            String 要转换的日期格式
     * @return String 经过格式化后的字符串
     */

    public static String getFormattedDate(String strDate, String strFormatTo) {

        if (strDate == null || strDate.trim().equals("")) {

            return "";

        }

        strDate = strDate.replace('/', '-');

        strFormatTo = strFormatTo.replace('/', '-');

        if (strDate.equals("0000-00-00 00:00:00")
                || strDate.equals("1800-01-01 00:00:00")) {

            return "";

        }

        String formatStr = strFormatTo; // "yyyyMMdd";
        if (strDate == null || strDate.trim().equals("")) {
            return "";
        }

        switch (strDate.trim().length()) {

        case 6:

            if (strDate.substring(0, 1).equals("0")) {
                formatStr = "yyMMdd";
            } else {
                formatStr = "yyyyMM";
            }

            break;

        case 8:
            formatStr = "yyyyMMdd";
            break;

        case 10:
            if (strDate.indexOf("-") == -1) {
                formatStr = "yyyy/MM/dd";
            } else {
                formatStr = "yyyy-MM-dd";
            }

            break;
        case 11:
            if (strDate.getBytes().length == 14) {
                formatStr = "yyyy年MM月dd日";
            } else {
                return "";
            }

        case 14:
            formatStr = "yyyyMMddHHmmss";
            break;

        case 19:
            if (strDate.indexOf("-") == -1) {
                formatStr = "yyyy/MM/dd HH:mm:ss";
            } else {
                formatStr = "yyyy-MM-dd HH:mm:ss";
            }
            break;

        case 21:
            if (strDate.indexOf("-") == -1) {
                formatStr = "yyyy/MM/dd HH:mm:ss.S";
            } else {
                formatStr = "yyyy-MM-dd HH:mm:ss.S";
            }
            break;
        default:
            return strDate.trim();
        }

        try {

            DateFormat formatter = getDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(strDate));
            formatter = getDateFormat(strFormatTo);
            return formatter.format(calendar.getTime());
        } catch (Exception e) {
            logger.warn("转换日期字符串格式时出错;" + e.getMessage());
            return "";
        }
    }    
	
    
    /**
     * 在原有的时间上加上某个值
     * @param aDate 指定时间
     * @param days 要变更的量，可以为负数，
     * @return 返回变更后的日期
     * @see VirtualDate
     */
    public static Date addDays(Date aDate, long days){
        long timeInMs = aDate.getTime() + days * MS_IN_DAY;
        return new Date(timeInMs);
    }
    
    /**
     * 增加
     * @param aDate 指定时间
     * @param delta 要变更的量，可以为负数，
     * @param timeUnit delta参数的时间单位
     * @return 返回变更后的日期
     * @see TimeUnit
     * @see VirtualDate
     */
    public static Date addMonths(Date aDate, int delta){
        Date newDate = org.apache.commons.lang.time.DateUtils.addMonths(aDate, delta);
        return new Date(newDate.getTime());
    }
    
    /**
     * 在原有的时间上加上某个值
     * @param aDate 指定时间
     * @param delta 要变更的量，可以为负数，
     * @param timeUnit delta参数的时间单位
     * @return 返回变更后的日期
     * @see TimeUnit
     * @see VirtualDate
     */
    public static Date addYears(Date aDate, int delta){
        Date newDate = org.apache.commons.lang.time.DateUtils.addYears(aDate, delta);
        return new Date(newDate.getTime());
    }
    
    /**
     * 将时间截取到指定时间的当天的0时0分0秒
     * <p/>
     * 例如 传入的时间是2011-06-21 10:19:13，
     * 结果得到 2011-06-21 00:00:00
     * @param aDate 指定时间
     * @return 返回变更后的日期
     * @see VirtualDate
     */
    public static Date startOfDay(Date aDate){
        Date newDate = org.apache.commons.lang.time.DateUtils.truncate(aDate, Calendar.DATE);
        return newDate;
    }
    
    /**
     * 将时间截取到指定时间的当天的23时59分59秒
     * <p/>
     * 例如 传入的时间是2011-06-21 10:19:13，
     * 结果得到 2011-06-21 23:59:59.999
     * @param aDate 指定时间
     * @return 返回变更后的日期
     * @see VirtualDate
     */
    public static Date endOfDay(Date aDate){
        Date newDate = org.apache.commons.lang.time.DateUtils.truncate(aDate, Calendar.DATE);
        long timeImMs = newDate.getTime() + MS_IN_DAY - 1;
        return new Date(timeImMs);
    }
    
    /**
     * 字符串形式的金额转换成数字
     * @param strValue String 换的字符串来源，格式“xxxx.xx”，单位为"元"
     * @return long 数字金额，单位“分”
     */
    public static Date endOfMonth(Date ts) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts.getTime());
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH,1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        c.set(Calendar.MILLISECOND,0);

        return new Date(c.getTimeInMillis());
    }

    
    /**
     * 获得所在月的第一天
     * @param ts
     * @return
     */
    public static Date startOfMonth(Date ts) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts.getTime());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        return new Date(c.getTimeInMillis());
    }
    
    /**
     * 获得所在月的第一天
     * @param ts
     * @return
     */
    public static Date startOfYear(Date ts) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts.getTime());
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return new Date(c.getTimeInMillis());
        
    }
    
    /**
     * 获得上一年的时间
     * @return 上一年的时间
     * @see #getCurrentDate()
     */
    public static Date getLastYearDate(){
        Calendar c = Calendar.getInstance();
        Date ts = getCurrentDate();// instance of VirtualDate;
        c.setTimeInMillis(getCurrentDate().getTime());
        c.set(Calendar.YEAR, c.get(Calendar.YEAR)-1);
        
        return new Date(c.getTimeInMillis());
    }
  

    /**
     * 将一个兹洛夫表示的日期,转化的另外一个日期字符表示
     * 
     * @param strDate  要转化的日期字符
     * @param strOldFormat 要转化的日期字符的格式
     * @param strNewFormat 新的格式
     * @return 按新的格式表示的日期字符
     */
    public static String cvtDateString(String strDate, String strOldFormat,
            String strNewFormat) {
        try {
            DateFormat sdfOld = getDateFormat(strOldFormat);
            DateFormat sdfNew = getDateFormat(strNewFormat);
            Date date = sdfOld.parse(strDate);
            return sdfNew.format(date);
        }
        catch (Exception ex) {
            return "";
        }
    }

    /**
     * 将一个日期字符按指定格式转换成{@link Timestap}
     * @param inputDate 输入的日期
     * @param format 日期格式
     * @return 如果格式正确将返回正确的 Timestap
     */
    public static Date cvtStringToDate(String inputDate, String format) {
        if (inputDate == null || inputDate.equalsIgnoreCase(""))
            return null;
        DateFormat sdf = getDateFormat(format);
        Date date = sdf.parse(inputDate, new ParsePosition(0));
        if(date == null){
            logger.error("执行函数cvtStringToDate(\""+inputDate+"\",\""+format+"\")失败，原因：SimpleDateFormat和输入日期不匹配！");
            return null;
        }
        return new Date(date.getTime());
    }

    /**
     * 将字符串解析为短整型
     * @param data 短整型字符串
     * @return 返回短整型 ,NOTE:如果出错返回0
     */
    public static short stringToShort(String data) {
        short ret = (short) 0;
        try {
            ret = Short.parseShort(data);
        }
        catch (NumberFormatException ex) {
            if(null != data && !"".equals(data.trim()))
                logger.info("DataFormat.stringToShort方法错误提示：该关键字[" + data + "]不是短整数类型!");
        }
        return ret;
    }

    /**
     * 将字符串解析为整型
     * @param data 整型字符串
     * @return 返回整型 ,NOTE:如果出错返回0
     */
    public static int stringToInt(String data) {
        int keyId = 0;
        try {
            keyId = Integer.parseInt(data);
        }
        catch (NumberFormatException ex) {
            if(null != data && !"".equals(data.trim()))
                logger.info("DataFormat.stringToInt方法错误提示：该关键字[" + data + "]不是整数类型!");
        }
        return keyId;
    }

    /**
     * 将字符串解析为长整型
     * @param data 长整型字符串
     * @return 返回长整型 ,NOTE:如果出错返回0
     */
    public static long stringToLong(String data) {
        long keyId = 0l;
        try {
            keyId = Long.parseLong(data);
        }
        catch (NumberFormatException ex) {
            if(null != data && !"".equals(data.trim()))
                logger.info("DataFormat.stringToLong方法错误提示：该关键字[" + data + "]不是长整数类型!");
        }
        return keyId;
    }

    /**
     * 将字符串解析为双精度浮点
     * @param data 双精度浮点字符串
     * @return 返回双精度浮点 ,NOTE:如果出错返回0.0
     */
    public static double stringToDouble(String data) {
        double ret = 0.0;
        try {
            ret = Double.parseDouble(data);
        }
        catch (NumberFormatException ex) {
            String errMsg = "该字段不是double类型!" + ex.getMessage();
            logger.error(errMsg);
        }
        return ret;
    }

    /**
     * 将字符串解析为浮点
     * @param data 浮点字符串
     * @return 返回浮点 ,NOTE:如果出错返回0.0
     */
    public static float stringToFloat(String data) {
        float ret = 0;
        try {
            ret = Float.parseFloat(data);
        } catch (NumberFormatException ex) {
            String errMsg = "该字段不是float类型!" + ex.getMessage();
            logger.error(errMsg);
        }
        return ret;
    }

    /**
     * 这个转化函数会对日期日期字符串进行尝试
     * 首先用 {@link #DATE_FORMAT} 尝试能不能转换
     * 如果不行 使用格式 {@link DATE_SLASH_FORMAT} 格式进行尝试
     * 
     * 字符转化成日期
     * @param inputDate 输入日期
     * @return
     */
    public static Date stringToDate(String inputDate) {
        if (inputDate == null || inputDate.equalsIgnoreCase(""))
            return null;
        DateFormat sdf = getDateFormat(DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(inputDate, new ParsePosition(0));
            if(date==null){
                sdf = getDateFormat(DATE_FORMAT2);//DateFormats.DATE_SHORT.get().format(date)
                date = sdf.parse(inputDate, new ParsePosition(0));
            }
        }
        catch (Exception ex) {
            DateFormat sdfs = getDateFormat(DATE_SLASH_FORMAT);
            date = sdfs.parse(inputDate, new ParsePosition(0));
            return date;
        }
        return date;
    }

    /**
     * 使用指定的格式 格式化一个日期
     * @param time 要格式化的时间
     * @param strPattern 格式
     * @return 格式化后的时间字串
     */
    public static String dateToString(java.util.Date time, String strPattern) {
        DateFormat sdf = getDateFormat(strPattern);
        return sdf.format(time);
    }

    /**
     * 把timestamp类型的日期转换成yyyy-MM-dd hh:mm:ss格式的字符串。
     * @param dtDate Date
     * @return String
     */
    public static String cvtToDateTimeStr(Date dtDate) {
        return cvtFormattedDate(dtDate, DATETIME_FORMAT);
    }

    /**
     * 把timestamp类型的日期转换成yyyy-MM-dd格式的字符串。
     * @param dtDate Date
     * @return String
     */
    public static String cvtToDateStr(Date dtDate) {
        return cvtFormattedDate(dtDate, DATE_FORMAT);
    }

    /**
     * 把timestamp类型的日期转换成yyyy/MM/dd hh:mm:ss格式的字符串。
     * @param dtDate Date
     * @return String
     */
    public static String cvtToDateTimeSlashStr(Date dtDate) {
        return cvtFormattedDate(dtDate, DATETIME_SLASH_FORMAT);
    }

    /**
     * 把timestamp类型的日期转换成yyyy/MM/dd格式的字符串。
     * @param dtDate Date
     * @return String
     */
    public static String cvtToDateSlashStr(Date dtDate) {
        return cvtFormattedDate(dtDate, DATE_SLASH_FORMAT);
    }

    /**
     * 把timestamp类型的日期转换成指定格式的字符串,如果时间值为null、年份小于1900、转换出错
     * 等，返回默认值。
     * @param dtDate Date    被转换的时间
     * @param strFormatTo String  转换的时间格式
     * @param defaultValue String 默认值
     * @return String  转换后的字符串格式
     */
    public static String cvtFormattedDate(Date dtDate,
            String strFormatTo,
            String defaultValue) {
        String dateString = cvtFormattedDate(dtDate, strFormatTo);
        if (dateString == null || "".equals(dateString)) {
            return defaultValue;
        }

        return dateString;

    }

    /**
     * 格式化日期
     * 
     * @param dtDate
     * @param strFormatTo
     * @return
     */
    public static String cvtFormattedDate(Date dtDate,String strFormatTo) {
        if (dtDate == null) {
            return "";
        }
        strFormatTo = strFormatTo.replace('/', '-');
        try {
            DateFormat formatter = getDateFormat(strFormatTo);
            return formatter.format(dtDate);
        } catch (Exception e) {
            logger.error("转换日期字符串格式时出错;" + e.getMessage());
            return "";
        }
    }

    /**
     * Adds the specified (signed) amount of time to the given time field
     * @param original
     * @param field year:Calendar.YEAR; month:Calendar.MONTH; date:Calendar.DATE;
     * hour:Calendar.HOUR; minute:Calendar.MINUTE; second:Calendar.SECOND
     * 
     * @param original
     * @param field
     * @param amount
     * @return
     */
    public static Date addDateTime(Date original, int field, int amount) {
        Calendar calOriginal = Calendar.getInstance();
        calOriginal.setTime(original);

        //+1900
        GregorianCalendar calendar = new GregorianCalendar(calOriginal.get(Calendar.
                YEAR),
                calOriginal.get(Calendar.MONTH), calOriginal.get(Calendar.DAY_OF_MONTH),
                calOriginal.get(Calendar.HOUR_OF_DAY), calOriginal.get(Calendar.MINUTE),
                calOriginal.get(Calendar.SECOND));
        calendar.add(field, amount);
        Date temp = new Date(calendar.getTime().getTime());
        return temp;
    }
  
    /**
     * 返回指定月的月末信息
     * @param ts 指定时间
     * @return 月末
     */
    public static Date endOfAMonth(Date ts) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts.getTime());
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH,1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        c.set(Calendar.MILLISECOND,0);
        return new Date(c.getTimeInMillis());
    }
    
    /**
     * 获得所在月的第一天
     * @param ts
     * @return
     */
    public static Date startOfAmonth(Date ts) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts.getTime());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return new Date(c.getTimeInMillis());

    }
    
    /**
     * 校验字符串是否为合法日期格式
     * @param dateStr
     * @param pattern
     * @return
     */
    public static boolean isValidDate(String dateStr, String pattern) {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(pattern);
		try {
			df.setLenient(false);
			df.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;

		}
	}
    
    /**
     * 
     * @param periodType 周期类型 0-指定 1-年 2-月 3-周 4-日
     * @param periodUnit 周期单位，日的时候无效
     * @param offerDay   偏移天数
     * @param validDate  生效期
     * @param expireDate 失效期
     * @return			  第一个有效时间
     */
    public static Date getNextDay(int periodType,int periodUnit,int offerDay,Date validDate , Date expireDate) {
		Date targetDate = null;
		Date today = DateUtils.getCurrentDate();
		if(validDate !=null && today.before(validDate)){
			today = validDate;
		}
		offerDay--;
		if(periodType == 1){//年
			if(offerDay+1 >= DAYS_OF_YEAR){
				offerDay = DAYS_OF_YEAR-1;
			}
			targetDate  = org.apache.commons.lang.time.DateUtils.addDays(org.apache.commons.lang.time.DateUtils.truncate(today, Calendar.YEAR),offerDay);
			if(targetDate.before(today)){//表示今年已经过了
				targetDate  = org.apache.commons.lang.time.DateUtils.addDays(org.apache.commons.lang.time.DateUtils.truncate(org.apache.commons.lang.time.DateUtils.addYears(today, periodUnit), Calendar.YEAR),offerDay);
			}
		}else if(periodType == 2){//月
			int dayOfMonth = 28;//当前月天数
			if(dayOfMonth-1 <=offerDay){
				offerDay = dayOfMonth-1;
			}
			targetDate  = org.apache.commons.lang.time.DateUtils.addDays(org.apache.commons.lang.time.DateUtils.truncate(today,Calendar.MONTH),offerDay);
			if(targetDate.before(today)){//表示当前月已经过了
				targetDate  = org.apache.commons.lang.time.DateUtils.addDays(org.apache.commons.lang.time.DateUtils.truncate(org.apache.commons.lang.time.DateUtils.addMonths(today, periodUnit), Calendar.MONTH),offerDay);
			}
		}else if(periodType == 3){//周
			Calendar calendar =	Calendar.getInstance();
			if(offerDay >= calendar.DAY_OF_WEEK){
				offerDay = calendar.DAY_OF_WEEK-1;
			}
			calendar.setFirstDayOfWeek(Calendar.SUNDAY);
			calendar.setTime(today);
			calendar.add(Calendar.DATE, 1 - calendar.get(Calendar.DAY_OF_WEEK));
			targetDate  = org.apache.commons.lang.time.DateUtils.addDays(org.apache.commons.lang.time.DateUtils.truncate(calendar.getTime(), Calendar.DATE),offerDay);
			if(targetDate.before(today)){//表示当前周已经过了
				targetDate  = org.apache.commons.lang.time.DateUtils.addDays(org.apache.commons.lang.time.DateUtils.addWeeks(org.apache.commons.lang.time.DateUtils.truncate(calendar.getTime(), Calendar.DATE), periodUnit),offerDay);
			}
		}else if(periodType == 4){//日
			Calendar calendar =	Calendar.getInstance();
			targetDate = org.apache.commons.lang.time.DateUtils.addDays(org.apache.commons.lang.time.DateUtils.truncate(calendar.getTime(), Calendar.DATE),++offerDay);
		}
		return  expireDate !=null && targetDate.after(expireDate)?null:targetDate;
	}
    
    /**
     * 获取format
     * @param format
     */
    private static DateFormat getDateFormat(String format){
    	if(DATETIME_FORMAT.equals(format)){
    		return DateFormats.DATE_TIME_CS.get();
    	}else if(DATETIME_FORMAT2.equals(format)){
    		return DateFormats.DATE_TIME_SHORT_14.get();
    	}else if(DATE_ZEROTIME_FORMAT.equals(format)){
    		return TH_DATE_ZEROTIME_FORMAT.get();
    	}else if(DATE_ZEROTIME_FORMAT2.equals(format)){
    		return TH_DATE_ZEROTIME_FORMAT2.get();
    	}else if(DATE_FULLTIME_FORMAT.equals(format)){
    		return TH_DATE_FULLTIME_FORMAT.get();
    	}else if(DATE_FULLTIME_FORMAT2.equals(format)){
    		return TH_DATE_FULLTIME_FORMAT2.get();
    	}else if(DATETIME12_FORMAT.equals(format)){
    		return TH_DATETIME12_FORMAT.get();
    	}else if(DATETIME12_FORMAT2.equals(format)){
    		return DateFormats.DATE_TIME_SHORT_14.get();
    	}else if(DATE_FORMAT.equals(format)){
    		return DateFormats.DATE_CS.get();
    	}else if(DATE_FORMAT2.equals(format)){
    		return DateFormats.DATE_SHORT.get();
    	}else if(YEAR_MONTH_FORMAT.equals(format)){
    		return TH_YEAR_MONTH_FORMAT.get();
    	}else if(YEAR_MONTH_FORMAT2.equals(format)){
    		return DateFormats.YAER_MONTH.get();
    	}else if(YEAR_MONTH_FIRSTDAY.equals(format)){
    		return TH_YEAR_MONTH_FIRSTDAY.get();
    	}else if(DATETIME_SLASH_FORMAT.equals(format)){
    		return TH_DATETIME_SLASH_FORMAT.get();
    	}else if(DATE_SLASH_FORMAT.equals(format)){
    		return TH_DATE_SLASH_FORMAT.get();
    	}
    	
    	return new SimpleDateFormat(format);
    }
    
    /**
     * 秒数转化为时间
     * @param seconds
     */
    public static Date secToDate(long second){
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(second*1000);
    	return c.getTime();
    }
    
    /**
     * 除去时分秒，看是否为同一天
     * @param srcDate
     * @param desDate
     * @return
     */
	public static boolean isSameDay(Date srcDate,Date desDate){
		
		if(srcDate == null && desDate != null){
			return false;
		}
		
		if(desDate == null && srcDate != null){
			return false;
		}		
		
		Calendar c = Calendar.getInstance();
		c.setTime(srcDate);
		
		int srcYear = c.get(Calendar.YEAR);
		int srcMonth = c.get(Calendar.MONTH);
		int srcDay = c.get(Calendar.DAY_OF_MONTH);
		
		c.setTime(desDate);
		
		int desYear = c.get(Calendar.YEAR);
		int desMonth = c.get(Calendar.MONTH);
		int desDay = c.get(Calendar.DAY_OF_MONTH);
		
		return srcYear==desYear && srcMonth==desMonth && srcDay==desDay;
		
	}    
    
    
    
    public static void main (String args[])
    {
    	
//    	System.out.println(DateFormats.DATE_CS.get().format(DateUtils.secToDate(1361953926000L)));
//    	
//		int userCycle = 10000006;
//
//		int by1000000 = userCycle/1000000;
//		int by100000  = userCycle/100000;
//		int by10000   = userCycle/10000;
//		int by1000    = userCycle/1000;
//		int by100 	  = userCycle/100;
//		
//		int os_sts = by1000000;
//		int rerating_flag = by100000 - by1000000*10;
//		int unbilling_flag = by10000 - by100000*10;
//		int fraud = by100 - by1000*10;
//		int sts = userCycle - by100*100;
    	

    	Date srcDate = DateUtils.getCurrentDate();
    	Date desDate = DateUtils.addDays(srcDate, 1);
    	
    	long startTime = System.currentTimeMillis();
    	
    	for(int i =0;i<10;i++){
    		isSameDay(srcDate,desDate);
    	}
    	
    	long endTime = System.currentTimeMillis();

//    	System.out.println("isSameDay cost :"+(endTime - startTime));
//    	
//    	startTime = System.currentTimeMillis();
//    	
//    	for(int i =0;i<10;i++){
//    		isSameDay2(srcDate,desDate);
//    	}
//    	
//    	endTime = System.currentTimeMillis();    
//    	
//    	System.out.println("isSameDay2 cost :"+(endTime - startTime));
     
    }

 
}
