package com.ailk.ims.listener;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.listener.action.IListenerAction;
import com.ailk.ims.listener.action.MdbSyncListenerAction;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;
/**
 * 监听器里每个流程用到的Action集合载体
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2012-6-14
 */
public class IMSListenerServiceFlow {
    private static ImsLogger logger  = new ImsLogger(IMSListenerServiceFlow.class);
	private IMSContext context;
	private List<IListenerAction> beforeMdbList;
	private MdbSyncListenerAction mdbAction;
	private List<IListenerAction> afterMdbList;
	
	public void addActionBeforeMdb(IListenerAction action){
	    logger.debug("addActionBeforeMdb : "+action.getClass().getName(), context);
	    if(beforeMdbList == null)
			beforeMdbList = new ArrayList<IListenerAction>();
		beforeMdbList.add(action);
	}
	public void addActionAfterMdb(IListenerAction action){
		logger.debug("addActionAfterMdb : "+action.getClass().getName(), context);
	    if(afterMdbList == null)
			afterMdbList = new ArrayList<IListenerAction>();
		afterMdbList.add(action);
	}
	
	public IMSListenerServiceFlow(IMSContext context){
		this.setContext(context);
	}
	
	public IMSContext getContext() {
		return context;
	}

	public void setContext(IMSContext context) {
		this.context = context;
	}

	public MdbSyncListenerAction getMdbAction() {
		return mdbAction;
	}

	public void setMdbAction(MdbSyncListenerAction mdbAction) {
		this.mdbAction = mdbAction;
	}
	public List<IListenerAction> getBeforeMdbList() {
		return beforeMdbList;
	}
	public void setBeforeMdbList(List<IListenerAction> beforeMdbList) {
		this.beforeMdbList = beforeMdbList;
	}
	public List<IListenerAction> getAfterMdbList() {
		return afterMdbList;
	}
	public void setAfterMdbList(List<IListenerAction> afterMdbList) {
		this.afterMdbList = afterMdbList;
	}
}
