package com.ailk.easyframe.sdl.MImsSyncApp;

import com.ailk.easyframe.sdl.sdlclient.CsdlClientObject;
import org.apache.commons.logging.LogFactory;
import java.util.LinkedHashMap;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_StreamDecorator;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_Stream;
import jef.common.In;
import jef.common.Out;
import jef.common.InOut;
import com.ailk.easyframe.sdl.util.Utility;
import com.ailk.openbilling.persistence.imssdl.entity.SOperInfo;
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
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import org.apache.commons.logging.Log;
import jef.codegen.support.NotModified;
@NotModified
public class IImsSyncAppInt extends CsdlClientObject{

	protected static Log log = LogFactory.getLog(IImsSyncAppInt.class);

	public int do_upSelling(@In SOperInfo sOperInfo,@In SUpSellReq sUpSellReq,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_upSelling()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SUpSellReq> sUpSellReq_h=new CsdlStructObjectHolder<SUpSellReq>(sUpSellReq);
		sUpSellReq_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_upSelling()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_upSelling", in, out);
		log.debug("ended call remote method IImsSyncApp.do_upSelling() alias do_upSelling");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_upSelling() with successful executing.");
		return iRetValue;
	}

	public int do_upSelling_upload(@In SOperInfo sOperInfo,@In SUpSellReq sUpSellReq,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_upSelling()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SUpSellReq> sUpSellReq_h=new CsdlStructObjectHolder<SUpSellReq>(sUpSellReq);
		sUpSellReq_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_upSelling()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_upSelling", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_upSelling() alias do_upSelling");
		return iRetValue;
	}

	public int do_lifeCycleChg(@In SOperInfo sOperInfo,@In CsdlArrayList<SLifeCycleChg4Abm> listLifeCycleChg4Abm,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_lifeCycleChg()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SLifeCycleChg4Abm> listLifeCycleChg4Abm_h=new CsdlArrayListHolder<SLifeCycleChg4Abm>(listLifeCycleChg4Abm);
		listLifeCycleChg4Abm_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_lifeCycleChg()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_lifeCycleChg", in, out);
		log.debug("ended call remote method IImsSyncApp.do_lifeCycleChg() alias do_lifeCycleChg");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_lifeCycleChg() with successful executing.");
		return iRetValue;
	}

	public int do_lifeCycleChg_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SLifeCycleChg4Abm> listLifeCycleChg4Abm,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_lifeCycleChg()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SLifeCycleChg4Abm> listLifeCycleChg4Abm_h=new CsdlArrayListHolder<SLifeCycleChg4Abm>(listLifeCycleChg4Abm);
		listLifeCycleChg4Abm_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_lifeCycleChg()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_lifeCycleChg", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_lifeCycleChg() alias do_lifeCycleChg");
		return iRetValue;
	}

	public int ts_firstAct(@In SOperInfo sOperInfo,@In SFirstActiveRes sFirstActiveRes,@In CsdlArrayList<SCaRewardInfo> listCaRewardInfo,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.ts_firstAct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SFirstActiveRes> sFirstActiveRes_h=new CsdlStructObjectHolder<SFirstActiveRes>(sFirstActiveRes);
		sFirstActiveRes_h.writeData(in);
		CsdlArrayListHolder<SCaRewardInfo> listCaRewardInfo_h=new CsdlArrayListHolder<SCaRewardInfo>(listCaRewardInfo);
		listCaRewardInfo_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.ts_firstAct()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "ts_firstAct", in, out);
		log.debug("ended call remote method IImsSyncApp.ts_firstAct() alias ts_firstAct");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.ts_firstAct() with successful executing.");
		return iRetValue;
	}

	public int ts_firstAct_upload(@In SOperInfo sOperInfo,@In SFirstActiveRes sFirstActiveRes,@In CsdlArrayList<SCaRewardInfo> listCaRewardInfo,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.ts_firstAct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SFirstActiveRes> sFirstActiveRes_h=new CsdlStructObjectHolder<SFirstActiveRes>(sFirstActiveRes);
		sFirstActiveRes_h.writeData(in);
		CsdlArrayListHolder<SCaRewardInfo> listCaRewardInfo_h=new CsdlArrayListHolder<SCaRewardInfo>(listCaRewardInfo);
		listCaRewardInfo_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.ts_firstAct()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "ts_firstAct", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.ts_firstAct() alias ts_firstAct");
		return iRetValue;
	}

	public int sync_productStatus(@In SOperInfo sOperInfo,@In CsdlArrayList<SProductStatus> listProductStatus,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.sync_productStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SProductStatus> listProductStatus_h=new CsdlArrayListHolder<SProductStatus>(listProductStatus);
		listProductStatus_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.sync_productStatus()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "sync_productStatus", in, out);
		log.debug("ended call remote method IImsSyncApp.sync_productStatus() alias sync_productStatus");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.sync_productStatus() with successful executing.");
		return iRetValue;
	}

	public int sync_productStatus_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SProductStatus> listProductStatus,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.sync_productStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SProductStatus> listProductStatus_h=new CsdlArrayListHolder<SProductStatus>(listProductStatus);
		listProductStatus_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.sync_productStatus()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "sync_productStatus", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.sync_productStatus() alias sync_productStatus");
		return iRetValue;
	}

	public int sync_creditStatus(@In SOperInfo sOperInfo,@In SCreditStatus sCreditStatus,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.sync_creditStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SCreditStatus> sCreditStatus_h=new CsdlStructObjectHolder<SCreditStatus>(sCreditStatus);
		sCreditStatus_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.sync_creditStatus()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "sync_creditStatus", in, out);
		log.debug("ended call remote method IImsSyncApp.sync_creditStatus() alias sync_creditStatus");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.sync_creditStatus() with successful executing.");
		return iRetValue;
	}

	public int sync_creditStatus_upload(@In SOperInfo sOperInfo,@In SCreditStatus sCreditStatus,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.sync_creditStatus()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SCreditStatus> sCreditStatus_h=new CsdlStructObjectHolder<SCreditStatus>(sCreditStatus);
		sCreditStatus_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.sync_creditStatus()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "sync_creditStatus", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.sync_creditStatus() alias sync_creditStatus");
		return iRetValue;
	}

	public int read_lifeCycleStsCheck(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.read_lifeCycleStsCheck()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.read_lifeCycleStsCheck()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "read_lifeCycleStsCheck", in, out);
		log.debug("ended call remote method IImsSyncApp.read_lifeCycleStsCheck() alias read_lifeCycleStsCheck");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.read_lifeCycleStsCheck() with successful executing.");
		return iRetValue;
	}

	public int read_lifeCycleStsCheck_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.read_lifeCycleStsCheck()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsSyncApp.read_lifeCycleStsCheck()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "read_lifeCycleStsCheck", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.read_lifeCycleStsCheck() alias read_lifeCycleStsCheck");
		return iRetValue;
	}

	public int deal_lifeCycleStsCheck(@In SOperInfo sOperInfo,@In SResLifeCycleSync sResLifeCycleSync,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.deal_lifeCycleStsCheck()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SResLifeCycleSync> sResLifeCycleSync_h=new CsdlStructObjectHolder<SResLifeCycleSync>(sResLifeCycleSync);
		sResLifeCycleSync_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.deal_lifeCycleStsCheck()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "deal_lifeCycleStsCheck", in, out);
		log.debug("ended call remote method IImsSyncApp.deal_lifeCycleStsCheck() alias deal_lifeCycleStsCheck");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.deal_lifeCycleStsCheck() with successful executing.");
		return iRetValue;
	}

	public int deal_lifeCycleStsCheck_upload(@In SOperInfo sOperInfo,@In SResLifeCycleSync sResLifeCycleSync,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.deal_lifeCycleStsCheck()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SResLifeCycleSync> sResLifeCycleSync_h=new CsdlStructObjectHolder<SResLifeCycleSync>(sResLifeCycleSync);
		sResLifeCycleSync_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.deal_lifeCycleStsCheck()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "deal_lifeCycleStsCheck", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.deal_lifeCycleStsCheck() alias deal_lifeCycleStsCheck");
		return iRetValue;
	}

	public int add_expireProd(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.add_expireProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.add_expireProd()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "add_expireProd", in, out);
		log.debug("ended call remote method IImsSyncApp.add_expireProd() alias add_expireProd");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.add_expireProd() with successful executing.");
		return iRetValue;
	}

	public int add_expireProd_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.add_expireProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsSyncApp.add_expireProd()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "add_expireProd", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.add_expireProd() alias add_expireProd");
		return iRetValue;
	}

	public int add_validProd(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.add_validProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.add_validProd()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "add_validProd", in, out);
		log.debug("ended call remote method IImsSyncApp.add_validProd() alias add_validProd");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.add_validProd() with successful executing.");
		return iRetValue;
	}

	public int add_validProd_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.add_validProd()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsSyncApp.add_validProd()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "add_validProd", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.add_validProd() alias add_validProd");
		return iRetValue;
	}

	public int add_prodCycleNotify(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.add_prodCycleNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.add_prodCycleNotify()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "add_prodCycleNotify", in, out);
		log.debug("ended call remote method IImsSyncApp.add_prodCycleNotify() alias add_prodCycleNotify");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.add_prodCycleNotify() with successful executing.");
		return iRetValue;
	}

	public int add_prodCycleNotify_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.add_prodCycleNotify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsSyncApp.add_prodCycleNotify()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "add_prodCycleNotify", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.add_prodCycleNotify() alias add_prodCycleNotify");
		return iRetValue;
	}

	public int do_resSevCycleChg(@In SOperInfo sOperInfo,@In CsdlArrayList<SResSevCycle> listResSevCycle,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_resSevCycleChg()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SResSevCycle> listResSevCycle_h=new CsdlArrayListHolder<SResSevCycle>(listResSevCycle);
		listResSevCycle_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_resSevCycleChg()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_resSevCycleChg", in, out);
		log.debug("ended call remote method IImsSyncApp.do_resSevCycleChg() alias do_resSevCycleChg");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_resSevCycleChg() with successful executing.");
		return iRetValue;
	}

	public int do_resSevCycleChg_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SResSevCycle> listResSevCycle,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_resSevCycleChg()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SResSevCycle> listResSevCycle_h=new CsdlArrayListHolder<SResSevCycle>(listResSevCycle);
		listResSevCycle_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_resSevCycleChg()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_resSevCycleChg", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_resSevCycleChg() alias do_resSevCycleChg");
		return iRetValue;
	}

	public int sync_oneTimePromUsedUp(@In SOperInfo sOperInfo,@In SOneTimeProm sOneTimeProm,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.sync_oneTimePromUsedUp()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SOneTimeProm> sOneTimeProm_h=new CsdlStructObjectHolder<SOneTimeProm>(sOneTimeProm);
		sOneTimeProm_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.sync_oneTimePromUsedUp()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "sync_oneTimePromUsedUp", in, out);
		log.debug("ended call remote method IImsSyncApp.sync_oneTimePromUsedUp() alias sync_oneTimePromUsedUp");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.sync_oneTimePromUsedUp() with successful executing.");
		return iRetValue;
	}

	public int sync_oneTimePromUsedUp_upload(@In SOperInfo sOperInfo,@In SOneTimeProm sOneTimeProm,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.sync_oneTimePromUsedUp()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SOneTimeProm> sOneTimeProm_h=new CsdlStructObjectHolder<SOneTimeProm>(sOneTimeProm);
		sOneTimeProm_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.sync_oneTimePromUsedUp()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "sync_oneTimePromUsedUp", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.sync_oneTimePromUsedUp() alias sync_oneTimePromUsedUp");
		return iRetValue;
	}

	public int do_sendNotification(@In SOperInfo sOperInfo,@In CsdlArrayList<SNotifySendStandardIntf> listNotifySendStandardIntf,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_sendNotification()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SNotifySendStandardIntf> listNotifySendStandardIntf_h=new CsdlArrayListHolder<SNotifySendStandardIntf>(listNotifySendStandardIntf);
		listNotifySendStandardIntf_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_sendNotification()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_sendNotification", in, out);
		log.debug("ended call remote method IImsSyncApp.do_sendNotification() alias do_sendNotification");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_sendNotification() with successful executing.");
		return iRetValue;
	}

	public int do_sendNotification_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SNotifySendStandardIntf> listNotifySendStandardIntf,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_sendNotification()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SNotifySendStandardIntf> listNotifySendStandardIntf_h=new CsdlArrayListHolder<SNotifySendStandardIntf>(listNotifySendStandardIntf);
		listNotifySendStandardIntf_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_sendNotification()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_sendNotification", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_sendNotification() alias do_sendNotification");
		return iRetValue;
	}

	public int do_syncLowBalanceEtopup(@In SOperInfo sOperInfo,@In CsdlArrayList<SLowBalance> listLowBalance,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_syncLowBalanceEtopup()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SLowBalance> listLowBalance_h=new CsdlArrayListHolder<SLowBalance>(listLowBalance);
		listLowBalance_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_syncLowBalanceEtopup()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_syncLowBalanceEtopup", in, out);
		log.debug("ended call remote method IImsSyncApp.do_syncLowBalanceEtopup() alias do_syncLowBalanceEtopup");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_syncLowBalanceEtopup() with successful executing.");
		return iRetValue;
	}

	public int do_syncLowBalanceEtopup_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SLowBalance> listLowBalance,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_syncLowBalanceEtopup()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SLowBalance> listLowBalance_h=new CsdlArrayListHolder<SLowBalance>(listLowBalance);
		listLowBalance_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_syncLowBalanceEtopup()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_syncLowBalanceEtopup", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_syncLowBalanceEtopup() alias do_syncLowBalanceEtopup");
		return iRetValue;
	}

	public int do_userValidityChg(@In SOperInfo sOperInfo,@In SUserValidityChg sUserValidityChg,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_userValidityChg()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SUserValidityChg> sUserValidityChg_h=new CsdlStructObjectHolder<SUserValidityChg>(sUserValidityChg);
		sUserValidityChg_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_userValidityChg()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_userValidityChg", in, out);
		log.debug("ended call remote method IImsSyncApp.do_userValidityChg() alias do_userValidityChg");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_userValidityChg() with successful executing.");
		return iRetValue;
	}

	public int do_userValidityChg_upload(@In SOperInfo sOperInfo,@In SUserValidityChg sUserValidityChg,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_userValidityChg()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SUserValidityChg> sUserValidityChg_h=new CsdlStructObjectHolder<SUserValidityChg>(sUserValidityChg);
		sUserValidityChg_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_userValidityChg()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_userValidityChg", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_userValidityChg() alias do_userValidityChg");
		return iRetValue;
	}

	public int do_orderRewardProduct(@In SOperInfo sOperInfo,@In SRewardOrderProduct sRewardOrderProduct,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_orderRewardProduct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SRewardOrderProduct> sRewardOrderProduct_h=new CsdlStructObjectHolder<SRewardOrderProduct>(sRewardOrderProduct);
		sRewardOrderProduct_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_orderRewardProduct()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_orderRewardProduct", in, out);
		log.debug("ended call remote method IImsSyncApp.do_orderRewardProduct() alias do_orderRewardProduct");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_orderRewardProduct() with successful executing.");
		return iRetValue;
	}

	public int do_orderRewardProduct_upload(@In SOperInfo sOperInfo,@In SRewardOrderProduct sRewardOrderProduct,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_orderRewardProduct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SRewardOrderProduct> sRewardOrderProduct_h=new CsdlStructObjectHolder<SRewardOrderProduct>(sRewardOrderProduct);
		sRewardOrderProduct_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_orderRewardProduct()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_orderRewardProduct", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_orderRewardProduct() alias do_orderRewardProduct");
		return iRetValue;
	}

	public int do_lifeCycleChgByPayment(@In SOperInfo sOperInfo,@In SLifeCycleChgByPayment sLifeCycleChgByPayment,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_lifeCycleChgByPayment()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SLifeCycleChgByPayment> sLifeCycleChgByPayment_h=new CsdlStructObjectHolder<SLifeCycleChgByPayment>(sLifeCycleChgByPayment);
		sLifeCycleChgByPayment_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_lifeCycleChgByPayment()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_lifeCycleChgByPayment", in, out);
		log.debug("ended call remote method IImsSyncApp.do_lifeCycleChgByPayment() alias do_lifeCycleChgByPayment");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_lifeCycleChgByPayment() with successful executing.");
		return iRetValue;
	}

	public int do_lifeCycleChgByPayment_upload(@In SOperInfo sOperInfo,@In SLifeCycleChgByPayment sLifeCycleChgByPayment,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_lifeCycleChgByPayment()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SLifeCycleChgByPayment> sLifeCycleChgByPayment_h=new CsdlStructObjectHolder<SLifeCycleChgByPayment>(sLifeCycleChgByPayment);
		sLifeCycleChgByPayment_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_lifeCycleChgByPayment()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_lifeCycleChgByPayment", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_lifeCycleChgByPayment() alias do_lifeCycleChgByPayment");
		return iRetValue;
	}

	public int do_updateProdPriceParam(@In SOperInfo sOperInfo,@In CsdlArrayList<SUpdatePriceParam> listUpdatePriceParam,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_updateProdPriceParam()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SUpdatePriceParam> listUpdatePriceParam_h=new CsdlArrayListHolder<SUpdatePriceParam>(listUpdatePriceParam);
		listUpdatePriceParam_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_updateProdPriceParam()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_updateProdPriceParam", in, out);
		log.debug("ended call remote method IImsSyncApp.do_updateProdPriceParam() alias do_updateProdPriceParam");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_updateProdPriceParam() with successful executing.");
		return iRetValue;
	}

	public int do_updateProdPriceParam_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SUpdatePriceParam> listUpdatePriceParam,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_updateProdPriceParam()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SUpdatePriceParam> listUpdatePriceParam_h=new CsdlArrayListHolder<SUpdatePriceParam>(listUpdatePriceParam);
		listUpdatePriceParam_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_updateProdPriceParam()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_updateProdPriceParam", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_updateProdPriceParam() alias do_updateProdPriceParam");
		return iRetValue;
	}

	public int do_barService(@In SOperInfo sOperInfo,@In CsdlArrayList<SBarService> listBarService,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_barService()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SBarService> listBarService_h=new CsdlArrayListHolder<SBarService>(listBarService);
		listBarService_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_barService()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_barService", in, out);
		log.debug("ended call remote method IImsSyncApp.do_barService() alias do_barService");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_barService() with successful executing.");
		return iRetValue;
	}

	public int do_barService_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SBarService> listBarService,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_barService()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SBarService> listBarService_h=new CsdlArrayListHolder<SBarService>(listBarService);
		listBarService_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_barService()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_barService", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_barService() alias do_barService");
		return iRetValue;
	}

	public int create_prodNotifyInfo(@In SOperInfo sOperInfo,@In CsdlArrayList<SProdNotify> listProdNotify,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.create_prodNotifyInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SProdNotify> listProdNotify_h=new CsdlArrayListHolder<SProdNotify>(listProdNotify);
		listProdNotify_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.create_prodNotifyInfo()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "create_prodNotifyInfo", in, out);
		log.debug("ended call remote method IImsSyncApp.create_prodNotifyInfo() alias create_prodNotifyInfo");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.create_prodNotifyInfo() with successful executing.");
		return iRetValue;
	}

	public int create_prodNotifyInfo_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SProdNotify> listProdNotify,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.create_prodNotifyInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SProdNotify> listProdNotify_h=new CsdlArrayListHolder<SProdNotify>(listProdNotify);
		listProdNotify_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.create_prodNotifyInfo()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "create_prodNotifyInfo", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.create_prodNotifyInfo() alias create_prodNotifyInfo");
		return iRetValue;
	}

	public int do_queryOperatorInfo(@In SOperInfo sOperInfo,@In SQueryOperReq sQueryOperReq,@Out CsdlArrayListHolder<SOperInfoResp> listOperInfoResp,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_queryOperatorInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SQueryOperReq> sQueryOperReq_h=new CsdlStructObjectHolder<SQueryOperReq>(sQueryOperReq);
		sQueryOperReq_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		holderMap.put("listOperInfoResp", listOperInfoResp);
		log.debug("starting call remote method IImsSyncApp.do_queryOperatorInfo()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_queryOperatorInfo", in, out);
		log.debug("ended call remote method IImsSyncApp.do_queryOperatorInfo() alias do_queryOperatorInfo");
		holderMap.put("listOperInfoResp", listOperInfoResp);
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_queryOperatorInfo() with successful executing.");
		return iRetValue;
	}

	public int do_queryOperatorInfo_upload(@In SOperInfo sOperInfo,@In SQueryOperReq sQueryOperReq,@Out CsdlArrayListHolder<SOperInfoResp> listOperInfoResp,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_queryOperatorInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SQueryOperReq> sQueryOperReq_h=new CsdlStructObjectHolder<SQueryOperReq>(sQueryOperReq);
		sQueryOperReq_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_queryOperatorInfo()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_queryOperatorInfo", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_queryOperatorInfo() alias do_queryOperatorInfo");
		return iRetValue;
	}

	public int do_queryOrganizeInfo(@In SOperInfo sOperInfo,@In SQueryOrgReq sQueryOrgReq,@Out CsdlArrayListHolder<SOrgInfoResp> listOrgInfoResp,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_queryOrganizeInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SQueryOrgReq> sQueryOrgReq_h=new CsdlStructObjectHolder<SQueryOrgReq>(sQueryOrgReq);
		sQueryOrgReq_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		holderMap.put("listOrgInfoResp", listOrgInfoResp);
		log.debug("starting call remote method IImsSyncApp.do_queryOrganizeInfo()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_queryOrganizeInfo", in, out);
		log.debug("ended call remote method IImsSyncApp.do_queryOrganizeInfo() alias do_queryOrganizeInfo");
		holderMap.put("listOrgInfoResp", listOrgInfoResp);
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_queryOrganizeInfo() with successful executing.");
		return iRetValue;
	}

	public int do_queryOrganizeInfo_upload(@In SOperInfo sOperInfo,@In SQueryOrgReq sQueryOrgReq,@Out CsdlArrayListHolder<SOrgInfoResp> listOrgInfoResp,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_queryOrganizeInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SQueryOrgReq> sQueryOrgReq_h=new CsdlStructObjectHolder<SQueryOrgReq>(sQueryOrgReq);
		sQueryOrgReq_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_queryOrganizeInfo()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_queryOrganizeInfo", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_queryOrganizeInfo() alias do_queryOrganizeInfo");
		return iRetValue;
	}

	public int do_insertImsResourceMonitor(@In SOperInfo sOperInfo,@In CsdlArrayList<SResourceMonitor> listResourceMonitor,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_insertImsResourceMonitor()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SResourceMonitor> listResourceMonitor_h=new CsdlArrayListHolder<SResourceMonitor>(listResourceMonitor);
		listResourceMonitor_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_insertImsResourceMonitor()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_insertImsResourceMonitor", in, out);
		log.debug("ended call remote method IImsSyncApp.do_insertImsResourceMonitor() alias do_insertImsResourceMonitor");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_insertImsResourceMonitor() with successful executing.");
		return iRetValue;
	}

	public int do_insertImsResourceMonitor_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SResourceMonitor> listResourceMonitor,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_insertImsResourceMonitor()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SResourceMonitor> listResourceMonitor_h=new CsdlArrayListHolder<SResourceMonitor>(listResourceMonitor);
		listResourceMonitor_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_insertImsResourceMonitor()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_insertImsResourceMonitor", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_insertImsResourceMonitor() alias do_insertImsResourceMonitor");
		return iRetValue;
	}

	public int do_insertImsUserOrderConfirm(@In SOperInfo sOperInfo,@In CsdlArrayList<SDeductAlarm> listDeductAlarm,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_insertImsUserOrderConfirm()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SDeductAlarm> listDeductAlarm_h=new CsdlArrayListHolder<SDeductAlarm>(listDeductAlarm);
		listDeductAlarm_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_insertImsUserOrderConfirm()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_insertImsUserOrderConfirm", in, out);
		log.debug("ended call remote method IImsSyncApp.do_insertImsUserOrderConfirm() alias do_insertImsUserOrderConfirm");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_insertImsUserOrderConfirm() with successful executing.");
		return iRetValue;
	}

	public int do_insertImsUserOrderConfirm_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SDeductAlarm> listDeductAlarm,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_insertImsUserOrderConfirm()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SDeductAlarm> listDeductAlarm_h=new CsdlArrayListHolder<SDeductAlarm>(listDeductAlarm);
		listDeductAlarm_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_insertImsUserOrderConfirm()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_insertImsUserOrderConfirm", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_insertImsUserOrderConfirm() alias do_insertImsUserOrderConfirm");
		return iRetValue;
	}

	public int do_modifyProduct(@In SOperInfo sOperInfo,@In CsdlArrayList<SChangeProdInfo> listProdInfo,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_modifyProduct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SChangeProdInfo> listProdInfo_h=new CsdlArrayListHolder<SChangeProdInfo>(listProdInfo);
		listProdInfo_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_modifyProduct()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_modifyProduct", in, out);
		log.debug("ended call remote method IImsSyncApp.do_modifyProduct() alias do_modifyProduct");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_modifyProduct() with successful executing.");
		return iRetValue;
	}

	public int do_modifyProduct_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SChangeProdInfo> listProdInfo,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_modifyProduct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SChangeProdInfo> listProdInfo_h=new CsdlArrayListHolder<SChangeProdInfo>(listProdInfo);
		listProdInfo_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_modifyProduct()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_modifyProduct", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_modifyProduct() alias do_modifyProduct");
		return iRetValue;
	}

	public int do_rewardOtFreeres(@In SOperInfo sOperInfo,@In SRewardOtFreeres sRewardOtFreeres,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_rewardOtFreeres()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SRewardOtFreeres> sRewardOtFreeres_h=new CsdlStructObjectHolder<SRewardOtFreeres>(sRewardOtFreeres);
		sRewardOtFreeres_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_rewardOtFreeres()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_rewardOtFreeres", in, out);
		log.debug("ended call remote method IImsSyncApp.do_rewardOtFreeres() alias do_rewardOtFreeres");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_rewardOtFreeres() with successful executing.");
		return iRetValue;
	}

	public int do_rewardOtFreeres_upload(@In SOperInfo sOperInfo,@In SRewardOtFreeres sRewardOtFreeres,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_rewardOtFreeres()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SRewardOtFreeres> sRewardOtFreeres_h=new CsdlStructObjectHolder<SRewardOtFreeres>(sRewardOtFreeres);
		sRewardOtFreeres_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_rewardOtFreeres()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_rewardOtFreeres", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_rewardOtFreeres() alias do_rewardOtFreeres");
		return iRetValue;
	}

	public int read_userValidity(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.read_userValidity()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.read_userValidity()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "read_userValidity", in, out);
		log.debug("ended call remote method IImsSyncApp.read_userValidity() alias read_userValidity");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.read_userValidity() with successful executing.");
		return iRetValue;
	}

	public int read_userValidity_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.read_userValidity()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsSyncApp.read_userValidity()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "read_userValidity", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.read_userValidity() alias read_userValidity");
		return iRetValue;
	}

	public int do_firstActive(@In SOperInfo sOperInfo,@In SFirstAct sFirstAct,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_firstActive()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SFirstAct> sFirstAct_h=new CsdlStructObjectHolder<SFirstAct>(sFirstAct);
		sFirstAct_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_firstActive()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_firstActive", in, out);
		log.debug("ended call remote method IImsSyncApp.do_firstActive() alias do_firstActive");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_firstActive() with successful executing.");
		return iRetValue;
	}

	public int do_firstActive_upload(@In SOperInfo sOperInfo,@In SFirstAct sFirstAct,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_firstActive()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SFirstAct> sFirstAct_h=new CsdlStructObjectHolder<SFirstAct>(sFirstAct);
		sFirstAct_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_firstActive()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_firstActive", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_firstActive() alias do_firstActive");
		return iRetValue;
	}

	public int do_queryAcctInfo(@In SOperInfo sOperInfo,@In SQueryAcctInfoSdlReq sQueryAcctInfoSdlReq,@Out CsdlStructObjectHolder<SAccountInfo> sAccountInfo,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_queryAcctInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SQueryAcctInfoSdlReq> sQueryAcctInfoSdlReq_h=new CsdlStructObjectHolder<SQueryAcctInfoSdlReq>(sQueryAcctInfoSdlReq);
		sQueryAcctInfoSdlReq_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		holderMap.put("sAccountInfo", sAccountInfo);
		log.debug("starting call remote method IImsSyncApp.do_queryAcctInfo()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_queryAcctInfo", in, out);
		log.debug("ended call remote method IImsSyncApp.do_queryAcctInfo() alias do_queryAcctInfo");
		holderMap.put("sAccountInfo", sAccountInfo);
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_queryAcctInfo() with successful executing.");
		return iRetValue;
	}

	public int do_queryAcctInfo_upload(@In SOperInfo sOperInfo,@In SQueryAcctInfoSdlReq sQueryAcctInfoSdlReq,@Out CsdlStructObjectHolder<SAccountInfo> sAccountInfo,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_queryAcctInfo()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SQueryAcctInfoSdlReq> sQueryAcctInfoSdlReq_h=new CsdlStructObjectHolder<SQueryAcctInfoSdlReq>(sQueryAcctInfoSdlReq);
		sQueryAcctInfoSdlReq_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_queryAcctInfo()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_queryAcctInfo", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_queryAcctInfo() alias do_queryAcctInfo");
		return iRetValue;
	}

	public int do_deductFailureTs(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_deductFailureTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_deductFailureTs()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_deductFailureTs", in, out);
		log.debug("ended call remote method IImsSyncApp.do_deductFailureTs() alias do_deductFailureTs");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_deductFailureTs() with successful executing.");
		return iRetValue;
	}

	public int do_deductFailureTs_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_deductFailureTs()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsSyncApp.do_deductFailureTs()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_deductFailureTs", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_deductFailureTs() alias do_deductFailureTs");
		return iRetValue;
	}

	public int do_insertImmeSmsBySdl(@In SOperInfo sOperInfo,@In CsdlArrayList<SImmeSms> listImmeSms,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_insertImmeSmsBySdl()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SImmeSms> listImmeSms_h=new CsdlArrayListHolder<SImmeSms>(listImmeSms);
		listImmeSms_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_insertImmeSmsBySdl()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_insertImmeSmsBySdl", in, out);
		log.debug("ended call remote method IImsSyncApp.do_insertImmeSmsBySdl() alias do_insertImmeSmsBySdl");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_insertImmeSmsBySdl() with successful executing.");
		return iRetValue;
	}

	public int do_insertImmeSmsBySdl_upload(@In SOperInfo sOperInfo,@In CsdlArrayList<SImmeSms> listImmeSms,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_insertImmeSmsBySdl()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlArrayListHolder<SImmeSms> listImmeSms_h=new CsdlArrayListHolder<SImmeSms>(listImmeSms);
		listImmeSms_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_insertImmeSmsBySdl()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_insertImmeSmsBySdl", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_insertImmeSmsBySdl() alias do_insertImmeSmsBySdl");
		return iRetValue;
	}

	public int do_insertBatchSmsBySdl(@In SOperInfo sOperInfo,@In SBatchSmsInfo sBatchSmsInfo,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_insertBatchSmsBySdl()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SBatchSmsInfo> sBatchSmsInfo_h=new CsdlStructObjectHolder<SBatchSmsInfo>(sBatchSmsInfo);
		sBatchSmsInfo_h.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsSyncApp.do_insertBatchSmsBySdl()");
		iRetValue = call_server("MImsSyncApp_IImsSyncApp", "do_insertBatchSmsBySdl", in, out);
		log.debug("ended call remote method IImsSyncApp.do_insertBatchSmsBySdl() alias do_insertBatchSmsBySdl");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsSyncApp.do_insertBatchSmsBySdl() with successful executing.");
		return iRetValue;
	}

	public int do_insertBatchSmsBySdl_upload(@In SOperInfo sOperInfo,@In SBatchSmsInfo sBatchSmsInfo,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsSyncApp.do_insertBatchSmsBySdl()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		CsdlStructObjectHolder<SOperInfo> sOperInfo_h=new CsdlStructObjectHolder<SOperInfo>(sOperInfo);
		sOperInfo_h.writeData(in);
		CsdlStructObjectHolder<SBatchSmsInfo> sBatchSmsInfo_h=new CsdlStructObjectHolder<SBatchSmsInfo>(sBatchSmsInfo);
		sBatchSmsInfo_h.writeData(in);
		log.debug("starting call remote method IImsSyncApp.do_insertBatchSmsBySdl()");
		call_server_upload("MImsSyncApp_IImsSyncApp", "do_insertBatchSmsBySdl", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsSyncApp.do_insertBatchSmsBySdl() alias do_insertBatchSmsBySdl");
		return iRetValue;
	}


}