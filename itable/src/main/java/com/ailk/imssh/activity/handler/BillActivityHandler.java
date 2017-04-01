package com.ailk.imssh.activity.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.activity.cmp.BillActivityCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.itable.entity.IBillActivity;

/**
 * @Description 处理出帐活动信息
 * @author zenglu
 * @Date 2012-09-17
 */
public class BillActivityHandler extends BaseHandler 
{
    
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        // TODO Auto-generated method stub
        
    }
    
	@Override
	public void handle(List<? extends DataObject>... dataArr)
			throws IMSException 
	{
		List<? extends DataObject>	billActivityList = dataArr[0];
		
		BillActivityCmp cmp = this.getContext().getCmp(BillActivityCmp.class);
		for (int i = 0; i < billActivityList.size(); i++)
		{
			IBillActivity iBillActivity = (IBillActivity) billActivityList.get(i);
            ITableUtil.setValue2ContextHolder(null, null, iBillActivity.getUserId());
			int busiType = iBillActivity.getOperType();
			switch(busiType)
			{
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				cmp.createBillActivity(iBillActivity);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				cmp.deleteBillActivity(iBillActivity);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				cmp.updateBillActivity(iBillActivity);
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
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
