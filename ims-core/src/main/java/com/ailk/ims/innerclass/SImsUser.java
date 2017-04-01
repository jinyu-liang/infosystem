package com.ailk.ims.innerclass;

 public class SImsUser
    {
        private Long  serv_id;
        private int brand;
        private int region_code;
        private int county_code;
        private int lang_reading;
        private int lang_listening;
        private int lang_writing;
        private int bill_type;    //0：预付费 1：后付费
        private int create_date;  //入网时间
        private int first_use_time;   //seconds
        private int user_segment; //用户等级
        private int flh_flag;
        private int continue_flag;
        private int valid_date;   //seconds
        private int expire_date;  //seconds
        private int sync_flag;    //0全量 , 1增量
        public Long getServ_id()
        {
            return serv_id;
        }
        public void setServ_id(Long serv_id)
        {
            this.serv_id = serv_id;
        }
        public int getBrand()
        {
            return brand;
        }
        public void setBrand(int brand)
        {
            this.brand = brand;
        }
        public int getRegion_code()
        {
            return region_code;
        }
        public void setRegion_code(int region_code)
        {
            this.region_code = region_code;
        }
        public int getCounty_code()
        {
            return county_code;
        }
        public void setCounty_code(int county_code)
        {
            this.county_code = county_code;
        }
        public int getLang_reading()
        {
            return lang_reading;
        }
        public void setLang_reading(int lang_reading)
        {
            this.lang_reading = lang_reading;
        }
        public int getLang_listening()
        {
            return lang_listening;
        }
        public void setLang_listening(int lang_listening)
        {
            this.lang_listening = lang_listening;
        }
        public int getLang_writing()
        {
            return lang_writing;
        }
        public void setLang_writing(int lang_writing)
        {
            this.lang_writing = lang_writing;
        }
        public int getBill_type()
        {
            return bill_type;
        }
        public void setBill_type(int bill_type)
        {
            this.bill_type = bill_type;
        }
        public int getCreate_date()
        {
            return create_date;
        }
        public void setCreate_date(int create_date)
        {
            this.create_date = create_date;
        }
        public int getFirst_use_time()
        {
            return first_use_time;
        }
        public void setFirst_use_time(int first_use_time)
        {
            this.first_use_time = first_use_time;
        }
        public int getUser_segment()
        {
            return user_segment;
        }
        public void setUser_segment(int user_segment)
        {
            this.user_segment = user_segment;
        }
        public int getFlh_flag()
        {
            return flh_flag;
        }
        public void setFlh_flag(int flh_flag)
        {
            this.flh_flag = flh_flag;
        }
        public int getContinue_flag()
        {
            return continue_flag;
        }
        public void setContinue_flag(int continue_flag)
        {
            this.continue_flag = continue_flag;
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
    