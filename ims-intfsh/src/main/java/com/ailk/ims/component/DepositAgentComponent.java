package com.ailk.ims.component;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.PackingUtil;
import com.ailk.openbilling.persistence.cust.entity.ImsFundDepositAgent;
import com.ailk.openbilling.persistence.imscnsh.entity.ImsFundDepositAgentReq;

/**
 * @description:充值代理商账户密码表组件
 * @author caohm5
 * @date:2012-07-12
 */
public class DepositAgentComponent extends BaseComponent
{
    /**
     * @description:新增DB操作
     * @author caohm5
     * @date:2012-07-12
     */
    public void createImsFundDepositAgent(ImsFundDepositAgentReq req)
    {
        ImsFundDepositAgent imsFundDepositAgent = new ImsFundDepositAgent();
        imsFundDepositAgent.setAcctId(req.getAcct_id());
        imsFundDepositAgent.setPassword(PackingUtil.getMd5Str(req.getNew_password()));
        if (CommonUtil.isNotEmpty(req.getValid_date()))
        {
            imsFundDepositAgent.setValidDate(IMSUtil.formatDate(req.getValid_date(), null));
        }
        if (CommonUtil.isNotEmpty(req.getExpire_date()))
        {
            imsFundDepositAgent.setExpireDate(IMSUtil.formatDate(req.getExpire_date(), null));
        }
        if (CommonUtil.isNotEmpty(req.getNote()))
        {
            imsFundDepositAgent.setNote(req.getNote());
        }
        this.insert(imsFundDepositAgent);
    }

    /**
     * @description:修改DB操作
     * @author caohm5
     * @date:2012-07-12
     */
    public void updateImsFundDepositAgent(ImsFundDepositAgentReq req)
    {
        ImsFundDepositAgent imsFundDepositAgent = new ImsFundDepositAgent();
        imsFundDepositAgent.setPassword(PackingUtil.getMd5Str(req.getNew_password()));
        if (CommonUtil.isNotEmpty(req.getValid_date()))
        {
            imsFundDepositAgent.setValidDate(IMSUtil.formatDate(req.getValid_date(), null));
        }
        if (CommonUtil.isNotEmpty(req.getExpire_date()))
        {
            imsFundDepositAgent.setExpireDate(IMSUtil.formatDate(req.getExpire_date(), null));
        }
        if (CommonUtil.isNotEmpty(req.getNote()))
        {
            imsFundDepositAgent.setNote(req.getNote());
        }
        this.updateByCondition(imsFundDepositAgent, new DBCondition(ImsFundDepositAgent.Field.acctId, req.getAcct_id()));
    }

    /**
     * @description:查询充值代理商账户密码表
     * @author caohm5
     * @param acctId 账户编号
     * @date:2012-07-12
     */
    public ImsFundDepositAgent queryImsFundDepositAgent(Long acctId)
    {
        if (null == acctId)
        {
            return null;
        }
        return this.querySingle(ImsFundDepositAgent.class, new DBCondition(ImsFundDepositAgent.Field.acctId, acctId));
    }
}
