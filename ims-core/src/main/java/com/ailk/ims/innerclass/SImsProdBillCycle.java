package com.ailk.ims.innerclass;

public  class SImsProdBillCycle   
    {
        private Long product_id    ;
        private Long object_id;
        private short object_type        ;//0:Dev 1:Account 2:GCA
        private short cycle_type    ;
        private short cycle_unit    ;
        private int cycle_flag;   //一次性产品参考周期标识
        private int first_bill_date;  //首周期截止时间
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
        public short getCycle_type()
        {
            return cycle_type;
        }
        public void setCycle_type(short cycle_type)
        {
            this.cycle_type = cycle_type;
        }
        public short getCycle_unit()
        {
            return cycle_unit;
        }
        public void setCycle_unit(short cycle_unit)
        {
            this.cycle_unit = cycle_unit;
        }
        public int getCycle_flag()
        {
            return cycle_flag;
        }
        public void setCycle_flag(int cycle_flag)
        {
            this.cycle_flag = cycle_flag;
        }
        public int getFirst_bill_date()
        {
            return first_bill_date;
        }
        public void setFirst_bill_date(int first_bill_date)
        {
            this.first_bill_date = first_bill_date;
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