package com.ailk.ims.util;

import java.util.Date;
import org.apache.log4j.Logger;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.ImsSendRsp;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * 业务流水记录工具类，跟业务流水记录相关的操作可以放在这里定义
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2
 * @Date 2012-3-20 luojb 删除ImsReceiveRecord、ImsReceiveRsp的引用
 * @Date 2012-4-17 wangjt delete insertSendRecord method
 * @Date 2012-4-17 wangjt delete getSendDoneCode method
 */
public class BusiRecordUtil
{
    private static ImsLogger logger = new ImsLogger(BusiRecordUtil.class);
    /**
     * @Description: 根据done_code（信息管理流水号）查询请求流水记录
     * @author : wuyj
     * @date : 2011-10-5
     * @param doneCode
     * @return
     */
    public static CmBusi queryReceiveRecordByDoneCode(Long doneCode)
    {
        return DBUtil.querySingle(CmBusi.class, new DBCondition(CmBusi.Field.soNbr, doneCode));
    }

    /**
     * @Description: 根据crm流水号查询请求流水记录
     * @author : wuyj
     * @date : 2011-10-5
     * @param soNbr
     * @return
     */
    public static CmBusi queryReceiveRecordByOutSoNbr(String outSoNbr)
    {
        return DBUtil.querySingle(CmBusi.class, new DBCondition(CmBusi.Field.outerSoNbr, outSoNbr));
    }

    /**
     * @Description: 根据外部soNbr获取内部工单号
     * @param outerSoNbr
     * @return
     * @author: tangjl5
     * @Date: 2011-12-12
     */
    public static CmBusi queryCmBusiByOuterSoNbr(String outerSoNbr)
    {
        return DBUtil.querySingle(CmBusi.class, new DBCondition(CmBusi.Field.outerSoNbr, outerSoNbr));
    }

    /**
     * 根据外部soNbr和stepId获取内部工单号 luojb 2012-3-8
     */
    public static CmBusi queryCmBusiByOuterSoNbrAndStepId(String outerSoNbr, Integer stepId)
    {
        return DBUtil.querySingle(CmBusi.class, new DBCondition(CmBusi.Field.outerSoNbr, outerSoNbr), new DBCondition(
                CmBusi.Field.stepId, stepId));
    }

    public static SOperInfo buildOperInfo(long doneCode)
    {
        CmBusi receiveRec = DBUtil.querySingle(CmBusi.class, new DBCondition(CmBusi.Field.soNbr, doneCode));
        if (receiveRec == null)
            return null;
        SOperInfo operInfo = new SOperInfo();

        operInfo.setBusi_code(receiveRec.getBusiCode());
        if (receiveRec.getCountyCode() != null)
            operInfo.setCounty_code(receiveRec.getCountyCode().shortValue());
        if (receiveRec.getIsnormal() != null)
            operInfo.setIsnormal(receiveRec.getIsnormal().shortValue());
        if (receiveRec.getOpId() != null)
            operInfo.setOp_id(receiveRec.getOpId());
        if (receiveRec.getOrgId() != null)
            operInfo.setOrg_id(receiveRec.getOrgId().intValue());
        operInfo.setPhone_id(receiveRec.getPhoneId());
        if (receiveRec.getProvCode() != null)
            operInfo.setProv_code(receiveRec.getProvCode().shortValue());
        if (receiveRec.getRegionCode() != null)
            operInfo.setRegion_code(receiveRec.getRegionCode().shortValue());
        if (receiveRec.getOrigSoNbr() != null)
            operInfo.setRso_nbr(receiveRec.getOrigSoNbr().toString());
        if (receiveRec.getSoDate() != null)
            operInfo.setSo_date(DateUtil.formatDate(receiveRec.getSoDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        if (receiveRec.getChannel() != null)
            operInfo.setSo_mode(receiveRec.getChannel().shortValue());
        operInfo.setSo_nbr(receiveRec.getOuterSoNbr());
        return operInfo;
    }

    /**
     * 存储发送应答流水记录
     */
    public static void insertSendRsp(IMSContext context)
    {
        try
        {
            long done_code = context.getDoneCode();
            SOperInfo oper = context.getOper();
            ImsSendRsp rsp = new ImsSendRsp();
            CBSErrorMsg errorMsg = context.getCbsErrorMsg();
            if (errorMsg != null)
            {
                rsp.setResultCode(errorMsg.getResult_code());
                rsp.setResultMsg(errorMsg.getError_msg());
            }
            rsp.setDoneCode(done_code);
            rsp.setDoneTime(context.getRequestDate());
            rsp.setBusiCode(oper.getBusi_code());
            rsp.setPhoneNo(oper.getPhone_id());
            // receiveDM.setTarget(target);
            // receiveDM.setDestination(destination);
            rsp.setSoNbr(oper.getSo_nbr());
            rsp.setSoMode((int) oper.getSo_mode());
            rsp.setSoDate((DateUtil.getFormattedDate(oper.getSo_date())));
            if (oper.getIsnormal() != null)
                rsp.setIsnormal((int) oper.getIsnormal());
            if (oper.getRso_nbr() != null)
                rsp.setRsoNbr(oper.getRso_nbr().toString());
            if (oper.getProv_code() != null)
                rsp.setProvCode((int) oper.getProv_code());
            if (oper.getRegion_code() != null)
                rsp.setRegionCode((int) oper.getRegion_code());
            if (oper.getCounty_code() != null)
                rsp.setCountyCode((int) oper.getCounty_code());
            if (oper.getOp_id() != null)
                rsp.setOpId((long) oper.getOp_id());
            if (oper.getOrg_id() != null)
                rsp.setOrgId((long) oper.getOrg_id());
            if (CommonUtil.isNotEmpty(oper.getRemark()))
                rsp.setRemark(oper.getRemark());

            rsp.setProcessContent(saveReceiveRspMessage(context));

            DBUtil.insert(rsp);

            //context.setSendRsp(rsp);
        }
        catch (Exception e)
        {
            Logger.getLogger(BusiRecordUtil.class).error(e, e);
        }
    }

    /**
     * 存储发送成功流水记录
     */
    public static void insertCmSend(IMSContext context)
    {
        // try
        // {
        // long done_code = context.getDoneCode();
        // SOperInfo oper = context.getOper();
        // CmSend rsp = new CmSend();
        // CBSErrorMsg errorMsg = context.getCbsErrorMsg();
        // if (errorMsg != null)
        // {
        // rsp.setResultCode(errorMsg.getResult_code());
        // rsp.setResultMsg(errorMsg.getError_msg());
        // }
        // rsp.setDoneCode(done_code);
        // rsp.setDoneTime(context.getRequestDate());
        // rsp.setBusiCode(oper.getBusi_code());
        // rsp.setPhoneNo(oper.getPhone_id());
        // // receiveDM.setTarget(target);
        // // receiveDM.setDestination(destination);
        // rsp.setSoNbr(oper.getSo_nbr());
        // rsp.setSoMode((int) oper.getSo_mode());
        // rsp.setSoDate((DateUtil.getFormattedDate(oper.getSo_date())));
        // if (oper.getIsnormal() != null)
        // rsp.setIsnormal((int) oper.getIsnormal());
        // if (oper.getRso_nbr() != null)
        // rsp.setRsoNbr(oper.getRso_nbr().toString());
        // if (oper.getProv_code() != null)
        // rsp.setProvCode((int) oper.getProv_code());
        // if (oper.getRegion_code() != null)
        // rsp.setRegionCode((int) oper.getRegion_code());
        // if (oper.getCounty_code() != null)
        // rsp.setCountyCode((int) oper.getCounty_code());
        // if (oper.getOp_id() != null)
        // rsp.setOpId((long) oper.getOp_id());
        // if (oper.getOrg_id() != null)
        // rsp.setOrgId((long) oper.getOrg_id());
        // if (CommonUtil.isNotEmpty(oper.getRemark()))
        // rsp.setRemark(oper.getRemark());
        //
        // rsp.setProcessContent(saveReceiveRspMessage(context));
        //
        // DBUtil.insert(rsp);
        //
        // // context.setSendRsp(rsp);
        // }
        // catch (Exception e)
        // {
        // Logger.getLogger(BusiRecordUtil.class).error(e, e);
        // }
    }

    /**
     * 存储接收报文，存储路径在数据库中配置
     */
    public static String saveReceiveMessage(IMSContext context)
    {
        // 获取报文报文路径
        String filepath = IMSUtil.getMessageSavePath(context);

        try
        {
            FileUtil.createFile(filepath);
            FileUtil.writeFile(filepath, context.getSoap());
        }
        catch (Exception e)
        {
            logger.error("failed to save message to file: " + filepath, e);
        }
        return filepath;
    }

    /**
     * 存储接收应答报文，存储路径在数据库中配置
     */
    public static String saveReceiveRspMessage(IMSContext context)
    {
        return null;
    }

    // /**
    // * 信息管理接受流水号加上请求时间 ljc 2011-10-6
    // *
    // * @param context
    // * @return doneCode 格式为 yymmhh+doneCode
    // */
    // public static long getReceiveDoneCode(IMSContext context)
    // {
    // long doneCode = DBUtil.getSequence(CmBusi.class);
    // String requestDate = DateUtil.formatDate(context.getRequestDate(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
    // requestDate = requestDate.substring(2, 8);// 取yymmhh
    // requestDate += doneCode;
    // return Long.parseLong(requestDate);
    // }

    public static long getReceiveDoneCode(Date currentDate)
    {
        long doneCode = DBUtil.getSequence(CmBusi.class);
        String requestDate = DateUtil.formatDate(currentDate, DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
        requestDate = requestDate.substring(2, 8);// 取yymmhh
        requestDate += doneCode;
        return Long.parseLong(requestDate);
    }
}
