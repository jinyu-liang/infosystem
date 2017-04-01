package com.ailk.ims.smsts.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresMonth;

public class FreeresDayNotifyHelper
{
    
    public static int getDay(CaFreeresDayNotify dayNotify){
        return Integer.valueOf(subString(String.valueOf(dayNotify.getExportDate()),6,2));
    }
    public static int getHour(CaFreeresDayNotify dayNotify){
        return DateUtil.getDateField(dayNotify.getUpdateTime(),Calendar.HOUR_OF_DAY);
    }
    public static int getMinute(CaFreeresDayNotify dayNotify){
        return DateUtil.getDateField(dayNotify.getUpdateTime(),Calendar.MINUTE);
    }
    public static int getYear(CaFreeresDayNotify dayNotify){
        return DateUtil.getDateField(dayNotify.getUpdateTime(), Calendar.YEAR);
    }
    public static int getMonth(CaFreeresDayNotify dayNotify){
        return Integer.valueOf(subString(String.valueOf(dayNotify.getExportDate()),4,2));
    }
    public static String getFreeresTableName(CaFreeresDayNotify dayNotify){
        return getFreeresTableName(String.valueOf(dayNotify.getExportDate()),CommonUtil.long2Int(dayNotify.getSubTableIndex()));
    }
    public static String getFreeresTableName(String yyyyMMdd,int subIndex){
         return CommonUtil.concat(DBUtil.buildTableName(CaFreeres.class),"_",String.valueOf(subIndex),"_",yyyyMMdd);
    }
    
    //获取上个月 yyyyMM
    public static String getLastMonth(CaFreeresDayNotify dayNotify){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MONTH, -1);
    	Date date = cal.getTime();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
    	return formatter.format(date);
    }
    
    //免费资源的月导出表
    public static String getLastMonthFreeresTableName(CaFreeresDayNotify dayNotify){
    	String yyyyMM = getLastMonth(dayNotify);
    	String subIndex = String.valueOf(CommonUtil.long2Int(dayNotify.getSubTableIndex()));
    	return CommonUtil.concat(DBUtil.buildTableName(CaFreeresMonth.class),"_", subIndex, "_", yyyyMM);
    }
    
    //获取当月免费资源的使用情况
    public static CaFreeres getCurrentMonthUsedFreeres( List<CaFreeres>list, List<CaFreeresMonth>monthList, String items)
    {
    	CaFreeres freeres = new CaFreeres();
        long total = 0;
        long used = 0;
        
        for (CaFreeres res : list)
        {
            if (items.contains(String.valueOf(res.getItemCode())))
            {
                total += res.getUnit();
                used += res.getRealUnit();
            }
            if(res.getVaildDate().before(getFirstDayOfMonth()))
            {
            	for (CaFreeresMonth resMonth : monthList)
            	{
            		if(res.getVaildDate().equals(resMonth.getVaildDate())
            				&&(res.getExpireDate().equals(resMonth.getExpireDate()))
            				&&(res.getItemCode()==resMonth.getItemCode())
            				&&res.getProductId()==0)
            		{
            			total -=resMonth.getRealUnit();//减去上个月已使用的
            			used -=resMonth.getRealUnit();
            		}
            	}
            }
        }
        freeres.setUnit(total);
        freeres.setRealUnit(used);
        return freeres;
    }
    
    
//    public static void main(String[] args){
//    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
//    	yyyyMM =formatter.format(date);
//    	
//    }
    
    
    /**
     * 取当天免费资源表
     * @param index
     * @return
     */
    public static CaFreeresDayNotify getDefaultFreeresDayNotify(Long index){
        CaFreeresDayNotify notify=new CaFreeresDayNotify();
        notify.setSubTableIndex(index);
        notify.setUpdateTime(new Date());
        notify.setExportDate(Long.valueOf(DateUtil.currentString(DateUtil.DATE_FORMAT_YYYYMMDD)));
        return notify;
    }
    private static String subString(String str,int begin,int lenght){
        int end=begin+lenght;
        if(str.length()<end){
            return "";
        }
        return str.substring(begin,end);
    }
    
    /**
     * 获取本月1号0时0分0秒 
     * 
     */
    private static Date getFirstDayOfMonth()
    {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.DAY_OF_MONTH, 1);
    	Date date = cal.getTime();
    	return DateUtil.dayBegin(date);
    }
}
