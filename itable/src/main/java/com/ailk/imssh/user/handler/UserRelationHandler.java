package com.ailk.imssh.user.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;
import jef.tools.StringUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CmUserMap;
import com.ailk.openbilling.persistence.cust.entity.CmUserRelation;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.itable.entity.IUserRelation;
import com.ailk.openbilling.persistence.jd.entity.ImsUserCtt;

/**
 * @Description 亲情号码相关操作方法
 * @author lijc3
 * @Date 2012-5-17
 * @Date 2012-08-01 zenglu 用户关系类型增加铁通上网帐号、固网电话枚举值
 */
public class UserRelationHandler extends BaseHandler {

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		List<IUserRelation> userRelaList = (List<IUserRelation>) dataArr[0];
		Long userId = context.getUserId();
		Date validDate = DateUtils.monthBegin(context.getCommitDate());
		Set<Long> prodIdList = new HashSet<Long>();
		boolean isFriendNumber = false;
		boolean isFixedNo = false;
		boolean isNetAcctId = false;
		boolean isNetViceNumber = false;
		boolean isEasyChange = false;
		if (CommonUtil.isNotEmpty(userRelaList)) {
			for (IUserRelation userRel : userRelaList) {
				if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_FRIEND_NUMBER) {
					prodIdList.add(userRel.getProductId());
					isFriendNumber = true;
				} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_RAILCOM_FIXED_NO) {
					isFixedNo = true;
				} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_RAILCOM_NET_ACCTID) {
					isNetAcctId = true;
				} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_NET_PARTNER_VICE_NUMBER) {
					isNetViceNumber = true;
				} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_EASY_CHANGE_VICE_NUMBER) {
					isEasyChange = true;
				}
				if (userRel.getValidDate().before(validDate)) {
					userRel.setValidDate(validDate);
				}
			}
		} else {
			return;
		}
		baseCmp = getContext().getCmp(RouterCmp.class);
		ITableUtil.setValue2ContextHolder(context.getCustId(), context.getAcctId(), context.getUserId());
		if (isFriendNumber) {
			baseCmp.deleteByCondition_noInsert(CoFnCharValue.class, validDate, new DBCondition(CoFnCharValue.Field.productId, prodIdList,
					Operator.IN), new DBCondition(CoFnCharValue.Field.objectId, userId), new DBCondition(CoFnCharValue.Field.expireDate,
					validDate, Operator.GREAT));
		}
		if (isFixedNo) {
			// TODO 全量同步还存在问题，要考虑是否加对端号码
			baseCmp.deleteByCondition_noInsert(ImsUserCtt.class, validDate, new DBCondition(ImsUserCtt.Field.resourceId, userId),
					new DBCondition(ImsUserCtt.Field.identityType, EnumCodeExDefine.RESOURCE_IDENTITYTYPE_RAILCOM_FIXED_NO),
					new DBCondition(ImsUserCtt.Field.expireDate, validDate, Operator.GREAT));
		}
		if (isNetAcctId) {
			// TODO 全量同步还存在问题，要考虑是否加对端号码
			baseCmp.deleteByCondition_noInsert(ImsUserCtt.class, validDate, new DBCondition(ImsUserCtt.Field.resourceId, userId),
					new DBCondition(ImsUserCtt.Field.identityType, EnumCodeExDefine.RESOURCE_IDENTITYTYPE_RAILCOM_NET_ACCTID),
					new DBCondition(ImsUserCtt.Field.expireDate, validDate, Operator.GREAT));
		}
		if (isNetViceNumber) {
			baseCmp.deleteByCondition_noInsert(CmResIdentity.class, validDate, new DBCondition(CmResIdentity.Field.resourceId, userId),
					new DBCondition(CmResIdentity.Field.identityAttr, EnumCodeExDefine.RESOURCE_IDENTITYATTR_NET_PARTNER_VICE_NUMBER),
					new DBCondition(ImsUserCtt.Field.expireDate, validDate, Operator.GREAT));
		}
		if (isEasyChange) {
			baseCmp.deleteByCondition_noInsert(CmResIdentity.class, validDate, new DBCondition(CmResIdentity.Field.resourceId, userId),
					new DBCondition(CmResIdentity.Field.identityAttr, EnumCodeExDefine.RESOURCE_IDENTITYATTR_EASY_CHANGE_VICE_NUMBER),
					new DBCondition(ImsUserCtt.Field.expireDate, validDate, Operator.GREAT));
		}
	}

	private ProdCmp prodCmp = null;
	private RouterCmp baseCmp = null;

	@Override
	public void handle(List<? extends DataObject>... dataArr) {
		prodCmp = getContext().getCmp(ProdCmp.class);
		baseCmp = getContext().getCmp(RouterCmp.class);
		List<IUserRelation> userRels = (List<IUserRelation>) dataArr[0];
		Map<Long, List<Long>> userRelMap = new HashMap<Long, List<Long>>();
		
		for (IUserRelation userRel : userRels) {
			Long acctId=baseCmp.queryAcctIdByUserId(userRel.getUserId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
			int operType = userRel.getOperType();
			if (operType == EnumCodeExDefine.OPER_TYPE_INSERT) {
				createUserRelation(userRel);
			} else if (operType == EnumCodeExDefine.OPER_TYPE_DELETE) {
				deleteUserRelation(userRel);
			} else if (operType == EnumCodeExDefine.OPER_TYPE_UPDATE) {
				updateUserRelation(userRel);
			}
			addToRelMap(userRel, userRelMap);
		}

		if (CommonUtil.isNotEmpty(userRelMap)) {
			baseCmp.publicUserUserRelRoute(userRelMap);
		}
	}

	private void addToRelMap(IUserRelation userRel, Map<Long, List<Long>> userRelMap) {
		if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_MANY_TERM || userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_4G_SHARE) {
			List<Long> relUserList = userRelMap.get(userRel.getUserId());
			if (CommonUtil.isEmpty(relUserList)) {
				relUserList = new ArrayList<Long>();
				userRelMap.put(userRel.getUserId(), relUserList);
			}
			relUserList.add(CommonUtil.string2Long(userRel.getOppAttr()));
		}
	}

	/*
	 * 创建用户关系 zenglu 2012-08-01
	 */
	private void createUserRelation(IUserRelation userRel) {
		/**
		if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_FRIEND_NUMBER) {
			createFriendNumber(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_RAILCOM_FIXED_NO) {
			createFixedPhone(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_RAILCOM_NET_ACCTID) {
			createNetAccount(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_NET_PARTNER_VICE_NUMBER
				|| userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_EASY_CHANGE_VICE_NUMBER) {
			createViceNumber(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_ONE_PAY_NUMBER ){
			createRelationNumber(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_MASO) {
			createMasoRel(userRel);
		}
		*/
		
			createRelationNumber(userRel);
		
		
	}

	/*
	 * 删除用户关系 zenglu 2012-08-01
	 */
	private void deleteUserRelation(IUserRelation userRel) {
		/**
		if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_FRIEND_NUMBER) {
			deleteFriendNumber(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_RAILCOM_FIXED_NO) {
			deleteFixedPhone(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_RAILCOM_NET_ACCTID) {
			deleteNetAccount(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_NET_PARTNER_VICE_NUMBER
				|| userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_EASY_CHANGE_VICE_NUMBER) {
			deleteViceNumber(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_ONE_PAY_NUMBER ){
			deleteRelationNumber(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_MASO) {
			deleteMasoRel(userRel);
		}
		*/
	
			
			deleteRelationNumber(userRel);
		
	}

	/*
	 * 修改用户关系 zenglu 2012-08-01
	 */
	private void updateUserRelation(IUserRelation userRel) {
		/**
		if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_RAILCOM_FIXED_NO) {
			updateFixedPhone(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_RAILCOM_NET_ACCTID) {
			updateNetAccount(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_NET_PARTNER_VICE_NUMBER
				|| userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_EASY_CHANGE_VICE_NUMBER) {
			updateViceNumber(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_ONE_PAY_NUMBER ){
			updateRelationNumber(userRel);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_MASO) {
			updateMasoRel(userRel);
		}
		*/
	
			
		updateRelationNumber(userRel);
		
	}
	
	public void createUserMap(IUserRelation userRel){
		CmUserMap cmUserMap = new CmUserMap();
		cmUserMap.setResourceId(userRel.getUserId());
		cmUserMap.setMsisdn(userRel.getOppNumber());
		cmUserMap.setSoNbr(context.getSoNbr());
		cmUserMap.setValidDate(userRel.getValidDate());
		cmUserMap.setExpireDate(userRel.getExpireDate());
		cmUserMap.setBusiType(userRel.getRelationType());
		cmUserMap.setSoDate(userRel.getCommitDate());
		cmUserMap.setRegionCode(EnumCodeExDefine.TJ_REGION_CODE);
		baseCmp.insert(cmUserMap);
	}
	
	public void updateUserMap(IUserRelation userRel){
		CmUserMap cmUserMap = new CmUserMap();
		cmUserMap.setResourceId(userRel.getUserId());
		cmUserMap.setMsisdn(userRel.getOppNumber());
		cmUserMap.setSoNbr(context.getSoNbr());
		cmUserMap.setValidDate(userRel.getValidDate());
		cmUserMap.setExpireDate(userRel.getExpireDate());
		cmUserMap.setBusiType(userRel.getRelationType());
		cmUserMap.setSoDate(userRel.getCommitDate());
		baseCmp.updateByCondition(cmUserMap, new DBCondition(CmUserMap.Field.resourceId, userRel.getUserId()), 
				new DBCondition(CmUserMap.Field.msisdn, userRel.getOppNumber()));
	}
	
	
	public void deleteUserMap(IUserRelation userRel){
		baseCmp.deleteByCondition(CmUserMap.class, new DBCondition(CmUserMap.Field.resourceId, userRel.getUserId()), 
				new DBCondition(CmUserMap.Field.msisdn, userRel.getOppNumber()));
	}
	/**
	 * 增加铁通固定电话的资源标识 zenglu 2012-08-01
	 */
	private void createFixedPhone(IUserRelation userRel) {
		ImsUserCtt imsUserCtt = new ImsUserCtt();
		imsUserCtt.setResourceId(userRel.getUserId());
		imsUserCtt.setIdentity(userRel.getOppNumber());
		imsUserCtt.setIdentityType(EnumCodeExDefine.RESOURCE_IDENTITYTYPE_RAILCOM_FIXED_NO);
		imsUserCtt.setCreateDate(userRel.getCommitDate());
		imsUserCtt.setValidDate(userRel.getValidDate());
		imsUserCtt.setExpireDate(userRel.getExpireDate());
		imsUserCtt.setSoDate(userRel.getCommitDate());
		imsUserCtt.setSoNbr(context.getSoNbr());
		baseCmp.insert(imsUserCtt);
	}

	/**
	 * 增加铁通上网账户的资源标识 zenglu 2012-08-01
	 */
	private void createNetAccount(IUserRelation userRel) {
		ImsUserCtt imsUserCtt = new ImsUserCtt();
		imsUserCtt.setResourceId(userRel.getUserId());
		imsUserCtt.setIdentity(userRel.getOppNumber());
		imsUserCtt.setIdentityType(EnumCodeExDefine.RESOURCE_IDENTITYTYPE_RAILCOM_NET_ACCTID);
		imsUserCtt.setCreateDate(userRel.getCommitDate());
		imsUserCtt.setValidDate(userRel.getValidDate());
		imsUserCtt.setExpireDate(userRel.getExpireDate());
		imsUserCtt.setSoDate(userRel.getCommitDate());
		imsUserCtt.setSoNbr(context.getSoNbr());
		baseCmp.insert(imsUserCtt);
	}

	/**
	 * 同步副号码 zenglu 2012-09-25 TODO 2012-10-29 #62685 gaoqc5 XM_RES_IDENTITY
	 * 用户号码和IMSI 修改为横表存储方式
	 */
	private void createViceNumber(IUserRelation userRel) {
		if(StringUtils.isNotBlank(userRel.getOppAttr())){
			userRel.setOppAttr(userRel.getOppAttr().replaceAll("^(0+)", ""));
    	}
		if(StringUtils.isNotBlank(userRel.getOppNumber())){
			userRel.setOppNumber(userRel.getOppNumber().replaceAll("^(0+)", ""));
    	}
		CmResIdentity cmResIdentity = new CmResIdentity();
		cmResIdentity.setCreateDate(userRel.getCommitDate());
		cmResIdentity.setSoDate(context.getCommitDate());
		cmResIdentity.setResourceId(userRel.getUserId());
		cmResIdentity.setIdentity(userRel.getOppNumber());
		cmResIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);
		if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_NET_PARTNER_VICE_NUMBER) {
			cmResIdentity.setIdentityAttr(EnumCodeExDefine.RESOURCE_IDENTITYATTR_NET_PARTNER_VICE_NUMBER);
			if (null != userRel.getOppAttr()) {
				cmResIdentity.setRelIdentity(userRel.getOppAttr());
			} else {
				cmResIdentity.setRelIdentity(userRel.getOppNumber());
			}
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_EASY_CHANGE_VICE_NUMBER) {
			cmResIdentity.setIdentityAttr(EnumCodeExDefine.RESOURCE_IDENTITYATTR_EASY_CHANGE_VICE_NUMBER);
			if (null != userRel.getOppAttr()) {
				cmResIdentity.setRelIdentity(userRel.getOppAttr());
			} else {
				cmResIdentity.setRelIdentity(userRel.getOppNumber());
			}
		}
		cmResIdentity.setValidDate(userRel.getValidDate());
		cmResIdentity.setExpireDate(userRel.getExpireDate());
		cmResIdentity.setSoNbr(context.getSoNbr());
		baseCmp.insert(cmResIdentity);
		// 20130807 发布imsi路由
		baseCmp.createUserRouter(userRel.getUserId(), baseCmp.getAcctIdByUserIdRout(userRel.getUserId()), userRel.getOppNumber(),
				userRel.getOppAttr(), userRel.getValidDate(), userRel.getExpireDate());
	}

	/**
	 * 同步副号码 zenglu 2012-09-25
	 */
	private void deleteViceNumber(IUserRelation userRel) {
		if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_NET_PARTNER_VICE_NUMBER) {
			baseCmp.deleteByCondition(CmResIdentity.class, userRel.getExpireDate(),
					new DBCondition(CmResIdentity.Field.resourceId, userRel.getUserId()), new DBCondition(CmResIdentity.Field.identityAttr,
							EnumCodeExDefine.RESOURCE_IDENTITYATTR_NET_PARTNER_VICE_NUMBER), new DBCondition(CmResIdentity.Field.identity,
							userRel.getOppNumber()));
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_EASY_CHANGE_VICE_NUMBER) {
			baseCmp.deleteByCondition(CmResIdentity.class, userRel.getExpireDate(),
					new DBCondition(CmResIdentity.Field.resourceId, userRel.getUserId()), new DBCondition(CmResIdentity.Field.identityAttr,
							EnumCodeExDefine.RESOURCE_IDENTITYATTR_EASY_CHANGE_VICE_NUMBER), new DBCondition(CmResIdentity.Field.identity,
							userRel.getOppNumber()));
		}
		// 删除路由信息
		baseCmp.deleteRouter(null, userRel.getOppNumber(), userRel.getOppAttr(), userRel.getExpireDate());
	}

	/**
	 * 同步副号码 zenglu 2012-09-25 2012-10-29 gaoqc5 #62685 用户号码和IMSI 修改为横表存储方式
	 */
	private void updateViceNumber(IUserRelation userRel) {
		if(StringUtils.isNotBlank(userRel.getOppAttr())){
			userRel.setOppAttr(userRel.getOppAttr().replaceAll("^(0+)", ""));
    	}
		if(StringUtils.isNotBlank(userRel.getOppNumber())){
			userRel.setOppNumber(userRel.getOppNumber().replaceAll("^(0+)", ""));
    	}
		CmResIdentity cmResIdentity = new CmResIdentity();
		cmResIdentity.setExpireDate(userRel.getExpireDate());
		cmResIdentity.setIdentity(userRel.getOppNumber());
		// cmResIdentity.setIdentityType(obj);
		// cmResIdentity.setResourceId(obj);
		// cmResIdentity.setResourcePasswd(obj);
		// cmResIdentity.setSerialNo(obj);
		cmResIdentity.setSoDate(context.getCommitDate());
		cmResIdentity.setSoNbr(context.getSoNbr());

		if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_NET_PARTNER_VICE_NUMBER) {
			cmResIdentity.setIdentityAttr(EnumCodeExDefine.RESOURCE_IDENTITYATTR_NET_PARTNER_VICE_NUMBER);
		} else if (userRel.getRelationType() == EnumCodeExDefine.USER_RELATION_EASY_CHANGE_VICE_NUMBER) {
			cmResIdentity.setIdentityAttr(EnumCodeExDefine.RESOURCE_IDENTITYATTR_EASY_CHANGE_VICE_NUMBER);
		}

		if (null != userRel.getOppAttr()) {
			cmResIdentity.setRelIdentity(userRel.getOppAttr());
		} else {
			cmResIdentity.setRelIdentity(userRel.getOppNumber());
		}

		// 2012-11-02 gaoqc5 #62685 对于上海项目该表中，只会存在IDENTITY_TYPE=0的数据记录
		// baseCmp.updateByCondition(cmResIdentity, new
		// DBCondition(CmResIdentity.Field.resourceId, userRel.getUserId()),
		// new DBCondition(CmResIdentity.Field.identityType,
		// userRel.getRelationType()));
		baseCmp.updateByCondition(cmResIdentity, new DBCondition(CmResIdentity.Field.resourceId, userRel.getUserId()), new DBOrCondition(
				new DBCondition(CmResIdentity.Field.identityAttr, EnumCodeExDefine.RESOURCE_IDENTITYATTR_NET_PARTNER_VICE_NUMBER),
				new DBCondition(CmResIdentity.Field.identityAttr, EnumCodeExDefine.RESOURCE_IDENTITYATTR_EASY_CHANGE_VICE_NUMBER)));
		// 20130807 发布imsi路由信息
		baseCmp.createUserRouter(userRel.getUserId(), baseCmp.getAcctIdByUserIdRout(userRel.getUserId()), userRel.getOppNumber(),
				userRel.getOppAttr(), userRel.getValidDate(), userRel.getExpireDate());
	}

	/*
	 * 创建亲情号码
	 */
	private void createFriendNumber(IUserRelation userRel) {
		CoFnCharValue value = new CoFnCharValue();
		value.setValidDate(userRel.getValidDate());
		value.setCreateDate(userRel.getCommitDate());
		value.setExpireDate(userRel.getExpireDate());
		value.setGroupId(DBUtil.getSequence());
		value.setObjectId(userRel.getUserId());
		value.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
		value.setProductId(userRel.getProductId());
		value.setSpecCharId(SpecCodeDefine.FRIENDNUMBER_NUMBER);
		value.setValue(userRel.getOppNumber());
		baseCmp.insert(value);
	}

	/*
	 * 删除亲情号码
	 */
	private void deleteFriendNumber(IUserRelation userRel) {
		prodCmp.deleteByCondition(CoFnCharValue.class, userRel.getExpireDate(),
				new DBCondition(CoFnCharValue.Field.productId, userRel.getProductId()),
				new DBCondition(CoFnCharValue.Field.value, userRel.getOppNumber()),
				new DBCondition(CoFnCharValue.Field.objectId, userRel.getUserId()));
	}

	/**
	 * 删除铁通固定电话的资源标识 zenglu 2012-08-01
	 */
	private void deleteFixedPhone(IUserRelation userRel) {
		baseCmp.deleteByCondition(ImsUserCtt.class, new DBCondition(ImsUserCtt.Field.resourceId, userRel.getUserId()), new DBCondition(
				ImsUserCtt.Field.identityType, EnumCodeExDefine.RESOURCE_IDENTITYTYPE_RAILCOM_FIXED_NO), new DBCondition(
				ImsUserCtt.Field.identity, userRel.getOppNumber()));
	}

	/**
	 * 删除铁通上网账户的资源标识 zenglu 2012-08-01
	 */
	private void deleteNetAccount(IUserRelation userRel) {
		baseCmp.deleteByCondition(ImsUserCtt.class, new DBCondition(ImsUserCtt.Field.resourceId, userRel.getUserId()), new DBCondition(
				ImsUserCtt.Field.identityType, EnumCodeExDefine.RESOURCE_IDENTITYTYPE_RAILCOM_NET_ACCTID), new DBCondition(
				ImsUserCtt.Field.identity, userRel.getOppNumber()));
	}

	/**
	 * 修改铁通固定电话的资源标识 zenglu 2012-08-01
	 */
	private void updateFixedPhone(IUserRelation userRel) {
		ImsUserCtt imsUserCtt = new ImsUserCtt();
		imsUserCtt.setIdentity(userRel.getOppNumber());
		imsUserCtt.setExpireDate(userRel.getExpireDate());
		imsUserCtt.setSoDate(userRel.getCommitDate());
		imsUserCtt.setSoNbr(context.getSoNbr());

		baseCmp.updateByCondition(imsUserCtt, new DBCondition(ImsUserCtt.Field.resourceId, userRel.getUserId()), new DBCondition(
				ImsUserCtt.Field.identityType, EnumCodeExDefine.RESOURCE_IDENTITYTYPE_RAILCOM_FIXED_NO), new DBCondition(
				ImsUserCtt.Field.identity, userRel.getOppNumber()));
	}

	/**
	 * 修改铁通上网账户的资源标识 zenglu 2012-08-01
	 */
	private void updateNetAccount(IUserRelation userRel) {
		ImsUserCtt imsUserCtt = new ImsUserCtt();
		imsUserCtt.setIdentity(userRel.getOppNumber());
		imsUserCtt.setExpireDate(userRel.getExpireDate());
		imsUserCtt.setSoDate(userRel.getCommitDate());
		imsUserCtt.setSoNbr(context.getSoNbr());

		baseCmp.updateByCondition(imsUserCtt, new DBCondition(ImsUserCtt.Field.resourceId, userRel.getUserId()), new DBCondition(
				ImsUserCtt.Field.identityType, EnumCodeExDefine.RESOURCE_IDENTITYTYPE_RAILCOM_NET_ACCTID), new DBCondition(
				ImsUserCtt.Field.identity, userRel.getOppNumber()));

	}

	/**
	 * 一卡付费(江西) 
	 * 
	 * @param userRelation
	 */
	private void createRelationNumber(IUserRelation userRelation) {
		CmUserRelation cmRelation = new CmUserRelation();
		cmRelation.setResourceId(userRelation.getUserId());
		cmRelation.setRResourceId(CommonUtil.string2Long(userRelation.getOppAttr()));
		cmRelation.setRRegionCode(userRelation.getOppRegionCode());
//		CmResource cmResource = baseCmp.querySingle(CmResource.class,
//				new DBCondition(CmResource.Field.resourceId, userRelation.getUserId()));
		cmRelation.setRegionCode(userRelation.getOppRegionCode());
		cmRelation.setValidDate(userRelation.getValidDate());
		cmRelation.setExpireDate(userRelation.getExpireDate());
		cmRelation.setCreateDate(userRelation.getCommitDate());
		cmRelation.setSoDate(userRelation.getCommitDate());
		cmRelation.setProductId(0L);
		cmRelation.setProperty(null);
		cmRelation.setBusiType(userRelation.getRelationType());
		ITableUtil.setValue2ContextHolder(null, null, userRelation.getUserId());
		baseCmp.insert(cmRelation);
	}
	
	/**
	 * MASO业务  (湖南)
	 * 接口表的user_id 和号码交换存储 分表也要变化
	 * @param userRelation
	 */
	private void createMasoRel(IUserRelation userRelation) {
		CmUserRelation cmRelation = new CmUserRelation();
		
		//成员编号
		Long resource_id = userRelation.getUserId();
		cmRelation.setResourceId(resource_id);
		CmResource cmResource = baseCmp.querySingle(CmResource.class,
				new DBCondition(CmResource.Field.resourceId, resource_id));
		cmRelation.setRegionCode(cmResource.getRegionCode());
		//群用户编号
		Long rResource_id = Long.valueOf(userRelation.getOppNumber());
		cmRelation.setRResourceId(rResource_id);
		CmResource cmRResource = baseCmp.querySingle(CmResource.class,
				new DBCondition(CmResource.Field.resourceId, rResource_id));
		cmRelation.setRRegionCode(cmRResource.getRegionCode());
		
		cmRelation.setValidDate(userRelation.getValidDate());
		cmRelation.setExpireDate(userRelation.getExpireDate());
		cmRelation.setCreateDate(userRelation.getCommitDate());
		cmRelation.setSoDate(userRelation.getCommitDate());
		cmRelation.setProductId(0L);
		cmRelation.setProperty(null);
		cmRelation.setBusiType(userRelation.getRelationType());
		baseCmp.insert(cmRelation);
	}

	private void updateRelationNumber(IUserRelation userRelation) {
		CmUserRelation cmRelation = new CmUserRelation();
		cmRelation.setRRegionCode(userRelation.getOppRegionCode());
		CmResource cmResource = baseCmp.querySingle(CmResource.class,
				new DBCondition(CmResource.Field.resourceId, userRelation.getUserId()));
		cmRelation.setRegionCode(cmResource.getRegionCode());
		cmRelation.setExpireDate(userRelation.getExpireDate());
		cmRelation.setCreateDate(userRelation.getCommitDate());
		cmRelation.setSoDate(userRelation.getCommitDate());
		cmRelation.setProductId(0L);
		cmRelation.setProperty(null);
		baseCmp.updateMode2(cmRelation,EnumCodeExDefine.OPER_TYPE_UPDATE,userRelation.getValidDate(),
		new DBCondition(CmUserRelation.Field.resourceId, userRelation.getUserId()),
		new DBCondition(CmUserRelation.Field.rResourceId, Long.valueOf(userRelation.getOppAttr())),
		new DBCondition(CmUserRelation.Field.busiType, userRelation.getRelationType()));
		/**
		baseCmp.deleteByCondition(CmUserRelation.class,new DBCondition(CmUserRelation.Field.resourceId, userRelation.getUserId()),
				new DBCondition(CmUserRelation.Field.rResourceId, CommonUtil.string2Long(userRelation.getOppNumber())),
				new DBCondition(CmUserRelation.Field.busiType,userRelation.getRelationType()),
				new DBCondition(CmUserRelation.Field.validDate,userRelation.getValidDate()));
		*/
	}
	
	/**
	 * (湖南)MASO业务 
	 * @param userRelation
	 */
	private void updateMasoRel(IUserRelation userRelation) {

		CmUserRelation cmRelation = new CmUserRelation();
		// 主副号关系不能修改 只能失效
		CmResource cmResource = baseCmp.querySingle(CmResource.class,
				new DBCondition(CmResource.Field.resourceId, userRelation.getUserId()));
		cmRelation.setRegionCode(cmResource.getRegionCode());
		cmRelation.setExpireDate(userRelation.getExpireDate());
		cmRelation.setSoDate(userRelation.getCommitDate());
		CmResource cmRResource = baseCmp.querySingle(CmResource.class,
				new DBCondition(CmResource.Field.resourceId, CommonUtil.string2Long(userRelation.getOppNumber())));
		cmRelation.setRRegionCode(cmRResource.getRegionCode());
		cmRelation.setValidDate(userRelation.getValidDate());
		baseCmp.updateByCondition(cmRelation, new DBCondition(CmUserRelation.Field.resourceId, userRelation.getUserId()), new DBCondition(
				CmUserRelation.Field.rResourceId, CommonUtil.string2Long(userRelation.getOppNumber())),
				new DBCondition(CmUserRelation.Field.busiType,EnumCodeExDefine.USER_RELATION_MASO));
	}

	private void deleteRelationNumber(IUserRelation userRelation) {
		CmUserRelation cmRelation=new CmUserRelation();
		cmRelation.setExpireDate(userRelation.getExpireDate());
		baseCmp.updateMode2(cmRelation,EnumCodeExDefine.OPER_TYPE_DELETE,userRelation.getValidDate(),
				new DBCondition(CmUserRelation.Field.resourceId, userRelation.getUserId()),
				new DBCondition(CmUserRelation.Field.rResourceId, Long.valueOf(userRelation.getOppAttr())),
				new DBCondition(CmUserRelation.Field.busiType, userRelation.getRelationType()));
			
	}
	
	/**
	 * (湖南)MASO业务 
	 * @param userRelation
	 */
	private void deleteMasoRel(IUserRelation userRelation) {
		baseCmp.deleteByCondition(CmUserRelation.class, new DBCondition(CmUserRelation.Field.resourceId, userRelation.getUserId()),
				new DBCondition(CmUserRelation.Field.busiType, EnumCodeExDefine.USER_RELATION_MASO), new DBCondition(
						CmUserRelation.Field.rResourceId, Long.valueOf(userRelation.getOppNumber())));
	}
	
	private Long getResourceIdByIdentity(IUserRelation userRelation) {
		ITableUtil.cleanRequestParamter();
		CmResIdentity cmResIdentity = baseCmp.querySingle(CmResIdentity.class, 
				new DBCondition(CmResIdentity.Field.identity,userRelation.getOppNumber()));
		return cmResIdentity.getResourceId();
	}

	/**
	 * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<?
	 *      extends jef.database.DataObject>[])
	 */
	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
