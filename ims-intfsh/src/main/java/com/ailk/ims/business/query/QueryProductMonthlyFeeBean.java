package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BalanceShComponent;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryProductMonthlyFeeResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryMonthlyFeeReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SMonthlyFee;
import com.ailk.openbilling.persistence.imscnsh.entity.SProductFee;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;

/**
 * 查询已订购产品的月费
 * 
 * @author wangyh3
 * @Date 2012-7-9
 * @date 2012-11-5 zhangzj3 性能优化
 */
public class QueryProductMonthlyFeeBean extends BaseBusiBean
{

    private QueryMonthlyFeeReq req;
    Long userId = null;
    List<Long> prodIdList = null;
    BalanceShComponent balanceCmp;
    ProductQuery prodCmp;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (QueryMonthlyFeeReq) input[0];
        balanceCmp = context.getComponent(BalanceShComponent.class);
        prodCmp = context.getComponent(ProductQuery.class);
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (req == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "queryMonthlyFeeReq");
        }

        userId = req.getUser_id();
        prodIdList = req.getProdIdList();
        String phoneId = req.getPhone_id();

        if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "phone_id and user_id");
        }
        
        if (CommonUtil.isEmpty(prodIdList))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "prodIdList");
        } 
        else
        {
            for (Long prodId : prodIdList)
            {
                if (! CommonUtil.isValid(prodId))
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "prodIdList");
                }
            }
        }
       /* User3hBean bean = context.get3hTree().loadUser3hBean(userId, phoneId);
        userId = bean.getUserId();*/
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        /*List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(userId, req.getPhone_id()));
        return list;*/
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        //@Date 2012-11-5 zhangzj3 性能优化,公共校验
        Check3HParamReturn bean = context.getComponent(CheckComponentSH.class).check3HParam(null, null, userId, req.getPhone_id());
        userId = bean.getUserId();
        
        List<SProductFee> rtnList = new ArrayList<SProductFee>();
        List<Long> prodIds = new ArrayList<Long>();
        // 2012-11-25 luojb 查询性能优化
        for (Long prodId : prodIdList)
        {
            prodIds.add(prodId);
        }
        List<CoProd> dmProds = queryProdList(prodIds);
        if(dmProds == null)
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, prodIdList.get(0));
        for (Long prodId : prodIdList)
        {
            try
            {
                List<CoProd> prods = (List<CoProd>)IMSUtil.matchDataObject(dmProds, 
                        new CacheCondition(CoProd.Field.productId, prodId));
                if(prods == null || prods.size() == 0)
                    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, prodId);
                rtnList.add(queryProductFee(prods.get(0)));
            }
            catch (Exception e)
            {
                IMSUtil.throwBusiException(e);
            }
        }
        
        
        return new Object[] { rtnList };
    }
    
    private SProductFee queryProductFee(CoProd prod)
    {
        List<SMonthlyFee> monthlyFeeList = balanceCmp.queryMonthlyFee(prod.getPricingPlanId(), context.getRequestDate(), 0);
        SProductFee prodFee = new SProductFee();
        prodFee.setProduct_id(prod.getProductId());
        prodFee.setOffer_id(CommonUtil.IntegerToLong(prod.getProductOfferingId()));
        prodFee.setMonthlyFeeList(monthlyFeeList);
        return prodFee;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryProductMonthlyFeeResponse resp = new Do_queryProductMonthlyFeeResponse();
        resp.setProductFeeList((List<SProductFee>) result[0]);

        return resp;
    }

    @Override
    public void destroy()
    {
    }
    
    
    private List<CoProd> queryProdList(List<Long> prodIds)
    {
        return prodCmp.query(CoProd.class, new DBCondition(CoProd.Field.productId,prodIds,Operator.IN));
    }
}
