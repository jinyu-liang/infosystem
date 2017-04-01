package com.ailk.openbilling.service.imssdl;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.WebServiceProvider;
import jef.codegen.support.NotModified;
@NotModified
@WebService
@WebServiceProvider(portName="IImsItableSyncAppService")
public interface IImsItableSyncAppServiceWs{

	@WebMethod
	public Integer do_dbmDispatchStart();
	@WebMethod
	public Integer do_dbmScanStart();
	@WebMethod
	public Integer db_dbmCustTransfer();
	@WebMethod
	public Integer do_dbmAcctDeposit();
	@WebMethod
	public Integer do_dbmUserDepositDispatch();
	@WebMethod
	public Integer do_dbmManualModify();

}