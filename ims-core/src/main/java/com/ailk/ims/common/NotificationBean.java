package com.ailk.ims.common;

import java.util.List;
/**
 * 存放IMSContext和IMSNotificationList
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2012-6-14
 */
public class NotificationBean extends ContextBean {
	private List<IMSNotification> notifications;

	public List<IMSNotification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<IMSNotification> notifications) {
		this.notifications = notifications;
	}
	
}
