package com.ailk.ims.smsts.helper;

import java.util.Date;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;

public class ComplexSqlBuilder
{
     private static final ImsLogger imsLogger=new ImsLogger(ComplexSqlBuilder.class);
//    public static  String getUserQueryConditionSql()
//    {
//        return new StringBuffer().append("  and u.ACTIVE_DATE<" + getSqlDateTime(getCurrentDate()))
//                .append(" and u.BILLING_TYPE=1").append(" and VALID_DATE<=" + getSqlDateTime(getCurrentDate()))
//                .append(" and EXPIRE_DATE>=" + getSqlDateTime(getCurrentDate())).append(" and u.RESOURCE_ID=t.RESOURCE_ID")
//                .toString();
//    }
    public static String getSqlDateTime(Date date)
    {
        return CommonUtil.concat(" to_date('" , 
                DateUtil.formatDate(date, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS) , 
                "','YYYY-MM-DD HH24:MI:SS')");

    }

    
    /**
     * 由于免资源表和用户表都是按账户分表，所以他们的分表是相同的
     * 
     * @param freeresTableName 如 AD.CA_FREERES_0_20121101
     * @return
     */
    public static String getSubUserTableName(String freeresTableName)
    {
       
        return CommonUtil.concat("CD.CM_RESOURCE_", getFreeresSubTableIndex(freeresTableName), " u ");
    }
    
    /**
     *  2012-11-9号前的免资源表名是  AD.CA_FREERES_0_20121101
     *  之后的表名是 : AD.CA_FREERES_DAY_1_20121110，为了兼容，需要判断
     * @param freeresTableName 如：AD.CA_FREERES_0_20121101|AD.CA_FREERES_DAY_1_20121110
     * @return
     */
    private static  String getFreeresSubTableIndex(String freeresTableName){
        imsLogger.info("免资源表:" + freeresTableName);
        String[] table=freeresTableName.split("_");
        String index="";
        if("day".equalsIgnoreCase(table[2])){
            index=table[3];
        }else{
            index=table[2];
        }
        return index;
    }
    
    /**
     * 由于免资源表和用户表都是按账户分表，所以他们的分表是相同的
     * 
     * @param freeresTableName 如 AD.CA_FREERES_0_20121101
     * @return
     */
    public static String getSubProdTableName(String freeresTableName)
    {
        
        return CommonUtil.concat("CD.CO_PROD_", getFreeresSubTableIndex(freeresTableName), " p ");
    }
    
    
    
    
//    public static void clearAndInsertFreeres(String freeresTableName,String tempTableName,String items) {
//        
////        String cleanSql="truncate table "+tempTableName;
////          imsLogger.info("开始清除数据:"+cleanSql);
////        DBUtil.getDBClient().executeSql(cleanSql);
//        try
//        {
////            String[] parts=freeresTableName.split("_");
////            String sufix=parts[parts.length-1];
////            for(int index=0;index<50;index++){
////                System.out.println(buildSingleTableSql(index,sufix,tempTableName, items));
////            DBUtil.getDBClient().executeSql(buildSingleTableSql(index,sufix,tempTableName, items));
////            String sql=buildUnionSql(freeresTableName,tempTableName, items);
//          
//            DBUtil.getDBClient().executeSql(buildUnionSql(freeresTableName,tempTableName, items));
////            }
//        }
//        catch (Exception e)
//        {
//            imsLogger.error(e,e);
//        }
//    }
//    private static String buildUnionSql(String freeresTableName,String tempTableName,String items){
//        StringBuffer sb=new StringBuffer()
//        .append(" insert into "+tempTableName +" (USER_ID,PHONE_ID) (  ");
//        String[] parts=freeresTableName.split("_");
//        String sufix=parts[parts.length-1];
//        for(int index=0;index<50;index++){
//            sb.append(" select i.resource_id,1")
//            .append(" from CD.CM_RESOURCE_"+index+" i")
//            .append(CommonUtil.concat(" where  i.valid_date<=",getSqlDateTime(new Date()),"  and i.expire_date>=",getSqlDateTime(new Date())))
//            .append(" and   exists (")
//            .append(CommonUtil.concat(" select 1 from  AD.CA_FREERES_DAY_",index,"_",sufix," t where t.item_code  in (",items,")" ))
//             .append(" and i.resource_id = t.resource_id )")
//            .append(" union all ");
//        }
//        int index=sb.lastIndexOf("union all");
//        sb.delete(index, sb.length()-1);
//        sb.append(")");
//        String sql=sb.toString();
//        imsLogger.debug("50分表合并到"+tempTableName+" 表的 sql 语句:"+sql);
//        return sql;
//    }
 
//    public static void main(String[] args)
//    {
//        String freeresTableName="AD.CA_FREERES_DAY_0_20130110";
//        System.out.println(buildUnionSql(freeresTableName,"JD.IMS_GPRS_USER","6000051,6000401 ,6000411 ,6000541"));
//    }
    
    
    
    
    
}
