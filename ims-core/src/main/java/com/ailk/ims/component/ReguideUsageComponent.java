package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.ResultTable;
import com.ailk.ims.complex.CharValueHelper;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.imsintf.entity.SPayReguideInfo;

/**
 * @Description:代付使用费
 * @author wangjt
 * @Date 2011-9-27
 * @Date 2012-04-03 lijc3 合并产品
 * @Date 2012-3-31 tangjl5 修改用户被REGUIDE_USAGE的产品,删除多余代码
 * @Date 2012-04-10 yangjh Time_period->Time_segment
 * @Date 2012-04-11 yangjh modifyReguide 修改objectId获取
 * @Date 2012-05-10 lijc3 增加处理货币单位
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @date 2012-08-10 luojb #54807 time_segment改为String
 * @Date 2012-11-13 wangdw5 : 传入String会调用查询手机
 */
public class ReguideUsageComponent extends ReguideComponent
{
    public ReguideUsageComponent()
    {
        super.setReguideType(EnumCodeDefine.REGUIDE_TYPE_USAGE);
    }

    /**
     * 
     * luojb 2012-8-10
     * @param payAcctId
     * @param userId
     * @param busiServiceId
     * @param sPayReguideInfo
     * @param timeSegment
     * @date 2012-08-10 luojb #54807 time_segment改为String
     */
    public void createReguide(Long payAcctId, Long userId, Long busiServiceId, SPayReguideInfo sPayReguideInfo, String timeSegment)
    {
        CoProd coProd = super.insertCoProd(sPayReguideInfo);
        Long prodId = coProd.getProductId();
        this.insertCharValueList(prodId, sPayReguideInfo, busiServiceId, timeSegment, userId.toString());

        // 增加账期(和账户账期一致)
        ProductCycleComponent prodCmp = context.getComponent(ProductCycleComponent.class);
        prodCmp.createProdBillCycle(coProd, payAcctId);
    }

    /**
     * 
     * luojb 2012-8-10
     * @param prodId
     * @param info
     * @param busiServiceId
     * @param timeSegment
     * @param objectId
     * @date 2012-08-10 luojb #54807 time_segment改为String
     */
    private void insertCharValueList(Long prodId, SPayReguideInfo info, Long busiServiceId, String timeSegment, String objectId)
    {
        String validDate = info.getValid_date();
        String expireDate = info.getExpire_date();
        
        CharValueHelper helper = new CharValueHelper(prodId, busiServiceId, validDate, expireDate, super.getUserIdByPhoneId(info
                .getPay_phone_id()), EnumCodeDefine.PROD_OBJECTTYPE_DEV);

        List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();
        // busiServiceId 变为场景业务标示,计费通过业务标示来查询服务编号和策略号
        list.add(helper.getNew(SpecCodeDefine.REGUIDE_USAGE_POLICY_ID, busiServiceId));
        list.add(helper.getNew(SpecCodeDefine.RU_TIME_PERIOD, timeSegment));
        list.add(helper.getNew(SpecCodeDefine.RU_USER_ID, objectId));

        list.add(helper.getNew(SpecCodeDefine.RU_PRIORITY, info.getPriority()));
      //2012-10-21 linzt 增加b_Number 判断
        int i=info.getB_number().indexOf(",");
        if(i!=-1){
        	String [] str=info.getB_number().split(",");
        	for(String s:str){
        		list.add(helper.getNew(SpecCodeDefine.RU_B_NUMBER, s));
        	}
        }
        else{
        	list.add(helper.getNew(SpecCodeDefine.RU_B_NUMBER, info.getB_number()));
        }
        // 2012-2-27taoyf 增加一个12001的特征值，而且暂时统一填-999.
        // list.add(helper.getNew(SpecCodeDefine.REGUIDE_USAGE_POLICY_ID, EnumCodeDefine.MDB_DEFAULT_VALUE));

        // 2011-12-15 hunan add :从配置表中读取增加的提醒值
        ConfigQuery cfgCmp = context.getComponent(ConfigQuery.class);
        String notiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.RU_PAY_SUCC_NOTI);
        String bePaidSideNotiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.RU_BEPAID_SUCC_NOTI);
        String failNotiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.RU_PAY_FAIL_BAR);
        String bePaidSideFailNotiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.RU_BEPAID_FAIL_BAR);

        list.add(helper.getNew(SpecCodeDefine.RU_PAY_SUCC_NOTI, notiValue));
        list.add(helper.getNew(SpecCodeDefine.RU_BEPAID_SUCC_NOTI, bePaidSideNotiValue));
        list.add(helper.getNew(SpecCodeDefine.RU_PAY_FAIL_BAR, failNotiValue));
        list.add(helper.getNew(SpecCodeDefine.RU_BEPAID_FAIL_BAR, bePaidSideFailNotiValue));
        //@Date 2012-05-10 lijc3 增加处理货币单位
        // @Date 2012-08-22 tangjl5 :Story # 49447 reguid存储为计费的单位
        //@Date 2012-11-13 wangdw5 : 传入String会调用查询手机
        Integer dbMeasureId = context.getComponent(AmountComponent.class).getRatingMeasureId(info.getMeasure_id(), context.get3hTree().loadUser3hBean(CommonUtil.string2Long(objectId)).getAcctId());
        list.add(helper.getNew(SpecCodeDefine.RU_MEASURE_ID, dbMeasureId));
        // 2011-12-31 delete
        // list.add(helper.getNew(SpecCodeDefine.RU_MAX_VALUE, AmountUtil.getDbAmount(info.getMeasure_id(),
        // info.getMax_value())));// 转换
        // list.add(helper.getNew(SpecCodeDefine.RU_AMOUNT, AmountUtil.getDbAmount(info.getMeasure_id(),
        // info.getPart_value())));// 转换

        // ThresholdList thresholdList = info.getThresholdList_item();
        // if (thresholdList != null)
        // {
        // long groupIdTemp = -1;
        // for (Threshold th : thresholdList.getThresholdList_Item())
        // {
        // helper = new CharValueHelper(prodId, groupIdTemp, validDate, expireDate);
        //
        // list.add(helper.getNew(SpecCodeDefine.RU_THREHOLD, AmountUtil.getDbAmount(info.getMeasure_id(), th.getThreshold())));//
        // 转换
        // list.add(helper.getNew(SpecCodeDefine.RU_NOTIFY_TYPE, th.getNotify_type()));
        // list.add(helper.getNew(SpecCodeDefine.RU_NOTIFY_ADDR, th.getNotify_addr()));
        // list.add(helper.getNew(SpecCodeDefine.RU_NOTIFY_PHONE, th.getNotify_phone_id()));
        // groupIdTemp--;
        // }
        // }

        super.insertBatch(list);
    }
    /**
     * 
     * luojb 2012-8-10
     * @param prodId
     * @param busiServiceId
     * @param payUserId
     * @param info
     * @param timeSegment
     * @date 2012-08-10 luojb #54807 time_segment改为String
     */
    public void modifyReguide(Long prodId, Long busiServiceId,Long payUserId, SPayReguideInfo info, String timeSegment)
    {
        // modify by yangjh 2012-02-23
        Date validDate = IMSUtil.formatDate(info.getValid_date(), context.getRequestDate());
        Date expireDate = IMSUtil.formatExpireDate(info.getExpire_date());
//        Long objectId = super.getUserIdByPhoneId(info.getPay_phone_id());
        //yangjh 2012-04-11
        Long objectId = payUserId;
        Integer objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;

        CoProd coProd = new CoProd();
        coProd.setValidDate(validDate);
        coProd.setExpireDate(expireDate);
        CoProd coProdWhere = new CoProd();
        coProdWhere.setProductId(prodId);
        coProdWhere.setObjectId(objectId);
        coProdWhere.setObjectType(objectType);

        // 更新生失效时间
        updateByCondition(coProd, new DBCondition(CoProd.Field.productId, prodId));

        CharValueHelper helper = new CharValueHelper(prodId, busiServiceId, validDate, expireDate, objectId, objectType);

//        updateByCondition(helper.getUpdate(timeSegment), helper.getCond(SpecCodeDefine.RU_TIME_PERIOD));
        updateByCondition(helper.getUpdate(timeSegment), helper.getCondForDBCondition(SpecCodeDefine.RU_TIME_PERIOD));
        updateByCondition(helper.getUpdate(info.getPriority()), helper.getCondForDBCondition(SpecCodeDefine.RU_PRIORITY));
        //先删除之前的B_Number，然后在插入相应B_Number
        helper.deleteByCondition(CoProdCharValue.class, helper.getCondForDBCondition(SpecCodeDefine.RU_B_NUMBER));
        //2012-10-21 linzt 增加b_Number 判断
        List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();
        int i=info.getB_number().indexOf(",");
        if(i!=-1){
        	String [] str=info.getB_number().split(",");
        	for(String s:str){
        		list.add(helper.getNew(SpecCodeDefine.RU_B_NUMBER, s));
        	}
        }
        else{
        	list.add(helper.getNew(SpecCodeDefine.RU_B_NUMBER, info.getB_number()));
        }
        super.insertBatch(list);
//        updateByCondition(helper.getUpdate(info.getPriority()), helper.getCond(SpecCodeDefine.RU_PRIORITY));
//        updateByCondition(helper.getUpdate(info.getB_number()), helper.getCond(SpecCodeDefine.RU_B_NUMBER));

        // updateByCondition(helper.getUpdate(AmountUtil.getDbAmount(info.getMeasure_id(), info.getMax_value())),
        // helper.getCond(SpecCodeDefine.RU_MAX_VALUE));// 转换后的值
        // updateByCondition(helper.getUpdate(AmountUtil.getDbAmount(info.getMeasure_id(), info.getPart_value())),
        // helper.getCond(SpecCodeDefine.RU_AMOUNT));// 转换后的值

        // context.getComponent(BudgetComponent.class).deleteThreshold(prodId);
        //
        // ThresholdList thresholdList = info.getThresholdList_item();
        // if (thresholdList != null)
        // {
        // List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();
        // long groupIdTemp = -1;
        // for (Threshold th : thresholdList.getThresholdList_Item())
        // {
        // helper = new CharValueHelper(prodId, groupIdTemp, validDate, expireDate);
        //
        // list.add(helper.getNew(SpecCodeDefine.RU_THREHOLD, AmountUtil.getDbAmount(info.getMeasure_id(), th.getThreshold())));//
        // 转换后的值
        // list.add(helper.getNew(SpecCodeDefine.RU_NOTIFY_TYPE, th.getNotify_type()));
        // list.add(helper.getNew(SpecCodeDefine.RU_NOTIFY_ADDR, th.getNotify_addr()));
        // list.add(helper.getNew(SpecCodeDefine.RU_NOTIFY_PHONE, th.getNotify_phone_id()));
        // groupIdTemp--;
        // }
        // super.insertBatch(list);
        // }
    }
    
    public boolean isReguideExist(Long payUserId, Long userId, Long busiServiceId)
    {
        return queryReguide(payUserId, userId, busiServiceId) != null;
    }

    public Long queryReguide(Long payUserId, Long userId, Long busiServiceId)
    {
        ResultTable<CoProd> resultTable = new ResultTable<CoProd>(CoProd.Field.productId, new DBCondition(CoProd.Field.busiFlag,
                super.getBusiFlag()), new DBCondition(CoProd.Field.objectId, payUserId), new DBCondition(CoProd.Field.objectType,
                EnumCodeDefine.PROD_OBJECTTYPE_DEV));

        resultTable.addCondTable(CoProdCharValue.Field.productId, new DBCondition(CoProdCharValue.Field.specCharId,
                SpecCodeDefine.RU_USER_ID), new DBCondition(CoProdCharValue.Field.value, String.valueOf(userId)), new DBCondition(
                CoProdCharValue.Field.groupId, busiServiceId), new DBCondition(CoProdCharValue.Field.objectId, payUserId),
                new DBCondition(CoProdCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        //@Date 2012-04-03 lijc3 合并产品
        List<CoProd> resultList = context.getComponent(BaseProductComponent.class).mergeProdList(context.getComponent(ResultComponent.class).getResultList(resultTable));
        return resultList == null ? null : resultList.get(0).getProductId();
    }

    public List<CoProd> queryProdListByBepayedUserId(Long userId, Long payUserId)
    {
        List<CoProd> prodList = new ArrayList<CoProd>();
        List<CoProdCharValue> charValueList = query(
                CoProdCharValue.class, 
                new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.RU_USER_ID),
                new DBCondition(CoProdCharValue.Field.value, String.valueOf(userId)),
                // @Date 2012-3-31 tangjl5 修改用户被REGUIDE_USAGE的产品,删除多余代码
//              new DBCondition(CoProdCharValue.Field.objectId,payUserId),
                new DBCondition(CoProdCharValue.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        for(CoProdCharValue ccv : charValueList){
                    
            CoProd prod = prodCmp.queryProd(ccv.getProductId());
            if(prod == null || prod.getBusiFlag().intValue() != SpecCodeDefine.REGUIDE_USAGE ||  prod.getObjectType().intValue() != EnumCodeDefine.PROD_OBJECTTYPE_DEV){
                continue;
            }
            prodList.add(prod);             
            
        }
        
        return prodList;
    }

    public List<CoProd> queryProdListByBepayedUserId(Long userId)
    {
        List<CoProd> prodList = new ArrayList<CoProd>();
        List<CoProdCharValue> charValueList = query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.specCharId,
                SpecCodeDefine.RU_USER_ID), new DBCondition(CoProdCharValue.Field.value, String.valueOf(userId)), new DBCondition(
                CoProdCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));

        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        for (CoProdCharValue ccv : charValueList)
        {

            CoProd prod = prodCmp.queryProd(ccv.getProductId());
            if (prod == null || prod.getBusiFlag().intValue() != SpecCodeDefine.REGUIDE_USAGE || prod.getObjectType().intValue() != EnumCodeDefine.PROD_OBJECTTYPE_DEV)
            {
                continue;
            }
            prodList.add(prod);

        }

        return prodList;

    }
}
