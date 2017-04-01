package com.ailk.imssh.user.helper;

import java.util.Map;

import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.openbilling.persistence.itable.entity.IUserAcctRel;


/**
 * @Description 用户相关处理方法
 * @author wukl
 * @Date 2012-5-18
 */
public class UserAcctRelHelper
{
    private UserAcctRelHelper()
    {
        
    }
    
    /**
     * 判断是否需要发布路由
     * @param context
     * @param userId
     * @return
     */
    public static boolean needCreateRoute(ITableContext context,Long userId){
    	 Object flag = context.getExtendParam(ConstantExDefine.CHANGE_ACCT_FLAG);
    	 if(flag != null && Boolean.valueOf(flag.toString())){
    		 Map<Long,Map<String,Object>> changeAcctMap = (Map<Long, Map<String, Object>>) context.getExtendParam(ConstantExDefine.CHANGE_ACCT_MAP);
    		 if(changeAcctMap!=null && changeAcctMap.get(userId) != null){
    			 //发生了过户，并且是这个用户的过户，那么这个用户先不发布路由，在数据搬迁的时候发布
    			 return false;
    		 }
    	 }
    	 return true;
    }
    
    /**
     * 判断是否过户
     * wukl 2012-11-27
     * @param context
     * @return 是过户返回true
     */
    public static boolean isChangeOwner(ITableContext context)
    {
        Object flag = context.getExtendParam(ConstantExDefine.CHANGE_ACCT_FLAG);
        return flag == null ? false : Boolean.valueOf(flag.toString());
    }
    
    /**
     * 判断过户是否需要移数据
     * wukl 2012-11-27
     * @param context
     * @return
     */
    public static boolean isRouterMoveData(Map<String,Object> paramMap)
    {
        Object flag = paramMap.get(ConstantExDefine.ROUTER_MOVE_DATA_FLAG);
        return flag == null ? false : Boolean.valueOf(flag.toString());
    }
    
    
    /**
     * 判断用户是否销户
     * wukl 2012-11-27
     * @param context
     * @return
     */
    public static boolean isPoolUser(ITableContext context)
    {
        Object flag = context.getExtendParam(ConstantExDefine.USER_STS_POOL);
        return flag == null ? false : Boolean.valueOf(flag.toString());
    }
}
