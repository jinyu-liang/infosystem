package com.ailk.imssh.prod.cmp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.acct.cmp.AcctQuery;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.prod.help.ProdHelper;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCellInfo;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdMapping;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.itable.entity.IProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IProdPriceParam;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAttribute;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferMapping;

/**
 * @Description 产品组件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author lijc3
 * @Date 2012-5-14
 * @Date 2012-5-19 lijc3 增加对CO_PROD_VALID的操作
 * @Date 2012-08-29 lijc3 处理静态小区cell_code读取销售品配置
 */
public class ProdCmp extends ProdQuery
{

    /**
     * 获取产品 lijc3 2012-5-17
     */
    public CoProd loadProd(Long prodId, Long objectId) throws IMSException
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(
                CoProd.Field.objectId, objectId));
        CoProd coProd = mergeProd(prodList);
        if (coProd == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeExDefine.COMMON_PROD_NOTEXIST, prodId);
        }
        return coProd;
    }
    /**
     * 获取产品 空的不报错 yuxz 2015-12-15
     */
    public CoProd loadProdWithNull(Long prodId, Long objectId) throws IMSException
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(
                CoProd.Field.objectId, objectId));
        CoProd coProd = mergeProd(prodList);
        return coProd;
    }

    /**
     * @Description 根据产品id，用户id或者账户编号查询产品，userId,acctId不能同时为空，优先使用userId
     * @Author lijingcheng
     */
    public CoProd loadProd(Long prodId, Long userId, Long acctId) throws IMSException
    {
        if (userId != null)
        {
            return loadProd(prodId, userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        }
        else if (acctId != null)
        {
            return loadProd(prodId, acctId);
        }
        throw IMSUtil.throwBusiException(ErrorCodeExDefine.COMMON_PARAMS_ALL_ISNULL, "userId,acctId");
    }

    /**
     * lijc3 2012-5-19 获取产品
     */
    public CoProd loadProd(Long prodId, Long objectId, Integer objectType) throws IMSException
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(
                CoProd.Field.objectId, objectId), new DBCondition(CoProd.Field.objectType, objectType));
        CoProd coProd = mergeProd(prodList);
        if (coProd == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeExDefine.COMMON_PROD_NOTEXIST, prodId);
        }
        return coProd;
    }

    /**
     * lijc3 2012-9-20 延长最后一条的失效时间
     * 
     * @param <T>
     * @param list
     * @param upValue
     * @param conditions
     * @throws IMSException
     */
    public <T extends DataObject> void extendLastObjectNoQuery(List<T> list, DataObject upValue, DBCondition... conditions)
            throws IMSException
    {
        if (CommonUtil.isEmpty(list))
        {
            return;
        }
        DataObject lastData = ProdHelper.getLastDataObjectByExpireDate(list);
        List<DBCondition> cons = CommonUtil.parseArray2List(conditions);
        cons.add(new DBCondition(DBUtil.getJefField(upValue.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE), ClassUtil
                .getFieldValue(lastData, ConstantDefine.ENTITY_FIELD_VALID_DATE)));
        this.updateByConditionWithNoQuery(list, upValue, context.getCommitDate(), cons.toArray(new DBCondition[cons.size()]));
    }

    /**
     * lijc3 2012-5-26 延长失效时间
     */
    //TODO
    public void extendLastObject(DataObject upValue, DBCondition... conditions) throws IMSException
    {
        List<DataObject> list = this.query(upValue.getClass(), conditions);
        if (CommonUtil.isEmpty(list))
        {
            return;
        }
        DataObject lastData = ProdHelper.getLastDataObjectByExpireDate(list);
        List<DBCondition> cons = CommonUtil.parseArray2List(conditions);
        cons.add(new DBCondition(DBUtil.getJefField(upValue.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE), ClassUtil
                .getFieldValue(lastData, ConstantDefine.ENTITY_FIELD_VALID_DATE)));
        this.updateByCondition(upValue, cons.toArray(new DBCondition[cons.size()]));
    }
    
    /**
     * lijc3 2012-5-26 延长失效时间
     */
    public void extendCoProd(CoProd upValue,Integer pricePlanId, DBCondition... conditions) throws IMSException
    {
        List<CoProd> list = this.query(upValue.getClass(), conditions);
        if (CommonUtil.isEmpty(list))
        {
            return;
        }
        CoProd lastData = ProdHelper.getLastDataObjectByExpireDate(list);
        //如果有多条记录，并且更新的失效时间比上一条的失效时间小，则上一条小的也要置为失效
        if(list.size()>1&&upValue.getExpireDate().before(lastData.getValidDate())){
        	imsLogger.debug("测试截断数据置失效问题");
        	List<DBCondition> consLit=CommonUtil.parseArray2List(conditions);
        	consLit.add(new DBCondition(CoProd.Field.expireDate, lastData.getValidDate()));
        	DBUtil.updateByCondition(upValue, consLit.toArray(new DBCondition[consLit.size()]));
        	imsLogger.debug("测试截断数据置失效问题结束");
        }
        
        CoProd newUpValue=new CoProd();
        newUpValue.setSoNbr(upValue.getSoNbr());
        newUpValue.setProdExpireDate(upValue.getExpireDate());
        newUpValue.setExpireDate(upValue.getExpireDate());
        List<DBCondition> cons = CommonUtil.parseArray2List(conditions);
        cons.add(new DBCondition(CoProd.Field.validDate, lastData.getValidDate()));
        if(pricePlanId!=null){
        	newUpValue.setPricingPlanId(pricePlanId);
        }
        this.updateByCondition(newUpValue, cons.toArray(new DBCondition[cons.size()]));
        
        
        
    }
    
    /**
     * lijc3 2012-5-26 延长失效时间 支持设置新记录的生效时间，老记录的失效时间
     * 2013-08-06 liming15 激活用户产品生失效时间有误问题修复
     */
    public void activeProdDate(CoProd upValue, Date oldExpireDate, Date newValidDate, List<CoProd> prodList) throws IMSException
    {
        if (CommonUtil.isEmpty(prodList))
        {
            return;
        }
        Long productId = prodList.get(0).getProductId();
        //老记录的失效时间需要置为老记录的生效时间，避免下周期激活的时候造成产品收费
        oldExpireDate=DateUtils.dayBegin(prodList.get(0).getProdValidDate());
        Date oldValidDate=prodList.get(0).getProdValidDate();
        /*
        if (prodList.size() > 1)
        {
        */
            // 全部设置为失效
            imsLogger.debug("^^^^set all prod expired ,product_id: ", productId);
            Date expireDate = upValue.getExpireDate();
            // 原来产品的失效时间
            //新的生效时间比原来的大，是改大，
            if(newValidDate.after(oldValidDate)){
            	this.deleteByCondition_noInsert(CoProd.class, oldExpireDate, new DBCondition(CoProd.Field.productId, productId));
            }else{
            	//新的生效时间比原来的小，是改小，需要将新的生效时间后生效的记录也都置为失效，且失效时间应该是传入的生效时间
            	this.deleteByCondition_noInsert(CoProd.class, newValidDate, new DBCondition(CoProd.Field.productId, productId),
            			new DBCondition(CoProd.Field.expireDate, newValidDate,Operator.GREAT));
            }
            // 删除生效时间比失效时间大的数据
            DBUtil.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, productId), new DBCondition(
                    CoProd.Field.objectId, prodList.get(0).getObjectId()),
                    new DBCondition(CoProd.Field.objectType, prodList.get(0).getObjectType()), new DBCondition(
                            CoProd.Field.validDate, oldExpireDate, Operator.GREAT_EQUALS), new DBCondition(CoProd.Field.expireDate,
                            oldExpireDate, Operator.LESS_EQUALS));
            List<CoProd> newProdList = new ArrayList<CoProd>();
            for (CoProd prod : prodList)
            {
                CoProd dmProd = (CoProd) IMSUtil.copyDataObject(prod);
                //改小的话，只改最小的那条
                if(newValidDate.before(oldValidDate)){
                	if(prodList.size() > 1){
                		//多于一条的，只改生效时间小于等于提交时间的
                		if(!prod.getValidDate().after(context.getCommitDate())){
                			dmProd.setValidDate(newValidDate);
                		}
                	}else{
                		dmProd.setValidDate(newValidDate);
                	}
                }else{
                	//改大
                	if (prod.getValidDate().before(newValidDate) || prod.getValidDate().equals(newValidDate))
                	{
                		dmProd.setValidDate(newValidDate);
                	}
                }
            
                dmProd.setSoDate(context.getCommitDate());
                dmProd.setSoNbr(context.getSoNbr());
                if (expireDate != null && dmProd.getExpireDate().after(expireDate))
                {
                    dmProd.setExpireDate(expireDate);
                }
                // 设置失效时间
                if (dmProd.getExpireDate().after(dmProd.getValidDate()))
                {
                    newProdList.add(dmProd);
                }
            }
            this.insertBatch(newProdList);
            /*
        }
        else
        {
            imsLogger.debug("active***1");
            List<CoProd> result = updateByConditionWithNoCache(null, upValue, oldExpireDate, newValidDate, new DBCondition(
                    CoProd.Field.productId, productId));
            // 删除生效时间比失效时间大的数据
            DBUtil.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, productId), new DBCondition(
                    CoProd.Field.objectId, prodList.get(0).getObjectId()),
                    new DBCondition(CoProd.Field.objectType, prodList.get(0).getObjectType()), new DBCondition(
                            CoProd.Field.validDate, oldExpireDate, Operator.GREAT), new DBCondition(CoProd.Field.expireDate,
                            oldExpireDate, Operator.LESS_EQUALS));
            context.cacheList(result);
        }
        */
    }



    /**
     * lijc3 2012-5-24 查询定价计划
     */
    public Integer queryPricePlanByProd(CoProd currProd, CoProd mainOffer)
    {
        String pricePlanIdKey = "PROD_PRICE_PLAN" + currProd.getProductId() + mainOffer.getProductOfferingId();
        if (context.getExtendParam(pricePlanIdKey) != null)
        {
            return (Integer) context.getExtendParam(pricePlanIdKey);
        }
        else
        {
            Integer pricePlanId = null;
            if (currProd.getBusiFlag() == SpecCodeDefine.GROUP_IN_PERSON
                    || currProd.getBusiFlag() == SpecCodeDefine.GROUP_NO_PORT_PERSON
                    || currProd.getBusiFlag() == SpecCodeDefine.GROUP_OUT_PERSON)
            {
                List<CoProdPriceParam> priceParams = queryProdPriceParam(currProd.getProductId());
                // List<CoProdCharValue> values = this.queryProdCharValue(currProd.getProductId());
                pricePlanId = queryPricePlanId(currProd.getProductOfferingId(), priceParams, null, null,
                        mainOffer.getProductOfferingId(), null, null);
            }
            else
            {
                pricePlanId = queryPricePlanId(currProd.getProductOfferingId(), null, null, null,
                        mainOffer.getProductOfferingId(), null, null);
            }
            if (pricePlanId == null)
            {
                pricePlanId = 0;
            }
            context.addExtendParam(pricePlanIdKey, pricePlanId);
            return pricePlanId;
        }
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param prodId
     * @param expireDate
     * @param prodList 2012-09-20 zengxr without query
     */
    @Deprecated
    public void modifyProdValidNoQuery(Long prodId, Date expireDate, List<CoProd> prodList)
    {
        CoProd valid = new CoProd();
        valid.setProdExpireDate(expireDate);
        updateDirectByConditionNoQuery(valid, prodList, new DBCondition(CoProd.Field.productId, prodId));
    }

    /**
     * lijc3 2012-5-19 修改CO_PROD_VALID
     */

    public void modifyProdValid(Long prodId, Date expireDate)
    {
        CoProd valid = new CoProd();
        valid.setProdExpireDate(expireDate);
        updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId, prodId));
    }
    public void modifyProdValidBySplitCharValue(Long prodId, Date expireDate)
    {
        CoProd valid = new CoProd();
        valid.setProdExpireDate(getNextMonthBegin(expireDate));
        updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId, prodId));
    }
    /**
     * lijc3 2012-5-17 创建CM_RES_IDENTITYE
     */
    public CmResIdentity createIdentityByProduct(CoProd dmProd, String identity)
    {
        CmResIdentity dmResIdentity = new CmResIdentity();
        dmResIdentity.setIdentity(identity);
        if (dmProd.getBusiFlag() == SpecCodeDefine.FAX)
        {
            dmResIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER);
        }
        else if (dmProd.getBusiFlag() == SpecCodeDefine.MULTI_SIM)
        {
            dmResIdentity.setIdentityAttr(1000);
            dmResIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM);
        }
        dmResIdentity.setSoNbr(context.getSoNbr());
        dmResIdentity.setResourceId(dmProd.getObjectId());
        // 生效和实效时间同产品的生效和失效时间 ljc
        dmResIdentity.setValidDate(dmProd.getValidDate());
        dmResIdentity.setExpireDate(dmProd.getExpireDate());
        return dmResIdentity;
    }

    /**
     * lijc3 2012-7-14 判断赠送的产品是否需要下周期失效
     * 
     * @param expireDate
     * @param dmProd
     * @return
     */
    public Date evolveProductDeleteExpireDate(CoProd currentProd, CoProd dmProd)
    {
        Date expireDate = currentProd.getExpireDate();
        // Date validDate = currentProd.getValidDate();
        Date validDate = currentProd.getProdValidDate();
        imsLogger.info("******自带优惠生效时间PROD_VALID_DATE=", validDate, " ; product_id = ", currentProd.getProductId());
        // 失效时间是在生效时间当天至12点的 不需要延期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(validDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        validDate = DateUtil.dayBegin(calendar.getTime());
        //这里判断订购时间大于一天
        if (expireDate.after(validDate))
        {
            AcctQuery acctQuery = context.getCmp(AcctQuery.class);
            Long acctId = null;
            if (currentProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
            {
                acctId = context.getCmp(RouterCmp.class).getAcctIdByUserIdRout(currentProd.getObjectId());
            }
            else
            {
                acctId = currentProd.getObjectId();
            }
            if (acctId == null)
            {
                return null;
            }
            // 出账日
            Date date = acctQuery.queryAcctNextCycleStart(acctId);
            imsLogger.debug("###next cycle start date is " , date);
            imsLogger.debug("###expire_date  " , date);
            if (date == null)
            {
                return null;
            }
            int day = DateUtil.getDateField(date, Calendar.DAY_OF_MONTH);
            //int nextMonth = DateUtil.getDateField(date, Calendar.MONTH);
            // 失效日
            int expireDay = DateUtil.getDateField(expireDate, Calendar.DAY_OF_MONTH);
           // int crruentMont = DateUtil.getDateField(expireDate, Calendar.MONTH);
            // 二者不相等 
            //2013-03-25  || (day == expireDay && nextMonth != crruentMont)
            //这里判断不是月初
            if ((day != expireDay))
            {
                // 判断销售品上标识，是否要立即失效

                PmProductOfferAttribute bute = context.get3hTree().loadOffering3hBean(dmProd.getProductOfferingId().longValue())
                        .getAttribute();
                if (bute != null && bute.getDiscountExpireMode() != null
                        && bute.getDiscountExpireMode() == EnumCodeExDefine.ENVOPROD_EXPIRE_DELAY)
                {
                    return date;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * lijc3 2012-5-19 查询关联关系
     * 
     * @param offerId
     * @param mainOfferId
     * @param relationType
     * @return
     */
    private List<PmProductOfferMapping> queryPmOfferMapp(Integer offerId, Integer mainOfferId, int relationType)
    {
        List<PmProductOfferMapping> mappingList = context.getMappList();
        if (CommonUtil.isEmpty(mappingList))
        {
            return null;
        }
        List<PmProductOfferMapping> resultList = new ArrayList<PmProductOfferMapping>();

        if (relationType == EnumCodeDefine.PRODUCT_REL_ONE_2_MORE)
        {
            for (PmProductOfferMapping mapp : mappingList)
            {
                if (mapp.getProductOfferingId() == offerId.intValue() && mapp.getRelationTypeId() == relationType)
                {
                    resultList.add(mapp);
                }
            }
        }
        else
        {
            if (mainOfferId == null)
            {
                return null;
            }

            if (offerId != null)
            {
                for (PmProductOfferMapping mapp : mappingList)
                {
                    if (mapp.getProductOfferingId() == offerId.intValue() && mapp.getRelationTypeId() == relationType
                            && mapp.getRefMainOffering() == mainOfferId.intValue())
                    {
                        resultList.add(mapp);
                    }
                }
            }
            else
            {
                for (PmProductOfferMapping mapp : mappingList)
                {
                    if (mapp.getRelationTypeId() == relationType && mapp.getRefMainOffering() == mainOfferId.intValue())
                    {
                        resultList.add(mapp);
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * lijc3 2012-5-19 获取产品配生的销售品id
     * 
     * @param offerId
     * @param mainOfferId
     * @param relationType
     * @return
     */
    public List<Integer> queryPmOfferMapping(Integer offerId, Integer mainOfferId, int relationType)
    {
        List<Integer> offerIds = new ArrayList<Integer>();
        List<PmProductOfferMapping> mapps = this.queryPmOfferMapp(offerId, mainOfferId, relationType);
        if (CommonUtil.isEmpty(mapps))
        {
            return null;
        }
        for (PmProductOfferMapping mp : mapps)
        {
            offerIds.add(mp.getRelProductOffering());
        }
        return offerIds;
    }

    /**
     * lijc3 2012-5-19 获取派生关系
     * 
     * @param productId
     * @param mainProductId
     * @param relationType
     * @return
     */
    public List<Long> queryProdMapping(Long productId, int relationType)
    {
        List<Long> productIds = new ArrayList<Long>();
        List<CoProdMapping> mapps = this.query(CoProdMapping.class, new DBCondition(CoProdMapping.Field.productId, productId),
                new DBCondition(CoProdMapping.Field.relationTypeId, relationType));
        if (CommonUtil.isEmpty(mapps))
        {
            return null;
        }
        for (CoProdMapping mp : mapps)
        {
            productIds.add(mp.getRelProduct());
        }
        return productIds;
    }

    /**
     * lijc3 2012-9-21 根据productId查询出派生的产品列表
     * 
     * @param prodId
     * @return
     */
    public List<Long> queryProdMapping(CoProd dmProd)
    {
        Long prodId = dmProd.getProductId();
        List<CoProdMapping> mapps = DBUtil.query(CoProdMapping.class, new DBCondition(CoProdMapping.Field.productId, prodId));
        /** 主产品
        if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
        {
            mapps = this.query(CoProdMapping.class, new DBCondition(CoProdMapping.Field.objectId, dmProd.getObjectId()),
                    new DBCondition(CoProdMapping.Field.objectType, dmProd.getObjectType()), new DBCondition(
                            CoProdMapping.Field.relationTypeId, EnumCodeDefine.PRODUCT_REL_ENVLOPROD));
        }
        else
        {
            mapps = this.query(CoProdMapping.class, new DBCondition(CoProdMapping.Field.productId, prodId));
        }
        */
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
     * lijc3 2012-5-19 创建派生关系
     * 
     * @param dmProd
     * @param evolveProd
     * @param mainProductId
     * @param relationType
     * @return
     */
    public CoProdMapping evolveProductMapping(CoProd dmProd, CoProd evolveProd, int relationType)
    {
        CoProdMapping mapp = new CoProdMapping();
        mapp.setCreateDate(evolveProd.getCreateDate());
        mapp.setExpireDate(evolveProd.getExpireDate());
        mapp.setProductId(dmProd.getProductId());
        mapp.setRelationTypeId(relationType);
        mapp.setRelProduct(evolveProd.getProductId());
        mapp.setValidDate(evolveProd.getValidDate());
        mapp.setSoDate(evolveProd.getSoDate());
        mapp.setSoNbr(getContext().getSoNbr());
        mapp.setObjectId(dmProd.getObjectId());
        mapp.setObjectType(dmProd.getObjectType());
        return mapp;
    }

    /**
     * lijc3 2012-5-19 生成CoProdCharValue
     * 
     * @param productId
     * @param groupId
     * @param specCode
     * @param value
     * @param expireDate
     * @param validDate
     * @param objectId
     * @param objectType
     * @return
     * @throws IMSException
     */
    public CoProdCharValue createCoProdCharValue(long productId, long groupId, int specCode, String value, Date expireDate,
            Date validDate, Long objectId, Integer objectType) throws IMSException
    {
        CoProdCharValue charValue = new CoProdCharValue();
        charValue.setSoNbr(this.context.getSoNbr());
        charValue.setGroupId(groupId);
        charValue.setProductId(productId);
        charValue.setExpireDate(expireDate);
        charValue.setValidDate(validDate);
        charValue.setSpecCharId(specCode);
        charValue.setValue(value);
        charValue.setCreateDate(context.getCommitDate());
        charValue.setObjectId(objectId);
        charValue.setObjectType(objectType);
        return charValue;
    }

    /**
     * lijc3 2012-5-17 根据CO_PROD_CHAR_VALUE创建IDENTITY
     */
    private CmResIdentity createIdentityByCharValue(CoProdCharValue charval, Long userId)
    {
        CmResIdentity resIdentity = new CmResIdentity();
        resIdentity.setIdentity(charval.getValue());
        resIdentity.setResourceId(userId);
        if (charval.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
        {
            resIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER);
        }
        else
        {
            resIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM);
        }
        resIdentity.setValidDate(charval.getValidDate());
        resIdentity.setExpireDate(charval.getExpireDate());
        return resIdentity;
    }

    /**
     * 取消日停产品（停机保号、保留期停、日保号停）<br>
     * wukl 2012-5-22
     */
    public void cancelProd(Long userId, Date userValidDate, Integer busiFlag)
    {
        List<CoProd> prodList = queryProdListByUserId(userId, busiFlag);
        if (CommonUtil.isNotEmpty(prodList))
        {
            CoProd coProd = prodList.get(0);
            deleteByCondition(CoProd.class, userValidDate, new DBCondition(CoProd.Field.productId, coProd.getProductId()));
            // 修改有效期
            this.modifyProdValid(coProd.getProductId(), userValidDate);

        }
    }

    /**
     * lijc3 2012-6-7 小增量修改特征值
     */
    public void modifyParamList(List<IProdCharValue> iValueList, CoProd dmProd)
    {
        if (CommonUtil.isEmpty(iValueList))
        {
            return;
        }
        if (dmProd.getBusiFlag() == SpecCodeDefine.BUDGET)
        {
            this.deleteByCondition_noInsert(CoBudgetCharValue.class, context.getCommitDate(), new DBCondition(
                    CoBudgetCharValue.Field.productId, dmProd.getProductId()), new DBCondition(
                    CoBudgetCharValue.Field.specCharId, getInSpecIds(iValueList), Operator.IN));

            List<CoBudgetCharValue> list = new ArrayList<CoBudgetCharValue>();
            Long groupId = DBUtil.getSequence(CoProdCharValue.class);
            for (IProdCharValue param : iValueList)
            {
                // 未来生效的，生效时间不能修改的
                Date validDate = param.getValidDate().after(context.getCommitDate()) ? param.getValidDate() : context
                        .getCommitDate();
                Date expireDate = param.getExpireDate();
                list.add(createBudgetCharValue(dmProd, groupId, param.getParamId(), param.getParamValue(), validDate, expireDate));
            }

            if (CommonUtil.isNotEmpty(list))
            {
                this.insertBatch(list);
            }

//            List<Integer> idList = new ArrayList<Integer>();
//            idList.add(SpecCodeDefine.BUDGET_RULE);
//            idList.add(SpecCodeDefine.BUDGET_PAYMODE);
//            idList.add(SpecCodeDefine.BUDGET_OBJECT_TYPE);
//            idList.add(SpecCodeDefine.BUDGET_MEASURE_ID);
//            idList.add(SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_TYPE);
//            idList.add(SpecCodeDefine.BUDGET_ACTION);
            //修改为not in
            CoBudgetCharValue upValue = new CoBudgetCharValue();
            upValue.setExpireDate(dmProd.getExpireDate());
            upValue.setGroupId(groupId);
            this.updateByCondition(upValue, new DBCondition(CoBudgetCharValue.Field.productId, dmProd.getProductId()),
                    new DBCondition(CoBudgetCharValue.Field.specCharId, getInSpecIds(iValueList), Operator.NOT_IN));

            return;
        }
        // 全量删除传入的id列表
        List<CoProdCharValue> existlist = this.deleteByCondition_noInsert(CoProdCharValue.class, context.getCommitDate(),
                new DBCondition(CoProdCharValue.Field.productId, dmProd.getProductId()), new DBCondition(
                        CoProdCharValue.Field.specCharId, getInSpecIds(iValueList), Operator.IN));
        if (CommonUtil.isNotEmpty(existlist))
        {
            for (CoProdCharValue value : existlist)
            {
                // 删除
                if (value.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER)
                {
                    this.deleteByCondition(CmResIdentity.class,
                            new DBCondition(CmResIdentity.Field.resourceId, dmProd.getObjectId()), new DBCondition(
                                    CmResIdentity.Field.identity, value.getValue()), new DBCondition(
                                    CmResIdentity.Field.identity, EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM));
                }
                else if (value.getSpecCharId() == SpecCodeDefine.FAX_NUMBER)
                {
                    this.deleteByCondition(CmResIdentity.class,
                            new DBCondition(CmResIdentity.Field.resourceId, dmProd.getObjectId()), new DBCondition(
                                    CmResIdentity.Field.identity, value.getValue()), new DBCondition(
                                    CmResIdentity.Field.identity, EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER));
                }
            }
        }
        List<CoProdCharValue> newList = new ArrayList<CoProdCharValue>();
        for (IProdCharValue param : iValueList)
        {
            // 未来生效的，生效时间不能修改的
            Date validDate = param.getValidDate().after(context.getCommitDate()) ? param.getValidDate() : context.getCommitDate();
            Date expireDate = param.getExpireDate();
            newList.add(createCoProdCharValue(dmProd.getProductId(), DBUtil.getSequence(), param.getParamId(),
                    param.getParamValue(), expireDate, validDate, dmProd.getObjectId(), dmProd.getObjectType()));
        }

        if (CommonUtil.isNotEmpty(newList))
        {
            this.insertBatch(newList);
            List<CmResIdentity> idens = new ArrayList<CmResIdentity>();
            for (CoProdCharValue charval : newList)
            {
                if (charval.getSpecCharId() == SpecCodeDefine.FAX_NUMBER
                        || charval.getSpecCharId() == SpecCodeDefine.MULTI_SIM_NUMBER)
                {
                    idens.add(createIdentityByCharValue(charval, dmProd.getObjectId()));
                }
            }
            if (CommonUtil.isNotEmpty(idens))
            {
                this.insertBatch(idens);
            }
        }
    }

    public CoBudgetCharValue createBudgetCharValue(CoProd dmProd, Long groupId, int specCharId, String value, Date validDate,
            Date expireDate)
    {
        CoBudgetCharValue charValue = new CoBudgetCharValue();
        charValue.setCreateDate(dmProd.getCreateDate());
        charValue.setExpireDate(expireDate);
        charValue.setGroupId(groupId);
        charValue.setObjectId(dmProd.getObjectId());
        charValue.setObjectType(dmProd.getObjectType());
        charValue.setProductId(dmProd.getProductId());
        charValue.setSoDate(context.getCommitDate());
        charValue.setSoNbr(context.getSoNbr());
        charValue.setSpecCharId(specCharId);
        charValue.setValidDate(validDate);
        charValue.setValue(value);
        return charValue;
    }

    /**
     * lijc3 2012-6-7 获取传入的specId列表
     */
    private Set<Integer> getInSpecIds(List<IProdCharValue> params)
    {
        Set<Integer> specIds = new HashSet<Integer>();
        for (IProdCharValue param : params)
        {
            specIds.add(param.getParamId());
        }
        return specIds;
    }

    /**
     * lijc3 2012-6-7 获取传入的extend_id
     */
    private Set<Integer> getInExtendIds(List<IProdPriceParam> params)
    {
        Set<Integer> specIds = new HashSet<Integer>();
        for (IProdPriceParam param : params)
        {
            specIds.add(param.getParamId());
        }
        return specIds;
    }

    /**
     * lijc3 2012-5-26 修改二次议价参数 全量操作
     */
    public void modifyExtendParamList(List<IProdPriceParam> iPriceParamList, CoProd dmProd, Integer pricePlanId)
    {
        if (CommonUtil.isNotEmpty(iPriceParamList))
        {
            // 是否有同步成员数
            boolean flag = false;
            Set<Integer> set = getInExtendIds(iPriceParamList);
            for (Iterator iterator = set.iterator(); iterator.hasNext();)
            {
                Integer i = (Integer) iterator.next();
                if (i == EnumCodeExDefine.PROD_PARAM_COUNT)
                {
                    flag = true;
                    iterator.remove();
                }
            }
            if (CommonUtil.isNotEmpty(set))
            {
                // 全量删除传入的二次议价参数
                this.deleteByCondition_noInsert(CoProdPriceParam.class, context.getCommitDate(), new DBCondition(
                        CoProdPriceParam.Field.productId, dmProd.getProductId()), new DBCondition(CoProdPriceParam.Field.paramId,
                        set, Operator.IN));
            }
            if (flag)
            {
                // 删除下周期生效的数据成员数
                this.deleteByCondition_noInsert(CoProdPriceParam.class, context.getCommitDate(), new DBCondition(
                        CoProdPriceParam.Field.productId, dmProd.getProductId()), new DBCondition(CoProdPriceParam.Field.paramId,
                        EnumCodeExDefine.PROD_PARAM_COUNT),
                        new DBCondition(CoProdPriceParam.Field.validDate, context.getCommitDate(), Operator.GREAT_EQUALS));
            }
            List<CoProdPriceParam> paramList = new ArrayList<CoProdPriceParam>();
            // 然后插入传入的特征值列表
            for (IProdPriceParam ep : iPriceParamList)
            {
                if (ep.getParamId() == EnumCodeExDefine.PROD_PARAM_COUNT)
                {
                    // 如果是成员数，先查询，如果存在则修改，如果不存在就新增
                    CoProdPriceParam param = this.querySingle(CoProdPriceParam.class, new DBCondition(
                            CoProdPriceParam.Field.productId, dmProd.getProductId()), new DBCondition(
                            CoProdPriceParam.Field.paramId, EnumCodeExDefine.PROD_PARAM_COUNT));
                    if (param != null)
                    {
                        CoProdPriceParam pp = new CoProdPriceParam();
                        pp.setParamValue(ep.getParamValue());
                        pp.setExpireDate(ep.getExpireDate());
                        // 更新掉,从传入的生效时间截断
                        this.updateByCondition(pp, ep.getValidDate(),
                                new DBCondition(CoProdPriceParam.Field.productId, dmProd.getProductId()), new DBCondition(
                                        CoProdPriceParam.Field.paramId, EnumCodeExDefine.PROD_PARAM_COUNT));
                    }
                    else
                    {
                        CoProdPriceParam price = new CoProdPriceParam();
                        price.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
                        price.setCreateDate(context.getRequestDate());
                        price.setExpireDate(ep.getExpireDate());
                        price.setParamId(ep.getParamId());
                        price.setParamValue(ep.getParamValue());
                        price.setSoDate(context.getCommitDate());
                        price.setSoNbr(context.getSoNbr());
                        price.setObjectId(dmProd.getObjectId());
                        price.setObjectType(dmProd.getObjectType());
                        price.setProductId(dmProd.getProductId());
                        price.setValidDate(ep.getValidDate());
                        paramList.add(price);
                    }
                }
                else
                {
                    CoProdPriceParam price = new CoProdPriceParam();
                    price.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
                    price.setCreateDate(context.getRequestDate());
                    price.setExpireDate(ep.getExpireDate());
                    price.setParamId(ep.getParamId());
                    price.setParamValue(ep.getParamValue());
                    price.setSoDate(context.getCommitDate());
                    price.setSoNbr(context.getSoNbr());
                    price.setObjectId(dmProd.getObjectId());
                    price.setObjectType(dmProd.getObjectType());
                    price.setProductId(dmProd.getProductId());
                    // 使用生效时间晚的
                    Date validDate = ep.getValidDate().after(context.getCommitDate()) ? ep.getValidDate() : context
                            .getCommitDate();
                    price.setValidDate(validDate);
                    paramList.add(price);
                }
            }

            if (CommonUtil.isNotEmpty(paramList))
            {
                this.insertBatch(paramList);
            }
        }
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param productId
     * @param prodList 2012-09-20 zengxr noquery for update and delete
     */
    public void deleteProdByIdNoQuery(Long productId, List<CoProd> prodList)
    {
        this.deleteByConditionNoQuery(CoProd.class, null, prodList, new DBCondition(CoProd.Field.productId, productId));
    }

    public List<CoProdCharValue> deleteCoProdCharValue(Long productId)
    {
        return this.deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, productId));
    }

    public void deleteCoProdPriceParam(Long productId)
    {
        this.deleteByCondition(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, productId));
    }

    public void deleteCoProdBillingCycle(Long productId)
    {
        this.deleteByCondition(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, productId));
    }

    /**
     * 删除产品 lijc3 2012-5-17
     * 
     * @param prodId
     * @param effectiveDate
     */
    public void deleteProdById(Long prodId, Date effectiveDate)
    {
        this.deleteByCondition(CoProd.class, effectiveDate, new DBCondition(CoProd.Field.productId, prodId));
    }

    /**
     * 删除特征值 lijc3 2012-5-17
     * 
     * @param prodId
     * @param effectiveDate
     */
    public void deleteProdCharValueByProdId(Long prodId, Date effectiveDate)
    {
        super.deleteByCondition(CoProdCharValue.class, effectiveDate, new DBCondition(CoProdCharValue.Field.productId, prodId));
    }

    /**
     * 删除亲情号码 lijc3 2012-5-17
     * 
     * @param prodId
     * @param effectiveDate
     */
    public void deleteProdFnCharValue(Long prodId, Date effectiveDate)
    {
        this.deleteByCondition(CoFnCharValue.class, effectiveDate, new DBCondition(CoFnCharValue.Field.productId, prodId));
    }

    /**
     * lijc3 2012-5-19 指定日期删除paramprice
     * 
     * @param prodId
     * @param effectiveDate
     */
    public void deleteProdParamPriceByProdId(Long prodId, Date effectiveDate)
    {
        this.deleteByCondition(CoProdPriceParam.class, effectiveDate, new DBCondition(CoProdPriceParam.Field.productId, prodId));
    }

    /**
     * lijc3 2012-5-19 删除关联关系
     * 
     * @param productId
     * @param expireDate
     */
    public void deleteProdMapping(Long productId, Date expireDate)
    {
        expireDate = expireDate == null ? context.getCommitDate() : expireDate;
        this.deleteByCondition(CoProdMapping.class, expireDate, new DBCondition(CoProdMapping.Field.relProduct, productId));
    }

    /**
     * 根据订购对象删除产品 wukl 2012-12-15
     * 
     * @param expireDate
     */
    public void deleteProdByUserId(Long userId, Date expireDate)
    {
        List<CoProd> prods = this.deleteByCondition(CoProd.class, expireDate, new DBCondition(CoProd.Field.objectId, userId),
                new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        if (CommonUtil.isNotEmpty(prods))
        {
            Set<Long> prodIds = new HashSet<Long>();
            for (CoProd prod : prods)
            {
                prodIds.add(prod.getProductId());
            }
            // CoProdMapping有object_id的索引
            this.deleteByCondition(CoProdMapping.class, expireDate, new DBCondition(CoProdMapping.Field.objectId, userId),
                    new DBCondition(CoProdMapping.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));

            // 以下的表是没有建object_id的索引
            this.deleteByCondition(CoProdBillingCycle.class, expireDate, new DBCondition(CoProdBillingCycle.Field.productId,
                    prodIds, Operator.IN));
            this.deleteByCondition(CoProdPriceParam.class, expireDate, new DBCondition(CoProdPriceParam.Field.productId, prodIds,
                    Operator.IN));
            this.deleteByCondition(CoProdCharValue.class, expireDate, new DBCondition(CoProdCharValue.Field.productId, prodIds,
                    Operator.IN));
            this.deleteByCondition(CoBudgetCharValue.class, expireDate, new DBCondition(CoBudgetCharValue.Field.productId,
                    prodIds, Operator.IN));
            this.deleteByCondition(CoFnCharValue.class, expireDate, new DBCondition(CoFnCharValue.Field.productId, prodIds,
                    Operator.IN));
            this.deleteByCondition(CoProdCellInfo.class, expireDate, new DBCondition(CoProdCellInfo.Field.productId, prodIds,
                    Operator.IN));

            // 修改产品的PROD_EXPIRE_DATE
            CoProd valid = new CoProd();
            valid.setProdExpireDate(expireDate);
            updateDirectByCondition(valid, new DBCondition(CoProd.Field.objectId, userId), new DBCondition(
                    CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        }

        // this.deleteByCondition(CoSplitCharValue.class, expireDate, new DBCondition(CoSplitCharValue.Field.objectId, userId),new
        // DBCondition(CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        // this.deleteByCondition(CoSplitPayRel.class, expireDate, new DBCondition(CoSplitPayRel.Field.objectId, userId),new
        // DBCondition(CoSplitPayRel.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));

    }
    
    /**
     * 对主产品加前缀处理
     * @param productId
     * @param acctId
     * @param userId
     * @return
     */
    public Long convertMainProductId(Long productId,Long acctId,Long userId){
    	
    	Long prodId = ITableUtil.convertMainProductId(FlowConst.MAN_PRODUCT_ID_PREFIX, productId);
    	
    	CoProd prod = context.getSingleCache(CoProd.class, new CacheCondition(CoProd.Field.productId, prodId));
		if(prod != null){
			if(prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN){
				return prodId;
			}
		}else{
			ITableUtil.setValue2ContextHolder(null, acctId, userId);
			prod = queryProd(prodId);
			if(prod != null && prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN){
				return prodId;
			}
		}
    	return productId;
    }
    
    public CoProd getCoProdByProdId(Long prodId,Long acctId,Long userId){
    	CoProd prod = context.getSingleCache(CoProd.class, new CacheCondition(CoProd.Field.productId, prodId));
		if(prod == null){
			ITableUtil.setValue2ContextHolder(null, acctId, userId);
			prod = queryProd(prodId);
		}
		return prod;
    }

}
