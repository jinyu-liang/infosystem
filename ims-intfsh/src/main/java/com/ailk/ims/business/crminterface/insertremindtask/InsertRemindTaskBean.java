package com.ailk.ims.business.crminterface.insertremindtask;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertRemindTaskResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.InsertRemindTaskIn;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * @description:营业员临时工作通知
 * @author caohm5
 * @date:2012-09-20
 *
 */
public class InsertRemindTaskBean extends BaseBusiBean {

    private InsertRemindTaskIn req;
    
    @Override
    public void init(Object... input) throws BaseException
    {
        req = (InsertRemindTaskIn) input[0];
        
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (null==req)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "InsertRemindTaskIn");
        }
        if(CommonUtil.isEmpty(req.getStaff_code())){
        	IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "staff_code");
        }
        if(CommonUtil.isEmpty(req.getSender_code())){
        	IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sender_code");
        }
        if(CommonUtil.isEmpty(req.getRemind_grade())){
        	IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "remind_code");
        }
        if(CommonUtil.isEmpty(req.getRemind_title())){
        	IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "remind_title");
        }
        if(CommonUtil.isEmpty(req.getRemind_content())){
        	IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "remind_content");
        }
        if(CommonUtil.isEmpty(req.getRemind_time())){
        	IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "remind_time");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	return null;
  
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        Map<String, String> busiParams = getCrmInsertRemindTaskIn(req);
        long doneCode = this.getContext().getDoneCode();
        HttpJsonToCrmService.insertRemindTask(String.valueOf(doneCode), busiParams);
        return null;
    }
    /**
     * @param req 帐管入参
     * @return crm需要的入参
     */
    private Map<String, String> getCrmInsertRemindTaskIn(InsertRemindTaskIn req) {
    	
    	Map<String, String> busiParams = new HashMap<String, String>();
    	busiParams.put("StaffCode", req.getStaff_code());
    	busiParams.put("SenderCode", req.getSender_code());
    	busiParams.put("RemainGrade", req.getRemind_grade());
    	busiParams.put("RemindTitle", req.getRemind_title());
    	busiParams.put("RemindContent", req.getRemind_content());
    	busiParams.put("RemindTime", req.getRemind_time());
    	busiParams.put("RemindId", req.getRemind_id());
		return busiParams;
	}

	@Override
    public BaseResponse createResponse(Object[] result)
    {   
    	Do_insertRemindTaskResponse resp = new Do_insertRemindTaskResponse();
        return resp;
    }

    @Override
    public void destroy()
    {
    }
}
