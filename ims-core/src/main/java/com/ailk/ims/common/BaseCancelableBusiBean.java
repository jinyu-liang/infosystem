package com.ailk.ims.common;

import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;

/**
 * @Description: 所有撤单的业务bean都必须继承这个基类               
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-7-5
 */
public abstract class BaseCancelableBusiBean extends BaseBusiBean{
	
	/**
	 * @Description:撤单
	  * @param cancelInfo
	  * @return
	 */
	abstract public void cancel(CancelOrderInfo cancelInfo);
}
