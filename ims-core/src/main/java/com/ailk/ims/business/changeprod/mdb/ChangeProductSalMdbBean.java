package com.ailk.ims.business.changeprod.mdb;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturn;
import com.ailk.ims.common.BaseSalMdbBean;
import com.ailk.ims.common.ListMapMdbRdl;
import com.ailk.ims.common.MdbRdl;
import com.ailk.ims.component.MdbSalComponent;
import com.ailk.ims.component.ProdMdbSalComponent;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
/**
 * @Description: 修改产品上发MDB
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tangjl5
 * @Date 2012-6-19
 * @Date 2012-07-05 yangjh 增加非空判断 避免空指针
 */
public class ChangeProductSalMdbBean extends BaseSalMdbBean {

	@Override
	public List<MdbRdl> createMdbRdl() {
		// TODO Auto-generated method stub
		List<MdbRdl> result = new ArrayList<MdbRdl>();
		MdbSalComponent salCmp = context.getComponent(MdbSalComponent.class);
		ProdMdbSalComponent prodSalCmp = context.getComponent(ProdMdbSalComponent.class);
		
		prodSalCmp.buildSalProductOrder(result);
		result.add(salCmp.buildSIdentityList());
		result.add(salCmp.buildSImsiSerialNoReList());
		
		ListMapMdbRdl userGroupRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERGROUP,SReturn.class);
		List<CaAccountRv> resList = context.getAllCacheList(CaAccountRv.class);
		//2012-07-05 yangjh 增加非空判断 避免空指针
		if(CommonUtil.isNotEmpty(resList))
		{
			 for (CaAccountRv res : resList)
		        {
		            userGroupRdl.addRecord(res.getResourceId(), getMdbSalBuildComp().buildCorpUserGroup(res,context.isDiffException()));
		        }
		}
        result.add(userGroupRdl);
        
        ListMapMdbRdl groupInfoRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_GROUPINFO,SReturn.class);
        List<CaAccountGroup> vpns = context.getAllCacheList(CaAccountGroup.class);
        if(CommonUtil.isNotEmpty(vpns))
        {
        	for (CaAccountGroup vpn : vpns)
            {
                groupInfoRdl.addRecord(vpn.getAcctId(), getMdbSalBuildComp().buildGroup(vpn,context.isDiffException()));
            }
        }
        result.add(groupInfoRdl);
        
        // 2012-08-18 luojb #56035 打包产品关系，上海项目不需要上发
        if(!ProjectUtil.is_CN_SH()){
            ListMapMdbRdl bundleCompRdl = salCmp.buildPromComposite();
            if(bundleCompRdl != null)
            {
                result.add(bundleCompRdl);
            }
        }
        return result;
	}

}
