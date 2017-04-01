package com.ailk.ims.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.transaction.support.CpfTransactionInfo;
import com.ailk.easyframe.transaction.support.CpfTransactionResult;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.listener.action.IListenerAction;
import com.ailk.ims.listener.action.MdbSyncListenerAction;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SpringUtil;
/**
 * 
 * @author yugb
 * 事务一致性改造
 * @Date 2012-12-17 wukl: On_Site Defect #67735 REDO模块加解锁用户IMS接口没有上发MDB
 */

public class IMSTransListener implements IDBOperatorListener{
	
	private ThreadLocal<List<IMSListenerServiceFlow>> serviceFlowContainer = new ThreadLocal<List<IMSListenerServiceFlow>>();
	private ImsLogger imsLogger=new ImsLogger(this.getClass());

	public void afterCompletion(CpfTransactionInfo arg0,
			CpfTransactionResult arg1) {
		
	}
	
	 //每次退出线程之前必须调用这个方法把当前线程中的serviceFlowContainer给清掉。
    public void clearServiceFlow(){
        List<IMSListenerServiceFlow> actionContainerList = serviceFlowContainer.get();
        if(actionContainerList != null)
            actionContainerList.clear();//因为本类是单例的，所以每次运行完毕需要清空
        
        serviceFlowContainer.set(null);
    }
    public void addActionContainer(IMSListenerServiceFlow bean)
    {
        List<IMSListenerServiceFlow> actionContainerList = serviceFlowContainer.get();
        if (actionContainerList == null){
            actionContainerList = new ArrayList<IMSListenerServiceFlow>();
            serviceFlowContainer.set(actionContainerList);
        }
        actionContainerList.add(bean);
    }
	//原先在IMSDbOperatorListener类里的逻辑都IMSTransListener.beforeCommit里
	public void beforeCommit(CpfTransactionInfo arg0) {

        imsLogger.info("****** enter postCommit");
        try{
            List<IMSListenerServiceFlow> actionContainerList = serviceFlowContainer.get();
            if (CommonUtil.isNotEmpty(actionContainerList)){
                for (IMSListenerServiceFlow container : actionContainerList)
                {
                    // 一个DbListenerBean相当于一次完整请求里的一个子业务流程
                    IMSContext context = container.getContext();
                    List<IListenerAction> actionList = container.getBeforeMdbList();
                    // 先执行mdb上发前的操作
                    //上海工程只用到mdb action
                    if (CommonUtil.isNotEmpty(actionList) && !ProjectUtil.is_CN_SH())
                    {
                        for (IListenerAction action : actionList)
                        {
                            imsLogger.info("begin to exec action before mdb : " + action.getClass().getName(), context);
                            action.doAction(context);
                        }
                    }
                    imsLogger.info("begin to exec mdb action", context);
                    // 执行mdb操作
                    MdbSyncListenerAction mdbAction = container.getMdbAction();
                    if (mdbAction != null)
                    {
                        mdbAction.doAction(context);
                    }
        
                    // 再执行mdb上发后的操作
                    //上海工程只用到mdb action
                    actionList = container.getAfterMdbList();
                    if (CommonUtil.isNotEmpty(actionList) && !ProjectUtil.is_CN_SH())
                    {
                        for (IListenerAction action : actionList)
                        {
                            imsLogger.info("begin to exec action after mdb : " + action.getClass().getName(), context);
                            action.doAction(context);
                        }
                    }
                    //@Date 2013-6-5 zhangzj3: 现金充值整个流程不能超过15s,超过回滚报错
                    int busiCode = getTopContextBusiCode(context);
                    if(busiCode == EnumCodeDefine.DEPOSIT_BY_CASH_BEAN){
                        Date startDate = context.getRequestDate();
                        Date endDate = DateUtil.currentDate();
                        long startSecond = startDate.getTime();
                        long endSecond = endDate.getTime();
                        imsLogger.info("startSecond::::"+startSecond  +"endSecond::::"+endSecond);
                        if(endSecond - startSecond > EnumCodeDefine.CALL_DEPOSIT_BY_CASH_TIME_OUT_TIME){
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.CALL_INTERFACE_TIME_OUT,EnumCodeDefine.CALL_DEPOSIT_BY_CASH_TIME_OUT_TIME+"ms");
                        }
                    }
                    // 清空下上文对象的一些数据
                    context.destroy();
                }
            }
        }catch(Exception e){
//           e.printStackTrace();
           imsLogger.error(e);
           //超时特殊处理
           String errCode = String.valueOf(ErrorCodeDefine.CALL_INTERFACE_TIME_OUT) ;
           String timeOut =  String.valueOf(EnumCodeDefine.CALL_DEPOSIT_BY_CASH_TIME_OUT_TIME);
           if(e.getMessage().indexOf(errCode) >=0 && e.getMessage().indexOf(timeOut)>=0){
               throw IMSUtil.throwBusiException(ErrorCodeDefine.CALL_INTERFACE_TIME_OUT,EnumCodeDefine.CALL_DEPOSIT_BY_CASH_TIME_OUT_TIME+"ms");
           }
        }
	    finally
	    {
	    	//@Date 2012-12-04 yugb :On_Site Defect #67735 REDO模块加解锁用户IMS接口没有上发MDB
	        //如果是顶层请求，退出时需要清空
	        SpringUtil.getDbOperatorListener().clearServiceFlow();
	    }
        imsLogger.info("****** exit postCommit");
	}

	public void beginTransaction(CpfTransactionInfo arg0) {
		
	}
	private int contextCount = 0;
	public int getTopContextBusiCode(IMSContext context){
	    imsLogger.info("pcontext info::::::::::::"+context.getOper().getBusi_code());
	    contextCount++;
        if(contextCount > 10){
            contextCount = 0;
            return 0;
        }
	    if(context.getParent() != null){
	        return getTopContextBusiCode(context.getParent());
	    }
	    contextCount = 0;
	    return context.getOper().getBusi_code();
	}
	
}
