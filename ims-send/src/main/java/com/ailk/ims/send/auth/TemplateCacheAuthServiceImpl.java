package com.ailk.ims.send.auth;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jef.database.AutoCloseIterator;
import jef.database.DataObject;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.ims.send.convert.ImsCrmCvt;
import com.ailk.ims.send.init.InitInfo;
import com.ailk.ims.send.util.CommonDbUtil;
import com.ailk.ims.send.util.ComplexSqlBuilder;
import com.ailk.ims.send.util.SendSpringUtil;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.ImsNoAuthSmsDetail;
import com.ailk.openbilling.persistence.jd.entity.ImsNoAuthSmsSend;
import com.ailk.openbilling.persistence.jd.entity.ImsNoAuthTiImme;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsBatch;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImme;

public class TemplateCacheAuthServiceImpl extends TemplateCacheAuthService
{
    private static final String IMSNOAUTHTIIMME="JD.IMS_NO_AUTH_TI_IMME";
    private static final String IMSNOAUTHSMSSEND="JD.IMS_NO_AUTH_SMS_SEND";
    private static final String IMSNOAUTHSMSDETAIL="JD.IMS_NO_AUTH_SMS_DETAIL";
   private Logger logger=Logger.getLogger(TemplateCacheAuthServiceImpl.class);
//    /**
//     * 是否需要认证
//     */
//    private boolean needAuth = true;
    
    

    @Transactional
    public boolean AllowSyncSms(DataObject dataObject,int fetchSize)
    {
        if(null==dataObject){
            return false;
        }

        if (InitInfo.needAuth()&&(!hasTemplateId(getModCode(dataObject))))
        {
            if (dataObject instanceof CheckSmsSend)
            {
                CheckSmsSend checkSmsSend = (CheckSmsSend) dataObject;
               
                    logger.info("**************短信ID为:" + checkSmsSend.getModCode() + "没有通过认证，蒋移到未认证表");
                    operSmsDatail(checkSmsSend.getBlockId(),fetchSize);
                    operSmsSend(checkSmsSend);
            }else if(dataObject instanceof TiOSmsImme){
                operTiOSmsImme((TiOSmsImme)dataObject);
            }else if(dataObject instanceof TiOSmsBatch){
                operTiOSmsBatch((TiOSmsBatch)dataObject);
            }
            else{
                logger.debug("未知的对象类型:"+dataObject.toString());
                throw new IllegalArgumentException("未知的对象类型:"+dataObject.toString());
            }
            return false;
        }
        return true;

    }
   private Long getModCode(DataObject dataObject){
       Long tempId=0L;
       if(dataObject instanceof CheckSmsSend){
           tempId=  ((CheckSmsSend) dataObject).getModCode();
       }else if(dataObject instanceof TiOSmsImme){
           tempId=((TiOSmsImme)dataObject).getSmsCode();
       }else if(dataObject instanceof TiOSmsBatch){
           tempId=((TiOSmsBatch)dataObject).getSmsCode();
       }
          return tempId;
   }
    
    /**
     * 处理实时短信
     * @param tiOSmsImme
     */
   private void operTiOSmsImme(TiOSmsImme tiOSmsImme){
       
       //插入到未认证的实时短信表
       List<ImsNoAuthTiImme> noAuthImmes=new ArrayList<ImsNoAuthTiImme>();
       noAuthImmes.add(ImsCrmCvt.cvt2NoAuthObj(tiOSmsImme));
       
       CommonDbUtil.batchInsertImsNoAuthTiImme(noAuthImmes.get(0), noAuthImmes,IMSNOAUTHTIIMME);
       
       List<DataObject> immes=new ArrayList<DataObject>();
       immes.add(tiOSmsImme);
       
       CommonDbUtil.batchDelete(TiOSmsImme.class,immes); 
   }
   
   /**
    * 处理实时短信
    * @param tiOSmsImme
    */
  private void operTiOSmsBatch(TiOSmsBatch tiOSmsImme){
      
      //插入到未认证的实时短信表
      List<ImsNoAuthTiImme> noAuthImmes=new ArrayList<ImsNoAuthTiImme>();
      noAuthImmes.add(cvt2NoAuthObj(tiOSmsImme));
      
      CommonDbUtil.batchInsertImsNoAuthTiImme(noAuthImmes.get(0), noAuthImmes,IMSNOAUTHTIIMME);
      
      List<DataObject> immes=new ArrayList<DataObject>();
      immes.add(tiOSmsImme);
      
      CommonDbUtil.batchDelete(TiOSmsBatch.class,immes); 
  }
    /**
     * 处理审核明细表
     * @param blockId
     * @param size
     */
    private void operSmsDatail(Long blockId, int size)
    {
        logger.info("开始处理blockId为" + blockId + "短信明细表数据");
        AutoCloseIterator<SmsSendInterfaceCheck> iter = null;
        try
        {
            iter = SendSpringUtil.getCommonDao().getClient()
                    .createNativeQuery(ComplexSqlBuilder.buildSelectSmsDetailSql(blockId), SmsSendInterfaceCheck.class)
                    .getResultIterator();
            logger.info("开始挪短信明细表到未认证表blockId=" + blockId);
            long t = System.currentTimeMillis();
            while (iter.hasNext())
            {
                List<ImsNoAuthSmsDetail> noAuthDetails = fetchSmsDetailSize(iter, size);
                CommonDbUtil.batchInsertImsNoAuthSmsDetail(noAuthDetails.get(0), noAuthDetails, IMSNOAUTHSMSDETAIL);
            }
            logger.info("挪短信明细表数据成功[" + String.valueOf(System.currentTimeMillis() - t) + "]ms");
            logger.info("开始删除blockId[" + blockId + "]的审核总表数据");

            SmsSendInterfaceCheck cond = new SmsSendInterfaceCheck();
            cond.setBlockId(blockId);
            SendSpringUtil.getCommonDao().getClient().delete(cond);

        }
        catch (SQLException e)
        {
            logger.error(e, e);
        }
        finally
        {
            if (null != iter)
            {
                iter.close();
            }
        }

       
    }
  
    private List<ImsNoAuthSmsDetail> fetchSmsDetailSize(AutoCloseIterator<SmsSendInterfaceCheck>iter,int size){
        List<ImsNoAuthSmsDetail> list=new ArrayList<ImsNoAuthSmsDetail>();
        int currentCount=0;
        while(iter.hasNext()&&(++currentCount)<size){
            list.add(ImsCrmCvt.cvt2NoAuthObj(iter.next()));
        }
        return list;
    }
 

    private void operSmsSend(CheckSmsSend checkSmsSend)
    {
        // 插入未认证明细表
        List<ImsNoAuthSmsSend> noAuthSmsSends = new ArrayList<ImsNoAuthSmsSend>();
        noAuthSmsSends.add(ImsCrmCvt.cvt2NoAuthObj(checkSmsSend));
        CommonDbUtil.batchInsertImsNoAuthSmsSend(noAuthSmsSends.get(0), noAuthSmsSends,IMSNOAUTHSMSSEND);
        // 删除billing侧数据库中的相关数据
        List<DataObject> smsSends = new ArrayList<DataObject>();
        smsSends.add(checkSmsSend);
        CommonDbUtil.batchDelete(CheckSmsSend.class, smsSends);

    }

    public static ImsNoAuthTiImme cvt2NoAuthObj(com.ailk.openbilling.persistence.jd.entity.TiOSmsBatch imsObj)
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


}
