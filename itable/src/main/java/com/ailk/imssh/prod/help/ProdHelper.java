package com.ailk.imssh.prod.help;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;

/**
 * @Description:产品相关静态方法
 * @author wangjt
 * @Date 2012-6-12
 */
public class ProdHelper extends com.ailk.ims.component.helper.ProdHelper
{
    /**
     * @description List<CoProdCharValue> 转换成 List<CoFnCharValue>
     * @author wangyh3
     * @date 2012-04-28
     */
    public static List<CoFnCharValue> coProdCharValues2CoFnCharValues(List<CoProdCharValue> coProdCharValues)
    {
        List<CoFnCharValue> coFnCharValues = new ArrayList<CoFnCharValue>();
        for (Iterator<CoProdCharValue> iterator2 = coProdCharValues.iterator(); iterator2.hasNext();)
        {
            CoProdCharValue value = iterator2.next();
            coFnCharValues.add(coProdCharValue2CoFnCharValue(value));
        }
        return coFnCharValues;
    }

    /**
     * @description CoProdCharValue 转换成 CoFnCharValue
     * @author wangyh3
     * @date 2012-04-28
     */
    private static CoFnCharValue coProdCharValue2CoFnCharValue(CoProdCharValue coProdCharValue)
    {
        CoFnCharValue coFnCharValue = null;
        if (coProdCharValue != null)
        {
            coFnCharValue = new CoFnCharValue();
            coFnCharValue.setProductId(coProdCharValue.getProductId());
            coFnCharValue.setGroupId(coProdCharValue.getGroupId());
            coFnCharValue.setSpecCharId(coProdCharValue.getSpecCharId());
            coFnCharValue.setValue(coProdCharValue.getValue());
            coFnCharValue.setValidDate(coProdCharValue.getValidDate());
            coFnCharValue.setExpireDate(coProdCharValue.getExpireDate());
            coFnCharValue.setSoNbr(coProdCharValue.getSoNbr());
            coFnCharValue.setCreateDate(coProdCharValue.getCreateDate());
            coFnCharValue.setSoDate(coProdCharValue.getSoDate());
            coFnCharValue.setObjectId(coProdCharValue.getObjectId());
            coFnCharValue.setObjectType(coProdCharValue.getObjectType());
        }
        return coFnCharValue;
    }
}
