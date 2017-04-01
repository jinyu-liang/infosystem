package com.ailk.ims.ts.business;

import jef.database.DataObject;
import com.ailk.ims.business.mdberror.sync.MdbUPBean;
import com.ailk.ims.common.BaseTsBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.cust.entity.ImsMdbError;
import com.ailk.openbilling.persistence.cust.entity.ImsMdbErrorRst;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

public class MdbErrorTsBean extends BaseTsBean
{

    @Override
    public boolean isDealBatch()
    {
        return false;
    }

    @Override
    public Class<? extends DataObject> getJavaClass()
    {
        return ImsMdbError.class;
    }

    @Override
    public Class<? extends DataObject> getRstJavaClass()
    {
        return ImsMdbErrorRst.class;
    }

    @Override
    public CBSErrorMsg deal(Object dbObj)
    {
        ImsMdbError record = (ImsMdbError) dbObj;
        String upfield = record.getUpField();
        if (CommonUtil.isEmpty(upfield))
            return null;

        Long doneCode = record.getId();
        IMSContext context = new IMSContext();
        context.setRequestDate(DateUtil.currentDate());
        // 查询流水表，构建SOperInfo
        SOperInfo operInfo = BusiRecordUtil.buildOperInfo(doneCode);
        context.setOper(operInfo);
        context.setDoneCode(doneCode);
        BaseNode methodConfig = BusiUtil.getMethodNode(record.getBusiCode());
        if (methodConfig == null)
        {
            throw IMSUtil
                    .throwBusiException("method with busi_code \"" + record.getBusiCode() + "\" does not exsit in beans.xml");
        }
        context.setMethodConfig(methodConfig);
        com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg errorMsg = new MdbUPBean(context).sendError(record);

        if (errorMsg == null || errorMsg.get_errorCode() == 0)
            return null;// 处理成功

        com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg result = new com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg();

        // 需要从sdl的CBSErrorMsg转成需要的CBSErrorMsg对象
        result.setResult_code((long) errorMsg.get_errorCode());
        result.setError_msg(errorMsg.get_errorMsg());
        result.setHint(errorMsg.get_hint());
        return result;
    }

}
