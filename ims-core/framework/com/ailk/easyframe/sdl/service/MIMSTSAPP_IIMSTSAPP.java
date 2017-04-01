package com.ailk.easyframe.sdl.service;

import org.apache.commons.logging.LogFactory;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_Stream;
import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.util.Utility;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.openbilling.persistence.imsts.entity.STsRecord;
import jef.common.Out;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayListHolder;
import com.ailk.openbilling.persistence.imsts.entity.SUserConfirm;
import com.ailk.openbilling.persistence.imsts.entity.SImsOrderProduct;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import org.apache.commons.logging.Log;
import jef.codegen.support.NotModified;
@NotModified
public class MIMSTSAPP_IIMSTSAPP{

	protected static Log log = LogFactory.getLog(MIMSTSAPP_IIMSTSAPP.class);

	public static int READ_FIRSTACTIVE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_FIRSTACTIVE",".sdl","MImsTsApp.::IImsTsApp::READ_FIRSTACTIVE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_FIRSTACTIVE() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_firstActive";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_FIRSTACTIVE",".sdl","MImsTsApp.::IImsTsApp::READ_FIRSTACTIVE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_FIRSTACTIVE() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_FIRSTACTIVE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_FIRSTACTIVE",".sdl","MImsTsApp.::IImsTsApp::DEAL_FIRSTACTIVE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sCustProdSyncList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sCustProdSyncList_h=new CsdlArrayListHolder<STsRecord>(sCustProdSyncList);
		sCustProdSyncList_h.readData(in);
		sCustProdSyncList=sCustProdSyncList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_FIRSTACTIVE() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_firstActive";
		Object[] params=new Object[]{sCustProdSyncList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_FIRSTACTIVE",".sdl","MImsTsApp.::IImsTsApp::DEAL_FIRSTACTIVE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_FIRSTACTIVE() with successful executing.");
		return iRetValue;
	}

	public static int READ_CREDITSTATUS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_CREDITSTATUS",".sdl","MImsTsApp.::IImsTsApp::READ_CREDITSTATUS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_CREDITSTATUS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_creditStatus";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_CREDITSTATUS",".sdl","MImsTsApp.::IImsTsApp::READ_CREDITSTATUS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_CREDITSTATUS() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_CREDITSTATUS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_CREDITSTATUS",".sdl","MImsTsApp.::IImsTsApp::DEAL_CREDITSTATUS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sCreditStatusSyncList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sCreditStatusSyncList_h=new CsdlArrayListHolder<STsRecord>(sCreditStatusSyncList);
		sCreditStatusSyncList_h.readData(in);
		sCreditStatusSyncList=sCreditStatusSyncList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_CREDITSTATUS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_creditStatus";
		Object[] params=new Object[]{sCreditStatusSyncList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_CREDITSTATUS",".sdl","MImsTsApp.::IImsTsApp::DEAL_CREDITSTATUS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_CREDITSTATUS() with successful executing.");
		return iRetValue;
	}

	public static int READ_PRODUCTSTATUS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_PRODUCTSTATUS",".sdl","MImsTsApp.::IImsTsApp::READ_PRODUCTSTATUS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_PRODUCTSTATUS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_productStatus";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_PRODUCTSTATUS",".sdl","MImsTsApp.::IImsTsApp::READ_PRODUCTSTATUS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_PRODUCTSTATUS() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_PRODUCTSTATUS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_PRODUCTSTATUS",".sdl","MImsTsApp.::IImsTsApp::DEAL_PRODUCTSTATUS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sProdStsSyncList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sProdStsSyncList_h=new CsdlArrayListHolder<STsRecord>(sProdStsSyncList);
		sProdStsSyncList_h.readData(in);
		sProdStsSyncList=sProdStsSyncList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_PRODUCTSTATUS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_productStatus";
		Object[] params=new Object[]{sProdStsSyncList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_PRODUCTSTATUS",".sdl","MImsTsApp.::IImsTsApp::DEAL_PRODUCTSTATUS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_PRODUCTSTATUS() with successful executing.");
		return iRetValue;
	}

	public static int READ_USERSTSSYNC(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_USERSTSSYNC",".sdl","MImsTsApp.::IImsTsApp::READ_USERSTSSYNC()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_USERSTSSYNC() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_userStsSync";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_USERSTSSYNC",".sdl","MImsTsApp.::IImsTsApp::READ_USERSTSSYNC()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_USERSTSSYNC() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_USERSTSSYNC(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_USERSTSSYNC",".sdl","MImsTsApp.::IImsTsApp::DEAL_USERSTSSYNC()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_USERSTSSYNC() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_userStsSync";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_USERSTSSYNC",".sdl","MImsTsApp.::IImsTsApp::DEAL_USERSTSSYNC()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_USERSTSSYNC() with successful executing.");
		return iRetValue;
	}

	public static int READ_BARSERVICE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_BARSERVICE",".sdl","MImsTsApp.::IImsTsApp::READ_BARSERVICE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_BARSERVICE() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_barService";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_BARSERVICE",".sdl","MImsTsApp.::IImsTsApp::READ_BARSERVICE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_BARSERVICE() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_BARSERVICE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_BARSERVICE",".sdl","MImsTsApp.::IImsTsApp::DEAL_BARSERVICE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_BARSERVICE() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_barService";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_BARSERVICE",".sdl","MImsTsApp.::IImsTsApp::DEAL_BARSERVICE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_BARSERVICE() with successful executing.");
		return iRetValue;
	}

	public static int READ_REWARDINFO(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_REWARDINFO",".sdl","MImsTsApp.::IImsTsApp::READ_REWARDINFO()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_REWARDINFO() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_rewardInfo";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_REWARDINFO",".sdl","MImsTsApp.::IImsTsApp::READ_REWARDINFO()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_REWARDINFO() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_REWARDINFO(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_REWARDINFO",".sdl","MImsTsApp.::IImsTsApp::DEAL_REWARDINFO()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_REWARDINFO() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_rewardInfo";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_REWARDINFO",".sdl","MImsTsApp.::IImsTsApp::DEAL_REWARDINFO()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_REWARDINFO() with successful executing.");
		return iRetValue;
	}

	public static int READ_OTPROMUSEDUP(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_OTPROMUSEDUP",".sdl","MImsTsApp.::IImsTsApp::READ_OTPROMUSEDUP()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_OTPROMUSEDUP() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_otPromUsedUp";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_OTPROMUSEDUP",".sdl","MImsTsApp.::IImsTsApp::READ_OTPROMUSEDUP()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_OTPROMUSEDUP() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_OTPROMUSEDUP(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_OTPROMUSEDUP",".sdl","MImsTsApp.::IImsTsApp::DEAL_OTPROMUSEDUP()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sOtpromUsedupSyncList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sOtpromUsedupSyncList_h=new CsdlArrayListHolder<STsRecord>(sOtpromUsedupSyncList);
		sOtpromUsedupSyncList_h.readData(in);
		sOtpromUsedupSyncList=sOtpromUsedupSyncList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_OTPROMUSEDUP() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_otPromUsedUp";
		Object[] params=new Object[]{sOtpromUsedupSyncList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_OTPROMUSEDUP",".sdl","MImsTsApp.::IImsTsApp::DEAL_OTPROMUSEDUP()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_OTPROMUSEDUP() with successful executing.");
		return iRetValue;
	}

	public static int READ_LOWBALANCE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_LOWBALANCE",".sdl","MImsTsApp.::IImsTsApp::READ_LOWBALANCE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_LOWBALANCE() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_lowBalance";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_LOWBALANCE",".sdl","MImsTsApp.::IImsTsApp::READ_LOWBALANCE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_LOWBALANCE() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_LOWBALANCE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_LOWBALANCE",".sdl","MImsTsApp.::IImsTsApp::DEAL_LOWBALANCE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_LOWBALANCE() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_lowBalance";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_LOWBALANCE",".sdl","MImsTsApp.::IImsTsApp::DEAL_LOWBALANCE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_LOWBALANCE() with successful executing.");
		return iRetValue;
	}

	public static int READ_MDBERROR(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_MDBERROR",".sdl","MImsTsApp.::IImsTsApp::READ_MDBERROR()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_MDBERROR() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_mdbError";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_MDBERROR",".sdl","MImsTsApp.::IImsTsApp::READ_MDBERROR()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_MDBERROR() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_MDBERROR(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_MDBERROR",".sdl","MImsTsApp.::IImsTsApp::DEAL_MDBERROR()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_MDBERROR() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_mdbError";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_MDBERROR",".sdl","MImsTsApp.::IImsTsApp::DEAL_MDBERROR()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_MDBERROR() with successful executing.");
		return iRetValue;
	}

	public static int READ_EXPIREPROD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_EXPIREPROD",".sdl","MImsTsApp.::IImsTsApp::READ_EXPIREPROD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_EXPIREPROD() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_expireProd";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_EXPIREPROD",".sdl","MImsTsApp.::IImsTsApp::READ_EXPIREPROD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_EXPIREPROD() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_EXPIREPROD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_EXPIREPROD",".sdl","MImsTsApp.::IImsTsApp::DEAL_EXPIREPROD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sExpireProdNotifyList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sExpireProdNotifyList_h=new CsdlArrayListHolder<STsRecord>(sExpireProdNotifyList);
		sExpireProdNotifyList_h.readData(in);
		sExpireProdNotifyList=sExpireProdNotifyList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_EXPIREPROD() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_expireProd";
		Object[] params=new Object[]{sExpireProdNotifyList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_EXPIREPROD",".sdl","MImsTsApp.::IImsTsApp::DEAL_EXPIREPROD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_EXPIREPROD() with successful executing.");
		return iRetValue;
	}

	public static int READ_EVENT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_EVENT",".sdl","MImsTsApp.::IImsTsApp::READ_EVENT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_EVENT() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_event";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_EVENT",".sdl","MImsTsApp.::IImsTsApp::READ_EVENT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_EVENT() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_EVENT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_EVENT",".sdl","MImsTsApp.::IImsTsApp::DEAL_EVENT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_EVENT() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_event";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_EVENT",".sdl","MImsTsApp.::IImsTsApp::DEAL_EVENT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_EVENT() with successful executing.");
		return iRetValue;
	}

	public static int READ_EVENTREWARD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_EVENTREWARD",".sdl","MImsTsApp.::IImsTsApp::READ_EVENTREWARD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_EVENTREWARD() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_eventReward";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_EVENTREWARD",".sdl","MImsTsApp.::IImsTsApp::READ_EVENTREWARD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_EVENTREWARD() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_EVENTREWARD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_EVENTREWARD",".sdl","MImsTsApp.::IImsTsApp::DEAL_EVENTREWARD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_EVENTREWARD() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_eventReward";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_EVENTREWARD",".sdl","MImsTsApp.::IImsTsApp::DEAL_EVENTREWARD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_EVENTREWARD() with successful executing.");
		return iRetValue;
	}

	public static int READ_DELAYVALIDPROD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_DELAYVALIDPROD",".sdl","MImsTsApp.::IImsTsApp::READ_DELAYVALIDPROD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_DELAYVALIDPROD() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_delayValidProd";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_DELAYVALIDPROD",".sdl","MImsTsApp.::IImsTsApp::READ_DELAYVALIDPROD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_DELAYVALIDPROD() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_DELAYVALIDPROD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_DELAYVALIDPROD",".sdl","MImsTsApp.::IImsTsApp::DEAL_DELAYVALIDPROD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sDelayValidProdSyncList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sDelayValidProdSyncList_h=new CsdlArrayListHolder<STsRecord>(sDelayValidProdSyncList);
		sDelayValidProdSyncList_h.readData(in);
		sDelayValidProdSyncList=sDelayValidProdSyncList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_DELAYVALIDPROD() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_delayValidProd";
		Object[] params=new Object[]{sDelayValidProdSyncList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_DELAYVALIDPROD",".sdl","MImsTsApp.::IImsTsApp::DEAL_DELAYVALIDPROD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_DELAYVALIDPROD() with successful executing.");
		return iRetValue;
	}

	public static int READ_PRODONCENOTIFY(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_PRODONCENOTIFY",".sdl","MImsTsApp.::IImsTsApp::READ_PRODONCENOTIFY()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_PRODONCENOTIFY() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_prodOnceNotify";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_PRODONCENOTIFY",".sdl","MImsTsApp.::IImsTsApp::READ_PRODONCENOTIFY()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_PRODONCENOTIFY() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_PRODONCENOTIFY(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_PRODONCENOTIFY",".sdl","MImsTsApp.::IImsTsApp::DEAL_PRODONCENOTIFY()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sProdOnceNotifyList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sProdOnceNotifyList_h=new CsdlArrayListHolder<STsRecord>(sProdOnceNotifyList);
		sProdOnceNotifyList_h.readData(in);
		sProdOnceNotifyList=sProdOnceNotifyList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_PRODONCENOTIFY() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_prodOnceNotify";
		Object[] params=new Object[]{sProdOnceNotifyList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_PRODONCENOTIFY",".sdl","MImsTsApp.::IImsTsApp::DEAL_PRODONCENOTIFY()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_PRODONCENOTIFY() with successful executing.");
		return iRetValue;
	}

	public static int READ_PRODCYCLENOTIFY(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_PRODCYCLENOTIFY",".sdl","MImsTsApp.::IImsTsApp::READ_PRODCYCLENOTIFY()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_PRODCYCLENOTIFY() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_prodCycleNotify";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_PRODCYCLENOTIFY",".sdl","MImsTsApp.::IImsTsApp::READ_PRODCYCLENOTIFY()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_PRODCYCLENOTIFY() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_PRODCYCLENOTIFY(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_PRODCYCLENOTIFY",".sdl","MImsTsApp.::IImsTsApp::DEAL_PRODCYCLENOTIFY()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sProdCycleNotifyList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sProdCycleNotifyList_h=new CsdlArrayListHolder<STsRecord>(sProdCycleNotifyList);
		sProdCycleNotifyList_h.readData(in);
		sProdCycleNotifyList=sProdCycleNotifyList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_PRODCYCLENOTIFY() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_prodCycleNotify";
		Object[] params=new Object[]{sProdCycleNotifyList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_PRODCYCLENOTIFY",".sdl","MImsTsApp.::IImsTsApp::DEAL_PRODCYCLENOTIFY()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_PRODCYCLENOTIFY() with successful executing.");
		return iRetValue;
	}

	public static int READ_LIFECYCLEONCEPRENOTIFY(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_LIFECYCLEONCEPRENOTIFY",".sdl","MImsTsApp.::IImsTsApp::READ_LIFECYCLEONCEPRENOTIFY()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_LIFECYCLEONCEPRENOTIFY() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_lifeCycleOncePreNotify";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_LIFECYCLEONCEPRENOTIFY",".sdl","MImsTsApp.::IImsTsApp::READ_LIFECYCLEONCEPRENOTIFY()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_LIFECYCLEONCEPRENOTIFY() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_LIFECYCLEONCEPRENOTIFY(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_LIFECYCLEONCEPRENOTIFY",".sdl","MImsTsApp.::IImsTsApp::DEAL_LIFECYCLEONCEPRENOTIFY()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_LIFECYCLEONCEPRENOTIFY() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_lifeCycleOncePreNotify";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_LIFECYCLEONCEPRENOTIFY",".sdl","MImsTsApp.::IImsTsApp::DEAL_LIFECYCLEONCEPRENOTIFY()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_LIFECYCLEONCEPRENOTIFY() with successful executing.");
		return iRetValue;
	}

	public static int READ_IMSUSERORDERCONFIRM(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_IMSUSERORDERCONFIRM",".sdl","MImsTsApp.::IImsTsApp::READ_IMSUSERORDERCONFIRM()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_IMSUSERORDERCONFIRM() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_imsUserOrderConfirm";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_IMSUSERORDERCONFIRM",".sdl","MImsTsApp.::IImsTsApp::READ_IMSUSERORDERCONFIRM()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_IMSUSERORDERCONFIRM() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_IMSUSERORDERCONFIRM(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_IMSUSERORDERCONFIRM",".sdl","MImsTsApp.::IImsTsApp::DEAL_IMSUSERORDERCONFIRM()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<SUserConfirm> sUserConfirmList = new CsdlArrayList<SUserConfirm>(SUserConfirm.class);
		CsdlArrayListHolder<SUserConfirm> sUserConfirmList_h=new CsdlArrayListHolder<SUserConfirm>(sUserConfirmList);
		sUserConfirmList_h.readData(in);
		sUserConfirmList=sUserConfirmList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_IMSUSERORDERCONFIRM() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_imsUserOrderConfirm";
		Object[] params=new Object[]{sUserConfirmList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_IMSUSERORDERCONFIRM",".sdl","MImsTsApp.::IImsTsApp::DEAL_IMSUSERORDERCONFIRM()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_IMSUSERORDERCONFIRM() with successful executing.");
		return iRetValue;
	}

	public static int READ_IMSORDERPRODUCT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_IMSORDERPRODUCT",".sdl","MImsTsApp.::IImsTsApp::READ_IMSORDERPRODUCT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_IMSORDERPRODUCT() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_imsOrderProduct";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_IMSORDERPRODUCT",".sdl","MImsTsApp.::IImsTsApp::READ_IMSORDERPRODUCT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_IMSORDERPRODUCT() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_IMSORDERPRODUCT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_IMSORDERPRODUCT",".sdl","MImsTsApp.::IImsTsApp::DEAL_IMSORDERPRODUCT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<SImsOrderProduct> listImsOrderProduct = new CsdlArrayList<SImsOrderProduct>(SImsOrderProduct.class);
		CsdlArrayListHolder<SImsOrderProduct> listImsOrderProduct_h=new CsdlArrayListHolder<SImsOrderProduct>(listImsOrderProduct);
		listImsOrderProduct_h.readData(in);
		listImsOrderProduct=listImsOrderProduct_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_IMSORDERPRODUCT() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_imsOrderProduct";
		Object[] params=new Object[]{listImsOrderProduct};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_IMSORDERPRODUCT",".sdl","MImsTsApp.::IImsTsApp::DEAL_IMSORDERPRODUCT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_IMSORDERPRODUCT() with successful executing.");
		return iRetValue;
	}

	public static int READ_SHARINGPROD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_SHARINGPROD",".sdl","MImsTsApp.::IImsTsApp::READ_SHARINGPROD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_SHARINGPROD() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_sharingProd";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_SHARINGPROD",".sdl","MImsTsApp.::IImsTsApp::READ_SHARINGPROD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_SHARINGPROD() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_SHARINGPROD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_SHARINGPROD",".sdl","MImsTsApp.::IImsTsApp::DEAL_SHARINGPROD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_SHARINGPROD() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_sharingProd";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_SHARINGPROD",".sdl","MImsTsApp.::IImsTsApp::DEAL_SHARINGPROD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_SHARINGPROD() with successful executing.");
		return iRetValue;
	}

	public static int READ_IMSSYNCTS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_IMSSYNCTS",".sdl","MImsTsApp.::IImsTsApp::READ_IMSSYNCTS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_IMSSYNCTS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_imsSyncTs";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_IMSSYNCTS",".sdl","MImsTsApp.::IImsTsApp::READ_IMSSYNCTS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_IMSSYNCTS() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_IMSSYNCTS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_IMSSYNCTS",".sdl","MImsTsApp.::IImsTsApp::DEAL_IMSSYNCTS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_IMSSYNCTS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_imsSyncTs";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_IMSSYNCTS",".sdl","MImsTsApp.::IImsTsApp::DEAL_IMSSYNCTS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_IMSSYNCTS() with successful executing.");
		return iRetValue;
	}

	public static int READ_DEDUCTFAILURETS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_DEDUCTFAILURETS",".sdl","MImsTsApp.::IImsTsApp::READ_DEDUCTFAILURETS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_DEDUCTFAILURETS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_deductFailureTs";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_DEDUCTFAILURETS",".sdl","MImsTsApp.::IImsTsApp::READ_DEDUCTFAILURETS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_DEDUCTFAILURETS() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_DEDUCTFAILURETS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_DEDUCTFAILURETS",".sdl","MImsTsApp.::IImsTsApp::DEAL_DEDUCTFAILURETS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_DEDUCTFAILURETS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_deductFailureTs";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_DEDUCTFAILURETS",".sdl","MImsTsApp.::IImsTsApp::DEAL_DEDUCTFAILURETS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_DEDUCTFAILURETS() with successful executing.");
		return iRetValue;
	}

	public static int READ_RESETSYSNEGTIVEVALUE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_RESETSYSNEGTIVEVALUE",".sdl","MImsTsApp.::IImsTsApp::READ_RESETSYSNEGTIVEVALUE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_RESETSYSNEGTIVEVALUE() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_resetSysNegtiveValue";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_RESETSYSNEGTIVEVALUE",".sdl","MImsTsApp.::IImsTsApp::READ_RESETSYSNEGTIVEVALUE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_RESETSYSNEGTIVEVALUE() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_RESETSYSNEGTIVEVALUE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_RESETSYSNEGTIVEVALUE",".sdl","MImsTsApp.::IImsTsApp::DEAL_RESETSYSNEGTIVEVALUE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_RESETSYSNEGTIVEVALUE() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_resetSysNegtiveValue";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_RESETSYSNEGTIVEVALUE",".sdl","MImsTsApp.::IImsTsApp::DEAL_RESETSYSNEGTIVEVALUE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_RESETSYSNEGTIVEVALUE() with successful executing.");
		return iRetValue;
	}

	public static int READ_SYNCICS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_SYNCICS",".sdl","MImsTsApp.::IImsTsApp::READ_SYNCICS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_SYNCICS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_syncIcs";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_SYNCICS",".sdl","MImsTsApp.::IImsTsApp::READ_SYNCICS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_SYNCICS() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_SYNCICS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_SYNCICS",".sdl","MImsTsApp.::IImsTsApp::DEAL_SYNCICS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_SYNCICS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_syncIcs";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_SYNCICS",".sdl","MImsTsApp.::IImsTsApp::DEAL_SYNCICS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_SYNCICS() with successful executing.");
		return iRetValue;
	}

	public static int READ_TERMINATEBILLACCTTS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_TERMINATEBILLACCTTS",".sdl","MImsTsApp.::IImsTsApp::READ_TERMINATEBILLACCTTS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsTsApp.READ_TERMINATEBILLACCTTS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "read_terminateBillAcctTs";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_TERMINATEBILLACCTTS",".sdl","MImsTsApp.::IImsTsApp::READ_TERMINATEBILLACCTTS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.READ_TERMINATEBILLACCTTS() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_TERMINATEBILLACCTTS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_TERMINATEBILLACCTTS",".sdl","MImsTsApp.::IImsTsApp::DEAL_TERMINATEBILLACCTTS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlArrayList<STsRecord> sTsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.readData(in);
		sTsRecordList=sTsRecordList_h.value;
		
		log.debug("start call impl method IImsTsApp.DEAL_TERMINATEBILLACCTTS() alias query_rewardInfo2");
		String moduleName = "imsts";
		String serviceName = "IImsTsApp";
		String methodName = "deal_terminateBillAcctTs";
		Object[] params=new Object[]{sTsRecordList};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_TERMINATEBILLACCTTS",".sdl","MImsTsApp.::IImsTsApp::DEAL_TERMINATEBILLACCTTS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsTsApp.DEAL_TERMINATEBILLACCTTS() with successful executing.");
		return iRetValue;
	}


}