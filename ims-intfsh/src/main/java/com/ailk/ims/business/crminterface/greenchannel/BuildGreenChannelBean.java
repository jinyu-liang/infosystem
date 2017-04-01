package com.ailk.ims.business.crminterface.greenchannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_buildGreenChannelResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.GreenChannelReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * @description:VC充值绿色通道搭建
 * @author caohm5
 * @date:2012-11-13
 *
 */
public class BuildGreenChannelBean extends BaseBusiBean {
	private GreenChannelReq req;
    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(GreenChannelReq)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if (req==null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "GreenChannelReq");
        }
    	if(CommonUtil.isEmpty(req.getPhone_id())){
    		IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phone_id");
    	}
    	if(CommonUtil.isEmpty(req.getNotify_flag())){
    		IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "notify_flag");
    	}
    	if(CommonUtil.isEmpty(req.getDone_date())){
    		IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "done_date");
    	}
    	if(CommonUtil.isEmpty(req.getData_source())){
    		IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "data_source");
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
        context.getComponent(CheckComponentSH.class).check3HParam(null, null, null, req.getPhone_id());
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("BILL_ID", req.getPhone_id());
        busiParams.put("NOTIFY_FLAG", req.getNotify_flag());
        busiParams.put("DONE_DATE", req.getDone_date());
        busiParams.put("DATA_SOURCE", req.getData_source());
        busiParams.put("OP_ID", req.getOrg_id());
        busiParams.put("ORG_ID", req.getOrg_id());
        long doneCode = this.getContext().getDoneCode();
        HttpJsonToCrmService.builGreenChannel(String.valueOf(doneCode), busiParams);
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Do_buildGreenChannelResponse respn=new Do_buildGreenChannelResponse();
        return respn;
    }
    @Override
    public void destroy()
    {
    }
}
