package com.ailk.imssh.common.manual.handler;

import org.springframework.transaction.annotation.Transactional;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.openbilling.persistence.cust.entity.CmUserPortability;


public interface ITransferHandler
{
    
    @Transactional
    public void transfer(CmUserPortability protability,ITableContext context) throws Exception;
    
}
