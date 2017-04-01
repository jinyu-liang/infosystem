package com.ailk.ims.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.DataObject;
import com.ailk.ims.common.CacheCondition;
import com.ailk.openbilling.persistence.sys.entity.SysParameter;
/**
 * sys_parameter表的缓存封装类
 * @Description
 * @author luojb
 * @Date 2012-10-23
 */
public class SysParameterCacheParser extends BaseCacheParser
{

    private Map<String,String> map = new HashMap<String, String>();
    
    @Override
    protected void cacheSingleByCustom(DataObject obj)
    {
        if(obj==null)
            return ;
        SysParameter param = (SysParameter)obj;
        map.put(param.getParamCode(), param.getParamValue());
    }

    @Override
    protected void cacheListByCustom(List<DataObject> objList)
    {
        if(objList == null || objList.size()<=0)
            return;
        for(DataObject obj:objList)
        {
            cacheSingleByCustom(obj);
        }
    }
    
    public String getParamByCode(String key){
        String value = map.get(key);
        if(value != null)
            return value;
        if(CacheUtil.isAllowedFromDB(this.getCacheLevel()))
        {
            SysParameter obj = (SysParameter)this.getSingle(new CacheCondition(SysParameter.Field.paramCode,key));
            value = obj.getParamValue();
            this.cacheSingle(obj);
        }
        return value;
    }
    
}
