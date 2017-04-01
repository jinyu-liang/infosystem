package com.ailk.imssh.activity.cmp;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.openbilling.persistence.cust.entity.CmMktAct;
import com.ailk.openbilling.persistence.itable.entity.IBillActivity;
/**
 * @Description 处理出帐活动信息
 * @author zenglu
 * @Date 2012-09-17
 */
public class BillActivityCmp extends BaseCmp 
{
	public void createBillActivity(IBillActivity iBillActivity)
	{
		CmMktAct cmMktAct = new CmMktAct();
		cmMktAct.setCreateDate(context.getCommitDate());
		cmMktAct.setExpireDate(iBillActivity.getExpireDate());
		cmMktAct.setResourceId(iBillActivity.getUserId());
		cmMktAct.setOfferId(iBillActivity.getOfferId());
		if(iBillActivity.getOfferName() != null)
		{
			cmMktAct.setOfferName(iBillActivity.getOfferName());
		}
		if(iBillActivity.getOfferTag() != null)
		{
			cmMktAct.setOfferTag(iBillActivity.getOfferTag());
		}
		if(iBillActivity.getOfferType() != null)
		{
			cmMktAct.setOfferType(CommonUtil.string2Integer(iBillActivity.getOfferType()));
		}
		cmMktAct.setSoDate(context.getCommitDate());
		cmMktAct.setSoNbr(context.getSoNbr());
		cmMktAct.setValidDate(iBillActivity.getValidDate());
		super.insert(cmMktAct);
	}
	
	public void deleteBillActivity(IBillActivity iBillActivity)
	{
		super.deleteByCondition(CmMktAct.class, new DBCondition(CmMktAct.Field.resourceId, iBillActivity.getUserId()),
				new DBCondition(CmMktAct.Field.offerId, iBillActivity.getOfferId()));
	}
	
	public void updateBillActivity(IBillActivity iBillActivity)
	{
		CmMktAct cmMktAct = new CmMktAct();
		if(iBillActivity.getOfferName() != null)
		{
			cmMktAct.setOfferName(iBillActivity.getOfferName());
		}
		if(iBillActivity.getOfferTag() != null)
		{
			cmMktAct.setOfferTag(iBillActivity.getOfferTag());
		}
		if(iBillActivity.getOfferType() != null)
		{
			cmMktAct.setOfferType(CommonUtil.string2Integer(iBillActivity.getOfferType()));
		}
		cmMktAct.setExpireDate(iBillActivity.getExpireDate());
		cmMktAct.setSoDate(context.getCommitDate());
		cmMktAct.setSoNbr(context.getSoNbr());
		super.updateByCondition(cmMktAct, new DBCondition(CmMktAct.Field.resourceId, iBillActivity.getUserId()),
				new DBCondition(CmMktAct.Field.offerId, iBillActivity.getOfferId()));
	}
}
