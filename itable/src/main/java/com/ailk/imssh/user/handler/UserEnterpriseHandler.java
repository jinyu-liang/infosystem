package com.ailk.imssh.user.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.itable.entity.IUserEnterprise;

/**
 * @Description:行业网关接口数据表操作
 * @author lijc3
 * @Date 2012-5-11
 */
public class UserEnterpriseHandler extends BaseHandler
{
    
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        // TODO Auto-generated method stub
        
    }
    
    
    private BaseCmp baseCmp;

    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        baseCmp = context.getCmp(BaseCmp.class);
        List<IUserEnterprise> priseList = (List<IUserEnterprise>) dataArr[0];
        List<CmUserEnterprise> insertList = new ArrayList<CmUserEnterprise>();
        RouterCmp cmp=this.context.getCmp(RouterCmp.class);
        for (IUserEnterprise prise : priseList)
        {   
        	Long acctId=cmp.queryAcctIdByUserId(prise.getUserId());
            ITableUtil.setValue2ContextHolder(null, acctId, null);
            int operType = prise.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                insertList.add(createUserEnterprice(prise,true));
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                updateUserEnterprice(prise);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                deleteUserEnterprice(prise);
                break;
            default:
                break;
            }
        }
        if (CommonUtil.isNotEmpty(insertList))
        {
            baseCmp.insertBatch(insertList);
        }
    }

    /**
     * lijc3 2012-5-18 创建行业网关信息
     * @param prise IUserEnterprise
     * @return
     */
    private CmUserEnterprise createUserEnterprice(IUserEnterprise prise,boolean flag)
    {

        
        CmUserEnterprise cmUserEnterprise = new CmUserEnterprise();
        cmUserEnterprise.setSoDate(context.getCommitDate());
        cmUserEnterprise.setRegionCode(prise.getRegionCode());

        cmUserEnterprise.setBusiType(prise.getBusiType());
        if(prise.getUnbalancedFlag()!=null){
            cmUserEnterprise.setUnbalancedFlag(prise.getUnbalancedFlag());
        }else{
            cmUserEnterprise.setUnbalancedFlag(0);//default
        }
        cmUserEnterprise.setReplaceFlag(prise.getReplaceFlag());
        cmUserEnterprise.setExpireDate(prise.getExpireDate());
        cmUserEnterprise.setSoNbr(CommonUtil.long2String(context.getSoNbr()));
        cmUserEnterprise.setProperty(prise.getProperty());

        if(flag){
            cmUserEnterprise.setValidDate(prise.getValidDate());

            cmUserEnterprise.setResourceId(prise.getUserId());
            cmUserEnterprise.setServiceCode(prise.getServiceCode());
            cmUserEnterprise.setOperatorCode(prise.getOperatorCode());


        }
        if(prise.getSpCode() != null){
            cmUserEnterprise.setSpCode(prise.getSpCode());
            context.getCmp(RouterCmp.class).createEnterpriseRoute(cmUserEnterprise.getResourceId(),cmUserEnterprise.getServiceCode(),cmUserEnterprise.getOperatorCode(),prise.getSpCode(),cmUserEnterprise.getValidDate(),cmUserEnterprise.getExpireDate());
        }else{
        	context.getCmp(RouterCmp.class).createEnterpriseRoute(cmUserEnterprise.getResourceId(),cmUserEnterprise.getServiceCode(),cmUserEnterprise.getOperatorCode(),Long.valueOf(0),cmUserEnterprise.getValidDate(),cmUserEnterprise.getExpireDate());
            cmUserEnterprise.setSpCode(0L);//default
        }
        
        return cmUserEnterprise;
    }

    /**
     * lijc3 2012-5-18 更新行业网关信息
     * @param prise IUserEnterprise
     */
    private void updateUserEnterprice(IUserEnterprise prise){	
        CmUserEnterprise updatePrise = createUserEnterprice(prise,true);
        //不设置VALID_DATE
    	baseCmp.updateMode2(updatePrise,EnumCodeExDefine.OPER_TYPE_UPDATE,prise.getValidDate(),
    			new DBCondition(CmUserEnterprise.Field.resourceId, prise.getUserId()),
                new DBCondition(CmUserEnterprise.Field.serviceCode, prise.getServiceCode()),
                new DBCondition(CmUserEnterprise.Field.operatorCode, prise.getOperatorCode()));
    }

    /**
     * lijc3 2012-5-18 删除行业网关信息
     * @param prise IUserEnterprise
     */
    private void deleteUserEnterprice(IUserEnterprise prise){	
    	CmUserEnterprise updatePrise=new CmUserEnterprise();
    	updatePrise.setExpireDate(prise.getExpireDate());
    	baseCmp.updateMode2(updatePrise,EnumCodeExDefine.OPER_TYPE_DELETE,prise.getValidDate(),
    			new DBCondition(CmUserEnterprise.Field.resourceId, prise.getUserId()),
                new DBCondition(CmUserEnterprise.Field.serviceCode, prise.getServiceCode()),
                new DBCondition(CmUserEnterprise.Field.operatorCode, prise.getOperatorCode()));
        if(prise.getSpCode() != null){
        	context.getCmp(RouterCmp.class).deleteEnterpriseRoute(prise.getUserId(),prise.getServiceCode(),prise.getOperatorCode(),prise.getSpCode(),prise.getExpireDate());
        }else{
        	context.getCmp(RouterCmp.class).deleteEnterpriseRoute(prise.getUserId(),prise.getServiceCode(),prise.getOperatorCode(),null,prise.getExpireDate());
        } 
    }


    /**
     * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<? extends jef.database.DataObject>[])
     */
    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		//发布路由不处理
		imsLogger.info("****************************");
	}
}
