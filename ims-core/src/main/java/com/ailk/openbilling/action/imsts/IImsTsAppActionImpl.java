package com.ailk.openbilling.action.imsts;

import org.springframework.beans.factory.annotation.Autowired;
import com.ailk.easyframe.web.action.BaseAction;
import com.ailk.openbilling.service.imsts.IImsTsAppService;
public class IImsTsAppActionImpl extends BaseAction implements IImsTsAppAction{


	@Autowired
	protected IImsTsAppService imsts_iImsTsAppService;

	public void setImsts_iImsTsAppService(IImsTsAppService obj){
		this.imsts_iImsTsAppService = obj;
	}

	public IImsTsAppService getImsts_iImsTsAppService(){
		return imsts_iImsTsAppService;
	}

	protected IImsTsAppService getCrudService(){
		return imsts_iImsTsAppService;
	}


}