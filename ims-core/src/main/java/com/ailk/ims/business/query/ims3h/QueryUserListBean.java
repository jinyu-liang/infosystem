package com.ailk.ims.business.query.ims3h;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.BudgetComponent;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.cust.entity.CmIdentityVsImei;
import com.ailk.openbilling.persistence.cust.entity.CmResExt;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.AcctQueryReq;
import com.ailk.openbilling.persistence.imsinner.entity.BaseQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.BaseQueryRet;
import com.ailk.openbilling.persistence.imsinner.entity.Do_userQueryResponse;
import com.ailk.openbilling.persistence.imsinner.entity.UserQueryReq;
import com.ailk.openbilling.persistence.imsinner.entity.UserQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.UserRet;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SUserLifecycle;

/**
 * @Description 优化后的查询用户信息接口
 * @author yanchuan
 * @Date 2012-2-16
 * @Date 2012-07-18 wukl 删除setMain_promotion重复设值的情况
 * @Date 2012-09-01 wukl 设置前先清一把分表参数值，避免影响后续操作
 * @Date 2012-12-12 wukl 上海项目存在主副号码区分，通过IDENTITY_ATTR来标识，计费在处理上网伴侣副号码、副号随意换副号码业务时会用到付号码
 */
public class QueryUserListBean extends QueryAcctListBean
{
    Boolean isCmResLifeCycle;
    Boolean isCmResIdentity;
    Boolean isCmIdentityVsImei;
    Boolean isCmResExt;
    Boolean isBillAcctId;
    Boolean isUserBudget;
    Boolean isMainPromotion;
    Boolean isCmResource;
    //lijc3 add
    Boolean isValidtity;
    UserQuery userQry;

    public <T extends BaseQueryRet> void query(BaseQueryReqHolder baseReq, List<T> retList) throws BaseException
    {
        userQry = context.getComponent(UserQuery.class);
        UserQueryReqHolder reqHolder = (UserQueryReqHolder) baseReq;
        if (reqHolder == null || reqHolder.getUserReq() == null)
            return;
        UserQueryReq req = reqHolder.getUserReq();
        isCmResource = req.getIsCmResource();
        if (isCmResource == null || isCmResource != true)
            return;
        if (req.getUserIds() == null && req.getPhoneIds() == null)
            return;
        List<Long> userIds = req.getUserIds();
        List<String> phoneIds = req.getPhoneIds();

        if (CommonUtil.isEmpty(userIds) && CommonUtil.isEmpty(phoneIds))
            return;
        // if(!CommonUtil.isNotEmpty(userIds) && !CommonUtil.isNotEmpty(phoneIds))
        // IMSUtil.throwBusiException();
        List<CmResource> resList = new ArrayList<CmResource>();
        User3hBean bean = null;
        if (CommonUtil.isNotEmpty(userIds))
        {
            for (Long userId : userIds)
            {
                if (!CommonUtil.isValid(userId))
                {
                    continue;
                }
                //往session里面设置分表的字段RESOURCE_ID lijc3
                //TODO @Date 2012-09-01 wukl 设置前先清一把分表参数值，避免影响后续操作
                IMSUtil.removeRouterParam();
                IMSUtil.setUserRouterParam(userId);
                // 查询用户信息
                // 捕捉异常，当用户不存在时不抛异常，直接循环下一个用户 yanchuan 2012-05-11
                try
                {
                    bean = context.get3hTree().loadUser3hBean(userId);
                }
                catch (IMS3hNotFoundException e)
                {
                    continue;
                }
                resList.add(bean.getUser());
            }
        }
        else if (CommonUtil.isNotEmpty(phoneIds))
        {
            for (String phoneId : phoneIds)
            {
                if (!CommonUtil.isValid(phoneId))
                    continue;
                //往session里面设置分表的字段RESOURCE_ID lijc3
                //TODO @Date 2012-09-01 wukl 设置前先清一把分表参数值，避免影响后续操作
                IMSUtil.removeRouterParam();
                IMSUtil.setIdenRouterParam(phoneId);
                // 捕捉异常，当电话号码不存在时不抛异常，直接循环下一个电话号码 yanchuan 2012-05-11
                try
                {
                    bean = context.get3hTree().loadUser3hBean(phoneId);
                }
                catch (IMS3hNotFoundException e)
                {
                    continue;
                }
                resList.add(bean.getUser());
            }
        }
        isCmResLifeCycle = req.getIsCmResLifeCycle();
        isCmResIdentity = req.getIsCmResIdentity();
        isCmIdentityVsImei = req.getIsCmIdentityVsImei();
        isCmResExt = req.getIsCmResExt();
        isBillAcctId = req.getIsBillAcctId();
        isUserBudget = req.getIsUserBudget();
        isMainPromotion = req.getIsMainPromotion();
        isValidtity=req.getIsValidtity();
        AcctQueryReq acctReq = reqHolder.getAcctReq();
        Boolean isCaAccount = null;
        if (acctReq != null)
            isCaAccount = acctReq.getIsCaAccount();
        for (CmResource res : resList)
        {
            if (res == null)
                continue;
            //往session里面设置分表的字段RESOURCE_ID lijc3
            //TODO @Date 2012-09-01 wukl 设置前先清一把分表参数值，避免影响后续操作
            IMSUtil.removeRouterParam();
            IMSUtil.setUserRouterParam(res.getResourceId());

            UserRet userRet = new UserRet();
            this.queryResource(res, userRet);
            //上海过户的时候用户账户关系在新分表里面，这里load3hbean去查的时候这个老分表已经查不到了
            if(!ProjectUtil.is_CN_SH()){
                userRet.setTitle_role_id(CommonUtil.IntegerToShort(bean.getTitleRoleId()));
            }
            retList.add((T) userRet);
            // 向上查询归属账户
            if (isCaAccount != null && isCaAccount == true)
            {
                // 查询归属帐户
                Long acctId = bean.getAcctId();
                if (CommonUtil.isValid(acctId))
                {
                    List<Long> acctIds = new ArrayList<Long>();
                    acctIds.add(acctId);
                    acctReq.setAcctIds(acctIds);
                    super.query(reqHolder, retList);
                }
            }

        }

        // super.query(req, response);

    }

    private void queryResource(CmResource res, UserRet userRet)
    {
        userTransform(res, userRet);
        if (isCmResLifeCycle != null && isCmResLifeCycle == true)
        {
          //2012-08-27 linzt 整理组件方法  采用load3hBean
            CmResLifecycle lifeCycle = null;
            try
            {
                //2012-11-14 zhangzj3 修复未对lifeCycle赋值
                lifeCycle = context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle();
            }
            catch(IMS3hNotFoundException e)
            {
            	imsLogger.error(e,e);
            }
            resLifeCycleTransform(lifeCycle, userRet);
            
        }
        if (isCmResIdentity != null && isCmResIdentity == true)
        {
            List<CmResIdentity> resIdentityResult = userQry.queryResIdentitysByUserId(res.getResourceId());
            resIdentityTransform(resIdentityResult, userRet);
        }
        if (isCmIdentityVsImei != null && isCmIdentityVsImei == true)
        {
            CmIdentityVsImei resImei = userQry.getCmIdentityVsImei(res.getResourceId());
            imeiTransform(resImei, userRet);
        }
        if (isCmResExt != null && isCmResExt == true)
        {
            CmResExt resExt = userQry.getCmResExt(res.getResourceId());
            resExtTransform(resExt, userRet);
        }
        if (isBillAcctId != null && isBillAcctId == true)
        {
            Long billAcctId =context.get3hTree().loadUser3hBean(res.getResourceId()).getBillAcctId();
            userRet.setBillable_acct_id(billAcctId);
        }
        if (isUserBudget != null && isUserBudget == true)
        {
            BudgetComponent bdgCmp = context.getComponent(BudgetComponent.class);
            userRet.setBudget((double) bdgCmp.getBudgetValueByUserId(res.getResourceId()));
        }
        if (isMainPromotion != null && isMainPromotion == true)
        {
            CoProd prod = context.getComponent(BaseProductComponent.class).queryPrimaryProductByUserId(res.getResourceId());
            if (prod == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_HAVE_MAINPROMOTION, res.getResourceId());
            }
            //@Date 2012-07-18 wukl 删除setMain_promotion重复设值的情况
            userRet.setMain_promotion(prod.getProductOfferingId());
            userRet.setMain_prod(prod.getProductId());
            userRet.setMain_prod_state(prod.getLifecycleStatusId().shortValue());
        }
        if(isValidtity!=null&&isValidtity){
            CmResValidity resValidtity = null;
            if (ProjectUtil.is_CN_SH())
            {
                resValidtity=context.getComponent(BaseLifeCycleComponent.class).queryUserValidity4Sh(res.getResourceId());
            }
            else
            {
                resValidtity=context.getComponent(BaseLifeCycleComponent.class).queryUserValidity(res.getResourceId());
            }
            if(resValidtity!=null){
                userRet.setUser_validity(resValidtity);
            }
        }
    }
    
    private SUserLifecycle convertoSUserLifecycle(CmResLifecycle lifeCycle){
        SUserLifecycle scycle=new SUserLifecycle();
        scycle.setCreate_date(IMSUtil.formatDate(lifeCycle.getCreateDate()));
        scycle.setExpire_date(IMSUtil.formatDate(lifeCycle.getExpireDate()));
        scycle.setFraud_flag(lifeCycle.getFrauldFlag());
        scycle.setOs_sts(lifeCycle.getOsSts());
        scycle.setRerating_flag(lifeCycle.getReratingFlag());
        scycle.setSts(lifeCycle.getSts());
        scycle.setUnbilling_flag(lifeCycle.getUnbillingFlag());
        scycle.setUser_id(lifeCycle.getResourceId());
        scycle.setUser_request_flag(lifeCycle.getUserrequestFlag());
        scycle.setValid_date(IMSUtil.formatDate(lifeCycle.getValidDate()));
        return scycle;
        
    }

    public <T extends BaseQueryRet> List createRet()
    {
        return new ArrayList<UserRet>();
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<UserRet> retList = (List<UserRet>) result[0];
        Do_userQueryResponse resp = new Do_userQueryResponse();
        resp.setUserList(retList);
        return resp;
    }

    private void userTransform(CmResource res, UserRet sUser)
    {
        // other fields
        sUser.setRegion_code(CommonUtil.IntegerToShort(res.getRegionCode()));
        sUser.setUser_id(res.getResourceId());

        if (null != res.getResSegment())
            sUser.setUser_segment(CommonUtil.IntegerToShort(res.getResSegment()));

        if (null != res.getBillingType())
            sUser.setPayment_mode(CommonUtil.IntegerToShort(res.getBillingType()));
        if (null != res.getBrandId())
            sUser.setBrand(CommonUtil.IntegerToShort(res.getBrandId()));
        if (null != res.getListeningLanguage())
            sUser.setIvr_language(CommonUtil.IntegerToShort(res.getListeningLanguage()));
        if (null != res.getReadingLanguage())
            sUser.setSms_language(CommonUtil.IntegerToShort(res.getReadingLanguage()));
        if (null != res.getWritingLanguage())
            sUser.setUssd_language(CommonUtil.IntegerToShort(res.getWritingLanguage()));

        Date createDate = res.getCreateDate();
        if (createDate != null)
            sUser.setCreate_date(DateUtil.getFormatDate(createDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        Date expireDate = res.getExpireDate();
        if (expireDate != null)
            sUser.setExpire_date(DateUtil.getFormatDate(expireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        if (null != res.getActiveDate())
        {

            sUser.setActive_date(DateUtil.getFormatDate(res.getActiveDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            // 2011-10-28 hunan 修改: 返回的用户validDate是cm_resource.active_date
            sUser.setValid_date(DateUtil.getFormatDate(res.getActiveDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

            // 2012/06/19 计算入网时间xieqr 计算天数的话：当前时间-激活时间 + 1
            Date activeDate = res.getActiveDate();
            Date nowDt = new Date();
            Long days = DateUtil.getBetweenDays(activeDate, nowDt) + 1;
            sUser.setIn_net_days(days);

        }else{
            sUser.setIn_net_days(0l);
        }
        if (null != res.getCountyCode())
            sUser.setCounty_code(CommonUtil.IntegerToShort(res.getCountyCode()));
        if (null != res.getResourceSpecId())
            sUser.setUser_type(res.getResourceSpecId().shortValue());
        // sUser.setMeasure_id(AmountUtil.getDefaultDbMeasureId());
    }

    private void resLifeCycleTransform(CmResLifecycle lifeCycle, UserRet sUser)
    {
        if (lifeCycle == null)
            return;
        sUser.setStatus(lifeCycle.getSts().shortValue());
        sUser.setOs_status(lifeCycle.getOsSts());
        if (ProjectUtil.is_CN_SH()){
            //上海停机位状态
            sUser.setOs_sts_dtl(lifeCycle.getOsStsDtl());
            if(lifeCycle.getOsStsDtl()!=null&&lifeCycle.getOsStsDtl()!=EnumCodeDefine.CM_RES_LIFECYCLE_OS_STS_DTL_NORMAL){
                sUser.setOut_service_date(DateUtil.getFormatDate(lifeCycle.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            sUser.setSUserLifecycle(convertoSUserLifecycle(lifeCycle));
        }

    }

    private void resIdentityTransform(List<CmResIdentity> identitys, UserRet sUser)
    {
        if (CommonUtil.isEmpty(identitys))
            return;
        for (CmResIdentity resIdenOther : identitys)
        {
            // imsi
//            if (resIdenOther.getIdentityType() == EnumCodeDefine.RESOURCE_IDENTITYTYPE_IMSI)
//            {
//                sUser.setImsi(resIdenOther.getIdentity());
//            }
            //@Date 2012-12-12 wukl 上海项目存在主副号码区分，通过IDENTITY_ATTR来标识，计费在处理上网伴侣副号码、副号随意换副号码业务时会用到付号码
            if (ProjectUtil.is_CN_SH() && resIdenOther.getIdentityAttr() != EnumCodeDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER)
            {
                continue;
            }
            if (resIdenOther.getIdentityType() == EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE)
            {
                sUser.setPhone_id(resIdenOther.getIdentity());
                //@Date 2012-10-29 yangjh : User Story #62685  RESOURCE_IDENTITYTYPE_SIMI->RESOURCE_IDENTITYTYPE_PHONE
                sUser.setImsi(resIdenOther.getRelIdentity());
            }
            if (null != resIdenOther.getResourcePasswd())
                sUser.setPassword(resIdenOther.getResourcePasswd());
        }
    }

    private void imeiTransform(CmIdentityVsImei resImei, UserRet sUser)
    {
        if (null == resImei)
            return;
        sUser.setIMEI(resImei.getImei().toString());
    }

    private void resExtTransform(CmResExt resExt, UserRet sUser)
    {
        if (resExt == null)
            return;
        ExtendParamList paramList = new ExtendParamList();
        List<ExtendParam> paramArr = new ArrayList<ExtendParam>();
        ExtendParam param = new ExtendParam();
        if (null != resExt.getProp1())
        {
            param.setParam_name(ConstantDefine.EXTPARAM_USER_PHFLAG);
            param.setParam_value(resExt.getProp1());
            paramArr.add(param);
        }
        if (null != resExt.getProp2())
        {
            param.setParam_name(ConstantDefine.EXTPARAM_USER_VOICEMAILFLAG);
            param.setParam_value(resExt.getProp2());
            paramArr.add(param);
        }
        if (null != resExt.getProp3())
        {
            param.setParam_name(ConstantDefine.EXTPARAM_USER_USSDCALLBACKFLAG);
            param.setParam_value(resExt.getProp3());
            paramArr.add(param);
        }
        if (!CommonUtil.isEmpty(paramArr))
        {
            paramList.setExtendParamList_Item(paramArr.toArray(new ExtendParam[paramArr.size()]));
        }
        sUser.setList_ext_param(paramList);
    }

    @Override
    public void setMain(BaseQueryReqHolder reqHolder)
    {
        UserQueryReqHolder req = (UserQueryReqHolder) reqHolder;
        req.getUserReq().setIsCmResource(true);
    }
}
