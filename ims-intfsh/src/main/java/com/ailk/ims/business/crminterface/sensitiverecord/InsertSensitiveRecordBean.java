package com.ailk.ims.business.crminterface.sensitiverecord;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertSensitiveRecordResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.SensitiveRecord;
import com.ailk.openbilling.persistence.imscnsh.entity.SensitiveRecordIn;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * @description:插入敏感信息
 * @author wangyh3
 * @date:2012-08-15
 *
 */
public class InsertSensitiveRecordBean extends BaseBusiBean {
	private SensitiveRecordIn qryIn;
	@Override
	public void init(Object... input) throws BaseException {
	    qryIn=(SensitiveRecordIn)input[0];
	}
	@Override
	public void validate(Object... input) throws BaseException {
		//请求参数不能为空
		if(null==qryIn){
			 IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SensitiveRecordIn");
		}
        if(CommonUtil.isEmpty(qryIn.getQuery_type())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "busi_type");
        }
        if(CommonUtil.isEmpty(qryIn.getFun_id())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "fun_id");
		}
        if(CommonUtil.isEmpty(qryIn.getPhone_id())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phone_id");
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
		busiParams.put("busiType", qryIn.getQuery_type());
		busiParams.put("funId", qryIn.getFun_id());
	    busiParams.put("phoneId", qryIn.getPhone_id());
        long doneCode = this.getContext().getDoneCode();
        List<SensitiveRecord> rtnList = HttpJsonToCrmService.insertSensitiveRecord(String.valueOf(doneCode), busiParams);
        
        return new Object[] { rtnList };
	}

    @SuppressWarnings("unchecked")
    @Override
	public BaseResponse createResponse(Object[] result) {
	    Do_insertSensitiveRecordResponse resp = new Do_insertSensitiveRecordResponse();
	    resp.setSensitiveRecordList((List<SensitiveRecord>) result[0]);
        return resp;
	}
	@Override
	public void destroy() {
		
	}
}
