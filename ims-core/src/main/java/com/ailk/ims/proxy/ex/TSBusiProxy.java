package com.ailk.ims.proxy.ex;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.database.DataObject;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.ims.common.BaseTsBean;
import com.ailk.ims.common.BusiRequestInfo;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ts.TSUtil;
import com.ailk.ims.ts.TsReadCondition;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imsts.entity.STsRecord;

/**
 * 定时任务代理
 * @Description
 * @author luojb
 * @Date 2012-10-25
 */
public class TSBusiProxy implements IExBusiProxy
{
    private ImsLogger logger = new ImsLogger(getClass());
    
    public Date createRequestDate() throws Exception
    {
        return DateUtil.currentDate();
    }

    public long createDoneCode(Date requestDate) throws Exception
    {
        return BusiRecordUtil.getReceiveDoneCode(requestDate);
    }

    public BusiRequestInfo createBusiRequest(IMSContext context, Object[] params) throws Exception
    {
        SOperInfo oper = buildTSOper(context);
//        Object[] inputparams = new Object[params.length];
//        System.arraycopy(params, 0, inputparams, 0, params.length);
        
        BusiRequestInfo requestInfo = new BusiRequestInfo(oper,params);
        
        return requestInfo;
    }
    
    private SOperInfo buildTSOper(IMSContext context)
    {
        SOperInfo oper = new SOperInfo();
        oper.setSo_nbr("-1");
        oper.setSo_mode((short) 99);
        oper.setSo_date(DateUtil.currentString19());
        oper.setStep_id((short) 0);// 一次调用
        Integer busiCode = context.getMethodConfig().getIntAttribute(BusiUtil.ATTR_BUSI_CODE);
        logger.info("set busi_code[" + busiCode + "] to SOperInfo", context);
        oper.setBusi_code(busiCode);
        return oper;
    }

    public Object doProxyService(IMSContext context, Object[] params) throws Exception
    {
        Do_sdlResponse rsp = new Do_sdlResponse();
        try{
            String serviceName = context.getServiceName();
            String methodName = context.getMethodName();
            
            // 根据syncBean获取业务处理类
            BaseTsBean tsBean = BusiUtil.getTsBean(serviceName, methodName);
            if (tsBean == null)
            {
                throw new IMSException("no ts bean is configured with <service: " + serviceName + "> <method: " + methodName + ">");
            }
            tsBean.setContext(context);
            
            long start = 0;
            if (CommonUtil.isEmpty(params))
            {
                // 如果没有参数传入，则表示当前流程是读取数据的流程
                start = System.currentTimeMillis();
                logger.info("begin to doRead", context);
                doRead(tsBean);
                logger.info("finish to doRead", start,context);
            }
            else
            {
                //如果有参数传入，则表示当前流程是处理数据的流程
                start = System.currentTimeMillis();
                logger.info("begin to doDeal", context);
                doDeal(tsBean,params[0]);
                logger.info("finish to doDeal", start,context);
            }
        }catch(Exception e){
            logger.error(e, e);
            rsp.setErrorMsg(IMSUtil.createCBSErrorMsg(context, e));
        }
        
        if (rsp.getErrorMsg() == null || rsp.getErrorMsg().getResult_code() == null)
        {
            return null;
        }
        return rsp.getErrorMsg().getResult_code().intValue();
    }

    public void doRead(BaseTsBean syncBean) throws Exception{
        //创建读取的条件
        List<TsReadCondition> conditions = syncBean.createReadConditions();
        
        //读取接口表数据
        long start = System.currentTimeMillis();
        logger.info("begin to read", syncBean.getContext());
        List<DataObject> dbList = syncBean.read(conditions);
        logger.info("finish to read", start,syncBean.getContext());
        
        logger.info("read total count : "+dbList.size(), syncBean.getContext());
        
        //把接口表数据对象转成上发到TB的SDL对象
        logger.info("begin to trans2TsRecord", syncBean.getContext());
        CsdlArrayList<STsRecord> tsRecordList = new CsdlArrayList<STsRecord>(STsRecord.class);
        for (DataObject dbObj : dbList)
        {
            
            STsRecord sdlObj = TSUtil.trans2TsRecord(dbObj);
            tsRecordList.add(sdlObj);
        }
        logger.info("finish to trans2TsRecord",start, syncBean.getContext());
        
        //上传转换后的SDL对象到TB,以供Deal的TS从TB中获取数据进行逻辑处理
        start = System.currentTimeMillis();
        logger.info("begin to upload", syncBean.getContext());
        upload(tsRecordList,syncBean);
        logger.info("finish to upload", start,syncBean.getContext());
    }
    
    
    
    public void doDeal(BaseTsBean syncBean,Object input) throws Exception{
        CsdlArrayList<STsRecord> sdlObjList = (CsdlArrayList<STsRecord>) input;
        
        List<DataObject> dbObjList = new ArrayList<DataObject>();
        List<Exception> errorList = new ArrayList<Exception>();
        
        //把SDL对象再次转换回对应的数据库,实体对象
        for (STsRecord sdlObj : sdlObjList)
        {
            try{
                DataObject dbObj = TSUtil.trans2DataObject(sdlObj,syncBean.getJavaClass());
                dbObjList.add(dbObj);
            }catch(Exception e){
                errorList.add(e);//转换失败则也要更新到原始接口表中
            }
        }
        
        //进行逻辑处理
        boolean isDealBatch = syncBean.isDealBatch();
        if(isDealBatch){
            //如果是批量操作，则一次性把所有数据传入业务处理
            syncBean.deal(dbObjList);
        }else{
            //如果是单条操作，则每次只把一条数据传入业务处理
            IMSContext context = syncBean.getContext();
            long start = 0;
            for (int i=0;i< dbObjList.size();i++){
                start = System.currentTimeMillis();
                DataObject dbObj = dbObjList.get(i);
                Long recordId = null;
                try {
                    recordId = (Long)ClassUtil.getPropertyValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_ID);
                } catch (Exception e2) {
                	logger.error(e2,e2);
                }
                try{
                    logger.info("begin to deal of record["+recordId+"] : " ,syncBean.getContext());
                    CBSErrorMsg error = syncBean.deal(dbObj);
                    List<DataObject> list = new ArrayList<DataObject>();
                    logger.info("begin to dealResult record["+recordId+"] : " ,syncBean.getContext());
                    syncBean.dealResult(list,error);
                }catch(Exception e){
                    logger.error(e, e);
                    //处理失败
                    try {
                        syncBean.dealFail(dbObj, IMSUtil.createCBSErrorMsg(context, e));
                    } catch (Exception e1) {
                        logger.error(e1, e1);
                    }
                }
            }
        }
    }
    
    
    private void upload(CsdlArrayList<STsRecord> tsRecordList,BaseTsBean syncBean){
        Class[] paramTypes = { CsdlArrayList.class, com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg.class };
        boolean isLocalTest = SysUtil.isLocalTest();
        
        /*获取上发的对象和方法，如果是本地模拟测试的话，则不用上发到TB，直接调用本地生成的服务端类\
            同时对应的处理方法就是把read_xxx改成deal_xxx
        */
        Object dealAppObj = TSUtil.getUploadInvokeObject(syncBean.getContext().getServiceName(),isLocalTest);
        String dealUploadMethodName = TSUtil.getUploadInvokeMethodName(syncBean.getContext().getMethodName(),isLocalTest);
       
        logger.debug("upload by method : "+dealAppObj.getClass().getName()+"."+dealUploadMethodName, syncBean.getContext());
        
        int recordCount = SysUtil.getInt(SysCodeDefine.busi.ONE_TIME_UPLOAD_RECORD_COUNT, 100);// 每次上发多少条
        int sendTimes = tsRecordList.size() / recordCount + 1;// 一次读取的总上发次数

        for (int i = 0; i < sendTimes; i++)
        {
            CsdlArrayList<STsRecord> sendSdlObjList = new CsdlArrayList<STsRecord>(STsRecord.class);
            int start = i * recordCount;
            int end = (i + 1) * recordCount;
            if (recordCount * (i + 1) > tsRecordList.size())
            {
                end = tsRecordList.size();
            }
            sendSdlObjList.addAll(tsRecordList.subList(start, end));

            Object[] params = { sendSdlObjList, new com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg() };
            try {
                ClassUtil.invokeMethod(dealAppObj, dealUploadMethodName, paramTypes, params);
            } catch (Exception e) {
                IMSUtil.throwBusiException(e);
            }
        }
    }
    

    public Integer doProxyException(IMSContext context, Class<?> retrunType, Throwable t) throws Exception
    {
        throw IMSUtil.throwBusiException(new IMSException(t));
    }

}
