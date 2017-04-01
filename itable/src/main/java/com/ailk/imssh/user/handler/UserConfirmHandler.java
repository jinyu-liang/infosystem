package com.ailk.imssh.user.handler;

import java.util.ArrayList;
import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrderConfirm;
import com.ailk.openbilling.persistence.itable.entity.IUserConfirm;

/**
 * @Description:二次扣费确认表
 * @author lijc3
 * @Date 2012-5-18
 * @Date 2012-11-08 wukl 当mms业务时，不会有ThirdMsisdn，所以置为默认值0
 */
public class UserConfirmHandler extends BaseHandler
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
        List<IUserConfirm> confirmList = (List<IUserConfirm>) dataArr[0];
        List<CmUserOrderConfirm> insertList = new ArrayList<CmUserOrderConfirm>();
        for (IUserConfirm confirm : confirmList)
        {
            ITableUtil.setValue2ContextHolder(null, null, confirm.getUserId());
            int operType = confirm.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:{
            	CmUserOrderConfirm cmUserOrderConfirm =createUserOrderConfirm(confirm);
            	cmUserOrderConfirm.setValidDate(confirm.getValidDate());
            	insertList.add(cmUserOrderConfirm);
            	}
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                updateUserOrderConfirm(confirm);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                deleteUserOrderConfirm(confirm);
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
     * lijc3 2012-5-19 创建CM_USER_ORDER_CONFIRM
     * 
     * @param confirm
     * @return
     */
    private CmUserOrderConfirm createUserOrderConfirm(IUserConfirm confirm)
    {
        CmUserOrderConfirm cmUserOrderConfirm = new CmUserOrderConfirm();
        cmUserOrderConfirm.setSoDate(confirm.getSoDate());
        cmUserOrderConfirm.setResourceId(confirm.getUserId());
        cmUserOrderConfirm.setBusiType(confirm.getBusiType());
        cmUserOrderConfirm.setIdentity(confirm.getIdentity());
        if (confirm.getServType() != null)
        {
            cmUserOrderConfirm.setServType(confirm.getServType());
        }
        else
        {
            cmUserOrderConfirm.setServType(0);
        }
        cmUserOrderConfirm.setBillFlag(confirm.getBillFlag());
        if (confirm.getBillProp() != null)
        {
            cmUserOrderConfirm.setBillProp(confirm.getBillProp());
        }
        else
        {
            cmUserOrderConfirm.setBillProp(0);
        }
        cmUserOrderConfirm.setConfirmResult(confirm.getConfirmResult());
        cmUserOrderConfirm.setConfirmTime(confirm.getConfirmTime());
        cmUserOrderConfirm.setAlarmTime(confirm.getAlarmTime());
        if(CommonUtil.isNotEmpty(confirm.getSequenceNo())){
        	cmUserOrderConfirm.setSequenceNo(confirm.getSequenceNo());
        }else{
        	cmUserOrderConfirm.setSequenceNo("0");
        }
        cmUserOrderConfirm.setSrcType(confirm.getSrcType());
        cmUserOrderConfirm.setAlarmDoneCode(confirm.getAlarmDoneCode());
        cmUserOrderConfirm.setExtendFlag(confirm.getExtendFlag());
        if (CommonUtil.isNotEmpty(confirm.getSpCode()))
        {
            cmUserOrderConfirm.setSpCode(confirm.getSpCode());
        }
        else
        {
            cmUserOrderConfirm.setSpCode("0");
        }
        cmUserOrderConfirm.setOperatorCode(confirm.getOperatorCode());
        // @Date 2012-11-08 wukl 当mms业务时，不会有ThirdMsisdn，所以置为默认值0
        if(CommonUtil.isEmpty(confirm.getThirdMsisdn())){
            cmUserOrderConfirm.setThirdMsisdn("0");
        }else{
            cmUserOrderConfirm.setThirdMsisdn(confirm.getThirdMsisdn());
        }
        cmUserOrderConfirm.setRegionCode(confirm.getRegionCode());
        
        cmUserOrderConfirm.setExpireDate(confirm.getExpireDate());
        cmUserOrderConfirm.setSoNbr(context.getSoNbr());
        cmUserOrderConfirm.setServiceCode(confirm.getServiceCode());
        if (CommonUtil.isNotEmpty(confirm.getRemark()))
        {
            cmUserOrderConfirm.setRemark(confirm.getRemark());
        }
        return cmUserOrderConfirm;
    }

    /**
     * lijc3 2012-5-18 更新二次扣费确认信息
     * 
     * @param prise IUserEnterprise
     */
    private void updateUserOrderConfirm(IUserConfirm confirm)
    {
        CmUserOrderConfirm cmConfirm = createUserOrderConfirm(confirm);
        baseCmp.updateByCondition(cmConfirm, new DBCondition(CmUserOrderConfirm.Field.resourceId, confirm.getUserId()),
        		new DBCondition(CmUserOrderConfirm.Field.serviceCode, confirm.getServiceCode()),
        		new DBCondition(CmUserOrderConfirm.Field.servType, confirm.getServType()),
        		new DBCondition(CmUserOrderConfirm.Field.operatorCode, confirm.getOperatorCode()),
        		new DBCondition(CmUserOrderConfirm.Field.busiType, confirm.getBusiType()));
    }

    /**
     * lijc3 2012-5-18 删除二次扣费确认信息
     * 
     * @param prise IUserEnterprise
     */
    private void deleteUserOrderConfirm(IUserConfirm confirm)
    {
        CmUserOrderConfirm firm = new CmUserOrderConfirm();
        firm.setExpireDate(confirm.getExpireDate());
        baseCmp.updateByCondition(firm, new DBCondition(CmUserOrderConfirm.Field.resourceId, confirm.getUserId()),
        		new DBCondition(CmUserOrderConfirm.Field.serviceCode, confirm.getServiceCode()),
        		new DBCondition(CmUserOrderConfirm.Field.servType, confirm.getServType()),
        		new DBCondition(CmUserOrderConfirm.Field.operatorCode, confirm.getOperatorCode()),
        		new DBCondition(CmUserOrderConfirm.Field.busiType, confirm.getBusiType()));
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
		// TODO Auto-generated method stub
		
	}

}
