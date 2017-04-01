package com.ailk.ims.smsts.helper;

import java.util.Calendar;
import java.util.Date;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;

public class FlowInstanceHelper
{

    private static final ImsLogger imsLogger = new ImsLogger(FlowInstanceHelper.class); // 子类可以直接使用

    /**
     * 判断在指定时间范围内消费为零 注意: 起始时间必须小于结束时间,即 beginDate<endDate
     */
    public static boolean isCostZreoInRangeDate(Long userId, Long acctId, Date beginDate, Date endDate)
    {
        /*
        if (billExtOut != null && 0 != billExtOut.getBillFee())
        {

            imsLogger.info("用户[", userId, "] 在前两个账期消费记录 : " + billExtOut.getBillFee());
            return false;

        }else if(isFullOfReduced(billExtOut)){
            imsLogger.info("用户[",userId,"]是全额优惠，不做日保号停");
            return false;
        }else*/
        if(isSplitRel(userId, acctId)){
            imsLogger.info("用户[",userId,"]已被代付,不做日报号停");
            return false;
        }
        imsLogger.info("用户[", userId, "] 在前两个账期消费记录为零");
        return true;
    }
    /**
     * 是否是有人代付
     * @return
     */
    private static boolean isSplitRel(Long userId,Long acctId){
        IMSUtil.setAcctRouterParam(acctId);
      return  DBUtil.queryCount(CoSplitPayRel.class, 
//                new DBCondition[]{
            new DBCondition(CoSplitPayRel.Field.objectId, userId)
        ,new DBCondition(CoSplitPayRel.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV)
//        }
        )>0;
    }
    
 

    /**
     * 是否不是日保号停
     */
    public static boolean isNotDayKeepNumUser(Long resourceId)
    {
        CmResLifecycle lifeCycle = DBUtil.querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId,
                resourceId), new DBCondition(CmResLifecycle.Field.recSts, new Integer[] { EnumCodeDefine.IS_VALID_DATA
        // ,EnumCodeDefine.IN_DEALING
                }, Operator.IN), new DBCondition(CmResLifecycle.Field.validDate, new Date(), Operator.LESS), new DBCondition(
                CmResLifecycle.Field.expireDate, new Date(), Operator.GREAT_EQUALS));
        if (null != lifeCycle)
        {
            //2012-12-28 gaoqc5 日报号停 位数判断修正,应该是11位
            boolean notDayKeep = !CommonUtilSh.check(lifeCycle, EnumCodeShDefine.STS_DTL_ELEVEN - 1);//.isDayKeepBit(getBossOsStsDtlFromDB(lifeCycle.getOsStsDtl()));
            if (notDayKeep)
            {
                imsLogger.info("用户[", resourceId, "]不是日保号停!");
            }
            return notDayKeep;
        }
        return false;
    }
    public static Calendar getFormatHms2ZeroCalendar(int month, int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.dayBegin(new Date()));
        if (0 != month)
        {
            calendar.set(Calendar.MONTH, month);
        }
        if (0 != day)
        {
            calendar.set(Calendar.DAY_OF_MONTH, day);
        }
        return calendar;
    }

    /**
     * 在指定时间之前激活没激活过
     */
    public static boolean isNotActiveRecInLatelyDate(Long resourceId, Long acctId,int billDay, boolean todayBeforeBillDay)
    {
        Long STOPPHONESPECID = 202011401L;// 日保号停IAD.BI_BUSI_SPEC_DEF
        
        // 如果当前日期大于账期，取本月fisrtBillDate + 2个月；如果当前日期小于账期，取上月fisrtBillDate + 2个月
        int month = DateUtil.getDateField(new Date(), Calendar.MONTH);
//        if (!todayBeforeBillDay)
//        {
//            month -= 1;
//        }

        // 对时间轮询
        Calendar calendar = getFormatHms2ZeroCalendar(0, billDay);
        for (int i = 0; i < 2; i++)
        {

            calendar.set(Calendar.MONTH, month);
            Date date = calendar.getTime();
            month -= 1;

        }
        imsLogger.info("用户[", resourceId, "] 在前两个月至今 没有   激活记录");
        return true;
    }


    /**
     * 根据开始时间和结束时间返回查询账单类型:
     *  如果开始时间和结束时间都是当月,返回查询实时账单类型
     *  如果开始时间小于当月,结束时间是当前,返回查询历史账单和实时账单类型
     *  如果开始时间和结束时间都小于当月,返回查询历史账单类型
     * @param beginDate
     * @param endDate
     */
    private static Short getRealType(Date beginDate,Date endDate){
                long begin=  beginDate.getTime();
                long current=getCurrentTime();
                if(begin>=current){//开始时间大于当前1号,说明是查实时
                    return EnumCodeDefine.BILL_EXT_IN_REAL_BILL;
                }
                long end=endDate.getTime();
                 if(end<current){//如果是结束时间都小于当前1号,说明是查历史
                     return EnumCodeDefine.BILL_EXT_IN_HISTORY_BILL;
                     
                 }
                 return EnumCodeDefine.BILL_EXT_IN_HISTORY_AND_REAL_BILL;
                
                
    }
    private static long getCurrentTime(){
       Date date=DateUtil.dayBegin(DateUtil.getFirstDateAtMonth(new Date()));
        return date.getTime();
    }
    
//    public static void main(String[] args)
//    {
//    	Date date = new Date();
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.MONTH, -1);
//		date = cal.getTime();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//		System.out.println(formatter.format(date));
//    }
    
    
    /*
     * 调用abm的rdl接口查历史免费资源
     * 
     */
    /*
    public static CaFreeres queryFreeResList(Long userId, Long acctId,  String itemCodes,Date beginDate,Date endDate){
        SAbmBalanceQueryRet sAbmBalanceQueryRet =query(24,userId, acctId,0, beginDate, endDate);
            long amout=0;
            long used=0;
            CsdlArrayList<com.ailk.easyframe.sdl.MAbmRdlCommonDef.SFreeRes> sFreeRes=sAbmBalanceQueryRet.get_freeresList(); 
            Date mdbExpireDate = null;
            for(com.ailk.easyframe.sdl.MAbmRdlCommonDef.SFreeRes res:sFreeRes)
            {
            	mdbExpireDate =DateUtil.getFormatDate(String.valueOf(res.get_expireDate()));
            	if(mdbExpireDate.equals(endDate)||mdbExpireDate.before(endDate)&&mdbExpireDate.after(beginDate))
            	{
            		if (itemCodes.contains(String.valueOf( res.get_itemCode())))
    	            {
    	                amout += res.get_amount();
    	                used += res.get_usedValue();
    	            }
            	}
            }
            imsLogger.info("1.周期性免费资源total："+amout+", used：" +used);
            
            CsdlArrayList<com.ailk.easyframe.sdl.MAbmRdlCommonDef.SOtFreeRes>sOtFreeRes =sAbmBalanceQueryRet.get_otFreeresList();
            for(com.ailk.easyframe.sdl.MAbmRdlCommonDef.SOtFreeRes otRes:sOtFreeRes)
            {
            	mdbExpireDate =DateUtil.getFormatDate(String.valueOf(otRes.get_expireDate()));
            	if(mdbExpireDate.equals(endDate)||mdbExpireDate.before(endDate)&&mdbExpireDate.after(beginDate))
            	{
            		if (itemCodes.contains(String.valueOf( otRes.get_itemCode())))
    	            {
    	                amout += otRes.get_amount();
    	                used += otRes.get_usedValue();
    	            }
            	}
            }
            imsLogger.info("2.周期性免费资源和一次性免费资源total："+amout+", used：" +used);
          CaFreeres freeres=new CaFreeres();
          freeres.setResourceId(userId);
          freeres.setAcctId(acctId);
          freeres.setUnit(amout);
          freeres.setRealUnit(used);
          return freeres;          
    }
*/
    
    /**
     * 通过直接查询表获取累积量
     * @param userId
     * @param items
     * @param beginDate
     * @param endDate
     * @return
     */
//   public static long queryAccumulateFromTable(Long userId,String items,Date beginDate,Date endDate){
//        
//         long result=0;
//        try
//        {
//            String sql=buildQueryDilyBillTableSql(userId, items,beginDate,endDate);
//            List<CaFreeres> list = DBUtil.getDBClient().createNativeQuery(sql,CaFreeres.class).getResultList();
//            if (CommonUtil.isNotEmpty(list))
//            {
//                if (null != list.get(0) && null != list.get(0).getUnit())
//                {
//                    result = list.get(0).getUnit(); //累积量保存在unit字段中
//                }
//            }
//        }
//        catch (Exception e)
//        {
//           imsLogger.error(e,e);
//        }
//        return  result;
//   }
//
//   private static String buildQueryDilyBillTableSql(Long userId,String items,Date beginDate,Date endDate){
//       String tableSuffix=DateUtil.getFormatDate(  DateUtil.getCutoffNow(Calendar.DAY_OF_MONTH, -1), DateUtil.DATE_FORMAT_YYYYMMDD);
//       return new StringBuffer()
//       .append(" select ")
//       .append(" sum(t.accumulate_value) as unit  ")
//       .append("  from ad.ca_daily_bill_"+tableSuffix+" b, ad.ca_usage_bill_dtl_"+tableSuffix+" t ")
//       .append(" where   b.object_id="+userId+" and b.bill_no = t.bill_no  " )
//       .append(" and b.begin_date>="+ComplexSqlBuilder.getSqlDateTime(beginDate))
//       .append( " and b.end_date<="+ComplexSqlBuilder.getSqlDateTime(endDate))
//       .append("  and  b.object_type = 0   and t.item_code in ( "+items+")")
//       .toString();
//   }

/*
    private static SAbmBalanceQueryRet query(int tableFlag,Long userId, Long acctId,int itemCode, Date beginDate,Date endDate){
    	SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
        imsLogger.info("table_flag:",tableFlag,",userId:",userId,",acctId:",acctId,",itemCode:",itemCode,",beginDate:",Long.valueOf(formatter.format(beginDate.getTime())),",endDate:",Long.valueOf(formatter.format(endDate.getTime())));
        SAbmBalanceQueryUp sAbmBalanceQueryUp=new SAbmBalanceQueryUp();
        sAbmBalanceQueryUp.set_tableFlag(tableFlag);
        sAbmBalanceQueryUp.set_acctId(acctId);
        sAbmBalanceQueryUp.set_servId(userId);
        if(itemCode>0){
          sAbmBalanceQueryUp.set_itemCode(itemCode);
        }
        sAbmBalanceQueryUp.set_cycleBegin(Long.valueOf(formatter.format(beginDate.getTime())));
        sAbmBalanceQueryUp.set_cycleEnd(Long.valueOf(formatter.format(endDate.getTime())));
//        sAbmBalanceQueryUp.set_cycleBegin(beginDate.getTime());
//        sAbmBalanceQueryUp.set_cycleEnd(endDate.getTime());
         SAbmBalanceQueryRet ret=null;
        try{
//            imsLogger.dumpJsonObject("##############queryFreeResList  ###################", sAbmBalanceQueryUp);
         ret= SpringUtil.getSalClient().get(MdbKVDefine.QUERY_ABM_TABLES,sAbmBalanceQueryUp , 
                 new SAbmBalanceQueryRet());
//         imsLogger.dumpJsonObject("##############queryFreeResList  ###################", ret);         
        }catch (OBBufferErrorException e)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_DATA_FROM_MDB_ERROR);
        }
        catch (SALException e)
        {
            imsLogger.error(e,e);
        }
        if(null==ret){
            imsLogger.error("查询历史免费资源失败!");
            return null;
        }
        return ret;
    }
*/
    /**
     * 是否是免停
     * gaoqc 2012-11-17
     * @param acctId
     * @return
     */
    public static boolean isExemption(Long acctId)
    {
          IMSUtil.setAcctRouterParam(acctId);
        DBCondition[] conds = new DBCondition[5];
        conds[0] = new DBCondition(CaNotifyExempt.Field.objectId, acctId);
        conds[1] = new DBCondition(CaNotifyExempt.Field.objectType, EnumCodeShDefine.PROD_OBJECTTYPE_ACCOUNT);// 账户的免催免停
        conds[2] = new DBCondition(CaNotifyExempt.Field.validDate, new Date(), Operator.LESS_EQUALS);
        conds[3] = new DBCondition(CaNotifyExempt.Field.expireDate, new Date(), Operator.GREAT);
        conds[4] = new DBCondition(CaNotifyExempt.Field.exemptionType, new Integer[] { EnumCodeShDefine.EXEMPTION_NO_STOP,// 信用度免停
                EnumCodeShDefine.EXEMPTION_NO_CALL_AND_NO_STOP,// 信用度免催免停 103
                EnumCodeShDefine.EXEMPTION_HOLIDAYS_NO_STOP, // 节假日免停
                EnumCodeShDefine.EXEMPTION_HOLIDAYS_NO_CALL_AND_NO_STOP, //  节假日免催免停
                }, Operator.IN);

        return DBUtil.queryCount(CaNotifyExempt.class, conds) > 0;

    }
    /**
     *是否是随心聊,群类型是212
     * gao 2012-11-19
     * @param userId
     * @return
     */
    public static boolean isSXL(Long userId){
        boolean sxl=false;
        Date date=new Date();
        CaResGrpRevs revs=DBUtil.querySingle(CaResGrpRevs.class,
                new DBCondition(CaResGrpRevs.Field.resourceId , userId)
        ,new DBCondition(CaResGrpRevs.Field.validDate,date,Operator.LESS)
        ,new DBCondition(CaResGrpRevs.Field.expireDate, date,Operator.GREAT)

        );
        if(null!=revs){
            sxl=   DBUtil.queryCount(CaAccountGroup.class,new DBCondition(CaAccountGroup.Field.acctId   ,revs.getAcctId())
            ,new DBCondition(CaAccountGroup.Field.validDate,date,Operator.LESS)
            ,new DBCondition(CaAccountGroup.Field.expireDate, date,Operator.GREAT)
            ,new DBCondition(CaAccountGroup.Field.accountType, 212))>0;//212，随心聊类型
        }
     
        return sxl;
        
     
    }
    
    
    
    
}
