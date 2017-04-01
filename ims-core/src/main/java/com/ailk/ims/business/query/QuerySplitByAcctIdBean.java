package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AmountComponent;
import com.ailk.ims.component.SplitComponent;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.imsinner.entity.Do_querySplitListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQuerySplitListReq;
import com.ailk.openbilling.persistence.imsinner.entity.SplitList;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;


/**
 * @Description 根据 被分账的 账户Id (对应coProdCharValue的特征值对应的value)，查询出分账信息列表 这个id被谁分账（split）的信息（别人为自己分账的信息）
 * @author yangjh
 * @Date 2011-11-18
 * @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换为ims侧显示的单位
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class QuerySplitByAcctIdBean extends BaseBusiBean
{
    private Long acctId = null;
    private int splitamount = -1;
    private SplitComponent splitCmp = null;

    @Override
    public void init(Object... input) throws IMSException
    {
        SQuerySplitListReq req = (SQuerySplitListReq) input[0];
        acctId = req.getAcct_id();
        splitCmp = context.getComponent(SplitComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        List<SplitList> split = new ArrayList<SplitList>();
        Long objectId = acctId;
        Long objectType = new Long(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        IMSUtil.setAcctRouterParam(acctId);
        //查询账户的分账信息
        List<CoSplitPayRel> relList = splitCmp.querySplitPayRel(objectId, objectType);
        if (CommonUtil.isNotEmpty(relList))
        {
        	split = getSplitInfos(relList);
        }

        return new Object[] { split };
    }

    /**
     * 根据代付关系查询分账信息
     * @param CoSplitPayRel   分账关系
     * @author zenglu 2012-08-31
     */
    private List<SplitList> getSplitInfos(List<CoSplitPayRel> relList)
    {
        List<SplitList> split = new ArrayList<SplitList>();
        for (CoSplitPayRel rel : relList)
        {
            // 设置要返回的分账信息
            SplitList sp = new SplitList();
            if(rel.getReguidObjectType()==EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT){
            	sp.setAcct_id(rel.getObjectId());
            }else{
            	sp.setUser_id(rel.getObjectId());
            }
            sp.setExpire_date(DateUtil.getFormatDate(rel.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            sp.setValid_date(DateUtil.getFormatDate(rel.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            sp.setPay_acct_id(rel.getReguidObjectId());
           
            IMSUtil.removeRouterParam();
            //对用户、账户id进行分表缓存  
            if(rel.getReguidObjectType()==EnumCodeDefine.PROD_OBJECTTYPE_DEV){
                IMSUtil.setUserRouterParam(rel.getReguidObjectId());
            }else{
                IMSUtil.setAcctRouterParam(rel.getReguidObjectId());
            }

            List<CoSplitCharValue> charValues=splitCmp.querySplitProdCharValue(rel.getProductId());
            if (!CommonUtil.isEmpty(charValues))
            {
                Map maps = new HashMap<Long, Long>();
                for (CoSplitCharValue charValue : charValues)
                {
                    if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRODUCT_ID)
                        sp.setProduct_id(CommonUtil.string2Long(charValue.getValue()));
                    if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRIORITY)
                        sp.setPriority(CommonUtil.string2Short(charValue.getValue()));
                    if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_AMOUNT)
                    {
                        maps.put(charValue.getSpecCharId(), charValue.getValue());
                    }
                    if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_NUMERATOR)
                    {
                        maps.put(charValue.getSpecCharId(), charValue.getValue());
                    }
                }
                // 根据12802的特征值来判断part_type， 如果为-1 则part_type=0，为按比例分账，则part_value取12803的值；
                //   若12802的特征值不为-1，则part_type=1，为按数量分账，则part_value取12802的值
                Long value = CommonUtil.string2Long((String) maps.get(SpecCodeDefine.SPLIT_AMOUNT));
                if (value.equals(CommonUtil.int2Long(splitamount)))
                {
                    sp.setPart_value(CommonUtil.string2Long((String) maps.get(SpecCodeDefine.SPLIT_NUMERATOR)));
                    sp.setPart_type((long) EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE);
                }
                else
                {
                    // @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换为ims侧显示的单位
                    AmountComponent amountCmp = context.getComponent(AmountComponent.class);
                    Integer orgMeasureId = context.getComponent(ProductQuery.class).querySplitMeasureId(rel.getProductId(),rel.getReguidObjectId());
                    sp.setPart_value(CommonUtil.double2Long(amountCmp.parseImsAmount(orgMeasureId, CommonUtil.long2Double(value))));
                    sp.setPart_type((long) EnumCodeDefine.PAY_PART_TYPE_AMOUNT);
                }
            }
            split.add(sp);
        }
        return split;
    }

//    /**
//     * 根据帐户查询其订购的分账产品
//     * 
//     * @author yangjh 2011-11-18
//     * @param acctId
//     * @return
//     */
//    private List<CoProd> queryProdsBySplit(Long acctId)
//    {
//        DBExistsCond charCond1 = null;
//        DBExistsCond charCond2 = null;
//        charCond1 = new DBExistsCond(CoProdCharValue.Field.productId, CoProd.Field.productId);
//        charCond1.addCondition(new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_TYPE));
//        charCond1.addCondition(new DBCondition(CoProdCharValue.Field.value, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));// ACCOUNT
//
//        charCond2 = new DBExistsCond(CoProdCharValue.Field.productId, CoProd.Field.productId);
//        charCond2.addCondition(new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_ID));
//        charCond2.addCondition(new DBCondition(CoProdCharValue.Field.value, acctId));// acctId
//
//        DBExistsCond invObjCond = null;
//        // if(expire_date == null && valid_date == null)
//        // {
//        invObjCond = new DBExistsCond(CoProdInvObj.Field.productId, CoProd.Field.productId);
//        invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
//        // }
//        // if(expire_date != null && valid_date != null)
//        // {
//        // invObjCond = new DBExistsCond(CoProdInvObj.Field.productId, CoProd.Field.productId);
//        // invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
//        // invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.validDate,DateUtil.getFormatDate(expire_date,
//        // DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS),Operator.LESS_EQUALS));
//        // invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.expireDate,DateUtil.getFormatDate(valid_date,
//        // DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS),Operator.GREAT_EQUALS));
//        // }
//        // if(valid_date != null && expire_date == null)
//        // {
//        // invObjCond = new DBExistsCond(CoProdInvObj.Field.productId, CoProd.Field.productId);
//        // invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
//        // invObjCond.addCondition(new
//        // DBCondition(CoProdInvObj.Field.expireDate,DateUtil.getFormatDate(valid_date,DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS),Operator.GREAT_EQUALS));
//        // }
//        // if(expire_date != null && valid_date == null )
//        // {
//        // invObjCond = new DBExistsCond(CoProdInvObj.Field.productId, CoProd.Field.productId);
//        // invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
//        // invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.validDate,DateUtil.getFormatDate(expire_date,
//        // DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS),Operator.LESS_EQUALS));
//        // }
//
//        List<DBCondition> mainConds = new ArrayList<DBCondition>();
//        mainConds.add(new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.SPLIT));
//
//        List<CoProd> prodList = baseCmp.queryExists(mainConds, invObjCond, charCond1, charCond2);
//        if (!CommonUtil.isEmpty(prodList))
//        {
//            return prodList;
//        }
//        return null;
//    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_querySplitListResponse resp = new Do_querySplitListResponse();
        List<SplitList> split = (List<SplitList>) result[0];
        if(CommonUtil.isNotEmpty(split)){
        	resp.setSplitList_Item(split.toArray(new SplitList[split.size()]));
        }
        return resp;
    }

    @Override
    public void destroy()
    {

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		return null;
	}

}
