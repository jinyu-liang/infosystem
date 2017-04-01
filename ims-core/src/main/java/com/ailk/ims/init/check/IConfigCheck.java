package com.ailk.ims.init.check;

import com.ailk.ims.cache.BaseCacheParser;
import com.ailk.ims.xml.BaseNode;

/**
 * 配置文件中如果需要对数据做检测，实现本接口
 * @Description
 * @author wuyj
 * @Date 2012-9-17
 */
public interface IConfigCheck
{
    public void check(BaseNode checkNode,BaseCacheParser cacheParser) throws Exception;
}
