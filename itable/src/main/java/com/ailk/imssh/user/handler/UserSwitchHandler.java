package com.ailk.imssh.user.handler;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserSwitchCmp;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.itable.entity.IUserSwitch;

import jef.database.DataObject;

/**
 * @Description 用户服务开关
 * @author lijc3
 * @Date 2012-7-27
 */
public class UserSwitchHandler extends BaseHandler
{
    
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        // TODO Auto-generated method stub
        
    }
    
    
    @Override
    public void handle(List<? extends DataObject>... dataArr) throws IMSException
    {
        UserSwitchCmp userCmp = context.getCmp(UserSwitchCmp.class);
        List<IUserSwitch> switchList = (List<IUserSwitch>) dataArr[0];
        RouterCmp routerCmp=context.getCmp(RouterCmp.class);
        for (IUserSwitch userSwitch : switchList)
        {   
        	long acctId=routerCmp.queryAcctIdByUserId(userSwitch.getUserId());
            ITableUtil.setValue2ContextHolder(null, acctId, null);
            int operType = userSwitch.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                userCmp.createUserSwitch(userSwitch);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                userCmp.updateUserSwitch(userSwitch);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                userCmp.deleteUserSwitch(userSwitch);
                break;

            default:
                break;
            }
        }
    }


	@Override
	public void initData(
			List<? extends DataObject>... dataArr) throws IMSException {

	}


	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr)  throws IMSException{
		 List<IUserSwitch> switchList = (List<IUserSwitch>) paramArr[0];
		 if(switchList.isEmpty()){
			 return;
		 } 
		 RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		 for (IUserSwitch iUserSwitch : switchList) {
			 if(EnumCodeExDefine.OPER_TYPE_DIFF != iUserSwitch.getOperType()){
					continue;
			 }
			    UserSwitchCmp  userSwitchCmp =  context.getCmp(UserSwitchCmp.class);
				List<Integer> offerIdList = userSwitchCmp.queryUserSwitchOfferList(iUserSwitch.getServiceCode(), iUserSwitch.getStatus());
				if (CommonUtil.isEmpty(offerIdList)) {
					imsLogger.info("*****************no offerId is by service_code = ", iUserSwitch.getServiceCode(), 
							"and status = ",iUserSwitch.getStatus());
					return;
				}
			    baseCmp.checkUserRouter(iUserSwitch.getUserId());
				ITableUtil.setValue2ContextHolder(null, null, iUserSwitch.getUserId());
					//按objectId删除
				userSwitchCmp.deleteByConditionForDiff(CoProd.class, new DBCondition(CoProd.Field.objectId, iUserSwitch.getUserId()));
				ITableUtil.setValue2ContextHolder(null, null, iUserSwitch.getUserId());
				userSwitchCmp.createUserSwitch(iUserSwitch);
		      }
	     }
		}
