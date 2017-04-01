package com.ailk.ims.business.undovaliditychg.busi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.imsinner.entity.Do_unDoValidityChgResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SLifeCycleByAcctChgReq;
import com.ailk.openbilling.persistence.imsinner.entity.SUnDoValidityChgReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;

/**
 * @Description: 用户有效期撤单操作类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author tangjl5
 * @Date 2011-10-10
 * @Date 2011-10-27 将外部soNbr转换为内部soNbr
 * @Date 2012-2-2 添加账户级账本和用户级账本的有效期撤单
 */
public class UndoValidityChgBusiBean extends BaseCancelableBusiBean
{

    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
    }

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SUnDoValidityChgReq req = (SUnDoValidityChgReq) input[0];
        Long unDealSoNbr = req.getUnDealsoNbr();
        Date unDealDate = req.getUnDealDate();
        if (!CommonUtil.isValid(unDealSoNbr))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "unDealSoNbr");
        }

        if (unDealDate == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "unDealDate");
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SUnDoValidityChgReq req = (SUnDoValidityChgReq) input[0];
        Long outerSoNbr = req.getUnDealsoNbr();
        CmBusi record = BusiRecordUtil.queryCmBusiByOuterSoNbr(String.valueOf(outerSoNbr));

        if (record == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_UNDO_VALIDITY_INNER_SONBR_NOT_EXIST, outerSoNbr);
        }
        SLifeCycleByAcctChgReq lifeCycleReq = new SLifeCycleByAcctChgReq();
        BaseLifeCycleComponent lifeCycleCmp = context.getComponent(BaseLifeCycleComponent.class);
        List<CmResValidity> validityList = lifeCycleCmp.queryValidityBySoNbrUserId(record.getSoNbr());

        if (CommonUtil.isNotEmpty(validityList))
        {
            for (CmResValidity validity : validityList)
            {
                long beforeChgDays = validity.getBeforeChangeDays();
                long afterChgDays = validity.getAfterChangeDays();
                Long validityDays = afterChgDays - beforeChgDays;
                if (null != validityDays && validityDays != 0)
                {
                    if (validityDays > 0)
                    {
                        lifeCycleReq.setReduceDays(validityDays.intValue());
                    }
                    else
                    {
                        lifeCycleReq.setExtendDays(-validityDays.intValue());
                    }
                }
                else
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_VALIDITY_HAS_NOT_BE_CHANGED, outerSoNbr);
                }

                if (req.getForce_flag() != null)
                    lifeCycleReq.setForceFlag(req.getForce_flag());

                if (!CommonUtil.isValid(validity.getResourceId()))
                {
                    // 账户级账本有效期撤单的请求参数设置
                    lifeCycleReq.setAcct_id(validity.getAccountId());
                    lifeCycleReq.setBalanceType(EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_SINGLE_BALANCE);
                }
                else
                {
                    // 用户级账本有效期撤单的请求参数设置
                    lifeCycleReq.setResource_id(validity.getResourceId());
                    lifeCycleReq.setBalanceType(EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_USER);
                }
                lifeCycleReq.setTriggerEventType(EnumCodeDefine.LIFECYCLE_EVENT_VALIDITYCHANGE);
                lifeCycleCmp.unDoUserValidity(lifeCycleReq, req.getUnDealDate(), req.getUnDealsoNbr());
            }
        }

        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_unDoValidityChgResponse();
    }

    @Override
    public void destroy()
    {
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
	   SUnDoValidityChgReq req = (SUnDoValidityChgReq) input[0];
	   Long acctId = req.getAcctId();
	   if (!CommonUtil.isValid(acctId))
       {
           return null;
       }
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadAcct3hBean(acctId));
        return list;
    }
}
