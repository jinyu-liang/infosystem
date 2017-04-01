package com.ailk.ims.business.query;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.shinner.entity.Do_queryNextBillTypeResponse;

/**
 * 查询用户下周期的预后付属性接口
 * 
 * @Description
 * @author wukl
 * @Date 2012-11-8
 */
public class QryUserNextBillTypeBean extends BaseBusiBean
{
    private Integer creditSignControl;

    @Override
    public void init(Object... input) throws BaseException
    {

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
        Long acctId = (Long) input[0];
        List<CaAccount> acctList = null;
        if (CommonUtil.isValid(acctId))
        {
            acctList = context.getComponent(AccountQuery.class).query(CaAccount.class,
                    new DBCondition(CaAccount.Field.acctId, acctId));
            if (CommonUtil.isEmpty(acctList))
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_NOTEXIST, acctId);
            }
        }

        if (acctList.size() == 1)
        {//只有一条数据
            creditSignControl = acctList.get(0).getCreditSignControl();
        }
        else
        {
            //当存在下周期的数据时，将下周期的数据返回
            for (CaAccount acct : acctList)
            {
                if (acct.getValidDate().after(context.getRequestDate()))
                {
                    creditSignControl = acct.getCreditSignControl();
                }
            }
        }

        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryNextBillTypeResponse resp = new Do_queryNextBillTypeResponse();
        resp.setCreditSignControl(creditSignControl);
        return resp;
    }

    @Override
    public void destroy()
    {

    }
}
