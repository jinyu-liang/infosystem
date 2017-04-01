package com.ailk.ims.business.query;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.DispositComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_qrySoNbrExistResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QrySoNbrExistReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:判断记录是否存在系统中
 * @author caohm5
 * @date:2013-01-12
 *
 */
public class QrySoNbrExistBean extends BaseBusiBean {


    private QrySoNbrExistReq req;
    
    private String soMode;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (QrySoNbrExistReq) input[0];

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if(null==req){
    		throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QrySoNbrExistReq");
    	}
        if (CommonUtil.isEmpty(req.getOut_so_mode()))
        {
        	 soMode = CommonUtil.short2String2Bit(context.getOper().getSo_mode());
            
        }else{
        	soMode=req.getOut_so_mode();
        }
        if (CommonUtil.isEmpty(req.getOut_so_nbr()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "out_so_nbr");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
    	Boolean flag=context.getComponent(DispositComponent.class).checkSoNbrIsExist(soMode, req.getOut_so_nbr(), null);
        return new Object[] { flag };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Boolean flag=(Boolean)result[0];
        Do_qrySoNbrExistResponse respn = new Do_qrySoNbrExistResponse();
        respn.setFlag(flag);
        return respn;
    }

    @Override
    public void destroy()
    {
    }
}
