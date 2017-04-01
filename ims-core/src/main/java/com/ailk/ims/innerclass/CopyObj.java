package com.ailk.ims.innerclass;

public class CopyObj{
    private CopyObj(){
        
    }
    public static SImsCoProd copyProd(SImsCoProd prod){
        if(prod == null)
            return null;
        SImsCoProd newProd = new SImsCoProd();
        newProd.setProduct_id(prod.getProduct_id());
        newProd.setProd_type_id(prod.getProd_type_id());
        newProd.setLifecycle_status_id(prod.getLifecycle_status_id());
        newProd.setProduct_offering_id(prod.getProduct_offering_id());
        newProd.setPrice_plan_id(prod.getPrice_plan_id());
        newProd.setBusi_flag(prod.getBusi_flag());
        newProd.setIs_main(prod.getIs_main());
        newProd.setObject_id(prod.getObject_id());
        newProd.setObject_type(prod.getObject_type());
        newProd.setBill_mode(prod.getBill_mode());
        newProd.setProm_cycle(prod.getProm_cycle());
        newProd.setExpire_date(prod.getExpire_date());
        newProd.setValid_date(prod.getValid_date());
        newProd.setProd_expire_date(prod.getProd_expire_date());
        newProd.setProd_valid_date(prod.getProd_valid_date());
        newProd.setSync_flag(prod.getSync_flag());
        return newProd;
    }
    
    public static SImsProdCharValue copyCharValue(SImsProdCharValue charvalue){
        if(charvalue == null)
            return null;
        SImsProdCharValue newchar = new SImsProdCharValue();
        newchar.setProduct_id(charvalue.getProduct_id());
        newchar.setObject_id(charvalue.getObject_id());
        newchar.setObject_type(charvalue.getObject_type());
        newchar.setGroup_id(charvalue.getGroup_id());
        newchar.setSpec_char_id(charvalue.getSpec_char_id());
        newchar.setValue(charvalue.getValue());
        newchar.setValid_date(charvalue.getValid_date());
        newchar.setExpire_date(charvalue.getExpire_date());
        newchar.setSync_flag(charvalue.getSync_flag());
        return newchar;
    }
    
    public static SImsProdBillCycle copyBillCycle(SImsProdBillCycle cycle){
        if(cycle ==  null)
            return null;
        SImsProdBillCycle newcycle = new SImsProdBillCycle();
        newcycle.setProduct_id(cycle.getProduct_id());
        newcycle.setObject_id(cycle.getObject_id());
        newcycle.setObject_type(cycle.getObject_type());
        newcycle.setCycle_type(cycle.getCycle_type());
        newcycle.setCycle_unit(cycle.getCycle_unit());
        newcycle.setCycle_flag(cycle.getCycle_flag());
        newcycle.setFirst_bill_date(cycle.getFirst_bill_date());
        newcycle.setValid_date(cycle.getValid_date());
        newcycle.setExpire_date(cycle.getExpire_date());
        newcycle.setSync_flag(cycle.getSync_flag());
        return newcycle;
    }
    
    
    public static SImsProductOrder copyOrder(SImsProductOrder order){
        if(order == null)
            return null;
        SImsProductOrder newOrder = new SImsProductOrder();
        newOrder.setOrder_flag(order.getOrder_flag());
        newOrder.setList_order_info(order.getList_order_info());
        return  newOrder;
    }
    
    public static SImsProdPriceParam copyPrice(SImsProdPriceParam price){
        if(price == null)
            return null;
        SImsProdPriceParam newPrice = new SImsProdPriceParam();
        newPrice.setExpire_date(price.getExpire_date());
        newPrice.setParam_id(price.getParam_id());
        newPrice.setParam_value(price.getParam_value());
        newPrice.setProduct_id(price.getProduct_id());
        newPrice.setSync_flag(price.getSync_flag());
        newPrice.setExpire_date(price.getValid_date());
        
        return newPrice;
    }
    
    
    public static SImsUserCycle copyUserCycle(SImsUserCycle cycle){
        if(cycle == null)
            return null;
        SImsUserCycle newCycle = new SImsUserCycle();
        newCycle.setServ_id(cycle.getServ_id());
        newCycle.setUser_cycle(cycle.getUser_cycle());
        newCycle.setNext_cycle(cycle.getNext_cycle());
        newCycle.setSts_dtl(cycle.getSts_dtl());
        newCycle.setValid_date(cycle.getValid_date());
        newCycle.setExpire_date(cycle.getExpire_date());
        newCycle.setNext_expire_date(cycle.getNext_expire_date());
        newCycle.setSync_flag(cycle.getSync_flag());
        return newCycle;
        
    }
    
    
    public static SImsUser copyUser(SImsUser user){
        if(user == null)
            return null;
        SImsUser newUser = new SImsUser();
        newUser.setServ_id(user.getServ_id());
        newUser.setBrand(user.getBrand());
        newUser.setRegion_code(user.getRegion_code());
        newUser.setCounty_code(user.getCounty_code());
        newUser.setLang_reading(user.getLang_reading());
        newUser.setLang_listening(user.getLang_listening());
        newUser.setLang_writing(user.getLang_writing());
        newUser.setBill_type(user.getBill_type());
        newUser.setCreate_date(user.getCreate_date());
        newUser.setFirst_use_time(user.getFirst_use_time());
        newUser.setUser_segment(user.getUser_segment());
        newUser.setFlh_flag(user.getFlh_flag());
        newUser.setContinue_flag(user.getContinue_flag());
        newUser.setValid_date(user.getValid_date());
        newUser.setExpire_date(user.getExpire_date());
        newUser.setSync_flag(user.getSync_flag());
        return newUser;
    }
}