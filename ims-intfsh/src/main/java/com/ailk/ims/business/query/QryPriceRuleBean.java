package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import jef.common.wrapper.IntRange;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_qryPriceRuleResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.PriceRuleInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.QryPriceRule;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmProdOfferPriceRule;

/**
 * @description:分账规则查询！
 * @author caohm5
 * @date:2012-10-06
 */
public class QryPriceRuleBean extends BaseBusiBean
{

    private QryPriceRule req;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (QryPriceRule) input[0];

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (null == req)
        {

            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QryPriceRule");
        }
        if (!CommonUtil.isValid(req.getRule_id()) && CommonUtil.isEmpty(req.getName()))
        {

            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "rule_id、name");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        List<PriceRuleInfo> PriceRuleInfoList = qryPriceRuleInfoList(req.getRule_id(), req.getName());
        return new Object[] { PriceRuleInfoList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<PriceRuleInfo> PriceRuleInfoList = (List<PriceRuleInfo>) result[0];
        Do_qryPriceRuleResponse respn = new Do_qryPriceRuleResponse();
        respn.setPriceRuleInfoList(PriceRuleInfoList);
        return respn;
    }

    @Override
    public void destroy()
    {
    }

    /**
     * @param ruleId 规则编号
     * @param name 规则名称
     * @return
     */
    private List<PriceRuleInfo> qryPriceRuleInfoList(Integer ruleId, String name)
    {
        // 查询

        List<DBCondition> constList = new ArrayList<DBCondition>();
        if (ruleId != null)
        {
            constList.add(new DBCondition(PmProdOfferPriceRule.Field.priceRuleId, ruleId));
        }
        if (!CommonUtil.isEmpty(name))
        {
            constList.add(new DBCondition(PmProdOfferPriceRule.Field.name, name, Operator.MATCH_ANY));
        }
        constList.add(new DBCondition(PmProdOfferPriceRule.Field.priceRuleType,EnumCodeShDefine.PRICE_RULE_SPLIT));
        
        List<PmProdOfferPriceRule> pmProdOfferPriceRuleList = this.context.getComponent(BaseComponent.class).query(
                PmProdOfferPriceRule.class, new OrderCondition[] { new OrderCondition(PmProdOfferPriceRule.Field.priceRuleId) },
                new IntRange(0, 100), constList.toArray(new DBCondition[constList.size()]));
        // 转换
        if (pmProdOfferPriceRuleList == null || pmProdOfferPriceRuleList.isEmpty())
        {
            return null;
        }
        List<PriceRuleInfo> priceRuleInfoList = new ArrayList<PriceRuleInfo>();
        for (int i = 0; i < pmProdOfferPriceRuleList.size(); i++)
        {
            PmProdOfferPriceRule pmRule = pmProdOfferPriceRuleList.get(i);
            if (null == pmRule)
            {
                continue;
            }
            PriceRuleInfo info = new PriceRuleInfo();
            info.setPrice_rule_id(pmRule.getPriceRuleId());
            info.setName(pmRule.getName());
            info.setDescription(pmRule.getDescription());
            priceRuleInfoList.add(info);
        }
        return priceRuleInfoList;
    }
}
