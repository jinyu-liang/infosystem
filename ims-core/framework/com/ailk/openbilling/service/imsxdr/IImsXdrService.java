package com.ailk.openbilling.service.imsxdr;

import com.ailk.easyframe.web.common.dal.IService;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.annotation.Sdl;
import jef.common.wrapper.Holder;
import com.ailk.openbilling.persistence.imsxdr.entity.SXdr;
import jef.codegen.support.NotModified;
@NotModified
@Transactional
@Sdl(module="MImsXdr")
public interface IImsXdrService extends IService{

	/**
	 * 
	 * @param sXdr  
	 * @return 
	 */
	public Integer do_xdrFirstAct(Holder<SXdr> sXdr);

}