package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.ProductCycleComponent;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProdListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.ProdList;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryProductsByUserIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description 根据 phoneId或者用户ID 查询用户级产品信息
 * @author yangjh
 * @Date 2012-01-10
 * @Date 2012-03-29 同时校验phoneId,userId
 * @Date 2012-6-7 tangjl5 对打包产品下的子产品进行过滤
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @Date 2012-11-15 wukl 当产品账期与账户账期一致时，上海不会实例化CoProdBillingCycle，去掉空校验
 */
public class QueryUserProdListBusiBean extends BaseBusiBean
{

    private Long userId = null;
    private String phoneId = null;
    private ProductQuery prodQuery = null;

    // BaseComponent baseCmp = context.getComponent(BaseComponent.class);
    @Override
    public void init(Object... input) throws IMSException
    {
        prodQuery = context.getComponent(ProductQuery.class);
        SQueryProductsByUserIdReq req = (SQueryProductsByUserIdReq) input[0];
        userId = req.getUserId();
        phoneId = (String) req.getPhoneId();
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.INPUT_ONE_USERID_PHONEID);
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        // @Date 2012-03-29 同时校验phoneId,userId
        User3hBean userBean = context.get3hTree().loadUser3hBean(userId, phoneId);
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        userId = userBean.getUserId();
        phoneId = userBean.getPhoneId();
        List<Long> prodIds = new ArrayList<Long>();
        List<Long> Ids = prodQuery.queryProdIdsByUserId(userId);
        
        // @Date 2012-6-7 tangjl5 对打包产品下的子产品进行过滤
        for (Long prodId : Ids)
        {
            if (!prodCmp.isComponentProd(prodId))
            {
                prodIds.add(prodId);
            }
        }
        
        if (CommonUtil.isEmpty(prodIds))
            return null;
        List<ProdList> prodlist = new ArrayList<ProdList>();

        for (Long productId : prodIds)
        {
            ProdList prodInfo = new ProdList();
            CoProd coProd = context.getComponent(BaseProductComponent.class).loadProd(productId, userId);
            if (coProd == null)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, productId);
            Boolean isSpecProd = SpecCodeDefine.isSpecialProd(coProd.getBusiFlag());
            if (isSpecProd)
            {
                continue;
            }
            prodInfo.setCoProd(coProd);

            List<CoProdCharValue> charValues = prodQuery.queryProdCharValue(productId, coProd.getObjectId());
            prodInfo.setCoProdCharValueList(charValues);

            PmProductOffering offer = prodQuery.queryOfferingByProdId(productId);
            if (offer == null)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OFFER_NOTEXIST, coProd.getProductOfferingId());
            prodInfo.setPmProductOffering(offer);
            // COMMON_OFFER_NOTEXIST
            List<CoProdBillingCycle> cycles = context.getComponent(ProductCycleComponent.class).queryProdBillingCycles(productId,
                    coProd.getObjectId());
            //@Date 2012-11-15 wukl 当产品账期与账户账期一致时，上海不会实例化CoProdBillingCycle，去掉空校验
            if (ProjectUtil.is_CN_SH())
            {
                if (CommonUtil.isNotEmpty(cycles))
                    prodInfo.setCoProdBillCycleList(cycles);
            }
            else
            {
                if (CommonUtil.isEmpty(cycles))
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.BILL_CYCLE_NOT_EXIST);
                prodInfo.setCoProdBillCycleList(cycles);
            }

            List<CoProdPriceParam> params = prodQuery.queryProdPriceParam(productId, coProd.getObjectId());
            
            // @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换为信息管理侧的measure_id   
            if (CommonUtil.isNotEmpty(params))
            {
                context.getComponent(BaseProductComponent.class).packgeShowPriceParam(params);
            }
            
            prodInfo.setCoProdPriceParamList(params);
            prodlist.add(prodInfo);
        }
        return new Object[] { prodlist };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryProdListResponse resp = new Do_queryProdListResponse();
        if (result == null)
        {
            return resp;
        }
        List<ProdList> prodlist = (List<ProdList>) result[0];
        resp.setProdList(prodlist.toArray(new ProdList[prodlist.size()]));
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(userId,phoneId));
        return list;
    }

}
