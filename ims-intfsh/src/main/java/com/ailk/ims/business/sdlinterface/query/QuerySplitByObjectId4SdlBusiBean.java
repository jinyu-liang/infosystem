package com.ailk.ims.business.sdlinterface.query;

import java.util.ArrayList;
import java.util.List;
import jef.common.wrapper.Holder;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.SplitShComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SQuerySplitRel;
import com.ailk.openbilling.persistence.imssdl.entity.SSplitInfo;
import com.ailk.openbilling.persistence.imssdl.entity.Split;

/**
 * @Description 根据 分账的 账户Id( 若A为B分账，即为B )、起始时间、结束时间查出分账（split）信息。
 * @author zenglu
 * @Date 2012-07-27
 */
public class QuerySplitByObjectId4SdlBusiBean extends BaseBusiBean
{
    private Long objectId = null;
    private Long objectType = null;
    private String validDate = null;
    private SplitShComponent splitShCmp = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        SQuerySplitRel req = (SQuerySplitRel) input[0];
        objectId = req.getObjectId();
        objectType = req.getObjectType();
        validDate = req.getValidDate();
        splitShCmp = context.getComponent(SplitShComponent.class);
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        List<Split> splitList = new ArrayList<Split>();
        Holder holder = (Holder) input[1];
        List<SSplitInfo> splitInfoList = (List<SSplitInfo>) holder.get();

		if(objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV){
		    IMSUtil.setUserRouterParam(objectId);
		}
		else
		{
		    IMSUtil.setAcctRouterParam(objectId);
		}
        // 先查账户或用户的分账产品，再查分账信息
        List<CoSplitPayRel> relList = splitShCmp.querySplitPayRel(objectId, objectType, IMSUtil.formatDate(validDate));
        if (CommonUtil.isEmpty(relList))
        {
        	return null;
        }
        splitList = splitShCmp.getSplitInfos(relList);
        // 把splitList转为sdl的splitInfoList
        for (Split split : splitList)
        {
            SSplitInfo sSplitInfo = new SSplitInfo();
            copySplitList2SSplitInfo(split, sSplitInfo);
            splitInfoList.add(sSplitInfo);
        }
        return new Object[] { splitInfoList };
    }

    private void copySplitList2SSplitInfo(Split split, SSplitInfo sSplitInfo)
    {
        sSplitInfo.setProductId(split.getProduct_id());
        sSplitInfo.setPayAcctId(split.getPay_acct_id());
        sSplitInfo.setPriority(split.getPriority());
        sSplitInfo.setValidDate(split.getValid_date());
        sSplitInfo.setExpireDate(split.getExpire_date());
        sSplitInfo.setPartValue(split.getPart_value());
        // sSplitInfo.setPartType(split.getPart_type());
        sSplitInfo.setSplitNumerator(split.getSplit_numerator());
        sSplitInfo.setSplitDenominator(split.getSplit_denominator());
        sSplitInfo.setSplitType(split.getSplit_type());
        if (CommonUtil.isValid(split.getAcct_id()))
        {
            sSplitInfo.setAcctId(split.getAcct_id());
        }
        if (CommonUtil.isValid(split.getUser_id()))
        {
            sSplitInfo.setUserId(split.getUser_id());
        }
        if (CommonUtil.isValid(split.getPrice_rule_id()))
        {
            sSplitInfo.setPriceRuleId(split.getPrice_rule_id());
        }
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_sdlResponse rs = new Do_sdlResponse();
        return rs;
    }

    @Override
    public void destroy()
    {

    }

}
