package com.ailk.ims.init.check;

import java.util.List;
import com.ailk.ims.cache.BaseCacheParser;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.acct.entity.BiBusiSpecExt;

public class BiBusiSpecExtCheckBean implements IConfigCheck
{
    private static String TAG_ITEM = "item";
    private static String ATTR_CODE = "code";
    private ImsLogger imsLogger=new ImsLogger(this.getClass());
    public void check(BaseNode checkNode, BaseCacheParser cacheParser) throws Exception
    {
        List<BaseNode> children = checkNode.getChildrenByTagName(TAG_ITEM);
        if (CommonUtil.isEmpty(children))
        {
            return;
        }
        StringBuffer errorList = new StringBuffer();
        for (BaseNode item : children)
        {
            Integer code = item.getIntAttribute(ATTR_CODE);
            if (code == null)
                continue;
            Integer notifyId = getNotificationId(code,cacheParser);
            imsLogger.info("+++++++++[check][specId:" + code + "] = [notifyId:" + notifyId + "]");
            if (notifyId == null)
            {
                errorList.append("\r\nbusiSpecId '" + code + "' is not configed in bi_busi_spec_ext");
            }

        }
        if(CommonUtil.isNotEmpty(errorList))
            throw IMSUtil.throwBusiException(errorList.toString());
    }
    
    private Integer getNotificationId(Integer busiSpecId,BaseCacheParser cacheParser) throws Exception
    {
        BiBusiSpecExt dmSpecExt = (BiBusiSpecExt) cacheParser.getSingle(new CacheCondition(BiBusiSpecExt.Field.busiSpecId, busiSpecId));
        if (dmSpecExt != null)
        {
            return dmSpecExt.getNotificationId();
        }
        return null;
    }
}
