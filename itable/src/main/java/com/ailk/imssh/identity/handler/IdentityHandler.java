package com.ailk.imssh.identity.handler;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.identity.cmp.IdentityCmp;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.itable.entity.IIdentity;

import jef.database.DataObject;

public class IdentityHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr)
			throws IMSException {
			
		List<? extends DataObject> list = dataArr[0];
		if(CommonUtil.isEmpty(list)){
			return;
		}
		IdentityCmp identityCmp = this.getContext().getCmp(IdentityCmp.class);
		RouterCmp cmp=this.getContext().getCmp(RouterCmp.class);
		for(int i=0;i<list.size();i++){
			IIdentity identity = (IIdentity) list.get(i);
			Long acctId=cmp.queryAcctIdByUserId(identity.getUserId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
			switch (identity.getOperType()){
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				identityCmp.insertIdentity(identity);
				break;

			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				identityCmp.updateIdentity(identity);
				break;

			case EnumCodeExDefine.OPER_TYPE_DELETE:
				identityCmp.deleteIdentity(identity);
				break;

			default:
				break;
			}
		}
		
		
	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.ailk.imssh.common.handler.BaseHandler#handleDiffException(java.util.List[])
	 */
	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		List<IIdentity> dataList = (List<IIdentity>)paramArr[0];
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		if(CommonUtil.isEmpty(dataList)){
			return;
		}
		IdentityCmp identityCmp = this.getContext().getCmp(IdentityCmp.class);
		for (IIdentity iIdentity : dataList) {
			if(EnumCodeExDefine.OPER_TYPE_DIFF != iIdentity.getOperType()){
				continue;
			}
			   baseCmp.checkUserRouter(iIdentity.getUserId());
				ITableUtil.setValue2ContextHolder(null, null, iIdentity.getUserId());
				identityCmp.deleteByConditionForDiff(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId, iIdentity.getUserId()));
				ITableUtil.setValue2ContextHolder(null, null, iIdentity.getUserId());
				identityCmp.insertIdentity(iIdentity);
		}
	}
 }
