package com.ailk.ims.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.sd.entity.SysEntityMultiLang;
import com.ailk.openbilling.persistence.sys.entity.SysMeasure;
import com.ailk.openbilling.persistence.sys.entity.SysMeasureUnitExchange;

/**
 * 
 * @Description 单位转换类
 * @author zhangzj3
 * @Date 2012-12-13
 */
public class MeasureComponentSH extends BaseComponent{
    /**
     * @Description: 根据measure_id查询measure_type_id
     * @param measureId
     * @return   
     * @author: zhangzj3
     * @Date: 2012-12-13
     */
    @SuppressWarnings("deprecation")
    public Integer queryMeasureTypeId(Integer measureId){
        SysMeasure sysMeasure = DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition(
                SysMeasure.Field.measureId, measureId));
        if (sysMeasure == null){
            IMSUtil.throwBusiException(ErrorCodeDefine.LACK_CONFIG_DATE, "sys_measure", "measure_id", measureId);
        }
        return sysMeasure.getMeasureTypeId();
    }
    
    /**
     * @Description: 查询后台处理资金时使用的度量。
     * @param measureTypeId
     * @return   SysMeasure
     * @author: zhangzj3
     * @Date: 2012-12-13
     */
    @SuppressWarnings("deprecation")
    public SysMeasure queryPrecisionMeasure(Integer measureTypeId){
        SysMeasure sysMeasure = DBConfigInitBean.getSingleCached(SysMeasure.class, 
                new DBCondition(SysMeasure.Field.measureTypeId, measureTypeId),
                //0：表示无意义，1：后台处理资金时，使用的度量精确。（每一个货币类型中，都有且只有一个precisionFlag=1的精度标识）
                new DBCondition(SysMeasure.Field.precisionFlag,1));
        if (sysMeasure == null){
            IMSUtil.throwBusiException(ErrorCodeDefine.LACK_CONFIG_DATE2, "sys_measure", "measure_id", measureTypeId,"precision_flag",1);
        }
        return sysMeasure;
    }
    
    /**
     * @Description: 查询显示的度量。
     * @param measureTypeId
     * @return   SysMeasure
     * @author: zhangzj3
     * @Date: 2012-12-13
     */
    @SuppressWarnings("deprecation")
    public SysMeasure queryDisplayMeasure(Integer measureTypeId){
        SysMeasure sysMeasure = DBConfigInitBean.getSingleCached(SysMeasure.class, 
                new DBCondition(SysMeasure.Field.measureTypeId, measureTypeId),
                //：表示不用于显示，1：用于显示
                new DBCondition(SysMeasure.Field.isDisplay,1));
        if (sysMeasure == null){
            IMSUtil.throwBusiException(ErrorCodeDefine.LACK_CONFIG_DATE2, "sys_measure", "measure_id", measureTypeId,"is_display",1);
        }
        return sysMeasure;
    }
    
    /**
     * @Description: 货币单位转换
     * @param amount
     * @param origMeasureId 原始单位
     * @param targetMeasureId 目标单位
     * @return   
     * @author: zhangzj3
     * @Date: 2012-12-13
     */
    @SuppressWarnings("deprecation")
    public Long parseMeasure(Long amount,Integer origMeasureId, Integer targetMeasureId)
    {
        SysMeasureUnitExchange exchange = DBConfigInitBean.getSingleCached(SysMeasureUnitExchange.class,
                new CacheCondition(SysMeasureUnitExchange.Field.measureId, origMeasureId), 
                new CacheCondition(SysMeasureUnitExchange.Field.destMeasureId, targetMeasureId));
        if (exchange == null){
            throw IMSUtil.throwBusiException("measure_id = " + origMeasureId + " and dest_measure_id="
                    + targetMeasureId + " does not exist SYS_MEASURE_UNIT_EXCHANGE table!");
        }
        return amount * exchange.getExchangeNumerator() / exchange.getExchangeDenominator();
    }
    
    /**
     * @Description: 获取系统默认显示的货币单位
     * @param origMeasureId 原始单位
     * @return   
     * @author: zhangzj3
     * @Date: 2012-12-13
     */
    public SysMeasure querySysMeauser(Integer origMeasureId)
    {   
        //获取该measure_id所对应的measure_type_id
        Integer meatsureTypeId = queryMeasureTypeId(origMeasureId);
        //获取该measure_type_id所定义的系统显示measure_id
        SysMeasure measure = queryDisplayMeasure(meatsureTypeId);
        return measure;
    }
    
    /**
     * @Description: 获取科目名称
     * @author zhangzhj 2012-12-14
     * @param itemCode[]科目集合
     * @return 
     */
    public Map<String,String> queryItemShortName(String[] itemCode){
        List<SysEntityMultiLang> sysEntityList = DBUtil.query(
                SysEntityMultiLang.class,
                new DBCondition(SysEntityMultiLang.Field.entityId,4003),
                new DBCondition(SysEntityMultiLang.Field.entityType,8006),
                new DBCondition(SysEntityMultiLang.Field.entityValue,itemCode,Operator.IN)
        );
        Map<String,String> map = new HashMap<String, String>();
        if(CommonUtil.isNotEmpty(sysEntityList)){
            for(SysEntityMultiLang tem : sysEntityList){
                map.put(tem.getEntityValue(), tem.getShortName());
            }
        }
        return map;
    }
}
