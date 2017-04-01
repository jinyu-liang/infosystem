package com.ailk.ims.listener.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.component.BaseProductNotifyComponent;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;
import com.ailk.ims.util.SysUtil;

/**
 * @Description 产品发通知监听
 * @author yanchuan
 * @Date 2012-7-14
 */
public class ProdNotifiListenerAction implements IListenerAction {
	private List<IMSNotification> notifications;
	private ImsLogger imsLogger=new ImsLogger(getClass());
	
	public List<IMSNotification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<IMSNotification> notifications) {
		this.notifications = notifications;
	}

	public Object doAction(IMSContext context) {
		if (CommonUtil.isEmpty(notifications))
        {
            return null;
        }
        //2012-04-07 zengxr from 0, avoid no send for max retry time = 0
        int times = 0;
        boolean sendResult = false;
        while(!sendResult){
            imsLogger.info("["+context.getOper().getBusi_code()+"]begin to notify", context);
            sendResult = reSend(times, notifications,context);
            times = times + 1;
        }
	    return sendResult;
		
	}

    /**
     * 立即发送通知，重发控制
     * luojb 2012-2-14
     * @param times
     * @param notifications
     * @return
     */
    private boolean reSend(int times,List<IMSNotification> notifications,IMSContext context){
        int maxReTryTimes = SysUtil.getInt(SysCodeDefine.busi.PROD_NOTIFY_MAX_RETRY_TIMES);
        BaseProductNotifyComponent pnc = context.getComponent(BaseProductNotifyComponent.class);
        if(times <= maxReTryTimes){
            try{
                imsLogger.info(">>>>retry time:"+times);
                IMSUtil.doNotification(notifications, context);
                return true;
            }catch(Exception e){
                imsLogger.error(e.getMessage());
                return false;
            }
        }
        //超过最大重试次数，写入失败信息表
        pnc.writeNotifyError(notifications);
        return true;
    }
    
  
}
