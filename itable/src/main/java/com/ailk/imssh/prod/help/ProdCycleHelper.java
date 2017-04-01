package com.ailk.imssh.prod.help;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.jetty.util.log.Log;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.common.define.ErrorCodeExDefine;

/**
 * @Description:产品账期相关的静态方法
 * @author lijc3
 * @Date 2011-12-23
 * @Date 2012-04-19 lijc3 去掉2个方法
 */
public class ProdCycleHelper
{
    private ProdCycleHelper()
    {
    }

    /**
     * 根据开始时间和指定的账单日，推算正确账单日。<br>
     * 比如当前时间为2011-7-10,dueDay为20,则正确账单日为2011 -7-20,在同一个月; <br>
     * 如果dueDay为05，则正确账单日为2011-8-05,在下一个月
     */
    public static Date createFirstBillDate(Date startDate, Integer dueDay, int cycleType, int cycleUnit)
    {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_DAY)
        {// 自然天 下一天00:00
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + cycleUnit);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_MONTH)
        {// 自然月,下个月1号00:00
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + cycleUnit);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_WEEK)
        {// 自然周
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + cycleUnit);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_YEAR)
        {// 自然年
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + cycleUnit);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH || cycleType == EnumCodeDefine.PROD_CYCLETYPE_MONTH_OFFSET)
        {// 动态月
         // int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
         // if (dueDay == null)
         // {
         // return DateUtil.dayBegin(startDate);
         // }
         // if (dueDay > currentDay)
         // {
         // // 在同一个月
         // calendar.set(Calendar.DAY_OF_MONTH, dueDay);
         // }
         // else
         // {
         // dueDay=dueDay==null?currentDay:dueDay;
         // 修改动态月出账时间
            calendar.roll(Calendar.MONTH, cycleUnit);
            // calendar.set(Calendar.DAY_OF_MONTH, dueDay);
            // }
            if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH)
            {
                return DateUtil.dayBegin(calendar.getTime());
            }
            else
            {
                return calendar.getTime();
            }
        }
        return IMSUtil.offsetDate(startDate, cycleType, cycleUnit);
    }

    /**
     * @Description 按照账户账期计算产品出账日
     * @Author lijc3
     */
    public static Date createFirstBillDateByAcct(Date startDate, Integer dueDay,Integer cycleUnit,Integer cycleType)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentWeekDay=calendar.get(Calendar.DAY_OF_WEEK);
        //2012-06-05 yangjh story：47002 账户账期类型与产品账期类型枚举值一致
        if(cycleType==EnumCodeDefine.PROD_CYCLETYPE_DAY){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + cycleUnit);
        }else if(cycleType==EnumCodeDefine.PROD_CYCLETYPE_WEEK){
            Integer dayOfWeek=0;
             if(dueDay==7){
                 dayOfWeek=Calendar.SUNDAY;
             }else if (dueDay>0&&dueDay<7){
                 dayOfWeek=dueDay+1;
             }else{
                 throw IMSUtil.throwBusiException(ErrorCodeExDefine.COMMON_PARAM_INVALID,"account''s billingCycle first_bill_date","1-7");
             }
             calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
             if(dayOfWeek<=currentWeekDay){
                 calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + cycleUnit);
             }
        }else if(cycleType==EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH){
            if (dueDay > currentDay)
              {
                  // 在同一个月
                  calendar.set(Calendar.DAY_OF_MONTH, dueDay);
              }
              else
              {
                  calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + cycleUnit);
                  calendar.set(Calendar.DAY_OF_MONTH, dueDay);
              }
        }else if(cycleType==EnumCodeDefine.PROD_CYCLETYPE_YEAR){
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + cycleUnit);
        }
//        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
//        if (dueDay == null)
//        {
//            return startDate;
//        }
//        if (dueDay >= currentDay)
//        {
//            // 在同一个月
//            calendar.set(Calendar.DAY_OF_MONTH, dueDay);
//        }
//        else
//        {
//            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
//            calendar.set(Calendar.DAY_OF_MONTH, dueDay);
//        }
        return DateUtil.dayBegin(calendar.getTime());
    }

    /**
     * 转换产品生效周期变计费周期
     */
    public static Integer transferValidCylceType2BillCylceType(int validCycle)
    {
        int temp = 0;
        switch (validCycle)
        {
        case EnumCodeDefine.PROD_VALID_CYCLETYPE_DAY:
            temp = EnumCodeDefine.PROD_CYCLETYPE_DAY;
            break;
        case EnumCodeDefine.PROD_VALID_CYCLETYPE_WEEK:
            temp = EnumCodeDefine.PROD_CYCLETYPE_WEEK;
            break;
        case EnumCodeDefine.PROD_VALID_CYCLETYPE_MONTH:
            temp = EnumCodeDefine.PROD_CYCLETYPE_MONTH;
            break;
        case EnumCodeDefine.PROD_VALID_CYCLETYPE_YEAR:
            temp = EnumCodeDefine.PROD_CYCLETYPE_YEAR;
            break;
        case EnumCodeDefine.PROD_VALID_CYCLETYPE_HOUR:
            temp = EnumCodeDefine.PROD_CYCLETYPE_HOUR;
            break;
        case EnumCodeDefine.PROD_VALID_CYCLETYPE_RUN_DAY:
            temp = EnumCodeDefine.PROD_CYCLETYPE_RUN_DAY;
            break;
        case EnumCodeDefine.PROD_VALID_CYCLETYPE_RUN_WEEK:
            temp = EnumCodeDefine.PROD_CYCLETYPE_RUN_WEEK;
            break;
        case EnumCodeDefine.PROD_VALID_CYCLETYPE_RUN_MONTH:
            temp = EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH;
            break;
        case EnumCodeDefine.PROD_VALID_CYCLETYPE_RUN_YEAR:
            temp = EnumCodeDefine.PROD_CYCLETYPE_RUN_YEAR;
            break;

        default:
            throw IMSUtil.throwBusiException(ErrorCodeExDefine.PARAM_INVALID, validCycle);
        }
        return temp;
    }

    /**
     * 计算偏移日期 luojb 2011-12-23
     */
    public static Date calculateMoveDate(Date offDate, Integer offType, Integer offUnit, Integer notifyMode, Integer notifyType)
    {
        if (offDate == null)
            return null;
        if (notifyMode != null && notifyMode.intValue() == EnumCodeDefine.PROD_NOTIFY_MODE_IMMEDIATELY && notifyType != null)
        {
            if (notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_VALID
                    || notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_EXPIRE
                    || notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_TRIAL)
                return offDate;
        }

        int count = offUnit;
        Date date = null;
        if (notifyMode != null && notifyMode.intValue() == EnumCodeDefine.PROD_NOTIFY_MODE_BEFORE)
            count = count * -1;
        if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_DAY)
        {
            date = DateUtil.offsetDate(offDate, Calendar.DATE, count);
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_WEEK)
        {
            date = DateUtil.offsetDate(offDate, Calendar.WEEK_OF_MONTH, count);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            date = cal.getTime();
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_MONTH)
        {
            date = DateUtil.offsetDate(offDate, Calendar.MONTH, count);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            date = cal.getTime();
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_YEAR)
        {
            date = DateUtil.offsetDate(offDate, Calendar.YEAR, count);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_YEAR, 1);
            date = cal.getTime();
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_MONTH)
        {
            date = DateUtil.offsetDate(offDate, Calendar.MONTH, count);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_HOUR)
        {
            date = DateUtil.offsetDate(offDate, Calendar.HOUR, count);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_OFF_MONTH)
        {
            date = DateUtil.offsetDate(offDate, Calendar.MONTH, count);
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_DAY)
        {
            date = DateUtil.offsetDate(offDate, Calendar.DATE, count);
        }
        else
        {
        	new ImsLogger(ProdCycleHelper.class).info(
                    "**********the config of offsetType/cycleType is wrong.offsetType/cycleType=[" + offType + "] notifyType=["
                            + notifyType + "]");
            return null;
        }

        Integer maxDay = null;
        if (notifyType != null)
        {
            if (notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_VALID)
                maxDay = SysUtil.getInt(SysCodeDefine.busi.PROD_VALID_NOTIFY_MAX_DAY);
            else if (notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_EXPIRE)
                maxDay = SysUtil.getInt(SysCodeDefine.busi.PROD_EXPIRE_NOTIFY_MAX_DAY);
        }
        if (maxDay != null)
        {
            long tempDay = DateUtil.getBetweenDays(offDate, date);
            if (maxDay.longValue() < tempDay)
            {
                Log.getLog().info(
                        "**********configed notify offset day[" + tempDay + "] is larger than max notify offset day[" + maxDay
                                + "]");
                return null;
            }
        }

        return date;
    }

    /**
     * 计算偏移日期 luojb 2011-12-23
     * 
     * @param offDate
     * @param cycleType
     * @param cycleUnit
     * @return
     */
    public static Date calculateMoveDate(Date offDate, Integer cycleType, Integer cycleUnit)
    {
        return calculateMoveDate(offDate, cycleType, cycleUnit, null, null);
    }
}
