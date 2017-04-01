package com.ailk.ims.common;

import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;

/**
 * @Description: 基准sdl业务bean
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-8-9
 */
public abstract class BaseSdlBusiBean extends BaseBusiBean {
	public BaseResponse createResponse(Object[] result){
		return new Do_sdlResponse();
	}
	
}
