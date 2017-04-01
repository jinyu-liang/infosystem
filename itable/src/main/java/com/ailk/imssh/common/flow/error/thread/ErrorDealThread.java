package com.ailk.imssh.common.flow.error.thread;

import org.apache.log4j.Logger;

import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.error.bean.ErrorHolder;
import com.ailk.imssh.common.flow.error.bean.ErrorListQueue;
import com.ailk.imssh.common.flow.scandeal.deal.IBaseDeal;
import com.ailk.imssh.common.flow.scandeal.dealprocess.DealProcess;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;
import com.ailk.imssh.common.util.ITableUtil;

/**
 * @Description:处理错单的线程
 * @author wangjt
 * @Date 2012-11-9
 */
public class ErrorDealThread extends Thread {
	private Logger imsLogger = Logger.getLogger(getClass());
	private IBaseDeal baseDeal;
	private IConfig config;

	public ErrorDealThread(IBaseDeal baseDeal, IConfig config) {
		this.baseDeal = baseDeal;
		this.config = config;
		// 启动的时候就设置一个标志位进去
		DataHolder holder = new DataHolder("I_DATA_INDEX", null, EnumCodeExDefine.ERROR_THREAD_BUSI_VALUE);
		//ErrorListQueue.addErrorHolderToQueue(new ErrorHolder(holder));
		imsLogger.info("****init error queue ,queue size = " + ErrorListQueue.getSize());
	}

	@Override
	public void run() {
		while (true) {
			try {
				// 获取队列中的第一个用户的错单列表中的第一个错单
				imsLogger.info("%%%%%%%%%%error list queue size = " + ErrorListQueue.getSize());
				ErrorHolder errorHolder = ErrorListQueue.getFirstErrorHolder();
				if (errorHolder == null)// 无错单，等待之后继续
				{
					imsLogger.debug("######error list is null, sleep 3s######");
					ITableUtil.sleep(3000);
					continue;
				}

				if (errorHolder.getBusiValue() == EnumCodeExDefine.ERROR_THREAD_BUSI_VALUE) {
					// 是标志位，sleep 2s 后 移到末尾
					imsLogger.info("######this error data is flag , sleep 3s add to last ");
					ITableUtil.sleep(3000);
					ErrorListQueue.moveFirstListToLast();
					continue;
				}
				/*
				Long soNbr = config.getValueOfSoNbr(errorHolder.getDataObject());
				IDataIndexSubModify modify = DBUtil.querySingle(IDataIndexSubModify.class, new DBCondition(IDataIndexSubModify.Field.soNbr,
						soNbr));
				if (modify != null) {
					imsLogger.info("######begin to deal error data, busi_value = " + errorHolder.getBusiValue());
					errorHolder.setNeedWaiting(false);
					errorHolder.setRemove(false);
					DealProcess.dealProcess(errorHolder, baseDeal, config);
					imsLogger.info("######delete from I_DATA_INDEX_SUB_MODIFY...");
					DBUtil.deleteByCondition(IDataIndexSubModify.class, new DBCondition(IDataIndexSubModify.Field.soNbr, soNbr));
				} else {
				*/
					// 判断错单是否需要等待
					if (errorHolder.isNeedWaiting(DateUtil.currentDate())) {
						imsLogger.debug("######remove error data to queue last  . busi_value = " + errorHolder.getBusiValue());
						ErrorListQueue.moveFirstListToLast();// 如果未到处理时间，则移动到队列末尾
					} else {// 执行
						imsLogger.info("######begin to deal error data, busi_value = " + errorHolder.getBusiValue());
						errorHolder.setRemove(false);
						DealProcess.dealProcess(errorHolder, baseDeal, config);
					}
			} catch (Exception e) {
				imsLogger.error("%%%%%%%%%%%%%exception accour ,debug ,continue");
				imsLogger.error(e, e);
			}
		}
	}
}
