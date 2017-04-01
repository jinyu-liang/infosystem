package com.ailk.ims.util;

import jef.common.wrapper.IntRange;
import com.ailk.ims.common.OrderCondition;
import com.ailk.openbilling.persistence.imsinner.entity.SPage;

/**
 * 分页相关共用处理类
 * 
 * @author wangjt
 * @Date 2011-12-13
 */
public class PageUtil
{
    /**
     * 页面分页对象转为数据库分页对象
     */
    public static IntRange toIntRange(SPage page)
    {
        int num = page.getNum();
        int size = page.getSize();
        // 如果num或者size有一个小于等于0，就认为无需分页
        if (num < 1 || size < 1)
        {
            return null;
        }
        int start = ((num - 1) * size) + 1;// start with 1
        int end = (num * size);// start with 'start + size -1'
        return new IntRange(start, end);
    }

    public static OrderCondition[] toOrderCondition(SPage page)
    {
        return null;
    }

}
