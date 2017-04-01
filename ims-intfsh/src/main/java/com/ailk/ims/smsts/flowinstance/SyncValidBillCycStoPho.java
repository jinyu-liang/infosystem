package com.ailk.ims.smsts.flowinstance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.Field;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.smsts.flowbase.BaseFlow;
import com.ailk.ims.smsts.helper.FlowInstanceHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.jd.entity.ImsResStsSync;
import com.ailk.openbilling.persistence.jd.entity.ImsStsSyncStore;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * 把 IMS_STS_SYNC_STORE 表中 CREATE_DATE 为当天时间的数据挪到 IMS_RES_STS_SYNC 表中
 *  同步条件跟与日保号停发送短信条件一样，不同的时，只判断当前月与前一个月没有产生费用即可
 * @author gaoqc5
 * @date 2012-10-16
 * busi_code=7510
 */
public class SyncValidBillCycStoPho extends BaseFlow
{
    public Class<? extends DataObject> getScanTableClass()
    {
        return ImsStsSyncStore.class;
    }

    public Field getKeyField()
    {
        return ImsStsSyncStore.Field.id;
    }

    @Override
    public OrderCondition[] getOrderCond()
    {
        OrderCondition orderCondition = new OrderCondition(true, ImsStsSyncStore.Field.id);
        return new OrderCondition[] { orderCondition };
    }

    public DBCondition[] getQueryConds()
    {

        Date startDate = DateUtil.dayBegin(getCurrentDate());
        Date endDate = DateUtil.dayEnd(getCurrentDate());
        // 只需要createDate 在当天的数据
        return new DBCondition[] {
        new DBCondition(ImsStsSyncStore.Field.createDate, startDate, Operator.GREAT_EQUALS),
        new DBCondition(ImsStsSyncStore.Field.createDate, endDate, Operator.LESS_EQUALS)
//                ,new DBCondition(ImsStsSyncStore.Field.phoneId, new Long[]{15821465212L,13917390359L,15221437603L}, Operator.IN)
        };

    }
    

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {

        ImsStsSyncStore store = (ImsStsSyncStore) obj;
        imsLogger.debug("begin to insert IMS_STS_SYNC_STORE");
        Integer billDay = getFisrtBillDay(store.getAcctId());
        imsLogger.info("首帐日:"+billDay);
       Calendar cal=Calendar.getInstance();
       cal.set(Calendar.DAY_OF_MONTH, billDay);
       cal.add(Calendar.MONTH, -1);
       Long acctId=store.getAcctId();
       Long resourceId=store.getResourceId();
       IMSUtil.setAcctRouterParam(acctId);
       if(FlowInstanceHelper.isExemption(acctId)){
           imsLogger.info("账户["+acctId+"]是免停,不做日保号停，并且把此记录删除");
           deleteStore(store.getSoNbr());
           return null;
       }
       //2012-11-19 gaoqc5  用户如果设置了“随心聊”功能的，不发短信
       if(FlowInstanceHelper.isSXL(resourceId)){
           imsLogger.info("用户["+resourceId+"]属于随心聊群,不做日保号停，并且把此记录删除");
           deleteStore(store.getSoNbr());
           return null;
       }
       //2013-04-17 zhangzj3 用户如果是后付费就return null
       CaAccount account  = this.getSmsCmp().queryAccountByUserId(store.getResourceId());
       if(account != null && account.getCreditSignControl() == 1){
           imsLogger.info("用户["+resourceId+"]的账户属于后付费,不做日保号停，并且把此记录删除");
           deleteStore(store.getSoNbr());
           return null;
       }
       
        if ( FlowInstanceHelper.isNotDayKeepNumUser(store.getResourceId())
                && isNotActiveRecInLatelyDate(store.getResourceId())
                &&isCostZreoInRangeDate(store.getResourceId(), store.getAcctId(), cal.getTime(), getCurrentDate()))
        {
            imsLogger.info("把手机号码为[",store.getPhoneId(),"]同步数据到反向表!");
            DBUtil.insert(warpParam(store));// 数据插入 IMS_RES_STS_SYNC
        }
        imsLogger.info("删除手机号码为[",store.getPhoneId(),"]的数据");
        deleteStore(store.getSoNbr());
        return new ArrayList<SmsSendInterfaceCheck>();
    }
    private void  deleteStore(Long soNbr){
    
        DBUtil.deleteByCondition(ImsStsSyncStore.class, new DBCondition(ImsStsSyncStore.Field.soNbr, soNbr));// 删除IMS_STS_SYNC_STORE表数据
    }

    public boolean isNotActiveRecInLatelyDate(Long resourceId)
    {
        Long acctId = getSmsCmp().loadAcctIdByUserId(resourceId);
        Integer billDay = getFisrtBillDay(acctId);
        Integer today = DateUtil.getDateField(getCurrentDate(), Calendar.DAY_OF_MONTH);// 当前日期
        imsLogger.info("**********首帐日期  :", billDay);
        boolean todayBeforeBillday = true;
        if (today > billDay)
        {
            todayBeforeBillday = false;
        }
        return FlowInstanceHelper.isNotActiveRecInLatelyDate(resourceId, acctId,billDay, todayBeforeBillday);
    }

    /**
     * 获取首帐日
     * 
     * @param acctId
     * @return
     */
    private int getFisrtBillDay(Long acctId)
    {
        List<BillCycleComplex> complex = this.getSmsCmp().queryBillCycle(acctId);
        if (CommonUtil.isEmpty(complex))
        {
            return 1;
        }
        BillCycleComplex billCycleComplex = complex.get(0);
        return billCycleComplex.getFirstBillDate();// 首账日期
    }

    /**
     * 如果是在 扫描当天 至今天
     * 
     * @param userId
     * @param acctId
     * @param beginDate
     * @param endDate
     * @return
     */
    private boolean isCostZreoInRangeDate(Long userId, Long acctId, Date beginDate, Date endDate)
    {
        beginDate = DateUtil.dayBegin(beginDate);
//        endDate = DateUtil.getCutoffDate(Calendar.DAY_OF_MONTH, -1, endDate);
        endDate = DateUtil.dayEnd(endDate);
        imsLogger.info("用户编号:", userId, "账户编号:", acctId, "开始时间:",
                DateUtil.formatDate(beginDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS), "结束时间:",
                DateUtil.formatDate(endDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        return FlowInstanceHelper.isCostZreoInRangeDate(userId, acctId, beginDate, endDate);
    }

    private static ImsResStsSync warpParam(ImsStsSyncStore store)
    {
        ImsResStsSync sync = new ImsResStsSync();
        sync.setId(store.getId());
        sync.setPhoneId(store.getPhoneId());
        sync.setNotifyFlag(store.getNotifyFlag());
        sync.setCreateDate(store.getCreateDate());
        sync.setResourceId(store.getResourceId());
        sync.setAcctId(store.getAcctId());
        sync.setOpId(store.getOpId());
        sync.setOrgId(store.getOrgId());
        sync.setSts(1);
        sync.setNsubType(store.getNsubType());
        sync.setPsubType(store.getPsubType());
        sync.setSoNbr(store.getSoNbr());

        return sync;
    }

    @Override
    public void commitOther()
    {
        
    }

    @Override
    protected Long getTemplateId()
    {
        return null;
    }

}
