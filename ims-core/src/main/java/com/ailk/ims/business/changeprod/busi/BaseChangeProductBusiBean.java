package com.ailk.ims.business.changeprod.busi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.BusiRecordComponent;
import com.ailk.ims.component.ProdStsComponent;
import com.ailk.ims.component.query.CacheQuery;
import com.ailk.ims.define.BusiSpecDefine;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.cust.entity.CmBusiOrder;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imsintf.entity.Do_changeProductResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SChangeProduct;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOper;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOperList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderResultList;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description 修改产品
 * @author ljc
 * @Date 2011-9-27 2012-02-14 wuyujie : 为了后续收取一次性费用，需要把add的销售品列表都记录下来 2012-03-19 lijc3 //更换主产品，需要变更周期性免费资源为一次性免费资源
 *       prodCmp.covertFreeResByChangeMainProd(mainProd.getProductId(), userBean.getBillAcctId());
 * @Date2012-04-11 lijc3 取消product_type非空验证
 * @Date 2012-04-16 调用查询用户生效最晚的主产品
 * @Date 2012-04-26 lijc3 使用object_id进行查询,传入分表字段
 * @Date 2012-07-03 luojb 退订的产品缓存起来，后面需要加标志
 * @Date 2012-07-09 yangjh story:50254 黑白名单增加最大数量限制,新增keep_ics_list字段 在删除callScreen的时候是否需要恢复黑白名单
 * @Date 2012-07-16 yangjh 新增busiFail(Exception e,Object[] input,IMSContext context)发订购失败告警ID
 * @Date 2012-07-17 yangjh prodOrderFail2Notify方法修改 传入alarmId
 * @Date 2012-07-18 wangdw5 :#51867没有product_id空验证
 * @Date 2012-07-27 yangjh : busiFail增加oper_type = add的判断
 * @date 2012-08-13 luojb #54964 145产品suspend_no_validity用户可以订购
 */
public class BaseChangeProductBusiBean extends BaseCancelableBusiBean
{
    private Long acctId;
    private Long userId;
    private String phoneId;
    private Long custId;
    private Short keepIcsList;
    private BaseProductComponent prodCmp;
    private CoProd mainProd;
    private PmProductOffering newOffer;
    private boolean changeMainProdFlag = false;

    @Override
    public void init(Object... input) throws IMSException
    {
        SChangeProduct sChgProduct = (SChangeProduct) input[0];
        acctId = sChgProduct.getAcct_id();
        userId = sChgProduct.getUser_id();
        phoneId = sChgProduct.getPhone_id();
        custId = sChgProduct.getCust_id();
        keepIcsList = sChgProduct.getKeep_ics_list();
        prodCmp = this.getProductCmp();
    }
    
    protected BaseProductComponent getProductCmp()
    {
        return context.getComponent(BaseProductComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SChangeProduct sChgProduct = (SChangeProduct) input[0];
        SProductOrderOperList sProdOrderOperList = sChgProduct.getProd_order_list();
        if (sProdOrderOperList == null || CommonUtil.isEmpty(sProdOrderOperList.getSProductOrderOperList_Item()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sProdOrderOperList");
        }

        // 不能同时为空
        if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId) && !CommonUtil.isValid(acctId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "user_id,phone_id,acct_id");
        }

        if (!CommonUtil.isValid(keepIcsList))
        {
            keepIcsList = EnumCodeDefine.PROD_KEEP_ICS_LIST;
        }
        context.addExtendParam(ConstantDefine.WHETHER_NEED_KEEP_ICS_LIST, keepIcsList);
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SChangeProduct sChgProduct = (SChangeProduct) input[0];
        IMS3hBean bean = context.getMain3hBean();
        // 2012-08-13 luojb #54964 145产品suspend_no_validity用户可以订购
        this.checkSts(sChgProduct, bean);
        this.checkAllIds(sChgProduct, bean);

        SProductOrderResultList result = prodCmp.operateProduct(sChgProduct.getProd_order_list());
        if (changeMainProdFlag)
        {
            context.addExtendParam(ConstantDefine.OLD_MAIN_PROM, mainProd);
            prodCmp.changeUserValidityByReplaceMainProd(mainProd, newOffer, userId, phoneId);
            // User3hBean userBean = context.get3hTree().loadUser3hBean(userId, phoneId);
            // 更换主产品，需要变更周期性免费资源为一次性免费资源
            // if (ProjectUtil.is_TH_AIS())
            // {
            // prodCmp.covertFreeResByChangeMainProd(mainProd.getProductId(), userBean.getBillAcctId());
            // }
        }

        return result == null ? null : new Object[] { result };
    }

    /**
     * 订购产品用户状态检查，AIS 145产品特殊操作 luojb 2012-8-13
     * 
     * @param sChgProduct
     * @param bean
     */
    private void checkSts(SChangeProduct sChgProduct, IMS3hBean bean)
    {
        if (!bean.isUser3hBean())
            return;
        Integer sts = ((User3hBean)bean).getUserLifeCycle().getSts();
        if (sts != EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY)
            return;
        // 非AIS项目，suspend状态用户需要报错
        if (!ProjectUtil.is_TH_AIS())
            IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USER_BUSISTS, bean.getUserId(), sts, context.getOper()
                    .getBusi_code());

        SProductOrderOperList prodOperList = sChgProduct.getProd_order_list();
        for (SProductOrderOper prodOper : prodOperList.getSProductOrderOperList_Item())
        {
            Short operType = prodOper.getOper_type();
            // 非订购产品，报错
            if (operType != null && operType != EnumCodeDefine.OPER_TYPE_ADD)
                IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USER_BUSISTS, bean.getUserId(), sts, context.getOper()
                        .getBusi_code());
            SProductOrderList orderList = prodOper.getProd_list();
            if (orderList == null || CommonUtil.isEmpty(orderList.getItem()))
                continue;
            for (SProductOrder order : orderList.getItem())
            {
                Long offerId = order.getOffer_id();
                if (offerId == null)
                    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "offer_id");
                // 不是145产品，报错
                Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offerId);
                if (offerBean.getBusiFlag() != SpecCodeDefine.REWARD_VALIDITY_PROMOTION)
                    IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USER_BUSISTS, bean.getUserId(), sts, context.getOper()
                            .getBusi_code());
                // 检查是否配置了鉴权，没配置则报错
                PmCompositeDeductRule deductRule = context.getComponent(ProdStsComponent.class)
                        .queryPmCompsiteDeductRuleByOfferId(CommonUtil.long2Int(offerId), EnumCodeDefine.PROD_BILLTYPE_PREPAID);
                if (deductRule == null)
                    IMSUtil.throwBusiException(ErrorCodeDefine.LACK_CONFIG_DATE2, "pm_composite_deduct_rule", "offerId", offerId,
                            "billingType", EnumCodeDefine.PROD_BILLTYPE_PREPAID);
                if (deductRule.getNeedAuth() == null || deductRule.getNeedAuth() == EnumCodeDefine.FEE_DEDUCTION_AUTH_FALSE)
                    IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_145_MUST_AUTH, offerId);
            }
        }
    }

    private void checkAllIds(SChangeProduct sChgProduct, IMS3hBean bean)
    {
        SProductOrderOperList sProdOrderOperList = sChgProduct.getProd_order_list();
        Set<Long> delProdIds = null;
        int delMainPromFlag = 0;
        int addMainPromFlag = 0;
        SProductOrderOper[] sProOrderOpers = sProdOrderOperList.getSProductOrderOperList_Item();
        for (SProductOrderOper sProOrderOper : sProOrderOpers)
        {
            if (sProOrderOper == null || sProOrderOper.getProd_list() == null
                    || CommonUtil.isEmpty(sProOrderOper.getProd_list().getItem()))
            {
                continue;
            }
            for (SProductOrder order : sProOrderOper.getProd_list().getItem())
            {
                // 只校验外部的id 内部ID和外部的统一设值
                order.setAcct_id(bean.getAcctId());
                order.setUser_id(bean.getUserId());
                order.setPhone_id(bean.getPhoneId());
                order.setCust_id(bean.getCustId());
            }

            Short operFlag = sProOrderOper.getOper_type();
            switch (operFlag)
            {
            case EnumCodeDefine.CHANGE_PROD_ADD:
                List<Long> offerIdList = new ArrayList<Long>();
                for (SProductOrder order : sProOrderOper.getProd_list().getItem())
                {
                    // @Date2012-04-11 lijc3 取消product_type非空验证
                    // if (order.getProduct_type() == null) {
                    // throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "product_type");
                    // }
                    if (order.getOffer_id() == null)
                    {
                        IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "offer_id");
                    }
                    newOffer = context.getComponent(CacheQuery.class).queryPmProductOfferingByOfferId(
                            order.getOffer_id().intValue());
                    if (newOffer.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                    {
                        if (bean.getUserId() == null)
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCT_CANOT_ORDER_MAIN_PROD);
                        }
                        order.setProduct_type(EnumCodeDefine.PROD_TYPE_MAIN);
                        addMainPromFlag++;
                    }
                    else
                    {
                        order.setProduct_type(EnumCodeDefine.PROD_TYPE_COMPONENT);
                    }
                    // 2012-02-14 wuyujie : 为了后续收取一次性费用，需要把add的销售品列表都记录下来
                    offerIdList.add(order.getOffer_id());

                    // if(order.getProduct_id()==null){
                    // order.setProduct_id(DBUtil.getSequence(CoProd.class));
                    // }
                }

                // 2012-02-14 wuyujie : 放入上下文中,后续一次性费用取用
                context.addExtendParam(ConstantDefine.CHGPROD_FOROTF_OFFERIDLIST, offerIdList);

                break;
            case EnumCodeDefine.CHANGE_PROD_DELETE:
                for (SProductOrder order : sProOrderOper.getProd_list().getItem())
                {
                    if (order.getProduct_id() == null)
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_ID_IS_NULL);
                    }
                    // @Date2012-04-26 lijc3 使用object_id进行查询
                    CoProd prod = prodCmp.loadProd(order.getProduct_id(), order.getUser_id(), order.getAcct_id());
                    // if (prod == null)
                    // {
                    // throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST,
                    // new Object[] { order.getProduct_id() });
                    // }
                    if (prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                    {
                        // 初始化旧的销售品
                        mainProd = prod;
                        delMainPromFlag++;
                    }
                    // 2012-07-03 luojb 退订的产品id保存起来
                    if (delProdIds == null)
                        delProdIds = new HashSet<Long>();
                    delProdIds.add(order.getProduct_id());
                }
                break;
            // @Date 2012-07-18 wangdw5 :#51867没有product_id空验证
            case EnumCodeDefine.CHANGE_PROD_EXTEND:
                for (SProductOrder prod : sProOrderOper.getProd_list().getItem())
                {
                    Long prodId = prod.getProduct_id();
                    if (prodId == null)
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_ID_IS_NULL);
                    }
                }
                break;
            default:
                break;
            }
        }
        if (addMainPromFlag == 1 && delMainPromFlag == 1)
        {
            changeMainProdFlag = true;
        }
        if ((addMainPromFlag == 1 && delMainPromFlag == 0))
        {
            // @Date 2012-04-16 调用查询用户生效最早的主产品
            mainProd = prodCmp.queryFirstPrimaryProductByUserId(bean.getUserId());
            if (mainProd == null)
            {
                return;
            }
            changeMainProdFlag = true;
            SProductOrder order = new SProductOrder();
            order.setProduct_id(mainProd.getProductId());
            order.setAcct_id(bean.getAcctId());
            order.setUser_id(bean.getUserId());
            order.setPhone_id(bean.getPhoneId());
            order.setCust_id(bean.getCustId());
            SProductOrderOper delOper = new SProductOrderOper();
            delOper.setOper_type(EnumCodeDefine.CHANGE_PROD_DELETE);
            SProductOrderList orderList = new SProductOrderList();
            orderList.setItem(new SProductOrder[] { order });
            delOper.setProd_list(orderList);
            SProductOrderOper[] delOrderOper = new SProductOrderOper[] { delOper };
            SProductOrderOper[] addOpers = sChgProduct.getProd_order_list().getSProductOrderOperList_Item();
            SProductOrderOper[] result = new SProductOrderOper[addOpers.length + 1];
            System.arraycopy(delOrderOper, 0, result, 0, delOrderOper.length);
            System.arraycopy(addOpers, 0, result, delOrderOper.length, addOpers.length);
            SProductOrderOperList list = new SProductOrderOperList();
            list.setSProductOrderOperList_Item(result);
            sChgProduct.setProd_order_list(list);
        }
        else
        {
            if (delMainPromFlag != addMainPromFlag)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.MAIN_PROMOTION_OP_ERROR);
            }
        }

        // luojb 2012-07-03 退订的产品缓存起来，后面需要加标志
        if (delProdIds != null)
        {
            context.addExtendParam(ConstantDefine.TERMINATE_FLAG, delProdIds);
        }

    }

    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_changeProductResponse resp = new Do_changeProductResponse();
        Object[] tmp = result;
        if (!CommonUtil.isEmpty(tmp))
        {
            resp.setProd_result_list((SProductOrderResultList) tmp[0]);
        }
        return resp;
    }

    @Override
    public void destroy()
    {
        context = null;
    }

    /**
     * 告警通知
     * 
     * @author wuyj
     */
    public List<IMSNotification> createNotifications(Object[] input, Object[] result, BaseResponse resp)
    {
        // 在ts里发通知，这里不处理
        return null;
    }

    /**
     * 产品订购失败业务异常捕获发告警
     * 
     * @author yangjh
     */
    @Override
    public BaseResponse busiFail(Exception e, Object[] input)
    {
        SChangeProduct sChgProduct = (SChangeProduct) input[0];
        SProductOrderOperList operList = sChgProduct.getProd_order_list();
        if (operList == null || CommonUtil.isEmpty(operList.getSProductOrderOperList_Item()))
            return null;
        for (SProductOrderOper oper : operList.getSProductOrderOperList_Item())
        {
            if (oper == null)
                continue;
            // @Date 2012-07-27 yangjh : busiFail增加oper_type = add的判断
            if (oper.getOper_type() != EnumCodeDefine.CHANGE_PROD_ADD)
                continue;
            SProductOrderList prodList = oper.getProd_list();
            if (prodList == null || CommonUtil.isEmpty(prodList.getItem()))
                continue;
            // 2012-07-17 yangjh prodOrderFail2Notify方法修改 传入alarmId
            Integer alarmId = IMSUtil.getNotificationIdByBusiSpecIdAndFailedResult(context.getBusiSpecId(true,
                    BusiSpecDefine.NOTIFY_PROD_ADD));
            IMSUtil.prodOrderFail2Notify(prodList, e, context, alarmId);
        }
        return null;
    }

    /**
     * 创建业务记录
     */
    @Override
    public List<CmBusiOrder> createBusiOrderRecord(Object[] input)
    {
        BusiRecordComponent bc = context.getComponent(BusiRecordComponent.class);
        List<CmBusiOrder> list = new ArrayList<CmBusiOrder>();
        SChangeProduct sChgProduct = (SChangeProduct) input[0];
        SProductOrderOperList operList = sChgProduct.getProd_order_list();
        if (operList == null || CommonUtil.isEmpty(operList.getSProductOrderOperList_Item()))
            return null;
        for (SProductOrderOper sProOrderOper : operList.getSProductOrderOperList_Item())
        {
            if (sProOrderOper == null || sProOrderOper.getProd_list() == null)
                continue;
            Short operFlag = sProOrderOper.getOper_type();
            switch (operFlag)
            {
            case EnumCodeDefine.CHANGE_PROD_ADD:
                list.addAll(bc.buildAddProdBusiOrderList(sProOrderOper.getProd_list()));
                break;
            case EnumCodeDefine.CHANGE_PROD_DELETE:
                list.addAll(bc.buildDelProdBusiOrderList(sProOrderOper.getProd_list()));
                break;
            case EnumCodeDefine.CHANGE_PROD_MODIFY:
                list.addAll(bc.buildModifyProdBusiOrderList(sProOrderOper.getProd_list()));
                break;
            case EnumCodeDefine.CHANGE_PROD_EXTEND:
                list.addAll(bc.buildExtendProdBusiOrderList(sProOrderOper.getProd_list()));
                break;
            }
        }
        return list.size() == 0 ? null : list;
    }

    public List<com.ailk.ims.ims3h.IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        List<com.ailk.ims.ims3h.IMS3hBean> list = new ArrayList<com.ailk.ims.ims3h.IMS3hBean>();
        if (CommonUtil.isValid(phoneId) || CommonUtil.isValid(userId))
        {
            // 创建用户级3hbean
            list.add(context.get3hTree().loadUser3hBean(userId, phoneId));
            return list;
        }
        if (CommonUtil.isValid(acctId))
        {
            // 创建账户级3hbean
            list.add(context.get3hTree().loadAcct3hBean(acctId));
            return list;
        }
        if (CommonUtil.isValid(custId))
        {
            // 创建客户级3hbean
            list.add(context.get3hTree().loadCust3hBean(custId));
            return list;
        }

        return list;
    }
}
