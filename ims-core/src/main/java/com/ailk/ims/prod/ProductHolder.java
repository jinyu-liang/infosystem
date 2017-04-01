package com.ailk.ims.prod;

import java.util.ArrayList;
import java.util.List;
import com.ailk.ims.common.DataCacheBean;
import com.ailk.ims.common.InnerClass.ProdInvObj;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
/**
 * ProductOrder持有对象，里面还可以定义与产品相关的其它信息
 * @Description
 * @author wuyj
 * @Date 2012-7-18
 */
public class ProductHolder extends DataCacheBean
{
    private ProductHolder parent;// 存放打包产品的父产品holder
    
    private SProductOrder prodOrder;//原始CRM传入订单对象
    private Offering3hbean offer3hBean;//销售品的3hbean
    private ProdInvObj prodInv;//产品的使用对象信息
    
    private List<CoProd> coProds; // 分段的产品
    private List<CoProdBillingCycle> billingCycleList;
    private List<CoProdCharValue> charValueList;
    private List<CoProdPriceParam> priceParamList;
    private List<CmResServCycle> resServList;
    private boolean override;
    private Integer pricePlanId;
    private Integer overridePricePlanId;
    
    
    
    public void addDataObject(){
        
    }
    
    public Integer getPricePlanId()
    {
        return pricePlanId;
    }
    public void setPricePlanId(Integer pricePlanId)
    {
        this.pricePlanId = pricePlanId;
    }
    public Integer getOverridePricePlanId()
    {
        return overridePricePlanId;
    }
    public void setOverridePricePlanId(Integer overridePricePlanId)
    {
        this.overridePricePlanId = overridePricePlanId;
    }
    
    public Integer getOfferingId(){
        return this.offer3hBean.getOffering().getProductOfferingId();
    }
    public ProductHolder(SProductOrder prodOrder){
        this.setProdOrder(prodOrder);
    }

    public SProductOrder getProdOrder()
    {
        return prodOrder;
    }


    public void setProdOrder(SProductOrder prodOrder)
    {
        this.prodOrder = prodOrder;
    }

    public Offering3hbean getOffer3hBean()
    {
        return offer3hBean;
    }


    public void setOffer3hBean(Offering3hbean offer3hBean)
    {
        this.offer3hBean = offer3hBean;
    }


    public ProdInvObj getProdInv()
    {
        return prodInv;
    }


    public void setProdInv(ProdInvObj prodInv)
    {
        this.prodInv = prodInv;
    }

    public List<CoProdBillingCycle> getBillingCycleList()
    {
        return billingCycleList;
    }


    public void setBillingCycleList(List<CoProdBillingCycle> billingCycleList)
    {
        this.billingCycleList = billingCycleList;
    }

    /**
     * 该方法适用未分段产品，大部分都是未分段的
     * luojb 2012-8-23
     * @return
     */
    public CoProd getCoProd()
    {
        return coProds.get(0);
    }

    public List<CoProd> getCoProds()
    {
        return coProds;
    }

    public void addCoProd(CoProd coProd)
    {
        if(coProds == null)
            coProds = new ArrayList<CoProd>();
        coProds.add(coProd);
    }


    public List<CoProdCharValue> getCharValueList()
    {
        return charValueList;
    }


    public void setCharValueList(List<CoProdCharValue> charValueList)
    {
        this.charValueList = charValueList;
    }


    public List<CoProdPriceParam> getPriceParamList()
    {
        return priceParamList;
    }


    public void setPriceParamList(List<CoProdPriceParam> priceParamList)
    {
        this.priceParamList = priceParamList;
    }


    public List<CmResServCycle> getResServList()
    {
        return resServList;
    }


    public void setResServList(List<CmResServCycle> resServList)
    {
        this.resServList = resServList;
    }
    
    public boolean isOverride()
    {
        return override;
    }

    public void setOverride(boolean override)
    {
        this.override = override;
    }

    public ProductHolder getParent()
    {
        return parent;
    }

    public void setParent(ProductHolder parent)
    {
        this.parent = parent;
    }

    public boolean isAcctLevel(){
        return this.prodInv.getObjType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
    }
    public boolean isUserLevel(){
        return this.prodInv.getObjType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV;
    }
    public boolean isGroupLevel(){
        return this.prodInv.getObjType() == EnumCodeDefine.PROD_OBJECTTYPE_GCA;
    }
    
}
