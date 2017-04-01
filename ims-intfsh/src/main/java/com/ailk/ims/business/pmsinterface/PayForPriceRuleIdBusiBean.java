package com.ailk.ims.business.pmsinterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jef.database.Batch;
import jef.database.query.NativeQuery;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.PmsFeeComponent;
import com.ailk.ims.component.PmsUtils;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imspgmt.entity.PayForPriceRuleIdEntity;
import com.ailk.openbilling.persistence.imspgmt.entity.QueryPayForPriceRuleIdOut;
import com.ailk.openbilling.persistence.imspgmt.entity.QueryPayForPriceRuleIdReq;
import com.ailk.openbilling.persistence.pd.entity.PmAccumulateItemDef;
import com.ailk.openbilling.persistence.pd.entity.PmAccumulateItemRel;
import com.ailk.openbilling.persistence.pd.entity.PmPayforItemLimit;
import com.ailk.openbilling.persistence.pd.entity.PmPayforRegulation;
import com.ailk.openbilling.persistence.pd.entity.PmPriceEvent;
import com.ailk.openbilling.persistence.pd.entity.PmProdOfferPriceRule;

public class PayForPriceRuleIdBusiBean extends BaseBusiBean {

	private QueryPayForPriceRuleIdReq req;
	private PmsFeeComponent pmsF ;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... arg0)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] arg0) {
		// TODO Auto-generated method stub
		QueryPayForPriceRuleIdOut response = new QueryPayForPriceRuleIdOut();
		List<PayForPriceRuleIdEntity> pmList = (List<PayForPriceRuleIdEntity>) arg0[0];
		response.setPriceRuleIdList(pmList);
		return response;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... arg0) throws BaseException {
		List<PmAccumulateItemRel> accumlateItem= new ArrayList<PmAccumulateItemRel>();
		List<PayForPriceRuleIdEntity> priceRuleIdresult = new ArrayList<PayForPriceRuleIdEntity>();
		List<PayForPriceRuleIdEntity> result = new ArrayList<PayForPriceRuleIdEntity>();
		int payItemCode = -1;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select distinct a.accumulate_item from PD.PM_ACCUMULATE_ITEM_REL a ");
		StringBuffer itemIds = new StringBuffer();
		if(req.getItemIds()!=null && req.getItemIds().size() > 0){
			for(int i=0;i<req.getItemIds().size();i++){
				itemIds.append(req.getItemIds().get(i));
				if (i + 1 != req.getItemIds().size()) {
					itemIds.append(",");
				}
			}
				sqlStr.append(" where a.item_id in (").append(itemIds).append(")");
				sqlStr.append(" and not exists (select 1 from PD.PM_ACCUMULATE_ITEM_REL b where b.accumulate_item=a.accumulate_item and b.item_id not in(");
				sqlStr.append(itemIds).append("))");
		}
		try {
			NativeQuery<PmAccumulateItemRel> query = DBUtil.getDBClient().createNativeQuery(sqlStr.toString(),PmAccumulateItemRel.class );
			accumlateItem = query.getResultList();
			
			if(accumlateItem!=null && accumlateItem.size()>0){
				StringBuffer itemSql = new StringBuffer();
				StringBuffer ids = new StringBuffer();
				itemSql.append("Select distinct c.price_rule_id as priceRuleId FROM PD.PM_ACCUMULATE_ITEM_REL a,PD.PM_PRICE_EVENT b,PD.PM_PAYFOR_REGULATION c, PD.PM_PAYFOR_ITEM_LIMIT d, PD.PM_PAYFOR_SPEC  e, PD.PM_PROD_OFFER_PRICE_RULE f ");
				itemSql.append(" Where a.accumulate_item = b.item_id AND b.item_id = d.item_id AND c.price_rule_id = d.price_rule_id AND c.price_rule_id = f.price_rule_id AND c.REGULATION_SPEC_ID = e.REGULATION_SPEC_ID ");
				itemSql.append(" AND SYSDATE BETWEEN c.VALID_DATE AND c.EXPIRE_DATE AND a.ACCUMULATE_ITEM in (");
				for(int i=0;i<accumlateItem.size();i++){
					ids.append(accumlateItem.get(i).getAccumulateItem());
					if (i + 1 != accumlateItem.size()) {
						ids.append(",");
					}
				}
				//itemSql.append(accumlateItem.get(0).getAccumulateItem()).append(")");
				itemSql.append(ids).append(")");
				itemSql.append("  ORDER BY c.PRICE_RULE_ID");
				NativeQuery<PayForPriceRuleIdEntity> itemQuery = DBUtil.getDBClient().createNativeQuery(itemSql.toString(), PayForPriceRuleIdEntity.class);
				priceRuleIdresult  = itemQuery.getResultList();
				
				//只取一个price_rule_id显示
//				if(priceRuleIdresults!=null && priceRuleIdresults.size()>0){
//					PayForPriceRuleIdEntity payFor = new PayForPriceRuleIdEntity();
//					payFor.setPriceRuleId(priceRuleIdresults.get(0).getPriceRuleId());
//					priceRuleIdresult.add(payFor);
//				}
				
			}else{
				int maxItemId = pmsF.getMaxItemId();
				 payItemCode = maxItemId+1;
				//pd.pm_accumulate_item_def
				PmAccumulateItemDef pma = new PmAccumulateItemDef();
				pma.setAccumulateItem(payItemCode);
				pma.setName("新增科目名称-未命名");
				pma.setAccumulateClass(-1);
				pma.setAccumulateType(7);//7，表示账务虚科目，科目代码统一定义在pm_price_event
				pma.setAccumulateUse(0);
				pma.setDescription(pma.getName());
				pmsF.insert(pma);
				//PM_PRICE_EVENT
				PmPriceEvent pmp = new PmPriceEvent();
				pmp.setItemId(pma.getAccumulateItem());
				pmp.setName(pma.getName());
				pmp.setServiceSpecId(0);
				pmp.setItemType(7);
				pmp.setSubType(-1);
				pmp.setPriority(100);
				pmp.setDescription(pma.getDescription());
				//pmp.setOweTag(0);
				pmsF.insert(pmp);
				//pm_accumulate_item_rel
				Batch<PmAccumulateItemRel> batch = DBUtil.getDBClient().startBatchInsert(PmAccumulateItemRel.class);
				for(int j = 0 ; j < req.getItemIds().size();j++){
					PmAccumulateItemRel pmAcc = new PmAccumulateItemRel();
					pmAcc.setAccumulateItem(payItemCode);
					pmAcc.setItemId(req.getItemIds().get(j));
					pmAcc.setPolicyId(0);
					pmAcc.setCalType(0);
					pmAcc.setPriority(1);
					batch.add(pmAcc);
				}
				batch.commit();
				
				StringBuffer itemIdSql = new StringBuffer();
				itemIdSql.append("Select distinct c.price_rule_id as priceRuleId FROM PD.PM_ACCUMULATE_ITEM_REL a,PD.PM_PRICE_EVENT b,PD.PM_PAYFOR_REGULATION c, PD.PM_PAYFOR_ITEM_LIMIT d, PD.PM_PAYFOR_SPEC e, PD.PM_PROD_OFFER_PRICE_RULE f ");
				itemIdSql.append(" Where a.accumulate_item = b.item_id AND b.item_id = d.item_id AND c.price_rule_id = d.price_rule_id AND c.price_rule_id = f.price_rule_id AND c.REGULATION_SPEC_ID = e.REGULATION_SPEC_ID ");
				itemIdSql.append(" AND SYSDATE BETWEEN c.VALID_DATE AND c.EXPIRE_DATE AND a.ACCUMULATE_ITEM in (");
				itemIdSql.append(payItemCode).append(")");
				itemIdSql.append("  ORDER BY c.PRICE_RULE_ID");
				NativeQuery<PayForPriceRuleIdEntity> itemQuery = DBUtil.getDBClient().createNativeQuery(itemIdSql.toString(), PayForPriceRuleIdEntity.class);
				priceRuleIdresult  = itemQuery.getResultList();
			}
			
			if(priceRuleIdresult.isEmpty() && priceRuleIdresult.size()<=0 ){
				int maxPriceRuleId = pmsF.getMaxRriceRuleId();
				int priceRuleId = maxPriceRuleId+1;
				PmPayforRegulation pmReg = new PmPayforRegulation();
				pmReg.setPriceRuleId(priceRuleId);
				pmReg.setRegulationSpecId(0);
				pmReg.setPayforType(2);
				pmReg.setUseMode(3);
				pmReg.setState(0);
				pmReg.setValidMode(0);
				pmReg.setExpireMode(0);
				pmReg.setPaymentType(2);
				pmReg.setValidDate(PmsUtils.getDefaultStartDate());
				pmReg.setExpireDate(PmsUtils.getDefaultEndDate());
				pmsF.insert(pmReg);
				
				PmPayforItemLimit pmLimit = new PmPayforItemLimit();
				pmLimit.setPriceRuleId(pmReg.getPriceRuleId());
				pmLimit.setItemId(payItemCode);
				pmLimit.setPriority(1);
				pmLimit.setMaxLim(0L);
				pmLimit.setNumerator(100);
				pmLimit.setDenominator(100);
				pmsF.insert(pmLimit);
				
				//PmPayforSpec pmSpec = new PmPayforSpec();
				PmProdOfferPriceRule pmPro = new PmProdOfferPriceRule();
				pmPro.setPriceRuleId(pmReg.getPriceRuleId());
				pmPro.setName("新增代付规则名称-未定义");
				pmPro.setPriceRuleType(0);
				pmPro.setDescription(pmPro.getName());
				pmsF.insert(pmPro);
				
				PayForPriceRuleIdEntity payFor = new PayForPriceRuleIdEntity();
				payFor.setPriceRuleId(pmReg.getPriceRuleId());
				priceRuleIdresult.add(payFor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			IMSUtil.throwBusiException(e);
		}
		PayForPriceRuleIdEntity payForRule = new PayForPriceRuleIdEntity();
		payForRule.setPriceRuleId(priceRuleIdresult.get(0).getPriceRuleId());
		result.add(payForRule);
		return new Object[]{result};
	}

	@Override
	public void init(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub
		req = (QueryPayForPriceRuleIdReq) arg0[0];
		pmsF = context.getComponent(PmsFeeComponent.class);
	}

	@Override
	public void validate(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub

	}

}
