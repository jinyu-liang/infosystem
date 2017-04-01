package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.jd.entity.ImsSnMapping;
import com.ailk.openbilling.persistence.jd.entity.ImsUniqueMapping;

/**
 * @Description: 充值组件，用于定义跟充值操作相关的各种方法
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wangyh3
 * @Date 2012-05-08
 * @Date 2012-05-08 wangyh3 : 增加方法unDdisposit
 */
public class DispositComponent extends BaseComponent
{
    public DispositComponent()
    {
    }

   

    /**
     * @description:工单映射改用 IMS_SN_MAPPING表，按out_so_nbr分表
     * @authorwangyh3
     * @Date:2012-6-16
     */
    public ImsSnMapping querySnMapping(String outSoMode, String outSoNbr,Long acctId)
    {
        List<DBCondition> constList = new ArrayList<DBCondition>();
        if (CommonUtil.isValid(acctId))
        {
            constList.add(new DBCondition(ImsSnMapping.Field.accountId, acctId));
        }
        if (!CommonUtil.isEmpty(outSoNbr))
        {
            constList.add(new DBCondition(ImsSnMapping.Field.outSoNbr, outSoNbr));
        }
        if (!CommonUtil.isEmpty(outSoMode))
        {
            constList.add(new DBCondition(ImsSnMapping.Field.outSoMode, outSoMode));
        }
        List<ImsSnMapping> mappingList = this.query(ImsSnMapping.class, 
        		constList.toArray(new DBCondition[constList.size()]));
//        ImsSnMapping mapping=this.querySingle(ImsSnMapping.class, constList.toArray(new DBCondition[constList.size()]));
        if (mappingList == null||mappingList.isEmpty())
        {
            return null;
        }
        return mappingList.get(0);
    }

    /**
     * @description:保存内外部工单号的映射
     * @author caohm5
     * @Date:2012-06-16
     */
    public void saveSoNbrMapping(Long acctId, String outSoNbr, String outSoDate, Long bosSoNbr, Date bosSoDate)
    {
        ImsSnMapping mapping = new ImsSnMapping();
        if (CommonUtil.isNotEmpty(outSoNbr))
        {
            mapping.setOutSoNbr(outSoNbr);
        }
        else
        {
            mapping.setOutSoNbr("" + bosSoNbr);
        }
        if (CommonUtil.isNotEmpty(outSoDate))
        {
            mapping.setOutSoDate(IMSUtil.formatDate(outSoDate, null));
        }
        else
        {
            mapping.setOutSoDate(bosSoDate);
        }
        mapping.setBosSoNbr(bosSoNbr);
        mapping.setBosSoDate(bosSoDate);
        mapping.setOutSoMode(CommonUtil.short2String2Bit(context.getOper().getSo_mode()));
        // TODO 目前账户编号没有设置值
        // mapping.setAccountId(obj);
        mapping.setAccountId(acctId);
        BaseComponent baseCmp = (BaseComponent) context.getComponent(BaseComponent.class);
        baseCmp.insert(mapping);
    }
    
    
    
   /**
    * 保存流水信息到表JD.IMS_UNIQUE_MAPPING
    * @param acctId
    * @param outSoNbr
    * @param outSoDate
    * @param bosSoNbr
    * @param bosSoDate
    */
    public void saveOutSoNbrMapping(Long acctId, String outSoNbr, String outSoDate, Long bosSoNbr, Date bosSoDate)
    {
        ImsUniqueMapping mapping = new ImsUniqueMapping();
        if (CommonUtil.isNotEmpty(outSoNbr))
        {
            mapping.setOutSoNbr(outSoNbr);
        }
        else
        {
            mapping.setOutSoNbr("" + bosSoNbr);
        }
        if (CommonUtil.isNotEmpty(outSoDate))
        {
            mapping.setOutSoDate(IMSUtil.formatDate(outSoDate, null));
        }
        else
        {
            mapping.setOutSoDate(bosSoDate);
        }
        mapping.setBosSoNbr(bosSoNbr);
        mapping.setBosSoDate(bosSoDate);
        mapping.setOutSoMode(CommonUtil.short2String2Bit(context.getOper().getSo_mode()));
        // TODO 目前账户编号没有设置值
        // mapping.setAccountId(obj);
        mapping.setAccountId(acctId);
        BaseComponent baseCmp = (BaseComponent) context.getComponent(BaseComponent.class);
        baseCmp.insert(mapping);
    }
    


    /**
     * 判断该工单号是否已经使用过
     */

    public Boolean checkSoNbrIsExist(String outSoMode, String outSoNbr,Long acctId)
    {

        ImsSnMapping mapping = querySnMapping(outSoMode, outSoNbr,acctId);

        if (mapping != null && CommonUtil.isNotEmpty(mapping.getOutSoNbr()))
        {

            return true;
        }
        return false;
    }
}
