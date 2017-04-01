package com.ailk.imssh.common.util;

import java.util.Calendar;
import java.util.Date;

import com.ailk.easyframe.route.client.RouterClient;
import com.ailk.ims.util.SpringUtil;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.flow.dispatch.service.IDispatchService;
import com.ailk.imssh.common.flow.dispatch.service.IErrDealService;
import com.ailk.imssh.common.flow.dispatch.service.ISendToMemberService;
import com.ailk.imssh.common.flow.scandeal.deal.IBaseDeal;
import com.ailk.imssh.common.manual.handler.IHandler;
import com.ailk.imssh.common.manual.handler.ITransferHandler;
import com.ailk.imssh.common.manual.handler.IUserDepositHandler;


/**
 * @Description:数据接口模式定义的spring配置bean
 * @author wangjt
 * @Date 2012-7-9
 */
public class SpringExUtil
{
    public static RouterClient getRouteSearchService()
    {
        return (RouterClient) SpringUtil.getBean(ConstantExDefine.ROUTER_SERVICE_BEAN);
    }

    public static IHandler getManualHandler()
    {
        return (IHandler) SpringUtil.getBean("manualHandler");
    }
    
    public static ITransferHandler getTransferHandler()
    {
        return (ITransferHandler) SpringUtil.getBean("transferHandler");
    }
    
    public static IUserDepositHandler getUserDepositHandler()
    {
        return (IUserDepositHandler) SpringUtil.getBean("userDepositHandler");
    }
    
    public static ITransferHandler getTransferCountyHandler()
    {
        return (ITransferHandler) SpringUtil.getBean("transferCountyHandler");
    }

    public static IBaseDeal getBaseDeal()
    {
        return (IBaseDeal) SpringUtil.getBean("baseDeal");
    }
    
    //Add Err 2015-10-26
    public static IErrDealService getErrDealService()
    {
        return (IErrDealService) SpringUtil.getBean("errDealService");
    }

    public static IDispatchService getDispatchService()
    {
        return (IDispatchService) SpringUtil.getBean("dispatchService");
    }

    // 2013-06-04 liming15 群组成员提醒
    public static ISendToMemberService getSendToMemberService()
    {
        return (ISendToMemberService) SpringUtil.getBean("sendSmsToMemberService");
    }
    

    // 2012-12-24 gaoqc5 账管信用度的接口
    /*
    public static CreditService getCreditService()
    {
        return (CreditService) SpringUtil.getBean(ConstantExDefine.CREDIT_SERVICE_BEAN);
    }

    public static DepositPocketBusiService getDepositPocketBusiService()
    {
        return (DepositPocketBusiService) SpringUtil.getBean(ConstantExDefine.POCKET_SERVICE_BEAN);
    }
*/
    public static RouterClient getRouteService()
    {
        return SpringUtil.getRouteSearchService();
//        RouterClient routeService = (RouterClient) SpringUtil.getBean("routerClient");
//        return routeService;
    }
     
   
    /**
     * 取传入时间的下月一号零点
     * @param date
     * @return
     */
    public static Date nextMonthFirstDate(Date date) { 

        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1); 

        calendar.add(Calendar.MONTH, 1); 
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime(); 

    } 
    /*TODO
    public static SalInterfaceIms getSalInterfaceIms()
    {
        SalInterfaceIms salInterfaceIms = (SalInterfaceIms) SpringUtil.getBean("salInterfaceIms");
        return salInterfaceIms;
    }
    */

}
