package com.ailk.ims.business.sdlinterface.ims;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SImsIrGprsAlarm;
import com.ailk.openbilling.persistence.jd.entity.ImsIrGprsAlarm;

/**
 * 国际GPRS漫游的反向通知
 * 
 * @Description
 * @author wukl
 * @Date 2012-11-21
 * @Date 2012-12-06 wukl 当账本可用余额小于GPRS国际漫游费用累计金额时，才插入反向表
 */
public class GprsAlarmBusiBean extends BaseBusiBean
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
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SImsIrGprsAlarm sNotify = (SImsIrGprsAlarm) input[0];
        
        /*XXX
        PocketExtIn in = new PocketExtIn();
        in.setResourceId(sNotify.getUserId());
        
        User3hBean userBean = context.get3hTree().loadUser3hBean(sNotify.getUserId());
        in.setAcctId(userBean.getAcctId());
        in.setQryType("1");//查询账本 :1
        AmsResponseHelper out = context.getComponent(AmsInterfaceComponent.class).queryPocketExt(in);
        PocketExtOut pocket = (PocketExtOut) out.getPocket() ;
        */
        
//        Long balance = pocket.getUsableBalance();
        Long balance=0L;
        
        imsLogger.debug("***** usable balance = " + balance + "; gprs fee = " + sNotify.getGprsFee());
        
        //当账本可用余额小于GPRS国际漫游费用累计金额时，才插入反向表
        if (balance.longValue() >= sNotify.getGprsFee())
        {
            return null;
        }
        
        ImsIrGprsAlarm alarm = new ImsIrGprsAlarm();
        alarm.setBillCycleBegin(sNotify.getBillCycleBegin());
        alarm.setBillCycleEnd(sNotify.getBillCycleEnd());
        alarm.setUserId(sNotify.getUserId());
        alarm.setPhoneId(sNotify.getPhoneId());
        alarm.setGprsFee(sNotify.getGprsFee());
        alarm.setGprsTraffic(sNotify.getGprsTraffic());
        //计费传入的时间格式为20121205151308
        alarm.setCreateDate(ConvertUtil.sdlLong2JavaDate(sNotify.getCreateDate()));
        context.getComponent(BaseComponent.class).insert(alarm);
        
        return null;
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
