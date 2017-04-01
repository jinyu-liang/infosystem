package com.ailk.ims.send.convert;

import com.ailk.ims.send.bean.TiOSmsImmeBean;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsBatch;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImme;


/**
 * 将游标返回的对象转成数据库的对象
 * @Description
 * @author wukl
 * @Date 2013-1-7
 */
public class IteratorCvt
{
    
    /**
     * 实时短信表的转换
     * wukl 2013-1-7
     * @param immeBean
     * @return
     */
    public static TiOSmsBatch immeSmsBatchCvt(TiOSmsImmeBean immeBean)
    {
        TiOSmsBatch tiOSmsImme = new TiOSmsBatch();
        tiOSmsImme.setSmsNoticeId(immeBean.getSmsNoticeId());
        tiOSmsImme.setPartitionId(immeBean.getPartitionId());
        tiOSmsImme.setEparchyCode(immeBean.getEparchyCode());
        tiOSmsImme.setBrandCode(immeBean.getBrandCode());
        tiOSmsImme.setInModeCode(immeBean.getInModeCode());
        tiOSmsImme.setSmsNetTag(immeBean.getSmsNetTag());
        tiOSmsImme.setChanId(immeBean.getChanId());
        tiOSmsImme.setSendObjectCode(immeBean.getSendObjectCode());
        tiOSmsImme.setSendTimeCode(immeBean.getSendTimeCode());
        tiOSmsImme.setSendCountCode(immeBean.getSendCountCode());
        tiOSmsImme.setRecvObjectType(immeBean.getRecvObjectType());
        tiOSmsImme.setRecvObject(immeBean.getRecvObject());
        tiOSmsImme.setRecvId(immeBean.getRecvId());
        tiOSmsImme.setSmsTypeCode(immeBean.getSmsTypeCode());
        tiOSmsImme.setSmsKindCode(immeBean.getSmsKindCode());
        tiOSmsImme.setNoticeContentType(immeBean.getNoticeContentType());
        tiOSmsImme.setNoticeContent(immeBean.getNoticeContent());
        tiOSmsImme.setReferedCount(immeBean.getReferedCount());
        tiOSmsImme.setForceReferCount(immeBean.getForceReferCount());
        tiOSmsImme.setForceObject(immeBean.getForceObject());
        tiOSmsImme.setForceStartTime(immeBean.getForceStartTime());
        tiOSmsImme.setForceEndTime(immeBean.getForceEndTime());
        tiOSmsImme.setSmsPriority(immeBean.getSmsPriority());
        tiOSmsImme.setReferTime(immeBean.getReferTime());
        tiOSmsImme.setReferStaffId(immeBean.getReferStaffId());
        tiOSmsImme.setReferDepartId(immeBean.getReferDepartId());
        tiOSmsImme.setDealTime(immeBean.getDealTime());
        tiOSmsImme.setDealStaffid(immeBean.getDealStaffid());
        tiOSmsImme.setDealDepartid(immeBean.getDealDepartid());
        tiOSmsImme.setDealState(immeBean.getDealState());
        tiOSmsImme.setDoneCode(immeBean.getDoneCode());
        tiOSmsImme.setBlockId(immeBean.getBlockId());
        tiOSmsImme.setSmsCode(immeBean.getSmsCode());
        tiOSmsImme.setRemark(immeBean.getRemark());
        tiOSmsImme.setRevc1(immeBean.getRevc1());
        tiOSmsImme.setRevc2(immeBean.getRevc2());
        tiOSmsImme.setRevc3(immeBean.getRevc3());
        tiOSmsImme.setRevc4(immeBean.getRevc4());
        tiOSmsImme.setDay(immeBean.getDay());
        tiOSmsImme.setMonth(immeBean.getMonth());
        return tiOSmsImme;
    }
    /**
     * 实时短信表的转换
     * wukl 2013-1-7
     * @param immeBean
     * @return
     */
    public static TiOSmsImme immeSmsCvt(TiOSmsImmeBean immeBean)
    {
        TiOSmsImme tiOSmsImme = new TiOSmsImme();
        tiOSmsImme.setSmsNoticeId(immeBean.getSmsNoticeId());
        tiOSmsImme.setPartitionId(immeBean.getPartitionId());
        tiOSmsImme.setEparchyCode(immeBean.getEparchyCode());
        tiOSmsImme.setBrandCode(immeBean.getBrandCode());
        tiOSmsImme.setInModeCode(immeBean.getInModeCode());
        tiOSmsImme.setSmsNetTag(immeBean.getSmsNetTag());
        tiOSmsImme.setChanId(immeBean.getChanId());
        tiOSmsImme.setSendObjectCode(immeBean.getSendObjectCode());
        tiOSmsImme.setSendTimeCode(immeBean.getSendTimeCode());
        tiOSmsImme.setSendCountCode(immeBean.getSendCountCode());
        tiOSmsImme.setRecvObjectType(immeBean.getRecvObjectType());
        tiOSmsImme.setRecvObject(immeBean.getRecvObject());
        tiOSmsImme.setRecvId(immeBean.getRecvId());
        tiOSmsImme.setSmsTypeCode(immeBean.getSmsTypeCode());
        tiOSmsImme.setSmsKindCode(immeBean.getSmsKindCode());
        tiOSmsImme.setNoticeContentType(immeBean.getNoticeContentType());
        tiOSmsImme.setNoticeContent(immeBean.getNoticeContent());
        tiOSmsImme.setReferedCount(immeBean.getReferedCount());
        tiOSmsImme.setForceReferCount(immeBean.getForceReferCount());
        tiOSmsImme.setForceObject(immeBean.getForceObject());
        tiOSmsImme.setForceStartTime(immeBean.getForceStartTime());
        tiOSmsImme.setForceEndTime(immeBean.getForceEndTime());
        tiOSmsImme.setSmsPriority(immeBean.getSmsPriority());
        tiOSmsImme.setReferTime(immeBean.getReferTime());
        tiOSmsImme.setReferStaffId(immeBean.getReferStaffId());
        tiOSmsImme.setReferDepartId(immeBean.getReferDepartId());
        tiOSmsImme.setDealTime(immeBean.getDealTime());
        tiOSmsImme.setDealStaffid(immeBean.getDealStaffid());
        tiOSmsImme.setDealDepartid(immeBean.getDealDepartid());
        tiOSmsImme.setDealState(immeBean.getDealState());
        tiOSmsImme.setDoneCode(immeBean.getDoneCode());
        tiOSmsImme.setBlockId(immeBean.getBlockId());
        tiOSmsImme.setSmsCode(immeBean.getSmsCode());
        tiOSmsImme.setRemark(immeBean.getRemark());
        tiOSmsImme.setRevc1(immeBean.getRevc1());
        tiOSmsImme.setRevc2(immeBean.getRevc2());
        tiOSmsImme.setRevc3(immeBean.getRevc3());
        tiOSmsImme.setRevc4(immeBean.getRevc4());
        tiOSmsImme.setDay(immeBean.getDay());
        tiOSmsImme.setMonth(immeBean.getMonth());
        return tiOSmsImme;
    }
}
