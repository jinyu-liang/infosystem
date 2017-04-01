package com.ailk.ims.business.crminterface.bindactive;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryBindActiveInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.SBindActiveInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.SQryBindActiveReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 查询用户硬捆绑活动信息
 * 
 * @Description
 * @author xieqr
 * @Date 2012-7-2
 */
public class QueryBindActiveInfoBean extends BaseBusiBean
{
    private SQryBindActiveReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (SQryBindActiveReq) input[0];

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (null==req)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sQryBindActiveReq");
        }
        if (!CommonUtil.isValid(req.getResource_id()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resource_id");
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
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("resource_id", req.getResource_id().toString());
        busiParams.put("begin_date", req.getBegin_date());
        busiParams.put("end_date", req.getEnd_date());
        long doneCode = this.getContext().getDoneCode();
        List<SBindActiveInfo> infoList = HttpJsonToCrmService.getBindActiveInfo(String.valueOf(doneCode), busiParams);
        return new Object[] { infoList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<SBindActiveInfo> infoList = (List<SBindActiveInfo>) result[0];
        Do_queryBindActiveInfoResponse resp = new Do_queryBindActiveInfoResponse();
        resp.setSBindActiveInfoList(infoList);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

}
