package com.ailk.ims.component.helper;

import com.ailk.ims.define.ColCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;

/**
 * @Description:群组操作相关静态方法类
 * @author wangjt
 * @Date 2011-12-23
 * @Date 2012-04-10 zengxr isGroupProd add SpecCodeDefine.INTER_GROUP_PROMOTION
 * @Date 2012-06-06 wangdw5 : [47115]Community Promotion
 * @date 2012-07-27 luojb #53753 修改群产品的判断
 */
public class GroupHelper
{
    private GroupHelper()
    {
    }

    /**
     * 校验是否为群产品 luojb 2011-10-13(群独有的所有产品)
     * 
     */
    public static boolean isGroupProd(Integer busiFlag)
    {
        if (busiFlag == null)
        {
            return false;
        }
        return CommonUtil.isIn(busiFlag,ColCodeDefine.GROUP_PROD_BUSIFLAGS);
    }
    /**
     * 是否是群可以订购的产品（包括黑白名单，不包括群间产品--群间产品是在受理群间关系的接口操作的）
     * luojb 2012-7-27
     * @param busiFlag
     * @return
     */
    public static boolean isGroupCanOrderProd(Integer busiFlag)
    {
        if (busiFlag == null)
        {
            return false;
        }
        return CommonUtil.isIn(busiFlag,ColCodeDefine.CHG_GROUP_PROD_BUSIFLAGS);
    }
    
    /**
     * 是否是群可以订购的产品（包括黑白名单，包括群间产品）
     * @Date 2012-09-26 yangjh 
     * @param busiFlag
     * @return
     */
    public static boolean isGroupCanOrderAllProd(Integer busiFlag)
    {
        if (busiFlag == null)
        {
            return false;
        }
        return CommonUtil.isIn(busiFlag,ColCodeDefine.GROUP_PROD_ALL_BUSIFLAGS);
    }
    
    /**
     * 是否是群间产品
     * luojb 2012-7-27
     * @param busiFlag
     * @return
     */
    public static boolean isGroupRelationProd(Integer busiFlag)
    {
        return busiFlag != null && busiFlag == SpecCodeDefine.INTER_GROUP_PROMOTION;
    }

    /**
     * 校验是否为群成员个性化产品 luojb 2011-10-13
     */
    public static boolean isGroupPersonProd(Integer busiFlag)
    {
        if (busiFlag == null)
        {
            return false;
        }
        //@Date 2012-06-06 wangdw5 : [47115]Community Promotion
        return CommonUtil.isIn(busiFlag,ColCodeDefine.GROUP_MEM_PROD_BUSIFLAGS);
    }

    /**
     * 判断产品特征是是不是群个性化产品对端群号 luojb 2011-11-9
     */
    public static boolean isGroupPersonalProdParamId(Integer paramId)
    {
        if (paramId == null)
        {
            return false;
        }
        return paramId == SpecCodeDefine.GROUP_IN_PERSONAL_ID || paramId == SpecCodeDefine.GROUP_OUT_PERSONAL_ID
                || paramId == SpecCodeDefine.GROUP_NO_PORT_PERSONAL_ID;
    }

    public static Integer getSpecGroupProdParamId(Integer busiFlag)
    {
        if (busiFlag.intValue() == SpecCodeDefine.GROUP_IN_PERSON)
            return SpecCodeDefine.GROUP_IN_PERSONAL_ID;
        else if (busiFlag.intValue() == SpecCodeDefine.GROUP_OUT_PERSON)
            return SpecCodeDefine.GROUP_OUT_PERSONAL_ID;
        else if (busiFlag.intValue() == SpecCodeDefine.GROUP_NO_PORT_PERSON)
            return SpecCodeDefine.GROUP_NO_PORT_PERSONAL_ID;
        else if(busiFlag.intValue() == SpecCodeDefine.COMMUNITY_PROMOTION)
            return SpecCodeDefine.COMMUNITY_GROUP_ID;
        return null;
    }
}
