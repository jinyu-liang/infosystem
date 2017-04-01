package com.ailk.ims.innerclass;

import java.util.Date;

/**
 * @Description
 * @author wangdw5
 * @Date 2013-2-25
 */
public class PhoneHearCacher
{
    private String numberHeader;
    private Date validDate;
    private Date expireDate;
    private Integer length;
    /**
     * @return the length
     */
    public Integer getLength()
    {
        return length;
    }
    /**
     * @param length the length to set
     */
    public void setLength(Integer length)
    {
        this.length = length;
    }
    /**
     * @return the numberHeader
     */
    public String getNumberHeader()
    {
        return numberHeader;
    }
    /**
     * @param numberHeader the numberHeader to set
     */
    public void setNumberHeader(String numberHeader)
    {
        this.numberHeader = numberHeader;
        this.length = numberHeader.length();
    }
    /**
     * @return the validDate
     */
    public Date getValidDate()
    {
        return validDate;
    }
    /**
     * @param validDate the validDate to set
     */
    public void setValidDate(Date validDate)
    {
        this.validDate = validDate;
    }
    /**
     * @return the expireDate
     */
    public Date getExpireDate()
    {
        return expireDate;
    }
    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }
}
