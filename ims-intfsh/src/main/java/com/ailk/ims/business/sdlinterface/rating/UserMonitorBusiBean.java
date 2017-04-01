package com.ailk.ims.business.sdlinterface.rating;

import java.util.ArrayList;
import java.util.List;
import jef.database.Batch;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SResourceMonitor;
import com.ailk.openbilling.persistence.jd.entity.ImsResMonitor;

/**
 * @Description 提供给计费的接口，插入受控用户表，通知给CRM
 * @author wukl
 * @Date 2012-9-1
 * @Date 2012-10-27 wukl 反向表移到JD用户下的修改
 */
public class UserMonitorBusiBean extends BaseCancelableBusiBean
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
        CsdlArrayList<SResourceMonitor> resMonitorList = (CsdlArrayList<SResourceMonitor>) input[0];
        List<ImsResMonitor> list = new ArrayList<ImsResMonitor>();
        for (SResourceMonitor resMonitor : resMonitorList)
        {
            list.add(createImsResourceMonitor(resMonitor));
        }
        if (list.size() > 0)
        {
            try
            {
                Batch<ImsResMonitor> batch = DBUtil.getDBClient().startBatchInsert(ImsResMonitor.class);
                batch.add(list);
                batch.setGroupForPartitionTable(true);
                batch.commit();
            }
            catch (Exception e)
            {
                throw IMSUtil.throwBusiException(e);
            }
        }

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

    private ImsResMonitor createImsResourceMonitor(SResourceMonitor resMonitor)
    {
        ImsResMonitor imsResMonitor = new ImsResMonitor();
        imsResMonitor.setResourceId(resMonitor.getServId());
        imsResMonitor.setServiceId(resMonitor.getServiceId());
        imsResMonitor.setMonitorCondId(resMonitor.getMoniCondId());
        imsResMonitor.setMonitorType(resMonitor.getMoniType());
        imsResMonitor.setRemark(resMonitor.getNotes());
        imsResMonitor.setValidDate(ConvertUtil.sdlLong2JavaDate(resMonitor.getValidDate()));
        imsResMonitor.setExpireDate(ConvertUtil.sdlLong2JavaDate(resMonitor.getExpireDate()));
        imsResMonitor.setOperType(resMonitor.getOperType());
        imsResMonitor.setCreateDate(context.getRequestDate());
        imsResMonitor.setSoNbr(context.getDoneCode());
        imsResMonitor.setSts(1);
        imsResMonitor.setDealDate(context.getRequestDate());
        imsResMonitor.setSourceId(13);//数据渠道,默认为13
        return imsResMonitor;
    }
}
