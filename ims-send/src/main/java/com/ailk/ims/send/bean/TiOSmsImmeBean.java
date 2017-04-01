package com.ailk.ims.send.bean;

import java.util.Date;
import oracle.sql.ROWID;

public class TiOSmsImmeBean
{

    /**
     * 短信通知流水号（不能重复） 每个内部系统分配不同的流水范围(20000000000---29999999999) create sequence SEQ_NOTICE_ID minvalue 20000000000 maxvalue
     * 29999999999 start with 20000000000 increment by 1 cache 4000 cycle;
     */
    private long smsNoticeId;

    /**
     * 分区标识，取值为SMS_NOTICE_ID的后3位
     */
    private Integer partitionId;

    /**
     * 地州编码，固定填0021
     */
    private String eparchyCode;

    /**
     * 品牌：可为空
     */
    private String brandCode;

    /**
     * 接入方式编码，取值： 其他接入：0 人工接入：1 自助接入：2
     */
    private String inModeCode;

    /**
     * 短信回执标记，取值： 需要回执：1 不需要回执：0
     */
    private String smsNetTag;

    /**
     * 渠道编码，取值： 营业：A001 账管：B001 小I：C001 鸿冠：C002 4A：C003 网管：C004
     */
    private String chanId;

    /**
     * 发送方号码编码，可为空
     */
    private Integer sendObjectCode;

    /**
     * 发送时间编码，固定填1
     */
    private Integer sendTimeCode;

    /**
     * 发送次数编码，可为空
     */
    private Integer sendCountCode;

    /**
     * 接收号码类型： 00－手机号码 01－组ID 02－集团ID
     */
    private String recvObjectType;

    /**
     * 取值为USER_ID或者0
     */
    private String recvObject;

    private Long recvId;

    /**
     * 短信类型编码，取值： 文本短信：20 PUSH短信：10 二进制短信：00
     */
    private String smsTypeCode;

    /**
     * 短信通道编码，不同通道的短信相互不影响，避免对下发资源的争抢，取值： 01-保留 02-保留 03-07分给营业管理自己定义 08-一级BOSS 09-10 帐务管理
     */
    private String smsKindCode;

    /**
     * 0－指定内容发送
     */
    private String noticeContentType;

    private String noticeContent;

    /**
     * 发送次数：填0
     */
    private Integer referedCount;

    /**
     * 指定发送次数：填1
     */
    private Integer forceReferCount;

    /**
     * 发送号码：10086或者10086+扩展码
     */
    private String forceObject;

    /**
     * 指定起始时间：可为空或者指定时间
     */
    private Date forceStartTime;

    /**
     * 指定终止时间：可为空或者指定时间
     */
    private Date forceEndTime;

    /**
     * 短信优先级，取值越大优先级越低
     */
    private Integer smsPriority;

    private Date referTime;

    /**
     * 提交员工：可为空
     */
    private String referStaffId;

    /**
     * 提交部门：可为空
     */
    private String referDepartId;

    private Date dealTime;

    /**
     * 完成员工：可为空
     */
    private String dealStaffid;

    /**
     * 完成部门：可为空
     */
    private String dealDepartid;

    /**
     * 15－未处理
     */
    private String dealState;

    private Long doneCode;

    private Long blockId;

    private Long smsCode;

    private String remark;

    private String revc1;

    private String revc2;

    private String revc3;

    private String revc4;

    private Integer month;

    private Integer day;
    
    private ROWID rowid;
    

    public long getSmsNoticeId()
    {
        return smsNoticeId;
    }

    public void setSmsNoticeId(long smsNoticeId)
    {
        this.smsNoticeId = smsNoticeId;
    }

    public Integer getPartitionId()
    {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId)
    {
        this.partitionId = partitionId;
    }

    public String getEparchyCode()
    {
        return eparchyCode;
    }

    public void setEparchyCode(String eparchyCode)
    {
        this.eparchyCode = eparchyCode;
    }

    public String getBrandCode()
    {
        return brandCode;
    }

    public void setBrandCode(String brandCode)
    {
        this.brandCode = brandCode;
    }

    public String getInModeCode()
    {
        return inModeCode;
    }

    public void setInModeCode(String inModeCode)
    {
        this.inModeCode = inModeCode;
    }

    public String getSmsNetTag()
    {
        return smsNetTag;
    }

    public void setSmsNetTag(String smsNetTag)
    {
        this.smsNetTag = smsNetTag;
    }

    public String getChanId()
    {
        return chanId;
    }

    public void setChanId(String chanId)
    {
        this.chanId = chanId;
    }

    public Integer getSendObjectCode()
    {
        return sendObjectCode;
    }

    public void setSendObjectCode(Integer sendObjectCode)
    {
        this.sendObjectCode = sendObjectCode;
    }

    public Integer getSendTimeCode()
    {
        return sendTimeCode;
    }

    public void setSendTimeCode(Integer sendTimeCode)
    {
        this.sendTimeCode = sendTimeCode;
    }

    public Integer getSendCountCode()
    {
        return sendCountCode;
    }

    public void setSendCountCode(Integer sendCountCode)
    {
        this.sendCountCode = sendCountCode;
    }

    public String getRecvObjectType()
    {
        return recvObjectType;
    }

    public void setRecvObjectType(String recvObjectType)
    {
        this.recvObjectType = recvObjectType;
    }

    public String getRecvObject()
    {
        return recvObject;
    }

    public void setRecvObject(String recvObject)
    {
        this.recvObject = recvObject;
    }

    public Long getRecvId()
    {
        return recvId;
    }

    public void setRecvId(Long recvId)
    {
        this.recvId = recvId;
    }

    public String getSmsTypeCode()
    {
        return smsTypeCode;
    }

    public void setSmsTypeCode(String smsTypeCode)
    {
        this.smsTypeCode = smsTypeCode;
    }

    public String getSmsKindCode()
    {
        return smsKindCode;
    }

    public void setSmsKindCode(String smsKindCode)
    {
        this.smsKindCode = smsKindCode;
    }

    public String getNoticeContentType()
    {
        return noticeContentType;
    }

    public void setNoticeContentType(String noticeContentType)
    {
        this.noticeContentType = noticeContentType;
    }

    public String getNoticeContent()
    {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent)
    {
        this.noticeContent = noticeContent;
    }

    public Integer getReferedCount()
    {
        return referedCount;
    }

    public void setReferedCount(Integer referedCount)
    {
        this.referedCount = referedCount;
    }

    public Integer getForceReferCount()
    {
        return forceReferCount;
    }

    public void setForceReferCount(Integer forceReferCount)
    {
        this.forceReferCount = forceReferCount;
    }

    public String getForceObject()
    {
        return forceObject;
    }

    public void setForceObject(String forceObject)
    {
        this.forceObject = forceObject;
    }

    public Date getForceStartTime()
    {
        return forceStartTime;
    }

    public void setForceStartTime(Date forceStartTime)
    {
        this.forceStartTime = forceStartTime;
    }

    public Date getForceEndTime()
    {
        return forceEndTime;
    }

    public void setForceEndTime(Date forceEndTime)
    {
        this.forceEndTime = forceEndTime;
    }

    public Integer getSmsPriority()
    {
        return smsPriority;
    }

    public void setSmsPriority(Integer smsPriority)
    {
        this.smsPriority = smsPriority;
    }

    public Date getReferTime()
    {
        return referTime;
    }

    public void setReferTime(Date referTime)
    {
        this.referTime = referTime;
    }

    public String getReferStaffId()
    {
        return referStaffId;
    }

    public void setReferStaffId(String referStaffId)
    {
        this.referStaffId = referStaffId;
    }

    public String getReferDepartId()
    {
        return referDepartId;
    }

    public void setReferDepartId(String referDepartId)
    {
        this.referDepartId = referDepartId;
    }

    public Date getDealTime()
    {
        return dealTime;
    }

    public void setDealTime(Date dealTime)
    {
        this.dealTime = dealTime;
    }

    public String getDealStaffid()
    {
        return dealStaffid;
    }

    public void setDealStaffid(String dealStaffid)
    {
        this.dealStaffid = dealStaffid;
    }

    public String getDealDepartid()
    {
        return dealDepartid;
    }

    public void setDealDepartid(String dealDepartid)
    {
        this.dealDepartid = dealDepartid;
    }

    public String getDealState()
    {
        return dealState;
    }

    public void setDealState(String dealState)
    {
        this.dealState = dealState;
    }

    public Long getDoneCode()
    {
        return doneCode;
    }

    public void setDoneCode(Long doneCode)
    {
        this.doneCode = doneCode;
    }

    public Long getBlockId()
    {
        return blockId;
    }

    public void setBlockId(Long blockId)
    {
        this.blockId = blockId;
    }

    public Long getSmsCode()
    {
        return smsCode;
    }

    public void setSmsCode(Long smsCode)
    {
        this.smsCode = smsCode;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRevc1()
    {
        return revc1;
    }

    public void setRevc1(String revc1)
    {
        this.revc1 = revc1;
    }

    public String getRevc2()
    {
        return revc2;
    }

    public void setRevc2(String revc2)
    {
        this.revc2 = revc2;
    }

    public String getRevc3()
    {
        return revc3;
    }

    public void setRevc3(String revc3)
    {
        this.revc3 = revc3;
    }

    public String getRevc4()
    {
        return revc4;
    }

    public void setRevc4(String revc4)
    {
        this.revc4 = revc4;
    }

    public Integer getMonth()
    {
        return month;
    }

    public void setMonth(Integer month)
    {
        this.month = month;
    }

    public Integer getDay()
    {
        return day;
    }

    public void setDay(Integer day)
    {
        this.day = day;
    }

    public ROWID getRowid()
    {
        return rowid;
    }

    public void setRowid(ROWID rowid)
    {
        this.rowid = rowid;
    }
    
}