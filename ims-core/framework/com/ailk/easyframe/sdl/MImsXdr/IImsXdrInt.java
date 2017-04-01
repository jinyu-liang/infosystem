package com.ailk.easyframe.sdl.MImsXdr;

import com.ailk.easyframe.sdl.sdlclient.CsdlClientObject;
import org.apache.commons.logging.LogFactory;
import java.util.LinkedHashMap;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_StreamDecorator;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_Stream;
import jef.common.In;
import jef.common.Out;
import jef.common.InOut;
import com.ailk.easyframe.sdl.util.Utility;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObjectHolder;
import com.ailk.openbilling.persistence.imsxdr.entity.SXdr;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import org.apache.commons.logging.Log;
import jef.codegen.support.NotModified;
@NotModified
public class IImsXdrInt extends CsdlClientObject{

	protected static Log log = LogFactory.getLog(IImsXdrInt.class);

	public int do_xdrFirstAct(@InOut CsdlStructObjectHolder<SXdr> sXdr,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsXdr.do_xdrFirstAct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		sXdr.writeData(in);
		LinkedHashMap<String, Object> holderMap = new LinkedHashMap<String, Object>();
		holderMap.put("sXdr", sXdr);
		log.debug("starting call remote method IImsXdr.do_xdrFirstAct()");
		iRetValue = call_server("MImsXdr_IImsXdr", "do_xdrFirstAct", in, out);
		log.debug("ended call remote method IImsXdr.do_xdrFirstAct() alias do_xdrFirstAct");
		holderMap.put("sXdr", sXdr);
		holderMap.put("cErrorMsg", cErrorMsg);
		try{
		iRetValue = Utility.result(iRetValue, out, holderMap, cErrorMsg);
		} catch (Exception e) {
		}
		log.debug("exit IImsXdr.do_xdrFirstAct() with successful executing.");
		return iRetValue;
	}

	public int do_xdrFirstAct_upload(@InOut CsdlStructObjectHolder<SXdr> sXdr,CBSErrorMsg cErrorMsg)throws OBBufferErrorException{
		log.debug("entered IImsXdr.do_xdrFirstAct()");
		COBB_Stream in = new COBB_StreamDecorator();
		COBB_Stream out = new COBB_StreamDecorator(false);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		sXdr.writeData(in);
		log.debug("starting call remote method IImsXdr.do_xdrFirstAct()");
		call_server_upload("MImsXdr_IImsXdr", "do_xdrFirstAct", in);
		 try {iRetValue = Utility.result(iRetValue,null,null,cErrorMsg);} catch(Exception e) {}
		log.debug("ended call remote method IImsXdr.do_xdrFirstAct() alias do_xdrFirstAct");
		return iRetValue;
	}


}