package com.ailk.ims.business.queryoperator.busi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryOperInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SOperInfoResp;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryOperReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.jd.entity.SecOperator;
import com.ailk.openbilling.persistence.jd.entity.SecOrganize;
import com.ailk.openbilling.persistence.jd.entity.SecStaff;

/**
 * @Description:查询操作员信息bean
 * @author zengqm
 * @Date 2012-03-20
 */
public class QueryOperatorInfoBusiBean extends BaseBusiBean
{

    private Long operatorId;
    private Long organizeId;

    @Override
    public void init(Object... input) throws IMSException
    {
        SQueryOperReq sQueryOperReq = (SQueryOperReq) input[0];
        operatorId = sQueryOperReq.getOperator_id();
        organizeId = sQueryOperReq.getOrganize_id();

    }

    @Override
    public void validate(Object... input)
    {
       /* if (CommonUtil.isEmpty(operatorId) && CommonUtil.isEmpty(organizeId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "operator_Id or organize_Id");

        }*/
    }

    @Override
    public Object[] doBusiness(Object... input)
    {
        List<SOperInfoResp> sOperInfoRespList = new ArrayList<SOperInfoResp>();
        // 查询所有操作员信息
        if (operatorId !=null&&operatorId== 0 && organizeId!=null&&organizeId == 0 )
        {
            List<SecOperator> list = context.getComponent(BaseComponent.class).query(SecOperator.class,
                    new DBCondition(SecOperator.Field.expireDate, context.getRequestDate(), Operator.GREAT));
            if (CommonUtil.isNotEmpty(list))
            {
                for (SecOperator secOperator : list)
                {
                    converOperatorTosOperInfoResp(secOperator, sOperInfoRespList);
                }
            }

        }
        // 根据操作员编号查询该操作员信息
        if (operatorId != null && operatorId > 0)
        {
            SecOperator secOperator = context.getComponent(BaseComponent.class).querySingle(SecOperator.class,
                    new DBCondition(SecOperator.Field.operatorId, operatorId));
            if (secOperator != null)
            {
                converOperatorTosOperInfoResp(secOperator, sOperInfoRespList);
            }
        }
        // 查询指定营业厅下所有操作员信息
        if (operatorId != null && organizeId != null && operatorId <= 0 && organizeId > 0)
        {
            List<SecStaff> list = context.getComponent(BaseComponent.class).query(SecStaff.class,
                    new DBCondition(SecStaff.Field.organizeId, organizeId));
            if (CommonUtil.isNotEmpty(list))
            {
                List<Long> staffIds = CommonUtil.getFieldValueList(list, "staffId");
                if (CommonUtil.isEmpty(staffIds))
                    return null;

                List<SecOperator> listOper = context.getComponent(BaseComponent.class).query(SecOperator.class,
                        new DBCondition(SecOperator.Field.staffId, staffIds.toArray(), Operator.IN));// TODO 若list值过多，该方式将不可行
                if (CommonUtil.isNotEmpty(listOper))
                {
                    for (SecOperator secOperator : listOper)
                    {
                        // 这里有一点点效率上的损失，但由于都是通过主键查询，影响不大
                        converOperatorTosOperInfoResp(secOperator, sOperInfoRespList);
                    }
                }
            }
        }

        return new Object[] { sOperInfoRespList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryOperInfoResponse resp = new Do_queryOperInfoResponse();
        List<SOperInfoResp> sOperInfoRespList = (List<SOperInfoResp>) result[0];
        resp.setOperInfoList(sOperInfoRespList);
        return resp;
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public List<com.ailk.ims.ims3h.IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        // 内部接口既没有告警也不收一次性费用
        return null;
    }

    /**
     * 查出的操作员转换成返回的对象类型 zengqm 2012-3-21
     * 
     * @param secOperator
     */
    private void converOperatorTosOperInfoResp(SecOperator secOperator, List<SOperInfoResp> sOperInfoRespList)
    {
        SOperInfoResp sOperInfoResp = new SOperInfoResp();
        sOperInfoResp.setOperator_id(secOperator.getOperatorId());
        SecStaff secStaff = queryStaffById(secOperator.getStaffId());
        sOperInfoResp.setCode(secOperator.getCode());
        if (secStaff != null)
        {
            sOperInfoResp.setStaff_name(secStaff.getStaffName());
            sOperInfoResp.setOrganize_id(secStaff.getOrganizeId());
            SecOrganize secOrganize = queryOrganizeById(secStaff.getOrganizeId());
            if (secOrganize != null)
            {
                sOperInfoResp.setOrganize_name(secOrganize.getOrganizeName());
                sOperInfoResp.setCounty_id(secOrganize.getCountyId());
                sOperInfoResp.setDistrict_id(secOrganize.getDistrictId());
            }

        }
        sOperInfoRespList.add(sOperInfoResp);
    }

    private Map<Long, SecStaff> mapStaff = new HashMap<Long, SecStaff>();

    /**
     * 通过ID查询员工信息 zengqm 2012-3-21
     * 
     * @param staffId
     * @return
     */
    private SecStaff queryStaffById(long staffId)
    {
        SecStaff staff = mapStaff.get(staffId);
        if (staff != null)
            return staff;
        SecStaff secStaff = context.getComponent(BaseComponent.class).querySingle(SecStaff.class,
                new DBCondition(SecStaff.Field.staffId, staffId));
        mapStaff.put(staffId, secStaff);
        return secStaff;
    }

    private Map<Long, SecOrganize> mapOrg = new HashMap<Long, SecOrganize>();

    /**
     * 通过 ID查询营业厅信息 zengqm 2012-3-21
     * 
     * @param organizeId
     * @return
     */
    private SecOrganize queryOrganizeById(long organizeId)
    {
        SecOrganize org = mapOrg.get(organizeId);
        if (org != null)
            return org;

        SecOrganize secOrganize = context.getComponent(BaseComponent.class).querySingle(SecOrganize.class,
                new DBCondition(SecOrganize.Field.organizeId, organizeId));
        mapOrg.put(organizeId, secOrganize);
        return secOrganize;
    }
}
