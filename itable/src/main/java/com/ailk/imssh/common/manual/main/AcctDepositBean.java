package com.ailk.imssh.common.manual.main;

import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.config.UserDepositConfig;
import com.ailk.imssh.common.flow.scandeal.dealthread.UserDepositThread;
import com.ailk.imssh.common.flow.scandeal.queue.bean.Queue;
import com.ailk.imssh.common.flow.scandeal.queue.list.QueueList;
import com.ailk.imssh.common.flow.scandeal.scan.Scan;
import com.ailk.imssh.common.flow.scandeal.scanthread.ScanThread;
import com.ailk.imssh.common.init.CommonInit;
import com.ailk.imssh.common.manual.handler.IUserDepositHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;

public class AcctDepositBean extends Scan {

	@Override
	protected IConfig getConfig() {
		// TODO Auto-generated method stub
		return UserDepositConfig.getInstance();
	}
	
	public static void main(String[] args)
    {
        ImsLogger imsLogger = new ImsLogger(AcctDepositBean.class);
        ITableUtil.startTs();
        
        while (!OLTPServiceContext.isInitSpringFinished())
        {
            try
            {
                imsLogger.debug("***spring context not start,sleep 5 s");
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                imsLogger.error(e.getMessage());
            }
        }

        String subTableIndex = args[0];
        if(CommonUtil.isEmpty(subTableIndex)){
        	IMSUtil.throwBusiException("启动脚本或者流程配置中缺少分表尾号,请检查配置");
        }
        new AcctDepositBean().startScanDeal(Integer.parseInt(subTableIndex));
    }
	
	
	public void dbmStart(){
        ImsLogger imsLogger = new ImsLogger(AcctDepositBean.class);
        
        imsLogger.debug("***************************itable dbm start***********************");
       
        String subTableIndex = OLTPServiceContext.getInstance().getConfigText(ConstantExDefine.XPATH+"/subTable","0");
        
        imsLogger.debug("subTable:"+subTableIndex);
        
        new AcctDepositBean().startScanDeal(Integer.parseInt(subTableIndex));
    }
	
	
	public void startScanDeal(int subTableIndex) {
		ImsLogger imsLogger = new ImsLogger(AcctDepositBean.class);
		CommonInit.commonInitWithTs();

		IConfig config = this.getConfig();
		QueueList queueList = QueueList.getQueueListByConfig(config);

		// CD.I_DATA_INDEX_SUB_{subTableIndex}
		String subTableName = config.getSubTableNamePrefix() + subTableIndex;

		// 1 启动扫描小索引表的线程
		ScanThread scanThread = new ScanThread(subTableName, config, queueList);
		scanThread.start();
		imsLogger.debug(subTableName, " ScanThread start succ!!!");

		IUserDepositHandler baseDeal = SpringExUtil.getUserDepositHandler();

		// 2 启动【30】 个处理队列线程
		for (int i = 0; i <= config.getDealThreadCount(); i++) {
			Queue queue = queueList.getQueue(i);
			UserDepositThread dealThread = new UserDepositThread(queue, baseDeal,config);
			if (i == config.getDealThreadCount()) {
				dealThread.setName("低优先级线程：");
			}
			dealThread.start();
			// 启动一个线程后，停止100ms，再开始启动第二个线程，后续读取seq防止锁住
			ITableUtil.sleep(100);
		}

		

		imsLogger.debug("DealQueueThread[thread_count=", config.getDealThreadCount(), "] start succ!!!");
	}

}
