package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.tools.reflect.BeanUtils;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.IMS3hTree;
import com.ailk.ims.listener.IMSListenerServiceFlow;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.OneTimeFee;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * 上下文对象，在整个请求过程中进行流通，一些公用信息可放置于此
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj 2012-02-16 wuyujie : getComponent增加日志耗时输出
 * @Date 2012-3-20 luojb 删除ImsReceiveRecord、ImsReceiveRsp的引用
 * @Date 2012-3-21 luojb 删除busiRecord
 * @date 2012-04-02 zengxr get right busi spec of OneTimeFee/Reward for multi spec in one interface(getBusiSpecId)
 * @date 2012-04-19 wuyujie : getComponent日志改成trace级别
 */
public class IMSContext extends DataCacheBean
{
    private boolean diffException;
    private SOperInfo oper;// 操作员信息
    private OneTimeFee oneTimeFee;// 一次性费用
    private long doneCode;// 流水号，每个请求都会生成一个唯一的流水号
    private long soNbr;
    private Date requestDate;// 当前请求时间，取的是虚拟时间[上海项目，取的是索引表中的commit_date]
    private Map<Object, Object> extendParams;// 扩展参数
    private String soap;// soap报文
    private String soapPath;// soap报文存放地址
    private CBSErrorMsg cbsErrorMsg;// 应答公用信息
    private boolean sync = true;// mdb上发是否同步，默认同步
    private Date validDate;//记录每个实体的生效时间，供发布路由使用
    private Long userId;
    private boolean manualFlag;

	public boolean isManualFlag() {
		return manualFlag;
	}

	public void setManualFlag(boolean manualFlag) {
		this.manualFlag = manualFlag;
	}

	/*
     * private Object service;//调用的服务对象 private Method method;//调用的服务中的方法
     */
    private BaseNode serviceConfig;// 配置服务节点对象
    private BaseNode methodConfig;// 配置方法节点对象

    private List<com.ailk.ims.ims3h.IMS3hBean> main3hBeans;

    private List<BaseComponent> cmp_list;// 用于存放一次请求中的组件对象，避免重复new新对象

    private List<BaseBusiBean> busi_list;// 用于存放一次请求中的BUSI对象，避免重复new新对象

    private IMSContext parent;// 一次请求中如果有回调过个接口，则会创建多个context，这个字段用于关联追溯

    private String servicePrefix;// 每次请求的服务前缀，即service.method,这个会加在日志前面，便于查看

    private IMSListenerServiceFlow listenerServiceFlow;
    
    private BaseResponse busiFailedResponse;

    public IMS3hBean getMain3hBean()
    {
        return CommonUtil.isEmpty(main3hBeans) ? null : main3hBeans.get(0);
    }

    public List<IMS3hBean> getMain3hBeanList()
    {
        return main3hBeans;
    }

    public void setMain3hBean(List<IMS3hBean> main3hBeans)
    {
        this.main3hBeans = main3hBeans;
    }

    /**
     * @Description: 添加单个main3hbean
     * @author : wuyj
     * @date : 2012-1-13
     * @param main3hBean
     */
    public void addMain3hBean(IMS3hBean main3hBean)
    {
        if (this.main3hBeans == null)
        {
            this.main3hBeans = new ArrayList<IMS3hBean>();
        }
        this.main3hBeans.add(main3hBean);
    }
    
    public BaseResponse getBusiFailedResponse()
    {
        return busiFailedResponse;
    }

    public void setBusiFailedResponse(BaseResponse busiFailedResponse)
    {
        this.busiFailedResponse = busiFailedResponse;
    }

    public SOperInfo getOper()
    {
        return oper;
    }

    public void setOper(SOperInfo oper)
    {
        this.oper = oper;
    }

    public long getDoneCode()
    {
        return doneCode;
    }

    public void setDoneCode(long doneCode)
    {
        this.doneCode = doneCode;
    }

    public long getSoNbr() {
		return soNbr;
	}

	public void setSoNbr(long soNbr) {
		this.soNbr = soNbr;
	}

	public void addExtendParam(Object key, Object value)
    {
        if (extendParams == null)
            extendParams = new HashMap<Object, Object>();
        extendParams.put(key, value);
    }

    public Object getExtendParam(Object key)
    {
        if (extendParams == null)
            return null;
        return extendParams.get(key);
    }

    public void destroy()
    {
        if (cachedData != null)
            cachedData.clear();
        if (cmp_list != null)
            cmp_list.clear();
    }

    public Date getRequestDate()
    {
        return requestDate;
    }

    public void setRequestDate(Date requestDate)
    {
        this.requestDate = requestDate;
    }

    public String getSoap()
    {
        return soap;
    }

    public void setSoap(String soap)
    {
        this.soap = soap;
    }

    public CBSErrorMsg getCbsErrorMsg()
    {
        return cbsErrorMsg;
    }

    public void setCbsErrorMsg(CBSErrorMsg cbsErrorMsg)
    {
        this.cbsErrorMsg = cbsErrorMsg;
    }

    public String getSoapPath()
    {
        return soapPath;
    }

    public void setSoapPath(String soapPath)
    {
        this.soapPath = soapPath;
    }

    public boolean isSync()
    {
        return sync;
    }

    public void setSync(boolean sync)
    {
        this.sync = sync;
    }
    public int getSyncFlag()
    {
        return sync ?1:0;
    }

    public int getSyncFlag(Date validDate)
    {
        if (sync)
        {
            if (validDate == null)
            {
                return 1;
            }
            if (validDate.after(requestDate))
            {
                return 2;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            return 0;
        }
    }

    public OneTimeFee getOneTimeFee()
    {
        return oneTimeFee;
    }

    public void setOneTimeFee(OneTimeFee oneTimeFee)
    {
        this.oneTimeFee = oneTimeFee;
    }

    public String getMethodName()
    {
        return methodConfig.getTagName();
    }

    public String getServiceName()
    {
        return serviceConfig.getAttribute(BusiUtil.ATTR_BEAN);
        /*
         * String str = service.toString(); return str.substring(0,str.indexOf("@"));
         */
    }

    public BaseNode getServiceConfig()
    {
        return serviceConfig;
    }

    public void setServiceConfig(BaseNode serviceConfig)
    {
        this.serviceConfig = serviceConfig;
    }

    /**
     * @Description:获取当前业务的spec_id，在beans.xml里配置，如果有多个spec_id,则根据配置取默认的
     * @author : luojb
     * @date : 2011-11-3
     * @param isNormal，true-获取正常业务的spec_id, false-获取撤单业务的spec_id
     * @return
     */
    public Integer getBusiSpecId(boolean isNormal)
    {
        return getBusiSpecId(isNormal, null);
    }

    /**
     * @Description: 获取对应子类型的spec_id，适应于一个业务有多个spec_id的业务情景
     * @author : luojb
     * @date : 2011-11-3
     * @date 2012-04-02 zengxr get right busi spec of OneTimeFee/Reward for multi spec in one interface
     * @param isNormal,true-获取正常业务的spec_id, false-获取撤单业务的spec_id
     * @param type,子类的类型值，如果为
     * @return
     */
    public Integer getBusiSpecId(boolean isNormal, Integer type)
    {
        Integer specId = (Integer) this.getExtendParam(EnumCodeDefine.ACTION_SPEC_ID_KEY);
        if (specId != null)
            return specId;
        return BusiUtil.getSpecId(getServiceName(), getMethodName(), isNormal, type);
    }

    /**
     * @Description: 根据组件类型获取组件对象
     * @author : wuyj
     * @date : 2011-9-28
     * @param <T>
     * @param clazz
     * @return
     */
    public <T extends BaseComponent> T getComponent(Class<T> clazz)
    {
        // 2012-02-16 wuyujie : getComponent增加日志耗时输出
        // 2012-04-19 wuyujie : getComponent日志改成trace级别
        if (!CommonUtil.isEmpty(cmp_list))
        {
            for (BaseComponent cmp : cmp_list)
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
            newCmp.setContext(this);
            // yanchuan 没必要做整个创建代理类，现注释 2012-03-12
            // if (SysUtil.busi.getBoolean(SysCodeDefine.busi.INNER_COMPONENT_TRACE, false))
            // {
            // // 如果启用组件方法追踪，则需要创建代理类
            // newCmp = ProxyUtil.createProxyComponent(newCmp);
            // }

            if (cmp_list == null)
                cmp_list = new ArrayList<BaseComponent>();

            cmp_list.add(newCmp);

            return newCmp;
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }

    public BaseNode getMethodConfig()
    {
        return methodConfig;
    }

    public void setMethodConfig(BaseNode methodConfig)
    {
        this.methodConfig = methodConfig;
    }

    public IMSContext getParent()
    {
        return parent;
    }

    public String getServicePrefix()
    {
        return servicePrefix;
    }

    public void setServicePrefix(String servicePrefix)
    {
        this.servicePrefix = servicePrefix;
    }

    public IMSContext getTopParent()
    {
        IMSContext temp = this;
        while (temp.getParent() != null)
        {
            temp = this.getParent();
        }
        return temp;
    }

    public void setParent(IMSContext parent)
    {
        this.parent = parent;
    }

    public String getLogPrefix()
    {
        String serviceName = this.getServiceName();
        serviceName = serviceName.substring(serviceName.lastIndexOf(".") + 1);
        return serviceName + "." + this.getMethodName();
    }

    /*
     * public void addSoNbrMapping(List<ImsSonbrMapping> mappingList){ if(CommonUtil.isEmpty(mappingList)) return;
     * for(ImsSonbrMapping mapping : mappingList){ if(mapping.getDoneCode() == null) mapping.setDoneCode(this.getDoneCode());
     * if(mapping.getDoneDate() == null) mapping.setDoneDate(this.getReceiveRecord().getDoneTime());
     * if(CommonUtil.isEmpty(mapping.getOutSoNbr())){ mapping.setOutSoNbr(this.getOper().getSo_nbr()); } if(mapping.getOutSoDate()
     * == null){ mapping.setOutSoDate(IMSUtil.formatDate(this.getOper().getSo_date())); } if(mapping.getBusiCode() == null){
     * mapping.setBusiCode(this.getOper().getBusi_code()); } if(mapping.getBusiSpecId() == null){
     * mapping.setBusiSpecId(this.getBusiSpecId()); } } if(soNbrMappingList == null) soNbrMappingList = new
     * ArrayList<ImsSonbrMapping>(); soNbrMappingList.addAll(mappingList); } public List<ImsSonbrMapping> getSoNbrMappingList() {
     * return soNbrMappingList; }
     */

    /**
     * 判断该业务是否为非查询业务(比方说三户新装等就属于非查询业务)
     * 
     * @author wangjt 2011-12-22
     */
    public boolean isNotQueryBusi()
    {
        return methodConfig.getBooleanAttribute(BusiUtil.ATTR_NOT_QUERY, false);
    }

    /**
     * @Description 获取业务统计的uri
     * @Author lijingcheng
     * @return
     */
    // public String getUri()
    // {
    // return methodConfig.getAttribute(BusiUtil.ATTR_URI);
    // }

    /**
     * 获取线程变量ims3hTree luojb 2011-12-29
     */
    public IMS3hTree get3hTree()
    {
        IMS3hTree tree = (IMS3hTree) IMSUtil.getRequestContextParam(ConstantDefine.THREAD_LOCAL_IMS3HTREE);
        if (tree == null)
        {
            tree = new IMS3hTree();
            IMSUtil.setRequestContextParam(ConstantDefine.THREAD_LOCAL_IMS3HTREE, tree);
        }
        tree.setContext(this);
        return tree;
    }

    /**
     * 获取busiCode对应的busiBean wukl 2012-2-29
     * 
     * @param <T>
     * @param busiCode
     * @return
     */
    public <T extends BaseBusiBean> T getBusiBean(Integer busiCode)
    {
        try
        {
            String path = BusiUtil.getBusiBeanPath(busiCode);
            if (CommonUtil.isEmpty(path))
            {
                IMSUtil.throwBusiException("busi_code< " + busiCode + " > does not match");
            }

            Class<T> serviceClass = (Class<T>) Class.forName(path);
            // 如果没找到，则new一个
            if (!CommonUtil.isEmpty(busi_list))
            {
                for (BaseBusiBean busi : busi_list)
                {
                    if (busi.getClass() == serviceClass)
                    {
                        return (T) busi;
                    }
                }
            }

            T newBusiBean = BeanUtils.newInstance(serviceClass);
            newBusiBean.setContext(this);

            /*
             * if (SysUtil.inner.getBoolean(SysCodeDefine.inner.INNER_COMPONENT_TRACE, false)) { // 如果启用组件方法追踪，则需要创建代理类 newCmp =
             * ProxyUtil.createProxyComponent(newCmp); }
             */

            if (busi_list == null)
                busi_list = new ArrayList<BaseBusiBean>();

            busi_list.add(newBusiBean);

            return newBusiBean;
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }
    



    public IMSListenerServiceFlow getListenerServiceFlow()
    {
        return listenerServiceFlow;
    }

    public void setListenerServiceFlow(IMSListenerServiceFlow listenerServiceFlow)
    {
        this.listenerServiceFlow = listenerServiceFlow;
    }

	public boolean isDiffException() {
		return diffException;
	}

	public void setDiffException(boolean diffException) {
		this.diffException = diffException;
	}
    public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
