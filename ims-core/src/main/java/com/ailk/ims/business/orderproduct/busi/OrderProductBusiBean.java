package com.ailk.ims.business.orderproduct.busi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.BusiRecordComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.ThreadUtil;
import com.ailk.openbilling.persistence.cust.entity.CmBusiOrder;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.ImsSonbrMapping;
import com.ailk.openbilling.persistence.imsinner.entity.Do_orderProductResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOper;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOperList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderResult;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderResultList;

/**
 * 回馈产品订购
 * 
 * @Description
 * @author luojb
 * @Date 2011-9-27
 * @Date 2012-2-6 删除reward产品同步信息，在rewardSync接口一并处理
 * @Date 2012-2-10 ljc 上海需求的特殊处理
 * @Date 2012-10-27 wukl 上海侧内部产品订购直接插入到JD域下的反向表中
 */
public class OrderProductBusiBean extends BaseCancelableBusiBean
{

    private SProductOrderResultList result = null;

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

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        imsLogger.info("******reward order product");
        SProductOrderList orderList = (SProductOrderList) input[0];
        imsLogger.dump("SProductOrderList", orderList);

        // 根据线程局部变量判断是否是首次激活的reward调用，若变量存在则将当前流水号设置为该值
        if (null != ThreadUtil.getThreadLocal())
        {
            context.setDoneCode((Long) ThreadUtil.getThreadLocal());
        }

        if (orderList == null || CommonUtil.isEmpty(orderList.getItem()))
        {
            return null;
        }

        List<com.ailk.openbilling.persistence.jd.entity.ImsOrderProduct> jdProdList = new ArrayList<com.ailk.openbilling.persistence.jd.entity.ImsOrderProduct>();
        SProductOrder[] prodOrders = orderList.getItem();
        for (SProductOrder order : prodOrders)
        {
            if (order.getOffer_id() == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "offer_id");
            }
            Long userId = order.getUser_id();
            Long acctId = order.getAcct_id();
            if (acctId == null && userId == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acctId/userId");
            }
            // acctId为空是根据userId取得acctId
            if (userId != null && acctId == null)
            {
                try
                {
                    acctId=context.get3hTree().loadUser3hBean(userId).getAcctId();
                }
                catch(IMS3hNotFoundException e)
                {
                	imsLogger.error(e,e);
                }
                order.setAcct_id(acctId);
            }
            // 根据acctId 取得 custId
            if (acctId != null)
            {
                CmCustomer customer = context.get3hTree().loadAcct3hBean(acctId).getCustomer();
                order.setCust_id(customer.getCustId());
            }
            // 同一用户的产品只插入一条记录
            // if (userId != null && !insertUserList.contains(userId))
            // {
            // insertUserList.add(userId);
            // }
            // 同一账户的产品只插入一条记录
            // if (!insertCaList.contains(acctId))
            // {
            // insertCaList.add(acctId);
            // }
            order.setProduct_type(EnumCodeDefine.PROD_TYPE_COMPONENT); // 单一产品
            if (ProjectUtil.is_CN_SH())
            {
                Date validDate = null;
                Date expireDate = null;
                if (CommonUtil.isNotEmpty(order.getValid_date()))
                {
                    validDate = DateUtil.getFormatDate(order.getValid_date());
                }
                if (order.getValid_type() != null && order.getValid_type() == EnumCodeDefine.PROD_VALID_SPECIFIC_DATE
                        && CommonUtil.isNotEmpty(order.getExpire_date()))
                {
                    expireDate = DateUtil.getFormatDate(order.getExpire_date());
                }
                
                 com.ailk.openbilling.persistence.jd.entity.ImsOrderProduct imsProd = context.getComponent(BaseProductComponent.class).createJDImsOrderProduct(
                        order.getOffer_id().intValue(), acctId, userId, validDate, expireDate);
                
                jdProdList.add(imsProd);
            }
        }
        SProductOrderOper orderOper = new SProductOrderOper();
            orderOper.setOper_type(EnumCodeDefine.CHANGE_PROD_ADD);
            orderOper.setProd_list(orderList);

            SProductOrderOper[] orderOpers = new SProductOrderOper[1];
            orderOpers[0] = orderOper;

            SProductOrderOperList operList = new SProductOrderOperList();
            operList.setSProductOrderOperList_Item(orderOpers);
        if (ProjectUtil.is_CN_SH())
        {
            if (jdProdList.size() > 0)
            {
                context.getComponent(BaseComponent.class).insertBatch(jdProdList);
            }
        }
        else
        {
            result = context.getComponent(BaseProductComponent.class).operateProduct(operList);
        }

        return result == null ? null : new Object[] { result };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_orderProductResponse resp = new Do_orderProductResponse();
        if (!CommonUtil.isEmpty(result))
        {
            resp.setProd_result_list((SProductOrderResultList) result[0]);
        }
        return resp;
    }

    @Override
    public ImsSonbrMapping chargeOneTimeFee()
    {
        return null;
    }

    @Override
    public void destroy()
    {
        result = null;

    }

    public List<CmBusiOrder> createBusiOrderRecord(Object[] input)
    {
        if (result == null)
        {
            return null;
        }
        SProductOrderResult[] resultItem = result.getSProductOrderResultList_Item();
        SProductOrder[] orderItem = new SProductOrder[resultItem.length];
        for (int i = 0; i < orderItem.length; i++)
        {
            SProductOrder order = new SProductOrder();
            order.setProduct_id(resultItem[i].getProduct_id());
            orderItem[i] = order;
        }
        SProductOrderList orderList = new SProductOrderList();
        orderList.setItem(orderItem);

        return context.getComponent(BusiRecordComponent.class).buildAddRewardProdBusiOrderList(orderList);
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        SProductOrderList orderList = (SProductOrderList) input[0];
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        for (SProductOrder order : orderList.getItem())
        {
            if (CommonUtil.isValid(order.getUser_id()) || CommonUtil.isValid(order.getPhone_id()))
            {
                list.add(context.get3hTree().loadUser3hBean(order.getUser_id(), order.getPhone_id()));
                break;
            }

            if (CommonUtil.isValid(order.getAcct_id()))
            {
                list.add(context.get3hTree().loadAcct3hBean(order.getAcct_id()));
                break;
            }

        }
        return list;
    }

}
