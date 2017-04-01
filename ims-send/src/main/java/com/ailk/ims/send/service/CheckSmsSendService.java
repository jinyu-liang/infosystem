package com.ailk.ims.send.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import jef.common.wrapper.IntRange;
import jef.database.Batch;
import jef.database.QueryArg.MyTableName;
import jef.database.query.Query;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.send.config.ImmeSmsConfig;
import com.ailk.ims.send.config.SendConfig;
import com.ailk.ims.send.convert.HisObjCvt;
import com.ailk.ims.send.convert.ImsCrmCvt;
import com.ailk.ims.send.util.SendSpringUtil;
import com.ailk.ims.send.util.SendUtil;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSendHis;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;
import com.ailk.openbilling.persistence.jd.entity.SmsSendIntfChkHis;

/**
 * @Description:数据从billing侧发送至crm侧
 * @author wangjt
 * @Date 2012-10-15
 * @Date 2012-12-21 wukl client不用保存起来，每次得重新从Dao中获取
 */
public class CheckSmsSendService implements ICheckSmsSendService
{
	
	protected static Logger logger = Logger.getLogger(CheckSmsSendService.class);
    /**
     * 获取一条审核总表中的数据，并且统计明细表对应的条数，update到审核总表
     */
    @Transactional
    public CheckSmsSend selectAndUpdateCheckSmsSend(Query<CheckSmsSend> query, IntRange intRange, int subTableIndex,SendConfig sendConfig)
            throws BaseException
    {
        CheckSmsSend checkSmsSend = null;
        try
        {
//            CommonDao commonDao = SendSpringUtil.getCommonDao();
//            AbstractDbClient imsClient = commonDao.getClient();
//            String name = "JD.CHECK_SMS_SEND_" + subTableIndex;
            String name = sendConfig.getTablePrefix() + subTableIndex;
            MyTableName tableName = new MyTableName(name);
            List<CheckSmsSend> imsList = SendSpringUtil.getCommonDao().getClient().select(query, intRange, tableName);
            if (imsList == null || imsList.isEmpty())
            {
                return null;
            }
            checkSmsSend = imsList.get(0);
            Long sendNum = checkSmsSend.getSendNum();

            if (sendNum != null && sendNum > 0)
            {
                // 如果sendNum有值，则表示该条数据上次已经计算过明细表总数了
                return checkSmsSend;
            }

            // sendNum为空，需要通过blockId，计算短信明细表中有多少条记录
            SmsSendInterfaceCheck detail = new SmsSendInterfaceCheck();
            detail.setBlockId(checkSmsSend.getBlockId());
//            long count = imsClient.count(detail, "JD.SMS_SEND_INTERFACE_CHECK_" + subTableIndex);
            long count = SendSpringUtil.getCommonDao().getClient().count(detail, sendConfig.getTableExtPrefix() + subTableIndex);
            CheckSmsSend temp = new CheckSmsSend();
            temp.setSendNum(count);
            temp.getQuery().addCondition(CheckSmsSend.Field.blockId, checkSmsSend.getBlockId());
            // 把计算的总数更新到审核总表中，以便发送给CRM
            SendSpringUtil.getCommonDao().getClient().update(temp, name);

            // 总数设置到对象中，返回
            checkSmsSend.setSendNum(count);
        }
        catch (SQLException e)
        {
            Logger logger = Logger.getLogger(CheckSmsSendService.class);
            logger.error("SQL error at: " + e.getSQLState(), e);
            throw new BaseException(e.getErrorCode(), e, e.getMessage());
        }
        return checkSmsSend;
    }

    /**
     * 短信明细表中的数据分批次发送给CRM<BR>
     * 返回数据发送给CRM数据条数 <BR>
     * 发送多个表，表之间有关联
     */
    @Transactional
    public int sendDetail(SmsSendInterfaceCheck cond, IntRange intRange, int subTableIndex,SendConfig sendConfig) throws BaseException
    {
        int size = 0;
        List<SmsSendInterfaceCheck> imsList = null;
        try
        {
//            CommonDao commonDao = SendSpringUtil.getCommonDao();
//            AbstractDbClient imsClient = commonDao.getClient();
//            CommonDao commonCrmDao = SendSpringUtil.getCommonCrmDao();
//            AbstractDbClient crmClient = commonCrmDao.getClient();
//            String name = "JD.SMS_SEND_INTERFACE_CHECK_" + subTableIndex;
            String name = sendConfig.getTableExtPrefix() + subTableIndex;
            MyTableName tableName=new MyTableName(name);
            imsList = (List<SmsSendInterfaceCheck>) SendSpringUtil.getCommonDao().getClient().select(cond.getQuery(), intRange,tableName);
            if (imsList == null || imsList.isEmpty())
            {
                return 0;
            }

            List<com.ailk.openbilling.persistence.crmsms.entity.SmsSendInterfaceCheck> crmList = ImsCrmCvt
                    .cvt2CrmDetailList(imsList);

            // insert到crm数据库
            Batch crmBatch = null;
            crmBatch = SendSpringUtil.getCommonCrmDao().getClient().startBatchInsert(crmList.get(0),sendConfig.getCrmTableExtName());
            crmBatch.add(crmList);
            crmBatch.commit();

            // 删除billing侧数据库中的相关数据
            Batch imsBatch = SendSpringUtil.getCommonDao().getClient().startBatchDelete(imsList.get(0), name);
            imsBatch.setForceTableName(name);
            imsBatch.add(imsList);
            imsBatch.commit();

            // 转换为历史表list
            Date soDate = SendUtil.currentDate();

            List<SmsSendIntfChkHis> imsHisList = HisObjCvt.cvt2HisDetailList(imsList, soDate);

            // 自己拼具体分表名-增加到历史表
//            String hisTableName = "JD.SMS_SEND_INTF_CHK_HIS_" + SendUtil.getYYYYMM(soDate);
//            String hisTableName = sendConfig.getHisTableExtPrefix() + SendUtil.getYYYYMM(soDate);
            String hisTableName = sendConfig.getHisTableExtPrefix() ;
            Batch imsHisBatch = SendSpringUtil.getCommonDao().getClient().startBatchInsert(imsHisList.get(0), hisTableName);
            imsHisBatch.add(imsHisList);
            imsHisBatch.commit();

            size = imsList.size();
        }
        catch (SQLException e)
        {
            Logger logger = Logger.getLogger(CheckSmsSendService.class);
            logger.error("SQL error at: " + e.getSQLState(), e);
            if (e.getMessage().contains("ORA-00001"))
            {
                dealORA00001ForDetail(sendConfig, subTableIndex ,imsList);
            }
            else
            {
                String errorMsg = printErrorDoneCode(imsList);
                if (errorMsg != null)
                {
                    logger.error(errorMsg);
                }
                throw new BaseException(e.getErrorCode(), e, e.getMessage());
            }
        }
        return size;
    }

    private void dealORA00001ForDetail(SendConfig sendConfig, int subTableIndex , List<SmsSendInterfaceCheck> imsList)
    {
        // 删除billing侧数据库中的相关数据
        try
        {
            String name = sendConfig.getTableExtPrefix() + subTableIndex;
            Batch imsBatch = SendSpringUtil.getCommonDao().getClient().startBatchDelete(imsList.get(0), name);
            imsBatch.setForceTableName(name);
            imsBatch.add(imsList);
            imsBatch.commit();

            // 转换为历史表list
            Date soDate = SendUtil.currentDate();

            List<SmsSendIntfChkHis> imsHisList = HisObjCvt.cvt2HisDetailList(imsList, soDate);

            // 自己拼具体分表名-增加到历史表
            // String hisTableName = "JD.SMS_SEND_INTF_CHK_HIS_" + SendUtil.getYYYYMM(soDate);
            String hisTableName = sendConfig.getHisTableExtPrefix() ;
//            String hisTableName = sendConfig.getHisTableExtPrefix() + SendUtil.getYYYYMM(soDate);
            Batch imsHisBatch = SendSpringUtil.getCommonDao().getClient().startBatchInsert(imsHisList.get(0), hisTableName);
            imsHisBatch.add(imsHisList);
            imsHisBatch.commit();
        }
        catch (SQLException e)
        {
        	logger.error(e,e);
        }

    }

    /**
     * 删除审核总表中的一条记录，并发送至CRM
     */
    @Transactional
    public void deleteAndSendCheckSmsSend(CheckSmsSend checkSmsSend,int subTableIndex,SendConfig sendConfig) throws BaseException
    {
        Date soDate = SendUtil.currentDate();
        try
        {
//            CommonDao commonDao = SendSpringUtil.getCommonDao();
//            AbstractDbClient imsClient = commonDao.getClient();
//            CommonDao commonCrmDao = SendSpringUtil.getCommonCrmDao();
//            AbstractDbClient crmClient = commonCrmDao.getClient();

            com.ailk.openbilling.persistence.crmsms.entity.CheckSmsSend crmObj = ImsCrmCvt.cvt2CrmObj(checkSmsSend);
            CheckSmsSendHis hisObj = HisObjCvt.cvt2HisObj(checkSmsSend, soDate);

            CheckSmsSend condObj = new CheckSmsSend();
            condObj.setBlockId(checkSmsSend.getBlockId());

            SendSpringUtil.getCommonCrmDao().getClient().insert(crmObj,sendConfig.getCrmTableName());
//            imsClient.delete(condObj, "JD.CHECK_SMS_SEND_" + subTableIndex);
            SendSpringUtil.getCommonDao().getClient().delete(condObj, sendConfig.getTablePrefix() + subTableIndex);
            // 增加到历史表 - 自己拼具体分表名
//            String hisTableName = "JD.CHECK_SMS_SEND_HIS_" + SendUtil.getYYYYMM(soDate);
            String hisTableName = sendConfig.getHisTablePrefix() ;
            SendSpringUtil.getCommonDao().getClient().insert(hisObj, hisTableName);
        }
        catch (SQLException e)
        {
            Logger logger = Logger.getLogger(CheckSmsSendService.class);
            logger.error("SQL error at: " + e.getSQLState(), e);
            if (e.getMessage().contains("ORA-00001"))
            {
                dealORA00001ForTotal(sendConfig, subTableIndex ,checkSmsSend,soDate);
            }
            else
            {
                throw new BaseException(e.getErrorCode(), e, e.getMessage());
            }
            
        }
    }

    private void dealORA00001ForTotal(SendConfig sendConfig, int subTableIndex, CheckSmsSend checkSmsSend, Date soDate)
    {
        try
        {
            CheckSmsSend condObj = new CheckSmsSend();
            condObj.setBlockId(checkSmsSend.getBlockId());
            SendSpringUtil.getCommonDao().getClient().delete(condObj, sendConfig.getTablePrefix() + subTableIndex);
//            String hisTableName = sendConfig.getHisTablePrefix() + SendUtil.getYYYYMM(soDate);
            String hisTableName = sendConfig.getHisTablePrefix() ;
            CheckSmsSendHis hisObj = HisObjCvt.cvt2HisObj(checkSmsSend, soDate);
            SendSpringUtil.getCommonDao().getClient().insert(hisObj, hisTableName);
        }
        catch (SQLException e)
        {
        	logger.error(e,e);
        }
    }

    /* (non-Javadoc)
     * @see com.ailk.ims.send.service.ICheckSmsSendService#errorDeal(int)
     */
    public void errorDeal(int dealCount)
    {
        Logger logger = Logger.getLogger(CheckSmsSendService.class);
        int time = getDelayTime(dealCount);
        logger.error("********* sleep [" + time/1000 + "]s for error ***********");
        SendUtil.sleep(time);// 停time秒继续执行
    }

    /* (non-Javadoc)
     * @see com.ailk.ims.send.service.ICheckSmsSendService#getDelayTime(int)
     */
    public int getDelayTime(int dealCount)
    {
        if (dealCount > ImmeSmsConfig.MAX_RETRY_TIME)
        {
            dealCount = ImmeSmsConfig.MAX_RETRY_TIME;
        }
        int delayTime = (int) (Math.pow(ImmeSmsConfig.MULTIPLY_FACTOR, dealCount - 1) * ImmeSmsConfig.FIRST_WAIT_SECOND);
        return delayTime * 1000;
    }
    
    private String printErrorDoneCode(List<SmsSendInterfaceCheck> smsList)
    {
        if (smsList != null && !smsList.isEmpty())
        {
            StringBuffer sb = new StringBuffer("");
            sb.append("******** error DONE_CODE list,[ ");
            for (SmsSendInterfaceCheck imme : smsList)
            {
                sb.append(imme.getDoneCode());
                sb.append(",");
            }
            sb.append(" ] **********");
            return sb.toString();
        }
        return null;
    }
}
