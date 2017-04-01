package com.ailk.ims.business.modifyacctstate.mdb;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.ims.common.BaseSalMdbBean;
import com.ailk.ims.common.MdbRdl;
import com.ailk.ims.component.MdbSalComponent;

/**
 * @Description: 将账户信息同步到mdb
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author wangdw5
 * @Date 2012-6-19
 */
public class ModifyAcctStateSalMdbBean extends BaseSalMdbBean {

	@Override
	public List<MdbRdl> createMdbRdl() throws Exception {
		List<MdbRdl> result = new ArrayList<MdbRdl>();
		MdbSalComponent salCmp = context.getComponent(MdbSalComponent.class);
		result.add(salCmp.buildSAcctCycleList());
		return result;
	}
}
