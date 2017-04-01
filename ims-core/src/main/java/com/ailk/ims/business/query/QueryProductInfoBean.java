package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.ProductCycleComponent;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProductInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SProdSpecChar;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryProductInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 查询产品信息信息
 * @author yangyang
 * @Date 2011-9-27
 * @Date 2012-06-07 yangjh 创建3Hbean增加判断避免userId没传报错
 * @Date 2012-06-07 yangjh load3hBean 修改
 * @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换
 * @date 2012-10-03 luojb On_Site Defect #60419
 * @date 2012-10-05 luojb On_Site Defect #60462
 * @date 2012-10-23 sunpf3 On_Site Defect #62288
 * @date 2012-11-17 zhangzj3 设置路由
 * @date 2012-11-19 luojb Task #65597 增加acct_id入参，设置路由
 * @2012-11-26 wukl 上海存在空的情况，当产品账期与账户账期一致时，该表不会实例化数据
 */
public class QueryProductInfoBean extends BaseBusiBean
{
    private Long productId = null;
    private BaseProductComponent pc = null;
    private CoProd coProd = null;
    private List<CoProdCharValue> charValues = null;
    private List<CoProdBillingCycle> cycles = null;
    private List<CoProdPriceParam> params = null;
    private List<SProdSpecChar> sProdSpecCharList = null;

    @Override
    public void init(Object... input) throws IMSException
    {
        pc = context.getComponent(BaseProductComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryProductInfoReq req = (SQueryProductInfoReq) input[0];

        productId = req.getProductId();

        if (!CommonUtil.isValid(productId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_ID_IS_NULL);
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SQueryProductInfoReq req = (SQueryProductInfoReq) input[0];
        Long acctId = req.getAcct_id();
        Long userId = req.getUser_id();
        IMS3hBean bean = context.get3hTree().load3hBean(null, acctId, userId, null);
        Integer objectType = null;
        Long objectId = null;
        if (bean.isUser3hBean())
        {
            objectId = bean.getUserId();
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
            IMSUtil.setUserRouterParam(userId);
        }
        else if (bean.isGroup3hBean())
        {
            objectId = bean.getAcctId();
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_GCA;
            IMSUtil.setAcctRouterParam(acctId);
        }
        else if (bean.isAcct3hBean())
        {
            objectId = bean.getAcctId();
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
            IMSUtil.setAcctRouterParam(acctId);
        }

        List<DBCondition> conds = new ArrayList<DBCondition>();
        conds.add(new DBCondition(CoProd.Field.productId, productId));
        if (objectId != null)
        {
            conds.add(new DBCondition(CoProd.Field.objectId, objectId));
            conds.add(new DBCondition(CoProd.Field.objectType, objectType));
        }

        coProd = pc.querySingle(CoProd.class, conds.toArray(new DBCondition[conds.size()]));
        if (coProd == null)
            return null;

        charValues = pc.queryProdCharValue(productId, coProd.getObjectId());
        cycles = context.getComponent(ProductCycleComponent.class).queryProdBillingCycleAsc(productId);
        // @2012-11-26 wukl 上海存在空的情况，当产品账期与账户账期一致时，该表不会实例化数据
        if (!ProjectUtil.is_CN_SH())
        {
            if (CommonUtil.isEmpty(cycles))
                throw IMSUtil.throwBusiException(ErrorCodeDefine.BILL_CYCLE_NOT_EXIST);
        }
        // lijc3不是上海工程才进这段逻辑
        if (!ProjectUtil.is_CN_SH())
        {
            params = pc.queryProdPriceParam(productId, coProd.getObjectId());
            // @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换
            if (CommonUtil.isNotEmpty(params))
            {
                context.getComponent(BaseProductComponent.class).packgeShowPriceParam(params);
            }
            sProdSpecCharList = context.getComponent(ProductQuery.class).queryProdSpecCharValue(coProd.getProductOfferingId());
            // @Date 2012-10-23 sunpf3 : On_Site Defect #62288,空指针异常
            if (!CommonUtil.isEmpty(sProdSpecCharList))
            {
                // @Date 2012-09-27 wangdw5 : On_Site Defect #60013 没有展示IMIS和Serial number
                for (SProdSpecChar sProdSpecChar : sProdSpecCharList)
                {
                    sProdSpecChar.setChar_value(getCahr_value(sProdSpecChar, charValues));
                }
            }
        }

        return null;
    }

    private String getCahr_value(SProdSpecChar sProdSpecChar, List<CoProdCharValue> charValues)
    {
        for (CoProdCharValue coProdCharValue : charValues)
        {
            if (coProdCharValue.getSpecCharId().equals(sProdSpecChar.getSpec_char_id()))
            {
                return coProdCharValue.getValue();
            }
        }
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryProductInfoResponse resp = new Do_queryProductInfoResponse();
        if (coProd != null)
            resp.setCoProd(coProd);
        if (CommonUtil.isNotEmpty(cycles))
            resp.setCoProdBillingCycleList(cycles);
        if (CommonUtil.isNotEmpty(charValues))
            resp.setCoProdCharValueList(charValues);
        if (CommonUtil.isNotEmpty(params))
            resp.setCoProdPriceParamList(params);
        if (CommonUtil.isNotEmpty(sProdSpecCharList))
            resp.setSProdSpecCharList(sProdSpecCharList);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        // 2012-10-03 luojb On_Site Defect #60419内部查询接口不收一次性费用，不记业务记录，不用创建3hbean（避免查询产品报错）
        return null;
    }

}
