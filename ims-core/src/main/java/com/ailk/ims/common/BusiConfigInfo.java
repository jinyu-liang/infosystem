package com.ailk.ims.common;

import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.xml.BaseNode;
/**
 * 业务配置信息，当前请求与busi.xml相关联的属性都定义存放在这里
 * @Description
 * @author wuyj
 * @Date 2012-9-21
 */
public class BusiConfigInfo
{
    private BaseNode methodNode;//对应的方法节点
    private BaseNode serviceNode;//对应的服务节点
    
    //以下是冗余信息，因为常用，为了提高效率
    private int busiCode;
    private Class busiBeanClass;
    private Class mdbBeanClass;
    private Class tsBeanClass;
    
    
    
    public BusiConfigInfo(BaseNode methodNode) throws Exception{
        this.methodNode = methodNode;
        this.serviceNode = methodNode.getParent();
        this.busiCode = methodNode.getIntAttribute(BusiUtil.ATTR_BUSI_CODE);
        
        
        BaseNode busiNode = methodNode.getChildByTagName(BusiUtil.TAG_BUSI);
        if(busiNode != null){
            String bean = busiNode.getAttribute(BusiUtil.ATTR_BEAN);
            if(CommonUtil.isNotEmpty(bean)){
                this.busiBeanClass = (Class<BaseBusiBean>)Class.forName(bean);
            }
        }
        BaseNode mdbNode = methodNode.getChildByTagName(BusiUtil.TAG_MDB);
        if(mdbNode != null){
            String bean = mdbNode.getAttribute(BusiUtil.ATTR_BEAN);
            if(CommonUtil.isNotEmpty(bean)){
                this.mdbBeanClass = (Class<BaseMdbBean>)Class.forName(bean);
            }
        }
        BaseNode tsNode = methodNode.getChildByTagName(BusiUtil.TAG_TS);
        if(tsNode != null){
            String bean = tsNode.getAttribute(BusiUtil.ATTR_BEAN);
            if(CommonUtil.isNotEmpty(bean)){
                this.tsBeanClass = (Class<BaseTsBean>)Class.forName(bean);
            }
        }
    }
    
    public Class getBusiBeanClass()
    {
        return busiBeanClass;
    }
    public Class getMdbBeanClass()
    {
        return mdbBeanClass;
    }
    public int getBusiCode()
    {
        return busiCode;
    }

    public BaseNode getMethodNode()
    {
        return methodNode;
    }
    public BaseNode getServiceNode()
    {
        return serviceNode;
    }
    
    
    public Class getTsBeanClass()
    {
        return tsBeanClass;
    }

    /**
    * 获取实例化的mdbbean
    * @author wuyj 2012-9-21
    * @return
    * @throws IMSException
    */
    public BaseMdbBean getMdbBean() throws IMSException
    {
        try
        {
            return (BaseMdbBean) ClassUtil.instance(this.getMdbBeanClass());
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }
    
    /**
     * 获取实例化的busibean
     * @author wuyj 2012-9-21
     * @return
     * @throws IMSException
     */
    public BaseBusiBean getBusiBean() throws IMSException
    {
        try
        {
            return (BaseBusiBean) ClassUtil.instance(this.getBusiBeanClass());
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }
    /**
     * 获取实例化的tsbean
     * @author wuyj 2012-9-21
     * @return
     * @throws IMSException
     */
    public BaseTsBean getTsBean() throws IMSException
    {
        try
        {
            return (BaseTsBean) ClassUtil.instance(this.getTsBeanClass());
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }
    
    
    /**
     * 获取服务节点的bean属性
     * @author wuyj 2012-9-29
     * @return
     */
    public String getServiceBean(){
        return serviceNode.getAttribute(BusiUtil.ATTR_BEAN);
    }
    /**
     * 获取服务名称，即bean属性的类名（不包括包名）
     * @author wuyj 2012-9-29
     * @return
     */
    public String getServiceName(){
        String serviceBean = getServiceBean();
        return serviceBean.substring(serviceBean.lastIndexOf(".") + 1);
    }
    /**
     * 获取方法名称
     * @author wuyj 2012-9-29
     * @return
     */
    public String getMethodName(){
        return methodNode.getTagName();
    }
    
    
    public String toString(){
        return CommonUtil.concat(getServiceBean(),"@",getMethodName());
    }
    
    
    public boolean isNotQueryBusi(){
        return methodNode.getBooleanAttribute(
                BusiUtil.ATTR_NOT_QUERY,
                serviceNode.getBooleanAttribute(BusiUtil.ATTR_NOT_QUERY, false));//默认是查询接口
    }
    public boolean isOnetimefee(){
        return methodNode.getBooleanAttribute(
                ConstantDefine.IS_ONE_TIME_FEE,
                serviceNode.getBooleanAttribute(ConstantDefine.IS_ONE_TIME_FEE, true));//默认需要收取
    }
    
    public Integer getSpecId(boolean isNormal) throws IMSException{
        return getSpecId(isNormal,null);
    }
    public Integer getSpecId(boolean isNormal, Integer type) throws IMSException
    {
        BaseNode methodNode = this.getMethodNode();
        BaseNode specNode = methodNode.getChildByTagName(BusiUtil.TAG_SPEC_ID);
        String attr = null;
        Integer specId = null;
        if (isNormal)
        {
            attr = BusiUtil.ATTR_NORMAL;
        }
        else
        {
            attr = BusiUtil.ATTR_CANCEL;
        }
        if (type == null)
        {
            specId = getDefaultSpecId(specNode, attr);
        }
        else
        {
            String attrType = "type" + type;
            specId = getSpecId(specNode, attr, attrType);
        }

        return specId;
    }

    private static Integer getSpecId(BaseNode specNode, String attr, String attrType)
    {
        BaseNode specTypeNode = specNode.getChildByTagName(attrType);
        if (specTypeNode == null)
        {
            return null;
        }
        return Integer.parseInt(specTypeNode.getAttribute(attr));

    }

    private static Integer getDefaultSpecId(BaseNode specNode, String attr)
    {
        BaseNode defaultSpecNode = specNode.getChildByAttribute(BusiUtil.ATTR_ISDEFAULT, "true");
        if (defaultSpecNode == null)
        {
            String specId = specNode.getAttribute(attr);
            if (specId == null)
            {
                specId = specNode.getChildren().get(0).getAttribute(attr);
            }
            return Integer.parseInt(specId);
        }
        return Integer.parseInt(defaultSpecNode.getAttribute(attr));
    }

    
}
