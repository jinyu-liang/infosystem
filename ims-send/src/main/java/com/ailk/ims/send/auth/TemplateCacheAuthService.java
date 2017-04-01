package com.ailk.ims.send.auth;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import com.ailk.ims.send.util.SendSpringUtil;
import com.ailk.openbilling.persistence.jd.entity.ImsSmsSendAuth;

public abstract class TemplateCacheAuthService implements TemplateAuthService
{
    protected Logger logger = Logger.getLogger(TemplateCacheAuthService.class);
    //审核总表
    private Set<Long> templateSet=new HashSet<Long>();
 
    /**
     * 缓存的最大值
     */
    private long maxCount=100000;

    protected boolean  hasTemplateId(Long templateId){
        if(templateSet.contains(templateId)){
            return true;
        }else{
            if(templateSet.size()>=maxCount){
                realseCache();
            }
            logger.info("**********从数据库中获取ID为"+templateId+"的短信模板");
            ImsSmsSendAuth auth=querySmsSendAuthFromDb(templateId);
            if(null==auth){
                return false;
            }
//            templateSet.add(auth.getSmsCode());
            return true;
        }
    }

    protected ImsSmsSendAuth querySmsSendAuthFromDb(Long templateId){
        ImsSmsSendAuth sendAuth=new ImsSmsSendAuth();
        sendAuth.setSmsCode(templateId);
//        sendAuth.getQuery().addCondition(ImsSmsSendAuth.Field.validDate, Operator.LESS_EQUALS, new Date());
//        sendAuth.getQuery().addCondition(ImsSmsSendAuth.Field.expireDate, Operator.GREAT_EQUALS, new Date)
        List<ImsSmsSendAuth> list=null;
        try
        {
            list=  SendSpringUtil.getCommonDao().getClient().select(sendAuth);
        }
        catch (SQLException e)
        {
        	logger.error(e,e);
        }
        if(null==list||list.size()==0){
            return null;
        }
        return list.get(0);


    }
    /**
     * 释放掉一些缓存，暂时不实现
     */
    private void realseCache(){
        //       templateSet.clear();
    }







}
