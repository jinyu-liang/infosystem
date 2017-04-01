package com.ailk.ims.business.lifeCycleByAcctChg.busi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.LifeCycleByAcctChgComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.imsinner.entity.Do_LifeCycleByAcctChgResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SLifeCycleByAcctChgReq;
import com.ailk.openbilling.persistence.imsinner.entity.SLifeCycleChg;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;

/**
 * @Description: 更改用户有效接口
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tangjl5
 * @Date 2011-9-22
 * @Date 2012-2-2 用户级账本和账户级账本的有效期变更
 */
public class LifeCycleByAcctChgBusiBean extends BaseCancelableBusiBean {
	private LifeCycleByAcctChgComponent lifeCycleByAcctChgCmp = null; 
	@Override
	public void cancel(CancelOrderInfo cancelInfo) {

	}

	@Override
	public void init(Object... input) throws IMSException {
		lifeCycleByAcctChgCmp = context.getComponent(LifeCycleByAcctChgComponent.class);
	}

	@Override
	public void validate(Object... input) throws IMSException {
		SLifeCycleByAcctChgReq lifeCycleReq = (SLifeCycleByAcctChgReq)input[0];
		
		if (lifeCycleReq.getTriggerEventType() == null)
		{
			throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_LIFECYCLE_TRIGGER_EVENT_TYPE_IS_NULL);
		}
		
		if (!CommonUtil.isValid(lifeCycleReq.getReduceDays()) && !CommonUtil.isValid(lifeCycleReq.getExtendDays()))
		{
		    throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "extend_days, reduce_days");
		}
		
		if (lifeCycleReq.getForceFlag() != null && lifeCycleReq.getForceFlag() != EnumCodeDefine.NOT_REDUCE_VALIDITY_FORCE &&
		        lifeCycleReq.getForceFlag() != EnumCodeDefine.REDUCE_VALIDITY_FORCE_TO_REQDATE && lifeCycleReq.getForceFlag() != EnumCodeDefine.REDUCE_VALIDITY_FORCE
		        && lifeCycleReq.getForceFlag() != EnumCodeDefine.ADD_VALIDITY_NOT_FORCE)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "forceFlag","0,1,2,3,null");
        }
		
		if ((null == lifeCycleReq.getBalanceType()) || (lifeCycleReq.getBalanceType() != EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_USER && lifeCycleReq.getBalanceType() != EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_SINGLE_BALANCE))
		{
		    throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "balanceType","0,1");
		}
		
		if (EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_USER == lifeCycleReq.getBalanceType() && !CommonUtil.isValid(lifeCycleReq.getResource_id()))
		{
		    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_UPDATE_SINGLEBALANCE_USER_IS_NULL);
		}
		
		if (EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_SINGLE_BALANCE == lifeCycleReq.getBalanceType() && !CommonUtil.isValid(lifeCycleReq.getAcct_id()))
		{
		    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_UPDATE_SINGLEBALANCE_ACCT_IS_NULL);
		}
		
	}

	@Override
	public Object[] doBusiness(Object... input) throws IMSException {
		SLifeCycleByAcctChgReq lifeCycleReq = (SLifeCycleByAcctChgReq)input[0];
		List<Object[]> returnData = lifeCycleByAcctChgCmp.updateValidityByTopUp(lifeCycleReq, null);
		return new Object[] {returnData};
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		Do_LifeCycleByAcctChgResponse resp = new Do_LifeCycleByAcctChgResponse();
		if (!CommonUtil.isEmpty(result)) {
			if (null != result[0]) {
				@SuppressWarnings("unchecked")
				List<Object[]> resLifeCycleData =  (List<Object[]>)result[0];
				List<SLifeCycleChg> lifeCycleChgList = new ArrayList<SLifeCycleChg>();
				if (!CommonUtil.isEmpty(resLifeCycleData))
				{
					for (Object[] tmp : resLifeCycleData)
					{
						SLifeCycleChg lifeCycleChg = new SLifeCycleChg();
						if (null != tmp[0])
						{
						    CmResLifecycle resLifeCycle = (CmResLifecycle) tmp[0];
	                        lifeCycleChg.setExpireDate(resLifeCycle.getExpireDate());
	                        lifeCycleChg.setValidDate(resLifeCycle.getValidDate());
	                        lifeCycleChg.setSts(resLifeCycle.getSts());
						}
						
						if (null != tmp[1])
						{
							lifeCycleChg.setExtendDays(((Integer)tmp[1]));
						}
						
						if (null != tmp[2])
						{
						    Map<String, Date> periodDate = (Map<String, Date>)tmp[2];
						    lifeCycleChg.setActiveValidDate(periodDate.get(ConstantDefine.TOP_UP_ACTIVE_VALIDDATE));
						    lifeCycleChg.setActiveExpireDate(periodDate.get(ConstantDefine.TOP_UP_ACTIVE_EXPIREDATE));
						    lifeCycleChg.setSuspendExpireDate(periodDate.get(ConstantDefine.TOP_UP_SUSPEND_EXPIREDATE));
						    lifeCycleChg.setDisableExprieDate(periodDate.get(ConstantDefine.TOP_UP_DISABLE_EXPIREDATE));
						}
						
						lifeCycleChgList.add(lifeCycleChg);
					}
				}
				
				resp.setLifeCycleChgList(lifeCycleChgList);
			}
		}
		return resp;
	}

	@Override
	public void destroy() {
		lifeCycleByAcctChgCmp = null;
	}

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
	    SLifeCycleByAcctChgReq lifeCycleReq = (SLifeCycleByAcctChgReq)input[0];
	    lifeCycleReq.getResource_id();
	    lifeCycleReq.getAcct_id();
	    List<IMS3hBean> ims3HBeanList = new ArrayList<IMS3hBean>();
	    IMS3hBean ims3HBean = context.get3hTree().load3hBean(null, lifeCycleReq.getAcct_id(), lifeCycleReq.getResource_id(), null);
	    if (null != ims3HBean)
	    {
	        ims3HBeanList.add(ims3HBean);
	    }
	    return ims3HBeanList;
	}

}
