package com.ailk.openbilling.action.imssdl;

import org.springframework.beans.factory.annotation.Autowired;
import com.ailk.easyframe.web.action.BaseAction;
import com.ailk.openbilling.service.imssdl.IImsSyncAppService;
public class IImsSyncAppActionImpl extends BaseAction implements IImsSyncAppAction{


	@Autowired
	protected IImsSyncAppService imssdl_iImsSyncAppService;

	public void setImssdl_iImsSyncAppService(IImsSyncAppService obj){
		this.imssdl_iImsSyncAppService = obj;
	}

	public IImsSyncAppService getImssdl_iImsSyncAppService(){
		return imssdl_iImsSyncAppService;
	}

	protected IImsSyncAppService getCrudService(){
		return imssdl_iImsSyncAppService;
	}


}