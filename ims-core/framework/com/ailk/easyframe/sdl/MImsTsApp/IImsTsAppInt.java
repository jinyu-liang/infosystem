package com.ailk.easyframe.sdl.MImsTsApp;

import com.ailk.easyframe.sdl.sdlclient.CsdlClientObject;
import org.apache.commons.logging.LogFactory;
import java.util.LinkedHashMap;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_StreamDecorator;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_Stream;
import jef.common.In;
import jef.common.Out;
import jef.common.InOut;
import com.ailk.easyframe.sdl.util.Utility;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.openbilling.persistence.imsts.entity.STsRecord;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayListHolder;
import com.ailk.openbilling.persistence.imsts.entity.SUserConfirm;
import com.ailk.openbilling.persistence.imsts.entity.SImsOrderProduct;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import org.apache.commons.logging.Log;
import jef.codegen.support.NotModified;
@NotModified
public class IImsTsAppInt extends CsdlClientObject{

	protected static Log log = LogFactory.getLog(IImsTsAppInt.class);

	public int read_firstActive(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_firstActive()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_firstActive()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_firstActive", in, out);
		log.debug("ended call remote method IImsTsApp.read_firstActive() alias read_firstActive");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_firstActive() with successful executing.");
		return iRetValue;
	}

	public int read_firstActive_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_firstActive()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_firstActive()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_firstActive", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_firstActive() alias read_firstActive");
		return iRetValue;
	}

	public int deal_firstActive(@In CsdlArrayList<STsRecord> sCustProdSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_firstActive()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sCustProdSyncList_h=new CsdlArrayListHolder<STsRecord>(sCustProdSyncList);
		sCustProdSyncList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_firstActive()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_firstActive", in, out);
		log.debug("ended call remote method IImsTsApp.deal_firstActive() alias deal_firstActive");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_firstActive() with successful executing.");
		return iRetValue;
	}

	public int deal_firstActive_upload(@In CsdlArrayList<STsRecord> sCustProdSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_firstActive()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sCustProdSyncList_h=new CsdlArrayListHolder<STsRecord>(sCustProdSyncList);
		sCustProdSyncList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_firstActive()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_firstActive", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_firstActive() alias deal_firstActive");
		return iRetValue;
	}

	public int read_creditStatus(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_creditStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_creditStatus()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_creditStatus", in, out);
		log.debug("ended call remote method IImsTsApp.read_creditStatus() alias read_creditStatus");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_creditStatus() with successful executing.");
		return iRetValue;
	}

	public int read_creditStatus_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_creditStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_creditStatus()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_creditStatus", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_creditStatus() alias read_creditStatus");
		return iRetValue;
	}

	public int deal_creditStatus(@In CsdlArrayList<STsRecord> sCreditStatusSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_creditStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sCreditStatusSyncList_h=new CsdlArrayListHolder<STsRecord>(sCreditStatusSyncList);
		sCreditStatusSyncList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_creditStatus()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_creditStatus", in, out);
		log.debug("ended call remote method IImsTsApp.deal_creditStatus() alias deal_creditStatus");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_creditStatus() with successful executing.");
		return iRetValue;
	}

	public int deal_creditStatus_upload(@In CsdlArrayList<STsRecord> sCreditStatusSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_creditStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sCreditStatusSyncList_h=new CsdlArrayListHolder<STsRecord>(sCreditStatusSyncList);
		sCreditStatusSyncList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_creditStatus()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_creditStatus", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_creditStatus() alias deal_creditStatus");
		return iRetValue;
	}

	public int read_productStatus(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_productStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_productStatus()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_productStatus", in, out);
		log.debug("ended call remote method IImsTsApp.read_productStatus() alias read_productStatus");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_productStatus() with successful executing.");
		return iRetValue;
	}

	public int read_productStatus_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_productStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_productStatus()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_productStatus", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_productStatus() alias read_productStatus");
		return iRetValue;
	}

	public int deal_productStatus(@In CsdlArrayList<STsRecord> sProdStsSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_productStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sProdStsSyncList_h=new CsdlArrayListHolder<STsRecord>(sProdStsSyncList);
		sProdStsSyncList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_productStatus()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_productStatus", in, out);
		log.debug("ended call remote method IImsTsApp.deal_productStatus() alias deal_productStatus");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_productStatus() with successful executing.");
		return iRetValue;
	}

	public int deal_productStatus_upload(@In CsdlArrayList<STsRecord> sProdStsSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_productStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sProdStsSyncList_h=new CsdlArrayListHolder<STsRecord>(sProdStsSyncList);
		sProdStsSyncList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_productStatus()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_productStatus", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_productStatus() alias deal_productStatus");
		return iRetValue;
	}

	public int read_userStsSync(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_userStsSync()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_userStsSync()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_userStsSync", in, out);
		log.debug("ended call remote method IImsTsApp.read_userStsSync() alias read_userStsSync");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_userStsSync() with successful executing.");
		return iRetValue;
	}

	public int read_userStsSync_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_userStsSync()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_userStsSync()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_userStsSync", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_userStsSync() alias read_userStsSync");
		return iRetValue;
	}

	public int deal_userStsSync(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_userStsSync()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_userStsSync()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_userStsSync", in, out);
		log.debug("ended call remote method IImsTsApp.deal_userStsSync() alias deal_userStsSync");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_userStsSync() with successful executing.");
		return iRetValue;
	}

	public int deal_userStsSync_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_userStsSync()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_userStsSync()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_userStsSync", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_userStsSync() alias deal_userStsSync");
		return iRetValue;
	}

	public int read_barService(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_barService()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_barService()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_barService", in, out);
		log.debug("ended call remote method IImsTsApp.read_barService() alias read_barService");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_barService() with successful executing.");
		return iRetValue;
	}

	public int read_barService_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_barService()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_barService()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_barService", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_barService() alias read_barService");
		return iRetValue;
	}

	public int deal_barService(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_barService()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_barService()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_barService", in, out);
		log.debug("ended call remote method IImsTsApp.deal_barService() alias deal_barService");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_barService() with successful executing.");
		return iRetValue;
	}

	public int deal_barService_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_barService()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_barService()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_barService", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_barService() alias deal_barService");
		return iRetValue;
	}

	public int read_rewardInfo(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_rewardInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_rewardInfo()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_rewardInfo", in, out);
		log.debug("ended call remote method IImsTsApp.read_rewardInfo() alias read_rewardInfo");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_rewardInfo() with successful executing.");
		return iRetValue;
	}

	public int read_rewardInfo_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_rewardInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_rewardInfo()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_rewardInfo", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_rewardInfo() alias read_rewardInfo");
		return iRetValue;
	}

	public int deal_rewardInfo(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_rewardInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_rewardInfo()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_rewardInfo", in, out);
		log.debug("ended call remote method IImsTsApp.deal_rewardInfo() alias deal_rewardInfo");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_rewardInfo() with successful executing.");
		return iRetValue;
	}

	public int deal_rewardInfo_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_rewardInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_rewardInfo()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_rewardInfo", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_rewardInfo() alias deal_rewardInfo");
		return iRetValue;
	}

	public int read_otPromUsedUp(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_otPromUsedUp()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_otPromUsedUp()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_otPromUsedUp", in, out);
		log.debug("ended call remote method IImsTsApp.read_otPromUsedUp() alias read_otPromUsedUp");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_otPromUsedUp() with successful executing.");
		return iRetValue;
	}

	public int read_otPromUsedUp_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_otPromUsedUp()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_otPromUsedUp()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_otPromUsedUp", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_otPromUsedUp() alias read_otPromUsedUp");
		return iRetValue;
	}

	public int deal_otPromUsedUp(@In CsdlArrayList<STsRecord> sOtpromUsedupSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_otPromUsedUp()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sOtpromUsedupSyncList_h=new CsdlArrayListHolder<STsRecord>(sOtpromUsedupSyncList);
		sOtpromUsedupSyncList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_otPromUsedUp()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_otPromUsedUp", in, out);
		log.debug("ended call remote method IImsTsApp.deal_otPromUsedUp() alias deal_otPromUsedUp");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_otPromUsedUp() with successful executing.");
		return iRetValue;
	}

	public int deal_otPromUsedUp_upload(@In CsdlArrayList<STsRecord> sOtpromUsedupSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_otPromUsedUp()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sOtpromUsedupSyncList_h=new CsdlArrayListHolder<STsRecord>(sOtpromUsedupSyncList);
		sOtpromUsedupSyncList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_otPromUsedUp()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_otPromUsedUp", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_otPromUsedUp() alias deal_otPromUsedUp");
		return iRetValue;
	}

	public int read_lowBalance(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_lowBalance()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_lowBalance()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_lowBalance", in, out);
		log.debug("ended call remote method IImsTsApp.read_lowBalance() alias read_lowBalance");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_lowBalance() with successful executing.");
		return iRetValue;
	}

	public int read_lowBalance_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_lowBalance()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_lowBalance()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_lowBalance", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_lowBalance() alias read_lowBalance");
		return iRetValue;
	}

	public int deal_lowBalance(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_lowBalance()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_lowBalance()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_lowBalance", in, out);
		log.debug("ended call remote method IImsTsApp.deal_lowBalance() alias deal_lowBalance");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_lowBalance() with successful executing.");
		return iRetValue;
	}

	public int deal_lowBalance_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_lowBalance()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_lowBalance()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_lowBalance", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_lowBalance() alias deal_lowBalance");
		return iRetValue;
	}

	public int read_mdbError(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_mdbError()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_mdbError()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_mdbError", in, out);
		log.debug("ended call remote method IImsTsApp.read_mdbError() alias read_mdbError");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_mdbError() with successful executing.");
		return iRetValue;
	}

	public int read_mdbError_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_mdbError()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_mdbError()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_mdbError", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_mdbError() alias read_mdbError");
		return iRetValue;
	}

	public int deal_mdbError(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_mdbError()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_mdbError()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_mdbError", in, out);
		log.debug("ended call remote method IImsTsApp.deal_mdbError() alias deal_mdbError");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_mdbError() with successful executing.");
		return iRetValue;
	}

	public int deal_mdbError_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_mdbError()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_mdbError()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_mdbError", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_mdbError() alias deal_mdbError");
		return iRetValue;
	}

	public int read_expireProd(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_expireProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_expireProd()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_expireProd", in, out);
		log.debug("ended call remote method IImsTsApp.read_expireProd() alias read_expireProd");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_expireProd() with successful executing.");
		return iRetValue;
	}

	public int read_expireProd_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_expireProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_expireProd()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_expireProd", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_expireProd() alias read_expireProd");
		return iRetValue;
	}

	public int deal_expireProd(@In CsdlArrayList<STsRecord> sExpireProdNotifyList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_expireProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sExpireProdNotifyList_h=new CsdlArrayListHolder<STsRecord>(sExpireProdNotifyList);
		sExpireProdNotifyList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_expireProd()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_expireProd", in, out);
		log.debug("ended call remote method IImsTsApp.deal_expireProd() alias deal_expireProd");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_expireProd() with successful executing.");
		return iRetValue;
	}

	public int deal_expireProd_upload(@In CsdlArrayList<STsRecord> sExpireProdNotifyList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_expireProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sExpireProdNotifyList_h=new CsdlArrayListHolder<STsRecord>(sExpireProdNotifyList);
		sExpireProdNotifyList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_expireProd()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_expireProd", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_expireProd() alias deal_expireProd");
		return iRetValue;
	}

	public int read_event(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_event()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_event()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_event", in, out);
		log.debug("ended call remote method IImsTsApp.read_event() alias read_event");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_event() with successful executing.");
		return iRetValue;
	}

	public int read_event_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_event()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_event()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_event", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_event() alias read_event");
		return iRetValue;
	}

	public int deal_event(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_event()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_event()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_event", in, out);
		log.debug("ended call remote method IImsTsApp.deal_event() alias deal_event");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_event() with successful executing.");
		return iRetValue;
	}

	public int deal_event_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_event()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_event()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_event", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_event() alias deal_event");
		return iRetValue;
	}

	public int read_eventReward(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_eventReward()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_eventReward()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_eventReward", in, out);
		log.debug("ended call remote method IImsTsApp.read_eventReward() alias read_eventReward");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_eventReward() with successful executing.");
		return iRetValue;
	}

	public int read_eventReward_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_eventReward()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_eventReward()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_eventReward", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_eventReward() alias read_eventReward");
		return iRetValue;
	}

	public int deal_eventReward(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_eventReward()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_eventReward()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_eventReward", in, out);
		log.debug("ended call remote method IImsTsApp.deal_eventReward() alias deal_eventReward");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_eventReward() with successful executing.");
		return iRetValue;
	}

	public int deal_eventReward_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_eventReward()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_eventReward()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_eventReward", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_eventReward() alias deal_eventReward");
		return iRetValue;
	}

	public int read_delayValidProd(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_delayValidProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_delayValidProd()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_delayValidProd", in, out);
		log.debug("ended call remote method IImsTsApp.read_delayValidProd() alias read_delayValidProd");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_delayValidProd() with successful executing.");
		return iRetValue;
	}

	public int read_delayValidProd_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_delayValidProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_delayValidProd()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_delayValidProd", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_delayValidProd() alias read_delayValidProd");
		return iRetValue;
	}

	public int deal_delayValidProd(@In CsdlArrayList<STsRecord> sDelayValidProdSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_delayValidProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sDelayValidProdSyncList_h=new CsdlArrayListHolder<STsRecord>(sDelayValidProdSyncList);
		sDelayValidProdSyncList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_delayValidProd()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_delayValidProd", in, out);
		log.debug("ended call remote method IImsTsApp.deal_delayValidProd() alias deal_delayValidProd");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_delayValidProd() with successful executing.");
		return iRetValue;
	}

	public int deal_delayValidProd_upload(@In CsdlArrayList<STsRecord> sDelayValidProdSyncList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_delayValidProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sDelayValidProdSyncList_h=new CsdlArrayListHolder<STsRecord>(sDelayValidProdSyncList);
		sDelayValidProdSyncList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_delayValidProd()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_delayValidProd", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_delayValidProd() alias deal_delayValidProd");
		return iRetValue;
	}

	public int read_prodOnceNotify(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_prodOnceNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_prodOnceNotify()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_prodOnceNotify", in, out);
		log.debug("ended call remote method IImsTsApp.read_prodOnceNotify() alias read_prodOnceNotify");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_prodOnceNotify() with successful executing.");
		return iRetValue;
	}

	public int read_prodOnceNotify_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_prodOnceNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_prodOnceNotify()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_prodOnceNotify", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_prodOnceNotify() alias read_prodOnceNotify");
		return iRetValue;
	}

	public int deal_prodOnceNotify(@In CsdlArrayList<STsRecord> sProdOnceNotifyList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_prodOnceNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sProdOnceNotifyList_h=new CsdlArrayListHolder<STsRecord>(sProdOnceNotifyList);
		sProdOnceNotifyList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_prodOnceNotify()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_prodOnceNotify", in, out);
		log.debug("ended call remote method IImsTsApp.deal_prodOnceNotify() alias deal_prodOnceNotify");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_prodOnceNotify() with successful executing.");
		return iRetValue;
	}

	public int deal_prodOnceNotify_upload(@In CsdlArrayList<STsRecord> sProdOnceNotifyList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_prodOnceNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sProdOnceNotifyList_h=new CsdlArrayListHolder<STsRecord>(sProdOnceNotifyList);
		sProdOnceNotifyList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_prodOnceNotify()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_prodOnceNotify", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_prodOnceNotify() alias deal_prodOnceNotify");
		return iRetValue;
	}

	public int read_prodCycleNotify(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_prodCycleNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_prodCycleNotify()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_prodCycleNotify", in, out);
		log.debug("ended call remote method IImsTsApp.read_prodCycleNotify() alias read_prodCycleNotify");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_prodCycleNotify() with successful executing.");
		return iRetValue;
	}

	public int read_prodCycleNotify_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_prodCycleNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_prodCycleNotify()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_prodCycleNotify", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_prodCycleNotify() alias read_prodCycleNotify");
		return iRetValue;
	}

	public int deal_prodCycleNotify(@In CsdlArrayList<STsRecord> sProdCycleNotifyList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_prodCycleNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sProdCycleNotifyList_h=new CsdlArrayListHolder<STsRecord>(sProdCycleNotifyList);
		sProdCycleNotifyList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_prodCycleNotify()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_prodCycleNotify", in, out);
		log.debug("ended call remote method IImsTsApp.deal_prodCycleNotify() alias deal_prodCycleNotify");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_prodCycleNotify() with successful executing.");
		return iRetValue;
	}

	public int deal_prodCycleNotify_upload(@In CsdlArrayList<STsRecord> sProdCycleNotifyList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_prodCycleNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sProdCycleNotifyList_h=new CsdlArrayListHolder<STsRecord>(sProdCycleNotifyList);
		sProdCycleNotifyList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_prodCycleNotify()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_prodCycleNotify", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_prodCycleNotify() alias deal_prodCycleNotify");
		return iRetValue;
	}

	public int read_lifeCycleOncePreNotify(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_lifeCycleOncePreNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_lifeCycleOncePreNotify()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_lifeCycleOncePreNotify", in, out);
		log.debug("ended call remote method IImsTsApp.read_lifeCycleOncePreNotify() alias read_lifeCycleOncePreNotify");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_lifeCycleOncePreNotify() with successful executing.");
		return iRetValue;
	}

	public int read_lifeCycleOncePreNotify_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_lifeCycleOncePreNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_lifeCycleOncePreNotify()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_lifeCycleOncePreNotify", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_lifeCycleOncePreNotify() alias read_lifeCycleOncePreNotify");
		return iRetValue;
	}

	public int deal_lifeCycleOncePreNotify(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_lifeCycleOncePreNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_lifeCycleOncePreNotify()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_lifeCycleOncePreNotify", in, out);
		log.debug("ended call remote method IImsTsApp.deal_lifeCycleOncePreNotify() alias deal_lifeCycleOncePreNotify");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_lifeCycleOncePreNotify() with successful executing.");
		return iRetValue;
	}

	public int deal_lifeCycleOncePreNotify_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_lifeCycleOncePreNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_lifeCycleOncePreNotify()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_lifeCycleOncePreNotify", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_lifeCycleOncePreNotify() alias deal_lifeCycleOncePreNotify");
		return iRetValue;
	}

	public int read_imsUserOrderConfirm(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_imsUserOrderConfirm()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_imsUserOrderConfirm()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_imsUserOrderConfirm", in, out);
		log.debug("ended call remote method IImsTsApp.read_imsUserOrderConfirm() alias read_imsUserOrderConfirm");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_imsUserOrderConfirm() with successful executing.");
		return iRetValue;
	}

	public int read_imsUserOrderConfirm_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_imsUserOrderConfirm()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_imsUserOrderConfirm()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_imsUserOrderConfirm", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_imsUserOrderConfirm() alias read_imsUserOrderConfirm");
		return iRetValue;
	}

	public int deal_imsUserOrderConfirm(@In CsdlArrayList<SUserConfirm> sUserConfirmList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_imsUserOrderConfirm()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<SUserConfirm> sUserConfirmList_h=new CsdlArrayListHolder<SUserConfirm>(sUserConfirmList);
		sUserConfirmList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_imsUserOrderConfirm()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_imsUserOrderConfirm", in, out);
		log.debug("ended call remote method IImsTsApp.deal_imsUserOrderConfirm() alias deal_imsUserOrderConfirm");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_imsUserOrderConfirm() with successful executing.");
		return iRetValue;
	}

	public int deal_imsUserOrderConfirm_upload(@In CsdlArrayList<SUserConfirm> sUserConfirmList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_imsUserOrderConfirm()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<SUserConfirm> sUserConfirmList_h=new CsdlArrayListHolder<SUserConfirm>(sUserConfirmList);
		sUserConfirmList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_imsUserOrderConfirm()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_imsUserOrderConfirm", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_imsUserOrderConfirm() alias deal_imsUserOrderConfirm");
		return iRetValue;
	}

	public int read_imsOrderProduct(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_imsOrderProduct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_imsOrderProduct()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_imsOrderProduct", in, out);
		log.debug("ended call remote method IImsTsApp.read_imsOrderProduct() alias read_imsOrderProduct");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_imsOrderProduct() with successful executing.");
		return iRetValue;
	}

	public int read_imsOrderProduct_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_imsOrderProduct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_imsOrderProduct()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_imsOrderProduct", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_imsOrderProduct() alias read_imsOrderProduct");
		return iRetValue;
	}

	public int deal_imsOrderProduct(@In CsdlArrayList<SImsOrderProduct> listImsOrderProduct,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_imsOrderProduct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<SImsOrderProduct> listImsOrderProduct_h=new CsdlArrayListHolder<SImsOrderProduct>(listImsOrderProduct);
		listImsOrderProduct_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_imsOrderProduct()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_imsOrderProduct", in, out);
		log.debug("ended call remote method IImsTsApp.deal_imsOrderProduct() alias deal_imsOrderProduct");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_imsOrderProduct() with successful executing.");
		return iRetValue;
	}

	public int deal_imsOrderProduct_upload(@In CsdlArrayList<SImsOrderProduct> listImsOrderProduct,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_imsOrderProduct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<SImsOrderProduct> listImsOrderProduct_h=new CsdlArrayListHolder<SImsOrderProduct>(listImsOrderProduct);
		listImsOrderProduct_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_imsOrderProduct()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_imsOrderProduct", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_imsOrderProduct() alias deal_imsOrderProduct");
		return iRetValue;
	}

	public int read_sharingProd(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_sharingProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_sharingProd()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_sharingProd", in, out);
		log.debug("ended call remote method IImsTsApp.read_sharingProd() alias read_sharingProd");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_sharingProd() with successful executing.");
		return iRetValue;
	}

	public int read_sharingProd_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_sharingProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_sharingProd()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_sharingProd", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_sharingProd() alias read_sharingProd");
		return iRetValue;
	}

	public int deal_sharingProd(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_sharingProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_sharingProd()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_sharingProd", in, out);
		log.debug("ended call remote method IImsTsApp.deal_sharingProd() alias deal_sharingProd");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_sharingProd() with successful executing.");
		return iRetValue;
	}

	public int deal_sharingProd_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_sharingProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_sharingProd()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_sharingProd", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_sharingProd() alias deal_sharingProd");
		return iRetValue;
	}

	public int read_imsSyncTs(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_imsSyncTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_imsSyncTs()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_imsSyncTs", in, out);
		log.debug("ended call remote method IImsTsApp.read_imsSyncTs() alias read_imsSyncTs");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_imsSyncTs() with successful executing.");
		return iRetValue;
	}

	public int read_imsSyncTs_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_imsSyncTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_imsSyncTs()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_imsSyncTs", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_imsSyncTs() alias read_imsSyncTs");
		return iRetValue;
	}

	public int deal_imsSyncTs(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_imsSyncTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_imsSyncTs()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_imsSyncTs", in, out);
		log.debug("ended call remote method IImsTsApp.deal_imsSyncTs() alias deal_imsSyncTs");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_imsSyncTs() with successful executing.");
		return iRetValue;
	}

	public int deal_imsSyncTs_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_imsSyncTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_imsSyncTs()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_imsSyncTs", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_imsSyncTs() alias deal_imsSyncTs");
		return iRetValue;
	}

	public int read_deductFailureTs(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_deductFailureTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_deductFailureTs()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_deductFailureTs", in, out);
		log.debug("ended call remote method IImsTsApp.read_deductFailureTs() alias read_deductFailureTs");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_deductFailureTs() with successful executing.");
		return iRetValue;
	}

	public int read_deductFailureTs_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_deductFailureTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_deductFailureTs()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_deductFailureTs", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_deductFailureTs() alias read_deductFailureTs");
		return iRetValue;
	}

	public int deal_deductFailureTs(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_deductFailureTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_deductFailureTs()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_deductFailureTs", in, out);
		log.debug("ended call remote method IImsTsApp.deal_deductFailureTs() alias deal_deductFailureTs");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_deductFailureTs() with successful executing.");
		return iRetValue;
	}

	public int deal_deductFailureTs_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_deductFailureTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_deductFailureTs()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_deductFailureTs", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_deductFailureTs() alias deal_deductFailureTs");
		return iRetValue;
	}

	public int read_resetSysNegtiveValue(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_resetSysNegtiveValue()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_resetSysNegtiveValue()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_resetSysNegtiveValue", in, out);
		log.debug("ended call remote method IImsTsApp.read_resetSysNegtiveValue() alias read_resetSysNegtiveValue");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_resetSysNegtiveValue() with successful executing.");
		return iRetValue;
	}

	public int read_resetSysNegtiveValue_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_resetSysNegtiveValue()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_resetSysNegtiveValue()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_resetSysNegtiveValue", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_resetSysNegtiveValue() alias read_resetSysNegtiveValue");
		return iRetValue;
	}

	public int deal_resetSysNegtiveValue(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_resetSysNegtiveValue()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_resetSysNegtiveValue()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_resetSysNegtiveValue", in, out);
		log.debug("ended call remote method IImsTsApp.deal_resetSysNegtiveValue() alias deal_resetSysNegtiveValue");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_resetSysNegtiveValue() with successful executing.");
		return iRetValue;
	}

	public int deal_resetSysNegtiveValue_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_resetSysNegtiveValue()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_resetSysNegtiveValue()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_resetSysNegtiveValue", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_resetSysNegtiveValue() alias deal_resetSysNegtiveValue");
		return iRetValue;
	}

	public int read_syncIcs(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_syncIcs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_syncIcs()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_syncIcs", in, out);
		log.debug("ended call remote method IImsTsApp.read_syncIcs() alias read_syncIcs");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_syncIcs() with successful executing.");
		return iRetValue;
	}

	public int read_syncIcs_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_syncIcs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_syncIcs()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_syncIcs", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_syncIcs() alias read_syncIcs");
		return iRetValue;
	}

	public int deal_syncIcs(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_syncIcs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_syncIcs()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_syncIcs", in, out);
		log.debug("ended call remote method IImsTsApp.deal_syncIcs() alias deal_syncIcs");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_syncIcs() with successful executing.");
		return iRetValue;
	}

	public int deal_syncIcs_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_syncIcs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_syncIcs()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_syncIcs", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_syncIcs() alias deal_syncIcs");
		return iRetValue;
	}

	public int read_terminateBillAcctTs(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_terminateBillAcctTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.read_terminateBillAcctTs()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "read_terminateBillAcctTs", in, out);
		log.debug("ended call remote method IImsTsApp.read_terminateBillAcctTs() alias read_terminateBillAcctTs");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.read_terminateBillAcctTs() with successful executing.");
		return iRetValue;
	}

	public int read_terminateBillAcctTs_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.read_terminateBillAcctTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsTsApp.read_terminateBillAcctTs()");
		call_server_upload("MImsTsApp_IImsTsApp", "read_terminateBillAcctTs", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.read_terminateBillAcctTs() alias read_terminateBillAcctTs");
		return iRetValue;
	}

	public int deal_terminateBillAcctTs(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_terminateBillAcctTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsTsApp.deal_terminateBillAcctTs()");
		iRetValue = call_server("MImsTsApp_IImsTsApp", "deal_terminateBillAcctTs", in, out);
		log.debug("ended call remote method IImsTsApp.deal_terminateBillAcctTs() alias deal_terminateBillAcctTs");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsTsApp.deal_terminateBillAcctTs() with successful executing.");
		return iRetValue;
	}

	public int deal_terminateBillAcctTs_upload(@In CsdlArrayList<STsRecord> sTsRecordList,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsTsApp.deal_terminateBillAcctTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlArrayListHolder<STsRecord> sTsRecordList_h=new CsdlArrayListHolder<STsRecord>(sTsRecordList);
		sTsRecordList_h.writeData(in);
		log.debug("starting call remote method IImsTsApp.deal_terminateBillAcctTs()");
		call_server_upload("MImsTsApp_IImsTsApp", "deal_terminateBillAcctTs", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsTsApp.deal_terminateBillAcctTs() alias deal_terminateBillAcctTs");
		return iRetValue;
	}


}