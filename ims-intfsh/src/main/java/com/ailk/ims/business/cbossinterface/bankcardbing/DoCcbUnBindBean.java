package com.ailk.ims.business.cbossinterface.bankcardbing;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.CcbUnBindRsp;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.httpjson.service.HttpJsonToCbossService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.openbilling.persistence.imscnsh.entity.CcbUnBindReq;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_ccbUnBindResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 调用CBOSS的（建行）发起解除绑定(JSON)
 * @Description
 * @author wangyh3
 * @Date 2012-7-31
 */
public class DoCcbUnBindBean extends BaseBusiBean
{
    private CcbUnBindReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (CcbUnBindReq) input[0];
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
        CcbUnBindRsp unBindRsp = HttpJsonToCbossService.doCcbUnBind(CommonUtilSh.genCbossBusiJson("UnBindReq", req));
        
        return new Object[] { unBindRsp };
    }

    @Override
    public BaseResponse createResponse(Object[] input)
    {
        Do_ccbUnBindResponse resp = new Do_ccbUnBindResponse();
        resp.setUnBindRsp((CcbUnBindRsp) input[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }


}
