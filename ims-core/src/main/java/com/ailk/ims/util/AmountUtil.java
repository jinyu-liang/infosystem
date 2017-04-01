package com.ailk.ims.util;

import java.util.ArrayList;
import java.util.List;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.MeasureBean;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.openbilling.persistence.sys.entity.SysMeasure;
import com.ailk.openbilling.persistence.sys.entity.SysMeasureType;
import com.ailk.openbilling.persistence.sys.entity.SysMeasureUnitExchange;

/**
 * @Description:货币转换的工具类
 * @author wangjt
 * @Date 2011-12-29
 */
public class AmountUtil
{
    /**
     * @Description: 获取转换后的货币信息
     * @author : wuyj
     * @date : 2011-12-29
     * @param measureBean，传入的measure信息对象
     * @return,转换后的货币信息对象
     */
    private static MeasureBean getMeasure(MeasureBean measureBean)
    {
        // 获取传入的mesure_id的measure_type_id
        SysMeasure orig_measure = DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition(
                SysMeasure.Field.measureId, measureBean.getMeasureId()));
        if (orig_measure == null)
        {
            throw IMSUtil.throwBusiException("measure_id<" + measureBean.getMeasureId() + "> does not exist!");
        }
        int typeId = orig_measure.getMeasureTypeId();
        String msg = null;

        // 根据typeId和传入的flag，查询出真正的目标measure记录
        List<CacheCondition> conds = new ArrayList<CacheCondition>();
        conds.add(new CacheCondition(SysMeasure.Field.measureTypeId, typeId));
        if (measureBean.getFlag() == MeasureBean.FLAG_DISPLAY)
        {
            conds.add(new CacheCondition(SysMeasure.Field.isDisplay, 1));
            msg = SysMeasure.Field.isDisplay.name() + " = 1";
        }
        else if (measureBean.getFlag() == MeasureBean.FLAG_PRECISION)
        {
            conds.add(new CacheCondition(SysMeasure.Field.precisionFlag, 1));
            msg = SysMeasure.Field.precisionFlag.name() + " = 1";
        }
        SysMeasure targetMeasure = DBConfigInitBean.getSingleCached(SysMeasure.class,
                conds.toArray(new CacheCondition[conds.size()]));
        if (targetMeasure == null)
        {
            throw IMSUtil.throwBusiException("measure_type_id = " + typeId + " and " + msg + " does not exist!");
        }

        // 如果目标货币等同传入货币，则直接返回
        if (targetMeasure.getMeasureId().intValue() == measureBean.getMeasureId())
        {
            return measureBean;
        }

        // 货币转换
        SysMeasureUnitExchange exchange = DBConfigInitBean.getSingleCached(SysMeasureUnitExchange.class, new CacheCondition(
                SysMeasureUnitExchange.Field.measureId, orig_measure.getMeasureId()), new CacheCondition(
                SysMeasureUnitExchange.Field.destMeasureId, targetMeasure.getMeasureId()));
        if (exchange == null)
        {
            throw IMSUtil.throwBusiException("measure_id = " + orig_measure.getMeasureId() + " and dest_measure_id="
                    + targetMeasure.getMeasureId() + " does not exist SYS_MEASURE_UNIT_EXCHANGE table!");
        }
        double amount = measureBean.getAmount() * exchange.getExchangeNumerator() / exchange.getExchangeDenominator();

        // 组织返回信息
        MeasureBean retMeasureBean = new MeasureBean(targetMeasure.getMeasureId(), targetMeasure.getName(), amount,
                measureBean.getFlag());
        return retMeasureBean;
    }

    /**
     * 获取数据库默认存储的measure_id
     * 
     * @author wangjt 2011-12-29
     */
    public static Integer getDefaultDbMeasureId()
    {
        SysMeasureType sysMeasureType = DBConfigInitBean.getSingleCached(SysMeasureType.class, new CacheCondition(
                SysMeasureType.Field.localCurrency, 1), new CacheCondition(SysMeasureType.Field.measureClass, 0));

        if (sysMeasureType == null)
        {
            throw IMSUtil.throwBusiException("config record in sd.sys_measure_type<local_currency=1> does not exist!");
        }

        SysMeasure sysMeasure = DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition(
                SysMeasure.Field.measureTypeId, sysMeasureType.getMeasureTypeId()), new CacheCondition(
                SysMeasure.Field.precisionFlag, 1));

        if (sysMeasure == null)
        {
            throw IMSUtil.throwBusiException("config record in sd.sys_measure<precision_flag=1, measure_type_id="
                    + sysMeasureType.getMeasureTypeId() + "> does not exist!");
        }

        return sysMeasure.getMeasureId();// 数据库默认存储的measure_id
    }
    /**
     * 获取默认的显示的存储的measure_id
     * @return
     */
    public static Integer getDefaultDisplayMeasureId()
    {
        SysMeasureType sysMeasureType = DBConfigInitBean.getSingleCached(SysMeasureType.class, new CacheCondition(
                SysMeasureType.Field.localCurrency, 1), new CacheCondition(SysMeasureType.Field.measureClass, 0));

        if (sysMeasureType == null)
        {
            throw IMSUtil.throwBusiException("config record in sd.sys_measure_type<local_currency=1> does not exist!");
        }

        SysMeasure sysMeasure = DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition(
                SysMeasure.Field.measureTypeId, sysMeasureType.getMeasureTypeId()), new CacheCondition(
                SysMeasure.Field.isDisplay, 1));

        if (sysMeasure == null)
        {
            throw IMSUtil.throwBusiException("config record in sd.sys_measure<is_display=1, measure_type_id="
                    + sysMeasureType.getMeasureTypeId() + "> does not exist!");
        }

        return sysMeasure.getMeasureId();// 数据库默认存储的measure_id
    }


    /**
     * 根据输入measure_id获取数据库存储的measure_id
     * 
     * @author wangjt 2011-12-29
     */
    public static Integer getDbMeasureId(Integer measureId)
    {
        if (measureId == null)
        {
            return getDefaultDbMeasureId();// 数据库默认存储的measure_id
        }
        return getMeasure(new MeasureBean(measureId, 0, MeasureBean.FLAG_PRECISION)).getMeasureId();
    }

    /**
     * 根据输入measure_id获取显示的measure_id
     * 
     * @author wangjt 2012-2-1
     */
    public static Integer getDisplayMeasureId(Integer measureId)
    {
        if (measureId == null)
        {
            measureId = getDefaultDbMeasureId();// 数据库默认存储的measure_id
        }
        return getMeasure(new MeasureBean(measureId, 0, MeasureBean.FLAG_DISPLAY)).getMeasureId();
    }

    /**
     * 从参数中的amount转换为数据库的amount
     * 
     * @author wangjt 2011-12-29
     */
    public static Long getDbAmount(Integer measureId, Double amount)
    {
        // 目前暂时不转换 2011-12-31 yanchuan
        // // 2012-3-30 luojb 转换
        if (amount == null)
        {
            return Long.valueOf(0L);
        }
        if (measureId == null)
        {
            measureId = getDefaultDbMeasureId();
            // 数据库默认存储的measure_id
        }
        MeasureBean dbMeasure = getMeasure(new MeasureBean(measureId, amount, MeasureBean.FLAG_PRECISION));
        return CommonUtil.double2Long(dbMeasure.getAmount());
        // return CommonUtil.double2Long(amount);
    }

    /**
     * 提供给界面使用的将参数中的amount转换为数据库的amount
     * 
     * @author yanchuan 2012-01-31
     */
    public static Long getDbAmountForGUI(Integer measureId, Double amount)
    {
        // 目前暂时不转换 2011-12-31 yanchuan
        if (amount == null)
        {
            return Long.valueOf(0L);
        }
        if (measureId == null)
        {
            measureId = getDefaultDbMeasureId();// 数据库默认存储的measure_id
        }

        MeasureBean dbMeasure = getMeasure(new MeasureBean(measureId, amount, MeasureBean.FLAG_PRECISION));
        return CommonUtil.double2Long(dbMeasure.getAmount());
    }

    /**
     * 从数据库中的amount转换为显示的amount
     * 
     * @author wangjt 2011-12-29
     * @param measureId: 数据库记录中的measureId
     * @param amount:数据库中的值
     */
    public static Double getDisplayAmount(Integer measureId, Long amount)
    {
        if (amount == null)
        {
            return Double.valueOf(0);
        }
        if (measureId == null)
        {
            measureId = getDefaultDbMeasureId();// 数据库默认存储的measure_id
        }
        return getMeasure(new MeasureBean(measureId, amount, MeasureBean.FLAG_DISPLAY)).getAmount();
    }

}
