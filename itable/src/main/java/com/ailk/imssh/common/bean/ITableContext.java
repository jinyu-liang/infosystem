package com.ailk.imssh.common.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import jef.database.DataObject;
import jef.tools.reflect.BeanUtils;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.flow.bean.IndexHandlerObject;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.mdb.UserInfoComplex;
import com.ailk.imssh.prod.complex.ProdComplex;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferMapping;

/**
 * @Description:公共信息对象
 * @author wangjt
 * @Date 2012-5-11
 */
public class ITableContext extends IMSContext
{
    private ImsLogger imsLogger = new ImsLogger(ITableContext.class);
    protected IDataIndex iDataIndex;
    private Map<Class, Object> cmpMap = new HashMap<Class, Object>();
	/**
     * 从sub表中获取，通过seq生成
     */
    private Long seqId;

    private Date localDate = DateUtil.currentDate();
    // 产品接口中的所有参数存放
    //参数为short opertype,   long object_id
    private Map<Short, Map<Long, List<ProdComplex>>> allProdMap;
    
    private List<PmProductOfferMapping> mappList;
    
    //存放当前索引表下对应I表的数据
    private Map<IndexHandlerObject, List<? extends DataObject>[]> indexHandlerObjectMap;
    
    /**
     * 过户存放用户相关信息
     */
    private UserInfoComplex userInfoComplex;
    
    /**
     * 过户修改为批量过户，过户时mdb存放的信息为多组
     */
    private List<UserInfoComplex> userInfoComplexList;
    
    /**
     * 方法名称
     */
    // private String methodName;

    public UserInfoComplex getUserInfoComplex()
    {
        return userInfoComplex;
    }
    public void setUserInfoComplex(UserInfoComplex userInfoComplex)
    {
        this.userInfoComplex = userInfoComplex;
    }
    /**
     * 非空
     */
    public Long getCustId()
    {
        return iDataIndex.getCustId();
    }
    public Long getBusiCode(){
        return iDataIndex.getBusiCode();
    }

    /**
     * 只有在只涉及到客户有关的表的操作时才为空
     */
    public Long getAcctId()
    {
        return iDataIndex.getAcctId();
    }

    /**
     * 如果只涉及到客户账户相关的表，为空；其余情况不为空
     */
    public Long getUserId()
    {
        return iDataIndex.getUserId();
    }

    /**
     * @return:索引表的commitDate，对应数据库中的create_date和so_date
     */
    public Date getCommitDate()
    {
        return iDataIndex.getCommitDate();
    }

    /**
     * 本地操作的时间，由信管自己生成
     */
    public Date getLocalDate()
    {
        return localDate;
    }

    public <T extends BaseCmp> T getCmp(Class<T> clazz)
    {
        Object object = cmpMap.get(clazz);
        if (object != null)
        {
            return (T) object;
        }

        try
        {
            T newCmp = BeanUtils.newInstance(clazz);
            newCmp.setContext(this);

            cmpMap.put(clazz, newCmp);
            return newCmp;
        }
        catch (Exception e)
        {
            imsLogger.error(e.getMessage());
            throw IMSUtil.throwBusiException(e);
        }
    }

    // public String getMethodName()
    // {
    // return methodName;
    // }
    //
    // public void setMethodName(String methodName)
    // {
    // this.methodName = methodName;
    // }

    public void setiDataIndex(IDataIndex iDataIndex)
    {
        this.iDataIndex = iDataIndex;
        super.setDoneCode(iDataIndex.getDoneCode());
    }

    public Map<Short, Map<Long, List<ProdComplex>>> getAllProdMap()
    {
        return allProdMap;
    }

    public void setAllProdMap(Map<Short, Map<Long, List<ProdComplex>>> allProdMap)
    {
        this.allProdMap = allProdMap;
    }

    public void setSeqId(Long seqId)
    {
        this.seqId = seqId;
    }

    public Long getSeqId()
    {
        return seqId;
    }

    public List<PmProductOfferMapping> getMappList()
    {
        return mappList;
    }

    public void setMappList(List<PmProductOfferMapping> mappList)
    {
        this.mappList = mappList;
    }

    public Map<IndexHandlerObject, List<? extends DataObject>[]> getIndexHandlerObjectMap()
    {
        return indexHandlerObjectMap;
    }

    public void setIndexHandlerObjectMap(Map<IndexHandlerObject, List<? extends DataObject>[]> indexHandlerObjectMap)
    {
        this.indexHandlerObjectMap = indexHandlerObjectMap;
    }
    
    
    
	public List<UserInfoComplex> getUserInfoComplexList() {
		return userInfoComplexList;
	}
	public void setUserInfoComplexList(List<UserInfoComplex> userInfoComplexList) {
		this.userInfoComplexList = userInfoComplexList;
	}
	/**
     * 
     * lijc3 2012-10-23 根据传入的handle获取到对应的I表数据
     * @param clazz
     * @return
     */
    public List<? extends DataObject>[] getIDataObjectList(Class<? extends BaseHandler> clazz){
        if(indexHandlerObjectMap!=null){
            Iterator<Entry<IndexHandlerObject, List<? extends DataObject>[]>> it = indexHandlerObjectMap.entrySet().iterator();
            while (it.hasNext())
            {
                Entry<IndexHandlerObject, List<? extends DataObject>[]> next = it.next();
                IndexHandlerObject indexHandlerObject = next.getKey();
                Class<? extends BaseHandler> handlerClass = indexHandlerObject.getHandler();
                if(handlerClass.equals(clazz)){
                    return next.getValue();
                }
            }
            return null;
        }else{
            return null;
        }
    }
    
    /**
     * 
     * lijc3 2012-10-23 获取单个I表数据，不能获取I_PROD_CHAR_VALUE,I_PROD_PRICE_PARAM,活动多用户信息表
     * @param clazz
     * @return
     */
    public DataObject getSingleIDataObjectList(Class<? extends BaseHandler> clazz){
        List<? extends DataObject>[] arr=getIDataObjectList(clazz);
        if(CommonUtil.isNotEmpty(arr)){
            List<? extends DataObject> list=arr[0];
            if(CommonUtil.isNotEmpty(list)){
                return list.get(0);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }


}
