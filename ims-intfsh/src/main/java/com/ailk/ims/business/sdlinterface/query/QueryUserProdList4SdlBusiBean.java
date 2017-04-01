package com.ailk.ims.business.sdlinterface.query;

import java.util.ArrayList;
import java.util.List;
import jef.common.wrapper.Holder;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SCoProd;
import com.ailk.openbilling.persistence.imssdl.entity.SCoProdCharValue;
import com.ailk.openbilling.persistence.imssdl.entity.SProductInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SQueryInfo;

/**
 * 根据用户编号查询订购产品的信息
 * 
 * @Description
 * @author wukl
 * @Date 2012-7-19
 */
public class QueryUserProdList4SdlBusiBean extends BaseBusiBean
{

    @Override
    public void init(Object... input) throws BaseException
    {

    }

    @Override
    public void validate(Object... input) throws BaseException
    {

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        SQueryInfo queryInfo = (SQueryInfo) input[0];
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(queryInfo.getUserId()));
        return list;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SQueryInfo queryInfo = (SQueryInfo) input[0];
        Holder holder = (Holder)input[1];
        List<SProductInfo> sProductInfoList = (List<SProductInfo> ) holder.get();
        
        IMSUtil.setUserRouterParam(queryInfo.getUserId());

        User3hBean userBean = context.get3hTree().loadUser3hBean(queryInfo.getUserId());
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        Long userId = userBean.getUserId();
        List<CoProd> prods = prodCmp.queryProdListByUserId(userId);

        if (CommonUtil.isEmpty(prods))
        {
            return null;
        }

        for (CoProd coProd : prods)
        {
            SProductInfo sProductInfo = new SProductInfo();
            // 查询CO_PROD信息
            SCoProd sCoProd = new SCoProd();

            Boolean isSpecProd = SpecCodeDefine.isSpecialProd(coProd.getBusiFlag());
            if (isSpecProd)
            {
                continue;
            }
            sProductInfo.setProductId(coProd.getProductId());
            sProductInfo.setBusiFlag(coProd.getBusiFlag());
            copyCoProd2SCoProd(sCoProd, coProd);
            sProductInfo.setCoProd(sCoProd);

            List<CoProdCharValue> charValues = prodCmp.queryProdCharValue(coProd.getProductId(), userId);
            CsdlArrayList<SCoProdCharValue> sCoProdCharValueList=new CsdlArrayList<SCoProdCharValue>(SCoProdCharValue.class);
            copyListCharValue(sCoProdCharValueList, charValues);
            sProductInfo.setCoProdCharValueList(sCoProdCharValueList);

            sProductInfoList.add(sProductInfo);
        }
        return null;

    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_sdlResponse rs = new Do_sdlResponse();
        return rs;
    }

    @Override
    public void destroy()
    {
        
    }
    
    private void copyCoProd2SCoProd(SCoProd sProd,CoProd dmProd){
        sProd.setBillingType(dmProd.getBillingType());
        sProd.setBusiFlag(dmProd.getBusiFlag());
        sProd.setExpireDate(dmProd.getExpireDate());
        sProd.setIsMain(dmProd.getIsMain().shortValue());
        sProd.setLifecycleStatusId(dmProd.getLifecycleStatusId().shortValue());
        sProd.setObjectId(dmProd.getObjectId());
        sProd.setObjectType(dmProd.getObjectType().shortValue());
        sProd.setPricePlanId(dmProd.getPricingPlanId());
        sProd.setProdExpireDate(dmProd.getProdExpireDate());
        sProd.setProdTypeId(dmProd.getProdTypeId());
        sProd.setProductId(dmProd.getProductId());
        sProd.setProductOfferingId(dmProd.getProductOfferingId());
        sProd.setProdValidDate(dmProd.getProdValidDate());
        sProd.setValidDate(dmProd.getValidDate());
    }
    
    private void copyListCharValue(CsdlArrayList<SCoProdCharValue> sCoProdCharValueList,List<CoProdCharValue> charValues){
        if(CommonUtil.isNotEmpty(charValues)){
            for(CoProdCharValue dmValue:charValues){
                SCoProdCharValue value=new SCoProdCharValue();
                copyCharValue2SCharValue(value, dmValue);
                sCoProdCharValueList.add(value);
            }
        }
    }
    
    private void copyCharValue2SCharValue(SCoProdCharValue value,CoProdCharValue dmValue){
        value.setExpireDate(dmValue.getExpireDate());
        value.setGroupId(dmValue.getGroupId());
        value.setObjectId(dmValue.getObjectId());
        value.setObjectType(dmValue.getObjectType().shortValue());
        value.setProductId(dmValue.getProductId());
        value.setSpecCharId(dmValue.getSpecCharId());
        value.setValidDate(dmValue.getValidDate());
        value.setValue(dmValue.getValue());
    }

}
