package com.ailk.ims.phoneidhead;

/**
 * @Description: 默认实现类,phoneId不进行号码头处理,国内项目配置
  * @Author wangdw5                                                                                                                                                                                                                                                                           
  * @Date 2012-06-14
 */
public class DefaultPhoneIdHeadHandler implements IPhoneIdHead {

	public String phoneIdParse(String phoneId) {
		return phoneId;
	}

    public void phoneIdCheck(String phoneId)
    {
        
    }

    public String phoneIdParse(String phoneId, Short deviceType)
    {
        return phoneId;
    }

}
