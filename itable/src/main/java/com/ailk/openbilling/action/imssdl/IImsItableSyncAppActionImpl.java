package com.ailk.openbilling.action.imssdl;

import org.springframework.beans.factory.annotation.Autowired;
import com.ailk.easyframe.web.action.BaseAction;
import com.ailk.openbilling.service.imssdl.IImsItableSyncAppService;
public class IImsItableSyncAppActionImpl extends BaseAction implements IImsItableSyncAppAction{


	@Autowired
	protected IImsItableSyncAppService imssdl_iImsItableSyncAppService;

	public void setImssdl_iImsItableSyncAppService(IImsItableSyncAppService obj){
		this.imssdl_iImsItableSyncAppService = obj;
	}

	public IImsItableSyncAppService getImssdl_iImsItableSyncAppService(){
		return imssdl_iImsItableSyncAppService;
	}

	protected IImsItableSyncAppService getCrudService(){
		return imssdl_iImsItableSyncAppService;
	}


}