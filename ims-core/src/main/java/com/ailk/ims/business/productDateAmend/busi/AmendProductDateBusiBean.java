package com.ailk.ims.business.productDateAmend.busi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.ProductCycleComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.imsinner.entity.AmendProductDate;
import com.ailk.openbilling.persistence.imsinner.entity.AmendProductDateReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_amendProductDateResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderResult;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderResultList;

/**
 * 修改产品时间
 * 
 * @author luojb
 * @Date 2012-4-13
 * @Date 2012-6-25 zhangzj3 [48124]修改产品生失效时间补充，修改为能接收多个
 * @Date 2012-06-28 yangjh story:48124产品生失效时间补充 bug:49083 生效时间修改增加限制 增加账户级产品和用户级产品的区分
 * @date 2012-07-10 luojb #50229 删除co_prod_valid, co_prod 增加prod_valid_date，prod_expire_date
 * @Date 2012-07-24 tangjl5 :Story # 51379 若产品剩余的有效期为短周期，则通知扣费
 */
public class AmendProductDateBusiBean extends BaseBusiBean
{

    private Long userId = null;
    private Long acctId = null;
    private String phoneId = null;
    // private Long payAcctId = null;
    // private BaseComponent baseCmp = null;
    private BaseProductComponent prodCmp = null;

    // private AccountComponent acctCmp = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        AmendProductDateReq req = (AmendProductDateReq) input[0];
        // baseCmp = context.getComponent(BaseComponent.class);
        prodCmp = context.getComponent(BaseProductComponent.class);
        // acctCmp = context.getComponent(AccountComponent.class);
        userId = req.getUser_id();
        acctId = req.getAcct_id();
        phoneId = req.getPhone_id();
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        AmendProductDateReq req = (AmendProductDateReq) input[0];
        AmendProductDate[] amendProdArr = req.getAmendProductDate_Item();
        if (CommonUtil.isEmpty(amendProdArr))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "amendProductDate_Item");
        }
        if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(acctId) && !CommonUtil.isValid(phoneId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "user_id,phone_id,acctId");
        }
        if ((CommonUtil.isValid(userId) || CommonUtil.isValid(phoneId)) && CommonUtil.isValid(acctId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.SETBUT_ACCOUNT_USER_EXIST);
        }
        for (AmendProductDate amendProd : amendProdArr)
        {
            if (amendProd.getProduct_id() == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "product_id");
            }
            if (!CommonUtil.isValid(amendProd.getExpire_date()) && !CommonUtil.isValid(amendProd.getValid_date()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "expire_date,valid_date");
            }
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        List<IMS3hBean> beanList = new ArrayList<IMS3hBean>();
        if (!CommonUtil.isValid(acctId))
        {
            User3hBean bean = context.get3hTree().loadUser3hBean(userId, phoneId);
            beanList.add(bean);
        }
        else
        {
            Acct3hBean bean = context.get3hTree().loadAcct3hBean(acctId);
            beanList.add(bean);
        }
        return beanList;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        AmendProductDateReq req = (AmendProductDateReq) input[0];
        AmendProductDate[] amendProdArr = req.getAmendProductDate_Item();
        SProductOrderResultList sProductOrderResultList = new SProductOrderResultList();
        List<SProductOrderResult> list = null;
        IMS3hBean bean = context.getMain3hBean();
        acctId = bean.getAcctId();
        userId = bean.getUserId();
        Long objectId = null;
        if (CommonUtil.isValid(userId))
        {
            // payAcctId = ((User3hBean) bean).getBillAcctId();
            objectId = userId;
        }
        else
        {
            objectId = acctId;
        }

        if (CommonUtil.isNotEmpty(amendProdArr))
        {
            list = new ArrayList<SProductOrderResult>();
            for (AmendProductDate amendProd : amendProdArr)
            {
                Long productId = amendProd.getProduct_id();
                Date validDate = IMSUtil.formatDate(amendProd.getValid_date());
                Date expireDate = IMSUtil.formatDate(amendProd.getExpire_date());
                // 查出所有记录
                List<CoProd> prodList = DBUtil.query(CoProd.class, new DBCondition(CoProd.Field.productId, productId),
                        new DBCondition(CoProd.Field.objectId, objectId));
                if (CommonUtil.isEmpty(prodList))
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.CAN_NOT_FIND_PROD_BY_ID, objectId);
                }

                CoProd currProd = null;
                for (CoProd prod : prodList)
                {
                    if (prod.getValidDate().before(context.getRequestDate())
                            && prod.getExpireDate().after(context.getRequestDate()))
                    {
                        currProd = prod;
                    }
                }

                if(null == currProd){
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.THE_PRODUCT_NOT_VALID, objectId);
                }
                
                prodCmp.checkAmend(prodCmp.mergeProd(prodList), validDate, expireDate, acctId);

                // @Date 2012-07-24 tangjl5 :Story # 51379 若产品剩余的有效期为短周期，则通知扣费
                List<CoProdBillingCycle> cycles = context.getComponent(ProductCycleComponent.class).queryProdBillingCycles(
                        currProd.getProductId(), currProd.getObjectId());
                CoProdBillingCycle curBillCycle = null;
                if (CommonUtil.isNotEmpty(cycles))
                {
                    for (CoProdBillingCycle billCycle : cycles)
                    {
                        Date billCycleValidDate = billCycle.getValidDate();
                        Date billCycleExpireDate = billCycle.getExpireDate();
                        if (billCycleValidDate.before(context.getRequestDate())
                                && billCycleExpireDate.after(context.getRequestDate()))
                        {
                            curBillCycle = billCycle;
                        }
                    }
                }

                SProductOrderResult result = prodCmp.amendProdDate(productId, prodList, objectId,
                        IMSUtil.formatDate(amendProd.getValid_date()), IMSUtil.formatDate(amendProd.getExpire_date()));

                // 增加为空判断 避免空指针
                if (result != null)
                {
                    list.add(result);
                }
            }
        }

        return new Object[] { sProductOrderResultList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_amendProductDateResponse resp = new Do_amendProductDateResponse();
        SProductOrderResultList sProductOrderResultList = (SProductOrderResultList) result[0];
        resp.setSProductOrderResultList(sProductOrderResultList);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

}
