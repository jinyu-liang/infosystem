package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserByOfferNmaeResponse;
import com.ailk.openbilling.persistence.imsinner.entity.ProdList;
import com.ailk.openbilling.persistence.imsinner.entity.SPage;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserByOfferNameReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.imsintf.entity.SUserList;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * 根据电话号码，销售品名称查询用户列表及产品信息列表
 * 
 * @Description
 * @author xieqr
 * @Date 2012-3-5
 * @Date 2012-5-7 tangjl5 判断销售品是否存在
 */

public class QueryUserByOfferNameBean extends BaseBusiBean
{

    private SQueryUserByOfferNameReq req;
    private String phoneId;
    private String offerName;
    private SPage page;
    private BaseProductComponent prodCmp = null;
    private int start;
    private int limit;
    private int count = 0;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (SQueryUserByOfferNameReq) input[0];
        prodCmp = context.getComponent(BaseProductComponent.class);

        phoneId = req.getPhone_id();
        offerName = req.getOffer_name();
        page = req.getPage();
        if (page == null || page.getNum() == null)
        {
            start = 0;
        }
        else
        {
            start = page.getNum();
        }
        if (page == null || page.getSize() == null)
        {
            limit = 10;
        }
        else
        {
            limit = page.getSize();
        }

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (CommonUtil.isEmpty(phoneId) && CommonUtil.isEmpty(offerName))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phone_id or offer_name");
        }

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(null,req.getPhone_id()));
        return list;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        List<SUser> userList = new ArrayList<SUser>();
        List<ProdList> prodList = new ArrayList<ProdList>();
        Integer offerId;
        if (!CommonUtil.isEmpty(phoneId))
        {
            IMS3hBean bean = context.get3hTree().load3hBean(null, null, null, phoneId);
            Long userId = bean.getUserId();
            CoProd main = prodCmp.queryPrimaryProductByUserId(userId);
            offerId = main.getProductOfferingId();

        }
        else
        {
            if (CommonUtil.isEmpty(offerName))
            {
                return null;
            }
            PmProductOffering offer = prodCmp.queryOfferingByOfferName(offerName);
            // @Date 2012-5-7 tangjl5 判断销售品是否存在
            if (offer == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_OFFER_NOTEXIST, offerName);
            }
            offerId = offer.getProductOfferingId();

        }
        if (offerId != null)
        {
            List<CoProd> list = prodCmp.queryProdByOfferId(offerId, EnumCodeDefine.PROD_OBJECTTYPE_DEV, start, limit);
            count = prodCmp.queryProdCountByOfferId(offerId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
            if (!CommonUtil.isEmpty(list))
            {
                PmProductOffering offer = prodCmp.queryOfferingByOfferId(Long.valueOf(offerId));
                for (CoProd coProd : list)
                {
                    ProdList pList = new ProdList();
                    pList.setCoProd(coProd);
                    pList.setPmProductOffering(offer);
                    prodList.add(pList);
                    Long resourceId = coProd.getObjectId();
                    User3hBean bean2 = context.get3hTree().loadUser3hBean(resourceId);
                    CaAccountRes acctRes =context.get3hTree().loadUser3hBean(resourceId).getCaAccountRes();

                    SUser sUser = context.getComponent(UserComponent.class).sUserTransform(bean2.getUser(),
                            bean2.getPhoneId(), acctRes, null, null, bean2.getUserLifeCycle());
                    userList.add(sUser);
                }
            }
        }
        return new Object[] { userList, prodList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<SUser> userList = (List<SUser>) result[0];
        List<ProdList> prodList = (List<ProdList>) result[1];
        Do_queryUserByOfferNmaeResponse resp = new Do_queryUserByOfferNmaeResponse();
        SUserList sUserList = new SUserList();
        sUserList.setSUserList_Item(userList.toArray(new SUser[userList.size()]));
        resp.setUserList(sUserList);
        resp.setProdList(prodList);
        SPage page = new SPage();
        page.setNum(start);
        page.setSize(limit);
        page.setCount(Long.valueOf(count));
        resp.setSPage(page);
        return resp;
    }

    @Override
    public void destroy()
    {
    }

}
