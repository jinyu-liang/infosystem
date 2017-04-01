package com.ailk.ims.business.resvalid;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.LifeCycleChg;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.jd.entity.ImsResStsSync;

/**
 * @description: 用户的有效期复机
 * @author zenglu
 * @date:2012-08-14
 * @date 2012-11-25 luojb 性能优化
 */
public class ChgLifeCycleBusiBean extends BaseBusiBean
{
    List<LifeCycleChg> lifeCycleChgList = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        lifeCycleChgList = (List<LifeCycleChg>) input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {

        if (CommonUtil.isEmpty(lifeCycleChgList))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "lifeCycleChgList");
        }
        for (LifeCycleChg in : lifeCycleChgList)
        {
            if (CommonUtil.isEmpty(in.getPhoneId()) && in.getUserId() == null)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "user_id and phoneId");
            }
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
        //2012-11-25 luojb 性能优化
        List<ImsResStsSync> synclist = new ArrayList<ImsResStsSync>();

        for (LifeCycleChg lifeCycleChg : lifeCycleChgList)
        {
            ImsResStsSync info = packageImsResStsSync(lifeCycleChg.getUserId(), lifeCycleChg.getNotifyFlag());
            synclist.add(info);
        }
        DBUtil.insertBatch(synclist);
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return null;
    }

//    /**
//     * 只需将信控通知的状态插入到用户通知表中<br>
//     * zenglu 2012-08-14
//     * 
//     * @param LifeCycleChg
//     */
//    private ArrayList<ImsResStsSync> getImsResStsSyncList(LifeCycleChg chgInfo)
//    {
//        ArrayList<ImsResStsSync> result = new ArrayList<ImsResStsSync>();
//
//        ImsResStsSync info = packageImsResStsSync(chgInfo, chgInfo.getUserId(), chgInfo.getNotifyFlag());
//        if (info != null)
//        {
//            result.add(info);
//        }
//        return result;
//    }

    private ImsResStsSync packageImsResStsSync(Long userId, Short notifyFlag)
    {
        User3hBean bean = context.get3hTree().loadUser3hBean(userId);
        ImsResStsSync imsResStsSync = new ImsResStsSync();
        imsResStsSync.setId(DBUtil.getSequence(ImsResStsSync.class));
        imsResStsSync.setPhoneId(bean.getPhoneId());
        imsResStsSync.setAcctId(bean.getAcctId());
        imsResStsSync.setResourceId(userId);
        imsResStsSync.setCreateDate(context.getRequestDate());
        imsResStsSync.setSoNbr(context.getDoneCode());
        imsResStsSync.setNsubType(0);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setPsubType(0);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
        if (notifyFlag != null)
        {
            imsResStsSync.setNotifyFlag(notifyFlag.intValue());
        }

        // 上海增加OP_ID,ORG_ID
        if (null != context.getOper().getOp_id())
        {
            imsResStsSync.setOpId(context.getOper().getOp_id());
        }
        else
        {
            imsResStsSync.setOpId(9L);
        }
        if (null != context.getOper().getOrg_id())
        {
            imsResStsSync.setOrgId(context.getOper().getOrg_id());
        }
        else
        {
            imsResStsSync.setOrgId(0);
        }

        return imsResStsSync;
    }

    @Override
    public void destroy()
    {

    }

}
