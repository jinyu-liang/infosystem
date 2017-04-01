package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProdOfferingInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.ProdOfferInfo;
import com.ailk.openbilling.persistence.imsinner.entity.SQryProdOfferInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;

/**
 * 支持GUI选择销售品后可以查询相关规格信息
 * 
 * @Description
 * @author liuyang
 * @Date 2011-10-22
 */
public class QueryProdOfferInfoBean extends BaseCancelableBusiBean
{

    private List<ProdOfferInfo> prodOffers = null;
    private Integer totalCount = null;
    SQryProdOfferInfoReq req = null;
    BaseProductComponent prodCmp = null;

    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(Object... input) throws IMSException
    {

        req = (SQryProdOfferInfoReq) input[0];
        prodOffers = new ArrayList<ProdOfferInfo>();
        prodCmp = context.getComponent(BaseProductComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
       

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        if (req.getOrderType() == null)
        {
            imsLogger.info("***************** order type is null so return null******");
            return null;
            
        }
        prodOffers = prodCmp.queryProdOffers(req);
        totalCount = prodCmp.queryProdOffersCount(req);

        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryProdOfferingInfoResponse rep = new Do_queryProdOfferingInfoResponse();
        rep.setTotalCount(totalCount);
        rep.setProdOfferInfoList(prodOffers);

        return rep;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
