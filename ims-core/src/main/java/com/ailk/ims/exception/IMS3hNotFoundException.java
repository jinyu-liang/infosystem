package com.ailk.ims.exception;

import com.ailk.ims.define.ErrorCodeDefine;

public class IMS3hNotFoundException extends IMSException {
	public IMS3hNotFoundException()
    {
    }

    public IMS3hNotFoundException(Throwable e)
    {
    	super(ErrorCodeDefine.UNKNOWN_ERROR, e);
    }
    public IMS3hNotFoundException(String message)
    {
        super(ErrorCodeDefine.UNKNOWN_ERROR, message);
    }

    public IMS3hNotFoundException(String message, Throwable e)
    {
        super(ErrorCodeDefine.UNKNOWN_ERROR, e, message);
    }

    public IMS3hNotFoundException(long errorCode, Object... param)
    {
        super(errorCode, param);
    }
}
