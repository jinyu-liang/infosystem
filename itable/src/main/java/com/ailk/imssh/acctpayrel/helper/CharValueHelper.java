package com.ailk.imssh.acctpayrel.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;

/**
 * @Description 特征值处理类，包括CO_PROD_CHAR_VALUE,CO_SPLIT_CHAR_VALUE,CO_FN_CHAR_VALUE表可以使用，其他都不可以使用
 * @author lijc3
 * @Date 2012-6-11
 */
public class CharValueHelper
{
    public static final String EXPIRE_DATE = "expireDate";
    public static final String PRODUCT_ID = "productId";
    public static final String VALID_DATE = "validDate";
    public static final String GROUP_ID = "groupId";
    public static final String OBJECT_ID = "objectId";
    public static final String OBJECT_TYPE = "objectType";
    public static final String SPEC_CHAR_ID = "specCharId";
    public static final String VALUE = "value";
    private Long productId;
    private Long groupId;
    private Date validDate;
    private Date expireDate;
    private Long objectId;
    private Integer objectType;
    private Class<DataObject> clazz;

    /**
     * @param clazz CO_PROD_CHAR_VALUE,CO_SPLIT_CHAR_VALUE,CO_FN_CHAR_VALUE只允许其中之一
     * @param productId 产品id
     * @param groupId group_id
     * @param validDate 生效时间
     * @param expireDate 失效时间
     * @param objectId
     * @param objectType
     */
    public CharValueHelper(Class clazz, Long productId, Long groupId, Date validDate, Date expireDate, Long objectId,
            Integer objectType)
    {
        this.productId = productId;
        this.groupId = groupId;
        this.validDate = validDate;
        this.expireDate = expireDate;
        this.objectId = objectId;
        this.objectType = objectType;
        this.clazz = clazz;
    }

    /**
     * @param clazz CO_PROD_CHAR_VALUE,CO_SPLIT_CHAR_VALUE,CO_FN_CHAR_VALUE只允许其中之一
     * @param productId 产品id
     * @param groupId group_id
     * @param validDate 生效时间
     * @param expireDate 失效时间
     * @param objectId
     * @param objectType
     */
    public CharValueHelper(Class clazz, Long productId, Long groupId, String validDate, String expireDate, Long objectId,
            Integer objectType)
    {
        this.productId = productId;
        this.groupId = groupId;
        this.validDate = DateUtil.getFormattedDate(validDate);
        this.expireDate = IMSUtil.formatExpireDate(expireDate);
        this.objectId = objectId;
        this.objectType = objectType;
        this.clazz = clazz;
    }

    /**
     * 获取新增对象
     * 
     * @param objectType
     * @param objectId
     */
    public <T extends DataObject> T getNew(Integer specCharId, Object value)
    {
        try
        {
            DataObject newObj = clazz.newInstance();
            if (expireDate != null)
            {
                ClassUtil.setFieldValue(newObj, EXPIRE_DATE, expireDate);
            }
            if (validDate != null)
            {
                ClassUtil.setFieldValue(newObj, VALID_DATE, validDate);
            }
            ClassUtil.setFieldValue(newObj, GROUP_ID, groupId);
            ClassUtil.setFieldValue(newObj, PRODUCT_ID, productId);
            ClassUtil.setFieldValue(newObj, OBJECT_ID, objectId);
            ClassUtil.setFieldValue(newObj, OBJECT_TYPE, objectType);
            ClassUtil.setFieldValue(newObj, SPEC_CHAR_ID, specCharId);
            ClassUtil.setFieldValue(newObj, VALUE, format(value));
            return (T) newObj;
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
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
     * 获取修改对象
     */
    public <T extends DataObject> T getUpdate(Object value)
    {
        try
        {
            DataObject newObj = clazz.newInstance();
            if (expireDate != null)
            {
                ClassUtil.setFieldValue(newObj, EXPIRE_DATE, expireDate);
            }
            if (validDate != null)
            {
                ClassUtil.setFieldValue(newObj, VALID_DATE, validDate);
            }

            ClassUtil.setFieldValue(newObj, VALUE, format(value));
            return (T) newObj;
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }

    }

    /**
     * 获取查询对象
     * 
     * @param objectId
     */
    public <T extends DataObject> T getCond(Integer specCharId)
    {
        try
        {
            DataObject newObj = clazz.newInstance();
            ClassUtil.setFieldValue(newObj, GROUP_ID, groupId);
            ClassUtil.setFieldValue(newObj, PRODUCT_ID, productId);
            ClassUtil.setFieldValue(newObj, OBJECT_ID, objectId);
            ClassUtil.setFieldValue(newObj, OBJECT_TYPE, objectType);
            ClassUtil.setFieldValue(newObj, SPEC_CHAR_ID, specCharId);
            return (T) newObj;
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
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
}
