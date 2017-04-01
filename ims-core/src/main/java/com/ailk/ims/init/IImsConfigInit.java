package com.ailk.ims.init;

import com.ailk.ims.xml.BaseNode;

/**
 * @Description: 初始化解析类都必须继承的接口类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-12-1
 */
public interface IImsConfigInit
{
    /**
     * 合并版本
     * luojb 2012-2-23
     * @param node
     * @param nodeProj
     * @throws Exception
     */
    public void mergeNodes(BaseNode node,BaseNode nodeProj) throws Exception;

    public void init(BaseNode node) throws Exception;
}
