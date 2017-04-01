package com.ailk.ims.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jef.tools.JsonUtil;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;

/**
 * @description:上海工具类
 * @author caohm5
 * @date：2012-07-28
 */
public class CommonUtilSh
{
    /**
     * @description:判断某个元素是否在数据里面
     * @author caohm5
     * @date：2012-07-28
     */
    public static boolean isIn(Object arr[], Object item)
    {
        if (arr == null)
            return false;
        for (int i = 0; i < arr.length; i++)
            if (item.equals(arr[i]) || item == arr[i])
                return true;

        return false;
    }

    /**
     * 生成调用Cboss的JSON串 wangyh3 2012-7-31
     * 
     * @param pnodeName 父元素名称
     * @param req 请求对象
     * @return
     */
    public static String genCbossBusiJson(String pnodeName, Object req)
    {
        Map<String, Object> busiParams = new HashMap<String, Object>();
        busiParams.put(pnodeName, req);
        return JsonUtil.serialize(busiParams);
    }
    
    /**
     * 将DB表的OsStsDtl由NUMBER(6)转成VARCHAR2(20)
     */
    public static String getBossOsStsDtlFromDB(Integer dbOsStsDtl)
    {
        // 将DB中的十进制转成二进制
        String stsDtl = Integer.toBinaryString(dbOsStsDtl);

        // 获取需要补0的长度
        int len = EnumCodeShDefine.INIT_STS_DTL.length() - stsDtl.length();

        // 不足20位的，补0
        String prefix = EnumCodeShDefine.INIT_STS_DTL.substring(0, len);

        return prefix + stsDtl;
    }
    
    /**
     * 判断欠费停机
     * 19  欠费停机
     * 10  日保号停
     */
    public static Boolean  check(CmResLifecycle life,int len){
    	Integer osStsDtl=life.getOsStsDtl();
    	String osStsDtlStr=getBossOsStsDtlFromDB(osStsDtl);
    	if (osStsDtlStr.charAt(len) == '1')
        {
            return true;
        }
        return false;
    }
    
    
    /**
     * 获取CBOSS的流水号
     * xieqr 2012-11-7
     * @param soNbr  boss流水号
     * @param currDatre   当前时间
     * @return
     */
    public static String getCbossSeq(Long soNbr,Date currDatre){
        String nbr = String.valueOf(soNbr);
        String pre="210BIP1C005";
        String timeStr = DateUtil.formatDate(currDatre, DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
        int len = nbr.length();
        if(len<6){
            while(nbr.length()<6){
                nbr="0"+nbr;
            }
        }else if(len>6){
           nbr=nbr.substring(len-6);
        }
        return pre+timeStr+nbr;
    }
    /**
     * 获取CBOSS的订单号
     * xieqr 2012-11-7
     * @param soNbr  boss流水号
     * @param currDatre   当前时间
     * @return
     */
    public static String getCbossOrderId(Long soNbr,Date currDatre){
        String nbr = String.valueOf(soNbr);
        String pre="210";
        String timeStr = DateUtil.formatDate(currDatre, DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
        int len = nbr.length();
        if(len<6){
            while(nbr.length()<6){
                nbr="0"+nbr;
            }
        }else if(len>6){
           nbr=nbr.substring(len-6);
        }
        return pre+timeStr+nbr;
    }
    /**
     * 去掉字符串的最后一个字符
     *  @date 2012-11-22 gaoqc5
     * @param sb
     * @param ch
     * @return
     */
    public static  String removeLastFlag(String sb, char ch)
    {
        if(CommonUtil.isEmpty(sb)){
            return "";
        }
        if (sb.charAt(sb.length() - 1) == ch)
        {
            sb=sb.substring(0,sb.length()-1);
        }
        return sb.toString();
    }
}
