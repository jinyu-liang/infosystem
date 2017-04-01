package com.ailk.imssh.acct.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.itable.entity.ICancelDelayStop;

public class AcctCancelDelayStopHander extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<? extends DataObject> dataList = dataArr[0];
		for (int j = 0; j < dataList.size(); j++) {
			ICancelDelayStop owe = (ICancelDelayStop) dataList.get(j);
			ITableUtil.setValue2ContextHolder(null, owe.getAcctId(), null);
			int operType = owe.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				insertCoProdByDelayStop(owe);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				deleteCoProdByDelayStop(owe);
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	private CoProd insertCoProdByDelayStop(ICancelDelayStop owe) {
		ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
		Integer offeringId = prodCmp.queryOfferingId(SpecCodeDefine.CANCEL_DELAY_STOP);
		if (!CommonUtil.isValid(offeringId)) {
			throw IMSUtil.throwBusiException(ErrorCodeExDefine.PRODUCTOFFERINGID_ERROR, SpecCodeDefine.CANCEL_DELAY_STOP);
		}
		CoProd coProd = new CoProd();
		coProd.setProductId(DBUtil.getSequence(CoProd.class));
		coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
		coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
		coProd.setProductOfferingId(offeringId);
		coProd.setPricingPlanId(0);
		coProd.setBusiFlag(SpecCodeDefine.CANCEL_DELAY_STOP);
		coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
		coProd.setValidDate(owe.getValidDate());
		coProd.setExpireDate(owe.getExpireDate());
		coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
		coProd.setObjectId(owe.getAcctId());
		coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
		coProd.setProdValidDate(owe.getValidDate());
		coProd.setProdExpireDate(owe.getExpireDate());
		prodCmp.insert(coProd);
		// context.getComponent(ProductComponent.class).createProdValid(coProd);
		return coProd;
	}
	
	
	private void deleteCoProdByDelayStop(ICancelDelayStop owe) {
		getContext().getCmp(ProdCmp.class).deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.objectId, owe.getAcctId()),
				new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.CANCEL_DELAY_STOP));
		
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
