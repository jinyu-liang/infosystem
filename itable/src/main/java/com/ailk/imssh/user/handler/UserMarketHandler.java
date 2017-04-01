/**
 * 
 */
package com.ailk.imssh.user.handler;

import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserMarketCmp;
import com.ailk.openbilling.persistence.itable.entity.IUserMarket;

/**
 * @Description: 数据业务超时处理Handler
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author songcc
 * @Date 2014-1-13
 */
public class UserMarketHandler extends BaseHandler
{

    @Override
    public void handle(List<? extends DataObject>... dataArr) throws IMSException
    {

        UserMarketCmp userMarketCmp = this.getContext().getCmp(UserMarketCmp.class);
        List<? extends DataObject> dataList = dataArr[0];
        for (int i = 0; i < dataList.size(); i++)
        {

            IUserMarket iUserMarket = (IUserMarket) dataList.get(i);
            ITableUtil.setValue2ContextHolder(null, null, iUserMarket.getUserId());
            int operType = iUserMarket.getOperType();

            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                userMarketCmp.createUserMarket(iUserMarket);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                userMarketCmp.updateUserMarket(iUserMarket);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                userMarketCmp.deleteUserMarket(iUserMarket);
                break;
            default:
                break;
            }
        }

    }

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        // TODO Auto-generated method stub

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
