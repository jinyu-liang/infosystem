package com.ailk.ims.business.deleteacct.busi;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_deleteAccountResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteAccountReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;

/**
 * @Description: 根据账户ID删除账户
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tjl5
 * @Date 2011-10-20
 */
public class DeleteAcctBusiBean extends BaseCancelableBusiBean
{

    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
       SDeleteAccountReq req = (SDeleteAccountReq)input[0];
       if (!CommonUtil.isValid(req.getAcct_id()))
       {
           throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
       }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SDeleteAccountReq req = (SDeleteAccountReq)input[0];
        BaseProductComponent prdCmp = context.getComponent(BaseProductComponent.class);
        
        List<Long> prodIds = prdCmp.queryProdIdsByAcctId(req.getAcct_id());
        if (!CommonUtil.isEmpty(prodIds))
        {
            SProductOrder prodOrder = new SProductOrder();
            for(Long prodId : prodIds)
            {
                prodOrder.setProduct_id(prodId);
                // 删除产品
                prdCmp.deleteProdOrder(prodOrder);
            }
        }
        context.getComponent(AccountComponent.class).deleteAccount(req.getAcct_id());
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_deleteAccountResponse();
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        SDeleteAccountReq req = (SDeleteAccountReq)input[0];
        Long acctId = req.getAcct_id();   
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadAcct3hBean(acctId)); 
        return list;
    }

}
