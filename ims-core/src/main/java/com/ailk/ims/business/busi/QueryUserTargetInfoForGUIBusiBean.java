package com.ailk.ims.business.busi;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.GroupComponent;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserTargetStsInfoRespons;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserTargetStsInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.SUserStsInfo;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.sys.entity.SysCyclePatternTransfer;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCyclePattern;
import com.ailk.openbilling.persistence.sys.entity.SysStateDef;

/**
 * @Description: 获取用户目标状态信息for GUI
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author tangjl5
 * @Date 2011-12-9
 * @modify by xieqr at 2012-02-16 add userId
 * @Date 2012-3-26 tangjl5 页面不展示事件是15的用户流转状态
 */
public class QueryUserTargetInfoForGUIBusiBean extends BaseCancelableBusiBean {

	@Override
	public void cancel(CancelOrderInfo cancelInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(Object... input) throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Object... input) throws IMSException {
		SQueryUserTargetStsInfoReq req = (SQueryUserTargetStsInfoReq) input[0];
		if (!CommonUtil.isValid(req.getPhoneId())
				&& !CommonUtil.isValid(req.getUserId())) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,
					"phoneId or userId");
		}
	}

	@Override
	public Object[] doBusiness(Object... input) throws IMSException {
		SQueryUserTargetStsInfoReq req = (SQueryUserTargetStsInfoReq) input[0];
		String phoneId = req.getPhoneId();
		Long userId = req.getUserId();
		User3hBean bean = context.get3hTree().loadUser3hBean(userId,
				phoneId);

		BaseLifeCycleComponent lifeCycleCmp = context
				.getComponent(BaseLifeCycleComponent.class);
		// Map<String, Object> objectMap =
		// lifeCycleCmp.getAllInfoByPhoneIdOrUserId(req.getPhoneId(), null);
		CmResource res = bean.getUser();
		CaAccount acct = bean.getAccount();
		CmCustomer cust = bean.getCustomer();
		CmResLifecycle curLifeCycle = bean.getUserLifeCycle();

		Do_queryUserTargetStsInfoRespons response = new Do_queryUserTargetStsInfoRespons();
		response.setPhoneId(bean.getPhoneId());
		response.setUserId(res.getResourceId());
		response.setBillingType(res.getBillingType());
		response.setSts(curLifeCycle.getSts());
		response.setUnbillingFlag(curLifeCycle.getUnbillingFlag());
		response.setFrauldFlag(curLifeCycle.getFrauldFlag());
		response.setReratingFlag(curLifeCycle.getReratingFlag());
		response.setUserrequestFlag(curLifeCycle.getUserrequestFlag());
		response.setOsSts(curLifeCycle.getOsSts());
		response.setValidDate(DateUtil.formatDate(curLifeCycle.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
		//2012-11-02 linzt SingleBalance 用户查询CM_validity表失效日期
		if(acct.getBalanceType()==EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_SINGLE_BALANCE){
			CmResValidity validity = context.getComponent(BaseLifeCycleComponent.class).queryAcctValidity(acct.getAcctId());
			response.setExpireDate(DateUtil.formatDate(validity.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
			response.setCreateDate(DateUtil.formatDate(validity.getCreateDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
		else{
			response.setExpireDate(DateUtil.formatDate(curLifeCycle.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
			response.setCreateDate(DateUtil.formatDate(curLifeCycle.getCreateDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
		}
	    
		// 后付费直接返回后付费的状态列表
		if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_POSTPAID
				|| res.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID
				|| res.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID) {
			List<SysStateDef> stateDefList = context.getComponent(
					GroupComponent.class).queryPostPaidStsList();
			List<SUserStsInfo> stsInfoList = new ArrayList<SUserStsInfo>();
			if (CommonUtil.isNotEmpty(stateDefList)) {
				for (SysStateDef stsDef : stateDefList) {
					SUserStsInfo stsInfo = new SUserStsInfo();
					stsInfo.setSts(stsDef.getSts());
					stsInfo.setStsName(stsDef.getName());
					stsInfoList.add(stsInfo);
				}
			}
			response.setNewStsInfoList(stsInfoList);
			return new Object[] { response };
		}

		SysGroupCyclePattern groupCyclePatten = lifeCycleCmp
				.queryCaGroupCyclePattern(res, cust, acct);

		if (null == groupCyclePatten) {
			// 用户的brand_id、res_type、billing_type，客户的cust_type、cust_class，帐户的account_type未匹配到组
			throw IMSUtil.throwBusiException(
					ErrorCodeDefine.USER_NOT_MATCHING_GROUP,
					res.getResourceId());
		}

		List<SysCyclePatternTransfer> cyclePatternTrs = lifeCycleCmp
				.getUserTargetStsByStsFrom(groupCyclePatten.getPatternId(),
						curLifeCycle.getSts(), curLifeCycle.getOsSts());

		if (CommonUtil.isNotEmpty(cyclePatternTrs)) {
			List<SUserStsInfo> stsInfoList = new ArrayList<SUserStsInfo>();
			for (SysCyclePatternTransfer transfer : cyclePatternTrs) {
			    // 页面不展示事件是15的用户流转状态
			    if (transfer.getTriggerEventType() != EnumCodeDefine.LIFECYCLE_EVENT_VALIDITYCHANGE)
			    {
			        SUserStsInfo userStsInfo = new SUserStsInfo();
	                userStsInfo.setSts(transfer.getStsTo());
	                String stsName = lifeCycleCmp.querySysStateDefName(transfer
	                        .getStsTo());
	                if (null != stsName)
	                    userStsInfo.setStsName(stsName);
	                stsInfoList.add(userStsInfo);
			    }
			}
			response.setNewStsInfoList(stsInfoList);
		}

		return new Object[] { response };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {

		return (Do_queryUserTargetStsInfoRespons) result[0];
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		SQueryUserTargetStsInfoReq req = (SQueryUserTargetStsInfoReq) input[0];
		
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().loadUser3hBean(req.getUserId(),req.getPhoneId()));
		return list;
	}

}
