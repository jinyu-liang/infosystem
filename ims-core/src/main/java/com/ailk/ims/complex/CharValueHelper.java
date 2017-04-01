package com.ailk.ims.complex;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.InnerClass.CharValueGroup;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;

/**
 * @author wangjt
 * @Date 2012-07-06 yangjh story：50195 budget信息入CoBudgetCharValue表
 *                                新增getBudgetCondForDBCondition
 */
public class CharValueHelper extends BaseComponent
{
    private Long productId;
    private Long groupId;
    private Date validDate;
    private Date expireDate;
    private Long objectId;
    private Integer objectType;

    public CharValueHelper(Long productId, Long groupId, Date validDate, Date expireDate,Long objectId,Integer objectType)
    {
        this.productId = productId;
        this.groupId = groupId;
        this.validDate = validDate;
        this.expireDate = expireDate;
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public CharValueHelper(Long productId, Long groupId, String validDate, String expireDate,Long objectId,Integer objectType)
    {
        this.productId = productId;
        this.groupId = groupId;
        this.validDate = DateUtil.getFormattedDate(validDate);
        this.expireDate = IMSUtil.formatExpireDate(expireDate);
        this.objectId = objectId;
        this.objectType = objectType;
    }

    /**
     * 获取新增对象
     * @param objectType 
     * @param objectId 
     */
    public CoProdCharValue getNew(Integer specCharId, Object value)
    {
    	CoProdCharValue newObj = new CoProdCharValue();
        newObj.setProductId(productId);
        newObj.setGroupId(groupId);
        if (validDate != null)
        {
            newObj.setValidDate(validDate);
        }
        if (expireDate != null)
        {
            newObj.setExpireDate(expireDate);
        }
        newObj.setObjectId(objectId);
        newObj.setObjectType(objectType);
      
        newObj.setSpecCharId(specCharId);
        newObj.setValue(format(value));
        return newObj;
    }
    
    /**
     * @Description: 获取查询对象
     * @param specCharId
     * @return   
     * @author: tangjl5
     * @Date: 2012-5-14
     */
    public DBCondition[] getCondForDBCondition(Integer specCharId)
    {
        List<DBCondition> con = new ArrayList<DBCondition>();
        con.add(new DBCondition(CoProdCharValue.Field.productId, productId));
        con.add(new DBCondition(CoProdCharValue.Field.groupId, groupId));
        con.add(new DBCondition(CoProdCharValue.Field.objectId, objectId));
        con.add(new DBCondition(CoProdCharValue.Field.objectType, objectType));
        con.add(new DBCondition(CoProdCharValue.Field.specCharId, specCharId));
        
        return con.toArray(new DBCondition[con.size()]);
    }
    
    /**
     * @Description: 获取查询对象
     * @param specCharId
     * @return   
     * @author: yangjh
     * @Date: 2012-7-6
     */
    public DBCondition[] getBudgetCondForDBCondition(Integer specCharId)
    {
        List<DBCondition> con = new ArrayList<DBCondition>();
        con.add(new DBCondition(CoBudgetCharValue.Field.productId, productId));
        con.add(new DBCondition(CoBudgetCharValue.Field.groupId, groupId));
        con.add(new DBCondition(CoBudgetCharValue.Field.objectId, objectId));
        con.add(new DBCondition(CoBudgetCharValue.Field.objectType, objectType));
        con.add(new DBCondition(CoBudgetCharValue.Field.specCharId, specCharId));
        
        return con.toArray(new DBCondition[con.size()]);
    }

    /**
     * 获取修改对象
     */
    public CoProdCharValue getUpdate(Object value)
    {
    	CoProdCharValue coProdCharValue = new CoProdCharValue();
        if (validDate != null)
        {
        	coProdCharValue.setValidDate(validDate);
        }
        if (expireDate != null)
        {
        	coProdCharValue.setExpireDate(expireDate);
        }

        coProdCharValue.setValue(format(value));
        return coProdCharValue;
    }

    /**
     * 获取查询对象
     * @param objectId 
     */
    public CoBudgetCharValue getCond(Integer specCharId)
    {
    	CoBudgetCharValue coBugdetCharValue = new CoBudgetCharValue();
    	coBugdetCharValue.setProductId(productId);
    	coBugdetCharValue.setGroupId(groupId);
    	coBugdetCharValue.setObjectId(objectId);
    	coBugdetCharValue.setObjectType(objectType);
        
    	coBugdetCharValue.setSpecCharId(specCharId);
        return coBugdetCharValue;
    }
    
    public static String format(Object value)
    {
        if (value == null || value.toString().trim().length() == 0)
        {
            // @Date 2012-3-28 tangjl5 BUG#42619没有值时设置为-999，避免“ ”导致计费业务分析失败
            return "-999";
        }
        if (value instanceof Double)// 去掉double的小数部分
        {
            return String.valueOf(((Double) value).longValue());
        }
        return value.toString().trim();
    }
    
    /**
     * 
     * yangjh 2012-9-20  根据groupId对charValue分组
     * @param charValueList
     * @return
     */
    public static List<CharValueGroup> groupCharValueLis(List<CoProdCharValue> charValueList)
    {
        List<CharValueGroup> groupList = new ArrayList<CharValueGroup>();
        List<CoProdCharValue> prodList;
        Map<Long ,List<CoProdCharValue>> map = new HashMap<Long ,List<CoProdCharValue>>();//<groupId,List<CoProdCharValue>>
        for(CoProdCharValue charValue : charValueList){
            prodList = map.get(charValue.getGroupId());
            if(CommonUtil.isNotEmpty(prodList)){
                prodList.add(charValue);
            }else{
                prodList = new ArrayList<CoProdCharValue>();
                prodList.add(charValue);
                map.put(charValue.getGroupId(), prodList);
            }
        }
        Iterator it = map.values().iterator();
        while(it.hasNext()){
            CharValueGroup group = new CharValueGroup();
            List<CoProdCharValue> charList = (List<CoProdCharValue>)it.next();
            Long groupId = charList.get(0).getGroupId();
            group.setGroupId(groupId);
            group.setCharValueList(charList);
            groupList.add(group);
        }
        return groupList;
    }
}
