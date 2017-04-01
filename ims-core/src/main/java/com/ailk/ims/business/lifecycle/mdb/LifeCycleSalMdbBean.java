/**
 * 
 */
package com.ailk.ims.business.lifecycle.mdb;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturn;
import com.ailk.ims.common.BaseSalMdbBean;
import com.ailk.ims.common.ListMapMdbRdl;
import com.ailk.ims.common.MdbRdl;
import com.ailk.ims.component.MdbSalComponent;
import com.ailk.ims.define.MdbKVDefine;

/**@Description: 生命周期状态上发
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tangjl5
 * @Date 2012-6-18 
 */
public class LifeCycleSalMdbBean extends BaseSalMdbBean
{

    /** (Not Javadoc)
     * <p>Title: createMdbRdl</p>
     * <p>Description: </p>
     * @return
     * @see com.ailk.ims.common.BaseSalMdbBean#createMdbRdl()
     */
    @Override
    public List<MdbRdl> createMdbRdl()
    {
        List<MdbRdl> result = new ArrayList<MdbRdl>();
        
        
        MdbSalComponent salCmp = context.getComponent(MdbSalComponent.class);
        result.add(salCmp.buildSUserCycleList());
        return result;
    }

}
