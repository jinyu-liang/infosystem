package com.ailk.ims.business.query4gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.complex.UserComplex;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserPaymemtModeResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserPaymentModeReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SBusiService;
import com.ailk.openbilling.persistence.imsintf.entity.SBusiServiceList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;

/**
 * @Description: 查询用户支付模式
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author hunan
 * @Date 2011-12-5
 * @Date 2012-04-10 yangjh getTime_period->setTime_segment
 * @Date 2012-06-29 wangdw5:#49538 页面仅显示一条busiService
 * @Date 2012-08-15 wangdw5 : hybrid2前台不展示busiService
 */
public class QueryUserPaymemtModeBusiBean extends BaseBusiBean
{
    private SQueryUserPaymentModeReq req;
    private BaseProductComponent prodCmp;
    private Do_queryUserPaymemtModeResponse resp;
    private UserQuery userQuery ;

    @Override
    public void init(Object... input) throws IMSException
    {
        req = (SQueryUserPaymentModeReq) input[0];
        userQuery = context.getComponent(UserQuery.class);
        prodCmp = context.getComponent(BaseProductComponent.class);
        resp = new Do_queryUserPaymemtModeResponse();
        userQuery = context.getComponent(UserQuery.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        if (req == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SQueryUserPaymemtModeReq");
        }
        if (!CommonUtil.isValid(req.getPhone_id()) && !CommonUtil.isValid(req.getUser_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "phone_id and user_id");
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        // SChangePaymentModeReq resp = new SChangePaymentModeReq();
    	//把用户状态该为不需要验证的
       /* CheckedComplex checkComplex = checkCmp.checkUserIdPhoneId(req.getUser_id(), req.getPhone_id());
        resp.setPhone_id(checkComplex.getPhoneId());
        resp.setUser_id(checkComplex.getUserId());
        Integer payMode = checkComplex.getUser().getBillingType();
        resp.setOld_payment_mode(CommonUtil.IntegerToShort(payMode));
        List<CoProd> coProds = prodCmp.queryProdListByUserId(checkComplex.getUserId());
        
        if (CommonUtil.isEmpty(coProds))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_HAVE_MAINPROMOTION, checkComplex.getUserId());
        }*/
    	User3hBean userBean = context.get3hTree().loadUser3hBean(req.getUser_id(), req.getPhone_id());
        resp.setUser_id(userBean.getUserId());
        resp.setPhone_id(userBean.getPhoneId());
        Integer payMode = userBean.getUser().getBillingType();
        resp.setOld_payment_mode(CommonUtil.IntegerToShort(payMode));
        List<CoProd> coProds = prodCmp.queryProdListByUserId(userBean.getUserId());
        if (CommonUtil.isEmpty(coProds))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_HAVE_MAINPROMOTION, userBean.getUser());
        }
        
        List<SProductOrder> prods = new ArrayList<SProductOrder>();
        List<SBusiService> busiSers = new ArrayList<SBusiService>();
        for (CoProd coProd : coProds)
        {
            if (coProd == null)
            {
                continue;
            }
            if (coProd.getBusiFlag() == SpecCodeDefine.PAYMODE)
            {
                List<SBusiService> busiSer = this.BusiServiceTram(coProd);
                busiSers.addAll(busiSer);
            }
            else
            {
                SProductOrder prod = prodCmp.sProductOrderTransform(coProd);
                prods.add(prod);
            }
        }
        SBusiServiceList busiList = new SBusiServiceList();
        busiList.setBusiServiceList(busiSers.toArray(new SBusiService[busiSers.size()]));
        resp.setSBusiServiceList(busiList);
        SProductOrderList list = new SProductOrderList();
        list.setItem(prods.toArray(new SProductOrder[prods.size()]));
        resp.setSProductOrderList(list);
        return null;
    }

    /**
     * @Description: 如果有busi_flag = 116 的coProd ，则封装成busiDervice
     * @param coProd
     * @return
     * @Date 2012-06-29 wangdw5:#49538 页面仅显示一条busiService
     */
    private List<SBusiService> BusiServiceTram(CoProd coProd)
    {
    	List<SBusiService> busiSerList = new ArrayList<SBusiService>();
    	Map<Long,SBusiService> busiSerMap = new HashMap<Long,SBusiService>();
        List<CoProdCharValue> charValues = prodCmp.queryCharValue(coProd.getProductId(), null);
        if (CommonUtil.isEmpty(charValues))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PROD_HAS_NO_CHAR_VALUES, coProd.getProductId());
        }
        for (CoProdCharValue charValue : charValues)
        {
            //@Date 2012-08-15 wangdw5 : hybrid2前台不展示busiService
            if (charValue.getSpecCharId() == SpecCodeDefine.PAYMODE_HYBRID_RULE &&
                    charValue.getValue().equals(String.valueOf(EnumCodeDefine.PAYMODE_HYBRID_RULE_CREDIT_LIMIT)))
            {
                return busiSerList;
            }
        	SBusiService busiSer = busiSerMap.get(charValue.getGroupId());
        	if(busiSer != null)
        	{
        		setBuiServiceField(busiSer,charValue);
        	}
        	else
        	{
        		busiSer = new SBusiService();
        		busiSer.setCreate_date(DateUtil.formatDate(charValue.getCreateDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                busiSer.setValid_date(DateUtil.formatDate(charValue.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                busiSer.setExpire_date(DateUtil.formatDate(charValue.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        		setBuiServiceField(busiSer,charValue);
        		busiSerMap.put(charValue.getGroupId(), busiSer);
        	}
        }
        if(CommonUtil.isNotEmpty(busiSerMap))
        {
        	for (SBusiService busiSer : busiSerMap.values()) {
        		busiSerList.add(busiSer);
			}
        }

        return busiSerList;
    }
    
    private void setBuiServiceField(SBusiService busiSer,CoProdCharValue charValue)
    {
    	if (charValue.getSpecCharId() == SpecCodeDefine.PAYMODE_RATING_SENARIO)
		{
			busiSer.setService_id(CommonUtil.string2Integer(charValue.getValue()));
			
		}
		
		if (charValue.getSpecCharId() == SpecCodeDefine.PAYMODE_THRESHOLD)
		{
			busiSer.setThreshold(CommonUtil.string2Double(charValue.getValue()));
			
		}
		if (charValue.getSpecCharId() == SpecCodeDefine.PAYMODE_TIME_PERIOD)
		{
			//@Date 2012-04-10 yangjh getTime_period->setTime_segment
			busiSer.setTime_segment(CommonUtil.StringToShort(charValue.getValue()));
			
		}
		if (charValue.getSpecCharId() == SpecCodeDefine.PAYMODE_PAYMODE)
		{
			busiSer.setPayment_mode(CommonUtil.StringToShort(charValue.getValue()));
		}
	}
    
    @Override
    public BaseResponse createResponse(Object[] result)
    {

        // Do_queryUserPaymemtModeResponse resp = new Do_queryUserPaymemtModeResponse();
        return resp;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().loadUser3hBean(req.getUser_id(), req.getPhone_id()));
		return list;
	}

}
