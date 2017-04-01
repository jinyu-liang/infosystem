package com.ailk.imssh.common.util;

import java.util.Comparator;
import jef.database.DataObject;
import jef.database.Field;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.DBUtil;

/**
 * @Description 接口表的数据排序工具（根据operType字段的3=删除>2=修改>1=新增）
 * @author wukl
 * @Date 2012-8-11
 */
public class CommonOperTypeComparator<T extends DataObject> implements Comparator<T>
{

    @Override
    public int compare(T o1, T o2)
    {
        Field field1 = DBUtil.getJefField(o1.getClass(), "operType");
        Field field2 = DBUtil.getJefField(o2.getClass(), "operType");
        //活动多用户表没有oper_type
        if (field1 == null || field2 == null)
        {
            return 0;
        }

        Integer value1 = (Integer) ClassUtil.getPropertyValue(o1, field1.name());
        Integer value2 = (Integer) ClassUtil.getPropertyValue(o2, field2.name());

        int result = 0;
        if (value1.intValue() == 1)
        {
            switch (value2.intValue())
            {
            case 1:
                result = 0;
                break;
            case 2:
                result = 1;
                break;

            default:
                result = 1;
                break;
            }

        }
        else if (value1.intValue() == 2)
        {
            switch (value2.intValue())
            {
            case 1:
                result = -1;
                break;
            case 2:
                result = 0;
                break;

            default:
                result = 1;
                break;
            }
        }
        else
        {
            switch (value2.intValue())
            {
            case 1:
                result = -1;
                break;
            case 2:
                result = -1;
                break;

            default:
                result = 0;
                break;
            }
        }
        return result;
    }

}
