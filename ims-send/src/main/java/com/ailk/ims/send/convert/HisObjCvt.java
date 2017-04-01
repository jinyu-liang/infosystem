package com.ailk.ims.send.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSendHis;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;
import com.ailk.openbilling.persistence.jd.entity.SmsSendIntfChkHis;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsBatch;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsBatchHis;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImme;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImmeHis;

/**
 * @Description:短信表转换为对应的历史表对象（List）
 * @author wangjt
 * @Date 2012-11-1
 */
public class HisObjCvt
{
    public static List<TiOSmsImmeHis> cvt2HisImmeList(List<TiOSmsImme> objList, Date soDate)
    {
        List<TiOSmsImmeHis> hisList = new ArrayList<TiOSmsImmeHis>();
        for (TiOSmsImme obj : objList)
        {
            hisList.add(cvt2HisObj(obj, soDate));
        }
        return hisList;
    }
    
    public static List<TiOSmsBatchHis> cvt2HisBatchList(List<TiOSmsBatch> objList, Date soDate)
    {
        List<TiOSmsBatchHis> hisList = new ArrayList<TiOSmsBatchHis>();
        for (TiOSmsBatch obj : objList)
        {
            hisList.add(cvt2BatchImmeHisObj(obj, soDate));
        }
        return hisList;
    }
    
    //2013-04-17
    private static TiOSmsBatchHis cvt2BatchImmeHisObj(TiOSmsBatch tiOSmsImme, Date soDate)
    {
        TiOSmsBatchHis tiOSmsImmeHis = new TiOSmsBatchHis();
        tiOSmsImmeHis.setSmsNoticeId(tiOSmsImme.getSmsNoticeId());
        tiOSmsImmeHis.setPartitionId(tiOSmsImme.getPartitionId());
        tiOSmsImmeHis.setEparchyCode(tiOSmsImme.getEparchyCode());
        tiOSmsImmeHis.setBrandCode(tiOSmsImme.getBrandCode());
        tiOSmsImmeHis.setInModeCode(tiOSmsImme.getInModeCode());
        tiOSmsImmeHis.setSmsNetTag(tiOSmsImme.getSmsNetTag());
        tiOSmsImmeHis.setChanId(tiOSmsImme.getChanId());
        tiOSmsImmeHis.setSendObjectCode(tiOSmsImme.getSendObjectCode());
        tiOSmsImmeHis.setSendTimeCode(tiOSmsImme.getSendTimeCode());
        tiOSmsImmeHis.setSendCountCode(tiOSmsImme.getSendCountCode());
        tiOSmsImmeHis.setRecvObjectType(tiOSmsImme.getRecvObjectType());
        tiOSmsImmeHis.setRecvObject(tiOSmsImme.getRecvObject());
        tiOSmsImmeHis.setRecvId(tiOSmsImme.getRecvId());
        tiOSmsImmeHis.setSmsTypeCode(tiOSmsImme.getSmsTypeCode());
        tiOSmsImmeHis.setSmsKindCode(tiOSmsImme.getSmsKindCode());
        tiOSmsImmeHis.setNoticeContentType(tiOSmsImme.getNoticeContentType());
        tiOSmsImmeHis.setNoticeContent(tiOSmsImme.getNoticeContent());
        tiOSmsImmeHis.setReferedCount(tiOSmsImme.getReferedCount());
        tiOSmsImmeHis.setForceReferCount(tiOSmsImme.getForceReferCount());
        tiOSmsImmeHis.setForceObject(tiOSmsImme.getForceObject());
        tiOSmsImmeHis.setForceStartTime(tiOSmsImme.getForceStartTime());
        tiOSmsImmeHis.setForceEndTime(tiOSmsImme.getForceEndTime());
        tiOSmsImmeHis.setSmsPriority(tiOSmsImme.getSmsPriority());
        tiOSmsImmeHis.setReferTime(tiOSmsImme.getReferTime());
        tiOSmsImmeHis.setReferStaffId(tiOSmsImme.getReferStaffId());
        tiOSmsImmeHis.setReferDepartId(tiOSmsImme.getReferDepartId());
        tiOSmsImmeHis.setDealTime(tiOSmsImme.getDealTime());
        tiOSmsImmeHis.setDealStaffid(tiOSmsImme.getDealStaffid());
        tiOSmsImmeHis.setDealDepartid(tiOSmsImme.getDealDepartid());
        tiOSmsImmeHis.setDealState(tiOSmsImme.getDealState());
        tiOSmsImmeHis.setDoneCode(tiOSmsImme.getDoneCode());
        tiOSmsImmeHis.setBlockId(tiOSmsImme.getBlockId());
        tiOSmsImmeHis.setSmsCode(tiOSmsImme.getSmsCode());
        tiOSmsImmeHis.setRemark(tiOSmsImme.getRemark());
        tiOSmsImmeHis.setRevc1(tiOSmsImme.getRevc1());
        tiOSmsImmeHis.setRevc2(tiOSmsImme.getRevc2());
        tiOSmsImmeHis.setRevc3(tiOSmsImme.getRevc3());
        tiOSmsImmeHis.setRevc4(tiOSmsImme.getRevc4());
        tiOSmsImmeHis.setDay(tiOSmsImme.getDay());
        tiOSmsImmeHis.setMonth(tiOSmsImme.getMonth());

        tiOSmsImmeHis.setSoDate(soDate);
        return tiOSmsImmeHis;
    }

    public static List<SmsSendIntfChkHis> cvt2HisDetailList(List<SmsSendInterfaceCheck> objList, Date soDate)
    {
        List<SmsSendIntfChkHis> hisList = new ArrayList<SmsSendIntfChkHis>();
        for (SmsSendInterfaceCheck temp : objList)
        {
            hisList.add(cvt2HisObj(temp, soDate));
        }
        return hisList;
    }

    private static TiOSmsImmeHis cvt2HisObj(TiOSmsImme tiOSmsImme, Date soDate)
    {
        TiOSmsImmeHis tiOSmsImmeHis = new TiOSmsImmeHis();
        tiOSmsImmeHis.setSmsNoticeId(tiOSmsImme.getSmsNoticeId());
        tiOSmsImmeHis.setPartitionId(tiOSmsImme.getPartitionId());
        tiOSmsImmeHis.setEparchyCode(tiOSmsImme.getEparchyCode());
        tiOSmsImmeHis.setBrandCode(tiOSmsImme.getBrandCode());
        tiOSmsImmeHis.setInModeCode(tiOSmsImme.getInModeCode());
        tiOSmsImmeHis.setSmsNetTag(tiOSmsImme.getSmsNetTag());
        tiOSmsImmeHis.setChanId(tiOSmsImme.getChanId());
        tiOSmsImmeHis.setSendObjectCode(tiOSmsImme.getSendObjectCode());
        tiOSmsImmeHis.setSendTimeCode(tiOSmsImme.getSendTimeCode());
        tiOSmsImmeHis.setSendCountCode(tiOSmsImme.getSendCountCode());
        tiOSmsImmeHis.setRecvObjectType(tiOSmsImme.getRecvObjectType());
        tiOSmsImmeHis.setRecvObject(tiOSmsImme.getRecvObject());
        tiOSmsImmeHis.setRecvId(tiOSmsImme.getRecvId());
        tiOSmsImmeHis.setSmsTypeCode(tiOSmsImme.getSmsTypeCode());
        tiOSmsImmeHis.setSmsKindCode(tiOSmsImme.getSmsKindCode());
        tiOSmsImmeHis.setNoticeContentType(tiOSmsImme.getNoticeContentType());
        tiOSmsImmeHis.setNoticeContent(tiOSmsImme.getNoticeContent());
        tiOSmsImmeHis.setReferedCount(tiOSmsImme.getReferedCount());
        tiOSmsImmeHis.setForceReferCount(tiOSmsImme.getForceReferCount());
        tiOSmsImmeHis.setForceObject(tiOSmsImme.getForceObject());
        tiOSmsImmeHis.setForceStartTime(tiOSmsImme.getForceStartTime());
        tiOSmsImmeHis.setForceEndTime(tiOSmsImme.getForceEndTime());
        tiOSmsImmeHis.setSmsPriority(tiOSmsImme.getSmsPriority());
        tiOSmsImmeHis.setReferTime(tiOSmsImme.getReferTime());
        tiOSmsImmeHis.setReferStaffId(tiOSmsImme.getReferStaffId());
        tiOSmsImmeHis.setReferDepartId(tiOSmsImme.getReferDepartId());
        tiOSmsImmeHis.setDealTime(tiOSmsImme.getDealTime());
        tiOSmsImmeHis.setDealStaffid(tiOSmsImme.getDealStaffid());
        tiOSmsImmeHis.setDealDepartid(tiOSmsImme.getDealDepartid());
        tiOSmsImmeHis.setDealState(tiOSmsImme.getDealState());
        tiOSmsImmeHis.setDoneCode(tiOSmsImme.getDoneCode());
        tiOSmsImmeHis.setBlockId(tiOSmsImme.getBlockId());
        tiOSmsImmeHis.setSmsCode(tiOSmsImme.getSmsCode());
        tiOSmsImmeHis.setRemark(tiOSmsImme.getRemark());
        tiOSmsImmeHis.setRevc1(tiOSmsImme.getRevc1());
        tiOSmsImmeHis.setRevc2(tiOSmsImme.getRevc2());
        tiOSmsImmeHis.setRevc3(tiOSmsImme.getRevc3());
        tiOSmsImmeHis.setRevc4(tiOSmsImme.getRevc4());
        tiOSmsImmeHis.setDay(tiOSmsImme.getDay());
        tiOSmsImmeHis.setMonth(tiOSmsImme.getMonth());

        tiOSmsImmeHis.setSoDate(soDate);
        return tiOSmsImmeHis;
    }

    private static SmsSendIntfChkHis cvt2HisObj(SmsSendInterfaceCheck smsSendInterfaceCheck, Date soDate)
    {
        SmsSendIntfChkHis smsSendIntfChkHis = new SmsSendIntfChkHis();
        smsSendIntfChkHis.setDoneCode(smsSendInterfaceCheck.getDoneCode());
        smsSendIntfChkHis.setBillId(smsSendInterfaceCheck.getBillId());
        smsSendIntfChkHis.setMessage(smsSendInterfaceCheck.getMessage());
        smsSendIntfChkHis.setPriorityLevel(smsSendInterfaceCheck.getPriorityLevel());
        smsSendIntfChkHis.setSendDate(smsSendInterfaceCheck.getSendDate());
        smsSendIntfChkHis.setDoneDate(smsSendInterfaceCheck.getDoneDate());
        smsSendIntfChkHis.setDealDate(smsSendInterfaceCheck.getDealDate());
        smsSendIntfChkHis.setSrcAddr(smsSendInterfaceCheck.getSrcAddr());
        smsSendIntfChkHis.setRequestReport(smsSendInterfaceCheck.getRequestReport());
        smsSendIntfChkHis.setSmsCode(smsSendInterfaceCheck.getSmsCode());
        smsSendIntfChkHis.setBlockId(smsSendInterfaceCheck.getBlockId());
        smsSendIntfChkHis.setExt1(smsSendInterfaceCheck.getExt1());
        smsSendIntfChkHis.setExt2(smsSendInterfaceCheck.getExt2());
        smsSendIntfChkHis.setFlag(smsSendInterfaceCheck.getFlag());

        smsSendIntfChkHis.setSoDate(soDate);
        return smsSendIntfChkHis;

    }

    public static CheckSmsSendHis cvt2HisObj(CheckSmsSend checkSmsSend, Date soDate)
    {
        CheckSmsSendHis checkSmsSendHis = new CheckSmsSendHis();
        checkSmsSendHis.setBlockId(checkSmsSend.getBlockId());
        checkSmsSendHis.setModCode(checkSmsSend.getModCode());
        checkSmsSendHis.setSendDate(checkSmsSend.getSendDate());
        checkSmsSendHis.setDoneDate(checkSmsSend.getDoneDate());
        checkSmsSendHis.setSendMod(checkSmsSend.getSendMod());
        checkSmsSendHis.setDataSource(checkSmsSend.getDataSource());
        checkSmsSendHis.setCheckStatus(checkSmsSend.getCheckStatus());
        checkSmsSendHis.setCheckOper(checkSmsSend.getCheckOper());
        checkSmsSendHis.setCheckDate(checkSmsSend.getCheckDate());
        checkSmsSendHis.setNote(checkSmsSend.getNote());
        checkSmsSendHis.setDoneStatus(checkSmsSend.getDoneStatus());
        checkSmsSendHis.setLastSendTime(checkSmsSend.getLastSendTime());
        checkSmsSendHis.setSendNum(checkSmsSend.getSendNum());
        checkSmsSendHis.setExt1(checkSmsSend.getExt1());
        checkSmsSendHis.setExt2(checkSmsSend.getExt2());

        checkSmsSendHis.setSoDate(soDate);
        return checkSmsSendHis;
    }
}
