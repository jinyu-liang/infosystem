package com.ailk.ims.business.query;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_isPrimaryProductResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 根据prod_id查询该产品是否为主产品
 * 
 * @author yangyang
 * @date 2011-8-16
 */
public class IsPrimaryProductBusiBean extends BaseBusiBean
{

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        Long productId = (Long) input[0];
        if (!CommonUtil.isValid(productId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_ID_IS_NULL);
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        long productId = (Long) input[0];

        CoProd prod = context.getComponent(BaseProductComponent.class).queryProd(productId);

        if (prod == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CAN_NOT_FIND_PROD_BY_ID, productId);
        }
        return new Object[] { prod };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_isPrimaryProductResponse resp = new Do_isPrimaryProductResponse();
        if (result[0] != null)
        {
            CoProd prod = (CoProd) result[0];
            boolean res = prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN ? true : false;
            resp.setRes(res);
        }
        return resp;
    }

    @Override
    public void destroy()
    {
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

}
