package com.ailk.ims.manualmdb;

import java.util.List;
import java.util.Set;

import jef.database.DataObject;

public abstract class UpfieldTableMainObject
{
    public static final String OBJ_TYPE_CUST = "cust";
    public static final String OBJ_TYPE_ACCT = "acct";
    public static final String OBJ_TYPE_USER = "user";
    
    private Class<? extends DataObject> DataObjClazz;
    private Set<String> mdbTables;
    
    public UpfieldTableMainObject(Class<? extends DataObject> clazz){
        this.DataObjClazz = clazz;
    }

    public Class<? extends DataObject> getDataObjClazz()
    {
        return DataObjClazz;
    }

    public void setDataObjClazz(Class<? extends DataObject> dataObjClazz)
    {
        DataObjClazz = dataObjClazz;
    }

    public abstract List<String> getFields();
    
    public boolean isObjIdType(){
        return this instanceof UpfieldObjIdType;
    }
    public boolean is3hId(){
        return this instanceof Upfield3hId;
    }
    
    public Set<String> getMdbTables()
    {
        return mdbTables;
    }

    public void setMdbTables(Set<String> mdbTables)
    {
        this.mdbTables = mdbTables;
    }

    public abstract String getUserIdFieldName();
    public abstract String getAcctIdFieldName();
    public abstract String getCustIdFieldName();
    public abstract String getProdIdFieldName();

}
