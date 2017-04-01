package com.ailk.ims.business.query;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_qryDayStopFlagResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QryDayStopReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;

/**
 * 根据用户编号，手机好，查询日保号停机标志
 * 
 * @Description
 * @author xieqr
 * @Date 2012-10-16
 */
public class QueryDayStopFlagBean extends BaseBusiBean
{

    private QryDayStopReq req;
    private Long userId;
    private String phoneId;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (QryDayStopReq) input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (null == req)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QryDayStopReq");
        }
        if (!CommonUtil.isValid(req.getResource_id()) && CommonUtil.isEmpty(req.getPhone_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resource_id、phone_id");
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
        userId = req.getResource_id();
        phoneId = req.getPhone_id();
        // @date 2012-10-16 zhangzj3 去掉3hbean,增加数据完整性判断
        Check3HParamReturn info = context.getComponent(CheckComponentSH.class).check3HParam(null, null, userId, phoneId);
        userId = info.getUserId();
        CmResLifecycle life = info.getResLifeCycle();
        // 停开机状态
        boolean bl = false;
        if (null != life)
        {
            bl = CommonUtilSh.check(life, EnumCodeShDefine.STS_DTL_ELEVEN - 1);
        }

        return new Object[] { bl };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        boolean bl = (Boolean) result[0];
        Do_qryDayStopFlagResponse resp = new Do_qryDayStopFlagResponse();
        if(bl){
            //日保号停机用户
            resp.setFlag((short)1);
        }else{
            //非日保号停机用户
            resp.setFlag((short)2);
        }
        
        return resp;
    }

    @Override
    public void destroy()
    {

    }
}
