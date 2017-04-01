package com.ailk.imssh.cust.handler;

import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.cust.cmp.CustCmp;
import com.ailk.openbilling.persistence.itable.entity.ICustomer;

/**
 * @Description:该类用来处理客户接口表的数据，保存到对应的数据库表
 * @author wangjt
 * @Date 2012-5-11
 */
public class CustHandler extends BaseHandler
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

        CustCmp custCmp = this.getContext().getCmp(CustCmp.class);
        for (int j = 0; j < dataList.size(); j++)
        {
            ICustomer iCustomer = (ICustomer) dataList.get(j);
            ITableUtil.setValue2ContextHolder(iCustomer.getCustId(), null, null);
            if (iCustomer.getOperType() == EnumCodeExDefine.OPER_TYPE_DELETE)
            {
                custCmp.deleteCust(iCustomer);
            }
            else
            {
            	if (iCustomer.getOperType() == EnumCodeExDefine.OPER_TYPE_UPDATE && custCmp.isOnlyUpdateCustSegment()) {
					custCmp.updateCmCustomerCustSegment(iCustomer);
				} else {
					custCmp.createOrModifyCust(iCustomer);
				}
            }
        }
    }

	@Override
	public void initData(
			List<? extends DataObject>... dataArr) throws IMSException {

	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		//发布路由不处理
       
	}
}
