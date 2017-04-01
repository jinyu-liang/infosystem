package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.define.SpecCodeExDefine;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IUserSubService;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;

/**
 * 个性化门限业务处理 .
 * 
 * @author chenxd
 * @Date 2013-03-18
 */
public class UserSubServiceCmp extends BaseCmp
{

    /**
     * 新增个性化门限业务 .
     * 
     * @param iUserSubService
     */
    public void addUserSubService(IUserSubService iUserSubService)
    {

        // 实列化产品编号
        Long productId = DBUtil.getSequence(CoProd.class);
        insertCoProd(iUserSubService, productId);
        insertCoProdCharValue(iUserSubService, productId);

    }

    /**
     * 数据库中新增特征值记录 .
     * 
     * @param iUserSubService
     * @param productId 产品实例序列号
     */
    private void insertCoProdCharValue(IUserSubService iUserSubService, Long productId)
    {
        CoProdCharValue charValue = new CoProdCharValue();
        charValue.setCreateDate(context.getCommitDate());
        charValue.setExpireDate(iUserSubService.getExpireDate());
        charValue.setValidDate(iUserSubService.getValidDate());
        charValue.setValue(iUserSubService.getBalance().toString());
        charValue.setObjectId(iUserSubService.getAcctId());
        charValue.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        charValue.setSoNbr(context.getSoNbr());
        charValue.setSoDate(context.getCommitDate());
        charValue.setSpecCharId(SpecCodeExDefine.SPEC_CHAR_ID);
        charValue.setProductId(productId);
        charValue.setGroupId(0L); // 未知
        this.insert(charValue);
    }

    /**
     * 数据库中新增（实例化）产品记录 .
     * 
     * @param iUserSubService
     * @param productId 产品实例序列号
     */
    private void insertCoProd(IUserSubService iUserSubService, Long productId)
    {
        Integer offeringId = getProductOfferingId(iUserSubService.getType());
        CoProd coProd = new CoProd();
        coProd.setProductId(productId);
        coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
        coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
        coProd.setProductOfferingId(offeringId);
        coProd.setBusiFlag(getBusiFlag(offeringId));
        coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
        coProd.setValidDate(iUserSubService.getValidDate());
        coProd.setExpireDate(iUserSubService.getExpireDate());
        coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
        coProd.setObjectId(iUserSubService.getAcctId());
        coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        coProd.setProdValidDate(iUserSubService.getValidDate());
        coProd.setProdExpireDate(iUserSubService.getExpireDate());
        coProd.setSoDate(context.getCommitDate());
        coProd.setSoNbr(context.getSoNbr());
        coProd.setPricingPlanId(0);

        this.insert(coProd);
    }

    /**
     * 更新个性化门限业务 .
     * 
     * @param iUserSubService
     */
    public void updateUserSubService(IUserSubService iUserSubService)
    {
        Integer offeringId = getProductOfferingId(iUserSubService.getType()); // 销售品编号，通过type获取
        List<Long> lstProdId = getLstProId(iUserSubService.getAcctId(), offeringId);
        if (!lstProdId.isEmpty())
        {

            // 更新实列化产品
            CoProd updateCoProd = new CoProd();
            updateCoProd.setExpireDate(iUserSubService.getExpireDate());
            updateCoProd.setProdExpireDate(iUserSubService.getExpireDate());
            updateCoProd.setSoDate(context.getCommitDate());
            updateCoProd.setSoNbr(context.getSoNbr());
            super.updateByCondition(updateCoProd, new DBCondition(CoProd.Field.productId, lstProdId, Operator.IN));

            // 更新特征值
            CoProdCharValue coProdCharValue = new CoProdCharValue();
            coProdCharValue.setValue(iUserSubService.getBalance().toString());
            coProdCharValue.setSoDate(context.getCommitDate());
            coProdCharValue.setSoNbr(context.getSoNbr());
            coProdCharValue.setExpireDate(iUserSubService.getExpireDate());
            super.updateByCondition(coProdCharValue,
                    new DBCondition(CoProdCharValue.Field.objectId, iUserSubService.getAcctId()), new DBCondition(
                            CoProdCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT), new DBCondition(
                            CoProdCharValue.Field.specCharId, SpecCodeExDefine.SPEC_CHAR_ID), new DBCondition(
                            CoProdCharValue.Field.productId, lstProdId, Operator.IN));

        }

    }

    /**
     * 删除个性化门限业务 .
     * 
     * @param iUserSubService
     */
    public void deleteUserSubService(IUserSubService iUserSubService)
    {
        Integer offeringId = getProductOfferingId(iUserSubService.getType()); // 销售品编号，通过type获取
        List<Long> lstProdId = getLstProId(iUserSubService.getAcctId(), offeringId);
        if (!lstProdId.isEmpty())
        {

            // 删除实列化产品(用户订购的产品)
            this.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, lstProdId, Operator.IN));
            
            CoProd valid = new CoProd();
            valid.setProdExpireDate(context.getCommitDate());
            updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId, lstProdId, Operator.IN));
            
            // 删除特征值
            this.deleteByCondition(CoProdCharValue.class,
                    new DBCondition(CoProdCharValue.Field.objectId, iUserSubService.getAcctId()), new DBCondition(
                            CoProdCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT), new DBCondition(
                            CoProdCharValue.Field.specCharId, SpecCodeExDefine.SPEC_CHAR_ID), new DBCondition(
                            CoProdCharValue.Field.productId, lstProdId, Operator.IN));

        }
    }

    /**
     * 获取账户下，对应的销售品被实例化的产品Id列表
     * 
     * @param acctId 账户编号 .
     * @param offeringId 销售品编号 .
     * @return
     */
    private List<Long> getLstProId(Long acctId, Integer offeringId)
    {
        List<Long> lstProdId = new ArrayList<Long>();
        List<CoProd> lstCoProd = getCoProds(acctId, offeringId);
        for (CoProd coProd : lstCoProd)
        {
            lstProdId.add(coProd.getProductId());
        }
        return lstProdId;
    }

    /**
     * 获取账户下，对应的销售品被实例化的产品列表 <br>
     * CoProd.Field.objectType=1 为账户级 .
     * 
     * @param acctId 账户编号 .
     * @param offeringId 销售品编号 .
     * @return 实例化（用户订购的产品）产品列表 .
     */

    private List<CoProd> getCoProds(Long acctId, Integer offeringId)
    {
        List<CoProd> lstCoProd = this.query(CoProd.class, new DBCondition(CoProd.Field.objectId, acctId), new DBCondition(
                CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT), new DBCondition(CoProd.Field.productOfferingId,
                offeringId));
        return lstCoProd;
    }

    /**
     * 通过type(门限类型)获取销售品编号
     * 
     * @param type 门限类型
     * @return 对应销售品编号
     */
    private Integer getProductOfferingId(Short type)
    {
        List<PmProductOfferSpecChar> lstPmProductOfferSpecChar = this.query(PmProductOfferSpecChar.class, new DBCondition(
                PmProductOfferSpecChar.Field.specCharId, SpecCodeExDefine.OFFER_SPEC_CHAR_ID), new DBCondition(
                PmProductOfferSpecChar.Field.value, type));
        if (!lstPmProductOfferSpecChar.isEmpty())
        {

            for (PmProductOfferSpecChar pmProductOfferSpecChar : lstPmProductOfferSpecChar)
            {
                return pmProductOfferSpecChar.getProductOfferingId();
            }
        }
        throw IMSUtil.throwBusiException(ErrorCodeExDefine.TYPE_TO_OFFERING_ID_NOT_EXIST, type);

    }

    /**
     * 获取销售品的BUSI_FLAG
     * 
     * @param offeringId
     * @return
     */
    private Integer getBusiFlag(Integer offeringId)
    {
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offeringId.longValue());
        if (offerBean != null)
        {
            return offerBean.getBusiFlag();
        }
        throw IMSUtil.throwBusiException(ErrorCodeExDefine.OFFERING_BUSI_FLAG_NOT_EXIST, offeringId);
    }
}
