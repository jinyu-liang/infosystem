package com.ailk.ims.business.mdberror.sync;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.database.DataObject;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.ims.common.BaseMdbBean;
import com.ailk.ims.common.ContextBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.init.UpfieldInitBeanBak;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.openbilling.persistence.cust.entity.ImsMdbError;

/**
 * @Description: mdb异步上发处理bean
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-9-27
 */
public class MdbUPBean extends ContextBean
{
    public MdbUPBean(IMSContext context)
    {
        this.setContext(context);
    }

    /**
     * @Description:保存错单
     * @param mdbError
     */
    public void handleError(CBSErrorMsg mdbError)
    {
        if (mdbError == null)
            return;
        try
        {
            Date currentDate = DateUtil.currentDate();
            ImsMdbError dmError = new ImsMdbError();
            dmError.setId(context.getSoNbr());
            dmError.setBusiCode(context.getOper().getBusi_code());
            dmError.setCreateDate(currentDate);
            dmError.setDealDate(currentDate);
            dmError.setUpField(buildUpField());
            dmError.setErrorCode(mdbError.get_errorCode());
            dmError.setErrorMsg(mdbError.get_hint() + ":" + mdbError.get_errorMsg());
            dmError.setSts((int) EnumCodeDefine.SYNC_DEAL_STS_INITIAL);// 表示初始状态
            dmError.setDealCount(0);

            DBUtil.insert(dmError);
        }
        catch (Exception e)
        {
            // 数据库操作失败则保存到文件
        	imsLogger.error(e,e);
        }
    }

    /**
     * @Description: 上发一条错单
     */
    public CBSErrorMsg sendError(ImsMdbError record)
    {
        String upfield = record.getUpField();
        if (CommonUtil.isEmpty(upfield))
            return null;
        CBSErrorMsg errorMsg = null;
        try
        {
            List<? extends Class> list = UpfieldInitBeanBak.parseUpField(upfield);
            if (CommonUtil.isEmpty(list))
                return null;
            Long doneCode = record.getId();
            // Integer busiCode = record.getBusiCode();

            for (Class clz : list)
            {
                List<DataObject> relList = dealUpClass(clz, doneCode);
                context.cacheList(relList);// 设置到缓存中
            }
            context.setSync(false);// 标志是异步

            // 提取出重新上发mdb的处理，可供子类重写
            errorMsg = this.reSync();
        }
        catch (Exception e)
        {
            imsLogger.error("error occurs : " + "so_nbr = " + record.getId() + " , busi_code = " + record.getBusiCode(), e);

            errorMsg = new CBSErrorMsg((int) ErrorCodeDefine.UNKNOWN_ERROR, e.getMessage(), null);
        }

        return errorMsg;
    }

    /**
     * 提取出重新上发mdb的处理，可供子类重写
     * 
     * @author wangjt 2012-5-29
     */
    public CBSErrorMsg reSync() throws Exception
    {
        BaseMdbBean mdbBean = BusiUtil.getMdbBean(context.getServiceName(), context.getMethodName());
        mdbBean.setContext(context);
        CBSErrorMsg errorMsg = mdbBean.doSyncronization(null);
        return errorMsg;
    }

    /**
     * @Description: 重缓存中解析出需要上发的表，构建成upfield字段
     * @return
     */
    private String buildUpField()
    {
        StringBuffer sb = new StringBuffer();

        List<Class<? extends DataObject>> upClasses = UpfieldInitBeanBak.getUpFieldClasses();

        for (Class<? extends DataObject> clazz : upClasses)
        {
            if (context.containCache(clazz))
            {
                imsLogger.debug("****** ", clazz.getName(), " : 1");
                sb.append(1);
            }
            else
            {
                imsLogger.debug("****** ", clazz.getName(), " : 0");
                sb.append(0);
            }
        }
        return sb.toString();
    }

    /**
     * @Description:处理某一条错误记录中的某一张表
     * @param clz,需要处理的表
     * @param doneCode，so_nbr
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @return,这张表需要上发的记录
     */
    private List<DataObject> dealUpClass(Class clz, long doneCode) throws Exception
    {
        // DataObject where = (DataObject) clz.newInstance();
        // ClassUtil.setFieldValue(where, ConstantDefine.ENTITY_FIELD_SO_NBR, doneCode);
        List<DataObject> matchList = DBUtil.query(clz,
                new DBCondition(DBUtil.getJefField(clz, ConstantDefine.ENTITY_FIELD_SO_NBR), doneCode));// 查出so_nbr相同的记录
        if (CommonUtil.isEmpty(matchList))
            return null;

        List<String> fields = UpfieldInitBeanBak.getUpFields(clz);
        List<DataObject> result = new ArrayList<DataObject>();
        for (DataObject match : matchList)
        {
            // where = (DataObject) clz.newInstance();
            List<DBCondition> cond = new ArrayList<DBCondition>();
            for (String f : fields)
            {
                Object fValue = ClassUtil.getPropertyValue(match, f);
                // ClassUtil.setFieldValue(where, f, fValue);
                cond.add(new DBCondition(DBUtil.getJefField(clz, f), fValue));
            }
            List<DataObject> relList = DBUtil.query(clz, cond.toArray(new DBCondition[cond.size()]));// 查出和字段值一样的所有记录
            if (!CommonUtil.isEmpty(relList))
            {
                result.addAll(relList);
            }
        }
        return result;
    }
}
