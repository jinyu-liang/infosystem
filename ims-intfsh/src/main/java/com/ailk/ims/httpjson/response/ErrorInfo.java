package com.ailk.ims.httpjson.response;

/**
 * @Description:对应crm返回的错误信息
 * @author wangjt
 * @Date 2012-3-28
 */
public class ErrorInfo
{
    private String Message;
    private String Code;
    private String Hint;

    public String getMessage()
    {
        return Message;
    }

    public void setMessage(String message)
    {
        Message = message;
    }

    public String getCode()
    {
        return Code;
    }

    public void setCode(String code)
    {
        Code = code;
    }

    public String getHint()
    {
        return Hint;
    }

    public void setHint(String hint)
    {
        Hint = hint;
    }
}
