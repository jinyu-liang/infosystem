package com.ailk.ims.init.check;

import java.util.List;
import jef.tools.string.RegexpUtils;
import com.ailk.ims.cache.BaseCacheParser;
import com.ailk.ims.cache.SysParameterCacheParser;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.xml.BaseNode;

public class SysParameterCheckBean implements IConfigCheck
{
    private static String TAG_ITEM = "item";
    private static String ATTR_CODE = "code";
    private static String ATTR_NULLABLE = "nullable";
    private static String ATTR_VALUE_PATTERN = "value_pattern";
    private static String ATTR_ERROR_HINT = "error_hint";
    private ImsLogger imsLogger=new ImsLogger(this.getClass());
    public void check(BaseNode checkNode,BaseCacheParser cacheParser) throws Exception
    {
        List<BaseNode> children = checkNode.getChildrenByTagName(TAG_ITEM);
        if (CommonUtil.isEmpty(children))
        {
            return;
        }
        StringBuffer errorList = new StringBuffer();
        for (BaseNode item : children)
        {
            String code = item.getAttribute(ATTR_CODE);
            boolean nullable = item.getBooleanAttribute(ATTR_NULLABLE,false);
            String valuePattern = item.getAttribute(ATTR_VALUE_PATTERN);
            String errorHint = item.getAttribute(ATTR_ERROR_HINT);
            if (!CommonUtil.isValid(code))
            {
                errorList.append("\r\nitem code  of <conifg name = " + checkNode.getAttribute("name") + "> can't be null");
                continue;
            }
            String value = ((SysParameterCacheParser)cacheParser).getParamByCode(code);
                
            imsLogger.info("+++++++++[check][" + code + " = " + value + "]");
            if (value == null)
            {
                if (nullable)
                {
                    continue;
                }
                else
                {
                    errorList.append("\r\nparam '" + code + "' is not configed in sys_parameter");
                    continue;
                }
            }
            if (!CommonUtil.isValid(valuePattern))
            {
                continue;
            }

            boolean matched = false;
            try
            {
                matched = RegexpUtils.matches(value, valuePattern);
            }
            catch (Exception e)
            {
                errorList.append("\r\nvalue_pattern[" + valuePattern + "] of code[" + code + "] is not a valid regexp");
                continue;
            }
            if (!matched)
            {
                errorList.append("\r\nparam '" + code + "' is a wrong configuration." + errorHint);
                continue;
            }
        }
        if(CommonUtil.isNotEmpty(errorList))
            throw IMSUtil.throwBusiException(errorList.toString());
    }
}
