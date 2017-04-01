package com.ailk.ims.exception;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.define.ErrorCodeDefine;

/**
 * @Description: 信息管理专用的异常对象，需要对每个异常进行code和message的定义
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-1
 */
public class IMSException extends BaseException
{
    private String hint;

    public IMSException()
    {
    }

    public IMSException(Throwable e)
    {
    	super(ErrorCodeDefine.UNKNOWN_ERROR, e);
    }
    public IMSException(String message)
    {
        super(ErrorCodeDefine.UNKNOWN_ERROR, message);
    }

    public IMSException(String message, Throwable e)
    {
        super(ErrorCodeDefine.UNKNOWN_ERROR, e, message);
    }

    public IMSException(long errorCode, Object... param)
    {
        super(errorCode, param);
    }

    public long getErrorCode()
    {
        return Long.valueOf(super.getCode());
    }

    public String getHint()
    {
        return hint;
    }

    public void setHint(String hint)
    {
        this.hint = hint;
    }

}
