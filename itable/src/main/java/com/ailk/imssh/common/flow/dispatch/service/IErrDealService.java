package com.ailk.imssh.common.flow.dispatch.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import jef.database.AbstractDbClient;
import jef.database.DataObject;
import com.ailk.imssh.common.flow.config.IConfig;

/**
 * @Description:i_data_index_err 重新处理
 * @author liucc
 * @Date 2015-10-26
 */
@Transactional
public interface IErrDealService
{

    /**
     * @throws Exception 
     * @param 处理的年月分表
     * @throws  
     */
	public void errDeal(String YYYYMM) throws Exception;
}