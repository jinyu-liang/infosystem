package com.ailk.imssh.alloc.cmp;

import java.util.List;

import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.ims.common.DBCondition;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProdShareAlloc;
import com.ailk.openbilling.persistence.itable.entity.IProdShareAlloc;

/**
 * @Date 2013-12-12
 * @author dingjiang3
 * 
 */
public class ProdShareAllocCmp extends BaseCmp {
	public void createProdShareAlloc(IProdShareAlloc iProdShareAlloc) {
		super.insert(convert(iProdShareAlloc, false));
	}

	public void updateProdShareAlloc(IProdShareAlloc iProdShareAlloc) {
		CoProdShareAlloc coProdShareAlloc = convert(iProdShareAlloc, true);
		this.updateByCondition(coProdShareAlloc, new DBCondition(CoProdShareAlloc.Field.productId, iProdShareAlloc.getProductId()),
				new DBCondition(CoProdShareAlloc.Field.itemId, iProdShareAlloc.getItemId()), new DBCondition(
						CoProdShareAlloc.Field.resourceId, iProdShareAlloc.getUserId()), new DBCondition(CoProdShareAlloc.Field.billFlag,
						iProdShareAlloc.getBillFlag()));
	}

	public void deleteProdShareAlloc(IProdShareAlloc iProdShareAlloc) {
		deleteByCondition(CoProdShareAlloc.class, new DBCondition(CoProdShareAlloc.Field.productId, iProdShareAlloc.getProductId()),
				new DBCondition(CoProdShareAlloc.Field.itemId, iProdShareAlloc.getItemId()), new DBCondition(
						CoProdShareAlloc.Field.resourceId, iProdShareAlloc.getUserId()), new DBCondition(CoProdShareAlloc.Field.billFlag,
						iProdShareAlloc.getBillFlag()));
	}

	public CoProdShareAlloc convert(IProdShareAlloc iProdShareAlloc, boolean isUpdateOper) {
		CoProdShareAlloc cProdShareAlloc = new CoProdShareAlloc();
		cProdShareAlloc.setProductId(iProdShareAlloc.getProductId());
		cProdShareAlloc.setResourceId(iProdShareAlloc.getUserId());
		cProdShareAlloc.setBillFlag(iProdShareAlloc.getBillFlag());
		if (iProdShareAlloc.getItemId() != null) {
			cProdShareAlloc.setItemId(iProdShareAlloc.getItemId());
		} else {
			cProdShareAlloc.setItemId(-1);
		}
		cProdShareAlloc.setAmount(iProdShareAlloc.getAmount());
		if (!isUpdateOper) {
			cProdShareAlloc.setValidDate(iProdShareAlloc.getValidDate());
			cProdShareAlloc.setCreateDate(iProdShareAlloc.getCommitDate());
		}
		cProdShareAlloc.setExpireDate(iProdShareAlloc.getExpireDate());
		cProdShareAlloc.setSoNbr(context.getSoNbr());

		cProdShareAlloc.setSoDate(iProdShareAlloc.getCommitDate());
		cProdShareAlloc.setObjectId(iProdShareAlloc.getObjectId());
		cProdShareAlloc.setObjectType(iProdShareAlloc.getObjectType());
		if (iProdShareAlloc.getMeasureId() != null) {
			cProdShareAlloc.setMeasureId(iProdShareAlloc.getMeasureId());
		} else {
			cProdShareAlloc.setMeasureId(0);
		}
		cProdShareAlloc.setStatus(iProdShareAlloc.getStatus());

		return cProdShareAlloc;

	}

	public void beforeHandle(List<? extends DataObject> dataList) {
		ITableUtil.cleanRequestParamter();
		IProdShareAlloc iProdShareAlloc = (IProdShareAlloc) dataList.get(0);
		ITableUtil.setValue2ContextHolder(null, null, iProdShareAlloc.getObjectId());
		delete_noValid(CoProdShareAlloc.class, new DBCondition(CoProdShareAlloc.Field.objectId, iProdShareAlloc.getObjectId())  ,new DBCondition(CoProdShareAlloc.Field.resourceId, iProdShareAlloc.getUserId()),
				new DBCondition(CoProdShareAlloc.Field.productId, iProdShareAlloc.getProductId()),
				new DBCondition(CoProdShareAlloc.Field.itemId, iProdShareAlloc.getItemId()),new DBCondition(
						CoProdShareAlloc.Field.validDate, iProdShareAlloc.getCommitDate(), Operator.GREAT), new DBCondition(
								CoProdShareAlloc.Field.expireDate, iProdShareAlloc.getCommitDate(), Operator.GREAT));

	}

}
