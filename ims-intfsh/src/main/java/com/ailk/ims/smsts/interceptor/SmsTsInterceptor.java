package com.ailk.ims.smsts.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import javax.persistence.Table;
import jef.database.DataObject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.w3c.dom.Element;
import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.config.SmsConfig;
import com.ailk.ims.smsts.flowbase.IBaseFlow;
import com.ailk.ims.smsts.helper.BuildHelper;
import com.ailk.ims.smsts.helper.FreeresDayNotifyHelper;
import com.ailk.ims.smsts.thread.FlowThread;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;

/**
 * @Description：定时拦截器
 * @author wangjt
 * @Date 2012-8-3
 * @Date 2012-08-22 wukl 扫描流程只执行一次，需要返回ONCE
 * @date 2013-1-17  gaoqc5 相同个流程中的blockId尽量保持一致，即使是在多线程。
 */
public class SmsTsInterceptor implements MethodInterceptor
{
    private ImsLogger imsLogger = new ImsLogger(SmsTsInterceptor.class);
    private static final Pattern PATTERN = Pattern.compile("\\d{1,2}");

    public Object invoke(MethodInvocation invocation) throws Throwable
    {
        try
        {
            long t1 = System.currentTimeMillis();
            imsLogger.info("---enter SmsTsInterceptor---");

            String serviceName = "com.ailk.openbilling.service.smsts.ISmsTsAppServiceImpl";
            String methodName = invocation.getMethod().getName();

            BaseNode methodNode = BusiUtil.getMethodNode(serviceName, methodName);
            Integer busiNum = Integer.parseInt(methodNode.getAttribute("busi_code"));
            String scanTableClass = methodNode.getAttribute("scan_table_class");
            String isQueryFreerRes = methodNode.getAttribute("isScanFreerRes");
            BaseNode flowNode = methodNode.getChildByTagName("flow");
            String flowClassName = flowNode.getAttribute("bean");
           Map<Long,Long> templateIdAndBlockIdMap=new HashMap<Long, Long>();
            Class<? extends IBaseFlow> flowClass = null;
            try
            {
                flowClass = (Class<? extends IBaseFlow>) Class.forName(flowClassName);// 统一获取一次，而不是在循环中获取
                templateIdAndBlockIdMap= getTemplateAndBlockIdMap(flowClass);
            }
            catch (Exception e)
            {
                imsLogger.error(e.getMessage());
                throw new IMSException(methodName + " is not configured ,plz contact with the developer !!!!!");
            }

            // 获取该流程中，需要扫描的所有分表的set
            Set<String> subTableNameSet = this.initScanTableNameSet(scanTableClass);

            List<FlowThread> threadList = new ArrayList<FlowThread>();
            List<SmsParam> paramList=new ArrayList<SmsParam>();
            int size = subTableNameSet.size();
            for (String subTableName : subTableNameSet)
            {  
                //smsParam 对象不能放在循环外面，否则所有线程会引用相同的 参数
                SmsParam smsParam=initSmsParam(busiNum,methodName,subTableName,flowClass, isScanFreerRes(isQueryFreerRes),size);
                smsParam.setTemplateIdAndBlockIdMap(templateIdAndBlockIdMap);
                FlowThread flowThread = new FlowThread(smsParam);
                flowThread.start();
                imsLogger.info(methodName, " start,", subTableName, "'s deal thread start....");
                threadList.add(flowThread);
                paramList.add(smsParam);
            }

            for (int i = 0; i < threadList.size(); i++)
            {
                threadList.get(i).join();
            }
            
            doLast(templateIdAndBlockIdMap,paramList);
            imsLogger.info(t1, "---leave SmsTsInterceptor---");
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            throw e;
        }
        // @Date 2012-08-22 wukl 扫描流程只执行一次，需要返回ONCE
        return OBBufferErrorException.SDL_ONCE;
    }
     /**
      * 最好的操作，审核总表 
      * @param map
      * @param params
      */
    private void doLast(Map<Long, Long> map,List<SmsParam>params)
    {
        Set<Long> tpls = new HashSet<Long>();
        for (SmsParam param : params)
        {
            tpls.addAll(param.getHasMsgSmsCode());
        }
        if (CommonUtil.isEmpty(tpls))
        {
            return;
        }
        Date date = new Date();
        for (Long smsCode : tpls)
        {
            Long blockId = map.get(smsCode);
            imsLogger.info("开始生成smsCode:",smsCode,"批次号为:",blockId,"的短信审核总表");
            SmsConfig.getSmsService().commitCheckSmsSend(BuildHelper.buildCheckSmsSend(blockId, smsCode, date, 100L));// 构建总表信息

        }
    }
    
     private SmsParam initSmsParam(int busiNum,String methodName,String subTableName,  Class<? extends IBaseFlow> flowClass ,boolean isQueryFreerRes,int threadCount){
         SmsParam param=new SmsParam();
         param.setMethodName(methodName);
         param.setSubTableName(subTableName);
         param.setFlowInstanceClass(flowClass);
         param.setBusiNum(busiNum);
         param.setSubTableIndex(getSubTableIndex(subTableName));
         param.setQueryFreerRes(isQueryFreerRes);
         param.setDayNotify(getFreeresDayNotify(param.getSubTableIndex()));
         return param;
         
     }
     /**
      * 根据具体业务类确保批次号
      * @param flowClass
      * @return
      * @throws InstantiationException
      * @throws IllegalAccessException
      */
     private Map<Long ,Long> getTemplateAndBlockIdMap(Class<? extends IBaseFlow> flowClass) throws InstantiationException, IllegalAccessException{
         Map<Long ,Long> map= flowClass.newInstance().getTemplateAndBlockIdMap();
         for(Long tpl:map.keySet()){
             imsLogger.info("模板编号:",tpl,"批次号:",map.get(tpl));
         }
         
         return map;
     }
     
     /**
      * 获取免费资源状态通知表
      * @param index
      * @return
      */
     private CaFreeresDayNotify getFreeresDayNotify(int index){
         CaFreeresDayNotify dayNotify= DBUtil.querySingle(CaFreeresDayNotify.class,new DBCondition(CaFreeresDayNotify.Field.subTableIndex, index));
         //2012-12-22 gaoqc5 如果免费资源通知表没有数据(可能被清理了),默认选择当前的免费资源表
         if(null == dayNotify){
             imsLogger.info("免费资源通知表为空,默认获取当天免费资源!");
             dayNotify=FreeresDayNotifyHelper.getDefaultFreeresDayNotify(CommonUtil.IntegerToLong(index));
         }
         imsLogger.info("获取:"+dayNotify.getExportDate()+",索引是:"+dayNotify.getSubTableIndex()+"的免资源表!");
         return dayNotify;
     }


   /**
    * 获取要扫描表的分表号
    * @param tableName
    * @return
    */
    private int getSubTableIndex(String tableName)
    {
        String[] partions = tableName.split("_");
        String index = partions[partions.length - 1];
        if (PATTERN.matcher(index).matches())
        {
            return Integer.parseInt(index);
        }
        imsLogger.info("*********扫描分表:", tableName, "找不到分表，返回零!!!");
        return 0;
    }

    // public static void main(String[] args)
    // {
    // for(int index=0;index<100;index++){
    // String table="CD.CM_RESOURCE_"+index;
    // System.out.println(getSubTableName(table));
    // }
    // }

    /*
     * 判断是否从免资源表扫描
     */
    private static  boolean isScanFreerRes(String isQueryFree)
    {
        return null != isQueryFree && isQueryFree.trim().equals("TRUE");
    }

    /**
     * 获取该流程中，需要扫描的所有分表的set
     */
    private Set<String> initScanTableNameSet(String scanTableClassName) throws Exception
    {
        Class<DataObject> scanTableClass = (Class<DataObject>) Class.forName(scanTableClassName);
        Set<String> subTableNameSet = new HashSet<String>();
        // String tableName=DBUtil.getEntityTableName(scanTableClass);
        // String domian=DBUtil.getEntitySchema(scanTableClass);
        // String name=domian+"."+tableName+"";
        // subTableNameSet.add(name);

        OLTPServiceContext oltpSvcContext = OLTPServiceContext.getInstance();
        Element tableFieldElement = oltpSvcContext.getConfigElement("/root/common_config/params/table_field");

        // 如果在云平台未配置分表名称列表
        if (tableFieldElement == null || tableFieldElement.getTextContent() == null
                || tableFieldElement.getTextContent().equals(""))
        {
            imsLogger.info("地址 /root/common_config/params/table_field 没有配置，将扫描所有分表");
            subTableNameSet = DBUtil.getDBClient().getSubTableNames(scanTableClass);

            if (CommonUtil.isEmpty(subTableNameSet))
            {// 没有找到分表
                String tableName = DBUtil.getEntityTableName(scanTableClass);
                String domian = DBUtil.getEntitySchema(scanTableClass);
                if (CommonUtil.isNotEmpty(tableName))
                {
                    tableName = domian + "." + tableName;
                    subTableNameSet.add(tableName);
                }
            }
        }
        else
        {
            // @Table(schema="cd",name="IMS_STS_SYNC_STORE")
            Table table = scanTableClass.getAnnotation(javax.persistence.Table.class);
            String preTable = CommonUtil.concat(table.schema(), ".", table.name(), "_");// 如 cd.CM_RESOURCE_

            // 在云平台配置的需要扫描的分表
            String[] tableIndexArr = tableFieldElement.getTextContent().split(",");

            subTableNameSet = new LinkedHashSet<String>();
            for (String tableIndex : tableIndexArr)
            {
                if(CommonUtil.isEmpty(tableIndex)){
                    continue;
                }
                String strSubTable = preTable + tableIndex;
                imsLogger.debug("扫描主表:",strSubTable);
                subTableNameSet.add(strSubTable);
            }
        }

        if (subTableNameSet == null || subTableNameSet.isEmpty())
        {
            throw new IMSException("subtables is zero, plz check!!!! ");
        }

        imsLogger.info("######find ", subTableNameSet.size(), " subtables");

        return subTableNameSet;
    }
}
