package com.ailk.imssh.activity.handler;

import java.util.ArrayList;
import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.openbilling.persistence.itable.entity.IActivityAllot;

/**
 * @Description 处理活动信息
 * @author wangyh3
 * @Date 2012-5-14
 */
public class ActivityHandler extends BaseHandler
{
    
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        /*
        List<? extends DataObject> activityList = dataArr[0];
        List<? extends DataObject> allotList = dataArr[1];

        ActivityCmp activityCmp = this.getContext().getCmp(ActivityCmp.class);

        for (int i = 0; i < activityList.size(); i++)
        {
            IActivity iActivity = (IActivity) activityList.get(i);

            int busiType = iActivity.getBusiType();
            switch (busiType)
            {
            case EnumCodeExDefine.ACTIVITY_NOCHANGE: // 未变更
                break;
            case EnumCodeExDefine.ACTIVITY_CREATE: // 受理
                activityCmp.createActivity(iActivity, filteActivityAllots(allotList, iActivity.getProductId()));
                break;
            case EnumCodeExDefine.ACTIVITY_CHANGE: // 变更
                activityCmp.changeActivity(iActivity);
                break;
            case EnumCodeExDefine.ACTIVITY_ROLLBACK: // 回退
                activityCmp.rollbackActivity(iActivity);
                break;
            case EnumCodeExDefine.ACTIVITY_CEASE: // 中止
                activityCmp.ceaseActivity(iActivity);
                break;
            case EnumCodeExDefine.ACTIVITY_PAUSE: // 暂停
                activityCmp.pauseActivity(iActivity);
                break;
            case EnumCodeExDefine.ACTIVITY_RESUME: // 恢复
                activityCmp.resumeActivity(iActivity);
                break;
            default:
                break;
            }
        }
        */
    }

    /**
     * @Description 过滤指定活动的活动用户
     * @author wangyh3
     * @Date 2012-05-14
     */
    private List<IActivityAllot> filteActivityAllots(List<? extends DataObject> allotList, Long productId)
    {
        if (CommonUtil.isEmpty(allotList))
        {
            return null;
        }
        List<IActivityAllot> activityAllots = new ArrayList<IActivityAllot>();
        for (int i = 0; i < allotList.size(); i++)
        {
            IActivityAllot allot = (IActivityAllot) allotList.get(i);
            if (allot.getProductId().longValue() == productId.longValue())
                activityAllots.add(allot);
        }

        return activityAllots;
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
