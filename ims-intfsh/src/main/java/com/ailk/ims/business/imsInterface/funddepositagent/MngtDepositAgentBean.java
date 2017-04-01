package com.ailk.ims.business.imsInterface.funddepositagent;

import java.util.Date;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.DepositAgentComponent;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.PackingUtil;
import com.ailk.openbilling.persistence.cust.entity.ImsFundDepositAgent;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_mngtAgentResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.ImsFundDepositAgentReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:充值代理商账户密码表，新增、修改
 * @author caohm5
 * @date：2012-07-12
 * @date 2012-11-5 zhangzj3 性能优化
 */
public class MngtDepositAgentBean extends BaseBusiBean
{
    private ImsFundDepositAgentReq req;
    private Short operType;
    private DepositAgentComponent cmp = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        cmp = context.getComponent(DepositAgentComponent.class);
        req = (ImsFundDepositAgentReq) input[0];
        operType = req.getOper_type();
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        // 请求参数不能为空
        if (null == req)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "ImsFundDepositAgentReq");
        }
        // 操作类型不能为空；且必须是在（0、2）内
        if (null == operType || (EnumCodeShDefine.OPERTYPE_IS_ADD != operType && EnumCodeShDefine.OPERTYPE_IS_MODIFY != operType))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAMTER_MUST_BE_IN, "operType", "0,2");
        }
        // 账户编号不能为空
        if (null == req.getAcct_id())
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
        }
        // 新增的时候；新密码不能为空
        if (EnumCodeShDefine.OPERTYPE_IS_ADD == operType)
        {
        	
            if (CommonUtil.isEmpty(req.getNew_password()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "new_password");
            }
        }
        // 修改的时候；新旧密码不能为空
        else if (EnumCodeShDefine.OPERTYPE_IS_MODIFY == operType)
        {
        	
            if (CommonUtil.isEmpty(req.getNew_password()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "new_password");
            }
            
            if (CommonUtil.isEmpty(req.getOld_password()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "old_password");
            }
        }
        //失效日期不能大于失效日期
        if(!CommonUtil.isEmpty(req.getExpire_date())&&!CommonUtil.isEmpty(req.getValid_date())){
        	//生效日期
        	Date beginDate=IMSUtil.formatDate(req.getValid_date(), null);
        	//失效日期
        	Date endDate=IMSUtil.formatDate(req.getExpire_date(), null);
        	
        	if(beginDate.after(endDate)){
        		throw IMSUtil.throwBusiException(ErrorCodeDefine.VPNRELATION_EXPIREDATE_ERROR, beginDate,endDate);
        	}
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	/*List<IMS3hBean> list = new ArrayList<IMS3hBean>();
		list.add(context.get3hTree().loadAcct3hBean(req.getAcct_id()));
        return list;*/
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {   
        //@Date 2012-11-5 zhangzj3 性能优化,公共校验
        context.getComponent(CheckComponentSH.class).check3HParam(null, req.getAcct_id(), null, null);
        
        if (EnumCodeShDefine.OPERTYPE_IS_ADD == operType)
        {
            addImsFundDepositAgent(req);
        }
        else if (EnumCodeShDefine.OPERTYPE_IS_MODIFY == operType)
        {
            modifyImsFundDepositAgent(req);
        }
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_mngtAgentResponse();
    }

    @Override
    public void destroy()
    {

    }

    /**
     * @description:新增
     * @author caohm5
     * @date:2012-07-12
     */
    public void addImsFundDepositAgent(ImsFundDepositAgentReq req)
    {
        Long acctId = req.getAcct_id();
        // 记录已经存在，不能新增
        ImsFundDepositAgent imsFundDepositAgent = cmp.queryImsFundDepositAgent(acctId);
        if (null != imsFundDepositAgent)
        {
        	throw IMSUtil.throwBusiException(ErrorCodeDefine.RECORD_IS_ALREADY_EXIST, acctId);
        }
        cmp.createImsFundDepositAgent(req);
    }

    /**
     * @description:修改
     * @author caohm5
     * @date:2012-07-12
     */
    public void modifyImsFundDepositAgent(ImsFundDepositAgentReq req)
    {
        Long acctId = req.getAcct_id();
        String oldPassWord = req.getOld_password();
        // 记录不存在，不能修改
        ImsFundDepositAgent imsFundDepositAgent = cmp.queryImsFundDepositAgent(acctId);
        if (null == imsFundDepositAgent)
        {
        	throw IMSUtil.throwBusiException(ErrorCodeDefine.RECORD_IS_NOT_ALREADY_EXIST, acctId);
        }
        if (null != imsFundDepositAgent && !imsFundDepositAgent.getPassword().equals(PackingUtil.getMd5Str(oldPassWord)))
        {
        	throw IMSUtil.throwBusiException(ErrorCodeDefine.PASSWORD_IS_NOT_RIGHT,"********", acctId);
        }
        cmp.updateImsFundDepositAgent(req);
    }

}
