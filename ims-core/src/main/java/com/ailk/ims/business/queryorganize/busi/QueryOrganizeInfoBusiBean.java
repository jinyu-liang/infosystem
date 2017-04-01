package com.ailk.ims.business.queryorganize.busi;

import java.util.ArrayList;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryOrgInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SOrgInfoResp;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryOrgReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.jd.entity.SecOrganize;

/**
 * @Description:查询营业厅信息bean
 * @author zengqm
 * @Date 2011-03-20
 */
public class QueryOrganizeInfoBusiBean extends BaseBusiBean
{

    private Long organizeId;

    @Override
    public void init(Object... input) throws IMSException
    {
        SQueryOrgReq sQueryOrgReq = (SQueryOrgReq) input[0];
        organizeId = sQueryOrgReq.getOrganize_id();

    }

    @Override
    public void validate(Object... input)
    {
        if (organizeId == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, " organize_Id");

        }
    }

    @Override
    public Object[] doBusiness(Object... input)
    {

        List<SOrgInfoResp> sOrgInfoRespList = new ArrayList<SOrgInfoResp>();
        // 查询所有营业厅信息
        if (organizeId <= 0)
        {
            List<SecOrganize> list = context.getComponent(BaseComponent.class).query(SecOrganize.class,
                    new DBCondition(SecOrganize.Field.expireDate, context.getRequestDate(), Operator.GREAT));
            if (CommonUtil.isNotEmpty(list))
            {
                for (SecOrganize secOrganize : list)
                {
                    converOrganizeTosOrgInfoResp(secOrganize, sOrgInfoRespList);
                }
            }

        }
        // 根据营业厅编号查询该营业厅信息
        if (organizeId > 0)
        {
            SecOrganize secOrganize = context.getComponent(BaseComponent.class).querySingle(SecOrganize.class,
                    new DBCondition(SecOrganize.Field.organizeId, organizeId));
            if (secOrganize != null)
            {
                converOrganizeTosOrgInfoResp(secOrganize, sOrgInfoRespList);
            }
        }
        return new Object[] { sOrgInfoRespList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryOrgInfoResponse resp = new Do_queryOrgInfoResponse();
        List<SOrgInfoResp> sOrgInfoRespList = (List<SOrgInfoResp>) result[0];
        resp.setOrgInfoList(sOrgInfoRespList);
        return resp;
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public List<com.ailk.ims.ims3h.IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    /**
     * 查出的操作员转换成返回的对象类型 zengqm 2012-3-21
     * 
     * @param SecOrganize
     */
    private void converOrganizeTosOrgInfoResp(SecOrganize secOrganize, List<SOrgInfoResp> sOrgInfoRespList)
    {
        SOrgInfoResp sOrgInfoResp = new SOrgInfoResp();
        sOrgInfoResp.setOrganize_id(secOrganize.getOrganizeId());
        sOrgInfoResp.setOrganize_name(secOrganize.getOrganizeName());
        sOrgInfoResp.setCounty_id(secOrganize.getCountyId());
        sOrgInfoResp.setDistrict_id(secOrganize.getDistrictId());
        sOrgInfoRespList.add(sOrgInfoResp);
    }
}
