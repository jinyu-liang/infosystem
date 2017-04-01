package com.ailk.imssh.user.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.easyframe.route.service.RouteNotFoundException;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserParamCmp;
import com.ailk.openbilling.persistence.cust.entity.CmUserParam;
import com.ailk.openbilling.persistence.itable.entity.IUserParam;

public class UserParamHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		UserParamCmp paramCmp = context.getCmp(UserParamCmp.class);
		RouterCmp routeCmp = context.getCmp(RouterCmp.class);

		List<? extends DataObject> dataList = dataArr[0];
		for (int j = 0; j < dataList.size(); j++)
        {

            IUserParam param = (IUserParam) dataList.get(j);
            Long acctId=routeCmp.queryAcctIdByUserId(param.getUserId());
            ITableUtil.setValue2ContextHolder(null, acctId,null);
            switch (param.getOperType())
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                paramCmp.createUserParam(param);
                break;

            case EnumCodeExDefine.OPER_TYPE_UPDATE:
            	paramCmp.modifyUserParam(param);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
            	paramCmp.deleteUserParam(param);
            	break;
            default:
                imsLogger.error("I表操作类型不正确。OPER_TYPE:" + String.valueOf(param.getOperType()));
                break;
            }
        }
	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) throws IMSException{
		List<IUserParam> dataList = (List<IUserParam>)paramArr[0];
		if(CommonUtil.isEmpty(dataList)){
	        return;			
		}
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		UserParamCmp paramCmp = context.getCmp(UserParamCmp.class);
		for (IUserParam iUserParam : dataList) {
			if(EnumCodeExDefine.OPER_TYPE_DIFF != iUserParam.getOperType()){
				continue;
			}
			  // 增加分表参数
			baseCmp.checkUserRouter(iUserParam.getUserId());
            ITableUtil.setValue2ContextHolder(null, null, iUserParam.getUserId());
		            paramCmp.deleteByConditionForDiff(CmUserParam.class, new DBCondition(CmUserParam.Field.resourceId, iUserParam.getUserId()), 
							new DBCondition(CmUserParam.Field.paramId, iUserParam.getParamId()));
		      ITableUtil.setValue2ContextHolder(null, null, iUserParam.getUserId());
			  paramCmp.createUserParam(iUserParam);
	     }
	  }
   }
