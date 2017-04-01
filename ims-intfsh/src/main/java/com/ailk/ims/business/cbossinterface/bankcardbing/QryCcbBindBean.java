package com.ailk.ims.business.cbossinterface.bankcardbing;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.CcbQryBindRsp;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.httpjson.service.HttpJsonToCbossService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.openbilling.persistence.imscnsh.entity.CcbQryBindReq;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_qryCcbBindResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 调用CBOSS的（建行）查询授权结果(JSON)
 * @Description
 * @author wangyh3
 * @Date 2012-7-31
 */
public class QryCcbBindBean extends BaseBusiBean
{
    private CcbQryBindReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (CcbQryBindReq) input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        CcbQryBindRsp qryBindRsp = HttpJsonToCbossService.doCcbQryBind(CommonUtilSh.genCbossBusiJson("QryBindReq", req));
        
        return new Object[] { qryBindRsp };
    }

    @Override
    public BaseResponse createResponse(Object[] input)
    {
        Do_qryCcbBindResponse resp = new Do_qryCcbBindResponse();
        resp.setQryBindRsp((CcbQryBindRsp) input[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }


}
