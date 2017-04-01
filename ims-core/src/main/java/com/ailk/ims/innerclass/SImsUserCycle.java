package com.ailk.ims.innerclass;

 public class SImsUserCycle{
        private Long serv_id;
        private int user_cycle;
        private int next_cycle;
        private int sts_dtl;
        private int valid_date;   //seconds
        private int expire_date;  //seconds
        private int next_expire_date;
        private int sync_flag;    //0全量 , 1增量
        public Long getServ_id()
        {
            return serv_id;
        }
        public void setServ_id(Long serv_id)
        {
            this.serv_id = serv_id;
        }
        public int getUser_cycle()
        {
            return user_cycle;
        }
        public void setUser_cycle(int user_cycle)
        {
            this.user_cycle = user_cycle;
        }
        public int getNext_cycle()
        {
            return next_cycle;
        }
        public void setNext_cycle(int next_cycle)
        {
            this.next_cycle = next_cycle;
        }
        public int getSts_dtl()
        {
            return sts_dtl;
        }
        public void setSts_dtl(int sts_dtl)
        {
            this.sts_dtl = sts_dtl;
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
        public int getNext_expire_date()
        {
            return next_expire_date;
        }
        public void setNext_expire_date(int next_expire_date)
        {
            this.next_expire_date = next_expire_date;
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
    