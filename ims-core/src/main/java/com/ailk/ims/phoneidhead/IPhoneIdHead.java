package com.ailk.ims.phoneidhead;

/**
 * @Description: 提供此接口对号码头进行处理,不同项目调用相应实现类.
  * @Author wangdw5                                                                                                                                                                                                                                                                           
  * @Date 2012-06-14
 */
public interface IPhoneIdHead {
	public String phoneIdParse(String phoneId);
	public String phoneIdParse(String phoneId,Short deviceType);
	public void phoneIdCheck(String phoneId);
}
