package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;

/**
 * 同步产品状态对应组件
 * 更改状态的时候不能更改当前已经更改过的记录
 * 
 * @author wangjt
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class ProdStsComponent extends BaseComponent
{
    public ProdStsComponent()
    {
    }

    /**
     * 修改产品的生命周期状态
     * 
     * @param newLifecycleStatusId:1-Active; 2-Suspend
     *  @Date 2012-04-27 lijc3 添加存在的打包销售品的情况
     */
    public void updateProdLifecycleStatus(CoProd coProd, int newLifecycleStatusId)
    {
        Integer oldLifecycleStatusId = coProd.getLifecycleStatusId();
        if (oldLifecycleStatusId != newLifecycleStatusId)
        {
            CoProd newCoProd = new CoProd();
            newCoProd.setLifecycleStatusId(newLifecycleStatusId);
            super.updateByCondition(newCoProd, new DBCondition(CoProd.Field.productId, coProd.getProductId()), new DBCondition(
                    CoProd.Field.objectId, coProd.getObjectId()),new DBCondition(CoProd.Field.soNbr, context.getSoNbr(),Operator.NOT_EQUALS));
        }
        BaseProductComponent prodCmp=context.getComponent(BaseProductComponent.class);
        List<Long> prodIds=prodCmp.queryProductIdsByParentProductId(coProd);
        if(CommonUtil.isEmpty(prodIds)){
        	return;
        }
//        //缓存中有,表示已经操作过
//        List<CoProd> prodList=context.getAllCacheList(CoProd.class, new CacheCondition(CoProd.Field.productId, prodIds,Operator.IN),
//        		new CacheCondition(CoProd.Field.soNbr,context.getDoneCode()) );
//        if(CommonUtil.isNotEmpty(prodList)){
//        	return;
//        }
        List<CoProd> prodList=this.query(CoProd.class, new DBCondition(CoProd.Field.productId, prodIds,Operator.IN),
			new DBCondition(CoProd.Field.objectId, coProd.getObjectId()));
        prodList=prodCmp.mergeProdList(prodList);
        if(CommonUtil.isEmpty(prodList)){
        	return ;
        }
        prodIds=new ArrayList<Long>();
        for (Iterator i = prodList.iterator(); i.hasNext();) {
			CoProd p = (CoProd) i.next();
			//状态不同，并且不能是当前操作过的
			if(p.getLifecycleStatusId()!=newLifecycleStatusId&&p.getSoNbr()!=context.getSoNbr()){
				prodIds.add(p.getProductId());
			}
		}
        if(CommonUtil.isNotEmpty(prodIds)){
        	CoProd newCoProd = new CoProd();
            newCoProd.setLifecycleStatusId(newLifecycleStatusId);
            super.updateByCondition(newCoProd, new DBCondition(CoProd.Field.productId, prodIds,Operator.IN), new DBCondition(
                    CoProd.Field.objectId, coProd.getObjectId()));
        }
    }

    public void deleteProdInfo(Long prodId)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<CoProd> prodList = prodCmp.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, prodId));
        if (CommonUtil.isNotEmpty(prodList))
        {
            CoProd prod = prodList.get(0);
            prodCmp.deleteProdCharValueByProdId(prodId, prod.getObjectId());
            context.getComponent(ProductCycleComponent.class).deleteProdBillingCycleByProdId(prodId, prod.getObjectId());
            prodCmp.deleteProdParamPriceByProdId(prodId, prod.getObjectId());
        }
    }

    /**
     * 产品用户级产品的手机号码（只取一个号码）
     */
    public String getPhoneIdByProdId(long prodId)
    {
        CoProd obj = this.querySingle(CoProd.class, new DBCondition(CoProd.Field.productId, prodId),
                new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        if (obj == null)
        {
            return null;
        }
        CmResIdentity iden = this.querySingle(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identityType,
                EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE), new DBCondition(CmResIdentity.Field.resourceId, obj.getObjectId()));
        return iden == null ? null : iden.getIdentity();

        /*
         * DBJoinCondition join = new DBJoinCondition(CoProdInvObj.class); ProductComponent
         * prodCmp=context.getComponent(ProductComponent.class); join.innerJoin(CmResIdentity.class, new
         * DBRelation(CmResIdentity.Field.resourceId, CoProdInvObj.Field.objectId)); Map<Class, DataObject> result =
         * super.queryJoinSingle(join, new DBCondition(CoProdInvObj.Field.productId, prodId), new
         * DBCondition(CoProdInvObj.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(
         * CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE)); if (result == null || result.isEmpty())
         * { return null; } CmResIdentity cmResIdentity = (CmResIdentity) result.get(CmResIdentity.class); return
         * cmResIdentity.getIdentity();
         */
    }

    public void updateProdFirstBillCycleDate(Date firstBillDate, CoProdBillingCycle cycle)
    {
        CoProdBillingCycle newCycle = new CoProdBillingCycle();
        newCycle.setFirstBillDate(firstBillDate);
        super.updateByCondition(newCycle, new DBCondition(CoProdBillingCycle.Field.productId, cycle.getProductId()),
                new DBCondition(CoProdBillingCycle.Field.objectId, cycle.getObjectId()), new DBCondition(
                        CoProdBillingCycle.Field.validDate, cycle.getValidDate()),new DBCondition(CoProdBillingCycle.Field.soNbr, context.getSoNbr(),Operator.NOT_EQUALS));
        
        BaseProductComponent prodCmp=context.getComponent(BaseProductComponent.class);
        CoProd temp=new CoProd();
        temp.setObjectId(cycle.getObjectId());
        temp.setProductId(cycle.getProductId());
        List<Long> prodIds=prodCmp.queryProductIdsByParentProductId(temp);
        if(CommonUtil.isEmpty(prodIds)){
        	return;
        }
        List<CoProd> prodList=this.query(CoProd.class, new DBCondition(CoProd.Field.productId, prodIds,Operator.IN),
			new DBCondition(CoProd.Field.objectId, cycle.getObjectId()));
        prodList=prodCmp.mergeProdList(prodList);
        if(CommonUtil.isEmpty(prodList)){
        	return ;
        }
        prodIds=new ArrayList<Long>();
        for (Iterator i = prodList.iterator(); i.hasNext();) {
			CoProd p = (CoProd) i.next();
			//状态不同，并且不能是当前操作过的
			CoProdBillingCycle firstCycle=context.getComponent(ProductCycleComponent.class).queryFirstBillCycle(p, null);
			if(firstCycle!=null){
				if(firstCycle.getSoNbr()!=context.getSoNbr()){
					CoProdBillingCycle newCoProd = new CoProdBillingCycle();
					newCoProd.setFirstBillDate(firstBillDate);
					super.updateByCondition(newCoProd, new DBCondition(CoProdBillingCycle.Field.productId, p.getProductId()),
			                new DBCondition(CoProdBillingCycle.Field.objectId, p.getObjectId()), new DBCondition(
			                        CoProdBillingCycle.Field.validDate, firstCycle.getValidDate()));
				}
			}
		}
    }

    /**
     * 根据销售品id查询扣费规则 ljc 2011-11-14
     * 
     * @param offerId
     * @return
     */
    public PmCompositeDeductRule queryPmCompsiteDeductRuleByOfferId(Integer offerId, Integer billType)
    {
        if (context.getExtendParam("Deduct_rule" + offerId) != null)
        {
            return (PmCompositeDeductRule) context.getExtendParam("Deduct_rule" + offerId);
        }
//        PmCompositeDeductRule rule = new PmCompositeDeductRule();
//        rule.setProductOfferingId(offerId);
//        rule.setBillingType(billType);
        PmCompositeDeductRule rule = this.querySingle(PmCompositeDeductRule.class,new DBCondition(PmCompositeDeductRule.Field.productOfferingId,offerId),
        		new DBCondition(PmCompositeDeductRule.Field.billingType,billType));
        if (rule != null)
        {
            context.addExtendParam("Deduct_rule" + offerId, rule);
            return rule;
        }
        return null;
    }
}
