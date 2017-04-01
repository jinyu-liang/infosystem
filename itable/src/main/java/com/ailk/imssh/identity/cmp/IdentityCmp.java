package com.ailk.imssh.identity.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jef.database.DataObject;
import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.itable.entity.IIdentity;
import com.ailk.openbilling.persistence.itable.entity.IUser;

public class IdentityCmp extends BaseCmp {
	
	 public List<CmResIdentity> beforeHandle(List<? extends DataObject> dataList)
	    {


	        // 过户时索引中的账户为过户下家，这样就会错误的从新的分表中查询，所以先清一把SESSION中的分表参数
	        ITableUtil.cleanRequestParamter();

	        // 将用户相关的三张表中未来生效数据置为失效
	        IIdentity identity = (IIdentity) dataList.get(0);
	        // @Date 2012-12-27 wukl 增加这层路由查询判断，是为了避免开户时用户路由没发布导致的，下面三个操作的全表扫描
	        try
	        {
	            boolean isExist = context.getCmp(RouterCmp.class).isUserRouted(identity.getUserId());
	            if (!isExist)
	            {
	                return null;
	            }
	        }
	        catch (Exception e)
	        {
	            // 此处抛异常属于正常，不用处理
	            return null;
	        }
	        List<CmResIdentity> idenList = this.query(CmResIdentity.class,
	                new DBCondition(CmResIdentity.Field.resourceId, identity.getUserId()), new DBCondition(
	                        CmResIdentity.Field.identityAttr, EnumCodeExDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER));

	        Date expireDate = context.getCommitDate();

	        delete_noValid(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId, identity.getUserId()), new DBCondition(
	                CmResIdentity.Field.validDate, expireDate, Operator.GREAT), new DBCondition(CmResIdentity.Field.expireDate,
	                expireDate, Operator.GREAT));

	        return idenList;
	    }
	
	private CmResIdentity createIdentity(IIdentity identity, boolean isUpdate){
		CmResIdentity resIdentity = new CmResIdentity();
		resIdentity.setIdentity(identity.getPhoneId());
		resIdentity.setIdentityType(0);
		resIdentity.setIdentityAttr(0);
		resIdentity.setExpireDate(identity.getExpireDate());
		resIdentity.setSoNbr(context.getSoNbr());
		resIdentity.setCreateDate(identity.getValidDate());

		if(!isUpdate){
			resIdentity.setResourceId(identity.getUserId());
			resIdentity.setValidDate(identity.getValidDate());

		}
		
		resIdentity.setSoDate(identity.getCommitDate());
		resIdentity.setRelIdentity(identity.getImsi());
		
		return resIdentity;
	}
	
	public void insertIdentity(IIdentity identity){
		CmResIdentity resIdentity = createIdentity(identity,false);
		RouterCmp routCmp = context.getCmp(RouterCmp.class);
		String phoneId =resIdentity.getIdentity();
		String imsi =resIdentity.getRelIdentity();
		
		routCmp.createUserRouter(resIdentity.getResourceId(), null,phoneId, imsi,
				resIdentity.getValidDate(), resIdentity.getExpireDate());
		this.insert(resIdentity);
	}
	
	public void updateIdentity(IIdentity identity){
		CmResIdentity resIdentity = createIdentity(identity,true);
		List<CmResIdentity> idenList = this.query(CmResIdentity.class,
	                new DBCondition(CmResIdentity.Field.resourceId, identity.getUserId()), new DBCondition(
	                        CmResIdentity.Field.identityAttr, EnumCodeExDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER));
		 
		 
		CmResIdentity oldIdentity = null;
		if (CommonUtil.isNotEmpty(idenList)){
			oldIdentity = ITableUtil.getLastDataObjectByExpireDate(idenList);
	    }
		 
		 if (oldIdentity != null
	                && (!resIdentity.getIdentity().equals(oldIdentity.getIdentity()) ||  !oldIdentity
	                        .getExpireDate().equals(resIdentity.getExpireDate()))){
			 // 只发布手机号码与账户的关系，要不然过户时带有换号业务，提前更新路由导致后面业务出错
	            RouterCmp routerCmp = context.getCmp(RouterCmp.class);
	            // 换卡换号，先删除原来号码路由，再发布新号码路由
	            imsLogger
	                    .debug("********change phone or imsi or expire_date,delete old phone or imsi route,publish new phone or imsi route");
	            String phoneId = oldIdentity.getIdentity();
	            String imsi = oldIdentity.getRelIdentity();
	            String newPhoneId = resIdentity.getIdentity();
	            String newImsi = resIdentity.getRelIdentity();
	            
	            routerCmp.deleteRouterWithExpire(phoneId, imsi, context.getCommitDate());
	            routerCmp.createUserRouter(resIdentity.getResourceId(), null, newPhoneId, newImsi, context.getCommitDate(), resIdentity.getExpireDate());
		 }
		 this.updateMode2(resIdentity,EnumCodeExDefine.OPER_TYPE_UPDATE,identity.getValidDate(),
				 new DBCondition(CmResIdentity.Field.resourceId, identity.getUserId()));
		
	}
	
	public void deleteIdentity(IIdentity identity){
		CmResIdentity resIdentity =new CmResIdentity();
		resIdentity.setExpireDate(identity.getExpireDate());
		 this.updateMode2(resIdentity,EnumCodeExDefine.OPER_TYPE_DELETE,identity.getValidDate(),
				 new DBCondition(CmResIdentity.Field.resourceId, identity.getUserId()));
		
		RouterCmp routerCmp = context.getCmp(RouterCmp.class);
		routerCmp.deleteRouterWithExpire(identity.getPhoneId(), identity.getImsi(), context.getCommitDate());
	}

}
