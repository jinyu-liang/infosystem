package com.ailk.ims.business.orderrewardprod.busi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.orderproduct.busi.OrderProductBusiBean;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.ImsProdOrderSync;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SRewardOrderProduct;

/**
 * @Description: reward产品订购
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author user
 * @Date 2011-11-23
 * @Date 2012-05-22 lijc3传入指定时间生效
 * @Date 2012-05-29 zhangzj3 [42979]Reward From BOS Sync To CRM，信息存入Ims_Prod_Order_Sync
 * @date 2012-07-25 luojb #51386 增加count
 */
public class OrderRewardProdBusiBean extends BaseCancelableBusiBean
{

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
        SRewardOrderProduct req = (SRewardOrderProduct)input[0];
        imsLogger.dump("SRewardOrderProduct", req);
        short count = req.getCount();
        if(!CommonUtil.isValid(count))
            count = 1;
        if(ProjectUtil.is_CN_SH()){
        	Date validDate=null;
        	Date expireDate=null;
        	if (req.getValidDate() != 0)
        		validDate=DateUtil.getFormatDate(String.valueOf(req.getValidDate()));
        	else{
        		validDate=context.getRequestDate();
        	}
        	if (req.getExpireDate() != 0)
        		expireDate=DateUtil.getFormatDate(String.valueOf(req.getExpireDate()));
        	BaseProductComponent prodCmp=context.getComponent(BaseProductComponent.class);
        	if(req.getObjectType()==EnumCodeDefine.PROD_OBJECTTYPE_DEV){
                IMSUtil.setUserRouterParam(Long.valueOf(req.getObjectId()));
            }else{
                IMSUtil.setAcctRouterParam(Long.valueOf(req.getObjectId()));
            }
            CoProd mainProd = prodCmp.queryPrimaryProductByUserId((long) req.getObjectId());
            Integer maniOfferId = null;
            if (mainProd != null)
            {
                maniOfferId = mainProd.getProductOfferingId();
            }
            Integer pricePlanId = prodCmp.queryPricePlanId(req.getOfferingId(), null, null, null, null, null, null, maniOfferId,
                    null, null);
        	//2012-07-25 luojb #51386 增加count
        	List<CoProd> productList = new ArrayList<CoProd>();
            for (int i = 0; i < count; i++)
            {
                // productList.add(prodCmp.createImsOrderProduct(req.getOfferingId(), (int)req.getObjectType(),
                // (long)req.getObjectId(), validDate, expireDate));
                productList.add(createCoProd4Sh(req.getOfferingId(), (long) req.getObjectId(), (int) req.getObjectType(),
                        validDate, expireDate, pricePlanId));
            }
        	prodCmp.insertBatch(productList);
        	return null;
        }else{
        	OrderProductBusiBean busi = new OrderProductBusiBean();
        	SProductOrderList orderList = new SProductOrderList();
        	// 2012-07-25 luojb #51386 增加count
        	SProductOrder[] prodOrderArr = new SProductOrder[count];
        	for(int i = 0;i < count; i++){
        	    SProductOrder prodOrder = new SProductOrder();
        	    prodOrderArr[i] = prodOrder;
            	if (req.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV){
            		prodOrder.setUser_id((long)req.getObjectId());
            	}else{
            		prodOrder.setAcct_id((long)req.getObjectId());
            	}        	
            	if (req.getValidDate() != 0){
            	    prodOrder.setValid_date(String.valueOf(req.getValidDate()));
            	    //@Date 2012-05-22 lijc3传入指定时间生效
            	    prodOrder.setValid_type((short)EnumCodeDefine.PROD_VALID_SPECIFIC_DATE);
            	}else{
            		prodOrder.setValid_date(DateUtil.formatDate(context.getRequestDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            	}
            	if (req.getExpireDate() != 0){
            		prodOrder.setExpire_date(String.valueOf(req.getExpireDate()));
            	}
            	prodOrder.setOffer_id((long)req.getOfferingId());
        	}
        	orderList.setItem(prodOrderArr);
        	Object[] inputObject = new Object[]{orderList};
        	busi.setContext(context);
        	busi.doBusiness(inputObject);
        	
        	//2012-05-29 zhangzj3 [42979]Reward From BOS Sync To CRM
            IMS3hBean bean = null;
            Long objectId = req.getObjectId();
            if (req.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV){
            	bean = context.get3hTree().load3hBean(null, null,objectId.longValue(), null);
            }else{
            	bean = context.get3hTree().load3hBean(null, objectId.longValue(), null, null);
            }
            ImsProdOrderSync imsProdOrderSync = context.getComponent(BaseProductComponent.class).getImsProdOrderSync(bean, EnumCodeDefine.SRC_ACTION_RATEING_REWARD_PRODUCT);
        	if(imsProdOrderSync != null){
        		context.getComponent(BaseComponent.class).insert(imsProdOrderSync);
        	}
        	return input;
        }
    }
    
    private CoProd createCoProd4Sh(int offerId, long objectId, int objectType, Date validDate, Date expireDate,
            Integer pricePlanId)
    {
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean((long) offerId);
        CoProd prod = new CoProd();
        prod.setBillingType(offerBean.getBillingType());
        prod.setBusiFlag(offerBean.getBusiFlag());
        prod.setCreateDate(context.getRequestDate());
        prod.setExpireDate(expireDate);
        prod.setIsMain(offerBean.getOffering().getIsMain());
        prod.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
        prod.setObjectId(objectId);
        prod.setObjectType(objectType);
        prod.setProdExpireDate(expireDate);
        prod.setProdTypeId(offerBean.getOffering().getOfferTypeId());
        prod.setProductId(DBUtil.getSequence(CoProd.class));
        prod.setProdValidDate(validDate);
        prod.setProductOfferingId(offerId);
        prod.setValidDate(validDate);
        prod.setProdValidDate(validDate);
        prod.setSoNbr(context.getSoNbr());
        prod.setSoDate(context.getRequestDate());
        prod.setPricingPlanId(pricePlanId == null ? 0 : pricePlanId);
        return prod;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_sdlResponse();
    }

    @Override
    public void destroy()
    {

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)throws BaseException {
		SRewardOrderProduct req = (SRewardOrderProduct)input[0];
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
		if(req.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
			list.add(context.get3hTree().loadUser3hBean((long)req.getObjectId()));
		else if(req.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
			list.add(context.get3hTree().loadAcct3hBean((long)req.getObjectId()));
		return list;
		
//		SRewardOrderProduct req = (SRewardOrderProduct)input[0];
//		List<IMS3hBean> ims3h = new ArrayList<IMS3hBean>();
//		if(req.get_objectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
//		{
//			Long userId = CommonUtil.IntegerToLong(req.get_objectId());
//			if (CommonUtil.isValid(userId))
//			{
//				ims3h.add(context.get3hTree().loadUser3hBean(userId));
//			}
//		}else {
//			Long acctId = CommonUtil.IntegerToLong(req.get_objectId());
//			if(CommonUtil.isValid(acctId))
//			{
//				ims3h.add(context.get3hTree().loadAcct3hBean(acctId));
//			}
//		}
//		return ims3h;
	}

}
