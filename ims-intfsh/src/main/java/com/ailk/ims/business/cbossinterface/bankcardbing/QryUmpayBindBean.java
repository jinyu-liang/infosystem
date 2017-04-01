package com.ailk.ims.business.cbossinterface.bankcardbing;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.UmpayQryBindRsp;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.httpjson.service.HttpJsonToCbossService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_qryUmpayBindResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.UmpayQryBindReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 调用CBOSS的（联动优势）查询绑定关系(JSON)
 * 
 * @Description
 * @author wangyh3
 * @Date 2012-7-31
 */
public class QryUmpayBindBean extends BaseBusiBean
{
    private UmpayQryBindReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (UmpayQryBindReq) input[0];
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
        UmpayQryBindRsp qryBindRsp = HttpJsonToCbossService.doUmpayQryBind(CommonUtilSh.genCbossBusiJson("QryBindReq", req));

        return new Object[] { qryBindRsp };
    }

    @Override
    public BaseResponse createResponse(Object[] input)
    {
        Do_qryUmpayBindResponse resp = new Do_qryUmpayBindResponse();
        resp.setQryBindRsp((UmpayQryBindRsp) input[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }

}
