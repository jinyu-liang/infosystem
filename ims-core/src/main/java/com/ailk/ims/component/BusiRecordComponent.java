package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.ims.common.BusiRecord;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmBusiOrder;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.ImsSonbrMapping;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imssdl.entity.SProductStatus;

/**
 * @Description: 业务记录组件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-10-24
 * @Date 2012-04-27 lijc3 修改创建业务记录
 */
public class BusiRecordComponent extends BaseComponent
{
    public BusiRecordComponent()
    {
    }

    /**
     * @Description: 构建赠送产品订购业务记录对象
     * @author : luojb
     * @date : 2011-10-26
     * @param prodList
     * @return
     */
    public List<CmBusiOrder> buildAddRewardProdBusiOrderList(SProductOrderList prodList)
    {
        int operType = EnumCodeDefine.BUSIREC_OPERTYPE_ADDPROD;
        Integer specId = getSpecId(true, operType);

        List<CmBusiOrder> result = new ArrayList<CmBusiOrder>();
        for (SProductOrder order : prodList.getItem())
        {
            Long productId = order.getProduct_id();
            CoProd prod = context.getSingleCache(CoProd.class, new CacheCondition(CoProd.Field.productId, productId));
            if (prod == null)
                prod = context.getComponent(BaseProductComponent.class).queryProd(productId);
            if (prod == null)
                continue;
            CmBusiOrder busiOrder = buildProdOrderList(productId, operType, specId, prod.getObjectId());
            busiOrder.setOriginType(EnumCodeDefine.BUSIREC_PRODTYPE_REWARD);
            result.add(busiOrder);
        }
        return result;
    }

    /**
     * @Description: 构建产品订购业务记录对象
     * @author : wuyj
     * @date : 2011-10-26
     * @param prodList
     * @return
     */
    public List<CmBusiOrder> buildAddProdBusiOrderList(SProductOrderList prodList)
    {
        int operType = EnumCodeDefine.BUSIREC_OPERTYPE_ADDPROD;
        // change product接口是有多个spec_id的，所订购的时候需要获取add类型的子spec_id
        // 如果没有获取子spec_id，那么说明是三户新装的，重新获取s
        Integer specId = getSpecId(true, operType);

        List<CmBusiOrder> result = new ArrayList<CmBusiOrder>();
        for (SProductOrder order : prodList.getItem())
        {
            boolean acctLevel = context.getComponent(BaseProductComponent.class).isAccountLevelOrderProduct(order);
            Long objectId = null;
            if (acctLevel)
            {
                objectId = order.getAcct_id();
            }
            else
            {
                User3hBean bean = context.get3hTree().loadUser3hBean(order.getUser_id(), order.getPhone_id());
                objectId = bean.getUserId();
            }

            CmBusiOrder busiOrder = buildProdOrderList(order.getProduct_id(), operType, specId, objectId);
            result.add(busiOrder);
        }
        return result;
    }

    /**
     * @Description: 构建产品删除业务记录对象
     * @author : wuyj
     * @date : 2011-10-26
     * @param prodList
     * @return
     */
    public List<CmBusiOrder> buildDelProdBusiOrderList(SProductOrderList prodList)
    {
        int operType = EnumCodeDefine.BUSIREC_OPERTYPE_DELPROD;
        Integer specId = getSpecId(true, operType);

        List<CmBusiOrder> result = new ArrayList<CmBusiOrder>();
        for (SProductOrder order : prodList.getItem())
        {
            CmBusiOrder busiOrder = buildProdOrderList(order.getProduct_id(), operType, specId, null);
            // @Date 2012-04-27 lijc3 修改创建业务记录
            if (context.getExtendParam(ConstantDefine.CACHE_DEL_PACK_PRODLIST + order.getProduct_id()) != null)
            {
                SProductOrderList orderList = (SProductOrderList) context.getExtendParam(ConstantDefine.CACHE_DEL_PACK_PRODLIST
                        + order.getProduct_id());
                if (CommonUtil.isNotEmpty(orderList.getItem()))
                {
                    for (SProductOrder oo : orderList.getItem())
                    {
                        result.add(buildProdOrderList(oo.getProduct_id(), operType, specId, null));
                    }
                }
            }
            result.add(busiOrder);
        }
        return result;
    }

    /**
     * @Description: 构建产品修改业务记录对象
     * @author : wuyj
     * @date : 2011-10-26
     * @param prodList
     * @return
     */
    public List<CmBusiOrder> buildModifyProdBusiOrderList(SProductOrderList prodList)
    {
        int operType = EnumCodeDefine.BUSIREC_OPERTYPE_MODIFYPROD;
        Integer specId = getSpecId(true, operType);

        List<CmBusiOrder> result = new ArrayList<CmBusiOrder>();
        for (SProductOrder order : prodList.getItem())
        {
            CmBusiOrder busiOrder = buildProdOrderList(order.getProduct_id(), operType, specId, null);
            result.add(busiOrder);
        }
        return result;
    }

    /**
     * @Description: 构建产品延期业务记录对象
     * @author : wuyj
     * @date : 2011-10-26
     * @param prodList
     * @return
     */
    public List<CmBusiOrder> buildExtendProdBusiOrderList(SProductOrderList prodList)
    {
        int operType = EnumCodeDefine.BUSIREC_OPERTYPE_EXTENDPROD;
        Integer specId = getSpecId(true, operType);

        List<CmBusiOrder> result = new ArrayList<CmBusiOrder>();
        for (SProductOrder order : prodList.getItem())
        {
            CmBusiOrder busiOrder = buildProdOrderList(order.getProduct_id(), operType, specId, null);
            result.add(busiOrder);
        }
        return result;
    }

    /**
     * 替换主产品用 ljc 2011-11-2
     */
    public List<CmBusiOrder> buildReplaceMainProdBusiOrderList(SProductOrderList prodList)
    {
        int operType = EnumCodeDefine.BUSIREC_OPERTYPE_CHGMAINPROD;
        Integer specId = getSpecId(true, operType);

        List<CmBusiOrder> result = new ArrayList<CmBusiOrder>();
        for (SProductOrder order : prodList.getItem())
        {
            User3hBean bean = context.get3hTree().loadUser3hBean(order.getUser_id(), order.getPhone_id());
            CmBusiOrder busiOrder = buildProdOrderList(order.getProduct_id(), operType, specId, bean.getUserId());
            result.add(busiOrder);
        }
        return result;
    }

    /**
     * @Description: 最终产品业务记录构建方法
     * @author : wuyj
     * @date : 2011-11-2
     * @param prod
     * @param operType，这个必须是cm_busi_order对应的oper_type
     * @param specId
     * @return
     */
    private CmBusiOrder buildProdOrderList(long prodId, int operType, Integer specId, Long objectId)
    {
        CoProd coProd = context.getSingleCache(CoProd.class, new CacheCondition(CoProd.Field.productId, prodId),
                new CacheCondition(CoProd.Field.soNbr, context.getSoNbr()));

        if (coProd == null)
        {
            if (CommonUtil.isValid(objectId))
            {
                coProd = context.getComponent(BaseProductComponent.class).loadProd(prodId, objectId);
            }
            else
            {
                coProd = context.getComponent(BaseProductComponent.class).loadProd(prodId);
            }
        }

        // 如果是主产品并且spec_id不是三户新装的,则该类型标志为替换主产品
        if (coProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN && specId != 1001001)
        {
            operType = EnumCodeDefine.BUSIREC_OPERTYPE_CHGMAINPROD;
        }
        CmBusiOrder busiOrder = new CmBusiOrder();

        busiOrder.setBusiFlag(coProd.getBusiFlag());
        busiOrder.setDoneDate(context.getRequestDate());
        busiOrder.setSoNbr(context.getSoNbr());
        busiOrder.setObjectId(coProd.getObjectId());
        busiOrder.setObjectType(coProd.getObjectType());

        busiOrder.setProductId(prodId);

        busiOrder.setOriginType(EnumCodeDefine.BUSIREC_PRODTYPE_NORMAL);// 赠送产品、普通产品

        Long origSoNbr = null;

        if (operType == EnumCodeDefine.BUSIREC_OPERTYPE_ADDPROD)
        {
            origSoNbr = context.getSoNbr();// 订购的orig_so_nbr是自身

        }
        else
        {
            // 除了订购，其它的都要记之前的so_nbr
            try
            {
                origSoNbr = getOrigSoNbr(prodId, operType);
            }
            catch (Exception e)
            {
                imsLogger.error(e, e);
            }
        }
        busiOrder.setOrigSoNbr(origSoNbr);
        if (specId != null)
            busiOrder.setSpecId(Integer.valueOf(specId).longValue());
        busiOrder.setOperType(operType);
        busiOrder.setSts(EnumCodeDefine.BUSIREC_STS_NORMAL);

        return busiOrder;
    }

    /**
     * @Description: 获取产品变更前的so_nbr
     * @author : wuyj
     * @date : 2011-11-1
     * @param order
     * @param type
     * @return
     * @throws Exception
     */
    private Long getOrigSoNbr(Long productId, int operType) throws Exception
    {
        if (operType == EnumCodeDefine.BUSIREC_OPERTYPE_DELPROD || operType == EnumCodeDefine.BUSIREC_OPERTYPE_EXTENDPROD)
        {
            // 删除和延期都能缓存中找到原始的记录
            List<CoProd> list = context.getAllCacheList(CoProd.class, new CacheCondition(CoProd.Field.productId, productId));
            // 需要过滤出不是当前so_nbr的记录，只取其中一条即可
            for (CoProd prod : list)
            {
                if (context.getSoNbr() != prod.getSoNbr().longValue())
                {
                    return prod.getSoNbr();
                }
            }
        }
        else if (operType == EnumCodeDefine.BUSIREC_OPERTYPE_MODIFYPROD)
        {

            // 修改可以在缓存中找到co_prod_char_value
            List list = context.getAllCacheList(CoProdCharValue.class, new CacheCondition(CoProdCharValue.Field.productId,
                    productId));

            if (CommonUtil.isEmpty(list))
            {
                // 适用修改产品账期
                list = context.getAllCacheList(CoProdBillingCycle.class, new CacheCondition(CoProdBillingCycle.Field.productId,
                        productId));
            }

            if (!CommonUtil.isEmpty(list))
            {
                // 需要过滤出不是当前so_nbr的记录，只取其中一条即可
                for (Object obj : list)
                {
                    Long soNbr = (Long) ClassUtil.getPropertyValue(obj, ConstantDefine.ENTITY_FIELD_SO_NBR);
                    if (context.getSoNbr() != soNbr)
                    {
                        return soNbr;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @Description: 获取修改产品账期时产品账期变更前的so_nbr
     * @author : yanchuan
     * @date : 2011-11-5
     * @param productId
     * @return
     */
    public Long getChgCycleOrigSoNbr(Long productId)
    {
        // 修改可以在缓存中找到co_prod_char_value
        List<CoProdBillingCycle> list = context.getAllCacheList(CoProdBillingCycle.class, new CacheCondition(
                CoProdBillingCycle.Field.productId, productId));
        // 需要过滤出不是当前so_nbr的记录，只取其中一条即可
        for (CoProdBillingCycle billCycle : list)
        {
            if (context.getSoNbr() != billCycle.getSoNbr().longValue())
            {
                return billCycle.getSoNbr();
            }
        }
        return null;
    }

    public void createBusiRecord(BusiRecord record)
    {
        if (record.getBusi() != null)
        {
            // 业务总表
            DBUtil.insert(record.getBusi());
        }
        if (CommonUtil.isEmpty(record.getBusiOrders()))
        {
            // 业务明细记录表
            DBUtil.insertBatch(record.getBusiOrders());
        }
    }

    public List<CmBusiOrder> buildUpsellingProdList(CoProd p1, CoProd p2)
    {
        int operType = EnumCodeDefine.BUSIREC_OPERTYPE_ADDPROD;
        Integer specId = getSpecId(true, operType);

        List<CmBusiOrder> result = new ArrayList<CmBusiOrder>();

        if (p1 != null)
        {
            CmBusiOrder busiOrder = buildProdOrderList(p1.getProductId(), operType, specId, p1.getObjectId());
            result.add(busiOrder);
        }
        if (p2 != null)
        {
            CmBusiOrder busiOrder = buildProdOrderList(p2.getProductId(), operType, specId, p2.getObjectId());
            result.add(busiOrder);
        }

        return result;
    }

    public List<CmBusiOrder> buildSyncProdStsList(CsdlArrayList<SProductStatus> sProductStatusList)
    {
        int operType = EnumCodeDefine.BUSIREC_OPERTYPE_MODIFYPROD;
        Integer specId = getSpecId(true, operType);

        List<CmBusiOrder> result = new ArrayList<CmBusiOrder>();

        for (SProductStatus ps : sProductStatusList)
        {
            CmBusiOrder busiOrder = buildProdOrderList(ps.getProductSequenceId(), operType, specId, null);
            result.add(busiOrder);
        }
        return result;
    }

    /**
     * @author luojb
     * @param outSoNbr
     * @return
     */
    public Long outSoNbr2DoneCode(String outSoNbr)
    {
        // CmBusi cmBusi = new CmBusi();
        // cmBusi.setOuterSoNbr(outSoNbr);
        //
        // cmBusi = (CmBusi) DBUtil.querySingle(cmBusi);
        CmBusi cmBusi = DBUtil.querySingle(CmBusi.class, new DBCondition(CmBusi.Field.outerSoNbr, outSoNbr));
        if (cmBusi == null)
            return null;
        return cmBusi.getSoNbr();
    }

    /**
     * @Description: 获取busi_spec_id
     * @author : wuyj
     * @date : 2011-11-21
     * @param isNormal
     * @param type
     * @return
     */
    public Integer getSpecId(boolean isNormal, int type)
    {
        Integer specId = null;
        try
        {
            // 先根据类型查找
            specId = context.getBusiSpecId(true, type);
        }
        catch (Exception e)
        {
        	imsLogger.error(e,e);
        }
        // 找不到就直接查找
        if (specId == null)
        {
            specId = context.getBusiSpecId(true);
        }
        return specId;
    }

    /**
     * @Description: 获取outSoNbr
     * @author : liuyang8
     * @date : 2012-03-08
     * @param doneCode
     * @return
     */
    public String bosCode2outSoNbr(Long bosCode)
    {
        // ImsSonbrMapping mapping = new ImsSonbrMapping();
        // mapping.setBosSoNbr(bosCode);
        // mapping = (ImsSonbrMapping) DBUtil.querySingle(mapping);
        ImsSonbrMapping mapping = DBUtil.querySingle(ImsSonbrMapping.class, new DBCondition(ImsSonbrMapping.Field.bosSoNbr,
                bosCode));
        if (mapping == null)
            return null;
        return mapping.getOutSoNbr();
    }

    /**
     * 查询业务记录 luojb 2012-7-12
     * 
     * @param busiCode
     * @param acctId
     * @param userId
     * @param phoneId
     * @return
     */
    public List<CmBusi> queryBusiLog(Integer busiCode, Long acctId, Long userId, String phoneId, Date startDate, Date endDate)
    {
        if (!CommonUtil.isValid(busiCode) && !CommonUtil.isValid(acctId) && !CommonUtil.isValid(userId)
                && !CommonUtil.isValid(phoneId))
            return null;
        List<DBCondition> conditions = new ArrayList<DBCondition>();
        if (CommonUtil.isValid(busiCode))
        {
            conditions.add(new DBCondition(CmBusi.Field.busiCode, busiCode));
        }
        if (CommonUtil.isValid(acctId))
        {
            conditions.add(new DBCondition(CmBusi.Field.acctId, acctId));
        }
        if (CommonUtil.isValid(userId))
        {
            conditions.add(new DBCondition(CmBusi.Field.resourceId, userId));
        }
        if (CommonUtil.isValid(phoneId))
        {
            conditions.add(new DBCondition(CmBusi.Field.phoneId, phoneId));
        }
        if (startDate != null)
        {
            conditions.add(new DBCondition(CmBusi.Field.soDate, startDate, Operator.GREAT_EQUALS));
        }
        if (endDate != null)
        {
            conditions.add(new DBCondition(CmBusi.Field.soDate, endDate, Operator.LESS_EQUALS));
        }

        List<CmBusi> busiList = query(CmBusi.class, conditions.toArray(new DBCondition[conditions.size()]));
        return busiList;
    }
    
    /**
     * 生成账管工单映射
     * luojb 2012-8-8
     * @param out
     * @throws IMSException
     */
    public void saveZgSoNbr(Long zgSoNbr, Date zgSoDate) throws IMSException
    {
        ImsSonbrMapping dmMapping = new ImsSonbrMapping();
        dmMapping.setDoneCode(context.getDoneCode());
        dmMapping.setDoneDate(context.getRequestDate());
        dmMapping.setOutSoNbr(context.getOper().getSo_nbr());
        dmMapping.setOutSoDate(IMSUtil.formatDate(context.getOper().getSo_date()));
        dmMapping.setBosSoNbr(zgSoNbr);
        dmMapping.setBosSoDate(zgSoDate);
        dmMapping.setBusiCode(CommonUtil.string2Integer(context.getMethodConfig().getAttribute("busi_code")));
        dmMapping.setBusiSpecId(context.getBusiSpecId(true));
        dmMapping.setBusiType(EnumCodeDefine.SONBR_MAPPING_BUSITYPE_PAYMENT);

        DBUtil.insert(dmMapping);
    }

}
