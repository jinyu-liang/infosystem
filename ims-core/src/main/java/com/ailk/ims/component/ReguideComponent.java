package com.ailk.ims.component;

import java.util.HashMap;
import java.util.Map;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.imsintf.entity.SPayReguideInfo;

/**
 * @Description:代付基类
 * @author wangjt
 * @Date 2011-9-26
 * @Date 2012-04-11 yangjh insertCoProd 操作 修改object_id和type的获取
 * @Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
 * @date 2012-07-10 luojb #50229 删除co_prod_valid,  co_prod 增加prod_valid_date，prod_expire_date
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public abstract class ReguideComponent extends BaseComponent
{
    public ReguideComponent()
    {
    }

    public void setReguideType(short reguideType)
    {
        this.reguideType = reguideType;
    }

    private short reguideType;

    private Map<String, Long> phone_user = new HashMap<String, Long>();

    public void put(String phoneId, Long userId)
    {
        phone_user.put(phoneId, userId);
    }

    public Long getUserIdByPhoneId(String phoneId)
    {
        Long userId = phone_user.get(phoneId);
        if (!CommonUtil.isValid(userId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_USER_FOR_PHONE_NOTEXIST, phoneId);
        }
        return userId;
    }

    public boolean isCharge()
    {
        return reguideType == EnumCodeDefine.REGUIDE_TYPE_CHARGE;
    }

    public boolean isUsage()
    {
        return reguideType == EnumCodeDefine.REGUIDE_TYPE_USAGE;
    }

    public int getBusiFlag()
    {
        // 0 reguide charge 1 reguide usage(只能给用户代付)
        return (isCharge() ? SpecCodeDefine.REGUIDE_CHARGE : SpecCodeDefine.REGUIDE_USAGE);
    }

    protected CoProd insertCoProd(SPayReguideInfo sPayReguideInfo)
    {
        int busiFlag = this.getBusiFlag();
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        Integer offeringId = prodCmp.queryOfferingId(busiFlag);
        Long userId = null;
        if (!CommonUtil.isValid(offeringId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCTOFFERINGID_ERROR, busiFlag);
        }
        Integer pricingPlanId = null;
        if (isCharge())
        {
            pricingPlanId = prodCmp.queryPricePlanId(offeringId, sPayReguideInfo.getPay_acct_id(), null);
        }
        else if (isUsage())
        {
            String phoneId = sPayReguideInfo.getPay_phone_id();
            userId = this.getUserIdByPhoneId(phoneId);// 必然有值
            pricingPlanId = prodCmp.queryPricePlanId(offeringId, null, userId);
        }
        if (pricingPlanId == null)
        {
            pricingPlanId = 0;
        }

        CoProd coProd = new CoProd();
        coProd.setProductId(DBUtil.getSequence(CoProd.class));
        coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);// ProductComponent
        coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);// Active
        coProd.setProductOfferingId(offeringId);
        coProd.setPricingPlanId(pricingPlanId);
        coProd.setBusiFlag(busiFlag);
        coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_PREPAID);
        coProd.setValidDate(IMSUtil.formatDate(sPayReguideInfo.getValid_date(), context.getRequestDate()));
        coProd.setExpireDate(IMSUtil.formatExpireDate(sPayReguideInfo.getExpire_date()));

        //2012-07-10 luojb
        coProd.setProdValidDate(coProd.getValidDate());
        coProd.setProdExpireDate(coProd.getExpireDate());
        
        // LIJC修改空指针 2012-03-22
        // if (sPayReguideInfo.getPay_acct_id() != null)
        // {
        // coProd.setObjectId(sPayReguideInfo.getPay_acct_id());
        // }
        // else
        // {
        // coProd.setObjectId(userId);
        // }
        // coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        // yangjh modify 2012-04-11
        if (isCharge())
        {
            coProd.setObjectId(sPayReguideInfo.getPay_acct_id());
            coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        }
        else if (isUsage())
        {
            // yangjh modify
            coProd.setObjectId(userId);
            coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        }
        // ljc add
        coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
        super.insert(coProd);
        return coProd;
    }

    public void deleteReguideInfoByProdId(Long prodId)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        prodCmp.deleteProdById(prodId);
        prodCmp.deleteProdCharValueByProdId(prodId);
        //@Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
        prodCmp.modifyProdValid(prodId, context.getRequestDate());
        // 删除账期信息
        super.deleteByCondition(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, prodId));
    }

}
