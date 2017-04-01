package com.ailk.ims.complex;

import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;

/**
 * 群复合类
 * @Description
 * @author luojb
 * @Date 2012-8-30
 */
public class GroupComplex extends AccountComplex
{
    private CaAccountGroup group;

    public CaAccountGroup getGroup()
    {
        return group;
    }
    public void setGroup(CaAccountGroup group)
    {
        this.group = group;
    }
    

}
