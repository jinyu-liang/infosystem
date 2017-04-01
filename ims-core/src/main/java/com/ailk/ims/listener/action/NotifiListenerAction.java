package com.ailk.ims.listener.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;

public class NotifiListenerAction implements IListenerAction {
	private ImsLogger imsLogger = new ImsLogger(this.getClass());
	private List<IMSNotification> notifications;
	
	
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
		imsLogger.info("["+context.getOper().getBusi_code()+"]begin to notify", context);
		return IMSUtil.doNotification(notifications, context);
		
	}

}
