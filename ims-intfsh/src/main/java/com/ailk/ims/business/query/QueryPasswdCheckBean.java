package com.ailk.ims.business.query;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryPasswdCheckResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.sys.entity.SysBankInfo;

/**
 * @Description 根据BANK_ID查询该银行是否需要进行密码校验
 * @author wangyh3
 * @Date 2012-6-22
 */
public class QueryPasswdCheckBean extends BaseBusiBean
{
    private Integer bankId;

    @Override
    public void init(Object... input) throws BaseException
    {
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        bankId = (Integer) input[0];
        if (!CommonUtil.isValid(bankId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "bank_id");
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
        // 从基础数据缓存中查找
        // SysBankInfo record = DBConfigInitBean.getSingleCached(SysBankInfo.class, new
        // CacheCondition(SysBankInfo.Field.bankId, bankId),
        // new CacheCondition(SysBankInfo.Field.isNeedPasswd, 0));

        SysBankInfo record = DBUtil.querySingle(SysBankInfo.class, new DBCondition(SysBankInfo.Field.bankId, bankId),
                new DBCondition(SysBankInfo.Field.isNeedPasswd, 0));

        return new Object[] { new Integer((record != null) ? 1 : 0) };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Integer status = (Integer) result[0];
        Do_queryPasswdCheckResponse resp = new Do_queryPasswdCheckResponse();
        if (status != null)
        {
            resp.setStatus(status);
        }
        return resp;
    }

    @Override
    public void destroy()
    {
    }

}
