package com.ailk.ims.business.crminterface.valuedcard;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_operValuedCardStatusResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.SOperValuedCardStatusReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SOperValuedCardStatusRst;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 操作有价卡状态
 * @author wangyh3
 * @Date 2012-8-9
 */
public class OperValuedCardStatusBean extends BaseBusiBean
{
    private SOperValuedCardStatusReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (SOperValuedCardStatusReq) input[0];

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (null==req)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sOperValuedCardStatusReq");
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
        long doneCode = this.getContext().getDoneCode();
        List<SOperValuedCardStatusRst> infoList = HttpJsonToCrmService.operValuedCardStatus(String.valueOf(doneCode), req);
        return new Object[] { infoList };
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<SOperValuedCardStatusRst> infoList = (List<SOperValuedCardStatusRst>) result[0];
        Do_operValuedCardStatusResponse resp = new Do_operValuedCardStatusResponse();
        resp.setListOperResult(infoList);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

}
