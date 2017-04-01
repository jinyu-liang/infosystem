package com.ailk.ims.innerclass;

import java.util.List;

public  class SImsProductOrder
{
    private int order_flag;   //特定产品规格枚举
    private List<SImsOrderInfo> list_order_info;
    public int getOrder_flag()
    {
        return order_flag;
    }
    public void setOrder_flag(int order_flag)
    {
        this.order_flag = order_flag;
    }
    public List<SImsOrderInfo> getList_order_info()
    {
        return list_order_info;
    }
    public void setList_order_info(List<SImsOrderInfo> list_order_info)
    {
        this.list_order_info = list_order_info;
    }
}