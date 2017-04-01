package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.imssdl.entity.Split;

/**
 * 分账组件
 * @author zenglu
 * @date 2012-07-30
 */

public class SplitShComponent extends BaseComponent 
{
	public SplitShComponent()
	{
		
	}

	/**
     * 根据分账产品查询分账信息
     * 
     * @author zenglu
     * @param prods,objectId, objectType
     * @return Split
     * @date 2012-08-31
     */
    public List<Split> getSplitInfos(List<CoSplitPayRel> relList)
    {
		SplitComponent SplitCmp = context.getComponent(SplitComponent.class);
		List<Split> splitList = new ArrayList<Split>();
		for (CoSplitPayRel rel : relList)
        {
	        Split sp = new Split();
	        if(rel.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT){
	        	sp.setAcct_id(rel.getObjectId());
	        }else if(rel.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV){
	        	sp.setUser_id(rel.getObjectId());
	        }
	        sp.setExpire_date(DateUtil.getFormatDate(rel.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
	        sp.setValid_date(DateUtil.getFormatDate(rel.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
	        sp.setPay_acct_id(rel.getReguidObjectId());
	        
	        //对用户、账户id进行分表缓存  
            if(rel.getReguidObjectType()==EnumCodeDefine.PROD_OBJECTTYPE_DEV){
                IMSUtil.setUserRouterParam(rel.getReguidObjectId());
            }else{
                IMSUtil.setAcctRouterParam(rel.getReguidObjectId());
            }
	        
	        List<CoSplitCharValue> charValues = SplitCmp.querySplitProdCharValue(rel.getProductId());
	        if (!CommonUtil.isEmpty(charValues))
	        {
	            for (CoSplitCharValue charValue : charValues)
	            {
	                if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRODUCT_ID)
	                {
	                    sp.setProduct_id(CommonUtil.string2Long(charValue.getValue()));
	                }else if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRIORITY)
	                {
	                    sp.setPriority(CommonUtil.string2Short(charValue.getValue()));
	                }else if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_AMOUNT)
	                {
	                	sp.setPart_value(CommonUtil.string2Long(charValue.getValue()));
	                }else if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_NUMERATOR)
	                {
	                	sp.setSplit_numerator(CommonUtil.string2Long(charValue.getValue()));
	                }else if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_DENOMINATOR)
	                {
	                	sp.setSplit_denominator(CommonUtil.string2Long(charValue.getValue()));
	                }else if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRICE_RULE_ID)
	                {
	                	sp.setPrice_rule_id(CommonUtil.string2Long(charValue.getValue()));
	                }
	            }
	            
	            //如果是科目级分账时，需要返回科目规则Id
	            if(CommonUtil.isValid(sp.getPrice_rule_id()))
	            {
	            	sp.setSplit_type(CommonUtil.int2Short(EnumCodeDefine.SPLIT_TYPE_ITEM));
	            }
	            else
	            {
	            	//产品级分账时，product_sequence_id这里不做返回处理
	            	sp.setSplit_type(CommonUtil.int2Short(EnumCodeDefine.SPLIT_TYPE_PRODUCT));
	            }
	        }
	        splitList.add(sp);
        }
    return splitList;
    }
    
    /**
     * 根据objectId、object_type、时间点date
     * 查询分账关系
     * @author zenglu 
     * @return CoSplitPayRel  分账关系表
     * @date 2012-08-31
     */
    public List<CoSplitPayRel> querySplitPayRel(Long objectId, Long objectType, Date date)
    {
    	return  query(CoSplitPayRel.class, 
    			new DBCondition(CoSplitPayRel.Field.objectId, objectId), 
    			new DBCondition(CoSplitPayRel.Field.objectType, objectType),
    			new DBCondition(CoSplitPayRel.Field.expireDate, date, Operator.GREAT),
    			new DBCondition(CoSplitPayRel.Field.validDate, date, Operator.LESS_EQUALS));
    	
    }
	

}
