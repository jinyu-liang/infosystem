/**
 * 
 */
package com.ailk.ims.common;

import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;

/**@Description: 定时器处理类
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tangjl5
 * @Date 2012-6-11 
 */
public abstract class BaseTsHandleBean extends ContextBean
{
    /**
     * 进行具体的逻辑处理.。
     * @param dbObj,如果是批量模式，传入的是List<DataObject>，单条模式传入的是DataObject
     * @return 需要返回处理结果
     */
    public abstract CBSErrorMsg deal(Object dbObj);
}
