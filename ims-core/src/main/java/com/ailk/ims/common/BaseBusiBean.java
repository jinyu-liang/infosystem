package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmBusiKey;
import com.ailk.openbilling.persistence.cust.entity.CmBusiOrder;
import com.ailk.openbilling.persistence.cust.entity.ImsSonbrMapping;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description: 基准业务bean
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-8-9
 * @Date 2012-06-19 wuyujie ：新增registerListenerActions方法
 * @Date 2012-07-18 yangjh : Stroy 51143 新增busiFail方法
 */
public abstract class BaseBusiBean extends ContextBean
{
    /**
     * @Description: 进行初始化工作
     * @param input
     * @throws Exception
     */
    abstract public void init(Object... input) throws BaseException;

    /**
     * @Description: 业务数据有效性校验，如果数据不合法，则需要抛出IMSException异常
     * @param input
     * @throws Exception
     */
    abstract public void validate(Object... input) throws BaseException;

    /**
     * @Description: 实现main3hbean
     * @date : 2012-1-13
     * @param input
     * @return
     * @throws BaseException
     */
    abstract public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException;

    /**
     * @Description: 执行具体业务操作，如存数据库等
     * @param input
     * @return
     * @throws Exception
     */
    abstract public Object[] doBusiness(Object... input) throws BaseException;

    /**
     * @Description: 创建应答对象，用于返回给webservice调用的客户端
     * @param busiResult
     * @param syncResult
     * @return
     */
    abstract public BaseResponse createResponse(Object[] result);

    /**
     * @Description: 数据清理工作
     * @param busiResult
     * @param syncResult
     * @return
     */
    abstract public void destroy();

    /**
     * @Description:收取一次性费用，有默认实现，如果个别业务有特殊操作，请重载 如果soap报文中有Onetimefee这个节点，则框架会自行调用这个方法进行收取
     */
    public ImsSonbrMapping chargeOneTimeFee()
    {
        return null;//context.getComponent(AcctWrapperComponent.class).doBusiCharge();
    }

    /**
     * @Description: 如果业务需要告警提醒，则重载实现这个方法,返回null则是不需要提醒, 一些公用的参数比如cust_title,first_name，family_name不用自行添加，框架会自动加上，如果添加了则不会再重复添加。
     * @param input,输入参数
     * @param result，业务处理结果
     * @return
     */
    public List<IMSNotification> createNotifications(Object[] input, Object[] busiResult, BaseResponse resp)
    {
        return null;
    }

    /**
     * @Description:整个业务流程处理失败的回调方法busiFail(),用户产品订购失败的告警
     * @param input,输入参数
     * @param result，业务处理结果
     * @return
     */
    public BaseResponse busiFail(Exception e,Object[] input){
    	return null;
    }

    
    /**
     * 检查请求是否正确
     * luojb 2012-3-3
     * @param context
     */
    public void checkBusiKey(Object[] input)
    {
        String soNbr = context.getOper().getSo_nbr();
        Short soMode = context.getOper().getSo_mode();
        Short stepId = context.getOper().getStep_id();
        CmBusiKey busiKey = new CmBusiKey();
        busiKey.setDoneCode(context.getDoneCode());
        busiKey.setSoNbr(soNbr);
        if(soMode != null)
            busiKey.setSoMode((int)soMode);
        if(stepId != null)
            busiKey.setStepId((int)stepId);
        if(CommonUtil.isValid(context.getOper().getSo_date()))
            busiKey.setSoDate(IMSUtil.formatDate(context.getOper().getSo_date()));
        try{
            DBUtil.insert(busiKey);
        }catch(Exception e)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.BUSI_KEY_ERROR,soNbr,stepId,soMode);
        }
    }
    
    
    /**
     * @Description: 创建业务记录总表，有默认实现。一般一个请求中只会有一个总表记录， 但是如果有多个操作的话，需要采用父子工单的方式,就会有多条记录。
     * @author : wuyj
     * @date : 2011-10-25
     * @return
     */
    public List<CmBusi> createBusiRecord(Object[] input)
    {
        IMS3hBean hbean = context.getMain3hBean();
        if (hbean == null)
            return null;

        List<CmBusi> result = new ArrayList<CmBusi>();

        CmBusi busi = new CmBusi();

        busi.setSoNbr(context.getSoNbr());
        busi.setOuterSoNbr(context.getOper().getSo_nbr());
        busi.setBatchFlag(0);
        busi.setRecType(EnumCodeDefine.BUSIREC_RECTYPE_NORMAL);// 正常业务
        busi.setBusiCode(context.getOper().getBusi_code());
        if (context.getOper().getSo_mode() != null)
            busi.setChannel(context.getOper().getSo_mode().intValue());
        busi.setBusiDirect(EnumCodeDefine.BUSIREC_DIREC_CRM2BOS);// crm2bos

        if (context.getOper().getOp_id() != null)
            busi.setOpId(context.getOper().getOp_id().longValue());

        busi.setPhoneId(hbean.getPhoneId());
        busi.setResourceId(hbean.getUserId());
        busi.setAcctId(hbean.getAcctId());
        busi.setCustId(hbean.getCustId());

        busi.setSts(EnumCodeDefine.BUSIREC_STS_NORMAL);// 正常状态
        busi.setDoneDate(context.getRequestDate());
        Integer specId = context.getBusiSpecId(true);
        if (specId != null)
            busi.setBusiSpecId(specId);
        
        ImsSonbrMapping onetimefee = (ImsSonbrMapping) context.getExtendParam(ConstantDefine.ONETIMEFEE_OBJECT);
        if (onetimefee != null)
        {
            busi.setOtcFlag(1);// 表示有onetimefee
            busi.setOtcSoNbr(onetimefee.getBosSoNbr());// 帐管的so_nbr
        }
        else
            busi.setOtcFlag(0);// 表示未受理onetimefee
        busi.setAuthFlag(0);// 表示没有鉴权

        Object reward = context.getExtendParam(ConstantDefine.REWARD_OBJECT);
        if (reward != null)
        {
            busi.setRewardFlag(1);// 表示reward已受理
        }
        else
            busi.setRewardFlag(0);// 表示reward未受理
        
        //bss broker 报文头增加字段
        if(context.getOper().getStep_id()!=null){
            busi.setStepId((int)context.getOper().getStep_id());
        }else{
            busi.setStepId(-1);
        }
        busi.setSourceSystem(context.getOper().getSource_system());
        if(CommonUtil.isValid(context.getOper().getIs_monitor()))
            busi.setIsMonitor((int)context.getOper().getIs_monitor());
        if(CommonUtil.isValid(context.getOper().getIsnormal()))
            busi.setIsnormal((int)context.getOper().getIsnormal());
        if(CommonUtil.isValid(context.getOper().getCounty_code()))
            busi.setCountyCode((int)context.getOper().getCounty_code());
        if(CommonUtil.isValid(context.getOper().getNotify_flag()))
            busi.setNotifyFlag((int)context.getOper().getNotify_flag());
        if(CommonUtil.isValid(context.getOper().getProv_code()))
            busi.setProvCode((int)context.getOper().getProv_code());
        if(CommonUtil.isValid(context.getOper().getRegion_code()))
            busi.setRegionCode((int)context.getOper().getRegion_code());
        Date soDate = IMSUtil.formatDate(context.getOper().getSo_date());
        if(soDate != null)
            busi.setSoDate(soDate);
        else{
        	 busi.setSoDate(context.getRequestDate());
        }
        busi.setOrgId(context.getOper().getOrg_id());
        result.add(busi);
        imsLogger.debug("-----cm_busi.so_date:"+IMSUtil.formatDate(busi.getSoDate()));
        return result;
    }

    /**
     * @Description: 创建业务记录子表，大部分不需要实现，跟产品订购类相关的都需要实现
     * @author : wuyj
     * @date : 2011-11-1
     * @param input
     * @return
     */
    public List<CmBusiOrder> createBusiOrderRecord(Object[] input)
    {
        return null;
    }
    /**
     * @Description:[56230]writeCap优化--创建WriteCapInfo对象，将uri、业务信息key/value绑定在该对象上
     * @author : linzt
     * @date : 2012-08-16
     * @param input
     * @return
     */
    public InnerClass.WriteCapInfo createWriteCapInfo(Object[] input){
        return null;
    }
}	
