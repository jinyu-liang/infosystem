package com.ailk.ims.manualmdb;

import java.util.ArrayList;
import java.util.List;
import jef.database.DataObject;

public class Upfield3hId extends UpfieldTableMainObject
{
    private String custIdFieldName;
    private String acctIdFieldName;
    private String userIdFieldName;
    
    
    public Upfield3hId(Class<? extends DataObject> clazz)
    {
        super(clazz);
    }
    
    public String getCustIdFieldName()
    {
        return custIdFieldName;
    }
    public void setCustIdFieldName(String custIdFieldName)
    {
        this.custIdFieldName = custIdFieldName;
    }
    public String getAcctIdFieldName()
    {
        return acctIdFieldName;
    }
    public void setAcctIdFieldName(String acctIdFieldName)
    {
        this.acctIdFieldName = acctIdFieldName;
    }
    public String getUserIdFieldName()
    {
        return userIdFieldName;
    }
    public void setUserIdFieldName(String userIdFieldName)
    {
        this.userIdFieldName = userIdFieldName;
    }
    @Override
    public List<String> getFields()
    {
        List<String> list = new ArrayList<String>();
        if(custIdFieldName!=null){
            list.add(custIdFieldName);
        }
        if(acctIdFieldName!=null){
            list.add(acctIdFieldName);
        }
        if(userIdFieldName!=null){
            list.add(userIdFieldName);
        }
        
        return list;
    }
    
    public String toString(){
        return "[cust:"+custIdFieldName+"][acct:"+acctIdFieldName+"][user:"+userIdFieldName+"]";
    }

    @Override
    public String getProdIdFieldName()
    {
        return null;
    }
    

}
