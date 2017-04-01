package com.ailk.imssh.common.manual.main;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;
import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.flow.scandeal.deal.BaseDeal;
import com.ailk.imssh.common.init.CommonInit;
import com.ailk.imssh.common.manual.handler.ITransferHandler;
import com.ailk.imssh.common.mdb.ErrorMsgHolder;
import com.ailk.imssh.common.mdb.ItableSalMdbBean;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;
import com.ailk.openbilling.persistence.cust.entity.CmUserPortability;
import com.ailk.openbilling.persistence.jd.entity.ImsPortabilityErr;
import com.ailk.openbilling.persistence.jd.entity.ImsPortabilityHis;
import com.ailk.ts.util.TSUtils;

public class CustTransferBean
{
    private static ImsLogger imsLogger = new ImsLogger(CustTransferBean.class);

    public static void main(String[] args)
    {
        ITableUtil.startTs();

        // TS采用异步方式 加载Spring配置，若没有启动完则无法使用Spring容器，故采用线程sleep方式，待完全起来后再执行程序的启动
        while (!OLTPServiceContext.isInitSpringFinished())
        {
            try
            {
                imsLogger.debug("***spring context not start,sleep 5 s");
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                imsLogger.error(e.getMessage());
            }
        }

        CommonInit.commonInitWithTs();
        String dealDateStr = null;
        if (CommonUtil.isNotEmpty(args))
        {
            dealDateStr = args[0];
        }
        new CustTransferBean().transfer(dealDateStr);

        TSUtils.doExit(0, "success", null);

    }

    public void transfer(String dealDateStr)
    {

        Date date = DateUtil.currentDate();
        Query<CmUserPortability> query = QueryBuilder.create(CmUserPortability.class);
        if (CommonUtil.isNotEmpty(dealDateStr))
        {
            try
            {
                date = DateUtil.getFormatDate(dealDateStr, DateUtil.DATE_FORMAT_YYYYMMDD);
                query.addCondition(CmUserPortability.Field.validDate, date);
            }
            catch (Exception e)
            {
                imsLogger.error("parse dealDate error,use sysdate");
                query.addCondition(CmUserPortability.Field.validDate, Operator.GREAT, date);
            }
        }else{
        	query.addCondition(CmUserPortability.Field.validDate, Operator.GREAT, date);
        }
        query.addCondition(CmUserPortability.Field.expireDate, Operator.GREAT, date);
        query.addOrderBy(true, CmUserPortability.Field.resourceId);// 按照resource_id从小到大排序读取


        ITransferHandler handler = SpringExUtil.getTransferHandler();
        ITransferHandler countyHandler=SpringExUtil.getTransferCountyHandler();
        try
        {
            List<CmUserPortability> allList = DBUtil.getDBClient().select(query);

            if (CommonUtil.isNotEmpty(allList))
            {
                for (CmUserPortability cmUserPortability : allList)
                {
                    ImsPortabilityHis his = DBUtil.querySingle(ImsPortabilityHis.class, new DBCondition(
                            ImsPortabilityHis.Field.userId, cmUserPortability.getResourceId()), new DBCondition(
                            ImsPortabilityHis.Field.validDate, cmUserPortability.getValidDate()));
                    if (his != null)
                    {
                        continue;
                    }
                    ITableUtil.requestInit();
                    ITableContext context = new ITableContext();
                    context.setSync(true);
                    context.setRequestDate(DateUtil.offsetDate(cmUserPortability.getValidDate(), Calendar.HOUR_OF_DAY, -2));
                    String errorMsg = null;
                    //入地市和出地市是同一个地市的，不做处理
                	try
                	{
                		if(cmUserPortability.getInRegion().equals(cmUserPortability.getOutRegion())){
                			countyHandler.transfer(cmUserPortability, context);
                		}else{
                			handler.transfer(cmUserPortability, context);
                		}
                		ErrorMsgHolder syncMdb = new ItableSalMdbBean(context).syncMdb(null, null);
                		if (syncMdb.getCbsErrorMsg().get_errorCode() != 0)
                		{
                			errorMsg = syncMdb.getCbsErrorMsg().get_errorMsg();
                		}
                	}
                	catch (Exception e)
                	{
                		errorMsg = new BaseDeal().getErrorMsg(e.getLocalizedMessage());
                	}
                    if (errorMsg != null)
                    {
                        // 向错误表插数据
                        removeUserPortability(cmUserPortability, errorMsg);
                    }
                    else
                    {
                        // 向历史表插入数据
                        removeUserPortability(cmUserPortability, null);
                    }
                }
            }

        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }
    }

    private static void removeUserPortability(CmUserPortability cmUserPortability, String msg)
    {
        try
        {
            if (msg == null)
            {
                ImsPortabilityHis his = new ImsPortabilityHis();
                his.setUserId(cmUserPortability.getResourceId());
                his.setInRegion(cmUserPortability.getInRegion());
                his.setOutRegion(cmUserPortability.getOutRegion());
                his.setRegionCode(cmUserPortability.getRegionCode());
                his.setValidDate(cmUserPortability.getValidDate());
                his.setDealDate(DateUtil.currentDate());
                DBUtil.insert(his);
            }
            else
            {
                ImsPortabilityErr err = new ImsPortabilityErr();
                err.setUserId(cmUserPortability.getResourceId());
                err.setDealDate(DateUtil.currentDate());
                err.setInRegion(cmUserPortability.getInRegion());
                err.setOutRegion(cmUserPortability.getOutRegion());
                err.setRegionCode(cmUserPortability.getRegionCode());
                err.setErrorMsg(msg);
                err.setValidDate(cmUserPortability.getValidDate());
                DBUtil.insert(err);
            }
        }
        catch (Exception e2)
        {
            imsLogger.error(e2, e2);
        }
    }
}
