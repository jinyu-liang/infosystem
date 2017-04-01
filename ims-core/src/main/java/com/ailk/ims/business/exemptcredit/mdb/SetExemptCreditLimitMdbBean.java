package com.ailk.ims.business.exemptcredit.mdb;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.sdl.MMdbSyncUpDef.SExemption;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturn;
import com.ailk.ims.common.BaseSalMdbBean;
import com.ailk.ims.common.ListMapMdbRdl;
import com.ailk.ims.common.MdbRdl;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
/**
 * 免催免停上发mdb
 * @Description
 * @author liuyang
 * @Date 2012-2-23
 * @Date lijc3 2012-03-31 增加空判断
 */
public class SetExemptCreditLimitMdbBean extends BaseSalMdbBean
{

	public List<MdbRdl> createMdbRdl() {
		return buildExemptionList();
	}
		
    private List<MdbRdl> buildExemptionList()
    {
    	List<MdbRdl> result = new ArrayList<MdbRdl>();
    	ListMapMdbRdl exemptionRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_EXEMPTION, SReturn.class);
        List<CaNotifyExempt> exemptions = context.getAllCacheList(CaNotifyExempt.class);

        if (CommonUtil.isNotEmpty(exemptions))
        {
            for (CaNotifyExempt caNotifyExempt : exemptions)
            {
                SExemption exemption = buildExemption(caNotifyExempt);
                exemptionRdl.addRecord(exemption.get_objectId(), exemption);
            }
        }
        result.add(exemptionRdl);
        return result;
    }
    
    private SExemption  buildExemption(CaNotifyExempt caNotifyExempt){
    	SExemption exemption = new SExemption();
        exemption.set_objectId(caNotifyExempt.getObjectId());
        exemption.set_exemptType(caNotifyExempt.getExemptionType());
        exemption.set_objectType(caNotifyExempt.getObjectType());
        exemption.set_expireDate(this.toMdbExpireDate(caNotifyExempt.getExpireDate()));
        exemption.set_validDate(this.toMdbValidDate(caNotifyExempt.getValidDate()));
        // @Date 2012-08-14 yangjh : story057 免催免停 增加billing_type的上
        exemption.set_billType(caNotifyExempt.getBillingType());
        // CUserCycle \CExemption \CBudgetInfoDtl特殊的设置为2
        if (context.isSync())
        {
            exemption.set_syncFlag(2);
        }
        else
        {
            exemption.set_syncFlag(0);
        }
        return exemption;
    }

}
