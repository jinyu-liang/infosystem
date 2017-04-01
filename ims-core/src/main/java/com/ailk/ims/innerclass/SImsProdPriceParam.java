package com.ailk.ims.innerclass;

public  class SImsProdPriceParam  
    {
        private Long product_id    ;
        //int64 price_plan_id   ;   //销售品定价计划
        private int param_id  ;   //价格参数键值编号
        private String param_value  ;   
        private int valid_date    ;
        private int expire_date   ;
        private int sync_flag; //0全量 , 1增量
        public Long getProduct_id()
        {
            return product_id;
        }
        public void setProduct_id(Long product_id)
        {
            this.product_id = product_id;
        }
        public int getParam_id()
        {
            return param_id;
        }
        public void setParam_id(int param_id)
        {
            this.param_id = param_id;
        }
        public String getParam_value()
        {
            return param_value;
        }
        public void setParam_value(String param_value)
        {
            this.param_value = param_value;
        }
        public int getValid_date()
        {
            return valid_date;
        }
        public void setValid_date(int valid_date)
        {
            this.valid_date = valid_date;
        }
        public int getExpire_date()
        {
            return expire_date;
        }
        public void setExpire_date(int expire_date)
        {
            this.expire_date = expire_date;
        }
        public int getSync_flag()
        {
            return sync_flag;
        }
        public void setSync_flag(int sync_flag)
        {
            this.sync_flag = sync_flag;
        }
    }