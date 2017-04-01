package com.ailk.imssh.common.flow.config;

import java.util.Date;
import java.util.List;

import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;

import jef.database.DataObject;
import jef.database.query.Query;


/**
 * @Description:群成员提醒-配置接口类
 * @author liming15
 * @Date 2013-5-31
 */
public interface ISendToMemberConfig
{
	
    /**
     * 根据群id获取所有群成员 
     */
    public List<CaAccountRv> getGroupMember(DataObject dataObject,Date expireDate);
    
    /**
	 * TiOSmsImme_[0~49] 
	 */
	public String getTiOSmsImmeNamePrefix();
	
	/**
     * 历史表
     */
	public String getHisTableName();
	
	 /**
     * 信控群组成员提醒触发表 
     */
    public String getTableName();
    
    /**
     * 扫描提醒触发表的查询条件，包括order by条件
     */
    public Query newTableQuery();
    
    /**
     * 短信表分表总数
     */
    public int getSubTableCount();
    
    /**
     * 获取提醒触发表中，要删除的对象
     */
    public DataObject getDeleteDataObject(DataObject dataObject);

    /**
     * 将提醒触发对象转换为历史记录对象，处理完后移历史表/
     */
    public DataObject convertToHis(DataObject dataObject, Date dealDate);
    
    public DataObject convertToTiOSmsImmeObject(DataObject caNotifyObject,String phoneId);
}