package com.ailk.imssh.common.manual.main;

import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.config.ManualConfig;
import com.ailk.imssh.common.flow.scandeal.dealthread.ManualModifyThread;
import com.ailk.imssh.common.flow.scandeal.queue.bean.Queue;
import com.ailk.imssh.common.flow.scandeal.queue.list.QueueList;
import com.ailk.imssh.common.flow.scandeal.scan.Scan;
import com.ailk.imssh.common.flow.scandeal.scanthread.ScanThread;
import com.ailk.imssh.common.init.CommonInit;
import com.ailk.imssh.common.manual.handler.IHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;

public class ManualMultiBean extends Scan {

	@Override
	protected IConfig getConfig() {
		// TODO Auto-generated method stub
		return ManualConfig.getInstance();
	}
	
	public static void main(String[] args)
    {
        ImsLogger imsLogger = new ImsLogger(ManualMultiBean.class);
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
        if(CommonUtil.isNotEmpty(subTableIndex)){
        	new ManualMultiBean().startScanDeal(Integer.parseInt(subTableIndex));
        }else{
        	new ManualMultiBean().startScanDeal(null);
        }
    }
	
	
	public void dbmStart(String partName){
        	new ManualMultiBean().startScanDeal(null);
    }
	
	
	public void startScanDeal(Integer subTableIndex) {
		ImsLogger imsLogger = new ImsLogger(ManualMultiBean.class);
		CommonInit.commonInitWithTs();

		IConfig config = this.getConfig();
		QueueList queueList = QueueList.getQueueListByConfig(config);
		String subTableName = config.getSubTableNamePrefix();
		if(subTableIndex != null){
			subTableName = config.getSubTableNamePrefix()+ "_" + subTableIndex;
		}

		// 1 启动扫描小索引表的线程
		ScanThread scanThread = new ScanThread(subTableName, config, queueList);
		scanThread.start();
		imsLogger.debug(subTableName, " ScanThread start succ!!!");

		IHandler baseDeal = SpringExUtil.getManualHandler();

		// 2 启动【30】 个处理队列线程
		for (int i = 0; i < config.getDealThreadCount(); i++) {
			Queue queue = queueList.getQueue(i);
			ManualModifyThread dealThread = new ManualModifyThread(queue, baseDeal,config,subTableName,subTableIndex);
			dealThread.start();
			ITableUtil.sleep(1000);
		}
		
		imsLogger.debug("DealQueueThread[thread_count=", config.getDealThreadCount(), "] start succ!!!");
	}

}
