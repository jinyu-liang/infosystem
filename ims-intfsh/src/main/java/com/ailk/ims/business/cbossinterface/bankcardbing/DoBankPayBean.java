package com.ailk.ims.business.cbossinterface.bankcardbing;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.BankPayRsp;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.httpjson.service.HttpJsonToCbossService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.openbilling.persistence.imscnsh.entity.BankPayReq;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_bankPayResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 调用CBOSS的银行卡绑定充值接口(JSON)
 * @Description
 * @author wangyh3
 * @Date 2012-7-31
 */
public class DoBankPayBean extends BaseBusiBean
{
    private BankPayReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (BankPayReq) input[0];
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
        BankPayRsp bankPayRsp = HttpJsonToCbossService.doBankPay(CommonUtilSh.genCbossBusiJson("BankPayReq", req));
        
        return new Object[] { bankPayRsp };
    }

    @Override
    public BaseResponse createResponse(Object[] input)
    {
        Do_bankPayResponse resp = new Do_bankPayResponse();
        resp.setBankPayRsp((BankPayRsp) input[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }


}
