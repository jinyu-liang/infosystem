package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryPrimaryProductResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryPrimaryProductReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;

/**
 * @Description 查询主产品信息
 * @author yangyang
 * @Date 2011-9-27
 */
public class QueryPrimaryProductInfoBean extends BaseBusiBean
{

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
        SQueryPrimaryProductReq req = (SQueryPrimaryProductReq) input[0];
        Long resourceId = req.getUser_id();
        if (!CommonUtil.isValid(resourceId))
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resouceID");

        CoProd coProd = context.getComponent(BaseProductComponent.class).queryPrimaryProductByUserId(resourceId);
        SProductOrder po = new SProductOrder();
        //@Date 2012-10-18 yugb :如果没有查到主产品，则返回的主产品id为-1
        if (coProd == null)
        {
        	//throw IMSUtil.throwBusiException(ErrorCodeDefine.CAN_NOT_FIND_PRIMARY_PRODUCT_BY_USER_ID, resourceId);
        	po.setProduct_id(-1L);
        	po.setOffer_id(-1L);
        	po.setValid_date(DateUtil.formatDate(context.getRequestDate(),DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
        	po.setCreate_date(DateUtil.formatDate(context.getRequestDate(),DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
        	po.setExpire_date(DateUtil.formatDate(IMSUtil.getDefaultExpireDate(),DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
        }
        else
        {
        	 po.setProduct_id(coProd.getProductId());
        	 po.setOffer_id(coProd.getProductOfferingId().longValue());
             po.setValid_date(coProd.getValidDate() == null ? null : DateUtil.formatDate(coProd.getValidDate(),
                     DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
             po.setCreate_date(coProd.getCreateDate() == null ? null : DateUtil.formatDate(coProd.getCreateDate(),
                     DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
             po.setExpire_date(coProd.getExpireDate() == null ? null : DateUtil.formatDate(coProd.getExpireDate(),
                     DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
        }
        return new Object[] { po };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        SProductOrder po = (SProductOrder) result[0];
        Do_queryPrimaryProductResponse resp = new Do_queryPrimaryProductResponse();
        resp.setProductOrder(po);
        return resp;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        //增加获取3HBean方法 yanchuan 2012-05-15
        SQueryPrimaryProductReq req = (SQueryPrimaryProductReq) input[0];
        List<IMS3hBean> beans = new ArrayList<IMS3hBean>();
        beans.add(context.get3hTree().loadUser3hBean(req.getUser_id()));
        return beans;
    }

}
