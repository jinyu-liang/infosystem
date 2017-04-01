package com.ailk.imssh.prod.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jef.database.AbstractDbClient;
import jef.database.Batch;
import jef.database.Condition.Operator;
import jef.database.query.Query;
import jef.tools.DateUtils;

import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.ColCodeDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.acct.cmp.AcctCmp;
import com.ailk.imssh.acctpayrel.cmp.SplitCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.prod.complex.ProdComplex;
import com.ailk.imssh.prod.help.ProdCycleHelper;
import com.ailk.imssh.prod.help.ProdHelper;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdBundleComposite;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdMapping;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.itable.entity.IProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IProdPriceParam;
import com.ailk.openbilling.persistence.itable.entity.IProduct;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAttribute;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferMapping;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharValue;

public class ProdExCmp extends ProdCmp
{

    /**
     * lijc3 2012-9-28 从prodList中获取对应的prodList
     * 
     * @param prodId
     * @param list
     * @return
     */
    private List<CoProd> getProdList(Long prodId, List<CoProd> list)
    {
        if (CommonUtil.isEmpty(list))
        {
            return null;
        }
        List<CoProd> result = new ArrayList<CoProd>();
        for (CoProd dmProd : list)
        {
            if (dmProd.getProductId() == prodId.longValue())
            {
                result.add(dmProd);
            }
        }
        return result;
    }

    private List<CoProdBillingCycle> getProdCycleList(Long prodId, List<CoProdBillingCycle> list)
    {
        if (CommonUtil.isEmpty(list))
        {
            return null;
        }
        List<CoProdBillingCycle> result = new ArrayList<CoProdBillingCycle>();
        for (CoProdBillingCycle cycle : list)
        {
            if (cycle.getProductId() == prodId.longValue())
            {
                result.add(cycle);
            }
        }
        return result;
    }

    private ProdComplex packageProdComplex(IProduct prod, List<IProdCharValue> charValueList,
            List<IProdPriceParam> priceParamList, Map<Long, List<CoProd>> map, Map<Long, List<CoProdBillingCycle>> cycleMap,
            Map<Long, List<CoProd>> acctMap)
    {
        ProdComplex complex = new ProdComplex();
        complex.setProd(prod);
        if(prod.getUserId()!=null){
        if(map.containsKey(prod.getUserId())){
     		List<Long> productIdList=new ArrayList<Long>();
         	for(Entry<Long, List<CoProd>> entry:map.entrySet()){
         		Long userId=entry.getKey();
         		List<CoProd> list=entry.getValue();
         		for(CoProd coProd:list){
         			productIdList.add(coProd.getProductId());
         		}
         	}
         	if(!productIdList.contains(prod.getProductId())&&prod.getOperType()==EnumCodeExDefine.OPER_TYPE_UPDATE){
         		prod.setOperType(EnumCodeExDefine.OPER_TYPE_INSERT);
         	}
         }
        }else{
        	  if(acctMap.containsKey(prod.getAcctId())){
           		List<Long> productIdList=new ArrayList<Long>();
               	for(Entry<Long, List<CoProd>> entry:acctMap.entrySet()){
               		Long acctId=entry.getKey();
               		List<CoProd> list=entry.getValue();
               		for(CoProd coProd:list){
               			productIdList.add(coProd.getProductId());
               		}
               	}
               	if(!productIdList.contains(prod.getProductId())&&prod.getOperType()==EnumCodeExDefine.OPER_TYPE_UPDATE){
               		prod.setOperType(EnumCodeExDefine.OPER_TYPE_INSERT);
               	}
               }
        }
        // 不是insert的 load出产品来 后面直接处理
        if (prod.getOperType() != EnumCodeExDefine.OPER_TYPE_INSERT)
        {
            List<CoProd> dmProdList = null;
            List<CoProdBillingCycle> cycleList = null;
            if (prod.getUserId() != null)
            {   
            	
                dmProdList = getProdList(prod.getProductId(), map.get(prod.getUserId()));
                cycleList = getProdCycleList(prod.getProductId(), cycleMap.get(prod.getUserId()));
            }
            else
            {   
           
                dmProdList = getProdList(prod.getProductId(), acctMap.get(prod.getAcctId()));
                cycleList = getProdCycleList(prod.getProductId(), cycleMap.get(prod.getAcctId()));
            }
            if (CommonUtil.isNotEmpty(dmProdList))
            {
                CoProd dmProd = mergeProd(dmProdList);
                // 设置失效时间，后续算定价计划用,更新或者删除的时候，不以这个时间为更新时间
                // 缓存起来算定价计划用
                // 缓存修改主产品的失效时间，算定价计划的时候会取这个时间进行计算
                // dmProd.setExpireDate(prod.getExpireDate());
                if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                {
                    context.addExtendParam("MAIN_PROD_EXPIREDATE" + dmProd.getProductId(), prod.getExpireDate());
                }
                complex.setDmProd(dmProd);
                complex.setOldExpireDate(dmProd.getProdExpireDate());
                complex.setDmProdList(dmProdList);
                complex.setDmCycleList(cycleList);
            }
            else
            {
                return complex;
            }
        }
        if (CommonUtil.isNotEmpty(priceParamList))
        {
            List<IProdPriceParam> paramList = new ArrayList<IProdPriceParam>();
            for (IProdPriceParam value : priceParamList)
            {
                if (value.getProductId().equals(prod.getProductId()))
                {
                    paramList.add(value);
                }
            }
            complex.setParamList(paramList);
        }
        if (CommonUtil.isNotEmpty(charValueList))
        {
            List<IProdCharValue> valueList = new ArrayList<IProdCharValue>();
            for (IProdCharValue value : charValueList)
            {
                if (value.getProductId().equals(prod.getProductId()))
                {
                    valueList.add(value);
                }
            }
            complex.setValueList(valueList);
        }
        return complex;
    }

    /**
     * lijc3 2012-9-29 修改和删除的产品load出所有的东西
     */
    private Map<Long, List<CoProd>> getAllModifyOrDeleteProdList(List<IProduct> prodList,
            Map<Long, List<CoProdBillingCycle>> cycleMap, Map<Long, List<CoProd>> acctDmProdMap)
    {
        // 用户级产品和账户级产品分开处理 acctDmProdMap 账户级产品 coprodbillingcycle没有数据 可以忽略
        Map<Long, List<Long>> userMap = new HashMap<Long, List<Long>>();
        Map<Long, List<Long>> acctMap = new HashMap<Long, List<Long>>();
        for (IProduct prod : prodList)
        {   

        	
            if (prod.getOperType() != EnumCodeExDefine.OPER_TYPE_INSERT)
            {
                if (prod.getUserId() != null)
                {
                    List<Long> prodIdList = userMap.get(prod.getUserId());
                    if (CommonUtil.isEmpty(prodIdList))
                    {
                        prodIdList = new ArrayList<Long>();
                        userMap.put(prod.getUserId(), prodIdList);
                    }
                    prodIdList.add(prod.getProductId());
                }
                else
                {
                    List<Long> prodIdList = acctMap.get(prod.getAcctId());
                    if (CommonUtil.isEmpty(prodIdList))
                    {
                        prodIdList = new ArrayList<Long>();
                        acctMap.put(prod.getAcctId(), prodIdList);
                    }
                    prodIdList.add(prod.getProductId());
                }
            }
        }
        // object_id,co_prod_list 每个用户要修改删除的产品列表
        RouterCmp routeCmp = context.getCmp(RouterCmp.class);
        Map<Long, List<CoProd>> map = new HashMap<Long, List<CoProd>>();
        if (CommonUtil.isNotEmpty(userMap))
        {	
        	List<Long> userProdIds = new ArrayList<Long>(); 
        	List<CoProd> userProds = new ArrayList<CoProd>();
        	for(Entry<Long,List<Long>> entry:userMap.entrySet())
            {	
        		Long userId = entry.getKey();
        		Long acctId=routeCmp.queryAcctIdByUserId(userId);
                ITableUtil.setValue2ContextHolder(null, acctId, null);
        		List<Long> prodIds = entry.getValue();
        		for(int i=0;i<prodIds.size();i++){
        			userProdIds.add(prodIds.get(i));
        			if((i+1)%10 == 0 || (i+1)==prodIds.size()){
        				
                        List<CoProd> subList = DBUtil.query(CoProd.class, new DBCondition(CoProd.Field.productId,userProdIds, Operator.IN));
                        if(CommonUtil.isNotEmpty(subList)){
                        	 userProds.addAll(subList);
                        }
                        userProdIds.clear();
        			}
        		}

                if (CommonUtil.isNotEmpty(userProds))
                {
                    map.put(userId, userProds);
                }
//                List<CoProdBillingCycle> cycleList = this.query(CoProdBillingCycle.class, new DBCondition(
//                        CoProdBillingCycle.Field.productId, completeListSize(entry.getValue()), Operator.IN));
//                if (CommonUtil.isNotEmpty(cycleList))
//                {
//                    cycleMap.put(userId, cycleList);
//                }
            }
        }
        if (CommonUtil.isNotEmpty(acctMap))
        {
        	for(Entry<Long,List<Long>> entry:acctMap.entrySet())
            {
        		Long acctId = entry.getKey();
                ITableUtil.setValue2ContextHolder(null, acctId, null);
                List<CoProd> list = DBUtil.query(CoProd.class, new DBCondition(CoProd.Field.productId, completeListSize(entry.getValue()),
                        Operator.IN));
                if (CommonUtil.isNotEmpty(list))
                {
                    acctDmProdMap.put(acctId, list);
                }
//                List<CoProdBillingCycle> cycleList = this.query(CoProdBillingCycle.class, new DBCondition(
//                        CoProdBillingCycle.Field.productId, completeListSize(entry.getValue()), Operator.IN));
//                if (CommonUtil.isNotEmpty(cycleList))
//                {
//                    cycleMap.put(acctId, cycleList);
//                }
            }
        }
        return map;
    }

    /**
     * lijc3 2012-9-28 产品订购入口
     */
    public void operateProduct(List<IProduct> prodList, List<IProdCharValue> charValueList, List<IProdPriceParam> priceParamList)
    {
        List<ProdComplex> addList = new ArrayList<ProdComplex>();
        List<ProdComplex> delList = new ArrayList<ProdComplex>();
        List<ProdComplex> upList = new ArrayList<ProdComplex>();

        // 包装出每一个complex.
        Map<Long, List<CoProdBillingCycle>> cycleMap = new HashMap<Long, List<CoProdBillingCycle>>();
        // 账户级产品
        Map<Long, List<CoProd>> acctMap = new HashMap<Long, List<CoProd>>();
        // 用户级产品
        Map<Long, List<CoProd>> map = getAllModifyOrDeleteProdList(prodList, cycleMap, acctMap);
 
    	
        for (IProduct prod : prodList)
        {
            ProdComplex complex = packageProdComplex(prod, charValueList, priceParamList, map, cycleMap, acctMap);
            if (complex != null)
            {
                if (prod.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT)
                {
                    // 先进行创建
                    createCoProd(complex);
                    addList.add(complex);
                    putProdComplex(EnumCodeDefine.CHANGE_PROD_ADD, complex);
                }
                else if (prod.getOperType() == EnumCodeExDefine.OPER_TYPE_DELETE)
                {
                    delList.add(complex);
                    putProdComplex(EnumCodeDefine.CHANGE_PROD_DELETE, complex);
                }
                else
                {
                    upList.add(complex);
                    putProdComplex(EnumCodeDefine.CHANGE_PROD_MODIFY, complex);
                }
            }
        }

        if (CommonUtil.isNotEmpty(delList))
        {
            deleteProductListInfo();
        }
        Set<Long> enModifyList = new HashSet<Long>();
        if (CommonUtil.isNotEmpty(upList))
        {
            ProdComplex cp = null;
            // 主产品后处理 不会存在两个主产品同时修改 可能同时存在 主产品：1个新增 1个修改 1个删除
            // 主产品后处理，是为了先处理配生关系，后处理由主产品变化引起的定价计划变化
            for (ProdComplex complex : upList)
            {
            	modifyProduct(complex);
            }

        }
        // 2013-04-22 新增
        addList = getAllAddProdList();
        if (CommonUtil.isNotEmpty(addList))
        {

            for (ProdComplex complex : addList)
            {
                addProductInfo(complex, false);

            }
            imsLogger.debug("***************create prod list end");
            imsLogger.debug("***************begin to do all enlove product list");
            enloveAllProductList(new HashSet<Long>());

            imsLogger.debug("**************begin to do create price plan");
            // 避免重复处理
            //Set<Long> userList = new HashSet<Long>();
            //Set<Long> acctList = new HashSet<Long>();
            // 把派生的也获取出来进行计算定价计划
            // 删除代码2013-04-22
            // 一次性查出所有的pricePlan
            // getAllOfferPricePlan(addList);
            imsLogger.debug("**********all add product size = ", addList.size());
            /**
            for (ProdComplex complex : addList)
            {
                CoProd dmProd = complex.getDmProd();
                if (dmProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                {
                    if (userList.contains(dmProd.getObjectId()))
                    {
                        continue;
                    }
                    else
                    {
                        userList.add(dmProd.getObjectId());
                        calculatePricePlan(dmProd.getObjectId());
                    }
                }
                else
                {
                    if (acctList.contains(dmProd.getObjectId()))
                    {
                        continue;
                    }
                    else
                    {
                        acctList.add(dmProd.getObjectId());
                        calculatePricePlan(null);
                    }
                }
            }**/
            imsLogger.debug("**************end to do create price plan");

        }

        if (CommonUtil.isNotEmpty(addList))
        {
            imsLogger.debug("***********begin insert to db****");
            // 产品入库
            insertProdInfoList();
        }
//        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), context.getUserId());
    }

    private void modifyProduct(ProdComplex complex){
    	boolean needReMapping = true;
    	IProduct iProd = complex.getProd();
    	imsLogger.debug("================================modify product : ",iProd.getProductId());
    	List<CoProd> oldProdList = complex.getDmProdList();
    	 Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(iProd.getOfferId());
    	CoProd newProd = createModifyProd(iProd,offerBean);
    	setContextHodlerByCoProd(newProd);
    	this.updateMode3(newProd, new DBCondition(CoProd.Field.productId,iProd.getProductId()));
    	if(CommonUtil.isNotEmpty(oldProdList)){
    		CoProd lastProd = ProdHelper.getLastDataObjectByExpireDate(complex.getDmProdList());
        	List<Long> productIdList = queryProdMapping(lastProd);
        	if(CommonUtil.isNotEmpty(productIdList)){
        		if(iProd.getOfferId().intValue() == lastProd.getProductOfferingId().intValue()){
        			needReMapping = false;
            		//offerid不变，只需修改派生产品的失效时间
            		CoProdMapping mapp = new CoProdMapping();
                    mapp.setExpireDate(iProd.getExpireDate());
                    DBUtil.updateByCondition(mapp, new DBCondition(CoProdMapping.Field.relProduct, productIdList,Operator.IN),
                                new DBCondition(CoProdMapping.Field.objectId, lastProd.getObjectId()));
                        
                    CoProd mappingProd = new CoProd();
                    mappingProd.setObjectId(iProd.getUserId());
                    mappingProd.setCreateDate(iProd.getCommitDate());
                    mappingProd.setValidDate(iProd.getValidDate());
                    mappingProd.setExpireDate(iProd.getExpireDate());
                    mappingProd.setProdValidDate(iProd.getValidDate());
                    mappingProd.setProdExpireDate(iProd.getExpireDate());
                    mappingProd.setSoDate(context.getCommitDate());
                    mappingProd.setSoNbr(context.getSoNbr());
                    this.updateDirectByCondition(mappingProd, new DBCondition(CoProd.Field.productId, productIdList,Operator.IN));
               
            	}else{
            		//删除旧的派生产品，重新生成新的派生产品
            	
                      DBUtil.deleteByCondition(CoProdMapping.class, new DBCondition(CoProdMapping.Field.relProduct, productIdList,Operator.IN),
                                new DBCondition(CoProdMapping.Field.objectId, lastProd.getObjectId()));
                        
                      DBUtil.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, productIdList,Operator.IN));
            		
            	}
        	}
        		
    	}
    	if(needReMapping){
    		List<PmProductOfferMapping> mappList = this.query(PmProductOfferMapping.class, new DBCondition(PmProductOfferMapping.Field.productOfferingId,
   				 iProd.getOfferId()), new DBCondition(PmProductOfferMapping.Field.relationTypeId,
                    EnumCodeDefine.PRODUCT_REL_ONE_2_MORE));
   		 
    		List<CoProdMapping> coMapList = new ArrayList<CoProdMapping>();
    		List<CoProd> mappingProdList = new ArrayList<CoProd>();
    		if(CommonUtil.isNotEmpty(mappList)){
    			for(PmProductOfferMapping mapping : mappList){
   				 
    				CoProd prod = new CoProd();
    				prod.setProductId(DBUtil.getSequence(CoProd.class));
    				prod.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
    				prod.setObjectId(iProd.getUserId());
    				Offering3hbean mapOfferBean = context.get3hTree().loadOffering3hBean((long)mapping.getRelProductOffering());
    				prod.setProdTypeId(0);
    				prod.setProductOfferingId(mapping.getRelProductOffering());
    				if (iProd.getProductStatus() != null)
    				{
    					prod.setLifecycleStatusId(iProd.getProductStatus());
    				}
    				else
    				{
    					prod.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);// active
    				}
    				PmProductOffering mapDmOffering = mapOfferBean.getOffering();
    				int busiFlag = 0;
    				if (mapDmOffering.getSpecTypeFlag() != null && mapDmOffering.getSpecTypeFlag() == 1)
    				{
    					busiFlag = 0;
    				}
    				else
    				{
    					busiFlag = mapOfferBean.getBusiFlag();
    				}

    				prod.setBusiFlag(busiFlag);
    				if (EnumCodeDefine.PRODUCT_MAIN == mapDmOffering.getIsMain())
    				{
    					prod.setIsMain(EnumCodeDefine.PRODUCT_MAIN);
    				}
    				else
    				{
    					prod.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
    				}
    				prod.setBillingType(mapOfferBean.getAttribute().getBillingType());
    				prod.setCreateDate(iProd.getCommitDate());
    				prod.setValidDate(iProd.getValidDate());
    				prod.setExpireDate(iProd.getExpireDate());
    				prod.setProdValidDate(iProd.getValidDate());
    				prod.setProdExpireDate(iProd.getExpireDate());
    				prod.setSoDate(context.getCommitDate());
    				prod.setSoNbr(context.getSoNbr());
    				prod.setPricingPlanId(0);
    				mappingProdList.add(prod);
    				coMapList.add(evolveProductMapping(newProd, prod, prod.getIsMain()));
    			}
    			this.insertBatch(mappingProdList);
    			this.insertBatch(coMapList);
    		}
    	}
    	
    }
    
    /**
     * lijc3 2012-10-10 批量修改产品接口
     * 
     * @throws Exception
     */
    public void modifyProdList(Set<Long> enModifyList) throws Exception
    {
        Map<Short, Map<Long, List<ProdComplex>>> allMap = context.getAllProdMap();
        if (CommonUtil.isEmpty(allMap))
        {
            return;
        }
        Map<Long, List<ProdComplex>> chgMap = allMap.get(EnumCodeDefine.CHANGE_PROD_MODIFY);
        if (CommonUtil.isEmpty(chgMap))
        {
            return;
        }
        AbstractDbClient db = DBUtil.getDBClient();
        for (Entry<Long, List<ProdComplex>> entry : chgMap.entrySet())
        {
            Long objectId = entry.getKey();
            List<ProdComplex> complexList = entry.getValue();
            if (objectId == EnumCodeExDefine.ACCT_PROD_ID)
            {
                ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
            }
            else
            {
            	RouterCmp routeCmp = context.getCmp(RouterCmp.class);
            	long acctId = routeCmp.queryAcctIdByUserId(objectId);
             
                ITableUtil.setValue2ContextHolder(null, acctId, null);
            }
            // 修改的数据
            // 插入的数据
            List<CoProd> insertList = new ArrayList<CoProd>();
           // List<CoProdBillingCycle> insertCycleList = new ArrayList<CoProdBillingCycle>();
            Batch<CoProd> expireLastBatch = null;
           // Batch<CoProdBillingCycle> batchCycle = null;

            for (ProdComplex complex : complexList)
            {
                Date expireDate = complex.getProd().getExpireDate();
                CoProd dmProd = mergeProd(complex.getDmProdList());
                // 失效时间相同 跳过
                if (dmProd.getExpireDate().equals(expireDate))
                {
                    continue;
                }
                // 产品规格
                modifyParamList(complex.getValueList(), dmProd);
                modifyExtendParamList(complex.getParamList(), dmProd, null);

                List<CoProd> prodList = complex.getDmProdList();
                List<CoProdBillingCycle> cycleList = complex.getDmCycleList();
                // 分段的数据
                if (prodList.size() > 1)
                {
                    for (CoProd prod : prodList)
                    {
                        CoProd upValue = new CoProd();
                        Query<?> query = upValue.getQuery();
                        upValue.setProdExpireDate(expireDate);
                        upValue.setSoDate(context.getCommitDate());
                        query.addCondition(CoProd.Field.productId, prod.getProductId());
                        query.addCondition(CoProd.Field.soNbr, prod.getSoNbr());
                        query.addCondition(CoProd.Field.validDate, prod.getValidDate());
                        if (expireLastBatch == null)
                        {
                            expireLastBatch = db.startBatchUpdate(upValue);
                        }
                        if (dmProd.getExpireDate().equals(prod.getExpireDate()) && prod.getExpireDate().before(expireDate))
                        {
                            // valid > exp'
                            // olny update
                            upValue.setSoNbr(context.getSoNbr());
                            upValue.setExpireDate(expireDate);
                            expireLastBatch.add(upValue);
                            prod.setProdExpireDate(expireDate);
                            prod.setExpireDate(expireDate);
                            prod.setSoNbr(context.getSoNbr());
                            prod.setSoDate(context.getCommitDate());
                            context.cacheSingle(prod);
                        }
                        else if (prod.getValidDate().before(expireDate) && prod.getExpireDate().after(expireDate)
                                && prod.getValidDate().after(context.getCommitDate()))
                        {
                            // valid < exp' && expire > exp' && valid > sysdate
                            // olny update
                            upValue.setSoNbr(context.getSoNbr());
                            upValue.setExpireDate(expireDate);
                            expireLastBatch.add(upValue);
                            prod.setProdExpireDate(expireDate);
                            prod.setExpireDate(expireDate);
                            prod.setSoNbr(context.getSoNbr());
                            prod.setSoDate(context.getCommitDate());
                            context.cacheSingle(prod);
                        }
                        else if (prod.getValidDate().before(expireDate) && prod.getExpireDate().after(expireDate)
                                && prod.getValidDate().before(context.getCommitDate()))
                        {
                            // valid < exp' && expire > exp' && valid < sysdate
                            // insert
                            // update

                            upValue.setExpireDate(context.getCommitDate());
                            upValue.setProdExpireDate(context.getCommitDate());
                            expireLastBatch.add(upValue);

                            CoProd insert = (CoProd) IMSUtil.copyDataObject(prod);
                            insert.setSoDate(context.getCommitDate());
                            insert.setExpireDate(expireDate);
                            insert.setValidDate(context.getCommitDate());
                            insert.setSoNbr(context.getSoNbr());
                            insert.setProdExpireDate(expireDate);
                            insertList.add(insert);

                            prod.setExpireDate(context.getCommitDate());
                            prod.setProdExpireDate(context.getCommitDate());
                            context.cacheSingle(prod);

                        }
                        else if (prod.getValidDate().after(expireDate) || prod.getValidDate().equals(expireDate))
                        {
                            // valid >= exp'
                            // delete
                            upValue.setSoNbr(context.getSoNbr());
                            upValue.setExpireDate(context.getCommitDate());
                            upValue.setProdExpireDate(context.getCommitDate());
                            expireLastBatch.add(upValue);
                            prod.setExpireDate(context.getCommitDate());
                            prod.setProdExpireDate(context.getCommitDate());
                            prod.setSoNbr(context.getSoNbr());
                            prod.setSoDate(context.getCommitDate());
                            context.cacheSingle(prod);
                        }
                    }
                }
                else
                {
                    CoProd value = new CoProd();
                    value.setExpireDate(context.getCommitDate());
                    value.setSoDate(context.getCommitDate());
                    value.setProdExpireDate(context.getCommitDate());
                    value.getQuery().addCondition(CoProd.Field.productId, dmProd.getProductId());
                    value.getQuery().addCondition(CoProd.Field.soNbr, dmProd.getSoNbr());
                    value.getQuery().addCondition(CoProd.Field.validDate, dmProd.getValidDate());
                    if (expireLastBatch == null)
                    {
                        expireLastBatch = db.startBatchUpdate(value);
                    }
                    expireLastBatch.add(value);
                    // 缓存
                    CoProd cache = (CoProd) IMSUtil.copyDataObject(dmProd);
                    cache.setExpireDate(context.getCommitDate());
                    cache.setSoDate(context.getCommitDate());
                    cache.setProdExpireDate(context.getCommitDate());
                    context.cacheSingle(cache);
                    CoProd insert = (CoProd) IMSUtil.copyDataObject(dmProd);
                    insert.setSoDate(context.getCommitDate());
                    insert.setExpireDate(expireDate);
                    insert.setValidDate(context.getCommitDate());
                    insert.setSoNbr(context.getSoNbr());
                    insert.setProdExpireDate(expireDate);
                    insertList.add(insert);
                }
                if (CommonUtil.isNotEmpty(cycleList))
                {
                    if (cycleList.size() > 1)
                    {
                        for (CoProdBillingCycle prod : cycleList)
                        {
                            CoProdBillingCycle upValue = new CoProdBillingCycle();
                            Query<?> query = upValue.getQuery();
                            upValue.setSoDate(context.getCommitDate());
                            query.addCondition(CoProdBillingCycle.Field.productId, prod.getProductId());
                            query.addCondition(CoProdBillingCycle.Field.soNbr, prod.getSoNbr());
                            query.addCondition(CoProdBillingCycle.Field.validDate, prod.getValidDate());
//                            if (batchCycle == null)
//                            {
//                                batchCycle = db.startBatchUpdate(upValue);
//                            }
                            if (dmProd.getExpireDate().equals(prod.getExpireDate()) && prod.getExpireDate().before(expireDate))
                            {
                                // valid > exp'
                                // olny update
                                upValue.setSoNbr(context.getSoNbr());
                                upValue.setExpireDate(expireDate);
                               // batchCycle.add(upValue);
                                prod.setExpireDate(expireDate);
                                prod.setSoNbr(context.getSoNbr());
                                prod.setSoDate(context.getCommitDate());
                                context.cacheSingle(prod);
                            }
                            else if (prod.getValidDate().before(expireDate) && prod.getExpireDate().after(expireDate)
                                    && prod.getValidDate().after(context.getCommitDate()))
                            {
                                // valid < exp' && expire > exp' && valid > sysdate
                                // olny update
                                upValue.setSoNbr(context.getSoNbr());
                                upValue.setExpireDate(expireDate);
                               // batchCycle.add(upValue);
                                prod.setExpireDate(expireDate);
                                prod.setSoNbr(context.getSoNbr());
                                prod.setSoDate(context.getCommitDate());
                                context.cacheSingle(prod);
                            }
                            else if (prod.getValidDate().before(expireDate) && prod.getExpireDate().after(expireDate)
                                    && prod.getValidDate().before(context.getCommitDate()))
                            {
                                // valid < exp' && expire > exp' && valid < sysdate
                                // insert
                                // update

                                upValue.setExpireDate(context.getCommitDate());
                               // batchCycle.add(upValue);

                                CoProd insert = (CoProd) IMSUtil.copyDataObject(prod);
                                insert.setSoDate(context.getCommitDate());
                                insert.setExpireDate(expireDate);
                                insert.setValidDate(context.getCommitDate());
                                insert.setSoNbr(context.getSoNbr());
                                insert.setProdExpireDate(expireDate);
                                insertList.add(insert);

                                prod.setExpireDate(context.getCommitDate());
                                context.cacheSingle(prod);

                            }
                            else if (prod.getValidDate().after(expireDate) || prod.getValidDate().equals(expireDate))
                            {
                                // valid >= exp'
                                // delete
                                upValue.setSoNbr(context.getSoNbr());
                                upValue.setExpireDate(context.getCommitDate());
                                //batchCycle.add(upValue);
                                prod.setExpireDate(context.getCommitDate());
                                prod.setSoNbr(context.getSoNbr());
                                prod.setSoDate(context.getCommitDate());
                                context.cacheSingle(prod);
                            }
                        }
                    }
                    else
                    {
                        CoProdBillingCycle value = new CoProdBillingCycle();
                        value.setExpireDate(context.getCommitDate());
                        value.setSoDate(context.getCommitDate());
                        value.getQuery().addCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId());
                        value.getQuery().addCondition(CoProdBillingCycle.Field.soNbr, dmProd.getSoNbr());
                        value.getQuery().addCondition(CoProdBillingCycle.Field.validDate, dmProd.getValidDate());
//                        if (batchCycle == null)
//                        {
//                            batchCycle = db.startBatchUpdate(value);
//                        }
//                        batchCycle.add(value);
                        // 缓存
                        CoProdBillingCycle cache = (CoProdBillingCycle) IMSUtil.copyDataObject(cycleList.get(0));
                        cache.setExpireDate(context.getCommitDate());
                        cache.setSoDate(context.getCommitDate());
                        context.cacheSingle(cache);
                        CoProdBillingCycle insert = (CoProdBillingCycle) IMSUtil.copyDataObject(cycleList.get(0));
                        insert.setSoDate(context.getCommitDate());
                        insert.setExpireDate(expireDate);
                        insert.setValidDate(context.getCommitDate());
                        insert.setSoNbr(context.getSoNbr());
                       // insertCycleList.add(insert);
                    }
                }
                enloveProducts(EnumCodeDefine.CHANGE_PROD_MODIFY, complex, enModifyList);
            }
            imsLogger.debug("********begin to modify to db");
            if (expireLastBatch != null)
            {
                expireLastBatch.commit();
            }
//            if (batchCycle != null)
//            {
//                batchCycle.commit();
//            }
            this.insertBatch(insertList);
            //this.insertBatch(insertCycleList);
        }
    }

    /**
     * lijc3 2012-9-26 获取到销售品id列表
     * 
     * @param addList
     * @return
     */
    private List<Integer> getOfferIdList(List<ProdComplex> addList)
    {
        List<Integer> offerList = new ArrayList<Integer>();
        for (ProdComplex complex : addList)
        {
            offerList.add(complex.getProd().getOfferId().intValue());
        }
        return offerList;
    }

    /**
     * 派生产品 lijc3 2012-9-26
     * 
     * @param resServMap
     */
    private void enloveAllProductList(Set<Long> enModifyList)
    {
        List<ProdComplex> addList = getAllAddProdList();

        if (CommonUtil.isNotEmpty(addList))
        {
            // 先将PM_PRODUCT_OFFER_MAPPING全部查出
            getAllPmProductOfferMappList(addList);
            for (ProdComplex complex : addList)
            {
            	enloveProducts(EnumCodeDefine.CHANGE_PROD_ADD, complex, enModifyList);
            	/*
                if (complex.getProd().getUserId() != null)
                {
                    enloveProducts(EnumCodeDefine.CHANGE_PROD_ADD, complex, enModifyList);
                }
                else
                {
                    enloveProducts(EnumCodeDefine.CHANGE_PROD_ADD, complex, enModifyList);
                }
                */
            }
        }
    }

    /**
     * lijc3 2012-9-26 获取到所有的查询offer_mapp数据的
     * 
     * @param addList
     */
    private void getAllPmProductOfferMappList(List<ProdComplex> addList)
    {
        List<Integer> offerIdList = getOfferIdList(addList);
        List<CoProd> mainProdList = new ArrayList<CoProd>();
        Set<Long> userIdList = new HashSet<Long>();
        for (ProdComplex complex : addList)
        {
            Long userId = complex.getProd().getUserId();
            if (userId != null)
            {
                if (!userIdList.contains(userId))
                {
                    userIdList.add(userId);
                    List<CoProd> mainList = getMainProdList(userId);
                    if (CommonUtil.isNotEmpty(mainList))
                    {
                        mainProdList.addAll(mainList);
                    }
                }
            }
        }
        List<PmProductOfferMapping> mappList = null;
        if (CommonUtil.isNotEmpty(mainProdList))
        {
            Set<Integer> mainOfferIdList = new HashSet<Integer>();
            for (CoProd dmProd : mainProdList)
            {
                mainOfferIdList.add(dmProd.getProductOfferingId());
            }
            // mappList = this.query(PmProductOfferMapping.class, new DBCondition(PmProductOfferMapping.Field.productOfferingId,
            // offerIdList, Operator.IN), new DBCondition(PmProductOfferMapping.Field.refMainOffering, mainOfferIdList,
            // Operator.IN), new DBCondition(PmProductOfferMapping.Field.relationTypeId,
            // EnumCodeDefine.PRODUCT_REL_ENVLOPROD));
            mappList = this.query(PmProductOfferMapping.class, new DBCondition(PmProductOfferMapping.Field.productOfferingId,
                    offerIdList, Operator.IN), new DBOrCondition(new DBCondition(PmProductOfferMapping.Field.relationTypeId,
                    EnumCodeDefine.PRODUCT_REL_ONE_2_MORE), new DBCondition(new DBCondition(
                    PmProductOfferMapping.Field.refMainOffering, mainOfferIdList, Operator.IN), new DBCondition(
                    PmProductOfferMapping.Field.relationTypeId, EnumCodeDefine.PRODUCT_REL_ENVLOPROD))));
        }
        else
        {

            // List<PmProductOfferMapping> temp = this.query(PmProductOfferMapping.class, new DBCondition(
            // PmProductOfferMapping.Field.productOfferingId, offerIdList, Operator.IN), new DBCondition(
            // PmProductOfferMapping.Field.relationTypeId, EnumCodeDefine.PRODUCT_REL_ONE_2_MORE));
            mappList = this.query(PmProductOfferMapping.class, new DBCondition(PmProductOfferMapping.Field.productOfferingId,
                    offerIdList, Operator.IN), new DBCondition(PmProductOfferMapping.Field.relationTypeId,
                    EnumCodeDefine.PRODUCT_REL_ONE_2_MORE));
        }

        if (mappList != null)
        {
            context.setMappList(mappList);
        }

    }

    /**
     * lijc3 2012-9-22 获取所有订购的产品
     * 
     * @return
     */
    private List<ProdComplex> getAllAddProdList()
    {
        Map<Short, Map<Long, List<ProdComplex>>> allProdMap = context.getAllProdMap();
        if (CommonUtil.isEmpty(allProdMap) || CommonUtil.isEmpty(allProdMap.get(EnumCodeDefine.CHANGE_PROD_ADD)))
        {
            return null;
        }
        Map<Long, List<ProdComplex>> prodMap = allProdMap.get(EnumCodeDefine.CHANGE_PROD_ADD);
        List<ProdComplex> addList = new ArrayList<ProdComplex>();
        for(Entry<Long, List<ProdComplex>> entry : prodMap.entrySet()){
        	addList.addAll(entry.getValue());
        }
        return addList;
    }

    /**
     * lijc3 2012-10-11 是否多用户订购产品
     * 
     * @param prodMap
     * @return
     */
    private boolean isMultiUserOrderProduct(Map<Long, List<ProdComplex>> prodMap)
    {
        if (CommonUtil.isEmpty(prodMap))
        {
            return false;
        }
        Set<Long> set = prodMap.keySet();
        if (set.size() > 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void insertProdInfoList()
    {
        RouterCmp routCmp = context.getCmp(RouterCmp.class);
        // 订购入库
        Map<Short, Map<Long, List<ProdComplex>>> allProdMap = context.getAllProdMap();
        Map<Long, List<ProdComplex>> prodMap = allProdMap.get(EnumCodeDefine.CHANGE_PROD_ADD);
        boolean flag = isMultiUserOrderProduct(prodMap);
        List<CoProd> batchProdList = new ArrayList<CoProd>();
       // List<CoProdBillingCycle> batchCycleList = new ArrayList<CoProdBillingCycle>();
        for(Entry<Long, List<ProdComplex>> entry : prodMap.entrySet())
        {
        	Long objectId = entry.getKey();
            Long acctId = null;
            // 设置不同的分表参数
            if (objectId == EnumCodeExDefine.ACCT_PROD_ID)
            {
                List<ProdComplex> cxList = prodMap.get(objectId);
                if (CommonUtil.isNotEmpty(cxList))
                {
                    for (ProdComplex cx : cxList)
                    {
                        acctId = cx.getProd().getAcctId();
                        ITableUtil.setValue2ContextHolder(null, acctId, null);
                        if (CommonUtil.isNotEmpty(cx.getDmProdList()))
                        {
                            insertBatch(cx.getDmProdList());
                        }
                        else
                        {
                            insert(cx.getDmProd());
                        }
                        insertBatch(cx.getDmParamList());
                        insertBatch(cx.getDmValueList());
                        insertBatch(cx.getBudgetList());
                      //  insertBatch(cx.getDmCycleList());
                        insertBatch(cx.getMapps());
                        insert(cx.getComposite());
                    }
                }

                continue;
            }
            else
            {
                acctId = routCmp.queryAcctIdByUserId(objectId);
                ITableUtil.setValue2ContextHolder(null, acctId, null);
            }
            List<ProdComplex> complexList = entry.getValue();
            List<CoProd> prodList = new ArrayList<CoProd>();
//            List<CoProdBillingCycle> billcyclList = new ArrayList<CoProdBillingCycle>();
            List<CoProdCharValue> valueList = new ArrayList<CoProdCharValue>();
            List<CoProdPriceParam> paramList = new ArrayList<CoProdPriceParam>();
            List<CoProdMapping> mappList = new ArrayList<CoProdMapping>();
            List<CmResIdentity> idenList = new ArrayList<CmResIdentity>();
            List<CoProdBundleComposite> bundList = new ArrayList<CoProdBundleComposite>();
            List<CoBudgetCharValue> budgetList = new ArrayList<CoBudgetCharValue>();
            for (ProdComplex prod : complexList)
            {
                List<CoProd> dmList = prod.getDmProdList();
                if (CommonUtil.isNotEmpty(dmList))
                {
                    prodList.addAll(dmList);
                    for (CoProd dmProd : dmList)
                    {
                        dmProd.setAcctId(acctId);
                        batchProdList.add(dmProd);
                    }
                }
                else
                {
                    CoProd dmProd = prod.getDmProd();
                    prodList.add(dmProd);
                    dmProd.setAcctId(acctId);
                    batchProdList.add(dmProd);
                }
//                if (CommonUtil.isNotEmpty(prod.getDmCycleList()))
//                {
//                    billcyclList.addAll(prod.getDmCycleList());
//                    for (CoProdBillingCycle cycle : prod.getDmCycleList())
//                    {
//                        cycle.setAcctId(acctId);
//                        batchCycleList.add(cycle);
//                    }
//                }
                if (CommonUtil.isNotEmpty(prod.getDmParamList()))
                {
                    paramList.addAll(prod.getDmParamList());
                }
                if (CommonUtil.isNotEmpty(prod.getDmValueList()))
                {
                    valueList.addAll(prod.getDmValueList());
                }
                if (CommonUtil.isNotEmpty(prod.getIdenList()))
                {
                    idenList.addAll(prod.getIdenList());
                }
                if (CommonUtil.isNotEmpty(prod.getMapps()))
                {
                    mappList.addAll(prod.getMapps());
                }
                if (prod.getComposite() != null)
                {
                    bundList.add(prod.getComposite());
                }
                if (prod.getBudgetList() != null)
                {
                    budgetList.addAll(prod.getBudgetList());
                }
            }
            if (!flag)
            {
                try
                {
                    if (CommonUtil.isNotEmpty(prodList) && context.getBusiCode() == EnumCodeExDefine.ALL_DELETE_BUSI_CODE)
                    {
                        imsLogger.debug("******reset create date of products");
                        for (CoProd prod : prodList)
                        {
                            setCreateDateByReplaceAll(prod);
                        }
                    }
                }
                catch (Exception e)
                {
                    imsLogger.error("******exception accour where busi_code is 11111111111L");
                }
                insertBatch(prodList);
               // insertBatch(billcyclList);
            }
            insertBatch(paramList);
            insertBatch(valueList);
            insertBatch(idenList);
            insertBatch(mappList);
            insertBatch(bundList);
            insertBatch(budgetList);
            // 服务已经取消 不实例化

        }
        if (flag)
        {
            ITableUtil.cleanRequestParamter();
            try
            {
                if (CommonUtil.isNotEmpty(batchProdList) && context.getBusiCode() == EnumCodeExDefine.ALL_DELETE_BUSI_CODE )
                {
                    imsLogger.debug("******reset create date of products");
                    for (CoProd prod : batchProdList)
                    {
                        setCreateDateByReplaceAll(prod);
                    }
                }
            }
            catch (Exception e)
            {
                imsLogger.error("******exception accour where busi_code is 11111111111L");
            }
            try
            {
                AbstractDbClient client = DBUtil.getDBClient();
                Batch<CoProd> batch = client.startBatchInsert(CoProd.class);
                batch.add(batchProdList);
                batch.setGroupForPartitionTable(true);
                batch.commit();

//                if (CommonUtil.isNotEmpty(batchCycleList))
//                {
//                    Batch<CoProdBillingCycle> batchCycle = client.startBatchInsert(CoProdBillingCycle.class);
//                    batchCycle.add(batchCycleList);
//                    batchCycle.setGroupForPartitionTable(true);
//                    batchCycle.commit();
//                }
            }
            catch (Exception e)
            {
                throw IMSUtil.throwBusiException(e);
            }
            context.cacheList(batchProdList);
            //context.cacheList(batchCycleList);
        }
        // 设回分表参数
        //TODO 此处待确认？
//        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), context.getUserId());
    }

    /*
     * 全量覆盖产品的时候，设置生效时间
     */
    private void setCreateDateByReplaceAll(CoProd prod)
    {
        // 从内存中根据product_id获取到对应CO_PROD,有就修改prod的create_date,没有就不修改
        CoProd cacheProd = context.getSingleCache(CoProd.class, new CacheCondition(CoProd.Field.productId, prod.getProductId()));
        if (cacheProd != null)
        {
            prod.setCreateDate(cacheProd.getCreateDate());
            prod.setProdValidDate(cacheProd.getProdValidDate());
        }
    }

    @SuppressWarnings("unused")
    private void deleteMainProd(ProdComplex manComplex)
    {
        if (manComplex == null)
        {
            return;
        }
        imsLogger.debug("delete main product");
        Date expireDate = manComplex.getProd().getExpireDate();
        Long productId = manComplex.getProd().getProductId();

        this.deleteByCondition(CoProd.class, expireDate, new DBCondition(CoProd.Field.productId, productId));
        // 修改失效时间
        CoProd valid = new CoProd();
        valid.setProdExpireDate(expireDate);
        updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId, productId));
        // updateDirectByConditionNoQuery(valid, prodList, new DBCondition(CoProd.Field.productId, idList, Operator.IN));
        // 删除特征值
        // 2013-03-25 加
        this.deleteByCondition(CoProdCharValue.class, expireDate, new DBCondition(CoProdCharValue.Field.productId, productId));
        // this.deleteByCondition(CoBudgetCharValue.class, expireDate, new
        // DBCondition(CoBudgetCharValue.Field.productId,productId));

        // 删除二次议价
        this.deleteByCondition(CoProdPriceParam.class, expireDate, new DBCondition(CoProdPriceParam.Field.productId, productId));
        // 删除账期
        deleteByCondition(CoProdBillingCycle.class, expireDate, new DBCondition(CoProd.Field.productId, productId));
        imsLogger.debug("delete main product over");
    }
    
    /**
     * 根据失效时间分组删除的数据
     * @param complexList
     * @return
     */
    private Map<Date,List<Long>> groupByExpireDate(List<ProdComplex> complexList){
    	Map<Date,List<Long>> map = new HashMap<Date, List<Long>>();
    	for(ProdComplex complex : complexList){
    		Date expireDate = complex.getProd().getExpireDate();
    		List<Long> idList = map.get(expireDate);
    		if(CommonUtil.isEmpty(idList)){
    			idList = new ArrayList<Long>();
    			map.put(expireDate, idList);
    		}
    		idList.add(complex.getProd().getProductId());
    	}
    	return map;
    }

    /**
     * lijc3 2012-9-29 批量删除产品
     */
    private void deleteProductListInfo()
    {
        Map<Short, Map<Long, List<ProdComplex>>> allMap = context.getAllProdMap();
        if (CommonUtil.isEmpty(allMap))
        {
            return;
        }
        Map<Long, List<ProdComplex>> delMap = allMap.get(EnumCodeDefine.CHANGE_PROD_DELETE);
        if (CommonUtil.isEmpty(delMap))
        {
            return;
        }
        // 用户或者账户的都要把产品id循环出来 进行批量删除
        List<ProdComplex> allComplexList = new ArrayList<ProdComplex>();
        for (Entry<Long, List<ProdComplex>> entry : delMap.entrySet())
        {
            Long objectId = entry.getKey();
            List<ProdComplex> complexList = entry.getValue();
            if(CommonUtil.isEmpty(complexList)){
            	continue;
            }
            allComplexList.addAll(complexList);
            /* if (objectId == EnumCodeExDefine.ACCT_PROD_ID)
            {
                ITableUtil.setValue2ContextHolder(null, complexList.get(0).getProd().getAcctId(), null);
            }
            else
            {
                ITableUtil.setValue2ContextHolder(null, null, objectId);
            }
            
            Map<Date,List<Long>> map = groupByExpireDate(complexList);
            
            for(Entry<Date, List<Long>> entry2:map.entrySet()){
            	Date expireDate=entry2.getKey();
            	List<Long> idList=entry2.getValue();
            	
            	this.deleteByCondition(CoProd.class, expireDate, new DBCondition(CoProd.Field.productId, completeListSize(idList),
                        Operator.IN));
                // 修改失效时间
                CoProd valid = new CoProd();
                valid.setProdExpireDate(expireDate);
                updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId, completeListSize(idList), Operator.IN));
                
                
                this.deleteByCondition(CoProdCharValue.class, expireDate, new DBCondition(CoProdCharValue.Field.productId, completeListSize(idList),
                        Operator.IN));
                this.deleteByCondition(CoBudgetCharValue.class, expireDate, new DBCondition(CoBudgetCharValue.Field.productId,
                		completeListSize(idList), Operator.IN));
                // 删除二次议价
                this.deleteByCondition(CoProdPriceParam.class, expireDate, new DBCondition(CoProdPriceParam.Field.productId, completeListSize(idList),
                        Operator.IN));
                // 删除账期
                deleteByCondition(CoProdBillingCycle.class, expireDate,  new DBCondition(
                        CoProdBillingCycle.Field.productId, completeListSize(idList), Operator.IN));
                 
            }*/
        }
        Set<Long> idSet = new HashSet<Long>();
        for (ProdComplex complex : allComplexList)
        {
        	IProduct iProd = complex.getProd();
            Long prodId = iProd.getProductId();
            CoProd dmProd = complex.getDmProd();
            if(dmProd == null){
            	imsLogger.debug("================================productid:",iProd.getProductId()," no old product,no need to delete,deal next");
            	continue;
            }
            setContextHodlerByCoProd(dmProd);
            this.deleteByCondition(CoProd.class, iProd.getExpireDate(), new DBCondition(CoProd.Field.productId, iProd.getProductId()));
            // 修改失效时间
            CoProd valid = new CoProd();
            valid.setProdExpireDate(iProd.getExpireDate());
            updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId,iProd.getProductId()));
           
            if (complex.getProd().getParentProductId() != null)
            {
                // 直接删除，这个表目前没有使用的
                DBUtil.deleteByCondition(CoProdBundleComposite.class, new DBCondition(CoProdBundleComposite.Field.productId,
                        prodId), new DBCondition(CoProdBundleComposite.Field.parentProductNo, complex.getProd()
                        .getParentProductId()));
            }
            List<Long> prodIdList = null;
            // 主产品的查询需要加上生效时间，否则在下周期套餐换出的时候会将原来的也设置为失效
            // 加上生效时间比当前时间大，代表是取消下周期的套餐,避免换套餐立即生效的时候需要置为失效的没有设置为失效
            if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN
                    && complex.getProd().getValidDate().after(context.getCommitDate()))
            {
                prodIdList = queryProdMappingByMainProduct(dmProd, complex.getProd().getValidDate());
            }
            else
            {
                prodIdList = queryProdMapping(dmProd);
            }
            if (CommonUtil.isNotEmpty(prodIdList))
            {
                for (Long productId : prodIdList)
                {
                    if (!idSet.contains(productId))
                    {
                        idSet.add(productId);
                    }
                    else
                    {
                        continue;
                    }
                    CoProd temp = this.queryProd(productId);
                    // 为空 或者so_nbr相同的不处理
                    if (temp == null || temp.getSoNbr() == context.getSoNbr())
                    {
                        continue;
                    }
                    // 自带优惠需要判断是否需要下周期失效
                    // 2013-03-25 加
                    Date expire = complex.getProd().getExpireDate();
                    CoProd prod = (CoProd) IMSUtil.copyDataObject(dmProd);
                    prod.setExpireDate(expire);
                  
                    setContextHodlerByCoProd(temp);
                    this.deleteProdById(productId, expire);
                    CoProd validCoProd = new CoProd();
                    validCoProd.setProdExpireDate(iProd.getExpireDate());
                    updateDirectByCondition(validCoProd, new DBCondition(CoProd.Field.productId,productId));
                   
                    /*
                    this.deleteProdParamPriceByProdId(productId, expire);
                    this.deleteProdCharValueByProdId(productId, expire);
                    modifyProdValid(productId, expire);
                    deleteByCondition(CoProdBillingCycle.class, expire, new DBCondition(CoProdBillingCycle.Field.productId,
                            prodId));
					*/
                    deleteByCondition(CoProdMapping.class, new DBCondition(CoProdMapping.Field.relProduct, productId),
                            new DBCondition(CoProdMapping.Field.objectId, dmProd.getObjectId()), new DBCondition(
                                    CoProdMapping.Field.soNbr, context.getSoNbr(), Operator.NOT_EQUALS));
                  
                    
                }
            }
            // 取消派生关系

        }
        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
    }

    // 根据主产品查询套餐换出的时候需要设置为失效的配生产品
    private List<Long> queryProdMappingByMainProduct(CoProd dmProd, Date validDate)
    {
        List<CoProdMapping> mapps = this.query(CoProdMapping.class,
                new DBCondition(CoProdMapping.Field.objectId, dmProd.getObjectId()), new DBCondition(
                        CoProdMapping.Field.objectType, dmProd.getObjectType()), new DBCondition(
                        CoProdMapping.Field.relationTypeId, EnumCodeDefine.PRODUCT_REL_ONE_2_MORE), new DBCondition(
                        CoProdMapping.Field.validDate, validDate, Operator.GREAT_EQUALS));

        if (CommonUtil.isEmpty(mapps))
        {
            return null;
        }
        List<Long> productIds = new ArrayList<Long>();
        for (CoProdMapping mp : mapps)
        {
            productIds.add(mp.getRelProduct());
        }
        return productIds;
    }

    /**
     * lijc3 2012-9-29 单个删除产品
     * 
     * @param complex
     * @param addServSpecIdList
     * @param isEnlove
     */
    @SuppressWarnings("unused")
    private void deleteProductInfo(ProdComplex complex, boolean isEnlove)
    {
        if (complex.isDone())
        {
            return;
        }
        else
        {
            complex.setDone(true);
        }
        CoProd dmProd = complex.getDmProd();
        List<CoProd> prodList = complex.getDmProdList();

        Long prodId = dmProd.getProductId();
        // 2012-09-20 zengxr noquery for update and delete
        deleteProdByIdNoQuery(prodId, prodList);
        modifyProdValid(prodId, context.getCommitDate());
        // modifyProdValidNoQuery(prodId, context.getCommitDate(), prodList);
        List<CoProdCharValue> charValueList = deleteCoProdCharValue(prodId);
        deleteCoProdPriceParam(prodId);
        // deleteCoProdBillingCycle(prodId);
//        deleteByConditionNoQuery(CoProdBillingCycle.class, null, complex.getDmCycleList(), new DBCondition(
//                CoProdBillingCycle.Field.productId, prodId));
        Long overrideProdId = null;
        Integer overridePlanId = null;
        if (CommonUtil.isNotEmpty(charValueList))
        {
            for (CoProdCharValue value : charValueList)
            {
                if (value.getSpecCharId() == SpecCodeDefine.OVERRIDE_PRODUCT_ID)
                {
                    overrideProdId = CommonUtil.string2Long(value.getValue());
                }
                else if (value.getSpecCharId() == SpecCodeDefine.OLD_PRICE_PLAN_ID)
                {
                    overridePlanId = CommonUtil.string2Integer(value.getValue());
                }
                else if (value.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER)
                {
                    deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identityType,
                            EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM),
                            new DBCondition(CmResIdentity.Field.identity, value.getValue()));
                }
                else if (value.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
                {
                    deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identityType,
                            EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM),
                            new DBCondition(CmResIdentity.Field.identity, value.getValue()));
                }
            }
        }
        // 更改override的定价计划
        if (overrideProdId != null && overridePlanId != null)
        {
            CoProd value = new CoProd();
            value.setPricingPlanId(overridePlanId);
            updateByCondition(value, new DBCondition(CoProd.Field.productId, overrideProdId));
            CoProdPriceParam param = new CoProdPriceParam();
            // @Date 2012-10-08 gaoqc5 #60541 co_prod_price_param表的price_plan_id字段去掉
            // param.setPricingPlanId(overridePlanId);
            updateByCondition(param, new DBCondition(CoProdPriceParam.Field.productId, overrideProdId));
        }
        // 删除打包关系
        if (complex.getProd().getParentProductId() != null)
        {
            DBUtil.deleteByCondition(CoProdBundleComposite.class,
                    new DBCondition(CoProdBundleComposite.Field.productId, dmProd.getProductId()), new DBCondition(
                            CoProdBundleComposite.Field.parentProductNo, complex.getProd().getParentProductId()));
        }
        if (!isEnlove)
        {
            List<Long> prodIdList = queryProdMapping(dmProd);
            if (CommonUtil.isNotEmpty(prodIdList))
            {
                for (Long productId : prodIdList)
                {
                    ProdComplex newComplex = transferCoProd2ProdComplex(complex, EnumCodeDefine.CHANGE_PROD_DELETE, null,
                            productId, null, null);
                    putProdComplex(EnumCodeDefine.CHANGE_PROD_DELETE, newComplex);
                    deleteProductInfo(newComplex, true);
                }
                // 取消派生关系
                deleteByCondition(CoProdMapping.class, new DBCondition(CoProdMapping.Field.productId, prodId), new DBCondition(
                        CoProdMapping.Field.soNbr, context.getSoNbr(), Operator.NOT_EQUALS));
            }
        }
    }

    public void modifyProductInfo(ProdComplex complex, Set<Long> enModifyList, boolean isEnlove)
    {
        if (complex.isDone())
        {
            return;
        }
        else
        {
            complex.setDone(true);
        }
        ProdQuery prodQuery = context.getCmp(ProdQuery.class);
        List<CoProd> prodList = complex.getDmProdList();
        CoProd oldProd = null;
        if (CommonUtil.isNotEmpty(prodList))
        {
            oldProd = prodQuery.mergeProd(prodList);
        }
        else
        {
            return;
        }
        // 设置分表
        setContextHodlerByCoProd(oldProd);

        // 产品规格
        modifyParamList(complex.getValueList(), oldProd);
        modifyExtendParamList(complex.getParamList(), oldProd, null);

        Date expireDate = complex.getProd().getExpireDate();
        CoProd prodValue = new CoProd();
        prodValue.setExpireDate(expireDate);
        prodValue.setProdExpireDate(expireDate);
        prodValue.setSoNbr(context.getSoNbr());

        // crm传入的生效时间晚于产品的生效时间，做了激活修改
        Date validDate = null;
        // 20130805
        if (!complex.getProd().getValidDate().equals(oldProd.getProdValidDate()))
        {
            validDate = complex.getProd().getValidDate();
        }
        if (!isEnlove)
        {
            validDate = null;
        }

        setContextHodlerByCoProd(oldProd); // 保证分表正确


        if (validDate == null)
        {
            extendCoProd(prodValue, null, new DBCondition(CoProd.Field.productId, oldProd.getProductId()));
        }
        else
        {
                activeProdDate(prodValue, DateUtils.dayBegin(context.getRequestDate()), DateUtils.dayBegin(validDate), prodList);
        }

        if (validDate == null)
        {
            modifyProdValid(oldProd.getProductId(), expireDate);
        }
        else
        {
            modifyProdValid(oldProd.getProductId(), DateUtils.dayBegin(validDate), expireDate);
        }

        // 修改账期的有效期
//        CoProdBillingCycle upValue = new CoProdBillingCycle();
//        upValue.setExpireDate(expireDate);
//        extendLastObjectNoQuery(complex.getDmCycleList(), upValue,
//                new DBCondition(CoProdBillingCycle.Field.productId, oldProd.getProductId()));
        // 产品派生处理
        if (isEnlove)
        {
            enloveProducts(EnumCodeDefine.CHANGE_PROD_MODIFY, complex, enModifyList);
        }
        // modifyProdValidNoQuery(oldProd.getProductId(), expireDate, prodList);
        // 是主产品,并且是延长，表示换套餐取消才进入
        if (oldProd.getIsMain() == 1 && expireDate.after(oldProd.getExpireDate()))
        {
            // 还有其他的删除或者修改的主产品,则认为是换套餐取消
            if (hasOtherDelOrUpMainProd(oldProd))
            {
                imsLogger.info("***换套餐取消进入，设置中间主产品生效时间和失效时间");
                CoProd dmProd = (CoProd) IMSUtil.copyDataObject(oldProd);
                dmProd.setExpireDate(expireDate);
                dmProd.setValidDate(context.getCommitDate());
                chgPricePlanByMainProduct(dmProd, true);
            }
        }
    }

    // 延长产品的时候获取定价计划
    public Integer getPricePlanIdByExtendProd(CoProd dmProd)
    {
        CoProd mainProd = null;
        List<CoProd> mainList = getMainProdList(dmProd.getObjectId());
        if (CommonUtil.isNotEmpty(mainList))
        {
            for (CoProd p : mainList)
            {
                // 主产品的生效时间要小于等于该产品的生效时间，失效时间要大于该产品的失效时间
                if (!p.getValidDate().after(dmProd.getExpireDate()) && p.getExpireDate().after(dmProd.getExpireDate()))
                {
                    mainProd = p;
                    break;
                }
            }
        }
        if (mainProd == null)
        {
            mainProd = this.querySingle(CoProd.class, new DBCondition(CoProd.Field.objectId, dmProd.getObjectId()),
                    new DBCondition(CoProd.Field.isMain, EnumCodeDefine.PRODUCT_MAIN), new DBCondition(CoProd.Field.validDate,
                            dmProd.getExpireDate(), Operator.LESS_EQUALS),
                    new DBCondition(CoProd.Field.expireDate, dmProd.getExpireDate(), Operator.GREAT));
        }
        if (mainProd != null)
        {
            Integer pricePlanId = queryPricePlanByProd(dmProd, mainProd);
            return pricePlanId;
        }
        else
        {
            return 0;
        }

    }

    /**
     * lijc3 2013-1-25 同时修改产品的PROD_VALID_DATE,PROD_EXPIRE_DATE
     * 
     * @param prodId
     * @param validDate
     * @param expireDate
     */
    public void modifyProdValid(Long prodId, Date validDate, Date expireDate)
    {
        CoProd valid = new CoProd();
        valid.setProdValidDate(validDate);
        valid.setProdExpireDate(expireDate);
        updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId, prodId));
    }

    // 产品列表中还有其他的修改或者删除的主产品，则表示换套餐取消
    private boolean hasOtherDelOrUpMainProd(CoProd dmProd)
    {

        Map<Short, Map<Long, List<ProdComplex>>> allMap = context.getAllProdMap();
        if (CommonUtil.isEmpty(allMap))
        {
            return false;
        }
        // 删除列表里产品都是立即失效的，不考虑
        Map<Long, List<ProdComplex>> delMap = allMap.get(EnumCodeDefine.CHANGE_PROD_DELETE);
        if (CommonUtil.isNotEmpty(delMap))
        {
            List<ProdComplex> list = delMap.get(dmProd.getObjectId());
            if (CommonUtil.isNotEmpty(list))
            {
                for (ProdComplex cp : list)
                {
                    CoProd prod = cp.getDmProd();
                    if (prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN
                            && prod.getProductId() != dmProd.getProductId().longValue())
                    {
                        return true;
                    }
                }
            }
        }

        Map<Long, List<ProdComplex>> upMap = allMap.get(EnumCodeDefine.CHANGE_PROD_MODIFY);
        if (CommonUtil.isEmpty(upMap))
        {
            return false;
        }
        else
        {
            List<ProdComplex> delList = upMap.get(dmProd.getObjectId());
            if (CommonUtil.isEmpty(delList))
            {
                return false;
            }
            else
            {
                for (ProdComplex cp : delList)
                {
                    CoProd prod = cp.getDmProd();
                    if (prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN
                            && prod.getProductId() != dmProd.getProductId().longValue())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 2012-10-04 gaoqc5 机卡分离后不送免费资源 需求
     * 
     * @param iProduct
     * @param dmProd
     */
    private void modifyPMBySuspendProduct(IProduct iProduct, CoProd dmProd)
    {
        CoProdPriceParam pm = new CoProdPriceParam();
        AcctCmp acctCmp = getContext().getCmp(AcctCmp.class);

        Long acctId = getAcctId(iProduct);

        Date[] dates = acctCmp.queryAcctCycleStartAndEnd(acctId, iProduct.getCommitDate(), EnumCodeDefine.ACCT_NEXT_BILL_CYCLE);

        // 下一个账期生效
        pm.setValidDate(dates[0]);
        // 一个月后失效
        pm.setExpireDate(dates[1]);

        pm.setParamId(EnumCodeExDefine.PRICE_PARAM_ID_RC_SUSPEND_PRODUCT);
        pm.setParamValue("0");

        pm.setCreateDate(iProduct.getCommitDate());
        pm.setSoDate(iProduct.getCommitDate());
        pm.setSoNbr(iProduct.getSoNbr());
        pm.setObjectType(dmProd.getObjectType());
        pm.setObjectId(dmProd.getObjectId());
        pm.setProductId(dmProd.getProductId());
        pm.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
        IMSUtil.setUserRouterParam(iProduct.getUserId());
        insert(pm);
    }

    /**
     * 获取账户ID
     * 
     * @param iProduct
     * @return
     */
    private Long getAcctId(IProduct iProduct)
    {
        if (null != iProduct.getAcctId())
        {
            return iProduct.getAcctId();
        }
        else
        {
            RouterCmp userCmp = getContext().getCmp(RouterCmp.class);
            Long acctId = userCmp.getAcctIdByUserIdRout(iProduct.getUserId());
            return acctId;
        }
    }

    /**
     * lijc3 2012-6-18
     * 
     * @param complex
     * @param resServList
     * @param isEnlove 是否派生订购，一般调用为false
     */
    public void addProductInfo(ProdComplex complex, boolean isEnlove)
    {
        if (complex.isDone())
        {
            return;
        }
        else
        {
            complex.setDone(true);
        }
        CoProd dmProd = createCoProd(complex);
        // 特征值
//        createCoProdCharValue(complex);
        // 二次议价
//        createCoProdPriceParam(complex);
        //createCoProdBillingCycle(complex);

        // 打包产品
        createPackageProduct(complex);
        // 产品服务
        // createResourceServiceCycle(complex, resServList);
        // 创建Identity
        List<CmResIdentity> idenList = createIndentity(dmProd, complex.getDmValueList());
        complex.setIdenList(idenList);
        // 如果订购了主产品，还要改掉其他未处理过的定价计划
        // 全量替换则不需要进下面的方法
        /**
        long busiCode = context.getBusiCode() == null ? 0 : context.getBusiCode();
        
        if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN && (busiCode != EnumCodeExDefine.ALL_DELETE_BUSI_CODE && busiCode != EnumCodeExDefine.GX_MANUAL_MODIFY_PROD))
        {
            imsLogger.debug("**********order a main product,begin to change other price plan");
            chgPricePlanByMainProduct(dmProd, false);
        }
        */
    }

    private Set<Long> getModifyAndDeleteProdIdList(CoProd dmProd)
    {
        Set<Long> set = new HashSet<Long>();
        Map<Short, Map<Long, List<ProdComplex>>> allMap = context.getAllProdMap();
        if (CommonUtil.isEmpty(allMap))
        {
            return null;
        }
        Map<Long, List<ProdComplex>> upMap = allMap.get(EnumCodeDefine.CHANGE_PROD_MODIFY);
        Map<Long, List<ProdComplex>> delMap = allMap.get(EnumCodeDefine.CHANGE_PROD_DELETE);
        if (CommonUtil.isNotEmpty(delMap))
        {
            List<ProdComplex> complexList = delMap.get(dmProd.getObjectId());
            if (CommonUtil.isNotEmpty(complexList))
            {
                for (ProdComplex cp : complexList)
                {
                    set.add(cp.getProd().getProductId());
                }
            }
        }
        if (CommonUtil.isNotEmpty(upMap))
        {
            List<ProdComplex> complexList = upMap.get(dmProd.getObjectId());
            if (CommonUtil.isNotEmpty(complexList))
            {
                for (ProdComplex cp : complexList)
                {
                    set.add(cp.getProd().getProductId());
                }
            }

        }
        // 这里如果没有值，那么返回一个空结构
        return set;
    }

    private Set<Long> getModifyProdIdList(CoProd dmProd)
    {
        Map<Short, Map<Long, List<ProdComplex>>> allMap = context.getAllProdMap();
        if (CommonUtil.isEmpty(allMap))
        {
            return null;
        }
        // 删除列表里产品都是立即失效的，不考虑
        Map<Long, List<ProdComplex>> upMap = allMap.get(EnumCodeDefine.CHANGE_PROD_MODIFY);
        if (CommonUtil.isEmpty(upMap))
        {
            return null;
        }
        List<ProdComplex> complexList = upMap.get(dmProd.getObjectId());
        if (CommonUtil.isEmpty(complexList))
        {
            return null;
        }
        Set<Long> set = new HashSet<Long>();
        for (ProdComplex cp : complexList)
        {
            set.add(cp.getProd().getProductId());
        }
        return set;
    }

    /*
     * 换套餐的时候是否需要删除下周期生效的产品
     */
    private Set<Long> getDeleteProdIdListByChgMainProd(List<CoProd> allProdList, CoProd mainProd, Set<Long> modifyProdIdList)
    {
        Set<Long> idList = new HashSet<Long>();
        for (CoProd prod : allProdList)
        {
            if (prod.getSoNbr() == context.getSoNbr())
            {
                continue;
            }
            // 如果修改的id在当前传入的列表中，则不修改
            if (CommonUtil.isNotEmpty(modifyProdIdList))
            {
                if (modifyProdIdList.contains(prod.getProductId()))
                {
                    continue;
                }
            }
            // 新套餐生效的时候，该产品已经失效，则不需要做变化
            if (prod.getExpireDate().before(mainProd.getValidDate()) || prod.getExpireDate().equals(mainProd.getValidDate()))
            {
                continue;
            }
            if (prod.getValidDate().equals(mainProd.getValidDate()) || prod.getValidDate().after(mainProd.getValidDate()))
            {
                idList.add(prod.getProductId());
            }
        }
        return idList;
    }

    private void chgPricePlanByMainProduct(CoProd dmProd, boolean isCancel)
    {
        setContextHodlerByCoProd(dmProd);
        List<CoProd> oldProdList = this.query(CoProd.class, new DBCondition(CoProd.Field.objectId, dmProd.getObjectId()),
                new DBCondition(CoProd.Field.objectType, dmProd.getObjectType()));
        if (CommonUtil.isNotEmpty(oldProdList))
        {
            context.addExtendParam("OLD_PRODUCT_LIST" + dmProd.getObjectId(), oldProdList);
        }

        // lijc3 2014-4-3 取生效时间和当前时间大的时间
        Date expireDate = dmProd.getValidDate().after(context.getCommitDate()) ? dmProd.getValidDate() : context.getCommitDate();
        // 只查出非主产品,增加一个条件，必须大于主产品的生效时间的数据才查出来
        List<CoProd> dbProdList = this.query(CoProd.class, new DBCondition(CoProd.Field.objectId, dmProd.getObjectId()),
                new DBCondition(CoProd.Field.objectType, dmProd.getObjectType()), new DBCondition(CoProd.Field.isMain,
                        EnumCodeDefine.PRODUCT_MAIN, Operator.NOT_EQUALS), new DBCondition(CoProd.Field.busiFlag,
                        ColCodeDefine.GROUP_MEM_PROD_BUSIFLAGS, Operator.NOT_IN), new DBCondition(CoProd.Field.expireDate,
                        expireDate, Operator.GREAT));
        List<CoProd> allProdList = null;
        if (CommonUtil.isEmpty(dbProdList))
        {
            return;
        }
        else
        {
            // 对同一个id的产品进行合并
            allProdList = this.mergeProdList(dbProdList);
        }
        Set<Long> modifyProdIdList = getModifyProdIdList(dmProd);
        // 执行下周期的删除操作
        Set<Long> idList = getDeleteProdIdListByChgMainProd(dbProdList, dmProd, modifyProdIdList);
        if (CommonUtil.isNotEmpty(idList))
        {
            // 产品的生效时间等于主产品的生效时间，直接删除,只删除生效时间等于主产品的,一般都是下周期生效的数据
            // 换套餐取消或者换套餐立即生效的时候，删除的时候，如果不是下周期生效的产品，需要设置失效，然后上发
            List<CoProd> cacheProdList = (List<CoProd>) DBUtil.deleteByConditionWithReturn(CoProd.class, new DBCondition(
                    CoProd.Field.productId, completeSetSize(idList), Operator.IN), new DBCondition(CoProd.Field.validDate, dmProd.getValidDate(),
                    Operator.GREAT_EQUALS));
            if (CommonUtil.isNotEmpty(cacheProdList))
            {
                for (CoProd p : cacheProdList)
                {
                    // 生效时间和主产品生效时间不一致的 都要上发，否则就会出现数据不一致的情况
                    // 反向条件的都会插入一条,会进行正常上发
                    if (p.getValidDate().after(dmProd.getValidDate()) || p.getValidDate().equals(dmProd.getValidDate()))
                    {
                        p.setExpireDate(context.getCommitDate());
                        context.cacheSingle(p);
                    }
                }
            }
        }

        List<CoProd> list = new ArrayList<CoProd>();
        for (CoProd prod : allProdList)
        {
            imsLogger.info("&&&&&&& product_id = ", prod.getProductId(), "; expire_date:", prod.getExpireDate());
            // so_nbr相同的不处理,订购，修改或者删除的时候处理;失效时间早于生效时间的也不用处理
            imsLogger.debug("***", prod.getSoNbr(), " and done_code: ", context.getSoNbr());
            if (prod.getSoNbr() == context.getSoNbr())
            {
                continue;
            }
            // 如果修改的id在当前传入的列表中，则不修改
            if (CommonUtil.isNotEmpty(modifyProdIdList))
            {
                if (modifyProdIdList.contains(prod.getProductId()))
                {
                    continue;
                }
            }
            // 新套餐生效的时候，该产品已经失效，则不需要做变化
            if (prod.getExpireDate().before(dmProd.getValidDate()) || prod.getExpireDate().equals(dmProd.getValidDate()))
            {
                imsLogger.info("****when main promotion valid,this prod had expired which product_id is :" + prod.getProductId());
                continue;
            }

            Integer pricePlanId = queryPricePlanByProd(prod, dmProd);
            // 设置到新主产品生效的时间失效

            deleteByCondition_noInsert(CoProd.class, dmProd.getValidDate(),
                    new DBCondition(CoProd.Field.productId, prod.getProductId()));
            CoProd otherProd = (CoProd) IMSUtil.copyDataObject(prod);
            // 创建一个新的产品，已经生效的产品从主产品的生效时间开始，未生效的产品从产品本身的生效时间开始
            otherProd.setSoNbr(context.getSoNbr());
            // 如果还没有生效，则生效时间不修改
            if (otherProd.getProdValidDate().before(dmProd.getValidDate()))
            {
                otherProd.setValidDate(dmProd.getValidDate());
            }
            if (pricePlanId != 0)
            {
                otherProd.setPricingPlanId(pricePlanId);
            }
            otherProd.setSoDate(context.getCommitDate());
            if (otherProd.getExpireDate().after(otherProd.getValidDate()))
            {
                list.add(otherProd);
            }
        }
        if (CommonUtil.isNotEmpty(list))
        {
            insertBatch(list);
        }
        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
    }

    /**
     * 修改其他产品的定价计划 dmProd 主产品 isCancleChange 是否换套餐取消，changeMainProdImme 是否换套餐立即生效
     */
    @SuppressWarnings("unused")
    private void changePricePlanByMainProduct(CoProd dmProd, boolean isCancelProd, boolean changeMainProdImme)
    {
        setContextHodlerByCoProd(dmProd);
        List<CoProd> allProdList = null;
        if (isCancelProd)
        {
            allProdList = this.query(CoProd.class, new DBCondition(CoProd.Field.objectId, dmProd.getObjectId()), new DBCondition(
                    CoProd.Field.objectType, dmProd.getObjectType()));
        }
        else
        {
            allProdList = queryProdList(dmProd.getObjectId(), dmProd.getObjectType());
        }
        if (CommonUtil.isEmpty(allProdList))
        {
            return;
        }
        Set<Long> modifyProdIdList = getModifyProdIdList(dmProd);
        for (CoProd prod : allProdList)
        {
            // so_nbr相同的不处理,订购，修改或者删除的时候处理;失效时间早于生效时间的也不用处理
            imsLogger.debug("***", prod.getSoNbr(), " and done_code: ", context.getSoNbr());
            if (prod.getSoNbr() == context.getSoNbr())
            {
                continue;
            }
            // 如果修改的id在当前传入的列表中，则不修改
            if (CommonUtil.isNotEmpty(modifyProdIdList))
            {
                if (modifyProdIdList.contains(prod.getProductId()))
                {
                    continue;
                }
            }
            Integer pricePlanId = queryPricePlanByProd(prod, dmProd);
            List<CoProd> list = new ArrayList<CoProd>();
            // 如果是换套餐取消 都进入该方法
            if (prod.getPricingPlanId().intValue() != pricePlanId)
            {
                // 新主产品生效的时候已经当前产品已经失效了，跳过,要操作的是主产品跳过
                if (prod.getExpireDate().before(dmProd.getValidDate()) || prod.getExpireDate().equals(dmProd.getValidDate())
                        || prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                {
                    continue;
                }
                deleteByCondition_noInsert(CoProd.class, context.getCommitDate(),
                        new DBCondition(CoProd.Field.productId, prod.getProductId()));
                // 1--------1
                // 分成2段，一段到新主产品生效为止，另一端从新主产品生效开始
                // 两个生效时间一样，会生成一条生效时间等于失效时间的数据，可以不用插于
                if (prod.getValidDate().getTime() != dmProd.getValidDate().getTime())
                {
                    CoProd newProd = (CoProd) IMSUtil.copyDataObject(prod);
                    // 这里从更新时间起开始生效
                    // 下周期生效的产品的生效时间不能变 lijc3 1.9号修改
                    newProd.setValidDate(context.getCommitDate());
                    newProd.setSoNbr(context.getSoNbr());
                    newProd.setExpireDate(dmProd.getValidDate());
                    newProd.setSoDate(context.getCommitDate());
                    // 失效时间大于生效时间才插入
                    if (newProd.getExpireDate().after(newProd.getValidDate()))
                    {
                        list.add(newProd);
                    }
                }
                CoProd otherProd = (CoProd) IMSUtil.copyDataObject(prod);
                otherProd.setSoNbr(context.getSoNbr());
                if (isCancelProd || changeMainProdImme)
                {
                    // 换套餐取消,换套餐立即生效并且产品未生效的，生效时间不能改
                    if (otherProd.getProdValidDate().before(context.getCommitDate()))
                    {
                        otherProd.setValidDate(dmProd.getValidDate());
                    }
                }
                else
                {
                    otherProd.setValidDate(dmProd.getValidDate());
                }
                otherProd.setPricingPlanId(pricePlanId);
                otherProd.setSoDate(context.getCommitDate());
                otherProd.setCreateDate(context.getCommitDate());
                if (otherProd.getExpireDate().after(otherProd.getValidDate()))
                {
                    list.add(otherProd);
                }
            }
            if (CommonUtil.isNotEmpty(list))
            {
                insertBatch(list);
            }
        }
        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
    }

    private void putProdComplex(short operType, ProdComplex complex)
    {
        // Map<Short,List<ProdComplex>> prodMap=context.getProdMap();
        Map<Short, Map<Long, List<ProdComplex>>> allProdMap = context.getAllProdMap();
        if (allProdMap == null)
        {
            allProdMap = new HashMap<Short, Map<Long, List<ProdComplex>>>();
            Map<Long, List<ProdComplex>> map = new HashMap<Long, List<ProdComplex>>();
            List<ProdComplex> complexList = new ArrayList<ProdComplex>();
            complexList.add(complex);
            if (complex.getProd().getUserId() != null)
            {
                map.put(complex.getProd().getUserId(), complexList);
            }
            else
            {
                // 不存在一次给多个账户或者虚账户订购产品的情况
                map.put(EnumCodeExDefine.ACCT_PROD_ID, complexList);
            }
            allProdMap.put(operType, map);
            context.setAllProdMap(allProdMap);
        }
        else
        {
            Map<Long, List<ProdComplex>> map = allProdMap.get(operType);
            if (map == null)
            {
                map = new HashMap<Long, List<ProdComplex>>();
                allProdMap.put(operType, map);
            }
            Long userId = complex.getProd().getUserId();
            if (userId != null)
            {
                List<ProdComplex> complexList = map.get(userId);
                if (CommonUtil.isEmpty(complexList))
                {
                    complexList = new ArrayList<ProdComplex>();
                    complexList.add(complex);
                    map.put(complex.getProd().getUserId(), complexList);
                    allProdMap.put(operType, map);
                }
                else
                {
                    complexList.add(complex);
                }
            }
            else
            {
                List<ProdComplex> complexList = map.get(EnumCodeExDefine.ACCT_PROD_ID);
                if (CommonUtil.isEmpty(complexList))
                {
                    complexList = new ArrayList<ProdComplex>();
                    complexList.add(complex);
                    map.put(EnumCodeExDefine.ACCT_PROD_ID, complexList);
                    allProdMap.put(operType, map);
                }
                else
                {
                    complexList.add(complex);
                }
            }
        }

        // if(prodMap==null){
        // prodMap=new HashMap<Short, List<ProdComplex>>();
        // List<ProdComplex> prodList=new ArrayList<ProdComplex>();
        // prodList.add(complex);
        // prodMap.put(operType, prodList);
        // context.setProdMap(prodMap);
        // }else{
        // List<ProdComplex> prodList=prodMap.get(operType);
        // if(CommonUtil.isEmpty(prodList)){
        // prodList=new ArrayList<ProdComplex>();
        // }
        // prodList.add(complex);
        // prodMap.put(operType, prodList);
        // }
    }

    // 处理产品派生
    private void enloveProducts(short operType, ProdComplex complex, Set<Long> enModifyList)
    {
        CoProd dmProd = complex.getDmProd();
        List<CoProd> mainProdList = null;
        if (dmProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            mainProdList = getMainProdList(dmProd.getObjectId());
        }
        if (operType == EnumCodeDefine.CHANGE_PROD_ADD)
        {
            List<CoProdMapping> mappList = complex.getMapps();
            if (CommonUtil.isEmpty(mappList))
            {
                mappList = new ArrayList<CoProdMapping>();
                complex.setMapps(mappList);
            }
            List<Integer> offerIds = queryPmOfferMapping(dmProd.getProductOfferingId(), null,
                    EnumCodeDefine.PRODUCT_REL_ONE_2_MORE);

            if (CommonUtil.isNotEmpty(offerIds))
            {
                // context.get3hTree().loadOfferList3hBean(offerIds);
                for (Integer offerId : offerIds)
                {
                    ProdComplex newComplex = transferCoProd2ProdComplex(complex, operType, offerId, null, null, null);
                    if (newComplex != null)
                    {
                        putProdComplex(operType, newComplex);
                        addProductInfo(newComplex, true);
                        // 创建映射关系
                        mappList.add(evolveProductMapping(dmProd, newComplex.getDmProd(), EnumCodeDefine.PRODUCT_REL_ONE_2_MORE));
                    }
                }
            }

            if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
            {
                Set<Long> modOrDelList = getModifyAndDeleteProdIdList(dmProd);

                // 如果订购了主产品，需要看是否还有未变更的产品需要订购优惠
                setContextHodlerByCoProd(dmProd);
                List<CoProd> dbProdList = null;
                if (context.getExtendParam("OLD_PRODUCT_LIST" + dmProd.getObjectId()) != null)
                {
                    dbProdList = (List<CoProd>) context.getExtendParam("OLD_PRODUCT_LIST" + dmProd.getObjectId());
                }
                else
                {
                    dbProdList = this.query(CoProd.class, new DBCondition(CoProd.Field.objectId, dmProd.getObjectId()),
                            new DBCondition(CoProd.Field.objectType, dmProd.getObjectType()));
                }
                if (CommonUtil.isNotEmpty(dbProdList))
                {
                    dbProdList = mergeProdList(dbProdList);
                    for (CoProd p : dbProdList)
                    {
                        imsLogger.info("***** prod offering id is ", p.getProductOfferingId(), " ; product_id is ",
                                p.getProductId());
                        // 不在修改或者删除的列表中，需要判断优惠
                        if (!modOrDelList.contains(p.getProductId()))
                        {
                            offerIds = queryPmOfferMappingByMainProduct(p.getProductOfferingId(), dmProd.getProductOfferingId(),
                                    EnumCodeDefine.PRODUCT_REL_ENVLOPROD);
                            if (CommonUtil.isNotEmpty(offerIds))
                            {
                                for (Integer offerId : offerIds)
                                {
                                    // 返回可能为null；
                                    ProdComplex newComplex = transferCoProd2ProdComplex(complex, operType, offerId, null,
                                            p.getValidDate(), p.getExpireDate());
                                    if (newComplex != null)
                                    {
                                        // 存放
                                        putProdComplex(operType, newComplex);
                                        addProductInfo(newComplex, false);
                                        // 创建映射关系
                                        mappList.add(evolveProductMapping(p, newComplex.getDmProd(),
                                                EnumCodeDefine.PRODUCT_REL_ENVLOPROD));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (CommonUtil.isNotEmpty(mainProdList))
            {
                // 相同的进行合并
                mainProdList = mergeProdList(mainProdList);
                for (CoProd mainProd : mainProdList)
                {
                    offerIds = queryPmOfferMapping(dmProd.getProductOfferingId(), mainProd.getProductOfferingId(),
                            EnumCodeDefine.PRODUCT_REL_ENVLOPROD);
                    if (CommonUtil.isNotEmpty(offerIds))
                    {
                        for (Integer offerId : offerIds)
                        {
                            // 返回可能为null；
                            ProdComplex newComplex = transferCoProd2ProdComplex(complex, operType, offerId, null,
                                    mainProd.getValidDate(), mainProd.getExpireDate());
                            if (newComplex != null)
                            {
                                // 存放
                                putProdComplex(operType, newComplex);
                                addProductInfo(newComplex, false);
                                // 创建映射关系
                                mappList.add(evolveProductMapping(dmProd, newComplex.getDmProd(),
                                        EnumCodeDefine.PRODUCT_REL_ENVLOPROD));
                            }
                        }
                    }
                }
            }
        }
        else if (operType == EnumCodeDefine.CHANGE_PROD_MODIFY)
        {
            setContextHodlerByCoProd(dmProd);
            // 修改主产品，如果不是设置下周期失效，则不处理配生产品。
            if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
            {
                Date oldExpireDate = complex.getOldExpireDate();
                if (oldExpireDate != null && complex.getProd().getExpireDate().equals(oldExpireDate))
                {
                    return;
                }
            }
            // one2more 1个变多个的处理
            List<Long> productIdList = queryProdMapping(dmProd);
            if (CommonUtil.isNotEmpty(productIdList))
            {
                for (Long productId : productIdList)
                {
                    if (enModifyList.contains(productId))
                    {
                        continue;
                    }
                    else
                    {
                        enModifyList.add(productId);
                    }
                    ProdComplex newComplex = transferCoProd2ProdComplex(complex, operType, null, productId, null, null);
                    if (newComplex != null)
                    {
                        modifyProductInfo(newComplex, enModifyList, false);
                        putProdComplex(operType, newComplex);
                        // 修改映射表的失效时间
                        try
                        {
                            CoProdMapping mapp = new CoProdMapping();
                            mapp.setExpireDate(newComplex.getProd().getExpireDate());
                            DBUtil.updateByCondition(mapp, new DBCondition(CoProdMapping.Field.relProduct, productId),
                                    new DBCondition(CoProdMapping.Field.objectId, dmProd.getObjectId()));
                        }
                        catch (Exception e)
                        {
                            imsLogger.info("****exception  when modify expire_date");
                        }
                    }
                }
            }
            // 2013-04-23 原来产品的失效时间是2037-13-31以后的时间，进行延长的时候不考虑下面的操作
            Date defaultDate = IMSUtil.getDefaultExpireDate();
            if (dmProd.getProdExpireDate().equals(defaultDate) || dmProd.getProdExpireDate().after(defaultDate))
            {
                ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
                return;
            }
            dealExtendProdNextCycle(complex);

            ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
        }
    }

    /**
     * lijc3 2013-4-11 延长产品的时候判断下周期的是否需要订购优惠
     * 
     * @param complex
     */
    private void dealExtendProdNextCycle(ProdComplex complex)
    {
        Date oldExpireDate = complex.getOldExpireDate();
        if (oldExpireDate == null)
        {
            return;
        }
        Date expireDate = complex.getProd().getExpireDate();
        imsLogger.debug("expire_date:", expireDate);
        imsLogger.debug("old_expire_date :", oldExpireDate);
        if (!expireDate.after(oldExpireDate))
        {
            return;
        }
        CoProd dmProd = complex.getDmProd();
        if (dmProd.getObjectType() != EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            return;
        }
        setContextHodlerByCoProd(dmProd);
        List<Integer> offerIds = null;
        if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_COMMON)
        {
            List<CoProd> mainProdList = getMainProdList(dmProd.getObjectId());
            if (CommonUtil.isEmpty(mainProdList))
            {
                return;
            }
            mainProdList = mergeProdList(mainProdList);
            for (CoProd mainProd : mainProdList)
            {
                imsLogger.debug("***old_expire_date is ", oldExpireDate);
                // 之前普通产品和主产品的生失效时间没有交叉才要做下面的操作
                // !mainProd.getProdValidDate().before(oldExpireDate)
                if (oldExpireDate.after(mainProd.getProdValidDate()))// oldExpireDate.after(mainProdValidDate)||equals(mainProdValidDate)
                {
                    continue;
                }
                offerIds = queryPmOfferMappingByMainProduct(dmProd.getProductOfferingId(), mainProd.getProductOfferingId(),
                        EnumCodeDefine.PRODUCT_REL_ENVLOPROD);
                if (CommonUtil.isEmpty(offerIds))
                {
                    continue;
                }
                for (Integer offerId : offerIds)
                {
                    CoProd p = this.querySingle(CoProd.class, new DBCondition(CoProd.Field.objectId, dmProd.getObjectId()),
                            new DBCondition(CoProd.Field.productOfferingId, offerId));
                    // 已经订购了这个优惠销售品，就不再订购
                    if (p != null)
                    {
                        continue;
                    }
                    // 返回可能为null；
                    ProdComplex newComplex = transferCoProd2ProdComplex(complex, EnumCodeDefine.CHANGE_PROD_ADD, offerId, null,
                            mainProd.getValidDate(), mainProd.getExpireDate());
                    if (newComplex != null)
                    {
                        List<CoProdMapping> mappList = new ArrayList<CoProdMapping>();
                        newComplex.setMapps(mappList);
                        // 存放
                        putProdComplex(EnumCodeDefine.CHANGE_PROD_ADD, newComplex);
                        addProductInfo(newComplex, false);
                        // 创建映射关系
                        mappList.add(evolveProductMapping(dmProd, newComplex.getDmProd(), EnumCodeDefine.PRODUCT_REL_ENVLOPROD));
                    }
                }
            }
        }
        else
        {
            // 延长主产品
            List<CoProd> prodList = this.query(CoProd.class, new DBCondition(CoProd.Field.objectId, dmProd.getObjectId()),
                    new DBCondition(CoProd.Field.isMain, EnumCodeDefine.PRODUCT_MAIN, Operator.NOT_EQUALS));
            if (CommonUtil.isEmpty(prodList))
            {
                return;
            }
            prodList = mergeProdList(prodList);
            // 用户订购的销售品列表
            Set<Integer> hadIdList = new HashSet<Integer>();
            for (CoProd p : prodList)
            {
                imsLogger.debug("***add product_offer_id :", p.getProductOfferingId());
                hadIdList.add(p.getProductOfferingId());
            }
            for (CoProd p : prodList)
            {
                // 450优惠产品过滤
                if (String.valueOf(p.getProductOfferingId()).startsWith("450"))
                {
                    imsLogger.debug("***450销售品不配生");
                    continue;
                }
                offerIds = queryPmOfferMappingByMainProduct(p.getProductOfferingId(), dmProd.getProductOfferingId(),
                        EnumCodeDefine.PRODUCT_REL_ENVLOPROD);
                if (CommonUtil.isEmpty(offerIds))
                {
                    continue;
                }
                for (Integer offerId : offerIds)
                {
                    // 已经订购了这个优惠销售品，就不再订购
                    if (hadIdList.contains(offerId))
                    {
                        continue;
                    }
                    // 返回可能为null；
                    ProdComplex newComplex = transferCoProd2ProdComplex(complex, EnumCodeDefine.CHANGE_PROD_ADD, offerId, null,
                            p.getValidDate(), p.getExpireDate());
                    if (newComplex != null)
                    {
                        List<CoProdMapping> mappList = new ArrayList<CoProdMapping>();
                        newComplex.setMapps(mappList);
                        // 存放
                        putProdComplex(EnumCodeDefine.CHANGE_PROD_ADD, newComplex);
                        addProductInfo(newComplex, false);
                        // 创建映射关系
                        mappList.add(evolveProductMapping(p, newComplex.getDmProd(), EnumCodeDefine.PRODUCT_REL_ENVLOPROD));
                    }
                }
            }
        }

    }

    private List<Integer> queryPmOfferMappingByMainProduct(Integer offerId, Integer mainOfferId, int relationType)
    {
        List<PmProductOfferMapping> mappList = this.query(PmProductOfferMapping.class, new DBCondition(
                PmProductOfferMapping.Field.productOfferingId, offerId), new DBCondition(
                PmProductOfferMapping.Field.refMainOffering, mainOfferId), new DBCondition(
                PmProductOfferMapping.Field.relationTypeId, relationType));
        List<Integer> idList = new ArrayList<Integer>();
        if (CommonUtil.isNotEmpty(mappList))
        {
            for (PmProductOfferMapping mapp : mappList)
            {
                idList.add(mapp.getRelProductOffering());
            }
        }
        return idList;
    }

    /**
     * lijc3 2012-6-15 转换到ProdComplex
     * 
     * @param complex
     * @param operType
     * @param offerId 和productId不能同时为空
     * @param productId
     * @return
     */
    private ProdComplex transferCoProd2ProdComplex(ProdComplex complex, short operType, Integer offerId, Long productId,
            Date validDate, Date expireDate)
    {
        CoProd dmProd = complex.getDmProd();
        imsLogger.debug("***dmprod.valid_date is ", dmProd.getValidDate());
        // 增加的时候不能有生失效时间 的不交叉
        // 2013-04-01 加
        if (operType == EnumCodeDefine.CHANGE_PROD_ADD)
        {
            // 2013-04-22 正常新增 这个字段的值为空。
            if (complex.getOldExpireDate() == null)
            {
                imsLogger.debug("****add...");
                if (validDate != null && !validDate.before(dmProd.getExpireDate()))
                {
                    return null;
                }
                if (expireDate != null && !expireDate.after(dmProd.getValidDate()))
                {
                    return null;
                }
            }
        }
        ProdComplex cp = new ProdComplex();
        IProduct prod = new IProduct();
        // if(dmProd.getObjectId()==EnumCodeDefine.PROD_OBJECTTYPE_DEV){
        // prod.setUserId(dmProd.getObjectId());
        // }else{
        // prod.setAcctId(dmProd.getObjectId());
        // }
        prod.setProductType(0); //普通的，写死0
        prod.setAcctId(complex.getProd().getAcctId());
        prod.setUserId(complex.getProd().getUserId());
        prod.setCommitDate(context.getCommitDate());
        prod.setProductStatus(complex.getProd().getProductStatus());
        if (offerId != null)
        {
            prod.setOfferId(offerId.longValue());
        }
        if (validDate != null && validDate.after(dmProd.getValidDate()))
        {
            prod.setValidDate(validDate);
        }
        else
        {
            prod.setValidDate(dmProd.getValidDate());
        }
        if (expireDate != null && expireDate.before(dmProd.getExpireDate()))
        {
            prod.setExpireDate(expireDate);
        }
        else
        {
            prod.setExpireDate(dmProd.getExpireDate());
        }
        // 2013-04-22 新增代码
        if (operType == EnumCodeDefine.CHANGE_PROD_ADD && complex.getOldExpireDate() != null)
        {
            prod.setExpireDate(complex.getProd().getExpireDate());
        }
        // 修改使用传入的失效时间作为更新
        if (operType == EnumCodeDefine.CHANGE_PROD_MODIFY)
        {
            prod.setExpireDate(complex.getProd().getExpireDate());
        }
        imsLogger.debug("**派生出来的产品的生效时间为:", prod.getValidDate());
        prod.setSoNbr(context.getSoNbr());
        cp.setProd(prod);
        if (productId != null)
        {
            setContextHodlerByCoProd(dmProd);
            prod.setProductId(productId);
            List<CoProd> dmProdList = queryProdList(prod.getProductId());
            ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
            if (CommonUtil.isNotEmpty(dmProdList))
            {
                CoProd coProd = mergeProd(dmProdList);
                // 设置失效时间，后续算定价计划用,更新或者删除的时候，不以这个时间为更新时间
                dmProd.setExpireDate(coProd.getExpireDate());
                cp.setDmProd(coProd);
                cp.setDmProdList(dmProdList);
                cp.setOldExpireDate(coProd.getProdExpireDate());
            }
            else
            {
                return null;
            }
        }
        else
        {
            prod.setProductId(DBUtil.getSequence(CoProd.class));
        }
        // 201305013 add 二次议价也需要copy到派生的产品中
        cp.setParamList(complex.getParamList());
        if (CommonUtil.isNotEmpty(complex.getValueList()))
        {
            cp.setValueList(copyCharValue(complex.getValueList(), cp));
        }
        return cp;
    }

    // 特征值copy
    private List<IProdCharValue> copyCharValue(List<IProdCharValue> oldList, ProdComplex newComplex)
    {
        Long offerId = newComplex.getProd().getOfferId();
        if (offerId == null)
        {
            offerId = newComplex.getDmProd().getProductOfferingId().longValue();
        }
        List<Integer> specIds = querySpecCharIdsByOfferId(offerId.intValue());
        if (CommonUtil.isEmpty(specIds))
        {
            return null;
        }
        List<IProdCharValue> newList = new ArrayList<IProdCharValue>();
        for (IProdCharValue charValue : oldList)
        {
            if (specIds.contains(charValue.getParamId()))
            {
                newList.add(charValue);
            }
        }
        return CommonUtil.isNotEmpty(newList) ? newList : null;
    }

    /**
     * 计算定价计划 lijc3 2012-6-14 userId 可以位null
     */
    private void calculatePricePlan(Long userId)
    {
        Map<Short, Map<Long, List<ProdComplex>>> allProdMap = context.getAllProdMap();
        Map<Long, List<ProdComplex>> prodMap = allProdMap.get(EnumCodeDefine.CHANGE_PROD_ADD);
        if (CommonUtil.isEmpty(prodMap))
        {
            return;
        }
        if (userId != null)
        {
            // 获取用户所有主产品
            List<CoProd> maiProdList = getMainProdList(userId);
            // PrintUtil.print("maiProdList", maiProdList);
            imsLogger.debug("maiProdList", maiProdList);
            List<ProdComplex> addList = prodMap.get(userId);
            if (CommonUtil.isNotEmpty(addList))
            {
                for (ProdComplex complex : addList)
                {
                    calculatePricePlan(complex, maiProdList);
                }
            }
        }
        else
        {
            List<ProdComplex> addList = prodMap.get(EnumCodeExDefine.ACCT_PROD_ID);
            if (CommonUtil.isNotEmpty(addList))
            {
                for (ProdComplex complex : addList)
                {
                    calculatePricePlan(complex, null);
                }
            }
        }
    }

    public List<CoProd> calculteProdPricePlan(CoProd dmProd, List<CoProd> mainProdList, List<CoProdCharValue> valueList,
            List<CoProdPriceParam> paramList)
    {
        // {// 考虑主产品
        List<CoProd> prodList = new ArrayList<CoProd>();
        Integer tempPricePlanId = 0;
        if (context.getBusiCode() != EnumCodeExDefine.GX_MANUAL_MODIFY_PROD && CommonUtil.isNotEmpty(mainProdList))
        {
            for (CoProd coProd : mainProdList)
            {
                tempPricePlanId = queryPricePlanId(dmProd.getProductOfferingId(), paramList, null, valueList,
                        coProd.getProductOfferingId(), null, null);
                if (tempPricePlanId != 0)
                {
                    break;
                }
            }
            dmProd.setPricingPlanId(tempPricePlanId);
            imsLogger.debug(" ***** product_id = ", dmProd.getProductId(), " price_plan_id = ", tempPricePlanId);
            List<CoProd> dmProdList = new ArrayList<CoProd>();
            for (CoProd coProd : mainProdList)
            {
                Date expire = coProd.getExpireDate();
                if (context.getExtendParam("MAIN_PROD_EXPIREDATE" + coProd.getProductId()) != null)
                {
                    expire = (Date) context.getExtendParam("MAIN_PROD_EXPIREDATE" + coProd.getProductId());
                }
                imsLogger.debug("expire_date of main_product" + expire);
                imsLogger.debug("valid_date " + coProd.getValidDate());
                // 产品生效的时候 这个主产品失效了 跳过
                // if (dmProd.getExpireDate().before(coProd.getValidDate()) || !expire.after(dmProd.getValidDate())
                // || !coProd.getValidDate().before(expire))
                // {
                // continue;
                // }
                // 失效时间小于等于主产品生效时间的，跳过
                if (dmProd.getExpireDate().before(coProd.getValidDate()) || dmProd.getExpireDate().equals(coProd.getValidDate()))
                {
                    imsLogger.debug("失效时间小于等于主产品生效时间的，跳过");
                    continue;
                }
                // 生效时间大于等于主产品的，跳过
                if (dmProd.getValidDate().after(expire) || dmProd.getValidDate().equals(expire))
                {
                    imsLogger.debug("生效时间大于等于主产品的，跳过");
                    continue;
                }
                if (!coProd.getValidDate().before(expire))
                {
                    continue;
                }
                // 主产品 1------------------1-----------1
                // a b c 1----------1
                // 产品 1-----1 1----1 1-------1
                // d
                // 1--------------------------1
                Integer pricePlanId = queryPricePlanId(dmProd.getProductOfferingId(), paramList, null, valueList,
                        coProd.getProductOfferingId(), null, null);
                // 设置一个主产品生效晚的为定价计划

                // dmProd.setPricingPlanId(pricePlanId);

                if (dmProd.getValidDate().before(coProd.getValidDate()) || dmProd.getValidDate().equals(coProd.getValidDate()))
                {
                    if (dmProd.getExpireDate().after(expire))
                    {
                        CoProd a = (CoProd) IMSUtil.copyDataObject(dmProd);
                        a.setValidDate(coProd.getValidDate());
                        a.setExpireDate(expire);
                        a.setPricingPlanId(pricePlanId);
                        if (a.getExpireDate().after(a.getValidDate()))
                        {
                            dmProdList.add(a);
                        }
                    }
                    else
                    {
                        CoProd a = (CoProd) IMSUtil.copyDataObject(dmProd);
                        a.setPricingPlanId(pricePlanId);
                        a.setValidDate(coProd.getValidDate());
                        if (a.getExpireDate().after(a.getValidDate()))
                        {
                            dmProdList.add(a);
                        }
                    }
                }
                else
                {
                    if (dmProd.getExpireDate().after(expire) || dmProd.getExpireDate().equals(expire))
                    {
                        CoProd a = (CoProd) IMSUtil.copyDataObject(dmProd);
                        a.setPricingPlanId(pricePlanId);
                        a.setExpireDate(expire);
                        if (a.getExpireDate().after(a.getValidDate()))
                        {
                            dmProdList.add(a);
                        }
                    }
                    else
                    {
                        dmProd.setPricingPlanId(pricePlanId);
                        dmProdList.add(dmProd);
                    }
                }
            }
            if (CommonUtil.isEmpty(dmProdList))
            {
                prodList.add(dmProd);
            }
            else
            {
                for (CoProd p : dmProdList)
                {
                    if (p.getPricingPlanId() == null || p.getPricingPlanId() == 0)
                    {
                        p.setPricingPlanId(tempPricePlanId);
                    }
                }
                prodList.addAll(dmProdList);
            }
        }
        else
        {
            // 考虑特征值
            Integer pricePlanId = queryPricePlanId(dmProd.getProductOfferingId(), paramList, null, valueList, null, null, null);
            dmProd.setPricingPlanId(pricePlanId);
            prodList.add(dmProd);
        }

        return prodList;
    }

    /**
     * lijc3 2012-6-14 计算定价计划
     * 
     * @param complex
     * @param mainProdList
     */
    private void calculatePricePlan(ProdComplex complex, List<CoProd> mainProdList)
    {
        List<CoProd> prodList = calculteProdPricePlan(complex.getDmProd(), mainProdList, complex.getDmValueList(),
                complex.getDmParamList());
        complex.setDmProdList(prodList);
        /*
         * CoProd dmProd = complex.getDmProd(); // {// 考虑主产品 if (CommonUtil.isNotEmpty(mainProdList)) { List<CoProd> dmProdList =
         * new ArrayList<CoProd>(); for (CoProd coProd : mainProdList) { Date expire = coProd.getExpireDate(); if
         * (context.getExtendParam("MAIN_PROD_EXPIREDATE" + coProd.getProductId()) != null) { expire = (Date)
         * context.getExtendParam("MAIN_PROD_EXPIREDATE" + coProd.getProductId()); } imsLogger.debug("expire_date of main_product"
         * + expire); imsLogger.debug("valid_date " + coProd.getValidDate()); // 产品生效的时候 这个主产品失效了 跳过 if
         * (dmProd.getExpireDate().before(coProd.getValidDate()) || !expire.after(dmProd.getValidDate()) ||
         * !coProd.getValidDate().before(expire)) { continue; } // 主产品 1------------------1-----------1 // a b c // 产品 1-----1
         * 1----1 1-------1 // d // 1--------------------------1 Integer pricePlanId =
         * queryPricePlanId(dmProd.getProductOfferingId(), complex.getDmParamList(), null, complex.getDmValueList(),
         * coProd.getProductOfferingId(), null, null); // 设置一个主产品生效晚的为定价计划 dmProd.setPricingPlanId(pricePlanId); if
         * (dmProd.getValidDate().before(coProd.getValidDate()) || dmProd.getValidDate().equals(coProd.getValidDate())) { if
         * (dmProd.getExpireDate().after(expire)) { CoProd a = (CoProd) IMSUtil.copyDataObject(dmProd);
         * a.setValidDate(coProd.getValidDate()); a.setExpireDate(expire); a.setPricingPlanId(pricePlanId); if
         * (!a.getExpireDate().equals(a.getValidDate())) { dmProdList.add(a); } } else { CoProd a = (CoProd)
         * IMSUtil.copyDataObject(dmProd); a.setPricingPlanId(pricePlanId); a.setValidDate(coProd.getValidDate()); if
         * (!a.getExpireDate().equals(a.getValidDate())) { dmProdList.add(a); } } } else { if
         * (dmProd.getExpireDate().after(expire)) { CoProd a = (CoProd) IMSUtil.copyDataObject(dmProd);
         * a.setPricingPlanId(pricePlanId); a.setExpireDate(expire); if (!a.getExpireDate().equals(a.getValidDate())) {
         * dmProdList.add(a); } } else { dmProdList.add(dmProd); } } } if (CommonUtil.isEmpty(dmProdList)) { Integer pricePlanId =
         * queryPricePlanId(dmProd.getProductOfferingId(), complex.getDmParamList(), null, complex.getDmValueList(), null, null,
         * null); dmProd.setPricingPlanId(pricePlanId); } else { complex.setDmProdList(dmProdList); } } else { // 考虑特征值 Integer
         * pricePlanId = queryPricePlanId(dmProd.getProductOfferingId(), complex.getDmParamList(), null, complex.getDmValueList(),
         * null, null, null); dmProd.setPricingPlanId(pricePlanId); }
         */

    }

    // 暂时没用
    public CoProd getOverrideCoProd(CoProd dmProd, Integer tarOfferId)
    {
        Map<Short, Map<Long, List<ProdComplex>>> allMap = context.getAllProdMap();
        if (CommonUtil.isEmpty(allMap))
        {
            return null;
        }
        Map<Long, List<ProdComplex>> addMap = allMap.get(EnumCodeDefine.CHANGE_PROD_ADD);
        if (CommonUtil.isEmpty(addMap))
        {
            return null;
        }
        List<ProdComplex> addList = addMap.get(dmProd.getObjectId());
        if (CommonUtil.isNotEmpty(addList))
        {
            for (ProdComplex complex : addList)
            {
                if (complex.getDmProd().getProductOfferingId().intValue() == tarOfferId)
                {
                    return complex.getDmProd();
                }
            }
        }
        Map<Long, List<ProdComplex>> upMap = allMap.get(EnumCodeDefine.CHANGE_PROD_ADD);
        if (CommonUtil.isEmpty(upMap))
        {
            return null;
        }
        List<ProdComplex> updateList = upMap.get(dmProd.getObjectId());
        if (CommonUtil.isNotEmpty(updateList))
        {
            for (ProdComplex complex : updateList)
            {
                if (complex.getDmProd().getProductOfferingId().intValue() == tarOfferId)
                {
                    return complex.getDmProd();
                }
            }
        }
        // 获取特定的销售品的产品
        List<CoProd> existList = queryProdByUserIdAndOfferId(dmProd.getObjectId(), dmProd.getObjectType(), tarOfferId);
        if (CommonUtil.isEmpty(existList))
        {
            return null;
        }
        Map<Long, List<ProdComplex>> delMap = allMap.get(EnumCodeDefine.CHANGE_PROD_ADD);
        if (CommonUtil.isEmpty(delMap))
        {
            return null;
        }
        List<ProdComplex> delList = delMap.get(dmProd.getObjectId());
        if (CommonUtil.isEmpty(delList))
        {
            return existList.get(0);// 取第一个
        }
        for (CoProd prod : existList)
        {
            for (ProdComplex complex : delList)
            {
                if (complex.getDmProd().getProductId().longValue() != prod.getProductId())
                {
                    return prod;
                }
                else
                {
                    continue;
                }
            }
        }
        return null;
    }

    /**
     * 获取主产品 lijc3 2012-9-14
     * 
     * @param userId
     * @return
     */
    private List<CoProd> getMainProdList(Long userId)
    {
        if (userId == null)
        {
            return null;
        }
        String key = "MAIN_PROD_LIST" + userId;
        if (context.getExtendParam(key) != null)
        {
            return (List<CoProd>) context.getExtendParam(key);
        }
        else
        {
            List<CoProd> prodList = new ArrayList<CoProd>();
            Map<Short, Map<Long, List<ProdComplex>>> allMap = context.getAllProdMap();
            if (CommonUtil.isEmpty(allMap))
            {
                return null;
            }
            // 删除列表里产品都是立即失效的，不考虑
            Map<Long, List<ProdComplex>> addMap = allMap.get(EnumCodeDefine.CHANGE_PROD_ADD);
            Map<Long, List<ProdComplex>> upMap = allMap.get(EnumCodeDefine.CHANGE_PROD_MODIFY);
            List<ProdComplex> addList = null;
            List<ProdComplex> updateList = null;
            if (CommonUtil.isNotEmpty(addMap))
            {
                addList = addMap.get(userId);
            }
            if (CommonUtil.isNotEmpty(upMap))
            {
                updateList = upMap.get(userId);
            }
            Long busiCode = context.getBusiCode() == null ? 0 : context.getBusiCode();
            if (CommonUtil.isNotEmpty(addList))
            {
                for (ProdComplex complex : addList)
                {
                    CoProd dmProd = complex.getDmProd();
                    if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                    {
                        prodList.add(dmProd);
                        // 如果不是全量覆盖，则只取一个主产品，否则，就取到所有主产品为止
                        if (busiCode != EnumCodeExDefine.ALL_DELETE_BUSI_CODE && busiCode != EnumCodeExDefine.GX_MANUAL_MODIFY_PROD)
                        {
                            break;
                        }
                    }
                }
            }
            if (CommonUtil.isNotEmpty(updateList))
            {
                for (ProdComplex complex : updateList)
                {
                    CoProd dmProd = complex.getDmProd();
                    
                    if (dmProd !=null && dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
                    {
                        // 2013-04-01 加
                        // prodList.add(complex.getDmProd());
                        CoProd p = (CoProd) IMSUtil.copyDataObject(dmProd);
                        p.setExpireDate(complex.getProd().getExpireDate());
                        prodList.add(p);
                    }
                }
            }
            // 新增的没有，修改的也没有，那么从数据库查出主产品,可能也是分两段的
            if (CommonUtil.isEmpty(prodList))
            {
            	RouterCmp routeCmp = context.getCmp(RouterCmp.class);
            	Long acctId=routeCmp.queryAcctIdByUserId(userId);
                ITableUtil.setValue2ContextHolder(null, acctId, null);
                List<CoProd> existList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, userId), new DBCondition(
                        CoProd.Field.isMain, EnumCodeDefine.PRODUCT_MAIN));
                ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
                // 相同产品id的进行合并
                existList = mergeProdList(existList);
                if (CommonUtil.isNotEmpty(existList))
                {
                    for (CoProd prod : existList)
                    {
                        if (prod.getValidDate().before(prod.getExpireDate()))
                        {
                            prodList.add(prod);
                        }
                    }
                }
            }
            context.addExtendParam(key, prodList);
            return prodList;
        }
    }

    // 创建multi-sim,fax-number
    private List<CmResIdentity> createIndentity(CoProd dmProd, List<CoProdCharValue> dmValueList)
    {
        if (dmProd.getObjectType() != EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            return null;
        }
        if (CommonUtil.isEmpty(dmValueList))
        {
            return null;
        }
        List<CmResIdentity> idenList = new ArrayList<CmResIdentity>();
        for (CoProdCharValue value : dmValueList)
        {
            if (value.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER || value.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
            {
                idenList.add(createIdentityByProduct(dmProd, value.getValue()));
            }
        }
        return idenList;
    }

    private String getSpecCharValueById(int specCharId, List<PmProductSpecCharValue> result)
    {
        // 如果未配置，直接抛异常
        if (CommonUtil.isEmpty(result))
        {
            return null;
        }
        for (PmProductSpecCharValue value : result)
        {
            if (value.getSpecCharId() == specCharId)
            {
                return value.getValue();
            }
        }
        return null;
    }

    // 创建特征值
    @SuppressWarnings("unused")
    private void createCoProdCharValue(ProdComplex complex)
    {
        // 添加默认特征值
        CoProd dmProd = complex.getDmProd();
        if (dmProd.getBusiFlag() == SpecCodeDefine.BUDGET)
        {
            List<IProdCharValue> iValueList = complex.getValueList();
            if (CommonUtil.isEmpty(iValueList))
            {
                imsLogger.debug("****no i_prod_char_value in,return");
                return;
            }
            List<Integer> idList = new ArrayList<Integer>();
            idList.add(SpecCodeDefine.BUDGET_RULE);
            idList.add(SpecCodeDefine.BUDGET_PAYMODE);
            idList.add(SpecCodeDefine.BUDGET_OBJECT_TYPE);
            idList.add(SpecCodeDefine.BUDGET_MEASURE_ID);
            idList.add(SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_TYPE);
            idList.add(SpecCodeDefine.BUDGET_ACTION);
            List<PmProductSpecCharValue> configList = context.getCmp(SplitCmp.class).getSpecCharValueListByIdList(idList);
            String action = getSpecCharValueById(SpecCodeDefine.BUDGET_ACTION, configList);
            String notifyType = getSpecCharValueById(SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_TYPE, configList);
            String measureId = getSpecCharValueById(SpecCodeDefine.BUDGET_MEASURE_ID, configList);
            String objectType = getSpecCharValueById(SpecCodeDefine.BUDGET_OBJECT_TYPE, configList);
            String payMode = getSpecCharValueById(SpecCodeDefine.BUDGET_PAYMODE, configList);
            String rule = getSpecCharValueById(SpecCodeDefine.BUDGET_RULE, configList);
            if (CommonUtil.isEmpty(rule))
            {
                throw IMSUtil.throwBusiException(ErrorCodeExDefine.PM_PROD_CHAR_VALUE_NO_DEFAULT_VALUE,
                        SpecCodeDefine.BUDGET_RULE);
            }

            List<CoBudgetCharValue> valueList = new ArrayList<CoBudgetCharValue>();
            Long groupId = DBUtil.getSequence(CoProdCharValue.class);
            valueList.add(createBudgetCharValue(dmProd, groupId, SpecCodeDefine.BUDGET_RULE, rule, dmProd.getValidDate(),
                    dmProd.getExpireDate()));
            valueList.add(createBudgetCharValue(dmProd, groupId, SpecCodeDefine.BUDGET_ACTION, action == null ? String.valueOf(3)
                    : action, dmProd.getValidDate(), dmProd.getExpireDate()));
            valueList.add(createBudgetCharValue(dmProd, groupId, SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_TYPE,
                    notifyType == null ? String.valueOf(1) : notifyType, dmProd.getValidDate(), dmProd.getExpireDate()));
            valueList.add(createBudgetCharValue(dmProd, groupId, SpecCodeDefine.BUDGET_MEASURE_ID,
                    measureId == null ? String.valueOf(10403) : measureId, dmProd.getValidDate(), dmProd.getExpireDate()));
            valueList.add(createBudgetCharValue(dmProd, groupId, SpecCodeDefine.BUDGET_OBJECT_TYPE,
                    objectType == null ? String.valueOf(1) : objectType, dmProd.getValidDate(), dmProd.getExpireDate()));
            valueList.add(createBudgetCharValue(dmProd, groupId, SpecCodeDefine.BUDGET_PAYMODE,
                    payMode == null ? String.valueOf(1) : payMode, dmProd.getValidDate(), dmProd.getExpireDate()));
            for (IProdCharValue charValue : iValueList)
            {
                valueList.add(createBudgetCharValue(dmProd, groupId, charValue.getParamId(), charValue.getParamValue(),
                        charValue.getValidDate(), charValue.getExpireDate()));
            }
            complex.setBudgetList(valueList);
        }
        else
        {
            List<IProdCharValue> iValueList = addDefaultSpecChars(complex);
            Long groupId = DBUtil.getSequence(CoProdCharValue.class);
            Set<Integer> charIds = new HashSet<Integer>();
            if (CommonUtil.isNotEmpty(iValueList))
            {
                List<CoProdCharValue> charValueList = new ArrayList<CoProdCharValue>();
                for (IProdCharValue value : iValueList)
                {
                    CoProdCharValue charValue = new CoProdCharValue();
                    charValue.setCreateDate(context.getCommitDate());
                    charValue.setExpireDate(value.getExpireDate());
                    charValue.setValidDate(value.getValidDate());
                    charValue.setValue(value.getParamValue());
                    charValue.setObjectId(dmProd.getObjectId());
                    charValue.setObjectType(dmProd.getObjectType());
                    charValue.setSoNbr(context.getSoNbr());
                    charValue.setSpecCharId(convertSpecId(dmProd, value.getParamId()));
                    charValue.setProductId(value.getProductId());
                    if (!charIds.contains(value.getParamId()))
                    {
                        charIds.add(value.getParamId());
                    }
                    else
                    {
                        groupId = DBUtil.getSequence(CoProdCharValue.class);
                    }
                    charValue.setGroupId(groupId);
                    charValueList.add(charValue);
                }
                complex.setDmValueList(charValueList);
            }
        }

    }

    // 13001-->13201
    private int convertSpecId(CoProd dmProd, int specId)
    {
        if (specId == SpecCodeDefine.GROUP_IN_PERSONAL_ID)
        {
            if (dmProd.getBusiFlag() == SpecCodeDefine.GROUP_IN_PERSON)
            {
                return SpecCodeDefine.GROUP_IN_PERSONAL_ID;
            }
            else if (dmProd.getBusiFlag() == SpecCodeDefine.GROUP_NO_PORT_PERSON)
            {
                return SpecCodeDefine.GROUP_NO_PORT_PERSONAL_ID;
            }
            else if (dmProd.getBusiFlag() == SpecCodeDefine.GROUP_OUT_PERSON)
            {
                return SpecCodeDefine.GROUP_OUT_PERSONAL_ID;
            }
            else
            {
                return SpecCodeDefine.GROUP_IN_PERSONAL_ID;
            }
        }
        else
        {
            return specId;
        }

    }

    // 没有设置定价计划
    @SuppressWarnings("unused")
    private List<CoProdPriceParam> createCoProdPriceParam(ProdComplex complex)
    {
        List<IProdPriceParam> paramList = complex.getParamList();
        if (CommonUtil.isEmpty(paramList))
        {
            return null;
        }
        // createTrialPriceParam(complex);
        CoProd dmProd = complex.getDmProd();
        List<CoProdPriceParam> dmParamList = new ArrayList<CoProdPriceParam>();
        for (IProdPriceParam sParam : paramList)
        {
            CoProdPriceParam pm = new CoProdPriceParam();
            pm.setCreateDate(sParam.getCommitDate());
            pm.setExpireDate(sParam.getExpireDate());
            pm.setValidDate(sParam.getValidDate());
            pm.setObjectId(dmProd.getObjectId());
            pm.setObjectType(dmProd.getObjectType());
            pm.setParamId(sParam.getParamId());
            pm.setParamValue(sParam.getParamValue());
            pm.setProductId(dmProd.getProductId());
            pm.setSoDate(sParam.getCommitDate());
            pm.setSoNbr(sParam.getSoNbr());
            pm.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
            dmParamList.add(pm);
        }
        complex.setDmParamList(dmParamList);
        return dmParamList;

    }

    // 创建账期
    private List<CoProdBillingCycle> createCoProdBillingCycle(ProdComplex complex)
    {
        IProduct prod = complex.getProd();
        List<CoProdBillingCycle> cycleList = new ArrayList<CoProdBillingCycle>();
        ProdCycleCmp prodCycleCmp = context.getCmp(ProdCycleCmp.class);
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(prod.getOfferId());
        Integer cycleSyncFlag = offerBean.getOfferLifeCycle().getCycleSyncFlag();
        if (cycleSyncFlag != null && cycleSyncFlag != EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_BY_ACCOUNT)
        {
            cycleList.add(prodCycleCmp.parseProdBillingCycle(prod));
        }
        complex.setDmCycleList(cycleList);
        return cycleList;
    }

    // 获取使用关系
    private Object[] getObjectIdAndType(IProduct prod)
    {
        Long objectId = null;
        Integer objectType = null;
        if (prod.getUserId() != null)
        {
            objectId = prod.getUserId();
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        }
        else
        {
            objectId = prod.getAcctId();
            CaAccount dmAccount = context.get3hTree().loadAcct3hBean(prod.getAcctId()).getAccount();
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

        return new Object[] { objectId, objectType };
    }

    private CoProd createModifyProd(IProduct prod,Offering3hbean offerBean){
    	 CoProd dmProd = new CoProd();
    	 Object[] obj = getObjectIdAndType(prod);
    	 dmProd.setPricingPlanId(0);
         dmProd.setProductId(prod.getProductId());
         dmProd.setObjectId((Long) obj[0]);
         if(prod.getProductType() == 0 || prod.getProductType() == 1){
         	dmProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
         }else if(prod.getProductType() == 2){
         	dmProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_GCA);
         }else if(prod.getProductType() == 3){
         	dmProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
         }
        
         PmProductOffering dmOffering = offerBean.getOffering();
         dmProd.setProdTypeId(prod.getProductType());
         dmProd.setProductOfferingId(prod.getOfferId().intValue());
         if (prod.getProductStatus() != null)
         {
             dmProd.setLifecycleStatusId(prod.getProductStatus());
         }
         else
         {
             dmProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);// active
         }

         int busiFlag = 0;
         if (dmOffering.getSpecTypeFlag() != null && dmOffering.getSpecTypeFlag() == 1)
         {
             busiFlag = prod.getProductType();
         }
         else
         {
             busiFlag = offerBean.getBusiFlag();
         }
         // 程控产品399
         if(prod.getProductType() != null && prod.getProductType() == EnumCodeExDefine.PRODUCT_TYPE_PROGRAMCONTROL){
         	busiFlag = EnumCodeExDefine.PRODUCT_TYPE_PROGRAMCONTROL;
         }

         dmProd.setBusiFlag(busiFlag);
         if (EnumCodeDefine.PRODUCT_MAIN == dmOffering.getIsMain())
         {
             dmProd.setIsMain(EnumCodeDefine.PRODUCT_MAIN);
         }
         else
         {
             dmProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
         }
         dmProd.setBillingType(offerBean.getAttribute().getBillingType());
         dmProd.setCreateDate(prod.getCommitDate());
         dmProd.setValidDate(prod.getValidDate());
         dmProd.setExpireDate(prod.getExpireDate());
         dmProd.setProdValidDate(prod.getValidDate());
         dmProd.setProdExpireDate(prod.getExpireDate());
         dmProd.setSoDate(context.getCommitDate());
         dmProd.setSoNbr(context.getSoNbr());
    	 return dmProd;
    }
    
    // 没有计算定价计划
    private CoProd createCoProd(ProdComplex complex)
    {
        if (complex.getDmProd() != null)
        {
            return complex.getDmProd();
        }
        IProduct prod = complex.getProd();
        Object[] obj = getObjectIdAndType(prod);
        CoProd dmProd = new CoProd();
        dmProd.setPricingPlanId(0);
        dmProd.setProductId(prod.getProductId());
        dmProd.setObjectId((Long) obj[0]);
        if(prod.getProductType() == 0 || prod.getProductType() == 1){
        	dmProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        }else if(prod.getProductType() == 2){
        	dmProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_GCA);
        }else if(prod.getProductType() == 3){
        	dmProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        }
     
        Offering3hbean offerBean = getOffer3hBean(complex);
        PmProductOffering dmOffering = offerBean.getOffering();
        dmProd.setProdTypeId(prod.getProductType());
        dmProd.setProductOfferingId(prod.getOfferId().intValue());
        if (prod.getProductStatus() != null)
        {
            dmProd.setLifecycleStatusId(prod.getProductStatus());
        }
        else
        {
            dmProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);// active
        }

        int busiFlag = 0;
        if (dmOffering.getSpecTypeFlag() != null && dmOffering.getSpecTypeFlag() == 1)
        {
            busiFlag = prod.getProductType();
        }
        else
        {
            busiFlag = offerBean.getBusiFlag();
        }
        // 程控产品399
        if(prod.getProductType() != null && prod.getProductType() == EnumCodeExDefine.PRODUCT_TYPE_PROGRAMCONTROL){
        	busiFlag = EnumCodeExDefine.PRODUCT_TYPE_PROGRAMCONTROL;
        }

        dmProd.setBusiFlag(busiFlag);
        if (EnumCodeDefine.PRODUCT_MAIN == dmOffering.getIsMain())
        {
            dmProd.setIsMain(EnumCodeDefine.PRODUCT_MAIN);
        }
        else
        {
            dmProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
        }
        dmProd.setBillingType(offerBean.getAttribute().getBillingType());
        dmProd.setCreateDate(prod.getCommitDate());
        dmProd.setValidDate(prod.getValidDate());
        dmProd.setExpireDate(prod.getExpireDate());
        dmProd.setProdValidDate(prod.getValidDate());
        dmProd.setProdExpireDate(prod.getExpireDate());
        dmProd.setSoDate(context.getCommitDate());
        dmProd.setSoNbr(context.getSoNbr());
        complex.setDmProd(dmProd);
        return dmProd;
    }

    // 添加默认值
    public List<IProdCharValue> addDefaultSpecChars(ProdComplex complex)
    {
        CoProd dmProd = complex.getDmProd();

        List<IProdCharValue> list = complex.getValueList();
        if (CommonUtil.isEmpty(list))
        {
            list = new ArrayList<IProdCharValue>();
        }

        // @Date 2012-08-29 lijc3 处理静态小区cell_code读取销售品配置
        Offering3hbean offering3hbean = getOffer3hBean(complex);
        PmProductOffering dmOffering = offering3hbean.getOffering();
        IProduct iproduct = complex.getProd();
        int busiFlag = 0;

        if (dmOffering.getSpecTypeFlag() != null && dmOffering.getSpecTypeFlag() == 1)
        {
            busiFlag = iproduct.getProductType();
        }
        else
        {
            busiFlag = offering3hbean.getBusiFlag();
        }

        if (busiFlag == SpecCodeDefine.HOME_ZONE)
        {
            List<Integer> cellCodeList = context.getComponent(ProductQuery.class).queryCellCode(
                    dmProd.getProductOfferingId().intValue());
            if (CommonUtil.isNotEmpty(cellCodeList))
            {
                for (Integer cellCode : cellCodeList)
                {
                    IProdCharValue charValue = new IProdCharValue();
                    charValue.setParamId(SpecCodeDefine.HOME_ZONE_CELL);
                    charValue.setParamValue(String.valueOf(cellCode));
                    charValue.setCommitDate(dmProd.getCreateDate());
                    charValue.setExpireDate(dmProd.getExpireDate());
                    charValue.setValidDate(dmProd.getValidDate());
                    charValue.setProductId(dmProd.getProductId());
                    charValue.setSoNbr(dmProd.getSoNbr());
                    list.add(charValue);
                }
            }
        }
        // 两城一家的处理
        else if (busiFlag == SpecCodeDefine.TWO_CITY_ONE_HOME)
        {
            List<PmProductOfferSpecChar> specChars = offering3hbean.getOfferSpecCharList();
            if (CommonUtil.isNotEmpty(specChars))
            {
                for (PmProductOfferSpecChar specChar : specChars)
                {
                    IProdCharValue charValue = new IProdCharValue();
                    charValue.setParamId(SpecCodeDefine.TWO_CITY_ONE_HOME_CELL);
                    charValue.setParamValue(specChar.getValue());
                    charValue.setCommitDate(dmProd.getCreateDate());
                    charValue.setExpireDate(dmProd.getExpireDate());
                    charValue.setValidDate(dmProd.getValidDate());
                    charValue.setProductId(dmProd.getProductId());
                    charValue.setSoNbr(dmProd.getSoNbr());
                    list.add(charValue);
                }
            }

        }

        List<Integer> specIds = querySpecCharIdsByOfferId(dmProd.getProductOfferingId());
        if (!CommonUtil.isEmpty(specIds))
        {
            // 传入的特征值id集合
            List<Integer> inParamIds = new ArrayList<Integer>();
            for (IProdCharValue sp : list)
            {
                // 传入的特征值id集合只存放数据库存在的，传入的但是数据库不存在则不添加到传入的特征值集合中
                if (specIds.contains(sp.getParamId()))
                {
                    inParamIds.add(sp.getParamId());
                }
            }
            List<Integer> notInParamIds = new ArrayList<Integer>();
            for (Integer specId : specIds)
            {
                // 传入的参数id不包含从数据库里面查出来的，则不包含的特征值需要设置默认值
                if (!inParamIds.contains(specId))
                {
                    notInParamIds.add(specId);
                }
            }
            // 没有传入的特征值id，看有没有默认值，有，就设置到sproductorder里面
            if (notInParamIds.size() > 0)
            {
                List<PmProductSpecCharValue> specCharValues = query(PmProductSpecCharValue.class, new DBCondition(
                        PmProductSpecCharValue.Field.specCharId, notInParamIds, Operator.IN), new DBCondition(
                        PmProductSpecCharValue.Field.isDefault, 1));// 1表示默认值
                if (!CommonUtil.isEmpty(specCharValues))
                {
                    for (PmProductSpecCharValue value : specCharValues)
                    {
                        IProdCharValue charValue = new IProdCharValue();
                        charValue.setParamId(value.getSpecCharId());
                        charValue.setParamValue(value.getValue());
                        charValue.setCommitDate(dmProd.getCreateDate());
                        charValue.setExpireDate(dmProd.getExpireDate());
                        charValue.setValidDate(dmProd.getValidDate());
                        charValue.setProductId(dmProd.getProductId());
                        charValue.setSoNbr(dmProd.getSoNbr());
                        list.add(charValue);
                    }
                }
            }
        }
        /*
         * if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN) { PmProductOfferSpecChar prop =
         * offering3hbean.getOfferSpecChar(SpecCodeDefine.MAIN_PROD_SPEC_CHAR_11628); if (prop != null) { IProdCharValue charValue
         * = new IProdCharValue(); charValue.setParamId(prop.getSpecCharId()); charValue.setParamValue(prop.getValue());
         * charValue.setCommitDate(dmProd.getCreateDate()); charValue.setExpireDate(dmProd.getExpireDate());
         * charValue.setValidDate(dmProd.getValidDate()); charValue.setProductId(dmProd.getProductId());
         * charValue.setSoNbr(dmProd.getSoNbr()); list.add(charValue); } else { throw
         * IMSUtil.throwBusiException(ErrorCodeExDefine.CHANGE_PROD_NEED_SPEC, dmProd.getProductOfferingId(), 11628); } }
         */
        // 将处理完后的charvaluelist放入complex中
        if (CommonUtil.isNotEmpty(list))
        {
            complex.setValueList(list);
            return list;
        }
        else
        {
            complex.setValueList(null);
            return null;
        }
    }

    // 打包产品
    private void createPackageProduct(ProdComplex complex)
    {
        IProduct prod = complex.getProd();
        if (prod.getParentProductId() == null)
        {
            return;
        }
        CoProd dmProd = complex.getDmProd();
        CoProdBundleComposite bdc = new CoProdBundleComposite();
        bdc.setParentProductNo(prod.getParentProductId());
        bdc.setProductId(dmProd.getProductId());
        bdc.setObjectId(dmProd.getObjectId());
        bdc.setObjectType(dmProd.getObjectType());
        complex.setComposite(bdc);
    }

    // 试用计划
    public void createTrialPriceParam(ProdComplex complex)
    {
        CoProd dmProd = complex.getDmProd();

        PmProductOfferAttribute attr = context.get3hTree().loadOffering3hBean(dmProd.getProductOfferingId().longValue())
                .getAttribute();
        if (attr != null)
        {
            Integer trialType = attr.getProbationCycleType();
            Integer trialUnit = attr.getProbationCycleUnit();
            Integer tiralMode = attr.getProbationEffectMod();

            if (tiralMode == null)
                return;
            Date trialValidDate = dmProd.getValidDate();
            if (tiralMode.intValue() == EnumCodeDefine.PROD_NOTIFY_TRIAL_MODE_OFFSET)
            {
                Integer offType = attr.getOffsetCycleType();
                Integer offUnit = attr.getOffsetCycleUnit();

                trialValidDate = ProdCycleHelper.calculateMoveDate(trialValidDate, offType, offUnit);
                if (trialValidDate == null)
                {
                    // imsLogger.debug(
                    // "***** OffsetCycleType[" , offType , "] OffsetCycleUnit[" , offUnit , "] ProbationEffectMod["
                    // , tiralMode , "] no free trial config for offering[" , dmProd.getProductOfferingId() , "].");
                    return;
                }
            }

            Date trialExpireDate = ProdCycleHelper.calculateMoveDate(trialValidDate, trialType, trialUnit);
            if (trialExpireDate == null)
            {
                // imsLogger
                // .debug("***** ProbationCycleType[" , trialType , "] ProbationCycleUnit[" , trialUnit
                // , "] ProbationEffectMod[" , tiralMode , "] no free trial period config for offering["
                // , dmProd.getProductOfferingId() , "]");
                return;
            }

            if (!trialValidDate.before(dmProd.getExpireDate()))
            {
                IMSUtil.throwBusiException(ErrorCodeExDefine.CHANGE_PROD_TRIAL_VALID_ERROR, trialValidDate,
                        dmProd.getExpireDate());
            }
            IProdPriceParam param = new IProdPriceParam();
            param.setParamId(SpecCodeDefine.PRICE_PARAM_ID_RC_BASE_FEE);
            param.setParamValue("0");// 免费
            param.setValidDate(trialValidDate);
            param.setExpireDate(trialExpireDate);
            param.setCommitDate(context.getCommitDate());
            param.setProductId(dmProd.getProductId());
            param.setSoNbr(context.getSoNbr());

            List<IProdPriceParam> paramList = complex.getParamList();
            if (CommonUtil.isEmpty(paramList))
            {
                paramList = new ArrayList<IProdPriceParam>();
            }
            paramList.add(param);
            complex.setParamList(paramList);
        }
    }

    public void setContextHodlerByCoProd(CoProd dmProd)
    {
        if (dmProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
        {
            ITableUtil.setValue2ContextHolder(null, dmProd.getObjectId(), null);
        }
        else
        {
        	RouterCmp routeCmp = context.getCmp(RouterCmp.class);
        	Long acctId=routeCmp.queryAcctIdByUserId(dmProd.getObjectId());
            ITableUtil.setValue2ContextHolder(null,acctId,null);
        }
    }

    /**
     * lijc3 2012-10-11 获取3hbean
     * 
     * @param complex
     * @return
     */
    public Offering3hbean getOffer3hBean(ProdComplex complex)
    {
        if (complex.getOfferBean() != null)
        {
            return complex.getOfferBean();
        }
        Long offerId = null;
        if (complex.getDmProd() != null)
        {
            offerId = complex.getDmProd().getProductOfferingId().longValue();
        }
        else
        {
            offerId = complex.getProd().getOfferId();
        }
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offerId);
        complex.setOfferBean(offerBean);
        return offerBean;
    }

    /**
     * lijc3 2012-10-30 专门修改产品的expire_date
     * 
     * @param prodList
     * @param expireDate
     */
    public void updateCoProdExpireDate(List<CoProd> prodList, Date expire)
    {
        if (CommonUtil.isEmpty(prodList))
        {
            return;
        }
        // 一条 正常更新
        if (prodList.size() == 1)
        {
            CoProd prodValue = new CoProd();
            prodValue.setExpireDate(expire);
            prodValue.setProdExpireDate(expire);
            prodValue.setSoNbr(context.getSoNbr());
            this.updateByConditionWithNoQuery(prodList, prodValue, context.getRequestDate(), new DBCondition(
                    CoProd.Field.productId, prodList.get(0).getProductId()));
            // extendLastObjectNoQuery(prodList, prodValue, new DBCondition(CoProd.Field.productId,
            // prodList.get(0).getProductId()));
        }
        else
        {
            CoProd oldProd = this.mergeProd(prodList);
            // 原产品的时间长度
            Date validDate = oldProd.getValidDate();
            Date expireDate = oldProd.getExpireDate();
            // 传入的失效时间大于产品原来的失效时间，更新最后一条即可
            if (expire.after(expireDate) || expire.equals(expireDate))
            {

                return;
                // 传入的失效时间小于等于原来产品的生效时间 则三条都要更新
                // expire<=validDate则三条都要更新
            }
            else if (expire.before(validDate) || expire.equals(validDate))
            {

                return;
            }
            // 带生效时间更新的，只更新当前段的，更新它的失效时间
            for (CoProd dmProd : prodList)
            {
                if (dmProd.getValidDate().before(expire))
                {

                }
                else if (!dmProd.getValidDate().before(expire) && dmProd.getExpireDate().after(expire))
                {

                }
                else
                {

                }
            }

        }
    }
}
