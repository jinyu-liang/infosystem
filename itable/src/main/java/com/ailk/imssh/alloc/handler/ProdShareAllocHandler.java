package com.ailk.imssh.alloc.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.alloc.cmp.ProdShareAllocCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.itable.entity.IProdShareAlloc;

/**
 * @Description
 * @author dingjiang3
 * @Date 2013-12-12
 */
public class ProdShareAllocHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<? extends DataObject> list = dataArr[0];
		ProdShareAllocCmp cmp = this.getContext().getCmp(ProdShareAllocCmp.class);
		cmp.beforeHandle(list);
		for (int index = 0; index < list.size(); index++) {
			IProdShareAlloc iProdShareAlloc = (IProdShareAlloc) list.get(index);
			if (iProdShareAlloc.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT) {
				ITableUtil.setValue2ContextHolder(null, iProdShareAlloc.getObjectId(), null);
			} else {
				ITableUtil.setValue2ContextHolder(null, null, iProdShareAlloc.getObjectId());
			}
			switch (iProdShareAlloc.getOperType().intValue()) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				cmp.createProdShareAlloc(iProdShareAlloc);
				break;

			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				cmp.updateProdShareAlloc(iProdShareAlloc);
				break;

			case EnumCodeExDefine.OPER_TYPE_DELETE:
				cmp.deleteProdShareAlloc(iProdShareAlloc);
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

	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
