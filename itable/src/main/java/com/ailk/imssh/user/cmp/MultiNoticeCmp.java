package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.define.SpecCodeExDefine;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IMultiNotice;

/**
 * 多渠道提醒业务处理 .
 * 
 * @author chenxd
 * @Date 2013-03-18
 */
public class MultiNoticeCmp extends BaseCmp
{

    /**
     * 新增多渠道提醒业务 .
     * 
     * @param iUserSubService
     */
    public void addMultiNotice(IMultiNotice iMultiNotice)
    {

        // 实列化产品编号
        Long productId = DBUtil.getSequence(CoProd.class);
        insertCoProd(iMultiNotice, productId);
        insertCoProdCharValue(iMultiNotice, productId);

    }

    /**
     * 数据库中新增特征值记录 .
     * 
     * @param iUserSubService
     * @param productId 产品实例序列号
     */
    private void insertCoProdCharValue(IMultiNotice iMultiNotice, Long productId)
    {
        insetSendMethodCharValue(iMultiNotice, productId);
        insertContentTypeCharValue(iMultiNotice, productId);
    }

    /**
     * 添加内容范围特征值 ..
     * 
     * @param iMultiNotice
     * @param productId
     */
    private void insertContentTypeCharValue(IMultiNotice iMultiNotice, Long productId)
    {
        CoProdCharValue charValue = new CoProdCharValue();
        charValue.setCreateDate(context.getCommitDate());
        charValue.setExpireDate(iMultiNotice.getExpireDate());
        charValue.setValidDate(iMultiNotice.getValidDate());
        charValue.setValue(iMultiNotice.getContentType());
        charValue.setObjectId(iMultiNotice.getUserId());
        charValue.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        charValue.setSoNbr(context.getSoNbr());
        charValue.setSoDate(context.getCommitDate());
        charValue.setSpecCharId(SpecCodeExDefine.MULTI_NOTICE_CONTENT_TYPE_SPEC_CHAR_ID);
        charValue.setProductId(productId);
        charValue.setGroupId(0L);
        this.insert(charValue);
    }

    /**
     * 添加发送方式特征值 ..
     * 
     * @param iMultiNotice
     * @param productId
     */
    private void insetSendMethodCharValue(IMultiNotice iMultiNotice, Long productId)
    {
        CoProdCharValue charValue = new CoProdCharValue();
        charValue.setCreateDate(context.getCommitDate());
        charValue.setExpireDate(iMultiNotice.getExpireDate());
        charValue.setValidDate(iMultiNotice.getValidDate());
        charValue.setValue(iMultiNotice.getSendMethod());
        charValue.setObjectId(iMultiNotice.getUserId());
        charValue.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        charValue.setSoNbr(context.getSoNbr());
        charValue.setSoDate(context.getCommitDate());
        charValue.setSpecCharId(SpecCodeExDefine.MULTI_NOTICE_SEND_METHOD_SPEC_CHAR_ID);
        charValue.setProductId(productId);
        charValue.setGroupId(0L);
        this.insert(charValue);
    }

    /**
     * 数据库中新增（实例化）产品记录 .
     * 
     * @param iUserSubService
     * @param productId 产品实例序列号
     */
    private void insertCoProd(IMultiNotice iMultiNotice, Long productId)
    {
        Integer offeringId = getProductOfferingId();
        CoProd coProd = new CoProd();
        coProd.setProductId(productId);
        coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
        coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
        coProd.setProductOfferingId(offeringId);
        coProd.setBusiFlag(SpecCodeExDefine.MULTI_NOTICE_BUSI_FLAG);
        coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
        coProd.setValidDate(iMultiNotice.getValidDate());
        coProd.setExpireDate(iMultiNotice.getExpireDate());
        coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
        coProd.setObjectId(iMultiNotice.getUserId());
        coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        coProd.setProdValidDate(iMultiNotice.getValidDate());
        coProd.setProdExpireDate(iMultiNotice.getExpireDate());
        coProd.setSoDate(context.getCommitDate());
        coProd.setSoNbr(context.getSoNbr());
        coProd.setPricingPlanId(0);

        this.insert(coProd);
    }

    /**
     * 删除多渠道提醒 .
     * 
     * @param iUserSubService
     */
    public void deleteMultiNotice(IMultiNotice iMultiNotice)
    {
        Integer offeringId = getProductOfferingId(); // 销售品编号，通过type获取
        List<Long> lstProdId = getLstProId(iMultiNotice.getUserId(), offeringId, iMultiNotice);
        if (!lstProdId.isEmpty())
        {

            // 删除实列化产品(用户订购的产品)
            this.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, lstProdId, Operator.IN));
            // 删除特征值
            this.deleteByCondition(CoProdCharValue.class,
                    new DBCondition(CoProdCharValue.Field.productId, lstProdId, Operator.IN));

        }
    }

    /**
     * 获取账户下，对应的销售品被实例化的产品Id列表
     * 
     * @param userId 账户编号 .
     * @param offeringId 销售品编号 .
     * @param iMultiNotice .
     * @return
     */
    private List<Long> getLstProId(Long userId, Integer offeringId, IMultiNotice iMultiNotice)
    {
        List<Long> lstProdId = new ArrayList<Long>();
        List<CoProd> lstCoProd = getCoProds(userId, offeringId);
        for (CoProd coProd : lstCoProd)
        {
            List<CoProdCharValue> lstCharValue = this.query(CoProdCharValue.class, new DBCondition(
                    CoProdCharValue.Field.productId, coProd.getProductId()));
            boolean isSelectedMethod = false;
            boolean isSelectedContent = false;
            for (CoProdCharValue charValue : lstCharValue)
            {
                switch (charValue.getSpecCharId())
                {
                case SpecCodeExDefine.MULTI_NOTICE_SEND_METHOD_SPEC_CHAR_ID:
                    if (charValue.getValue().trim().equals(iMultiNotice.getSendMethod().trim()))
                    {
                        isSelectedMethod = true;
                    }
                    break;
                case SpecCodeExDefine.MULTI_NOTICE_CONTENT_TYPE_SPEC_CHAR_ID:
                    if (charValue.getValue().trim().equals(iMultiNotice.getContentType().trim()))
                    {
                        isSelectedContent = true;
                    }

                    break;
                default:

                }
            }
            if (isSelectedMethod && isSelectedContent)
            {
                lstProdId.add(coProd.getProductId());
            }
        }
        return lstProdId;
    }

    /**
     * 获取用户下，对应的销售品被实例化的产品列表 <br>
     * CoProd.Field.objectType=0 为账户级 .
     * 
     * @param userId 用户编号 .
     * @param offeringId 销售品编号 .
     * @return 实例化（用户订购的产品）产品列表 .
     */

    private List<CoProd> getCoProds(Long userId, Integer offeringId)
    {
        List<CoProd> lstCoProd = this.query(CoProd.class, new DBCondition(CoProd.Field.objectId, userId), new DBCondition(
                CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(CoProd.Field.productOfferingId,
                offeringId));
        return lstCoProd;
    }

    /**
     * 获取多渠道提醒销售品编号
     */
    private Integer getProductOfferingId()
    {
        ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
        Integer offerId =  prodCmp.queryOfferingId(SpecCodeExDefine.MULTI_NOTICE_BUSI_FLAG);
        if(offerId == null){
        	IMSUtil.throwBusiException(ErrorCodeExDefine.OFFERING_BUSI_FLAG_NOT_EXIST, SpecCodeExDefine.MULTI_NOTICE_BUSI_FLAG);
        }
        return offerId;
    }

}
