package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BalanceShComponent;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryUserVNetMonthFeeResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryUserVNetMonthFeeReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SMonthlyFee;
import com.ailk.openbilling.persistence.imscnsh.entity.SProductFee;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;
/**
 * 
 * @Description查询用户集团V网实际月租费
 * @author zhangzhj
 * @Date 2012-8-25
 * @date 2012-11-5 zhangzj3 性能优化
 */
public class QueryUserVNetMonthFeeBean extends BaseBusiBean
{   
    private QueryUserVNetMonthFeeReq req;
    ProductQuery prodCmp;
    BalanceShComponent balanceCmp;
    @Override
    public void init(Object... input) throws BaseException{
        req = (QueryUserVNetMonthFeeReq)input[0];
        balanceCmp = context.getComponent(BalanceShComponent.class);
        prodCmp = context.getComponent(ProductQuery.class);
    }
    @Override
    public void validate(Object... arg0) throws BaseException{
        if (req == null){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QueryUserVNetMonthFeeReq");
        }
        if(!CommonUtil.isValid(req.getUser_id()) && !CommonUtil.isValid(req.getPhone_id())){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "user_id and phone_id");
        }
    }
    
    @Override
    public List<IMS3hBean> createMain3hBeans(Object... arg0) throws BaseException{
        /*List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(req.getUser_id(),req.getPhone_id()));
        return list;*/
        return null;
    }
    
    @Override
    public Object[] doBusiness(Object... arg0) throws BaseException{
        //@Date 2012-11-5 zhangzj3 性能优化,公共校验
        Check3HParamReturn bean = context.getComponent(CheckComponentSH.class).check3HParam(null, null, req.getUser_id(), req.getPhone_id());
        //IMS3hBean ims3hBean = context.get3hTree().loadUser3hBean(req.getUser_id(),req.getPhone_id());
        List<CoProd> prodIdList = prodCmp.queryProdListByUserId(bean.getUserId(),EnumCodeShDefine.GROUP_V_NET_BUSI_FLAG);
        List<SProductFee> productFeeList = new ArrayList<SProductFee>();
        if(prodIdList!=null&&!prodIdList.isEmpty()){
            for(CoProd prod : prodIdList){
                SProductFee productFee = queryProductFee(prod);
                if(productFee != null){
                    productFeeList.add(productFee);
                }
            }
        }
        return new Object[]{productFeeList};
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse createResponse(Object[] input){
        Do_queryUserVNetMonthFeeResponse res = new Do_queryUserVNetMonthFeeResponse();
        List<SProductFee> productFeeList = (List<SProductFee>)input[0];
        if(CommonUtil.isNotEmpty(productFeeList)){
            res.setProductFeeList(productFeeList);
            //有v网产品
            res.setIs_has_vnet_prod(EnumCodeShDefine.IS_HAS_VNET_PROD);
        }else {
        	//没有v网产品
        	res.setIs_has_vnet_prod(EnumCodeShDefine.IS_NO_VNET_PROD);
        }
        return res;
    }

    @Override
    public void destroy()
    {
    }

    private SProductFee queryProductFee(CoProd prod){
        List<SMonthlyFee> monthlyFeeList = balanceCmp.queryMonthlyFee(prod.getPricingPlanId(), context.getRequestDate(), 0);
        SProductFee prodFee = null;
        if(CommonUtil.isNotEmpty(monthlyFeeList)){
            prodFee = new SProductFee();
            prodFee.setProduct_id(prod.getProductId());
            prodFee.setOffer_id(CommonUtil.IntegerToLong(prod.getProductOfferingId()));
            prodFee.setMonthlyFeeList(monthlyFeeList);
        }
        return prodFee;
    }
}
