package com.ailk.ims.business.crminterface.insertservicerecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertServiceRecordResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.ServiceRecord;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * @description:加强BOSS应用系统客户信息记录的优化需求
 * @author caohm5
 * @date:2012-07-26
 *
 */
public class InsertServiceRecordBean extends BaseBusiBean {
	private ServiceRecord service;
	@Override
	public void init(Object... input) throws BaseException {
		service=(ServiceRecord)input[0];
	}
	@Override
	public void validate(Object... input) throws BaseException {
		//请求参数不能为空
		if(null==service){
			 IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "ServiceRecord");
		}
		//进行业务操作时,所使用的查询类型(必填)
		if(CommonUtil.isEmpty(service.getQuery_type())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "query_type");
		}
		//服务号码(必填)
		if(CommonUtil.isEmpty(service.getQuery_num())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "query_num");
		}
		//TODO
		if(CommonUtil.isEmpty(service.getDoman_id())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "doman_id");
		}
		//受理状态(必填)
		if(CommonUtil.isEmpty(service.getService_status())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "service_status");
		}
		//TODO
		if(CommonUtil.isEmpty(service.getSession_id())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "session_id");
		}
		//TODO
		if(CommonUtil.isEmpty(service.getDone_status())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "done_status");
		}
		//受理来源(必填)
		if(CommonUtil.isEmpty(service.getService_source())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "service_source");
		}
		
		// 业务编码(必填)
		if(CommonUtil.isEmpty(service.getBusi_id())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "busi_id");
		}
	}
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		return null;
	}
	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		Map<String, String> busiParams = new HashMap<String, String>();
		busiParams.put("qryType", service.getQuery_type());
		busiParams.put("qryNum", service.getQuery_num());
		busiParams.put("domanId", service.getDoman_id());
		busiParams.put("serviceStatus", service.getService_status());
		busiParams.put("sessionId", service.getSession_id());
		busiParams.put("doneStatus", service.getDone_status());
		busiParams.put("serviceSource", service.getService_source());
		busiParams.put("busiId", service.getBusi_id());
        long doneCode = this.getContext().getDoneCode();
        HttpJsonToCrmService.insertServiceRecord(String.valueOf(doneCode), busiParams);
        return null;
	}
	@Override
	public BaseResponse createResponse(Object[] result) {
		Do_insertServiceRecordResponse resp = new Do_insertServiceRecordResponse();
        return resp;
	}
	@Override
	public void destroy() {
		
	}
}
