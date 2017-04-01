package com.ailk.ims.component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jef.database.Condition.Operator;

import com.ailk.easyframe.sdl.MImsSyncApp.IImsSyncAppInt;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.InnerClass.MeasureInfo;
import com.ailk.ims.common.InnerClass.ProdCycleInfo;
import com.ailk.ims.complex.ProductOrderComplex;
import com.ailk.ims.component.helper.GroupHelper;
import com.ailk.ims.component.helper.ProdHelper;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.CacheQuery;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.component.query.GroupQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.BusiCodeDefine;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.Group3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLastIcs;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdBundleComposite;
import com.ailk.openbilling.persistence.cust.entity.CoProdCellInfo;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdMapping;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.cust.entity.ImsBarServiceSync;
import com.ailk.openbilling.persistence.cust.entity.ImsIcsSync;
import com.ailk.openbilling.persistence.cust.entity.ImsOrderProduct;
import com.ailk.openbilling.persistence.cust.entity.ImsProdOrderSync;
import com.ailk.openbilling.persistence.cust.entity.ImsSharProdSync;
import com.ailk.openbilling.persistence.cust.entity.ImsSonbrMapping;
import com.ailk.openbilling.persistence.cust.entity.ImsUpsellReq;
import com.ailk.openbilling.persistence.imsinner.entity.SLifeCycleByAcctChgReq;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SCallScreenNo;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOper;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOperList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderResult;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderResultList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SimItem;
import com.ailk.openbilling.persistence.imssdl.entity.SProdNotify;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmProdBusiLimit;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAttribute;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferComposite;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferMapping;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharUse;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharValue;
import com.ailk.openbilling.persistence.pm.entity.PmRentDeductAction;
import com.ailk.openbilling.persistence.sys.entity.SysCountry;
import com.ailk.openbilling.persistence.sys.entity.SysTimeZoneDetail;

/**
 * @Description: 产品组件，用于定义跟产品操作相关的各种方法
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-1 2011-07-28 wuyujie :组合产品只实例化一条记录 <br>
 *       2011-7-29 王金涛：修改createResourceServiceCycle方法中的update方法<br>
 *       2011-07-29 zengxr co_prod_price_param keep the same date with co_prod <br>
 *       2011-07-30 liuyang 增加了isProdExistWhithModify方法，判断产品是否存在<br>
 *       2011-08-02 hunan 将operateProductOrder方法增加了返回值SProductOrderResultList<br>
 * @Date 2011-08-08ljc 新增 createProductOrder(CoProd prod,String phoneId)
 * @Date 2011-08-08 tangjl5 修改解析出产品账期周期方法，添加若接口传入的账期不存在则是否账期表中的默认配置<br>
 * @Date 2011-08-09 ljc 根据销售品ID和busi_Flag查询产品列表
 * @Date 2011-08-11 ljc 根据prodId,specCode,value查询CoProdCharValue List
 * @date 2011-08-16 yangyang 根据userId或phoneId查询subscriber's product status
 * @Date 2011-08-16 wangyh3 createProductOrder 增加传入 user_id 的分支
 * @Date 2011-08-18 zengxr check there main promotion delete
 * @Date 2011-08-19 zengxr check for billing type<br>
 * @Date 2011-08-24 wangjt add: queryPriceRuleIdByProdId()<br>
 * @Date 2011-10-31 tangjl5用户级产品订购时，判断用户的billing_typ与产品的billing_type是否匹配<br>
 * @Date 2012-03-19 lijc3 extendprod增加空判断 2012-03-19 lijc3 预付费预扣周期内退订清理免费资源clearFreeResourceByDeleteProd(dmProd.getProductId(),
 *       payAcctIdMap.get(dmProd.getObjectId())); 2012-03-19 lijc3 更换主产品的时候需要更改周期性免费资源为一次性免费资费 covertFreeResByChangeMainProd(Long
 *       productId,Long acctId)
 * @Date 2012-03-19 luojb 修改产品定价计划，若新的pricePlanId为空，设置为0（On_Site Defect #41105）
 * @Date 2012-03-19 lijc3 extend增加返回值
 * @Date 2012-03-20 luojb 上发通知处理ts，busi_code设置
 * @Date 2012-03-23 luojb 主产品失效可以新订购主产品，新增、更换主产品的失效时间没有传入则从销售品配置计算（与普通产品一样）
 * @Date 2012-03-26 lijc3 修改balanceType 对用户有效期的影响
 * @Date 2012-03-26使用偏移地时间减系统时间 lijc3
 * @Date 2012-03-27 luojb 增加更换主产品通知,产品通知的方法统一移到operateProduct方法里调用
 * @Date 2012-03-29 wangjt 修改isEmpty方法用来判断对象的问题
 * @Date 2012-4-2 luojb 增加主产品12000特征值
 * @Date 2012-04-03 zengxr real busi spec id for one time fee & reward.
 * @Date 2012-4-6 tangjl5 #43383 解决修改MultiSim,Fax_num的产品特质值，未修改CM_RES_IDENTITY记录的问题
 * @date 2012-04-07 luojb 下周期、下月、第二天生效ir产品的生效时间都参考漫游地
 * @Date 2012-04-09 lijc3 修改
 * @Date 2012-04-09 lijc3 调用abm接口增加日志输出
 * @Date 2012-04-12 lijc3 修改付费模式为2的时候的处理 2012-04-13 luojb 增加修改产品时间的方法
 * @Date2012-04-13 lijc3 验证付费模式逻辑修改
 * @Date 2012-04-16 lijc3 增加创建object_id,object_type
 * @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
 * @Date 2012-04-17 lijc3 替换主产品优化
 * @Date 2012-04-19 luojb 增加checkAmend方法
 * @Date 2012-04-20 yangjh amendProdDate 方法中update方法修改
 * @Date 2012-04-25 lijc3 CO_PROD_INV_CA表删除，删除相应方法
 * @Date 2012-04-26 lijc3 add CoProd loadProd(Long prodId,Long userId,Long acctId)
 * @Date 2012-04-26 lijc3查询产品加上分表字段
 * @Date 2012-04-26 lijc3 增加产品派生的方法
 * @Date 2012-04-26 lijc3 增加打包产品处理
 * @Date 2012-04-30 lijc3 亲情号码产品 还要退订CO_FN_CHAR_VALUE,split产品还需要退订CO_SPLIT_CHAR_VALUE
 * @Date 2012-05-03 lijc3 二次议价参数设置price_plan_id
 * @Date 2012-05-07 lijc3 上海添加默认特征值不抛异常
 * @Date 2012-05-10 wangdw5 : 新增的sim卡号需要判断是否已经被别的用户使用
 * @Date 2012-05-11 lijc3 修改动态小区特征值规格值
 * @Date 2012-05-12 luojb 判断negotiation_flag标志，是否参考二次议价（0不议价 1 议价）
 * @Date 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
 * @Date 2012-05-18 zhangzj3 [45390]MultiSIM Serial No, 增加处理sim卡serial no
 * @Date 2012-5-19 lijc3 增加对CO_PROD_VALID的操作
 * @Date 2012-05-22 lijc3修改产品特征值增加空判断
 * @Date 2012-5-23 yangjh User Story #45396 deleteProdOrder里面增加insertCmResLastIcs方法，删除的时候把callScreen信息保存到CmResLastIcs表
 *       createProductCharValues里面增加CmResLastIcs表中信息恢复到charValue表
 * @Date 2012-5-24 yangjh User Story #45738 普通账户订购产品默认的billing_type是后付费 single balance账户订购的产品默认的billing_type是预付费
 * @Date 2012-05-30 lijc3 增加空指针判断
 * @Date 2012-06-01 yangjh story：46789 IMS_RealStory_HomeZone销售品级的特征值的固定值实例化
 * @Date 2012-06-04 yugb [47121]Fixed expire date of offering
 * @Date 2012-06-05 yangjh story：44572 账户级订购产品以及产品退订未来失效同步到产品退订表
 * @Date 2012-06-06 wangdw5 : [47115]Community Promotion
 * @Date 2012-6-18 yangjh task:45396 增加是否是黑白名单的判断 并对插入方法修改
 * @Date 2012-06-21 lijc3 群产品需要设置billing_type
 * @Date 2012-6-28 tangjl5 #49609 so_product_id 修改为string 类型
 * @Date 2012-06-28 yangjh checkAmend修改为公用方法 并增加validDate的判断
 *                         bug:49083 生效时间修改增加限制 将原先的一部分代码移到amendProdDatebusiBean里面
 * @Date 2012-07-04 yangjh	 bug:50104 callScreen增加黑白名单最大数量限制
 * @Date 2012-07-04 yangjh coprodcellinfo增加object_id和objectType的获取
 * @Date 2012-07-04 yangjh CoProdValid增加object_id，object_type的实例化
 * @Date 2012-07-05 yangjh story:49802 IR-OUT产品增加特征值入库，12203特征值存储时差
 * @Date 2012-07-05 yangjh story：50685 增加国家name的入库上发
 * @date 2012-07-06 luojb 主动退订产品增加标志#49827
 * @Date 2012-07-09 yangjh story:50254 黑白名单增加最大数量限制
 * @Date 2012-07-10 yangjh story：50778 增加operType=6的处理
 * @date 2012-07-10 luojb #50229 删除co_prod_valid,  co_prod 增加prod_valid_date，prod_expire_date
 * @Date 2012-07-17 wangdw5:#51867 处理 CoProdInvObj空指针,直接取CoProd的ObjectId和ObjectType
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @Date 2012-07-24 tangjl5 :Story # 51379 若产品剩余的有效期为短周期，则通知扣费
 * @date 2012-07-25 luojb #51918 特征值默认优先级处理
 * @Date 2012-07-25 wangdw5 : #52844 dmProdList空不执行以下逻辑
 * @Date 2012-07-26 yangjh : 增加whiteProd，blackProd为空判断 避免空指针
 * @Date 2012-07-27 yangjh :  bug：53000 SysTimeZoneDetail表有变更 SysTimeSegDtl不用
 * @Date 2012-08-03 yangjh : ConstantDefine.IR_TIME_ZONE_DETAIL
 * @Date 2012-08-03 linzt : [52249]changeGroupProduct支持offer_id删除修改
 * @Date 2012-08-06 sunpf3 : [54626]IMS_Req_Promotion life cycle management.
 *                          增加验证销售品失效的方法：checkProductOffering
 *@Date 2012-08-07 yugb :增加查询产品固费的方法 callCalcAuthRecurFee
 *@date 2012-08-18 luojb #56412 有传入valid_date才验证
 *@Date 2012-08-23 yangjh : 时区计算内系统不考虑夏令时，只去sys_country的时区
 *@Date 2012-10-11 wukl 上海项目不需要上发ImsSharProdSync
 *@date 2012-10-23 luojb On_Site Defect #62372 修改判断方式，community产品单独处理
 *@Date 2012-10-29 yangjh : User Story #62685  RESOURCE_IDENTITYTYPE_SIMI->RESOURCE_IDENTITYTYPE_PHONE
 *@date 2012-11-01 luojb On_Site Defect #63324 赠送一次性免费资源的生效失效时间要按配置算
 */

public class BaseProductComponent extends ProductQuery
{

    public BaseProductComponent()
    {
    }

    /**
     * 获取产品对象，不存在报异常
     */
    public CoProd loadProd(long prodId) throws IMSException
    {
        CoProd coProd = this.queryProd(prodId);
        if (coProd == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, prodId);
        }
        return coProd;
    }

    public CoProd loadProd(Long prodId, Long objectId) throws IMSException
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(
                CoProd.Field.objectId, objectId));
        CoProd coProd = mergeProd(prodList);
        if (coProd == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, prodId);
        }
        return coProd;
    }

    /**
     * @Description 根据产品id，用户id或者账户编号查询产品，userId,acctId不能同时为空，优先使用userId
     * @Author lijingcheng
     * @param prodId
     * @param userId
     * @param acctId
     * @return
     */
    public CoProd loadProd(Long prodId, Long userId, Long acctId)
    {
        if (userId != null)
        {
            return loadProd(prodId, userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        }
        else if (acctId != null)
        {
        	//代码优化
            return loadProd(prodId, acctId, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        }
        throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "userId,acctId");
    }

    public CoProd loadProd(Long prodId, Long objectId, Integer objectType) throws IMSException
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(
                CoProd.Field.objectId, objectId), new DBCondition(CoProd.Field.objectType, objectType));
        CoProd coProd = mergeProd(prodList);
        if (coProd == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, prodId);
        }
        return coProd;
    }

    /**
     * wangjt 2011-10-1 ljc change CmResource dmUser
     */
    public Object[] getObjectIdType(SProductOrder prod, CaAccount dmAccount, CmResource dmUser)
    {
        Long objectId = null;
        int objectType = -1;

        if (this.isAccountLevelOrderProduct(prod))
        {
            objectId = dmAccount.getAcctId();
            Integer accountClass = dmAccount.getAccountClass();

            if (accountClass.shortValue() == EnumCodeDefine.ACCOUNT_CLASS_GCA)
            {
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_GCA;// 使用类型为虚账户
            }
            else if (accountClass.shortValue() == EnumCodeDefine.ACCOUNT_CLASS_CA)
            {
                // 如果普通账户订购的是群产品，设置object_type=2
                Long offerId = prod.getOffer_id();
                int busiFlag = this.queryBusiflag(offerId.intValue());
                if (GroupHelper.isGroupProd(busiFlag))
                {
                    objectType = EnumCodeDefine.PROD_OBJECTTYPE_GCA;
                }
                else
                {
                    objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;// 使用类型为账户
                }
            }
        }
        else
        {
            objectId = dmUser.getResourceId();
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;// 使用类型为用户
        }

        return new Object[] { objectId, Integer.valueOf(objectType) };
    }

    /**
     * 判断账户（群账户）或者用户是否订购了黑白名单产品 ljc 2011-10-28
     */
    public boolean userOrGroupHasOrderICSProd(Long objectId, int objectType)
    {
        List<CoProd> prods = null;
        if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            prods = queryProdListByUserId(objectId, SpecCodeDefine.MCS_NUMBER);
        }
        else if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
        {
            prods = queryProdListByAcctIdAndFlag(objectId, SpecCodeDefine.MCS_NUMBER);
        }
        else
        {
            prods = queryProdListByGroupId(objectId, SpecCodeDefine.MCS_NUMBER);
        }
        return CommonUtil.isEmpty(prods);
    }


    /**
     * lijc3 2012-5-19 修改CO_PROD_VALID
     * luojb 2012-07-13 #50229
     * @param prodId
     * @param expireDate
     */
    public void modifyProdValid(Long prodId, Date expireDate)
    {
        CoProd valid = new CoProd();
        valid.setProdExpireDate(expireDate);
        updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId, prodId));
    }

    /**
     * 订购产品，resServList参数为存放产品服务对象用
     * 
     * @author yanchuan 2011-10-14
     */
    public CoProd createProductOrder(SProductOrder prod, CmCustomer dmCust, CaAccount dmAccount, CmResource dmUser,
            List<CmResServCycle> resServList) throws IMSException
    {
        // 使用关系 账户级或者用户级订购
        Object[] objectIdType = this.getObjectIdType(prod, dmAccount, dmUser);
        Long objectId = (Long) objectIdType[0];
        int objectType = (Integer) objectIdType[1];

        long busiFlag = queryBusiflag(prod.getOffer_id().intValue());
        // 解析账期，获得生效失效时间
        CoProdBillingCycle dmBillCycle = context.getComponent(ProductCycleComponent.class).parseProdBillingCycle(prod);
        // 产品规格
        // ljc 10.28 添加指定业务判断,如黑白名单产品只能有一个
        if (busiFlag == SpecCodeDefine.MCS_NUMBER && !userOrGroupHasOrderICSProd(objectId, objectType) && ProjectUtil.is_TH_AIS())
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.MCS_PRODUCT_IS_EXIST);
        }
        // 获取价格扩展参数 ，传入定价计划计算表达式
        List<CoProdPriceParam> pps = getPriceParamList(prod);

        PmProductOffering dmOffering = super.queryPmProductOfferingByOfferId(Integer.valueOf(prod.getOffer_id().intValue()));

        // 要订购升级产品，需要去临时表里面查询相关记录 如果是主产品升级，前面就判断了
        if (dmOffering.getIsMain() != EnumCodeDefine.PRODUCT_MAIN && busiFlag == SpecCodeDefine.UP_SELL)
        {
            checkUpSell(objectId, objectType, prod.getOffer_id().intValue());
            CoProdPriceParam param = new CoProdPriceParam();
            param.setParamId(SpecCodeDefine.UPSELLING_FLAG);
            param.setParamValue(String.valueOf(1));
            pps.add(param);
        }
        // 账户级订购该字段为空
        Integer mainOfferId = null;
        if (ProjectUtil.is_CN_SH())
        {
            if (dmOffering.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
            {
                mainOfferId = dmOffering.getProductOfferingId();
            }
            else
            {
                if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                {
                    CoProd mainProd = this.queryPrimaryProductByUserId(objectId);
                    if (mainProd != null)
                    {
                        mainOfferId = mainProd.getProductOfferingId();
                    }
                }
            }
        }

        // 获取特征值 ，传入定价计划计算表达式
        List<CoProdCharValue> charValues = getCharValueList(prod);

        boolean overridFlag = false;
        Integer pricingPlanId = null;
        // 二者失效时间差所造成的第二个定价计划(可以理解为产品本身没有override关系的定价计划)
        Integer newPriceingPlanId = null;
        CoProd ovrrideProd = null;
        // 判断是否存在override
        Integer tarOffer = queryOverrideOfferIdByOrgOfferId(prod.getOffer_id().intValue());
        // caohm5--
        // 作为参数传queryPricePlanId
        Object objList = context.getExtendParam(ConstantDefine.SON_OFFERING_ID_LIST + prod.getProduct_id());
        Long offeringPlan = (Long) context.getExtendParam(ConstantDefine.SON_PRODUCT_IN_PARCKET_LIST + prod.getOffer_id()
                + prod.getParent_serv_product());
        // --caohm5
        if (tarOffer != null)
        {
            // 查询是否订购了有override关系的产品
            List<CoProd> prodList = queryProdByUserIdAndOfferId(objectId, objectType, tarOffer);
            // 订购了 那么需要将override的销售品传入作为定价计划参数
            if (!CommonUtil.isEmpty(prodList))
            {
                // 取产品实例id最小的一个进行override
                ovrrideProd = prodList.get(0);
                // 没有override过
                if (CommonUtil.isEmpty(this.queryCharValue(ovrrideProd.getProductId(), SpecCodeDefine.OVERRIDE_PRODUCT_ID)))
                {
                    // 将标志设置为true
                    overridFlag = true;
                    // 获取有override关系的定价计划
                    pricingPlanId = this.queryPricePlanId(prod.getOffer_id(), dmCust, dmAccount, dmUser, pps, tarOffer,
                            charValues, mainOfferId, objList, offeringPlan);
                }
                else
                {
                    // 设置overrideProd为空 不进行override
                    ovrrideProd = null;
                    pricingPlanId = this.queryPricePlanId(prod.getOffer_id(), dmCust, dmAccount, dmUser, pps, null, charValues,
                            mainOfferId, objList, offeringPlan);
                }
                // 后续还要将这个产品实例化的ID保存到对方的特征值中
            }
            else
            {
                pricingPlanId = this.queryPricePlanId(prod.getOffer_id(), dmCust, dmAccount, dmUser, pps, null, charValues,
                        mainOfferId, objList, offeringPlan);
            }
        }
        else
        {
            pricingPlanId = this.queryPricePlanId(prod.getOffer_id(), dmCust, dmAccount, dmUser, pps, null, charValues,
                    mainOfferId, objList, offeringPlan);
        }
        // 产品本身没有override的定价计划
        if (ovrrideProd != null)
        {
            newPriceingPlanId = this.queryPricePlanId(prod.getOffer_id(), dmCust, dmAccount, dmUser, pps, null, charValues,
                    mainOfferId, objList, offeringPlan);
        }
        imsLogger.info("query pricingPlanId没有override的定价计划***********************+newPriceingPlanId=" + newPriceingPlanId);
        imsLogger.info("query pricingPlanId***********************+pricingPlanId=" + pricingPlanId);
        if(pricingPlanId == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.PRICE_PLAN_NOT_EXIST,prod.getOffer_id());
        }
        // 产品创建
        CoProd dmProd = insertCoProd(prod, pricingPlanId, dmBillCycle, dmUser, busiFlag, newPriceingPlanId, ovrrideProd,
                objectId, objectType);
        // 2012-06-05 yangjh story：44572 账户级订购产品同步到产品退订表
        if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
        {
            this.insertImsSharProdSync(dmProd, dmProd.getExpireDate());
        }

        // 普通产品替换 缓存新订购的产品的生效时间 作为退订产品的失效时间
        if (context.getExtendParam(ConstantDefine.REPLACE_COMMON_PROD) != null
                && dmProd.getIsMain() == EnumCodeDefine.PRODUCT_COMMON)
        {
            context.addExtendParam(ConstantDefine.REPLACE_PROD_ADD_VALID_DATE, dmProd.getValidDate());
        }
        if (context.getExtendParam(ConstantDefine.REPLACE_MAIN_PROD) != null && dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
        {
            context.addExtendParam(ConstantDefine.REPLACE_MAIN_PROD_ADD_VALID_DATE, dmProd.getValidDate());
        }

        long prodId = dmProd.getProductId();

        IMS3hBean bean = context.getMain3hBean();

        // 判断ovrride标志
        if (overridFlag)
        {
            modifyOverrideProdPricePlanId(ovrrideProd, bean, dmProd);
        }


        // 实例化产品规格特征表

        if (dmProd.getBusiFlag() == SpecCodeDefine.HOMEGATE)
        {
            createCoProdCellInfo(prod.getParam_list(), dmProd, busiFlag);
        }
        else
        {
            createProductCharValues(prod.getParam_list(), dmProd, busiFlag,
                    DateUtil.formatDate(dmProd.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS),
                    DateUtil.formatDate(dmProd.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }

        // 实例化产品二次议价价格参数

        // 判断negotiation_flag标志，是否参考二次议价（0不议价 1 议价）luojb 2012-05-12
        boolean isNegotiation = isNegotiation(dmOffering.getProductOfferingId());
        if (isNegotiation)
        {
            // 实例化免费试用期二次计价参数 luojb 2012-02-28s
            createTrialPriceParam(prod, dmProd);

            if (prod.getReguid_price_param() != null
                    && !CommonUtil.isEmpty(prod.getReguid_price_param().getExtendParamList_Item()))
            {
                // 2011-07-29 zengxr keep the same date with co_prod
                createReguidPrice(prod.getReguid_price_param(), prodId, dmProd);
            }
        }
        // 实例化产品账期 dmBillCycle中存有cycleUnit cycleType
        if (dmProd.getBusiFlag() == SpecCodeDefine.IR_OUT)
        {
            dmBillCycle.setValidDate(dmProd.getValidDate());
            dmBillCycle.setExpireDate(dmProd.getExpireDate());
        }
        Long acctId = null;
        if (dmUser != null)
        {
            User3hBean userBean = context.get3hTree().loadUser3hBean(dmUser.getResourceId());
//            acctId = userBean.getBillAcctId();
        }
        else
        {
            acctId = context.getComponent(AccountQuery.class).queryBillAcctIdByAcct(dmAccount);
            // 2012-3-26 luojb 账户级产品，把支付账户到产品结构中，以便鉴权时取用
            prod.setBillable_acct_id(acctId);
        }

        // 增加产品账期
//        context.getComponent(ProductCycleComponent.class).createProdBillCycle(dmBillCycle, dmProd, acctId, dmUser, objectType);
        
        // 创建父子关系
        if (prod.getParent_serv_product() != null)
        {
            createPackageProduct(dmProd, prod.getParent_serv_product());
        }

        return dmProd;
    }

    /**
     * 是否议价 luojb 2012-5-12
     * 
     * @param offeringId
     * @return
     */
    private boolean isNegotiation(Integer offeringId)
    {
        PmProdBusiLimit busiLimit = queryProdBusiLimit(offeringId);
        if (busiLimit == null || busiLimit.getNegotiationFlag() == null)
            return true;
        return busiLimit.getNegotiationFlag().intValue() != 0;
    }
    
    /**
     * 订购次数限制
     * luojb 2012-11-6
     * @param offeringId
     * @param objectId
     * @param objectType
     */
    private void checkProdOrderLimit(Long offeringId,Long objectId,Integer objectType)
    {
        PmProdBusiLimit busiLimit = queryProdBusiLimit(CommonUtil.long2Int(offeringId));
        if (busiLimit == null || !CommonUtil.isValid(busiLimit.getMaxOrderTimesLimit()))
            return ;
        int limit = busiLimit.getMaxOrderTimesLimit();
        List<CoProd> prodList = query(CoProd.class,
                new DBCondition(CoProd.Field.objectId,objectId),
                new DBCondition(CoProd.Field.objectType,objectType),
                new DBCondition(CoProd.Field.productOfferingId,offeringId));
        if(CommonUtil.isEmpty(prodList))
            return ;
        Set<Long> prodIds = new HashSet<Long>();
        for(CoProd prod:prodList)
        {
            prodIds.add(prod.getProductId());
        }
        if(prodIds.size() >= limit)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.ORDER_PROD_LIMIT,offeringId);
        }
    }

    private void createTrialPriceParam(SProductOrder prod, CoProd dmProd)
    {
        PmProductOfferAttribute attr = context.getComponent(CacheQuery.class).queryPmProductOfferAttribute(
                dmProd.getProductOfferingId());
        if (attr != null)
        {
            Integer trialType = attr.getProbationCycleType();
            Integer trialUnit = attr.getProbationCycleUnit();
            Integer tiralMode = attr.getProbationEffectMod();

            if (tiralMode == null)
                return;
            BaseProductNotifyComponent pnc = context.getComponent(BaseProductNotifyComponent.class);
            Date trialValidDate = dmProd.getValidDate();
            if (tiralMode.intValue() == EnumCodeDefine.PROD_NOTIFY_TRIAL_MODE_OFFSET)
            {
                Integer offType = attr.getOffsetCycleType();
                Integer offUnit = attr.getOffsetCycleUnit();

                trialValidDate = pnc.calculateMoveDate(trialValidDate, offType, offUnit);
                if (trialValidDate == null)
                {
                    imsLogger.debug(
                            "***** OffsetCycleType[" + offType + "] OffsetCycleUnit[" + offUnit + "] ProbationEffectMod["
                                    + tiralMode + "] no free trial config for offering[" + dmProd.getProductOfferingId() + "].");
                    return;
                }
            }

            Date trialExpireDate = pnc.calculateMoveDate(trialValidDate, trialType, trialUnit);
            if (trialExpireDate == null)
            {
                imsLogger
                        .debug("***** ProbationCycleType[" + trialType + "] ProbationCycleUnit[" + trialUnit
                                + "] ProbationEffectMod[" + tiralMode + "] no free trial period config for offering["
                                + dmProd.getProductOfferingId() + "]");
                return;
            }

            if (!trialValidDate.before(dmProd.getExpireDate()))
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_TRIAL_VALID_ERROR, trialValidDate, dmProd.getExpireDate());
            }

            // 实例化免费试用二次计价参数

            ExtendParam param = new ExtendParam();
            param.setParam_name(CommonUtil.int2String(SpecCodeDefine.PRICE_PARAM_ID_RC_BASE_FEE));
            param.setParam_value("0");// 免费
            param.setValid_date(IMSUtil.formatDate(trialValidDate));
            param.setExpire_date(IMSUtil.formatDate(trialExpireDate));

            ExtendParamList prodPriceParamList = prod.getReguid_price_param();
            if (prodPriceParamList == null)
            {
                prodPriceParamList = new ExtendParamList();
                prod.setReguid_price_param(prodPriceParamList);
            }
            ExtendParam[] params = prodPriceParamList.getExtendParamList_Item();
            if (CommonUtil.isEmpty(params))
            {
                params = new ExtendParam[1];
                params[0] = param;
                prodPriceParamList.setExtendParamList_Item(params);
            }
            else
            {
                ExtendParam[] newParams = new ExtendParam[params.length + 1];
                for (int i = 0; i < params.length; i++)
                {
                    newParams[i] = params[i];
                }
                newParams[params.length] = param;
                prodPriceParamList.setExtendParamList_Item(newParams);
            }
        }
    }

    /**
     * 获取获取特征值 ，传入定价计划计算表达式
     */
    private List<CoProdCharValue> getCharValueList(SProductOrder prod)
    {
        List<CoProdCharValue> charValues = new ArrayList<CoProdCharValue>();
        if (prod.getParam_list() != null && !CommonUtil.isEmpty(prod.getParam_list().getSProductParamList_Item()))
        {
            SProductParam[] prodParams = prod.getParam_list().getSProductParamList_Item();
            for (SProductParam sp : prodParams)
            {
                CoProdCharValue value = new CoProdCharValue();
                value.setSpecCharId(sp.getParam_id());
                value.setValue(sp.getParam_value());
                charValues.add(value);
            }
        }
        return charValues;
    }

    /**
     * 获取价格扩展参数 ，传入定价计划计算表达式
     */
    private List<CoProdPriceParam> getPriceParamList(SProductOrder prod)
    {
        List<CoProdPriceParam> pps = new ArrayList<CoProdPriceParam>();
        if (prod.getReguid_price_param() != null && !CommonUtil.isEmpty(prod.getReguid_price_param().getExtendParamList_Item()))
        {
            ExtendParam[] params = prod.getReguid_price_param().getExtendParamList_Item();
            for (ExtendParam ep : params)
            {
                CoProdPriceParam price = new CoProdPriceParam();
                if (CommonUtil.isEmpty(ep.getParam_name()))
                {
                    continue;
                }
                price.setParamId(Integer.parseInt(ep.getParam_name()));
                price.setParamValue(ep.getParam_value());
                pps.add(price);
            }
        }
        return pps;
    }

    /**
     * 检验是否产品升级订购 ljc
     * 
     * @param objectId
     * @param objectType
     * @param offerId
     */
    private ImsUpsellReq checkUpSell(Long objectId, Integer objectType, Integer offerId)
    {
        ImsUpsellReq up = this.querySingle(ImsUpsellReq.class, new DBCondition(ImsUpsellReq.Field.objId, objectId),
                new DBCondition(ImsUpsellReq.Field.objType, objectType), new DBCondition(ImsUpsellReq.Field.tarOfferId, offerId));
        if (up != null)
        {// 如果存在数据，删除数据不报错，允许订购
         // 彻底删除
            DBUtil.deleteByCondition(ImsUpsellReq.class, new DBCondition(ImsUpsellReq.Field.id, up.getId()));
        }
        else
        {// 报错，提示不允许订购升级产品
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CANOT_ORDER_UPSALE_PRODUCT);
        }
        return up;
    }

    /**
     * 修改override对方的定价计划
     * 
     * @param ovrrideProd
     * @param bean
     * @param currentOfferId 当前订购的销售品id
     */
    private void modifyOverrideProdPricePlanId(CoProd ovrrideProd, IMS3hBean bean, CoProd dmProd)
    {
        CmResource dmUser = null;
        Integer mainOfferId = null;
        if (bean.isUser3hBean())
        {
            dmUser = ((User3hBean)bean).getUser();
            mainOfferId = this.getUserMainProdOfferingId(dmUser.getResourceId());
        }
        UserComponent userCmp = context.getComponent(UserComponent.class);
        // 将旧的保存下来 存特征值
        Integer oldPricePlanId = ovrrideProd.getPricingPlanId();
        // 保存下来供删除用
        List<CoProdCharValue> charValues = new ArrayList<CoProdCharValue>();
        charValues.add(userCmp.createCoProdCharValue(ovrrideProd.getProductId(), 0, SpecCodeDefine.OLD_PRICE_PLAN_ID,
                String.valueOf(oldPricePlanId), ovrrideProd.getExpireDate(), ovrrideProd.getValidDate(), dmProd.getObjectId(),
                dmProd.getObjectType()));
        charValues.add(userCmp.createCoProdCharValue(ovrrideProd.getProductId(), 0, SpecCodeDefine.OVERRIDE_PRODUCT_ID,
                String.valueOf(dmProd.getProductId()), ovrrideProd.getExpireDate(), ovrrideProd.getValidDate(),
                dmProd.getObjectId(), dmProd.getObjectType()));
        this.insertBatch(charValues);
        Integer newPricePlanId = null;
        List<CoProdCharValue> charValueList = null;
        List<CoProdPriceParam> priceParamList = null;
        // 创建两个特征值将oldPricePlanId dmProd.getProductId 保存下来
        // 还需要从缓存中去获取一次，如果是同一次订购的，不能用this.update
        CoProd cacheProd = context.getSingleCache(CoProd.class, new CacheCondition(CoProd.Field.soNbr, context.getSoNbr()),
                new CacheCondition(CoProd.Field.productId, ovrrideProd.getProductId()));
        if (cacheProd != null)
        {
            // 同一次操作中订购的override对方产品 cacheProd 和ovrrideProd 是同一个产品
            priceParamList = context.getAllCacheList(CoProdPriceParam.class, new CacheCondition(CoProdPriceParam.Field.soNbr,
                    context.getSoNbr()), new CacheCondition(CoProdPriceParam.Field.productId, ovrrideProd.getProductId()));
            charValueList = context.getAllCacheList(CoProdCharValue.class, new CacheCondition(CoProdCharValue.Field.soNbr,
                    context.getSoNbr()), new CacheCondition(CoProdCharValue.Field.productId, ovrrideProd.getProductId()));
            newPricePlanId = this.queryPricePlanId(ovrrideProd.getProductOfferingId(), bean.getCustomer(), bean.getAccount(),
                    dmUser, priceParamList, dmProd.getProductOfferingId(), charValueList, mainOfferId, null, null);
            if (newPricePlanId == null)
            {
                newPricePlanId = 0;
            }
            // newPricePlanId=6;
            if (!oldPricePlanId.equals(newPricePlanId))
            {
                // //缓存中设置，保证上发数据一致性
                // cacheProd.setPricingPlanId(newPricePlanId);
                // cacheProd.setExpireDate(context.getRequestDate());
                context.removeCacheData(cacheProd);
                //
                // //更新定价计划
                // CoProd value=new CoProd();
                // value.setPricingPlanId(newPricePlanId);
                // CoProd where =new CoProd();
                // where.setProductId(cacheProd.getProductId());
                // DBUtil.updateByCondition(value, where);
                // CoProd where = new CoProd();
                // where.setProductId(ovrrideProd.getProductId());
                DBUtil.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, ovrrideProd.getProductId()));// 设置原产品失效，插入新的，时间是接续的

                // 当前产品 override对方产品 有优惠的定价计划 没有优惠的定价计划
                this.insertBatch(createCoProdListByOverride(ovrrideProd, dmProd, newPricePlanId, oldPricePlanId));
            }
        }
        else
        {
            // 需要手动刷新产品相关表
            this.updateByCondition(new CoProdBillingCycle(),
                    new DBCondition(CoProdBillingCycle.Field.productId, ovrrideProd.getProductId()));
            this.updateByCondition(new CoProdPriceParam(),
                    new DBCondition(CoProdPriceParam.Field.productId, ovrrideProd.getProductId()));
            // this.updateByCondition(new CoProdCharValue(), new
            // DBCondition(CoProdCharValue.Field.productId,ovrrideProd.getProductId()));
            // 不是同一次操作
            priceParamList = this.queryProdPriceParam(ovrrideProd.getProductId());
            charValueList = this.queryProdCharValue(ovrrideProd.getProductId());
            newPricePlanId = this.queryPricePlanId(ovrrideProd.getProductOfferingId(), bean.getCustomer(), bean.getAccount(),
                    dmUser, priceParamList, dmProd.getProductOfferingId(), charValueList, mainOfferId, null, null);
            // 没有查到设置为0
            if (newPricePlanId == null)
            {
                newPricePlanId = 0;
            }
            // 新旧不一样才进行修改
            if (!oldPricePlanId.equals(newPricePlanId))
            {
                // CoProd where = new CoProd();
                // where.setProductId(ovrrideProd.getProductId());
                DBUtil.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, ovrrideProd.getProductId()));// 删除原产品的产品记录
                                                                                                                            // 插入新的，时间是接续的

                // 当前产品 override对方产品 有优惠的定价计划 没有优惠的定价计划
                this.insertBatch(createCoProdListByOverride(ovrrideProd, dmProd, newPricePlanId, oldPricePlanId));
                // CoProd value=new CoProd();
                // value.setPricingPlanId(newPricePlanId);
                // CoProd where =new CoProd();
                // where.setProductId(ovrrideProd.getProductId());
                // //调用this
                // this.updateByCondition(value, where);
            }
        }
    }

    /**
     * @param dmProdList
     * @throws IMSException
     */
    protected void doProductReward(List<CoProd> dmProdList) throws IMSException
    {
        
    }

    /**
     * 订购产品 wuyj 2011-9-26
     */
    public CoProd createProductOrder(SProductOrder prod, List<CmResServCycle> resServList) throws IMSException
    {
        // 如果是订购虚账户产品（例如群产品），没有其他的三户信息 luojb 2011-11-08
        if (this.isAccountLevelOrderProduct(prod))// 账户级产品，检查是否是群产品
        {
            CaAccount account = context.get3hTree().loadAcct3hBean(prod.getAcct_id()).getAccount();
            return createProductOrder(prod, null, account, null, resServList);
        }

        User3hBean bean = context.get3hTree().loadUser3hBean(prod.getUser_id(), prod.getPhone_id());

        CmResource cmResource = bean.getUser();
        return createProductOrder(prod, bean.getCustomer(), bean.getAccount(), cmResource, resServList);
    }

    /**
     * 修改为返回list ljc 2011-10-17
     */
    private static List<SProductParam> findProdParam(SProductParamList paramList, int charValueId)
    {
        if (paramList == null || CommonUtil.isEmpty(paramList.getSProductParamList_Item()))
        {
            return null;
        }

        List<SProductParam> params = new ArrayList<SProductParam>();
        for (SProductParam param : paramList.getSProductParamList_Item())
        {
            if (param.getParam_id() != null && param.getParam_id().intValue() == charValueId)
            {
                params.add(param);
            }
        }
        return params;
    }

    /**
     * @param newPriceingPlanId 没有override的定价计划 只在overrideProd不为空的时候对该值进行计算
     * @param ovrrideProd override对方的产品 可能为空 不为空 计算对立的二者之间的失效时间的关系，判断插入数据库的是一条还是2条数据
     */
    private CoProd insertCoProd(SProductOrder prod, Integer pricingPlanId, CoProdBillingCycle dmBillCyle, CmResource dmUser,
            long busiFlag, Integer newPriceingPlanId, CoProd overrideProd, Long objectId, Integer objectType) throws IMSException
    {
        CoProd dmProd = new CoProd();
        Long productId = null;
        if (prod.getProduct_id() != null)
        {
            productId = prod.getProduct_id();
        }
        else
        {
            productId = DBUtil.getSequence(CoProd.class);
        }
        dmProd.setProductId(productId);
        dmProd.setObjectId(objectId);
        dmProd.setObjectType(objectType);
        // product type 根据销售品配置得到, ljc
        PmProductOffering dmOffering = super.queryPmProductOfferingByOfferId(Integer.valueOf(prod.getOffer_id().intValue()));
        // PmProductOffering dmOffering =
        // queryOfferingByOfferId(offerId.longValue());
        dmProd.setProdTypeId(dmOffering.getOfferTypeId());
        dmProd.setProductOfferingId(prod.getOffer_id().intValue());
        dmProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);// active

        if (pricingPlanId != null)
            dmProd.setPricingPlanId(pricingPlanId);
        else
        {
            pricingPlanId = 0;
            dmProd.setPricingPlanId(0);
        }
        dmProd.setBusiFlag((int) busiFlag);
        // 如果是pre_match产品，那么要设置用户的unbilling_flag设置成2(cm_res_lifecycle中)
        if (busiFlag == SpecCodeDefine.PREMATCH)
        {
            CmResLifecycle value = new CmResLifecycle();
            value.setUnbillingFlag(EnumCodeDefine.LIFECYCLE_UNBILLING_PROSPECT);
            // 该处只能在三户新装的时候才能进入，故不能调用this.,否则主键冲突
            DBUtil.updateByCondition(value, new DBCondition(CmResLifecycle.Field.resourceId, dmUser.getResourceId()));
            if (context.containCache(CmResLifecycle.class))
            {
                // 往存在的缓存设置更新后的值，后续上发MDB
                CmResLifecycle lifeCycle = context.getSingleCache(CmResLifecycle.class, new CacheCondition(
                        CmResLifecycle.Field.resourceId, dmUser.getResourceId()));
                lifeCycle.setUnbillingFlag(EnumCodeDefine.LIFECYCLE_UNBILLING_PROSPECT);
            }
        }
        // 如果是主产品， ljc
        if (EnumCodeDefine.PRODUCT_MAIN == dmOffering.getIsMain())
        {
            dmProd.setIsMain(EnumCodeDefine.PRODUCT_MAIN);
            // 主产品设置失效时间和用户失效时间一样，生效时间晚于用户生效时间
            // 首次激活时 主产品的生效时间用激活时间 2011-12-15 wukl
            Date activeDate = this.getActiveDate();
            if (activeDate != null)
            {
                dmBillCyle.setValidDate(activeDate);
            }
            // 2012-3-22 luojb主产品的失效时间和普通产品一样，取销售品计算
            // dmBillCyle.setExpireDate(dmUser.getExpireDate());
        }
        else
        {
            dmProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
        }

        // check for billing type
        dmProd.setBillingType(this.getProdBillingType(prod, dmUser));
        dmProd.setCreateDate(IMSUtil.formatDate(prod.getCreate_date(), context.getRequestDate()));

        // 根据产品账期的生效时间设置产品的生效时间2011-09-19 ljc
        dmProd.setValidDate(dmBillCyle.getValidDate());

        dmProd.setExpireDate(dmBillCyle.getExpireDate());
        // 如果是国际漫游产品，则生失效时间需要和漫游地的时间进行合算。
        if (dmProd.getBusiFlag() == SpecCodeDefine.IR_OUT)
        {
            this.resetValidExpireDate(dmProd, CommonUtil.long2Int(prod.getOffer_id()), prod);
        }
        
        //2012-07-10 luojb
        dmProd.setProdValidDate(dmProd.getValidDate());
        dmProd.setProdExpireDate(dmProd.getExpireDate());
        
        List<CoProd> dmProdList = new ArrayList<CoProd>();
        if (overrideProd == null)
        {
            dmProdList.add(dmProd);
        }
        else
        {
            // 有override销售品需要特殊计算 返回的还是一个产品，但是插入数据库的数据进行了分段
            if (newPriceingPlanId == null)
            {
                newPriceingPlanId = 0;
            }
            // 二者相等，只需要插入一个产品
            if (newPriceingPlanId.equals(pricingPlanId))
            {
                dmProdList.add(dmProd);
            }
            else
            {// 不相等需要插入两个产品只是生效时间失效时间和定价计划不同
                dmProdList = createCoProdListByOverride(dmProd, overrideProd, pricingPlanId, newPriceingPlanId);
            }
            // 创建2个特征值13802保存没有优惠的定价计划id
            UserComponent userCmp = context.getComponent(UserComponent.class);
            List<CoProdCharValue> charValueList = new ArrayList<CoProdCharValue>();
            charValueList.add(userCmp.createCoProdCharValue(dmProd.getProductId(), 0, SpecCodeDefine.OLD_PRICE_PLAN_ID,
                    String.valueOf(newPriceingPlanId), dmProd.getExpireDate(), dmProd.getValidDate(), objectId, objectType));
            charValueList.add(userCmp.createCoProdCharValue(dmProd.getProductId(), 0, SpecCodeDefine.OVERRIDE_PRODUCT_ID,
                    String.valueOf(overrideProd.getProductId()), dmProd.getExpireDate(), dmProd.getValidDate(), objectId,
                    objectType));
            this.insertBatch(charValueList);
        }
        insertBatch(dmProdList);

        return dmProd;

    }

    private void resetValidExpireDate(CoProd dmProd, Integer productOfferingId, SProductOrder prod)
    {
        String value = context.getComponent(ConfigQuery.class).queryPmSpecChar(SpecCodeDefine.IR_OUT_FLAG).getValue();
        int flag = Integer.parseInt(value);
        if (flag == 1)
        {
            SysCountry irCountry = getTzIrCountryWithCheck(productOfferingId);
//            if(!CommonUtil.isValid(irCountry.getTimeZone())){
//                 IMSUtil.throwBusiException(ErrorCodeDefine.TIME_ZONE_NULL,irCountry.getName());
//            }
            // 获取系统时区
            String coutryName = SysUtil.getString(SysCodeDefine.busi.INNER_COUNTRY);
            SysCountry sc = this.querySingle(SysCountry.class, new DBCondition(SysCountry.Field.name, coutryName));
            if (sc == null || !CommonUtil.isValid(sc.getTimeZone()))
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.TIME_ZONE_NULL,coutryName);
            }
            //系统时区
            String scTZ = sc.getTimeZone();
            Integer scValueShift = this.getTransfTimeZone(scTZ);
            //获取漫游地时区
            Integer validZoneShift = null;
            Integer expireZoneShift = null;
            if(irCountry.getTimeZoneId() != null){
                validZoneShift = dateIsInZoneDetail(dmProd.getValidDate(), irCountry.getTimeZoneId());
                expireZoneShift = dateIsInZoneDetail(dmProd.getExpireDate(), irCountry.getTimeZoneId());
            }else{
               if(!CommonUtil.isValid(irCountry.getTimeZone())){
                    IMSUtil.throwBusiException(ErrorCodeDefine.TIME_ZONE_NULL,irCountry.getName());
               }
                //timeZoneId为空表示不用去匹配夏令时 直接取timeZone
                validZoneShift = getTransfTimeZone(irCountry.getTimeZone());
                expireZoneShift = validZoneShift;
            }
            //如果获取不到sys_time_zone_detail配置的时区 那么也直接取sys_country的timeZone
            if(validZoneShift == null){
               if(!CommonUtil.isValid(irCountry.getTimeZone())){
                    IMSUtil.throwBusiException(ErrorCodeDefine.TIME_ZONE_NULL,irCountry.getName());
               }
               validZoneShift = this.getTransfTimeZone(irCountry.getTimeZone());
            }
            if(expireZoneShift == null){
                if(!CommonUtil.isValid(irCountry.getTimeZone())){
                    IMSUtil.throwBusiException(ErrorCodeDefine.TIME_ZONE_NULL,irCountry.getName());
                }
                expireZoneShift = this.getTransfTimeZone(irCountry.getTimeZone());
            }
            
           
            Integer temp1 = validZoneShift - scValueShift;
            Integer temp2 = expireZoneShift - scValueShift;
            
            
//            Integer countryId = irCountry.getCountryId();
//            Integer temp = getTimeZoneDefValue(irCountry);
            /*PmProductOfferSpecChar offerChar = getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.IR_OUT_COUNTRY,
                    productOfferingId);
            if (offerChar == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_NO_REECORD);
            }
            // 漫游国家id
            int countryId = Integer.valueOf(offerChar.getValue());
            // 获取漫游地时区
            SysCountry irCountry = querySingle(SysCountry.class, new DBCondition(SysCountry.Field.countryId, countryId));
            if (irCountry == null)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.CONFIG_OF_COUNTRY);
            }
            // 保存漫游地国家简写
            context.addExtendParam(ConstantDefine.IR_PROD_NAME, irCountry.getNameAbbreviation());
            if (irCountry.getTimeZoneId() == null)
            {
                context.addExtendParam(ConstantDefine.IR_PROD_VALID_DATE, dmProd.getValidDate());
                context.addExtendParam(ConstantDefine.IR_PROD_EXPIRE_DATE, dmProd.getExpireDate());
                return;
            }
            int timeZone = Integer.valueOf(irCountry.getTimeZoneId());
            // 获取系统时区
            String coutryName = SysUtil.getString(SysCodeDefine.busi.INNER_COUNTRY);
            SysCountry sc = this.querySingle(SysCountry.class, new DBCondition(SysCountry.Field.name, coutryName));
            if (sc == null || sc.getTimeZoneId() == null)
            {
                context.addExtendParam(ConstantDefine.IR_PROD_VALID_DATE, dmProd.getValidDate());
                context.addExtendParam(ConstantDefine.IR_PROD_EXPIRE_DATE, dmProd.getExpireDate());
                return;
            }
            // irCountryTZ = Integer.parseInt(irCountry.getTimeZone());
            int scTZ = Integer.valueOf(sc.getTimeZoneId());
            Integer validZoneShift = dateIsInZoneDetail(dmProd.getValidDate(), timeZone);
            int temp = timeZone - scTZ;// 获得两个时区的时间差
            if (validZoneShift != null)
            {
                temp = validZoneShift - scTZ;
            }
            validZoneShift = dateIsInZoneDetail(dmProd.getExpireDate(), timeZone);
            int temp2 = timeZone - scTZ;
            if (validZoneShift != null)
            {
                // 使用偏移地时间减系统时间 lijc3
                temp2 = validZoneShift - scTZ;
            }  */
            // 下一天生效的时候是notify漫游地的第二天00:00：00，所以要把这个时间内保存下来notify
            // 2012-04-07 luojb 下周期、下月、第二天生效产品的生效时间都参考漫游地
            if (prod.getValid_type() != null
                    && (prod.getValid_type() == EnumCodeDefine.PROD_VALID_NEXT_DAY
                            || prod.getValid_type() == EnumCodeDefine.PROD_VALID_NEXT_CYCLE || prod.getValid_type() == EnumCodeDefine.PROD_VALID_NEXT_MONTH))
            {
                context.addExtendParam(ConstantDefine.IR_PROD_VALID_DATE, dmProd.getValidDate());
                context.addExtendParam(ConstantDefine.IR_PROD_EXPIRE_DATE, dmProd.getExpireDate());
                imsLogger.debug("-------IR PROD_ID["+dmProd.getProductId()+"] VALID_DATE["+dmProd.getValidDate()+"] EXPIRE_DATE["+dmProd.getExpireDate()+"]");
                dmProd.setValidDate(DateUtil.getDateDelayHours(dmProd.getValidDate(), temp1));
                dmProd.setExpireDate(DateUtil.getDateDelayHours(dmProd.getExpireDate(), temp2));
                imsLogger.debug("-------DB PROD_ID["+dmProd.getProductId()+"] VALID_DATE["+dmProd.getValidDate()+"] EXPIRE_DATE["+dmProd.getExpireDate()+"]");
              // 保存下来notification使用
            }
            else
            {
                // 保存下来notification使用
                Date nValidDate = DateUtil.getDateDelayHours(dmProd.getValidDate(), temp1);
                Date nExpireDate = DateUtil.getDateDelayHours(dmProd.getExpireDate(), temp2);
                context.addExtendParam(ConstantDefine.IR_PROD_VALID_DATE, nValidDate);
                context.addExtendParam(ConstantDefine.IR_PROD_EXPIRE_DATE,nExpireDate);
                imsLogger.debug("-------IR PROD_ID["+dmProd.getProductId()+"] VALID_DATE["+nValidDate+"] EXPIRE_DATE["+nExpireDate+"]");
                imsLogger.debug("-------DB PROD_ID["+dmProd.getProductId()+"] VALID_DATE["+dmProd.getValidDate()+"] EXPIRE_DATE["+dmProd.getExpireDate()+"]");
            }
        }
    }
    
    /**
     * 
     * yangjh 2012-8-27
     * @param timeZone
     * @return
     */
    private Integer getTransfTimeZone(String timeZone){
        Integer transfTimeZone = null;
        String str = timeZone.substring(1,3);
        if('-' == timeZone.charAt(0)){
            transfTimeZone = -Integer.parseInt(str);
        }else{
            transfTimeZone = Integer.parseInt(str);
        }
        return transfTimeZone;
    }
    
    private SysCountry getTzIrCountryWithCheck(Integer productOfferingId)
    {
        PmProductOfferSpecChar offerChar = getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.IR_OUT_COUNTRY,
                productOfferingId);
        if (offerChar == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_NO_REECORD);
        }
        // 漫游国家id
        Integer countryId = Integer.valueOf(offerChar.getValue());
        // 获取漫游地时区
        SysCountry irCountry = querySingle(SysCountry.class, new DBCondition(SysCountry.Field.countryId, countryId));
        if (irCountry == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CONFIG_OF_COUNTRY);
        }
        // 保存漫游地国家简写
        context.addExtendParam(ConstantDefine.IR_PROD_NAME, irCountry.getNameAbbreviation());
       /* if (irCountry.getTimeZoneId() == null)
        {
            context.addExtendParam(ConstantDefine.IR_PROD_VALID_DATE, dmProd.getValidDate());
            context.addExtendParam(ConstantDefine.IR_PROD_EXPIRE_DATE, dmProd.getExpireDate());
            return;
        }  */
        if(!CommonUtil.isValid(irCountry.getTimeZone())){
            IMSUtil.throwBusiException(ErrorCodeDefine.TIME_ZONE_NULL,irCountry.getName());
        }
        return irCountry;
    }
    /**
     * 
     * yangjh 2012-8-23 获取时区差
     * @return
     */
    private Integer getTimeZoneDefValue(SysCountry irCountry){
//        PmProductOfferSpecChar offerChar = getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.IR_OUT_COUNTRY,
//                productOfferingId);
//        if (offerChar == null)
//        {
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_NO_REECORD);
//        }
//        // 漫游国家id
//        countryId = Integer.valueOf(offerChar.getValue());
//        // 获取漫游地时区
//        SysCountry irCountry = querySingle(SysCountry.class, new DBCondition(SysCountry.Field.countryId, countryId));
//        if (irCountry == null)
//        {
//            IMSUtil.throwBusiException(ErrorCodeDefine.CONFIG_OF_COUNTRY);
//        }
//        // 保存漫游地国家简写
//        context.addExtendParam(ConstantDefine.IR_PROD_NAME, irCountry.getNameAbbreviation());
//       /* if (irCountry.getTimeZoneId() == null)
//        {
//            context.addExtendParam(ConstantDefine.IR_PROD_VALID_DATE, dmProd.getValidDate());
//            context.addExtendParam(ConstantDefine.IR_PROD_EXPIRE_DATE, dmProd.getExpireDate());
//            return;
//        }  */
        if(!CommonUtil.isValid(irCountry.getTimeZone())){
            IMSUtil.throwBusiException(ErrorCodeDefine.TIME_ZONE_NULL,irCountry.getName());
        }
        //漫游地时区
        String myTZ = irCountry.getTimeZone();
        Integer myValueShit = this.getTransfTimeZone(myTZ);
        
        // 获取系统时区
        String coutryName = SysUtil.getString(SysCodeDefine.busi.INNER_COUNTRY);
        SysCountry sc = this.querySingle(SysCountry.class, new DBCondition(SysCountry.Field.name, coutryName));
        if (sc == null || !CommonUtil.isValid(sc.getTimeZone()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.TIME_ZONE_NULL,coutryName);
            /*
             * context.addExtendParam(ConstantDefine.IR_PROD_VALID_DATE, dmProd.getValidDate());
               context.addExtendParam(ConstantDefine.IR_PROD_EXPIRE_DATE, dmProd.getExpireDate());
                return; 
            */
        }
        //系统时区
        String scTZ = sc.getTimeZone();
        Integer scValueShit = this.getTransfTimeZone(scTZ);
        int temp = myValueShit - scValueShit;// 获得两个时区的时间差
        return  temp;
    }

    /**
     * 获取产品真正的付费模式
     */
    private Integer getProdBillingType(SProductOrder prod, CmResource dmUser)
    {

        if (dmUser != null)
        {
            if (prod.getBilling_type() == null)
            {
                return dmUser.getBillingType() % 10;
            }
            else
            {
                if (dmUser.getBillingType() < 10)
                {
                    // IF billint_type not fit the subscriber's billing_type
                    // throw a exception
                    if (dmUser.getBillingType().intValue() != prod.getBilling_type().shortValue())
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_BILLTYPE_INVALIDE);
                    }
                }
                // @Date 2012-04-12 lijc3 修改为2的时候的处理
                if (prod.getBilling_type() == EnumCodeDefine.USER_PAYMODE_HYBRID)
                {
                    Integer offeringBillingType = context.getComponent(CacheQuery.class).queryProdOfferingBillingType(
                            prod.getOffer_id());
                    if (offeringBillingType != null && offeringBillingType != EnumCodeDefine.PROD_BILLTYPE_ALL)
                    {
                        return offeringBillingType;
                    }
                    else
                    {
                        return dmUser.getBillingType() % 10;
                    }
                }
                else
                {
                    return prod.getBilling_type().intValue();
                }
            }
        }
        else
        {
            // 帐户产品的付费类型2011-8-24 luojb
            if (prod.getBilling_type() == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "billing_type");
            }
            if (prod.getBilling_type() == -1)
            {
                return EnumCodeDefine.PROD_BILLTYPE_POSTPAID;
            }
            else
            {
                return CommonUtil.short2Int(prod.getBilling_type());
            }
        }
    }

    /**
     * 根据override的不同情况拆分dmProd
     * 
     * @param dmProd
     * @param overrideProd
     * @param pricingPlanId 有优惠
     * @param newPriceingPlanId 没有优惠
     * @return
     * @date 2012-11-14 luojb On_Site Defect #64896 
     */
    private List<CoProd> createCoProdListByOverride(CoProd dmProd, CoProd overrideProd, Integer overridePricingPlanId,
            Integer priceingPlanId)
    {
        dmProd.setPricingPlanId(priceingPlanId);
        List<CoProd> prodList = new ArrayList<CoProd>();
        
        Date prodValidDate = dmProd.getValidDate();
        Date prodExpireDate = dmProd.getExpireDate();
        Date overProdValidDate = overrideProd.getValidDate();
        Date overProdExpireDate = overrideProd.getExpireDate();
        
        // override生效的时间段
        Date startDate = prodValidDate.before(overProdValidDate)?overProdValidDate:prodValidDate;
        Date endDate = prodExpireDate.after(overProdExpireDate)?overProdExpireDate:prodExpireDate;
        
        // 两个产品时间没有交集，override不生效
        if(!startDate.before(endDate)){
            prodList.add(dmProd);
            return prodList;
        }

        if(startDate.after(prodValidDate))
        {
            CoProd prod_first_no_over = (CoProd) IMSUtil.copyDataObject(dmProd);
            prod_first_no_over.setExpireDate(startDate);
            prodList.add(prod_first_no_over);
            dmProd.setValidDate(startDate);
        }
        
        if(endDate.before(prodExpireDate))
        {
            CoProd prod_last_no_over = (CoProd) IMSUtil.copyDataObject(dmProd);
            prod_last_no_over.setValidDate(endDate);
            prodList.add(prod_last_no_over);
            dmProd.setExpireDate(endDate);
        }
        
        dmProd.setPricingPlanId(overridePricingPlanId);
        prodList.add(dmProd);
        
        return prodList;
    }


    // 特征值有效期应该在产品有效期之内 luojb 2011-08-27
    private void createProductCharValues(SProductParamList productParamList, CoProd dmProd, long busiFlag, String prodValidDate,
            String prodExpireDate) throws IMSException
    {
        List<CoProdCharValue> dmProdChars = null;
        if (productParamList != null && CommonUtil.isNotEmpty(productParamList.getSProductParamList_Item()))
        {
            dmProdChars = createProdCharValueNoSave(productParamList, dmProd, busiFlag, IMSUtil.formatDate(prodValidDate, null),
                    IMSUtil.formatExpireDate(prodExpireDate));
        }
        if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
        {
            PmProductOfferSpecChar prop = getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.MAIN_PROD_SPEC_CHAR_11628,
                    dmProd.getProductOfferingId());
            if (prop != null)
            {
                CoProdCharValue value = context.getComponent(UserComponent.class).createCoProdCharValue(dmProd.getProductId(), 0,
                        SpecCodeDefine.MAIN_PROD_SPEC_CHAR_11628, prop.getValue(), dmProd.getExpireDate(), dmProd.getValidDate(),
                        dmProd.getObjectId(), dmProd.getObjectType());
                if (CommonUtil.isEmpty(dmProdChars))
                {
                    dmProdChars = new ArrayList<CoProdCharValue>();
                    dmProdChars.add(value);
                }
                else
                {
                    // @Date2012-04-09 lijc3 修改
                    dmProdChars.add(value);
                }
            }
            else
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_NEED_SPEC, dmProd.getProductOfferingId(), 11628);
            }
            // 2012-4-2 luojb 增加主产品12000特征值
            PmProductOfferSpecChar prop2 = getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.MAIN_PROD_SPEC_CHAR_12000,
                    dmProd.getProductOfferingId());
            if (prop2 != null)
            {
                CoProdCharValue value = context.getComponent(UserComponent.class).createCoProdCharValue(dmProd.getProductId(), 0,
                        SpecCodeDefine.MAIN_PROD_SPEC_CHAR_12000, prop2.getValue(), dmProd.getExpireDate(),
                        dmProd.getValidDate(), dmProd.getObjectId(), dmProd.getObjectType());
                if (CommonUtil.isEmpty(dmProdChars))
                {
                    dmProdChars = new ArrayList<CoProdCharValue>();
                    dmProdChars.add(value);
                }
                else
                {
                    dmProdChars.add(value);
                }
            }
            else
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_NEED_SPEC, dmProd.getProductOfferingId(), 12000);
            }
        }
        if (dmProdChars != null)
        {
            if (busiFlag == SpecCodeDefine.FRIENDNUMBER)
            { // 亲情号码保存到co_fn_char_value表
                this.insertBatch(ConvertUtil.coProdCharValues2CoFnCharValues(dmProdChars));
            }
            else
            {
                this.insertBatch(dmProdChars);
            }
        }
    }

    private void backIcsList(List<CmResLastIcs> icsList, int i, boolean needLimitCount, int count,long userId,SProductParamList paramList)
    {
        SProductParam[] oldParamItems = paramList.getSProductParamList_Item();
        List<SProductParam> newParamList = new ArrayList<SProductParam>();
       
        if(oldParamItems != null)
        {
            for(SProductParam param:oldParamItems){
                newParamList.add(param);
            }
        }
    	for (CmResLastIcs ics : icsList)
        {
        	i++;
        	if(needLimitCount)
        	{
        		if(count > 0 && i > count)
        		{ 
            		break;
        	    }
        	}
            Long groupId = DBUtil.getSequence();
            SProductParam param = new SProductParam();
            param.setParam_id(SpecCodeDefine.MCS_NUMBER_SEVICE);
            param.setParam_value(String.valueOf(50001));
            param.setGroup_id(groupId);
            newParamList.add(param);
            
            param = new SProductParam();
            param.setParam_id(SpecCodeDefine.MCS_NUMBER_RULE);
            param.setParam_value(String.valueOf(userId));
            param.setGroup_id(groupId);
            newParamList.add(param);
            
            param = new SProductParam();
            param.setParam_id(SpecCodeDefine.MCS_NUMBER_NUMBER);
            param.setParam_value(ics.getPhoneId());
            param.setGroup_id(groupId);
            newParamList.add(param);
            
            if (ics.getIrRouteFlag() != null)
            {
                param = new SProductParam();
                param.setParam_id(SpecCodeDefine.MCS_NUMBER_ROUTE_FLAG);
                param.setParam_value(String.valueOf(ics.getIrRouteFlag()));
                param.setGroup_id(groupId);
                newParamList.add(param);
            }
            if (ics.getRoutingMethod() != null)
            {
                param = new SProductParam();
                param.setParam_id(SpecCodeDefine.MCS_NUMBER_ROUTE_METHOD);
                param.setParam_value(String.valueOf(ics.getRoutingMethod()));
                param.setGroup_id(groupId);
                newParamList.add(param);
            }
            if (ics.getListType() != null)
            {
                param = new SProductParam();
                param.setParam_id(SpecCodeDefine.MCS_NUMBER_TYPE);
                param.setParam_value(String.valueOf(ics.getListType()));
                param.setGroup_id(groupId);
                newParamList.add(param);
            }
            if (CommonUtil.isValid(ics.getEffectTime()))
            {
                param = new SProductParam();
                param.setParam_id(SpecCodeDefine.MCS_NUMBER_EFFECT_TIME);
                param.setParam_value(String.valueOf(ics.getEffectTime()));
                param.setGroup_id(groupId);
                newParamList.add(param);
            }
            if (CommonUtil.isValid(ics.getExpireTime()))
            {
                param = new SProductParam();
                param.setParam_id(SpecCodeDefine.MCS_NUMBER_EXPIRE_TIME);
                param.setParam_value(String.valueOf(ics.getExpireTime()));
                param.setGroup_id(groupId);
                newParamList.add(param);
            }
            if (ics.getWeekStart() != null)
            {
                param = new SProductParam();
                param.setParam_id(SpecCodeDefine.MCS_NUMBER_WEEK_START);
                param.setParam_value(String.valueOf(ics.getWeekStart()));
                param.setGroup_id(groupId);
                newParamList.add(param);
            }
            if (CommonUtil.isValid(ics.getWeekStop()))
            {
                param = new SProductParam();
                param.setParam_id(SpecCodeDefine.MCS_NUMBER_WEEK_END);
                param.setParam_value(String.valueOf(ics.getWeekStop()));
                param.setGroup_id(groupId);
                newParamList.add(param);
            }
            
            //@Date 2012-09-04 yangjh : stroy：55862 ics上发crm 插入ImsIcsSync上发表
            User3hBean bean = context.get3hTree().loadUser3hBean(ics.getResourceId());
            String bosPhoneId = bean.getPhoneId();
            this.syncIcsInfo(ics,bosPhoneId);
        }
    	paramList.setSProductParamList_Item(newParamList.toArray(new SProductParam[newParamList.size()]));
    }
    
    /**
     * 
     * yangjh 2012-8-28
     * @param ics
     */
    private void syncIcsInfo(CmResLastIcs ics, String bosPhoneId)
    {
        // TODO Auto-generated method stub
        ImsIcsSync icsSync = new ImsIcsSync();
        icsSync.setId(DBUtil.getSequence(ImsIcsSync.class));
        icsSync.setUserId(ics.getResourceId());
        icsSync.setBosPhoneId(bosPhoneId);
        icsSync.setPhoneId(ics.getPhoneId());
        icsSync.setCreateDate(ics.getCreateDate());
        icsSync.setValidDate(ics.getValidDate());
        icsSync.setExpireDate(ics.getExpireDate());
        icsSync.setSoDate(ics.getSoDate());
        icsSync.setSoNbr(ics.getSoNbr());
        icsSync.setWeekStart(ics.getWeekStart());
        icsSync.setWeekStop(ics.getWeekStop());
        icsSync.setListType(ics.getListType());
        if(ics.getRouteNumber() != null){
            icsSync.setRouteNumber(ics.getRouteNumber());
        }
        if(ics.getRoutingMethod() != null){
            icsSync.setRoutingMethod(ics.getRoutingMethod());
        }
        if(CommonUtil.isValid(ics.getEffectTime())){
            icsSync.setEffectTime(ics.getEffectTime());
        }
        if(CommonUtil.isValid(ics.getExpireTime())){
            icsSync.setExpireTime(ics.getExpireTime());
        }
        if(ics.getIrRouteFlag() != null){
            icsSync.setIrRouteFlag(ics.getIrRouteFlag());
        }
        icsSync.setSts(CommonUtil.ShortToInteger(EnumCodeDefine.SYNC_DEAL_STS_INITIAL));
        super.insert(icsSync);
    }
    
    public List<CoProdCharValue> createProdCharValueNoSave(SProductParamList productParamList, CoProd dmProd, long busiFlag,
            Date prodValid, Date prodExpire)
    {
        Long prodId = dmProd.getProductId();
        Integer offerId = dmProd.getProductOfferingId();
        if (productParamList == null || CommonUtil.isEmpty(productParamList.getSProductParamList_Item()))
            return null;
        int count = productParamList.getSProductParamList_Item().length;
        List<CoProdCharValue> dmProdChars = new ArrayList<CoProdCharValue>();
        List<Integer> list = context.getComponent(ConfigQuery.class).querySpecCharIdsByOfferId(dmProd.getProductOfferingId());
        if (CommonUtil.isEmpty(list))
        {
            list = new ArrayList<Integer>();
            list.add(SpecCodeDefine.SLA_SPEED);
            list.add(SpecCodeDefine.PROD_COUNT);
        }
        Long groupId = DBUtil.getSequence();
        Set<Integer> charIds = new HashSet<Integer>();
        // 验证亲情号码和multi-sim是否重复
        Set<String> fnValues = new HashSet<String>();
        Set<String> mlValues = new HashSet<String>();
        // 特殊号码
        Set<String> spValues = new HashSet<String>();
        // 小区
        Set<String> hzValues = new HashSet<String>();
        for (int i = 0; i < count; i++)
        {
            SProductParam param = (SProductParam) productParamList.getSProductParamList_Item()[i];

            boolean flag = false;
            for (Integer sc : list)
            {
                /*
                 * @Date 2012-08-13 tangjl5 :Story # 51473 修改子产品对应特殊特征值时，需要通过主产品的id来操作，例如修改FN，
                 *  需要通过package的产品id来修改子产品的FN号码特征值
                 *  判断该改param_id是否为子产品的特征规格值，若是则不抛出异常
                 */
                if (checkParamIsInSonProd(dmProd, param.getParam_id()))
                {
                    flag = true;
                    continue;
                }
               
                if (param.getParam_id().intValue() == sc || param.getParam_id().intValue() == SpecCodeDefine.SLA_SPEED
                        || param.getParam_id().intValue() == SpecCodeDefine.PROD_COUNT)
                {
                    flag = true;
                    CoProdCharValue dmProdChar = new CoProdCharValue();
                    dmProdChar.setProductId(prodId);
                    dmProdChar.setSoNbr(context.getSoNbr());
                    dmProdChar.setSpecCharId(param.getParam_id());
                    dmProdChar.setValue(param.getParam_value());
                    dmProdChar.setObjectId(dmProd.getObjectId());
                    dmProdChar.setObjectType(dmProd.getObjectType());
                    // 如果是亲情号码产品或者是multi_sim产品,特殊号码，需要判断是否重复。
                    if (busiFlag == SpecCodeDefine.FRIENDNUMBER)
                    {
                        if (fnValues.contains(param.getParam_value()))
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID,
                                    "the second param value" + param.getParam_value());
                        }
                        else
                        {
                            fnValues.add(param.getParam_value());
                        }
                    }
                    if (busiFlag == SpecCodeDefine.MULTI_SIM)
                    {
                        if (mlValues.contains(param.getParam_value()))
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID,
                                    "the second param value" + param.getParam_value());
                        }
                        else
                        {
                            mlValues.add(param.getParam_value());
                        }
                    }
                    // 特殊号码
                    if (busiFlag == SpecCodeDefine.SPECIAL_NUMBER)
                    {
                        if (param.getParam_id().intValue() == SpecCodeDefine.SPECIAL_NUMBER_NUMBER)
                        {
                            if (spValues.contains(param.getParam_value()))
                            {
                                throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID,
                                        "the second param value" + param.getParam_value());
                            }
                            else
                            {
                                spValues.add(param.getParam_value());
                            }
                        }
                    }
                    if (busiFlag == SpecCodeDefine.HOME_ZONE)
                    {
                      //@Date 2012-09-13 yangjh : homeZone产品 只对小区编号做限制
                        if(param.getParam_id().intValue() == SpecCodeDefine.HOME_ZONE_CELL)
                        {
                            if (hzValues.contains(param.getParam_value()))
                            {
                                throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID,
                                        "the second param value" + param.getParam_value());
                            }
                            else
                            {
                                hzValues.add(param.getParam_value());
                            }
                        }
                    }
                    Date paramValid = IMSUtil.formatDate(param.getValid_date(), null);
                    Date paramExpire = IMSUtil.formatExpireDate(param.getExpire_date());
                    // 如果特征值生效期为空，或早于产品生效期，取产品生效期
                    if (param.getValid_date() != null && paramValid != null && paramValid.after(prodValid))
                    {
                        dmProdChar.setValidDate(IMSUtil.formatDate(param.getValid_date(), null));
                    }
                    else
                    {
                        dmProdChar.setValidDate(prodValid);
                    }
                    // 如果特征值的失效期为空，或晚于产品失效期，取产品失效期
                    if (param.getExpire_date() != null && paramExpire != null && paramExpire.before(prodExpire))
                    {
                        dmProdChar.setExpireDate(IMSUtil.formatExpireDate(param.getExpire_date()));
                    }
                    else
                    {
                        dmProdChar.setExpireDate(prodExpire);
                    }
                    if (dmProdChar.getExpireDate().before(dmProdChar.getValidDate()))
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_CONFIG_OF_EXPIRE_AND_VALID_DATE);
                    }
                    if (param.getGroup_id() != null)
                    {
                        groupId = param.getGroup_id();
                    }
                    else
                    {
                        // 如果传入的特征值id不在已经增加的id里面又没有传入group_id则重新取一个seq
                        if (!charIds.contains(param.getParam_id()))
                        {
                            charIds.add(param.getParam_id());
                        }
                        else
                        {
                            groupId = DBUtil.getSequence();
                        }
                    }
                    dmProdChar.setGroupId(groupId);
                    // if (busiFlag != SpecCodeDefine.FRIENDNUMBER)
                    // dmProdChar.setGroupId(groupId);
                    // else
                    // dmProdChar.setGroupId(new Long(i));//
                    // 特殊判断，亲情号码的groupid是每个号码的序号
                    // 设置回生失效时间给response
                    param.setValid_date(IMSUtil.formatDate(dmProdChar.getValidDate()));
                    param.setExpire_date(IMSUtil.formatDate(dmProdChar.getExpireDate()));
                    dmProdChars.add(dmProdChar);
                }
                else
                {
                    continue;
                }
            }
            // 没有找到对应的 报异常
            if (!flag)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_SPEC_CHAR_NOT_FOUND_BY_OFFER, param.getParam_id()
                        .intValue(), offerId, busiFlag);
            }

        }

        if (SpecCodeDefine.FRIENDNUMBER == busiFlag)
        {
            // 特殊操作：亲情号码增加一个特征值表示数量
            CoProdCharValue dmProdChar = new CoProdCharValue();
            dmProdChar.setProductId(prodId);
            dmProdChar.setSoNbr(context.getSoNbr());
            dmProdChar.setGroupId(0L);
            dmProdChar.setSpecCharId(SpecCodeDefine.FRIENDNUMBER_COUNT);
            dmProdChar.setValue(String.valueOf(count));
            // ljc 生失效时间按产品算
            dmProdChar.setValidDate(prodValid);
            dmProdChar.setExpireDate(prodExpire);
            dmProdChar.setObjectId(dmProd.getObjectId());
            dmProdChar.setObjectType(dmProd.getObjectType());
            dmProdChars.add(dmProdChar);
        }
        return dmProdChars;
    }

    public SProductOrder buildSProductOrder(long offeringId) throws IMSException
    {
        PmProductOffering dmProdOffer = new PmProductOffering();
        dmProdOffer.setProductOfferingId((int) offeringId);
        dmProdOffer = super.queryPmProductOfferingByOfferId(CommonUtil.long2Int(offeringId));
        SProductOrder prod = new SProductOrder();
        prod.setOffer_id(dmProdOffer.getProductOfferingId().longValue());
        prod.setProduct_type(EnumCodeDefine.PROD_TYPE_MAIN);
        prod.setValid_date(DateUtil.getFormatDate(dmProdOffer.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        prod.setExpire_date(DateUtil.getFormatDate(dmProdOffer.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

        return prod;
    }

    /**
     * 实例化二次议价参数
     */
    public void createReguidPrice(ExtendParamList paramList, long prodId, CoProd dmProd) throws IMSException
    {
        if (CommonUtil.isEmpty(paramList.getExtendParamList_Item()))
            return;
        List<CoProdPriceParam> dmProdPriceParams = new ArrayList<CoProdPriceParam>();
        
        for (int i = 0; i < paramList.getExtendParamList_Item().length; i++)
        {
            ExtendParam param = (ExtendParam) paramList.getExtendParamList_Item()[i];
            if (CommonUtil.isEmpty(param.getParam_name()))
            {
                continue;
            }
            Integer paramId = Integer.parseInt(param.getParam_name());
            if (noInsertPriceParamId(paramId))
                continue;
            
            CoProdPriceParam priceParam = getNewProdPriceParam(dmProd, paramId, param.getParam_value(), param.getValid_date(), param.getExpire_date(), paramList.getExtendParamList_Item());
            dmProdPriceParams.add(priceParam);
        }
        insertBatch(dmProdPriceParams);
    }
    
    /**
     * @Description: 组装CoProdPriceParam对象
     * @param prod
     * @param paramId
     * @param paramValue
     * @param validDate
     * @param expireDate
     * @param paramList
     * @return	 
     * @author: tangjl5
     * @Date: 2012-7-18
     */
    public CoProdPriceParam getNewProdPriceParam(CoProd prod, Integer paramId, String paramValue, String validDate, String expireDate, ExtendParam[] paramList)
    {
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        Long acctId = null;
        if (prod.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
        {
            acctId = prod.getObjectId();
        }
        else if(prod.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            acctId = context.get3hTree().loadUser3hBean(prod.getObjectId()).getAcctId();
        }
        else{
            acctId = context.get3hTree().loadGroup3hBean(prod.getObjectId()).getBillAcctId();
        }
        
        CoProdPriceParam priceParam = new CoProdPriceParam();
        priceParam.setParamValue(paramValue);
        if (paramId == SpecCodeDefine.CALC_PARA_RC_BASE_FEE)
        {
            for (ExtendParam paramEnum : paramList)
            {
                Integer measure_id = null;
                if (paramEnum.getParam_name().equals(String.valueOf(SpecCodeDefine.CALC_PARA_RC_BASE_FEE_MEASURE)))
                {
                    measure_id = CommonUtil.string2Integer(paramEnum.getParam_value());
                    break;
                }
                MeasureInfo info = amountCmp.parseBillingAmountMeasure(measure_id, CommonUtil.string2Double(paramValue), acctId);
                //@Date 2012-10-11 yugb :Bug #60997 定购产品和设置二次议价
                if(measure_id == null)
                {
                	setCoProdPriceParamValue(prod, validDate, expireDate,SpecCodeDefine.CALC_PARA_RC_BASE_FEE_MEASURE,info.getMeasureId());
                }
                priceParam.setParamValue(String.valueOf(info.getAmount()));
            }
        }else if (paramId == SpecCodeDefine.CALC_YEAR_PROM_FEE)
        {
            for (ExtendParam paramEnum : paramList)
            {
                Integer measure_id = null;
                if (paramEnum.getParam_name().equals(String.valueOf(SpecCodeDefine.CALC_YEAR_PROM_FEE_MEASURE)))
                {
                    measure_id = CommonUtil.string2Integer(paramEnum.getParam_value());
                    break;
                }
                MeasureInfo info = amountCmp.parseBillingAmountMeasure(measure_id, CommonUtil.string2Double(paramValue), acctId);
                //@Date 2012-10-11 yugb :Bug #60997 定购产品和设置二次议价
                if(measure_id == null)
                {
                	setCoProdPriceParamValue(prod, validDate, expireDate,SpecCodeDefine.CALC_YEAR_PROM_FEE_MEASURE,info.getMeasureId());
                }
                priceParam.setParamValue(String.valueOf(info.getAmount()));
            }
        }else if (paramId == SpecCodeDefine.FREE_RESOURCE_REWARD)
        {
            for (ExtendParam paramEnum : paramList)
            {
                Integer measure_id = null;
                if (paramEnum.getParam_name().equals(String.valueOf(SpecCodeDefine.FREE_RESOURCE_REWARD_MEASURE)))
                {
                    measure_id = CommonUtil.string2Integer(paramEnum.getParam_value());
                    break;
                }
                MeasureInfo info = amountCmp.parseRatingAmountMeasure(measure_id, CommonUtil.string2Double(paramValue), acctId);
                //@Date 2012-10-11 yugb :Bug #60997 定购产品和设置二次议价
                if(measure_id == null)
                {
                	setCoProdPriceParamValue(prod, validDate, expireDate,SpecCodeDefine.FREE_RESOURCE_REWARD_MEASURE,info.getMeasureId());
                }
                priceParam.setParamValue(String.valueOf(info.getAmount()));
            }
        }
        else if (paramId == SpecCodeDefine.CALC_PARA_RC_BASE_FEE_MEASURE || paramId == SpecCodeDefine.CALC_YEAR_PROM_FEE_MEASURE)
        {
            priceParam.setParamValue(String.valueOf(amountCmp.getBillingMeasureId(CommonUtil.string2Integer(paramValue), acctId)));
        }
        else if (paramId == SpecCodeDefine.FREE_RESOURCE_REWARD_MEASURE)
        {
            priceParam.setParamValue(String.valueOf(amountCmp.getBillingMeasureId(CommonUtil.string2Integer(paramValue), acctId)));
        }
        
        priceParam.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
        priceParam.setParamId(paramId);
        priceParam.setProductId(prod.getProductId());
        priceParam.setSoNbr(context.getSoNbr());
        // @Date2012-05-03 lijc3 二次议价参数设置price_plan_id
        //@Date 2012-10-08  gaoqc5 #60541  co_prod_price_param表的price_plan_id字段去掉
//        priceParam.setPricingPlanId(prod.getPricingPlanId());

        Date paramValid = IMSUtil.formatDate(validDate, null);
        Date paramExpire = IMSUtil.formatExpireDate(expireDate);
        // 如果特征值生效期为空，或早于产品生效期，取产品生效期
        if (validDate != null && paramValid != null && paramValid.after(prod.getValidDate()))
        {
            priceParam.setValidDate(IMSUtil.formatDate(validDate, null));
        }
        else
        {
            priceParam.setValidDate(prod.getValidDate());
        }
        // 如果特征值的失效期为空，或晚于产品失效期，取产品失效期
        if (expireDate != null && paramExpire != null && paramExpire.before(prod.getExpireDate()))
        {
            priceParam.setExpireDate(IMSUtil.formatExpireDate(expireDate));
        }
        else
        {
            priceParam.setExpireDate(prod.getExpireDate());
        }
        if (priceParam.getExpireDate().before(priceParam.getValidDate()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_CONFIG_OF_EXPIRE_AND_VALID_DATE);
        }

        // 2011-07-29 zengxr keep the same date with co_prod
        // priceParam.setValidDate(dmProd.getValidDate());
        // priceParam.setExpireDate(dmProd.getExpireDate());
        priceParam.setCreateDate(prod.getCreateDate());
        priceParam.setSoDate(prod.getSoDate());
        priceParam.setObjectId(prod.getObjectId());
        priceParam.setObjectType(prod.getObjectType());
        return priceParam;
    }

    /**
     * 不需要入库的二次计价参数 luojb 2012-1-17
     * 
     * @param paramId
     * @return
     */
    private boolean noInsertPriceParamId(Integer paramId)
    {
        Set<Integer> paramIds = new HashSet<Integer>();
        paramIds.add(SpecCodeDefine.GROUP_TYPE_PROD_PARAM_ID);
        paramIds.add(SpecCodeDefine.UPSELLING_FLAG);

        return paramIds.contains(paramId);
    }

    public void removeProductOrder(long prodId) throws IMSException
    {
        ProductOrderComplex complex = queryProductOrder(prodId);
        if (complex == null)
            return;
    }

    public void insertprodPriceParamBath(List<CoProdPriceParam> priceParamList)
    {
        insertBatch(priceParamList);
    }

    public void updateprodPriceParam(CoProdPriceParam newProdPriceParam)
    {
        CoProdPriceParam paramCon = new CoProdPriceParam();
        paramCon.setParamValue(newProdPriceParam.getParamValue());
        paramCon.setExpireDate(newProdPriceParam.getExpireDate());
        paramCon.setValidDate(newProdPriceParam.getValidDate());
        updateByCondition(paramCon, paramCon.getValidDate(),
                new DBCondition(CoProdPriceParam.Field.paramId, newProdPriceParam.getParamId()), new DBCondition(
                        CoProdPriceParam.Field.productId, newProdPriceParam.getProductId()));
    }

    /**
     * changeProd接口入库，不能操作主产品包括新增和删除 ljc 2011-11-1
     */
    public SProductOrderResultList operateProduct(SProductOrderOperList sProdOrderOperList) throws IMSException
    {
        if (sProdOrderOperList == null || CommonUtil.isEmpty(sProdOrderOperList.getSProductOrderOperList_Item()))
        {
            return null;
        }
        // @Date 2012-04-17 lijc3 替换主产品优化
        // 可能存在的未生效的主产品,但是又不是当前操作的主产品
        CoProd lastMainProd = null;
        SProductOrderOper[] sProOrderOpers = sProdOrderOperList.getSProductOrderOperList_Item();

        // check there main promotion delete
        int delMainPromFlag = 0;
        int addMainPromFlag = 0;
        // 统计增加和删除的普通产品数 判断是否是替换普通产品
        int tempAdd = 0;
        int tempDel = 0;
        // 是否主产品升级产品订购标识
        boolean mainProdUpsellFlag = false;
        Integer mainOfferUpsellId = null;

        // 要删除的产品列表
        List<ProductOrderComplex> delProdList = new ArrayList<ProductOrderComplex>();
        // 订购的offer列表
        List<Integer> offerIdList = new ArrayList<Integer>();
        List<Integer> addServsList = new ArrayList<Integer>();
        // caohm5--
        List<Long> productIdList = new ArrayList<Long>();
        Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();
        // --caohm5
        for (SProductOrderOper sProOrderOper : sProOrderOpers)
        {
            if (sProOrderOper == null || sProOrderOper.getProd_list() == null)
            {
                continue;
            }
            Short operFlag = sProOrderOper.getOper_type();
            switch (operFlag)
            {
            case EnumCodeDefine.CHANGE_PROD_ADD:
                for (SProductOrder order : sProOrderOper.getProd_list().getItem())
                {
                    // wjt add
                    if (!CommonUtil.isValid(order.getAcct_id()) && !CommonUtil.isValid(order.getUser_id())
                            && !CommonUtil.isValid(order.getPhone_id()))
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "acct_id,user_id,phone_id");
                    }

                    if (order.getOffer_id() == null)
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "offer_id");
                    }
                    offerIdList.add(order.getOffer_id().intValue());
                    // caohm5--
                    // 是否产品包;
                    if (order.getParent_serv_product() == null)
                    {
                        productIdList.add(order.getProduct_id());
                    }
                    else
                    {
                        if (map.get(order.getParent_serv_product()) != null)
                        {
                            List<Long> list = map.get(order.getParent_serv_product());
                            list.add(order.getOffer_id());
                            map.put(order.getParent_serv_product(), list);
                        }
                        else
                        {
                            List<Long> id = new ArrayList<Long>();
                            id.add(order.getOffer_id());
                            map.put(order.getParent_serv_product(), id);
                        }
                    }
                    // --caohm5
                    PmProductOffering offer = super.queryPmProductOfferingByOfferId(order.getOffer_id().intValue());
                    Integer busiFlag = queryBusiflag(offer.getProductOfferingId());
                    // taoyf 2012-3-9 支付帐户的用户数量是否超过MAX_SUBSCRIBER_LIMIT值
                    if (isAccountLevelOrderProduct(order) && ProjectUtil.is_Overseas())
                    {
                        if (isExceedUserLimit(order.getAcct_id(), offer.getProductOfferingId()))
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.USERS_NOT_SATISFY_SUBSCRIBER_LIMIT,
                                    order.getAcct_id(), offer.getProductOfferingId());
                        }
                    }
                    if (offer.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                    {
                        if (isAccountLevelOrderProduct(order) && ProjectUtil.is_TH_AIS())
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCT_CANOT_ORDER_MAIN_PROD);
                        }
                        if (busiFlag == SpecCodeDefine.UP_SELL)
                        {
                            mainProdUpsellFlag = true;// 主产品升级订购
                            mainOfferUpsellId = offer.getProductOfferingId();
                        }
                        addMainPromFlag++;
                        order.setProduct_type(EnumCodeDefine.PROD_TYPE_MAIN);
                        // 替换主产品 新增的主产品不能是pre_match产品 ljc
                        if (busiFlag == SpecCodeDefine.PREMATCH && ProjectUtil.is_TH_AIS())
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.TARGET_MAIN_OFFERING_IS_PRE_MATCH,
                                    offer.getProductOfferingId());
                        }
                    }
                    else
                    {
                        order.setProduct_type(EnumCodeDefine.PRODUCT_COMMON);
                        tempAdd++;
                    }
                    // For delete service life cycle
                    List<Integer> list = this.queryServiceSpecIdListByOfferId(order.getOffer_id());
                    if (CommonUtil.isNotEmpty(list))
                    {
                        addServsList.addAll(list);
                    }
                }
                break;
            case EnumCodeDefine.CHANGE_PROD_DELETE:
                for (SProductOrder order : sProOrderOper.getProd_list().getItem())
                {
                    if (order.getProduct_id() == null)
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_ID_IS_NULL);
                    }
                    // @Date2012-04-26 lijc3查询产品加上分表字段
                    CoProd prod = null;
                    if (order.getUser_id() != null || order.getAcct_id() != null || CommonUtil.isNotEmpty(order.getPhone_id()))
                    {
                        if (order.getUser_id() != null || CommonUtil.isNotEmpty(order.getPhone_id()))
                        {
                            User3hBean bean = context.get3hTree().loadUser3hBean(order.getUser_id(), order.getPhone_id());
                            prod = this.loadProd(order.getProduct_id(), bean.getUserId(), order.getAcct_id());
                        }
                        else
                        {
                            prod = this.loadProd(order.getProduct_id(), null, order.getAcct_id());
                        }
                    }
                    else
                    {
                        prod = this.loadProd(order.getProduct_id());
                    }
                    if (ProjectUtil.is_CN_SH())
                    {
                        if (CommonUtil.isEmpty(order.getExpire_date()))
                        {
                            delProdList.add(transferCoProdComplex(prod, context.getRequestDate(), order.getSo_id(),
                                    order.getValid_type()));
                        }
                        else
                        {
                            delProdList.add(transferCoProdComplex(prod, IMSUtil.formatDate(order.getExpire_date()),
                                    order.getSo_id(), order.getValid_type()));
                        }
                    }
                    else
                    {
                        delProdList.add(transferCoProdComplex(prod, null, order.getSo_id(), order.getValid_type()));
                    }
                    if (prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                    {
                        delMainPromFlag++;
                        // 替换主产品设置特殊的spec_id 供后续新增主产品的时候不判断是否订购了主产品
                        context.addExtendParam(ConstantDefine.CHANGE_MAIN_PROMOTION, context.getBusiSpecId(true));
                        // 保存旧的主产品销售品id，产品通知时使用 2012-3-27 luojb
                        context.addExtendParam(ConstantDefine.REPLACE_MAIN_PROD_OLD, prod.getProductOfferingId());
                        // 目前处理用户最多只有两个主产品，
                        // 这里肯定不会为空
                        lastMainProd = this.queryLastPrimaryProductByUserId(prod.getObjectId());
                        // 如果用户最后一个主产品是当前主产品，那么设置为null，不用第二次删除，如果不相等，说明还有一个主产品未生效，需要退订掉
                        // @Date 2012-05-30 lijc3 增加空指针判断
                        if (lastMainProd != null)
                        {
                            if (lastMainProd.getProductId().longValue() == prod.getProductId())
                            {
                                lastMainProd = null;
                            }
                            else
                            {
                                context.addExtendParam(ConstantDefine.CHANGE_MAIN_PROD_NOT_VALID, lastMainProd);
                            }
                        }
                    }
                    else
                    {
                        tempDel++;
                    }
                }
                break;
            }
        }
//        Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();
        // caom5--
        if (map.size() > 0 && productIdList.size() > 0)
        {
            for (Map.Entry<Long, List<Long>> prodEntry : map.entrySet())
            {
                if (productIdList.contains(prodEntry.getKey()))
                {
                    // 子销售品列表
                    context.addExtendParam(ConstantDefine.SON_OFFERING_ID_LIST + prodEntry.getKey(), prodEntry.getValue());
                }
            }
        }
        // --caohm5
        // 主产品升级订购
        if (mainProdUpsellFlag)
        {
            // 设置标志，不进行是否订购了主产品判断
            context.addExtendParam(ConstantDefine.CHANGE_MAIN_PROMOTION, context.getBusiSpecId(true));
        }
        // 2012-3-23 luojb 主产品失效后，允许新增主产品
        // else {
        // if (delMainPromFlag != addMainPromFlag) {
        // throw
        // IMSUtil.throwBusiException(ErrorCodeDefine.MAIN_PROMOTION_OP_ERROR);
        // }
        // }
        // 替换普通和主产品 把新增的prodorder缓存起来
        if (delMainPromFlag == 1 && addMainPromFlag == 1)
        {
            for (SProductOrderOper sProOrderOper : sProOrderOpers)
            {
                if (sProOrderOper == null || sProOrderOper.getProd_list() == null)
                    continue;
                if (sProOrderOper.getOper_type() == EnumCodeDefine.CHANGE_PROD_ADD)
                {
                    for (SProductOrder order : sProOrderOper.getProd_list().getItem())
                    {
                        if (order.getProduct_type() == EnumCodeDefine.PROD_TYPE_MAIN)
                        {
                            context.addExtendParam(ConstantDefine.REPLACE_MAIN_PROD, order);
                            break;
                        }
                    }
                }
            }
        }
        if (tempAdd == 1 && tempDel == 1)
        {
            for (SProductOrderOper sProOrderOper : sProOrderOpers)
            {
                if (sProOrderOper == null || sProOrderOper.getProd_list() == null)
                    continue;
                if (sProOrderOper.getOper_type() == EnumCodeDefine.CHANGE_PROD_ADD)
                {
                    for (SProductOrder order : sProOrderOper.getProd_list().getItem())
                    {
                        if (order.getProduct_type() == EnumCodeDefine.PRODUCT_COMMON)
                        {
                            context.addExtendParam(ConstantDefine.REPLACE_COMMON_PROD, order);
                            break;
                        }
                    }
                }
            }
        }
        // 缓存订购的销售品的列表
        context.addExtendParam(ConstantDefine.CHANGE_PROD_ADD_OFFERIDS, offerIdList);
        long start = System.currentTimeMillis();
        SProductOrderResultList prodOrderResultList = null;
        boolean flag = false;
        // 2012-04-03 zengxr get right spec id for every oper type
        short maxOperType = EnumCodeDefine.CHANGE_PROD_CPME;
        for (SProductOrderOper sProOrderOper : sProOrderOpers)
        {
            if (sProOrderOper == null || sProOrderOper.getProd_list() == null)
                continue;
            Short operFlag = sProOrderOper.getOper_type();
            // the priority : add > delete > modify > extend > change paymode >
            // 5( modify + extend + change paymode )
            if (maxOperType > operFlag.shortValue())
                maxOperType = operFlag;
            switch (operFlag)
            {
            case EnumCodeDefine.CHANGE_PROD_ADD:
                start = System.currentTimeMillis();
                imsLogger.info("*****************begin to add productorder list---------", context);
                SProductOrderResultList addResultList = this.addProductInfo(sProOrderOper.getProd_list());
                imsLogger.info("*****************end to add productorder list-----------", start, context);
                prodOrderResultList = mergerResult(prodOrderResultList, addResultList);
                break;
            case EnumCodeDefine.CHANGE_PROD_DELETE:
                if (!flag)
                {
                    flag = true;
                    start = System.currentTimeMillis();
                    imsLogger.info("*****************begin to delete productorder list---------", context);
                    SProductOrderResultList delResultList = this.deleteProductInfo(delProdList, addServsList);
                    imsLogger.info("*****************end to add productorder list--------------", start,
                            context);
                    prodOrderResultList = mergerResult(prodOrderResultList, delResultList);
                }
                break;
            case EnumCodeDefine.CHANGE_PROD_MODIFY:
                SProductOrderResultList modResultList = this.modifyProductInfo(sProOrderOper.getProd_list());
                prodOrderResultList = mergerResult(prodOrderResultList, modResultList);
                break;
            case EnumCodeDefine.CHANGE_PROD_EXTEND:
                SProductOrderResultList extResultList = this.extendProductInfo(sProOrderOper.getProd_list());
                prodOrderResultList = mergerResult(prodOrderResultList, extResultList);
                break;
            case EnumCodeDefine.CHANGE_PROD_CHANGE_PAYMODE:
                SProductOrderResultList changePayModeResultList = this.changePayModeProductInfo(sProOrderOper.getProd_list());
                prodOrderResultList = mergerResult(prodOrderResultList, changePayModeResultList);
                break;
            case EnumCodeDefine.CHANGE_PROD_CPME:
                SProductOrderResultList changePayModeAndExtendResultList = this.changePayModeExtendModifyProdList(sProOrderOper
                        .getProd_list(),operFlag);
                prodOrderResultList = mergerResult(prodOrderResultList, changePayModeAndExtendResultList);
                break;
                //2012-07-10 yangjh story：50778 增加operType=6的处理
            case EnumCodeDefine.CHNAGE_PROD_EXTEND_CMP:
            	SProductOrderResultList ExtendAndCPMResultList = this.changePayModeExtendModifyProdList(sProOrderOper
                        .getProd_list(),operFlag);
            	prodOrderResultList = mergerResult(prodOrderResultList, ExtendAndCPMResultList);
                break;
            default:
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PRODUCT_OPER_TYPE_NOEXSIT,
                        new Object[] { (Short) operFlag });
            }
        }
        // 2012-04-03 zengxr real busi spec id for one time fee & reward.
        this.getContext().addExtendParam(EnumCodeDefine.ACTION_SPEC_ID_KEY,
                this.getContext().getBusiSpecId(true, Integer.valueOf(maxOperType)));
        // 主产品升级订购 则从context里面取出IMS3Hbean,如果没有不做操作
        if (mainProdUpsellFlag)
        {
            delProdList = new ArrayList<ProductOrderComplex>();
            IMS3hBean bean = context.getMain3hBean();
            if (bean != null)
            {
                ImsUpsellReq upsellReq = checkUpSell(bean.getUserId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV, mainOfferUpsellId);
                CoProd dmProd = this.loadProd(upsellReq.getProdId(), bean.getUserId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV);
                delProdList.add(transferCoProdComplex(dmProd, context.getRequestDate(), null, null));
                SProductOrderResultList delResultList = this.deleteProductInfo(delProdList, addServsList);
                prodOrderResultList = mergerResult(prodOrderResultList, delResultList);
            }
        }
        // 退订未生效的主产品，需要立即失效
        if (lastMainProd != null)
        {
            delProdList = new ArrayList<ProductOrderComplex>();
            delProdList.add(transferCoProdComplex(lastMainProd, context.getRequestDate(), null, null));
            // 将可能存在的主产品的失效时间设置为立即失效
            context.addExtendParam(ConstantDefine.REPLACE_MAIN_PROD_ADD_VALID_DATE, context.getRequestDate());
            SProductOrderResultList delResultList = this.deleteProductInfo(delProdList, addServsList);
            prodOrderResultList = mergerResult(prodOrderResultList, delResultList);
        }

        return prodOrderResultList;

    }

    public SProductOrderResultList mergerResult(SProductOrderResultList src, SProductOrderResultList returnResult)
    {
        SProductOrderResultList prodOrderResultList = new SProductOrderResultList();
        if (src == null || CommonUtil.isEmpty(src.getSProductOrderResultList_Item()))
        {
            return returnResult;
        }
        if (returnResult == null || CommonUtil.isEmpty(returnResult.getSProductOrderResultList_Item()))
        {
            return src;
        }

        int srcLen = src.getSProductOrderResultList_Item().length;
        int returnResultLen = returnResult.getSProductOrderResultList_Item().length;
        int resultLen = srcLen + returnResultLen;
        SProductOrderResult[] result = new SProductOrderResult[resultLen];
        System.arraycopy(src.getSProductOrderResultList_Item(), 0, result, 0, src.getSProductOrderResultList_Item().length);
        System.arraycopy(returnResult.getSProductOrderResultList_Item(), 0, result, src.getSProductOrderResultList_Item().length,
                returnResult.getSProductOrderResultList_Item().length);
        prodOrderResultList.setSProductOrderResultList_Item(result);
        return prodOrderResultList;
    }

    public CoProd modifyProduct(SProductOrder prod, List<CoProd> rewardList)
    {
        // 增加处理非空bug
        if (prod.getProduct_id() == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "product_id");
        }
        CoProd oldProd = this.queryProd(prod.getProduct_id());
        if(oldProd == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, prod.getProduct_id());
        }
        // 产品规格
        modifyProductParamList(prod, oldProd);

        // modify price plan id
        List<CoProdPriceParam> priceParams = queryProdPriceParam(prod.getProduct_id());

        // modify extendparam
        ExtendParamList extendParamList = prod.getReguid_price_param();
        if (extendParamList != null && !CommonUtil.isEmpty(extendParamList.getExtendParamList_Item()))
        {
            for (ExtendParam ep : extendParamList.getExtendParamList_Item())
            {
                if (CommonUtil.isEmpty(ep.getParam_name()) || CommonUtil.isEmpty(ep.getParam_value()))
                {
                    imsLogger.info("************continue,because param_name or param_value is null");
                    continue;
                }
                Integer paramId = Integer.parseInt(ep.getParam_name());
                if (priceParamIdIsInParamList(paramId, priceParams))
                {
                    CoProdPriceParam paramCon = new CoProdPriceParam();
                    //@Date 2012-10-08  gaoqc5 #60541  co_prod_price_param表的price_plan_id字段去掉
//                    paramCon.setPricingPlanId(pricePlanId);
                    paramCon.setParamValue(ep.getParam_value());
                    updateByCondition(paramCon,
                            new DBCondition(CoProdPriceParam.Field.productId, oldProd.getProductId()),
                            new DBCondition(CoProdPriceParam.Field.paramId, Integer.parseInt(ep.getParam_name())), new DBCondition(
                                    CoProdPriceParam.Field.objectId, oldProd.getObjectId()), new DBCondition(
                                    CoProdPriceParam.Field.objectType, oldProd.getObjectType()));
                }
                else
                {
                    CoProdPriceParam prodPrice = new CoProdPriceParam();
                    //@Date 2012-10-08  gaoqc5 #60541  co_prod_price_param表的price_plan_id字段去掉
//                    prodPrice.setPricingPlanId(pricePlanId);
                    prodPrice.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
                    prodPrice.setCreateDate(context.getRequestDate());
                    prodPrice.setExpireDate(oldProd.getExpireDate());
                    prodPrice.setParamId(Integer.parseInt(ep.getParam_name()));
                    prodPrice.setParamValue(ep.getParam_value());
                    prodPrice.setSoDate(context.getRequestDate());
                    prodPrice.setSoNbr(context.getSoNbr());
                    prodPrice.setObjectId(oldProd.getObjectId());
                    prodPrice.setObjectType(oldProd.getObjectType());
                    prodPrice.setProductId(oldProd.getProductId());
                    prodPrice.setValidDate(context.getRequestDate());
                    this.insert(prodPrice);
                }
            }
        }


        return oldProd;
    }

    // 判断参数id是否存在于列表中
    private boolean priceParamIdIsInParamList(Integer paramId, List<CoProdPriceParam> paramList)
    {
        if (CommonUtil.isEmpty(paramList))
        {
            return false;
        }
        for (CoProdPriceParam param : paramList)
        {
            if (param.getParamId().intValue() == paramId)
            {
                return true;
            }
        }
        return false;

    }

    /**
     * 修改product order 的param list信息
     */
    private SProductOrderResultList modifyProductInfo(SProductOrderList prodList)
    {
        SProductOrderResultList prodResutList = new SProductOrderResultList();
        if (prodList == null || CommonUtil.isEmpty(prodList.getItem()))
        {
            return prodResutList;
        }

        // modify product reward
        List<CoProd> rewardList = new ArrayList<CoProd>();
        SProductOrderResult[] prodResults = new SProductOrderResult[prodList.getItem().length];
        SProductOrderList packOrderList = new SProductOrderList();
        for (int i = 0; i < prodList.getItem().length; i++)
        {
            SProductOrder prod = prodList.getItem()[i];
            CoProd oldProd = modifyProduct(prod, rewardList);

            handleOfferRel(oldProd, prod, EnumCodeDefine.CHANGE_PROD_MODIFY, null, null, packOrderList);

            SProductOrderResult prodResult = new SProductOrderResult();
            prodResult.setOffer_id(prod.getOffer_id());
            prodResult.setProduct_id(prod.getProduct_id());
            String soId = prod.getSo_id();
            soId = soId == null ? String.valueOf(context.getSoNbr()) : soId;
            prodResult.setSo_id(soId);
            prodResult.setExpire_date(DateUtil.formatDate(oldProd.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            prodResult.setValid_date(DateUtil.formatDate(oldProd.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            prodResult.setProduct_param_list(prod.getParam_list());
            // prodResults[i++] = prodResult;
            prodResults[i] = prodResult;
        }
        // 返回结果进行合并
        mergtSProductOrderList(prodList, packOrderList);
        prodResutList.setSProductOrderResultList_Item(prodResults);

        // 产品reward ljc
        if (SysUtil.getBoolean(SysCodeDefine.busi.DO_REWARD, true))
        {
            this.doProductReward(rewardList);
        }
        // 上发通知ts 上海不需要
        if (!ProjectUtil.is_CN_SH())
        {
            List<SProdNotify> sProdNotifyList = this.parseProdNotifyList(prodResutList, EnumCodeDefine.CHANGE_PROD_MODIFY);
            this.prodNotifyUpload(sProdNotifyList);
        }

        return prodResutList;
    }

    private void modifyProductParamList(SProductOrder prod, CoProd coProd)
    {
        if (coProd.getBusiFlag().intValue() == SpecCodeDefine.FRIENDNUMBER)
        {
            modifyProductParamList_fn(prod, coProd);
            return;
        }
        // all old spec char values should be replaced by new char values
        // first query all old char values
        List<CoProdCharValue> oldList = this.queryProdCharValue(prod.getProduct_id());
        // create all new char values
        List<CoProdCharValue> newList = this.createProdCharValueNoSave(prod.getParam_list(), coProd, coProd.getBusiFlag(),
                context.getRequestDate(), coProd.getExpireDate());
        
        // @Date 2012-08-13 tangjl5 :Story # 51473 修改子产品对应特殊特征值时，需要通过主产品的id来操作
        if (CommonUtil.isEmpty(newList))
        {
            return;
        }

        // split old char values to update list and delete list
        List<CoProdCharValue> uplist = new ArrayList<CoProdCharValue>();
        List<CoProdCharValue> dellist = new ArrayList<CoProdCharValue>();
        // @Date 2012-05-22 lijc3修改产品特征值增加空判断
        if (CommonUtil.isEmpty(newList))
        {
            // 新的为空，全部删除
            if (CommonUtil.isNotEmpty(oldList))
            {
                dellist.addAll(oldList);
            }
        }
        else
        {
            if (CommonUtil.isNotEmpty(oldList))
            {
                for (CoProdCharValue oldCharVal : oldList)
                {
                    CoProdCharValue resCharVal = null;
                    for (Iterator i = newList.iterator(); i.hasNext();)
                    {
                        CoProdCharValue newCharVal = (CoProdCharValue) i.next();
                        if (oldCharVal.getSpecCharId().equals(newCharVal.getSpecCharId()))
                        {
                            uplist.add(newCharVal);
                            resCharVal = newCharVal;
                            i.remove();
                            break;
                        }
                    }
                    if (resCharVal == null)
                    {
                        dellist.add(oldCharVal);
                    }
                }
            }
        }
        // delete all char values that not exits in new list
        for (CoProdCharValue charval : dellist)
        {
            // CoProdCharValue del = new CoProdCharValue();
            // del.setProductId(charval.getProductId());
            // del.setSpecCharId(charval.getSpecCharId());
            // del.setObjectId(coProd.getObjectId());
            this.deleteByCondition(CoProdCharValue.class,
                    new DBCondition(CoProdCharValue.Field.productId, charval.getProductId()), new DBCondition(
                            CoProdCharValue.Field.specCharId, charval.getSpecCharId()), new DBCondition(
                            CoProdCharValue.Field.objectId, coProd.getObjectId()));
            // @Date 2012-4-6 tangjl5 #43383
            // 解决修改MultiSim特质值，未修改CM_RES_IDENTITY记录的问题
            if (charval.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER)
            {
                // CmResIdentity resIdentity = new CmResIdentity();
                // resIdentity.setIdentity(charval.getValue());
                // resIdentity.setResourceId(coProd.getObjectId());
                // resIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM);
                this.deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identity, charval.getValue()),
                        new DBCondition(CmResIdentity.Field.resourceId, coProd.getObjectId()), new DBCondition(
                                CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM));
            }
            if (charval.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
            {
                // CmResIdentity resIdentity = new CmResIdentity();
                // resIdentity.setIdentity(charval.getValue());
                // resIdentity.setResourceId(coProd.getObjectId());
                // resIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER);
                this.deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identity, charval.getValue()),
                        new DBCondition(CmResIdentity.Field.resourceId, coProd.getObjectId()), new DBCondition(
                                CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER));
            }
        }
        // update all char values exits in new list
        for (CoProdCharValue charval : uplist)
        {
            // CoProdCharValue up = new CoProdCharValue();
            // up.setProductId(charval.getProductId());
            // up.setSpecCharId(charval.getSpecCharId());
            // up.setObjectId(coProd.getObjectId());
            // should use valid_date of new record
            this.updateByCondition(charval, charval.getValidDate(),
                    new DBCondition(CoProdCharValue.Field.productId, charval.getProductId()), new DBCondition(
                            CoProdCharValue.Field.specCharId, charval.getSpecCharId()), new DBCondition(
                            CoProdCharValue.Field.objectId, coProd.getObjectId()));

            // @Date 2012-4-6 tangjl5 #43383
            // 解决修改MultiSim特质值，未修改CM_RES_IDENTITY记录的问题
            if (charval.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER)
            {
                CmResIdentity resIdentity = new CmResIdentity();
                resIdentity.setIdentity(charval.getValue());
                // 2012-10-16 luojb serial_no一并修改
                try
                {
                    List<CoProdCharValue> value = IMSUtil.matchDataObject(uplist, 
                            new CacheCondition(CoProdCharValue.Field.productId,charval.getProductId()),
                            new CacheCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MULTI_SIM_SERIAL_NO),
                            new CacheCondition(CoProdCharValue.Field.groupId,charval.getGroupId()));
                    if(value != null)
                        resIdentity.setSerialNo(value.get(0).getValue());
                
                }
                catch (Exception e)
                {
                    imsLogger.debug(e);
                }
                //2012-10-16 luojb 更新的条件里必须要有old sim卡号，否则用户的所有sim卡都会被更新
                String oldSim = null;
                try
                {
                    List<CoProdCharValue> oldSimValue = IMSUtil.matchDataObject(oldList, new CacheCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MULTI_SIM_NUMBER));
                    oldSim = oldSimValue.get(0).getValue();//ais一个产品只对应一条sim
                }
                catch (Exception e)
                {
                    imsLogger.debug(e);
                }
                this.updateByCondition(resIdentity, new DBCondition(CmResIdentity.Field.resourceId, coProd.getObjectId()),
                        new DBCondition(CmResIdentity.Field.identity,oldSim),
                        new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM));
            }

            // @Date 2012-4-6 tangjl5 #43383
            // 解决修改Fax_num特质值，未修改CM_RES_IDENTITY记录的问题
            if (charval.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
            {
                CmResIdentity resIdentity = new CmResIdentity();
                resIdentity.setIdentity(charval.getValue());
                resIdentity.setRelIdentity(charval.getValue());
                this.updateByCondition(resIdentity, new DBCondition(CmResIdentity.Field.resourceId, coProd.getObjectId()),
                        new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER));
            }
        }
        // add all char values no old record
        if (newList!=null && newList.size() > 0)
        {
            this.insertBatch(newList);

            for (CoProdCharValue charval : newList)
            {
                // @Date 2012-4-6 tangjl5 #43383
                // 解决修改MultiSim特质值，未修改CM_RES_IDENTITY记录的问题
                if (charval.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER)
                {
                    CmResIdentity resIdentity = new CmResIdentity();
                    resIdentity.setIdentity(charval.getValue());
                  //@Date 2012-10-29 yangjh : User Story #62685 MultiSIM的SIM卡改成两个字段填写同样的值
                    resIdentity.setRelIdentity(charval.getValue());
                    resIdentity.setResourceId(coProd.getObjectId());
                    resIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM);
                    resIdentity.setValidDate(charval.getValidDate());
                    resIdentity.setExpireDate(charval.getExpireDate());
                    // 2012-10-16 luojb serial_no和sim_num是在同一条记录里的
                    try
                    {
                        List<CoProdCharValue> value = IMSUtil.matchDataObject(newList, 
                                new CacheCondition(CoProdCharValue.Field.productId,charval.getProductId()),
                                new CacheCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MULTI_SIM_SERIAL_NO),
                                new CacheCondition(CoProdCharValue.Field.groupId,charval.getGroupId()));
                        if(value != null)
                            resIdentity.setSerialNo(value.get(0).getValue());
                    }
                    catch (Exception e)
                    {
                        imsLogger.debug(e);
                    }
                    this.insert(resIdentity);
                }
                // @Date 2012-4-6 tangjl5 #43383
                // 解决修改Fax_num特质值，未修改CM_RES_IDENTITY记录的问题
                if (charval.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
                {
                    CmResIdentity resIdentity = new CmResIdentity();
                    resIdentity.setIdentity(charval.getValue());
                  //@Date 2012-10-29 yangjh : User Story #62685 Fax也改成两个字段填写同样的值
                    resIdentity.setRelIdentity(charval.getValue());
                    resIdentity.setResourceId(coProd.getObjectId());
                    resIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER);
                    resIdentity.setValidDate(charval.getValidDate());
                    resIdentity.setExpireDate(charval.getExpireDate());
                    this.insert(resIdentity);
                }
            }
        }
    }

    private void modifyProductParamList_fn(SProductOrder prod, CoProd coProd)
    {
        // all old spec char values should be replaced by new char values
        // first query all old char values
        List<CoFnCharValue> oldList = this.queryFnCharValue(prod.getProduct_id());
        // create all new char values
        List<CoProdCharValue> tmpList = this.createProdCharValueNoSave(prod.getParam_list(), coProd, coProd.getBusiFlag(),
                context.getRequestDate(), coProd.getExpireDate());
        // 没有修改 直接返回 ljc
        if (CommonUtil.isEmpty(tmpList))
        {
            return;
        }
        List<CoFnCharValue> newList = ConvertUtil.coProdCharValues2CoFnCharValues(tmpList);
        // split old char values to update list and delete list
        List<CoFnCharValue> uplist = new ArrayList<CoFnCharValue>();
        List<CoFnCharValue> dellist = new ArrayList<CoFnCharValue>();
        for (CoFnCharValue oldCharVal : oldList)
        {
            CoFnCharValue resCharVal = null;
            for (CoFnCharValue newCharVal : newList)
            {
                if (oldCharVal.getSpecCharId().equals(newCharVal.getSpecCharId()))
                {
                    uplist.add(newCharVal);
                    resCharVal = newCharVal;
                    newList.remove(newCharVal);
                    break;
                }
            }
            if (resCharVal == null)
            {
                dellist.add(oldCharVal);
            }
        }
        // delete all char values that not exits in new list
        for (CoFnCharValue charval : dellist)
        {
            this.deleteByCondition(CoFnCharValue.class, new DBCondition(CoFnCharValue.Field.productId, charval.getProductId()),
                    new DBCondition(CoFnCharValue.Field.specCharId, charval.getSpecCharId()), new DBCondition(
                            CoFnCharValue.Field.objectId, coProd.getObjectId()));
            // @Date 2012-4-6 tangjl5 #43383
            // 解决修改MultiSim特质值，未修改CM_RES_IDENTITY记录的问题
            if (charval.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER)
            {
                this.deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identity, charval.getValue()),
                        new DBCondition(CmResIdentity.Field.resourceId, coProd.getObjectId()), new DBCondition(
                                CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM));
            }
            if (charval.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
            {
                this.deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identity, charval.getValue()),
                        new DBCondition(CmResIdentity.Field.resourceId, coProd.getObjectId()), new DBCondition(
                                CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER));
            }
        }
        // update all char values exits in new list
        for (CoFnCharValue charval : uplist)
        {
            this.updateByCondition(charval, charval.getValidDate(),
                    new DBCondition(CoFnCharValue.Field.productId, charval.getProductId()), new DBCondition(
                            CoFnCharValue.Field.specCharId, charval.getSpecCharId()), new DBCondition(
                            CoFnCharValue.Field.objectId, coProd.getObjectId()));

            // @Date 2012-4-6 tangjl5 #43383
            // 解决修改MultiSim特质值，未修改CM_RES_IDENTITY记录的问题
            if (charval.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER)
            {
                CmResIdentity resIdentity = new CmResIdentity();
                resIdentity.setIdentity(charval.getValue());
                //@Date 2012-10-29 yangjh : User Story #62685 Fax也改成两个字段填写同样的值
                resIdentity.setRelIdentity(charval.getValue());
                this.updateByCondition(resIdentity, new DBCondition(CmResIdentity.Field.resourceId, coProd.getObjectId()),
                        new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM));
            }

            // @Date 2012-4-6 tangjl5 #43383
            // 解决修改Fax_num特质值，未修改CM_RES_IDENTITY记录的问题
            if (charval.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
            {
                CmResIdentity resIdentity = new CmResIdentity();
                resIdentity.setIdentity(charval.getValue());
                //@Date 2012-10-29 yangjh : User Story #62685 Fax也改成两个字段填写同样的值
                resIdentity.setRelIdentity(charval.getValue());
                this.updateByCondition(resIdentity, new DBCondition(CmResIdentity.Field.resourceId, coProd.getObjectId()),
                        new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER));
            }
        }
        // add all char values no old record
        if (newList!=null && newList.size() > 0)
        {
            this.insertBatch(newList);

            for (CoFnCharValue charval : newList)
            {
                // @Date 2012-4-6 tangjl5 #43383
                // 解决修改MultiSim特质值，未修改CM_RES_IDENTITY记录的问题
                if (charval.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER)
                {
                    CmResIdentity resIdentity = new CmResIdentity();
                    resIdentity.setIdentity(charval.getValue());
                    //@Date 2012-10-29 yangjh : User Story #62685 MultiSIM的SIM卡改成两个字段填写同样的值
                    resIdentity.setRelIdentity(charval.getValue());
                    resIdentity.setResourceId(coProd.getObjectId());
                    resIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM);
                    resIdentity.setValidDate(charval.getValidDate());
                    resIdentity.setExpireDate(charval.getExpireDate());
                    this.insert(resIdentity);
                }
                // @Date 2012-4-6 tangjl5 #43383
                // 解决修改Fax_num特质值，未修改CM_RES_IDENTITY记录的问题
                if (charval.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
                {
                    CmResIdentity resIdentity = new CmResIdentity();
                    //@Date 2012-10-29 yangjh : User Story #62685 Fax也改成两个字段填写同样的值
                    resIdentity.setRelIdentity(charval.getValue());
                    resIdentity.setIdentity(charval.getValue());
                    resIdentity.setResourceId(coProd.getObjectId());
                    resIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER);
                    resIdentity.setValidDate(charval.getValidDate());
                    resIdentity.setExpireDate(charval.getExpireDate());
                    this.insert(resIdentity);
                }
            }
        }
    }

    /**
     * @Date 20110809增加了返回值
     * @Description: 延长一组产品的有效期至
     * @param prodList
     * @return
     */
    private SProductOrderResultList extendProductInfo(SProductOrderList prodList)
    {
        // ljc extend prod do reward
        List<CoProd> rewardList = new ArrayList<CoProd>();
        SProductOrderResultList prodResutList = new SProductOrderResultList();
        if (prodList == null || CommonUtil.isEmpty(prodList.getItem()))
        {
            return prodResutList;
        }
        SProductOrderResult[] prodResults = new SProductOrderResult[prodList.getItem().length];
        SProductOrderList packOrderList = new SProductOrderList();
        for (int i = 0; i < prodList.getItem().length; i++)
        {
            SProductOrder prod = (SProductOrder) prodList.getItem()[i];
            // userIsAvailable(prod.getProduct_id());
            CoProd dmProd = this.extendExpireDate(prod, prod.getExpire_date());

            SProductOrderResult prodResult = new SProductOrderResult();
            prodResult.setOffer_id(prod.getOffer_id());
            prodResult.setProduct_id(prod.getProduct_id());
            String soId = prod.getSo_id();
            soId = soId == null ? String.valueOf(context.getSoNbr()) : soId;
            prodResult.setSo_id(soId);
            if (dmProd != null)
            {
                prodResult.setExpire_date(DateUtil.formatDate(dmProd.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                prodResult.setValid_date(DateUtil.formatDate(dmProd.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            prodResult.setProduct_param_list(prod.getParam_list());
            prodResults[i] = prodResult;
            // ljc 3.19增加空判断
            if (dmProd != null)
            {
                if (!SpecCodeDefine.isSpecialProd(dmProd.getBusiFlag()))
                {
                    rewardList.add(dmProd);
                }
            }
        }
        // 返回结果进行合并
        mergtSProductOrderList(prodList, packOrderList);
        prodResutList.setSProductOrderResultList_Item(prodResults);
        // 产品reward ljc
        if (SysUtil.getBoolean(SysCodeDefine.busi.DO_REWARD, true))
        {
            this.doProductReward(rewardList);
        }
        return prodResutList;
    }

    /**
     * @Date 2011-08-09 延长服务有效期至做了修改。如果服务的有效期大于crm传入的有效期，不做修改
     * @Description: 延长产品有效期至
     * @param prod
     * @param expireDate
     */
    private CoProd extendExpireDate(SProductOrder prod, String expireDate)
    {
        // 修改&& 为|| 只要一个不满足就返回
        if (CommonUtil.isEmpty(expireDate) || prod == null || !CommonUtil.isValid(prod.getProduct_id()))
        {
            return null;
        }
        Date expire = IMSUtil.formatDate(expireDate, null);
        // 增加处理非空bug
        if (prod.getProduct_id() == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "product_id");
        }
        Long prodId = prod.getProduct_id();
        CoProd dmProd = this.queryProd(prodId);
        if (dmProd == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_NOT_USER_PRODUCT, prodId);// prodId
        }
        PmProductOfferLifecycle dmLifeCycle = queryProdOfferLifeCycle(dmProd.getProductOfferingId());
        if (dmLifeCycle != null)
        {
            if (dmLifeCycle.getValidType() == EnumCodeDefine.PROD_VALID_FIXED_EXPIRE_TYPE)
            {
                Date fixed_expire_date = dmLifeCycle.getFixedExpireDate();
                Date formatDate = IMSUtil.formatDate(expireDate, null);
                if (formatDate.after(fixed_expire_date))
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_EXPIRE_DATE_AFTER_FIXED_EXPIRE_DATE, formatDate,
                            fixed_expire_date);
                }

            }
        }
        // 上海不需要
        if (ProjectUtil.is_TH_AIS() && dmProd.getLifecycleStatusId().intValue() == EnumCodeDefine.PROD_LIFECYCLE_SUSPEND)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PROD_IS_SUSPEND, dmProd.getProductId());
        }
        // 不能延长超过销售品的失效期
        PmProductOffering offer = super.queryPmProductOfferingByOfferId(dmProd.getProductOfferingId());
        if (expire.after(offer.getExpireDate()))
        {
            expire = new Date(offer.getExpireDate().getTime());
        }
        this.extendProdExpireDate(dmProd, expire, null, null);
        // 延长产品规格特征 expire date
        CoProdCharValue prodCharValVal = new CoProdCharValue();
        // CoProdCharValue prodCharValWhere = new CoProdCharValue();
        prodCharValVal.setExpireDate(expire);
        // prodCharValWhere.setProductId(prod.getProduct_id());
        // prodCharValWhere.setObjectId(dmProd.getObjectId());
        updateByCondition(prodCharValVal, new DBCondition(CoProdCharValue.Field.productId, prod.getProduct_id()),
                new DBCondition(CoProdCharValue.Field.objectId, dmProd.getObjectId()));

        // lijc 2012-03-19 extend增加返回值
        if (expire != null)
        {
            dmProd.setExpireDate(expire);
        }
        // 上发通知ts
        List<SProdNotify> sProdNotifyList = new ArrayList<SProdNotify>();
        sProdNotifyList.add(this.parseProdNotify(dmProd, EnumCodeDefine.CHANGE_PROD_EXTEND));
        this.prodNotifyUpload(sProdNotifyList);
        return dmProd;
    }

    /**
     * @Date 2011-08-09 增加了方法的返回值
     * @Description: 删除一组产品
     * @param prodList
     * @return
     */
    public SProductOrderResultList deleteProductInfo(List<ProductOrderComplex> delProdList, List<Integer> addServsList)
    {
        SProductOrderResultList prodResutList = new SProductOrderResultList();
        if (delProdList.size() == 0)
        {
            return prodResutList;
        }
        List<Long> prodIds = new ArrayList<Long>();
        List<Integer> delOfferIds = new ArrayList<Integer>();
        for (ProductOrderComplex complex : delProdList)
        {
            prodIds.add(complex.getCoProd().getProductId());
            delOfferIds.add(complex.getCoProd().getProductOfferingId());
        }
        context.addExtendParam(ConstantDefine.CHANGE_PROD_DEL_OFFERIDS, delOfferIds);
        context.addExtendParam(ConstantDefine.DELETE_PRODIDS, prodIds);
        List<SProductOrderResult> prodResults = new ArrayList<SProductOrderResult>();
        SProductOrderList packOrderList = new SProductOrderList();
        // SProductOrderResult[] prodResults = new
        // SProductOrderResult[delProdList.size()];
        for (Iterator i = delProdList.iterator(); i.hasNext();)
        {
            ProductOrderComplex complex = (ProductOrderComplex) i.next();
            // SProductOrder prod = (SProductOrder) prodList.getItem()[i];
            this.deleteProdOrder(complex, addServsList);
            CoProd dmProd = complex.getCoProd();
            // 预付费预扣产品需要重新退费 设置标志
//            List<CoProd> prodList = handleOfferRel(dmProd, null, EnumCodeDefine.CHANGE_PROD_DELETE, null, addServsList,
//                    packOrderList);
            SProductOrderResult prodResult = new SProductOrderResult();
            prodResult.setOffer_id(dmProd.getProductOfferingId().longValue());
            prodResult.setProduct_id(dmProd.getProductId());
            String soId = complex.getSoId();
            soId = soId == null ? String.valueOf(context.getSoNbr()) : soId;
            prodResult.setSo_id(soId);
            if (dmProd != null)
            {
                prodResult.setExpire_date(DateUtil.formatDate(dmProd.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                prodResult.setValid_date(DateUtil.formatDate(dmProd.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            // prodResult.setProduct_param_list(prod.getParam_list());
            prodResults.add(prodResult);
        }
        prodResutList.setSProductOrderResultList_Item(prodResults.toArray(new SProductOrderResult[prodResults.size()]));
        return prodResutList;
    }


    public void deleteProdOrder(SProductOrder prod) throws IMSException
    {
        CoProd dmProd = queryProd(prod.getProduct_id());
        if (dmProd != null)
        {
            deleteProdOrder(transferCoProdComplex(dmProd, null, null, null), new ArrayList<Integer>());
        }
    }

    public void deleteProdImmediate(CoProd dmProd)
    {
        deleteProdOrder(transferCoProdComplex(dmProd, context.getRequestDate(), null, null), new ArrayList<Integer>());
    }

    public void deleteProd(CoProd dmProd)
    {
        deleteProdOrder(transferCoProdComplex(dmProd, null, null, null), new ArrayList<Integer>());
    }

    private void insertCmResLastIcs(Long objectId, CoProd coProd)
    {
          List<CmResLastIcs> ics = query(CmResLastIcs.class, new DBCondition(CmResLastIcs.Field.resourceId, objectId));
          if (CommonUtil.isNotEmpty(ics))
          {
               super.deleteByCondition_noInsert(CmResLastIcs.class, null, new DBCondition(CmResLastIcs.Field.resourceId,
            		   objectId));
          }
          //2012-06-18 yangjh 直接传入coProd，并将插入cmResLastIcs表放到第一次遍历里面
//            CoProd coProd = super.queryCoProdByUserId(objectId);
//            if (prod != null)
//            {
          List<CoProdCharValue> charValues = this.query(CoProdCharValue.class, new DBCondition(
                    CoProdCharValue.Field.productId, coProd.getProductId()));
                       /* new DBCondition(CoProdCharValue.Field.objectId,
                        objectId), new DBCondition(CoProdCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV)); */
          if (CommonUtil.isEmpty(charValues))
                   return;
//          List<SCallScreenNo> scns = new ArrayList<SCallScreenNo>();
          if (!CommonUtil.isEmpty(charValues))
          {
               for (CoProdCharValue value : charValues)
               {
                   if (value.getSpecCharId().intValue() == SpecCodeDefine.MCS_NUMBER_NUMBER)
                   {// 获取号码
                        SCallScreenNo no = new SCallScreenNo();
                        no.setPhone_id(value.getValue());
                        for (CoProdCharValue charValue : charValues)
                        {
                            if (charValue.getGroupId().longValue() == value.getGroupId().longValue())
                            {
                                switch (charValue.getSpecCharId().intValue())
                                {
                                    case SpecCodeDefine.MCS_NUMBER_ROUTE_FLAG:
                                        no.setIr_route_flag(Short.valueOf(charValue.getValue()));
                                        break;
                                    case SpecCodeDefine.MCS_NUMBER_ROUTE_NUMBER:
                                        no.setRoute_number(charValue.getValue());// 设置转移
                                        break;
                                    case SpecCodeDefine.MCS_NUMBER_ROUTE_METHOD:
                                        no.setRouting_method(Short.valueOf(charValue.getValue()));// 获取rout_method
                                        break;
                                    case SpecCodeDefine.MCS_NUMBER_WEEK_END:
                                        no.setWeek_stop(Short.valueOf(charValue.getValue()));
                                        break;
                                    case SpecCodeDefine.MCS_NUMBER_WEEK_START:
                                        no.setWeek_start(Short.valueOf(charValue.getValue()));
                                        break;
                                    case SpecCodeDefine.MCS_NUMBER_EFFECT_TIME:
                                        no.setEffect_time(charValue.getValue());
                                        break;
                                    case SpecCodeDefine.MCS_NUMBER_EXPIRE_TIME:
                                        no.setExpire_time(charValue.getValue());
                                        break;
                                    case SpecCodeDefine.MCS_NUMBER_TYPE:
                                        no.setList_type(Short.valueOf(charValue.getValue()));
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
//                        scns.add(no);
                        CmResLastIcs lastIcs = new CmResLastIcs();
                        lastIcs.setResourceId(objectId);
                        lastIcs.setExpireDate(coProd.getExpireDate());
                        lastIcs.setValidDate(coProd.getValidDate());
                        lastIcs.setCreateDate(coProd.getCreateDate());
                        lastIcs.setSoDate(coProd.getSoDate());
                        lastIcs.setSoNbr(coProd.getSoNbr());
                        if (no.getList_type() != null)
                        {
                            lastIcs.setCallScreenNoType(CommonUtil.ShortToInteger(no.getList_type()));
                            lastIcs.setListType(CommonUtil.ShortToInteger(no.getList_type()));
                        }
                        lastIcs.setEffectTime(no.getEffect_time());
                        lastIcs.setExpireTime(no.getExpire_time());
                        lastIcs.setIrRouteFlag(CommonUtil.ShortToInteger(no.getIr_route_flag()));
                        if (CommonUtil.isValid(no.getRoute_number()))
                        {
                            lastIcs.setRouteNumber(no.getRoute_number());
                        }
                        if (CommonUtil.isValid(no.getRouting_method()))
                        {
                            lastIcs.setRoutingMethod(CommonUtil.ShortToInteger(no.getRouting_method()));
                        }
                        lastIcs.setWeekStart(CommonUtil.ShortToInteger(no.getWeek_start()));
                        lastIcs.setWeekStop(CommonUtil.ShortToInteger(no.getWeek_stop()));

                        lastIcs.setPhoneId(no.getPhone_id());
                        super.insert(lastIcs);
                    }
                }
            }
    }

    /**
     * @Description: 最底层的删除产品方法
     */
    private void deleteProdOrder(ProductOrderComplex complex, List<Integer> addServsList) throws IMSException
    {
        CoProd dmProd = complex.getCoProd();
        Long prodId = dmProd.getProductId();
        
        // 获取失效时间
        Date expireDate = complex.getExpireDate();
        if (expireDate == null)
        {
            expireDate = context.getRequestDate();
        }
        // 如果是附加退订未生效的主产品 则失效时间可以为当前产品的失效时间还大
        if (expireDate.after(dmProd.getExpireDate()) && context.getExtendParam(ConstantDefine.CHANGE_MAIN_PROD_NOT_VALID) == null)
        {
            return;
        }
        imsLogger.info("***************expire_date=" + expireDate);
        // 初始化3hbean
        this.deleteProdById(prodId, expireDate);

        // delete prodCharValue
        this.deleteProdCharValueByProdId(prodId, expireDate);

        // 亲情号码产品 还要退订CO_FN_CHAR_VALUE
        if (dmProd.getBusiFlag() == SpecCodeDefine.FRIENDNUMBER)
        {
            this.deleteProdFnCharValue(prodId, expireDate);
        }
        if (dmProd.getBusiFlag() == SpecCodeDefine.SPLIT)
        {
            this.deleteProdSplitCharValue(prodId, expireDate);
        }

        // delete prod price param
        this.deleteProdParamPriceByProdId(prodId, expireDate);
        
        this.modifyProdValid(prodId, expireDate);

        // delete prod billing cycle
        context.getComponent(ProductCycleComponent.class).deleteProdBillingCycleByProdId(prodId, expireDate);
        // 如果是群个性化产品，则要看是否需要退出comm 上海需求不需要

        // 设置失效时间 供返回resp使用
        dmProd.setExpireDate(expireDate);
    }
    
    protected void cancelReward(Long bosSoNbr,Long acctId,Date soDate)
    {
       

    }
    
    /**
     * 根据产品失效时间，计算最后一个周期的开始日
     * luojb 2012-7-6
     * @param prodId
     * @param expireDate
     * @return
     */
    public Date calculateLastBillDate(Long prodId,Date expireDate){
        ProductCycleComponent pcc = context.getComponent(ProductCycleComponent.class);
        List<CoProdBillingCycle> billCycles = pcc.queryProdBillingCycleAsc(prodId);
        CoProdBillingCycle billCycle = pcc.getTargetBillCycle(billCycles,expireDate);
        // 未来生效的产品还未扣过费
        if(billCycle==null||context.getRequestDate().before(billCycle.getValidDate()))
            return null;
        Date firstBillDate = billCycle.getFirstBillDate();
        Integer cycleUnit = billCycle.getCycleUnit();
        Integer cycleType = billCycle.getCycleType();
        ProdCycleInfo cycleInfo = calcuProdCycleUnit(cycleUnit, cycleType, firstBillDate, expireDate);
        
        return cycleInfo != null ? cycleInfo.getLastDeductDate():null;
    }
    
    /**
     * 计算cycleNum
     * luojb 2012-9-3
     * @param prodId
     * @param chgDate
     * @return
     */
    public int calcuCycleNum(Long prodId,Date newCycleValidDate,CoProdBillingCycle oldCycle)
    {
        ProductCycleComponent pcc = context.getComponent(ProductCycleComponent.class);
        if(oldCycle == null){
            List<CoProdBillingCycle> billCycles = pcc.queryProdBillingCycleAsc(prodId);
            oldCycle = pcc.getTargetBillCycle(billCycles,newCycleValidDate);
        }
        int cycleNum = oldCycle.getCycleNum();
        // 第一段还要判断短周期
        if(cycleNum == 0)
        {
            if(oldCycle.getValidDate().before(oldCycle.getFirstBillDate()))
                cycleNum = 1;
        }
        
        ProdCycleInfo info = calcuProdCycleUnit(oldCycle.getCycleUnit(), oldCycle.getCycleType(), oldCycle.getFirstBillDate(), newCycleValidDate);
        if(info!= null)
            cycleNum = cycleNum + info.getBetweenUnit();
        return cycleNum;
    }
    
    /**
     * 计算产品从firstBillDate到当前，经历了多少个周期，并计算最后扣费日
     * luojb 2012-9-3
     * @param cycleUnit
     * @param cycleType
     * @param firstBillDate
     * @param curDate
     * @return
     */
    private ProdCycleInfo calcuProdCycleUnit(int cycleUnit,int cycleType,Date firstBillDate,Date curDate)
    {
        int dateType = -1;
        Calendar fCal = Calendar.getInstance();
        fCal.setTime(firstBillDate);
        if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_DAY){
            dateType = Calendar.DAY_OF_MONTH;
        }
        else if(cycleType == EnumCodeDefine.PROD_CYCLETYPE_DAY){
            dateType = Calendar.DAY_OF_MONTH;
            fCal.set(Calendar.HOUR, 0);
            fCal.set(Calendar.MINUTE, 0);
            fCal.set(Calendar.SECOND, 0);
            fCal.set(Calendar.MILLISECOND, 0);
        }
        else if(cycleType== EnumCodeDefine.PROD_CYCLETYPE_HOUR ){
            dateType = Calendar.HOUR;
        }
        else if(cycleType== EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH){
            dateType = Calendar.MONTH;
            fCal.set(Calendar.HOUR, 0);
            fCal.set(Calendar.MINUTE, 0);
            fCal.set(Calendar.SECOND, 0);
            fCal.set(Calendar.MILLISECOND, 0);
        }
        else if(cycleType== EnumCodeDefine.PROD_CYCLETYPE_MONTH){
            dateType = Calendar.MONTH;
            fCal.set(Calendar.DAY_OF_MONTH, 1);
            fCal.set(Calendar.HOUR, 0);
            fCal.set(Calendar.MINUTE, 0);
            fCal.set(Calendar.SECOND, 0);
            fCal.set(Calendar.MILLISECOND, 0);
        }
        else if(cycleType== EnumCodeDefine.PROD_CYCLETYPE_MONTH_OFFSET){
            dateType = Calendar.MONTH;
        }
        else if(cycleType== EnumCodeDefine.PROD_CYCLETYPE_RUN_WEEK){
            dateType = Calendar.WEEK_OF_MONTH;
        }
        else if(cycleType== EnumCodeDefine.PROD_CYCLETYPE_WEEK)
        {
            dateType = Calendar.WEEK_OF_MONTH;
            fCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            fCal.set(Calendar.HOUR, 0);
            fCal.set(Calendar.MINUTE, 0);
            fCal.set(Calendar.SECOND, 0);
            fCal.set(Calendar.MILLISECOND, 0);
        }
        else if(cycleType== EnumCodeDefine.PROD_CYCLETYPE_YEAR){
            dateType = Calendar.YEAR;
            fCal.set(Calendar.DAY_OF_MONTH, 1);
            fCal.set(Calendar.MONTH, 0);
            fCal.set(Calendar.HOUR, 0);
            fCal.set(Calendar.MINUTE, 0);
            fCal.set(Calendar.SECOND, 0);
            fCal.set(Calendar.MILLISECOND, 0);
        }
        else if(cycleType== EnumCodeDefine.PROD_CYCLETYPE_RUN_YEAR)
        {
            dateType = Calendar.YEAR;
        }
        else{
            return null;
        }
        Calendar eCal = Calendar.getInstance();
        eCal.setTime(curDate);
        int unit = 0;
        int fYear = fCal.get(Calendar.YEAR);
        int eYear = eCal.get(Calendar.YEAR);
        int fMonth = fCal.get(Calendar.MONTH);
        int eMonth = eCal.get(Calendar.MONTH);
        int fDay = fCal.get(Calendar.DAY_OF_MONTH);
        int eDay = eCal.get(Calendar.DAY_OF_MONTH);
        if(dateType == Calendar.YEAR)
        {
            unit = (eYear - fYear)/cycleUnit;
            // 不足周期的部分要扣减
            if((eYear - fYear)%cycleUnit == 0)
            {    
                if(eMonth < fMonth || eMonth == fMonth && eDay < fDay)
                {
                    unit = unit - 1;
                }
            }
        }
        else if(dateType == Calendar.MONTH)
        {
            int months =  (eMonth - fMonth + (eYear - fYear)*12);
            unit = months/cycleUnit;
            //如果months刚好是周期整数倍，判断日期如果不足周期，需要扣减一个周期
            if(months%cycleUnit == 0)
            {
                if(eDay < fDay)
                {
                    unit = unit - 1;
                }
            }
        }
        else if(dateType == Calendar.HOUR)
        {
            long hours = (eCal.getTimeInMillis() - fCal.getTimeInMillis())/(1000*60*60);
            unit = (int)hours/cycleUnit;
        }
        else if(dateType == Calendar.DAY_OF_MONTH)
        {
            long days = (eCal.getTimeInMillis() - fCal.getTimeInMillis())/(1000*60*60*24);
            unit = (int)days/cycleUnit;
        }
        else if(dateType == Calendar.WEEK_OF_MONTH)
        {
            long weeks = (eCal.getTimeInMillis() - fCal.getTimeInMillis())/(1000*60*60*24*7);
            unit = (int)weeks/cycleUnit;
           
        }
        
        // 最后扣费日
        fCal.add(dateType, unit*cycleUnit);
        
        ProdCycleInfo cycleInfo = new ProdCycleInfo();
        cycleInfo.setBetweenUnit(unit);
        cycleInfo.setLastDeductDate(fCal.getTime());
        
        return cycleInfo;
    }

    // 删除打包关系
    private void deletePackageProd(CoProd dmProd)
    {
        // CoProdBundleComposite bc = new CoProdBundleComposite();
        // bc.setParentProductNo(dmProd.getProductId());
        // bc.setObjectId(dmProd.getObjectId());
        List<CoProdBundleComposite> resultList = (List<CoProdBundleComposite>)DBUtil.deleteByConditionWithReturn(CoProdBundleComposite.class,
                new DBCondition(CoProdBundleComposite.Field.parentProductNo, dmProd.getProductId()));
        context.cacheList(resultList);
        if (ProjectUtil.is_CN_SH())
        {
            // bc = new CoProdBundleComposite();
            // bc.setProductId(dmProd.getProductId());
            // bc.setObjectId(dmProd.getObjectId());
            DBUtil.deleteByCondition(CoProdBundleComposite.class,
                    new DBCondition(CoProdBundleComposite.Field.productId, dmProd.getProductId()));
        }
    }

    /**
     * @Description 查询soNbr mapping
     * @Author lijingcheng
     * @param orSoNbr
     * @return
     */
    private ImsSonbrMapping queryImsSonbrMapping(String orSoNbr)
    {
        // ImsSonbrMapping dmMapping = new ImsSonbrMapping();
        // dmMapping.setOutSoNbr(orSoNbr);
        // dmMapping = DBUtil.querySingle(dmMapping);
        ImsSonbrMapping dmMapping = DBUtil.querySingle(ImsSonbrMapping.class, new DBCondition(ImsSonbrMapping.Field.outSoNbr,
                orSoNbr));
        return dmMapping;
    }

    private void setProductId(SProductOrder prod)
    {
        if (ProjectUtil.is_CN_SH())
        {
            if (prod.getProduct_id() == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "product_id");
            }
            else
            {
                if (queryProd(prod.getProduct_id()) != null)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_ID_HAS_EXIST, prod.getProduct_id());
                }
            }
        }
        else
        {
            // 不是首次激活都要取seq
            if (!(CommonUtil.isValid(context.getOper().getBusi_code())
                    && context.getOper().getBusi_code() == EnumCodeDefine.FIRST_ACTIVE_4_TS_BUSI_BEAN && CommonUtil.isValid(prod
                    .getProduct_id())))
            {
                prod.setProduct_id(DBUtil.getSequence(CoProd.class));
            }
            if (prod.getProduct_id() == null)
            {
                prod.setProduct_id(DBUtil.getSequence(CoProd.class));
            }
        }
    }

    /**
     * 验证产品订购级别
     */
    private void checkOfferLevel(SProductOrder prod, PmProductOffering offer, boolean isAccountLevelProd)
    {
        if (offer.getOfferClass() == null || Integer.parseInt(offer.getOfferClass()) != EnumCodeDefine.OFFER_CLASS_ALL)
        {
            String offerClass = offer.getOfferClass();
            imsLogger.info("*********offer_class of offering_id+ " + offer.getProductOfferingId() + "is " + offerClass);
            if (!isAccountLevelProd)
            {
                // 用户级只能用户能订购的销售品
                if (offerClass.charAt(0) != '1')
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_LEVEL_ONLY_ORDER_USER_LEVEL, prod.getOffer_id());
                }
            }
            else
            {
                CaAccount dmAcct = context.get3hTree().loadAcct3hBean(prod.getAcct_id()).getAccount();
                if (dmAcct == null)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_NOTEXIST, prod.getAcct_id());
                }
                if (dmAcct.getAccountClass().intValue() == EnumCodeDefine.ACCOUNT_CLASS_GCA)
                {
                    if (offerClass.charAt(2) != '1')
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.GROUP_LEVEL_ONLY_ORDER_GROUP_LEVEL, prod.getOffer_id());
                    }
                }
                else
                {
                    if (offerClass.charAt(1) != '1')
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCT_LEVEL_CANNOT_ORDER_USER_LEVEL, prod.getOffer_id());
                    }
                }
            }
        }
    }

    // 创建打包关系
    private void createPackageProduct(CoProd dmProd, Long parentProductId)
    {
        CoProdBundleComposite bdc = new CoProdBundleComposite();
        bdc.setParentProductNo(parentProductId);
        bdc.setProductId(dmProd.getProductId());
        bdc.setObjectId(dmProd.getObjectId());
        bdc.setObjectType(dmProd.getObjectType());
        if (CommonUtil.isEmpty(query(CoProdBundleComposite.class,
                new DBCondition(CoProdBundleComposite.Field.productId, dmProd.getProductId()), new DBCondition(
                        CoProdBundleComposite.Field.objectId, dmProd.getObjectId()), new DBCondition(
                        CoProdBundleComposite.Field.objectType, dmProd.getObjectType()), new DBCondition(
                        CoProdBundleComposite.Field.parentProductNo, parentProductId))))
        {
            this.insert(bdc);
        }
    }

    /**
     * @Description 验证销售品是否有效
     * @Author sunpf3
     * @param prodList
     * @return
     */
    private void checkProductOffering(PmProductOffering offer, Long offeringId)
    {
        if (offer != null)
        {
            int statusId = offer.getLifecycleStatusId().intValue();
            //判断销售品是否有效
            if (statusId != EnumCodeDefine.PROD_OFFERING_STATUS_ACTIVE) 
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.OFFERING_NOT_ACTIVE, offeringId);
            }
        }
        else
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OFFER_NOTEXIST, offeringId);
        }
        
        
        
        
        
    }
    
    /**
     * 产品订购入口,新开户接口需要传入账户的账期
     */
    public SProductOrderResultList addProductInfo(SProductOrderList prodList)
    {
        if (prodList == null || CommonUtil.isEmpty(prodList.getItem()))
        {
            return null;
        }
        SProductOrderResult[] prodResults = new SProductOrderResult[prodList.getItem().length];
        // 用户包含的服务列表
        List<CmResServCycle> resServList = new ArrayList<CmResServCycle>();

        SProductOrderList packOrderList = new SProductOrderList();
        for (int i = 0; i < prodList.getItem().length; i++)
        {
            SProductOrder prod = (SProductOrder) prodList.getItem()[i];
            // 2012-5-24 yangjh User Story #45738 增加设置产品默认属性 普通账户订购产品默认的billing_type是后付费 single balance账户订购的产品默认的billing_type是预付费
            if (prod.getBilling_type() == null)
            {
                boolean isAccountLevelProds = this.isAccountLevelOrderProduct(prod);
                if (isAccountLevelProds)
                {
                    IMS3hBean bean = context.get3hTree().loadAcct3hBean(prod.getAcct_id());
                    CaAccount acct = bean.getAccount();
                    if (acct != null)
                    {
                        // single_balance只能订购预付费，实账户只能订购后付费用户
                        if (acct.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE)
                        {
                            prod.setBilling_type((short) EnumCodeDefine.USER_PAYMODE_PREPAID);
                        }
                        else
                        {
                            prod.setBilling_type((short) EnumCodeDefine.USER_PAYMODE_POSTPAID);
                        }
                    }
                }
            }

            // 上海需求需要传入prodId
            this.setProductId(prod);
            // lijc3 上海需求不验证
            //@Date 2012-09-04 yugb :修改空指针异常
            PmProductOffering offer = super.queryPmProductOfferingByOfferId(CommonUtil.long2Int(prod.getOffer_id()));
            checkProductOffering(offer, prod.getOffer_id());
            
            if (offer.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
            {
                context.addExtendParam(ConstantDefine.CHANGE_PROD_ADD_MAIN_PRODUCT_ID, prod.getProduct_id());
            }
            // 处理特征值，如果没有需要设置默认取值 ljc .
            this.addDefaultSpecChars(prod);

            // 增加产品，包括所有相关信息
            CoProd dmProd = createProductOrder(prod, resServList);

            handleOfferRel(dmProd, prod, EnumCodeDefine.CHANGE_PROD_ADD, resServList, null,
                    packOrderList);
            prod.setProduct_id(dmProd.getProductId());// 2011-09-14 wuyujie ://
                                                      // 把prodId设置到结构中，方便后续取用
            SProductOrderResult prodResult = new SProductOrderResult();
            prodResult.setOffer_id(prod.getOffer_id());
            prodResult.setProduct_id(dmProd.getProductId());
            if (prod.getSo_id() != null)
            {
                prodResult.setSo_id(prod.getSo_id());
            }
            else
            {
                prodResult.setSo_id(String.valueOf(context.getSoNbr()));
            }
            // 2011-09-14 hunan ： add return vilad_date
            prodResult.setValid_date(DateUtil.formatDate(dmProd.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            prodResult.setExpire_date(DateUtil.formatDate(dmProd.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            prodResult.setProduct_param_list(prod.getParam_list());
            prodResults[i] = prodResult;
        }
        // 返回结构进行合并，业务记录用
        mergtSProductOrderList(prodList, packOrderList);

        // 修改设备服务状态定义表

        SProductOrderResultList prodResutList = new SProductOrderResultList();
        prodResutList.setSProductOrderResultList_Item(prodResults);

        return prodResutList;
    }

    /**
     * @Description 合并SProductOrderList，合并到src中
     * @Author lijingcheng
     * @param src
     * @param tar
     */
    private void mergtSProductOrderList(SProductOrderList src, SProductOrderList tar)
    {
        if (tar != null && CommonUtil.isNotEmpty(tar.getItem()))
        {
            List<SProductOrder> orderArray = new ArrayList<SProductOrder>(Arrays.asList(src.getItem()));
            orderArray.addAll(new ArrayList<SProductOrder>(Arrays.asList(tar.getItem())));
            src.setItem(orderArray.toArray(new SProductOrder[orderArray.size()]));
        }
    }

    /**
     * 处理产品特征值，对没有传入的设置默认值
     */
    public void addDefaultSpecChars(SProductOrder prod)
    {
        // 修复空指针
        if (prod.getOffer_id() == null)
        {
            return;
        }
        long busiflag = (long) this.queryBusiflag(prod.getOffer_id().intValue());
        List<SProductParam> list = new ArrayList<SProductParam>();
        SProductParamList paramList = prod.getParam_list();
        
        //2012-07-25 luojb #51918 特征值默认优先级处理
        if (paramList == null || CommonUtil.isEmpty(paramList.getSProductParamList_Item()))
        {
            paramList = new SProductParamList();
            prod.setParam_list(paramList);
             boolean isAcctProd = this.isAccountLevelOrderProduct(prod);
             if (busiflag == SpecCodeDefine.MCS_NUMBER && !isAcctProd)
             {
                 int count = 0;
                 int i = 0;
                 boolean needLimitCount = false;
                 User3hBean bean = context.get3hTree().loadUser3hBean(prod.getUser_id(),prod.getPhone_id());
                 Long userId = bean.getUserId();
                 //2012-07-09 yangjh story:50254 黑白名单增加最大数量限制
                 PmProductOfferSpecChar whiteProd = this.getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.MCS_NUMBER_WHITELIST_LIMT,
                         CommonUtil.long2Int(prod.getOffer_id()));
                 PmProductOfferSpecChar blackProd = this.getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.MCS_NUMBER_BLACK_LIMIT,
                         CommonUtil.long2Int(prod.getOffer_id()));
                 //2012-07-04 yangjh  bug:50104 callScreen增加黑白名单最大数量限制
                 List<CmResLastIcs> icsList = query(CmResLastIcs.class, new DBCondition(CmResLastIcs.Field.resourceId, userId));
                 List<CmResLastIcs> whiteIcsList = new ArrayList<CmResLastIcs>();
                 List<CmResLastIcs> blackIcsList = new ArrayList<CmResLastIcs>();
                 if (!CommonUtil.isEmpty(icsList))
                 {
                     if(whiteProd != null || blackProd != null)
                     {
                          for (CmResLastIcs ics : icsList)
                          {       
                              if(ics.getListType() != null && ics.getListType() == EnumCodeDefine.MCS_CS_NOTYPE_WHITEIST)
                             {
                                  whiteIcsList.add(ics);
                             }
                             if(ics.getListType() != null && ics.getListType() == EnumCodeDefine.MCS_CS_NOTYPE_BLACKLIST)
                             {
                                  blackIcsList.add(ics);
                             }
                          }
                          //@Date 2012-07-26 yangjh : 增加whiteProd，blackProd为空判断 避免空指针
                          if(whiteProd != null && CommonUtil.isValid(whiteProd.getValue()))
                          {
                             if(whiteIcsList.size() > Integer.parseInt(whiteProd.getValue()))
                             {
                                 count = Integer.parseInt(whiteProd.getValue());
                             }
                             else{
                                 count = whiteIcsList.size();
                             }
                             this.backIcsList(whiteIcsList, i, true, count, userId, paramList);
                          }
                          if(blackProd != null && CommonUtil.isValid(blackProd.getValue()))
                          {
                             if(blackIcsList.size() > Integer.parseInt(blackProd.getValue()))
                             {
                                 count = Integer.parseInt(blackProd.getValue());
                             }
                             else{
                                 count = blackIcsList.size();
                             }
                             this.backIcsList(blackIcsList, i, true, count, userId, paramList);
                          }
                     }else{
                         this.backIcsList(icsList, i, needLimitCount, count, userId, paramList);
                     }
                 }
             }
             // 2012-05-29 yangjh story：46789 IMS_RealStory_HomeZone销售品级的特征值的固定值实例化
             else if (busiflag == SpecCodeDefine.HOME_ZONE)
             {
                 List<Integer> cellCodes = queryCellCode(CommonUtil.long2Int(prod.getOffer_id()));
                 if (CommonUtil.isNotEmpty(cellCodes)){
                     paramList = new SProductParamList();
                     prod.setParam_list(paramList);
                     List<SProductParam> newParamList = new ArrayList<SProductParam>();
                     for (Integer cellCode : cellCodes)
                     {
                         Long groupId = DBUtil.getSequence();
                         SProductParam param = new SProductParam();
                         param.setGroup_id(groupId);
                         param.setParam_id(SpecCodeDefine.HOME_ZONE_CELL);
                         param.setParam_value(CommonUtil.int2String(cellCode));
                         newParamList.add(param);
                     }
                     paramList.setSProductParamList_Item(newParamList.toArray(new SProductParam[newParamList.size()]));
                 }
             }
             //2012-07-05 yangjh story:49802 IR-OUT产品增加特征值入库，12203特征值存储时差
             else if(busiflag == SpecCodeDefine.IR_OUT)
             {
                 SysCountry irCountry = getTzIrCountryWithCheck(CommonUtil.long2Int(prod.getOffer_id()));
                 Integer countryId = irCountry.getCountryId();
                 Integer temp = getTimeZoneDefValue(irCountry);
                 /* PmProductOfferSpecChar offerChar = getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.IR_OUT_COUNTRY,
                          CommonUtil.long2Int(prod.getOffer_id()));
                  if (offerChar == null)
                  {
                      throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_NO_REECORD);
                  }
                  // 漫游国家id
                  int countryId = Integer.valueOf(offerChar.getValue());
                  // 获取漫游地时区
                  SysCountry irCountry = querySingle(SysCountry.class, new DBCondition(SysCountry.Field.countryId, countryId));
                  if(irCountry == null || irCountry.getTimeZoneId() == null){
                      return;
                  }
                  int timeZone = Integer.valueOf(irCountry.getTimeZoneId());
                  // 获取系统时区
                  String coutryName = SysUtil.getString(SysCodeDefine.busi.INNER_COUNTRY);
                  SysCountry sc = this.querySingle(SysCountry.class, new DBCondition(SysCountry.Field.name, coutryName));
                  if (sc == null || sc.getTimeZoneId() == null)
                  {
                      return;
                  }
                  // irCountryTZ = Integer.parseInt(irCountry.getTimeZone());
                  int scTZ = Integer.valueOf(sc.getTimeZoneId());
                  int temp = timeZone - scTZ;// 获得两个时区的时间差  */
                  paramList = new SProductParamList();
                  prod.setParam_list(paramList);
                  List<SProductParam> newParamList = new ArrayList<SProductParam>();
                  SProductParam param = new SProductParam();
                  param.setParam_id(SpecCodeDefine.IR_OUT_TIME_DIFFERENCE);
                  param.setParam_value(CommonUtil.int2String(temp));
                  newParamList.add(param);
                  
                  param = new SProductParam();
                  param.setParam_id(SpecCodeDefine.IR_OUT_COUNTRY);
                  param.setParam_value(CommonUtil.int2String(countryId));
                  newParamList.add(param);
                  paramList.setSProductParamList_Item(newParamList.toArray(new SProductParam[newParamList.size()]));
             }
         }

        if (paramList != null && !CommonUtil.isEmpty(paramList.getSProductParamList_Item()))
        {
            SProductParam[] param = paramList.getSProductParamList_Item();
            for (SProductParam pa : param)
            {
                list.add(pa);
            }
        }
        
        List<Integer> specIds = context.getComponent(ConfigQuery.class).querySpecCharIdsByOfferId(prod.getOffer_id().intValue());
        if (!CommonUtil.isEmpty(specIds))
        {
            // 传入的特征值id集合
            List<Integer> inParamIds = new ArrayList<Integer>();
            for (SProductParam sp : list)
            {
                // 传入的特征值id集合只存放数据库存在的，传入的但是数据库不存在则不添加到传入的特征值集合中
                if (specIds.contains(sp.getParam_id()))
                {
                    inParamIds.add(sp.getParam_id());
                }
            }
            List<Integer> notInParamIds = new ArrayList<Integer>();
            for (Integer specId : specIds)
            {
                // 传入的参数id不包含从数据库里面查出来的，则不包含的特征值需要设s置默认值
                if (!inParamIds.contains(specId))
                {
                    notInParamIds.add(specId);
                }
            }
            // 没有传入的特征值id，看有没有默认值，有，就设置到sproductorder里面
            if (notInParamIds.size() > 0)
            {
                List<PmProductSpecCharValue> specCharValues = this.query(PmProductSpecCharValue.class, new DBCondition(
                        PmProductSpecCharValue.Field.specCharId, notInParamIds, Operator.IN), new DBCondition(
                        PmProductSpecCharValue.Field.isDefault, 1));// 1表示默认值
                if (!CommonUtil.isEmpty(specCharValues))
                {
                    for (PmProductSpecCharValue value : specCharValues)
                    {
                        SProductParam sParam = new SProductParam();
                        sParam.setParam_id(value.getSpecCharId());
                        sParam.setParam_value(value.getValue());
                        list.add(sParam);
                    }
                }
            }
        }
        else
        {
            // 数据库不存在这个特征类型的特征值，把SProductParam清空 2012-04-24 抛出异常
            // @Date 2012-05-07 lijc3 上海添加默认特征值不抛异常
            if (CommonUtil.isNotEmpty(list) && (busiflag != SpecCodeDefine.HOMEGATE || ProjectUtil.is_TH_AIS()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_SPEC_CHAR_NOT_CONFIG, prod.getOffer_id());
            }
        }
        // 将处理完后的SProductParam List放入sChangeProd中
        if (list == null || list.size() == 0)
        {
            prod.setParam_list(null);
        }
        else
        {
            paramList = new SProductParamList();
            paramList.setSProductParamList_Item(list.toArray(new SProductParam[list.size()]));
            prod.setParam_list(paramList);
        }
    }

    /**
     * 对群产品的检查 luojb 2011-11-14
     */
    private void checkGroupProd(VPNComponent vpnCmp, AccountComponent accCmp, SProductOrder prod)
    {
        String phoneId = prod.getPhone_id();
        Long userId = prod.getUser_id();
        Long acctId = prod.getAcct_id();
        // 不允许用户订购群产品
        // 普通账户可以订购群产品，这里放开控制 2011-12-26
        Integer busiFlag = this.queryBusiflag(prod.getOffer_id().intValue());
        if (GroupHelper.isGroupProd(busiFlag))
        {
            if (CommonUtil.isValid(phoneId) || CommonUtil.isValid(userId))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_PERSONAL_CANOT_ORDER_GROUP_PROD);
            }

            if (context.getComponent(AccountComponent.class).isGCAAccount(acctId))
            {
                return; // 群账户 直接返回
            }

            // 普通账户订购群产品，自动创建群处理：把账户的所有归属用户和支付关系用户都加入 2012-01-12
            // 查询账户的支付关系用户，和没有其他支付账户的归属用户
            List<Long> userIds = vpnCmp.autoQueryUsers(acctId);
            // 筛选出userIds中还未建立关系的用户id
            userIds = vpnCmp.autoUsersNotIn(userIds);
            // 建立关系
            if (CommonUtil.isNotEmpty(userIds))
            {
                vpnCmp.autoJoinMembers(acctId, userIds);
            }

            return;
        }

        // 普通产品不需要走下面流程(非个性化群产品)
        if (!GroupHelper.isGroupPersonProd(busiFlag))
        {
            return;
        }

        // 个性化产品必须群用户订购
        if (!CommonUtil.isValid(phoneId) && !CommonUtil.isValid(userId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_ACCOUNT_CANOT_ORDER_PERSONAL_GROUP_PROD);
        }

        // 判断用户是否是群内成员 ljc 使用3hbean
        User3hBean bean = context.get3hTree().loadUser3hBean(userId, phoneId);
        userId = bean.getUserId();
        // 2012-10-23 luojb On_Site Defect #62372 修改判断方式，community产品单独处理
        if(busiFlag == SpecCodeDefine.COMMUNITY_PROMOTION){
            addGroupMemberIntoGroup(prod, vpnCmp, bean);
            return;
        }
       
        // 群个性化产品，判断成员是否加入vpn
        CaAccountRv rv = context.getComponent(GroupQuery.class).queryVpnMemberAcctRvByUserId(userId);
        if (rv == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_PERSONAL_CANOT_ORDER_GROUP_PROD);
        }
        else
        {
            Long groupId = rv.getAcctId();
            // 群id放入特征值

            SProductParamList paramList = prod.getParam_list();
            SProductParam param = new SProductParam();
            param.setParam_id(GroupHelper.getSpecGroupProdParamId(busiFlag));
            param.setParam_value(CommonUtil.long2String(groupId));

            if (paramList == null || CommonUtil.isEmpty(paramList.getSProductParamList_Item()))
            {
                paramList = new SProductParamList();
                SProductParam[] paramItemList = new SProductParam[1];
                paramItemList[0] = param;
                paramList.setSProductParamList_Item(paramItemList);
                prod.setParam_list(paramList);
            }
            else
            {
                SProductParam[] oldParamItemList = paramList.getSProductParamList_Item();
                SProductParam[] newParamItemList = new SProductParam[oldParamItemList.length + 1];
                for (int i = 0; i < oldParamItemList.length; i++)
                {
                    newParamItemList[i] = oldParamItemList[i];
                }
                newParamItemList[oldParamItemList.length] = param;
                paramList.setSProductParamList_Item(newParamItemList);
            }
        }
    }

    private void addGroupMemberIntoGroup(SProductOrder prod, VPNComponent vpnCmp,User3hBean bean)
    {
        Long groupId = null;
        if (prod.getParam_list() != null && CommonUtil.isNotEmpty(prod.getParam_list().getSProductParamList_Item()))
        {
            for (SProductParam param : prod.getParam_list().getSProductParamList_Item())
            {
                if (param.getParam_id() == null || CommonUtil.isEmpty(param.getParam_value()))
                {
                    continue;
                }
                if (param.getParam_id().equals(GroupHelper.getSpecGroupProdParamId(SpecCodeDefine.COMMUNITY_PROMOTION)))
                {
                    groupId = Long.parseLong(param.getParam_value());
                    break;
                }
            }
        }
        if (groupId == null)
        {
            PmProductOfferSpecChar specChar = getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.COMMUNITY_GROUP_ID, prod
                    .getOffer_id().intValue());
            if (specChar == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.MCS_CONF_PMPRODUCTOFFERSPECCHAR, prod.getOffer_id());
            }
            else
            {
                groupId = CommonUtil.string2Long(specChar.getValue());
            }
        }
      //2012-08-27 linzt 整理组件方法  采用load3hBean
        Group3hBean groupBean=context.get3hTree().loadGroup3hBean(groupId);
        CaAccount dmAccount=groupBean.getAccount();
        if (dmAccount.getAccountType() != EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_GROUP_NOT_COMMUNITY, groupId);
        }
        // 是否已经存在当前communtity
        if (vpnCmp.isMemberInGroup(bean.getUserId(), groupId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_MEMBER_EXIST, bean.getPhoneId(), bean.getUserId());
        }
        // 成员数限制
//        CaAccountGroup vpn = (CaAccountGroup) map.get(CaAccountGroup.class);
        CaAccountGroup vpn=groupBean.getGroup();
        if (vpn.getMemberNumber() >= vpn.getMaxusers())
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_FULL, vpn.getMaxusers());
        }
        // 用户允许加入的communtity数限制
        List<CaAccountRv> rvs = vpnCmp.queryJoinedCommsByUserId(bean.getUserId());
        Integer count = SysUtil.getInt(SysCodeDefine.busi.MAX_JOIN_GROUP.concat(CommonUtil.short2String(EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY)));
        if (CommonUtil.isNotEmpty(rvs) && count != null && count != CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
        {
            if (rvs.size() >= count)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_CANNOT_JOIN, bean.getUser(),
                        bean.getPhoneId(), count, "community");
            }
        }
        CoProdBillingCycle dmBillCycle = context.getComponent(ProductCycleComponent.class).parseProdBillingCycle(prod);
        context.addExtendParam(ConstantDefine.CACHE_GROUP_PERSON_PROD_CYCLE + prod.getOffer_id(), dmBillCycle);
        vpnCmp.addMemberAutoGroup(groupId, bean.getUserId(), bean.getPhoneId(), EnumCodeDefine.TITLEROLEID_VPN_NORMAL,
                EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP, dmBillCycle.getValidDate(), dmBillCycle.getExpireDate(), vpn);

    }

    /**
     * 判断是否为账户级产品
     * 
     * @author yangyang
     * @date 2011-9-2
     */
    public boolean isAccountLevelOrderProduct(SProductOrder sProductOrder)
    {
        Long userId = sProductOrder.getUser_id();
        Long acctId = sProductOrder.getAcct_id();
        String phoneId = sProductOrder.getPhone_id();
        // 用户ID、手机号码为空，账户ID不为空，则为账户级产品
        return (!CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId) && CommonUtil.isValid(acctId));
    }

    /**
     * 根据用户id判断用户是否拥有主产品 ljc 2011-11-11
     * 
     * @param prod
     * @return
     */
    public boolean hasPrimaryProduct(SProductOrder prod)
    {
        Long userId = prod.getUser_id();
        String phoneId = prod.getPhone_id();

        if (CommonUtil.isValid(userId) || CommonUtil.isValid(phoneId))
        {
            User3hBean userbean = context.get3hTree().loadUser3hBean(userId,phoneId);
            userId = userbean.getUserId();

            List<CoProd> prodList = queryProdListByUserId(userId);
            if (!CommonUtil.isEmpty(prodList))
            {
                for (CoProd dmProd : prodList)
                {
                    if (dmProd.getIsMain() != null && dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isPrimaryProdcutByOfferingId(Long offeringId)
    {

        if (CommonUtil.isValid(offeringId))
        {
            PmProductOffering offering = context.getComponent(ConfigQuery.class).queryOfferingByOfferId(offeringId);
            if (offering != null)
            {
                // modify by ljc
                // 根据offering来判断是不是主产品，如果offering.getIsMain()==1就代表是主产品
                if (EnumCodeDefine.PRODUCT_MAIN == offering.getIsMain())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteProdById(Long prodId, Date effectiveDate)
    {
        // 首次激活 新老记录生失效时间置为激活时间 2011-12-15 wukl
        Date activeDate = this.getActiveDate();
        effectiveDate = activeDate == null ? effectiveDate : activeDate;
        CoProd newProd = new CoProd();
        if (context.getExtendParam(ConstantDefine.NEED_CHANGE_PAY_MODE) != null)
        {
            newProd.setBillingType((Integer) context.getExtendParam(ConstantDefine.NEED_CHANGE_PAY_MODE));
        }
        newProd.setExpireDate(effectiveDate);
        super.updateByCondition(newProd, activeDate, new DBCondition(CoProd.Field.productId, prodId));
    }

    public void deleteProdById(Long prodId)
    {
        super.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, prodId));
    }

    public void deleteProdByIds(Long[] prodIds)
    {
        super.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, CommonUtil.parse2StringsArray(prodIds),
                Operator.IN));
    }



    /**
     * @Description 指定时间退订CO_FN_CHAR_VALUE
     * @Author lijingcheng
     * @param prodId
     * @param effectiveDate
     */
    public void deleteProdFnCharValue(Long prodId, Date effectiveDate)
    {
        // 首次激活 新老记录生失效时间置为激活时间 2011-12-15 wukl
        Date activeDate = this.getActiveDate();
        effectiveDate = activeDate == null ? effectiveDate : activeDate;

        CoFnCharValue obj = new CoFnCharValue();
        obj.setExpireDate(effectiveDate);
        // CoFnCharValue where = new CoFnCharValue();
        // where.setProductId(prodId);
        this.updateByCondition(obj, activeDate, new DBCondition(CoFnCharValue.Field.productId, prodId));
    }

    /**
     * @Description 指定时间退订CO_SPLIT_CHAR_VALUE
     * @Author lijingcheng
     * @param prodId
     * @param effectiveDate
     */
    public void deleteProdSplitCharValue(Long prodId, Date effectiveDate)
    {
        // 首次激活 新老记录生失效时间置为激活时间 2011-12-15 wukl
        Date activeDate = this.getActiveDate();
        effectiveDate = activeDate == null ? effectiveDate : activeDate;

        CoSplitCharValue obj = new CoSplitCharValue();
        obj.setExpireDate(effectiveDate);
        // CoSplitCharValue where = new CoSplitCharValue();
        // where.setProductId(prodId);
        this.updateByCondition(obj, activeDate, new DBCondition(CoSplitCharValue.Field.productId, prodId));
    }

    
    public void deleteSplitPayRel(Long prodId)
    {
    	super.deleteByCondition(CoSplitPayRel.class, 
    			new DBCondition(CoSplitPayRel.Field.productId, prodId));
    }

    /**
     * @Description: (指定日期删除)
     * @param prodId
     * @param effectiveDate
     */
    // @Date 2012-04-25 lijc3 表删除，删除相应方法
    /*
     * public void deleteProdInvCaByProdId(Long prodId, Date effectiveDate) { // 首次激活 新老记录生失效时间置为激活时间 2011-12-15 wukl Date
     * activeDate = this.getActiveDate(); effectiveDate = activeDate == null ? effectiveDate : activeDate; CoProdInvCa ca = new
     * CoProdInvCa(); ca.setExpireDate(effectiveDate); CoProdInvCa where = new CoProdInvCa(); where.setProductId(prodId);
     * this.updateByCondition(ca, activeDate, where); } public void deleteProdInvCaByProdId(Long prodId) {
     * super.deleteByCondition(CoProdInvCa.class, new DBCondition(CoProdInvCa.Field.productId, prodId)); } public void
     * deleteProdInvCaByProdIds(Long[] prodIds) { super.deleteByCondition(CoProdInvCa.class, new
     * DBCondition(CoProdInvCa.Field.productId, CommonUtil.parse2StringsArray(prodIds), Operator.IN)); }
     */
    /**
     * @Description: (指定日期删除charValue)
     * @param prodId
     * @param effectiveDate
     */
    public void deleteProdCharValueByProdId(Long prodId, Date effectiveDate)
    {
        // 首次激活 新老记录生失效时间置为激活时间 2011-12-15 wukl
        Date activeDate = this.getActiveDate();
        effectiveDate = activeDate == null ? effectiveDate : activeDate;

        CoProdCharValue value = new CoProdCharValue();
        value.setExpireDate(effectiveDate);
        // CoProdCharValue where = new CoProdCharValue();
        // where.setProductId(prodId);
        super.updateByCondition(value, activeDate, new DBCondition(CoProdCharValue.Field.productId, prodId));
    }

    public void deleteProdCharValueByProdId(Long prodId)
    {
        super.deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId));
    }

    public List<CoProdCharValue> deleteProdCharValueByProdId(Long prodId, Long objectId)
    {
        return super.deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId),
                new DBCondition(CoProdCharValue.Field.objectId, objectId));
    }

    public void deleteProdCharValueByProdIds(Long[] prodIds)
    {
        super.deleteByCondition(CoProdCharValue.class,
                new DBCondition(CoProdCharValue.Field.productId, CommonUtil.parse2StringsArray(prodIds), Operator.IN));

    }

    public void deleteProdCharValueById(Long prodId, Long groupId)
    {
        super.deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
                CoProdCharValue.Field.groupId, groupId));
    }

    public void deleteProdParamPriceByProdId(Long prodId)
    {
        super.deleteByCondition(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, prodId));
    }

    public void deleteProdParamPriceByProdId(Long prodId, Long objectId)
    {
        super.deleteByCondition(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, prodId),
                new DBCondition(CoProdPriceParam.Field.objectId, objectId));
    }

    /**
     * @Description: (指定日期删除paramprice)
     * @param prodId
     * @param effectiveDate
     */
    public void deleteProdParamPriceByProdId(Long prodId, Date effectiveDate)
    {
        // 首次激活 新老记录生失效时间置为激活时间 2011-12-15 wukl
        Date activeDate = this.getActiveDate();
        effectiveDate = activeDate == null ? effectiveDate : activeDate;

        CoProdPriceParam param = new CoProdPriceParam();
        param.setExpireDate(effectiveDate);
        // CoProdPriceParam where = new CoProdPriceParam();
        // where.setProductId(prodId);
        this.updateByCondition(param, activeDate, new DBCondition(CoProdPriceParam.Field.productId, prodId));
    }

    // private void deleteResIdentity(String number, int resIdentityType)
    // {
    // super.deleteByCondition(CmResIdentity.class, new
    // DBCondition(CmResIdentity.Field.identity, number), new DBCondition(
    // CmResIdentity.Field.identityType, resIdentityType));
    // }

    public void deleteResServCycleBySpecId(Long resourceId, Long specId)
    {
        super.deleteByCondition(CmResServCycle.class, new DBCondition(CmResServCycle.Field.serviceSpecId, specId),
                new DBCondition(CmResServCycle.Field.resourceId, resourceId));
    }

    public void deleteResServCycleByResourceId(Long resourceId)
    {
        super.deleteByCondition(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId, resourceId));
    }

    /**
     * @Description: 批量删除用户服务
     * @param userIds
     * @author: tangjl5
     * @Date: 2011-12-8
     */
    public void deleteBathResServCycleByResId(List<Long> userIds)
    {
        super.deleteByCondition(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId, userIds, Operator.IN));
    }

    /**
     * @Description: 根据CoProd 和phone构造SProductOrder
     */
    public SProductOrder createProductOrder(CoProd prod, String phoneId) throws IMSException
    {
        SProductOrder order = new SProductOrder();
        order.setValid_date(DateUtil.formatDate(prod.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        order.setExpire_date(DateUtil.formatDate(prod.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        order.setPhone_id(phoneId);
        order.setOffer_id((long) prod.getProductOfferingId());
        order.setProduct_id(prod.getProductId());
        return order;
    }

    public void modifyGroupProd(long groupId, SProductOrder prod) throws IMSException
    {
        Long prodId = prod.getProduct_id();
        if (prodId == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCTID_IS_NULL);
        }
        // 查询该群是否有该产品
        CoProd coProd = queryProd(prodId);
        if (coProd == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGEGROUPPROD_PROD_NOTEXIST, prodId);
        }

        SProductOrderList orderList = new SProductOrderList();
        SProductOrder[] orders = new SProductOrder[1];
        orders[0] = prod;
        orderList.setItem(orders);
        this.modifyProductInfo(orderList);
    }

    public void extendGroupProd(SProductOrder prod) throws IMSException
    {
        extendExpireDate(prod, prod.getExpire_date());
    }

    /**
     * @Description: (获取某一规格特征下的最大值?)
     * @param specCharId 规格特征
     * @param offerId 销售品Id
     * @return
     * @throws IMSException
     */
    public PmProductOfferSpecChar getOfferSpecCharBySpecCharIdAndOfferId(int specCharId, Integer offerId) throws IMSException
    {
        return this.querySingle(PmProductOfferSpecChar.class, new DBCondition(PmProductOfferSpecChar.Field.productOfferingId,
                offerId), new DBCondition(PmProductOfferSpecChar.Field.specCharId, specCharId));
    }

    /**
     * @Description: 根据首次激活配置的扩展参数构造规格特征值列表
     * @param dmProd
     * @param exCharVals
     * @return
     */
    public List<CoProdCharValue> buildCharVal(CoProd dmProd, String exCharVals)
    {
        List<CoProdCharValue> listVals = new ArrayList<CoProdCharValue>();
        List<String[]> confVals = CommonUtil.buildExtParam(exCharVals);
        for (String[] keyval : confVals)
        {
            CoProdCharValue cval = new CoProdCharValue();
            cval.setProductId(dmProd.getProductId());
            cval.setSoNbr(dmProd.getSoNbr());
            if (!CommonUtil.isInteger(keyval[0]))
            {
                imsLogger.info(keyval[0] + " is not a valid spec chararector id, the id must be a integer");
                continue;
            }
            cval.setSpecCharId(Integer.parseInt(keyval[0]));
            cval.setCreateDate(dmProd.getCreateDate());
            cval.setExpireDate(dmProd.getExpireDate());
            cval.setGroupId(0l);
            cval.setObjectId(dmProd.getObjectId());
            cval.setObjectType(dmProd.getObjectType());
            cval.setSoDate(dmProd.getSoDate());
            cval.setValidDate(dmProd.getValidDate());
            cval.setValue(keyval[1]);
            listVals.add(cval);
        }

        return listVals;
    }

    /**
     * 对单个产品订购的进一步封装 yanchuan 2011-9-26
     */
    public CoProd createProductOrder(SProductOrder prod) throws IMSException
    {
        List<CmResServCycle> resServList = new ArrayList<CmResServCycle>();
        CoProd coProd = createProductOrder(prod, resServList);
        context.getComponent(ServiceComponent.class).updateOrInsertResServ(resServList);
        return coProd;
    }

    /**
     * 订购产品时候定价计划上一次性免费资源初始化ljc 2011-9-23
     * 
     * @param planPriceId
     */
    public void doOneTimeFreeResourceByPlanPriceId(Integer planPriceId, Long acctId, Long userId, CoProd prod)
    {
        /*
        Long resAmount = null;
        Integer item = null;
        
        //2012-07-24 yanchuan 将联表查询改为单表查询
        ResultTable<PmCompositeOfferPrice> resultTable = new ResultTable<PmCompositeOfferPrice>(PmCompositeOfferPrice.Field.pricingPlanId,null);
        resultTable.addCondTable(PmProductPricingPlan.Field.pricingPlanId, new DBCondition(PmProductPricingPlan.Field.pricingPlanId,planPriceId));
        List<PmCompositeOfferPrice> compositeList= context.getComponent(ResultComponent.class).getResultList(resultTable);
        if(CommonUtil.isEmpty(compositeList))
            return;
        List<Integer> priceId_list = new ArrayList<Integer>();
        for(PmCompositeOfferPrice compositeOfferPrice : compositeList)
        {
            priceId_list.add(compositeOfferPrice.getPriceId());
        }
        List<PmComponentProdofferPrice> componentProd_list = this.query(PmComponentProdofferPrice.class,
                                                  new DBCondition(PmComponentProdofferPrice.Field.priceId,priceId_list,Operator.IN),
                                                  new DBCondition(PmComponentProdofferPrice.Field.priceType,4));
        priceId_list.clear();
        if(CommonUtil.isEmpty(componentProd_list))
            return;
        for(PmComponentProdofferPrice componentProdofferPrice : componentProd_list)
        {
            priceId_list.add(componentProdofferPrice.getPriceId());
        }
        List<PmOneTimeFreeUsage> freeUsage_list = this.query(PmOneTimeFreeUsage.class, 
                                                 new DBCondition(PmOneTimeFreeUsage.Field.priceId,priceId_list,Operator.IN));
        if(CommonUtil.isEmpty(freeUsage_list))
            return;
        List<Integer> allowanceRuleIdList = new ArrayList<Integer>();
        for(PmOneTimeFreeUsage freeUsage : freeUsage_list)
        {
            allowanceRuleIdList.add(freeUsage.getAllowanceRuleId());
        }
        List<PmAllowanceRegulation> allowanceRegulation_list = this.query(PmAllowanceRegulation.class,
                new DBCondition(PmAllowanceRegulation.Field.allowanceRuleId,allowanceRuleIdList,Operator.IN));
        if(CommonUtil.isEmpty(allowanceRegulation_list))
            return;
        allowanceRuleIdList.clear();
        for(PmAllowanceRegulation allowanceRegulation : allowanceRegulation_list)
        {
            allowanceRuleIdList.add(allowanceRegulation.getAllowanceRuleId());
        }
        List<PmAllowanceFreeUsage> allowanceFreeUsage_list = this.query(PmAllowanceFreeUsage.class,
                new DBCondition(PmAllowanceFreeUsage.Field.allowanceRuleId,allowanceRuleIdList,Operator.IN));
        if(CommonUtil.isEmpty(allowanceFreeUsage_list))
            return;
        imsLogger.debug("***** begin to do onetime freeresource");
        for(PmAllowanceFreeUsage allowanceFreeUsage : allowanceFreeUsage_list)
        {
            resAmount = allowanceFreeUsage.getAmount();
            //@Date 2012-09-17 wuyujie #59263 ：如果配置了多个科目，需要多次调用
            if(resAmount == null)
                continue;
            item = allowanceFreeUsage.getFreeresItem();
            
            FreeResource fr = new FreeResource();
            fr.setAcctId(acctId);
            fr.setFreeResItem(item);
            fr.setFreeResValue(resAmount);
            fr.setProductId(prod.getProductId());
            fr.setResourceId(userId);
            
            //2012-07-20 yanchuan 免费资源充值传入sepcId
            //fr.setBusiSpecId(EnumCodeDefine.FREE_SPEC_ID);
            //@Date 2012-10-18 yugb :User Story #61367
            fr.setRechargeMethod(EnumCodeDefine.FREERESOURCE_RECHARGE_METHOD);
            fr.setForwardCycle(allowanceFreeUsage.getForwardCycle());
            // 计算免费资源的生效失效时间
            List<PmAllowanceRegulation> reguList = null;
            try
            {
                reguList = (List<PmAllowanceRegulation>)IMSUtil.matchDataObject(allowanceRegulation_list, new CacheCondition(PmAllowanceRegulation.Field.allowanceRuleId,allowanceFreeUsage.getAllowanceRuleId()));
            }
            catch (Exception e)
            {
                imsLogger.error(e);
            }
            // 2012-11-01 luojb On_Site Defect #63324 赠送一次性免费资源的生效失效时间要按配置算
            calFRDate(fr, prod,reguList.get(0), allowanceFreeUsage,acctId);
            
            FreerescoreService tpSer = (FreerescoreService)SpringUtil.getBean(ConstantDefine.SPRING_BEAN_ACCT_ASSET_TOPUPSERVICE);
            Holder<com.ailk.openbilling.topuppayment.persistence.common.entity.CommParaOut> out = new Holder<com.ailk.openbilling.topuppayment.persistence.common.entity.CommParaOut>(null);
            
            imsLogger.debug("***************FreeResource begin :acctId=" + acctId + ";freeResItem=" + item + ";freeResValue=" + resAmount
                    + ";productId=" + prod.getProductId() + ";resourceId=" + userId);
            Integer result = tpSer.doFreeResourceDeposit(ConvertUtil.javaOper2CommonParam(context), fr, out);
            imsLogger.debug("********do one time free resource result : "+result);
       }
        */
        imsLogger.debug("***** finish to do onetime freeresource");
    }
    
    /**
     * 计算一次性免费资源的生效失效时间
     * luojb 2012-11-1
     * @param fr
     * @param prod
     * @param regu
     * @param frUsage
     * @param acctId
     */
    /*
    public void calFRDate(FreeResource fr,CoProd prod,PmAllowanceRegulation regu,PmAllowanceFreeUsage frUsage,Long acctId)
    {
        BaseProductNotifyComponent pnc = context.getComponent(BaseProductNotifyComponent.class);
        // 判断是立即生效还是延迟生效
        Date validDate = prod.getProdValidDate();
        Integer effect = frUsage.getImmediateEffect();
        if(effect == EnumCodeDefine.FR_VALID_DELAY)
        {
            Integer offCycleType = frUsage.getCycleTypeNoim();
            Integer offCycleUnit = frUsage.getCycleUnitNoim();
            validDate = pnc.calculateMoveDate(validDate, offCycleType, offCycleUnit);
        }
        fr.setValidDate(validDate);
        
        // 判断周期类型是哪种：
        //0、参考账户账期. 
        //1、参考产品计费周期(须IMS 上发到资料MDB，一旦查不到将打错单). 
        //2、参考局部数据配置定义.(默认)
        Integer refMode = frUsage.getCycleRefMode();
        int cycleUnit = regu.getCycleUnit();
        int cycleType = regu.getCycleType();
        if(refMode != null && refMode == EnumCodeDefine.FR_CYCLE_REF_MODE_ACCT)
        {
            AccountQuery aq = context.getComponent(AccountQuery.class);
            List<BillCycleComplex> billCycles =  aq.queryBillCycle(acctId);
            BillCycleComplex cycle = aq.getTargetAcctCycle(billCycles, validDate);
            if(cycle != null)
            {
                cycleUnit = cycle.getCycleUnit();
                cycleType = cycle.getCycleType();
            }
        }else if(refMode != null && refMode == EnumCodeDefine.FR_CYCLE_REF_MODE_PROD)
        {
            ProductCycleComponent pcc = context.getComponent(ProductCycleComponent.class);
            List<CoProdBillingCycle> billCycles = pcc.queryProdBillingCycles(prod.getProductId(), prod.getObjectId());
            CoProdBillingCycle cycle = pcc.getTargetBillCycle(billCycles, validDate);
            if(cycle != null)
            {
                cycleUnit = cycle.getCycleUnit();
                cycleType = cycle.getCycleType();
            }
        }
        Date expireDate = pnc.calculateMoveDate(validDate, cycleType, cycleUnit);
        fr.setExpireDate(expireDate);
        imsLogger.debug("-----one time free resource.cycleunit["+cycleUnit+"],cycletype["+cycleType+"],validDate["+validDate+"],expireDate["+expireDate+"].");
    }
    */

    /**
     * @Description: 根据用户Id查找用户的主产品的销售品Id
     * @param userId
     * @return
     */
    public Integer getUserMainProdOfferingId(Long userId)
    {
        CoProd prod = this.queryPrimaryProductByUserId(userId);
        if (prod != null)
        {
            return prod.getProductOfferingId();
        }
        //@Date 2012-10-09 yugb :On_Site Defect #60767
        else
        {
            return -1;
        }
        // List<CoProd> prods = this.queryProdList(userId,
        // EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        // if (CommonUtil.isNotEmpty(prods))
        // {
        // for (CoProd prod : prods)
        // {
        // if (prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
        // {
        // return prod.getProductOfferingId();
        // }
        // else
        // {
        // continue;
        // }
        // }
        // throw
        // IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_HAVE_MAINPROMOTION,
        // userId);
        // }
        // else
        // {
        // throw
        // IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_HAVE_MAINPROMOTION,
        // userId);
        // }
    }

    /**
     * @Description: 判断用户订购的产品的billing_type与用户的billing_type是否一致， 用户的billing_type与销售品的billing_type是否一致
     * @param res
     * @param prodOperList
     * @author: tangjl5
     * @Date: 2011-10-28
     */
    private void checkProdAndUserBillingType(CmResource res, SProductOrder order)
    {
        CacheQuery cacheQry = context.getComponent(CacheQuery.class);
        Integer offeringBillingType = cacheQry.queryProdOfferingBillingType(order.getOffer_id());
        if (null == offeringBillingType)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_OFFERING_ATTRIBUTE_IS_NULL, order.getOffer_id());
        }
        Short prodBillingType = order.getBilling_type();
        if (prodBillingType != null)
        {
            if (prodBillingType == EnumCodeDefine.PROD_BILLTYPE_ALL)
            {
                order.setBilling_type((short) EnumCodeDefine.USER_PAYMODE_HYBRID);
            }
            else if (prodBillingType != EnumCodeDefine.PROD_BILLTYPE_PREPAID
                    && prodBillingType != EnumCodeDefine.PROD_BILLTYPE_POSTPAID
                    && prodBillingType != EnumCodeDefine.USER_PAYMODE_HYBRID)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "billing_type", "0,1,2");
            }
        }
        if (res != null)
        {
            // @Date2012-04-13 lijc3 验证付费模式逻辑修改
            Integer resBillingType = res.getBillingType();

            // 传入的为空，判断销售品和用户的是否匹配
            if (resBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID
                    || resBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID
                    || resBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID)
            {
                // 用户混合付费 产品也混合付费
                if (offeringBillingType == EnumCodeDefine.PROD_BILLTYPE_ALL)
                {
                    if (order.getBilling_type() == null || order.getBilling_type() == EnumCodeDefine.USER_PAYMODE_HYBRID)
                    {
                        order.setBilling_type((short) (resBillingType % 10));
                    }
                }
                else
                {
                    order.setBilling_type(offeringBillingType.shortValue());
                }
            }
            else
            {
                // 用户不是混合付费
                if (offeringBillingType == EnumCodeDefine.PROD_BILLTYPE_ALL)
                {
                    order.setBilling_type((short) (resBillingType % 10));
                }
                else
                {
                    // 判断是否匹配
                    if (offeringBillingType != resBillingType.intValue())
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_OFFER_BILLINGTYPE_NOT_MATCHING_USER,
                                order.getOffer_id(), res.getResourceId());
                    }
                    else
                    {
                        order.setBilling_type(offeringBillingType.shortValue());
                    }
                }
            }
            if (prodBillingType != null && prodBillingType.shortValue() != EnumCodeDefine.USER_PAYMODE_HYBRID
                    && prodBillingType.shortValue() != order.getBilling_type())
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_OFFER_BILLINGTYPE_NOT_MATCHING_PROD);
            }

            /*
             * if (null == order.getBilling_type()) { // 若ProductOrder中没有billing_type则使用用户的billing_type prodBillingType =
             * resBillingType; } else { prodBillingType = order.getBilling_type().intValue(); } // 若用户是hybrid，不进行判断，均可使用 if
             * (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID || res.getBillingType() ==
             * EnumCodeDefine.USER_PAYMODE_HYBRID || res.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID) {
             * if(offeringBillingType !=EnumCodeDefine.PROD_BILLTYPE_ALL&&prodBillingType !=EnumCodeDefine
             * .USER_PAYMODE_HYBRID&&offeringBillingType!=prodBillingType){ throw IMSUtil.throwBusiException(ErrorCodeDefine.
             * CHANGE_OWNER_OFFER_BILLINGTYPE_NOT_MATCHING_USER, order.getOffer_id(), res.getResourceId()); } return; } //
             * 若销售品上设置了限制预后付费属性，则判断销售品上的预后付费属性与用户的billing_type是否一致 // 若销售品上的限制是预后付都可以使用，或者销售品上没有限制，则不作判断 if
             * (EnumCodeDefine.PROD_OFFERING_BILLINGTYPE_ALL != offeringBillingType && (offeringBillingType !=
             * resBillingType.intValue()||offeringBillingType!=prodBillingType)) { throw
             * IMSUtil.throwBusiException(ErrorCodeDefine. CHANGE_OWNER_OFFER_BILLINGTYPE_NOT_MATCHING_USER, order.getOffer_id(),
             * res.getResourceId()); } // 判断用户的billing_type与订购的产品的billing_type是否一致 if (prodBillingType!=EnumCodeDefine
             * .USER_PAYMODE_HYBRID&&res.getBillingType() != prodBillingType.intValue()) { throw
             * IMSUtil.throwBusiException(ErrorCodeDefine .CHANGE_OWNER_PRODUCT_BILLINGTYPE_NOT_MATCHING_USER,
             * order.getOffer_id()); }
             */
        }
        else
        {
            if (prodBillingType != null)
            {
                // 只要有一个为混合付费，就进入
                if (prodBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID
                        || offeringBillingType == EnumCodeDefine.PROD_BILLTYPE_ALL)
                {
                    // 传入混合付费则设置为销售品的付费模式，否则就不用处理
                    if (prodBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID)
                    {
                        if (offeringBillingType == EnumCodeDefine.PROD_BILLTYPE_ALL)
                        {
                            order.setBilling_type((short) EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
                        }
                        else
                        {
                            order.setBilling_type(offeringBillingType.shortValue());
                        }
                    }
                    else
                    {
                        if (offeringBillingType == EnumCodeDefine.PROD_BILLTYPE_ALL)
                        {
                            order.setBilling_type(prodBillingType);
                        }
                        else
                        {
                            if (offeringBillingType != prodBillingType.intValue())
                            {
                                throw IMSUtil.throwBusiException(
                                        ErrorCodeDefine.CHANGE_OWNER_PRODUCT_BILLINGTYPE_NOT_MATCHING_USER, order.getOffer_id());
                            }
                        }
                    }
                }
                else
                {
                    // 都不是混合的时候 判断是否一样
                    if (offeringBillingType != prodBillingType.intValue())
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_PRODUCT_BILLINGTYPE_NOT_MATCHING_USER,
                                order.getOffer_id());
                    }
                }
            }
            else
            {
                // 传入为空的情况
                if (offeringBillingType == EnumCodeDefine.PROD_BILLTYPE_ALL)
                {
                    order.setBilling_type((short) EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
                }
                else
                {
                    order.setBilling_type(offeringBillingType.shortValue());
                }
            }
            /*
             * if (order.getBilling_type() == null) { order.setBilling_type(offeringBillingType.shortValue()); } else { if
             * (offeringBillingType != null) { // 传入的不和销售品的付费属性对应，并且销售平对应属性不是混合的，报错 if (offeringBillingType.shortValue() !=
             * order.getBilling_type() && offeringBillingType.shortValue() != EnumCodeDefine.PROD_BILLTYPE_ALL) { throw
             * IMSUtil.throwBusiException (ErrorCodeDefine.CHANGE_PROD_BILL_TYPE_NOT_FIX, order.getBilling_type() ,
             * offeringBillingType); } if (order.getBilling_type().shortValue() == offeringBillingType &&
             * order.getBilling_type().shortValue() == EnumCodeDefine.PROD_BILLTYPE_ALL) { order.setBilling_type((short)
             * EnumCodeDefine.PROD_BILLTYPE_POSTPAID); } } }
             */
        }
    }

    /**
     * @Description: 把本来的入参由多个字段，变成simItem实体。实体新增serial_no字段
     * @param simItem
     * @author: zhangzj3
     * @Date: 2012-5-17
     */
    public void operateMultiSim(SimItem simItem)
    {
        Long productId = null;
        Short operType = null;
        String sim = "";
        if (simItem != null)
        {
            productId = simItem.getProduct_id();
            operType = simItem.getOper_type();
            sim = simItem.getSim();
        }
        CoProd prod = this.queryProd(simItem.getProduct_id());
        if (prod == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, productId);
        }
        Integer busiFlag = prod.getBusiFlag();

        if (busiFlag.equals(SpecCodeDefine.MULTI_SIM))
        {
            List<CoProdCharValue> charValueList = queryCharValueAndObjectId(productId, SpecCodeDefine.MULTI_SIM_NUMBER,
                    prod.getObjectId());

            switch (operType)
            {
            case EnumCodeDefine.OPER_TYPE_ADD:
                addSim(simItem, charValueList, prod);
                break;
            case EnumCodeDefine.OPER_TYPE_DELETE:
                delSim(sim, productId, charValueList);
                break;
            default:
                break;
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CAN_NOT_OPERATE_MULTI_SIM_FOR_THIS_PROD_ID, productId);
        }
    }

    /**
     * @Description: 把本来的入参sim,productId变成simItem实体。实体新增serial_no字段
     * @param simItem
     * @author: zhangzj3
     * @Date: 2012-5-17
     */
    private void addSim(SimItem simItem, List<CoProdCharValue> charValueList, CoProd prod)
    {
        // @Date 2012-05-10 wangdw5 : 新增的sim卡号需要判断是否已经被别的用户使用
        List<CmResIdentity> simIdens = query(CmResIdentity.class,
                new DBCondition(CmResIdentity.Field.identity, simItem.getSim()), new DBCondition(
                        CmResIdentity.Field.identityType, new int[] { EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE,
                                EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM }, Operator.IN));
        if (CommonUtil.isNotEmpty(simIdens))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.IMSI_IS_EXIST, simItem.getSim());
        }
        if (!CommonUtil.isEmpty(charValueList))
        {
            for (CoProdCharValue cpcv : charValueList)
            {
                String value = cpcv.getValue();
                if (value.equals(simItem.getSim()))
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.IMSI_IS_EXIST, simItem.getSim());
                }
            }
        }

        addIdentity(simItem, prod);

        long groupId = DBUtil.getSequence();
        Date validDate = context.getRequestDate();
        Date expireDate = IMSUtil.getDefaultExpireDate();

        if (expireDate.getTime() > prod.getExpireDate().getTime())
        {// 如果传入的expireDate比产品的expireDate大
         // 设置为小的
            expireDate = prod.getExpireDate();
        }

        CoProdCharValue coProdCharValue = context.getComponent(UserComponent.class).createCoProdCharValue(
                simItem.getProduct_id(), groupId, SpecCodeDefine.MULTI_SIM_NUMBER, simItem.getSim(), expireDate, validDate,
                prod.getObjectId(), prod.getObjectType());
        this.insert(coProdCharValue);
        
        //@Date 2012-06-27 zhangzj3 [45390]MultiSIM Serial No, 增加处理sim卡serial no
        if(simItem.getSerial_no() != null){
        	 CoProdCharValue prodCharValue = context.getComponent(UserComponent.class).createCoProdCharValue(
                     simItem.getProduct_id(), groupId, SpecCodeDefine.MULTI_SIM_SERIAL_NO, simItem.getSerial_no(), expireDate, validDate,
                     prod.getObjectId(), prod.getObjectType());
             this.insert(prodCharValue);
        }
       
    }

    /**
     * @Description: 把本来的入参sim,productId变成simItem实体。实体新增serial_no字段
     * @param simItem
     * @author: zhangzj3
     * @Date: 2012-5-17
     */
    private void addIdentity(SimItem simItem, CoProd prod)
    {
        CmResIdentity dmResIdentity = new CmResIdentity();
        CmResIdentity result = querySingle(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identity, simItem.getSim()),
                new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM));
        if (result != null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.IMSI_IS_EXIST, simItem.getSim());
        }

        dmResIdentity.setSoNbr(context.getSoNbr());
        dmResIdentity.setResourceId(queryUserIdByProductId(simItem.getProduct_id()));
        dmResIdentity.setValidDate(prod.getValidDate());
        dmResIdentity.setExpireDate(prod.getExpireDate());
        // 2012-05-18 zhangzj3 [45390]MultiSIM Serial No, 增加处理sim卡serial no
        dmResIdentity.setIdentity(simItem.getSim());
        //@Date 2012-10-29 yangjh : User Story #62685 MultiSIM的SIM卡改成两个字段填写同样的值
        dmResIdentity.setRelIdentity(simItem.getSim());
        dmResIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM);
        if (simItem.getSerial_no() != null)
        {
            dmResIdentity.setSerialNo(simItem.getSerial_no());
        }
        insert(dmResIdentity);
    }

    private void delIdentity(String sim, Long productId)
    {
        // CmResIdentity dmResIdentity = new CmResIdentity();
        // dmResIdentity.setIdentity(sim);
        // dmResIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM);
        // dmResIdentity.setResourceId(queryUserIdByProductId(productId));

        this.deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identity, sim), new DBCondition(
                CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM), new DBCondition(
                CmResIdentity.Field.resourceId, queryUserIdByProductId(productId)));
    }



    private void delSim(String sim, Long productId, List<CoProdCharValue> charValueList)
    {
        boolean flag = false;
        if (!CommonUtil.isEmpty(charValueList))
        {
            for (CoProdCharValue cpcv : charValueList)
            {
                String value = cpcv.getValue();
                if (value.equals(sim))
                {
                    // CoProdCharValue coProdCharValue = new CoProdCharValue();
                    // coProdCharValue.setProductId(productId);
                    // coProdCharValue.setValue(sim);
                    // coProdCharValue.setGroupId(cpcv.getGroupId());
                    // coProdCharValue.setObjectId(cpcv.getObjectId());
                    deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, productId),
                            new DBCondition(CoProdCharValue.Field.value, sim), new DBCondition(CoProdCharValue.Field.groupId,
                                    cpcv.getGroupId()), new DBCondition(CoProdCharValue.Field.objectId, cpcv.getObjectId()));
                    delIdentity(sim, productId);
                    flag = true;
                    break;
                }
            }
        }

        if (CommonUtil.isEmpty(charValueList) || !flag)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CAN_NOT_OPERATE_MULTI_SIM_FOR_THIS_PROD_ID, productId);
        }
    }

    /**
     * 首次激活 删除、修改产品记录时需要将老记录的失效时间、新记录的生效时间置为激活时间 wukl 2011-12-15
     * 
     * @return
     */
    public Date getActiveDate()
    {
        Date activeDate = null;
        if (context.getExtendParam(ConstantDefine.ACTIVE_DATE) != null)
        {
            activeDate = (Date) context.getExtendParam(ConstantDefine.ACTIVE_DATE);
        }

        return activeDate;
    }

    /**
     * 转换产品结构 2011-12-31 wukl transferCoProdComplex方法的失效时间参数设置为当前请求时间，确保产品立即删除，不依赖销售品的账期设置
     * 
     * @param prodList
     * @return
     */
    public List<ProductOrderComplex> transferCoProdList2Complex(List<CoProd> prodList)
    {
        List<ProductOrderComplex> complexs = new ArrayList<ProductOrderComplex>();
        for (CoProd dmProd : prodList)
        {
            complexs.add(transferCoProdComplex(dmProd, context.getRequestDate(), null, null));
        }
        return complexs;
    }

    /**
     * 转换产品结构
     * 
     * @param dmProd
     * @param expireDate
     * @param soId CRM传入的soId
     * @return
     */
    public ProductOrderComplex transferCoProdComplex(CoProd dmProd, Date expireDate, String soId, Short validType)
    {
        ProductOrderComplex complex = new ProductOrderComplex();
        soId = soId == null ? String.valueOf(context.getSoNbr()) : soId;
        complex.setSoId(soId);
        complex.setCoProd(dmProd);
        complex.setExpireDate(expireDate);
        complex.setValidType(validType);
        return complex;
    }

    /**
     * 替换主产品扣减用户有效期
     */
    public void changeUserValidityByReplaceMainProd(CoProd mainProd, PmProductOffering newOffer, Long userId, String phoneId)
    {
        BaseLifeCycleComponent lifeCmp = context.getComponent(BaseLifeCycleComponent.class);
        RuleComponent ruleCmp = context.getComponent(RuleComponent.class);
        User3hBean bean = context.get3hTree().loadUser3hBean(userId, phoneId);
        // 后付费直接返回
        if (mainProd.getBillingType() == EnumCodeDefine.PROD_BILLTYPE_POSTPAID)
        {
            return;
        }
        // 获取用户有效期 lijc3 修改balanceType
        Acct3hBean acctBean = bean.getBillAcct3hBean();
        Integer balanceType = acctBean.getAccount().getBalanceType();
        CmResValidity userValidity = null;
        if (EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_USER == balanceType)
        {
            userValidity = lifeCmp.queryUserValidity(bean.getUserId());
        }
        else
        {
            userValidity = lifeCmp.queryAcctValidity(acctBean.getAcctId());
        }
        if (userValidity == null)
        {
            imsLogger.info("*************CmResValidity is null by userId: " + bean.getUserId());
            return;
        }
        Integer oldDuctDay = null;
        Integer newDuctDay = null;
        // 获取处理规则.根据处理规则获取到扣减到多少天
        Integer oldPriceRuleId = ruleCmp.getPriceRuleId4ByReplaceMainProduct(bean.getUserId(), mainProd);
        if (oldPriceRuleId != null)
        {
            oldDuctDay = this.queryChangeMainDeductDaysByProductRule(oldPriceRuleId, EnumCodeDefine.CHANGE_PRODUCT_OUT);
        }
        // 缓存中获取新订购的销售品
        CoProd newCoProd = context.getSingleCache(CoProd.class,
                new DBCondition(CoProd.Field.productOfferingId, newOffer.getProductOfferingId()));
        Integer newPriceRuleId = ruleCmp.getPriceRuleId4ByReplaceMainProduct(bean.getUserId(), newCoProd);
        if (newPriceRuleId != null)
        {
            newDuctDay = this.queryChangeMainDeductDaysByProductRule(newPriceRuleId, EnumCodeDefine.CHANGE_PRODUCT_IN);
        }
        // 扣减到多少天
        Integer descDays = null;
        if (oldDuctDay != null && newDuctDay == null)
        {
            descDays = oldDuctDay;
        }
        else if (oldDuctDay == null && newDuctDay != null)
        {
            descDays = newDuctDay;
        }
        else if (oldDuctDay != null && newDuctDay != null)
        {
            if (oldDuctDay.intValue() < newDuctDay)
            {
                descDays = oldDuctDay;
            }
            else
            {
                descDays = newDuctDay;
            }
        }
        if (descDays == null)
        {
            imsLogger.info("*********deduct day = 0 ,return");
            return;
        }
        // 用户现有有效期天数
        Long days = DateUtil.getBetweenDays(context.getRequestDate(), userValidity.getExpireDate());
        // 当前有效期天数小于等于需要扣减到的天数,直接返回,否则进行扣减
        if (days.intValue() <= descDays)
        {
            return;
        }
        // 实际需要扣减的天数
        int reduceDays = days.intValue() - descDays.intValue();
        imsLogger.info("*********************实际需要扣减天数为: " + reduceDays);
        SLifeCycleByAcctChgReq lifeCycleReq = new SLifeCycleByAcctChgReq();
        lifeCycleReq.setTriggerEventType(EnumCodeDefine.LIFECYCLE_EVENT_VALIDITYCHANGE);
        lifeCycleReq.setReduceDays(reduceDays);
        lifeCycleReq.setAcct_id(bean.getBillAcctId());
        lifeCycleReq.setBalanceType(bean.getBillAcct3hBean().getAccount().getBalanceType().shortValue());
        lifeCycleReq.setForceFlag((short) 0);
        lifeCycleReq.setResource_id(bean.getUserId());
        // 调用接口调整有效期
        context.getComponent(LifeCycleByAcctChgComponent.class).updateValidityByTopUp(lifeCycleReq, null);
    }

    /**
     * @Description 修改付费模式
     * @Author lijingcheng
     * @param prodList
     * @return
     */
    public SProductOrderResultList changePayModeProductInfo(SProductOrderList prodList)
    {
        SProductOrderResultList prodResutList = new SProductOrderResultList();
        if (prodList == null || CommonUtil.isEmpty(prodList.getItem()))
        {
            return prodResutList;
        }
        SProductOrderResult[] prodResults = new SProductOrderResult[prodList.getItem().length];
        SProductOrderList packOrderList = new SProductOrderList();
        for (int i = 0; i < prodList.getItem().length; i++)
        {
            SProductOrder prod = (SProductOrder) prodList.getItem()[i];
            CoProd dmProd = null;
            // userIsAvailable(prod.getProduct_id());
            if (isAccountLevelOrderProduct(prod))
            {
                dmProd = this.changePayMode(prod, null);
            }
            else
            {
                User3hBean bean = context.get3hTree().loadUser3hBean(prod.getUser_id());
                dmProd = this.changePayMode(prod, bean.getUser());
            }
            // 处理打包关系
            handleOfferRel(dmProd, prod, EnumCodeDefine.CHANGE_PROD_CHANGE_PAYMODE, null, null, packOrderList);

            SProductOrderResult prodResult = new SProductOrderResult();
            prodResult.setOffer_id(prod.getOffer_id());
            prodResult.setProduct_id(prod.getProduct_id());
            String soId = prod.getSo_id();
            soId = soId == null ? String.valueOf(context.getSoNbr()) : soId;
            prodResult.setSo_id(soId);
            if (dmProd != null)
            {
                prodResult.setExpire_date(DateUtil.formatDate(dmProd.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                prodResult.setValid_date(DateUtil.formatDate(dmProd.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            prodResult.setProduct_param_list(prod.getParam_list());
            prodResults[i] = prodResult;

        }
        // 合并返回结果
        mergtSProductOrderList(prodList, packOrderList);
        prodResutList.setSProductOrderResultList_Item(prodResults);

        // 上发通知ts
        if (!ProjectUtil.is_CN_SH())
            this.prodNotifyUpload(this.parseProdNotifyList(prodResutList, EnumCodeDefine.CHANGE_PROD_CHANGE_PAYMODE));

        return prodResutList;
    }

    // 修改付费模式
    private CoProd changePayMode(SProductOrder prod, CmResource user)
    {
        if (prod.getProduct_id() == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_ID_IS_NULL);
        }
        CoProd dmProd = null;
        if (user != null)
        {
            dmProd = this.loadProd(prod.getProduct_id(), user.getResourceId());
        }
        else if (prod.getAcct_id() != null)
        {
            dmProd = this.loadProd(prod.getProduct_id(), prod.getAcct_id());
        }
        else
        {
            dmProd = this.loadProd(prod.getProduct_id());
        }

        // if (dmProd == null)
        // {
        // throw
        // IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, new
        // Object[] { prod.getProduct_id() });
        // }
        Short billType = null;
        // 用户是混合付费的时候，必须传入bill_type
        if (user != null)
        {
            if (user.getBillingType() != EnumCodeDefine.USER_PAYMODE_POSTPAID
                    && user.getBillingType() != EnumCodeDefine.USER_PAYMODE_PREPAID)
            {
                if (prod.getBilling_type() == null)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_BILLING_TYPE_IS_NULL);
                }
                else
                {
                    billType = prod.getBilling_type();
                }
            }
            else
            {
                if (prod.getBilling_type() != null)
                {
                    billType = prod.getBilling_type();
                    if (billType != EnumCodeDefine.USER_PAYMODE_HYBRID && billType.intValue() != user.getBillingType())
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.BILL_TYPE_NOT_MACTH_USER, billType);
                    }
                }
                else
                {
                    billType = user.getBillingType().shortValue();
                }
            }
        }
        else
        {
            billType = prod.getBilling_type();
        }
        if (billType == null)
        {
            return dmProd;
        }
        // if (user!=null&&user.getBillingType() !=
        // EnumCodeDefine.USER_PAYMODE_POSTPAID
        // && user.getBillingType() != EnumCodeDefine.USER_PAYMODE_PREPAID) {
        // if (prod.getBilling_type() == null) {
        // throw
        // IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_BILLING_TYPE_IS_NULL);
        // } else {
        // billType = prod.getBilling_type();
        // }
        // } else {
        // if (prod.getBilling_type() != null) {
        // billType = prod.getBilling_type();
        // if (billType.intValue() != user.getBillingType()) {
        // throw
        // IMSUtil.throwBusiException(ErrorCodeDefine.BILL_TYPE_NOT_MACTH_USER,
        // billType);
        // }
        // } else {
        // billType = user.getBillingType().shortValue();
        // }
        // }
        Integer offerId = dmProd.getProductOfferingId();
        PayModeComponent pmCmp = context.getComponent(PayModeComponent.class);
        // 验证销售品是否支持该付费模式
        pmCmp.checkMainProdByChangePayMode(billType, offerId);
        // 修改付费模式

        Date prodValidDate = this.changePayMode(dmProd, billType);

        dmProd.setValidDate(prodValidDate);
        return dmProd;
    }

    /**
     * 修改付费模式和扣费模式下周期生效 luojb 2012-3-17
     * 
     * @param dmProd
     * @param billType
     * @return
     */
    public Date changePayMode(CoProd dmProd, Short billType)
    {
        int oldBillingType = dmProd.getBillingType();

        // 计算deductFlag
        Integer coProdBillingType = billType.intValue();
        PmCompositeDeductRule deductRule = context.getComponent(ProdStsComponent.class).queryPmCompsiteDeductRuleByOfferId(
                dmProd.getProductOfferingId(), billType.intValue());
        if (deductRule != null)
        {
            coProdBillingType = deductRule.getDeductFlag();
        }
        else
        {
            if (coProdBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID)
            {// budget中存在2的付费模式，当做后付费处理
                coProdBillingType = EnumCodeDefine.USER_PAYMODE_POSTPAID;
            }
        }
        Date prodValidDate = context.getRequestDate();
        // 预转后 luojb 2012-3-14
        if (oldBillingType == EnumCodeDefine.PROD_BILLTYPE_PREPAID
                && billType.intValue() == EnumCodeDefine.PROD_BILLTYPE_POSTPAID)
        {
            // 查询账期
            ProductCycleComponent pcc = context.getComponent(ProductCycleComponent.class);
            List<CoProdBillingCycle> billCycleList = pcc.queryProdBillingCycles(dmProd.getProductId(), dmProd.getObjectId());
            CoProdBillingCycle curBillCycle = null;
            List<CoProdBillingCycle> futureBillCycleList = new ArrayList<CoProdBillingCycle>();
            if (CommonUtil.isNotEmpty(billCycleList))
            {
                for (CoProdBillingCycle billCycle : billCycleList)
                {
                    Date validDate = billCycle.getValidDate();
                    Date expireDate = billCycle.getExpireDate();
                    if (validDate.before(context.getRequestDate()) && expireDate.after(context.getRequestDate()))
                    {
                        curBillCycle = billCycle;
                    }
                    else if (validDate.after(context.getRequestDate()))
                    {
                        futureBillCycleList.add(billCycle);
                    }
                }
                // 设置产品和产品当前周期的旧记录下周期失效、新记录下周期生效
                if (curBillCycle != null)
                {
                    Date curBillCycleEndDate = pcc.getCurrentBillCycleEndDate(curBillCycle);
                    Date curBillCycleExpireDate = curBillCycle.getExpireDate();
                    prodValidDate = curBillCycleEndDate;

                    CoProd value = new CoProd();
                    value.setBillingType(billType.intValue());

                    // 可能已存在下周期生效数据，直接更新，若有数据更新则不需要分割
                    // CoProd prodNext = new CoProd();
                    // prodNext.setProductId(dmProd.getProductId());
                    // prodNext.setObjectId(dmProd.getObjectId());
                    // prodNext.setValidDate(prodValidDate);
                    List upNextProdList = this.updateByCondition(value,
                            new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
                                    CoProd.Field.objectId, dmProd.getObjectId()), new DBCondition(CoProd.Field.validDate,
                                    prodValidDate));
                    // 没有下周期的数据更新，则需要分割原来的数据
                    if (CommonUtil.isEmpty(upNextProdList))
                    {
                        // CoProd where = new CoProd();
                        // where.setProductId(dmProd.getProductId());
                        // where.setObjectId(dmProd.getObjectId());
                        // where.setValidDate(dmProd.getValidDate());
                        this.updateByCondition(value, prodValidDate,
                                new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
                                        CoProd.Field.objectId, dmProd.getObjectId()), new DBCondition(CoProd.Field.validDate,
                                        dmProd.getValidDate()));
                    }

                    CoProdBillingCycle cycleValue = new CoProdBillingCycle();
                    // 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
                    // cycleValue.setDeductFlag(coProdBillingType);
                    // CoProdBillingCycle cycleWhere = new CoProdBillingCycle();
                    // cycleWhere.setProductId(dmProd.getProductId());
                    // cycleWhere.setObjectId(dmProd.getObjectId());
                    // cycleWhere.setValidDate(curBillCycle.getValidDate());
                    if (curBillCycleEndDate.equals(curBillCycleExpireDate))
                    {
                        // 如果当前账期记录的失效时间为当前账期的结束日,不用切割记录
                        updateByCondition(cycleValue, new DBCondition(CoProdBillingCycle.Field.deductFlag, coProdBillingType),
                                new DBCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId()), new DBCondition(
                                        CoProdBillingCycle.Field.objectId, dmProd.getObjectId()), new DBCondition(
                                        CoProdBillingCycle.Field.validDate, curBillCycle.getValidDate()));
                    }
                    else
                    {
                        // 当前账期结束日在当前账期记录的中间，需要切割记录
                        updateByCondition(cycleValue, prodValidDate, new DBCondition(CoProdBillingCycle.Field.deductFlag,
                                coProdBillingType), new DBCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId()),
                                new DBCondition(CoProdBillingCycle.Field.objectId, dmProd.getObjectId()), new DBCondition(
                                        CoProdBillingCycle.Field.validDate, curBillCycle.getValidDate()));
                    }
                }
                else
                {
                    // 产品没有当前账期，新纪录可以立即生效
                    CoProd value = new CoProd();
                    value.setBillingType(billType.intValue());

                    // CoProd where = new CoProd();
                    // where.setProductId(dmProd.getProductId());
                    // where.setObjectId(dmProd.getObjectId());
                    this.updateByCondition(value, new DBCondition(CoProd.Field.productId, dmProd.getProductId()),
                            new DBCondition(CoProd.Field.objectId, dmProd.getObjectId()));
                }
                // 设置产品周期的未来数据
                if (CommonUtil.isNotEmpty(futureBillCycleList))
                {
                    for (CoProdBillingCycle billCycle : futureBillCycleList)
                    {
                        CoProdBillingCycle cycleValue = new CoProdBillingCycle();
                        // 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
                        // cycleValue.setDeductFlag(coProdBillingType);

                        // CoProdBillingCycle cycleWhere = new CoProdBillingCycle();
                        // cycleWhere.setProductId(dmProd.getProductId());
                        // cycleWhere.setObjectId(dmProd.getObjectId());
                        // cycleWhere.setValidDate(billCycle.getValidDate());
                        this.updateByCondition(cycleValue,
                                new DBCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId()), new DBCondition(
                                        CoProdBillingCycle.Field.objectId, dmProd.getObjectId()), new DBCondition(
                                        CoProdBillingCycle.Field.validDate, billCycle.getValidDate()));
                    }
                }
            }
            else
            {
                // 产品没有账期，新纪录可以立即生效
                CoProd value = new CoProd();
                value.setBillingType(billType.intValue());

                // CoProd where = new CoProd();
                // where.setProductId(dmProd.getProductId());
                // where.setObjectId(dmProd.getObjectId());
                this.updateByCondition(value, new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
                        CoProd.Field.objectId, dmProd.getObjectId()));
            }
        }
        else
        {
            CoProd value = new CoProd();
            value.setBillingType(billType.intValue());
            // CoProd where = new CoProd();
            // where.setProductId(dmProd.getProductId());
            // where.setObjectId(dmProd.getObjectId());
            this.updateByCondition(value, new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
                    CoProd.Field.objectId, dmProd.getObjectId()));

            CoProdBillingCycle cycleValue = new CoProdBillingCycle();
            // zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
            // cycleValue.setDeductFlag(coProdBillingType);
            // CoProdBillingCycle cycleWhere = new CoProdBillingCycle();
            // cycleWhere.setProductId(dmProd.getProductId());
            // cycleWhere.setObjectId(dmProd.getObjectId());
            this.updateByCondition(cycleValue, new DBCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId()),
                    new DBCondition(CoProdBillingCycle.Field.objectId, dmProd.getObjectId()));
        }
        return prodValidDate;
    }

    /**
     * 退订用户的服务并同步给CRM ljc 2011-11-15
     * 
     * @param delServList
     * @param userId
     */
    private void syncService2CRM(List<Integer> delServList, Long userId)
    {
        List<ImsBarServiceSync> servCycleList = new ArrayList<ImsBarServiceSync>();
        for (int i = delServList.size() - 1; i > -1; i--)
        {
            CmResServCycle servCycle = context.getComponent(ServiceComponent.class).queryCmResServ(userId, delServList.get(i));
            this.deleteResServCycleBySpecId(userId, Long.valueOf(delServList.get(i)));
            // 退订的服务同步给CRM
            if (servCycle != null)
            {
                ImsBarServiceSync sync = new ImsBarServiceSync();
                // @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
                sync.setId(DBUtil.getSequence());
                sync.setWorkOrderType(0);
                sync.setAction(EnumCodeDefine.STS_INVALID);
                sync.setBusiServiceId(servCycle.getServiceSpecId());
                sync.setUserId(userId);
                sync.setPhoneId(context.get3hTree().loadUser3hBean(userId).getPhoneId());
                // sync.setSoDate(servCycle.getSoDate());
                sync.setSoNbr(servCycle.getSoNbr());
                sync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
                sync.setCreateDate(context.getRequestDate());
                // sync.setBudget(0L);
                servCycleList.add(sync);
            }
        }
        // 插入同步CRM接口表中，后续会有定时任务将数据同步给CRM
        this.insertBatch(servCycleList);
    }

    public List<Integer> queryResourceServIdListExProdId(Long userId, Long prodId) throws IMSException
    {
        List<CoProd> prodList = this.queryProdListByUserId(userId);
        // 如果为空，那么不等于的产品也为空，直接返回null
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        Set<Integer> offerIds = new HashSet<Integer>();
        for (CoProd dmProd : prodList)
        {
            if (dmProd.getProductId() != prodId)
            {
                offerIds.add(dmProd.getProductOfferingId());
            }
        }
        if (offerIds.size() == 0)
        {
            return null;
        }
        return super.queryServiceSpecIdListByOfferIds(offerIds);
    }

    /**
     * BOSS内部返回订购的时候调用的(没有入库)
     * 
     * @Description
     * @Author lijingcheng
     * @param offerId 不可空
     * @param acctId
     * @param userId 和userId不能同时为空
     * @param validDate 可空
     * @param expireDate 可空
     * @return
     */
    public ImsOrderProduct createImsOrderProduct(Integer offerId, Long acctId, Long userId, Date validDate, Date expireDate)
    {
        if (acctId == null && userId == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acctId/userId");
        }
        Long objectId = null;
        Integer objectType = null;
        if (userId != null)
        {
            objectId = userId;
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        }
        else
        {
            CaAccount dmAccount = context.get3hTree().loadAcct3hBean(acctId).getAccount();
            
            objectId = acctId;
            if (dmAccount.getAccountClass() == EnumCodeDefine.ACCOUNT_CLASS_GCA)
            {
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_GCA;
            }
            else
            {
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
            }
        }
        return createImsOrderProduct(offerId, objectType, objectId, validDate, expireDate);
    }

    /**
     * BOSS内部返回订购的时候调用的(没有入库)
     * 
     * @Description
     * @Author lijingcheng
     * @param offerId 不可空
     * @param acctId
     * @param userId 和userId不能同时为空
     * @param validDate 可空
     * @param expireDate 可空
     * @return
     */
    public ImsOrderProduct createImsOrderProduct(Integer offerId, Integer objectType, Long objectId, Date validDate,
            Date expireDate)
    {
        ImsOrderProduct imsProd = new ImsOrderProduct();
        imsProd.setCreateDate(context.getRequestDate());
        // @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
        imsProd.setId(DBUtil.getSequence());
        imsProd.setOfferingId(offerId);
        imsProd.setSoNbr(context.getSoNbr());
        imsProd.setSts(EnumCodeDefine.SERVICE_CYCLESTS_ACTIVE);
        imsProd.setValidDate(validDate);
        imsProd.setExpireDate(expireDate);
        imsProd.setObjectId(objectId);
        imsProd.setObjectType(objectType);
        return imsProd;
    }

    private SProdNotify parseProdNotify(CoProd prod, int operType)
    {
        Timestamp validTime = DateUtil.date2Timestamp(prod.getValidDate());
        Timestamp expireTime = DateUtil.date2Timestamp(prod.getExpireDate());
        Date irValidDate = (Date) context.getExtendParam(ConstantDefine.IR_PROD_VALID_DATE);
        Date irExpireDate = (Date) context.getExtendParam(ConstantDefine.IR_PROD_EXPIRE_DATE);
        Timestamp irValidTime = DateUtil.date2Timestamp(irValidDate);
        Timestamp irExpireTime = DateUtil.date2Timestamp(irExpireDate);
        Long productId = prod.getProductId();
        SProdNotify notify = new SProdNotify();
        notify.setBusiFlag(prod.getBusiFlag());
        Long objectId = prod.getObjectId();
        Integer objectType = prod.getObjectType();
        notify.setObjectId(objectId);
        notify.setObjectType(objectType);
        notify.setOfferId(prod.getProductOfferingId());
        notify.setProductId(productId);
        notify.setValidDate(validTime);
        notify.setExpireDate(expireTime);
        notify.setOperType(operType);
        notify.setIrValidDate(irValidTime);
        notify.setIrExpireDate(irExpireTime);
        notify.setIrCountryName((String) context.getExtendParam(ConstantDefine.IR_PROD_NAME));
        notify.setBillingType(prod.getBillingType());
        
        if (prod.getPricingPlanId() != null)
            notify.setPricePlanId(prod.getPricingPlanId());

        if (prod.getIsMain().intValue() == EnumCodeDefine.PRODUCT_MAIN)
        {
            notify.setIsMain(EnumCodeDefine.PRODUCT_MAIN);
            Integer oldMainProdOfferId = (Integer) context.getExtendParam(ConstantDefine.REPLACE_MAIN_PROD_OLD);
            // 订购主产品，需要把旧的主产品一并保存
            if (operType == EnumCodeDefine.CHANGE_PROD_ADD && oldMainProdOfferId != null)
            {
                notify.setOldMainOfferId(oldMainProdOfferId);
            }
        }
        else
        {
            notify.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
        }

        return notify;
    }

    /**
     * 组装上发ts的数据 luojb 2012-2-10
     * 
     * @param prodResultList
     * @param objectId
     * @param objectType
     * @param operType
     * @return
     */
    private List<SProdNotify> parseProdNotifyList(SProductOrderResultList prodResultList, int operType)
    {
        if (prodResultList == null || CommonUtil.isEmpty(prodResultList.getSProductOrderResultList_Item()))
            return null;
        List<SProdNotify> prodNotifyList = new ArrayList<SProdNotify>();
        for (SProductOrderResult result : prodResultList.getSProductOrderResultList_Item())
        {
            Long productId = result.getProduct_id();
            CoProd prod = context.getSingleCache(CoProd.class, new CacheCondition(CoProd.Field.productId, productId));
            if (prod == null)
                prod = loadProd(productId);
            SProdNotify prodNotify = parseProdNotify(prod, operType);
            if (prodNotify != null)
                prodNotifyList.add(prodNotify);
        }
        return prodNotifyList;
    }

    public void prodNotifyUpload(List<SProdNotify> prodNotifyList)
    {
        CsdlArrayList<SProdNotify> sProdNotifyList = new CsdlArrayList<SProdNotify>(SProdNotify.class);
        if (CommonUtil.isEmpty(prodNotifyList))
            return;
        for (SProdNotify notify : prodNotifyList)
        {
            sProdNotifyList.add(notify);
        }
        com.ailk.openbilling.persistence.imssdl.entity.SOperInfo sOper = ConvertUtil.javaOper2SdlOper(context.getOper());
        sOper.setBusiCode(EnumCodeDefine.CREATE_PROD_NOTIFY_BUSI_CODE);
        CBSErrorMsg cErrorMsg = new CBSErrorMsg();
        Boolean uploadTs = SysUtil.getBoolean(SysCodeDefine.busi.CREATE_PROD_NOTIFY_UPLOAD_TS);
        if (uploadTs == null || !uploadTs)
        {
            imsLogger.info(
                    "#######start to create product notify. data list size = " + sProdNotifyList.size());
            BaseBusiBean busiBean = (BaseBusiBean)BusiUtil.getBusiBean(BusiCodeDefine.PROD_NOTIFY_BUSI);
            //CreateProdNotifyInfoBusiBean busiBean = new CreateProdNotifyInfoBusiBean();//9094
            busiBean.setContext(context);
            busiBean.init(sProdNotifyList);
            busiBean.doBusiness(sProdNotifyList);
        }
        else
        {
            imsLogger.info("#######product notify upload. data list size = " + sProdNotifyList.size());
            try
            {
                new IImsSyncAppInt().create_prodNotifyInfo_upload(sOper, sProdNotifyList, cErrorMsg);
            }
            catch (Exception e)
            {
                imsLogger.info("#######product notify upload error.");
            }
        }
    }

    
    /**
     * BOSS内部返回订购的时候调用的(没有入库)，上海使用，封装成JD域下的实体
     * 
     * @Description
     * @Author wukl
     * @param offerId 不可空
     * @param acctId
     * @param userId 和userId不能同时为空
     * @param validDate 可空
     * @param expireDate 可空
     * @return
     */
    public com.ailk.openbilling.persistence.jd.entity.ImsOrderProduct createJDImsOrderProduct(Integer offerId, Long acctId, Long userId, Date validDate, Date expireDate)
    {
        if (acctId == null && userId == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acctId/userId");
        }
        Long objectId = null;
        Integer objectType = null;
        if (userId != null)
        {
            objectId = userId;
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        }
        else
        {
            CaAccount dmAccount = context.get3hTree().loadAcct3hBean(acctId).getAccount();
            if (dmAccount == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_NOTEXIST, acctId);
            }
            objectId = acctId;
            if (dmAccount.getAccountClass() == EnumCodeDefine.ACCOUNT_CLASS_GCA)
            {
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_GCA;
            }
            else
            {
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
            }
        }
        return createJDImsOrderProduct(offerId, objectType, objectId, validDate, expireDate);
    }
    
    /**
     * BOSS内部返回订购的时候调用的(没有入库),上海使用，封装成JD域下的实体
     * 
     * @Description
     * @Author wukl
     * @param offerId 不可空
     * @param acctId
     * @param userId 和userId不能同时为空
     * @param validDate 可空
     * @param expireDate 可空
     * @return
     */
    public com.ailk.openbilling.persistence.jd.entity.ImsOrderProduct createJDImsOrderProduct(Integer offerId, Integer objectType, Long objectId, Date validDate,
            Date expireDate)
    {
        com.ailk.openbilling.persistence.jd.entity.ImsOrderProduct imsProd = new com.ailk.openbilling.persistence.jd.entity.ImsOrderProduct();
        imsProd.setCreateDate(context.getRequestDate());
        // @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
        imsProd.setId(DBUtil.getSequence());
        imsProd.setOfferingId(offerId);
        imsProd.setSoNbr(context.getSoNbr());
        imsProd.setSts(EnumCodeDefine.SERVICE_CYCLESTS_ACTIVE);
        imsProd.setValidDate(validDate);
        imsProd.setExpireDate(expireDate);
        imsProd.setObjectId(objectId);
        imsProd.setObjectType(objectType);
        return imsProd;
    }
    
    /**
     * @Description: 修改产品状态(1位)
     * @param productId
     * @param newStatus
     * @return
     */
    public void modifyProdStatus(List<Long> productIds, Integer newStatus)
    {
        if (CommonUtil.isEmpty(productIds) || !CommonUtil.isValid(newStatus))
            return;
        Integer lifecycleSts = newStatus;// 状态位变成3位后需要修改这里
        CoProd co = new CoProd();
        co.setLifecycleStatusId(lifecycleSts);
        super.updateByCondition(co, new DBCondition(CoProd.Field.productId, productIds, Operator.IN));
    }

    /**
     * 传入一位新状态、位数（1，10，100）、旧状态，返回新3位状态 luojb 2012-3-6
     * 
     * @param newSts
     * @param oldSts
     * @param offset
     * @return 新的3位状态
     */
    private Integer upSts(Integer newSts, Integer oldSts, Integer offset)
    {
        return oldSts % offset + newSts * offset + oldSts / (offset * 10) * (offset * 10);
    }

    /**
     * 群激活、暂停修改产品状态newSts:1位状态，oldSts：3位状态 luojb 2012-3-6
     * 
     * @param newSts
     * @param oldSts
     * @return 新的3位状态
     */
    public Integer upProdStsByGroupSts(Integer newSts, Integer oldSts)
    {
        return upSts(newSts, oldSts, 10);
    }

    /**
     * 产品状态为3位，从中获取出暂停/激活位 luojb 2012-2-17
     */
    public Integer parseProdStsFrom3Sts(Integer lifecycleStatusId)
    {
        // TODO 变更为3位后 添加转换
        return lifecycleStatusId;
    }

    private void createCoProdCellInfo(SProductParamList param_list, CoProd dmProd, long busiFlag)
    {
        if (param_list == null || CommonUtil.isEmpty(param_list.getSProductParamList_Item()))
        {
            return;
        }
        Date dmProdValidDate = dmProd.getValidDate();
        Date dmProdExpireDate = dmProd.getExpireDate();
        List<CoProdCellInfo> infoList = new ArrayList<CoProdCellInfo>();
        for (SProductParam param : param_list.getSProductParamList_Item())
        {
            if (param.getParam_id() == null || CommonUtil.isEmpty(param.getParam_value()))
            {
                continue;
            }
            // @Date 2012-05-11 lijc3 修改动态小区特征值规格值
            if (param.getParam_id() == SpecCodeDefine.HOMEGATE_LAC_ID)
            {
                Date paramValid = IMSUtil.formatDate(param.getValid_date(), null);
                Date paramExpire = IMSUtil.formatExpireDate(param.getExpire_date());
                // 如果特征值生效期为空，或早于产品生效期，取产品生效期
                CoProdCellInfo cellInfo = new CoProdCellInfo();
                if (param.getValid_date() != null && paramValid != null && paramValid.after(dmProdValidDate))
                {
                    cellInfo.setValidDate(IMSUtil.formatDate(param.getValid_date(), null));
                }
                else
                {
                    cellInfo.setValidDate(dmProdValidDate);
                }
                // 如果特征值的失效期为空，或晚于产品失效期，取产品失效期
                if (param.getExpire_date() != null && paramExpire != null && paramExpire.before(dmProdExpireDate))
                {
                    cellInfo.setExpireDate(IMSUtil.formatExpireDate(param.getExpire_date()));
                }
                else
                {
                    cellInfo.setExpireDate(dmProdExpireDate);
                }
                if (cellInfo.getExpireDate().before(cellInfo.getValidDate()))
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_CONFIG_OF_EXPIRE_AND_VALID_DATE);
                }
                //2012-07-04 yangjh coprodcellinfo增加object_id和objectType的获取
                cellInfo.setObjectId(dmProd.getObjectId());
                cellInfo.setObjectType(dmProd.getObjectType());
                cellInfo.setProductId(dmProd.getProductId());
                cellInfo.setSoDate(context.getRequestDate());
                cellInfo.setSoNbr(context.getSoNbr());
                cellInfo.setLacId(Integer.parseInt(param.getParam_value()));
                Long groupId = param.getGroup_id();
                groupId = groupId == null ? 0 : groupId;
                for (SProductParam param2 : param_list.getSProductParamList_Item())
                {
                    if (param2.getParam_id() == null || CommonUtil.isEmpty(param2.getParam_value()))
                    {
                        continue;
                    }
                    Long groupId2 = param2.getGroup_id();
                    groupId2 = groupId2 == null ? 0 : groupId2;
                    if (groupId2.equals(groupId) && param2.getParam_id() == SpecCodeDefine.HOMEGATE_SAC_ID)
                    {
                        cellInfo.setSacId(Integer.parseInt(param2.getParam_value()));
                    }
                }
                if (cellInfo.getSacId() == null)
                {
                    cellInfo.setSacId(0);
                }
                infoList.add(cellInfo);
            }
        }
        this.insertBatch(infoList);
    }

    /**
     * 校验传入的产品信息是否挂在操作的用户列表中 wukl 2012-2-15
     * 
     * @param userList
     * @param sProductOrderOperList
     */
    public void checkUserCorrect(ArrayList<Long> userList, SProductOrderOperList sProductOrderOperList)
    {
        SProductOrderOper[] operList = sProductOrderOperList.getSProductOrderOperList_Item();
        for (SProductOrderOper oper : operList)
        {
            if (oper.getProd_list() != null && CommonUtil.isNotEmpty(oper.getProd_list().getItem()))
            {
                SProductOrder[] prodList = oper.getProd_list().getItem();
                for (SProductOrder order : prodList)
                {
                    Long userId = order.getUser_id();
                    if (userId != null && !userList.contains(userId))
                    {
                        IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_NOT_CHANGE_PRODUCT, userId);
                    }

                    String phoneId = order.getPhone_id();
                    if (phoneId != null && userId == null)
                    {
                        User3hBean user3hBean = context.get3hTree().loadUser3hBean(null, phoneId);
                        if (user3hBean.getUserId() != null && !userList.contains(user3hBean.getUserId()))
                        {
                            IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_NOT_CHANGE_PRODUCT,
                                    user3hBean.getUserId());
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * yangjh 2012-7-27 计算夏令时时区 bug：53000 
     * @param date
     * @param timeZoneId
     * @return
     */
    public Integer dateIsInZoneDetail(Date date, Integer timeZoneId)
    {
        String validString = DateUtil.formatDate(date, DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
        SysTimeZoneDetail zoneDetail =  null;
        Integer valueShit = null;
        //@Date 2012-08-03 yangjh : ConstantDefine.IR_TIME_ZONE_DETAIL
        if (context.getExtendParam(ConstantDefine.IR_TIME_ZONE_DETAIL) != null)
        {
        	zoneDetail = (SysTimeZoneDetail) context.getExtendParam(ConstantDefine.IR_TIME_ZONE_DETAIL);
        }
        else
        {
            //@Date 2012-07-27 yangjh :  bug：53000 SysTimeZoneDetail表有变更 SysTimeSegDtl不用
            //@Date 2012-07-30 yangjh : SysTimeZoneDetail去掉 排序查询 已经没有这个字段
        	List<SysTimeZoneDetail> timeZoneDetails = this.query(SysTimeZoneDetail.class, 
        			new DBCondition(
                            SysTimeZoneDetail.Field.timeZoneId, timeZoneId));
        		
           /* DBJoinCondition join = new DBJoinCondition(SysTimeSegDef.class);
            join.innerJoin(SysTimeZoneDetail.class, new DBRelation(SysTimeSegDef.Field.segId, SysTimeZoneDetail.Field.segId));
            join.innerJoin(SysTimeSegDtl.class, new DBRelation(SysTimeSegDef.Field.segId, SysTimeSegDtl.Field.segId));
            resultMap = this.queryJoin(join,
                    new OrderCondition[] { new OrderCondition(false, SysTimeZoneDetail.Field.priority) }, null, new DBCondition(
                            SysTimeZoneDetail.Field.timeZoneId, timeZoneId));  */
            if (CommonUtil.isEmpty(timeZoneDetails))
            {
                return null;
            }
            else
            {
                zoneDetail = timeZoneDetails.get(0);
                context.addExtendParam(ConstantDefine.IR_TIME_ZONE_DETAIL, zoneDetail);
            }
        }
        String timeZoneOffset = zoneDetail.getTimeZoneOffset();
        if(CommonUtil.isValid(timeZoneOffset)){
            valueShit = this.getTransfTimeZone(timeZoneOffset);
        }else{
            return null;
        }
        Long start = zoneDetail.getStartVal();
        Long end = zoneDetail.getEndVal();
        
        Long valid =  Long.parseLong(validString);
        if(isInTimeIn(valid, start, end)){
            return valueShit;
        }else{
            return null;
        }
    }

    /**
     * 
     * yangjh 2012-7-27 判断时间是否在start 跟 end之内
     * @param time
     * @param start 默认为0 不可能为空
     * @param end 可能为空 空表示无限大  经确认产品管理配置的end肯定大于start
     * @return
     */
    private boolean isInTimeIn(Long time, Long start, Long end)
    {
        boolean startFlag = time - start >= 0;
        if(end == null){
            if(startFlag){
                return true;
            }
             return false;   
        }else{
            boolean endFlag = end - time >= 0;
            if(startFlag && endFlag){
                return true;
            }else{
                return false;
            }
        }
       /* boolean startFlag = time - start >= 0;
        boolean endFlag = end - time >= 0;
        if (start > end)
        {
            if (!startFlag && !endFlag)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (startFlag && endFlag)
            {
                return true;
            }
            else
            {
                return false;
            }
        } */
    }

    /**
     * @Description 修改付费模式,延长有效期,修改特征规格和二次议价参数
     * @Author lijingcheng
     * @param orderList
     */
    public SProductOrderResultList changePayModeExtendModifyProdList(SProductOrderList prodList, short operType)
    {
        SProductOrderResultList prodResutList = new SProductOrderResultList();
        if (prodList == null || CommonUtil.isEmpty(prodList.getItem()))
        {
            return null;
        }
        SProductOrderResult[] prodResults = new SProductOrderResult[prodList.getItem().length];
        SProductOrderList packOrderList = new SProductOrderList();
        for (int i = 0; i < prodList.getItem().length; i++)
        {
            SProductOrder prod = (SProductOrder) prodList.getItem()[i];
            CoProd dmProd = changePayModeExtendModify(prod,operType);
            // 处理打包关系
            handleOfferRel(dmProd, prod, EnumCodeDefine.CHANGE_PROD_CPME, null, null, packOrderList);

            SProductOrderResult prodResult = new SProductOrderResult();
            prodResult.setOffer_id(dmProd.getProductOfferingId().longValue());
            prodResult.setProduct_id(prod.getProduct_id());
            String soId = prod.getSo_id();
            soId = soId == null ? String.valueOf(context.getSoNbr()) : soId;
            prodResult.setSo_id(soId);
            if (dmProd != null)
            {
                prodResult.setExpire_date(DateUtil.formatDate(dmProd.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                prodResult.setValid_date(DateUtil.formatDate(dmProd.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            prodResult.setProduct_param_list(prod.getParam_list());
            prodResults[i] = prodResult;

        }
        // 合并返回结果
        mergtSProductOrderList(prodList, packOrderList);
        prodResutList.setSProductOrderResultList_Item(prodResults);
        // 上发通知ts 上海不需要
        if (!ProjectUtil.is_CN_SH())
            this.prodNotifyUpload(this.parseProdNotifyList(prodResutList, EnumCodeDefine.CHANGE_PROD_CPME));

        return prodResutList;
    }

    private CoProd changePayModeExtendModify(SProductOrder order,short operType)
    {
        if (order.getProduct_id() == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_ID_IS_NULL);
        }
        CoProd dmProd = this.queryProd(order.getProduct_id());
        if (dmProd == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, new Object[] { order.getProduct_id() });
        }
        if (dmProd.getLifecycleStatusId().intValue() == EnumCodeDefine.PROD_LIFECYCLE_SUSPEND)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PROD_IS_SUSPEND, dmProd.getProductId());
        }
        // 要修改的三项全部为null 直接返回
        if (order.getBilling_type() == null && CommonUtil.isEmpty(order.getExpire_date())
                && (order.getParam_list() == null || CommonUtil.isEmpty(order.getParam_list().getSProductParamList_Item())))
        {
            return dmProd;
        }
        PmProductOffering offer = super.queryPmProductOfferingByOfferId(dmProd.getProductOfferingId());
        if (offer == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_OFFER_NOTEXIST, dmProd.getProductOfferingId());
        }
        Date expireDate = null;
        if (CommonUtil.isNotEmpty(order.getExpire_date()))
        {
            expireDate = IMSUtil.formatDate(order.getExpire_date());
            if (!expireDate.after(dmProd.getExpireDate()))
            {
                expireDate = null;
            }
        }
        //2012-07-10 yangjh story：50778 增加operType=6的处理
        if(operType != EnumCodeDefine.CHNAGE_PROD_EXTEND_CMP){
        	// 修改特征值
            if (order.getParam_list() != null && order.getParam_list().getSProductParamList_Item() != null)
            {
                modifyParamList(dmProd, order.getParam_list(), expireDate);
            }
            // 修改二次议价参数
            modifExtendParamList(order, dmProd, expireDate);
        }
        // 查询定价是否有变化 pricePlanId为null 则表示定价计划没有修改
        Integer pricePlanId = queryPricePlanIdByModify(dmProd);
        // if(dmProd.getPricingPlanId().intValue()==pricePlanId){
        // pricePlanId=null;
        // }
        Short billingType = null;
        if (order.getBilling_type() != null)
        {
            billingType = order.getBilling_type();
        }
        if (billingType != null)
        {
            PayModeComponent pmCmp = context.getComponent(PayModeComponent.class);
            // 验证销售品是否支持该付费模式
            pmCmp.checkMainProdByChangePayMode(billingType, dmProd.getProductOfferingId());
            // 修改产品相关表
        }
        this.extendProdExpireDate(dmProd,expireDate, pricePlanId, billingType);
        if (expireDate != null)
        {
            dmProd.setExpireDate(expireDate);
        }
        return dmProd;
    }

    // oper_type=5的时候更新二次议价参数
    private void modifExtendParamList(SProductOrder prod, CoProd dmProd, Date expireDate)
    {
        ExtendParamList extendParamList = prod.getReguid_price_param();
        if (extendParamList != null && CommonUtil.isNotEmpty(extendParamList.getExtendParamList_Item()))
        {
            for (ExtendParam ep : extendParamList.getExtendParamList_Item())
            {
                // CoProdPriceParam paramWhere = new CoProdPriceParam();
                CoProdPriceParam paramCon = null;
                // paramWhere.setParamId(Integer.parseInt(ep.getParam_name()));
                // paramWhere.setProductId(dmProd.getProductId());
                // paramWhere.setObjectId(dmProd.getObjectId());
                // paramWhere.setObjectType(dmProd.getObjectType());
                CoProdPriceParam prodPrice = querySingle(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.paramId,
                        ep.getParam_name()), new DBCondition(CoProdPriceParam.Field.productId, dmProd.getProductId()),
                        new DBCondition(CoProdPriceParam.Field.objectId, dmProd.getObjectId()), new DBCondition(
                                CoProdPriceParam.Field.objectType, dmProd.getObjectType()));
                if (prodPrice != null)
                {
//                    paramCon.setParamValue(ep.getParam_value());
//                    paramCon.setParamId(Integer.parseInt(ep.getParam_name()));
                    // @Date 2012-07-18 tangjl5 :Story # 49447 split需要转换为账务侧的measure_id和amount进行存储
                    String newExpireDate = DateUtil.formatDate(prodPrice.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
                    if (expireDate != null)
                    {
                        newExpireDate =  DateUtil.formatDate(expireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
                    }
                    
                    paramCon = getNewProdPriceParam(dmProd, CommonUtil.string2Integer(ep.getParam_name()), ep.getParam_value(), DateUtil.formatDate(prodPrice.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS), 
                            newExpireDate, extendParamList.getExtendParamList_Item());
                    
                    updateByCondition(paramCon,
                            new DBCondition(CoProdPriceParam.Field.paramId, Integer.parseInt(ep.getParam_name())),
                            new DBCondition(CoProdPriceParam.Field.productId, dmProd.getProductId()), new DBCondition(
                                    CoProdPriceParam.Field.objectId, dmProd.getObjectId()), new DBCondition(
                                    CoProdPriceParam.Field.objectType, dmProd.getObjectType()));
                }
                else
                {
                    expireDate = expireDate == null ? dmProd.getExpireDate() : expireDate;
                    if (CommonUtil.isNotEmpty(ep.getExpire_date()))
                    {
                        Date temp = IMSUtil.formatDate(ep.getExpire_date());
                        // 传入的失效时间比产品的失效时间晚 取产品的失效时间
                        if (temp.after(expireDate))
                        {
                            expireDate = temp;
                        }
                    }
                    
                    prodPrice = getNewProdPriceParam(dmProd, CommonUtil.string2Integer(ep.getParam_name()), ep.getParam_value(), DateUtil.formatDate(context.getRequestDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS), 
                            DateUtil.formatDate(expireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS), extendParamList.getExtendParamList_Item());
                    this.insert(prodPrice);
                }
            }
        }
    }

    /**
     * @Description 修改特征值，同时修改它的失效时间（失效时间可以为空）
     * @Author lijingcheng
     * @param params
     * @param expireDate
     */
    private void modifyParamList(CoProd dmProd, SProductParamList paramList, Date expireDate)
    {

        // 亲情号码操作co_fn_char_value
        if (dmProd.getBusiFlag().intValue() == SpecCodeDefine.FRIENDNUMBER)
        {
            modifyParamList_fn(dmProd, paramList, expireDate);
            return;
        }

        UserComponent userCmp = context.getComponent(UserComponent.class);
        // 该销售品可以使用的特征值
        List<Integer> specIds = context.getComponent(ConfigQuery.class).querySpecCharIdsByOfferId(dmProd.getProductOfferingId());
        List<CoProdCharValue> existList = this.queryProdCharValue(dmProd.getProductId());
        List<CoProdCharValue> insertList = new ArrayList<CoProdCharValue>();
        if (CommonUtil.isEmpty(existList))
        {
            // 如果原来的为空,则需要全部插入
            expireDate = expireDate == null ? dmProd.getExpireDate() : expireDate;
            insertList = this.createProdCharValueNoSave(paramList, dmProd, dmProd.getBusiFlag(), dmProd.getValidDate(),
                    expireDate);
        }
        else
        {
            List<Integer> existSpecIds = new ArrayList<Integer>();
            for (CoProdCharValue value : existList)
            {
                existSpecIds.add(value.getSpecCharId());
            }
            // 原来的不为空，有存在的修改，不存在的插入
            // 如果传入的不是销售品可以添加的 去掉
            List<SProductParam> inParamList = new ArrayList<SProductParam>();
            for (SProductParam param : paramList.getSProductParamList_Item())
            {
                if (param.getParam_id() == null || CommonUtil.isEmpty(param.getParam_value()))
                {
                    continue;
                }
                if (specIds.contains(param.getParam_id()))
                {
                    inParamList.add(param);
                }
            }
            // 没有可以插入或者修改的,不做操作
            if (inParamList.size() != 0)
            {
                for (SProductParam param : inParamList)
                {
                    // 传入的存在于该产品下的特征值里面 则进行修改
                    if (existSpecIds.contains(param.getParam_id()))
                    {
                        CoProdCharValue value = new CoProdCharValue();
                        value.setValue(param.getParam_value());
                        if (expireDate != null)
                        {
                            value.setExpireDate(expireDate);
                        }
                        // CoProdCharValue where = new CoProdCharValue();
                        // where.setProductId(dmProd.getProductId());
                        // where.setSpecCharId(param.getParam_id());
                        // where.setObjectId(dmProd.getObjectId());
                        this.updateByCondition(value, new DBCondition(CoProdCharValue.Field.productId, dmProd.getProductId()),
                                new DBCondition(CoProdCharValue.Field.specCharId, param.getParam_id()), new DBCondition(
                                        CoProdCharValue.Field.objectId, dmProd.getObjectId()));
                    }
                    else
                    {// 不存在 则新建，进行插入
                        expireDate = expireDate == null ? dmProd.getExpireDate() : expireDate;
                        if (CommonUtil.isNotEmpty(param.getExpire_date()))
                        {
                            Date temp = IMSUtil.formatDate(param.getExpire_date());
                            // 传入的失效时间比产品的失效时间晚 取产品的失效时间
                            if (temp.after(expireDate))
                            {
                                expireDate = temp;
                            }
                        }
                        insertList.add(userCmp.createCoProdCharValue(dmProd.getProductId(), 0, param.getParam_id(),
                                param.getParam_value(), expireDate, context.getRequestDate(), dmProd.getObjectId(),
                                dmProd.getObjectType()));
                    }
                }
            }
        }
        if (insertList.size() > 0)
        {
            this.insertBatch(insertList);
        }
    }

    /**
     * @Description 修改亲情号码特征值，同时修改它的失效时间（失效时间可以为空）
     * @Author wangyh3
     * @Date 2012-04-28
     */
    private void modifyParamList_fn(CoProd dmProd, SProductParamList paramList, Date expireDate)
    {
        UserComponent userCmp = context.getComponent(UserComponent.class);
        // 该销售品可以使用的特征值
        List<Integer> specIds = context.getComponent(ConfigQuery.class).querySpecCharIdsByOfferId(dmProd.getProductOfferingId());
        List<CoFnCharValue> existList = this.queryFnCharValue(dmProd.getProductId());
        List<CoFnCharValue> insertList = new ArrayList<CoFnCharValue>();
        if (CommonUtil.isEmpty(existList))
        {
            // 如果原来的为空,则需要全部插入
            expireDate = expireDate == null ? dmProd.getExpireDate() : expireDate;
            List<CoProdCharValue> tmpList = this.createProdCharValueNoSave(paramList, dmProd, dmProd.getBusiFlag(),
                    dmProd.getValidDate(), expireDate);
            insertList = ConvertUtil.coProdCharValues2CoFnCharValues(tmpList);
        }
        else
        {
            List<Integer> existSpecIds = new ArrayList<Integer>();
            for (CoFnCharValue value : existList)
            {
                existSpecIds.add(value.getSpecCharId());
            }
            // 原来的不为空，有存在的修改，不存在的插入
            // 如果传入的不是销售品可以添加的 去掉
            List<SProductParam> inParamList = new ArrayList<SProductParam>();
            for (SProductParam param : paramList.getSProductParamList_Item())
            {
                if (param.getParam_id() == null || CommonUtil.isEmpty(param.getParam_value()))
                {
                    continue;
                }
                if (specIds.contains(param.getParam_id()))
                {
                    inParamList.add(param);
                }
            }
            // 没有可以插入或者修改的,不做操作
            if (inParamList.size() != 0)
            {
                for (SProductParam param : inParamList)
                {
                    // 传入的存在于该产品下的特征值里面 则进行修改
                    if (existSpecIds.contains(param.getParam_id()))
                    {
                        CoFnCharValue value = new CoFnCharValue();
                        value.setValue(param.getParam_value());
                        if (expireDate != null)
                        {
                            value.setExpireDate(expireDate);
                        }
                        // CoFnCharValue where = new CoFnCharValue();
                        // where.setProductId(dmProd.getProductId());
                        // where.setSpecCharId(param.getParam_id());
                        // where.setObjectId(dmProd.getObjectId());
                        this.updateByCondition(value, new DBCondition(CoFnCharValue.Field.productId, dmProd.getProductId()),
                                new DBCondition(CoFnCharValue.Field.specCharId, param.getParam_id()), new DBCondition(
                                        CoFnCharValue.Field.objectId, dmProd.getObjectId()));
                    }
                    else
                    {// 不存在 则新建，进行插入
                        expireDate = expireDate == null ? dmProd.getExpireDate() : expireDate;
                        if (CommonUtil.isNotEmpty(param.getExpire_date()))
                        {
                            Date temp = IMSUtil.formatDate(param.getExpire_date());
                            // 传入的失效时间比产品的失效时间晚 取产品的失效时间
                            if (temp.after(expireDate))
                            {
                                expireDate = temp;
                            }
                        }
                        insertList.add(userCmp.createCoFnCharValue(dmProd.getProductId(), 0, param.getParam_id(),
                                param.getParam_value(), expireDate, context.getRequestDate(), dmProd.getObjectId(),
                                dmProd.getObjectType()));
                    }
                }
            }
        }
        if (insertList.size() > 0)
        {
            this.insertBatch(insertList);
        }
    }

    // 查询产品定价计划
    private Integer queryPricePlanIdByModify(CoProd dmProd)
    {
        List<CoProdCharValue> charValues = this.queryProdCharValue(dmProd.getProductId(), dmProd.getObjectId());
        List<CoProdPriceParam> priceParms = this.queryProdPriceParam(dmProd.getProductId(), dmProd.getObjectId());
        CmCustomer cust = null;
        CaAccount account = null;
        CmResource user = null;
        Integer maniOfferId = null;
        Integer pricePlanId = null;
        if (dmProd.getObjectType().equals(EnumCodeDefine.PROD_OBJECTTYPE_DEV))
        {// 用户级产品
            User3hBean bean = context.get3hTree().loadUser3hBean(dmProd.getObjectId());
            user = bean.getUser();
            account = bean.getAccount();
            cust = bean.getCustomer();
            if (ProjectUtil.is_CN_SH())
            {
                CoProd mainProd = this.queryPrimaryProductByUserId(dmProd.getObjectId());
                if (mainProd != null)
                {
                    maniOfferId = mainProd.getProductOfferingId();
                }
            }
        }
        else if (dmProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
        {// 账户级产品
            IMS3hBean bean = context.get3hTree().loadAcct3hBean(dmProd.getObjectId());
            cust = bean.getCustomer();
            account = bean.getAccount();
        }
        else
        {
            account =context.get3hTree().loadAcct3hBean(dmProd.getObjectId()).getAccount(); 
        }
        pricePlanId = queryPricePlanId(dmProd.getProductOfferingId(), cust, account, user, priceParms, null, charValues,
                maniOfferId, null, null);// user
        if (pricePlanId == null)
        {
            pricePlanId = 0;
        }
        return pricePlanId;
    }

    /**
     * @Description 修改产品的相关信息,该方法没有操作特征值的表
     * @Author lijingcheng
     * @param dmProd
     * @param dmProdInvObj
     * @param expire
     * @param pricePlanId
     * @param billingType
     */
    private void extendProdExpireDate(CoProd dmProd,Date expire, Integer pricePlanId,
            Short billingType)
    {
        if (pricePlanId == null && expire == null && billingType == null)
        {
            return;
        }
        boolean nextValid = billingType == null ? false : (billingType.intValue() == EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
        List<CoProdBillingCycle> cycles = context.getComponent(ProductCycleComponent.class).queryProdBillingCycles(
                dmProd.getProductId(), dmProd.getObjectId());
        CoProdBillingCycle curBillCycle = null;
        List<CoProdBillingCycle> futureBillCycleList = new ArrayList<CoProdBillingCycle>();
        if (CommonUtil.isNotEmpty(cycles))
        {
            for (CoProdBillingCycle billCycle : cycles)
            {
                Date validDate = billCycle.getValidDate();
                Date expireDate = billCycle.getExpireDate();
                if (validDate.before(context.getRequestDate()) && expireDate.after(context.getRequestDate()))
                {
                    curBillCycle = billCycle;
                }
                else if (validDate.after(context.getRequestDate()))
                {
                    futureBillCycleList.add(billCycle);
                }
            }
        }
        else
        {
            nextValid = false;
        }
        
        // @Date 2012-07-24 tangjl5 :Story # 51379 先记录延长账期前的数据，再延长账期后使用
        Date curCycleEndDate = context.getComponent(ProductCycleComponent.class).getCurrentBillCycleEndDateNormal(curBillCycle);
        imsLogger.info("*****the product = "+ curBillCycle.getProductId() + " cur cycle end date is " + curCycleEndDate, context);
        imsLogger.info("*****the product = "+ curBillCycle.getProductId() + " expire date is " + dmProd.getProdExpireDate(), context);

        // 如果当前账期结束日大于当前账期失效期，取失效期
        if (curCycleEndDate.after(curBillCycle.getExpireDate()))
        {
            curCycleEndDate = curBillCycle.getExpireDate();
        }
        
        List<CoProd> prodList = this.queryProdList(dmProd.getProductId());
        if (CommonUtil.isNotEmpty(prodList) && prodList.size() > 1)
        {
            CoProd lastProd = ProdHelper.getLastDataObjectByExpireDate(prodList);
            CoProd value = new CoProd();
            if (billingType != null)
            {
                value.setBillingType(billingType.intValue());
            }
            if (pricePlanId != null)
            {
                value.setPricingPlanId(pricePlanId);
            }
            if (expire != null)
            {
                value.setExpireDate(expire);
            }
            // 更新最后一条的有效期长度
            this.updateByCondition(value, new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
                    CoProd.Field.objectId, dmProd.getObjectId()),
                    new DBCondition(CoProd.Field.validDate, lastProd.getValidDate()));
            CoProd value2 = new CoProd();
            if (billingType != null || pricePlanId != null)
            {
                if (billingType != null && !nextValid)
                {
                    value2.setBillingType(billingType.intValue());
                }
                if (pricePlanId != null)
                {
                    value2.setPricingPlanId(pricePlanId);
                }
                // 更新第一条
                this.updateByCondition(value2, new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
                        CoProd.Field.objectId, dmProd.getObjectId()),
                        new DBCondition(CoProd.Field.validDate, lastProd.getValidDate(), Operator.LESS));
            }
        }
        else
        {
            CoProd value = new CoProd();
            if (billingType != null)
            {
                value.setBillingType(billingType.intValue());
            }
            if (pricePlanId != null)
            {
                value.setPricingPlanId(pricePlanId);
            }
            if (expire != null)
            {
                value.setExpireDate(expire);
            }

            // CoProd where = new CoProd();
            // where.setProductId(dmProd.getProductId());
            // where.setObjectId(dmProd.getObjectId());
            // where.setValidDate(dmProd.getValidDate());
            if (nextValid)
                this.updateByCondition(value, curCycleEndDate, new DBCondition(CoProd.Field.productId, dmProd.getProductId()),
                        new DBCondition(CoProd.Field.objectId, dmProd.getObjectId()), new DBCondition(CoProd.Field.validDate,
                                dmProd.getValidDate()));
            else
                this.updateByCondition(value, new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
                        CoProd.Field.objectId, dmProd.getObjectId()),
                        new DBCondition(CoProd.Field.validDate, dmProd.getValidDate()));
        }


        Integer coProdBillingType = null;
        if (billingType != null)
        {
            coProdBillingType = billingType.intValue();
            PmCompositeDeductRule deductRule = context.getComponent(ProdStsComponent.class).queryPmCompsiteDeductRuleByOfferId(
                    dmProd.getProductOfferingId(), coProdBillingType.intValue());
            if (deductRule != null)
            {
                coProdBillingType = deductRule.getDeductFlag();
            }
            else
            {
                if (coProdBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID)
                {// budget中存在2的付费模式，当做后付费处理
                    coProdBillingType = EnumCodeDefine.USER_PAYMODE_POSTPAID;
                }
            }
        }
        if (expire == null && coProdBillingType == null)
        {
            return;
        }
        // 延长帐期

        if (CommonUtil.isNotEmpty(cycles))
        {
            if (cycles.size() > 1)
            {
                CoProdBillingCycle lastCycle = ProdHelper.getLastDataObjectByExpireDate(cycles);
                CoProdBillingCycle billCycle = new CoProdBillingCycle();
                if (expire != null)
                {
                    billCycle.setExpireDate(expire);
                }
                // 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
                /*
                 * if (coProdBillingType != null) { billCycle.setDeductFlag(coProdBillingType); }
                 */
                // 更新最后一条
                this.updateByCondition(billCycle, new DBCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId()),
                        new DBCondition(CoProdBillingCycle.Field.objectId, dmProd.getObjectId()), new DBCondition(
                                CoProdBillingCycle.Field.validDate, lastCycle.getValidDate()));
                // 更新deductFlag
                futureBillCycleList.remove(lastCycle);
                if (coProdBillingType != null)
                {
                    CoProdBillingCycle value = new CoProdBillingCycle();
                    // 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
                    // value.setDeductFlag(coProdBillingType);
                    // 下周期生效，不更新第一条
                    if (nextValid)
                    {
                        futureBillCycleList.remove(curBillCycle);
                        if (CommonUtil.isNotEmpty(futureBillCycleList))
                        {

                            for (CoProdBillingCycle bc : futureBillCycleList)
                            {
                                // CoProdBillingCycle where = new CoProdBillingCycle();
                                // where.setProductId(bc.getProductId());
                                // where.setObjectId(bc.getObjectId());
                                // where.setValidDate(bc.getValidDate());
                                this.updateByCondition(value,
                                        new DBCondition(CoProdBillingCycle.Field.productId, bc.getProductId()), new DBCondition(
                                                CoProdBillingCycle.Field.objectId, bc.getObjectId()), new DBCondition(
                                                CoProdBillingCycle.Field.validDate, bc.getValidDate()));
                            }
                        }
                    }
                    else
                    {
                        this.updateByCondition(value, new DBCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId()),
                                new DBCondition(CoProdBillingCycle.Field.objectId, dmProd.getObjectId()), new DBCondition(
                                        CoProdBillingCycle.Field.validDate, lastCycle.getValidDate(), Operator.LESS));
                    }
                }
            }
            else
            {
                CoProdBillingCycle billCycle = new CoProdBillingCycle();
                // CoProdBillingCycle billCycleWhere = new CoProdBillingCycle();
                if (expire != null)
                {
                    billCycle.setExpireDate(expire);
                }
                // 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
                /*
                 * if (coProdBillingType != null) { billCycle.setDeductFlag(coProdBillingType); }
                 */
                // billCycleWhere.setProductId(dmProd.getProductId());
                // billCycleWhere.setObjectId(dmProd.getObjectId());
                if (nextValid)
                    updateByCondition(billCycle, curCycleEndDate,
                            new DBCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId()), new DBCondition(
                                    CoProdBillingCycle.Field.objectId, dmProd.getObjectId()));
                else
                    updateByCondition(billCycle, new DBCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId()),
                            new DBCondition(CoProdBillingCycle.Field.objectId, dmProd.getObjectId()));
            }
        }
        else
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_BILL_CYCLE_NOT_EXIST, dmProd.getProductId());
        }

        if (expire == null)
        {
            return;
        }
        // 延长产品代付表 expire date
        // CoProdInvCa prodInvCaVal = new CoProdInvCa();
        // CoProdInvCa prodInvCaWhere = new CoProdInvCa();
        // prodInvCaVal.setExpireDate(expire);
        // prodInvCaWhere.setProductId(dmProd.getProductId());
        // updateByCondition(prodInvCaVal, prodInvCaWhere);
        // 延长产品二次议价价格参数 expire date
        CoProdPriceParam ProdPriceParamVal = new CoProdPriceParam();
        // CoProdPriceParam ProdPriceParamWhere = new CoProdPriceParam();
        ProdPriceParamVal.setExpireDate(expire);
        // ProdPriceParamWhere.setProductId(dmProd.getProductId());
        // ProdPriceParamWhere.setObjectId(dmProd.getObjectId());
        updateByCondition(ProdPriceParamVal, new DBCondition(CoProdPriceParam.Field.productId, dmProd.getProductId()),
                new DBCondition(CoProdPriceParam.Field.objectId, dmProd.getObjectId()));

        // 如果此服务的有效期大于crm传入的有效期，则不进行修改
        Integer offeringId = dmProd.getProductOfferingId();

        // 特殊操作,如果是fax产品延长，需要延长cm_res_identity中fax的有效期 ljc
        if (dmProd.getBusiFlag().intValue() == SpecCodeDefine.FAX || dmProd.getBusiFlag().intValue() == SpecCodeDefine.MULTI_SIM)
        {
                CmResIdentity idenVal = new CmResIdentity();
                idenVal.setExpireDate(expire);
                // CmResIdentity idenWhere = new CmResIdentity();
                List<DBCondition> cond = new ArrayList<DBCondition>();
                if (dmProd.getBusiFlag().intValue() == SpecCodeDefine.FAX)
                {
                    // idenWhere.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER);
                    cond.add(new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER));
                }
                else
                {
                    // idenWhere.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_IMSI);
                    cond.add(new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM));
                }

                // idenWhere.setResourceId(dmProdInvObj.getObjectId());
                cond.add(new DBCondition(CmResIdentity.Field.resourceId, dmProd.getObjectId()));
                updateByCondition(idenVal, cond.toArray(new DBCondition[cond.size()]));
        }
        // 如果是用户上的服务

        List<Integer> ids = queryServiceSpecIdListByOfferId(Long.valueOf(offeringId));
        if (!CommonUtil.isEmpty(ids))
        {
            for (Integer servSpecId : ids)
            {
                // Integer servSpecId =
                // queryServiceSpecIdByOfferId(Long.valueOf(offeringId));
                Long resourceId = dmProd.getObjectId();
                CmResServCycle resServCycle = super.querySingle(CmResServCycle.class, new DBCondition(
                        CmResServCycle.Field.resourceId, resourceId), new DBCondition(CmResServCycle.Field.serviceSpecId,
                        servSpecId));
                if (resServCycle != null)
                {
                    if (expire.after(resServCycle.getExpireDate()))
                    {
                        CmResServCycle servCycleVal = new CmResServCycle();
                        servCycleVal.setExpireDate(expire);
                        // CmResServCycle servCycleWhere = new CmResServCycle();
                        // servCycleWhere.setResourceId(resourceId);
                        // servCycleWhere.setServiceSpecId(servSpecId);
                        updateByCondition(servCycleVal, new DBCondition(CmResServCycle.Field.resourceId, resourceId),
                                new DBCondition(CmResServCycle.Field.serviceSpecId, servSpecId));
                    }
                }
            }
        }
        // 延长CO_PROD_VALID
        this.modifyProdValid(dmProd.getProductId(), expire);
        
    }

    /**
     * 校验是否能amend产品时间 luojb 2012-4-19
     * 
     * @param prod
     * @param validDate
     * @param expireDate
     * @Date 2012-06-28 yangjh 修改为公用方法 并增加validDate的判断
     */
    public void checkAmend(CoProd prod, Date validDate, Date expireDate, Long acctId)
    {
        if (prod.getBillingType().intValue() == EnumCodeDefine.PROD_BILLTYPE_PREPAID)
            IMSUtil.throwBusiException(ErrorCodeDefine.CANNOT_AMEND_PRIPAID_PROD, prod.getProductId());
        if (validDate != null && !validDate.before(prod.getProdValidDate()))
            IMSUtil.throwBusiException(ErrorCodeDefine.AMEND_VALID_DATE_MUST_BEFORE_OLD, prod.getProductId());
        if (expireDate != null && !expireDate.after(prod.getProdExpireDate()))
            IMSUtil.throwBusiException(ErrorCodeDefine.AMEND_EXPIRE_DATE_MUST_AFTER_OLD, prod.getProductId());
        PmProductOfferLifecycle lifeCycle = context.getComponent(ProductCycleComponent.class).queryProdOfferLifeCycle(
                prod.getProductOfferingId());
        if (lifeCycle.getCycleSyncFlag().shortValue() != EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_BY_ACCOUNT)
            IMSUtil.throwBusiException(ErrorCodeDefine.AMEND_DATE_PROD_CYCLE_MUST_SAME_AS_ACCT, prod.getProductId());
        // 2012-08-18 luojb #56412 有传入valid_date才验证
        if(validDate != null){
            Date currentCycleStart = context.getComponent(AccountQuery.class).queryAcctCurrentCycleStart(acctId);
            if(currentCycleStart != null && validDate.before(currentCycleStart)){
                IMSUtil.throwBusiException(ErrorCodeDefine.AMEND_VALID_DATE_CAN_NOT_BEFORE_CYCLE_START, currentCycleStart);
            }
        }
    }

    /**
     * 调整产品的生效、失效时间 luojb 2012-4-11
     * 
     * @param productId
     * @param objectId
     * @param validDate
     * @param expireDate
     * @Date yangjh 2012-06-28 bug:49083 生效时间修改增加限制 将原先的一部分代码移到amendProdDatebusiBean里面
     * @date luojb 2012-07-10 增加prod_valid_date,prod_expire_date,重写了逻辑，生效时间只能往前改，失效时间只能往后改，修改方式为插入记录
     * @return
     */
    public SProductOrderResult amendProdDate(Long productId,List<CoProd> prodList, Long objectId, Date validDate, Date expireDate)
    {
        if (validDate == null && expireDate == null)
            return null;
        if (validDate != null && expireDate != null && expireDate.before(validDate))
            return null;

        //取出产品旧的prod_valid_date和prod_expire_date
        Date prodValid = prodList.get(0).getProdValidDate();
        Date prodExpire = prodList.get(0).getProdExpireDate();
        
        //先把现有记录的产品生效时间失效时间更新
        CoProd upProd = new CoProd();
        if(validDate != null)
            upProd.setProdValidDate(validDate);
        if(expireDate != null)
            upProd.setProdExpireDate(expireDate);
        
        List<CoProd> upProdResultList = updateDirectByCondition(upProd, new DBCondition(CoProd.Field.productId,productId));
        
        
        //根据prod_valid_date 取产品的第一条记录
        try{
            if(validDate != null){
                List<CoProd> firstList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.validDate, prodValid));
                if(!CommonUtil.isEmpty(firstList)){//理论上是不会为空 而且只有一条的
                    
                    CoProd firstProd = firstList.get(0);
                    CoProd newCoProdValid = (CoProd)IMSUtil.copyDataObject(firstProd);
                    newCoProdValid.setValidDate(validDate);
                    newCoProdValid.setExpireDate(firstProd.getValidDate());
                    newCoProdValid.setProdValidDate(validDate);
                    newCoProdValid.setProdExpireDate(upProdResultList.get(0).getProdExpireDate());
                    insert(newCoProdValid);
                }
            }
            
            //根据prod_expire_date 取产品最后一条记录
            if(expireDate != null){
                List<CoProd> lastList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.expireDate, prodExpire));
                if(!CommonUtil.isEmpty(lastList)){//理论上是不会为空 而且只有一条的
                    CoProd lastProd = lastList.get(0);
                    CoProd newCoProdExpire = (CoProd)IMSUtil.copyDataObject(lastProd);
                    newCoProdExpire.setValidDate(lastProd.getExpireDate());
                    newCoProdExpire.setExpireDate(expireDate);
                    newCoProdExpire.setProdValidDate(upProdResultList.get(0).getProdValidDate());
                    newCoProdExpire.setProdExpireDate(expireDate);
                    insert(newCoProdExpire);
                }
            }
            
            // 改账期
            List<CoProdBillingCycle> cycleList = DBUtil.query(CoProdBillingCycle.class,new DBCondition(CoProdBillingCycle.Field.productId,productId));
            if (CommonUtil.isNotEmpty(cycleList))
            {
                if(validDate != null){
                    List<CoProdBillingCycle> firstList = IMSUtil.matchDataObject(cycleList, new CacheCondition(CoProdBillingCycle.Field.validDate,prodValid));
                    if(!CommonUtil.isEmpty(firstList)){//理论上是不会为空 而且只有一条的
                        CoProdBillingCycle firstCycle = firstList.get(0);
                        CoProdBillingCycle newCycleValid = (CoProdBillingCycle)IMSUtil.copyDataObject(firstCycle);
                        newCycleValid.setValidDate(validDate);
                        newCycleValid.setExpireDate(firstCycle.getValidDate());
                        insert(newCycleValid);
                    }
                }
                if(expireDate != null){
                    List<CoProdBillingCycle> lastList = IMSUtil.matchDataObject(cycleList, new CacheCondition(CoProdBillingCycle.Field.expireDate, prodExpire));
                    if(!CommonUtil.isEmpty(lastList)){//理论上是不会为空 而且只有一条的
                        CoProdBillingCycle lastCycle = lastList.get(0);
                        CoProdBillingCycle newCycleExpire = (CoProdBillingCycle)IMSUtil.copyDataObject(lastCycle);
                        newCycleExpire.setValidDate(lastCycle.getExpireDate());
                        newCycleExpire.setExpireDate(expireDate);
                        insert(newCycleExpire);
                    }
                }
            }
            
            
        }catch (Exception e) {
            IMSUtil.throwBusiException(e);
        }

        SProductOrderResult result = new SProductOrderResult();

        if (validDate == null)
            validDate = prodValid;
        if (expireDate == null)
            expireDate = prodExpire;
        result.setOffer_id(CommonUtil.IntegerToLong(this.mergeProd(prodList).getProductOfferingId()));
        result.setProduct_id(productId);
        result.setValid_date(IMSUtil.formatDate(validDate));
        result.setExpire_date(IMSUtil.formatDate(expireDate));
        return result;
    }

    /**
     * @Description 处理产品配生 A+B=C;A+O=C;O+A=C;D=B+C(A主产品，B，D普通产品，O表示任意，等号右边的都是BOSS派生出来的产品 ，不用返回给CRM)
     * @Author lijingcheng
     * @param dmProd 当前请求订购完的产品，即需要处理是否有派生的销售品
     * @param operType 操作类型 0-add;1-delete;2-modify;3-extend
     * @param addOfferIds 一次请求中新增加的offerIds
     * @Param delOfferIds 一次请求中退订的销售品OfferIds
     * @param sProductOrder 当前的SProductOrder
     * @param servList 增加的服务列表
     * @param delSerSpecIds 删除时候的服务id列表
     * @param orderList new出来的结构，用来返回创建出来的SProductOrderList,具体用于构建业务记录
     */
    public List<CoProd> evolveProduct(CoProd dmProd, List<Integer> addOfferIds, List<Integer> delOfferIds, int operType,
            SProductOrder sProductOrder, List<CmResServCycle> servList, List<Integer> delSerSpecIds, SProductOrderList orderList)
    {
        if (CommonUtil.isEmpty(servList))
        {
            servList = new ArrayList<CmResServCycle>();
        }
        List<CoProd> prodList = new ArrayList<CoProd>();
        // 判断传入的销售品id，是否符合派生条件
        // 查询是否订购了需要派生条件的销售品(订购列表和传入的列表)，并且得出主产品
        Integer mainOfferId = null;
        Long mainProductId = null;
        if (CommonUtil.isNotEmpty(addOfferIds))
        {
            for (Integer offerId : addOfferIds)
            {
                PmProductOffering offering = this.queryOfferingByOfferId(offerId.longValue());
                if (offering.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                {
                    mainOfferId = offering.getProductOfferingId();
                    break;
                }
            }
        }
        List<Integer> existIds = new ArrayList<Integer>();
        List<CoProd> existProdList = queryProdList(dmProd.getObjectId(), dmProd.getObjectType());
        if (CommonUtil.isNotEmpty(existProdList))
        {
            for (CoProd p : existProdList)
            {
                existIds.add(p.getProductOfferingId());
                if (mainOfferId == null && p.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                {
                    mainOfferId = p.getProductOfferingId();
                    mainProductId = p.getProductId();
                }
            }
        }
        if (mainProductId == null && context.getExtendParam(ConstantDefine.CHANGE_PROD_ADD_MAIN_PRODUCT_ID) != null)
        {
            mainProductId = (Long) context.getExtendParam(ConstantDefine.CHANGE_PROD_ADD_MAIN_PRODUCT_ID);
        }
        Set<Integer> set = new HashSet<Integer>(existIds);
        if (CommonUtil.isNotEmpty(addOfferIds))
        {
            set.addAll(addOfferIds);
        }
        // 获取真正最后剩下的产品
        if (CommonUtil.isNotEmpty(delOfferIds))
        {
            for (Iterator i = set.iterator(); i.hasNext();)
            {
                Integer pId = (Integer) i.next();
                if (delOfferIds.contains(pId))
                {
                    i.remove();
                }
            }
        }
        // set 即是用户所以的销售品列表 mainofferId 主销售品id
        List<SProductOrder> orderArray = new ArrayList<SProductOrder>();
        if (operType == EnumCodeDefine.CHANGE_PROD_ADD)
        {
            List<CoProdMapping> mappList = new ArrayList<CoProdMapping>();
            List<Integer> offerIds = queryPmOfferMapping(dmProd.getProductOfferingId(), mainOfferId,
                    EnumCodeDefine.PRODUCT_REL_ONE_2_MORE);
            if (CommonUtil.isNotEmpty(offerIds))
            {
                for (Integer offerId : offerIds)
                {
                    SProductOrder order = transfer2SprodOrder(dmProd, sProductOrder, offerId.longValue(), null, operType);
                    orderArray.add(order);
                    CoProd p = createProductOrder(order, servList);
                    prodList.add(p);
                    // 创建关系
                    mappList.add(evolveProductMapping(dmProd, p, EnumCodeDefine.PRODUCT_REL_ONE_2_MORE));
                }
            }
            if (mainProductId != null)
            {
                // 订购的是主产品
                offerIds = null;
                if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                {
                    offerIds = getMappOfferIdsByMainOfferId(dmProd.getProductOfferingId(), mainOfferId,
                            EnumCodeDefine.PRODUCT_REL_ENVLOPROD, new ArrayList<Integer>(set));
                }
                else
                {
                    // 订购了主产品，走上面的分支完成A+B=C+D
                    if (context.getExtendParam(ConstantDefine.CHANGE_PROD_ADD_MAIN_PRODUCT_ID) == null)
                    {
                        offerIds = queryPmOfferMapping(dmProd.getProductOfferingId(), mainOfferId,
                                EnumCodeDefine.PRODUCT_REL_ENVLOPROD);
                    }
                }
                if (CommonUtil.isNotEmpty(offerIds))
                {
                    for (Integer offerId : offerIds)
                    {
                        SProductOrder order = transfer2SprodOrder(dmProd, sProductOrder, offerId.longValue(), null, operType);
                        orderArray.add(order);
                        CoProd p = createProductOrder(order, servList);
                        prodList.add(p);
                        // 创建关系A+B=C+D
                        mappList.add(evolveProductMapping(dmProd, p, EnumCodeDefine.PRODUCT_REL_ENVLOPROD));
                    }
                }
            }
            if (CommonUtil.isNotEmpty(mappList))
            {
                this.insertBatch(mappList);
            }
        }
        else
        {
            List<Long> allProductIds = new ArrayList<Long>();
            List<Long> productIds = queryProdMapping(dmProd.getProductId(), mainProductId, EnumCodeDefine.PRODUCT_REL_ONE_2_MORE);
            if (CommonUtil.isNotEmpty(productIds))
            {
                allProductIds.addAll(productIds);
            }
            List<Long> ids = null;
            if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
            {
                ids = queryProdMapping(null, dmProd.getProductId(), EnumCodeDefine.PRODUCT_REL_ENVLOPROD);
            }
            else
            {
                ids = queryProdMapping(dmProd.getProductId(), mainProductId, EnumCodeDefine.PRODUCT_REL_ENVLOPROD);
            }
            if (CommonUtil.isNotEmpty(ids))
            {
                allProductIds.addAll(ids);
            }
            if (CommonUtil.isNotEmpty(allProductIds))
            {
                for (Iterator i = allProductIds.iterator(); i.hasNext();)
                {
                    Long tempProdId = (Long) i.next();
                    // 没有操作过的才进行操作
                    if (context.getSingleCache(CoProdMapping.class,
                            new CacheCondition(CoProdMapping.Field.relProduct, tempProdId)) == null)
                    {
                        SProductOrder order = transfer2SprodOrder(dmProd, sProductOrder, null, tempProdId, operType);
                        orderArray.add(order);
                        if (operType == EnumCodeDefine.CHANGE_PROD_DELETE)
                        {
                            CoProd p = loadProd(tempProdId, dmProd.getObjectId());
                            deleteProdOrder(
                                    transferCoProdComplex(p, dmProd.getExpireDate(), null,
                                            (short) EnumCodeDefine.PROD_VALID_SPECIFIC_DATE), new ArrayList<Integer>());
                            prodList.add(p);
                            // 删除关联关系
                            deleteProdMapping(tempProdId, dmProd.getExpireDate());
                        }
                        else if (operType == EnumCodeDefine.CHANGE_PROD_MODIFY)
                        {
                            prodList.add(modifyProduct(order, null));
                        }
                        else if (operType == EnumCodeDefine.CHANGE_PROD_EXTEND)
                        {
                            prodList.add(extendExpireDate(order, IMSUtil.formatDate(dmProd.getExpireDate())));
                        }
                    }
                }
            }
        }
        if (CommonUtil.isNotEmpty(orderArray))
        {
            orderList.setItem(orderArray.toArray(new SProductOrder[orderArray.size()]));
            if (operType == EnumCodeDefine.CHANGE_PROD_DELETE)
            {
                context.addExtendParam(ConstantDefine.CACHE_DEL_PACK_PRODLIST + dmProd.getProductId(), orderList);
            }
        }
        return prodList;
    }

    // 删除关联关系
    private void deleteProdMapping(Long productId, Date expireDate)
    {
        CoProdMapping mapp = new CoProdMapping();
        mapp.setRelProduct(productId);
        expireDate = expireDate == null ? context.getRequestDate() : expireDate;
        this.deleteByCondition(CoProdMapping.class, expireDate, new DBCondition(CoProdMapping.Field.relProduct, productId));
    }

    // 查出来的要在offerIds里面有才进行返回
    private List<Integer> getMappOfferIdsByMainOfferId(Integer offerId, Integer mainOfferId, int relationType,
            List<Integer> offerIds)
    {
        // 产品列表为空 不存在A+B=C+D,因为A不存在
        if (CommonUtil.isEmpty(offerIds))
        {
            return null;
        }
        else
        {
            List<Integer> offers = new ArrayList<Integer>();
            List<PmProductOfferMapping> mapps = this.queryPmOfferMapp(offerId, mainOfferId, relationType);
            if (CommonUtil.isEmpty(mapps))
            {
                return null;
            }
            // 关系id必须存在于用户的产品列表中，才实例化
            for (PmProductOfferMapping m : mapps)
            {
                if (offerIds.contains(m.getProductOfferingId()))
                {
                    offers.add(m.getRelProductOffering());
                }
            }
            return CommonUtil.isNotEmpty(offers) ? offers : null;
        }
    }

    // 查询关联关系
    private List<PmProductOfferMapping> queryPmOfferMapp(Integer offerId, Integer mainOfferId, int relationType)
    {
        // PmProductOfferMapping mapp=new PmProductOfferMapping();
        List<DBCondition> con = new ArrayList<DBCondition>();
        if (relationType == EnumCodeDefine.PRODUCT_REL_ONE_2_MORE)
        {
            con.add(new DBCondition(PmProductOfferMapping.Field.productOfferingId, offerId));
            con.add(new DBCondition(PmProductOfferMapping.Field.relationTypeId, relationType));
            // mapp.setProductOfferingId(offerId);
            // mapp.setRelationTypeId(relationType);
        }
        else
        {
            if (mainOfferId == null)
            {
                return null;
            }
            con.add(new DBCondition(PmProductOfferMapping.Field.productOfferingId, offerId));
            con.add(new DBCondition(PmProductOfferMapping.Field.relationTypeId, relationType));
            con.add(new DBCondition(PmProductOfferMapping.Field.refMainOffering, mainOfferId));
            // mapp.setProductOfferingId(offerId);
            // mapp.setRefMainOffering(mainOfferId);
            // mapp.setRelationTypeId(relationType);
        }
        return this.query(PmProductOfferMapping.class, con.toArray(new DBCondition[con.size()]));
    }

    // 获取产品配生的销售品id
    private List<Integer> queryPmOfferMapping(Integer offerId, Integer mainOfferId, int relationType)
    {
        List<Integer> offerIds = new ArrayList<Integer>();
        List<PmProductOfferMapping> mapps = this.queryPmOfferMapp(offerId, mainOfferId, relationType);
        if (CommonUtil.isEmpty(mapps))
        {
            return null;
        }
        for (PmProductOfferMapping mp : mapps)
        {
            offerIds.add(mp.getRelProductOffering());
        }
        return offerIds;
    }

    // 获取派生关系
    private List<Long> queryProdMapping(Long productId, Long mainProductId, int relationType)
    {
        List<Long> productIds = new ArrayList<Long>();
        // CoProdMapping mapp=new CoProdMapping();
        List<DBCondition> con = new ArrayList<DBCondition>();
        if (productId != null)
        {
            con.add(new DBCondition(CoProdMapping.Field.productId, productId));
            // mapp.setProductId(productId);
        }
        // mapp.setRelationTypeId(relationType);
        con.add(new DBCondition(CoProdMapping.Field.relationTypeId, relationType));
        
        //去掉CO_PROD_MAPPING.REF_MAIN_PRODUCT
//        if (relationType == EnumCodeDefine.PRODUCT_REL_ENVLOPROD && mainProductId != null)
//        {
//            // mapp.setRefMainProduct(mainProductId);
//            con.add(new DBCondition(CoProdMapping.Field.refMainProduct, mainProductId));
//        }
        List<CoProdMapping> mapps = this.query(CoProdMapping.class, con.toArray(new DBCondition[con.size()]));
        if (CommonUtil.isEmpty(mapps))
        {
            return null;
        }
        for (CoProdMapping mp : mapps)
        {
            productIds.add(mp.getRelProduct());
        }
        return productIds;
    }

    // 创建派生关系
    private CoProdMapping evolveProductMapping(CoProd dmProd, CoProd evolveProd,int relationType)
    {
        CoProdMapping mapp = new CoProdMapping();
        mapp.setCreateDate(evolveProd.getCreateDate());
        mapp.setExpireDate(evolveProd.getExpireDate());
        mapp.setProductId(dmProd.getProductId());
        mapp.setRelationTypeId(relationType);
        mapp.setRelProduct(evolveProd.getProductId());
        mapp.setValidDate(evolveProd.getValidDate());
        mapp.setSoDate(evolveProd.getSoDate());
        mapp.setSoNbr(context.getSoNbr());
        return mapp;
    }

    /**
     * 构建打包关系
     */
    private SProductOrder transfer2SprodOrder(CoProd dmProd, SProductOrder sProductOrder, Long evolveOfferId,
            Long evolveProductId, int operType)
    {
        Long acctId = null;
        Long userId = null;
        if (dmProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            userId = dmProd.getObjectId();
        }
        else
        {
            acctId = dmProd.getObjectId();
        }
        SProductOrder order = new SProductOrder();
        if (evolveProductId != null)
        {
            order.setProduct_id(evolveProductId);
            if (sProductOrder != null)
            {
                order.setBilling_type(sProductOrder.getBilling_type());
            }
        }
        else
        {
            order.setProduct_id(DBUtil.getSequence(CoProd.class));
        }
        if (evolveOfferId != null)
        {
            order.setOffer_id(evolveOfferId.longValue());
        }
        order.setValid_date(IMSUtil.formatDate(dmProd.getValidDate()));
        order.setValid_type((short) EnumCodeDefine.PROD_VALID_SPECIFIC_DATE);
        order.setCreate_date(IMSUtil.formatDate(dmProd.getCreateDate()));
        order.setExpire_date(IMSUtil.formatDate(dmProd.getExpireDate()));
        order.setAcct_id(acctId);
        order.setUser_id(userId);
        if (sProductOrder != null)
        {
            order.setBillable_acct_id(sProductOrder.getBillable_acct_id());
            order.setBill_cycle(sProductOrder.getBill_cycle());
            order.setReguid_price_param(sProductOrder.getReguid_price_param());
            //@Date 2012-06-21 lijc3 群产品需要设置billing_type
            order.setBilling_type(sProductOrder.getBilling_type());
            // 特征值的copy
            copySparamList(sProductOrder, order);
        }
        if (ProjectUtil.is_TH_AIS())
        {
            order.setParent_serv_product(dmProd.getProductId());
        }
        return order;
    }

    /**
     * copy特征值
     */
    private void copySparamList(SProductOrder old, SProductOrder newOrder)
    {
        // 获取销售品id
        Long offerId = newOrder.getOffer_id();
        if (offerId == null)
        {
            CoProd dmProd = this.loadProd(newOrder.getProduct_id(), newOrder.getUser_id(), newOrder.getAcct_id());
            offerId = dmProd.getProductOfferingId().longValue();
        }
        // 获取销售品能够使用的特征值id
        List<Integer> specIds = this.querySpecCharIdsByOfferId(offerId.intValue());
        if (CommonUtil.isEmpty(specIds))
        {
            return;
        }
        if (old.getParam_list() == null || CommonUtil.isEmpty(old.getParam_list().getSProductParamList_Item()))
        {
            return;
        }
        List<SProductParam> newSProdParams = new ArrayList<SProductParam>();
        SProductParam[] sps = old.getParam_list().getSProductParamList_Item();
        for (SProductParam param : sps)
        {
            if (param.getParam_id() == null || !CommonUtil.isValid(param.getParam_value()))
            {
                continue;
            }
            // 传入的特征值存在于新的销售品能够使用的特征值里面，则进行copy
            if (specIds.contains(param.getParam_id()))
            {
                SProductParam p = new SProductParam();
                p.setExpire_date(param.getExpire_date());
                p.setGroup_id(param.getGroup_id());
                p.setParam_id(param.getParam_id());
                p.setParam_name(param.getParam_name());
                p.setParam_value(param.getParam_value());
                p.setValid_date(param.getValid_date());
                newSProdParams.add(p);
            }
        }
        if (newSProdParams.size() > 0)
        {
            SProductParamList sparamList = new SProductParamList();
            sparamList.setSProductParamList_Item(newSProdParams.toArray(new SProductParam[newSProdParams.size()]));
            newOrder.setParam_list(sparamList);
        }
        // 添加默认的特征值
        addDefaultSpecChars(newOrder);
    }

    /**
     * @Description 处理销售品及产品之间的关系
     * @Author lijingcheng
     * @param dmProd 当前的产品
     * @param order 操作的SProductOrder参数 删除的时候这个可以位null
     * @param operType 操作类型
     * @param servList 增加产品包含的服务
     * @param addServsList 删除产品包含的服务id
     * @param orderList 这个结构必须是new 出来的
     * @return 处理过的派生的或者打包的产品列表
     */
    public List<CoProd> handleOfferRel(CoProd dmProd, SProductOrder order, int operType, List<CmResServCycle> servList,
            List<Integer> addServsList, SProductOrderList orderList)
    {
        List<SProductOrder> orderArray = new ArrayList<SProductOrder>();
        List<CoProd> prodList = new ArrayList<CoProd>();
        CmResource user = null;
        if (dmProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            User3hBean userBean = context.get3hTree().loadUser3hBean(dmProd.getObjectId());
            user = userBean.getUser();
        }
        // AIS处理打包产品关系
        if (ProjectUtil.is_TH_AIS())
        {
            if (operType == EnumCodeDefine.CHANGE_PROD_ADD)
            {
                List<Integer> offerIdList = queryPackageSonOfferIdsByParentOfferId(dmProd.getProductOfferingId());
                if (CommonUtil.isNotEmpty(offerIdList))
                {
                    for (Integer offerId : offerIdList)
                    {
                        imsLogger.info("***************begin to create son offer =" + order.getOffer_id()
                                + "under the parent offer =" + dmProd.getProductOfferingId());
                        SProductOrder newOrder = transfer2SprodOrder(dmProd, order, offerId.longValue(), null, operType);
                        orderArray.add(newOrder);
                        
                        prodList.add(this.createProductOrder(newOrder));
                    }
                }
            }
            else
            {
                List<Long> productIds = queryProductIdsByParentProductId(dmProd);
                if (CommonUtil.isNotEmpty(productIds))
                {
                    for (Long productId : productIds)
                    {
                        SProductOrder sOrder = transfer2SprodOrder(dmProd, order, null, productId, operType);
                        orderArray.add(sOrder);
                        if (operType == EnumCodeDefine.CHANGE_PROD_DELETE)
                        {
                            CoProd delProd = loadProd(productId, dmProd.getObjectId(), dmProd.getObjectType());
                            this.deleteProdOrder(
                                    transferCoProdComplex(delProd, dmProd.getExpireDate(), null,
                                            (short) EnumCodeDefine.PROD_VALID_SPECIFIC_DATE), addServsList);
                            // 删除打包关系
                            deletePackageProd(dmProd);
                            prodList.add(delProd);
                        }
                        else if (operType == EnumCodeDefine.CHANGE_PROD_MODIFY)
                        {
                            prodList.add(this.modifyProduct(sOrder, null));
                        }
                        else if (operType == EnumCodeDefine.CHANGE_PROD_EXTEND)
                        {
                            prodList.add(this.extendExpireDate(sOrder, IMSUtil.formatDate(dmProd.getExpireDate())));
                        }
                        else if (operType == EnumCodeDefine.CHANGE_PROD_CHANGE_PAYMODE)
                        {
                            prodList.add(this.changePayMode(sOrder, user));
                        }
                        else
                        {
                            prodList.add(this.changePayModeExtendModify(sOrder,CommonUtil.int2Short(operType)));
                        }
                    }
                }
            }
            if (CommonUtil.isNotEmpty(orderArray))
            {
                orderList.setItem(orderArray.toArray(new SProductOrder[orderArray.size()]));
                if (operType == EnumCodeDefine.CHANGE_PROD_DELETE)
                {
                    context.addExtendParam(ConstantDefine.CACHE_DEL_PACK_PRODLIST + dmProd.getProductId(), orderList);
                }
            }
            // 上海处理产品派生
        }
        else if (ProjectUtil.is_CN_SH())
        {
            // 判断A=B+C+D A+B=C+D XXX
            List<Integer> addOfferList = null;
            if (context.getExtendParam(ConstantDefine.CHANGE_PROD_ADD_OFFERIDS) != null)
            {
                addOfferList = (List<Integer>) context.getExtendParam(ConstantDefine.CHANGE_PROD_ADD_OFFERIDS);
            }
            List<Integer> delOfferList = null;
            if (context.getExtendParam(ConstantDefine.CHANGE_PROD_DEL_OFFERIDS) != null)
            {
                delOfferList = (List<Integer>) context.getExtendParam(ConstantDefine.CHANGE_PROD_DEL_OFFERIDS);
            }
            prodList = evolveProduct(dmProd, addOfferList, delOfferList, operType, order, servList, addServsList, orderList);
        }
        return prodList;
    }

    // 根据父销售品查询下面的子销售品列表
    public List<Integer> queryPackageSonOfferIdsByParentOfferId(Integer offerId)
    {
        List<Integer> ids = new ArrayList<Integer>();
        List<PmProductOfferComposite> list = this.query(PmProductOfferComposite.class, new DBCondition(
                PmProductOfferComposite.Field.parentProductOfferingId, offerId));
        if (CommonUtil.isNotEmpty(list))
        {
            for (PmProductOfferComposite p : list)
            {
                ids.add(p.getProductOfferingId());
            }
        }
        return CommonUtil.isNotEmpty(ids) ? ids : null;
    }

    /**
     * @Description 根据父产品id查询所有的子产品id
     * @Author lijingcheng
     * @param dmProd
     * @return
     */
    public List<Long> queryProductIdsByParentProductId(CoProd dmProd)
    {
        List<Long> ids = new ArrayList<Long>();
        List<CoProdBundleComposite> ccList = this.query(CoProdBundleComposite.class, new DBCondition(
                CoProdBundleComposite.Field.parentProductNo, dmProd.getProductId()), new DBCondition(
                CoProdBundleComposite.Field.objectId, dmProd.getObjectId()));
        if (CommonUtil.isNotEmpty(ccList))
        {
            for (CoProdBundleComposite c : ccList)
            {
                ids.add(c.getProductId());
            }
        }
        return CommonUtil.isNotEmpty(ids) ? ids : null;
    }
    
    /**
     * @Description: 判断特征规格值是否是子产品的特征规格值
     * @param dmProd
     * @return	 
     * @author: tangjl5
     * @Date: 2012-8-13
     */
    public boolean checkParamIsInSonProd(CoProd dmProd, Integer specCharId)
    {
        boolean isExist = false;
        List<Object> ccList = this.querySingleField(CoProdBundleComposite.class, CoProdBundleComposite.Field.productId, new DBCondition(
                CoProdBundleComposite.Field.parentProductNo, dmProd.getProductId()), new DBCondition(
                CoProdBundleComposite.Field.objectId, dmProd.getObjectId()));
        if (CommonUtil.isNotEmpty(ccList))
        {
            List<CoProd> prods = query(CoProd.class, new DBCondition(CoProd.Field.productId, ccList, Operator.IN));
            
            if (CommonUtil.isNotEmpty(prods))
            {
                List<Integer> offerIds = new ArrayList<Integer>();
                for (CoProd prod : prods)
                {
                    offerIds.add(prod.getProductOfferingId());
                }
                
                List<PmProductOfferSpecChar> specCharList = query(PmProductOfferSpecChar.class, new DBCondition(PmProductOfferSpecChar.Field.productOfferingId, 
                        offerIds, Operator.IN));
                
                List<Integer> specCharIds = new ArrayList<Integer>();
                if (CommonUtil.isNotEmpty(specCharList))
                {
                    for (PmProductOfferSpecChar specChar : specCharList)
                    {
                        specCharIds.add(specChar.getSpecCharId());
                    }
                    
                    List<PmProductSpecCharUse> charUse = query(PmProductSpecCharUse.class, new DBCondition(PmProductSpecCharUse.Field.specCharId, specCharIds, Operator.IN));
                    if (CommonUtil.isNotEmpty(charUse))
                    {
                        for (PmProductSpecCharUse specChar : charUse)
                        {
                            if (specChar.getSpecCharId() == specCharId.intValue())
                            {
                                isExist = true;  
                            }
                        }
                    }
                }
            }
        }
        
        return isExist;
    }
    
    /**
     * @Description 退订产品上发CRM
     * @Author yangjh
     * @Date 2012-06-05
     * @param coProd
     * @param expireDate
     * @return
     */
    public void insertImsSharProdSync(CoProd coProd, Date expireDate)
    {
        //2012-10-11 wukl 上海项目不需要上发ImsSharProdSync
        if (ProjectUtil.is_CN_SH())
        {
            return;
        }
        ImsSharProdSync imsSharProd = new ImsSharProdSync();
        if (coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
        {
            imsSharProd.setProductLevel(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
            Acct3hBean bean = context.get3hTree().loadAcct3hBean(coProd.getObjectId());
            imsSharProd.setCustId(bean.getCustId());
            imsSharProd.setAcctId(bean.getAcctId());
        }
        else if(coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            imsSharProd.setProductLevel(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
            User3hBean bean = context.get3hTree().loadUser3hBean(coProd.getObjectId());
            imsSharProd.setUserId(bean.getUserId());
            imsSharProd.setCustId(bean.getCustId());
            imsSharProd.setPhoneId(bean.getPhoneId());
            imsSharProd.setAcctId(bean.getAcctId());
            //linzt 加判断，加判断、抛异常
            //评审问题 2012-08-10
        }else if(coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_GCA){
        	imsSharProd.setProductLevel(EnumCodeDefine.PROD_OBJECTTYPE_GCA);
            Group3hBean bean = context.get3hTree().loadGroup3hBean(coProd.getObjectId());
            imsSharProd.setUserId(bean.getUserId());
            imsSharProd.setCustId(bean.getCustId());
            imsSharProd.setPhoneId(bean.getPhoneId());
            imsSharProd.setAcctId(bean.getAcctId());
        }
        else{
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID,"0,1,2");
        }
        imsSharProd.setCreateDate(context.getRequestDate());
        imsSharProd.setExpireDate(coProd.getExpireDate());
        if (expireDate == null)
        {
            imsSharProd.setEffectiveTime(context.getRequestDate());
        }
        else
        {
            imsSharProd.setEffectiveTime(expireDate);
        }
        imsSharProd.setId(DBUtil.getSequence());
        //编译错误 类型转换
        imsSharProd.setOfferId(CommonUtil.IntegerToLong(coProd.getProductOfferingId()));
        imsSharProd.setProductId(coProd.getProductId());
        imsSharProd.setSoNbr(context.getSoNbr());
        imsSharProd.setSts(1);
        super.insert(imsSharProd);
    }

    /**
     * @Description: 判断产品是不是打包销售品下的子产品
     * @param prodId
     * @return
     * @author: tangjl5
     * @Date: 2012-6-7
     */
    public boolean isComponentProd(Long prodId)
    {
        CoProdBundleComposite bundleProd = this.querySingle(CoProdBundleComposite.class, new DBCondition(
                CoProdBundleComposite.Field.productId, prodId));
        if (bundleProd == null)
            return false;
        else
            return true;
    }
    
    /**
     * @Description: 包装Co_prod_price_param，供查询使用，涉及到金额的三个参数，转换成了ims侧的单位
     * @param params	 
     * @author: user
     * @Date: 2012-7-20
     */
    public void packgeShowPriceParam(List<CoProdPriceParam> params)
    {
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        for (CoProdPriceParam param : params)
        {
            if (param.getParamId() == SpecCodeDefine.CALC_PARA_RC_BASE_FEE)
            {
                for (CoProdPriceParam priceParam : params)
                {
                    if (priceParam.getParamId() == SpecCodeDefine.CALC_PARA_RC_BASE_FEE_MEASURE)
                    {
                        priceParam.setParamValue(String.valueOf(amountCmp.parseImsAmount(CommonUtil.string2Integer(priceParam.getParamValue()), 
                                CommonUtil.string2Double(param.getParamValue()))));
                        break;
                    }
                }
            }else if (param.getParamId() == SpecCodeDefine.CALC_YEAR_PROM_FEE)
            {
                for (CoProdPriceParam priceParam : params)
                {
                    if (priceParam.getParamId() == SpecCodeDefine.CALC_YEAR_PROM_FEE_MEASURE)
                    {
                        priceParam.setParamValue(String.valueOf(amountCmp.parseImsAmount(CommonUtil.string2Integer(priceParam.getParamValue()), 
                                CommonUtil.string2Double(param.getParamValue()))));
                        break;
                    }
                }
            }else if (param.getParamId() == SpecCodeDefine.FREE_RESOURCE_REWARD)
            {
                for (CoProdPriceParam priceParam : params)
                {
                    if (priceParam.getParamId() == SpecCodeDefine.FREE_RESOURCE_REWARD_MEASURE)
                    {
                        priceParam.setParamValue(String.valueOf(amountCmp.parseImsAmount(CommonUtil.string2Integer(priceParam.getParamValue()), 
                                CommonUtil.string2Double(param.getParamValue()))));
                        break;
                    }
                }
            }
            else if (param.getParamId() == SpecCodeDefine.CALC_PARA_RC_BASE_FEE_MEASURE || param.getParamId() == SpecCodeDefine.CALC_YEAR_PROM_FEE_MEASURE
                    || param.getParamId() == SpecCodeDefine.FREE_RESOURCE_REWARD_MEASURE)
            {
                param.setParamValue(String.valueOf(amountCmp.getImsMeasureId(CommonUtil.string2Integer(param.getParamValue()))));
            }
        }
    
    }
    
    /**
     * 查询PmRentDeductAction扣减失败动作定义表
     * @param failuserActionId
     * @Date 2012-06-12 wangdw5
     */
    public PmRentDeductAction getFailureActionWithCheck(Integer failuserActionId){
    	PmRentDeductAction action= querySingle(PmRentDeductAction.class, 
    			new DBCondition(PmRentDeductAction.Field.failureActionId,failuserActionId));
    	if(action == null){
    		throw IMSUtil.throwBusiException(ErrorCodeDefine.DUCTFAILUREACTION_NULL, failuserActionId);
    	}
    	return action;
    }
    
    /**
     * 
     *@Description 将 SProductOrder对象 进行复制
     *@Author linzt
     *@Date 2012-8-3
     *@param prod
     *@param prodOrg
     */

    public static void copySProductOrder(SProductOrder prod,SProductOrder prodOrg)
    {
        prod.setBill_cycle(prodOrg.getBill_cycle());
        prod.setReguid_price_param(prodOrg.getReguid_price_param());
        prod.setParam_list(prodOrg.getParam_list());
        prod.setProduct_id(prodOrg.getProduct_id());
        prod.setOffer_id(prodOrg.getOffer_id());
        prod.setSo_id(prodOrg.getSo_id());
        prod.setProduct_type(prodOrg.getProduct_type());
        prod.setParent_serv_product(prodOrg.getParent_serv_product());
        prod.setOuter_cust_id(prodOrg.getOuter_cust_id());
        prod.setAcct_id(prodOrg.getAcct_id());
        prod.setUser_id(prodOrg.getUser_id());
        prod.setPhone_id(prodOrg.getPhone_id());
        prod.setOuter_billable_acct_id(prodOrg.getOuter_billable_acct_id());
        prod.setBillable_acct_id(prodOrg.getBillable_acct_id());
        prod.setBilling_type(prodOrg.getBilling_type());
        prod.setProd_state(prodOrg.getProd_state());
        prod.setCreate_date(prodOrg.getCreate_date());
        prod.setValid_date(prodOrg.getCreate_date());
        prod.setValid_type(prodOrg.getValid_type());
        prod.setExpire_date(prodOrg.getExpire_date());
        prod.setNextcycle_start_date(prodOrg.getNextcycle_start_date());
        prod.setPrice_description(prodOrg.getPrice_description());
    }

    /**
     * 
     *@Description 新增offerId判断，支持群对offerId进行修改
     *@Author linzt
     *@Date 2012-8-3
     *@param groupId
     *@param prod
     *@param prodList
     *@return SProductOrderList
     *@throws IMSException
     */
    public SProductOrderList modifyGroupProd(long groupId, SProductOrder prod,List<CoProd> prodList) throws IMSException
    {
        Long prodId = prod.getProduct_id();
        //orderList 在请求参数进行绑定时使用
        SProductOrderList orderList=null;
        if (prodId == null)
        {
            //判断是否是AIS版本
            if(!ProjectUtil.is_TH_AIS()){
                throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCTID_IS_NULL);
            }
            Long offerId=prod.getOffer_id();
            if(offerId==null){
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"product_id,offer_id");
            }
             orderList= new SProductOrderList();
            try{
                List<CoProd> prodListNew=IMSUtil.matchDataObject(prodList,new CacheCondition(CoProd.Field.productOfferingId,offerId));
                if(prodListNew==null){
                    IMSUtil.throwBusiException(ErrorCodeDefine.GROUP_NOT_ORDER_PRODUCT,groupId,offerId);
                }
                //需要进行修改的SProductOrder型数组orders
                SProductOrder[] orders = new SProductOrder[prodListNew.size()];
                for(int i=0;i<prodListNew.size();i++){
                    CoProd coProd=prodListNew.get(i);
                    SProductOrder prod1=new SProductOrder();
                    copySProductOrder(prod1,prod);
                    prod1.setProduct_id(coProd.getProductId());
                    orders[i]=prod1;
                }
                orderList.setItem(orders);
                this.modifyProductInfo(orderList);
                prodList.removeAll(prodListNew);
            }
            catch(Exception e)
            {
                IMSUtil.throwBusiException(e);
            }
        }
        else{
            // 查询该群是否有该产品
            try{
                List<CoProd> prodListNew=IMSUtil.matchDataObject(prodList,new CacheCondition(CoProd.Field.productId,prodId));
                if(prodListNew.isEmpty()){
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.GROUP_NOT_ORDER_PRODUCT,groupId,prodId,prod.getOffer_id());
                }
                orderList = new SProductOrderList();
                SProductOrder[] orders = new SProductOrder[1];
                orders[0] = prod;
                orderList.setItem(orders);
                this.modifyProductInfo(orderList);
            }
            catch(Exception e){
                IMSUtil.throwBusiException(e);
            }
        }
        return orderList;
    }
    
    
    /**
     * 
     * @param prod
     * @param validDate
     * @param expireDate
     * @param paramId
     * @param measureId
     * @Date 2012-10-11 yugb :Bug #60997 定购产品和设置二次议价
     */
    public void setCoProdPriceParamValue(CoProd prod, String validDate, String expireDate,Integer paramId,Integer measureId){
    	CoProdPriceParam coProdPriceParam = new CoProdPriceParam();
    	coProdPriceParam.setProductId(prod.getProductId());
    	coProdPriceParam.setCreateDate(prod.getCreateDate());
    	coProdPriceParam.setSoDate(prod.getSoDate());
    	coProdPriceParam.setObjectId(prod.getObjectId());
    	coProdPriceParam.setObjectType(prod.getObjectType());
        coProdPriceParam.setValidDate(IMSUtil.formatDate(validDate, null));
    	coProdPriceParam.setExpireDate(IMSUtil.formatDate(expireDate, null));
    	coProdPriceParam.setParamValue(CommonUtil.int2String(measureId));
    	coProdPriceParam.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
    	coProdPriceParam.setParamId(paramId);
    	coProdPriceParam.setSoNbr(context.getSoNbr());
    	this.insert(coProdPriceParam);
    }
    
    /**
     * 
     * @Description 组装Ims_Prod_Order_Sync信息
     * @Author zhangzj3
     * @param bean 三户信息
     * @param srcAction 触发类型
     */
    public ImsProdOrderSync getImsProdOrderSync(IMS3hBean bean,int srcAction){
        ImsProdOrderSync imsProdOrderSync = new ImsProdOrderSync();
        imsProdOrderSync.setId(DBUtil.getSequence());
        imsProdOrderSync.setSoNbr(context.getSoNbr());
        imsProdOrderSync.setReasonCode(0);
        imsProdOrderSync.setSts(1);
        imsProdOrderSync.setSrcAction(srcAction);
        if(bean != null){
            imsProdOrderSync.setAcctId(bean.getAcctId());
            imsProdOrderSync.setCustId(bean.getCustId());
            imsProdOrderSync.setUserId(bean.getUserId());
            imsProdOrderSync.setPhoneId(bean.getPhoneId());
        }
        return imsProdOrderSync;
    }
 
}
