package com.ailk.openbilling.service.imssdl;

import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.LogFactory;
import com.ailk.easyframe.web.common.annotation.Sdl;
import org.apache.commons.logging.Log;
@Transactional
@Sdl(module="MImsItableSyncApp")
public class IImsItableSyncAppServiceImpl extends IImsItableSyncAppServiceSkeleton implements IImsItableSyncAppService{


	private Log log = LogFactory.getLog(IImsItableSyncAppServiceImpl.class);

	public Integer do_dbmDispatchStart(){
		//TODO please implement this method for you business.
		return null;
	}

	public Integer do_dbmScanStart(){
		//TODO please implement this method for you business.
		return null;
	}

	public Integer db_dbmCustTransfer(){
		//TODO please implement this method for you business.
		return null;
	}

	public Integer do_dbmAcctDeposit(){
		//TODO please implement this method for you business.
		return null;
	}

	public Integer do_dbmUserDepositDispatch(){
		//TODO please implement this method for you business.
		return null;
	}

	public Integer do_dbmManualModify(){
		//TODO please implement this method for you business.
		return null;
	}


}