package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jef.database.Condition.Operator;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.complex.CheckedComplex;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.CheckComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryBudgetRuleResponse;
import com.ailk.openbilling.persistence.imsinner.entity.RuleAndName;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryBudgetRuleReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmBudgetItemLimit;
import com.ailk.openbilling.persistence.pm.entity.PmBudgetRegulation;
import com.ailk.openbilling.persistence.pm.entity.PmPriceEvent;
import com.ailk.openbilling.persistence.pm.entity.PmProdOfferPriceRule;

/**
 *@author taoyf
 *@date 2012-2-29
 *@time 上午10:43:43
 */
public class QueryBudgeRuleBean extends BaseBusiBean {

	@Override
	public void init(Object... input) throws BaseException {		
	}

	@Override
	public void validate(Object... input) throws BaseException {		
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		SQueryBudgetRuleReq req = (SQueryBudgetRuleReq)input[0];
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(req.getUser_id(),req.getPhone_id()));
        return list;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		SQueryBudgetRuleReq req = (SQueryBudgetRuleReq)input[0];
		//对于GUI来说不可能同时输入用户编号和手机号码的
		//caohm5 add 2012-01-01
		//查询条件中增加了手机号码字段
		Long userId=null;
		String phoneId=null;
		
		if(CommonUtil.isValid(phoneId)){
			userId = req.getUser_id();
		}
		if(CommonUtil.isValid(userId)){
			phoneId=req.getPhone_id();
		}
		
		if(!CommonUtil.isValid(phoneId)&&!CommonUtil.isValid(userId)){
			return null;
		}
		else if(CommonUtil.isValid(userId)&&!CommonUtil.isValid(phoneId)){
			
			context.getComponent(CheckComponent.class).checkUserId(userId);
		}
		else if(!CommonUtil.isValid(userId)&&CommonUtil.isValid(phoneId)){
			
			CheckedComplex checkCpl=context.getComponent(CheckComponent.class).checkPhoneId(phoneId);
			userId=checkCpl.getUserId();
			
		}
		
		BaseComponent baseCmp = context.getComponent(BaseComponent.class);
		List<CmResServCycle> list = baseCmp.query(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId, userId));
		List<Integer> specList = new ArrayList<Integer>();
		
		List<RuleAndName> result = new ArrayList<RuleAndName>();
		for(CmResServCycle serv : list){
			specList.add(serv.getServiceSpecId());					
		}
		
		DBJoinCondition joinCon = new DBJoinCondition(PmBudgetItemLimit.class);
		joinCon.innerJoin(PmPriceEvent.class, 
				new DBRelation(PmBudgetItemLimit.Field.itemId, PmPriceEvent.Field.itemId));
		joinCon.innerJoin(PmBudgetRegulation.class, 
				new DBRelation(PmBudgetItemLimit.Field.priceRuleId, PmBudgetRegulation.Field.priceRuleId));
		joinCon.innerJoin(PmProdOfferPriceRule.class, 
				new DBRelation(PmBudgetItemLimit.Field.priceRuleId, PmProdOfferPriceRule.Field.priceRuleId));
		
		List<Map> res = baseCmp.queryJoin(joinCon, new DBCondition(PmPriceEvent.Field.serviceSpecId, specList, Operator.IN));
		List<RuleAndName> rules = new ArrayList<RuleAndName>();
		if(CommonUtil.isEmpty(res)){
			return null;
		}
		for(Map map : res){
			if(map == null){
				continue;
			}
			RuleAndName rule = new RuleAndName();
			String name = ((PmProdOfferPriceRule)map.get(PmProdOfferPriceRule.class)).getName();
			Integer ruleId = ((PmProdOfferPriceRule)map.get(PmProdOfferPriceRule.class)).getPriceRuleId();
			if(ruleId == null){
				continue;
			}
			if(name == null){
				continue;
			}
			rule.setName(name);
			rule.setRule_id(ruleId);
			rules.add(rule);
		}
		Do_queryBudgetRuleResponse response = new Do_queryBudgetRuleResponse();
		response.setRules(rules);
		
		return new Object[]{response};
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		if(result == null){
			return new Do_queryBudgetRuleResponse();
		}else{
			return (BaseResponse) result[0];
		}
		
	}

	@Override
	public void destroy() {

	}

}
