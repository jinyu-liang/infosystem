package com.ailk.imssh.order.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.ColCodeDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.scandeal.deal.BaseDeal;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrder;
import com.ailk.openbilling.persistence.cust.entity.IIsmpOper;
import com.ailk.openbilling.persistence.itable.entity.IUserOrder;
import com.ailk.openbilling.persistence.pm.entity.PmBusiTypeRule;

/**
 * @Date 2013-11-27
 * @author dingjiang3
 */
public class UserOrderCmp extends BaseCmp {
	private ImsLogger imsLogger = new ImsLogger(UserOrderCmp.class);
	public void createUserOrder(IUserOrder iUserOrder) {
		CmUserOrder order = convert(iUserOrder);
		setHFLTimes4UserOrder(order);
		super.insert(order);
	}

	public void updateUserOrder(IUserOrder iUserOrder) {
		
		CmUserOrder cmUserOrder = convert(iUserOrder);
		this.updateMode3(cmUserOrder,new DBCondition(CmUserOrder.Field.instId, iUserOrder.getInstId()));
		
		
		/**
		this.deleteByCondition(CmUserOrder.class, new DBCondition(CmUserOrder.Field.resourceId, iUserOrder.getUserId()),
				new DBCondition(CmUserOrder.Field.servType, iUserOrder.getServType()), 
				new DBCondition(CmUserOrder.Field.spCode, iUserOrder.getSpCode()),
				new DBCondition(CmUserOrder.Field.operatorCode, iUserOrder.getOperatorCode()), 
				new DBCondition(CmUserOrder.Field.thirdMsisdn, iUserOrder.getThirdMsisdn()), 
				new DBCondition(CmUserOrder.Field.orderSts, EnumCodeExDefine.USER_ORDER_STS_2,Operator.NOT_EQUALS),
				new DBCondition(CmUserOrder.Field.validDate,iUserOrder.getValidDate()));
		*/
	}

	public void deleteUserOrder(IUserOrder iUserOrder) {
		CmUserOrder order = new CmUserOrder();
		order.setExpireDate(iUserOrder.getExpireDate());
        
		this.deleteByCondition(CmUserOrder.class,iUserOrder.getExpireDate(), new DBCondition(CmUserOrder.Field.instId, iUserOrder.getInstId()),
				new DBCondition(CmUserOrder.Field.validDate,iUserOrder.getExpireDate(),Operator.LESS_EQUALS));
		DBUtil.deleteByCondition(CmUserOrder.class, new DBCondition(CmUserOrder.Field.instId, iUserOrder.getInstId()),
				new DBCondition(CmUserOrder.Field.validDate, iUserOrder.getExpireDate(),Operator.GREAT));
	}

	public CmUserOrder convert(IUserOrder iUserOrder) {

		CmUserOrder cmUserOrder = new CmUserOrder();
		cmUserOrder.setResourceId(iUserOrder.getUserId());
		cmUserOrder.setBusiType(iUserOrder.getBusiType());
		cmUserOrder.setMsisdn(iUserOrder.getMsisdn());
		cmUserOrder.setServType(iUserOrder.getServType());
		cmUserOrder.setSpCode(iUserOrder.getSpCode());
		cmUserOrder.setInstId(iUserOrder.getInstId());
		cmUserOrder.setRelInsId(iUserOrder.getRelInsId());
		cmUserOrder.setThirdMsisdn(iUserOrder.getThirdMsisdn());
		cmUserOrder.setProperty(iUserOrder.getProperty());
		cmUserOrder.setRegionCode(iUserOrder.getRegionCode());
		cmUserOrder.setOrderSts(iUserOrder.getOrderSts());
		cmUserOrder.setHisFirstOrderTime(iUserOrder.getHisFirstOrderTime());
		cmUserOrder.setChangeTime(iUserOrder.getChangeTime());
		cmUserOrder.setFirstOrderTime(iUserOrder.getFirstOrderTime());
		cmUserOrder.setLastOrderTime(iUserOrder.getLastOrderTime());
		cmUserOrder.setSrcType(iUserOrder.getSrcType());
		cmUserOrder.setExtendFlag(iUserOrder.getExtendFlag());

		if( iUserOrder.getValidDate().getTime()==iUserOrder.getFirstOrderTime().getTime()){

			if(iUserOrder.getOrderSts()==0){
				cmUserOrder.setIsNew(1);

			}
		}else{
			cmUserOrder.setIsNew(0);

		}
		//cmUserOrder.setIsNew(iUserOrder.getIsNew());
		cmUserOrder.setSubType(iUserOrder.getSubType());
		cmUserOrder.setServiceCode(iUserOrder.getServiceCode());
		cmUserOrder.setProductId(iUserOrder.getProductId());
		cmUserOrder.setThirdResourceId(iUserOrder.getThirdResourceId());
		cmUserOrder.setBizTypeCode(iUserOrder.getBizTypeCode());
		cmUserOrder.setBillType(iUserOrder.getBillType());
		cmUserOrder.setSoDate(iUserOrder.getCommitDate());
		cmUserOrder.setSoNbr(context.getSoNbr());
		
		cmUserOrder.setValidDate(iUserOrder.getValidDate());
		cmUserOrder.setOperatorCode(iUserOrder.getOperatorCode());
		cmUserOrder.setCreateDate(iUserOrder.getCommitDate());
		
		cmUserOrder.setExpireDate(iUserOrder.getExpireDate());

		return cmUserOrder;

	}

	public void beforeHandle(List<? extends DataObject> dataList) {
		// ITableUtil.cleanRequestParamter();
		// IUserPbx iUserPbx = (IUserPbx) dataList.get(0);
		// delete_noValid(CmUserPbx.class, new
		// DBCondition(CmUserPbx.Field.resourceId, iUserPbx.getUserId()),
		// new DBCondition(CaResourceInv.Field.validDate,
		// iUserInv.getCommitDate(), Operator.GREAT), new DBCondition(
		// CaResourceInv.Field.expireDate, iUserInv.getCommitDate(),
		// Operator.GREAT));

	}

	/**
	 * @author songcc
	 *         信息管理在处理完IUserOrder之后，将数据插入计费通知表I_ISMP_OPER中
	 *         通知计费上发Mdb
	 * @param iUserOrder
	 */
	public void createIsmpOper(Long userId, Integer regionCode,Integer procSts) {

		IIsmpOper iIsmpOper = new IIsmpOper();
		iIsmpOper.setOwnerId(userId);
		iIsmpOper.setProcSts(procSts);// 默认为0
		if (regionCode != null) {
			iIsmpOper.setRegionCode(regionCode);
		} else {
			User3hBean userBean = context.get3hTree().loadUser3hBean(userId);
			iIsmpOper.setRegionCode(userBean.getUser().getRegionCode());
		}
		iIsmpOper.setDoneCode(context.getSoNbr());
		iIsmpOper.setCommitDate(context.getCommitDate());
		iIsmpOper.setDelayDate(context.getCommitDate());
		super.insert(iIsmpOper);
	}

	/**
	 * 
	 * @param userId
	 *            用户编号
	 * @param oldSts
	 *            老用户状态
	 * @param oldOsSts
	 *            老用户停开机状态
	 * @param newSts
	 *            新用户状态
	 * @param newOsSts
	 *            新用户停开机状态
	 * @param oldAcctStopFlag
	 *            老用户停机状态位是否包含账务连带停、信誉度停、欠费停
	 * @param newAcctStopFlag
	 *            新用户停机状态位是否包含账务连带停、信誉度停、欠费停
	 */
	public void dealOrderStsByUserLifecycle(Long userId, Integer oldSts, Integer oldOsSts, Integer newSts, Integer newOsSts,
			boolean oldAcctStopFlag, boolean newAcctStopFlag) {
		List<CmUserOrder> resultList = new ArrayList<CmUserOrder>();
		if (isUserStatusTerminal(newSts)) {// 变成销户
			// 7 将状态0，1，3，4，5改成2，失效时间改为立即失效
			//先删除状态是2,未来生效的数据
			DBUtil.deleteByCondition(CmUserOrder.class, new DBCondition(CmUserOrder.Field.resourceId, userId),
					new DBCondition(CmUserOrder.Field.orderSts, EnumCodeExDefine.USER_ORDER_STS_2),
					new DBCondition(CmUserOrder.Field.validDate,context.getCommitDate(),Operator.GREAT),
					new DBCondition(CmUserOrder.Field.expireDate,context.getCommitDate(),Operator.GREAT));
			
			int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_4, EnumCodeExDefine.USER_ORDER_STS_3,
					EnumCodeExDefine.USER_ORDER_STS_5, EnumCodeExDefine.USER_ORDER_STS_0, EnumCodeExDefine.USER_ORDER_STS_1 };
			resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_2, context.getCommitDate(), con);

			if (CommonUtil.isNotEmpty(resultList)) {
				createIsmpOper(userId, resultList.get(0).getRegionCode(),0);
			}
			return;
		}

		if (!isUserStatusPretTerminal(oldSts)) {
			if (isUserStatusPretTerminal(newSts)) {
				//3 正常变成预销户
				List<CmUserOrder> orderList = this.queryAllCmUserOrder(userId);
				if (CommonUtil.isEmpty(orderList)) {
					return;
				}
				List<PmBusiTypeRule> ruleList = this.queryPmBusiTypeRuleByBusiType(orderList);
				List<Integer> deleteList = new ArrayList<Integer>();
				List<Integer> suspendList = new ArrayList<Integer>();
				for (CmUserOrder order : orderList) {
					int result = getIsTruncPreByBusiType(order.getBusiType(), ruleList);
					if (result == 1) {
						deleteList.add(order.getBusiType());
					} else {
						suspendList.add(order.getBusiType());
					}
				}
				// 如果返回1 退订   则将状态（0，1，3，4）改成2
				if (CommonUtil.isNotEmpty(deleteList)) {
					int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_4, EnumCodeExDefine.USER_ORDER_STS_3,
							EnumCodeExDefine.USER_ORDER_STS_1, EnumCodeExDefine.USER_ORDER_STS_0 };
					resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_2, context.getCommitDate(), con, deleteList);
				}
				if (CommonUtil.isNotEmpty(suspendList)) {
					// 如果返回0 截断 , 则将状态（0，3，4）改为5
					int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_3, EnumCodeExDefine.USER_ORDER_STS_0,
							EnumCodeExDefine.USER_ORDER_STS_4 };
					if (CommonUtil.isEmpty(resultList)) {
						resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_5, null, con, suspendList);
					} else {
						updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_5, null, con, suspendList);
					}
				}
			} else if(isUserStatusKeep(newSts)){
				//变成保留期停 0,3 变成5
				int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_3, EnumCodeExDefine.USER_ORDER_STS_0 };
				resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_5, null, con);
			}else{// 正常-正常
				if (isOsStsDtlActive(oldOsSts) && isOsStsSuspend(newOsSts)) {// os_sts由10变成11或者12
					// 1 判断是否包含用户停，如果包含账户等停 将状态0，3改成4 否则将状态0，3改成5
					if (newAcctStopFlag) {
						int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_3, EnumCodeExDefine.USER_ORDER_STS_0 };
						resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_4, null, con);
					} else {
						int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_3, EnumCodeExDefine.USER_ORDER_STS_0 };
						resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_5, null, con);
					}
				} else if (isOsStsSuspend(oldOsSts) && isOsStsDtlActive(newOsSts)) {// os_sts由11或者12变成10
					// 4状态正常，用户停开机状态由停机状态变成开机状态 将状态4，5改成3
					int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_4, EnumCodeExDefine.USER_ORDER_STS_5 };
					resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_3, null, con);
				} else if (isOsStsSuspend(oldOsSts) && isOsStsSuspend(newOsSts)) {
					// 2 状态正常，os_sts 停机变停机
					// 老停开机状态不包含账户等停，新停开机状态包含账户等停 则将0，3改成4
					// 老停开机状态包含账户等停，新停开机状态不包含账户停 则将0，3改成5
					if (!oldAcctStopFlag && newAcctStopFlag) {
						int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_0, EnumCodeExDefine.USER_ORDER_STS_3 };
						resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_4, null, con);
					} else if (oldAcctStopFlag && !newAcctStopFlag) {
						int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_0, EnumCodeExDefine.USER_ORDER_STS_3 };
						resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_5, null, con);
					}
				}
			}
		} else {
			if (!isUserStatusPretTerminal(newSts)) {// 预销户-->正常
				if (isOsStsDtlActive(newOsSts)) {// os_sts也正常
					// 5， 用户状态由预销户变成正常状态，同时停开机状态也变成正常 将状态4，5改成3
					int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_4, EnumCodeExDefine.USER_ORDER_STS_5 };
					resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_3, null, con);
				} else {// os_sts不正常
					// 6， 用户状态由预销户变成正常状态，停开机状态还是停机状态 停机状态包含账户停等将状态0，3，5改成4
					// 停机状态不包含账户停等： 将状态0，3改成5
					if (newAcctStopFlag) {
						int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_0, EnumCodeExDefine.USER_ORDER_STS_3,
								EnumCodeExDefine.USER_ORDER_STS_5 };
						resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_4, null, con);
					} else {
						int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_0, EnumCodeExDefine.USER_ORDER_STS_3 };
						resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_5, null, con);
					}
				}
			}else if(isUserStatusKeep(newSts)){
				int[] con = new int[] { EnumCodeExDefine.USER_ORDER_STS_0, EnumCodeExDefine.USER_ORDER_STS_3 };
				resultList = updateCmUserOrder(userId, EnumCodeExDefine.USER_ORDER_STS_5, null, con);
			}
		}

		if (CommonUtil.isNotEmpty(resultList)) {
			createIsmpOper(userId, resultList.get(0).getRegionCode(),0);
		}
	}

	/**
	 * 是否预销户
	 * 
	 * @param sts
	 * @return
	 */
	private boolean isUserStatusPretTerminal(Integer sts) {
		return CommonUtil.isIn(sts, ColCodeDefine.LIFECYCLE_PRE_TERMINAL);
	}

	/**
	 * 是否保留期
	 * 
	 * @param sts
	 * @return
	 */
	private boolean isUserStatusKeep(Integer sts) {
		return CommonUtil.isIn(sts, ColCodeDefine.LIFECYCLE_KEEP);
	}

	/**
	 * 是否销户
	 * 
	 * @param sts
	 * @return
	 */
	private boolean isUserStatusTerminal(Integer sts) {
		return CommonUtil.isIn(sts, ColCodeDefine.LIFECYCLE_POOL_CANCEL);
	}

	/**
	 * 停开机是否是正常状态
	 * 
	 * @param osSts
	 * @return
	 */
	private boolean isOsStsDtlActive(Integer osSts) {
		return osSts == EnumCodeDefine.LIFECYCLE_OSSTS_ACTIVE;
	}

	/**
	 * 停开机状态是否停机状态
	 * 
	 * @param osSts
	 * @return
	 */
	private boolean isOsStsSuspend(Integer osSts) {
		return !isOsStsDtlActive(osSts);
	}

	private List<CmUserOrder> updateCmUserOrder(Long userId, int tagOrderSts, Date expireDate, int[] orgOrderSts) {
		CmUserOrder dmOrder = new CmUserOrder();
		dmOrder.setOrderSts(tagOrderSts);
		if (expireDate != null) {
			dmOrder.setExpireDate(context.getCommitDate());
		}
		return this.updateByCondition(dmOrder, new DBCondition(CmUserOrder.Field.resourceId, userId), new DBCondition(
				CmUserOrder.Field.orderSts, orgOrderSts, Operator.IN), new DBCondition(CmUserOrder.Field.orderSts,
				EnumCodeExDefine.USER_ORDER_STS_2, Operator.NOT_EQUALS));
	}

	/**
	 * 根据busi_type退订或者暂停相关业务
	 * 
	 * @param userId
	 * @param tagOrderSts
	 * @param expireDate
	 * @param orgOrderSts
	 * @param busiTypeList
	 * @return
	 */
	private List<CmUserOrder> updateCmUserOrder(Long userId, int tagOrderSts, Date expireDate, int[] orgOrderSts, List<Integer> busiTypeList) {
		CmUserOrder dmOrder = new CmUserOrder();
		dmOrder.setOrderSts(tagOrderSts);
		if (expireDate != null) {
			dmOrder.setExpireDate(context.getCommitDate());
		}
		return this.updateByCondition(dmOrder, new DBCondition(CmUserOrder.Field.resourceId, userId), new DBCondition(
				CmUserOrder.Field.orderSts, orgOrderSts, Operator.IN), new DBCondition(CmUserOrder.Field.busiType, busiTypeList,
				Operator.IN), new DBCondition(CmUserOrder.Field.orderSts, EnumCodeExDefine.USER_ORDER_STS_2, Operator.NOT_EQUALS));
	}

	private List<CmUserOrder> queryAllCmUserOrder(Long userId) {
		return this.query(CmUserOrder.class, new DBCondition(CmUserOrder.Field.resourceId, userId));
	}

	private int getIsTruncPreByBusiType(int busiType, List<PmBusiTypeRule> resultList) {
		if (CommonUtil.isEmpty(resultList)) {
			return 0;
		}
		for (PmBusiTypeRule rule : resultList) {
			if (rule.getBusiType() == null || rule.getBusiType() != busiType) {
				continue;
			}
			if (rule.getIsTruncPre() != null) {
				return rule.getIsTruncPre();
			} else {
				return 0;
			}
		}
		return 0;
	}

	private List<PmBusiTypeRule> queryPmBusiTypeRuleByBusiType(List<CmUserOrder> btList) {
		Set<Integer> conSet = new HashSet<Integer>();
		for (CmUserOrder order : btList) {
			conSet.add(order.getBusiType());
		}
		return DBUtil.query(PmBusiTypeRule.class, new DBCondition(PmBusiTypeRule.Field.busiType, conSet, Operator.IN));
	}
	
	/**
	 * 新增的时候，要重新设置三个时间
	 * 
	 * @param order
	 * @return
	 */
	private void setHFLTimes4UserOrder(CmUserOrder order) {
		// 先将三个时间统一设置为生效时间
		order.setHisFirstOrderTime(order.getValidDate());
		order.setLastOrderTime(order.getValidDate());
		order.setFirstOrderTime(order.getValidDate());
		Date beginDate = DateUtils.monthBegin(order.getValidDate());
//		Date lastBeginDate=DateUtil.offsetDate(beginDate, Calendar.DAY_OF_MONTH, -1);
		CaCycleRun run = DBUtil.querySingle(CaCycleRun.class, new DBCondition(CaCycleRun.Field.acctId, context.getCmp(RouterCmp.class)
				.queryAcctIdByUserId(order.getResourceId())));
		if (run != null) {
			beginDate = DateUtil.getFormattedDate(String.valueOf(run.getCurrCycleBegin()));
//			lastBeginDate = DateUtil.getFormattedDate(String.valueOf(run.getLastCycleBegin()));
		}
		// 查询数据获取数据
		List<CmUserOrder> orderList = DBUtil.query(CmUserOrder.class,new OrderCondition[] { (new OrderCondition(true,
				CmUserOrder.Field.lastOrderTime)) }, null, new DBCondition(CmUserOrder.Field.resourceId, order.getResourceId()),
				new DBCondition(CmUserOrder.Field.servType, order.getServType()),
				new DBCondition(CmUserOrder.Field.spCode, order.getSpCode()),
				new DBCondition(CmUserOrder.Field.operatorCode, order.getOperatorCode()));
		if (CommonUtil.isEmpty(orderList)) {
			return ;
		}
		
		//设置历史首次订购时间
		order.setHisFirstOrderTime(orderList.get(0).getHisFirstOrderTime());
		//设置首次订购时间  去当前账期生效的第一条记录
		for(CmUserOrder item : orderList){
			if(item.getExpireDate().after(beginDate)){
				order.setFirstOrderTime(item.getLastOrderTime());
				break;
			}
		}
		
	}

}
