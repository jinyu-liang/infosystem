package com.ailk.ims.business.query.his;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.route.entity.br.RouterCustomer;
import com.ailk.easyframe.route.entity.br.RouterIdentity;
import com.ailk.easyframe.route.entity.br.RouterResource;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_query3BeanHisRespose;
import com.ailk.openbilling.persistence.imsinner.entity.SQry3BeanHisRet;
import com.ailk.openbilling.persistence.imsinner.entity.SQuery3BeanHisReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * 查询历史三户接口
 * 根据手机号码查询手机号码的销户,过户历史记录
 * 根据用户查询用户的销户，过户历史记录
 * 根据客户ID查询客户的销户，过户历史记录
 * @author gaoqc5
 *
 */
public class Query3BeanHisBusiBean extends BaseBusiBean
{
    
    private static final Integer USER_OBJECT=0;
//    private static final Integer ACCT_OBJECT=1;//暂时没用上
    private static final Integer CUST_OBJECT=2;
    private static final Integer IDEN_OBJECT=3;
    
   
    @Override
    public void init(Object... input) throws BaseException
    {

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        SQuery3BeanHisReq req=(SQuery3BeanHisReq)input[0];
        Integer objectType=req.getObjecctType();
        if(null!=req.getObjectId()){
 
               
             if(USER_OBJECT!=objectType&&CUST_OBJECT!=objectType&&IDEN_OBJECT!=objectType){
                 throw IMSUtil.throwBusiException(ErrorCodeDefine.OPERTYPE_UNDEFINED, objectType);
            }
            
            
        }
        //两个id不能同时为空
        if(null==req.getAcctId()&&null==req.getObjectId()){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, " acctId   ,objectId ");
            
        }
         
        
        




    }
    
   
    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        List<com.ailk.ims.ims3h.IMS3hBean> list = new ArrayList<com.ailk.ims.ims3h.IMS3hBean>();
//        SQuery3BeanHisReq req=(SQuery3BeanHisReq)input[0];
//        if(null!=req.getObjecctType()&&null!=req.getObjectId()){
//            if(USER_OBJECT==req.getObjecctType()){
//                list.add(context.get3hTree().loadUser3hBean(req.getObjectId()));
//            }else if(CUST_OBJECT==req.getObjecctType()){
//                list.add(context.get3hTree().loadCust3hBean(req.getObjectId()));
//            }else if(IDEN_OBJECT==req.getObjecctType()){
//                list.add(context.get3hTree().loadUser3hBean(String.valueOf(req.getObjectId())));
//            }
//        }
        return  list;
}

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SQuery3BeanHisReq req=(SQuery3BeanHisReq)input[0];
        List<SQry3BeanHisRet>s3beanBeanHisRets=new ArrayList<SQry3BeanHisRet>();
        
        if(null!=req.getObjecctType()){//对象类型不能为空
            if(IDEN_OBJECT==req.getObjecctType()){
                s3beanBeanHisRets= doIdentityBusiness(req);
            }else if(CUST_OBJECT==req.getObjecctType()){
                s3beanBeanHisRets=doCustomerBusiness(req);
            }else if(USER_OBJECT==req.getObjecctType()){
                s3beanBeanHisRets=doResourceBusiness(req);
            }
        }else{
            throw IMSUtil.throwBusiException(ErrorCodeDefine.OPERTYPE_UNDEFINED, " ObjectType");
        }
        
 
 
        
        
         return new Object[]{s3beanBeanHisRets};
         
 
    }
    private List<SQry3BeanHisRet> doIdentityBusiness(SQuery3BeanHisReq req){
        
        Long acctId=req.getAcctId();
        Long identityId=req.getObjectId();
        Date beginDate=null;
        if(null!=req.getBeginDate()){
        DateUtil.getFormatDate(req.getBeginDate());
        }
        Date endDate=null;
        if(null!=req.getEndDate()){
        DateUtil.getFormatDate(req.getEndDate());
        }         
         imsLogger.info("************** acctId:",acctId,", identityId:",identityId,", beginDate:",req.getBeginDate(),
                 ", endDate:",req.getEndDate());
         
         List<RouterIdentity> historyRouterIdentities=HisRouterHelper.filterHistoryRouterIdentity(HisRouterHelper.queryHisIdentityRouter(identityId, acctId, null), beginDate, endDate);
       return   handlerHistoryRouterIdentity(historyRouterIdentities);
    }
    private List<SQry3BeanHisRet> doResourceBusiness(SQuery3BeanHisReq req){
        Long acctId=req.getAcctId();
        Long resourceId=req.getObjectId();
         Date beginDate=null;
         if(null!=req.getBeginDate()){
         DateUtil.getFormatDate(req.getBeginDate());
         }
         Date endDate=null;
         if(null!=req.getEndDate()){
         DateUtil.getFormatDate(req.getEndDate());
         }
         imsLogger.info("************** acctId:",acctId,", resourceId:",resourceId,", beginDate:",req.getBeginDate(),
                 ", endDate:",req.getEndDate());
         List<RouterResource> historyRouterResources=HisRouterHelper.filterHistoryRouterResource(HisRouterHelper.queryHisUserRouter(resourceId, acctId,null), beginDate, endDate);
         return handlerHistoryRouterResource(historyRouterResources);
         
         
    }
    private List<SQry3BeanHisRet> doCustomerBusiness(SQuery3BeanHisReq req){
        Long acctId=req.getAcctId();
        Long custId=req.getObjectId();
        Date beginDate=null;
        if(null!=req.getBeginDate()){
        DateUtil.getFormatDate(req.getBeginDate());
        }
        Date endDate=null;
        if(null!=req.getEndDate()){
        DateUtil.getFormatDate(req.getEndDate());
        }
         imsLogger.info("************** acctId:",acctId,", custId:",custId,", beginDate:",req.getBeginDate(),
                 ", endDate:",req.getEndDate());
         List<RouterCustomer> historyRouterCustomers=HisRouterHelper.filterHistoryRouterCustomer(HisRouterHelper.queyHisCustRouter(custId,acctId, null), beginDate, endDate);
        return  handlerHistoryRouterCustomer(historyRouterCustomers);
         
    }
    

    private List<SQry3BeanHisRet> handlerHistoryRouterIdentity(List<RouterIdentity> list){
        List<SQry3BeanHisRet> rets=new ArrayList<SQry3BeanHisRet>();
        for(RouterIdentity identity:list){
            SQry3BeanHisRet ret=new SQry3BeanHisRet();
            //TODO 路由历史接口需要重新实现
//            ret.setAcctId(identity.getAcctId());
            ret.setObjectType(IDEN_OBJECT);
            ret.setObjectId(Long.valueOf(identity.getIdentity()));
            if ( identity.getEffectiveDate()>0)
            {
                ret.setValidDate(formatYyyyMMddHHmmssDate(new Date(identity.getEffectiveDate())));
            }
            ret.setExpireDate(formatYyyyMMddHHmmssDate(new Date(identity.getExpireDate())));
           rets.add(ret);
        }
        return rets;
    }
    private List<SQry3BeanHisRet> handlerHistoryRouterResource(List<RouterResource> list){
        List<SQry3BeanHisRet> rets=new ArrayList<SQry3BeanHisRet>();
        for(RouterResource resource:list){
            SQry3BeanHisRet ret=new SQry3BeanHisRet();
            ret.setAcctId(resource.getAcctId());
            ret.setObjectId(resource.getResourceId());
            ret.setObjectType(USER_OBJECT);
            if ( resource.getEffectiveDate()>0)
            {
                ret.setValidDate(formatYyyyMMddHHmmssDate(new Date(resource.getEffectiveDate())));
            }
            ret.setExpireDate(formatYyyyMMddHHmmssDate(new Date(resource.getExpireDate())));
            rets.add(ret);
        }
        return rets;
    }
    private List<SQry3BeanHisRet> handlerHistoryRouterCustomer(List<RouterCustomer> list){
        List<SQry3BeanHisRet> rets=new ArrayList<SQry3BeanHisRet>();
        for(RouterCustomer customer:list){
            SQry3BeanHisRet ret=new SQry3BeanHisRet();
            ret.setAcctId(customer.getAcctId());
            ret.setObjectType(CUST_OBJECT);
            ret.setObjectId(customer.getCustId());
            if ( customer.getEffectiveDate()>0)
            {
                ret.setValidDate(formatYyyyMMddHHmmssDate(new Date(customer.getEffectiveDate())));
            }
            ret.setExpireDate(formatYyyyMMddHHmmssDate(new Date(customer.getExpireDate())));
            rets.add(ret);
        }
        return rets;
    }
    public static  String formatYyyyMMddHHmmssDate(Date date){
         return DateUtil.formatDate(date, DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
    }
   
    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_query3BeanHisRespose respose=new Do_query3BeanHisRespose();
        List<SQry3BeanHisRet>s3beanBeanHisRets=(List<SQry3BeanHisRet>)result[0];
        respose.setList(s3beanBeanHisRets);
       
        return respose;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }
    public static void main(String[] args)
    {                                                                                                                    
        System.out.println(formatYyyyMMddHHmmssDate(DateUtil.getFormatDate("2015082261348")));
    }
    
    

    

}
