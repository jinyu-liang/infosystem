package com.ailk.imssh.charValue.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.IConditionField.Or;

import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.ColCodeDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.imssh.acctpayrel.cmp.SplitCmp;
import com.ailk.imssh.acctpayrel.helper.IProdCharValueHelper;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.define.SpecCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IProdCharValue;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharUse;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharValue;

/**
 * @Date 2013-11-27
 * @author dingjiang3
 */
public class ProdCharValueCmp extends BaseCmp {

	public void beforeHandle(List<? extends DataObject> dataList) {

	}

	public void modifyCharValue(IProdCharValue value) {
		CoProdCharValue charValue = convertCharValue(value,false);

		this.updateMode2(charValue, EnumCodeExDefine.OPER_TYPE_UPDATE,value.getValidDate(),new DBCondition(CoProdCharValue.Field.productId,value.getProductId()), 
				new DBCondition(CoProdCharValue.Field.specCharId,value.getParamId()));
	
	}
	
	/**
	 * (201306，2011，2010)的修改
	 * @param iProdCharValue
	 */
	private void updateCoProdCharValue(IProdCharValue iProdCharValue){
		this.updateMode2(convertCoProdCharValue(iProdCharValue,SpecCodeExDefine.SPEC_10801,iProdCharValue.getServiceId().toString(),true), 
				                EnumCodeExDefine.OPER_TYPE_UPDATE,iProdCharValue.getValidDate(),
								new DBCondition(CoProdCharValue.Field.productId,iProdCharValue.getProductId()),
								new DBCondition(CoProdCharValue.Field.specCharId,SpecCodeExDefine.SPEC_10801));
		
		this.updateMode2(convertCoProdCharValue(iProdCharValue,SpecCodeExDefine.SPEC_10802,iProdCharValue.getParamId().toString(),true), 
				 EnumCodeExDefine.OPER_TYPE_UPDATE,iProdCharValue.getValidDate(),
				new DBCondition(CoProdCharValue.Field.productId,iProdCharValue.getProductId()),
				new DBCondition(CoProdCharValue.Field.specCharId,SpecCodeExDefine.SPEC_10802));

		this.updateMode2(convertCoProdCharValue(iProdCharValue,SpecCodeExDefine.SPEC_10803,iProdCharValue.getParamValue(),true), 
				 EnumCodeExDefine.OPER_TYPE_UPDATE,iProdCharValue.getValidDate(),
				new DBCondition(CoProdCharValue.Field.productId,iProdCharValue.getProductId()),
				new DBCondition(CoProdCharValue.Field.specCharId,SpecCodeExDefine.SPEC_10803));

	}
	
	private void deleteCoProdCharValue(IProdCharValue iProdCharValue){
		CoProdCharValue cpv=new CoProdCharValue();
		cpv.setExpireDate(iProdCharValue.getExpireDate());
		this.updateMode2(cpv, EnumCodeExDefine.OPER_TYPE_DELETE,iProdCharValue.getValidDate(),
				new DBCondition(CoProdCharValue.Field.productId,iProdCharValue.getProductId()),
				new DBCondition(CoProdCharValue.Field.specCharId,new Integer[]{SpecCodeExDefine.SPEC_10801,SpecCodeExDefine.SPEC_10802,SpecCodeExDefine.SPEC_10803},Operator.IN));
		
	}
	
	private void deleteSingleCharValue(IProdCharValue iProdCharValue){
		CoProdCharValue cpv=new CoProdCharValue();
		cpv.setExpireDate(iProdCharValue.getExpireDate());
		this.updateMode2(cpv, EnumCodeExDefine.OPER_TYPE_DELETE,iProdCharValue.getValidDate(),
				new DBCondition(CoProdCharValue.Field.productId,iProdCharValue.getProductId()),
				new DBCondition(CoProdCharValue.Field.specCharId,new Integer[]{SpecCodeExDefine.SPEC_10801,SpecCodeExDefine.SPEC_10802,SpecCodeExDefine.SPEC_10803},Operator.IN));
	}
	
	/**
	 * 
	 * @param iProdCharValue
	 * (201306，2011，2010)的入库处理
	 */
	private void insertCoProdCharValue(IProdCharValue iProdCharValue){
		List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();
		list.add(convertCoProdCharValue(iProdCharValue,SpecCodeExDefine.SPEC_10801,iProdCharValue.getServiceId().toString(),false));
		list.add(convertCoProdCharValue(iProdCharValue,SpecCodeExDefine.SPEC_10802,iProdCharValue.getParamId().toString(),false));
		list.add(convertCoProdCharValue(iProdCharValue,SpecCodeExDefine.SPEC_10803,iProdCharValue.getParamValue(),false));
		this.insertBatch(list);
	}
	
	/**
	 * (201306，2011，2010)iProdCharValue转换成CoProdCharValue,1条拆分成3条
	 * @param iProdCharValue
	 * @param specCharId
	 * @param value
	 * @return
	 */
	private CoProdCharValue convertCoProdCharValue(IProdCharValue iProdCharValue,int specCharId,String value,boolean isUpdate){
		CoProdCharValue prodCharValue = new CoProdCharValue();
		prodCharValue.setGroupId(0l);
		prodCharValue.setValue(value);
		prodCharValue.setCreateDate(iProdCharValue.getValidDate());
		if(!isUpdate){
			prodCharValue.setProductId(iProdCharValue.getProductId());
			prodCharValue.setSpecCharId(specCharId);
			prodCharValue.setValidDate(iProdCharValue.getValidDate());

		}
		prodCharValue.setExpireDate(iProdCharValue.getExpireDate());
		prodCharValue.setObjectId(iProdCharValue.getUserId());
		prodCharValue.setObjectType(0);
		return prodCharValue;
	}
	
	// 创建特征值
	private void createCoProdCharValue(IProdCharValue value) {

			CoProdCharValue charValue = convertCharValue(value,true);
			
			this.insert(charValue);
			
	}
	private void createSplitCoProdCharValue(List<IProdCharValue> splitList) {
        List<CoProdCharValue> coProdCharValueList=new ArrayList<CoProdCharValue>();
        long  groupId=0l;
        for(IProdCharValue value :splitList){
    		CoProdCharValue charValue = convertSplitCharValue(value,true,groupId);
    		groupId++;
        }
		
		this.insertBatch(coProdCharValueList);
		
    }
	private CoProdCharValue convertSplitCharValue(IProdCharValue value,boolean flag,Long groupId){
		CoProdCharValue charValue = new CoProdCharValue();
		charValue.setCreateDate(context.getCommitDate());
		charValue.setExpireDate(value.getExpireDate());
		charValue.setValidDate(value.getValidDate());
		charValue.setValue(value.getParamValue());
		charValue.setObjectType(value.getObjectType());
		if(value.getObjectType() == 1){
					
			charValue.setObjectId(value.getAcctId());
		}else{
					
			charValue.setObjectId(value.getUserId());
		}
		if(flag){
			charValue.setSpecCharId(value.getParamId());
			charValue.setProductId(value.getProductId());
		}
		charValue.setSoNbr(context.getSoNbr());
//				int specCharId = convertSpecId(dmProd, value.getParamId());
//				if(specCharId != 0){
//					charValue.setSpecCharId(specCharId);
//				}else{
//					continue;
//				}
		

		charValue.setGroupId(groupId);
		return charValue;
	}

	private CoProdCharValue convertCharValue(IProdCharValue value,boolean flag){
		CoProdCharValue charValue = new CoProdCharValue();
		charValue.setCreateDate(context.getCommitDate());
		charValue.setExpireDate(value.getExpireDate());
		charValue.setValidDate(value.getValidDate());
		charValue.setValue(value.getParamValue());
		charValue.setObjectType(value.getObjectType());
		if(value.getObjectType() == 1){
					
			charValue.setObjectId(value.getAcctId());
		}else{
					
			charValue.setObjectId(value.getUserId());
		}
		if(flag){
			charValue.setSpecCharId(value.getParamId());
			charValue.setProductId(value.getProductId());
		}
		charValue.setSoNbr(context.getSoNbr());
//				int specCharId = convertSpecId(dmProd, value.getParamId());
//				if(specCharId != 0){
//					charValue.setSpecCharId(specCharId);
//				}else{
//					continue;
//				}
		

		charValue.setGroupId(0l);
		return charValue;
	}

	// 添加默认值
	public List<IProdCharValue> addDefaultSpecChars(List<IProdCharValue> list, CoProd coProd) {
		if (CommonUtil.isEmpty(list)) {
			list = new ArrayList<IProdCharValue>();
		}

		// @Date 2012-08-29 lijc3 处理静态小区cell_code读取销售品配置
		int busiFlag = 0;

		Offering3hbean offering3hbean = getOffer3hBean(coProd);
		PmProductOffering dmOffering = offering3hbean.getOffering();

		if (dmOffering.getSpecTypeFlag() != null && dmOffering.getSpecTypeFlag() == 1) {
			busiFlag = coProd.getBusiFlag();
		} else {
			busiFlag = offering3hbean.getBusiFlag();
		}

		if (busiFlag == SpecCodeDefine.HOME_ZONE) {
			List<Integer> cellCodeList = context.getComponent(ProductQuery.class).queryCellCode(coProd.getProductOfferingId().intValue());
			if (CommonUtil.isNotEmpty(cellCodeList)) {
				for (Integer cellCode : cellCodeList) {
					IProdCharValue charValue = new IProdCharValue();
					charValue.setParamId(SpecCodeDefine.HOME_ZONE_CELL);
					charValue.setParamValue(String.valueOf(cellCode));
					charValue.setCommitDate(coProd.getCreateDate());
					charValue.setExpireDate(coProd.getExpireDate());
					charValue.setValidDate(coProd.getValidDate());
					charValue.setProductId(coProd.getProductId());
					charValue.setSoNbr(coProd.getSoNbr());
					list.add(charValue);
				}
			}
		}
		// 两城一家的处理
		else if (busiFlag == SpecCodeDefine.TWO_CITY_ONE_HOME) {
			List<PmProductOfferSpecChar> specChars = offering3hbean.getOfferSpecCharList();
			if (CommonUtil.isNotEmpty(specChars)) {
				for (PmProductOfferSpecChar specChar : specChars) {
					IProdCharValue charValue = new IProdCharValue();
					charValue.setParamId(SpecCodeDefine.TWO_CITY_ONE_HOME_CELL);
					charValue.setParamValue(specChar.getValue());
					charValue.setCommitDate(coProd.getCreateDate());
					charValue.setExpireDate(coProd.getExpireDate());
					charValue.setValidDate(coProd.getValidDate());
					charValue.setProductId(coProd.getProductId());
					charValue.setSoNbr(coProd.getSoNbr());
					list.add(charValue);
				}
			}

		}

		List<Integer> specIds = querySpecCharIdsByOfferId(coProd.getProductOfferingId());
		if (!CommonUtil.isEmpty(specIds)) {
			// 传入的特征值id集合
			List<Integer> inParamIds = new ArrayList<Integer>();
			for (IProdCharValue sp : list) {
				// 传入的特征值id集合只存放数据库存在的，传入的但是数据库不存在则不添加到传入的特征值集合中
				if (specIds.contains(sp.getParamId())) {
					inParamIds.add(sp.getParamId());
				}
			}
			List<Integer> notInParamIds = new ArrayList<Integer>();
			for (Integer specId : specIds) {
				// 传入的参数id不包含从数据库里面查出来的，则不包含的特征值需要设置默认值
				if (!inParamIds.contains(specId)) {
					notInParamIds.add(specId);
				}
			}
			// 没有传入的特征值id，看有没有默认值，有，就设置到sproductorder里面
			if (notInParamIds.size() > 0) {
				List<PmProductSpecCharValue> specCharValues = query(PmProductSpecCharValue.class, new DBCondition(
						PmProductSpecCharValue.Field.specCharId, completeListIntSize(notInParamIds), Operator.IN), new DBCondition(
						PmProductSpecCharValue.Field.isDefault, 1));// 1表示默认值
				if (!CommonUtil.isEmpty(specCharValues)) {
					for (PmProductSpecCharValue value : specCharValues) {
						IProdCharValue charValue = new IProdCharValue();
						charValue.setParamId(value.getSpecCharId());
						charValue.setParamValue(value.getValue());
						charValue.setCommitDate(coProd.getCreateDate());
						charValue.setExpireDate(coProd.getExpireDate());
						charValue.setValidDate(coProd.getValidDate());
						charValue.setProductId(coProd.getProductId());
						charValue.setSoNbr(coProd.getSoNbr());
						list.add(charValue);
					}
				}
			}
		}
		// // 将处理完后的charvaluelist放入complex中
		if (CommonUtil.isNotEmpty(list)) {
			// complex.setValueList(list);
			return list;
		} else {
			// complex.setValueList(null);
			return null;
		}
	}

	public List<Integer> querySpecCharIdsByOfferId(Integer offerId) {
		Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offerId.longValue());
		List<PmProductSpecCharUse> useList = offerBean.getSpecCharUseList();
		if (CommonUtil.isEmpty(useList)) {
			return null;
		}
		List<Integer> specCharIds = new ArrayList<Integer>();
		for (PmProductSpecCharUse use : useList) {
			specCharIds.add(use.getSpecCharId());
		}
		return specCharIds;
	}

	public Offering3hbean getOffer3hBean(CoProd coProd) {
		Long offerId = null;
		if (coProd != null) {
			offerId = coProd.getProductOfferingId().longValue();
		}
		Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offerId);
		return offerBean;
	}

	private String getSpecCharValueById(int specCharId, List<PmProductSpecCharValue> result) {
		// 如果未配置，直接抛异常
		if (CommonUtil.isEmpty(result)) {
			return null;
		}
		for (PmProductSpecCharValue value : result) {
			if (value.getSpecCharId() == specCharId) {
				return value.getValue();
			}
		}
		return null;
	}

	public CoBudgetCharValue createBudgetCharValue(CoProd dmProd, Long groupId, int specCharId, String value, Date validDate,
			Date expireDate) {
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

	private int convertSpecId(CoProd dmProd, int specId) {
		if (specId == SpecCodeDefine.GROUP_IN_PERSONAL_ID) {
			if (dmProd.getBusiFlag() == SpecCodeDefine.GROUP_IN_PERSON) {
				return SpecCodeDefine.GROUP_IN_PERSONAL_ID;
			} else if (dmProd.getBusiFlag() == SpecCodeDefine.GROUP_NO_PORT_PERSON) {
				return SpecCodeDefine.GROUP_NO_PORT_PERSONAL_ID;
			} else if (dmProd.getBusiFlag() == SpecCodeDefine.GROUP_OUT_PERSON) {
				return SpecCodeDefine.GROUP_OUT_PERSONAL_ID;
			} else {
				return 0;
			}
		} else if (specId == SpecCodeExDefine.REMIND_NOTIFY_30320) {
			if (ProjectUtil.is_CN_XZ()
					&& (dmProd.getProductOfferingId() == EnumCodeExDefine.REMIND_OFFERING_XZ_90004091 
							|| dmProd.getProductOfferingId() == EnumCodeExDefine.REMIND_OFFERING_XZ_90004092)) {
				return SpecCodeExDefine.REMIND_NOTIFY_30321;
			} else {
				return specId;
			}
		} else {
			return specId;
		}

	}

	
	public void operateProduct(List<IProdCharValue> charValueList) {
		RouterCmp cmp=this.context.getCmp(RouterCmp.class);
		for (IProdCharValue iProdCharValue : charValueList) {
			Long acctId=cmp.queryAcctIdByUserId(iProdCharValue.getUserId());
			ITableUtil.setValue2ContextHolder(null,acctId,null);
			int serviceId = iProdCharValue.getServiceId()!=null?iProdCharValue.getServiceId():0;
			if(serviceId == EnumCodeExDefine.USER_SERVICE_201306 || serviceId == EnumCodeExDefine.USER_SERVICE_2011 || serviceId == EnumCodeExDefine.USER_SERVICE_2010){
				//(201306,2011,2010)特殊处理
				if (iProdCharValue.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT) {
					insertCoProdCharValue(iProdCharValue);
				} else if (iProdCharValue.getOperType() == EnumCodeExDefine.OPER_TYPE_UPDATE) {
					updateCoProdCharValue(iProdCharValue);
				} else {
					deleteCoProdCharValue(iProdCharValue);
				}
			}else if(iProdCharValue.getParamId()==EnumCodeExDefine.PARAM_ID_30320||iProdCharValue.getParamId()==EnumCodeExDefine.PARAM_ID_30321){
				List<IProdCharValue> splitList=splitIProdCharVlalue(iProdCharValue);
					if (iProdCharValue.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT) {
						createSplitCoProdCharValue(splitList);						
					} else if (iProdCharValue.getOperType() == EnumCodeExDefine.OPER_TYPE_UPDATE) {
					
						modifyCharValue(iProdCharValue);
					} else {
						deleteSingleCharValue(iProdCharValue);
						
					}
				
			}else{
				
				if (iProdCharValue.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT) {
					createCoProdCharValue(iProdCharValue);
					
				} else if (iProdCharValue.getOperType() == EnumCodeExDefine.OPER_TYPE_UPDATE) {
				
					modifyCharValue(iProdCharValue);
				} else {
					deleteSingleCharValue(iProdCharValue);
					
				}
			}
		}

	}

	/**
	 * 30320 30321的要拆记录
	 * @param value
	 * @return
	 */
	private List<IProdCharValue> splitIProdCharVlalue(IProdCharValue value) {
		
		ArrayList<IProdCharValue> valueList = new ArrayList<IProdCharValue>();
		if (CommonUtil.isIn(value.getParamId(), ColCodeDefine.REMIND_NOTIFY_SPEC_CHAR_ID)) {
			String[] newValue = value.getParamValue().split("\\|");
			if (newValue.length == 1) {
				String paramValue=replaceParamValue(value.getParamId(), value.getParamValue());
				value.setParamValue(paramValue);
				valueList.add(value);
				return valueList;
			}
			IProdCharValueHelper helper = new IProdCharValueHelper(IProdCharValue.class, value.getProductId(), value.getUserId(),
					value.getAcctId(), value.getValidDate(), value.getExpireDate(), value.getCommitDate(), value.getSoNbr(),
					value.getParamId(), value.getOperType());
			for (String string : newValue) {
				string=replaceParamValue(value.getParamId(), string);
				valueList.add(helper.getNew(string, value));
			}
			return valueList;
		}
		valueList.add(value);
		return valueList;
	}
	/**
	 * 
	 * @param paramId
	 * @param pramValue
	 * @return
	 */
    private String replaceParamValue(Integer paramId,String pramValue){
    	if(paramId==EnumCodeExDefine.PARAM_ID_30320){
    		if(pramValue.equals("0/100")){
        		return EnumCodeExDefine.PARAM_VALUE_30320_0;
    		}else if(pramValue.equals("10/100")){
        		return EnumCodeExDefine.PARAM_VALUE_30320_10;
    		}else if(pramValue.equals("25/100")){
        		return EnumCodeExDefine.PARAM_VALUE_30320_25;
    		}else if(pramValue.equals("50/100")){
        		return EnumCodeExDefine.PARAM_VALUE_30320_50;
    		}else{
				throw IMSUtil.throwBusiException("paramId为"+paramId+",pramValu有误，不在0/100,10/100,25/100,50/100范围内");
    		}
    	}else{
    		if(pramValue.equals("0")){
        		return EnumCodeExDefine.PARAM_VALUE_30321_0;
    		}else if(pramValue.equals("10")){
        		return EnumCodeExDefine.PARAM_VALUE_30321_10;
    		}else if(pramValue.equals("100")){
        		return EnumCodeExDefine.PARAM_VALUE_30321_100;
    		}else if(pramValue.equals("30")){
        		return EnumCodeExDefine.PARAM_VALUE_30321_30;
    		}else if(pramValue.equals("5")){
        		return EnumCodeExDefine.PARAM_VALUE_30321_5;
    		}else{
				throw IMSUtil.throwBusiException("paramId为"+paramId+",pramValu有误，不在0,10,30,100,5范围内");
    		}
    	}
   
    }
	private CoProdCharValue createCoProdCharValue(long productId, long groupId, int specCode, String value, Date expireDate,
			Date validDate, Long objectId, Integer objectType) throws IMSException {
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

	private Set<Integer> getInSpecIds(List<IProdCharValue> params) {
		Set<Integer> specIds = new HashSet<Integer>();
		for (IProdCharValue param : params) {
			int specCharId = param.getParamId();
			if(specCharId != 0){
				specIds.add(specCharId);
			}
		}
		return specIds;
	}

}
