package com.ailk.ims.abmtransfer.commom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.tools.DateFormats;
import jef.tools.reflect.BeanUtils;
import jef.tools.support.ThreadLocal;
import com.ailk.common.virtualtime.VirtualDate;
import com.ailk.easyframe.route.client.RouterClient;
import com.ailk.easyframe.route.entity.dto.RouteDimension;
import com.ailk.easyframe.route.entity.dto.RouteQryParam;
import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.easyframe.route.entity.dto.RouteRet;
import com.ailk.easyframe.route.entity.dto.Sharding;
import com.ailk.easyframe.route.service.RouteNotFoundException;

public class SdlUtil
{

    /**
     * 用户级别0
     */
    public static int OWNER_TEYP_USER = 0;
    /**
     * 帐户级别1
     */
    public static int OWNER_TEYP_ACCT = 1;
    /**
     * 群组帐户级别2
     */
    public static int OWNER_TEYP_GROUP = 2;

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

    /**
     * 通过类型和帐户、用户号，生成唯一标识
     * 
     * @param ownerType
     * @param ownerId
     * @return
     */
    public static long genKey(int ownerType, long ownerId)
    {
        return ownerType + ownerId * 10;
    }

    /**
     * 通过唯一标识获取用户帐户类型
     * 
     * @param key
     * @return
     */
    public static int getKeyType(long key)
    {
        return (int) (key % 10);
    }

    /**
     * 通过唯一标识获取用户帐户号码
     * 
     * @param key
     * @return
     */
    public static long getKeyId(long key)
    {
        return key / 10;
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
     * abm的日期是 yyyymmddhhmmss
     * 
     * @param date
     * @return abmDate
     */
    public static long toAbmFormat(Date date)
    {
        if (date == null)
            return 0;
        String llStr = cvtFormattedDate(date, SdlUtil.DATETIME_FORMAT2);
        return Long.valueOf(llStr);
    }

    /**
     * abm的日期是 yyyymmdd
     * 
     * @param date
     * @return abmDate
     */
    public static int toAbmFormatInt(Date date)
    {
        String llStr = DateFormats.DATE_SHORT.get().format(date);
        return Integer.valueOf(llStr);
    }

    /**
     * 格式化日期
     * 
     * @param dtDate
     * @param strFormatTo
     * @return
     */
    public static String cvtFormattedDate(Date dtDate, String strFormatTo)
    {
        if (dtDate == null)
        {
            return "";
        }
        strFormatTo = strFormatTo.replace('/', '-');
        try
        {
            DateFormat formatter = getDateFormat(strFormatTo);
            return formatter.format(dtDate);
        }
        catch (Exception e)
        {
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
        return new SimpleDateFormat(format);
    }

    /**
     * 根据垂直维度和水平某维度查找MDB路由信息，并根据配置是否补全辅助维度（region_code,operator_id）
     * 
     * @param verticalValue:垂直维度的值，mdb类型：uri中kv值的第一部分:abm,user_info,rasmdb
     * @param dim：水平维度值
     * @return 该维度对应的路由信息以及维度对应的主维度AccountID和在需要补全时的辅助维度（region_code,operator_id）信息，如果路由过程经过Resource_id
     *         Acct_id的映射则还返回版本校验需要的Resource_id和effective_date
     */
    public static RouteResult searchRoutingInfo(RouterClient routerClient, String verticalValue, RouteDimension dim)
            throws RouteNotFoundException
    {
        RouteResult res = routerClient.searchMdbRoutingInfo(verticalValue, dim);
        return res;
    }

    /**
     * 根据帐户号、用户号等信息组织路由Key
     * 
     * @param dim
     * @return
     */
    public static String getKeyByDim(RouteDimension dim)
    {
        StringBuilder sb = new StringBuilder(100);
        if (null != dim)
        {
            sb.append("_").append(dim.getAccountId());
            sb.append("_").append(dim.getResourceId());
            sb.append("_").append(dim.getRegionCode());
            sb.append("_").append(dim.getCustomerId());
            sb.append("_").append(dim.getIdentityType());
            sb.append("_").append(dim.getIdentity());
        }
        return sb.toString();
    }

    /**
     * 根据垂直维度和水平某维度以及设定的查询模式查找路由信息,目前该接口只用于MDB路由查询
     * 
     * @param verticalValue:垂直维度的值，mdb类型：uri中kv值的第一部分:abm,user_info,rasmdb
     * @param routeQryParam：查询参数包括水平维度信息和查询方式（可定义路由是否返回额外信息如补全三户维度、分账关系、群组关系等)
     * @param qryBeginTime 查询开始时间,从1970开始的秒数,闭区间,当qryBeginTime==qryEndTime时则表示时间点查询,0表示当前时间
     * @param qryEndTime 查询结束时间,从1970开始的秒数,开区间，-1表示无穷大
     * @return 按查询方式定义的路由信息
     */
    public static RouteRet searchRoutingInfo(RouterClient routerClient, String verticalValue, RouteQryParam routeQryParam,
            int qryBeginTime, int qryEndTime) throws RouteNotFoundException
    {
        int a = 1;
        if (a == 0)
        {
            RouteResult res = genRouteResult(verticalValue, routeQryParam.getRouteDim());

            List<RouteResult> routeResults = new ArrayList<RouteResult>();
            routeResults.add(res);
            RouteRet routeRet = new RouteRet();
            routeRet.setRouteResults(routeResults);
            return routeRet;
        }
        return routerClient.searchMdbRoutingInfo(verticalValue, routeQryParam, qryBeginTime, qryEndTime);
    }

    /**
     * 本地测试使用,生成路由
     * 
     * @param verticalValue
     * @param dim
     * @return
     */
    private static RouteResult genRouteResult(String verticalValue, RouteDimension dim)
    {
        RouteDimension rtDimension = new RouteDimension();
        BeanUtils.copyProperties(dim, rtDimension);
        rtDimension.setRegionCode(571);
        if ((rtDimension.getResourceId() != null && rtDimension.getResourceId() > 0)
                && (rtDimension.getAccountId() == null || rtDimension.getAccountId() <= 0))
        {
            rtDimension.setAccountId(rtDimension.getResourceId() - 10000000000L);
            rtDimension.setAccountId(10000000002L);
        }
        int shardingId = 111;
        // String shardingAlias = String.valueOf(rtDimension.getAccountId()%8);
        String shardingAlias = "";
        if (verticalValue.indexOf("rasmdb") != -1)
        {
            shardingAlias = "aps_mdb_01";
        }
        else
        {
            String subFix = String.valueOf(rtDimension.getAccountId() % 10);
            shardingAlias = "abm_mdb_0" + subFix;
        }
        Sharding sharding = new Sharding(shardingId, shardingAlias);
        int version = 3333;
        RouteResult res = null;
        return res;
    }

    /**
     * 根据垂直维度和水平某维度以及设定的查询模式查找路由信息,目前该接口只用于MDB路由查询
     * 
     * @param verticalValue:垂直维度的值，mdb类型：uri中kv值的第一部分:abm,user_info,rasmdb
     * @param routeQryParam：查询参数包括水平维度信息和查询方式（可定义路由是否返回额外信息如补全三户维度、分账关系、群组关系等)
     * @param qryBeginTime 查询开始时间,从1970开始的秒数,闭区间,当qryBeginTime==qryEndTime时则表示时间点查询,0表示当前时间
     * @param qryEndTime 查询结束时间,从1970开始的秒数,开区间，-1表示无穷大
     * @return 按查询方式定义的路由信息
     */
    public static boolean isSameSharding(RouterClient routerClient, RouteResult routeResult1, RouteResult routeResult2)
    {
        List<RouteResult> routeInfos = new ArrayList<RouteResult>();
        routeInfos.add(routeResult1);
        routeInfos.add(routeResult2);
        return routerClient.isSameSharding(routeInfos);
    }
}
