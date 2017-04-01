package com.ailk.imssh.common.flow.scandeal.scan;

import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.error.thread.ErrorDealThread;
import com.ailk.imssh.common.flow.scandeal.deal.IBaseDeal;
import com.ailk.imssh.common.flow.scandeal.dealthread.DealThread;
import com.ailk.imssh.common.flow.scandeal.queue.bean.Queue;
import com.ailk.imssh.common.flow.scandeal.queue.list.QueueList;
import com.ailk.imssh.common.flow.scandeal.scanthread.ScanThread;
import com.ailk.imssh.common.init.CommonInit;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;

/**
 * @Description:启动扫描小索引表的线程、启动处理30个队列中的数据的线程
 * @author wangjt
 * @Date 2012-9-13
 */
public abstract class Scan {
	private ImsLogger imsLogger = new ImsLogger(Scan.class);

	/**
	 * 由子类控制具体的配置信息
	 */
	protected abstract IConfig getConfig();

	/**
	 * 需要输入i_data_index_sub分表的序号<br>
	 * 取值范围[0-7],分别对应8个分表：i_data_index_sub_{subTableIndex}
	 * 
	 * @param subTableIndex
	 *            : 需要扫描的分表index
	 */
	public void startScanDeal(int subTableIndex) {
		CommonInit.commonInitWithTs();

		IConfig config = this.getConfig();
		QueueList queueList = QueueList.getQueueListByConfig(config);

		// CD.I_DATA_INDEX_SUB_{subTableIndex}
		String subTableName = config.getSubTableNamePrefix() + subTableIndex;

		// 1 启动扫描小索引表的线程
		ScanThread scanThread = new ScanThread(subTableName, config, queueList);
		scanThread.start();
		imsLogger.debug(subTableName, " ScanThread start succ!!!");

		IBaseDeal baseDeal = SpringExUtil.getBaseDeal();

		// 2 启动【30】 个处理队列线程
		for (int i = 0; i <= config.getDealThreadCount(); i++) {
			Queue queue = queueList.getQueue(i);
			DealThread dealThread = new DealThread(queue, baseDeal, config);
			if (i == config.getDealThreadCount()) {
				dealThread.setName("低优先级线程：");
			}
			dealThread.start();
			// 启动一个线程后，停止100ms，再开始启动第二个线程，后续读取seq防止锁住
			ITableUtil.sleep(100);
		}

		// 3 启动1个处理错误队列的线程
//		ErrorDealThread errorDealThread = new ErrorDealThread(baseDeal, config);
//		errorDealThread.start();

		imsLogger.debug("DealQueueThread[thread_count=", config.getDealThreadCount(), "] start succ!!!");
	}
}
