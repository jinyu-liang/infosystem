package com.ailk.imssh.common.flow.scandeal.dealthread;

import org.apache.log4j.Logger;

import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.config.ManualConfig;
import com.ailk.imssh.common.flow.scandeal.queue.bean.Queue;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;
import com.ailk.imssh.common.manual.handler.IHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.jd.entity.ImsManualModify;

public class ManualModifyThread extends Thread {

	private Logger imsLogger = Logger.getLogger(ManualModifyThread.class);
	private IHandler handler;
	private Queue queue;// 需要处理的一个队列
	private IConfig config;
	private String tableName;
	private Integer subIndex;

	public ManualModifyThread(Queue queue, IHandler handler, IConfig config,
			String tableName,Integer subIndex) {
		this.queue = queue;
		this.handler = handler;
		this.config = config;
		this.tableName = tableName;
		this.subIndex  = subIndex;
	}

	@Override
	public void run() {
		
		while (true) {
			try {
				// 从队列中取出第一个对象（可能为空）
				DataHolder dataHolder = queue.getRemoveFirst();
				if (dataHolder != null) {
					ImsManualModify modify = ((ManualConfig) config)
							.convertToIUserDeposti(dataHolder.getDataObject());
					ITableUtil.requestInit();
					if(subIndex != null){
			        	ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_SUB_MOD, subIndex);
			        }
					ITableContext context = new ITableContext();// 重新生成
					context.setSync(false);// 表示异步
					if (null == context.getRequestDate()) {
						context.setRequestDate(DateUtil.currentDate());
					}
					handler.hander(modify, context, tableName);
				} else {
					// 队列为空，则停一秒之后执行
					ITableUtil.sleep(5000);
				}
			} catch (Throwable e) {
				imsLogger.error(e, e);
			}
		}
	}

}
