package com.ailk.ims.business.query4gui;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryMultiSim4GuiResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryMultiSimReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SimItem;
import com.ailk.openbilling.persistence.imsintf.entity.SimItemList;

/**
 * @Description: 根据spec_char_id,product_id查询sim号
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author yangjh
 * @Date 2012-04-26
 * @Date 2012-04-27 yangjh response实体更换
 */
public class QueryMultiSim4GuiBusiBean extends BaseBusiBean
{
    private Integer specCharId = null;
    private Long productId  = null;
    private BaseComponent baseCmp = null;
    private Do_queryMultiSim4GuiResponse resp = null;

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryMultiSimReq req = (SQueryMultiSimReq) input[0];
        resp = new Do_queryMultiSim4GuiResponse();
        baseCmp = context.getComponent(BaseComponent.class);
        productId = req.getProduct_id();
        specCharId = req.getSpec_char_id();
        if (!CommonUtil.isValid(productId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"productId");
        }
        if(!CommonUtil.isValid(specCharId)){
        	throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"specCharId");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
    	SimItemList list = new SimItemList();
    	List<SimItem> values = new ArrayList<SimItem>();
        List<CoProdCharValue> charvalues = baseCmp.query(CoProdCharValue.class, 
        		new DBCondition(CoProdCharValue.Field.productId,productId),
        		new DBCondition(CoProdCharValue.Field.specCharId,specCharId));
        if(CommonUtil.isEmpty(charvalues)){
        	return null;
        }
        for(CoProdCharValue charvalue:charvalues){
        	SimItem sim = new SimItem();
        	sim.setProduct_id(productId);
        	sim.setSim(charvalue.getValue());
        	values.add(sim);
        }
        list.setList(values.toArray(new SimItem[values.size()]));
        resp.setSim_Item(list);
        return null;
    }

    public BaseResponse createResponse(Object[] result)
    {
        return resp;
    }

    public void destroy()
    {
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }
}