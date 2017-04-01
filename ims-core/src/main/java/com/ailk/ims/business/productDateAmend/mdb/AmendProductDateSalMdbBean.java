package com.ailk.ims.business.productDateAmend.mdb;

import java.util.ArrayList;
import java.util.List;
import com.ailk.ims.common.BaseSalMdbBean;
import com.ailk.ims.common.MdbRdl;
import com.ailk.ims.component.ProdMdbSalComponent;

/**
 * @Description: 修改产品时间上发mdb
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author tangjl5
 * @Date 2012-6-19
 */
public class AmendProductDateSalMdbBean extends BaseSalMdbBean
{
    /**
     * (Not Javadoc)
     * <p>
     * Title: createMdbRdl
     * </p>
     * <p>
     * Description:
     * </p>
     * 
     * @return
     * @throws Exception
     * @see com.ailk.ims.common.BaseSalMdbBean#createMdbRdl()
     */
    @Override
    public List<MdbRdl> createMdbRdl() throws Exception
    {
        List<MdbRdl> result = new ArrayList<MdbRdl>();
        ProdMdbSalComponent salCmp = context.getComponent(ProdMdbSalComponent.class);
        salCmp.buildSalProductOrder(result);
        return result;
    }

}
