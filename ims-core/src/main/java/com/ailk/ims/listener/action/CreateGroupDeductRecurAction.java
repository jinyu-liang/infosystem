package com.ailk.ims.listener.action;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.util.ImsLogger;

/**
 * @Description: 通知扣费
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tangkun
 * @Date 2012-7-31
 */
public class CreateGroupDeductRecurAction implements IListenerAction {
	private ImsLogger logger = new ImsLogger(this.getClass());
	
	public ImsLogger getLogger() {
		return logger;
	}


	public void setLogger(ImsLogger logger) {
		this.logger = logger;
	}


	public Object doAction(IMSContext context) {
		/*
		List<CoProd> prodList = context.getAllCacheList(CoProd.class);
        if(prodList == null){
        	return null;
        }
        CsdlArrayList<SProdInfo> sPreProd_list = new CsdlArrayList<SProdInfo>(SProdInfo.class);
        BaseProductComponent pc = context.getComponent(BaseProductComponent.class);
        Long groupId = null;
        for(CoProd prod:prodList){
        	SProdInfo prodInfo = pc.packeageProdInfo(prod,null);
        	sPreProd_list.add(prodInfo);
        	if(groupId == null){
        		groupId = prod.getObjectId();
        	}
        }
        Long billAcctId = context.get3hTree().loadGroup3hBean(groupId).getBillAcctId();
        AuthComponent ac = context.getComponent(AuthComponent.class);
        ac.callPreOrderProd(sPreProd_list, groupId, (int)EnumCodeDefine.ACCOUNT_CLASS_GCA, billAcctId, null);
        */
		return null;
	}
}
