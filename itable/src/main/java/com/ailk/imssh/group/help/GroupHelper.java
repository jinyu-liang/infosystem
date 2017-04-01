package com.ailk.imssh.group.help;

import com.ailk.ims.define.SpecCodeDefine;

/**
 * @Description:群组操作相关静态方法类
 * @author wangjt
 * @Date 2011-12-23
 * @Date 2012-04-10 zengxr isGroupProd add SpecCodeDefine.INTER_GROUP_PROMOTION
 */
public class GroupHelper
{
    private GroupHelper()
    {
    }

    /**
     * 校验是否为群产品 luojb 2011-10-13
     */
    public static boolean isGroupProd(Integer busiFlag)
    {
        if (busiFlag == null)
        {
            return false;
        }
        return (busiFlag.equals(SpecCodeDefine.GROUP_IN) || busiFlag.equals(SpecCodeDefine.GROUP_OUT)
                || busiFlag.equals(SpecCodeDefine.GROUP_NO_PORT) || busiFlag.equals(SpecCodeDefine.INTER_GROUP_PROMOTION));
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
        return busiFlag.equals(SpecCodeDefine.GROUP_IN_PERSON) || busiFlag.equals(SpecCodeDefine.GROUP_NO_PORT_PERSON)
                || busiFlag.equals(SpecCodeDefine.GROUP_OUT_PERSON);
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

        return null;
    }
}
