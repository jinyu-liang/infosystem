package com.ailk.imssh.common.manual.init;

import java.util.ArrayList;
import java.util.List;
import com.ailk.ims.init.IImsConfigInit;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.xml.BaseNode;
import com.ailk.imssh.common.manual.bean.CaseBean;
import com.ailk.imssh.common.manual.bean.ClassBean;
import com.ailk.imssh.common.manual.bean.CondBean;
import com.ailk.imssh.common.manual.bean.IndexBean;

/**
 * @Description:手工数据维护流程中，表对应的upfield对应关系类
 * @author wangjt
 * @Date 2012-5-17
 */
public class ManualModifyUpfieldInitBean implements IImsConfigInit
{
    private static List<IndexBean> config = new ArrayList<IndexBean>();

    private ImsLogger imsLogger = new ImsLogger(ManualModifyUpfieldInitBean.class);

    @Override
    public void init(BaseNode node)
    {
        imsLogger.info("------- enter ManualModifyUpfieldInitBean");
        try
        {
            List<BaseNode> indexNodeList = node.getChildrenByTagName("index");// <index value="1">

            for (BaseNode indexNode : indexNodeList)
            {
                IndexBean indexBean = new IndexBean();

                // <class name="com.ailk.openbilling.persistence.cust.entity.CmCustomer">
                List<BaseNode> classNodeList = indexNode.getChildrenByTagName("class");

                for (BaseNode classNode : classNodeList)
                {
                    String name = classNode.getAttribute("name");// com.ailk.openbilling.persistence.cust.entity.CmIndividual
                    Class clazz = Class.forName(name);

                    ClassBean classBean = new ClassBean(clazz);

                    // <cond field="objectId" value="acctId" />
                    List<BaseNode> condNodeList1 = classNode.getChildrenByTagName("cond");
                    if (condNodeList1 != null && !condNodeList1.isEmpty())
                    {
                        for (BaseNode condNode : condNodeList1)
                        {
                            String field = condNode.getAttribute("field");
                            String value = condNode.getAttribute("value");
                            CondBean cond = new CondBean(DBUtil.getJefField(clazz, field), value);
                            classBean.addCondBean(cond);
                        }
                    }
                    else
                    {
                        List<BaseNode> caseNodeList = classNode.getChildrenByTagName("case");
                        for (BaseNode caseNode : caseNodeList)
                        {
                            List<BaseNode> condNodeList2 = caseNode.getChildrenByTagName("cond");
                            
                            String field = caseNode.getAttribute("field");
                            String value = caseNode.getAttribute("value");
                            CaseBean caseBean = new CaseBean(DBUtil.getJefField(clazz, field), value);

                            if (CommonUtil.isNotEmpty(condNodeList2))
                            {
                                for (BaseNode condNode : condNodeList2)
                                {
                                    field = condNode.getAttribute("field");
                                    value = condNode.getAttribute("value");
                                    imsLogger.info("*****field " , field);
                                    CondBean cond = new CondBean(DBUtil.getJefField(clazz, field), value);

                                    caseBean.addCondBean(cond);
                                }
                            }
                            classBean.addCaseBean(caseBean);
                        }
                    }
                    indexBean.addClassBean(classBean);
                }
                config.add(indexBean);
            }
            imsLogger.info("------- exit ManualModifyUpfieldInitBean");
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }

    public static IndexBean getManualUpfieldIndex(int index)
    {
        return config.get(index);
    }

    @Override
    public void mergeNodes(BaseNode node, BaseNode nodeProj) throws Exception
    {
    }

}
