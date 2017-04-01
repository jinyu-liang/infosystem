package com.ailk.ims.listener.action;

import org.apache.log4j.Logger;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.component.MdbSalComponent;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;

public class NewRegNofifyDebitListener implements IListenerAction
{
    private ImsLogger imsLogger =new ImsLogger(this.getClass());
    public Object doAction(IMSContext context)
    {
        CBSErrorMsg cbsErrorMsg = new CBSErrorMsg();
        imsLogger.info("first active notify deduct fee");
        context.getComponent(MdbSalComponent.class).deduct(cbsErrorMsg);
        return null;
    }

}
