package com.ailk.ims.complex;

import java.util.Date;
import java.util.List;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;

/**
 * @Description: 产品的复合类，可以集合存放产品的相关信息
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-9-27
 * @Date 2011-12-15 ljc 添加指定的失效时间
 * @Date 2011-03-02 ljc 添加validType
 */
public class ProductOrderComplex extends BaseComplex
{
    private CoProd coProd;// 主表
    private List<CoProdCharValue> coProdCharValueList;// 产品规格特征
    // private CoProdInvObj coProdInvObj;// 产品使用表
    // private List<CoProdInvCa> coProdInvCaList;// 产品支付表
    private List<ProductOrderComplex> subProdList;// 如果是打包产品的话，还需要子产品列表
    private List<CoProdBillingCycle> coProdBillingCycleList;// 产品账期
    private List<CoProdPriceParam> coProdPriceParamList;// 产品二次议价参数表
    /**
     * 删除产品时候指定的失效时间
     */
    private Date expireDate;
    /**
     * CRM传入的soId
     */
    private String soId;
    /**
     * 删除产品的时候指定validType
     */
    private Short validType;

    public Short getValidType()
    {
        return validType;
    }

    public void setValidType(Short validType)
    {
        this.validType = validType;
    }

    // public void setCoProdInvObj(CoProdInvObj coProdInvObj)
    // {
    // this.coProdInvObj = coProdInvObj;
    // }

    public String getSoId()
    {
        return soId;
    }

    public void setSoId(String soId)
    {
        this.soId = soId;
    }

    public CoProd getCoProd()
    {
        return coProd;
    }

    public void setCoProd(CoProd coProd)
    {
        this.coProd = coProd;
    }

    public List<CoProdCharValue> getCoProdCharValueList()
    {
        return coProdCharValueList;
    }

    public void setCoProdCharValueList(List<CoProdCharValue> coProdCharValueList)
    {
        this.coProdCharValueList = coProdCharValueList;
    }

    // public CoProdInvObj getCoProdInvObj()
    // {
    // return coProdInvObj;
    // }
    //
    // public void setCoProdInvolveObject(CoProdInvObj coProdInvObj)
    // {
    // this.coProdInvObj = coProdInvObj;
    // }

    public List<ProductOrderComplex> getSubProdList()
    {
        return subProdList;
    }

    public void setSubProdList(List<ProductOrderComplex> subProdList)
    {
        this.subProdList = subProdList;
    }

    public List<CoProdBillingCycle> getCoProdBillingCycleList()
    {
        return coProdBillingCycleList;
    }

    public void setCoProdBillingCycleList(List<CoProdBillingCycle> coProdBillingCycleList)
    {
        this.coProdBillingCycleList = coProdBillingCycleList;
    }

    public List<CoProdPriceParam> getCoProdPriceParamList()
    {
        return coProdPriceParamList;
    }

    public void setCoProdPriceParamList(List<CoProdPriceParam> coProdPriceParamList)
    {
        this.coProdPriceParamList = coProdPriceParamList;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

}
