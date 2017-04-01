package com.ailk.imssh.common.flow.bean;

import java.util.Map;

import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncAllInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncIvrAllInfo;

/**
 * @Description 上发MDB时候存储的一些信息
 * @author lijc3
 * @Date 2013-8-28
 */
public class SyncMdbInfoBean
{
    /**
     * 上发mdb需要的参数
     */
    private SSyncAllInfo sSyncAllInfo = null;
    /**
     * 过户上发老的MDB失效的参数
     */
    private SSyncAllInfo changeOwnSyncInfo = null;
    
    /**
     * 新增的ivr_mdb
     */
    private SSyncIvrAllInfo sSyncIvrAllInfo = null;
    /**
     * 过户时老的acct_id
     */
    private Long oldAcctId;
    /**
     * 过户时新的acct_id
     */
    private Long newAcctId;
    /**
     * 过户是用户id
     */
    private Long userId;
    
    /**
     * 过户时的数据，天津批量过户，key为“newAcctId,oldAcctId”
     */
    private Map<String,Map<String,Object>> changeAcctMap;

    /**
     * 资产迁移是否失败
     */
    private boolean abmFail;
	private boolean userMdbFail;
	private boolean ivrFail;
    public SSyncAllInfo getsSyncAllInfo()
    {
        return sSyncAllInfo;
    }
    public void setsSyncAllInfo(SSyncAllInfo sSyncAllInfo)
    {
        this.sSyncAllInfo = sSyncAllInfo;
    }
    public SSyncAllInfo getChangeOwnSyncInfo()
    {
        return changeOwnSyncInfo;
    }
    public void setChangeOwnSyncInfo(SSyncAllInfo changeOwnSyncInfo)
    {
        this.changeOwnSyncInfo = changeOwnSyncInfo;
    }
    public SSyncIvrAllInfo getsSyncIvrAllInfo() {
		return sSyncIvrAllInfo;
	}
	public void setsSyncIvrAllInfo(SSyncIvrAllInfo sSyncIvrAllInfo) {
		this.sSyncIvrAllInfo = sSyncIvrAllInfo;
	}
	public Long getOldAcctId()
    {
        return oldAcctId;
    }
    public void setOldAcctId(Long oldAcctId)
    {
        this.oldAcctId = oldAcctId;
    }
    public Long getNewAcctId()
    {
        return newAcctId;
    }
    public void setNewAcctId(Long newAcctId)
    {
        this.newAcctId = newAcctId;
    }
    public Long getUserId()
    {
        return userId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public boolean isUserMdbFail()
    {
        return userMdbFail;
    }
    public void setUserMdbFail(boolean userMdbFail)
    {
        this.userMdbFail = userMdbFail;
    }
 
    public boolean isAbmFail()
    {
        return abmFail;
    }
    public void setAbmFail(boolean abmFail)
    {
        this.abmFail = abmFail;
    }
    
    
	public boolean isIvrFail() {
		return ivrFail;
	}
	public void setIvrFail(boolean ivrFail) {
		this.ivrFail = ivrFail;
	}
	public Map<String, Map<String, Object>> getChangeAcctMap() {
		return changeAcctMap;
	}
	public void setChangeAcctMap(Map<String, Map<String, Object>> changeAcctMap) {
		this.changeAcctMap = changeAcctMap;
	}
    
    
    
}
