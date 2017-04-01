package com.ailk.imssh.prod.handler;

import java.util.ArrayList;
import java.util.List;

import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.easyframe.route.service.RouteNotFoundException;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareProd;
import com.ailk.openbilling.persistence.itable.entity.IUserShareProd;

/**
 * @Description 4G免费资源共享
 * @author lijc3
 * @Date 2013-12-13
 */
public class UserShareProdHandler extends BaseHandler
{

	private RouterCmp baseCmp;

    @Override
    public void handle(List<? extends DataObject>... dataArr) throws IMSException
    {
        if (CommonUtil.isEmpty(dataArr))
        {
            return;
        }
        baseCmp = context.getCmp(RouterCmp.class);
        List<IUserShareProd> shareProdList = (List<IUserShareProd>) dataArr[0];
        
        for (IUserShareProd shareProd : shareProdList)
        {
        	//广西特殊处理，营业同步的是0，但是插入库中为-1
        	if(shareProd.getServiceId() == 0) {
        		shareProd.setServiceId(-1);
        	}
        	
        	//4g免费资源共享按照群组编号分表
            ITableUtil.setValue2ContextHolder(null, null, shareProd.getUserId());
           
            int operType = shareProd.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                createCmUserShareProd(shareProd);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                updateCmUserShareProd(shareProd);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
            	CmUserShareProd prod = new CmUserShareProd();
            	prod.setExpireDate(shareProd.getExpireDate());
            	 baseCmp.updateMode2(prod,EnumCodeExDefine.OPER_TYPE_DELETE,shareProd.getValidDate(),
               		  new DBCondition(CmUserShareProd.Field.resourceId, shareProd.getUserId()),
                         new DBCondition( CmUserShareProd.Field.shareId, shareProd.getShareId()));
                break;
            default:
                break;
            }
        }
        
    }

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {

    }

    private void createCmUserShareProd(IUserShareProd shareProd)
    {
        CmUserShareProd prod = new CmUserShareProd();
        prod.setShareId(shareProd.getShareId());
        prod.setCreateDate(context.getCommitDate());
        prod.setExpireDate(shareProd.getExpireDate());
        prod.setValidDate(shareProd.getValidDate());
        prod.setProductId(shareProd.getProductId());
        prod.setResourceId(shareProd.getUserId());
        prod.setRegionCode(shareProd.getRegionCode());
        prod.setSoDate(context.getCommitDate());
        prod.setSoNbr(context.getSoNbr());
        prod.setServiceId(shareProd.getServiceId());
 
        baseCmp.insert(prod);
    }

    private void updateCmUserShareProd(IUserShareProd shareProd)
    {
    	  CmUserShareProd prod = new CmUserShareProd();
          prod.setCreateDate(context.getCommitDate());
          prod.setExpireDate(shareProd.getExpireDate());
          prod.setProductId(shareProd.getProductId());
          prod.setRegionCode(shareProd.getRegionCode());
          prod.setSoDate(context.getCommitDate());
          prod.setSoNbr(context.getSoNbr());
          prod.setServiceId(shareProd.getServiceId());
          baseCmp.updateMode2(prod,EnumCodeExDefine.OPER_TYPE_UPDATE,shareProd.getValidDate(),
        		  new DBCondition(CmUserShareProd.Field.resourceId, shareProd.getUserId()),
                  new DBCondition( CmUserShareProd.Field.shareId, shareProd.getShareId()));
       
    }
    

    /**
     * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<? extends jef.database.DataObject>[])
     */
    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    	/*
        List<? extends DataObject> dataList = dataArr[0];
        for (int i = 0; i < dataList.size(); i++) {

            IUserShareProd userShareProd = (IUserShareProd) dataList.get(i);
            long tempAccId = ITableUtil.convertGroupId(userShareProd.getGroupId());
            userShareProd.setGroupId(tempAccId);
        }
        */
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		if (CommonUtil.isEmpty(paramArr))
        {
            return;
        }
		baseCmp = context.getCmp(RouterCmp.class);
        List<IUserShareProd> shareProdList = (List<IUserShareProd>) paramArr[0];
        for (IUserShareProd iUserShareProd : shareProdList) {
        	if(EnumCodeExDefine.OPER_TYPE_DIFF != iUserShareProd.getOperType()){
				continue;
			}
        	    baseCmp.checkUserRouter(iUserShareProd.getUserId());
        		ITableUtil.setValue2ContextHolder(null, null, iUserShareProd.getUserId());
            		baseCmp.deleteByConditionForDiff(CmUserShareProd.class,
                            new DBCondition(CmUserShareProd.Field.resourceId, iUserShareProd.getUserId()),  new DBCondition(
                                    CmUserShareProd.Field.serviceId, iUserShareProd.getServiceId()), new DBCondition(
                                    CmUserShareProd.Field.productId, iUserShareProd.getProductId()));
            	ITableUtil.setValue2ContextHolder(null, null, iUserShareProd.getUserId());
            	createCmUserShareProd(iUserShareProd);
		}
	}
 }
