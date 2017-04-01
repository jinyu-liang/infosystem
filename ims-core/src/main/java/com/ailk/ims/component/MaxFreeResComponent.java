package com.ailk.ims.component;

import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdShareAlloc;
import com.ailk.openbilling.persistence.imsintf.entity.ProductSeqItem;
import com.ailk.openbilling.persistence.pm.entity.PmAssetItem;
import com.ailk.openbilling.persistence.pm.entity.PmFreeUsageProperty;
import com.ailk.openbilling.persistence.sys.entity.SysMeasure;


/**
 * @Description:群组成员共享免费资源
 * @author wangjt
 * @Date 2011-9-27
 * //@Date 2012-04-26 lijc3 共享免费资源产品是账户级产品
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @Date 2012-11-07 zengxr #64320 status should default 0
 */
public class MaxFreeResComponent extends BaseComponent
{
    public MaxFreeResComponent()
    {
    }

    /**
     * 判断该产品是否是该账户使用
     */
    public void checkProdBelongAcct(Long prodId, Long acctId)
    {
//        CoProdInvObj where = new CoProdInvObj();
//        where.setProductId(prodId);
//        where.setObjectId(acctId);
//        where.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        CoProd obj = super.querySingle(CoProd.class,new DBCondition(CoProd.Field.productId,prodId),
        		new DBCondition(CoProd.Field.objectId,acctId),
        		new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
        if (obj == null)
        {
//            where.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_GCA);
            obj = super.querySingle(CoProd.class,new DBCondition(CoProd.Field.productId,prodId),
            		new DBCondition(CoProd.Field.objectId,acctId),
            		new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_GCA));
        }
        if (obj == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.MAXFREE_PRODSEQID_USERID_ERROR, prodId, acctId);
        }
    }

    /**
     * 判断该用户是否和该账户关联
     */
    public boolean isUserAndAcctRelated(Long userId, Long acctId)
    {
        CaAccountRes result = querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, userId),
                new DBCondition(CaAccountRes.Field.acctId, acctId));
        return result != null;
    }

    public CoProdShareAlloc queryShareAlloc(Long userId, Long prodId, Integer itemId)
    {
//        CoProdShareAlloc where = new CoProdShareAlloc();
//        where.setResourceId(userId);
//        //caohm5 add 2012-03-08
//        where.setObjectId(userId);
//        where.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
//        where.setProductId(prodId);
//        where.setItemId(itemId);
//        where.setObjectId(userId);
    	int amount = SysUtil.getInt(SysCodeDefine.busi.IM_MAX_FREERESOURCE_AMOUNT);
    	Long acctId=null;
        try
        {
            acctId=context.get3hTree().loadUser3hBean(userId).getAcctId();
        }
        catch(IMS3hNotFoundException e)
        {
        	imsLogger.error(e,e);
        }
        //@Date 2012-09-27 yugb :Bug #60135 设置最大免费资源时resourceId不正确
    	return super.querySingle(CoProdShareAlloc.class,
    			new DBCondition(CoProdShareAlloc.Field.resourceId,userId),
        		new DBCondition(CoProdShareAlloc.Field.objectId,acctId),
        		new DBCondition(CoProdShareAlloc.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT),
        		new DBCondition(CoProdShareAlloc.Field.productId,prodId),
        		new DBCondition(CoProdShareAlloc.Field.itemId,itemId),
        		new DBCondition(CoProdShareAlloc.Field.amount, amount, Operator.LESS));
    }

    public void deleteShareAlloc(Long userId, Long prodId, Integer itemId)
    {
//        CoProdShareAlloc where = new CoProdShareAlloc();
//        where.setResourceId(userId);
//        where.setProductId(prodId);
//        where.setItemId(itemId);
//        where.setObjectId(userId);
        Long acctId=null;
        try
        {
            acctId=context.get3hTree().loadUser3hBean(userId).getAcctId();
        }
        catch(IMS3hNotFoundException e)
        {
        	imsLogger.error(e,e);
        }
        super.deleteByCondition(CoProdShareAlloc.class,
        		new DBCondition(CoProdShareAlloc.Field.productId,prodId),
        		new DBCondition(CoProdShareAlloc.Field.itemId,itemId),
        		new DBCondition(CoProdShareAlloc.Field.objectId,acctId));
    }
    
    /**
     * @Description: [48444]清理分配给userId的maxFreeResource
     * @param userId	 
     * @author: tangjl5
     * @Date: 2012-6-25
     */
    public void deleteShareAlloc(Long userId)
    {
        super.deleteByCondition(CoProdShareAlloc.class,new DBCondition(CoProdShareAlloc.Field.resourceId,userId));
    }
    
    /**
     * @Description: [48444]清理账户的maxFreeResource分配
     * @param acctId	 
     * @author: wangdw5
     * @Date: 2012-6-26
     */
    public void deleteShareAllocOfAccount(Long acctId){
    	deleteByCondition(CoProdShareAlloc.class,new DBCondition(CoProdShareAlloc.Field.objectId,acctId),
        		new DBCondition(CoProdShareAlloc.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
    }

    public CoProdShareAlloc insertOrUpdateShareAlloc(Long userId, Short flag, String remark, ProductSeqItem productSeqItem,Date curCycleEndDate)
    {
        Long productSeqId = productSeqItem.getProduct_id();
        Integer itemId = productSeqItem.getResource_id();
        Long amount = productSeqItem.getResource_value();
        int billFlag = CommonUtil.short2Int(flag);

        CoProdShareAlloc shareAlloc = this.queryShareAlloc(userId, productSeqId, itemId);
        //@Date 2012-04-26 lijc3 共享免费资源产品是账户级产品
        CoProd coProd = context.getComponent(BaseProductComponent.class).loadProd(productSeqId);
//        if (coProd == null)
//        {
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, "product_id==" + productSeqId);
//        }
        if (shareAlloc == null)
        {
            // 增加
            CoProdShareAlloc coProdShareAlloc = new CoProdShareAlloc();
            coProdShareAlloc.setProductId(productSeqId);
            coProdShareAlloc.setResourceId(userId);
            if (billFlag == 0)
            {
                coProdShareAlloc.setBillFlag(2);//需要转换成计费需要的类型2代表 fix类型的
            }
            else
            {
                coProdShareAlloc.setBillFlag(billFlag);
            }
            coProdShareAlloc.setItemId(itemId);
            coProdShareAlloc.setMeasureId(queryFreeResMeasureId(itemId));
            coProdShareAlloc.setAmount(amount);
            
            // @Date 2012-7-12 tangjl5 Story #49447 max free resource需要转换为计费侧的measure_id和amount进行存储
            if (isCaptialForFreeResource(itemId))
            {
                coProdShareAlloc.setMeasureId(queryFreeResMeasureId(itemId));
                coProdShareAlloc.setAmount(CommonUtil.double2Long(context.getComponent(AmountComponent.class).parseRatingMaxFreeResAmount(itemId, amount.doubleValue(), coProd.getObjectId())));
            }
           // coProdShareAlloc.setRemark(remark);
            //caohm add 2012-03-08 objectId and objectType
            coProdShareAlloc.setObjectId(coProd.getObjectId());
            coProdShareAlloc.setObjectType(coProd.getObjectType());
            
            // 2011-12-13 fangyw 修改 失效日期为该产品的失效日期
            coProdShareAlloc.setExpireDate(coProd.getExpireDate());
            //@Date 2012-08-20 yugb :增加curCycleEndDate
            if(curCycleEndDate != null)
                coProdShareAlloc.setValidDate(curCycleEndDate);
            //2012-11-07 zengxr #64320 status should default 0
            coProdShareAlloc.setStatus(EnumCodeDefine.MAX_FREE_RESOURCE_VALID);
            return coProdShareAlloc;
        }
        // 修改
        CoProdShareAlloc value = new CoProdShareAlloc();
        value.setAmount(amount);
        if (billFlag == 0)
        {
            value.setBillFlag(2);//需要转换成计费需要的类型2代表 fix类型的
        }
        else
        {
            value.setBillFlag(billFlag);
        }
        //@Date 2012-08-20 yugb :增加curCycleEndDate
        if(curCycleEndDate != null)
            value.setValidDate(curCycleEndDate);
            //@Date 2012-08-24 yugb :[49853]delete max freeresource
          value.setStatus(EnumCodeDefine.MAX_FREE_RESOURCE_VALID);//0表示有效
//        CoProdShareAlloc where = new CoProdShareAlloc();
//        where.setProductId(productSeqId);
//        where.setResourceId(userId);
//        where.setItemId(itemId);
//        //caohm add 2012-03-08 objectId and objectType
//        where.setObjectId(userId);
//        where.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);

        super.updateByCondition(value, new DBCondition(CoProdShareAlloc.Field.productId,productSeqId),
        		new DBCondition(CoProdShareAlloc.Field.itemId,itemId),
        		new DBCondition(CoProdShareAlloc.Field.objectId,coProd.getObjectId()),
        		new DBCondition(CoProdShareAlloc.Field.objectType,coProd.getObjectType()));
        return null;
    }
    
    public List<CoProdShareAlloc> queryShareAlloc(List<Long> prodIds, Long acctId)
    {
    	int amount = SysUtil.getInt(SysCodeDefine.busi.IM_MAX_FREERESOURCE_AMOUNT);
        return super.query(CoProdShareAlloc.class,
                new DBCondition(CoProdShareAlloc.Field.objectId, acctId),
                new DBCondition(CoProdShareAlloc.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT),
                new DBCondition(CoProdShareAlloc.Field.productId, prodIds, Operator.IN),
                new DBCondition(CoProdShareAlloc.Field.amount,amount,Operator.LESS));
    }
    
    public CoProdShareAlloc deleteShareAlloc(Long userId, ProductSeqItem productSeqItem,Long maxFree){
    	Long productSeqId = productSeqItem.getProduct_id();
        Integer itemId = productSeqItem.getResource_id();
        //@Date 2012-04-26 lijc3 共享免费资源产品是账户级产品
        CoProd coProd = context.getComponent(BaseProductComponent.class).loadProd(productSeqId);
        //获取当前账期的结束时间
        CoProdBillingCycle billCycle = context.getComponent(ProductCycleComponent.class).queryFirstBillCycle(coProd, null);
        Date currentExpireDate = context.getComponent(ProductCycleComponent.class).getCurrentBillCycleEndDate(billCycle);  
        CoProdShareAlloc value = new CoProdShareAlloc();
        value.setExpireDate(currentExpireDate);
        //@Date 2012-08-27 yugb :#bug 55978
        value.setAmount(maxFree);
        value.setStatus(EnumCodeDefine.MAX_FREE_RESOURCE_INVALID);//1表示失效
        super.updateByCondition(value, new DBCondition(CoProdShareAlloc.Field.productId,productSeqId),
        		new DBCondition(CoProdShareAlloc.Field.itemId,itemId),
        		new DBCondition(CoProdShareAlloc.Field.objectId,coProd.getObjectId()),
        		new DBCondition(CoProdShareAlloc.Field.objectType,coProd.getObjectType()));
        return null;
    }
    
    /**
     * @Description: 判断科目是否是资金类的免费资源
     * @param itemId
     * @return   
     * @author: tangjl5
     * @Date: 2012-7-17
     */
    public boolean isCaptialForFreeResource(Integer itemId)
    {
        PmAssetItem item = (PmAssetItem)querySingle(PmAssetItem.class, new DBCondition(PmAssetItem.Field.assetItemId, itemId),
                new DBCondition(PmAssetItem.Field.assetType, EnumCodeDefine.ACCOUNT_ASSETTYPE_FREERESOURCE));
        if (item == null)
            return false;
        Integer measerueId = queryFreeResMeasureId(itemId);
        SysMeasure measure = (SysMeasure)querySingle(SysMeasure.class, new DBCondition(SysMeasure.Field.measureId, measerueId));
        if (
            CommonUtil.isIn(
                    measure.getMeasureTypeId(),
                    new Object[]{
                        101,
                        102,
                        103,
                        104,
                        111,
                        112
                    }))
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * @Description: 获取免费资源measure_id
     * @param itemId
     * @return	 
     * @author: tangjl5
     * @Date: 2012-7-17
     */
    public Integer queryFreeResMeasureId(Integer itemId)
    {
        PmFreeUsageProperty property = (PmFreeUsageProperty)querySingle(PmFreeUsageProperty.class, new DBCondition(PmFreeUsageProperty.Field.assetItemId, itemId));
        if (property == null)
        {
            // lacking configuration data of table[{0}] where the column [{1}] = [{2}]
            IMSUtil.throwBusiException(ErrorCodeDefine.LACK_CONFIG_DATE, "PM_FREE_USAGE_PROPERTY", "asset_item_id", itemId);
        }
        
        return property.getMeasureId();
     }
}
