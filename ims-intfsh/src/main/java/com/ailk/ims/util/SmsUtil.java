package com.ailk.ims.util;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.sd.entity.SysNotificationTemplate;
/**
 * 短信模板工作类
 * 
 * @author gaoqc5
 * @Date 2012-10-13
 */
public class SmsUtil
{
    private static final ImsLogger imsLogger=new ImsLogger(SmsUtil.class);
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static float measure_flow=1024;//1024*1024;
    private static float maesure_time=60;
    /**
     * 从 模板表里取出模板信息实例化短信内容
     */
    public static String instanceSmsMessage(Long templateId, Map<String, String> param)
    {

        String origialNote = getOrgiTemplateNote(templateId);
        if (CommonUtil.isNotEmpty(origialNote))
        {
            return instanceMessage(origialNote, param);
        }
        return "";

    }

    /**
     * 实例化 字符串
     */
    public static String instanceMessage(String message, Map<String, String> param)
    {
    	for(Entry<String,String> enty : param.entrySet())
        {
            String val = enty.getValue();
             if(null==val){//如果为空，则跳过
                 imsLogger.debug("*********************key=",enty.getKey(),"  's value is null!" );
                continue;
             }
            message = message.replaceAll(addFlag(enty.getKey()), val);
        }
        return message;
    }

    /**
     * @description: byte 转 M ,小数点保留两位
     * @author zenglu
     */
    public static Double getMByteFromByte(Long bByte)
    {
        // DecimalFormat df = new DecimalFormat("0.00");
        double aaa = 100 / 3;
        return aaa;
    }

    /**
     * 增加标志符
     */
    private static String addFlag(String key)
    {
        String newKey = CommonUtil.concat("<", key, ">");

        return newKey;
    }

    public static String getOrgiTemplateNote(Long templateId)
    {

        SysNotificationTemplate template = DBUtil.querySingle(SysNotificationTemplate.class, new DBCondition(
                SysNotificationTemplate.Field.templateId, templateId));
        if (null != template)
        {
            return template.getTemplateContent();
        }
        else
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LACK_CONFIG_DATE, "SysNotificationTemplate", "templateId",
                    templateId);
        }

    }
    /**
     * 转换成兆(M)
     * @param measure
     * @param amount
     * @return
     */
    public static String measureChangeToM(int measureId,long amount){
        if(0==amount){
            return "0";
        }
        imsLogger.debug(" measureId is ",measureId,", begin chg to Mb!");
        float  flow=amount;
        if(EnumCodeDefine.MEASURE_ID_BYTE==measureId){
            flow=flow/(measure_flow*measure_flow);
        }else if(EnumCodeDefine.MEASURE_ID_KB==measureId){
            flow=flow/measure_flow;
        }else if(EnumCodeDefine.MEASURE_ID_MB==measureId){
        }else{
            imsLogger.info("unkown measureId",measureId,",so unChg!");
        }
        if(flow<=0.01){//小于0.01的话，需要设置定值
            flow=0.01f;
        }
        return df.format(flow);
    }
 
    /**
     * 转换成秒
     * @param measureId
     * @param amount
     * @return
     */
    public static String measureChangeToHuor(int measureId,long amount){
        imsLogger.debug(" measureId is ",measureId,", begin chg to Hour!");
        float  flow=amount;
        if(EnumCodeShDefine.MEASURE_ID_SEC==measureId){//秒
            flow=flow/(maesure_time*maesure_time);
        }else if(EnumCodeShDefine.MEASURE_ID_MILLISECOND==measureId){//毫秒
            flow=flow/(1000*maesure_time*maesure_time);
        }else if(EnumCodeShDefine.MEASURE_ID_MINUTE==measureId){//分钟
            flow=flow/(maesure_time);
        }
        
        return df.format(flow);
    }
    
    public static String measureChangeToMinute(int measureId,long amount){
        imsLogger.debug(" measureId is ",measureId,", begin chg to Minute!");
        float  flow=amount;
        if(EnumCodeShDefine.MEASURE_ID_SEC==measureId){//秒
            flow=flow/(maesure_time);
        }else if(EnumCodeShDefine.MEASURE_ID_MILLISECOND==measureId){//毫秒
            flow=flow/(1000*maesure_time);
        }else if(EnumCodeShDefine.MEASURE_ID_HOUR==measureId){//小时
            flow=flow*maesure_time;
        }
        
        return df.format(flow);
    }
    /**
     * 转换成秒单位
     * @param measureId
     * @param amount
     * @return
     */
    public static String measureChangeToSecod(int measureId,long amount){
        imsLogger.debug(" measureId is ",measureId,", begin chg to second!");
        float  flow=amount;
       if(EnumCodeShDefine.MEASURE_ID_SEC==measureId){//秒
//            flow=flow/(f);
        }else if(EnumCodeShDefine.MEASURE_ID_MILLISECOND==measureId){//毫秒
            flow=flow/1000;
        }else if(EnumCodeShDefine.MEASURE_ID_HOUR==measureId){//小时
            flow=flow*maesure_time*maesure_time;
        }else if(EnumCodeShDefine.MEASURE_ID_MINUTE==measureId){//分钟
            flow=flow*maesure_time;
        }
       
        return df.format(flow);
    }
    /**
     * 判断是否是时间单位
     * gao 2012-11-22
     * @param measureId
     * @return
     */
    public static boolean isTimeMeasure(int measureId){
       switch (measureId)
    {
    case EnumCodeShDefine.MEASURE_ID_SEC:
    case EnumCodeShDefine.MEASURE_ID_MILLISECOND:
    case EnumCodeShDefine.MEASURE_ID_MINUTE:
    case EnumCodeShDefine.MEASURE_ID_HOUR:
        return true;
    default:
      return false;
    }
    }
    /**
     * 判断是否是流量单位
     * @param measureId
     * @return
     */
    public static boolean isFlowMeasure(int measureId){
        switch (measureId)
        {
        case EnumCodeDefine.MEASURE_ID_BYTE:
        case EnumCodeDefine.MEASURE_ID_KB:
        case EnumCodeDefine.MEASURE_ID_MB:
            return true;
        

        default:
            return false;
        }
    }
    /**
     * 转换成标准单位(如果是时间，则转换成秒；如果是流量，则转换成字节)
     * @param res
     * @return
     */
    public static CaFreeres converToStandardMeasure(CaFreeres res){
        if(isTimeMeasure(res.getMeasureId())){
            res.setUnit(Math.round(Double.parseDouble(measureChangeToSecod(res.getMeasureId(), res.getUnit()))));
           res.setRealUnit(Math.round(Double.parseDouble(measureChangeToSecod(res.getMeasureId(), res.getRealUnit()))));
        }else{
            res.setUnit(measureChangeToByte(res.getMeasureId(), res.getUnit()));
            res.setRealUnit(measureChangeToByte(res.getMeasureId(), res.getRealUnit())); 
        }
        return res;
    }
    
    
    /**
     * 转换成字节
     * @param measure
     * @param amount
     * @return
     */
    public static long measureChangeToByte(int measureId,long amount){
        imsLogger.debug(" measureId is ",measureId,", begin chg to byte!");
        long  flow=amount;
           if(EnumCodeDefine.MEASURE_ID_BYTE==measureId){
                
           }else if(EnumCodeDefine.MEASURE_ID_KB==measureId){
               flow=(long) (flow*measure_flow);
           }else if(EnumCodeDefine.MEASURE_ID_MB==measureId){
               flow=(long) (flow*measure_flow*measure_flow);
           }else{
               imsLogger.info("unkown measureId",measureId,",so unChg!");
           }   
           return flow;
         
    }
    
    

    // private static Map<String,String>convertParamObjectToMap(){
    // Map<String,String> param=new HashMap<String, String>();
    // return param;
    // }
    // 另一种实现，但性能没直接替换好
    // private static Pattern pattern = Pattern.compile("(?<!\\\\)\\<[^\\>]+\\>");
    //
    //
    // public static String instanceSmsTemplateWithPattern(String str,Map<String,String> map){
    // Matcher matcher = pattern.matcher(str);
    // StringBuffer sb = new StringBuffer();
    // while (matcher.find()) {
    // String oldKey = matcher.group();
    // String val = map.get(getKey(oldKey));
    //
    // matcher.appendReplacement(sb, val);
    // }
    // matcher.appendTail(sb);
    // return sb.toString();
    // }
    // private static String getKey(String oldKey)
    // {
    // return oldKey.replace("<", "").replace(">", "");
    // }

    // public static void main(String[] args)
    // {
    //
    // long l=System.currentTimeMillis();
    // String message="尊敬的客户,至<5003>年<5004>月<5005>日,您余额已少于5元,为<4001>,请及时充值。欢迎登录上海移动网站交费。上海移动自助渠道24小时为您服务.";
    // Map<String,String >map=new HashMap<String,String>();
    // map.put("5003", "2012");
    // map.put("5004", "10");
    // map.put("5005", "13");
    // map.put("4001", "2");
    // int i=100000;
    // while((i--)>0){
    // // instanceSmsTemplateWithPattern(message,map);
    // System.out.println(instanceSmsTemplateMessage(message,map));
    // }
    // System.out.println("******************cost: "+String.valueOf(System.currentTimeMillis()-l)+"ms");
    // }
//    public static void main(String args[])
//    {
//        
//       
//    System.out.println(measureChangeToM(EnumCodeDefine.MEASURE_ID_KB,1024 ));
//    }
   public static void main(String[] args)
{
//       System.out.println(measureChangeToByte(EnumCodeDefine.MEASURE_ID_MB,6));
//       System.out.println(measureChangeToByte(EnumCodeDefine.MEASURE_ID_KB,1));
//       System.out.println(524288000l+3221225472l);
       System.out.println(measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE,2048));
//       System.out.println(CommonUtil.ShortToInteger(EnumCodeDefine.MEASURE_ID_BYTE));
          
}
}
