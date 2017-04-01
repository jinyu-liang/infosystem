package com.ailk.openbilling.action.imsxdr;

import org.springframework.beans.factory.annotation.Autowired;
import com.ailk.easyframe.web.action.BaseAction;
import com.ailk.openbilling.service.imsxdr.IImsXdrService;
public class IImsXdrActionImpl extends BaseAction implements IImsXdrAction{


	@Autowired
	protected IImsXdrService imsxdr_iImsXdrService;

	public void setImsxdr_iImsXdrService(IImsXdrService obj){
		this.imsxdr_iImsXdrService = obj;
	}

	public IImsXdrService getImsxdr_iImsXdrService(){
		return imsxdr_iImsXdrService;
	}

	protected IImsXdrService getCrudService(){
		return imsxdr_iImsXdrService;
	}


}