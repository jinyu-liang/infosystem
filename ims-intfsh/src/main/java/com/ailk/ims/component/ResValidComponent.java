package com.ailk.ims.component;

import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.imscnsh.entity.SMonthlyFee;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;

/**
 * @description:有效期组件
 * @author caohm5
 * @date:2012-11-24
 *
 */
public class ResValidComponent extends BaseComponent {
	
	public ResValidComponent(){
	}
	/**
	 * 
	 * @param userId:有户编号
	 * @param phoneId：手机号码
	 * @param isCancle：是取消有效期还是激活有效期；true为取消，false为激活
	 * @return
	 */
	public Integer checkIsCancleResVlid(Long userId,String phoneId,Boolean isCancle,Check3HParamReturn bean){
		Integer flag=null;
		Integer realNameSts = null;
		//条件一、查询实名制
		Boolean isNotRealName=false;
    	CaAccount account = context.getComponent(AccountQuery.class).queryAccountById(bean.getAcctId());
 		if(account == null){
 		    IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_NOTEXIST,bean.getAcctId());
 		}
		CmPartyIdentity partyIdentity = context.getComponent(PartyComponent.class).queryPartyByIdentity(account.getCustId());
    	if(partyIdentity!=null){
			realNameSts = partyIdentity.getRealNameSts();
			if(realNameSts==null ||realNameSts==0){
				isNotRealName=true;
			}
    	}
    	if(isNotRealName){
    		flag=EnumCodeShDefine.CM_VALIDITY_NO_REAL_NAME_STS;
    		return flag;
    	}
		//条件二、是否设置了有效期：
		//1、取消，数据库里面没有值或者值不等于有效，认为是没有设置
		//2、恢复，数据库里面没有值或者值不等于失效，认为是没有设置
		Boolean isResVidNull=false;
		CmResValidity res = context.getComponent(BaseLifeCycleComponent.class).queryUserValidity4Sh(bean.getUserId());
		if(res==null||res.getEffectFlag()==null){
			isResVidNull=true;
		}else{
			if(isCancle){
				//取消
				if(res.getEffectFlag()!=EnumCodeShDefine.CM_VALIDITY_EFFECTIVE){
					isResVidNull=true;
				}
			}else{
				//恢复
				if(res.getEffectFlag()!=EnumCodeShDefine.CM_VALIDITY_IN_VALID){
					isResVidNull=true;
				}
			}
		}
		if(isResVidNull){
			flag=EnumCodeShDefine.CM_VALIDITY_NULL;
    		return flag;
		}
		//条件三、欠费停不能设置有效期
		Boolean isOwe=false;
		CmResLifecycle life=bean.getResLifeCycle();
		if(life!=null&&CommonUtilSh.check(life,EnumCodeShDefine.STS_DTL_TWENTY - 1)){
			isOwe=true;
		}
		if(isOwe){
			flag=EnumCodeShDefine.CM_VALIDITY_OWE_BILL_STOP;
    		return flag;
		}
		//条件四、全球通有有效期不可办理
		Boolean isGloble=false;
		CmResource resource = context.getComponent(UserQuery.class).queryUserByUserID(bean.getUserId());
		if(resource == null){
 		    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_USER_NOTEXIST,bean.getUserId());
 		}
		Integer brandId=resource.getBrandId();
		if(brandId==EnumCodeShDefine.BRAND_ID_GLOBLE){
			isGloble=true;
		}
		if(isGloble){
			flag=EnumCodeShDefine.CM_VALIDITY_BRAND_IS_GLOBLE;
    		return flag;
		}
		//条件五、家庭活动
//		Boolean isFimilyActivity=false;
		List<CaResGrpRevs> revsList = context.getComponent(GroupShQuery.class).queryResGrpRevs(bean.getUserId());
//		if(revsList!=null&&!revsList.isEmpty()){
//			for(CaResGrpRevs revs:revsList){
//				//清除路由
//				IMSUtil.removeRouterParam();
//				IMSUtil.setAcctRouterParam(revs.getAcctId());
//				CaAccountGroup accountGroup = context.getComponent(BaseComponent.class).
//				querySingle(CaAccountGroup.class, 
//						new DBCondition(CaAccountGroup.Field.accountType,EnumCodeShDefine.FIMILY_ACTIVITY), 
//						new DBCondition(CaAccountGroup.Field.acctId,revs.getAcctId()));
//				IMSUtil.removeRouterParam();
//				IMSUtil.setAcctRouterParam(revs.getAcctId());
//				if (accountGroup != null){
//					isFimilyActivity=true;
//					break;
//				}
//			}
//		}
//		if(isFimilyActivity){
//			flag=EnumCodeShDefine.CM_VALIDITY_PARTY_FIMILY_ACTIVITY;
//    		return flag;
//		}
		//条件六、家庭统付副号码不可办理;只有isFamilySub==true&&isTongfu==true  =====>isTongfuAndFaS==true;
		Boolean isTongfu=false;
		Boolean isFamilySub=false;
		Boolean isTongfuAndFaS=false;
		if(revsList!=null&&!revsList.isEmpty()){
			for(CaResGrpRevs revs:revsList){
				//清除路由
				IMSUtil.removeRouterParam();
				IMSUtil.setAcctRouterParam(revs.getAcctId());
				CaAccountGroup accountGroup = context.getComponent(BaseComponent.class).
				querySingle(CaAccountGroup.class, 
						new DBCondition(CaAccountGroup.Field.accountType,EnumCodeShDefine.FIMILY_ACTIVITY), 
						new DBCondition(CaAccountGroup.Field.acctId,revs.getAcctId()));
				if (accountGroup != null){
					//家庭副号码
					CaAccountRv rv=context.getComponent(BaseComponent.class).querySingle(CaAccountRv.class, 
							new DBCondition(CaAccountRv.Field.acctId,accountGroup.getAcctId()),
							new DBCondition(CaAccountRv.Field.resourceId,bean.getUserId()),
							new DBCondition(CaAccountRv.Field.titleRoleId,EnumCodeShDefine.TITLE_ROLE_ID_42));
					if(rv==null){
						continue;
					}
					isFamilySub=true;
					//统付
					IMSUtil.removeRouterParam();
					IMSUtil.setAcctRouterParam(bean.getAcctId());
					List<CoSplitPayRel> coSplitPayRelList=context.getComponent(BaseComponent.class).query(CoSplitPayRel.class,
							new DBCondition(CoSplitPayRel.Field.objectId,bean.getUserId()),
							new DBCondition(CoSplitPayRel.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_DEV),
							new DBCondition(CoSplitPayRel.Field.reguidObjectId,accountGroup.getAcctId()),
							new DBCondition(CoSplitPayRel.Field.reguidObjectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
					IMSUtil.removeRouterParam();
					IMSUtil.setAcctRouterParam(revs.getAcctId());
					if(coSplitPayRelList!=null&&!coSplitPayRelList.isEmpty()){
						for(CoSplitPayRel rel:coSplitPayRelList){
							Long productId=rel.getProductId();
							CoSplitCharValue cv=context.getComponent(BaseComponent.class).querySingle(CoSplitCharValue.class, 
									new DBCondition(CoSplitCharValue.Field.objectId,accountGroup.getAcctId()),
									new DBCondition(CoSplitCharValue.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT),
									new DBCondition(CoSplitCharValue.Field.productId,productId),
									new DBCondition(CoSplitCharValue.Field.specCharId,12815),
									new DBCondition(CoSplitCharValue.Field.value,1));
							if(cv!=null){
								isTongfu=true;
								break;
							}
						}
					}
					if(isFamilySub&&isTongfu){
						isTongfuAndFaS=true;
						break;
					}
				}
				isTongfu=false;
				isFamilySub=false;
			}
		}
		if(isTongfuAndFaS){
			flag=EnumCodeShDefine.CM_VALIDITY_FIMILY_NOT_MAIN;
    		return flag;
		}
		IMSUtil.removeRouterParam();
		IMSUtil.setAcctRouterParam(bean.getAcctId());
		//条件七、预付费随e 行品牌的号码带有月租费的产品或功能 不能申请取消有效期
		//pr.getProductOfferingId() = 20000710 ismain =1 
		//把用户下所有的产品找出来 逐个查月租费
		Boolean isENumber=false;
		Integer mainProdutOffering=context.getComponent(ProductQuery.class).queryMainOfferIdByUserId(bean.getUserId());
		if(mainProdutOffering!=null&&mainProdutOffering==EnumCodeShDefine.E_PRODUCT_OFFERING_ID){
			List<CoProd> coProdList=context.getComponent(ProductQuery.class).queryProds(bean.getUserId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV);
			if(coProdList!=null&&!coProdList.isEmpty()){
				for(CoProd coProdTmp : coProdList){
					 List<SMonthlyFee> monthlyFeeList = context.getComponent(BalanceShComponent.class).
					 		queryMonthlyFee(coProdTmp.getPricingPlanId(), context.getRequestDate(), 0);
					if(monthlyFeeList!=null&&!monthlyFeeList.isEmpty()){
						isENumber=true;
						break;
					}
				}
			}
		}
		if(isENumber){
			flag=EnumCodeShDefine.CM_VALIDITY_E_PRICE;
    		return flag;
		}
		if(realNameSts == 1){
		    flag = 1;
		    return flag;
		}
		return flag;
	}
}
