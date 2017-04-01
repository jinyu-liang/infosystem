package com.ailk.ims.innerclass;

 public class SImsCoProd        
    {
        private Long product_id    ;
        private short prod_type_id  ;   //产品种类promClass
        //private int service_id  ;
        private short lifecycle_status_id;
        private int product_offering_id;
        private Long price_plan_id;    //销售品定价计划, 20120704 add
        private short busi_flag          ;  //promType  
        private int is_main;          //是否是主产品 0:普通产品 1:主产品
        private Long object_id          ;
        private short object_type        ;  //0:Dev 1:Acct 2:GCA
        private int bill_mode;        //预后付属性(账本)
        private int prom_cycle;       //产品状态
        private int expire_date  ;
        private int valid_date ;
        private int prod_expire_date  ;
        private int prod_valid_date   ;
        private int sync_flag;        //0全量 , 1增量 
        public Long getProduct_id()
        {
            return product_id;
        }
        public void setProduct_id(Long product_id)
        {
            this.product_id = product_id;
        }
        public short getProd_type_id()
        {
            return prod_type_id;
        }
        public void setProd_type_id(short prod_type_id)
        {
            this.prod_type_id = prod_type_id;
        }
        public short getLifecycle_status_id()
        {
            return lifecycle_status_id;
        }
        public void setLifecycle_status_id(short lifecycle_status_id)
        {
            this.lifecycle_status_id = lifecycle_status_id;
        }
        public int getProduct_offering_id()
        {
            return product_offering_id;
        }
        public void setProduct_offering_id(int product_offering_id)
        {
            this.product_offering_id = product_offering_id;
        }
        public Long getPrice_plan_id()
        {
            return price_plan_id;
        }
        public void setPrice_plan_id(Long price_plan_id)
        {
            this.price_plan_id = price_plan_id;
        }
        public short getBusi_flag()
        {
            return busi_flag;
        }
        public void setBusi_flag(short busi_flag)
        {
            this.busi_flag = busi_flag;
        }
        public int getIs_main()
        {
            return is_main;
        }
        public void setIs_main(int is_main)
        {
            this.is_main = is_main;
        }
        public Long getObject_id()
        {
            return object_id;
        }
        public void setObject_id(Long object_id)
        {
            this.object_id = object_id;
        }
        public short getObject_type()
        {
            return object_type;
        }
        public void setObject_type(short object_type)
        {
            this.object_type = object_type;
        }
        public int getBill_mode()
        {
            return bill_mode;
        }
        public void setBill_mode(int bill_mode)
        {
            this.bill_mode = bill_mode;
        }
        public int getProm_cycle()
        {
            return prom_cycle;
        }
        public void setProm_cycle(int prom_cycle)
        {
            this.prom_cycle = prom_cycle;
        }
        public int getExpire_date()
        {
            return expire_date;
        }
        public void setExpire_date(int expire_date)
        {
            this.expire_date = expire_date;
        }
        public int getValid_date()
        {
            return valid_date;
        }
        public void setValid_date(int valid_date)
        {
            this.valid_date = valid_date;
        }
        public int getProd_expire_date()
        {
            return prod_expire_date;
        }
        public void setProd_expire_date(int prod_expire_date)
        {
            this.prod_expire_date = prod_expire_date;
        }
        public int getProd_valid_date()
        {
            return prod_valid_date;
        }
        public void setProd_valid_date(int prod_valid_date)
        {
            this.prod_valid_date = prod_valid_date;
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
