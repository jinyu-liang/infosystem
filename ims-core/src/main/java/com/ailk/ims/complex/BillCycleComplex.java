package com.ailk.ims.complex;

import java.util.Date;

/**
 * @Description 账期处理参数
 * @author ljc
 * @Date 2011-9-27
 */
public class BillCycleComplex extends BaseComplex
{
    private Integer cycleUnit;
    private Integer cycleType;
    /**
     * 出账日 的日子的值如2011-10-11 这个字段是11
     */
    private Integer firstBillDate;
    private Date validDate;
    private Date expireDate;

    public Integer getCycleUnit()
    {
        return cycleUnit;
    }

    public void setCycleUnit(Integer cycleUnit)
    {
        this.cycleUnit = cycleUnit;
    }

    public Integer getCycleType()
    {
        return cycleType;
    }

    public void setCycleType(Integer cycleType)
    {
        this.cycleType = cycleType;
    }

    public Integer getFirstBillDate()
    {
        return firstBillDate;
    }

    public void setFirstBillDate(Integer firstBillDate)
    {
        this.firstBillDate = firstBillDate;
    }

    public Date getValidDate()
    {
        return validDate;
    }

    public void setValidDate(Date validDate)
    {
        this.validDate = validDate;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof BillCycleComplex))
            return false;

        BillCycleComplex bill = (BillCycleComplex) obj;

        return bill.getCycleType().intValue() == cycleType.intValue() && bill.getCycleUnit().intValue() == cycleUnit.intValue()
                && bill.getFirstBillDate().intValue() == firstBillDate.intValue() && bill.getExpireDate().equals(expireDate)
                && bill.getValidDate().equals(validDate);
    }
}
