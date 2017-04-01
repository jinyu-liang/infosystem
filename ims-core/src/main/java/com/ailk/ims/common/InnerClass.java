package com.ailk.ims.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.ims.prod.ProductHolder;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;

/**
 * 定义一些简单的内部变量
 * @Description
 * @author wuyj
 * @Date 2012-7-18
 */
public class InnerClass
{
    /**
     * 产品使用对象
     * @Description
     * @author wuyj
     * @Date 2012-7-18
     */
    public static class ProdInvObj{
        private Long objId;
        private int objType;
        
        public ProdInvObj(Long objId,int objType){
            this.setObjId(objId);
            this.setObjType(objType);
        }

        public Long getObjId()
        {
            return objId;
        }

        public void setObjId(Long objId)
        {
            this.objId = objId;
        }

        public int getObjType()
        {
            return objType;
        }

        public void setObjType(int objType)
        {
            this.objType = objType;
        }
    }
    
    public static class MainProduct{
        private short type;
        private ProductHolder prodHolder;
        
        public ProductHolder getProdHolder()
        {
            return prodHolder;
        }
        public void setProdHolder(ProductHolder prodHolder)
        {
            this.prodHolder = prodHolder;
        }
        public short getType()
        {
            return type;
        }
        public void setType(short type)
        {
            this.type = type;
        }
    }
    
    public static class OverrideOffer{
        private Integer overrideOffer1;
        private Integer overrideOffer2;
        
        public OverrideOffer(Integer overrideOffer1,Integer overrideOffer2){
            this.overrideOffer1 = overrideOffer1;
            this.overrideOffer2 = overrideOffer2;
        }

        public boolean isMatch(Integer offerId)
        {
            return offerId != null &&(offerId.intValue() == overrideOffer1 || offerId.intValue() == overrideOffer2);
        }
        
        public Integer getOverrideOfferId(Integer offerId)
        {
            if(!isMatch(offerId))
                return null;
            if(overrideOffer1.intValue() == offerId)
                return overrideOffer2;
            else
                return overrideOffer1;
        }
    }
    /**
     * @Description: [56230]writeCap优化
     * @author : linzt
     * @date : 2012-08-16
     * @return
     */
    public static class WriteCapInfo
    {
        private String uri;
        private Map<String,String> map=new HashMap<String,String>();
        public String getUri()
        {
            return uri;
        }
        public void setUri(String uri)
        {
            this.uri = uri;
        }
        public Map<String, String> getMap()
        {
            return map;
        }
        public void addMap(String key,String value)
        {
            map.put(key, value);
        }
        
    }
    
    public static class EventRewardInfo
    {
        private Long cust_id;
        private Long acct_id;
        private Long user_id;
        private Integer billing_type;
        public Long getCust_id()
        {
            return cust_id;
        }
        public void setCust_id(Long cust_id)
        {
            this.cust_id = cust_id;
        }
        public Long getAcct_id()
        {
            return acct_id;
        }
        public void setAcct_id(Long acct_id)
        {
            this.acct_id = acct_id;
        }
        public Long getUser_id()
        {
            return user_id;
        }
        public void setUser_id(Long user_id)
        {
            this.user_id = user_id;
        }
        public Integer getBilling_type()
        {
            return billing_type;
        }
        public void setBilling_type(Integer billing_type)
        {
            this.billing_type = billing_type;
        }
    }
    
    public static class ProdCycleInfo
    {
        private int betweenUnit; // 相差周期数
        private Date lastDeductDate; // 最后扣费日
        public int getBetweenUnit()
        {
            return betweenUnit;
        }
        public void setBetweenUnit(int betweenUnit)
        {
            this.betweenUnit = betweenUnit;
        }
        public Date getLastDeductDate()
        {
            return lastDeductDate;
        }
        public void setLastDeductDate(Date lastDeductDate)
        {
            this.lastDeductDate = lastDeductDate;
        }
        
    }
    /**
     * @Description: groupId分组使用类
     * @author : yangjh
     * @date : 2012-09-20
     * @return
     */
    public static class CharValueGroup{
        private Long groupId;
        private List<CoProdCharValue> charValueList;
        public Long getGroupId()
        {
            return groupId;
        }
        public void setGroupId(Long groupId)
        {
            this.groupId = groupId;
        }
        public List<CoProdCharValue> getCharValueList()
        {
            return charValueList;
        }
        public void setCharValueList(List<CoProdCharValue> charValueList)
        {
            this.charValueList = charValueList;
        }
    }
    //@Date 2012-10-11 yugb :Bug #60997 定购产品和设置二次议价
    public static class MeasureInfo
    {
    	private Double amount;
    	private Integer measureId;
    	
    	public MeasureInfo(Double amount,Integer measureId)
    	{
    		this.amount=amount;
    		this.measureId=measureId;
    	}
    	
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public Integer getMeasureId() {
			return measureId;
		}
		public void setMeasureId(Integer measureId) {
			this.measureId = measureId;
		}
    	
    }
    
}
