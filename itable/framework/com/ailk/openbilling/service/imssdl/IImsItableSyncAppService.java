package com.ailk.openbilling.service.imssdl;

import com.ailk.easyframe.web.common.dal.IService;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.annotation.Sdl;
import jef.codegen.support.NotModified;
@NotModified
@Transactional
@Sdl(module="MImsItableSyncApp")
public interface IImsItableSyncAppService extends IService{

	/**
	 * 
	 * @return 
	 */
	public Integer do_dbmDispatchStart();
	/**
	 * 
	 * @return 
	 */
	public Integer do_dbmScanStart();
	/**
	 * 
	 * @return 
	 */
	public Integer db_dbmCustTransfer();
	/**
	 * 
	 * @return 
	 */
	public Integer do_dbmAcctDeposit();
	/**
	 * 
	 * @return 
	 */
	public Integer do_dbmUserDepositDispatch();
	/**
	 * 
	 * @return 
	 */
	public Integer do_dbmManualModify();

}