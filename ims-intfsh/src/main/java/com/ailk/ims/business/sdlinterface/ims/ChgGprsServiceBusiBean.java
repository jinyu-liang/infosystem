package com.ailk.ims.business.sdlinterface.ims;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SImsServiceNotify;
import com.ailk.openbilling.persistence.jd.entity.ImsServiceNotify;
/**
 * #60738
 * @author gaoqc5
 * 针对15G封顶停服务，GPRS+WLAN 封顶停服务等等业务停服务的实现；
 * 信息管理需要提供接口给ABM调用，通过反向表同步给CRM。
 * 反向接口表包括用户编号serv_id、操作类型action_type。'
 *
 */
public class ChgGprsServiceBusiBean extends BaseBusiBean
{

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
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        SImsServiceNotify imsServiceNotify = (SImsServiceNotify) input[0];
        list.add(context.get3hTree().loadUser3hBean(new Long(imsServiceNotify.getUserId())));
        return list;

    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SImsServiceNotify sNotify = (SImsServiceNotify) input[0];
        context.getComponent(BaseComponent.class).insert(converToImsServiceNotify(sNotify));
        return null;
    }

    private ImsServiceNotify converToImsServiceNotify(SImsServiceNotify sNotify)
    {
        ImsServiceNotify notify = new ImsServiceNotify();
        int type = sNotify.getActionType();
        notify.setActionType(CommonUtil.IntegerToLong(type));
        notify.setCommitDate(sNotify.getCommitDate());
        notify.setUserId(sNotify.getUserId());
        return notify;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_sdlResponse();
    }

    @Override
    public void destroy()
    {

    }

}
