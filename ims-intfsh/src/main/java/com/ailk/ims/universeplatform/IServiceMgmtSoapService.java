package com.ailk.ims.universeplatform;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;

@WebServiceClient(name = "IServiceMgmtSoapService", 
                  wsdlLocation = "http://10.10.142.213:18082/ECPayKernel/services/IServiceMgmtSoapService?wsdl",
                  targetNamespace = "http://soap.integration.ecpay.ailk.com") 
public class IServiceMgmtSoapService extends javax.xml.ws.Service {

    public final static URL WSDL_LOCATION;
//    public final static String wsdlString="http://10.10.142.213:18082/ECPayKernel/services/IServiceMgmtSoapService?wsdl";
    public static final String wsdlString = SysUtil.getString(SysCodeDefine.busi.SH_UNIVER_PLAT_WEB_WSDL_ADDR);

    public final static QName SERVICE = new QName("http://soap.integration.ecpay.ailk.com", "IServiceMgmtSoapService");
    public final static QName IServiceMgmtSoapServiceHttpPort = new QName("http://soap.integration.ecpay.ailk.com", "IServiceMgmtSoapServiceHttpPort");
    static {
        URL url = null;
        try {
            url = new URL(wsdlString);
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(IServiceMgmtSoapService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", wsdlString);
            throw IMSUtil.throwBusiException(ErrorCodeDefine.GET_UNIVERSE_URL_BY_WSDL_ERROR,wsdlString);
        }
        WSDL_LOCATION = url;
    }

    public IServiceMgmtSoapService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IServiceMgmtSoapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IServiceMgmtSoapService() {
        super(WSDL_LOCATION, SERVICE);
    }
 
    @WebEndpoint(name = "IServiceMgmtSoapServiceHttpPort")
    public IServiceMgmtSoapServicePortType getIServiceMgmtSoapServiceHttpPort() {
        return super.getPort(IServiceMgmtSoapServiceHttpPort, IServiceMgmtSoapServicePortType.class);
    }

    @WebEndpoint(name = "IServiceMgmtSoapServiceHttpPort")
    public IServiceMgmtSoapServicePortType getIServiceMgmtSoapServiceHttpPort(WebServiceFeature... features) {
        return super.getPort(IServiceMgmtSoapServiceHttpPort, IServiceMgmtSoapServicePortType.class, features);
    }

}
