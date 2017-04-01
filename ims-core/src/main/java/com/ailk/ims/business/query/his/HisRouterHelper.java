package com.ailk.ims.business.query.his;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.route.client.RouterClient;
import com.ailk.easyframe.route.entity.br.RouterCustomer;
import com.ailk.easyframe.route.entity.br.RouterIdentity;
import com.ailk.easyframe.route.entity.br.RouterResource;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.SpringUtil;
/**
 * 历史路由
 * @author gaoqc5
 * @date  2012-10-10
 * TODO
 */
public class HisRouterHelper
{
  
    /**
     *  只要 HistoryRouterIdentity 的effectiveDate 和expirDate 的时间段 有段落在 beginDate 和endDate之间，符合要求
     *  即如果 effectiveDate 和expirDate 的时间段 和  beginDate 和endDate的时间段有交集，就应该返回
     * @param list
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<RouterIdentity> filterHistoryRouterIdentity(List<RouterIdentity> list,Date beginDate,Date endDate){
          List<RouterIdentity> filterIdentities=new ArrayList<RouterIdentity>();
          for(RouterIdentity historyRouterIdentity:list){
              if(hasMixed(beginDate, endDate, new Date(historyRouterIdentity.getEffectiveDate()), new Date(historyRouterIdentity.getExpireDate()))){
             filterIdentities.add(historyRouterIdentity);
              }
          }
          return filterIdentities;
    }
    
    
    public static List<RouterResource> filterHistoryRouterResource(List<RouterResource> list,Date beginDate,Date endDate){
        List<RouterResource> filterResources=new ArrayList<RouterResource>();
        for(RouterResource historyRouterResource:list){
            if(hasMixed(beginDate, endDate, new Date(historyRouterResource.getEffectiveDate()), new Date(historyRouterResource.getExpireDate()))){
                filterResources.add(historyRouterResource);
            }
        }
        return filterResources;
    }
    
    public static List<RouterCustomer> filterHistoryRouterCustomer(List<RouterCustomer> list,Date beginDate,Date endDate){
        List<RouterCustomer> filterCustomers=new ArrayList<RouterCustomer>();
        for(RouterCustomer historyRouterResource:list){
            if(hasMixed(beginDate, endDate, new Date(historyRouterResource.getEffectiveDate()), new Date(historyRouterResource.getExpireDate()))){
                filterCustomers.add(historyRouterResource);
            }
        }
        return filterCustomers;
  }
    
    
    
    
    /**
     * 是否有交集
     * @param rangeDateStart
     * 
     *                                       10                                                  20
     *    1              5                                                                                                                                              no
     *                         6                   12                                                                                                                  yes
     *                                                     13                   16                                                                                    yes
     *                                                                                     18                      23                                                yes
     *                                                                                                                        25                     N                no
     * 
     *@param rangeDateEnd
     * @param compareDateStart
     * @param compareDateEnd
     * @return
     */
    private static boolean hasMixed(Date rangeDateStart,Date rangeDateEnd,Date compareDateStart,Date compareDateEnd){
        if(null==rangeDateStart||null==rangeDateEnd){//不传时间，返回true
            return true;
        }
           if(rangeDateStart.after(compareDateEnd)){
               return false;
           }
           if(rangeDateEnd.before(compareDateStart)){
               return false;
           }
           return true;
    }
    
    
    /**
     *  
     * 
     * @param phoneId
     * @param date 该时间处在生效时间和过期时间之间
     * @return 历史路由信息
     */
    public static List<RouterIdentity> queryHisIdentityRouter(Long identityId,Long  acctId, Date date)
    {
        
        RouterClient router = SpringUtil.getRouteSearchService();
         return  router.getHistoryRouterIdentitys( RouterIdentity.IdentityType.PHONE,CommonUtil.long2String(identityId),  acctId, date);
//        return null;
 
      
    }

    /**
     *用户 历史路由信息
     * 
     * @param phoneId
     * @param date 该时间处在生效时间和过期时间之间
     * @return 
     */
    public  static List<RouterResource> queryHisUserRouter(Long userId,Long acctId, Date date)
    {
        RouterClient router = SpringUtil.getRouteSearchService();
        return router.getHistoryRouterResources(userId, acctId, date);
//        return null;
 
    }
    /**
     *  客户 历史路由信息
     * @param custId
     * @param date 该时间处在生效时间和过期时间之间
     * @return
     */
    public static List<RouterCustomer> queyHisCustRouter(Long custId,Long acctId,Date date){
        RouterClient router = SpringUtil.getRouteSearchService();
        return router.getHistoryRouterCustomers(custId, acctId, date);
    }
    public static void main(String[] args)
    {
        System.out.println(CommonUtil.long2String(null));
    }


}
