package com.ailk.openbilling.service.imsxdr;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.WebServiceProvider;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.xml.ws.Holder;
import com.ailk.openbilling.persistence.imsxdr.entity.SXdr;
import jef.codegen.support.NotModified;
@NotModified
@WebService
@WebServiceProvider(portName="IImsXdrService")
public interface IImsXdrServiceWs{

	@WebMethod
	public Integer do_xdrFirstAct(@WebParam(name = "sXdr",mode = Mode.INOUT) Holder<SXdr> sXdr);

}