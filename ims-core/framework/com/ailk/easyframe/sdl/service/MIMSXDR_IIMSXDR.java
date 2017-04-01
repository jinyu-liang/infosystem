package com.ailk.easyframe.sdl.service;

import org.apache.commons.logging.LogFactory;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_Stream;
import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.util.Utility;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObjectHolder;
import com.ailk.openbilling.persistence.imsxdr.entity.SXdr;
import jef.common.InOut;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import org.apache.commons.logging.Log;
import jef.codegen.support.NotModified;
@NotModified
public class MIMSXDR_IIMSXDR{

	protected static Log log = LogFactory.getLog(MIMSXDR_IIMSXDR.class);

	public static int DO_XDRFIRSTACT(COBB_Stream in,COBB_Stream out)throws OBBufferErrorException{
		Object[] inputs = {"DO_XDRFIRSTACT",".sdl","MImsXdr.::IImsXdr::DO_XDRFIRSTACT()",String.valueOf(in.getBufferSize())};
		ServiceUtil.print(log,true,0,inputs);
		int iRetValue = OBBufferErrorException.SDL_FAIL;
		long _t_start=System.currentTimeMillis();
		//prepare All params
		CBSErrorMsg cErrorMsg = new CBSErrorMsg();
		CsdlStructObjectHolder<SXdr> sXdr = new CsdlStructObjectHolder<SXdr>(new SXdr());
		sXdr.readData(in);
		
		log.debug("start call impl method IImsXdr.DO_XDRFIRSTACT() alias query_rewardInfo2");
		String moduleName = "imsxdr";
		String serviceName = "IImsXdr";
		String methodName = "do_xdrFirstAct";
		Object[] params=new Object[]{sXdr};
			iRetValue = OLTPServiceContext.invoke(moduleName, serviceName, methodName, cErrorMsg, params);
		java.util.LinkedHashMap<String,Object> holderMap = new java.util.LinkedHashMap<String,Object>();
		holderMap.put("sXdr", sXdr);
		holderMap.put("cErrorMsg", cErrorMsg);
		try {
			iRetValue = Utility.writeData(out,holderMap,cErrorMsg,iRetValue);
		} catch(Exception e) {}
		Object[] outputs = {"DO_XDRFIRSTACT",".sdl","MImsXdr.::IImsXdr::DO_XDRFIRSTACT()",String.valueOf(iRetValue),String.valueOf(System.currentTimeMillis() - _t_start), cErrorMsg.get_errorCode(),cErrorMsg.get_errorMsg(),cErrorMsg.get_hint(),out.getBufferSize()};
		ServiceUtil.print(log,false,iRetValue,outputs);

		log.debug("exit IImsXdr.DO_XDRFIRSTACT() with successful executing.");
		return iRetValue;
	}


}