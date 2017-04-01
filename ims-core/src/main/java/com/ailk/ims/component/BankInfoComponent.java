package com.ailk.ims.component;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BankInfo;
import com.ailk.openbilling.persistence.sys.entity.SysBankInfo;
import com.ailk.openbilling.persistence.sys.entity.SysBankInfoHis;

/**
 * @Description: SysBankInfo相关操作类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wangjt
 * @Date 2012-4-18
 */
public class BankInfoComponent extends BaseComponent
{
    public BankInfoComponent()
    {
    }

    /**
     * @Description 修改银行信息
     * @author fangyw
     * @Date 2011-10-10
     */
    public void modifyCaBankInfo(BankInfo bankInfo, SysBankInfo sysBankInfoWhere)
    {
        SysBankInfo sysBankInfo = new SysBankInfo();
        if (null != bankInfo.getBank_code())
        {
            sysBankInfo.setBankNo(String.valueOf(bankInfo.getBank_code()));
        }
        if (null != bankInfo.getBank_abbreviate())
        {
            sysBankInfo.setBankName(bankInfo.getBank_abbreviate());
        }
        if (null != bankInfo.getBank_desc_thai())
        {
            sysBankInfo.setBankDescExt(bankInfo.getBank_desc_thai());
        }
        if (null != bankInfo.getBank_desc_eng())
        {
            sysBankInfo.setBankDesc(bankInfo.getBank_desc_eng());
        }

        DBUtil.updateByCondition(sysBankInfo, new DBCondition(SysBankInfo.Field.bankId, sysBankInfoWhere.getBankId()));
    }

    /**
     * @Description 在数据库中查找银行信息
     * @author fangyw
     * @Date 2011-10-10
     */
    public SysBankInfo queryCaBankInfo(Integer bankCode)
    {
        return super.querySingle(SysBankInfo.class, new DBCondition(SysBankInfo.Field.bankNo, bankCode));
    }

    /**
     * @Description 将银行信息存入数据库中
     * @author fangyw
     * @Date 2011-10-10
     */
    public void createCaBankInfoHis(SysBankInfoHis sysBankInfoHis)
    {
        super.insert(sysBankInfoHis);
    }
}
