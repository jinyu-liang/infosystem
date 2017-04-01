package com.ailk.ims.business.imsInterface.uservalidtity;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.LifeCycleByAcctChgComponent;
import com.ailk.ims.component.ResValidComponent;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.CanclePrePaidUserValidityIn;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_canclePrePaidUserValidityResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;

/**
 * @descripton:取消预付费卡余额有效期
 * @author caohm5
 * @date:2012-08-20 20:57
 * @date 2012-11-5 zhangzj3 性能优化
 */
public class CanclePrePaidUserValidityBean extends BaseBusiBean {

	private CanclePrePaidUserValidityIn req;
	 private LifeCycleByAcctChgComponent lifeChgCmp=null;
	@Override
	public void init(Object... input) throws BaseException {
		req = (CanclePrePaidUserValidityIn) input[0];
		lifeChgCmp=context.getComponent(LifeCycleByAcctChgComponent.class);
	}

	@Override
	public void validate(Object... input) throws BaseException {
		if (null == req) {
			
			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"CanclePrePaidUserValidityIn");
		}
		if (!CommonUtil.isValid(req.getResource_id())&&CommonUtil.isEmpty(req.getPhone_id())) {
			
			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resource_id、phone_id ");
		}
		Integer operType=req.getOper_type();
		
		if(operType==null||
				(operType!=EnumCodeShDefine.RE_COVER_CM_VALIDITY&&operType!=EnumCodeShDefine.CANCLE_CM_VALIDITY)){
			
			 IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "oper_type", "0、1");
		}
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
	    return null;
	}
	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
	
		Check3HParamReturn bean = context.getComponent(CheckComponentSH.class).check3HParam(null, null, req.getResource_id(), req.getPhone_id());
		Integer flag=null;
		if(req.getOper_type()==EnumCodeShDefine.RE_COVER_CM_VALIDITY){
			
				flag=context.getComponent(ResValidComponent.class).checkIsCancleResVlid(req.getResource_id(),req.getPhone_id(),false,bean);
				if(flag==null){
					lifeChgCmp.updateUserValidity4SH(bean.getUserId(),bean.getAcctId(), null, EnumCodeShDefine.RE_COVER_CM_VALIDITY);
					
					flag=EnumCodeShDefine.CM_VALIDITY_YES_CANCLE;
				}
				
		}
		//取消
		else if(req.getOper_type()==EnumCodeShDefine.CANCLE_CM_VALIDITY){
			
				flag=context.getComponent(ResValidComponent.class).checkIsCancleResVlid(req.getResource_id(),req.getPhone_id(),true,bean);
				if(flag==null){
					
					lifeChgCmp.updateUserValidity4SH(bean.getUserId(),bean.getAcctId(), null, EnumCodeShDefine.CANCLE_CM_VALIDITY);
					
					flag=EnumCodeShDefine.CM_VALIDITY_YES_CANCLE;
				}
		}
    	return new Object[]{flag};
	}
	@Override
	public BaseResponse createResponse(Object[] result) {
		Integer flag=(Integer)result[0];
		Do_canclePrePaidUserValidityResponse respn = new Do_canclePrePaidUserValidityResponse();
		respn.setNflag(flag);
		return respn;
	}
	@Override
	public void destroy() {
	}
}
