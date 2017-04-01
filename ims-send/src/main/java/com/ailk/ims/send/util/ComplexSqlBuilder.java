package com.ailk.ims.send.util;

import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

public class ComplexSqlBuilder
{
    public static String buildSelectSmsDetailSql(Long blockId){
        return new StringBuffer("select ")
        .append(" DONE_CODE,BILL_ID,MESSAGE,PRIORITY_LEVEL,SEND_DATE,DONE_DATE,DEAL_DATE,SRC_ADDR,REQUEST_REPORT,FLAG,SMS_CODE,BLOCK_ID,EXT1,EXT2 ")
        .append(" from "+getSmsDetailTableName(blockId)+" t")
        .append(" where t.block_id = "+blockId)
        .toString();
    }
    /**
     * 根据blockId 模8 得到明细表分表
     * @param blockId
     * @return
     */
    private static String getSmsDetailTableName(Long blockId){
         long  subIndex=blockId%8;
         return  SendUtil.buildTableName(SmsSendInterfaceCheck.class)+"_"+subIndex;
    }
 
}
