package com.ailk.ims.listener.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;


/**
 * @Description: 产品订购失败发告警
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author yangjh
 * @Date 2012-7-11
 */
public class ProdFailNotifyListenerAction implements IListenerAction {
	private List<IMSNotification> notifications;
	protected ImsLogger imsLogger=new ImsLogger(this.getClass());
	
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
		imsLogger.info("["+context.getOper().getBusi_code()+"]begin to notify prod fail", context);
		imsLogger.dump("****** product fail notification list",notifications);
		return IMSUtil.doNotification(notifications, context);
		
	}

}
