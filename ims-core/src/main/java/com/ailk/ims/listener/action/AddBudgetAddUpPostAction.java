package com.ailk.ims.listener.action;

import com.ailk.ims.common.IMSContext;

/**
 * @Description: 新增预算
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author tangkun
 * @Date 2012-6-25
 * @Date 2012-8-8 tangkun #55066 新增非空判断
 * @date 2012-10-29 luojb On_Site Defect #62914 设置预算Measure_id上发abm
 */
public class AddBudgetAddUpPostAction implements IListenerAction
{
//    private ImsLogger logger = new ImsLogger(this.getClass());

    public Object doAction(IMSContext context)
    {
        
    /*
        logger.info("[" + context.getOper().getBusi_code() + "]begin to Add Up Budget", context);

        SAbmBudgetAddUp addUp = new SAbmBudgetAddUp();

        //加条件createDate获取新增产品，控制只对新增budget产品做上发abm
        List<CoProd> coProds = context.getAllCacheList(CoProd.class,
        		new CacheCondition(CoProd.Field.createDate, context.getRequestDate()));

        // 新增预算
        if (CommonUtil.isEmpty(coProds))
        {
            return null;
        }
        for (CoProd coProd : coProds)
        {
            if (coProd == null)
            {
                continue;
            }

            List<CoBudgetCharValue> coProdCharValues = context.getAllCacheList(CoBudgetCharValue.class, new CacheCondition(
                    CoBudgetCharValue.Field.productId, coProd.getProductId()), new CacheCondition(CoBudgetCharValue.Field.soNbr,
                    coProd.getSoNbr()));

            SBudgetInfo bgt = new SBudgetInfo();
            bgt.set_productId(coProd.getProductId());
            if (coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
            {
                Long billAcctId = context.get3hTree().loadUser3hBean(coProd.getObjectId()).getBillAcctId();
                bgt.set_acctId(billAcctId);
            }
            else
            {
                bgt.set_acctId(coProd.getObjectId());
            }
            bgt.set_objectId(coProd.getObjectId());
            bgt.set_objectType(coProd.getObjectType());

            // bgt.set_validDate();
            // bgt.set_expireDate();
            //@Date 2012-8-8 tangkun #55066 新增非空判断
            if(CommonUtil.isNotEmpty(coProdCharValues)){
            	for (CoBudgetCharValue charVal : coProdCharValues)
            	{
            		if (charVal.getSpecCharId() == SpecCodeDefine.BUDGET_THRESHOLD_VALUE)
            		{
            			bgt.set_budgetThreshold(Integer.valueOf(charVal.getValue()));
            		} else if (charVal.getSpecCharId() == SpecCodeDefine.BUDGET_ACTION)
            		{
            			bgt.set_actionType(Integer.valueOf(charVal.getValue()));
            		} else if (charVal.getSpecCharId() == SpecCodeDefine.BUDGET_RULE)
            		{
            			bgt.set_budgetRule(Integer.valueOf(charVal.getValue()));
            		} else if (charVal.getSpecCharId() == SpecCodeDefine.BUDGET_PAYMODE)
            		{
            			bgt.set_billingType(Short.valueOf(charVal.getValue()));
            		}
            		// 2012-10-29 luojb On_Site Defect #62914 设置预算Measure_id上发abm
            		else if(charVal.getSpecCharId() == SpecCodeDefine.BUDGET_MEASURE_ID)
            		{
            		    bgt.set_measureId(CommonUtil.string2Integer(charVal.getValue()));
            		}
            	}
            }
            addUp.set_budgetInfo(bgt);

            // 调用新增budget
            CBSErrorMsg errorMsg = new CBSErrorMsg();
            // ljc 修改账处SDL变更引起的类名称变更
            new IAbmIntfImsInt().do_addAbmBudget_upload(addUp, new CsdlStructObjectHolder<SAbmBudgetAddRet>(
                    new SAbmBudgetAddRet()), errorMsg);

        }
        */
        return null;
    }
}
