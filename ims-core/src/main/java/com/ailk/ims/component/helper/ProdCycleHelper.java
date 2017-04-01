package com.ailk.ims.component.helper;

import java.util.Calendar;
import java.util.Date;

import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;

/**
 * @Description:产品账期相关的静态方法
 * @author wangjt
 * @Date 2011-12-23
 * @Date 2012-04-19 Lijc3 去掉2个方法
 * @Date 2012-09-24 wangdw5 :自然周 (表示从礼拜1零点到下N个礼拜1零点，exp:Monday 00：00：00 - next Monday 00：00：00)
 */
public class ProdCycleHelper
{
    private ProdCycleHelper()
    {
    }

    /**
     * 产品生效时间必须大于销售品配置的一个周期 ljc 2011-11-7
     */
    public static void validtimeMustBiggerThanAnOfferCycleUnit(Date validDate, Date expireDate, Integer cycleType, Integer unit)
    {
        int day = 0;
        switch (cycleType.intValue())
        {
        case EnumCodeDefine.PROD_CYCLETYPE_DAY:
        case EnumCodeDefine.PROD_CYCLETYPE_RUN_DAY:
            day = unit;
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_WEEK:
            day = unit * 7;
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_MONTH:
        case EnumCodeDefine.PROD_CYCLETYPE_MONTH_OFFSET:
        case EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH:
        	day=unit*28;
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_YEAR:
            day = unit * 365;
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_HOUR:
            day = unit / 24;
            break;
        default:
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "bill_cycle_type");
        }
        long validtime = expireDate.getTime() - DateUtil.dayBegin(validDate).getTime();
        if (validtime > 0)
        {
            if ((day * 24 * 60 * 60 * 1000 - validtime) > 0)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.VALIDTIME_LOWER_THAN_OFFERING_CYCLE);
            }
        }
    }

    /**
     * 根据开始时间和指定的账单日，推算正确账单日。<br>
     * 比如当前时间为2011-7-10,dueDay为20,则正确账单日为2011 -7-20,在同一个月; <br>
     * 如果dueDay为05，则正确账单日为2011-8-05,在下一个月
     */
    public static Date createFirstBillDate(Date startDate, Integer dueDay, int cycleType,int cycleUnit)
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
        {// 自然周 @Date 2012-09-24 wangdw5 :自然周 (表示从礼拜1零点到下N个礼拜1零点，exp:Monday 00：00：00 - next Monday 00：00：00)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
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
//            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
//            if (dueDay == null)
//            {
//                return DateUtil.formatHms2Zero(startDate);
//            }
//            if (dueDay > currentDay)
//            {
//                // 在同一个月
//                calendar.set(Calendar.DAY_OF_MONTH, dueDay);
//            }
//            else
//            {
//            dueDay=dueDay==null?currentDay:dueDay;
        	//修改动态月出账时间
            calendar.roll(Calendar.MONTH, cycleUnit);
//            calendar.set(Calendar.DAY_OF_MONTH, dueDay);
//            }
            if(cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH){
            	return DateUtil.dayBegin(calendar.getTime());
            }else{
            	return calendar.getTime();
            }
        }
        return IMSUtil.offsetDate(startDate, cycleType, cycleUnit);
    }

    /**
     * 
     * @Description 按照账户账期计算产品出账日
     * @Author lijingcheng
     * @param startDate
     * @param dueDay
     * @param cycleUnit
     * @param cycleType
     * @return
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
        		 throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID,"account''s billingCycle first_bill_date","1-7");
        	 }
        	 calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        	 if(dayOfWeek<=currentWeekDay){
        		 calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + cycleUnit);
        	 }
        }else if(cycleType==EnumCodeDefine.PROD_CYCLETYPE_MONTH){
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
     * @Description: 推算订购生效时间
     * @param delay_unit ,延迟数量
     * @param delay_cycle ，延迟数量的单位
     * @param cycle_unit ,账期数量，延迟数量的单位为bill时才用到
     * @param cycle_type ，账期单位,延迟数量的单位为bill时才用到
     */
    public static Date createOrderValidDate(int delay_unit, int delay_cycle, int cycle_unit, int cycle_type, Date requestDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(requestDate);
        int offsetType = -1;
        int offset = delay_unit;

        if (delay_cycle == EnumCodeDefine.PROD_DELAY_CYCLE_DAY)
        {
            offsetType = Calendar.DAY_OF_MONTH;
        }
        else if (delay_cycle == EnumCodeDefine.PROD_DELAY_CYCLE_HOUR)
        {
            offsetType = Calendar.HOUR;
        }
        else if (delay_cycle == EnumCodeDefine.PROD_DELAY_CYCLE_MONTH)
        {
            offsetType = Calendar.MONTH;
        }
        else if (delay_cycle == EnumCodeDefine.PROD_DELAY_CYCLE_BILL)
        {
        	if(offset!=0){
        		cycle_unit=cycle_unit*offset;
        	}
            return IMSUtil.offsetDate(requestDate, cycle_type, cycle_unit);
        }
        if (offsetType != -1)
            calendar.add(offsetType, offset);
        return calendar.getTime();
    }


    //@Date [47002]帐户账期枚举值已与产品账期一致，不需要再转换了 yanchuan 2012-05-31
    /**
     * 根据传入的账户账期周期枚举值转换成产品账期周期枚举值
     */
/*    public static Integer changeAcctBillCycleEnum2Prod(Integer cycleType)
    {
        if (cycleType == EnumCodeDefine.ACCT_BILLCYCLETYPE_DAY)
        {
            return EnumCodeDefine.PROD_CYCLETYPE_DAY;
        }
        else if (cycleType == EnumCodeDefine.ACCT_BILLCYCLETYPE_WEEK)
        {
            return EnumCodeDefine.PROD_CYCLETYPE_WEEK;
        }
        else if (cycleType == EnumCodeDefine.ACCT_BILLCYCLETYPE_MONTH)
        {
            return EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH;
        }
        else if (cycleType == EnumCodeDefine.ACCT_BILLCYCLETYPE_YEAR)
        {
            return EnumCodeDefine.PROD_CYCLETYPE_YEAR;
        }
        else if (cycleType == EnumCodeDefine.ACCT_BILLCYCLETYPE_UNDEFINE)
        {
            return EnumCodeDefine.ACCT_BILLCYCLETYPE_UNDEFINE;
        }
        throw IMSUtil.throwBusiException(ErrorCodeDefine.CYCLE_TYPE_WRONG, cycleType);
    }*/
    
    //@Date 2012-04-19 Lijc3 去掉2个方法
//    public static CoProdBillingCycle getBeforeCycle(CoProdBillingCycle before, CoProdBillingCycle after)
//    {
//        return (before.getValidDate().before(after.getValidDate()) ? before : after);
//    }
//    
//    public static BillCycleComplex getBeforeCycle(BillCycleComplex before, BillCycleComplex after)
//    {
//        return (before.getValidDate().before(after.getValidDate()) ? before : after);
//    }
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
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "validCycle");
        }
        return temp;
    }
}
