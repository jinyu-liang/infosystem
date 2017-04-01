package com.ailk.imssh.user.handler;

import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserSubServiceCmp;
import com.ailk.openbilling.persistence.itable.entity.IUserSubService;

/**
 * 个性化门限业务 资料同步处理
 * 
 * @author chexd
 * @date 2014-03-17
 */
public class UserSubServiceHandler extends BaseHandler
{

    @Override
    public void handle(List<? extends DataObject>... dataArr) throws IMSException
    {
        List<? extends DataObject> dataList = dataArr[0];
        UserSubServiceCmp userSubServiceCmp = this.getContext().getCmp(UserSubServiceCmp.class);
        for (int j = 0; j < dataList.size(); j++)
        {

            IUserSubService iUserSubService = (IUserSubService) dataList.get(j);
            //增加分表参数
            ITableUtil.setValue2ContextHolder(null, iUserSubService.getAcctId(), null);
            switch (iUserSubService.getOperType())
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                userSubServiceCmp.addUserSubService(iUserSubService);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                userSubServiceCmp.updateUserSubService(iUserSubService);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                userSubServiceCmp.deleteUserSubService(iUserSubService);
                break;
            default:
                imsLogger.error("I表操作类型不正确。OPER_TYPE:" + String.valueOf(iUserSubService.getOperType()));
                break;
            }
        }
    }

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

	@Override
	public void initData(
			List<? extends DataObject>... dataArr) throws IMSException {

	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
