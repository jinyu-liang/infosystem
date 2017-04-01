package com.ailk.imssh.common.init;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ailk.ims.init.IImsConfigInit;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.xml.BaseNode;

/**
 * @Description:接口数据表，上发字段对应的类的配置
 * @author wangjt
 * @Date 2012-5-17
 */
public class EnumMappingInitBean implements IImsConfigInit
{
    private static String ATTR_BOSS = "boss";
    private static String ATTR_CRM = "crm";
    private static String ATTR_REMARK = "remark";
    private static String ATTR_NAME = "name";
    private static String TAG_ITEM = "item";
    private static String TAG_CODE = "code";

    private static LinkedHashMap<String, Map<Integer, Integer>> config = new LinkedHashMap<String, Map<Integer, Integer>>();

    private ImsLogger imsLogger = new ImsLogger(EnumMappingInitBean.class);

    @Override
    public void mergeNodes(BaseNode node, BaseNode nodeProj) throws Exception
    {
        // 无需合并版本
    }

    @Override
    public void init(BaseNode node)
    {
        try
        {
            List<BaseNode> codeList = node.getChildrenByTagName(TAG_CODE);
            if (CommonUtil.isEmpty(codeList))
            {
                return;
            }

            imsLogger.info("------- enter enum mapping");

            for (BaseNode codeNode : codeList)
            {
                String codeName = codeNode.getAttribute(ATTR_NAME);
                imsLogger.info("------ Field [" , codeName , "]");

                List<BaseNode> itemNodes = codeNode.getChildrenByTagName(TAG_ITEM);

                Map<Integer, Integer> map = new HashMap<Integer, Integer>();

                for (BaseNode itemNode : itemNodes)
                {
                    Integer crmValue = itemNode.getIntAttribute(ATTR_CRM);
                    Integer bossValue = itemNode.getIntAttribute(ATTR_BOSS);
                    map.put(crmValue, bossValue);
                    imsLogger.debug("------[Enum] CRM[" , crmValue , "]--BOSS[" , bossValue , "]--REMARK["
                            + itemNode.getAttribute(ATTR_REMARK) + "]");
                }

                config.put(codeName, map);
            }
            imsLogger.info("------- exit enum mapping");
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }

    public static Integer getBossEnumWithCrmEnum(String codeName, Integer crmEnum)
    {
        Map<Integer, Integer> map = config.get(codeName);
        if (map.isEmpty())
        {
            IMSUtil.throwBusiException("the code <" + codeName + "> is not enum mapping");
        }
        return map.get(crmEnum);
    }

}
