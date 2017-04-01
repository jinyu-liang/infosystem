package com.ailk.imssh.common.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.flow.dispatch.dispatch.BatchIndexDispatch;
import com.ailk.imssh.common.flow.dispatch.dispatch.DataIndexDispatch;
import com.ailk.imssh.common.flow.dispatch.dispatch.UserDepositDispatch;
import com.ailk.imssh.common.flow.scandeal.scan.BatchIndexScan;
import com.ailk.imssh.common.flow.scandeal.scan.DataIndexScan;
import com.ailk.imssh.common.manual.main.AcctDepositBean;
import com.ailk.imssh.common.manual.main.CustTransferBean;
import com.ailk.imssh.common.manual.main.ManualModify;
import com.ailk.imssh.common.manual.main.ManualMultiBean;
import com.ailk.imssh.common.util.ITableUtil;

public class ItableInterceptor implements MethodInterceptor {

	private final static String SCAN_METHOD = "DO_DBMSCANSTART";

	private final static String DISPATCH_METHOD = "DO_DBMDISPATCHSTART";

	private final static String CUST_TRANSFER = "DB_DBMCUSTTRANSFER";

	private final static String ACCT_DEPOSIT = "DO_DBMACCTDEPOSIT";
	
	private final static String USER_DEPOSIT_DISPATCH="DO_DBMUSERDEPOSITDISPATCH";
	
	private final static String MANUAL_MODIFY="DO_DBMMANUALMODIFY";
	
	private final static String USER_ACCT_NEXT_CHG="DO_DBMUSERACCTHANDLER";
	
	private ImsLogger imsLogger = new ImsLogger(ItableInterceptor.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		imsLogger.debug("enter DbmTsInterceptor");

		String methodName = invocation.getMethod().getName();
		imsLogger.info("methodName:" + methodName);

		if (methodName.equalsIgnoreCase(SCAN_METHOD)) {
			imsLogger.info("start scan```````");
			String batch = OLTPServiceContext.getInstance().getConfigText(ConstantExDefine.XPATH + "/batch_table", "false");
			if(Boolean.valueOf(batch)){
				imsLogger.info("启动批量处理进程");
				new BatchIndexScan().dbmStart();
			}else{
				imsLogger.info("启动处理进程");
				DataIndexScan scan = new DataIndexScan();
				scan.dbmStart();
			}
			while (true) {
				ITableUtil.sleep(1000000);
			}
		} else if (methodName.equalsIgnoreCase(DISPATCH_METHOD)) {
			imsLogger.error("start dispatch```````");
			String batch = OLTPServiceContext.getInstance().getConfigText(ConstantExDefine.XPATH + "/batch_table", "false");
			if(Boolean.valueOf(batch)){
				imsLogger.info("启动批量分发进程");
				new BatchIndexDispatch().doDbmStartDispatch();
			}else{
				imsLogger.info("启动分发进程");
				new DataIndexDispatch().doDbmStartDispatch();
			}
		} else if (methodName.equalsIgnoreCase(CUST_TRANSFER)) {
			imsLogger.info("start custtransfer```````");
			String dealDateStr = OLTPServiceContext.getInstance().getConfigText(ConstantExDefine.XPATH + "/dealDate", "");
			new CustTransferBean().transfer(dealDateStr);
			return OBBufferErrorException.SDL_ONCE;
		} else if (methodName.equalsIgnoreCase(ACCT_DEPOSIT)) {
			String subIndex = OLTPServiceContext.getInstance().getConfigText(ConstantExDefine.XPATH + "/sub_index", "");
			if(CommonUtil.isEmpty(subIndex)){
				imsLogger.info("流程配置的sub_index出错,流程退出，请检查");
				return OBBufferErrorException.SDL_ONCE;
			}
			new AcctDepositBean().startScanDeal(Integer.valueOf(subIndex));
			while (true) {
				ITableUtil.sleep(1000000);
			}
		} else if (methodName.equalsIgnoreCase(USER_DEPOSIT_DISPATCH)){
			imsLogger.info("start user desposit dispatch```````");
			new UserDepositDispatch().doDbmStartDispatch();
		}else if (methodName.equalsIgnoreCase(MANUAL_MODIFY)){
			imsLogger.info("start user maunal modify```````");
			String partName = OLTPServiceContext.getInstance().getConfigText(ConstantExDefine.XPATH + "/subTable", "");
			if(SysUtil.getBoolean("MANUAL_MODIFY_MULTI", true)){
				if(CommonUtil.isNotEmpty(partName)){
					new ManualMultiBean().startScanDeal(Integer.valueOf(partName));
				}else{
					new ManualMultiBean().startScanDeal(null);
				}
			}else{
				new ManualMultiBean().dbmStart(partName);
			}
			while (true) {
				ITableUtil.sleep(1000000);
			}
		}

		return OBBufferErrorException.SDL_OK;
	}

}
