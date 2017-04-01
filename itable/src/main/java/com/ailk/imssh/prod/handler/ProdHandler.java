package com.ailk.imssh.prod.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.ims.util.ThreadUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.SpecCodeExDefine;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.prod.cmp.ProdExCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdBundleComposite;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdMapping;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOper;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOperList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParamList;
import com.ailk.openbilling.persistence.itable.entity.IProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IProdPriceParam;
import com.ailk.openbilling.persistence.itable.entity.IProduct;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

/**
 * @Description:产品相关
 * @author lijc3
 * @Date 2012-5-11
 * @Date 2012-9-20 处理了服务
 */
public class ProdHandler extends BaseHandler
{
	  private ProdExCmp prodCmp;

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        List<IProduct> prodList = (List<IProduct>) dataArr[0];
        if (CommonUtil.isEmpty(prodList))
        {
            return;
        }
        Date validDate = DateUtils.monthBegin(context.getCommitDate());
        for (IProduct prod : prodList)
        {
            if (prod.getValidDate().before(validDate))
            {
                prod.setValidDate(validDate);
            }
        }
        /*
        List<IProdCharValue> charValueList = null;
        List<IProdPriceParam> priceParamList = null;
        if (dataArr[1] != null)
        {
            charValueList = (List<IProdCharValue>) dataArr[1];
            for (IProdCharValue value : charValueList)
            {
                if (value.getValidDate().before(validDate))
                {
                    value.setValidDate(validDate);
                }
            }
        }
        if (dataArr[2] != null)
        {
            priceParamList = (List<IProdPriceParam>) dataArr[2];
            for (IProdPriceParam param : priceParamList)
            {
                if (param.getValidDate().before(validDate))
                {
                    param.setValidDate(validDate);
                }
            }
        }
*/
        prodCmp = getContext().getCmp(ProdExCmp.class);
        Long userId = context.getUserId();
        Long acctId = context.getAcctId();
        Long objectId = null;
        Integer objectType = null;
        if (userId != null)
        {
            objectId = userId;
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        }
        else
        {
            objectId = acctId;
            CaAccount dmAccount = context.get3hTree().loadAcct3hBean(acctId).getAccount();
            if (dmAccount != null)
            {
                Integer accountClass = dmAccount.getAccountClass();
                if (accountClass.shortValue() == EnumCodeDefine.ACCOUNT_CLASS_GCA)
                {
                    objectType = EnumCodeDefine.PROD_OBJECTTYPE_GCA;// 使用类型为虚账户
                }
                else if (accountClass.shortValue() == EnumCodeDefine.ACCOUNT_CLASS_CA)
                {
                    objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;// 使用类型为账户
                }
            }
            else
            {
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
            }
        }
        ITableUtil.setValue2ContextHolder(context.getCustId(), context.getAcctId(), context.getUserId());
        //2013-06-08 liming15 对特定的销售品（11000001）全量同步时不删除,最后一个条件
        List<CoProd> list = prodCmp.deleteByCondition_noInsert(CoProd.class, validDate, new DBCondition(CoProd.Field.objectId,
                objectId), new DBCondition(CoProd.Field.objectType, objectType), new DBCondition(CoProd.Field.expireDate,
                validDate, Operator.GREAT), new DBCondition(CoProd.Field.busiFlag, SpecCodeExDefine.NO_DELETE_PRODUCT,
                Operator.NOT_IN),new DBCondition(CoProd.Field.productOfferingId,11000001,Operator.NOT_EQUALS));
        
         prodCmp.deleteByCondition_noInsert(CoProdMapping.class, validDate, new DBCondition(CoProdMapping.Field.objectId,
                objectId), new DBCondition(CoProdMapping.Field.objectType, objectType), new DBCondition(CoProdMapping.Field.expireDate,
                validDate, Operator.GREAT));
        if (CommonUtil.isNotEmpty(list))
        {
            CoProd value=new CoProd();
            value.setProdExpireDate(validDate);
            //更新失效时间
            prodCmp.updateDirectByCondition(value, new DBCondition(CoProd.Field.objectId,
                objectId), new DBCondition(CoProd.Field.objectType, objectType), new DBCondition(CoProd.Field.expireDate,
                validDate, Operator.GREAT), new DBCondition(CoProd.Field.busiFlag, SpecCodeExDefine.NO_DELETE_PRODUCT,
                Operator.NOT_IN));
            Set<Long> set = new HashSet<Long>();
            for (CoProd p : list)
            {
                set.add(p.getProductId());
            }
            prodCmp.deleteByCondition_noInsert(CoProdPriceParam.class, validDate,  new DBCondition(CoProdPriceParam.Field.productId, set,
                    Operator.IN),new DBCondition(
                            CoProdPriceParam.Field.objectId, objectId), new DBCondition(CoProdPriceParam.Field.objectType, objectType), new DBCondition(
                    CoProdPriceParam.Field.expireDate, validDate, Operator.GREAT));
            prodCmp.deleteByCondition_noInsert(CoProdCharValue.class, validDate, new DBCondition(CoProdCharValue.Field.productId,
                    set, Operator.IN), new DBCondition(CoProdCharValue.Field.objectId, objectId), new DBCondition(
                    CoProdCharValue.Field.objectType, objectType), new DBCondition(CoProdCharValue.Field.expireDate, validDate,
                    Operator.GREAT));
            prodCmp.deleteByCondition_noInsert(CoBudgetCharValue.class, validDate, new DBCondition(
                    CoBudgetCharValue.Field.productId, set, Operator.IN), new DBCondition(CoBudgetCharValue.Field.objectId,
                    objectId), new DBCondition(CoBudgetCharValue.Field.objectType, objectType), new DBCondition(
                    CoBudgetCharValue.Field.expireDate, validDate, Operator.GREAT));
        }
        //删除生效时间和失效时间相等的数据，不上发
        imsLogger.info("*****sync all product,delete products where  valid_date equals expire_date");
        DBUtil.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.objectId, objectId),
                new DBCondition(CoProd.Field.objectType, objectType),
                new DBCondition(CoProd.Field.validDate, validDate),
                new DBCondition(CoProd.Field.expireDate, validDate));
        
    }



    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        prodCmp = getContext().getCmp(ProdExCmp.class);
        /**
         * 对IProduct中的acctId进行修改，如果acctId来源于groupId 则做加100处理
         * @author songcc
         * @Date 2014-1-15
         */
        List<IProduct> prodList = (List<IProduct>) dataArr[0];
       
        //20130507 ljc 传入的修改操作，如果生效时间和失效时间相等，则统统认为是删除
        if (CommonUtil.isNotEmpty(prodList))
        {
            for (IProduct prod : prodList)
            {	
                if (prod.getValidDate().equals(prod.getExpireDate()) && prod.getOperType() == EnumCodeExDefine.OPER_TYPE_UPDATE)
                {
                    prod.setOperType(EnumCodeExDefine.OPER_TYPE_DELETE);
                }
            }
            //广西特殊的busi_code特殊处理
            if(context.getBusiCode().intValue() == SysUtil.getInt(ConstantExDefine.GX_MANUAL_MODIFY_PROD, EnumCodeExDefine.GX_MANUAL_MODIFY_PROD)){
            	for(IProduct prod : prodList){
            		ITableUtil.setValue2ContextHolder(null, prod.getAcctId(), prod.getUserId());
            		prodCmp.deleteByCondition_noInsert(CoProd.class, prod.getValidDate(),  new DBCondition(CoProd.Field.productId,prod.getProductId()),
            				new DBCondition(CoProd.Field.expireDate,prod.getValidDate(),Operator.GREAT_EQUALS));
            		DBUtil.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, prod.getProductId()),
                            new DBCondition(CoProd.Field.validDate, prod.getValidDate()),
                            new DBCondition(CoProd.Field.expireDate, prod.getValidDate()));
            		prod.setOperType(EnumCodeExDefine.OPER_TYPE_INSERT);
            	}
            }
        }
        /*
        List<IProdCharValue> charValueList = null;
        List<IProdPriceParam> priceParamList = null;
        if (dataArr[1] != null)
        {
            charValueList = (List<IProdCharValue>) dataArr[1];
        }
        if (dataArr[2] != null)
        {
            priceParamList = (List<IProdPriceParam>) dataArr[2];
        }
        */
        prodCmp.operateProduct(prodList, null, null);
        // 清除线程
        ThreadUtil.removeThreadLocal();
    }

    /*
     * 包装oder_oper_list
     */
    public SProductOrderOperList packageProductOrderOperList(List<IProduct> prodList, List<IProdCharValue> charValueList,
            List<IProdPriceParam> priceParamList)
    {
        if (CommonUtil.isEmpty(prodList))
        {
            imsLogger.debug("******************IProductList is null,return");
            return null;
        }
        List<Long> allProdIds = new ArrayList<Long>();
        List<SProductOrder> addList = new ArrayList<SProductOrder>();
        List<SProductOrder> delList = new ArrayList<SProductOrder>();
        List<SProductOrder> chgList = new ArrayList<SProductOrder>();
        for (IProduct prod : prodList)
        {
            allProdIds.add(prod.getProductId());
            int operType = prod.getOperType();
            SProductOrder order = packageSProductOrder(prod, charValueList, priceParamList);
            if (operType == EnumCodeExDefine.OPER_TYPE_INSERT)
            {
                addList.add(order);
            }
            else if (operType == EnumCodeExDefine.OPER_TYPE_DELETE)
            {
                delList.add(order);
            }
            else
            {
                chgList.add(order);
            }
        }
        // 缓存所有操作的产品
        context.addExtendParam(ConstantExDefine.CHANGE_PROD_OPERATE_PRODIDS, allProdIds);
        List<SProductOrderOper> operList = new ArrayList<SProductOrderOper>();
        if (CommonUtil.isNotEmpty(addList))
        {
            operList.add(packageOrderOper(EnumCodeDefine.CHANGE_PROD_ADD, addList));
        }
        if (CommonUtil.isNotEmpty(delList))
        {
            operList.add(packageOrderOper(EnumCodeDefine.CHANGE_PROD_DELETE, delList));
        }
        if (CommonUtil.isNotEmpty(chgList))
        {
            operList.add(packageOrderOper(EnumCodeDefine.CHANGE_PROD_MODIFY, chgList));
        }
        if (CommonUtil.isNotEmpty(operList))
        {
            SProductOrderOperList list = new SProductOrderOperList();
            list.setSProductOrderOperList_Item(operList.toArray(new SProductOrderOper[operList.size()]));
            return list;
        }
        return null;
    }

    /*
     * 包装SPRODUCT_ORDER_OPER
     */
    private SProductOrderOper packageOrderOper(int operType, List<SProductOrder> orderList)
    {
        SProductOrderOper oper = new SProductOrderOper();
        oper.setOper_type((short) operType);
        SProductOrderList addOrderList = new SProductOrderList();
        addOrderList.setItem(orderList.toArray(new SProductOrder[orderList.size()]));
        oper.setProd_list(addOrderList);
        return oper;
    }

    /*
     * 包装SPRODUCT_ORDER
     */
    private SProductOrder packageSProductOrder(IProduct prod, List<IProdCharValue> charValueList,
            List<IProdPriceParam> priceParamList)
    {
        SProductOrder order = new SProductOrder();
        int operType = prod.getOperType();
        order.setAcct_id(prod.getAcctId());
        order.setCreate_date(IMSUtil.formatDate(prod.getCommitDate()));
        order.setExpire_date(IMSUtil.formatDate(prod.getExpireDate()));
        order.setOffer_id(prod.getOfferId());
        order.setParent_serv_product(prod.getParentProductId());
        order.setProd_state(CommonUtil.IntegerToShort(prod.getProductStatus()));
        order.setValid_date(IMSUtil.formatDate(prod.getValidDate()));
        order.setValid_type((short) EnumCodeDefine.PROD_VALID_SPECIFIC_DATE);
        order.setUser_id(prod.getUserId());
        order.setProduct_id(prod.getProductId());
        SProductParamList spList = packageSprodParamList(prod.getProductId(), operType, charValueList);
        if (spList != null)
        {
            order.setParam_list(spList);
        }
        ExtendParamList etList = packageExtendParamList(prod.getProductId(), operType, priceParamList);
        if (etList != null)
        {
            order.setReguid_price_param(etList);
        }
        return order;
    }

    /**
     * @Description 包装特征值
     * @Author lijingcheng
     * @param productId
     * @param operType
     * @param charValueList
     * @return
     */
    private SProductParamList packageSprodParamList(Long productId, int operType, List<IProdCharValue> charValueList)
    {
        if (CommonUtil.isEmpty(charValueList))
        {
            return null;
        }
        List<SProductParam> paramList = new ArrayList<SProductParam>();
        if (CommonUtil.isNotEmpty(charValueList))
        {
            for (IProdCharValue value : charValueList)
            {
                if (value.getOperType() == operType && value.getProductId().equals(productId))
                {
                    SProductParam param = new SProductParam();
                    param.setExpire_date(IMSUtil.formatDate(value.getExpireDate()));
                    param.setParam_id(value.getParamId());
                    param.setParam_value(value.getParamValue());
                    param.setValid_date(IMSUtil.formatDate(value.getValidDate()));
                    paramList.add(param);
                }
            }
        }
        if (CommonUtil.isNotEmpty(paramList))
        {
            SProductParamList list = new SProductParamList();
            list.setSProductParamList_Item(paramList.toArray(new SProductParam[paramList.size()]));
            return list;
        }
        return null;
    }

    /**
     * @Description 包装二次议价参数
     * @Author lijingcheng
     * @param productId
     * @param operType
     * @param priceParamList
     * @return
     */
    private ExtendParamList packageExtendParamList(Long productId, int operType, List<IProdPriceParam> priceParamList)
    {
        if (CommonUtil.isEmpty(priceParamList))
        {
            return null;
        }
        List<ExtendParam> paramList = new ArrayList<ExtendParam>();
        if (CommonUtil.isNotEmpty(priceParamList))
        {
            for (IProdPriceParam value : priceParamList)
            {
                if (value.getOperType() == operType && value.getProductId().equals(productId))
                {
                    ExtendParam param = new ExtendParam();
                    param.setExpire_date(IMSUtil.formatDate(value.getExpireDate()));
                    param.setParam_name(String.valueOf(value.getParamId()));
                    param.setParam_value(value.getParamValue());
                    param.setValid_date(IMSUtil.formatDate(value.getValidDate()));
                    paramList.add(param);
                }
            }
        }
        if (CommonUtil.isNotEmpty(paramList))
        {
            ExtendParamList list = new ExtendParamList();
            list.setExtendParamList_Item(paramList.toArray(new ExtendParam[paramList.size()]));
            return list;
        }
        return null;
    }
    
    /**
     * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<? extends jef.database.DataObject>[])
     */
    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    	if (FlowConst.MAN_PRODUCT_ID_PREFIX == 0){
    		return;
    	}
    	
        List<? extends DataObject> dataList = dataArr[0];
        prodCmp = getContext().getCmp(ProdExCmp.class);
        for(int i = 0;i< dataList.size();i++){
            IProduct prod = (IProduct) dataList.get(i);
            Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(prod.getOfferId());
            if(offerBean.getOffering().getIsMain() == EnumCodeDefine.PRODUCT_MAIN){
            	prod.setProductId(ITableUtil.convertMainProductId(FlowConst.MAN_PRODUCT_ID_PREFIX, prod.getProductId()));
            }
       }
       
    }
    
	/**
	 * 返回object_id
	 * @param userId
	 * @param acctId
	 * @return
	 */
	private long getObjectId(Long userId, Long acctId) {
		return (userId !=null) ? userId:acctId; 
   }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		   List<IProduct> prodList = (List<IProduct>) paramArr[0];
	        if (CommonUtil.isEmpty(prodList))
	        {
	            return;
	        }
	        RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
	        IProduct product = prodList.get(0);
	        prodCmp = getContext().getCmp(ProdExCmp.class);
	        long objectId = getObjectId(product.getUserId(),product.getAcctId());
	       //判断账户路由
	        baseCmp.checkAcctRouter(product.getAcctId());
	    	//判断用户路由
	        baseCmp.checkUserRouter(objectId);
	    	//根据Object_id 删除所有匹配的CoProd,设置分表参数
	           ITableUtil.setValue2ContextHolder(null, product.getAcctId(), product.getUserId());
	    		    List<Long> parentProductIds = new ArrayList<Long>();
	    		    List<Integer> prodTypeIdList = new ArrayList<Integer>();
		    		for (IProduct iProduct : prodList) {
				        	if(iProduct.getOperType() != EnumCodeExDefine.OPER_TYPE_DIFF){
				  	        	return;
				  	        }
				        	iProduct.setOperType(EnumCodeExDefine.OPER_TYPE_INSERT);
				        	if(iProduct.getParentProductId() != null){
				        		parentProductIds.add(iProduct.getParentProductId());
				        	}
				        	prodTypeIdList.add(iProduct.getProductType());
					}
		    		
		    		if(parentProductIds.size() > 0){
		    			prodCmp.deleteByConditionForDiff(CoProdBundleComposite.class, 
		    					new DBCondition(CoProdBundleComposite.Field.parentProductNo,parentProductIds,Operator.IN));
		    		}
	    		   //物理删除并返回所有实体,按prodTypeId+objectId删除
		    			List<? extends DataObject>  delList = prodCmp.deleteForDiffWithReturn(CoProd.class,
		    					new DBCondition(CoProd.Field.objectId,objectId),new DBCondition(CoProd.Field.prodTypeId,prodTypeIdList,Operator.IN));
		    		//删除二次议价
	    		    prodCmp.deleteByConditionForDiff(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.objectId,objectId));
		    		//删除账期
		    		prodCmp.deleteByConditionForDiff(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.objectId,objectId));
		    		//删除CoProdCharValue
		    		prodCmp.deleteByConditionForDiff(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.objectId,objectId));
		    		//删除CoBudgetCharValue
		    		prodCmp.deleteByConditionForDiff(CoBudgetCharValue.class, new DBCondition(CoBudgetCharValue.Field.objectId,objectId));
		    		prodCmp.deleteByConditionForDiff(CoProdMapping.class, new DBCondition(CoProdMapping.Field.objectId,objectId), 
		    				new DBCondition(CoProdMapping.Field.soNbr, context.getSoNbr(), Operator.NOT_EQUALS));
		         prodCmp.operateProduct(prodList, null, null);
			}
    	}
