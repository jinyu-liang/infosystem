package com.ailk.ims.component;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.imscnsh.entity.UpPostsectReq;

/**
 * @description:更新账户道段信息组件
 * @author caohm5
 * @date:2012-07-13
 */
public class UpPostsectComponent extends BaseComponent
{
    /**
     * @description:更新账户道段信息
     * @author caohm5
     * @date:2012-07-13
     */
    public void upPostsect(UpPostsectReq req)
    {
        CmContactMedium cmContactMedium = new CmContactMedium();
        cmContactMedium.setPostsect(req.getPostsect());
        this.updateByCondition(cmContactMedium, new DBCondition(CmContactMedium.Field.objectId, req.getAcct_id()),
                new DBCondition(CmContactMedium.Field.objectType, EnumCodeDefine.ACCT_CONTACT_TYPE), new DBCondition(
                        CmContactMedium.Field.postCode, req.getPost_code()));
    }
}
