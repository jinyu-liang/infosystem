package com.ailk.ims.smsts.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.ailk.ims.smsts.flowbase.IBaseFlow;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * 流程类的参数类
 * 
 * @author gaoqc5
 * @date 2013-1-16
 */
public class SmsParam
{

    private String methodName;
    // 扫描表的其中一个分表
    private String subTableName;
    // 具体的一个业务的流程类
    private Class<? extends IBaseFlow> flowInstanceClass;
    // 业务编号
    private Integer busiNum;
    // 分表号
    private int subTableIndex;

 
    // 是否需要查询免费资源
    private boolean isQueryFreerRes;
    // //是否需要产品
    // private boolean needProduct;

    // 免费资源通知表
    private CaFreeresDayNotify dayNotify;

    private Map<Long, Long> templateIdAndBlockIdMap = null;
    
    private Set<Long> hasMsgSmsCode=new HashSet<Long>();
    
    /**
     * 存储sms_send_value 中的 currentValue ,如果上次有中断，那此值就是上次中断的那条数据的关键值(肯定大于-1)
     */
    private Long keywordVal=-1L;
    
    
//    //所有线程要发送的短信数据
//    private long outAllSmsCount=0;

    public Long getKeywordVal()
    {
        return keywordVal;
    }

    public void setKeywordVal(Long keywordVal)
    {
        this.keywordVal = keywordVal;
    }

    public Map<Long, Long> getTemplateIdAndBlockIdMap()
    {
        return templateIdAndBlockIdMap;
    }

    public void setTemplateIdAndBlockIdMap(Map<Long, Long> templateIdAndBlockIdMap)
    {
        this.templateIdAndBlockIdMap = templateIdAndBlockIdMap;
    }

    public void setDayNotify(CaFreeresDayNotify dayNotify)
    {
        this.dayNotify = dayNotify;
    }

    public CaFreeresDayNotify getDayNotify()
    {
        return dayNotify;
    }

    public int getSubTableIndex()
    {
        return subTableIndex;
    }

    public void setSubTableIndex(int subTableIndex)
    {
        this.subTableIndex = subTableIndex;
    }

    // public boolean isNeedProduct()
    // {
    // return needProduct;
    // }
    //
    // public void setNeedProduct(boolean needProduct)
    // {
    // this.needProduct = needProduct;
    // }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public String getSubTableName()
    {
        return subTableName;
    }

    public void setSubTableName(String subTableName)
    {
        this.subTableName = subTableName;
    }

    public Class<? extends IBaseFlow> getFlowInstanceClass()
    {
        return flowInstanceClass;
    }

    public void setFlowInstanceClass(Class<? extends IBaseFlow> flowInstanceClass)
    {
        this.flowInstanceClass = flowInstanceClass;
    }

    public Integer getBusiNum()
    {
        return busiNum;
    }

    public void setBusiNum(Integer busiNum)
    {
        this.busiNum = busiNum;
    }

    public boolean isQueryFreerRes()
    {
        return isQueryFreerRes;
    }

    public void setQueryFreerRes(boolean isQueryFreerRes)
    {
        this.isQueryFreerRes = isQueryFreerRes;
    }
    /**
     * 取第一个blockId
     * @return
     */
    public Long getFisrtBlockId()
    {
        for (Long l : templateIdAndBlockIdMap.keySet())
        {
            return templateIdAndBlockIdMap.get(l);
        }
        return null;
    }

    /**
     * 取一个模板编号
     */
    public Long getFisrtSmsCode()
    {
        for (Long l : templateIdAndBlockIdMap.keySet())
        {
            return l;
        }
        return null;
    }
    






    public void judeSmsCode(List<SmsSendInterfaceCheck> list)
    {
        if(CommonUtil.isEmpty(list)){
            return ;
        }
         if(hasMsgSmsCode.size()!=templateIdAndBlockIdMap.size()){
                  for(SmsSendInterfaceCheck check:list){
                         hasMsgSmsCode.add(check.getSmsCode());//不判断是否重复，直接插进入就ok
                  }
         }
    }

    public Set<Long> getHasMsgSmsCode()
    {
        return hasMsgSmsCode;
    }

    public void setHasMsgSmsCode(Set<Long> hasMsgSmsCode)
    {
        this.hasMsgSmsCode = hasMsgSmsCode;
    }
    /**
     * 对于不发送短信的流程，需要设置一个SmsCode
     * @param smsCode
     */
    public  void addHasMsgSmsCode(Long  smsCode){
        hasMsgSmsCode.add(smsCode);
    }
    

//    public void addSmsCount(int count)
//    {
//        this.outAllSmsCount += count;
//    }
//
//    public long getOutAllSmsCount()
//    {
//        return outAllSmsCount;
//    }
//
//    public void setOutAllSmsCount(long outAllSmsCount)
//    {
//        this.outAllSmsCount = outAllSmsCount;
//    }

}
