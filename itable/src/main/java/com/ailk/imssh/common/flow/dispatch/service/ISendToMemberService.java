package com.ailk.imssh.common.flow.dispatch.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.imssh.common.flow.config.ISendToMemberConfig;
import jef.database.DataObject;

/**
 * @Description:给群成员生成提醒短信接口类
 * @author liming15
 * @Date 2013-5-31
 */
@Transactional
public interface ISendToMemberService
{

    /**
     * @param dataObjectList:提醒触发表的数据List
     */
    public void makeSmsToTiOSmsImme(List<DataObject> dataObjectList,ISendToMemberConfig config) throws Exception;

}