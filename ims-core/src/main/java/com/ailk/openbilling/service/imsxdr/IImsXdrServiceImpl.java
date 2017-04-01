package com.ailk.openbilling.service.imsxdr;

import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.annotation.Sdl;
import jef.common.wrapper.Holder;
import com.ailk.openbilling.persistence.imsxdr.entity.SXdr;
@Transactional
@Sdl(module="MImsXdr")
public class IImsXdrServiceImpl extends IImsXdrServiceSkeleton{

	/**
	 * 
	 * @param sXdr  
	 * @return 
	 */
	public Integer do_xdrFirstAct(Holder<SXdr> sXdr){
		//TODO please implement this method for you business.
		return null;
	}


}