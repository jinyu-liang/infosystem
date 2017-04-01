package com.ailk.imssh.acct.cmp;

import java.util.ArrayList;
import java.util.List;

import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.acctpayrel.helper.CharValueHelper;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.imssh.prod.cmp.ProdExCmp;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IOweCustomDone;

public class AcctOweDoneCmp extends BaseCmp {

	private CoProd createProd(IOweCustomDone owe) {
		ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
		Integer offeringId = prodCmp.queryOfferingId(SpecCodeDefine.USER_REMIND);
		if (!CommonUtil.isValid(offeringId)) {
			throw IMSUtil.throwBusiException(ErrorCodeExDefine.PRODUCTOFFERINGID_ERROR, SpecCodeDefine.USER_REMIND);
		}

		CoProd dmProd = new CoProd();

		dmProd.setProductId(DBUtil.getSequence(CoProd.class));
		dmProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
		dmProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
		dmProd.setProductOfferingId(offeringId);
		dmProd.setPricingPlanId(0);
		dmProd.setBusiFlag(SpecCodeDefine.USER_REMIND);
		dmProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
		dmProd.setValidDate(owe.getValidDate());
		dmProd.setExpireDate(owe.getExpireDate());
		dmProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
		dmProd.setObjectId(owe.getUserId());
		dmProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
		dmProd.setProdValidDate(owe.getValidDate());
		dmProd.setProdExpireDate(owe.getExpireDate());
		super.insert(dmProd);
		return dmProd;
	}

	public void createOweCustomDone(IOweCustomDone owe) {
		CoProd dmProd = createProd(owe);

		// Long groupId = DBUtil.getSequence();
		List<DataObject> list = new ArrayList<DataObject>();
		CharValueHelper helper = new CharValueHelper(CoProdCharValue.class, dmProd.getProductId(), DBUtil.getSequence(),
				dmProd.getValidDate(), dmProd.getExpireDate(), dmProd.getObjectId(), dmProd.getObjectType());
		list.add(helper.getNew(SpecCodeDefine.USER_REMIND_TYPE, owe.getOweOrder()));
		list.add(helper.getNew(SpecCodeDefine.USER_REMIND_CHANNEL, -1));
		list.add(helper.getNew(SpecCodeDefine.USER_REMIND_FLAG, 3));
		if (owe.getIsStatus() == EnumCodeExDefine.ACCT_OWE_DONE_STS) {
			list.add(helper.getNew(SpecCodeDefine.USER_REMIND_BEGIN, owe.getOweOrder()));
			list.add(helper.getNew(SpecCodeDefine.USER_REMIND_END, owe.getOweOrder()));
		} else {
			list.add(helper.getNew(SpecCodeDefine.USER_REMIND_END, owe.getOweOrder()));
			list.add(helper.getNew(SpecCodeDefine.USER_REMIND_BEGIN, owe.getOweOrder()));
		}

		this.insertBatch(list);

	}

	public void deleteOweCustomDone(IOweCustomDone owe) {
		List<CoProd> list = context.getCmp(ProdExCmp.class).queryProdListByUserId(owe.getUserId(), SpecCodeDefine.USER_REMIND);
		if (CommonUtil.isEmpty(list)) {
			return;
		}
		List<Long> idList = new ArrayList<Long>();
		for (CoProd dmProd : list) {
			idList.add(dmProd.getProductId());
		}

		CoProdCharValue value = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, idList,
				Operator.IN), new DBCondition(CoProdCharValue.Field.objectId, owe.getUserId()), new DBCondition(
				CoProdCharValue.Field.specCharId, SpecCodeDefine.USER_REMIND_TYPE),
				new DBCondition(CoProdCharValue.Field.value, String.valueOf(owe.getOweOrder())));
		if (value == null) {
			return;
		}

		ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
		prodCmp.deleteProdById(value.getProductId(), context.getCommitDate());
		prodCmp.modifyProdValid(value.getProductId(), context.getCommitDate());
		this.deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, value.getProductId()),
				new DBCondition(CoProdCharValue.Field.objectId, owe.getUserId()));
	};

}
