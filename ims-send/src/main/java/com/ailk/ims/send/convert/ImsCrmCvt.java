package com.ailk.ims.send.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.ims.send.util.SendUtil;
import com.ailk.openbilling.persistence.crmsms.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.crmsms.entity.SmsSendInterfaceCheck;
import com.ailk.openbilling.persistence.crmsms.entity.TiOSmsBatch;
import com.ailk.openbilling.persistence.crmsms.entity.TiOSmsImme;
import com.ailk.openbilling.persistence.jd.entity.ImsNoAuthSmsDetail;
import com.ailk.openbilling.persistence.jd.entity.ImsNoAuthSmsSend;
import com.ailk.openbilling.persistence.jd.entity.ImsNoAuthTiImme;

/**
 * @Description:IMS对象转换为crm对象(List)
 * @author lijc3
 * @Date 2012-11-1
 */
public class ImsCrmCvt
{
    
    public static List<TiOSmsBatch> cvt2CrmImmeBatchList(List<com.ailk.openbilling.persistence.jd.entity.TiOSmsBatch> imsList)
    {
        List<TiOSmsBatch> crmList = new ArrayList<TiOSmsBatch>();
        for (com.ailk.openbilling.persistence.jd.entity.TiOSmsBatch check : imsList)
        {
            crmList.add(cvt2CrmBatchImmeObj(check));
        }
        return crmList;
    }
    
    //2013-04-17
    private static TiOSmsBatch cvt2CrmBatchImmeObj(com.ailk.openbilling.persistence.jd.entity.TiOSmsBatch imsObj)
    {
        TiOSmsBatch crmObj = new TiOSmsBatch();
        crmObj.setSmsNoticeId(imsObj.getSmsNoticeId());
        crmObj.setPartitionId(imsObj.getPartitionId());
        crmObj.setEparchyCode(imsObj.getEparchyCode());
        crmObj.setBrandCode(imsObj.getBrandCode());
        crmObj.setInModeCode(imsObj.getInModeCode());
        crmObj.setSmsNetTag(imsObj.getSmsNetTag());
        crmObj.setChanId(imsObj.getChanId());
        crmObj.setSendObjectCode(imsObj.getSendObjectCode());
        crmObj.setSendTimeCode(imsObj.getSendTimeCode());
        crmObj.setSendCountCode(imsObj.getSendCountCode());
        crmObj.setRecvObjectType(imsObj.getRecvObjectType());
        crmObj.setRecvObject(imsObj.getRecvObject());
        crmObj.setRecvId(imsObj.getRecvId());
        crmObj.setSmsTypeCode(imsObj.getSmsTypeCode());
        crmObj.setSmsKindCode(imsObj.getSmsKindCode());
        crmObj.setNoticeContentType(imsObj.getNoticeContentType());
        crmObj.setNoticeContent(imsObj.getNoticeContent());
        crmObj.setReferedCount(imsObj.getReferedCount());
        crmObj.setForceReferCount(imsObj.getForceReferCount());
        crmObj.setForceObject(imsObj.getForceObject());
        crmObj.setForceStartTime(imsObj.getForceStartTime());
        crmObj.setForceEndTime(imsObj.getForceEndTime());
        crmObj.setSmsPriority(imsObj.getSmsPriority());
        crmObj.setReferTime(imsObj.getReferTime());
        crmObj.setReferStaffId(imsObj.getReferStaffId());
        crmObj.setReferDepartId(imsObj.getReferDepartId());
        crmObj.setDealTime(imsObj.getDealTime());
        crmObj.setDealStaffid(imsObj.getDealStaffid());
        crmObj.setDealDepartid(imsObj.getDealDepartid());
        crmObj.setDealState(imsObj.getDealState());
        crmObj.setDoneCode(imsObj.getDoneCode());
        crmObj.setBlockId(imsObj.getBlockId());
        crmObj.setSmsCode(imsObj.getSmsCode());
        crmObj.setRemark(imsObj.getRemark());
        crmObj.setRevc1(imsObj.getRevc1());
        crmObj.setRevc2(imsObj.getRevc2());
        crmObj.setRevc3(imsObj.getRevc3());
        crmObj.setRevc4(imsObj.getRevc4());
        crmObj.setMonth(imsObj.getMonth());
        crmObj.setDay(imsObj.getDay());
        return crmObj;
    }
    
    public static List<TiOSmsImme> cvt2CrmImmeList(List<com.ailk.openbilling.persistence.jd.entity.TiOSmsImme> imsList)
    {
        List<TiOSmsImme> crmList = new ArrayList<TiOSmsImme>();
        for (com.ailk.openbilling.persistence.jd.entity.TiOSmsImme check : imsList)
        {
            crmList.add(cvt2CrmObj(check));
        }
        return crmList;
    }

    public static List<SmsSendInterfaceCheck> cvt2CrmDetailList(
            List<com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck> imsList)
    {
        List<SmsSendInterfaceCheck> crmList = new ArrayList<SmsSendInterfaceCheck>();
        for (com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck check : imsList)
        {
            crmList.add(cvt2CrmObj(check));
        }
        return crmList;
    }

    private static TiOSmsImme cvt2CrmObj(com.ailk.openbilling.persistence.jd.entity.TiOSmsImme imsObj)
    {
        TiOSmsImme crmObj = new TiOSmsImme();
        crmObj.setSmsNoticeId(imsObj.getSmsNoticeId());
        crmObj.setPartitionId(imsObj.getPartitionId());
        crmObj.setEparchyCode(imsObj.getEparchyCode());
        crmObj.setBrandCode(imsObj.getBrandCode());
        crmObj.setInModeCode(imsObj.getInModeCode());
        crmObj.setSmsNetTag(imsObj.getSmsNetTag());
        crmObj.setChanId(imsObj.getChanId());
        crmObj.setSendObjectCode(imsObj.getSendObjectCode());
        crmObj.setSendTimeCode(imsObj.getSendTimeCode());
        crmObj.setSendCountCode(imsObj.getSendCountCode());
        crmObj.setRecvObjectType(imsObj.getRecvObjectType());
        crmObj.setRecvObject(imsObj.getRecvObject());
        crmObj.setRecvId(imsObj.getRecvId());
        crmObj.setSmsTypeCode(imsObj.getSmsTypeCode());
        crmObj.setSmsKindCode(imsObj.getSmsKindCode());
        crmObj.setNoticeContentType(imsObj.getNoticeContentType());
        crmObj.setNoticeContent(imsObj.getNoticeContent());
        crmObj.setReferedCount(imsObj.getReferedCount());
        crmObj.setForceReferCount(imsObj.getForceReferCount());
        crmObj.setForceObject(imsObj.getForceObject());
        crmObj.setForceStartTime(imsObj.getForceStartTime());
        crmObj.setForceEndTime(imsObj.getForceEndTime());
        crmObj.setSmsPriority(imsObj.getSmsPriority());
        crmObj.setReferTime(imsObj.getReferTime());
        crmObj.setReferStaffId(imsObj.getReferStaffId());
        crmObj.setReferDepartId(imsObj.getReferDepartId());
        crmObj.setDealTime(imsObj.getDealTime());
        crmObj.setDealStaffid(imsObj.getDealStaffid());
        crmObj.setDealDepartid(imsObj.getDealDepartid());
        crmObj.setDealState(imsObj.getDealState());
        crmObj.setDoneCode(0L);
        crmObj.setBlockId(imsObj.getBlockId());
        crmObj.setSmsCode(imsObj.getSmsCode());
        crmObj.setRemark(imsObj.getRemark());
        crmObj.setRevc1(imsObj.getRevc1());
        crmObj.setRevc2(imsObj.getRevc2());
        crmObj.setRevc3(imsObj.getRevc3());
        crmObj.setRevc4(imsObj.getRevc4());
        crmObj.setMonth(imsObj.getMonth());
        crmObj.setDay(imsObj.getDay());
        return crmObj;
    }
    /**
     * 转换成认证失败的实时短信对象
     * @param imsObj
     * @return
     */
    public static ImsNoAuthTiImme cvt2NoAuthObj(com.ailk.openbilling.persistence.jd.entity.TiOSmsImme imsObj)
    {
        ImsNoAuthTiImme crmObj = new ImsNoAuthTiImme();
        crmObj.setSmsNoticeId(imsObj.getSmsNoticeId());
        crmObj.setPartitionId(imsObj.getPartitionId());
        crmObj.setEparchyCode(imsObj.getEparchyCode());
        crmObj.setBrandCode(imsObj.getBrandCode());
        crmObj.setInModeCode(imsObj.getInModeCode());
        crmObj.setSmsNetTag(imsObj.getSmsNetTag());
        crmObj.setChanId(imsObj.getChanId());
        crmObj.setSendObjectCode(imsObj.getSendObjectCode());
        crmObj.setSendTimeCode(imsObj.getSendTimeCode());
        crmObj.setSendCountCode(imsObj.getSendCountCode());
        crmObj.setRecvObjectType(imsObj.getRecvObjectType());
        crmObj.setRecvObject(imsObj.getRecvObject());
        crmObj.setRecvId(imsObj.getRecvId());
        crmObj.setSmsTypeCode(imsObj.getSmsTypeCode());
        crmObj.setSmsKindCode(imsObj.getSmsKindCode());
        crmObj.setNoticeContentType(imsObj.getNoticeContentType());
        crmObj.setNoticeContent(imsObj.getNoticeContent());
        crmObj.setReferedCount(imsObj.getReferedCount());
        crmObj.setForceReferCount(imsObj.getForceReferCount());
        crmObj.setForceObject(imsObj.getForceObject());
        crmObj.setForceStartTime(imsObj.getForceStartTime());
        crmObj.setForceEndTime(imsObj.getForceEndTime());
        crmObj.setSmsPriority(imsObj.getSmsPriority());
        crmObj.setReferTime(imsObj.getReferTime());
        crmObj.setReferStaffId(imsObj.getReferStaffId());
        crmObj.setReferDepartId(imsObj.getReferDepartId());
        crmObj.setDealTime(imsObj.getDealTime());
        crmObj.setDealStaffid(imsObj.getDealStaffid());
        crmObj.setDealDepartid(imsObj.getDealDepartid());
        crmObj.setDealState(imsObj.getDealState());
        crmObj.setDoneCode(imsObj.getDoneCode());
        crmObj.setBlockId(imsObj.getBlockId());
        crmObj.setSmsCode(imsObj.getSmsCode());
        crmObj.setRemark(imsObj.getRemark());
        crmObj.setRevc1(imsObj.getRevc1());
        crmObj.setRevc2(imsObj.getRevc2());
        crmObj.setRevc3(imsObj.getRevc3());
        crmObj.setRevc4(imsObj.getRevc4());
        crmObj.setMonth(imsObj.getMonth());
        crmObj.setDay(imsObj.getDay());
        return crmObj;
    }

    private static SmsSendInterfaceCheck cvt2CrmObj(com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck imsObj)
    {
        SmsSendInterfaceCheck crmObj = new SmsSendInterfaceCheck();
        crmObj.setDoneCode(imsObj.getDoneCode());
        crmObj.setBillId(imsObj.getBillId());
        crmObj.setMessage(imsObj.getMessage());
        crmObj.setPriorityLevel(imsObj.getPriorityLevel());
        crmObj.setSendDate(imsObj.getSendDate());
        crmObj.setDoneDate(imsObj.getDoneDate());
        crmObj.setDealDate(imsObj.getDealDate());
        crmObj.setSrcAddr(imsObj.getSrcAddr());
        crmObj.setRequestReport(imsObj.getRequestReport());
        crmObj.setFlag(imsObj.getFlag());
        crmObj.setSmsCode(imsObj.getSmsCode());
        crmObj.setBlockId(imsObj.getBlockId());
        crmObj.setExt1(imsObj.getExt1());
        crmObj.setExt2(imsObj.getExt2());
        return crmObj;
    }
    /**
     * 转换成认证失败明细表
     * @param imsObj
     * @return
     */
    public static ImsNoAuthSmsDetail cvt2NoAuthObj(com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck imsObj)
    {
        ImsNoAuthSmsDetail crmObj = new ImsNoAuthSmsDetail();
        crmObj.setDoneCode(imsObj.getDoneCode());
        crmObj.setBillId(imsObj.getBillId());
        crmObj.setMessage(imsObj.getMessage());
        crmObj.setPriorityLevel(imsObj.getPriorityLevel());
        crmObj.setSendDate(imsObj.getSendDate());
        crmObj.setDoneDate(imsObj.getDoneDate());
        crmObj.setDealDate(imsObj.getDealDate());
        crmObj.setSrcAddr(imsObj.getSrcAddr());
        crmObj.setRequestReport(imsObj.getRequestReport());
        crmObj.setFlag(imsObj.getFlag());
        crmObj.setSmsCode(imsObj.getSmsCode());
        crmObj.setBlockId(imsObj.getBlockId());
        crmObj.setExt1(imsObj.getExt1());
        crmObj.setExt2(imsObj.getExt2());
        return crmObj;
    }

    public static CheckSmsSend cvt2CrmObj(com.ailk.openbilling.persistence.jd.entity.CheckSmsSend imsObj)
    {
        Date date = SendUtil.currentDate();
        CheckSmsSend crmObj = new CheckSmsSend();
        crmObj.setBlockId(imsObj.getBlockId());
        crmObj.setModCode(imsObj.getModCode());
        crmObj.setSendDate(date);
        crmObj.setDoneDate(date);
        crmObj.setSendMod(imsObj.getSendMod());
        crmObj.setDataSource(imsObj.getDataSource());
        crmObj.setCheckStatus(0);
        crmObj.setCheckOper(imsObj.getCheckOper());
        crmObj.setCheckDate(imsObj.getCheckDate());
        crmObj.setNote(imsObj.getNote());
        crmObj.setDoneStatus(imsObj.getDoneStatus());
        crmObj.setLastSendTime(imsObj.getLastSendTime());
        crmObj.setSendNum(imsObj.getSendNum());
        crmObj.setExt1("10086");
        crmObj.setExt2(imsObj.getExt2());
        return crmObj;
    }
    /**
     * 转换成认证失败总表
     * @param imsObj
     * @return
     */
    public static ImsNoAuthSmsSend cvt2NoAuthObj(com.ailk.openbilling.persistence.jd.entity.CheckSmsSend imsObj)
    {
        Date date = SendUtil.currentDate();
        ImsNoAuthSmsSend crmObj = new ImsNoAuthSmsSend();
        crmObj.setBlockId(imsObj.getBlockId());
        crmObj.setModCode(imsObj.getModCode());
        crmObj.setSendDate(date);
        crmObj.setDoneDate(date);
        crmObj.setSendMod(imsObj.getSendMod());
        crmObj.setDataSource(imsObj.getDataSource());
        crmObj.setCheckStatus(0);
        crmObj.setCheckOper(imsObj.getCheckOper());
        crmObj.setCheckDate(imsObj.getCheckDate());
        crmObj.setNote(imsObj.getNote());
        crmObj.setDoneStatus(imsObj.getDoneStatus());
        crmObj.setLastSendTime(imsObj.getLastSendTime());
        crmObj.setSendNum(imsObj.getSendNum());
        crmObj.setExt1("10086");
        crmObj.setExt2(imsObj.getExt2());
        return crmObj;
    }
    
    
    
    
}
