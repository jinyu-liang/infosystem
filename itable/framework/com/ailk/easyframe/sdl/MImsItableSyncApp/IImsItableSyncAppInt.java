package com.ailk.easyframe.sdl.MImsItableSyncApp;

import com.ailk.easyframe.sdl.sdlclient.CsdlClientObject;
import org.apache.commons.logging.LogFactory;
import java.util.LinkedHashMap;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_StreamDecorator;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_Stream;
import jef.common.In;
import jef.common.Out;
import jef.common.InOut;
import com.ailk.easyframe.sdl.util.Utility;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import org.apache.commons.logging.Log;
import jef.codegen.support.NotModified;
@NotModified
public class IImsItableSyncAppInt extends CsdlClientObject{

	protected static Log log = LogFactory.getLog(IImsItableSyncAppInt.class);

	public int do_dbmDispatchStart(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmDispatchStart()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsItableSyncApp.do_dbmDispatchStart()");
		iRetValue = call_server("MImsItableSyncApp_IImsItableSyncApp", "do_dbmDispatchStart", in, out);
		log.debug("ended call remote method IImsItableSyncApp.do_dbmDispatchStart() alias do_dbmDispatchStart");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsItableSyncApp.do_dbmDispatchStart() with successful executing.");
		return iRetValue;
	}

	public int do_dbmDispatchStart_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmDispatchStart()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsItableSyncApp.do_dbmDispatchStart()");
		call_server_upload("MImsItableSyncApp_IImsItableSyncApp", "do_dbmDispatchStart", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsItableSyncApp.do_dbmDispatchStart() alias do_dbmDispatchStart");
		return iRetValue;
	}

	public int do_dbmScanStart(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmScanStart()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsItableSyncApp.do_dbmScanStart()");
		iRetValue = call_server("MImsItableSyncApp_IImsItableSyncApp", "do_dbmScanStart", in, out);
		log.debug("ended call remote method IImsItableSyncApp.do_dbmScanStart() alias do_dbmScanStart");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsItableSyncApp.do_dbmScanStart() with successful executing.");
		return iRetValue;
	}

	public int do_dbmScanStart_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmScanStart()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsItableSyncApp.do_dbmScanStart()");
		call_server_upload("MImsItableSyncApp_IImsItableSyncApp", "do_dbmScanStart", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsItableSyncApp.do_dbmScanStart() alias do_dbmScanStart");
		return iRetValue;
	}

	public int db_dbmCustTransfer(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.db_dbmCustTransfer()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsItableSyncApp.db_dbmCustTransfer()");
		iRetValue = call_server("MImsItableSyncApp_IImsItableSyncApp", "db_dbmCustTransfer", in, out);
		log.debug("ended call remote method IImsItableSyncApp.db_dbmCustTransfer() alias db_dbmCustTransfer");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsItableSyncApp.db_dbmCustTransfer() with successful executing.");
		return iRetValue;
	}

	public int db_dbmCustTransfer_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.db_dbmCustTransfer()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsItableSyncApp.db_dbmCustTransfer()");
		call_server_upload("MImsItableSyncApp_IImsItableSyncApp", "db_dbmCustTransfer", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsItableSyncApp.db_dbmCustTransfer() alias db_dbmCustTransfer");
		return iRetValue;
	}

	public int do_dbmAcctDeposit(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmAcctDeposit()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsItableSyncApp.do_dbmAcctDeposit()");
		iRetValue = call_server("MImsItableSyncApp_IImsItableSyncApp", "do_dbmAcctDeposit", in, out);
		log.debug("ended call remote method IImsItableSyncApp.do_dbmAcctDeposit() alias do_dbmAcctDeposit");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsItableSyncApp.do_dbmAcctDeposit() with successful executing.");
		return iRetValue;
	}

	public int do_dbmAcctDeposit_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmAcctDeposit()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsItableSyncApp.do_dbmAcctDeposit()");
		call_server_upload("MImsItableSyncApp_IImsItableSyncApp", "do_dbmAcctDeposit", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsItableSyncApp.do_dbmAcctDeposit() alias do_dbmAcctDeposit");
		return iRetValue;
	}

	public int do_dbmUserDepositDispatch(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmUserDepositDispatch()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsItableSyncApp.do_dbmUserDepositDispatch()");
		iRetValue = call_server("MImsItableSyncApp_IImsItableSyncApp", "do_dbmUserDepositDispatch", in, out);
		log.debug("ended call remote method IImsItableSyncApp.do_dbmUserDepositDispatch() alias do_dbmUserDepositDispatch");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsItableSyncApp.do_dbmUserDepositDispatch() with successful executing.");
		return iRetValue;
	}

	public int do_dbmUserDepositDispatch_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmUserDepositDispatch()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsItableSyncApp.do_dbmUserDepositDispatch()");
		call_server_upload("MImsItableSyncApp_IImsItableSyncApp", "do_dbmUserDepositDispatch", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsItableSyncApp.do_dbmUserDepositDispatch() alias do_dbmUserDepositDispatch");
		return iRetValue;
	}

	public int do_dbmManualModify(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmManualModify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		log.debug("starting call remote method IImsItableSyncApp.do_dbmManualModify()");
		iRetValue = call_server("MImsItableSyncApp_IImsItableSyncApp", "do_dbmManualModify", in, out);
		log.debug("ended call remote method IImsItableSyncApp.do_dbmManualModify() alias do_dbmManualModify");
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsItableSyncApp.do_dbmManualModify() with successful executing.");
		return iRetValue;
	}

	public int do_dbmManualModify_upload(CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsItableSyncApp.do_dbmManualModify()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		log.debug("starting call remote method IImsItableSyncApp.do_dbmManualModify()");
		call_server_upload("MImsItableSyncApp_IImsItableSyncApp", "do_dbmManualModify", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsItableSyncApp.do_dbmManualModify() alias do_dbmManualModify");
		return iRetValue;
	}


}