package com.ailk.imssh.prod.complex;

import java.util.Date;
import java.util.List;
import com.ailk.ims.complex.BaseComplex;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdBundleComposite;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdMapping;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.itable.entity.IProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IProdPriceParam;
import com.ailk.openbilling.persistence.itable.entity.IProduct;

public class ProdComplex extends BaseComplex
{
    private CoProd dmProd;
    private List<CoProd> dmProdList;// 分段的产品列表 不一定有值
    // private CoProdInvObj dmInvObj;// 使用关系
    private List<CoProdBillingCycle> dmCycleList;// 产品周期
    private List<CoProdCharValue> dmValueList;// 特征值
    private List<CoProdPriceParam> dmParamList;// 二次议价参数
    private CoProdBundleComposite composite;// 打包关系
    private List<CmResIdentity> idenList;// cm_res_identity
    private List<CoProdMapping> mapps;// 映射关系
    private List<CoBudgetCharValue> budgetList;
    private Date oldExpireDate;//产品原来的失效时间

    private Offering3hbean offerBean;
    
    private IProduct prod;
    private List<IProdCharValue> valueList;// 不一定有值
    private List<IProdPriceParam> paramList;// 不一定有值

    private boolean done = false;// 是否已经处理

    public boolean isDone()
    {
        return done;
    }
    public void setDone(boolean done)
    {
        this.done = done;
    }
    public List<CoProdMapping> getMapps()
    {
        return mapps;
    }
    public void setMapps(List<CoProdMapping> mapps)
    {
        this.mapps = mapps;
    }

    public List<CmResIdentity> getIdenList()
    {
        return idenList;
    }
    public void setIdenList(List<CmResIdentity> idenList)
    {
        this.idenList = idenList;
    }
    public CoProdBundleComposite getComposite()
    {
        return composite;
    }
    public void setComposite(CoProdBundleComposite composite)
    {
        this.composite = composite;
    }
    public CoProd getDmProd()
    {
        return dmProd;
    }
    public void setDmProd(CoProd dmProd)
    {
        this.dmProd = dmProd;
    }
    public List<CoProd> getDmProdList()
    {
        return dmProdList;
    }
    public void setDmProdList(List<CoProd> dmProdList)
    {
        this.dmProdList = dmProdList;
    }
    public List<CoProdBillingCycle> getDmCycleList()
    {
        return dmCycleList;
    }
    public void setDmCycleList(List<CoProdBillingCycle> dmCycleList)
    {
        this.dmCycleList = dmCycleList;
    }
    public List<CoProdCharValue> getDmValueList()
    {
        return dmValueList;
    }
    public void setDmValueList(List<CoProdCharValue> dmValueList)
    {
        this.dmValueList = dmValueList;
    }
    public List<CoProdPriceParam> getDmParamList()
    {
        return dmParamList;
    }
    public void setDmParamList(List<CoProdPriceParam> dmParamList)
    {
        this.dmParamList = dmParamList;
    }
    public IProduct getProd()
    {
        return prod;
    }
    public void setProd(IProduct prod)
    {
        this.prod = prod;
    }
    public List<IProdCharValue> getValueList()
    {
        return valueList;
    }
    public void setValueList(List<IProdCharValue> valueList)
    {
        this.valueList = valueList;
    }
    public List<IProdPriceParam> getParamList()
    {
        return paramList;
    }
    public void setParamList(List<IProdPriceParam> paramList)
    {
        this.paramList = paramList;
    }
    public Offering3hbean getOfferBean()
    {
        return offerBean;
    }
    public void setOfferBean(Offering3hbean offerBean)
    {
        this.offerBean = offerBean;
    }
    public List<CoBudgetCharValue> getBudgetList()
    {
        return budgetList;
    }
    public void setBudgetList(List<CoBudgetCharValue> budgetList)
    {
        this.budgetList = budgetList;
    }
    public Date getOldExpireDate()
    {
        return oldExpireDate;
    }
    public void setOldExpireDate(Date oldExpireDate)
    {
        this.oldExpireDate = oldExpireDate;
    }
    
    
}
