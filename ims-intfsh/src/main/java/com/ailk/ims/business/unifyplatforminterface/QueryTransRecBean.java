package com.ailk.ims.business.unifyplatforminterface;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.DispositComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.universeplatform.IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryTransRecResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryTransRecOut;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryTransRecReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.jd.entity.ImsSnMapping;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;

/**
 * @description:交易记录查询（统一支付平台）
 * @author caohm5
 * @date:2012-12-10
 *
 */
public class QueryTransRecBean extends BaseBusiBean {

	
	private QueryTransRecReq req;
	private Long acctId;

    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(QueryTransRecReq)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
    	//校验三户数据
    	List<QueryTransRecOut> outList=null;
    	try {
    		imsLogger.dumpJsonObject("*********交易记录查询（统一支付平台）******************实体入参", req);
    		outList=IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client.invokeGetTransInfoECPay(req);
		} catch (Exception e) {
			imsLogger.error(e, e);
			throw IMSUtil.throwBusiException(ErrorCodeDefine.INVOKE_UNIVERSE_PLAT_METHOD_CODE,e.getMessage());
		}
    	return new Object[]{outList};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Do_queryTransRecResponse respn=new Do_queryTransRecResponse();
    	List<QueryTransRecOut> outList=(List<QueryTransRecOut>)result[0];
    	if(outList!=null&&!outList.isEmpty()){
    		for(QueryTransRecOut out:outList){
    			String outSoNbr=out.getOrder_id();
    			Long bossSoNbr=null;
    			String bossSoDate=null;
    			if(!CommonUtil.isEmpty(outSoNbr)){
    				ImsSnMapping mapping = context.getComponent(DispositComponent.class).querySnMapping(null, outSoNbr,acctId);
    				if(mapping!=null){
    					bossSoNbr=mapping.getBosSoNbr();
    					bossSoDate=DateUtil.getFormatDate(mapping.getOutSoDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
    				}
    				out.setSo_date(bossSoDate);
    				out.setSo_nbr(bossSoNbr);
    			}
    		}
    		respn.setOutList(outList);
    		
    	}
    	return respn;
    	
    }

    @Override
    public void destroy()
    {

    }


	
	
}
