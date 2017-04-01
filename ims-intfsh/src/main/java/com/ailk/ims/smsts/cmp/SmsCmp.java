package com.ailk.ims.smsts.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import jef.database.QueryArg.MyTableName;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.RouterComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imscnsh.entity.FreeResOut;

/**
 * @Description:短信的组件类（缓存）
 * @author wangjt
 * @Date 2012-8-27
 */
public class SmsCmp extends BaseComponent
{
    public SmsCmp(IMSContext context)
    {
        this.setContext(context);
    }

    public Long loadAcctIdByUserId(Long userId)
    {
        return context.getComponent(RouterComponent.class).getAcctIdByUserIdRout(userId);
//        return context.getComponent(AccountQuery.class).queryBelongAcctIdByUserId(userId);
    }

    public String loadPhoneIdByUserId(Long userId)
    {
        return context.getComponent(UserQuery.class).queryPhoneIdByUserId(userId);
    }

    public List<CoProd> queryProdListByUserId(Long userId, Integer busiFlag)
    {
        List<CoProd> productList = context.getComponent(ProductQuery.class).queryProdListByUserId(userId, busiFlag);

        return productList;
    }

    public List<CoProd> queryProdByUserIdAndOfferId(Long objectId, Integer objectType, Integer offerId)
    {
        return context.getComponent(ProductQuery.class).queryProdByUserIdAndOfferId(objectId, objectType, offerId);

    }

    public CoProd queryPrimaryProductByUserId(Long userId)
    {
        return context.getComponent(ProductQuery.class).queryPrimaryProductByUserId(userId);
    }
    
    public CaAccount queryAccountByUserId(Long userId)
    {   
        Long acctId = loadAcctIdByUserId(userId);
        if(CommonUtil.isValid(acctId)){
            return context.getComponent(AccountQuery.class).queryAccountById(acctId);
        }
        return null;
    }

    public CoProd queryProd(Long prodId)
    {
        return context.getComponent(ProductQuery.class).queryProd(prodId);
    }


   /** 2013-01-04 gaqc5 去掉acctId为查询条件,因为免费资源是属于用户的,与账户没关 
    * @param  freeresTableName 免费资源分表，必填
    * @param resourceId 必填
    */
    public List<CaFreeres> queryFreeResListForSms(String freeresTableName,Long resourceId)
    {

       return queryFreeResListForSms(freeresTableName,null,resourceId);
    }
    /**
     * 
     * gao 2012-11-21
     * @param  freeresTableName 免费资源分表，必填
     * @param itemCode  选填
     * @param resourceId 必填
     * @return
     */
    public List<CaFreeres> queryFreeResListForSms(String freeresTableName,String itemCode,Long resourceId ){
        long time = System.currentTimeMillis();
        imsLogger.info("查询免费资源开始!");
        List<CaFreeres> list=null;
        try
        {
            list =queryCaFreeres(freeresTableName,itemCode, resourceId);
        }
        catch (Exception e)
        {
            imsLogger.error(e.toString());
            e.printStackTrace();
        }
        if(null==list){
           list=new ArrayList<CaFreeres>();
        }
        imsLogger.info(time, "免费资源查询结束，条数:" + list.size());
        return list;
    }
    
    @SuppressWarnings("unchecked")
    private List<CaFreeres> queryCaFreeres(String freeresTableName,String itemCode, Long resourceId) throws Exception
    {
       List<DBCondition> list=new ArrayList<DBCondition>();
       if(null!=resourceId){
           list.add(new DBCondition(CaFreeres.Field.resourceId, resourceId));
       }
       if(null!=itemCode){
           list.add(new DBCondition(CaFreeres.Field.itemCode,itemCode,Operator.IN));
       }
        return (List<CaFreeres>) DBUtil.getDBClient().select(DBUtil.getQueryCondition(CaFreeres.class, list.toArray(new DBCondition[list.size()]), null), null,
                new MyTableName(freeresTableName));
    }


    public List<BillCycleComplex> queryBillCycle(Long acctId)
    {
        return context.getComponent(AccountComponent.class).queryBillCycle(acctId);
    }


    // /**@description:获取科目免费资源，并返回freeRes_flag: true
    // * @author zenglu
    // * @date 2012-10-17
    // * @param return 有免费资源 return true
    // * @deprecated Method buildLogInfo is deprecated
    // */
    // public FreeResOut getFreeResOut(List<FreeResource>freeResourceList, String[] itemArr)
    // {
    // FreeResOut out = new FreeResOut();
    // long amount = 0L;
    // long used = 0L;
    // Boolean freeResFlag = false;
    // for (FreeResource freeRes : freeResourceList)
    // {
    // if (freeRes == null)
    // continue;
    // if (freeRes.getItem_code() != null && CommonUtilSh.isIn(itemArr, freeRes.getItem_code().toString()))
    // {
    // //统一先转换成字节类型，否则，不同科目的流量单位有可能不同
    // amount +=SmsUtil.measureChangeToByte(freeRes.getMeasure_id(),(freeRes.getAmount() != null ? freeRes.getAmount() : 0));
    // used +=SmsUtil.measureChangeToByte(freeRes.getMeasure_id(),(freeRes.getUsed_value() != null ? freeRes.getUsed_value() :
    // 0));
    // freeResFlag = true;
    // }
    // }
    // out.setMeasure_id(CommonUtil.ShortToInteger(EnumCodeDefine.MEASURE_ID_BYTE));//返回的流量以字节为单位
    // out.setAmount(amount);
    // out.setUsed(used);
    // out.setFreeRes_flag(freeResFlag);
    // return out;
    // }

    public FreeResOut getFreeResOut(List<CaFreeres> freeResourceList, String[] itemArr)
    {
        FreeResOut out = new FreeResOut();
        long amount = 0L;
        long used = 0L;
        Boolean freeResFlag = false;
        for (CaFreeres freeRes : freeResourceList)
        {
            if (freeRes == null)
                continue;
            if (freeRes.getItemCode() != null && CommonUtilSh.isIn(itemArr, freeRes.getItemCode().toString()))
            {
                // 统一先转换成字节类型，否则，不同科目的流量单位有可能不同
                amount += SmsUtil
                        .measureChangeToByte(freeRes.getMeasureId(), (freeRes.getUnit() != null ? freeRes.getUnit() : 0));
                used += SmsUtil.measureChangeToByte(freeRes.getMeasureId(),
                        (freeRes.getRealUnit() != null ? freeRes.getRealUnit() : 0));
                freeResFlag = true;
            }
        }
        out.setMeasure_id(CommonUtil.ShortToInteger(EnumCodeDefine.MEASURE_ID_BYTE));// 返回的流量以字节为单位
        out.setAmount(amount);
        out.setUsed(used);
        out.setFreeRes_flag(freeResFlag);
        return out;
    }

    /**
     * 增加业务记录
     */
    public void insertCmBusi(Date date)
    {
        CmBusi cmBusi = new CmBusi();
        cmBusi.setSoDate(date);
        cmBusi.setSoNbr(BusiRecordUtil.getReceiveDoneCode(date));
        // cmBusi.setOuterSoNbr(temp.getOuterSoNbr());
        // cmBusi.setBatchFlag(temp.getBatchFlag());
        // cmBusi.setParentSoNbr(temp.getParentSoNbr());
        // cmBusi.setRecType(temp.getRecType());
        // cmBusi.setBusiSpecId(temp.getBusiSpecId());
        // cmBusi.setBusiCode(temp.getBusiCode());
        // cmBusi.setChannel(temp.getChannel());
        // cmBusi.setBusiDirect(temp.getBusiDirect());
        // cmBusi.setOpId(temp.getOpId());
        // cmBusi.setPhoneId(temp.getPhoneId());
        // cmBusi.setResourceId(temp.getResourceId());
        // cmBusi.setAcctId(temp.getAcctId());
        // cmBusi.setCustId(temp.getCustId());
        // cmBusi.setSts(temp.getSts());
        // cmBusi.setCancelDate(temp.getCancelDate());
        // cmBusi.setOrigSoNbr(temp.getOrigSoNbr());
        // cmBusi.setDoneDate(temp.getDoneDate());
        // cmBusi.setOtcFlag(temp.getOtcFlag());
        // cmBusi.setOtcSoNbr(temp.getOtcSoNbr());
        // cmBusi.setRewardFlag(temp.getRewardFlag());
        // cmBusi.setRewardSoNbr(temp.getRewardSoNbr());
        // cmBusi.setAuthFlag(temp.getAuthFlag());
        // cmBusi.setAuthSoNbr(temp.getAuthSoNbr());
        // cmBusi.setSourceSystem(temp.getSourceSystem());
        // cmBusi.setStepId(temp.getStepId());
        // cmBusi.setProvCode(temp.getProvCode());
        // cmBusi.setRegionCode(temp.getRegionCode());
        // cmBusi.setCountyCode(temp.getCountyCode());
        // cmBusi.setOrgId(temp.getOrgId());
        // cmBusi.setIsMonitor(temp.getIsMonitor());
        // cmBusi.setIsnormal(temp.getIsnormal());
        // cmBusi.setNotifyFlag(temp.getNotifyFlag());

        DBUtil.insert(cmBusi);
    }
}
