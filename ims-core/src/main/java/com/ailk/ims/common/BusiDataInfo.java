package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.tools.reflect.BeanUtils;
import com.ailk.ims.cache.CacheHouse;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.listener.IMSListenerServiceFlow;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsintf.entity.OneTimeFee;

/**
 * 业务请求中存放的相关数据
 * @Description
 * @author wuyj
 * @Date 2012-9-21
 */
public class BusiDataInfo
{
    private List<BaseComponent> componentList;// 用于存放一次请求中的组件对象，避免重复new新对象
    
    private List<IMS3hBean> main3hBeans;//业务创建的3hbean
    
    private IMSListenerServiceFlow listenerServiceFlow;//当前请求相关的服务流程
    
    private CacheHouse cacheHouse;//存放缓存数据
    
    private Map<Object, Object> extendParams;// 扩展参数
    
    private boolean isMdbSync = true;// mdb上发是否同步，默认同步

    
    public BusiDataInfo(){
        cacheHouse = new CacheHouse();
    }
    
    public List<IMS3hBean> getMain3hBeans()
    {
        return main3hBeans;
    }

    public void setMain3hBeans(List<IMS3hBean> main3hBeans)
    {
        this.main3hBeans = main3hBeans;
    }
    public void addMain3hBean(IMS3hBean main3hBean)
    {
        if (this.main3hBeans == null)
        {
            this.main3hBeans = new ArrayList<IMS3hBean>();
        }
        this.main3hBeans.add(main3hBean);
    }

    public IMSListenerServiceFlow getListenerServiceFlow()
    {
        return listenerServiceFlow;
    }

    public void setListenerServiceFlow(IMSListenerServiceFlow listenerServiceFlow)
    {
        this.listenerServiceFlow = listenerServiceFlow;
    }

    public CacheHouse getCacheHouse()
    {
        return cacheHouse;
    }

    public void setCacheHouse(CacheHouse cacheHouse)
    {
        this.cacheHouse = cacheHouse;
    }

    public Object getExtendParam(Object key)
    {
        if (extendParams == null)
            return null;
        return extendParams.get(key);
    }

    public void addExtendParam(Object key, Object value)
    {
        if (extendParams == null)
            extendParams = new HashMap<Object, Object>();
        extendParams.put(key, value);
    }

    public <T extends BaseComponent> T getComponent(Class<T> clazz)
    {
        if(componentList == null)
            componentList = new ArrayList<BaseComponent>();
        
        
        if (!CommonUtil.isEmpty(componentList))
        {
            for (BaseComponent cmp : componentList)
            {
                if (cmp.getClass() == clazz)
                {
                    return (T) cmp;
                }
            }
        }
        // 如果没找到，则new一个
        try
        {
            T newCmp = BeanUtils.newInstance(clazz);
            componentList.add(newCmp);
            return newCmp;
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }

    public boolean isMdbSync()
    {
        return isMdbSync;
    }

    public void setMdbSync(boolean isMdbSync)
    {
        this.isMdbSync = isMdbSync;
    }
    public int getMdbSyncFlag()
    {
        return isMdbSync ? 2 : 0;
    }    
}
