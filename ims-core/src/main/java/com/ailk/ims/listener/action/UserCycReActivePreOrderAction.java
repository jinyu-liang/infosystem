package com.ailk.ims.listener.action;

import org.apache.log4j.Logger;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;

/**
 * @Description: 预用户生命周期状态被重新激活后进行通知扣费
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tangjl5
 * @Date 2012-6-20
 */
public class UserCycReActivePreOrderAction implements IListenerAction {
	private ImsLogger logger = new ImsLogger(getClass());

	public Object doAction(IMSContext context) {
	    logger.info("["+context.getOper().getBusi_code()+"]begin to call preorder", context);
		context.getComponent(BaseLifeCycleComponent.class).lifeCycleProdPreOrder();
		return null;
	}
}
