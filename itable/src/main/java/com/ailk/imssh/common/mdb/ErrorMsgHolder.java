package com.ailk.imssh.common.mdb;

import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncAllInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncIvrAllInfo;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;

public class ErrorMsgHolder
{
    private CBSErrorMsg cbsErrorMsg;
    private SSyncAllInfo sSyncAllInfo;
    private SSyncIvrAllInfo sSyncIvrAllInfo;

    public ErrorMsgHolder(CBSErrorMsg cbsErrorMsg, SSyncAllInfo sSyncAllInfo)
    {
        this.cbsErrorMsg = cbsErrorMsg;
        this.sSyncAllInfo = sSyncAllInfo;
    }
    
    public ErrorMsgHolder(CBSErrorMsg cbsErrorMsg,SSyncIvrAllInfo sSyncIvrAllInfo){
    	this.cbsErrorMsg = cbsErrorMsg;
    	this.sSyncIvrAllInfo = sSyncIvrAllInfo;
    }

    public CBSErrorMsg getCbsErrorMsg()
    {
        return cbsErrorMsg;
    }

    public SSyncAllInfo getsSyncAllInfo()
    {
        return sSyncAllInfo;
    }

	public SSyncIvrAllInfo getsSyncIvrAllInfo() {
		return sSyncIvrAllInfo;
	}
    
}
