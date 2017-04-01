package com.ailk.ims.common;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jef.common.wrapper.IntRange;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.Field;
import jef.tools.reflect.BeanUtils;

import com.ailk.easyframe.sdl.MImsTsApp.IImsTsAppInt;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObject;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;

/**
 * @Description：定时任务框架基类
 * @author wangjt
 * @Date 2011-9-27
 */
public abstract class BaseSyncBean extends ContextBean
{
    /**
     * 是否需要增加cm_send表记录
     */
    private boolean needSendRecord = true;

    /**
     * 设定
     */
    public void setNeedSendRecord(boolean needSendRecord)
    {
        this.needSendRecord = needSendRecord;
    }

    /**
     * 返回接口表对象的Class
     */
    public abstract Class<? extends DataObject> getTblObjClass();

    /**
     * 返回接口表对象对应的sdl对象的Class
     */
    public abstract Class<? extends CsdlStructObject> getSdlObjClass();

    /**
     * 处理数据接口表中的数据
     * 
     * @param object:数据表对象(InterfaceTable)
     * @return:错误对象，成功可以返回null
     */
    public abstract CBSErrorMsg invokeDealMethod(DataObject object);

    /**
     * 通过调用component中的这个统一方法，来获取需要上发的数据的列表,可重写 zengqm 2012-4-1
     */
    public Object[] doRead(Object... input)
    {
        imsLogger.debug("----enter doRead()----");
        CsdlArrayList<CsdlStructObject> sdlObjList = this.getSdlObjList();
        if (CommonUtil.isEmpty(sdlObjList))
        {
            imsLogger.debug("----sdlObjList is null, no need to sync----");
            imsLogger.debug("----leave doRead()----");
            return null;
        }
        try
        {
            imsLogger.debug("----sdlObjList.size()=[" + sdlObjList.size() + "]----");
            this.invokeUploadMethod(sdlObjList); // 调用上发数据的方法
        }
        catch (Exception e)
        {
            imsLogger.error(e.getMessage());
        }
        imsLogger.debug("----leave doRead()----");
        return null;
    }

    /**
     * 具体处理数据方法
     */
    public Object[] doDeal(Object... input)
    {
        imsLogger.debug("----enter doDeal()----");
        CsdlArrayList<? extends CsdlStructObject> sdlObjList = (CsdlArrayList<CsdlStructObject>) input[0];// 从参数中获取上发数据

        List<? extends DataObject> tblObjList = this.getTblObjList(sdlObjList);// sdl转换成tbl数据
        if (CommonUtil.isEmpty(tblObjList))
        {
            imsLogger.debug("----list is empty, dealList:" + this.getTblObjClass().getName() + "----");
            return null;
        }

        for (DataObject tblObj : tblObjList)
        {
            // 同步给crm，具体由各个业务实现
            CBSErrorMsg cbsErrorMsg = this.invokeDealMethod(tblObj);
            // 处理接口表数据，并同步至CRM
            this.dealTblObjList(tblObj, cbsErrorMsg);
        }
        imsLogger.debug("----deal list end, dealList:" + this.getTblObjClass().getName() + ", size:" + tblObjList.size());
        imsLogger.debug("----leave doDeal()----");
        return null;
    }

    /**
     * 调用上发数据的方法 可重写
     */
    public void invokeUploadMethod(CsdlArrayList<? extends CsdlStructObject> sdlObjList) throws Exception
    {
        Class[] paramTypes = { CsdlArrayList.class, com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg.class };

        Object dealAppObj = this.getDealAppObj();
        String dealUploadMethodName = this.getDealUploadMethodName();// 调用处理上发业务数据的方法对应的upload方法，上发数据

        int recordCount = SysUtil.getInt(SysCodeDefine.busi.ONE_TIME_UPLOAD_RECORD_COUNT, 100);// 每次上发多少条
        int sendTimes = sdlObjList.size() / recordCount + 1;// 一次读取的总上发次数

        for (int i = 0; i < sendTimes; i++)
        {
            CsdlArrayList<CsdlStructObject> sendSdlObjList = new CsdlArrayList<CsdlStructObject>(CsdlStructObject.class);
            int start = i * recordCount;
            int end = (i + 1) * recordCount;
            if (recordCount * (i + 1) > sdlObjList.size())
            {
                end = sdlObjList.size();
            }
            sendSdlObjList.addAll(sdlObjList.subList(start, end));

            Object[] params = { sendSdlObjList, new com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg() };
            ClassUtil.invokeMethod(dealAppObj, dealUploadMethodName, paramTypes, params);
            imsLogger.debug("----invoke method " + dealAppObj.getClass().getName() + "." + dealUploadMethodName + " success----");
        }

    }

    /**
     * 获取具体处理的sdl服务类, 特殊业务可重写该方法
     */
    public Object getDealAppObj()
    {
//        return (SysUtil.isLocalTest() ? new IImsTsAppImp() : new IImsTsAppInt());
    	return new IImsTsAppInt();
    }

    /**
     * 获取具体处理的sdl方法名称, 特殊业务可重写该方法
     */
    public String getDealUploadMethodName()
    {
        String dealMethod = this.getContext().getMethodName().replaceFirst("read_", "deal_");
        // 如果本地调试，则直接调用deal方法，而不是upload方法
        return (SysUtil.isLocalTest() ? dealMethod : dealMethod + "_upload");
    }

    /**
     * 将数据添加到历史表<br>
     * (特殊业务可重写该方法)
     * 
     * @param table:接口表对象
     * @return结果表对象
     */
    public void insertHisTblObj(DataObject table)
    {
        DataObject hisTable = this.newTblObjInstance("His");
        if (hisTable == null)
        {
            return;
        }
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(table.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                if (!Modifier.isPrivate(f.getModifiers()))
                {
                    continue;
                }
                String fieldName = f.getName();
                this.setFieldValue(hisTable, fieldName, BeanUtils.getFieldValue(table, fieldName));
            }
            this.setFieldValue(hisTable, "dealDate", this.getContext().getRequestDate());
        }
        catch (Exception e)
        {
            imsLogger.error(e.getMessage());
            throw new IMSException(e);
        }
        if (hisTable != null)
        {
            DBUtil.insert(hisTable);
        }
    }

    /**
     * 调用失败，看该条记录的deal_count是否超限，未超限,deal_count+1,超限移至错误表 dege 2012-4-4
     */
    public void dealErrData(DataObject tblObj, CBSErrorMsg cbsErrorMsg)
    {
        Long resultCode = cbsErrorMsg.getResult_code();
        String errorMsg = cbsErrorMsg.getError_msg();
        int recordDealCountValue = SysUtil.getInt(SysCodeDefine.busi.RECORD_DEAL_COUNT_VALUE, 3);// 不成功处理次数限制
        int dealCount = getNowDealCount(tblObj);// 获取当前处理次数

        if (dealCount >= recordDealCountValue)
        {
            deleteTblObj(tblObj);// 如果超限，则删除接口表对应记录
            insertErrTblObj(tblObj, dealCount, resultCode, errorMsg);// 同时数据insert到数据接口错误表
        }
        else
        { // 如果未超限，则接口表中对应的记录deal_sts改为3，deal_count+1，记录error_msg，error_code。
            updateDealCountAdd(tblObj, dealCount, resultCode, errorMsg);
        }
    }

    /**
     * 处理接口表数据(默认为删除接口表数据，子类可重写)
     */
    public void dealTblObj(DataObject tblObj, CBSErrorMsg cbsErrorMsg)
    {
        this.deleteTblObj(tblObj);
    }

    /**
     * 返回处理结果，可覆盖
     */
    public BaseResponse createResponse(Object... result)
    {
        return new Do_sdlResponse();
    }

    /**
     * 处理接口表数据，并同步至CRM
     */
    private void dealTblObjList(DataObject tblObj, CBSErrorMsg cbsErrorMsg)
    {
        if (cbsErrorMsg != null)
        {
            this.getContext().setCbsErrorMsg(cbsErrorMsg);
        }
        // 调用成功，则删除接口表中对应的记录，数据保存到对应的历史表。并且增加cm_send表中记录
        if (cbsErrorMsg == null || (cbsErrorMsg.getResult_code() != null && cbsErrorMsg.getResult_code() == 0))
        {
            this.insertHisTblObj(tblObj);// 如果没有返回接口表对应的his表的对象，则无需insert his表
            dealTblObj(tblObj, cbsErrorMsg); // 处理接口表数据,默认删除(可由子类重写)
            this.insertSendRecord();// 增加发送成功流水记录
        }
        // 调用失败
        else
        {
            dealErrData(tblObj, cbsErrorMsg);// 调用失败，处理失败数据
        }
    }

    /**
     * 如果未超限，则接口表中对应的记录deal_sts改为3，deal_count+1，记录error_msg，error_code。
     * 
     * @author zengqm
     * @date 2012-4-1
     */
    private void updateDealCountAdd(DataObject table, int dealCount, long resultCode, String errorMsg)
    {
        DataObject updateTblObj = newTblObjInstance();
        Field stsField = DBUtil.getJefField(table.getClass(), "dealSts");
        if (stsField == null)
        {
            this.setFieldValue(updateTblObj, "sts", Integer.valueOf(EnumCodeDefine.SYNC_DEAL_STS_FAIL));
        }
        else
        {
            this.setFieldValue(updateTblObj, "dealSts", Integer.valueOf(EnumCodeDefine.SYNC_DEAL_STS_FAIL));
        }
        this.setFieldValue(updateTblObj, "dealCount", dealCount + 1);
        this.setFieldValue(updateTblObj, "errorCode", (int) resultCode);
        this.setFieldValue(updateTblObj, "errorMsg", errorMsg);
        this.setFieldValue(updateTblObj, "dealDate", context.getRequestDate());

        KeyField keyField = new KeyField(table);
        DBUtil.updateByCondition(updateTblObj, new DBCondition(keyField.getKeyField(), keyField.getKeyFieldValue()));
    }

    /**
     * 达到错误次数限制可将数据移到错误表
     * 
     * @param table:接口表对象
     * @param dealCount:处理次数
     * @param errorCode：错误码
     * @param errorMsg：错误描述
     * @return结果表对象
     */
    private void insertErrTblObj(DataObject table, int dealCount, long errorCode, String errorMsg)
    {
        DataObject errTable = this.newTblObjInstance("Err");
        if (errTable == null)
        {
            return;
        }
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(table.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                if (!Modifier.isPrivate(f.getModifiers()))
                {
                    continue;
                }
                String fieldName = f.getName();
                this.setFieldValue(errTable, fieldName, BeanUtils.getFieldValue(table, fieldName));
            }
            this.setFieldValue(errTable, "dealcount", dealCount);
            this.setFieldValue(errTable, "errorCode", errorCode);
            this.setFieldValue(errTable, "errorMsg", (errorMsg == null ? "" : errorMsg));
            this.setFieldValue(errTable, "dealDate", this.getContext().getRequestDate());
        }
        catch (Exception e)
        {
            imsLogger.error(e.getMessage());
            throw new IMSException(e);
        }
        if (errTable != null)
        {
            DBUtil.insert(errTable);
        }
    }

    /**
     * 对上发CRM的业务，需要在cm_send表添加纪录，其他则不需要，可做控制 zengqm 2012-4-1
     */
    private void insertSendRecord()
    {
        if (needSendRecord)// 对上发CRM的业务，需要在cm_send表添加纪录，其他则不需要，可做控制
        {
            BusiRecordUtil.insertCmSend(context);// 增加发送成功流水记录
        }
    }

    /**
     * 获取当前处理次数 zengqm 2012-4-1
     */
    private int getNowDealCount(DataObject tblObj)
    {
        Object oDealCount = BeanUtils.getFieldValue(tblObj, "dealCount");// 当前处理次数
        if (oDealCount != null)
        {
            return Integer.parseInt(oDealCount.toString());
        }
        else
        {
            throw new IMSException("table[" + tblObj.getClass().getSimpleName() + "] miss column deal_count!");
        }
    }

    /**
     * 删除接口表数据
     */
    private final void deleteTblObj(DataObject tblObj)
    {
        if (tblObj == null)
        {
            return;
        }
        Class dmClz = tblObj.getClass();
        KeyField keyField = new KeyField(tblObj);
        DBUtil.deleteByCondition(dmClz, new DBCondition(keyField.getKeyField(), keyField.getKeyFieldValue()));
    }

    /**
     * 查询数据库，返回SDL需要的结构列表(通过查询数据库表)
     */
    private <T extends CsdlStructObject> CsdlArrayList<T> getSdlObjList()
    {
        List<DataObject> tblObjList = this.readUploadList();// 查询具体业务表记录列表
        if (CommonUtil.isEmpty(tblObjList))
        {
            return null;
        }

        CsdlArrayList<T> sdlObjList = new CsdlArrayList<T>(this.getSdlObjClass());
        for (DataObject tblObj : tblObjList)
        {
            T sdlObj = (T) this.newSdlObjInstance();
            ConvertUtil.copyJava2Sdl(sdlObj, tblObj);// 把ims对象中的数据拷贝到其对应的sdl对象中
            sdlObjList.add(sdlObj);
        }
        return sdlObjList;
    }

    /**
     * 读取需要上发的数据列表<br>
     * (默认实现是从接口表中读取数据 )<BR>
     * 特殊业务子类可重载<br>
     * wangjt 2011-9-27
     */
    public <T extends DataObject> List<T> readUploadList()
    {
        return queryTblObjList();
    }

    /**
     * 获取接口表中符合条件的记录列表（读数据操作默认实现方式）<BR>
     * (请不要覆盖该方法，可直接覆盖readUploadList方法)
     */
    private final <T extends DataObject> List<T> queryTblObjList()
    {
        Class<? extends DataObject> tblObjClass = this.getTblObjClass();

        // 同步,只能由一个线程来读取一张表中的数据
        synchronized (tblObjClass)
        {
            OrderCondition[] orders = new OrderCondition[1];
            Field createDateField = DBUtil.getJefField(tblObjClass, "createDate");
            orders[0] = new OrderCondition(createDateField);// 根据createDate排序
            Field stsField = DBUtil.getJefField(tblObjClass, "dealSts");
            if (stsField == null)
            {
                stsField = DBUtil.getJefField(tblObjClass, "sts");

            }
            Field dealDateField = DBUtil.getJefField(tblObjClass, "dealDate");

            int count = SysUtil.getInt(SysCodeDefine.busi.ONE_TIME_DEAL_RECORD_COUNT, 1000);// 一次处理记录条数

            // 获取接口表中，状态为失败的数据，优先处理
            List<DataObject> failList = DBUtil.query(tblObjClass, orders, new IntRange(0, count), new DBCondition(stsField,
                    EnumCodeDefine.SYNC_DEAL_STS_FAIL));

            if (!CommonUtil.isEmpty(failList))
            {
                count = count - failList.size();
            }
            List<DataObject> handingList = null;
            if (count > 0)
            {
                handingList = DBUtil.query(tblObjClass, orders, new IntRange(0, count), new DBCondition(stsField,
                        EnumCodeDefine.SYNC_DEAL_STS_HANDLING), new DBCondition(dealDateField, this.getTimeOutDate(),
                        Operator.LESS));

            }
            if (!CommonUtil.isEmpty(handingList))
            {
                count = count - handingList.size();
            }
            List<DataObject> initialList = null;
            if (count > 0)
            {
                initialList = DBUtil.query(tblObjClass, orders, new IntRange(0, count), new DBCondition(stsField,
                        EnumCodeDefine.SYNC_DEAL_STS_INITIAL));
            }
            // 合并列表
            List<DataObject> mergeList = IMSUtil.mergeList(failList, handingList, initialList);

            this.set2HandlingSts(mergeList);// 修改接口表中记录状态，同时修改处理时间，并提交事务

            return (List<T>) mergeList;
        }
    }

    /**
     * 修改接口表中记录状态，同时修改处理时间，并提交事务
     */
    private void set2HandlingSts(List<? extends DataObject> tblObjList)
    {
        if (CommonUtil.isEmpty(tblObjList))
        {
            return;
        }
        for (DataObject dm : tblObjList)
        {
            DataObject value = this.newTblObjInstance();
            this.setFieldValue(value, "dealSts", EnumCodeDefine.SYNC_DEAL_STS_HANDLING);// 设为处理中
            this.setFieldValue(value, "dealDate", context.getRequestDate());
            KeyField keyField = new KeyField(dm);

            DataObject where = this.newTblObjInstance();
            this.setFieldValue(where, keyField.keyFieldName, keyField.getKeyFieldValue());// 根据主键字段更新
            DBUtil.updateByCondition(value, new DBCondition(DBUtil.getJefField(where.getClass(), keyField.keyFieldName),keyField.getKeyFieldValue()));
        }
        // 提交事务
        DBUtil.commit();
    }

    /**
     * 从sdl对象列表中，获取table对象列表
     */
    private <T extends DataObject> List<T> getTblObjList(List<? extends CsdlStructObject> sdlObjList)
    {
        if (CommonUtil.isEmpty(sdlObjList))
        {
            return null;
        }

        List<T> tblObjList = new ArrayList<T>();

        for (CsdlStructObject sdlObj : sdlObjList)
        {
            T tblObj = (T) this.newTblObjInstance();

            ConvertUtil.copySdl2Java(sdlObj, tblObj);
            tblObjList.add(tblObj);
        }
        return tblObjList;
    }

    /**
     * 新建一个table对象的实例
     */
    private DataObject newTblObjInstance()
    {
        try
        {
            return this.getTblObjClass().newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException(" new table instance error!!");
        }
    }

    /**
     * 新建一个table rst对象的实例
     */
    public DataObject newTblObjInstance(String suffix)
    {
        String className = this.getTblObjClass().getName();
        try
        {
            return (DataObject) Class.forName(className + suffix).newInstance();
        }
        catch (Exception e)
        {
            return null;// 没有rst表，返回null
        }
    }

    /**
     * 新建一个sdl对象的实例
     */
    private CsdlStructObject newSdlObjInstance()
    {
        try
        {
            return this.getSdlObjClass().newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException(" new sdl instance error!!");
        }
    }

    /**
     * 获取过期时间，接口表中，状态为处理中，并且create_date早于这个过期时间的数据，需优先处理
     */
    public Date getFlagDate()
    {
        int hour = -1 * SysUtil.getInt(SysCodeDefine.busi.TIMEOUT_HOUR_VALUE, 2);

        Date requestDate = this.getContext().getRequestDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(requestDate);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * 读取deal_sts=2，并且处理超时（超时时间可配置，如60秒<根据具体业务分开配置，提供默认配置>，是否超时参考字段为deal_date）的数据，
     */
    private Date getTimeOutDate()
    {
        // 这里可以设置超时时间
        int second = -1 * SysUtil.getInt(SysCodeDefine.busi.TIMEOUT_SECOND_VALUE, 60);
        Date requestDate = this.getContext().getRequestDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(requestDate);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 自定义方法，不抛异常
     */
    public boolean setFieldValue(Object obj, String fieldName, Object fieldValue)
    {
        try
        {
            ClassUtil.setFieldValue(obj, fieldName, fieldValue);
            return true;
        }
        catch (Exception e)
        {
            imsLogger.debug(e.getMessage());
            return false;
        }
    }

    // /**
    // * 自定义方法，不抛异常
    // */
    // private Object getFieldValue(Object obj, String fieldName)
    // {
    // try
    // {
    // return ClassUtil.getFieldValue(obj, fieldName);
    // }
    // catch (Exception e)
    // {
    // imsLogger.error(e.getMessage());
    // return null;
    // }
    // }

    /**
     * 一个表对象的主键字段
     */
    class KeyField
    {
        private String keyFieldName;// 如 id，soNbr
        private jef.database.Field keyField;
        private Object keyFieldValue;

        public KeyField(DataObject tblObj)
        {
            keyFieldName = "id";
            Class dmClz = tblObj.getClass();
            keyField = DBUtil.getJefField(dmClz, keyFieldName);
            if (keyField == null)
            {
                keyFieldName = "soNbr";
                keyField = DBUtil.getJefField(dmClz, keyFieldName);
            }
            keyFieldValue = BeanUtils.getFieldValue(tblObj, keyFieldName);
        }

        public String getKeyFieldName()
        {
            return keyFieldName;
        }

        public jef.database.Field getKeyField()
        {
            return keyField;
        }

        public Object getKeyFieldValue()
        {
            return keyFieldValue;
        }
    }
}
