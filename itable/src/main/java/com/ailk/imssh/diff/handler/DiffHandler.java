package com.ailk.imssh.diff.handler;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.flow.scandeal.deal.BaseDeal;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.order.cmp.UserOrderCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustGroup;
import com.ailk.openbilling.persistence.cust.entity.CmCustVip;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResService;
import com.ailk.openbilling.persistence.cust.entity.CmResServiceStatus;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CmResourceMonitor;
import com.ailk.openbilling.persistence.cust.entity.CmRouteIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.cust.entity.CmUserImpu;
import com.ailk.openbilling.persistence.cust.entity.CmUserMap;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrder;
import com.ailk.openbilling.persistence.cust.entity.CmUserParam;
import com.ailk.openbilling.persistence.cust.entity.CmUserRelation;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareProd;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.IIsmpOper;
import com.ailk.openbilling.persistence.itable.entity.IContact;

import jef.database.Condition.Operator;
import jef.database.DataObject;

public class DiffHandler extends BaseHandler{
	protected String[] dbkey = {"account_db11","account_db21"};
	private String specialTableName="CD.CM_ROUTE_IDENTITY";


	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		



	}
	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) throws IMSException {
	    List<Long> productOfferingIdList=new ArrayList<Long>();
	    List<Long> specCharIdlist=new ArrayList<Long>();
		productOfferingIdList.add(468100001l);
		productOfferingIdList.add(468100000l);
		for (int i = 486000002; i <= 486000072; i++) {
			productOfferingIdList.add(Long.valueOf(486000002+""));
		}
		specCharIdlist.add(13100l);
		specCharIdlist.add(13101l);
		specCharIdlist.add(13102l);
		specCharIdlist.add(13103l);
		specCharIdlist.add(13104l);
		specCharIdlist.add(13105l);
//		 List<Long> busiTypelist=new ArrayList<Long>();
//			busiTypelist.add(15l);
//			busiTypelist.add(16l);
//			busiTypelist.add(29l);
//			busiTypelist.add(21l);
		
		 RouterCmp baseCmp = context.getCmp(RouterCmp.class);
     	 Long acctId=baseCmp.queryAcctIdByUserId(context.getUserId());
    	 ITableUtil.setValue2ContextHolder(null, acctId, null);
//		Long acctId=baseCmp.queryAcctIdByUserId(context.getUserId());
//		ITableUtil.setValue2ContextHolder(null, null, context.getUserId());
        DBUtil.deleteByCondition(CaAccountGroup.class, new DBCondition(CaAccountGroup.Field.acctId,context.getUserId()));
        
       // DBUtil.deleteByCondition(CaAccountRel.class, new DBCondition(CaAccountRel.Field.groupId,context.getUserId()));
        //
//        DBUtil.deleteByCondition(CaAccountRv.class, new DBCondition(CaAccountRv.Field.resourceId,context.getUserId()));
//        DBUtil.deleteByCondition(CaResGrpRevs.class, new DBCondition(CaResGrpRevs.Field.resourceId,context.getUserId()));
        //
        DBUtil.deleteByCondition(CmContactMedium.class, new DBCondition(CmContactMedium.Field.objectId,context.getUserId()));
       // DBUtil.deleteByCondition(CmCustGroup.class, new DBCondition(CmCustGroup.Field.resourceId,context.getUserId()));
        baseCmp.deleteByDbKey(CmRouteIdentity.class, dbkey[0], "CD.CM_CUST_GROUP", 
        		new DBCondition(CmRouteIdentity.Field.resourceId,context.getUserId()));
        baseCmp.deleteByDbKey(CmRouteIdentity.class, dbkey[1], "CD.CM_CUST_GROUP", 
        		new DBCondition(CmRouteIdentity.Field.resourceId,context.getUserId()));
        
        DBUtil.deleteByCondition(CmCustVip.class, new DBCondition(CmCustVip.Field.resourceId,context.getUserId()));
        //DBUtil.deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId,context.getUserId()));
        DBUtil.deleteByCondition(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId,context.getUserId()));
        DBUtil.deleteByCondition(CmResService.class, new DBCondition(CmResService.Field.resourceId,context.getUserId()));
        DBUtil.deleteByCondition(CmResServiceStatus.class, new DBCondition(CmResServiceStatus.Field.resourceId,context.getUserId()));
        DBUtil.deleteByCondition(CmResource.class, new DBCondition(CmResource.Field.resourceId,context.getUserId()));
        DBUtil.deleteByCondition(CmResourceMonitor.class, new DBCondition(CmResourceMonitor.Field.resourceId,context.getUserId()));
       // DBUtil.deleteByCondition(CmUserEnterprise.class, new DBCondition(CmUserEnterprise.Field.resourceId,context.getUserId()));
        DBUtil.deleteByCondition(CmUserImpu.class, new DBCondition(CmUserImpu.Field.resourceId,context.getUserId()));
       // DBUtil.deleteByCondition(CmUserMap.class, new DBCondition(CmUserMap.Field.resourceId,context.getUserId()));
        DBUtil.deleteByCondition(CmUserOrder.class, new DBCondition(CmUserOrder.Field.resourceId,context.getUserId()));
        DBUtil.deleteByCondition(IIsmpOper.class, new DBCondition(IIsmpOper.Field.ownerId, context.getUserId()));
        DBUtil.deleteByCondition(CmUserParam.class, new DBCondition(CmUserParam.Field.resourceId,context.getUserId()));
      
        DBUtil.deleteByCondition(CmUserShareProd.class, new DBCondition(CmUserShareProd.Field.resourceId,context.getUserId()));
        //
//    	DBUtil.deleteByCondition(CmUserRelation.class, new DBCondition(CmUserRelation.Field.rResourceId,context.getUserId()),
//    		    new DBCondition(CmUserRelation.Field.busiType, busiTypelist,Operator.NOT_IN));
        DBUtil.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.objectId,context.getUserId()),
        		 new DBCondition(CoProd.Field.productOfferingId,productOfferingIdList,Operator.NOT_IN));
        DBUtil.deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.objectId,context.getUserId()),
        		new DBCondition(CoProdCharValue.Field.specCharId,specCharIdlist, Operator.NOT_IN));
        //
        DBUtil.deleteByCondition(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.objectId,context.getUserId()),
        		new DBCondition(CoProdPriceParam.Field.paramId, 810301,Operator.NOT_EQUALS));
        //DBUtil.deleteByCondition(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.objectId,context.getUserId()));
        //DBUtil.deleteByCondition(CmRouteIdentity.class, new DBCondition(CmRouteIdentity.Field.resourceId,context.getUserId()));
        baseCmp.deleteByDbKey(CmRouteIdentity.class, dbkey[0], specialTableName, 
        		new DBCondition(CmRouteIdentity.Field.resourceId,context.getUserId()),
        		new DBCondition(CmRouteIdentity.Field.partitionId,context.getUserId()%10000));
        baseCmp.deleteByDbKey(CmRouteIdentity.class, dbkey[1], specialTableName, 
        		new DBCondition(CmRouteIdentity.Field.resourceId,context.getUserId()),
        		new DBCondition(CmRouteIdentity.Field.partitionId,context.getUserId()%10000));

        //DBUtil.deleteByDbKey(CmRouteIdentity.class, dbkey, specialTableName, new DBCondition(CmRouteIdentity.Field.resourceId,context.getUserId()));

        //        DBUtil.d(cmRouteIdentity, dbkey[0], specialTableName);
//    	DBUtil.insertWithName(cmRouteIdentity, dbkey[1], specialTableName);
	}

}
