package com.ailk.ims.component;

import java.sql.SQLException;
import java.util.List;

import jef.database.Condition.Operator;
import jef.database.query.NativeQuery;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imspgmt.entity.PayAndItemCodeEntity;
import com.ailk.openbilling.persistence.pd.entity.PmProductOfferRentFee;
import com.ailk.openbilling.persistence.pd.entity.PmSupermarketFreeres;
import com.ailk.openbilling.persistence.pm.entity.PmPriceEvent;

public class PmsFeeComponent extends BaseComponent {

	private PmsFeeComponent() {

	}

	public PmSupermarketFreeres queryPmSupermarketFreeres(Integer offerId,List<Integer> mainOfferList){
		return this.querySingle(PmSupermarketFreeres.class, new DBCondition(PmSupermarketFreeres.Field.productOfferingId, offerId),
				new DBCondition(PmSupermarketFreeres.Field.mainPromotion, mainOfferList,Operator.IN));
	}

	
	public PmProductOfferRentFee queryPmProductOfferRentFee(Integer offerId,int feeType){
		return this.querySingle(PmProductOfferRentFee.class, new DBCondition(PmProductOfferRentFee.Field.productOfferingId, offerId),
				new DBCondition(PmProductOfferRentFee.Field.feeType, feeType));
	}
	
	public PmPriceEvent queryPriceEvent(Integer itemCode){
		return this.querySingle(PmPriceEvent.class, new DBCondition(PmPriceEvent.Field.itemId, itemCode));
	}

	/**
	 * 获取最大的accumulate_item
	 * **/
	public int getMaxItemId() {
		List<PayAndItemCodeEntity> result = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(" select max(c.accumulate_item)as maxItemId from pd.pm_accumulate_item_def c ");
		try {
			NativeQuery<PayAndItemCodeEntity> query = DBUtil.getDBClient().createNativeQuery(sqlStr.toString(), PayAndItemCodeEntity.class);
			result = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			IMSUtil.throwBusiException(e);
		}
		return result.get(0).getMaxItemId();
	}
	/**
	 * 获取最大代付规则ID
	 * **/
	public int getMaxRriceRuleId() {
		// TODO Auto-generated method stub
		List<PayAndItemCodeEntity> result = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select max( t.price_rule_id) as maxPriceRuleId from pd.PM_PAYFOR_REGULATION t");
		try {
			NativeQuery<PayAndItemCodeEntity> query = DBUtil.getDBClient().createNativeQuery(sqlStr.toString(),PayAndItemCodeEntity.class);
			result = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			IMSUtil.throwBusiException(e);
		}
		return result.get(0).getMaxPriceRuleId();
	}
}
