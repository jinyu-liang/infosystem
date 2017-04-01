package com.ailk.ims.proxy;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.IMSContext;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * @Description: 业务代理基类，所有代理类都需要继承此类            
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public interface IBusiProxy {
	public BaseResponse doService(IMSContext context,Object... input) throws BaseException;
}
