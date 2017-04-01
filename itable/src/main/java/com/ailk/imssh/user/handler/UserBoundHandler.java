package com.ailk.imssh.user.handler;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserCmp;
import com.ailk.openbilling.persistence.cust.entity.CmIdentityVsImei;
import com.ailk.openbilling.persistence.itable.entity.IIdentityBound;

import jef.database.DataObject;

/**
 * 用户终端资料表<br>
 * 只有终端捆绑类业务才有数据
 * 
 * @author wukl
 * @Date 2012-5-11
 */
public class UserBoundHandler extends BaseHandler
{
    
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        List<? extends DataObject> dataList = dataArr[0];

        UserCmp userCmp = this.getContext().getCmp(UserCmp.class);
        for (int j = 0; j < dataList.size(); j++)
        {
            IIdentityBound iIdentityBound = (IIdentityBound) dataList.get(j);
            ITableUtil.setValue2ContextHolder(null, null, iIdentityBound.getUserId());
            int operType = iIdentityBound.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                userCmp.createUserBound(iIdentityBound);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                userCmp.modifyUserBound(iIdentityBound);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                userCmp.deleteUserBound(iIdentityBound);
                break;

            default:
                break;
            }
        }

    }

    /**
     * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<? extends jef.database.DataObject>[])
     */
    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

	/* (non-Javadoc)
	 * @see com.ailk.imssh.common.handler.BaseHandler#handleDiffException(java.util.List[])
	 */
	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
        List<IIdentityBound> dataList = (List<IIdentityBound>) paramArr[0];
        if (CommonUtil.isEmpty(dataList)) {
			return;
		}
        RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
        UserCmp userCmp = this.getContext().getCmp(UserCmp.class);
        for (IIdentityBound iIdentityBound : dataList) {
			if(EnumCodeExDefine.OPER_TYPE_DIFF != iIdentityBound.getOperType()){
				continue;
			} 
			baseCmp.checkUserRouter(iIdentityBound.getUserId());
			ITableUtil.setValue2ContextHolder(null, null, iIdentityBound.getUserId());
				userCmp.deleteByConditionForDiff(CmIdentityVsImei.class,
				            new DBCondition(CmIdentityVsImei.Field.resourceId, iIdentityBound.getUserId()));
			ITableUtil.setValue2ContextHolder(null, null, iIdentityBound.getUserId());
			userCmp.createUserBound(iIdentityBound);
		}
	}

}
