package com.ailk.easyframe.sdl.service;

import org.apache.commons.logging.LogFactory;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_Stream;
import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.util.Utility;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import org.apache.commons.logging.Log;
import jef.codegen.support.NotModified;
@NotModified
public class MIMSITABLESYNCAPP_IIMSITABLESYNCAPP{

	protected static Log log = LogFactory.getLog(MIMSITABLESYNCAPP_IIMSITABLESYNCAPP.class);

	public static int DO_DBMDISPATCHSTART(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_DBMDISPATCHSTART",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMDISPATCHSTART()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsItableSyncApp.DO_DBMDISPATCHSTART() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsItableSyncApp";
		String methodName = "do_dbmDispatchStart";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_DBMDISPATCHSTART",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMDISPATCHSTART()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsItableSyncApp.DO_DBMDISPATCHSTART() with successful executing.");
		return iRetValue;
	}

	public static int DO_DBMSCANSTART(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_DBMSCANSTART",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMSCANSTART()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsItableSyncApp.DO_DBMSCANSTART() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsItableSyncApp";
		String methodName = "do_dbmScanStart";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_DBMSCANSTART",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMSCANSTART()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsItableSyncApp.DO_DBMSCANSTART() with successful executing.");
		return iRetValue;
	}

	public static int DB_DBMCUSTTRANSFER(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DB_DBMCUSTTRANSFER",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DB_DBMCUSTTRANSFER()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsItableSyncApp.DB_DBMCUSTTRANSFER() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsItableSyncApp";
		String methodName = "db_dbmCustTransfer";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DB_DBMCUSTTRANSFER",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DB_DBMCUSTTRANSFER()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsItableSyncApp.DB_DBMCUSTTRANSFER() with successful executing.");
		return iRetValue;
	}

	public static int DO_DBMACCTDEPOSIT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_DBMACCTDEPOSIT",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMACCTDEPOSIT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsItableSyncApp.DO_DBMACCTDEPOSIT() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsItableSyncApp";
		String methodName = "do_dbmAcctDeposit";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_DBMACCTDEPOSIT",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMACCTDEPOSIT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsItableSyncApp.DO_DBMACCTDEPOSIT() with successful executing.");
		return iRetValue;
	}

	public static int DO_DBMUSERDEPOSITDISPATCH(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_DBMUSERDEPOSITDISPATCH",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMUSERDEPOSITDISPATCH()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsItableSyncApp.DO_DBMUSERDEPOSITDISPATCH() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsItableSyncApp";
		String methodName = "do_dbmUserDepositDispatch";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_DBMUSERDEPOSITDISPATCH",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMUSERDEPOSITDISPATCH()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsItableSyncApp.DO_DBMUSERDEPOSITDISPATCH() with successful executing.");
		return iRetValue;
	}

	public static int DO_DBMMANUALMODIFY(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_DBMMANUALMODIFY",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMMANUALMODIFY()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsItableSyncApp.DO_DBMMANUALMODIFY() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsItableSyncApp";
		String methodName = "do_dbmManualModify";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_DBMMANUALMODIFY",".sdl","MImsItableSyncApp.::IImsItableSyncApp::DO_DBMMANUALMODIFY()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsItableSyncApp.DO_DBMMANUALMODIFY() with successful executing.");
		return iRetValue;
	}


}