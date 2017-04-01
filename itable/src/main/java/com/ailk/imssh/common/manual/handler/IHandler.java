package com.ailk.imssh.common.manual.handler;

import org.springframework.transaction.annotation.Transactional;

import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.openbilling.persistence.jd.entity.ImsManualModify;

/**
 * @Description:公共处理基类
 * @author wangjt
 * @Date 2012-9-25
 */
public interface IHandler
{

    @Transactional
    void hander(ImsManualModify imsManualModify, ITableContext context, String tableName) throws Exception;

}