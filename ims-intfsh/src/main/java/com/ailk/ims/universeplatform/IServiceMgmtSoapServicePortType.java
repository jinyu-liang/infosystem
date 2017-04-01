package com.ailk.ims.universeplatform;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://soap.integration.ecpay.ailk.com", name = "IServiceMgmtSoapServicePortType")
@XmlSeeAlso({ObjectFactory.class})
public interface IServiceMgmtSoapServicePortType {

    @WebResult(name = "out", targetNamespace = "http://soap.integration.ecpay.ailk.com")
    @RequestWrapper(localName = "service", targetNamespace = "http://soap.integration.ecpay.ailk.com", className = "com.ailk.ims.universeplatform.Service")
    @WebMethod
    @ResponseWrapper(localName = "serviceResponse", targetNamespace = "http://soap.integration.ecpay.ailk.com", className = "com.ailk.ims.universeplatform.ServiceResponse")
    public java.lang.String service(
        @WebParam(name = "in0", targetNamespace = "http://soap.integration.ecpay.ailk.com")
        java.lang.String in0
    );
}
