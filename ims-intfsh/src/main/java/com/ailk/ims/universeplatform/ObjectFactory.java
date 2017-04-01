
package com.ailk.ims.universeplatform;

import javax.xml.bind.annotation.XmlRegistry;
@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }
    public ServiceResponse createServiceResponse() {
        return new ServiceResponse();
    }
    public Service createService() {
        return new Service();
    }
}
