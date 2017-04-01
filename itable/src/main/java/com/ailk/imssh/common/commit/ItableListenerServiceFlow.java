package com.ailk.imssh.common.commit;

import jef.database.DataObject;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.scandeal.deal.IBaseDeal;
/**
 * 
 * @Description 事务监听器变量
 * @author lijc3
 * @Date 2013-1-7
 */
public class ItableListenerServiceFlow
{
    
    private DataObject dataOjbect;
    private ITableContext context;
    private IBaseDeal baseDeal;
    private Boolean removeFlag=false;
    
    public DataObject getDataOjbect()
    {
        return dataOjbect;
    }
    public void setDataOjbect(DataObject dataOjbect)
    {
        this.dataOjbect = dataOjbect;
    }
    public ITableContext getContext()
    {
        return context;
    }
    public void setContext(ITableContext context)
    {
        this.context = context;
    }
    public IConfig getConfig()
    {
        return config;
    }
    public void setConfig(IConfig config)
    {
        this.config = config;
    }
    private IConfig config;
    public IBaseDeal getBaseDeal()
    {
        return baseDeal;
    }
    public void setBaseDeal(IBaseDeal baseDeal)
    {
        this.baseDeal = baseDeal;
    }
    public Boolean getRemoveFlag()
    {
        return removeFlag;
    }
    public void setRemoveFlag(Boolean removeFlag)
    {
        this.removeFlag = removeFlag;
    }
    
}
