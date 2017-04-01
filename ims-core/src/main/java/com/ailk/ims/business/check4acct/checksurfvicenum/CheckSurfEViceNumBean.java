package com.ailk.ims.business.check4acct.checksurfvicenum;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.SplitComponent;
import com.ailk.ims.component.VPNComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.imsinner.entity.Do_checkViceNumResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SCheckViceNumReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 判断是否是冲浪e家亲活动的副号码
 * 
 * @Description
 * @author xieqr
 * @Date 2012-2-23
 */
public class CheckSurfEViceNumBean extends BaseBusiBean
{

    private Long userId;
    private String phoneId;
    private Short checkType;
    private SCheckViceNumReq req;
    private VPNComponent vpnCmp = null;
    private SplitComponent splitCmp = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (SCheckViceNumReq) input[0];
        userId = req.getUser_id();
        phoneId = req.getPhone_id();
        checkType = req.getCheck_type();
        vpnCmp = context.getComponent(VPNComponent.class);
        splitCmp = context.getComponent(SplitComponent.class);

    }

    @Override
    public void validate(Object... input) throws BaseException
    {

        if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "user_id or phone_id");
        }
        if (checkType == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "check type");
        }

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        
        list.add(context.get3hTree().loadUser3hBean(userId,phoneId));

        return list;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        IMS3hBean bean = context.get3hTree().loadUser3hBean(userId, phoneId);

        userId = bean.getUserId();

        String isViceNum = "0"; // 1-是；0-不是；2-主号码

        if (checkType == EnumCodeDefine.CHECK_SURF_E_VICE_NUM)
        {
            // 判断是否是冲浪e家亲活动的副号码

            CaAccountRv rv = vpnCmp.queryGroupAcctRv(userId, (int) EnumCodeDefine.ACCOUNT_TYPE_SURF_E_FAMILY);
            if (rv != null)
            {
                // if(rv.getTitleRoleId().intValue()==EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE){
                // isViceNum="2";
                // }else if(rv.getTitleRoleId().intValue()!=EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE){
                // isViceNum="1";
                // }
                isViceNum = "1";
            }

        }
        else if (checkType == EnumCodeDefine.CHECK_SIX_SIX_FAMILY_PACKAGE_VICE_NUM)
        {
            // 判断是否是66家庭包活动的副号码

            CaAccountRv rv = vpnCmp.queryGroupAcctRv(userId, (int) EnumCodeDefine.ACCOUNT_TYPE_SIX_SIX_FAMILY_PACKAGE);
            if (rv != null)
            {
                isViceNum = "1";
            }

        }
        else if (checkType == EnumCodeDefine.CHECK_ALL_PAY_VICE_NUM)
        {
            // 判断是否统付类活动的副号码
        	
            boolean bl = splitCmp.checkTongFuViceNum(userId);
            if (bl)
            {
                isViceNum = "1";
            }
        }else if(checkType == EnumCodeDefine.CHECK_CHANGXIANG_VICR_NUM){
            CaAccountRv rv = vpnCmp.queryGroupAcctRv(userId, (int) EnumCodeDefine.ACCOUNT_TYPE_VIRTUAL_FAMILY);
            if (rv != null)
            {
                isViceNum = "1";
            }
        }
        else
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_VICE_NUM_CHECK_TYPE, "1,2,3,4");
        }

        return new Object[] { isViceNum };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        String isViceNum = (String) result[0];
        Do_checkViceNumResponse resp = new Do_checkViceNumResponse();
        // 1-是；0-不是；2-主号码
        resp.setCheck_flag(isViceNum);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

}
