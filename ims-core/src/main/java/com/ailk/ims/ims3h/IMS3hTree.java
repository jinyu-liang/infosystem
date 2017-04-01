package com.ailk.ims.ims3h;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jef.database.Condition.Operator;
import com.ailk.ims.common.ContextBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.complex.AccountComplex;
import com.ailk.ims.complex.GroupComplex;
import com.ailk.ims.complex.UserComplex;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.PhoneIdHeadUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAttribute;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductPricingPlan;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharUse;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecServiceRel;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecTypeGroups;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author luojb
 * @Date 2012-2-10 2012-02-10 wuyujie : 增加load3hbean时的日志输出，包括耗时 2012-06-01
 *       wuyujie : 去除checkSts参数，所有校验通过状态配置表进行 2012-06-01 wuyujie :
 *       检测load出来的3hbean和传入的三户id是否一致 2012-07-09 luojb:增加group3hbean
 * @Date 2012-09-26 zengxr remove logs
 * @Date 2012-12-12 wukl 上海项目存在主副号码区分，通过IDENTITY_ATTR来标识，计费在处理上网伴侣副号码、副号随意换副号码时会用到付号码
 */
public class IMS3hTree extends ContextBean {
	private List<Cust3hBean> custList;
	private List<Acct3hBean> acctList;
	private List<User3hBean> userList;
	private List<Offering3hbean> offeringList;
	private List<Group3hBean> groupList;

	public IMS3hTree() {
		custList = new ArrayList<Cust3hBean>();
		acctList = new ArrayList<Acct3hBean>();
		userList = new ArrayList<User3hBean>();
		offeringList = new ArrayList<Offering3hbean>();
		groupList = new ArrayList<Group3hBean>();
	}

	public Group3hBean loadGroup3hBean(Long groupId) {
		long start = System.currentTimeMillis();
		// imsLogger.debug(
		// "begin to load group3hBean[group_id=" + groupId + "]", start,
		// context));

		if (CommonUtil.isNotEmpty(groupList)) {
			for (Group3hBean bean : groupList) {

				if (bean.isMatch(groupId)) {
					// imsLogger.debug(
					// "finish to load group3hBean[group_id=" + groupId + "]",
					// start, context));
					return bean;
				}
			}
		}
		CaAccountGroup group = queryAcctGroup(groupId);
		if (group == null) {
			IMSUtil.throw3hBeanNotFoundException(
					ErrorCodeDefine.COMMON_GROUP_NOTEXIST, groupId);
		}
		GroupComplex groupComplex = new GroupComplex();
		groupComplex.setGroup(group);
		Group3hBean groupBean = new Group3hBean(groupComplex);
		groupList.add(groupBean);

		imsLogger.debug("finish to load group3hBean[group_id=" + groupId + "]",
				start, context);
		return groupBean;
	}

	public Group3hBean loadGroup3hBean(CaAccount account) {
		long start = System.currentTimeMillis();
		// imsLogger.debug(
		// "begin to load group3hBean[group_id=" + account.getAcctId() + "]",
		// start, context));

		if (CommonUtil.isNotEmpty(groupList)) {
			for (Group3hBean bean : groupList) {
				if (bean.isMatch(account.getAcctId())) {
					// imsLogger.debug(
					// "finish to load group3hBean[group_id=" +
					// account.getAcctId() + "]", start,
					// context));
					return bean;
				}
			}
		}
		CaAccountGroup group = this.queryAcctGroup(account.getAcctId());
		GroupComplex complex = new GroupComplex();
		complex.setAccount(account);
		complex.setGroup(group);
		Group3hBean groupBean = new Group3hBean(complex);
		groupList.add(groupBean);
		imsLogger.debug(
				"finish to load group3hBean[group_id=" + account.getAcctId()
						+ "]", start, context);
		return groupBean;
	}

	public Offering3hbean loadOffering3hBean(Long offeringId) {
		long start = System.currentTimeMillis();
		// imsLogger.debug(
		// "begin to load offering3hbean[offering_id=" + offeringId + "]",
		// context));

		if (CommonUtil.isNotEmpty(offeringList)) {
			for (Offering3hbean bean : offeringList) {
				if (bean.isMatch(offeringId)) {
					// imsLogger.debug(
					// "finish to load offering3hbean[offering_id=" + offeringId
					// + "]", context));
					return bean;
				}
			}
		}

		PmProductOffering offering = SpringUtil.getOfferCacheBean()
				.queryOfferingById(offeringId);
		if (offering == null)
			IMSUtil.throw3hBeanNotFoundException(
					ErrorCodeDefine.COMMON_OFFER_NOTEXIST, offeringId);

		Offering3hbean offeringBean = new Offering3hbean(offering, context);

		offeringList.add(offeringBean);
		imsLogger.debug("finish to load offering3hbean[offering_id="
				+ offeringId + "]", start, context);
		return offeringBean;
	}

	// 2012-01-14 wuyujie : 新增下面的两个四参数的方法
	public IMS3hBean load3hBean(Long custId, Long acctId, Long userId,
			String phoneId) {
		// imsLogger.debug(
		// "begin to load 3hbean[cust_id=" + custId + ",acct_id=" + acctId +
		// ",user_id=" + userId
		// + ",phone_id=" + phoneId + "]", context));
		IMS3hBean hbean = null;
		if (CommonUtil.isValid(userId) || CommonUtil.isNotEmpty(phoneId)) {
			hbean = this.loadUser3hBean(userId, phoneId);
		} else if (CommonUtil.isValid(acctId)) {
			hbean = this.loadAcct3hBean(acctId);
		} else if (CommonUtil.isValid(custId)) {
			hbean = this.loadCust3hBean(custId);
		} else {
			throw IMSUtil
					.throwBusiException("error parameters for loading 3hbean.");
		}

		// 检测load出来的3hbean和传入的三户id是否一致
		// @Date 2012-10-26 yugb :User Story #62519 pool或terminal,idle时允许查询用户状态
		hbean.checkMatchAll3hIds(custId, acctId, userId, phoneId);

		return hbean;

	}

	// ################### loadCust3hbean ############################
	public Cust3hBean loadCust3hBean(CmCustomer dmCustomer) {
		long start = System.currentTimeMillis();
		// imsLogger.debug(
		// "begin to load cust3hbean[cust_id=" + dmCustomer.getCustId() + "]",
		// context));
		if (CommonUtil.isNotEmpty(custList)) {
			for (Cust3hBean custBean : custList) {
				if (custBean.isMatch(dmCustomer.getCustId())) {
					// imsLogger.debug(
					// "finish to load cached cust3hbean[cust_id=" +
					// dmCustomer.getCustId() + "]",
					// start, context));
					return custBean;
				}
			}
		}

		Cust3hBean custBean = new Cust3hBean(dmCustomer);
		custList.add(custBean);
		imsLogger.debug(
				"finish to load cust3hbean[cust_id=" + dmCustomer.getCustId()
						+ "]", start, context);
		return custBean;
	}

	public Cust3hBean loadCust3hBean(Long custId) {
		long start = System.currentTimeMillis();
		// imsLogger.debug("begin to load cust3hbean[cust_id=" + custId + "]",
		// context));
		if (CommonUtil.isNotEmpty(custList)) {
			for (Cust3hBean custBean : custList) {
				if (custBean.isMatch(custId)) {
					// imsLogger.debug(
					// "finish to load cached cust3hbean[cust_id=" + custId +
					// "]", start, context));
					return custBean;
				}
			}
		}
		// 查询数据库
		CmCustomer customer = context.getComponent(BaseComponent.class)
				.querySingle(CmCustomer.class,
						new DBCondition(CmCustomer.Field.custId, custId));
		if (customer == null) {
			IMSUtil.throw3hBeanNotFoundException(
					ErrorCodeDefine.COMMON_CUST_NOTEXIST, custId);
		}
		Cust3hBean custBean = new Cust3hBean(customer);

		custList.add(custBean);
		imsLogger.debug("finish to load cust3hbean[cust_id=" + custId + "]",
				start, context);
		return custBean;
	}

	// ####################### end loadCust3hbean
	// ##################################

	// ################### loadAcct3hBean ############################
	public Acct3hBean loadAcct3hBean(CaAccount dmAccount, Long custId) {
		long start = System.currentTimeMillis();
		// imsLogger.debug(
		// "begin to load acct3hbean[acct_id=" + dmAccount.getAcctId() + "]",
		// context));
		if (CommonUtil.isNotEmpty(acctList)) {
			for (Acct3hBean acctBean : acctList) {
				if (acctBean.isMatch(dmAccount.getAcctId())) {
					// imsLogger.debug(
					// "finish to load cached acct3hbean[acct_id=" +
					// dmAccount.getAcctId() + "]",
					// start, context));
					return acctBean;
				}
			}
		}
		imsLogger.debug(
				"finish to load acct3hbean[acct_id=" + dmAccount.getAcctId()
						+ "]", start, context);
		AccountComplex complex = new AccountComplex();
		complex.setAccount(dmAccount);
		Acct3hBean acctBean = new Acct3hBean(complex);
		if (custId != null)
			acctBean.setParentId(custId);
		acctList.add(acctBean);

		return acctBean;
	}

	public Acct3hBean loadAcct3hBean(Long acctId) {
		long start = System.currentTimeMillis();
		// imsLogger.debug("begin to load acct3hbean[acct_id=" + acctId + "]",
		// context));
		if (!CommonUtil.isValid(acctId)) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,
					"acctId");
		}
		// 先匹配实账户
		if (CommonUtil.isNotEmpty(acctList)) {
			for (Acct3hBean acctBean : acctList) {
				if (acctBean.isMatch(acctId)) {
					// imsLogger.debug(
					// "finish to load cached acct3hbean[acct_id=" + acctId +
					// "]", start, context));
					return acctBean;
				}
			}
		}
		// 再匹配群
		if (CommonUtil.isNotEmpty(groupList)) {
			for (Group3hBean groupBean : groupList) {
				if (groupBean.isMatch(acctId)) {
					// imsLogger.debug(
					// "finish to load cached acct3hbean[acct_id=" + acctId +
					// "]", start, context));
					return groupBean;
				}
			}
		}

		// 查询数据库
		CaAccount account = queryAccountById(acctId);
		if (account == null) {
			IMSUtil.throw3hBeanNotFoundException(
					ErrorCodeDefine.COMMON_ACCT_NOTEXIST, acctId);
		}
		Acct3hBean acctBean = null;
		if (account.getAccountClass().intValue() == EnumCodeDefine.ACCOUNT_CLASS_CA) {
			AccountComplex complex = new AccountComplex();
			complex.setAccount(account);
			acctBean = new Acct3hBean(complex);
			acctList.add(acctBean);
		} else {
			acctBean = loadGroup3hBean(account);
			groupList.add((Group3hBean) acctBean);
		}
		imsLogger.debug("finish to load acct3hbean[acct_id=" + acctId + "]",
				start, context);
		return acctBean;
	}

	private CaAccount queryAccountById(Long acctId) {
		return context.getComponent(BaseComponent.class).querySingle(
				CaAccount.class,
				new DBCondition(CaAccount.Field.acctId, acctId));
	}

	private CaAccountGroup queryAcctGroup(Long groupId) {
		return context.getComponent(BaseComponent.class).querySingle(
				CaAccountGroup.class,
				new DBCondition(CaAccountGroup.Field.acctId, groupId));
	}

	// ################### end loadAcct3hBean ############################

	// ################### loadUser3hBean ############################
	public User3hBean loadUser3hBean(Long userId) {
		return loadUser3hBean(userId, null);
	}

	public User3hBean loadUser3hBean(String phoneId) {
		return loadUser3hBean(null, phoneId);
	}

	public User3hBean loadUser3hBean(UserComplex complex) {
		return loadUser3hBean(complex, null);
	}

	public User3hBean loadUser3hBean(UserComplex complex, CaAccountRes caRes) {
		long start = System.currentTimeMillis();
		// Long userId = complex.getResource() == null ? null :
		// complex.getResource().getResourceId();
		// imsLogger.debug(
		// "begin to load user3hbean[user_id=" + userId + ",phone_id=" +
		// complex.getPhoneId() + "]",
		// context));

		if (CommonUtil.isNotEmpty(userList)) {
			for (User3hBean userBean : userList) {
				if (userBean.isMatch(complex.getPhoneId())) {
					// imsLogger.debug(
					// "finish to load cached user3hbean[user_id=" +
					// userBean.getUserId()
					// + ",phone_id=" + userBean.getPhoneId() + "]", start,
					// context));
					return userBean;
				}
			}
		}
		User3hBean userBean = new User3hBean(complex);
		if (caRes != null)
			userBean.setCaAccountRes(caRes);
		userList.add(userBean);
		imsLogger.debug(
				"finish to load user3hbean[user_id=" + userBean.getUserId()
						+ ",phone_id=" + userBean.getPhoneId() + "]", start,
				context);
		return userBean;
	}

	public User3hBean loadUser3hBean(Long userId, String phoneId) {
		long start = System.currentTimeMillis();
		// imsLogger.debug(
		// "begin to load user3hbean[user_id=" + userId + ",phone_id=" + phoneId
		// + "]", context));
		if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId)) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,
					"userId and phoneId");
		}
		if (CommonUtil.isNotEmpty(userList)) {
			for (User3hBean userBean : userList) {
				if (userBean.isMatch(userId, phoneId)) {
					// imsLogger.debug(
					// "finish to load cached user3hbean[user_id=" +
					// userBean.getUserId()
					// + ",phone_id=" + userBean.getPhoneId() + "]", start,
					// context));
					return userBean;
				}
			}
		}

		// 查询数据库
		UserComplex complex = queryUserComplex(userId, phoneId);

		User3hBean userBean = new User3hBean(complex);
		userList.add(userBean);
		imsLogger.debug(
				"finish to load user3hbean[user_id=" + userBean.getUserId()
						+ ",phone_id=" + userBean.getPhoneId() + "]", start,
				context);
		return userBean;
	}

	public void destroy() {
		if (custList != null)
			custList.clear();
		if (acctList != null)
			acctList.clear();
		if (userList != null)
			userList.clear();
		imsLogger.debug("++++++clear all cached 3hbean");
	}

	/**
	 * @Description: 移除某个3hbean，同时也移除这个3hbean和其子3hbean的父子关系
	 * @author : wuyj
	 * @date : 2012-1-14
	 * @param hbean
	 */
	public void remove3hBean(IMS3hBean hbean) {
		if (hbean.isCust3hBean()) {
			if (CommonUtil.isNotEmpty(acctList)) {
				// 把当前cust3hbean下的所有acct3hbean的parent置为空
				for (Acct3hBean acct3h : acctList) {
					if (hbean == acct3h.getParent()) {
						acct3h.setParent(null);
					}
				}
			}
			// imsLogger.debug("++++++remove cached cust3hBean.cust_id = [" +
			// hbean.getCustId() + "]");
			custList.remove(hbean);
		} else if (hbean.isAcct3hBean()) {
			if (CommonUtil.isNotEmpty(userList)) {
				// 把当前acct3hbean下的所有user3hbean的parent置为空
				for (User3hBean user3h : userList) {
					if (hbean == user3h.getParent()) {
						user3h.setParent(null);
					}
				}
			}
			// imsLogger.debug("++++++remove cached acct3hBean.acct_id = [" +
			// hbean.getAcctId() + "]");
			acctList.remove(hbean);
		} else if (hbean.isUser3hBean()) {
			// imsLogger.debug(
			// "++++++remove cached user3hBean.user_id = [" + hbean.getUserId()
			// + "],phone_id=[" + hbean.getPhoneId() + "]");
			userList.remove(hbean);
		}
	}

	// --------------------------add-----------------------//

	/**
	 * 根据userId或phoneId查询UserComplex luojb 2012-1-9
	 */
	private UserComplex queryUserComplex(Long userId, String phoneId) {
		UserComplex complex = new UserComplex();
		CmResIdentity iden = null;
		CmResource res = null;

		if (CommonUtil.isValid(phoneId)) {
			phoneId = PhoneIdHeadUtil.phoneIdParse(phoneId);
			iden = queryResIdentity(userId, phoneId,
					EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);
			if (iden == null) {
				IMSUtil.throw3hBeanNotFoundException(
						ErrorCodeDefine.COMMON_PHONE_NOTEXIST, phoneId, userId);
			}
			if (iden != null) {
				complex.setPhoneId(iden.getIdentity());
				userId = iden.getResourceId();
			}
		}

		res = queryUserByUserID(userId);
		if (res == null) {
			IMSUtil.throw3hBeanNotFoundException(
					ErrorCodeDefine.COMMON_USER_NOTEXIST, userId);
		}
		complex.setResource(res);

		return complex;
	}

	private CmResIdentity queryResIdentity(Long userId, String iden,
			Integer idenType) throws IMSException {
		if (userId == null && CommonUtil.isEmpty(iden)) {
			return null;
		}

		List<DBCondition> conList = new ArrayList<DBCondition>();
		if (CommonUtil.isValid(userId)) {
			conList.add(new DBCondition(CmResIdentity.Field.resourceId, userId));
			
			if (ProjectUtil.is_CN_SH() && !CommonUtil.isValid(iden))
			{//@Date 2012-12-12 wukl 上海项目存在主副号码区分，通过IDENTITY_ATTR来标识，计费在处理上网伴侣副号码、副号随意换副号码业务时会用到付号码
				conList.add(new DBCondition(CmResIdentity.Field.identityAttr, EnumCodeDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER));
			}
		}

		if (CommonUtil.isValid(iden)) {
			conList.add(new DBCondition(CmResIdentity.Field.identity, iden));
		}

		if (idenType != null) {
			conList.add(new DBCondition(CmResIdentity.Field.identityType,
					idenType));
		}

		return context.getComponent(BaseComponent.class).querySingle(
				CmResIdentity.class,
				conList.toArray(new DBCondition[conList.size()]));
	}

	/**
	 * 根据用户ID获取用户信息
	 */
	private CmResource queryUserByUserID(Long userID) throws IMSException {
		return context.getComponent(BaseComponent.class).querySingle(
				CmResource.class,
				new DBCondition(CmResource.Field.resourceId, userID));
	}

	/**
	 * lijc3 2012-9-27 load list offer3hBean 该方法不会判断缓存中是否存在相应的单个offer3hBean,
	 * 所以该方法调用在loadOffer3hBean方法调用前
	 * 
	 * @param offerIdList
	 */
	public void loadOfferList3hBean(List<Integer> offerIdList) {
		Set<Integer> offerIdSet = new HashSet<Integer>(offerIdList);
		// 判断个数
		BaseComponent baseCmp = context.getComponent(BaseComponent.class);
		List<PmProductOffering> offerList = baseCmp.query(
				PmProductOffering.class, new DBCondition(
						PmProductOffering.Field.productOfferingId, offerIdSet,
						Operator.IN));
		List<PmProductOfferLifecycle> lifeCycleList = baseCmp.query(
				PmProductOfferLifecycle.class, new DBCondition(
						PmProductOfferLifecycle.Field.productOfferingId,
						offerIdSet, Operator.IN));
		List<PmProductOfferAttribute> buteList = baseCmp.query(
				PmProductOfferAttribute.class, new DBCondition(
						PmProductOfferAttribute.Field.productOfferingId,
						offerIdSet, Operator.IN));
		List<PmProductOfferSpecChar> specCharList = DBUtil.query(
				PmProductOfferSpecChar.class, new DBCondition(
						PmProductOfferSpecChar.Field.productOfferingId,
						offerIdSet, Operator.IN));
		if (CommonUtil.isEmpty(offerList) || CommonUtil.isEmpty(lifeCycleList)
				|| CommonUtil.isEmpty(buteList)) {
			throw IMSUtil
					.throwBusiException("configration data of offerlist all not exist");
		}
		Set<Integer> prodSpecIdList = new HashSet<Integer>();
		for (PmProductOffering offering : offerList) {
			prodSpecIdList.add(offering.getProdSpecId());
		}
		List<PmProductSpecTypeGroups> groupList = baseCmp.query(
				PmProductSpecTypeGroups.class, new DBCondition(
						PmProductSpecTypeGroups.Field.prodSpecId,
						prodSpecIdList, Operator.IN));
		List<PmProductSpecServiceRel> serviceList = baseCmp.query(
				PmProductSpecServiceRel.class, new DBCondition(
						PmProductSpecServiceRel.Field.prodSpecId,
						prodSpecIdList, Operator.IN));
		List<PmProductSpecCharUse> charUserList = baseCmp.query(
				PmProductSpecCharUse.class, new DBCondition(
						PmProductSpecCharUse.Field.prodSpecId, prodSpecIdList,
						Operator.IN));
		// List<PmProductPricingPlan> planList =
		// baseCmp.query(PmProductPricingPlan.class, new DBCondition(
		// PmProductPricingPlan.Field.productOfferingId, offerIdSet,
		// Operator.IN));
		List<PmCompositeDeductRule> deductRuleList = DBUtil.query(
				PmCompositeDeductRule.class, new DBCondition(
						PmProductPricingPlan.Field.productOfferingId,
						offerIdSet, Operator.IN));
		for (Integer offerId : offerIdSet) {
			PmProductOffering offering = getOffering(offerId, offerList);
			Offering3hbean offerBean = new Offering3hbean(offering, context);
			offerBean.setFlag(true);
			offeringList.add(offerBean);
			Integer prodSpecId = offering.getProdSpecId();
			offerBean.setAttribute(getOfferAttribute(offerId, buteList));
			offerBean.setLifeCycle(getOfferLifecycle(offerId, lifeCycleList));
			offerBean
					.setSpecTypeGroup(getSpecTypeGroups(prodSpecId, groupList));
			offerBean
					.setServiceList(getServiceRelList(prodSpecId, serviceList));
			offerBean.setSpecCharUserList(getCharUseList(prodSpecId,
					charUserList));
			// offerBean.setPricePlanList(getPricingPlanList(offerId,
			// planList));
			offerBean.setDeductRule(deductRuleList);
			offerBean.setSpecCharList(getOfferSpecCharList(offerId,
					specCharList));
		}

	}

	private List<PmProductOfferSpecChar> getOfferSpecCharList(Integer offerId,
			List<PmProductOfferSpecChar> specCharList) {
		if (CommonUtil.isEmpty(specCharList)) {
			return null;
		}
		List<PmProductOfferSpecChar> result = new ArrayList<PmProductOfferSpecChar>();
		for (PmProductOfferSpecChar spec : specCharList) {
			if (spec.getProductOfferingId().equals(offerId)) {
				result.add(spec);
			}
		}
		return CommonUtil.isEmpty(result) ? null : result;
	}

	// ############ 相应的获取对应的对象的方法

	private PmProductOffering getOffering(Integer offerId,
			List<PmProductOffering> offerList) {
		for (PmProductOffering offer : offerList) {
			if (offer.getProductOfferingId().intValue() == offerId) {
				return offer;
			}
		}
		throw IMSUtil.throw3hBeanNotFoundException(
				ErrorCodeDefine.COMMON_OFFER_NOTEXIST, offerId);
	}

	private PmProductOfferLifecycle getOfferLifecycle(Integer offerId,
			List<PmProductOfferLifecycle> lifeCycleList) {
		for (PmProductOfferLifecycle lifecycle : lifeCycleList) {
			if (lifecycle.getProductOfferingId().intValue() == offerId) {
				return lifecycle;
			}
		}
		throw IMSUtil.throwBusiException("offer lifecycle of offer_id = "
				+ offerId + " not exist");
	}

	private PmProductOfferAttribute getOfferAttribute(Integer offerId,
			List<PmProductOfferAttribute> buteList) {
		for (PmProductOfferAttribute attbute : buteList) {
			if (attbute.getProductOfferingId().intValue() == offerId) {
				return attbute;
			}
		}
		throw IMSUtil.throwBusiException("offer attribute of offer_id = "
				+ offerId + " not exist");
	}

	private PmProductSpecTypeGroups getSpecTypeGroups(int specId,
			List<PmProductSpecTypeGroups> groupList) {
		if (CommonUtil.isEmpty(groupList)) {
			return null;
		}
		for (PmProductSpecTypeGroups group : groupList) {
			if (group.getProdSpecId() == specId) {
				return group;
			}
		}
		return null;
	}

	private List<PmProductSpecServiceRel> getServiceRelList(int prodSpecId,
			List<PmProductSpecServiceRel> serviceList) {
		if (CommonUtil.isEmpty(serviceList)) {
			return null;
		}
		List<PmProductSpecServiceRel> result = new ArrayList<PmProductSpecServiceRel>();
		for (PmProductSpecServiceRel rel : serviceList) {
			if (rel.getProdSpecId() == prodSpecId) {
				result.add(rel);
			}
		}
		return result.size() == 0 ? null : result;
	}

	private List<PmProductSpecCharUse> getCharUseList(int prodSpecId,
			List<PmProductSpecCharUse> charUserList) {
		if (CommonUtil.isEmpty(charUserList)) {
			return null;
		}
		List<PmProductSpecCharUse> result = new ArrayList<PmProductSpecCharUse>();
		for (PmProductSpecCharUse rel : charUserList) {
			if (rel.getProdSpecId() == prodSpecId) {
				result.add(rel);
			}
		}
		return result.size() == 0 ? null : result;
	}

	public boolean isUserExist(String phoneId) {
		return this.isUserExist(null, phoneId);
	}

	public boolean isUserExist(Long userId) {
		return this.isUserExist(userId, null);
	}

	public boolean isUserExist(Long userId, String phoneId) {
		try {
			context.get3hTree().loadUser3hBean(userId, phoneId);
			return true;
		} catch (IMS3hNotFoundException e) {
			return false;
		}
	}
	/*
	 * private List<PmProductPricingPlan> getPricingPlanList(int offerId,
	 * List<PmProductPricingPlan> planList) { if (CommonUtil.isEmpty(planList))
	 * { return null; } List<PmProductPricingPlan> result = new
	 * ArrayList<PmProductPricingPlan>(); for (PmProductPricingPlan rel :
	 * planList) { if (rel.getProductOfferingId() == offerId) { result.add(rel);
	 * } } return result.size() == 0 ? null : result; }
	 */
}
