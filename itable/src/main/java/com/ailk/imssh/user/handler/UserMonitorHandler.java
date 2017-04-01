package com.ailk.imssh.user.handler;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResourceMonitor;
import com.ailk.openbilling.persistence.itable.entity.IUserMonitor;

import jef.database.Condition.Operator;
import jef.database.DataObject;

/**
 * @Description:受控用户
 * @author zenglu
 * @Date 2012-5-22
 */
public class UserMonitorHandler extends BaseHandler
{
    
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        // TODO Auto-generated method stub
        
    }
    
    private RouterCmp baseCmp;

    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        baseCmp = context.getCmp(RouterCmp.class);

        List<? extends DataObject> dataList = dataArr[0];

        List<CmResourceMonitor> insertList = new ArrayList<CmResourceMonitor>();

        for (int i = 0; i < dataList.size(); i++)
        {
            IUserMonitor iUserMonitor = (IUserMonitor) dataList.get(i);
            Long acctId = baseCmp.queryAcctIdByUserId(iUserMonitor.getUserId());
            ITableUtil.setValue2ContextHolder(null, acctId, null);
            int operType = iUserMonitor.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                insertList.add(createResourceMonitor(iUserMonitor));
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                updateResourceMonitor(iUserMonitor);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                deleteResourceMonitor(iUserMonitor);
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
     * zenglu 2012-5-22 创建受控用户信息
     * 
     * @param iUserMonitor IUserMonitor
     * @return
     */
    private CmResourceMonitor createResourceMonitor(IUserMonitor iUserMonitor)
    {
        CmResourceMonitor cmResourceMonitor = new CmResourceMonitor();
        cmResourceMonitor.setSoDate(context.getCommitDate());// 新增时
        cmResourceMonitor.setResourceId(iUserMonitor.getUserId());
        cmResourceMonitor.setRegionCode(EnumCodeExDefine.REGION_CODE_DEFAULT);
        RouterCmp routCmp = context.getCmp(RouterCmp.class);
        RouteResult routeResult = routCmp.queryUserRouter(iUserMonitor.getUserId());
        if (routeResult == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, iUserMonitor.getUserId());
        }
       // cmResourceMonitor.setRegionCode(routeResult.getRouteDimension().getRegionCode());
        cmResourceMonitor.setServiceId(iUserMonitor.getServiceId());
        if(iUserMonitor.getPolicyId()!=null){
            cmResourceMonitor.setPolicyId(iUserMonitor.getPolicyId());
        }else{
            cmResourceMonitor.setPolicyId(0);
        }
        cmResourceMonitor.setMonitorType(iUserMonitor.getMonitorType());
        cmResourceMonitor.setValidDate(iUserMonitor.getValidDate());
        cmResourceMonitor.setExpireDate(iUserMonitor.getExpireDate());
        // cmResourceMonitor.setRemarks(iUserMonitor.getRemarks());
        cmResourceMonitor.setSoNbr(context.getSoNbr());
        return cmResourceMonitor;
    }

    /**
     * zenglu 2012-5-22 修改受控用户信息
     * 
     * @param iUserMonitor IUserMonitor
     * @return
     */
    private void updateResourceMonitor(IUserMonitor iUserMonitor)
    {	
        CmResourceMonitor cmResourceMonitor = new CmResourceMonitor();
        cmResourceMonitor.setSoDate(context.getCommitDate());// 新增时
        cmResourceMonitor.setRegionCode(EnumCodeExDefine.REGION_CODE_DEFAULT);
        RouterCmp routCmp = context.getCmp(RouterCmp.class);
        RouteResult routeResult = routCmp.queryUserRouter(iUserMonitor.getUserId());
        if (routeResult == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, iUserMonitor.getUserId());
        }
       // cmResourceMonitor.setRegionCode(routeResult.getRouteDimension().getRegionCode());
        if(iUserMonitor.getPolicyId()!=null){
            cmResourceMonitor.setPolicyId(iUserMonitor.getPolicyId());
        }else{
            cmResourceMonitor.setPolicyId(0);
        }
        cmResourceMonitor.setMonitorType(iUserMonitor.getMonitorType());
        cmResourceMonitor.setExpireDate(iUserMonitor.getExpireDate());
        // cmResourceMonitor.setRemarks(iUserMonitor.getRemarks());
        cmResourceMonitor.setSoNbr(context.getSoNbr());
        baseCmp.updateMode2(cmResourceMonitor,EnumCodeExDefine.OPER_TYPE_UPDATE,iUserMonitor.getValidDate(),
                new DBCondition(CmResourceMonitor.Field.resourceId, iUserMonitor.getUserId()),
                new DBCondition(CmResourceMonitor.Field.serviceId, iUserMonitor.getServiceId()));
       
        /**
    	baseCmp.deleteByCondition(CmResourceMonitor.class,  new DBCondition(CmResourceMonitor.Field.resourceId, iUserMonitor.getUserId()),
                new DBCondition(CmResourceMonitor.Field.serviceId, iUserMonitor.getServiceId()),
    			new DBCondition(CmResourceMonitor.Field.validDate,iUserMonitor.getValidDate()));
    	*/
    }

    /**
     * zenglu 2012-5-22 删除受控用户信息
     * 
     * @param iUserMonitor IUserMonitor
     * @return
     */
    private void deleteResourceMonitor(IUserMonitor iUserMonitor)
    {      
    	CmResourceMonitor cmResourceMonitor=new CmResourceMonitor();
    	cmResourceMonitor.setExpireDate(iUserMonitor.getExpireDate());
    	   baseCmp.updateMode2(cmResourceMonitor,EnumCodeExDefine.OPER_TYPE_DELETE,iUserMonitor.getValidDate(),
                   new DBCondition(CmResourceMonitor.Field.resourceId, iUserMonitor.getUserId()),
                   new DBCondition(CmResourceMonitor.Field.serviceId, iUserMonitor.getServiceId()));
          
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
		List<IUserMonitor> dataList = (List<IUserMonitor>)paramArr[0];
		if(CommonUtil.isEmpty(dataList)){
	           return;			
  		}
		baseCmp = context.getCmp(RouterCmp.class);
	    for (IUserMonitor iUserMonitor : dataList) {
	    	if(EnumCodeExDefine.OPER_TYPE_DIFF != iUserMonitor.getOperType()){
				continue;
			}
	    	baseCmp.checkUserRouter(iUserMonitor.getUserId());
	    		ITableUtil.setValue2ContextHolder(null, null, iUserMonitor.getUserId());
		    		baseCmp.deleteByConditionForDiff(CmResourceMonitor.class,
			                new DBCondition(CmResourceMonitor.Field.resourceId, iUserMonitor.getUserId()),
			                new DBCondition(CmResourceMonitor.Field.serviceId, iUserMonitor.getServiceId()));
		    	ITableUtil.setValue2ContextHolder(null, null, iUserMonitor.getUserId());
		    	createResourceMonitor(iUserMonitor);
		}	
	}
  }
