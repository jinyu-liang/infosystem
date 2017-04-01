package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.ColCodeDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.define.SpecCodeExDefine;
import com.ailk.imssh.common.init.EnumMappingInitBean;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.order.cmp.UserOrderCmp;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.imssh.prod.cmp.ProdExCmp;
import com.ailk.imssh.prod.handler.ProdHandler;
import com.ailk.imssh.user.handler.UserHandler;
import com.ailk.imssh.user.handler.UserStsHandler;
import com.ailk.imssh.user.helper.UserAcctRelHelper;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResourceMonitor;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.cust.entity.CmUserMap;
import com.ailk.openbilling.persistence.cust.entity.CmUserMarket;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrderConfirm;
import com.ailk.openbilling.persistence.cust.entity.CmUserPbx;
import com.ailk.openbilling.persistence.cust.entity.CmUserPortability;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareProd;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.itable.entity.IProduct;
import com.ailk.openbilling.persistence.itable.entity.IUser;
import com.ailk.openbilling.persistence.itable.entity.IUserStatus;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description 用户状态组件
 * @author wukl
 * @Date 2012-5-17
 * @Data 2012-12-18 wukl 2010-3-16之后有效期到期的用户不再收费；即新系统中不需要订购保留期停产品
 */
public class UserStsCmp extends UserQuery {
	/*
	 * 用户停机位OS_STS_DTL说明：将CRM的64位转成BOSS的20位(映射关系见boot.xml)
	 * CRM的64位定义(从左到右)：
	 * 1 用户 2 租机租卡 3 担保人 4 欠费 5 欠费预销户 6 呼出限制 7 内部 8 特殊 9 网络部 10 一证多机 11 预销户
	 * 12 易通卡有效期 13 保留期 14 短信保留 15 恶意呼叫 16 欠费风险 17 城管 18 测试卡用户 19 举报 20 新入网额度 21
	 * 日保号
	 * 22 国际漫游高额监控 23 被盗/遗失 24 未开通停机 25 新入网额度连带26 开户停
	 * BOSS的20位定义(从左到右)：
	 * 1 其他停、11日保号停、12 特殊停、13 同证件停、14 新入网额度停、
	 * 15 有效期停、16 保留期停、17 用户停、18 呼出限制停、19 欠费预销户停、20 欠费停
	 * 
	 * 最后将20位串转成十进制存到数据库中
	 */
	/*
	 * 预销户、预销户回退、销户的处理说明：
	 * 接口表的传值说明：
	 * 预销户：CRM侧会传I_USER_STATUS（sts=1008，失效时间还是未来时间），I_PRODUCT表（产品置为失效，优惠置为下周期失效）
	 * 预销户回退：CRM会传I_USER_STATUS（sts=1001，失效时间还是未来时间），I_PRODUCT表（原产品重新订购，优惠也重新订购，
	 * 生效时间为回退的时间点）
	 * 销户：CRM会传CRM会传I_USER\I_USER_STATUS（sts=1008，失效时间为销户时间点）
	 * 程序的处理说明：
	 * 预销户：根据CRM传入的进行修改
	 * 预销户回退：根据CRM传入的进行修改，同时在产品订购前将未来失效的优惠删除，以便后面重新订购
	 * 销户：根据CRM传入的进行修改，同时需要将用户的相关资料表删除；目前需要删除的表整理如下（）；
	 */
	/**
	 * 新增用户状态<br>
	 * wukl 2012-5-22
	 */
	public void createUserStatus(IUserStatus iUserStatus) {
		CmResLifecycle cmResLifecycle = new CmResLifecycle();
		cmResLifecycle.setCreateDate(context.getCommitDate());
		cmResLifecycle.setSoDate(context.getCommitDate());
		cmResLifecycle.setResourceId(iUserStatus.getUserId());
		cmResLifecycle.setSts(iUserStatus.getSts());
		cmResLifecycle.setOsSts(iUserStatus.getOsSts());
		cmResLifecycle.setFrauldFlag(EnumCodeDefine.LIFECYCLE_UNFRAULD_FLAG);
		// 如果用户停和欠费停同时并存，那么sts用欠费停，同时用USERREQUEST_FLAG设置成1；
		cmResLifecycle.setUserrequestFlag(EnumCodeDefine.LIFECYCLE_USERREQUEST_NO_FLAG);
		cmResLifecycle.setUnbillingFlag(EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_NO);
		cmResLifecycle.setReratingFlag(EnumCodeDefine.LIFECYCLE_RERATING_FLAG_NO);
		cmResLifecycle.setValidDate(iUserStatus.getValidDate());
		cmResLifecycle.setExpireDate(iUserStatus.getExpireDate());
		cmResLifecycle.setSoNbr(context.getSoNbr());
		cmResLifecycle.setRecSts(EnumCodeDefine.IS_VALID_DATA);
//		String osStsDtl = this.getBossOsStsDtlFromCRM(iUserStatus.getOsStsDtl());
//		imsLogger.debug("CRM停机位转成BOSS侧，如下：" + osStsDtl);
//		Integer dbOsStsDtl = this.getDBOsStsDtl(osStsDtl);
		cmResLifecycle.setOsStsDtl(Integer.valueOf(iUserStatus.getOsStsDtl()));
		cmResLifecycle.setNextSts(11111111);// 与割接保持一致
		// cmResLifecycle.setNextStsExpiredate(iUserStatus.getNextStsExpiredate());
		super.insert(cmResLifecycle);
	}

	/**
	 * 修改用户状态，用户停状态涉及停机保号产品的处理<br>
	 * wukl 2012-5-22
	 */
	public void modifyUserStatus(IUserStatus iUserStatus) {
		//20160120
		Date currentDate = iUserStatus.getCommitDate();

		Long userId = iUserStatus.getUserId();

		// 查询用户状态
//		CmResLifecycle origLifeCycle = querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId, userId),
//				new DBCondition(CmResLifecycle.Field.expireDate, currentDate, Operator.GREAT_EQUALS));
//
//		if (origLifeCycle != null) {// 若用户状态已经存在，则先将用户状态置为历史数据
//			this.updateCmResLifeCycleSts4His(userId);
//
//		} else {
//			throw IMSUtil.throwBusiException(ErrorCodeExDefine.LIFE_CYCLE_IS_NULL, userId);
//		}

		this.updateCmResLifeCycle(iUserStatus, currentDate);

		// chenxd注释，广西项目用到，内蒙项目不用，所以注释

//		if (ProjectUtil.is_CN_GX()) {
//			String osStsDtl = this.getBossOsStsDtlFromCRM(iUserStatus.getOsStsDtl());
//			String oldOsStsDtl = this.getBossOsStsDtlFromDB(origLifeCycle.getOsStsDtl());
//			imsLogger.debug("new os sts dtl " + osStsDtl);
//			imsLogger.debug("old os sts dtl " + oldOsStsDtl);
//
//			boolean newAcctStopFlag = this.isOsStsDtlStopByAcct(osStsDtl);
//			boolean oldAcctStopFlag = this.isOsStsDtlStopByAcct(oldOsStsDtl);
//			context.getCmp(UserOrderCmp.class).dealOrderStsByUserLifecycle(userId, origLifeCycle.getSts(), origLifeCycle.getOsSts(),
//					iUserStatus.getSts(), iUserStatus.getOsSts(), oldAcctStopFlag, newAcctStopFlag);
//		}

	}

	/**
	 * 判断停机状态位中是否包含 账务连带停、信誉度停、欠费停
	 * 包含一个返回true
	 * 
	 * @param osStsDtl
	 * @return
	 */
	private boolean isOsStsDtlStopByAcct(String osStsDtl) {
		boolean acctStopFlag = this.judgeAcctStopInGx(osStsDtl);
		boolean creditStopFlag = this.judgeCreditStopInGx(osStsDtl);
		boolean noFeeStopFlag = this.judgeNoFeeStopInGx(osStsDtl);

		return acctStopFlag || creditStopFlag || noFeeStopFlag;
	}

	/**
	 * 预销户回退时，需要将生效的优惠删除（因为CRM通过工单会再次受理进来，如果不删除就会出现某一时间段产品重复）；
	 * wukl 2012-12-15
	 * 
	 * @param iUserStatus
	 * @param origLifeCycle
	 */
	@SuppressWarnings("unused")
	private void dealTerminalUser(IUserStatus iUserStatus, CmResLifecycle origLifeCycle) {
		// 预销户回退动作的判断：新状态不是预销户，老状态是预销户
		if (iUserStatus.getSts().intValue() != EnumCodeDefine.LIFECYCLE_TERMINAL
				&& origLifeCycle.getSts().intValue() == EnumCodeDefine.LIFECYCLE_TERMINAL) {
			// @Date 2012-12-26 wukl 由于commit_date与产品的valid_date
			// 时间不一致，会产生某一时间段产品重复
			IProduct iProduct = (IProduct) context.getSingleIDataObjectList(ProdHandler.class);
			Date expireDate = iProduct == null ? iUserStatus.getCommitDate() : iProduct.getValidDate();
			context.getCmp(ProdCmp.class).deleteProdByUserId(iUserStatus.getUserId(), expireDate);
		}
	}

	/**
	 * 日保号停，日保号停产品的相关处理 wukl 2012-11-19
	 * 
	 * @param iUserStatus
	 * @param origLifeCycle
	 * @param oldDtl
	 * @param newDtl
	 */
	@SuppressWarnings("unused")
	private void dealDayStop(IUserStatus iUserStatus, CmResLifecycle origLifeCycle, String newDtl, String oldDtl) {
		// 新状态是否日保号停
		boolean newFlag = judgeDayStop(newDtl);

		// 老状态是否日保号停
		boolean oldFlag = judgeDayStop(oldDtl);

		// 新状态是否是欠费停
		boolean isFeeStop = judgeFeeStop(newDtl);

		// 若新的状态为日保号停 且不是欠费停，则订购一个日保号停产品
		if (newFlag && !oldFlag && !isFeeStop) {
			createProd(iUserStatus, iUserStatus.getCommitDate(), iUserStatus.getExpireDate(), SpecCodeExDefine.DAY_PROD);
		}

		// 若新的状态不是日保号停、原先状态为日保号停 或者 是欠费停，则取消日保号停产品
		if ((!newFlag && oldFlag) || isFeeStop) {
			context.getCmp(ProdCmp.class).cancelProd(iUserStatus.getUserId(), context.getCommitDate(), SpecCodeExDefine.DAY_PROD);
		}
	}

	/**
	 * 保留期停，保留期产品的相关处理 wukl 2012-11-19
	 * 
	 * @param iUserStatus
	 * @param origLifeCycle
	 * @param oldDtl
	 * @param newDtl
	 */
	@SuppressWarnings("unused")
	private void dealRetainStop(IUserStatus iUserStatus, CmResLifecycle origLifeCycle, String newDtl, String oldDtl) {
		// 新状态是否保留期停
		boolean newFlag = judgeRetainStop(newDtl);

		// 老状态是否保留期停
		boolean oldFlag = judgeRetainStop(oldDtl);

		// 新状态是否是欠费停
		boolean isFeeStop = judgeFeeStop(newDtl);

		// 若新的状态为保留期停且不是欠费停，则订购一个保留期停产品
		// if (newFlag && !oldFlag && !isFeeStop)
		// {
		// createProd(iUserStatus, iUserStatus.getCommitDate(),
		// iUserStatus.getExpireDate(), SpecCodeExDefine.RETAIN_PROD);
		// }

		// 若新的状态不是保留期停、原先状态为保留期停 或者 是欠费停，则取消保留期停产品
		if ((!newFlag && oldFlag) || isFeeStop) {
			context.getCmp(ProdCmp.class).cancelProd(iUserStatus.getUserId(), context.getCommitDate(), SpecCodeExDefine.RETAIN_PROD);
		}
	}

	/**
	 * 用户停，停机保号产品的相关处理 wukl 2012-11-19
	 * 
	 * @param iUserStatus
	 * @param origLifeCycle
	 * @param oldDtl
	 * @param newDtl
	 * @param prodCmp
	 */
	@SuppressWarnings("unused")
	private void dealUserStop(IUserStatus iUserStatus, CmResLifecycle origLifeCycle, String newDtl, String oldDtl) {

		// 新状态是否用户停
		boolean newFlag = judgeUserStop(newDtl);

		// 老状态是否用户停
		boolean oldFlag = judgeUserStop(oldDtl);

		// 若新的状态为用户停，则订购一个停机保号产品
		if (newFlag && !oldFlag) {
			Date validDate = getSpecProdValidDate(iUserStatus.getUserId(), iUserStatus.getCommitDate());
			createProd(iUserStatus, validDate, iUserStatus.getExpireDate(), SpecCodeDefine.USER_SUSPEND_REQUEST);
		}

		// 若新的状态不是用户停，原先状态为用户停，则取消停机保号产品
		if (!newFlag && oldFlag) {
			context.getCmp(ProdCmp.class).cancelProd(iUserStatus.getUserId(), context.getCommitDate(), SpecCodeDefine.USER_SUSPEND_REQUEST);
		}
	}

	/**
	 * #61571停机保号产品的特殊处理 2012-10-17 gaoqc5 获取生效时间 判断接口表中的commit_date
	 * 是不是00：00：00，如果是而且commit_date
	 * 是用户的账期首日，则订购一个生效时间为commit_date的产品
	 */
	private Date getSpecProdValidDate(Long userId, Date commitDate) {
		Date validDate = null;
		User3hBean bean = context.get3hTree().loadUser3hBean(userId);
		if (isVliDateHHmInZero(bean.getAcctId(), commitDate)) {
			validDate = commitDate;
		} else {
			imsLogger.debug("***** access interface,query account next cycle!");
			validDate = context.getComponent(AccountQuery.class).queryAcctNextCycleStart(bean.getAcctId());
		}
		validDate = validDate == null ? context.getRequestDate() : validDate;
		return validDate;
	}

	/**
	 * 2012-10-17 gaoqc5 判断 commitDate 是否是00:00:00 以及是否就是首账日
	 */
	private boolean isVliDateHHmInZero(Long acctId, Date commitDate) {
		Calendar commitCal = Calendar.getInstance();
		commitCal.setTime(commitDate);
		if (0 == commitCal.get(Calendar.HOUR_OF_DAY) && // 零时
				0 == commitCal.get(Calendar.MINUTE) && // 零分
				0 == commitCal.get(Calendar.SECOND)) {// 零秒
			imsLogger.debug("***** access interface,query account current cycle!");
			Date billDate = context.getComponent(AccountQuery.class).queryAcctCurrentCycleStart(acctId);
			if (null == billDate) {
				return false;
			}
			int billDay = com.ailk.ims.util.DateUtil.getDateField(billDate, Calendar.DAY_OF_MONTH);
			int commitDay = commitCal.get(Calendar.DAY_OF_MONTH);
			return billDay == commitDay; // 判断commit_date 是否是用户的账期首日
		}
		return false;
	}

	/**
	 * 删除用户状态<br>
	 * wukl 2012-5-22
	 */
	public void deleteUserStatus(IUserStatus iUserStatus ) {
//		if (CommonUtil.isIn(iUserStatus.getSts().intValue(), ColCodeDefine.LIFECYCLE_POOL_CANCEL)) {
//			context.addExtendParam(ConstantExDefine.USER_STS_POOL, true);
//			IUser iUser = (IUser) context.getSingleIDataObjectList(UserHandler.class);
//			if (iUser != null) {
//				String imsi = context.getCmp(UserCmp.class).getImsi(iUser);
//				String phoneId = iUser.getPhoneId();
//				context.addExtendParam(ConstantExDefine.POOL_PHONE_ID, phoneId);
//				context.addExtendParam(ConstantExDefine.POOL_IMSI, imsi);
//			}
//			// chenxd注释，广西项目用到，内蒙项目不用，加上版本判断
//
//			// 广西特殊处理 销户的时候要将中心业务订购状态改为5
//			if (ProjectUtil.is_CN_GX()) {
//				context.getCmp(UserOrderCmp.class).dealOrderStsByUserLifecycle(iUserStatus.getUserId(), null, null, iUserStatus.getSts(),
//						iUserStatus.getOsSts(), false, false);
//			}
//		}

		// 先将记录的状态置为历史记录
		//this.updateCmResLifeCycleSts4His(iUserStatus.getUserId());
		//CmResLifecycle lifecycle = new CmResLifecycle();
		//lifecycle.setSts(iUserStatus.getSts());
		//lifecycle.setExpireDate(iUserStatus.getExpireDate());
		//this.updateByCondition(lifecycle, new DBCondition(CmResLifecycle.Field.resourceId, iUserStatus.getUserId()),new DBCondition(CmResLifecycle.Field.expireDate, DateUtil.currentDate(), Operator.GREAT_EQUALS));
		CmResLifecycle cmResLifecycle = new CmResLifecycle();
		cmResLifecycle.setExpireDate(iUserStatus.getExpireDate());
		this.updateMode2(cmResLifecycle,EnumCodeExDefine.OPER_TYPE_DELETE,iUserStatus.getValidDate(),
				new DBCondition(CmResLifecycle.Field.resourceId, iUserStatus.getUserId()), 
				new DBCondition(CmResLifecycle.Field.osSts,iUserStatus.getOsSts()),
				new DBCondition(CmResLifecycle.Field.osStsDtl,iUserStatus.getOsStsDtl()));
	}

	/**
	 * 将用户状态的记录置成历史记录<br>
	 * wukl 2012-5-22
	 */
	private void updateCmResLifeCycleSts4His(Long userId) {
		CmResLifecycle oldvalid = new CmResLifecycle();
		oldvalid.setRecSts(EnumCodeDefine.IS_HISTORY_DATA);

		DBUtil.updateByCondition(oldvalid, new DBCondition(CmResLifecycle.Field.resourceId, userId), new DBCondition(
				CmResLifecycle.Field.recSts, EnumCodeDefine.IS_VALID_DATA));
	}

	/**
	 * 修改用户生命表<br>
	 * wukl 2012-5-22
	 */
	private void updateCmResLifeCycle(IUserStatus iUserStatus, Date currentDate) {		
		CmResLifecycle cmResLifecycle = new CmResLifecycle();
		cmResLifecycle.setCreateDate(context.getCommitDate());
		cmResLifecycle.setSoDate(context.getCommitDate());
		cmResLifecycle.setFrauldFlag(EnumCodeDefine.LIFECYCLE_UNFRAULD_FLAG);
		// 如果用户停和欠费停同时并存，那么sts用欠费停，同时用USERREQUEST_FLAG设置成1；
		cmResLifecycle.setUserrequestFlag(EnumCodeDefine.LIFECYCLE_USERREQUEST_NO_FLAG);
		cmResLifecycle.setUnbillingFlag(EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_NO);
		cmResLifecycle.setReratingFlag(EnumCodeDefine.LIFECYCLE_RERATING_FLAG_NO);
		cmResLifecycle.setExpireDate(iUserStatus.getExpireDate());
		cmResLifecycle.setSoNbr(context.getSoNbr());
		cmResLifecycle.setRecSts(EnumCodeDefine.IS_VALID_DATA);
		cmResLifecycle.setSts(iUserStatus.getSts());
//		String osStsDtl = this.getBossOsStsDtlFromCRM(iUserStatus.getOsStsDtl());
//		imsLogger.debug("CRM停机位转成BOSS侧，如下：" + osStsDtl);
//		Integer dbOsStsDtl = this.getDBOsStsDtl(osStsDtl);
		cmResLifecycle.setNextSts(11111111);// 与割接保持一致
		this.updateMode2(cmResLifecycle,EnumCodeExDefine.OPER_TYPE_UPDATE,iUserStatus.getValidDate(),
				new DBCondition(CmResLifecycle.Field.resourceId, iUserStatus.getUserId()), 
				new DBCondition(CmResLifecycle.Field.osSts,iUserStatus.getOsSts()),
				new DBCondition(CmResLifecycle.Field.osStsDtl,iUserStatus.getOsStsDtl()));
		
		
	}

	/**
	 * 判断是否用户停<br>
	 * BOSS位串(从左到右)，第17位是用户停 wukl 2012-6-14
	 */
	private boolean judgeUserStop(String stsDtl) {
		return (stsDtl.charAt(EnumCodeShDefine.STS_DTL_SEVENTEEN - 1) == '1');
	}

	/**
	 * 账务连带停
	 * 
	 * @param stsDtl
	 * @return
	 */
	private boolean judgeAcctStopInGx(String stsDtl) {
		return (stsDtl.charAt(EnumCodeShDefine.GX_STS_DTL_SEVENTEEN - 1) == '1');
	}

	/**
	 * 信誉度停
	 * 
	 * @param stsDtl
	 * @return
	 */
	private boolean judgeCreditStopInGx(String stsDtl) {
		return (stsDtl.charAt(EnumCodeShDefine.GX_STS_DTL_SIXTEEN - 1) == '1');
	}

	/**
	 * 信誉度停
	 * 
	 * @param stsDtl
	 * @return
	 */
	private boolean judgeNoFeeStopInGx(String stsDtl) {
		return (stsDtl.charAt(EnumCodeShDefine.GX_STS_DTL_FIFTEEN - 1) == '1');
	}

	/**
	 * 判断是否保留期停<br>
	 * BOSS位串(从左到右)，第16位是保留期停 wukl 2012-6-14
	 */
	private boolean judgeRetainStop(String stsDtl) {
		return (stsDtl.charAt(EnumCodeShDefine.STS_DTL_SIXTEEN - 1) == '1');
	}

	/**
	 * 判断是否日保号停<br>
	 * BOSS位串(从左到右)，第11位是日保号停 wukl 2012-6-14
	 */
	private boolean judgeDayStop(String stsDtl) {
		return (stsDtl.charAt(EnumCodeShDefine.STS_DTL_ELEVEN - 1) == '1');
	}

	/**
	 * 判断是否欠费停<br>
	 * BOSS位串(从左到右)，第20位是日保号停 wukl 2012-6-14
	 */
	private boolean judgeFeeStop(String stsDtl) {
		return (stsDtl.charAt(EnumCodeShDefine.STS_DTL_TWENTY - 1) == '1');
	}

	/**
	 * 将CRM 64位串转成BOSS 20位串 ，其对应关系参考boot.xml<br>
	 * 1 用户 2 租机租卡 3 担保人 4 欠费 5 欠费预销户 6 呼出限制 7 内部 8 特殊 9 网络部 10 一证多机 11 预销户<br>
	 * 12 易通卡有效期 13 保留期 14 短信保留 15 恶意呼叫 16 欠费风险 17 城管 18 测试卡用户 19 举报 20 新入网额度 21
	 * 后付费信用<br>
	 * 22 国际漫游高额监控 23 被盗/遗失 24 违法类垃圾短信举报 25 骚扰电话举报 26 垃圾短信（有害） 27 日保号 28 商广类举报<br>
	 * <br>
	 * BOSS20位串（从左置右）：<br>
	 * 1 其他停、11日保号停、12 特殊停、13 同证件停、14 新入网额度停、<br>
	 * 15 有效期停、16 保留期停、17 用户停、18 呼出限制停、19 欠费预销户停、20 欠费停<br>
	 * wukl 2012-6-2
	 */
	private String getBossOsStsDtlFromCRM(String crmOsStsDtl) {
		// 初始化BOSS20位停机位串
		StringBuilder sb = new StringBuilder(EnumCodeExDefine.INIT_STS_DTL);
		char[] charArr = crmOsStsDtl.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			Integer index = getBossEnumWithCrmEnum(i + 1);
			if (index == null || sb.charAt(index - 1) == '1') {
				continue;
			}

			sb.setCharAt(index - 1, crmOsStsDtl.charAt(i));
		}

		return sb.toString();
	}

	/**
	 * 将DB表的OsStsDtl由VARCHAR2(64)转成NUMBER(6)<br>
	 * wukl 2012-6-1
	 */
	private Integer getDBOsStsDtl(String iOsStsDtl) {
		// 再将其转成十进制数值返回
		return Integer.valueOf(iOsStsDtl, 2);
	}

	/**
	 * 将DB表的OsStsDtl由NUMBER(6)转成VARCHAR2(64) wukl 2012-6-1
	 */
	private String getBossOsStsDtlFromDB(Integer dbOsStsDtl) {
		// 将DB中的十进制转成二进制
		String stsDtl = Integer.toBinaryString(dbOsStsDtl);

		// 获取需要补0的长度
		int len = EnumCodeExDefine.INIT_STS_DTL.length() - stsDtl.length();

		// 不足20位的，补0
		String prefix = EnumCodeExDefine.INIT_STS_DTL.substring(0, len);

		return prefix + stsDtl;
	}

	private void createProd(IUserStatus iUserStatus, Date validDate, Date expireDate, Integer specId) {
		ProdExCmp prodExCmp = context.getCmp(ProdExCmp.class);
		// 查询停机保号产品对应的销售品编号
		PmProductOffering offer = prodExCmp.queryOfferingByBusiFlag(specId);

		if (null == offer) {
			throw IMSUtil.throwBusiException(ErrorCodeExDefine.LIFECYCLE_LACK_UNIDENTITY_OFFERING, specId);
		}

		Long offeringId = offer.getProductOfferingId().longValue();

		if (offer.getIsMain().intValue() == EnumCodeDefine.PRODUCT_MAIN) {
			throw IMSUtil.throwBusiException(ErrorCodeExDefine.LIFECYCLE_PROD_CONFIG_ERROR, offeringId);
		}

		IProduct prod = new IProduct();
		prod.setUserId(iUserStatus.getUserId());
		prod.setOfferId(offeringId);
		prod.setValidDate(validDate);
		prod.setOperType(EnumCodeExDefine.OPER_TYPE_INSERT);
		prod.setProductId(DBUtil.getSequence(CoProd.class));
		prod.setCommitDate(iUserStatus.getCommitDate());
		prod.setExpireDate(expireDate);
		prod.setProductStatus(1);
		prod.setProductType(0);
		prod.setSoNbr(iUserStatus.getSoNbr());

		List<IProduct> prodList = new ArrayList<IProduct>();
		prodList.add(prod);

		prodExCmp.operateProduct(prodList, null, null);
		// 清空，产品订购进入
		context.setAllProdMap(null);

	}

	/**
	 * 销户(STS = 1009)时，将用户相关信息删除；同时删除路由的数据，并将其移到历史路由中
	 * wukl 2012-12-7
	 */
	public void removeRouter4DeleteUser() {
		// 删除用户的时候，会往context中设置销户的标识
		if (!UserAcctRelHelper.isPoolUser(context)) {
			return;
		}

		IUserStatus iUserStatus = (IUserStatus) context.getSingleIDataObjectList(UserStsHandler.class);
		if (CommonUtil.isIn(iUserStatus.getSts().intValue(), ColCodeDefine.LIFECYCLE_POOL_CANCEL)) {
			imsLogger.debug("******* pool user will remove user info and router ********");
			// 删除用户相关信息
			ITableUtil.setValue2ContextHolder(null, null, iUserStatus.getUserId());
			this.deleteByCondition(CaAccountRes.class, iUserStatus.getExpireDate(), new DBCondition(CaAccountRes.Field.resourceId,
					iUserStatus.getUserId()));
			this.deleteByCondition(CmResourceMonitor.class, new DBCondition(CmResourceMonitor.Field.resourceId, iUserStatus.getUserId()));
			List<CmUserEnterprise> enterpriseList = this.deleteByCondition(CmUserEnterprise.class, iUserStatus.getExpireDate(),
					new DBCondition(CmUserEnterprise.Field.resourceId, iUserStatus.getUserId()));
			this.deleteByCondition(CmUserOrderConfirm.class, new DBCondition(CmUserOrderConfirm.Field.resourceId, iUserStatus.getUserId()));
			this.deleteByCondition(CmUserMarket.class, new DBCondition(CmUserMarket.Field.resourceId, iUserStatus.getUserId()));
			this.deleteByCondition(CmUserMap.class, new DBCondition(CmUserMap.Field.resourceId, iUserStatus.getUserId()));
			this.deleteByCondition(CmUserPbx.class, new DBCondition(CmUserPbx.Field.resourceId, iUserStatus.getUserId()));
			this.deleteByCondition(CmUserPortability.class, new DBCondition(CmUserPortability.Field.resourceId, iUserStatus.getUserId()));
			this.deleteByCondition(CmUserShareProd.class, new DBCondition(CmUserShareProd.Field.resourceId, iUserStatus.getUserId()));

			List<CoSplitPayRel> splitList = this.query(CoSplitPayRel.class,
					new DBCondition(CoSplitPayRel.Field.objectId, iUserStatus.getUserId()), new DBCondition(CoSplitPayRel.Field.objectType,
							EnumCodeDefine.PROD_OBJECTTYPE_DEV));
			if (CommonUtil.isNotEmpty(splitList)) {
				for (CoSplitPayRel rel : splitList) {
					ITableUtil.setValue2ContextHolder(null, null, iUserStatus.getUserId());
					this.deleteByCondition(CoSplitPayRel.class, context.getRequestDate(), new DBCondition(CoSplitPayRel.Field.productId,
							rel.getProductId()));

					ITableUtil.setValue2ContextHolder(null, rel.getReguidObjectId(), null);
					this.deleteByCondition(CoProd.class, context.getRequestDate(),
							new DBCondition(CoProd.Field.productId, rel.getProductId()));
					this.deleteByCondition(CoSplitCharValue.class, context.getRequestDate(), new DBCondition(
							CoSplitCharValue.Field.productId, rel.getProductId()));

				}
			}

			if (CommonUtil.isNotEmpty(enterpriseList)) {
				for (CmUserEnterprise prise : enterpriseList) {
					if(prise.getSpCode() != null){
						context.getCmp(RouterCmp.class).deleteEnterpriseRoute(iUserStatus.getUserId(), prise.getServiceCode(),
								prise.getOperatorCode(),prise.getSpCode(), iUserStatus.getExpireDate());
					}else{
						context.getCmp(RouterCmp.class).deleteEnterpriseRoute(iUserStatus.getUserId(), prise.getServiceCode(),
								prise.getOperatorCode(),null, iUserStatus.getExpireDate());
					}
					
				}
			}

			// 删除路由信息
			// String phoneId =
			// context.getExtendParam(ConstantExDefine.POOL_PHONE_ID) == null ?
			// "" : context
			// .getExtendParam(ConstantExDefine.POOL_PHONE_ID).toString().trim();
			// context.getCmp(RouterCmp.class).deleteRouter(phoneId,
			// iUserStatus.getUserId());
		}
	}

	/**
	 * 销户(STS = 1009)时，将用户相关信息删除；同时删除路由的数据，并将其移到历史路由中
	 * wukl 2012-12-7
	 */
	public void removeRouter4Termi() {
		// 删除用户的时候，会往context中设置销户的标识
		if (!UserAcctRelHelper.isPoolUser(context)) {
			return;
		}

		IUserStatus iUserStatus = (IUserStatus) context.getSingleIDataObjectList(UserStsHandler.class);
		if (CommonUtil.isIn(iUserStatus.getSts().intValue(), ColCodeDefine.LIFECYCLE_POOL_CANCEL)) {
			// 该逻辑拆分出来 2014-3-14
			String phoneId = context.getExtendParam(ConstantExDefine.POOL_PHONE_ID) == null ? "" : context
					.getExtendParam(ConstantExDefine.POOL_PHONE_ID).toString().trim();
			String imsi = context.getExtendParam(ConstantExDefine.POOL_IMSI) == null ? "" : context
					.getExtendParam(ConstantExDefine.POOL_IMSI).toString().trim();
			context.getCmp(RouterCmp.class).deleteRouter(phoneId, iUserStatus.getUserId(), imsi, iUserStatus.getExpireDate());

		}
	}

	private Integer getBossEnumWithCrmEnum(int index) {
		if (ProjectUtil.is_CN_XZ()) {
			return EnumMappingInitBean.getBossEnumWithCrmEnum("XZ_OS_STS_DTL", index);
		}
		return EnumMappingInitBean.getBossEnumWithCrmEnum("OS_STS_DTL", index);
	}

}