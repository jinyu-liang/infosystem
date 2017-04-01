package com.ailk.imssh.acct.cmp;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.acct.entity.CaBankFund;
import com.ailk.openbilling.persistence.acct.entity.CaPayChannel;
import com.ailk.openbilling.persistence.acct.entity.CaPaymentPlan;
import com.ailk.openbilling.persistence.itable.entity.IAcctPayChannel;

/**
 * @Description:账户支付渠道组件
 * @author caohm5
 * @Date 2012-05-12
 * @Date 2012-06-05支付方式的代码修改
 * @Date 2012-08-10 wukl pdm中删除了CAAsset表，所以这里不去实例化，只取序列号
 * @Date 2012-10-29 wukl 设置代扣号码信息
 */
public class AcctPayChannelCmp extends BaseCmp
{
    /**
     * @Description:账户支付方式接口表数据通过逻辑转换，保存到数据库对应表中
     * @author caohm5
     * @Date 2012-05-12
     */
    public void createAcctPayChannel(IAcctPayChannel iAcctPayChannel)
    {

        Integer paymentType = iAcctPayChannel.getPaymentType();
        if (paymentType == EnumCodeExDefine.ACCOUNT_PAYTYPE_CASH)
        {// 接口表中传现金支付，不用去实例化数据，但是该账户的其他支付方式需要删除
            List<CaPayChannel> result = deleteByCondition(CaPayChannel.class, new DBCondition(CaPayChannel.Field.acctId,
                    iAcctPayChannel.getAcctId()));
            if (result != null && result.size() > 0)
            {
                deleteByCondition(CaBankFund.class, new DBCondition(CaBankFund.Field.acctId, iAcctPayChannel.getAcctId()));
            }
        }
        else
        {
            long assetId = DBUtil.getSequence(CaBankFund.class);
            insertCaBankFund(iAcctPayChannel, assetId, false);
            insertCaPayChannel(iAcctPayChannel, assetId, false);
        }

    }

    /**
     * @Description:银行资产信息资料
     * @param isUpdate 是否修改时调用;(现金 转非现金  支付，其生失效时间需要特殊处理，不能拿CRM传入的；因为CRM传入的生效时间一直都是不变的)
     * @author caohm5
     * @Date 2012-05-12
     */
    private void insertCaBankFund(IAcctPayChannel iAcctPayChannel, long assetId, boolean isUpdate)
    {
        CaBankFund caBankFund = new CaBankFund();

        caBankFund.setAssetId(assetId);
        caBankFund.setFundType((int) EnumCodeExDefine.CARD_TYPE_CREDIT);
        if (iAcctPayChannel.getPaymentType() == EnumCodeExDefine.ACCOUNT_PAYTYPE_PHONE_AUTO)
        {
            caBankFund.setBankId(9999);// 手机支付，账管要求将该值置成9999
        }
        else
        {
            caBankFund.setBankId(CommonUtil.string2Integer(iAcctPayChannel.getBankId()));
        }
        caBankFund.setBankAcctNo(iAcctPayChannel.getBankAcctNbr());
        caBankFund.setFundItem(0);
        caBankFund.setSts(EnumCodeExDefine.CA_BANK_FUN_STS_ACTIVE);
        caBankFund.setAcctId(iAcctPayChannel.getAcctId());
        // caBankFund.setBankAcctName(iAcctPayChannel.getBankAcctName());
        caBankFund.setBankAcctLimit((long) 0);
        caBankFund.setMeasureId(10403);

        caBankFund.setExpireDate(iAcctPayChannel.getExpireDate());
        caBankFund.setSoDate(context.getCommitDate());
        caBankFund.setSoNbr(context.getSoNbr());
        
        if (isUpdate)
        {//由现金 修改为 非现金，由于现金是没记录的，所以也会调用新增的方法；此时生效时间、创建时间应该设置成提交时间
            caBankFund.setCreateDate(context.getCommitDate());
            caBankFund.setValidDate(context.getCommitDate());
        }
        else
        {//新增
            caBankFund.setCreateDate(iAcctPayChannel.getValidDate());
            caBankFund.setValidDate(iAcctPayChannel.getValidDate());
        }

        super.insert(caBankFund);
    }

    /**
     * @Description:支付渠道信息资料
     * @param isUpdate 是否修改时调用;(现金 转非现金  支付，其生失效时间需要特殊处理，不能拿CRM传入的；因为CRM传入的生效时间一直都是不变的)
     * @author caohm5
     * @Date 2012-05-12
     */
    private void insertCaPayChannel(IAcctPayChannel iAcctPayChannel, long assetId,  boolean isUpdate)
    {
        CaPayChannel caPayChannel = new CaPayChannel();

        caPayChannel.setPaymentPlanId(DBUtil.getSequence(CaPaymentPlan.class));
        caPayChannel.setAcctId(iAcctPayChannel.getAcctId());
        caPayChannel.setAmount(0L);
        caPayChannel.setAssetId(assetId);
        caPayChannel.setPaymentMethod(iAcctPayChannel.getPaymentType());
        caPayChannel.setBindPhoneId(iAcctPayChannel.getPhoneId());
        caPayChannel.setBindType(iAcctPayChannel.getBindType());
        caPayChannel.setDayFixMoney(iAcctPayChannel.getDayFixMoney());
        caPayChannel.setTimeFixMoney(iAcctPayChannel.getTimeFixMoney());
        caPayChannel.setPlatForm(iAcctPayChannel.getPlatForm());
        caPayChannel.setPriority(0);
        caPayChannel.setResourceId(iAcctPayChannel.getUserId());
        // caPayChannel.setAuthId(obj);
        // caPayChannel.setExt1(obj);
        // caPayChannel.setExt2(obj);
        // caPayChannel.setOffsetDay(obj);
        // caPayChannel.setPeriodType(obj);
        // caPayChannel.setPeriodUnit(obj);
        // caPayChannel.setResourceId(iAcctPayChannel.get);

        caPayChannel.setExpireDate(iAcctPayChannel.getExpireDate());
        caPayChannel.setSoDate(context.getCommitDate());
        caPayChannel.setSoNbr(context.getSoNbr());
        
        if (isUpdate)
        {//由现金 修改为 非现金，由于现金是没记录的，所以也会调用新增的方法；此时生效时间、创建时间应该设置成提交时间
            caPayChannel.setCreateDate(context.getCommitDate());
            caPayChannel.setValidDate(context.getCommitDate());
        }
        else
        {//新增
            caPayChannel.setCreateDate(iAcctPayChannel.getValidDate());
            caPayChannel.setValidDate(iAcctPayChannel.getValidDate());
        }

        super.insert(caPayChannel);
    }

    /**
     * @Description:修改支付渠道信息资料
     * @author caohm5
     * @Date 2012-05-12
     */
    public void updatePayChannel(IAcctPayChannel iAcctPayChannel)
    {
        Integer paymentType = iAcctPayChannel.getPaymentType();
        if (paymentType == EnumCodeExDefine.ACCOUNT_PAYTYPE_CASH)
        {// 接口表中传现金支付，不用去实例化数据，但是该账户的其他支付方式需要删除
            List<CaPayChannel> result = deleteByCondition(CaPayChannel.class, new DBCondition(CaPayChannel.Field.acctId,
                    iAcctPayChannel.getAcctId()));
            if (result != null && result.size() > 0)
            {
                deleteByCondition(CaBankFund.class, new DBCondition(CaBankFund.Field.acctId, iAcctPayChannel.getAcctId()));
            }
        }
        else
        {
            CaPayChannel payChannel = queryPayChannelByAcctId(iAcctPayChannel.getAcctId());
            if (payChannel != null)
            {// 表中已经存在支付方式，则修改
                Long assetId = payChannel.getAssetId();
                updateCaPayChannel(iAcctPayChannel);
                updateCaBankFund(iAcctPayChannel, assetId);
            }
            else
            {// 表中不存在，则新增
                long assetId = DBUtil.getSequence(CaBankFund.class);
                insertCaBankFund(iAcctPayChannel, assetId, true);
                insertCaPayChannel(iAcctPayChannel, assetId, true);
            }
        }

    }

    private void updateCaBankFund(IAcctPayChannel iAcctPayChannel, Long assetId)
    {
        CaBankFund caBankFund = new CaBankFund();

        // caBankFund.setAssetId(assetId);
        // FundType 字段上海未使用，此时不做区分
        // caBankFund.setFundType((int) EnumCodeExDefine.CARD_TYPE_CREDIT);
        caBankFund.setFundType((int) EnumCodeExDefine.CARD_TYPE_CREDIT);
        if (iAcctPayChannel.getPaymentType() == EnumCodeExDefine.ACCOUNT_PAYTYPE_PHONE_AUTO)
        {
            caBankFund.setBankId(9999);// 手机支付，账管要求将该值置成9999
        }
        else
        {
            caBankFund.setBankId(CommonUtil.string2Integer(iAcctPayChannel.getBankId()));
        }
        caBankFund.setBankAcctNo(iAcctPayChannel.getBankAcctNbr());
        // caBankFund.setFundItem(0);
        // caBankFund.setSts(EnumCodeExDefine.CA_BANK_FUN_STS_ACTIVE);
        // caBankFund.setAcctId(iAcctPayChannel.getAcctId());
        // caBankFund.setBankAcctName(iAcctPayChannel.getBankAcctName());
        // caBankFund.setBankAcctLimit((long) 0);
        // caBankFund.setMeasureId(iAcctPayChannel.getMeasureId());

        // caBankFund.setCreateDate(context.getCommitDate());
        // caBankFund.setValidDate(iAcctPayChannel.getValidDate());
        caBankFund.setExpireDate(iAcctPayChannel.getExpireDate());
        caBankFund.setSoDate(context.getCommitDate());
        caBankFund.setSoNbr(context.getSoNbr());

        this.updateByCondition(caBankFund, new DBCondition(CaBankFund.Field.assetId, assetId));

    }

    private void updateCaPayChannel(IAcctPayChannel iAcctPayChannel)
    {
        CaPayChannel caPayChannel = new CaPayChannel();

        // caPayChannel.setPaymentPlanId(DBUtil.getSequence(CaPaymentPlan.class));
        // caPayChannel.setAcctId(iAcctPayChannel.getAcctId());
        // caPayChannel.setAssetId(assetId);
        caPayChannel.setPaymentMethod(iAcctPayChannel.getPaymentType());

        caPayChannel.setResourceId(iAcctPayChannel.getUserId());
        caPayChannel.setBindPhoneId(iAcctPayChannel.getPhoneId());
        caPayChannel.setBindType(iAcctPayChannel.getBindType());
        caPayChannel.setDayFixMoney(iAcctPayChannel.getDayFixMoney());
        caPayChannel.setTimeFixMoney(iAcctPayChannel.getTimeFixMoney());
        caPayChannel.setPlatForm(iAcctPayChannel.getPlatForm());
        caPayChannel.setExpireDate(iAcctPayChannel.getExpireDate());
        caPayChannel.setSoDate(context.getCommitDate());
        caPayChannel.setSoNbr(context.getSoNbr());

        this.updateByCondition(caPayChannel, new DBCondition(CaPayChannel.Field.acctId, iAcctPayChannel.getAcctId()));
    }

    /**
     * @Description:查询账户的支付渠道信息
     * @author caohm5
     * @Date 2012-05-12
     */
    public CaPayChannel queryPayChannelByAcctId(Long acctId)
    {
        return querySingle(CaPayChannel.class, new DBCondition(CaPayChannel.Field.acctId, acctId));
    }
  
}
