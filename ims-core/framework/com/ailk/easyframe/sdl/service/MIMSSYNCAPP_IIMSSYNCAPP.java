package com.ailk.easyframe.sdl.service;

import org.apache.commons.logging.LogFactory;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_Stream;
import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.util.Utility;
import com.ailk.openbilling.persistence.imssdl.entity.SOperInfo;
import jef.common.Out;
import com.ailk.openbilling.persistence.imssdl.entity.SUpSellReq;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObjectHolder;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.openbilling.persistence.imssdl.entity.SLifeCycleChg4Abm;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayListHolder;
import com.ailk.openbilling.persistence.imssdl.entity.SFirstActiveRes;
import com.ailk.openbilling.persistence.imssdl.entity.SCaRewardInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SProductStatus;
import com.ailk.openbilling.persistence.imssdl.entity.SCreditStatus;
import com.ailk.openbilling.persistence.imssdl.entity.SResLifeCycleSync;
import com.ailk.openbilling.persistence.imssdl.entity.SResSevCycle;
import com.ailk.openbilling.persistence.imssdl.entity.SOneTimeProm;
import com.ailk.openbilling.persistence.imssdl.entity.SNotifySendStandardIntf;
import com.ailk.openbilling.persistence.imssdl.entity.SLowBalance;
import com.ailk.openbilling.persistence.imssdl.entity.SUserValidityChg;
import com.ailk.openbilling.persistence.imssdl.entity.SRewardOrderProduct;
import com.ailk.openbilling.persistence.imssdl.entity.SLifeCycleChgByPayment;
import com.ailk.openbilling.persistence.imssdl.entity.SUpdatePriceParam;
import com.ailk.openbilling.persistence.imssdl.entity.SBarService;
import com.ailk.openbilling.persistence.imssdl.entity.SProdNotify;
import com.ailk.openbilling.persistence.imssdl.entity.SQueryOperReq;
import com.ailk.openbilling.persistence.imssdl.entity.SOperInfoResp;
import jef.common.In;
import com.ailk.openbilling.persistence.imssdl.entity.SQueryOrgReq;
import com.ailk.openbilling.persistence.imssdl.entity.SOrgInfoResp;
import com.ailk.openbilling.persistence.imssdl.entity.SResourceMonitor;
import com.ailk.openbilling.persistence.imssdl.entity.SDeductAlarm;
import com.ailk.openbilling.persistence.imssdl.entity.SChangeProdInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SRewardOtFreeres;
import com.ailk.openbilling.persistence.imssdl.entity.SFirstAct;
import com.ailk.openbilling.persistence.imssdl.entity.SQueryAcctInfoSdlReq;
import com.ailk.openbilling.persistence.imssdl.entity.SAccountInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SImmeSms;
import com.ailk.openbilling.persistence.imssdl.entity.SBatchSmsInfo;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import org.apache.commons.logging.Log;
import jef.codegen.support.NotModified;
@NotModified
public class MIMSSYNCAPP_IIMSSYNCAPP{

	protected static Log log = LogFactory.getLog(MIMSSYNCAPP_IIMSSYNCAPP.class);

	public static int DO_UPSELLING(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_UPSELLING",".sdl","MImsSyncApp.::IImsSyncApp::DO_UPSELLING()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SUpSellReq sUpSellReq = new SUpSellReq();
		CsdlStructObjectHolder<SUpSellReq> sUpSellReq_h=new CsdlStructObjectHolder<SUpSellReq>(sUpSellReq);
		sUpSellReq_h.readData(in);
		sUpSellReq=sUpSellReq_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_UPSELLING() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_upSelling";
		Object[] params=new Object[]{sOperInfo,sUpSellReq};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_UPSELLING",".sdl","MImsSyncApp.::IImsSyncApp::DO_UPSELLING()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_UPSELLING() with successful executing.");
		return iRetValue;
	}

	public static int DO_LIFECYCLECHG(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_LIFECYCLECHG",".sdl","MImsSyncApp.::IImsSyncApp::DO_LIFECYCLECHG()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SLifeCycleChg4Abm> listLifeCycleChg4Abm = new CsdlArrayList<SLifeCycleChg4Abm>(SLifeCycleChg4Abm.class);
		CsdlArrayListHolder<SLifeCycleChg4Abm> listLifeCycleChg4Abm_h=new CsdlArrayListHolder<SLifeCycleChg4Abm>(listLifeCycleChg4Abm);
		listLifeCycleChg4Abm_h.readData(in);
		listLifeCycleChg4Abm=listLifeCycleChg4Abm_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_LIFECYCLECHG() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_lifeCycleChg";
		Object[] params=new Object[]{sOperInfo,listLifeCycleChg4Abm};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_LIFECYCLECHG",".sdl","MImsSyncApp.::IImsSyncApp::DO_LIFECYCLECHG()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_LIFECYCLECHG() with successful executing.");
		return iRetValue;
	}

	public static int TS_FIRSTACT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"TS_FIRSTACT",".sdl","MImsSyncApp.::IImsSyncApp::TS_FIRSTACT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SFirstActiveRes sFirstActiveRes = new SFirstActiveRes();
		CsdlStructObjectHolder<SFirstActiveRes> sFirstActiveRes_h=new CsdlStructObjectHolder<SFirstActiveRes>(sFirstActiveRes);
		sFirstActiveRes_h.readData(in);
		sFirstActiveRes=sFirstActiveRes_h.value;
		CsdlArrayList<SCaRewardInfo> listCaRewardInfo = new CsdlArrayList<SCaRewardInfo>(SCaRewardInfo.class);
		CsdlArrayListHolder<SCaRewardInfo> listCaRewardInfo_h=new CsdlArrayListHolder<SCaRewardInfo>(listCaRewardInfo);
		listCaRewardInfo_h.readData(in);
		listCaRewardInfo=listCaRewardInfo_h.value;
		
		log.debug("start call impl method IImsSyncApp.TS_FIRSTACT() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "ts_firstAct";
		Object[] params=new Object[]{sOperInfo,sFirstActiveRes,listCaRewardInfo};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"TS_FIRSTACT",".sdl","MImsSyncApp.::IImsSyncApp::TS_FIRSTACT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.TS_FIRSTACT() with successful executing.");
		return iRetValue;
	}

	public static int SYNC_PRODUCTSTATUS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"SYNC_PRODUCTSTATUS",".sdl","MImsSyncApp.::IImsSyncApp::SYNC_PRODUCTSTATUS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SProductStatus> listProductStatus = new CsdlArrayList<SProductStatus>(SProductStatus.class);
		CsdlArrayListHolder<SProductStatus> listProductStatus_h=new CsdlArrayListHolder<SProductStatus>(listProductStatus);
		listProductStatus_h.readData(in);
		listProductStatus=listProductStatus_h.value;
		
		log.debug("start call impl method IImsSyncApp.SYNC_PRODUCTSTATUS() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "sync_productStatus";
		Object[] params=new Object[]{sOperInfo,listProductStatus};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"SYNC_PRODUCTSTATUS",".sdl","MImsSyncApp.::IImsSyncApp::SYNC_PRODUCTSTATUS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.SYNC_PRODUCTSTATUS() with successful executing.");
		return iRetValue;
	}

	public static int SYNC_CREDITSTATUS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"SYNC_CREDITSTATUS",".sdl","MImsSyncApp.::IImsSyncApp::SYNC_CREDITSTATUS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SCreditStatus sCreditStatus = new SCreditStatus();
		CsdlStructObjectHolder<SCreditStatus> sCreditStatus_h=new CsdlStructObjectHolder<SCreditStatus>(sCreditStatus);
		sCreditStatus_h.readData(in);
		sCreditStatus=sCreditStatus_h.value;
		
		log.debug("start call impl method IImsSyncApp.SYNC_CREDITSTATUS() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "sync_creditStatus";
		Object[] params=new Object[]{sOperInfo,sCreditStatus};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"SYNC_CREDITSTATUS",".sdl","MImsSyncApp.::IImsSyncApp::SYNC_CREDITSTATUS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.SYNC_CREDITSTATUS() with successful executing.");
		return iRetValue;
	}

	public static int READ_LIFECYCLESTSCHECK(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_LIFECYCLESTSCHECK",".sdl","MImsSyncApp.::IImsSyncApp::READ_LIFECYCLESTSCHECK()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsSyncApp.READ_LIFECYCLESTSCHECK() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "read_lifeCycleStsCheck";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_LIFECYCLESTSCHECK",".sdl","MImsSyncApp.::IImsSyncApp::READ_LIFECYCLESTSCHECK()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.READ_LIFECYCLESTSCHECK() with successful executing.");
		return iRetValue;
	}

	public static int DEAL_LIFECYCLESTSCHECK(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DEAL_LIFECYCLESTSCHECK",".sdl","MImsSyncApp.::IImsSyncApp::DEAL_LIFECYCLESTSCHECK()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SResLifeCycleSync sResLifeCycleSync = new SResLifeCycleSync();
		CsdlStructObjectHolder<SResLifeCycleSync> sResLifeCycleSync_h=new CsdlStructObjectHolder<SResLifeCycleSync>(sResLifeCycleSync);
		sResLifeCycleSync_h.readData(in);
		sResLifeCycleSync=sResLifeCycleSync_h.value;
		
		log.debug("start call impl method IImsSyncApp.DEAL_LIFECYCLESTSCHECK() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "deal_lifeCycleStsCheck";
		Object[] params=new Object[]{sOperInfo,sResLifeCycleSync};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DEAL_LIFECYCLESTSCHECK",".sdl","MImsSyncApp.::IImsSyncApp::DEAL_LIFECYCLESTSCHECK()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DEAL_LIFECYCLESTSCHECK() with successful executing.");
		return iRetValue;
	}

	public static int ADD_EXPIREPROD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"ADD_EXPIREPROD",".sdl","MImsSyncApp.::IImsSyncApp::ADD_EXPIREPROD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsSyncApp.ADD_EXPIREPROD() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "add_expireProd";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"ADD_EXPIREPROD",".sdl","MImsSyncApp.::IImsSyncApp::ADD_EXPIREPROD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.ADD_EXPIREPROD() with successful executing.");
		return iRetValue;
	}

	public static int ADD_VALIDPROD(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"ADD_VALIDPROD",".sdl","MImsSyncApp.::IImsSyncApp::ADD_VALIDPROD()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsSyncApp.ADD_VALIDPROD() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "add_validProd";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"ADD_VALIDPROD",".sdl","MImsSyncApp.::IImsSyncApp::ADD_VALIDPROD()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.ADD_VALIDPROD() with successful executing.");
		return iRetValue;
	}

	public static int ADD_PRODCYCLENOTIFY(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"ADD_PRODCYCLENOTIFY",".sdl","MImsSyncApp.::IImsSyncApp::ADD_PRODCYCLENOTIFY()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsSyncApp.ADD_PRODCYCLENOTIFY() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "add_prodCycleNotify";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"ADD_PRODCYCLENOTIFY",".sdl","MImsSyncApp.::IImsSyncApp::ADD_PRODCYCLENOTIFY()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.ADD_PRODCYCLENOTIFY() with successful executing.");
		return iRetValue;
	}

	public static int DO_RESSEVCYCLECHG(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_RESSEVCYCLECHG",".sdl","MImsSyncApp.::IImsSyncApp::DO_RESSEVCYCLECHG()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SResSevCycle> listResSevCycle = new CsdlArrayList<SResSevCycle>(SResSevCycle.class);
		CsdlArrayListHolder<SResSevCycle> listResSevCycle_h=new CsdlArrayListHolder<SResSevCycle>(listResSevCycle);
		listResSevCycle_h.readData(in);
		listResSevCycle=listResSevCycle_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_RESSEVCYCLECHG() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_resSevCycleChg";
		Object[] params=new Object[]{sOperInfo,listResSevCycle};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_RESSEVCYCLECHG",".sdl","MImsSyncApp.::IImsSyncApp::DO_RESSEVCYCLECHG()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_RESSEVCYCLECHG() with successful executing.");
		return iRetValue;
	}

	public static int SYNC_ONETIMEPROMUSEDUP(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"SYNC_ONETIMEPROMUSEDUP",".sdl","MImsSyncApp.::IImsSyncApp::SYNC_ONETIMEPROMUSEDUP()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SOneTimeProm sOneTimeProm = new SOneTimeProm();
		CsdlStructObjectHolder<SOneTimeProm> sOneTimeProm_h=new CsdlStructObjectHolder<SOneTimeProm>(sOneTimeProm);
		sOneTimeProm_h.readData(in);
		sOneTimeProm=sOneTimeProm_h.value;
		
		log.debug("start call impl method IImsSyncApp.SYNC_ONETIMEPROMUSEDUP() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "sync_oneTimePromUsedUp";
		Object[] params=new Object[]{sOperInfo,sOneTimeProm};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"SYNC_ONETIMEPROMUSEDUP",".sdl","MImsSyncApp.::IImsSyncApp::SYNC_ONETIMEPROMUSEDUP()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.SYNC_ONETIMEPROMUSEDUP() with successful executing.");
		return iRetValue;
	}

	public static int DO_SENDNOTIFICATION(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_SENDNOTIFICATION",".sdl","MImsSyncApp.::IImsSyncApp::DO_SENDNOTIFICATION()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SNotifySendStandardIntf> listNotifySendStandardIntf = new CsdlArrayList<SNotifySendStandardIntf>(SNotifySendStandardIntf.class);
		CsdlArrayListHolder<SNotifySendStandardIntf> listNotifySendStandardIntf_h=new CsdlArrayListHolder<SNotifySendStandardIntf>(listNotifySendStandardIntf);
		listNotifySendStandardIntf_h.readData(in);
		listNotifySendStandardIntf=listNotifySendStandardIntf_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_SENDNOTIFICATION() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_sendNotification";
		Object[] params=new Object[]{sOperInfo,listNotifySendStandardIntf};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_SENDNOTIFICATION",".sdl","MImsSyncApp.::IImsSyncApp::DO_SENDNOTIFICATION()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_SENDNOTIFICATION() with successful executing.");
		return iRetValue;
	}

	public static int DO_SYNCLOWBALANCEETOPUP(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_SYNCLOWBALANCEETOPUP",".sdl","MImsSyncApp.::IImsSyncApp::DO_SYNCLOWBALANCEETOPUP()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SLowBalance> listLowBalance = new CsdlArrayList<SLowBalance>(SLowBalance.class);
		CsdlArrayListHolder<SLowBalance> listLowBalance_h=new CsdlArrayListHolder<SLowBalance>(listLowBalance);
		listLowBalance_h.readData(in);
		listLowBalance=listLowBalance_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_SYNCLOWBALANCEETOPUP() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_syncLowBalanceEtopup";
		Object[] params=new Object[]{sOperInfo,listLowBalance};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_SYNCLOWBALANCEETOPUP",".sdl","MImsSyncApp.::IImsSyncApp::DO_SYNCLOWBALANCEETOPUP()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_SYNCLOWBALANCEETOPUP() with successful executing.");
		return iRetValue;
	}

	public static int DO_USERVALIDITYCHG(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_USERVALIDITYCHG",".sdl","MImsSyncApp.::IImsSyncApp::DO_USERVALIDITYCHG()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SUserValidityChg sUserValidityChg = new SUserValidityChg();
		CsdlStructObjectHolder<SUserValidityChg> sUserValidityChg_h=new CsdlStructObjectHolder<SUserValidityChg>(sUserValidityChg);
		sUserValidityChg_h.readData(in);
		sUserValidityChg=sUserValidityChg_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_USERVALIDITYCHG() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_userValidityChg";
		Object[] params=new Object[]{sOperInfo,sUserValidityChg};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_USERVALIDITYCHG",".sdl","MImsSyncApp.::IImsSyncApp::DO_USERVALIDITYCHG()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_USERVALIDITYCHG() with successful executing.");
		return iRetValue;
	}

	public static int DO_ORDERREWARDPRODUCT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_ORDERREWARDPRODUCT",".sdl","MImsSyncApp.::IImsSyncApp::DO_ORDERREWARDPRODUCT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SRewardOrderProduct sRewardOrderProduct = new SRewardOrderProduct();
		CsdlStructObjectHolder<SRewardOrderProduct> sRewardOrderProduct_h=new CsdlStructObjectHolder<SRewardOrderProduct>(sRewardOrderProduct);
		sRewardOrderProduct_h.readData(in);
		sRewardOrderProduct=sRewardOrderProduct_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_ORDERREWARDPRODUCT() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_orderRewardProduct";
		Object[] params=new Object[]{sOperInfo,sRewardOrderProduct};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_ORDERREWARDPRODUCT",".sdl","MImsSyncApp.::IImsSyncApp::DO_ORDERREWARDPRODUCT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_ORDERREWARDPRODUCT() with successful executing.");
		return iRetValue;
	}

	public static int DO_LIFECYCLECHGBYPAYMENT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_LIFECYCLECHGBYPAYMENT",".sdl","MImsSyncApp.::IImsSyncApp::DO_LIFECYCLECHGBYPAYMENT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SLifeCycleChgByPayment sLifeCycleChgByPayment = new SLifeCycleChgByPayment();
		CsdlStructObjectHolder<SLifeCycleChgByPayment> sLifeCycleChgByPayment_h=new CsdlStructObjectHolder<SLifeCycleChgByPayment>(sLifeCycleChgByPayment);
		sLifeCycleChgByPayment_h.readData(in);
		sLifeCycleChgByPayment=sLifeCycleChgByPayment_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_LIFECYCLECHGBYPAYMENT() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_lifeCycleChgByPayment";
		Object[] params=new Object[]{sOperInfo,sLifeCycleChgByPayment};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_LIFECYCLECHGBYPAYMENT",".sdl","MImsSyncApp.::IImsSyncApp::DO_LIFECYCLECHGBYPAYMENT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_LIFECYCLECHGBYPAYMENT() with successful executing.");
		return iRetValue;
	}

	public static int DO_UPDATEPRODPRICEPARAM(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_UPDATEPRODPRICEPARAM",".sdl","MImsSyncApp.::IImsSyncApp::DO_UPDATEPRODPRICEPARAM()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SUpdatePriceParam> listUpdatePriceParam = new CsdlArrayList<SUpdatePriceParam>(SUpdatePriceParam.class);
		CsdlArrayListHolder<SUpdatePriceParam> listUpdatePriceParam_h=new CsdlArrayListHolder<SUpdatePriceParam>(listUpdatePriceParam);
		listUpdatePriceParam_h.readData(in);
		listUpdatePriceParam=listUpdatePriceParam_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_UPDATEPRODPRICEPARAM() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_updateProdPriceParam";
		Object[] params=new Object[]{sOperInfo,listUpdatePriceParam};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_UPDATEPRODPRICEPARAM",".sdl","MImsSyncApp.::IImsSyncApp::DO_UPDATEPRODPRICEPARAM()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_UPDATEPRODPRICEPARAM() with successful executing.");
		return iRetValue;
	}

	public static int DO_BARSERVICE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_BARSERVICE",".sdl","MImsSyncApp.::IImsSyncApp::DO_BARSERVICE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SBarService> listBarService = new CsdlArrayList<SBarService>(SBarService.class);
		CsdlArrayListHolder<SBarService> listBarService_h=new CsdlArrayListHolder<SBarService>(listBarService);
		listBarService_h.readData(in);
		listBarService=listBarService_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_BARSERVICE() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_barService";
		Object[] params=new Object[]{sOperInfo,listBarService};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_BARSERVICE",".sdl","MImsSyncApp.::IImsSyncApp::DO_BARSERVICE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_BARSERVICE() with successful executing.");
		return iRetValue;
	}

	public static int CREATE_PRODNOTIFYINFO(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"CREATE_PRODNOTIFYINFO",".sdl","MImsSyncApp.::IImsSyncApp::CREATE_PRODNOTIFYINFO()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SProdNotify> listProdNotify = new CsdlArrayList<SProdNotify>(SProdNotify.class);
		CsdlArrayListHolder<SProdNotify> listProdNotify_h=new CsdlArrayListHolder<SProdNotify>(listProdNotify);
		listProdNotify_h.readData(in);
		listProdNotify=listProdNotify_h.value;
		
		log.debug("start call impl method IImsSyncApp.CREATE_PRODNOTIFYINFO() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "create_prodNotifyInfo";
		Object[] params=new Object[]{sOperInfo,listProdNotify};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"CREATE_PRODNOTIFYINFO",".sdl","MImsSyncApp.::IImsSyncApp::CREATE_PRODNOTIFYINFO()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.CREATE_PRODNOTIFYINFO() with successful executing.");
		return iRetValue;
	}

	public static int DO_QUERYOPERATORINFO(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_QUERYOPERATORINFO",".sdl","MImsSyncApp.::IImsSyncApp::DO_QUERYOPERATORINFO()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SQueryOperReq sQueryOperReq = new SQueryOperReq();
		CsdlStructObjectHolder<SQueryOperReq> sQueryOperReq_h=new CsdlStructObjectHolder<SQueryOperReq>(sQueryOperReq);
		sQueryOperReq_h.readData(in);
		sQueryOperReq=sQueryOperReq_h.value;
		CsdlArrayListHolder<SOperInfoResp> listOperInfoResp = new CsdlArrayListHolder<SOperInfoResp>(new CsdlArrayList<SOperInfoResp>(SOperInfoResp.class));
		
		log.debug("start call impl method IImsSyncApp.DO_QUERYOPERATORINFO() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_queryOperatorInfo";
		Object[] params=new Object[]{sOperInfo,sQueryOperReq,listOperInfoResp};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("listOperInfoResp", listOperInfoResp);
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_QUERYOPERATORINFO",".sdl","MImsSyncApp.::IImsSyncApp::DO_QUERYOPERATORINFO()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_QUERYOPERATORINFO() with successful executing.");
		return iRetValue;
	}

	public static int DO_QUERYORGANIZEINFO(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_QUERYORGANIZEINFO",".sdl","MImsSyncApp.::IImsSyncApp::DO_QUERYORGANIZEINFO()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SQueryOrgReq sQueryOrgReq = new SQueryOrgReq();
		CsdlStructObjectHolder<SQueryOrgReq> sQueryOrgReq_h=new CsdlStructObjectHolder<SQueryOrgReq>(sQueryOrgReq);
		sQueryOrgReq_h.readData(in);
		sQueryOrgReq=sQueryOrgReq_h.value;
		CsdlArrayListHolder<SOrgInfoResp> listOrgInfoResp = new CsdlArrayListHolder<SOrgInfoResp>(new CsdlArrayList<SOrgInfoResp>(SOrgInfoResp.class));
		
		log.debug("start call impl method IImsSyncApp.DO_QUERYORGANIZEINFO() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_queryOrganizeInfo";
		Object[] params=new Object[]{sOperInfo,sQueryOrgReq,listOrgInfoResp};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("listOrgInfoResp", listOrgInfoResp);
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_QUERYORGANIZEINFO",".sdl","MImsSyncApp.::IImsSyncApp::DO_QUERYORGANIZEINFO()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_QUERYORGANIZEINFO() with successful executing.");
		return iRetValue;
	}

	public static int DO_INSERTIMSRESOURCEMONITOR(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_INSERTIMSRESOURCEMONITOR",".sdl","MImsSyncApp.::IImsSyncApp::DO_INSERTIMSRESOURCEMONITOR()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SResourceMonitor> listResourceMonitor = new CsdlArrayList<SResourceMonitor>(SResourceMonitor.class);
		CsdlArrayListHolder<SResourceMonitor> listResourceMonitor_h=new CsdlArrayListHolder<SResourceMonitor>(listResourceMonitor);
		listResourceMonitor_h.readData(in);
		listResourceMonitor=listResourceMonitor_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_INSERTIMSRESOURCEMONITOR() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_insertImsResourceMonitor";
		Object[] params=new Object[]{sOperInfo,listResourceMonitor};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_INSERTIMSRESOURCEMONITOR",".sdl","MImsSyncApp.::IImsSyncApp::DO_INSERTIMSRESOURCEMONITOR()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_INSERTIMSRESOURCEMONITOR() with successful executing.");
		return iRetValue;
	}

	public static int DO_INSERTIMSUSERORDERCONFIRM(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_INSERTIMSUSERORDERCONFIRM",".sdl","MImsSyncApp.::IImsSyncApp::DO_INSERTIMSUSERORDERCONFIRM()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SDeductAlarm> listDeductAlarm = new CsdlArrayList<SDeductAlarm>(SDeductAlarm.class);
		CsdlArrayListHolder<SDeductAlarm> listDeductAlarm_h=new CsdlArrayListHolder<SDeductAlarm>(listDeductAlarm);
		listDeductAlarm_h.readData(in);
		listDeductAlarm=listDeductAlarm_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_INSERTIMSUSERORDERCONFIRM() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_insertImsUserOrderConfirm";
		Object[] params=new Object[]{sOperInfo,listDeductAlarm};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_INSERTIMSUSERORDERCONFIRM",".sdl","MImsSyncApp.::IImsSyncApp::DO_INSERTIMSUSERORDERCONFIRM()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_INSERTIMSUSERORDERCONFIRM() with successful executing.");
		return iRetValue;
	}

	public static int DO_MODIFYPRODUCT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_MODIFYPRODUCT",".sdl","MImsSyncApp.::IImsSyncApp::DO_MODIFYPRODUCT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SChangeProdInfo> listProdInfo = new CsdlArrayList<SChangeProdInfo>(SChangeProdInfo.class);
		CsdlArrayListHolder<SChangeProdInfo> listProdInfo_h=new CsdlArrayListHolder<SChangeProdInfo>(listProdInfo);
		listProdInfo_h.readData(in);
		listProdInfo=listProdInfo_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_MODIFYPRODUCT() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_modifyProduct";
		Object[] params=new Object[]{sOperInfo,listProdInfo};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_MODIFYPRODUCT",".sdl","MImsSyncApp.::IImsSyncApp::DO_MODIFYPRODUCT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_MODIFYPRODUCT() with successful executing.");
		return iRetValue;
	}

	public static int DO_REWARDOTFREERES(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_REWARDOTFREERES",".sdl","MImsSyncApp.::IImsSyncApp::DO_REWARDOTFREERES()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SRewardOtFreeres sRewardOtFreeres = new SRewardOtFreeres();
		CsdlStructObjectHolder<SRewardOtFreeres> sRewardOtFreeres_h=new CsdlStructObjectHolder<SRewardOtFreeres>(sRewardOtFreeres);
		sRewardOtFreeres_h.readData(in);
		sRewardOtFreeres=sRewardOtFreeres_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_REWARDOTFREERES() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_rewardOtFreeres";
		Object[] params=new Object[]{sOperInfo,sRewardOtFreeres};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_REWARDOTFREERES",".sdl","MImsSyncApp.::IImsSyncApp::DO_REWARDOTFREERES()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_REWARDOTFREERES() with successful executing.");
		return iRetValue;
	}

	public static int READ_USERVALIDITY(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"READ_USERVALIDITY",".sdl","MImsSyncApp.::IImsSyncApp::READ_USERVALIDITY()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsSyncApp.READ_USERVALIDITY() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "read_userValidity";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"READ_USERVALIDITY",".sdl","MImsSyncApp.::IImsSyncApp::READ_USERVALIDITY()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.READ_USERVALIDITY() with successful executing.");
		return iRetValue;
	}

	public static int DO_FIRSTACTIVE(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_FIRSTACTIVE",".sdl","MImsSyncApp.::IImsSyncApp::DO_FIRSTACTIVE()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SFirstAct sFirstAct = new SFirstAct();
		CsdlStructObjectHolder<SFirstAct> sFirstAct_h=new CsdlStructObjectHolder<SFirstAct>(sFirstAct);
		sFirstAct_h.readData(in);
		sFirstAct=sFirstAct_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_FIRSTACTIVE() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_firstActive";
		Object[] params=new Object[]{sOperInfo,sFirstAct};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_FIRSTACTIVE",".sdl","MImsSyncApp.::IImsSyncApp::DO_FIRSTACTIVE()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_FIRSTACTIVE() with successful executing.");
		return iRetValue;
	}

	public static int DO_QUERYACCTINFO(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_QUERYACCTINFO",".sdl","MImsSyncApp.::IImsSyncApp::DO_QUERYACCTINFO()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SQueryAcctInfoSdlReq sQueryAcctInfoSdlReq = new SQueryAcctInfoSdlReq();
		CsdlStructObjectHolder<SQueryAcctInfoSdlReq> sQueryAcctInfoSdlReq_h=new CsdlStructObjectHolder<SQueryAcctInfoSdlReq>(sQueryAcctInfoSdlReq);
		sQueryAcctInfoSdlReq_h.readData(in);
		sQueryAcctInfoSdlReq=sQueryAcctInfoSdlReq_h.value;
		CsdlStructObjectHolder<SAccountInfo> sAccountInfo = new CsdlStructObjectHolder<SAccountInfo>(new SAccountInfo());
		
		log.debug("start call impl method IImsSyncApp.DO_QUERYACCTINFO() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_queryAcctInfo";
		Object[] params=new Object[]{sOperInfo,sQueryAcctInfoSdlReq,sAccountInfo};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("sAccountInfo", sAccountInfo);
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_QUERYACCTINFO",".sdl","MImsSyncApp.::IImsSyncApp::DO_QUERYACCTINFO()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_QUERYACCTINFO() with successful executing.");
		return iRetValue;
	}

	public static int DO_DEDUCTFAILURETS(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_DEDUCTFAILURETS",".sdl","MImsSyncApp.::IImsSyncApp::DO_DEDUCTFAILURETS()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		
		log.debug("start call impl method IImsSyncApp.DO_DEDUCTFAILURETS() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_deductFailureTs";
		Object[] params=new Object[]{};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_DEDUCTFAILURETS",".sdl","MImsSyncApp.::IImsSyncApp::DO_DEDUCTFAILURETS()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_DEDUCTFAILURETS() with successful executing.");
		return iRetValue;
	}

	public static int DO_INSERTIMMESMSBYSDL(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_INSERTIMMESMSBYSDL",".sdl","MImsSyncApp.::IImsSyncApp::DO_INSERTIMMESMSBYSDL()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		CsdlArrayList<SImmeSms> listImmeSms = new CsdlArrayList<SImmeSms>(SImmeSms.class);
		CsdlArrayListHolder<SImmeSms> listImmeSms_h=new CsdlArrayListHolder<SImmeSms>(listImmeSms);
		listImmeSms_h.readData(in);
		listImmeSms=listImmeSms_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_INSERTIMMESMSBYSDL() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_insertImmeSmsBySdl";
		Object[] params=new Object[]{sOperInfo,listImmeSms};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_INSERTIMMESMSBYSDL",".sdl","MImsSyncApp.::IImsSyncApp::DO_INSERTIMMESMSBYSDL()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_INSERTIMMESMSBYSDL() with successful executing.");
		return iRetValue;
	}

	public static int DO_INSERTBATCHSMSBYSDL(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_INSERTBATCHSMSBYSDL",".sdl","MImsSyncApp.::IImsSyncApp::DO_INSERTBATCHSMSBYSDL()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		SOperInfo sOperInfo = new SOperInfo();
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.readData(in);
		sOperInfo=sOperInfo_h.value;
		SBatchSmsInfo sBatchSmsInfo = new SBatchSmsInfo();
		CsdlStructObjectHolder<SBatchSmsInfo> sBatchSmsInfo_h=new CsdlStructObjectHolder<SBatchSmsInfo>(sBatchSmsInfo);
		sBatchSmsInfo_h.readData(in);
		sBatchSmsInfo=sBatchSmsInfo_h.value;
		
		log.debug("start call impl method IImsSyncApp.DO_INSERTBATCHSMSBYSDL() alias query_rewardInfo2");
		String moduleName = "imssdl";
		String serviceName = "IImsSyncApp";
		String methodName = "do_insertBatchSmsBySdl";
		Object[] params=new Object[]{sOperInfo,sBatchSmsInfo};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_INSERTBATCHSMSBYSDL",".sdl","MImsSyncApp.::IImsSyncApp::DO_INSERTBATCHSMSBYSDL()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsSyncApp.DO_INSERTBATCHSMSBYSDL() with successful executing.");
		return iRetValue;
	}


}