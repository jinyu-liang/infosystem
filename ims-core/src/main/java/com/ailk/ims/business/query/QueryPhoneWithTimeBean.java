package com.ailk.ims.business.query;

import java.sql.SQLException;
import java.util.List;
import jef.database.QueryArg.MyTableName;
import jef.database.query.Query;
import com.ailk.easyframe.route.entity.br.RouterResource;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.query.his.HisRouterHelper;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.RouterComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.his.entity.CmResIdentityHis;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.innerqry.entity.Do_queryPhoneWithTimeResponse;
import com.ailk.openbilling.persistence.innerqry.entity.SQueryPhoneWithTimeReq;

/**
 * 根据用户编号查询指定时间点对应的手机号码
 * @Description
 * @author wukl
 * @Date 2013-1-17
 */
public class QueryPhoneWithTimeBean extends BaseBusiBean
{

    private String phoneId = null;
    
    @Override
    public void init(Object... input) throws BaseException
    {
        
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        SQueryPhoneWithTimeReq req = (SQueryPhoneWithTimeReq)input[0];
        if (!CommonUtil.isValid(req.getResourceId()) || !CommonUtil.isValid(req.getAcctId()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resourceId or acctId");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SQueryPhoneWithTimeReq req = (SQueryPhoneWithTimeReq) input[0];
        Long inputResourceId = req.getResourceId();
        Long inputAcctId = req.getAcctId();
//        Date queryTime = IMSUtil.formatDate(req.getQueryTime());

        //1、从当前路由表中查询对应的账户
        Long acctId = getAcctIdInCurrentRouter(inputResourceId);
        
        if (acctId != null)
        {
            //2、从路由查询账户对应的分表中找对应的手机号
            phoneId = getPhoneId(inputResourceId,acctId);
            
            if (CommonUtil.isEmpty(phoneId))
            {
                //3、如果为空，则去传入账户 的对应分表中找
                phoneId = getPhoneId(inputResourceId, inputAcctId);
                if (CommonUtil.isEmpty(phoneId))
                {
                    //4、如果为空，则去历史路由中找对应的账户，轮循每个账户，去对应的分表中查询
                    List<RouterResource> routerList = getAcctIdInHisRouter(inputResourceId);
                    
                    if (CommonUtil.isEmpty(routerList))
                    {
                        return null;
                    }
                    
                    for (RouterResource router : routerList)
                    {
                        //必须使用从历史路由中查询出来的账户编号，而不是外围传入的账户编号
                        acctId = router.getAcctId();
                        phoneId = getPhoneId(inputResourceId, acctId);
                        if (CommonUtil.isNotEmpty(phoneId))
                        {
                            break;
                        }

                    }
                }
            }
        }
        else
        {
            //3、如果为空，则去传入账户 的对应分表中找
            phoneId = getPhoneId(inputResourceId, inputAcctId);
            if (CommonUtil.isEmpty(phoneId))
            {
                //4、如果为空，则去历史路由中找对应的账户，轮循每个账户，去对应的分表中查询
                List<RouterResource> routerList = getAcctIdInHisRouter(inputResourceId);
                
                if (CommonUtil.isEmpty(routerList))
                {
                    return null;
                }
                
                for (RouterResource router : routerList)
                {
                    if(router == null)
                    {
                        continue;
                    }
                    
                    // 必须使用从历史路由中查询出来的账户编号，而不是外围传入的账户编号
                    acctId = router.getAcctId();
                    phoneId = getPhoneId(inputResourceId, acctId);
                    if (CommonUtil.isNotEmpty(phoneId))
                    {
                        break;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryPhoneWithTimeResponse resp = new Do_queryPhoneWithTimeResponse();
        if (phoneId != null)
        {
            resp.setPhoneId(phoneId);
        }
            
        return resp;
    }

    @Override
    public void destroy()
    {
    }
    
    /**
     * 根据传入的条件及分表表名进行搜索
     * wukl 2013-1-18
     * @param query
     * @param tableName
     * @return
     * @throws BaseException
     */
    private List queryMethod(Query<?> query,String tableName) throws BaseException
    {
        List list = null;
        try
        {
            MyTableName myTableName = new MyTableName(tableName);
            list = DBUtil.getDBClient().select(query,null,myTableName);
        }
        catch (SQLException e)
        {
            throw new BaseException(e.getErrorCode(), e, e.getMessage());
        }
        
        return list;
    }
    
    private Long getAcctIdInCurrentRouter(Long userId)
    {
        try
        {
            return context.getComponent(RouterComponent.class).getAcctIdByUserIdRout(userId);
        }
        catch (Exception e)
        {
        	imsLogger.error(e,e);
        }
        return null;
    }
    
    private List<RouterResource> getAcctIdInHisRouter(Long userId)
    {
//        RouterClient router = SpringUtil.getRouteSearchService();
        try
        {
//            List<HistoryRouterResource> result = router.getHistoryRouterResources(userId, null, null);
            List<RouterResource> result = HisRouterHelper.queryHisUserRouter(userId, null, null);
            if (CommonUtil.isNotEmpty(result))
            {
                return result;
            }
        }
        catch (Exception e)
        {
        	imsLogger.error(e,e);
        }
        return null;
    }
    
    /**
     * 根据传入的用户编号，账户编号  从原表中查询expireDate最晚的记录
     * wukl 2013-1-19
     * @param resourceId
     * @param acctId
     * @return
     */
    private CmResIdentity getLastCmResIdentity(Long resourceId, Long acctId)
    {
        Query<?> query = new CmResIdentity().getQuery();
        query.addCondition(CmResIdentity.Field.resourceId, resourceId);
        query.addCondition(CmResIdentity.Field.identityAttr,EnumCodeDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER);
        query.addOrderBy(false, CmResIdentity.Field.expireDate);
        String subTableName = "CD.CM_RES_IDENTITY_" + acctId % 50;
        List list = queryMethod(query, subTableName);
        if (CommonUtil.isNotEmpty(list))
            return (CmResIdentity) list.get(0);

        return null;
    }
    
    /**
     * 根据传入的用户编号，账户编号  从历史表中查询expireDate最晚的记录
     * wukl 2013-1-19
     * @param resourceId
     * @param acctId
     * @return
     */
    private CmResIdentityHis getLastCmResIdentityHis(Long resourceId, Long acctId)
    {
        Query<?> query = new CmResIdentityHis().getQuery();
        query.addCondition(CmResIdentityHis.Field.resourceId, resourceId);
        query.addCondition(CmResIdentity.Field.identityAttr,EnumCodeDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER);
        query.addOrderBy(false, CmResIdentityHis.Field.expireDate);
        String tableName = "CD.CM_RES_IDENTITY_HIS_" + acctId % 50;
        List list = queryMethod(query, tableName);
        if (CommonUtil.isNotEmpty(list))
        {
            return (CmResIdentityHis) list.get(0);
        }

        return null;
    }
    
    /**
     * 先从原表中查询，没有的话再查历史表
     * wukl 2013-1-19
     * @param resourceId
     * @param acctId
     * @return
     */
    private String getPhoneId(Long resourceId, Long acctId)
    {
        //获取失效时间最大的记录 对应的手机号码
        CmResIdentity identity = getLastCmResIdentity(resourceId, acctId);
        if (identity != null)
        {
            return identity.getIdentity();
        }

        //获取失效时间最大的记录 对应的手机号码
        CmResIdentityHis identityhis = getLastCmResIdentityHis(resourceId, acctId);
        if (identityhis != null)
        {
            return identityhis.getIdentity();
        }
        
        return null;
    }
    
}
