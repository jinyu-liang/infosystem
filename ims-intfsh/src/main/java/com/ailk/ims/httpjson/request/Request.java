package com.ailk.ims.httpjson.request;

import java.util.Map;

/**
 * @Description:请求的具体信息
 * @author wangjt
 * @Date 2012-3-28
 */
public class Request
{
    /**
     * 根据业务不同，输入不同的条件，用map形式
     */
    private Map<String, String> BusiParams;
    private Map<String ,String[]> BusiParamsExt;
    public Map<String, String[]> getBusiParamsExt() {
		return BusiParamsExt;
	}

	public void setBusiParamsExt(Map<String, String[]> busiParamsExt) {
		BusiParamsExt = busiParamsExt;
	}

	private String BusiCode;

    public String getBusiCode()
    {
        return BusiCode;
    }

    public void setBusiCode(String busiCode)
    {
        BusiCode = busiCode;
    }

    public Map<String, String> getBusiParams()
    {
        return BusiParams;
    }

    public void setBusiParams(Map<String, String> busiParams)
    {
        BusiParams = busiParams;
    }
    

}
