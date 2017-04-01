package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.CheckComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctExemptionFlagResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;

/**
 * @author caohm5 create @2012-03-22 查询账户的免催免停标示
 */

public class QueryAcctExemptionFlagBean extends BaseCancelableBusiBean
{
    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
    }

    @Override
    public void init(Object... input) throws BaseException
    {
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        Long acctId = (Long) input[0];
        // 账户编号不能为空
        if (!CommonUtil.isValid(acctId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {   
        Long accoutId = (Long) input[0];
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        if (CommonUtil.isValid(accoutId))
        {
            list.add(context.get3hTree().loadAcct3hBean(accoutId));
        }
        return list;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        Integer exemptionType = null;
        Long acctId = (Long) input[0];
        context.getComponent(CheckComponent.class).checkAcctId(acctId);
        CaNotifyExempt caExemption = context.getComponent(AccountQuery.class).queryCaNotificationExemption(acctId);
        if (caExemption == null)
        {
            // 如果表里面没有记录返回默认值：免催免停
            exemptionType = new Integer(-1);
        }
        else
        {
            Integer exemptionTemp = caExemption.getExemptionType();
            if (exemptionTemp.intValue() == EnumCodeDefine.EXEMPTION_NO_CALL)
            {
                exemptionType = new Integer(1);
            }
            else if (exemptionTemp.intValue() == EnumCodeDefine.EXEMPTION_NO_STOP)
            {
                exemptionType = new Integer(2);
            }
            else if (exemptionTemp.intValue() == EnumCodeDefine.EXEMPTION_NO_CALL_AND_NO_STOP)
            {
                exemptionType = new Integer(3);
            }
            else
            {
                exemptionType = new Integer(0);
            }
        }
        return new Object[] { exemptionType };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Integer exemptionType = (Integer) result[0];
        Do_queryAcctExemptionFlagResponse rspn = new Do_queryAcctExemptionFlagResponse();
        rspn.setExemption_flag(exemptionType);
        return rspn;
    }

    @Override
    public void destroy()
    {
    }
}
