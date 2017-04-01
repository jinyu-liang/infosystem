package com.ailk.ims.send.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jef.database.AutoCloseIterator;
import jef.database.Batch;
import oracle.sql.ROWID;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.send.auth.TemplateAuthService;
import com.ailk.ims.send.bean.TiOSmsImmeBean;
import com.ailk.ims.send.config.ImmeSmsConfig;
import com.ailk.ims.send.config.SendConfig;
import com.ailk.ims.send.convert.HisObjCvt;
import com.ailk.ims.send.convert.ImsCrmCvt;
import com.ailk.ims.send.convert.IteratorCvt;
import com.ailk.ims.send.util.SendSpringUtil;
import com.ailk.ims.send.util.SendUtil;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImme;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImmeHis;

/**
 * @Description:数据从billing侧发送至crm侧(适合单表数据发送)
 * @author wangjt
 * @Date 2012-10-15
 * @Date 2012-12-21 wukl client不用保存起来，每次得重新从Dao中获取
 */
public class ImmeSmsSendService implements IImmeSmsSendService
{

	
	protected static Logger logger = Logger.getLogger(ImmeSmsSendService.class);
    /**
     * 涉及两个数据源 <BR>
     * 返回发送给crm的数据条数 ， <BR>
     * 对于单表数据同步，统一采用该方法
     */
    @Transactional
    public int sendSingleTableData(SendConfig sendConfig, int subTableIndex, boolean isBig, int modIndex) throws BaseException
    {
        int size = 0;//用来判断进程是否需要停止3秒
        List<TiOSmsImme> imsList = new ArrayList<TiOSmsImme>();
        List<ROWID> rowidList = new ArrayList<ROWID>();//存放ROWID，用来删除数据
        AutoCloseIterator<TiOSmsImmeBean> iter = null;
        try
        {
            if (isBig)
            {
                iter = SendSpringUtil.getCommonDao().getClient().createNativeQuery(buildQuerySql4Begin(subTableIndex, modIndex), TiOSmsImmeBean.class).getResultIterator();
            }
            else
            {
                //对实时短信表采用游标的方式 进行全表扫描
                iter = SendSpringUtil.getCommonDao().getClient().createNativeQuery(buildQuerySql(subTableIndex), TiOSmsImmeBean.class).getResultIterator();
            }
            while(iter.hasNext())
            {
                size ++;
                TiOSmsImmeBean immeBean = iter.next();
                imsList.add(IteratorCvt.immeSmsCvt(immeBean));
                rowidList.add(immeBean.getRowid());
                
                //一批次（默认1000条，可配） 一处理
                if (imsList.size() < sendConfig.getBatchSize())
                {
                    continue;
                }
                
                immeSmsDeal(imsList,rowidList,sendConfig,subTableIndex);
                
                //一批次执行完，清除list的值
                imsList.clear();
                rowidList.clear();
            }
            
            //当游标扫描到的短信量不足批次的阀值，就需要这里来执行
            if (imsList != null && !imsList.isEmpty())
            {
                immeSmsDeal(imsList,rowidList,sendConfig,subTableIndex);
                //最后清除list的值
                imsList.clear();
                rowidList.clear();
            }
                

        }
        catch (SQLException e)
        {
            Logger logger = Logger.getLogger(ImmeSmsSendService.class);
            logger.error("SQL error at: " + e.getSQLState(), e);
            if (e.getMessage().contains("ORA-00001"))
            {
                dealORA00001(sendConfig, subTableIndex,imsList, rowidList);
            }
            else
            {
                String errorMsg = printErrorSmsId(imsList);
                
                if (errorMsg != null)
                {
                    logger.error(errorMsg);
                }
                
                throw new BaseException(e.getErrorCode(), e, e.getMessage());
            }
            
        }
        finally
        {
            if (iter != null)
            {
                iter.close();
            }
        }
        return size;
    }
    
   

    /**
     * 短信同步的处理逻辑
     * wukl 2013-1-7
     * @param imsList
     * @param rowidList
     * @param sendConfig
     * @param subTableIndex
     * @throws SQLException
     */
    private void immeSmsDeal(List<TiOSmsImme> imsList, List<ROWID> rowidList, SendConfig sendConfig, int subTableIndex) throws SQLException
    {
        imsList = authTiOSmsImme(imsList,sendConfig.getBatchSize());
        if (imsList == null || imsList.isEmpty())
        {
            imsList.clear();//imsList 不可能为null
            rowidList.clear();
            return;
        }

        // insert到crm数据库
        List<com.ailk.openbilling.persistence.crmsms.entity.TiOSmsImme> crmList = ImsCrmCvt.cvt2CrmImmeList(imsList);
        Batch crmBatch = SendSpringUtil.getCommonCrmDao().getClient()
                .startBatchInsert(crmList.get(0), sendConfig.getCrmTableName());
        crmBatch.add(crmList);
        crmBatch.commit();

        // 删除billing侧数据库中的相关数据，采用SQL拼装批量执行
        SendSpringUtil.getCommonDao().getClient().executeSqlBatch(buildDeleteSql(subTableIndex), getDeleteConds(rowidList));

        // 转换为历史表list
        Date soDate = SendUtil.currentDate();
        List<TiOSmsImmeHis> imsHisList = HisObjCvt.cvt2HisImmeList(imsList, soDate);

        // 拼具体分表名-插入到历史表
//        String hisTableName = sendConfig.getHisTablePrefix() + subTableIndex+ "_" + SendUtil.getYYYYMM(soDate);
        String hisTableName = sendConfig.getHisTablePrefix() ;
        Batch imsHisBatch = SendSpringUtil.getCommonDao().getClient().startBatchInsert(imsHisList.get(0), hisTableName);
        imsHisBatch.add(imsHisList);
        imsHisBatch.commit();
    }



    /**
     * 构造SQL对应的VALUE
     * wukl 2013-1-7
     * @param rowidList
     * @return
     */
    private List<Object>[] getDeleteConds(List<ROWID> rowidList)
    {
        List<?>[] rowids = new List<?>[rowidList.size()];
        List<ROWID> tempList = null;
        for (int i = 0; i < rowidList.size(); i++)
        {
            ROWID rowid = rowidList.get(i);
            tempList = new ArrayList<ROWID>();
            tempList.add(rowid);
            rowids[i] = tempList;
        }
        return (List<Object>[]) rowids;
    }

    /**
     * 短信模板校验
     * wukl 2012-12-22
     * @param list
     * @return
     */
    private List<TiOSmsImme> authTiOSmsImme(List<TiOSmsImme> list,int fetchSize)
    {
        List<TiOSmsImme> authTiOSmsImmes = new ArrayList<TiOSmsImme>();
        TemplateAuthService templateAuthService = SendSpringUtil.getTemplateAuthService();
        for (TiOSmsImme imme : list)
        {
            if (templateAuthService.AllowSyncSms(imme,fetchSize))
            {
                authTiOSmsImmes.add(imme);
            }
        }
        return authTiOSmsImmes;
    }

    /* (non-Javadoc)
     * @see com.ailk.ims.send.service.IImmeSmsSendService#errorDeal(int)
     */
    public void errorDeal(int dealCount)
    {
        Logger logger = Logger.getLogger(ImmeSmsSendService.class);
        int time = getDelayTime(dealCount);
        logger.error("********* sleep [" + time/1000 + "]s for error ***********");
        SendUtil.sleep(time);// 停time秒继续执行
    }

    /* (non-Javadoc)
     * @see com.ailk.ims.send.service.IImmeSmsSendService#getDelayTime(int)
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
    
    private String printErrorSmsId(List<TiOSmsImme> smsList)
    {
        if (smsList != null && !smsList.isEmpty())
        {
            StringBuffer sb = new StringBuffer("");
            sb.append("******** error SMS_NOTICE_ID list,[ ");
            for (TiOSmsImme imme : smsList)
            {
                sb.append(imme.getSmsNoticeId());
                sb.append(",");
            }
            sb.append(" ] **********");
            return sb.toString();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.ailk.ims.send.service.IImmeSmsSendService#buildQuerySql(int)
     */
    public String buildQuerySql(int subTableIndex)
    {
        return new StringBuffer("select t.rowid,")//rowid
        .append(" t.sms_notice_id as smsNoticeId,t.partition_id as partitionId,t.eparchy_code as eparchyCode,t.brand_code as brandCode,t.in_mode_code as inModeCode," +
        		"t.sms_net_tag as smsNetTag,t.chan_id as chanId,t.send_object_code as sendObjectCode,t.send_time_code as sendTimeCode,t.send_count_code as sendCountCode," +
        		"t.recv_object_type as recvObjectType,t.recv_object as recvObject,t.recv_id as recvId,t.sms_type_code as smsTypeCode,t.sms_kind_code as smsKindCode," +
        		"t.notice_content_type as noticeContentType,t.notice_content as noticeContent,t.refered_count as referedCount,t.force_refer_count as forceReferCount," +
        		"t.force_object as forceObject,t.force_start_time as forceStartTime,t.force_end_time as forceEndTime,t.sms_priority as smsPriority,t.refer_time as referTime," +
        		"t.refer_staff_id as referStaffId,t.refer_depart_id as referDepartId,t.deal_time as dealTime,t.deal_staffid as dealStaffid,t.deal_departid as dealDepartid," +
        		"t.deal_state as dealState,t.done_code as doneCode,t.block_id as blockId,t.sms_code as smsCode,t.remark as remark,t.revc1 as revc1,t.revc2 as revc2,t.revc3 as revc3," +
        		"t.revc4 as revc4,t.month as month,t.day as day from jd.ti_o_sms_imme_")//表字段
        .append(subTableIndex)
        .append(" t")
        .toString();
    }

    /* (non-Javadoc)
     * @see com.ailk.ims.send.service.IImmeSmsSendService#buildDeleteSql(int)
     */
    public String buildDeleteSql(int subTableIndex)
    {
        return new StringBuffer("delete from jd.ti_o_sms_imme_")
        .append(subTableIndex)
        .append(" where rowid=?")//?为占位符
        .toString();
    }
    
    /**
     * 构造查询的SQL，启动的时候如果数据量过大，会导致处理出现瓶颈；当达到一定的量，则根据recv_object的倒数第三数分10个批次处理
     * 因为jd.ti_o_sms_imme_[0-49]已经按照t.recv_object的末两位按50取模分表
     * wukl 2013-1-7
     * @param subTableIndex
     * @return
     */
    public String buildQuerySql4Begin(int subTableIndex, int modIndex)
    {
        return new StringBuffer("select t.rowid,")//rowid
        .append(" t.sms_notice_id as smsNoticeId,t.partition_id as partitionId,t.eparchy_code as eparchyCode,t.brand_code as brandCode,t.in_mode_code as inModeCode," +
                "t.sms_net_tag as smsNetTag,t.chan_id as chanId,t.send_object_code as sendObjectCode,t.send_time_code as sendTimeCode,t.send_count_code as sendCountCode," +
                "t.recv_object_type as recvObjectType,t.recv_object as recvObject,t.recv_id as recvId,t.sms_type_code as smsTypeCode,t.sms_kind_code as smsKindCode," +
                "t.notice_content_type as noticeContentType,t.notice_content as noticeContent,t.refered_count as referedCount,t.force_refer_count as forceReferCount," +
                "t.force_object as forceObject,t.force_start_time as forceStartTime,t.force_end_time as forceEndTime,t.sms_priority as smsPriority,t.refer_time as referTime," +
                "t.refer_staff_id as referStaffId,t.refer_depart_id as referDepartId,t.deal_time as dealTime,t.deal_staffid as dealStaffid,t.deal_departid as dealDepartid," +
                "t.deal_state as dealState,t.done_code as doneCode,t.block_id as blockId,t.sms_code as smsCode,t.remark as remark,t.revc1 as revc1,t.revc2 as revc2,t.revc3 as revc3," +
                "t.revc4 as revc4,t.month as month,t.day as day from jd.ti_o_sms_imme_")//表字段
        .append(subTableIndex)
        .append(" t")
        .append(" where substr(t.recv_object,length(t.recv_object)-2,1) = ")
        .append(modIndex)
        .toString();
    }
    
    private void dealORA00001(SendConfig sendConfig, int  subTableIndex, List<TiOSmsImme> imsList, List<ROWID> rowidList)
    {
        try
        {   // 删除billing侧数据库中的相关数据，采用SQL拼装批量执行
            SendSpringUtil.getCommonDao().getClient().executeSqlBatch(buildDeleteSql(subTableIndex), getDeleteConds(rowidList));

            // 转换为历史表list
            Date soDate = SendUtil.currentDate();
            List<TiOSmsImmeHis> imsHisList = HisObjCvt.cvt2HisImmeList(imsList, soDate);

            // 拼具体分表名-插入到历史表
//            String hisTableName = sendConfig.getHisTablePrefix() + subTableIndex + "_" + SendUtil.getYYYYMM(soDate);
            String hisTableName = sendConfig.getHisTablePrefix();
            Batch imsHisBatch = SendSpringUtil.getCommonDao().getClient().startBatchInsert(imsHisList.get(0), hisTableName);
            imsHisBatch.add(imsHisList);
            imsHisBatch.commit();
        }
        catch (SQLException e)
        {
        	logger.error(e,e);
        }
    }
}
