package com.ailk.ims.smsts.bean;

import java.util.Date;

public class FreeresBaseInfo
{
    private Long resourceId;
    private Long unit;
    private Long realUnit;
    private Long acctId;
    private Date beginDate;
    private Date endDate;
    private int billMonth;
    private String itemName;
    private Long productId;
    private String productName;
    private Integer measureId;
    private Date vaildDate;
    private Date expireDate;
    private String remarks;

    public Long getAcctId()
    {
        return acctId;
    }

    public void setAcctId(Long acctId)
    {
        this.acctId = acctId;
    }

    public Date getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public int getBillMonth()
    {
        return billMonth;
    }

    public void setBillMonth(int billMonth)
    {
        this.billMonth = billMonth;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public Integer getMeasureId()
    {
        return measureId;
    }

    public void setMeasureId(Integer measureId)
    {
        this.measureId = measureId;
    }

    public Date getVaildDate()
    {
        return vaildDate;
    }

    public void setVaildDate(Date vaildDate)
    {
        this.vaildDate = vaildDate;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public Long getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(Long resourceId)
    {
        this.resourceId = resourceId;
    }

    public Long getUnit()
    {
        return unit;
    }

    public void setUnit(Long unit)
    {
        this.unit = unit;
    }

    public Long getRealUnit()
    {
        return realUnit;
    }

    public void setRealUnit(Long realUnit)
    {
        this.realUnit = realUnit;
    }

    public Long getItemCode()
    {
        return itemCode;
    }

    public void setItemCode(Long itemCode)
    {
        this.itemCode = itemCode;
    }
    private Long itemCode;

}
