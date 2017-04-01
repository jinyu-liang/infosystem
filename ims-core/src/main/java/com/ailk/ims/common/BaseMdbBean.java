package com.ailk.ims.common;

import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.ims.component.MdbComponent;

/**
 * @Description:             
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-8-15
  * 2011-08-15 wuyujie : 增加component，以及修改返回值为CBSErrorMsg
 */
public abstract class BaseMdbBean extends ContextBean{
	/**
	 * @Description: 同步至mdb
	  * @param result
	  * @return
	  * @throws Exception
	 */
	public abstract CBSErrorMsg doSyncronization(Object[] result) throws Exception;
	
	
	public MdbComponent getMdbComponent() {
		return context.getComponent(MdbComponent.class);
	}
	
}
