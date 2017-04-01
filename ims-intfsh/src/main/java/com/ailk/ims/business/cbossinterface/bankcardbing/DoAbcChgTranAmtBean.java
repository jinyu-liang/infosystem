package com.ailk.ims.business.cbossinterface.bankcardbing;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.ChgTranAmtRsp;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.httpjson.service.HttpJsonToCbossService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.openbilling.persistence.imscnsh.entity.ChgTranAmtReq;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_abcChgTranAmtResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 调用CBOSS的（农行）修改充值金额(JSON)
 * @Description
 * @author wangyh3
 * @Date 2012-7-31
 */
public class DoAbcChgTranAmtBean extends BaseBusiBean
{
    private ChgTranAmtReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (ChgTranAmtReq) input[0];
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
        ChgTranAmtRsp chgTranAmtRsp = HttpJsonToCbossService.doChgTranAmt(CommonUtilSh.genCbossBusiJson("ChgTranAmtReq", req));
        
        return new Object[] { chgTranAmtRsp };
    }

    @Override
    public BaseResponse createResponse(Object[] input)
    {
        Do_abcChgTranAmtResponse resp = new Do_abcChgTranAmtResponse();
        resp.setChgTranAmtRsp((ChgTranAmtRsp) input[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }


}
