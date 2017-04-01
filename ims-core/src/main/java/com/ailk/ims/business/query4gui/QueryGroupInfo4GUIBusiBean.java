package com.ailk.ims.business.query4gui;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.query.GroupQuery;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryGroupInfo4GUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryGroup4GUIReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SGroupList;

/**
 * 提供给GUI查询群信息
 * @Description
 * @author luojb
 * @Date 2012-4-7
 */
public class QueryGroupInfo4GUIBusiBean extends BaseBusiBean
{
    private SQueryGroup4GUIReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (SQueryGroup4GUIReq)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if(!CommonUtil.isValid(req.getGroup_id())
                && !CommonUtil.isValid(req.getContact_phone())
                && !CommonUtil.isValid(req.getMember_phone()))
                return;
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	SQueryGroup4GUIReq req = (SQueryGroup4GUIReq)input[0];
    	long groupId = req.getGroup_id();
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        if (CommonUtil.isValid(groupId)){
       	 list.add(context.get3hTree().loadGroup3hBean(groupId));
        }
        return list;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SGroupList groupList = context.getComponent(GroupQuery.class).queryGroupInfo4GUIByConds(req.getGroup_id(), req.getMember_phone(), req.getContact_phone(), req.getStatus(),req.getGroup_type());
        return new Object[]{groupList};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryGroupInfo4GUIResponse resp = new Do_queryGroupInfo4GUIResponse();
        resp.setGroupInfoList((SGroupList)result[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
        req = null;
    }

}
