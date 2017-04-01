package com.ailk.ims.innerclass;

 public class SImsProdCharValue 
    {
        private Long product_id    ;
        private Long object_id          ;
        private short object_type        ;//0:Dev 1:Account 2:GCA
        private Long group_id  ;
        private int spec_char_id  ;
        private String value   ;
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
        public Long getGroup_id()
        {
            return group_id;
        }
        public void setGroup_id(Long group_id)
        {
            this.group_id = group_id;
        }
        public int getSpec_char_id()
        {
            return spec_char_id;
        }
        public void setSpec_char_id(int spec_char_id)
        {
            this.spec_char_id = spec_char_id;
        }
        public String getValue()
        {
            return value;
        }
        public void setValue(String value)
        {
            this.value = value;
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