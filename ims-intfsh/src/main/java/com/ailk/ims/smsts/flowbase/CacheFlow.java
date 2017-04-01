package com.ailk.ims.smsts.flowbase;

import java.util.HashMap;
import java.util.Map;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.ims.util.SysUtil;
/**
 * 缓存类,常用的科目和模板缓存起来
 * @author gao
 *
 */
public class CacheFlow 
{
    /**
     * 科目缓存器
     */
    private Map<String, String> itemMap = new HashMap<String, String>();
    private Map<Long ,String> templateMap=new HashMap<Long,String>();
   /**
    * 根据科目关键字返回科目编号
    * @param itemKeyWord
    * @return
    */
    protected String getItemsCodeByItemKeyWord(String itemKeyWord)
    {
        String val = itemMap.get(itemKeyWord);
        if (CommonUtil.isEmpty(val))
        {
            val = SysUtil.getString(itemKeyWord);

            itemMap.put(itemKeyWord, val);
        }
        return val;
    }
    protected String getMessageByTemplateId(Long templateId){
        String val=templateMap.get(templateId);
        if(CommonUtil.isEmpty(val)){
            val=SmsUtil.getOrgiTemplateNote(templateId);
            templateMap.put(templateId, val);
        }
        return val;
    }
    public Map<Long, Long> getTemplateAndBlockIdMap()
    {
        // TODO Auto-generated method stub
        return null;
    }
    public void commitOther()
    {
        // TODO Auto-generated method stub
        
    }
    
    
    
}
