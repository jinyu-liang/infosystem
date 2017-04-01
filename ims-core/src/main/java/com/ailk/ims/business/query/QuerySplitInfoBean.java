package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.CheckComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.SplitComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.imsinner.entity.Do_querySplitListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQuerySplitListReq;
import com.ailk.openbilling.persistence.imsinner.entity.SplitList;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;


/**
 * @Description 根据账户ID查询被分账信息（ 账户ID对应pay_acct_id,这个id为谁分账（split）的信息（自己为别人的分账信息））
 * @author yangjh
 * @Date 2011-11-18
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class QuerySplitInfoBean extends BaseBusiBean
{

    private Long acctId = null;
    // private String valid_date = null;
    // private String expire_date = null;
    private int splitamount;
    private BaseComponent baseCmp = null;
    private BaseProductComponent prodCmp = null;
    private CheckComponent checkCmp = null;
    private SplitComponent splCmp=null;

    // private AccountComponent acctCmp = null;

    @Override
    public void init(Object... input) throws IMSException
    {
        // valid_date = req.getValid_date();
        // expire_date = req.getExpire_date();
        splitamount = -1;
        prodCmp = context.getComponent(BaseProductComponent.class);
        baseCmp = context.getComponent(BaseComponent.class);
        checkCmp = context.getComponent(CheckComponent.class);
        splCmp=context.getComponent(SplitComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQuerySplitListReq req = (SQuerySplitListReq) input[0];
        acctId = checkCmp.checkAcctId(req.getAcct_id()).getAcctId();
        if (!CommonUtil.isValid(acctId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<Long> prodIds = prodCmp.queryProdIdsByAcctId(acctId);
        if(CommonUtil.isEmpty(prodIds)){
        	return null;
        }
        List<CoProd> prodList = prodCmp.query(CoProd.class , 
        		new DBCondition(CoProd.Field.productId, prodIds, Operator.IN),
        		new DBCondition(CoProd.Field.objectId, acctId),
        		new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.SPLIT));
        if(CommonUtil.isEmpty(prodList)){
        	return null;
        }	
        prodList = prodCmp.mergeProdList(prodList);
        
        List<SplitList> split = new ArrayList<SplitList>();
        List<Long> prodsbyacct = queryAcctSplit(acctId);
        List<Long> prodsbyuser = queryUserSplit(acctId);
        for (CoProd prod : prodList)
        {
            if (prod == null)
                continue;
            if (CommonUtil.isEmpty(prodsbyacct) && CommonUtil.isEmpty(prodsbyuser))
                return null;
            // 查询被分账的账户的分账信息
            if (!CommonUtil.isEmpty(prodsbyacct))
            {
                for (int i = 0; i < prodsbyacct.size(); i++)
                {
                    if (prod.getProductId().equals(prodsbyacct.get(i)))
                    {
                        SplitList sp = new SplitList();
                        sp.setPay_acct_id(acctId);
//                        List<CoProdCharValue> charValues = prodCmp.queryProdCharValue(prod.getProductId(),acctId);
                        List<CoSplitCharValue> charValues=splCmp.querySplitProdCharValue(prod.getProductId(), acctId);
                        if (!CommonUtil.isEmpty(charValues))
                        {
                            Map maps = new HashMap<Long, Long>();
                            // 遍历CoSplitCharValue表 得到需要的SpecCharValue跟value值
                            for (CoSplitCharValue charValue : charValues)
                            {
                                if (charValue.getExpireDate() != null)
                                    sp.setExpire_date(DateUtil.getFormatDate(charValue.getExpireDate(),
                                            DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                                if (charValue.getValidDate() != null)
                                    sp.setValid_date(DateUtil.getFormatDate(charValue.getValidDate(),
                                            DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRODUCT_ID)
                                    sp.setProduct_id(CommonUtil.string2Long(charValue.getValue()));
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRIORITY)
                                    sp.setPriority(CommonUtil.string2Short(charValue.getValue()));
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_OBJECT_ID)
                                    sp.setAcct_id(CommonUtil.string2Long(charValue.getValue()));
                                // 将12802,12803及对应的value存到map中便于下面的part_type判断跟取值
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_AMOUNT)
                                {
                                    maps.put(charValue.getSpecCharId(), charValue.getValue());
                                }
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_NUMERATOR)
                                {
                                    maps.put(charValue.getSpecCharId(), charValue.getValue());
                                }
                            }
                            // 根据12802的值来判断part_type 如果为-1 则part_type=0，part_value取12803的值，
                            // 12802的值不为-1，则part_type=0，part_value取12802的值
                            Long value = CommonUtil.string2Long((String) maps.get(SpecCodeDefine.SPLIT_AMOUNT));
                            if (value.equals(CommonUtil.int2Long(splitamount)))
                            {
                                sp.setPart_value(CommonUtil.string2Long((String) maps.get(SpecCodeDefine.SPLIT_NUMERATOR)));
                                sp.setPart_type((long) EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE);
                            }
                            else
                            {
                                sp.setPart_value(value);
                                sp.setPart_type((long) EnumCodeDefine.PAY_PART_TYPE_AMOUNT);
                            }
                        }
                        split.add(sp);
                    }
                }
            }
            // 根据被分账的用户的分账信息
            if (!CommonUtil.isEmpty(prodsbyuser))
            {
                for (int i = 0; i < prodsbyuser.size(); i++)
                {
                    if (prod.getProductId().equals(prodsbyuser.get(i)))
                    {
                        SplitList sp = new SplitList();
                        sp.setPay_acct_id(acctId);
//                        List<CoProdCharValue> charValues = prodCmp.queryProdCharValue(prod.getProductId());
                        List<CoSplitCharValue> charValues=splCmp.querySplitProdCharValue(prod.getProductId());
                        if (!CommonUtil.isEmpty(charValues))
                        {
                            Map maps = new HashMap<Long, Long>();
                            // 遍历CoSplitCharValue表 得到需要的SpecCharValue跟value值
                            for (CoSplitCharValue charValue : charValues)
                            {
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRIORITY)
                                    sp.setPriority(CommonUtil.string2Short(charValue.getValue()));
                                if (charValue.getExpireDate() != null)
                                    sp.setExpire_date(DateUtil.getFormatDate(charValue.getExpireDate(),
                                            DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                                if (charValue.getValidDate() != null)
                                    sp.setValid_date(DateUtil.getFormatDate(charValue.getValidDate(),
                                            DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRODUCT_ID)
                                    sp.setProduct_id(CommonUtil.string2Long(charValue.getValue()));
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRIORITY)
                                    sp.setPriority(CommonUtil.string2Short(charValue.getValue()));
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_OBJECT_ID)
                                    sp.setUser_id(CommonUtil.string2Long(charValue.getValue()));
                                // 将12802,12803及对应的value存到map中便于下面的part_type判断跟取值
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_AMOUNT)
                                {
                                    maps.put(charValue.getSpecCharId(), charValue.getValue());
                                }
                                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_NUMERATOR)
                                {
                                    maps.put(charValue.getSpecCharId(), charValue.getValue());
                                }
                            }
                            // 根据12802的值来判断part_type 如果为-1 则part_type=0，part_value取12803的值，
                            // 12802的值不为-1，则part_type=1，part_value取12802的值
                            Long value = CommonUtil.string2Long((String) maps.get(SpecCodeDefine.SPLIT_AMOUNT));
                            if (value.equals(CommonUtil.int2Long(splitamount)))
                            {
                                sp.setPart_value(CommonUtil.string2Long((String) maps.get(SpecCodeDefine.SPLIT_NUMERATOR)));
                                sp.setPart_type((long) EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE);
                            }
                            else
                            {
                                sp.setPart_value(value);
                                sp.setPart_type((long) EnumCodeDefine.PAY_PART_TYPE_AMOUNT);
                            }
                        }
                        split.add(sp);
                    }
                }
            }
        }
        return new Object[] { split };
    }

    /**
     * 根据账户查询账户的分账产品
     * 
     * @author yangjh 2011-11-18
     * @param payAcctId
     * @return
     */
    public List<Long> queryAcctSplit(Long payAcctId)
    {
        return querySplit(acctId, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
    }

    /**
     * 根据账户查询用户的分账产品
     * 
     * @author yangjh 2011-11-18
     * @param payAcctId
     * @return
     * @throws IMSException 
     */
    public List<Long> queryUserSplit(Long payAcctId) throws IMSException
    {
        return querySplit(acctId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
    }

    private List<Long> querySplit(Long payAcctId, int objectType) throws IMSException
    {
        
        
        //查询符合条件的支付关系    ljc 修改
        List<CoProd> prod_list = prodCmp.queryProdListByAcctId(payAcctId);
        if(CommonUtil.isEmpty(prod_list))
        	return null;
        
        //查询符合条件的特征值
        List<CoSplitCharValue> charList = baseCmp.query(
        		CoSplitCharValue.class,
        		new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_TYPE),
        		new DBCondition(CoSplitCharValue.Field.value, objectType)
        );
        
        //取两者交集，过滤出符合的productID
        List<Long> prodIdList = new ArrayList<Long>();
        for(CoProd prod : prod_list){
        	Long productId = prod.getProductId();
			try {
				List<CoSplitCharValue> matchData = IMSUtil.matchDataObject(charList, new CacheCondition(CoSplitCharValue.Field.productId,productId));
				if(CommonUtil.isNotEmpty(matchData)){
					prodIdList.add(productId);
				}
			} catch (Exception e) {
				IMSUtil.throwBusiException(e);
			}
				
        }
        
//        List<CoProd> prodList = baseCmp.query(
//        		CoProd.class,
//        		new DBCondition(CoProd.Field.productId, prodIdList,Operator.IN),
//        		new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.SPLIT)
//        );
//        prodList = prodCmp.mergeProdList(prodList);
       
        /*charCond = new DBExistsCond(CoProdCharValue.Field.productId, CoProd.Field.productId);
        charCond.addCondition(new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_TYPE));
        charCond.addCondition(new DBCondition(CoProdCharValue.Field.value, objectType));
        
        DBExistsCond invObjCond = new DBExistsCond(CoProdInvObj.Field.productId, CoProd.Field.productId);
        invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
        invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.objectId, payAcctId));
        
        List<DBCondition> mainConds = new ArrayList<DBCondition>();
        mainConds.add(new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.SPLIT));

        List<CoProd> prodList = baseCmp.queryExists(mainConds, invObjCond, charCond);*/
//        List<Long> productIds = new ArrayList<Long>();
//        if (!CommonUtil.isEmpty(prodList))
//        {
//            for (int i = 0; i < prodList.size(); i++)
//            {
//                productIds.add(prodList.get(i).getProductId());
//            }
//            return productIds;
//        }
        return prodIdList;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_querySplitListResponse resp = new Do_querySplitListResponse();
        if (result == null)
        {
            return resp;
        }
        List<SplitList> split = (List<SplitList>) result[0];
        resp.setSplitList_Item(split.toArray(new SplitList[split.size()]));
        return resp;
    }

    @Override
    public void destroy()
    {

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
	    //增加获取3HBean方法 yanchuan 2012-05-15
        List<IMS3hBean> beans = new ArrayList<IMS3hBean>();
        beans.add(context.get3hTree().loadAcct3hBean(acctId));
        
		return beans;
	}

}
