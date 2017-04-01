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
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProdListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.ProdList;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctProdListReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description 根据 账户ID 查询账户级产品信息
 * @author yangjh
 * @Date 2012-01-10
 * @Date 2012-6-7 tangjl5 对打包产品下的子产品进行过滤
 * @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换为信息管理侧的measure_id
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @Date 2012-11-15 wukl 当产品账期与账户账期一致时，上海不会实例化CoProdBillingCycle，去掉空校验
 */
public class QueryAcctProdListBusiBean extends BaseBusiBean
{
    private Long acctId = null;
    private ProductQuery prodQuery = null;
    private BaseProductComponent prodCmp = null;

    @Override
    public void init(Object... input) throws IMSException
    {
        prodQuery = context.getComponent(ProductQuery.class);
        prodCmp = context.getComponent(BaseProductComponent.class);
        SQueryAcctProdListReq req = (SQueryAcctProdListReq) input[0];
        acctId = req.getAcct_id();
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        if (!CommonUtil.isValid(acctId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acctId");
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        List<Long> Ids = prodQuery.queryProdIdsByAcctId(acctId);
        if (CommonUtil.isEmpty(Ids))
            return null;
        List<ProdList> prodlist = new ArrayList<ProdList>();
        List<Long> prodIds = new ArrayList<Long>();
        // @Date 2012-6-7 tangjl5 对打包产品下的子产品进行过滤
        for (Long prodId : Ids)
        {
            if (!prodCmp.isComponentProd(prodId))
            {
                prodIds.add(prodId);
            }
        }
        
        for (Long productId : prodIds)
        {
            ProdList prodInfo = new ProdList();
            CoProd coProd = prodCmp.loadProd(productId, acctId);
            if (coProd == null)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, productId);
            
            //date 2012-11-15 yanxl 64994
            if(SpecCodeDefine.isSpecialProd(coProd.getBusiFlag())){
            	continue;
            }
            
            prodInfo.setCoProd(coProd);

            List<CoProdCharValue> charValues = prodQuery.queryProdCharValue(productId, acctId);
            prodInfo.setCoProdCharValueList(charValues);

            PmProductOffering offer = prodQuery.queryOfferingByProdId(productId);
            if (offer == null)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OFFER_NOTEXIST, coProd.getProductOfferingId());
            prodInfo.setPmProductOffering(offer);
            // COMMON_OFFER_NOTEXIST
            List<CoProdBillingCycle> cycles = context.getComponent(ProductCycleComponent.class).queryProdBillingCycles(productId,
                    acctId);
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

            List<CoProdPriceParam> params = prodQuery.queryProdPriceParam(productId, acctId);
            
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
        list.add(context.get3hTree().loadAcct3hBean(acctId));
        return list;
    }

}
