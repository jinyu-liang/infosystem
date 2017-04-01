package com.ailk.ims.component.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.AccountRelationComponent;
import com.ailk.ims.component.AmountComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BudgetComponent;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.PhoneIdHeadUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIdentityVsImei;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmResExt;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.FN;
import com.ailk.openbilling.persistence.imsintf.entity.FNList;
import com.ailk.openbilling.persistence.imsintf.entity.ProductFN;
import com.ailk.openbilling.persistence.imsintf.entity.ProductFNList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.imsintf.entity.SUserLifecycle;
import com.ailk.openbilling.persistence.pm.entity.PmAssetItem;
import com.ailk.openbilling.persistence.pm.entity.PmServiceSpec;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCyclePattern;

/**
 * @Description:用户相关的信息查询的方法类
 * @author wangjt
 * @Date 2011-12-21
 * @Date tangjl5 2012-3-28 解决用户销户后，用户没有与账户的关联关系，未做空判断，导致空指针问题
 * @date wangjt 2012-3-30 修改isEmpty方法判断对象的问题
 * @Date 2012-3-30 tangjl5 在没有信用度的时候，可变更后付费，或混合付费用户的用户状态
 * @Date 2012-4-9 tangjl5 增加返回expire_date
 * @Date 2012-4-16 wangjt 优化： queryMainUserByAcctId方法
 * @Date 2012-4-25 yangjh sUserTransform方法中把新建对象放到判断后面
 * @Date 2012-04-27yangjh 增加 queryResIdentitysByIMSI 根据imsi查询CmResIdentityList,为了如果数据不合法有多条记录用
 * @Date 2012-06-14 wangdw5 : User Story #42479 AIS号码头处理 User Story #50766 CUS_IM_Suser结构变更'
 * @Date 2012-07-12 wangdw5 : #51466 需要重新new一个对象,否则就是一个对象重复ADD
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class UserQuery extends BaseComponent
{
    public UserQuery()
    {
    }

    /**
     * luojb 2012-11-12
     * 
     * @param userID
     * @return
     * @throws IMSException
     * @deprecated 仅仅提供给上海使用
     */
    public CmResource queryUserByUserID(Long userID) throws IMSException
    {
        return querySingle(CmResource.class, new DBCondition(CmResource.Field.resourceId, userID));
    }

    /**
     * luojb 2012-11-12
     * 
     * @param userId
     * @return
     * @deprecated 仅仅提供给上海使用
     */
    public String queryPhoneIdByUserId(Long userId)
    {
        CmResIdentity cmResIdentity = queryResIdentityByUserId(userId, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);
        if (cmResIdentity == null)
        {
            return null;
        }
        return cmResIdentity.getIdentity();
    }

    /**
     * luojb 2012-11-12
     * 
     * @param userId
     * @return
     * @deprecated 仅仅提供给上海使用
     */
    public CmResIdentity queryResIdentityByUserId(Long resourceId, Integer idenType) throws IMSException
    {
        return queryResIdentity(resourceId, null, idenType);
    }

    /**
     * luojb 2012-11-12
     * 
     * @param userId
     * @return
     * @deprecated 仅仅提供给上海使用
     */
    public CmResLifecycle queryLifeCycleByUserId(Long resId)
    {
        return querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId, resId));
    }

    /**
     * 根据手机号码查询用户
     */
    public CmResource queryUserByPhoneId(String phoneId)
    {
        CmResIdentity iden = queryResIdentityByIden(PhoneIdHeadUtil.phoneIdParse(phoneId),
                EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);

        // return iden == null ? null : queryUserByUserID(iden.getResourceId());
        return iden == null ? null : (CmResource) querySingle(CmResource.class,
                new DBCondition(CmResource.Field.resourceId, iden.getResourceId()));
    }

    /**
     * 根据手机号码查询CmResIdentity
     */
    public CmResIdentity queryIdentityByPhoneId(String phoneId)
    {
        CmResIdentity iden = queryResIdentityByIden(PhoneIdHeadUtil.phoneIdParse(phoneId),
                EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);
        return iden;
    }

    /**
     * @Description: 根据账户编号查询主用户，如果没有主用户，则用查到的第一个归属用户
     */
    public CmResource queryMainUserByAcctId(Long acctId)
    {
        List<CaAccountRes> result = query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.acctId, acctId));
        if (CommonUtil.isEmpty(result))
        {
            return null;
        }

        Long resourceId = null;

        // 查找主号码
        for (CaAccountRes rel : result)
        {
            if (rel.getRelationshipType() != null
                    && rel.getRelationshipType().intValue() == EnumCodeDefine.ACCOUNT_RELATION_RES_MAINRESOURCE)
            {
                resourceId = rel.getResourceId();
                break;
            }
        }
        if (resourceId == null) // 主号码不存在
        {
            for (CaAccountRes rel : result)// 获取第一个归属用户
            {
                if (rel.getRelationshipType() != null
                        && rel.getRelationshipType().intValue() == EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING)
                {
                    resourceId = rel.getResourceId();
                    break;
                }
            }
        }

        return super.querySingle(CmResource.class, new DBCondition(CmResource.Field.resourceId, resourceId));
    }

    private CmResIdentity queryResIdentity(Long userId, String iden, Integer idenType) throws IMSException
    {
        if (userId == null && CommonUtil.isEmpty(iden))
        {
            return null;
        }

        List<DBCondition> conList = new ArrayList<DBCondition>();
        if (CommonUtil.isValid(userId))
        {
            conList.add(new DBCondition(CmResIdentity.Field.resourceId, userId));

            // lijc3 上海需求，这里只需要返回主号码
            if (ProjectUtil.is_CN_SH() && !CommonUtil.isValid(iden))
            {
                conList.add(new DBCondition(CmResIdentity.Field.identityAttr, EnumCodeDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER));
            }
        }

        if (CommonUtil.isValid(iden))
        {
            conList.add(new DBCondition(CmResIdentity.Field.identity, iden));
        }

        if (idenType != null)
        {
            conList.add(new DBCondition(CmResIdentity.Field.identityType, idenType));
        }

        return querySingle(CmResIdentity.class, conList.toArray(new DBCondition[conList.size()]));
    }

    /**
     * 根据identity,idenType查询CmResIdentity
     */
    private CmResIdentity queryResIdentityByIden(String identity, Integer idenType) throws IMSException
    {
        return queryResIdentity(null, identity, idenType);
    }

    /**
     * 根据imsi查询CmResIdentity
     */
    // public CmResIdentity queryResIdentityByIMSI(String imsi)
    // {
    // return queryResIdentityByIden(imsi, EnumCodeDefine.RESOURCE_IDENTITYTYPE_IMSI);
    // }

    /**
     * 根据imsi查询CmResIdentityList,为了如果数据不合法有多条记录用
     * 
     * @Date 2012-10-29 yangjh : User Story #62889 imsi 跟identity合成一条记录
     */
    public List<CmResIdentity> queryResIdentitysByIMSI(String imsi)
    {
        List<CmResIdentity> identity = query(CmResIdentity.class, new DBCondition(CmResIdentity.Field.relIdentity, imsi),
                new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE));
        return identity;
    }

    // 2011-07-28 wuyujie :增加queryUserIdByPhone()
    public Long queryUserIdByPhone(String phoneId) throws IMSException
    {
        CmResource res = this.queryUserByPhoneId(phoneId);
        if (res == null)
            return null;// vaild

        return res.getResourceId();
    }

    /**
     * 根据 identity 查询CmResIdentity
     * 
     * @throws IMSException
     */
    public List<CmResIdentity> queryResIdentityByIdentity(String identity) throws IMSException
    {
        // CmResIdentity cmResIdentity = new CmResIdentity();
        // cmResIdentity.setIdentity(identity);
        // ljc delete cmResIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);
        List<CmResIdentity> resIdenList = query(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identity, identity));
        return resIdenList;
    }

    public ProductFNList queryUserFNList(Long offerId, Long product_seq, String phoneId)
    {
        List<CoFnCharValue> charValueList = null;
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        ProductFNList productFNList = new ProductFNList();
        List<ProductFN> productFNs = new ArrayList<ProductFN>();
        List<FN> fns = null;
        if (product_seq != null)
        {// 有亲情产品Id
            ProductFN prodFN = new ProductFN();
            FNList fnList = new FNList();
            charValueList = prodCmp.queryFnCharValue(product_seq);
            if (!CommonUtil.isEmpty(charValueList))
            {
                fns = getFNListFromCharValues(charValueList);
                fnList.setFNList_Item(fns.toArray(new FN[fns.size()]));
            }
            else
            {
                // throw IMSUtil.throwBusiException(ErrorCodeDefine.QUERYFN_NO_FRIEND_NUMBERS);
            }
            prodFN.setFNList(fnList);
            prodFN.setPhone_id(phoneId);
            productFNs.add(prodFN);
            productFNList.setProductFN(productFNs.toArray(new ProductFN[productFNs.size()]));
            return productFNList;
        }
        else
        {// 获取该用户订购的产品
         // @2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
            Long userId = queryUserIdByPhone(phoneId);
            List<CoProd> prods = prodCmp.queryProdListByUserId(userId);
            if (!CommonUtil.isEmpty(prods))
            {
                for (Iterator<CoProd> i = prods.iterator(); i.hasNext();)
                {
                    CoProd coProd = (CoProd) i.next();
                    ProductFN prodFN = new ProductFN();
                    FNList fnList = new FNList();
                    Long prodId = coProd.getProductId();
                    charValueList = prodCmp.queryFnCharValue(prodId);
                    if (!CommonUtil.isEmpty(charValueList))
                    {
                        fns = getFNListFromCharValues(charValueList);
                        if (fns != null && fns.size() == 0)
                        {// 有产品 没号码
                            continue;
                        }
                        fnList.setFNList_Item(fns.toArray(new FN[fns.size()]));
                    }
                    else
                    {
                        continue;// 没亲情号码，循环下一个
                        // throw
                        // IMSUtil.createBusiException(ErrorCodeDefine.QUERYFN_NO_FRIEND_NUMBERS);
                    }
                    SProductOrder sOrder = prodCmp.createProductOrder(coProd, phoneId);
                    // @Date 2012-10-10 yugb :增加phoneId
                    prodFN.setPhone_id(phoneId);
                    prodFN.setFNList(fnList);
                    prodFN.setProductOrder(sOrder);
                    productFNs.add(prodFN);
                }
                if (productFNs.size() > 0)
                {
                    productFNList.setProductFN(productFNs.toArray(new ProductFN[productFNs.size()]));
                    return productFNList;
                }
                else
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.QUERYFN_NO_FRIEND_NUMBERS);
                }
            }
            else
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.QUERYFN_NO_FRIEND_NUMBERS);
            }
        }
    }

    /**
     * @Description: 根据objectId, objectType查询Co_fn_char_value表的数据
     * @param objectId
     * @param objectType
     * @return
     * @author: tangjl5
     * @Date: 2012-8-28
     */
    public List<CoFnCharValue> queryFnCharValue(Long objectId, Integer objectType)
    {
        return query(CoFnCharValue.class, new DBCondition(CoFnCharValue.Field.objectId, objectId), new DBCondition(
                CoFnCharValue.Field.objectType, objectType));
    }

    /**
     * 按关系类型查询账户下面的用户 ljc 2011-11-18
     */
    public List<CmResource> queryUsersByAcctID(Long acctId, Integer... relationship)
    {
        List<CaAccountRes> resList = query(CaAccountRes.class, new OrderCondition[] { new OrderCondition(
                CaAccountRes.Field.resourceId) }, null, new DBCondition(CaAccountRes.Field.acctId, acctId), new DBCondition(
                CaAccountRes.Field.relationshipType, relationship, Operator.IN));

        if (CommonUtil.isEmpty(resList))
            return null;
        List<CmResource> userList = new ArrayList<CmResource>();
        for (CaAccountRes dmRes : resList)
        {
            // CmResource user = queryUserByUserID(dmRes.getResourceId());
            // if (user == null)
            // continue;
            // userList.add(user);
            // 2012-08-28 linzt 整理组件方法 采用load3hBean
            try
            {
                CmResource user = context.get3hTree().loadUser3hBean(dmRes.getResourceId()).getUser();
                userList.add(user);
            }
            catch (IMS3hNotFoundException e)
            {
                continue;
            }
        }
        return userList;
    }

    /**
     * @Description: 根据产品ID和特征值表获取FNs
     */
    private List<FN> getFNListFromCharValues(List<CoFnCharValue> charValueList) throws IMSException
    {
        List<FN> fns = new ArrayList<FN>();
        for (Iterator<CoFnCharValue> i = charValueList.iterator(); i.hasNext();)
        {
            CoFnCharValue charValue = (CoFnCharValue) i.next();
            if (charValue.getSpecCharId().intValue() == SpecCodeDefine.FRIENDNUMBER_NUMBER)
            {
                FN fn = new FN();
                fn.setPhone_id(charValue.getValue());// 获取phoneId
                fn.setExpire_date(DateUtil.formatDate(charValue.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                fn.setValid_date(DateUtil.formatDate(charValue.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                fns.add(fn);
            }
        }
        return fns;
    }

    /**
     * @Description: 查询一个账户下的所有归属的用户列表
     * @author : wuyj
     * @date : 2011-9-26
     */
    public List<CmResource> queryUsersByAcctID(Long acctId)
    {
        return queryUsersByAcctID(acctId, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING);
    }

    /**
     * @Description: 查询一个账户下的所有归属的用户Id列表
     * @author : wuyj
     * @date : 2011-9-26
     */
    public List<Long> queryUserIdsByAcctID(Long acctId)
    {
        List<CmResource> result = queryUsersByAcctID(acctId);
        if (CommonUtil.isEmpty(result))
            return null;
        List<Long> ids = new ArrayList<Long>();
        for (CmResource res : result)
        {
            if (res == null)
                continue;
            ids.add(res.getResourceId());
        }
        return ids;
    }

    /**
     * @Description: 查询支付账户下的后付费用户
     * @param acctId
     * @return
     * @author: tangjl5
     * @Date: 2011-12-8
     */
    public List<Long> queryPostUserIdsByBillAcctId(Long acctId)
    {
        List<CmResource> result = queryUserByBillAcctId(acctId);
        if (CommonUtil.isEmpty(result))
            return null;
        List<Long> ids = new ArrayList<Long>();
        for (CmResource res : result)
        {
            if (res == null || res.getBillingType() != EnumCodeDefine.USER_PAYMODE_PREPAID)
                continue;

            ids.add(res.getResourceId());
        }
        List<CaAccountRes> acctResList = query(CaAccountRes.class, new DBCondition[] {
                new DBCondition(CaAccountRes.Field.acctId, acctId),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING) });

        if (CommonUtil.isNotEmpty(acctResList))
        {
            for (CaAccountRes acctRes : acctResList)
            {
                if ((CommonUtil.isValid(acctRes.getResAcctId())) || (ids.contains(acctRes.getResourceId())))
                    continue;

                // CmResource res = queryUserByUserID(acctRes.getResourceId());
                // 2012-08-28 linzt 整理组件方法 采用load3hBean
                try
                {
                    CmResource res = context.get3hTree().loadUser3hBean(acctRes.getResourceId()).getUser();
                    if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
                        ids.add(acctRes.getResourceId());
                }
                catch (IMS3hNotFoundException e)
                {
                	imsLogger.error(e,e);
                }
            }
        }

        return ids;
    }

    /**
     * 查询用户特定服务
     */
    public CmResServCycle queryServCycleByUser(long userId, int serviceId) throws IMSException
    {
        // CmResServCycle servCycle = new CmResServCycle();
        // servCycle.setResourceId(userId);
        // servCycle.setServiceSpecId(serviceId);
        CmResServCycle servCycle = querySingle(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId, userId),
                new DBCondition(CmResServCycle.Field.serviceSpecId, serviceId));
        if (servCycle == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_SERVICE_NOT_EXIST);
        }
        return servCycle;
    }

    /**
     * 查询服务名称 luojb 2012-1-18
     * 
     * @param serviceId
     * @return
     * @throws IMSException
     */
    public String queryServiceName(Integer serviceId) throws IMSException
    {
        // PmServiceSpec service = new PmServiceSpec();
        // service.setServiceSpecId(serviceId);
        PmServiceSpec service = querySingle(PmServiceSpec.class, new DBCondition(PmServiceSpec.Field.serviceSpecId, serviceId));
        return service != null ? service.getName() : null;
    }

    public List<CmResServCycle> querySevCycleByUserIdAndSrvId(Long userId, Integer serviceId) throws IMSException
    {
        List<CmResServCycle> servCycle = null;
        List<DBCondition> cond = new ArrayList<DBCondition>();
        // CmResServCycle where = new CmResServCycle();
        // where.setResourceId(userId);
        cond.add(new DBCondition(CmResServCycle.Field.resourceId, userId));
        if (CommonUtil.isValid(serviceId))
            // where.setServiceSpecId(serviceId);
            cond.add(new DBCondition(CmResServCycle.Field.serviceSpecId, serviceId));
        servCycle = super.query(CmResServCycle.class, cond.toArray(new DBCondition[cond.size()]));

        if (CommonUtil.isEmpty(servCycle))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_SERVICE_NOT_EXIST);
        }
        return servCycle;
    }

    /**
     * 查询 Pm_Asset_Item表查询出信用度科目类型
     * 
     * @param assetItemId
     * @Date 2011-09-17
     * @Author fangyw
     */
    public Integer queryCreditItemTypeByItemId(Integer assetItemId)
    {
        PmAssetItem pmAssetItem = querySingle(PmAssetItem.class, new DBCondition(PmAssetItem.Field.assetItemId, assetItemId));
        if (pmAssetItem == null)
        {
            return null;
        }
        Integer ItemType = pmAssetItem.getItemType();

        return ItemType;
    }

    /**
     * 查询用户扩展表
     * 
     * @author yanchuan 2011-11-1
     */
    public CmResExt queryCmResExt(Long userId)
    {
        // CmResExt resExt = new CmResExt();
        // resExt.setResourceId(userId);
        return this.querySingle(CmResExt.class, new DBCondition(CmResExt.Field.resourceId, userId));
    }

    /**
     * 根据用户ID查询用户信息
     * 
     * @author yangjh 2011-12-3
     * @param userId
     * @return
     */
    public SUser querySUserByUserId(Long userId) throws IMSException
    {
        User3hBean userBean = context.get3hTree().loadUser3hBean(userId);
        String phoneId = userBean.getPhoneId();
        CmResource res = userBean.getUser();
        CmResLifecycle lifeCycle = userBean.getUserLifeCycle();
        CaAccountRes caRes = null;
        try
        {
            caRes = context.get3hTree().loadUser3hBean(userId).getCaAccountRes();
        }
        catch (IMS3hNotFoundException e)
        {
            return null;
        }
        Long acctId = caRes.getAcctId();
        CaAccount ca = context.get3hTree().loadAcct3hBean(acctId).getAccount();
        if (ca == null)
            return null;
        CmCustomer cust = null;
        try
        {
            cust = context.get3hTree().loadAcct3hBean(acctId).getCustomer();
        }
        catch (IMS3hNotFoundException e)
        {
            return null;
        }

        return this.sUserTransform(res, phoneId, caRes, cust, ca, lifeCycle);
    }

    /**
     * 将内部实体CmResource/CmResIdentity转换为接口实体SUser返回给外部接口
     * 
     * @Date 2011-11-24 hunan 将yindm写的封装用户信息的方法提取到组件中。
     */
    public SUser sUserTransform(CmResource res, String phoneId, CaAccountRes acRes, CmCustomer cust, CaAccount ca,
            CmResLifecycle lifeCycle)
    {
        // SUser sUser = new SUser();
        if (res == null)
        {
            return null;
        }
        SUser sUser = new SUser();
        // imsi+imei...
        List<CmResIdentity> resIdentityResult = queryResIdentitysByUserId(res.getResourceId());
        if (CommonUtil.isNotEmpty(resIdentityResult))
        {
            for (CmResIdentity resIdenOther : resIdentityResult)
            {
                // imsi
                if (resIdenOther.getIdentityType() == EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE)
                {
                    sUser.setImsi(resIdenOther.getRelIdentity());
                    if (resIdenOther.getSerialNo() != null)
                    {
                        sUser.setSerial_no(resIdenOther.getSerialNo());
                    }
                }

                if (null != resIdenOther.getResourcePasswd())
                    sUser.setPassword(resIdenOther.getResourcePasswd());
            }
        }
        CmIdentityVsImei resImei = this.getCmIdentityVsImei(res.getResourceId());
        if (null != resImei)
        {
            sUser.setIMEI(resImei.getImei().toString());
        }

        // other fields
        sUser.setRegion_code(CommonUtil.IntegerToShort(res.getRegionCode()));
        sUser.setPhone_id(phoneId);// phone number
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
        }
        BudgetComponent bdgCmp = context.getComponent(BudgetComponent.class);
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        // @Date 2012-10-16 yugb :修改空指针异常
        long budgetVulue = bdgCmp.getBudgetValueByUserId(res.getResourceId());
        if (budgetVulue != 0)
        {
            Integer imsMeasureId = amountCmp.getImsMeasureId(context.get3hTree().loadUser3hBean(res.getResourceId()).getAccount()
                    .getMeasureId());
            Integer origMeasureId = bdgCmp.queryBudgetMeasureID(res.getResourceId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV);
            sUser.setBudget(amountCmp.parseAmount((double) bdgCmp.getBudgetValueByUserId(res.getResourceId()), origMeasureId,
                    imsMeasureId));
            sUser.setMeasure_id(imsMeasureId);
        }
        if (null != res.getCountyCode())
            sUser.setCounty_code(CommonUtil.IntegerToShort(res.getCountyCode()));
        // 2011-11-24 zengxr no credit return for query customer information.
        // long creditLimit = bdgCmp.getUserCreditLimit(acRes.getAcctId(), res.getResourceId());
        // sUser.setCredit((int)creditLimit);
        if (cust != null)
        {
            sUser.setCust_id(cust.getCustId());
        }

        CmResExt resExt = this.getCmResExt(res.getResourceId());
        if (null != resExt)
        {
            ExtendParamList paramList = new ExtendParamList();
            List<ExtendParam> paramArr = new ArrayList<ExtendParam>();
            // @Date 2012-07-12 wangdw5 : #51466 需要重新new一个对象,否则就是一个对象重复ADD
            ExtendParam param = null;
            if (null != resExt.getProp1())
            {
                param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_USER_PHFLAG);
                param.setParam_value(resExt.getProp1());
                paramArr.add(param);
            }
            if (null != resExt.getProp2())
            {
                param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_USER_VOICEMAILFLAG);
                param.setParam_value(resExt.getProp2());
                paramArr.add(param);
            }
            if (null != resExt.getProp3())
            {
                param = new ExtendParam();
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
        // 修改主产品判断ljc
        // List<CoProd> prods = context.getComponent(ProductComponent.class).queryProdListByUserId(res.getResourceId(),
        // SpecCodeDefine.MAIN_PRODUCT);
        // if (!CommonUtil.isEmpty(prods))
        // {
        // sUser.setMain_promotion(prods.get(0).getProductOfferingId());
        // }
        CoProd dmProd = context.getComponent(ProductQuery.class).queryPrimaryProductByUserId(res.getResourceId());
        if (dmProd != null)
        {
            sUser.setMain_promotion(dmProd.getProductOfferingId());
        }
        if (null != res.getRegionCode())
            sUser.setRegion_code(CommonUtil.IntegerToShort(res.getRegionCode()));

        // @Date tangjl5 2012-3-28 解决用户销户后，用户没有与账户的关联关系，未做空判断，导致空指针问题
        if (acRes != null && null != acRes.getTitleRoleId())
            sUser.setTitle_role_id(CommonUtil.IntegerToShort(acRes.getTitleRoleId()));
        // 获取支付帐户 yanchuan 2012-05-09
        if (acRes != null && null != acRes.getResAcctId())
            sUser.setBillable_acct_id(acRes.getResAcctId());
        if (null != res.getResourceSpecId())
            sUser.setUser_type(CommonUtil.IntegerToShort(res.getResourceSpecId()));
        if (ca != null && CommonUtil.isValid(ca.getAcctId()))
        {
            sUser.setAcct_id(ca.getAcctId());
        }

        if (lifeCycle != null)
        {
            SUserLifecycle sUserLifeCycle = new SUserLifecycle();
            sUserLifeCycle.setCreate_date(DateUtil.formatDate(lifeCycle.getCreateDate(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
            sUserLifeCycle.setExpire_date(DateUtil.formatDate(lifeCycle.getExpireDate(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
            sUserLifeCycle.setFraud_flag(lifeCycle.getFrauldFlag());
            sUserLifeCycle.setOs_sts(lifeCycle.getOsSts());
            sUserLifeCycle.setRerating_flag(lifeCycle.getReratingFlag());
            sUserLifeCycle.setSts(lifeCycle.getSts());
            sUserLifeCycle.setUnbilling_flag(lifeCycle.getUnbillingFlag());
            sUserLifeCycle.setUser_request_flag(lifeCycle.getUserrequestFlag());
            sUserLifeCycle.setUser_id(lifeCycle.getResourceId());
            sUserLifeCycle.setValid_date(DateUtil.formatDate(lifeCycle.getValidDate(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
            sUser.setSUserLifeCycle(sUserLifeCycle);
            sUser.setStatus(lifeCycle.getSts().shortValue());

            // @Date 2012-7-6 tangjl5 User Story #50766 CUS_IM_Suser结构变更'
            if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
            {
                BaseLifeCycleComponent lifeCycleCmp = context.getComponent(BaseLifeCycleComponent.class);
                SysGroupCyclePattern groupCyclePatten = lifeCycleCmp.queryCaGroupCyclePattern(res, cust, ca);
                Map<String, Date> datePeriod = context.getComponent(BaseLifeCycleComponent.class)
                        .calculateActiveSuspendDisabelStopTime(lifeCycle.getSts(), lifeCycle.getValidDate(),
                                lifeCycle.getExpireDate(), groupCyclePatten.getPatternId());
                sUser.setSuspend_stop_date((Date) datePeriod.get(ConstantDefine.TOP_UP_SUSPEND_EXPIREDATE));
                sUser.setDisable_stop_date((Date) datePeriod.get(ConstantDefine.TOP_UP_DISABLE_EXPIREDATE));
            }
            // @Date 2012-09-29 yugb :58671 查询用户的时候需要返回active_stop_date
            if (lifeCycle.getSts() == EnumCodeDefine.LIFECYCLE_ACTIVE)
            {
                sUser.setActive_stop_date(lifeCycle.getExpireDate());
            }
        }

        // sUser.setStatus(obj);
        // sUser.setMNP_home_area(obj);
        // sUser.setOs_status(obj);
        // sUser.setDevice_type(obj);
        // sUser.setCompany();
        // sUser.setBill_cycle(re);
        return sUser;
    }

    /**
     * @Description: 查询支付帐户的关联的用户
     * @author yanchuan 2011-11-28
     * @return
     */
    public List<CmResource> queryUserByBillAcctId(Long acctId)
    {
        List<CaAccountRes> resList = query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.acctId, acctId),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));

        if (CommonUtil.isEmpty(resList))
            return null;
        List<CmResource> userList = new ArrayList<CmResource>();
        for (CaAccountRes dmRes : resList)
        {
            // CmResource user = queryUserByUserID(dmRes.getResourceId());
            // 2012-08-28 linzt 整理组件方法 采用load3hBean
            CmResource user = querySingle(CmResource.class, new DBCondition(CmResource.Field.resourceId, dmRes.getResourceId()));
            if (user == null)
                continue;
            userList.add(user);
        }
        return userList;
    }

    /**
     * @Description: 查询一个支付账户下的所有用户Id列表
     * @author : yanchuan
     * @date : 2011-11-28
     */
    public List<Long> queryUserIdsByBillAcctId(Long acctId)
    {
        List<CmResource> result = queryUserByBillAcctId(acctId);
        if (CommonUtil.isEmpty(result))
            return null;
        List<Long> ids = new ArrayList<Long>();
        for (CmResource res : result)
        {
            if (res == null)
                continue;
            ids.add(res.getResourceId());
        }
        return ids;
    }

    /**
     * @Description: 查询支付账户下的后付费用户
     * @param acctId
     * @return
     * @author: tangjl5
     * @Date: 2011-12-8
     */
    public List<Long> queryPostPaidUserIdsByBillAcctId(Long acctId)
    {
        List<CmResource> result = queryUserByBillAcctId(acctId);
        if (CommonUtil.isEmpty(result))
            return null;
        List<Long> ids = new ArrayList<Long>();
        for (CmResource res : result)
        {
            // @Date 2012-3-30 tangjl5 在没有信用度的时候，可变更后付费，或混合付费用户的用户状态
            if (res != null && res.getBillingType() != EnumCodeDefine.USER_PAYMODE_PREPAID)
            {
                ids.add(res.getResourceId());
            }
        }
        return ids;
    }

    /**
     * @Description: 查询多个支付账户下的所有用户数
     * @author : yanchuan
     * @date : 2012-03-09
     */
    public int queryUserNumsByBillAcctIds(List<Long> acctIds)
    {

        int userNum = 0;
        if (CommonUtil.isEmpty(acctIds))
            return userNum;
        DBCondition conds[] = { new DBCondition(CaAccountRes.Field.acctId, acctIds, Operator.IN),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING) };
        return super.queryCount(CaAccountRes.class, conds);
    }

    /**
     * @Description: 查询一个支付账户下的所有用户数
     * @author : yanchuan
     * @date : 2012-03-10
     */
    public int queryUserNumsByBillAcctId(Long acctId)
    {
        if (!CommonUtil.isValid(acctId))
            return 0;
        DBCondition conds[] = { new DBCondition(CaAccountRes.Field.acctId, acctId),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING) };
        return super.queryCount(CaAccountRes.class, conds);
    }

    /**
     * @author: tangjl5
     * @Date: 2011-10-17
     */
    public CmIdentityVsImei getCmIdentityVsImei(Long userId) throws IMSException
    {
        CmIdentityVsImei update = new CmIdentityVsImei();
        update.setResourceId(userId);
        CmIdentityVsImei result = querySingle(CmIdentityVsImei.class,
                new DBCondition(CmIdentityVsImei.Field.resourceId, update.getResourceId()));
        return result;
    }

    /**
     * @Description: 查询用户的附加信息
     * @author: tangjl5
     * @Date: 2011-10-17
     */
    public CmResExt getCmResExt(Long userId)
    {
        // 查询CM_RES_EXT表
        // CmResExt resExt = new CmResExt();
        // resExt.setResourceId(userId);
        CmResExt cmResExt = querySingle(CmResExt.class, new DBCondition(CmResExt.Field.resourceId, userId));
        return cmResExt;
    }

    /**
     * @descrption:为帐管提供的查询封装，只提供帐管所需的自动，不用封装结构实体中的每一个字段
     * @author hunan
     * @date 2011-12-22
     * @param res
     * @param resIdentity
     * @param coProd
     * @return
     */
    public SUser sUserTransform4Billing(CmResource res, CmResIdentity resIdentity, CoProd coProd, CmResLifecycle lifecycle,
            Long belongAcct, Long billAcct)
    {
        if (res == null || resIdentity == null)
        {
            return null;
        }

        SUser user = new SUser();
        user.setUser_id(res.getResourceId());
        if (res.getBillingType() != null)
        {
            user.setPayment_mode(res.getBillingType().shortValue());
        }
        if (coProd != null)
        {
            user.setMain_promotion(coProd.getProductOfferingId());
        }

        user.setCreate_date(DateUtil.formatDate(res.getCreateDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        user.setActive_date(DateUtil.formatDate(res.getActiveDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        // @Date 2012-4-9 tangjl5 增加返回expire_date
        user.setExpire_date(DateUtil.formatDate(res.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        if (res.getBrandId() != null)
        {
            user.setBrand(res.getBrandId().shortValue());
        }
        if (res.getResSegment() != null)
        {
            user.setUser_segment(res.getResSegment().shortValue());
        }

        // if(resIdentity.getIdentityType() != null )
        // {
        // user.set(resIdentity);
        // }

        if (billAcct != null)
        {
            user.setBillable_acct_id(billAcct);
        }

        if (belongAcct != null)
        {
            user.setAcct_id(belongAcct);
        }

        user.setPhone_id(resIdentity.getIdentity());

        SUserLifecycle sLifeCycle = this.sUserLifeCycleTransform(lifecycle);
        user.setSUserLifeCycle(sLifeCycle);
        return user;
    }

    private SUserLifecycle sUserLifeCycleTransform(CmResLifecycle lifecycle)
    {
        SUserLifecycle sLifeCycle = new SUserLifecycle();
        if (lifecycle == null)
        {
            return sLifeCycle;
        }
        sLifeCycle.setUser_id(lifecycle.getResourceId());
        sLifeCycle.setSts(lifecycle.getSts());
        sLifeCycle.setOs_sts(lifecycle.getOsSts());
        sLifeCycle.setFraud_flag(lifecycle.getFrauldFlag());
        sLifeCycle.setUnbilling_flag(lifecycle.getUnbillingFlag());
        sLifeCycle.setUser_request_flag(lifecycle.getUserrequestFlag());
        sLifeCycle.setRerating_flag(lifecycle.getReratingFlag());
        sLifeCycle.setCreate_date(DateUtil.formatDate(lifecycle.getCreateDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        sLifeCycle.setValid_date(DateUtil.formatDate(lifecycle.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        sLifeCycle.setExpire_date(DateUtil.formatDate(lifecycle.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

        return sLifeCycle;
    }

    /**
     * 根据客户名称查询客户信息,调用时做了限制，必定会传一个值 wukl 2012-1-9
     * 
     * @param firstName
     * @param middleName
     * @param familyName
     * @return
     */
    public List<CmIndividualName> queryIndividualNames(String firstName, String middleName, String familyName)
    {
        List<DBCondition> cond = new ArrayList<DBCondition>();
        if (CommonUtil.isValid(firstName))
            // where.setPreferredGivenName(firstName);
            cond.add(new DBCondition(CmIndividualName.Field.preferredGivenName, firstName));
        if (CommonUtil.isValid(familyName))
            // where.setFamilyNames(familyName);
            cond.add(new DBCondition(CmIndividualName.Field.familyNames, familyName));
        if (CommonUtil.isValid(middleName))
            // where.setMiddleNames(middleName);
            cond.add(new DBCondition(CmIndividualName.Field.middleNames, middleName));

        return query(CmIndividualName.class, cond.toArray(new DBCondition[cond.size()]));
    }

    public List<CmResIdentity> queryResIdentitysByUserId(Long userId)
    {
        return query(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId, userId));
    }

    /**
     * @Description: 根据用户Id查询服务列表
     * @param resId
     * @return
     * @author: tangjl5
     * @Date: 2012-6-12
     */
    public List<CmResServCycle> queryResServCycle(Long resId)
    {
        return query(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId, resId));
    }

    /**
     * @author yanchuan 2012-3-9
     * @describe 获取支付账户及该账户下的所有子账户的用户总数
     * @param offerId
     * @return
     */
    public int getUserNums(Long acctId)
    {
        int userNums = 0;
        List<Long> acctIds = context.getComponent(AccountRelationComponent.class).queryAllSubCa(acctId);
        acctIds.add(acctId);
        if (CommonUtil.isEmpty(acctIds))
            return userNums;
        userNums = queryUserNumsByBillAcctIds(acctIds);
        return userNums;
    }

}
