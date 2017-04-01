package com.ailk.ims.business.exemptcredit.mdb;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.sdl.MMdbSyncUpDef.SExemption;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturn;
import com.ailk.ims.common.BaseSalMdbBean;
import com.ailk.ims.common.ListMapMdbRdl;
import com.ailk.ims.common.MdbRdl;
import com.ailk.ims.component.MdbSalComponent;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;

/**
 * 免催免停上发mdb
 * 
 * @Description
 * @author tangkun
 * @Date 2012-6-19
 */
public class SetExemptCreditLimitSalMdbBean extends BaseSalMdbBean {

	@Override
	public List<MdbRdl> createMdbRdl() throws Exception {
		List<MdbRdl> result = new ArrayList<MdbRdl>();
		MdbSalComponent comp = context.getComponent(MdbSalComponent.class);
		buildExemptionList(result, comp);
		return result;
	}

	private void buildExemptionList(List<MdbRdl> result, MdbSalComponent comp) {
		List<CaNotifyExempt> exemptions = context
				.getAllCacheList(CaNotifyExempt.class);
		ListMapMdbRdl exemptionRdl = new ListMapMdbRdl(
				MdbKVDefine.SYNC_EXEMPTION, SReturn.class);
		if (CommonUtil.isNotEmpty(exemptions)) {
			for (CaNotifyExempt caNotifyExempt : exemptions) {
				SExemption exemption = getMdbSalBuildComp().buildExemption(caNotifyExempt,context.isDiffException());
				// @Date 2012-08-10 tangjl5 :Story # 55035 使用objectId来上发
				exemptionRdl.addRecord(exemption.get_objectId(), exemption);
			}
			result.add(exemptionRdl);
		}
	}

}
