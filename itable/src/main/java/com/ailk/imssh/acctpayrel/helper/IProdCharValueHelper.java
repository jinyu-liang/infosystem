package com.ailk.imssh.acctpayrel.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.net.aso.p;
import jef.database.DataObject;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IProdCharValue;

/**
 * @Description 特征值处理类，包括CO_PROD_CHAR_VALUE,CO_SPLIT_CHAR_VALUE,CO_FN_CHAR_VALUE表可以使用，其他都不可以使用
 * @author lijc3
 * @Date 2012-6-11
 */
public class IProdCharValueHelper
{
    public static final String EXPIRE_DATE = "expireDate";
    public static final String PRODUCT_ID = "productId";
    public static final String VALID_DATE = "validDate";
    public static final String USER_ID = "userId";
    public static final String ACCT_ID = "acctId";
    public static final String PARAM_VALUE = "paramValue";
    public static final String PARAM_ID = "paramId";
    public static final String OPER_TYPE = "operType";
    public static final String SO_NBR = "soNbr";
    public static final String COMMIT_DATE = "commitDate";
    private Long userId;
    private Long acctId;
    private Long productId;
    private Date validDate;
    private Date expireDate;
    private Date commitDate;
    private Integer paramId;
    private Integer operType;
    private Long soNbr;
    private Class<DataObject> clazz;

    /**
     * 
     * @param clazz
     * @param productId
     * @param userId
     * @param acctId
     * @param validDate 生效时间
     * @param expireDate 失效时间
     * @param commitDate
     * @param soNbr
     * @param paramId
     * @param operType
     */
    public IProdCharValueHelper(Class clazz, Long productId, Long userId, Long acctId, Date validDate, Date expireDate, Date commitDate, Long soNbr,
            Integer paramId, Integer operType )
    {
        this.productId = productId;
        this.userId = userId;
        this.acctId = acctId;
        this.validDate = validDate;
        this.expireDate = expireDate;
        this.commitDate = commitDate;
        this.soNbr = soNbr;
        this.paramId = paramId;
        this.operType = operType;
        this.clazz = clazz;
    }


    /**
     * 获取新增对象
     * 
     * @param objectType
     * @param objectId
     */
    public IProdCharValue getNew(String paramValue, IProdCharValue value)
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
            ClassUtil.setFieldValue(newObj, USER_ID, userId);
            ClassUtil.setFieldValue(newObj, PRODUCT_ID, productId);
            ClassUtil.setFieldValue(newObj, ACCT_ID, acctId);
            ClassUtil.setFieldValue(newObj, COMMIT_DATE, commitDate);
            ClassUtil.setFieldValue(newObj, PARAM_ID, paramId);
            ClassUtil.setFieldValue(newObj, PARAM_VALUE, format(paramValue));
            ClassUtil.setFieldValue(newObj, SO_NBR, soNbr);
            ClassUtil.setFieldValue(newObj, OPER_TYPE, operType);
            return (IProdCharValue) newObj;
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
