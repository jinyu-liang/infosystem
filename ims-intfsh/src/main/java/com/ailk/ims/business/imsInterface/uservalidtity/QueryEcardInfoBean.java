package com.ailk.ims.business.imsInterface.uservalidtity;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryEcardInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.EcardInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryEcardReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;

/**
 * 通过用户编号查询易卡通信息接口
 * 
 * @author zengqm 2012-3-3
 * @date 2012-09-22 zhangzj3 字段判断错误，导致空指针
 * @date 2012-10-16 zhangzj3 去掉3hbean,增加数据完整性判断
 */
public class QueryEcardInfoBean extends BaseBusiBean
{

    private QueryEcardReq req;
    private CmResLifecycle life;

    @Override
    public void init(Object... input) throws BaseException
    {
    	req = (QueryEcardReq) input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if(null==req){
    		throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QueryEcardReq");
    	}
        if (!CommonUtil.isValid(req.getResource_id())&&CommonUtil.isEmpty(req.getPhone_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resource_id、phone_id");
        }

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        /*List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        if (CommonUtil.isValid(req.getResource_id()))
        {
            list.add(context.get3hTree().loadUser3hBean(req.getResource_id()));
            
        }else if(CommonUtil.isNotEmpty(req.getPhone_id())){
        	
        	 list.add(context.get3hTree().loadUser3hBean(req.getPhone_id()));
        }
        return list;*/
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {   
        //@date 2012-10-16 zhangzj3 去掉3hbean,增加数据完整性判断
        Check3HParamReturn bean = context.getComponent(CheckComponentSH.class).check3HParam(null, null, req.getResource_id(), req.getPhone_id());
        life = bean.getResLifeCycle();
        CmResValidity res = context.getComponent(BaseLifeCycleComponent.class).queryUserValidity4Sh(bean.getUserId());
        return new Object[] { res};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        CmResValidity res = (CmResValidity) result[0];
        Do_queryEcardInfoResponse resp = new Do_queryEcardInfoResponse();
        if (res != null)
        {
        	EcardInfo ecardInfo = new EcardInfo();
            ecardInfo.setResource_id(res.getResourceId());
            //date 2012-09-22 zhangzj3 字段判断错误，导致空指针
            if (res.getEffectFlag() != null)
            {
                ecardInfo.setRec_sts(res.getEffectFlag().shortValue());
            }
            if (res.getUserValidDate() != null)
            {
                ecardInfo.setValid_date(DateUtil.getFormatDate(res.getUserValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            if (res.getUserExpireDate() != null)
            {
                ecardInfo.setExpire_date(DateUtil.getFormatDate(res.getUserExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            if(res.getEffectFlag() != null && res.getEffectFlag().intValue() == 1){
                ecardInfo.setExpire_date("2099-12-31 23:59:59");
            }
            //激活状态
            ecardInfo.setActive_status(res.getEffectFlag());
            //停开机状态,checkParam里面已查询
            //CmResLifecycle life = context.getComponent(UserQuery.class).queryLifeCycleByUserId(userId);
            if(null!=life){
            	//正常
            	if(life.getOsStsDtl()==0){
            		Integer sts=life.getSts();
            		if(sts==EnumCodeDefine.LIFECYCLE_ACTIVE){
            			//正常
            			ecardInfo.setOs_status(EnumCodeShDefine.ECARD_USER_NORMAL);
            		}else if(sts==EnumCodeDefine.LIFECYCLE_IDLE_INITIAL){
            			//预开户 
            			ecardInfo.setOs_status(EnumCodeShDefine.ECARD_USER_PRE_REGISTER);
            		}else if(sts==EnumCodeDefine.LIFECYCLE_TERMINAL){
            			//预销户
            			ecardInfo.setOs_status(EnumCodeShDefine.ECARD_USER_PRE_CANCLE);
            		}
            	}else if(life.getOsStsDtl()!=0){
            		//停机
            		ecardInfo.setOs_status(EnumCodeShDefine.ECARD_USER_STOP_SERVICE);
            	}
            }
            resp.setEcardInfo(ecardInfo);
            resp.setFlag(EnumCodeShDefine.SUBSCRIBER_HAS_ECARD_INFO);
        }else{
        	resp.setEcardInfo(null);
        	resp.setFlag(EnumCodeShDefine.SUBSCRIBER_HSA_NOT_ECARD_INFO);
        }
        return resp;
    }
    
    @Override
    public void destroy()
    {

    }
}
