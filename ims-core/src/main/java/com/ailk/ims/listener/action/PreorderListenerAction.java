package com.ailk.ims.listener.action;

import com.ailk.ims.common.IMSContext;

public class PreorderListenerAction implements IListenerAction {
	
	public Object doAction(IMSContext context) {
		/*
		Map<Long, CsdlArrayList<SProdInfo>> sPreProd_map = ( Map<Long, CsdlArrayList<SProdInfo>>)context.getExtendParam(ConstantDefine.PREORDER_PRODUCT_LIST_MAP);
        Map<Long, Integer> idTypeMap = (Map<Long, Integer> )context.getExtendParam(ConstantDefine.PREORDER_PRODUCT_OBJECT_TYPE_MAP);
        Map<Long, Long> payAcctIdMap = (  Map<Long, Long> )context.getExtendParam(ConstantDefine.PREORDER_PRODUCT_PAY_ACCT_MAP);
        
        Iterator proddPreObjIdIt = sPreProd_map.keySet().iterator();
        AuthComponent ac = context.getComponent(AuthComponent.class);
        while (proddPreObjIdIt.hasNext())
        {
            Long prodObjId = (Long) proddPreObjIdIt.next();
            CsdlArrayList<SProdInfo> sPreProd_list = sPreProd_map.get(prodObjId);

            if (sPreProd_list.size() != 0)
            {
                // 鉴权
                imsLogger.info("Notify to deduct recurring fee ...");
                ac.callPreOrderProd(sPreProd_list, prodObjId, idTypeMap.get(prodObjId), payAcctIdMap.get(prodObjId), null);
            }
        }
        return null;
		 */
		return null;
	}
}
