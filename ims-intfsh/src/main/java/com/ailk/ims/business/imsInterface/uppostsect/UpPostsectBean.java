package com.ailk.ims.business.imsInterface.uppostsect;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.UpPostsectComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_upPostsectResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.UpPostsectReq;
import com.ailk.openbilling.persistence.imscnsh.entity.UpPostsectReqList;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:更新道段信息
 * @author caohm5
 * @date:2012-07-13
 * @date 2012-11-5 zhangzj3 性能优化
 */
public class UpPostsectBean extends BaseBusiBean
{
    private UpPostsectComponent cmp;

    @Override
    public void init(Object... input) throws BaseException
    {
        cmp = context.getComponent(UpPostsectComponent.class);
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        UpPostsectReqList upPostsectReqList = (UpPostsectReqList) input[0];
        if (null == upPostsectReqList || CommonUtil.isEmpty(upPostsectReqList.getUpPostsectReqList_item()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "UpPostsectReqList");
        }
        for (UpPostsectReq req : upPostsectReqList.getUpPostsectReqList_item())
        {
            // 账户编号不能为空
            if (null == req.getAcct_id())
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
            }
            // 道段信息不能为空
            if (CommonUtil.isEmpty(req.getPostsect()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "postsect");
            }
            // 道段信息不能为空
            if (CommonUtil.isEmpty(req.getPost_code()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "post_code");
            }
           /* // 校验三户数据
            context.get3hTree().loadAcct3hBean(req.getAcct_id());*/
            //@Date 2012-11-5 zhangzj3 性能优化,公共校验
            context.getComponent(CheckComponentSH.class).check3HParam(null, req.getAcct_id(), null, null);
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        /*UpPostsectReqList sUserPaySplitRelList = (UpPostsectReqList) input[0];
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        for (UpPostsectReq rel : sUserPaySplitRelList.getUpPostsectReqList_item())
        {
            Long acctId = rel.getAcct_id();
            list.add(context.get3hTree().loadAcct3hBean(acctId));
        }
        return list;*/
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {   
        UpPostsectReqList upPostsectReqList = (UpPostsectReqList) input[0];
        for (UpPostsectReq req : upPostsectReqList.getUpPostsectReqList_item())
        {
            cmp.upPostsect(req);
        }
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_upPostsectResponse();
    }

    @Override
    public void destroy()
    {

    }
}
