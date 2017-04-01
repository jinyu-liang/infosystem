package com.ailk.ims.send.auth;

import jef.database.DataObject;


public interface TemplateAuthService
{
   /**
    * 是否允许同步短信到短厅
    * @param dataObject
    * @param fetchSize  分批处理的大小
    * @return
    */
    public  boolean   AllowSyncSms(DataObject dataObject,int fetchSize);
}
