package com.ailk.imssh.fee.helper;

/**
 * @Description:I_FEE 作相关静态方法类
 * @author wangyh3
 * @Date 2012-05-15
 */
public class FeeHelper
{

    private FeeHelper()
    {
    }

    /**
     * @Description 构建一次性费用新增的请求参数
     * @author wangyh3
     * @Date 2012-5-15
     */
    /*XXX
    public static ExtDataDef buildExtDataDef(IFee iFee)
    {
        ExtDataDef extDataDef = new ExtDataDef();
        extDataDef.setAcctId(iFee.getAcctId());
        extDataDef.setResourceId(iFee.getUserId());
        extDataDef.setItemCode(iFee.getFeeItemId());
        extDataDef.setMeasureId(EnumCodeExDefine.MEASURE_ID_FEN);
        extDataDef.setPayAcctId(iFee.getAcctId());
        extDataDef.setBillFee(iFee.getAmount());
        extDataDef.setDiscountFee(0L); // 优惠费用
        //20131025 增加一次性费用需要传入CRM同步过来的生失效时间
        
        extDataDef.setBillBeginDate(iFee.getValidDate());
        extDataDef.setBillEndDate(iFee.getExpireDate());

        return extDataDef;
    }
    */

    /**
     * @Description 构建押金的请求参数
     * @author wangyh3
     * @Date 2012-5-15
     */
    /*
    public static Pocket buildPocket(IFee iFee,Long soNbr)
    {
        Pocket pocket = new Pocket();
        // pocket.setCreateDate(iFee.getCommitDate());
        // pocket.setSoDate(iFee.getCommitDate());
        pocket.setAcctId(iFee.getAcctId());
        // pocket.setAssetId(iFee.getAssetId());
        pocket.setPocketItem(iFee.getFeeItemId());
        pocket.setAmount(iFee.getAmount());
        pocket.setSts(EnumCodeExDefine.POCKET_STS_VALID); // 押金状态有效
        pocket.setBillingType(EnumCodeExDefine.POCKET_BILLING_TYPE_POSTPAY); // 押金后付费
        pocket.setValidDate(iFee.getValidDate());
        pocket.setExpireDate(iFee.getExpireDate());
        pocket.setSoNbr(soNbr);
        pocket.setMeasureId(EnumCodeExDefine.MEASURE_ID_FEN);
        // pocket.setResourceId(iFee.getUserId());

        return pocket;

    }
    */

    /**
     * @Description 构建一次性免费资源的请求参数
     * @author wangyh3
     * @Date 2012-5-15
     */
    /*
    public static FreeResource buildFreeResource(IFee iFee)
    {
        FreeResource freeResource = new FreeResource();
        freeResource.setAcctId(iFee.getAcctId());
        freeResource.setResourceId(iFee.getUserId());
        freeResource.setFreeResItem(iFee.getFeeItemId());
        freeResource.setFreeResValue(iFee.getAmount());
        // freeResource.setProductId(iFee.getProductId());
        freeResource.setValidDate(iFee.getValidDate());
        freeResource.setExpireDate(iFee.getExpireDate());
        // freeResource.setCardBatchNo(iFee.getCardBatchNo());
        // freeResource.setCardSerialNo(iFee.getCardSerialNo());
        // freeResource.setCardType(iFee.getCardType());

        return freeResource;
    }
    */

    /**
     * @Description 创建公共请求参数
     * @author wangyh3
     * @Date 2012-5-15
     */
    /*
    public static CommonParam buildCommonParam(Date date,Long soNbr)
    {
        CommonParam commonParam = new CommonParam();
        commonParam.setChannelId(EnumCodeExDefine.CHANNEL_ID_DEFAULT);
        commonParam.setOpId(EnumCodeExDefine.OP_ID_DEFUALT);
        commonParam.setRegionCode(EnumCodeExDefine.REGION_CODE_DEFAULT);
        commonParam.setProvCode(EnumCodeExDefine.PROV_CODE_DEFAULT);
        commonParam.setCountyCode(EnumCodeExDefine.COUNTRY_CODE_DEFAULT);
        commonParam.setOrgId(EnumCodeExDefine.ORG_ID_DEFAULT);
        commonParam.setNotifyFlag(EnumCodeExDefine.COMMON_NO_NOTIFY);
        commonParam.setOutSoNbr(CommonUtil.long2String(soNbr));
        commonParam.setOutSoDate(date);
        // commonParam.setRemark(iFee.getRemark());
        // commonParam.setSpecialBusiFlag(iFee.getSpecialBusiFlag());
        // commonParam.setRelSoNbr(iFee.getRelSoNbr());
        // commonParam.setRelSoDate(iFee.getRelSoDate());
        // commonParam.setSourceType(iFee.getSourceType());
        commonParam.setSourceType(EnumCodeDefine.OUTER_SOURCETYPE);
        return commonParam;
    }
    */
    
}
