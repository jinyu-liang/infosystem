package com.ailk.ims.business.imsInterface.billstepinfo;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertImsBillStepInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.ImsBillStepInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.jd.entity.ImsBillStepInfo;

/**
 * @description:维护IMS_BILL_STEP_INFO表信息
 * @author xieqr
 * @date:2012-10-8
 */

public class InsertBillStepInfoBean extends BaseBusiBean
{
    private ImsBillStepInfoReq req;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (ImsBillStepInfoReq) input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (null == req)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "imsBillStepInfoReq");
        }
        if (!CommonUtil.isValid(req.getBill_date()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "bill_date");
        }
        if (CommonUtil.isEmpty(req.getBill_step_name()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "bill_step_name");
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
        ImsBillStepInfo info = getImsBillStepInfo(req);
        DBUtil.insert(info);
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_insertImsBillStepInfoResponse();
    }

    @Override
    public void destroy()
    {

    }

    private ImsBillStepInfo getImsBillStepInfo(ImsBillStepInfoReq req)
    {
        ImsBillStepInfo info = new ImsBillStepInfo();
        info.setBillDate(req.getBill_date());
        info.setBillStepName(req.getBill_step_name());
        info.setBillSts(req.getBill_sts());
        info.setCreateDate(IMSUtil.formatDate(req.getCreate_date(), null));
        return info;
    }
}
