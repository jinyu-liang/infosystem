package com.ailk.ims.component;

import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;

/**
 * @Description: mdb上发组件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-9-27
 * @Date 2012-3-29 wangjt: 修改isEmpty方法用来判断对象的问题
 * @Date 2012-03-30 去掉soNbr作为查询条件
 * @Date 2012-4-2 tangjl5 Bug #40000 将数据库中nextSts的存储形式修改为同mdb中的格式一致
 * @Date 2012-04-09 lijc3 调用方法设置异同步标志
 * @Date 2012-04-13 zengqm CmUserOrderConfirm转成同步mdb的对象类的方法
 * @Date 2012-04-16 lijc3 增加账户上的信控标志
 * @Date 2012-4-20 tangjl5 主干代码与tag代码同步，cm_res_lifecycle中的next_sts先回退为4位
 * @Date 2012-4-27 tangjl5 Task #44713 从扩张字段中获取税率标志存入CaAccount
 * @Date 2012-5-2 tangjl5 删除CoProdInvCa表相关代码，该表已删除
 * @Date 2012-05-09 wangdw5
 *       删除:SImAccount.set_acctSegment,SCoProd.set_serviceId,SCoProd.set_payAcctId,SImUserGroup.set_payAcctId,SImUserGroup
 *       .set_groupType
 * @Date 2012-05-10 wangdw5 新增设置CGroup的groupType和payAcctID
 * @Date 2012-5-14 zhangzj3 PromBillCycle.m_nDeductMode 字段不再实例化
 * @Date 2012-5-18 yangjh buildUser add set_flhFlag and set_continueFlag
 * @Date 2012-5-19 增加产品有效期上发
 * @Date 2012-5-22 14:31:54 wangjt 删除buildUser方法中无用的参数
 * @Date 2012-5-22 15:18:53 wangjt 增加buildImAcctContactList方法
 * @Date 2012-5-31 tangjl5 Bug #47201 解决空指针问题
 * @Date 2012-06-05 yangjh 账期增加firstBillDate上发
 * @Date 2012-6-7 tangjl Bug #47944 groupType,accountType未上发
 * @Date 2012-06-08 yangjh 删除SIMCUSTOMER email的获取
 * @Date 2012-06-09 yangjh story：46330 增加账户用户EMail的上发
 * @Date 2012-06-14 wukl 增加生命周期状态停机位的上发
 * @Date 2012-06-19 yangjh buildImAcctBillCycleList,buildBillCycle账期上发IMS自己处理
 * @date 2012-07-10 luojb #50229 删除co_prod_valid, co_prod 增加prod_valid_date，prod_expire_date
 */
public class MdbComponent extends BaseComponent
{
    /**
     * 构建客户
     */
   

    /**
     * @param coSplit
     * @return
     */
    public CoProdCharValue coSplitCharToCoProdChar(CoSplitCharValue coSplit)
    {
        if (coSplit == null)
        {
            return null;
        }
        CoProdCharValue coProdChar = new CoProdCharValue();
        if (coSplit.getCreateDate() != null)
            coProdChar.setCreateDate(coSplit.getCreateDate());
        if (coSplit.getExpireDate() != null)
            coProdChar.setExpireDate(coSplit.getExpireDate());
        if (coSplit.getGroupId() != null)
            coProdChar.setGroupId(coSplit.getGroupId());
        if (coSplit.getObjectId() != null)
            coProdChar.setObjectId(coSplit.getObjectId());
        if (coSplit.getObjectType() != null)
            coProdChar.setObjectType(coSplit.getObjectType());
        if (coSplit.getProductId() != null)
            coProdChar.setProductId(coSplit.getProductId());
        if (coSplit.getSoDate() != null)
            coProdChar.setSoDate(coSplit.getSoDate());
        if (coSplit.getSoNbr() != null)
            coProdChar.setSoNbr(coSplit.getSoNbr());
        if (coSplit.getSpecCharId() != null)
            coProdChar.setSpecCharId(coSplit.getSpecCharId());
        if (coSplit.getValidDate() != null)
            coProdChar.setValidDate(coSplit.getValidDate());
        if (coSplit.getValue() != null)
            coProdChar.setValue(coSplit.getValue());
        return coProdChar;
    }

    
}
