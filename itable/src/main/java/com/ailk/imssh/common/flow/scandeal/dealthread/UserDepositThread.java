package com.ailk.imssh.common.flow.scandeal.dealthread;

import org.apache.log4j.Logger;

import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.config.UserDepositConfig;
import com.ailk.imssh.common.flow.scandeal.deal.BaseDeal;
import com.ailk.imssh.common.flow.scandeal.queue.bean.Queue;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;
import com.ailk.imssh.common.manual.handler.IUserDepositHandler;
import com.ailk.imssh.common.mdb.ErrorMsgHolder;
import com.ailk.imssh.common.mdb.ItableSalMdbBean;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.jd.entity.IUserDepoNtHis;
import com.ailk.openbilling.persistence.jd.entity.IUserDepositNotify;

public class UserDepositThread extends Thread {

	private Logger imsLogger = Logger.getLogger(UserDepositThread.class);
	private IUserDepositHandler baseDeal;
	private Queue queue;// 需要处理的一个队列
	private IConfig config;

	public UserDepositThread(Queue queue, IUserDepositHandler realDeal, IConfig config) {
		this.queue = queue;
		this.baseDeal = realDeal;
		this.config = config;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// 从队列中取出第一个对象（可能为空）
				DataHolder dataHolder = queue.getRemoveFirst();
				if (dataHolder != null) {
					IUserDepositNotify notify = ((UserDepositConfig)config).convertToIUserDeposti( dataHolder.getDataObject());
					ITableUtil.requestInit();
					ITableUtil.setValue2ContextHolder(null, null, notify.getServId());
					ITableContext context = new ITableContext();
					context.setSync(true);
					context.setRequestDate(notify.getCreateDate());
					context.setDoneCode(notify.getSoNbr());
					String errorMsg = null;
					try {
						baseDeal.handleUserDeposit(notify, context);
						ErrorMsgHolder syncMdb = new ItableSalMdbBean(context).syncMdb(null, null);
						if (syncMdb.getCbsErrorMsg().get_errorCode() != 0) {
							errorMsg = syncMdb.getCbsErrorMsg().get_errorMsg();
						}
					} catch (Exception e) {
						imsLogger.error(e, e);
						errorMsg = new BaseDeal().getErrorMsg(e.getLocalizedMessage());
					}
					removeUserDepostNotify2His(dataHolder,config, 0, errorMsg);

				} else {
					// 队列为空，则停一秒之后执行
					ITableUtil.sleep(1000);
				}
			} catch (Exception e) {
				imsLogger.error(e, e);
			}
		}
	}

	private static void removeUserDepostNotify2His(DataHolder dataHolder,IConfig config, int errorCode, String errorMsg) {
		try {

			IUserDepositNotify notify = ((UserDepositConfig)config).convertToIUserDeposti( dataHolder.getDataObject());
			
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_BILL_MONTH,
					DateUtil.getFormatDate(notify.getCreateDate(), DateUtil.DATE_FORMAT_YYYYMMDD));

			IUserDepoNtHis his = new IUserDepoNtHis();
			his.setBusiType(notify.getBusiType());
			his.setChangeFlag(notify.getChangeFlag());
			his.setCondId(notify.getCondId());
			his.setCreateDate(notify.getCreateDate());
			his.setPromoId(notify.getPromoId());
			his.setRegionCode(notify.getRegionCode());
			his.setServId(notify.getServId());
			his.setSoNbr(notify.getSoNbr());
			his.setSrcType(notify.getSrcType());
			his.setDealCode(errorCode);
			his.setValidDate(notify.getValidDate());
			his.setRemark(errorMsg);
			his.setDealDate(DateUtil.currentDate());
			DBUtil.insert(his);
			
			DBUtil.getDBClient().delete(config.getDeleteDataObjectSub(dataHolder.getDataObject()), dataHolder.getSubTableName());
		} catch (Exception e2) {
			Logger.getLogger(UserDepositThread.class).error(e2, e2);
		}
	}
}
