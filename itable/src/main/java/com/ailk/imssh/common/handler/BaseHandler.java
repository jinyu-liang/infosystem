package com.ailk.imssh.common.handler;

import java.util.List;

import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.bean.ContextExBean;

import jef.database.DataObject;
/**
 * @Description：处理数据基类
 * @author wangjt
 * @param <T>
 * @Date 2012-5-11
 */
public abstract class BaseHandler extends ContextExBean
{   
    /**
     * @param dataArr:每张I表中的数据，都作为List传入；可以同时传入多张I表的数据
     */
    public abstract void handle(List<? extends DataObject>... dataArr) throws IMSException;
    
    /**
     * @param dataArr 正式处理前需要做的事情，清理数据等
     * 只有在busi_code=11111111111的时候才调用该方法，设置生效时间，清理数据
     */
    public abstract void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException;

    /**
     * 在处理前对groupId做处理 解决acctId和groupId冲突问题
     * @param dataArr
     * @throws IMSException
     */
    public abstract void initData(List<? extends DataObject>... dataArr) throws IMSException;

	/**
	 * 
	 * 处理CRM和BOSS数据差异
	 * oper_method=8 && busi_code = 999 
	 * @param paramArr
	 */
	public abstract void handleDiffException(List<? extends DataObject>[] paramArr) throws IMSException ;

	
   }
