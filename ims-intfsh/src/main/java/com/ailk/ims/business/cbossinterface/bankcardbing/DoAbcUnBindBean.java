package com.ailk.ims.business.cbossinterface.bankcardbing;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.TerminationRsp;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.httpjson.service.HttpJsonToCbossService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_abcUnBindResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.TerminationReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 调用CBOSS的（农行）解除绑定关系(JSON)
 * @Description
 * @author wangyh3
 * @Date 2012-7-31
 */
public class DoAbcUnBindBean extends BaseBusiBean
{
    private TerminationReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (TerminationReq) input[0];
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
        TerminationRsp terminationRsp = HttpJsonToCbossService.doUnbingRelation(CommonUtilSh.genCbossBusiJson("TerminationReq", req));
        
        return new Object[] { terminationRsp };
    }

    @Override
    public BaseResponse createResponse(Object[] input)
    {
        Do_abcUnBindResponse resp = new Do_abcUnBindResponse();
        resp.setTerminationRsp((TerminationRsp) input[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }


}
