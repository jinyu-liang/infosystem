package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AmountComponent;
import com.ailk.ims.component.SplitComponent;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.imsinner.entity.Do_querySplitListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQuerySplitByUserReq;
import com.ailk.openbilling.persistence.imsinner.entity.SplitList;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 根据 用户ID (对应coProdCharValue的特征值对应的value)，手机号码 ，查询出分账信息列表
 * @author yangjh
 * @Date 2011-11-18
 * @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换为ims侧显示的单位
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class QuerySplitByUserIdBean extends BaseBusiBean
{
    private Long userId = null;
    private String phoneId = null;
    private int splitamount = -1;
    private SplitComponent splitCmp = null;

    @Override
    public void init(Object... input) throws IMSException
    {
        SQuerySplitByUserReq req = (SQuerySplitByUserReq) input[0];
        userId = req.getUser_id();
        phoneId = (String) req.getPhone_id();
        splitCmp = context.getComponent(SplitComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {

        List<SplitList> split = new ArrayList<SplitList>();
        User3hBean userBean = context.get3hTree().loadUser3hBean(userId, phoneId);
        Long objectId = userBean.getUserId();
        Long objectType = new Long(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        IMSUtil.setUserRouterParam(objectId);
        // 查询用户的分账信息
        List<CoSplitPayRel> relList = splitCmp.querySplitPayRel(objectId, objectType);
        if (CommonUtil.isNotEmpty(relList))
        {
        	split = getSplitInfos(relList);
        }

        return new Object[] { split };
    }

    /**
     * 根据代付关系查询分账信息
     * 
     * @author zenglu 2012-08-31
     */
    private List<SplitList> getSplitInfos(List<CoSplitPayRel> relList)
    {
        List<SplitList> split = new ArrayList<SplitList>();
        for (CoSplitPayRel rel : relList)
        {
            // 设置要返回的分账信息
            SplitList sp = new SplitList();
            if(rel.getReguidObjectType()==EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT){
            	sp.setAcct_id(rel.getObjectId());
            }else{
            	sp.setUser_id(rel.getObjectId());
            }
            sp.setExpire_date(DateUtil.getFormatDate(rel.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            sp.setValid_date(DateUtil.getFormatDate(rel.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            sp.setPay_acct_id(rel.getReguidObjectId());
           
            IMSUtil.removeRouterParam();
            //对用户、账户id进行分表缓存  
            if(rel.getReguidObjectType()==EnumCodeDefine.PROD_OBJECTTYPE_DEV){
                IMSUtil.setUserRouterParam(rel.getReguidObjectId());
            }else{
                IMSUtil.setAcctRouterParam(rel.getReguidObjectId());
            }

            List<CoSplitCharValue> charValues=splitCmp.querySplitProdCharValue(rel.getProductId());
            if (!CommonUtil.isEmpty(charValues))
            {
                Map maps = new HashMap<Long, Long>();
                for (CoSplitCharValue charValue : charValues)
                {
                    if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRODUCT_ID)
                        sp.setProduct_id(CommonUtil.string2Long(charValue.getValue()));
                    if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_PRIORITY)
                        sp.setPriority(CommonUtil.string2Short(charValue.getValue()));
                    if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_AMOUNT)
                    {
                        maps.put(charValue.getSpecCharId(), charValue.getValue());
                    }
                    if (charValue.getSpecCharId() == SpecCodeDefine.SPLIT_NUMERATOR)
                    {
                        maps.put(charValue.getSpecCharId(), charValue.getValue());
                    }
                }
                // 根据12802的特征值来判断part_type， 如果为-1 则part_type=0，为按比例分账，则part_value取12803的值；
                //   若12802的特征值不为-1，则part_type=1，为按数量分账，则part_value取12802的值
                Long value = CommonUtil.string2Long((String) maps.get(SpecCodeDefine.SPLIT_AMOUNT));
                if (value.equals(CommonUtil.int2Long(splitamount)))
                {
                    sp.setPart_value(CommonUtil.string2Long((String) maps.get(SpecCodeDefine.SPLIT_NUMERATOR)));
                    sp.setPart_type((long) EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE);
                }
                else
                {
                    // @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换为ims侧显示的单位
                    AmountComponent amountCmp = context.getComponent(AmountComponent.class);
                    Integer orgMeasureId = context.getComponent(ProductQuery.class).querySplitMeasureId(rel.getProductId(),rel.getReguidObjectId());
                    sp.setPart_value(CommonUtil.double2Long(amountCmp.parseImsAmount(orgMeasureId, CommonUtil.long2Double(value))));
                    sp.setPart_type((long) EnumCodeDefine.PAY_PART_TYPE_AMOUNT);
                }
            }
            split.add(sp);
        }
        return split;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_querySplitListResponse resp = new Do_querySplitListResponse();
        List<SplitList> split = (List<SplitList>) result[0];
        if(CommonUtil.isNotEmpty(split)){
        	resp.setSplitList_Item(split.toArray(new SplitList[split.size()]));
        }
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	return null;
    }

}
