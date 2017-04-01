package com.ailk.ims.innerclass;

import java.util.List;

public  class SImsOrderInfo
{
    private SImsCoProd prod;
    private List<SImsProdBillCycle> list_bill_cycle;
    private List<SImsProdCharValue> list_char_value;
    //SProdPriceList list_prod_price;
    private List<SImsProdPriceParam> list_price_param;
//    SImUserServiceStatusList list_service_status;
//    SImUserHomeList list_user_home;
    //SProdValidInfo prod_valid_info;
    public SImsCoProd getProd()
    {
        return prod;
    }
    public void setProd(SImsCoProd prod)
    {
        this.prod = prod;
    }
    public List<SImsProdBillCycle> getList_bill_cycle()
    {
        return list_bill_cycle;
    }
    public void setList_bill_cycle(List<SImsProdBillCycle> list_bill_cycle)
    {
        this.list_bill_cycle = list_bill_cycle;
    }
    public List<SImsProdCharValue> getList_char_value()
    {
        return list_char_value;
    }
    public void setList_char_value(List<SImsProdCharValue> list_char_value)
    {
        this.list_char_value = list_char_value;
    }
    public List<SImsProdPriceParam> getList_price_param()
    {
        return list_price_param;
    }
    public void setList_price_param(List<SImsProdPriceParam> list_price_param)
    {
        this.list_price_param = list_price_param;
    }
}