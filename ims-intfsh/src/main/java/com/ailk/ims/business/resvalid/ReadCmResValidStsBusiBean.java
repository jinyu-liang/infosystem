package com.ailk.ims.business.resvalid;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.jd.entity.ImsResStsSync;

/**
 * @descrition:扫描用户有效期（当用户没有办理取消有效期业务（即：Effect_flag = 0），当天扫描时，昨天到期为保留期停；保留期停90天，需要置为有效期停；
 * @author caohm5
 * @Date 2012-05-28
 * @Date 2012-06-11 wukl 上海用户有效期查询方法修改，同时查询有效期停、保留期停的数据;
 */
public class ReadCmResValidStsBusiBean extends BaseCancelableBusiBean
{
    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
    }

    @Override
    public void init(Object... input) throws BaseException
    {
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        imsLogger.info("************* Enter ReadCmResValidStsBusiBean.doBusiness() *************");

        // 获取保留期的扫描时间(一般是凌晨4点，扫描前一天到期的时间)
        // 获取保留期的扫描的配置天数
        int retainConfig = -1;// TODO 后期做成配置
        Calendar retainDate = Calendar.getInstance();
        retainDate.setTime(context.getRequestDate());

        retainDate.add(Calendar.DATE, retainConfig);
        Date retainBeginTime = DateUtil.dayBegin(retainDate.getTime());
        Date retainEndTime = DateUtil.dayEnd(retainDate.getTime());

        // 获取有效期的扫描时间（当用户已经处于保留期停90天，则需要置成有效期停）
        // 获取保留期的扫描的配置天数
        int effectiveConfig = -91;// TODO 后期做成配置
        Calendar effectiveDate = Calendar.getInstance();
        effectiveDate.setTime(context.getRequestDate());

        effectiveDate.add(Calendar.DATE, effectiveConfig);
        Date effectiveBeginTime = DateUtil.dayBegin(effectiveDate.getTime());
        Date effectiveEndTime = DateUtil.dayEnd(effectiveDate.getTime());

        List<CmResValidity> queryList = this.queryCmResValidity(retainBeginTime, retainEndTime, effectiveBeginTime,
                effectiveEndTime);
        List<ImsResStsSync> retainList = new ArrayList<ImsResStsSync>();// 保留期停 存放list
        List<ImsResStsSync> effectiveList = new ArrayList<ImsResStsSync>();// 有效期停 存放list

        if (CommonUtil.isNotEmpty(queryList))
        {
            for (CmResValidity rv : queryList)
            {
                // 判断用户是否发起取消有效期的动作
                if (rv.getUserExpireDate().before(retainEndTime) && rv.getUserExpireDate().after(retainBeginTime))
                {
                    ImsResStsSync retainData = getRetainData(rv);
                    retainList.add(retainData);
                }
                else if (rv.getUserExpireDate().before(effectiveEndTime) && rv.getUserExpireDate().after(effectiveBeginTime))
                {
                    User3hBean bean = context.get3hTree().loadUser3hBean(rv.getResourceId());
                    CmResLifecycle life = bean.getUserLifeCycle();
                    boolean oldFlag = judgeRetainStop(getBossOsStsDtlFromDB(life.getOsStsDtl()));
                    if (oldFlag)
                    {
                        ImsResStsSync effectiveData = getEffectiveData(rv);
                        effectiveList.add(effectiveData);
                    }
                }
                else
                {
                    // 打印日志，说明下用户有效期扫描的sql存在问题，按理是不会检索出这样的数据
                    imsLogger.info(" ****** 用户有效期的扫描存在问题：user_id = " + rv.getResourceId() + " , expire_date = "
                            + rv.getExpireDate());
                }
            }
        }

        String now = DateUtil.getFormatDate(retainDate.getTime(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);

        if (CommonUtil.isNotEmpty(retainList))
        {
            imsLogger.info(" ***** 今日 [", now, "]保留期停的用户数量 count = ", retainList.size());
        }
        else
        {
            imsLogger.info(" ***** 今日 [", now, "]保留期停的用户数量 count = 0");
        }

        if (CommonUtil.isNotEmpty(effectiveList))
        {
            imsLogger.info(" ***** 今日 [", now, "]有效期停的用户数量 count = ", effectiveList.size());
        }
        else
        {
            imsLogger.info(" ***** 今日 [", now, "]有效期停的用户数量 count = 0");
        }

        List<ImsResStsSync> allData = IMSUtil.mergeList(retainList, effectiveList);

        if (CommonUtil.isNotEmpty(allData))
        {
            DBUtil.insertBatch(allData);
        }

        imsLogger.info("************* Exit ReadCmResValidStsBusiBean.doBusiness() **************");
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_sdlResponse();
    }

    @Override
    public void destroy()
    {
    }

    /**
     * @description:查询所在时间段失效的用户
     * @author caohm5
     * @date 2012-06-04
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param effectiveEndTime
     * @param effectiveBeginTime
     */
    public List<CmResValidity> queryCmResValidity(Date retainBeginTime, Date retainEndTime, Date effectiveBeginTime,
            Date effectiveEndTime)
    {
        // @Date 2012-06-11 wukl 上海用户有效期查询方法修改，同时查询有效期停、保留期停的数据
        List<CmResValidity> cmResValidList = DBUtil.query(CmResValidity.class, new DBCondition(
                CmResValidity.Field.expireDate, context.getRequestDate(), Operator.GREAT),new DBOrCondition(new DBCondition(new DBCondition(
                CmResValidity.Field.expireDate, retainEndTime, Operator.LESS_EQUALS), new DBCondition(
                CmResValidity.Field.expireDate, retainBeginTime, Operator.GREAT_EQUALS)), new DBCondition(new DBCondition(
                CmResValidity.Field.expireDate, effectiveEndTime, Operator.LESS_EQUALS), new DBCondition(
                CmResValidity.Field.expireDate, effectiveBeginTime, Operator.GREAT_EQUALS))));

        return cmResValidList;
    }

    /**
     * 封装有效停的数据<br>
     * wukl 2012-6-11
     * 
     * @param cmResValidity
     * @return
     */
    private ImsResStsSync getEffectiveData(CmResValidity cmResValidity)
    {
        User3hBean bean = context.get3hTree().loadUser3hBean(cmResValidity.getResourceId());
        ImsResStsSync imsResStsSync = new ImsResStsSync();
        imsResStsSync.setPhoneId(bean.getPhoneId());
        imsResStsSync.setAcctId(bean.getAcctId());
        imsResStsSync.setResourceId(cmResValidity.getResourceId());
        imsResStsSync.setCreateDate(context.getRequestDate());
        imsResStsSync.setSoNbr(cmResValidity.getSoNbr());
        imsResStsSync.setNsubType(10);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setPsubType(10);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
        imsResStsSync.setNotifyFlag((int) EnumCodeDefine.OS_STS_DTL_FIVE);
        imsResStsSync.setOpId(9L);
        imsResStsSync.setOrgId(0);

        return imsResStsSync;
    }

    /**
     * 封装保留停的数据<br>
     * wukl 2012-6-11
     * 
     * @param cmResValidity
     * @return
     */
    private ImsResStsSync getRetainData(CmResValidity cmResValidity)
    {
        User3hBean bean = context.get3hTree().loadUser3hBean(cmResValidity.getResourceId());
        ImsResStsSync imsResStsSync = new ImsResStsSync();
        imsResStsSync.setId(DBUtil.getSequence(ImsResStsSync.class));
        imsResStsSync.setPhoneId(bean.getPhoneId());
        imsResStsSync.setAcctId(bean.getAcctId());
        imsResStsSync.setResourceId(cmResValidity.getResourceId());
        imsResStsSync.setNotifyFlag((int) EnumCodeDefine.OS_STS_DTL_SIX);
        imsResStsSync.setCreateDate(context.getRequestDate());
        imsResStsSync.setSoNbr(cmResValidity.getSoNbr());
        imsResStsSync.setNsubType(0);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setPsubType(0);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
        imsResStsSync.setOpId(9L);
        imsResStsSync.setOrgId(0);

        return imsResStsSync;
    }

    /**
     * 将DB表的OsStsDtl由NUMBER(6)转成VARCHAR2(20) wukl 2012-6-1
     */
    private String getBossOsStsDtlFromDB(Integer dbOsStsDtl)
    {
        // 将DB中的十进制转成二进制
        String stsDtl = Integer.toBinaryString(dbOsStsDtl);

        // 获取需要补0的长度
        int len = EnumCodeShDefine.INIT_STS_DTL.length() - stsDtl.length();

        // 不足20位的，补0
        String prefix = EnumCodeShDefine.INIT_STS_DTL.substring(0, len);

        return prefix + stsDtl;
    }

    /**
     * 判断是否保留期停<br>
     * BOSS位串的从左到右数，第16位是保留期停 wukl 2012-6-14
     * 
     * @param crmOsStsDtl
     * @return
     */
    private boolean judgeRetainStop(String stsDtl)
    {
        if (stsDtl.charAt(15) == '1')
        {
            return true;
        }
        return false;
    }
}