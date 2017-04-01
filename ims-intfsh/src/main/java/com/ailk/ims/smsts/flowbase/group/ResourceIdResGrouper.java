package com.ailk.ims.smsts.flowbase.group;

import com.ailk.openbilling.persistence.acct.entity.CaFreeres;

public class ResourceIdResGrouper implements IGrouper
{

    public boolean isSameGroup(Object referObj,Object targetObj)
    {
         if(null==referObj){
             return true;
         }
         
        return ((CaFreeres)referObj).getResourceId().equals(((CaFreeres)targetObj).getResourceId());
    }

}
