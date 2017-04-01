package com.ailk.ims.business.cbossinterface.bankcardbing;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.TtradeDetailRsp;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.httpjson.service.HttpJsonToCbossService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_qryAbcTtradeDetailResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.TtradeDetailReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 调用CBOSS的（农行）查询交易明细(JSON)
 * @Description
 * @author wangyh3
 * @Date 2012-7-31
 */
public class QryAbcTtradeDetailBean extends BaseBusiBean
{
    private TtradeDetailReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (TtradeDetailReq) input[0];
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
        TtradeDetailRsp tradeDetailRsp = HttpJsonToCbossService.queryTtradeDetail(CommonUtilSh.genCbossBusiJson("TtradeDetailReq", req));
        
        return new Object[] { tradeDetailRsp };
    }

    @Override
    public BaseResponse createResponse(Object[] input)
    {
        Do_qryAbcTtradeDetailResponse resp = new Do_qryAbcTtradeDetailResponse();
        resp.setTtradeDetailRsp((TtradeDetailRsp) input[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }


}
