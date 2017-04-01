package com.ailk.imssh.common.init;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.init.IImsConfigInit;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.xml.BaseNode;
import com.ailk.imssh.common.flow.bean.IndexHandlerObject;

/**
 * @Description:接口数据表，上发字段对应的类的配置
 * @author wangjt
 * @Date 2012-5-17
 */
public class ITableUpfieldInitBean implements IImsConfigInit
{
    private static String ATTR_VALUE = "value";
    private static String ATTR_HANDLER = "handler";
    private static String TAG_INDEX = "index";
    private static String TAG_ITABLE = "itable";
    
    private static LinkedHashMap<Integer, IndexHandlerObject> config = new LinkedHashMap<Integer, IndexHandlerObject>();

    private ImsLogger imsLogger = new ImsLogger(ITableUpfieldInitBean.class);

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
            List<BaseNode> indexList = node.getChildrenByTagName(TAG_INDEX);
            if (CommonUtil.isEmpty(indexList))
            {
                return;
            }
            imsLogger.info("------- enter itableupfield");

            for (BaseNode indexNode : indexList)
            {
                Integer index = indexNode.getIntAttribute(ATTR_VALUE);
                String handlerClassName = indexNode.getAttribute(ATTR_HANDLER);

                Class handler = Class.forName(handlerClassName);
                List<BaseNode> itableNodes = indexNode.getChildrenByTagName(TAG_ITABLE);
                List<Class<? extends DataObject>> itableList = new ArrayList<Class<? extends DataObject>>();
                for (BaseNode itableNode : itableNodes)
                {
                    itableList.add((Class<? extends DataObject>) Class.forName(itableNode.getText()));

                    imsLogger.debug("[itable upfield] index=[" , index , "], itable=[" + itableNode.getText() , "]");
                }

                config.put(index, new IndexHandlerObject(index, handler, itableList, itableNodes.get(0).getText()));
            }

            imsLogger.info("------- exit itableupfield");
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }

    /**
     * 根据位置获取对应的配置信息对象
     */
    public static IndexHandlerObject getIndexHandlerObjectByIndex(Integer index)
    {
        return config.get(index);
    }
}
