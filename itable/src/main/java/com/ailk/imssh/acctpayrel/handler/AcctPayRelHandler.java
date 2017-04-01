package com.ailk.imssh.acctpayrel.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.acctpayrel.cmp.SplitCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.itable.entity.IAcctPayRelation;

/**
 * @Description:分账(代付)
 * @author lijc3
 * @Date 2012-5-11
 */
public class AcctPayRelHandler extends BaseHandler
{
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        List<IAcctPayRelation> rels = (List<IAcctPayRelation>) dataArr[0];
        if (CommonUtil.isEmpty(rels))
        {
            return;
        }
        SplitCmp splitCmp = getContext().getCmp(SplitCmp.class);
        Date validDate = DateUtils.monthBegin(context.getCommitDate());
        for (IAcctPayRelation rel : rels)
        {
            if (!CommonUtil.isValid(rel.getProductId()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeExDefine.PARAM_INVALID, "PRODUCT_ID");
            }
            ITableUtil.setValue2ContextHolder(null, null, rel.getUserId());
            if (rel.getValidDate().before(validDate))
            {
                rel.setValidDate(validDate);
            }
            // 同时删除反向表
            splitCmp.deleteByCondition(CoSplitPayRel.class, validDate,
                    new DBCondition(CoSplitPayRel.Field.objectId, rel.getUserId()), new DBCondition(
                            CoSplitPayRel.Field.reguidObjectId, rel.getPayAcctId()), new DBCondition(
                            CoSplitPayRel.Field.expireDate, validDate, Operator.GREAT));

            // Long productId = rel.getProductId();
            Long productId = splitCmp.querySplit(rel);
            // 正向表
            ITableUtil.setValue2ContextHolder(null, rel.getPayAcctId(), null);
            // 删除产品和代付关系
            splitCmp.deleteByCondition(CoProd.class, validDate, new DBCondition(CoProd.Field.productId, productId),
                    new DBCondition(CoProd.Field.expireDate, validDate, Operator.GREAT));
            CoProd value = new CoProd();
            value.setProdExpireDate(validDate);
            // 更新失效时间
            splitCmp.updateDirectByCondition(value, new DBCondition(CoProd.Field.productId, productId), new DBCondition(
                    CoProd.Field.expireDate, validDate, Operator.GREAT));
            // 修改失效时间
            splitCmp.deleteByCondition(CoSplitCharValue.class, validDate, new DBCondition(CoSplitCharValue.Field.productId,
                    productId), new DBCondition(CoSplitCharValue.Field.expireDate, validDate, Operator.GREAT));

        }
    }

    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        List<IAcctPayRelation> rels = (List<IAcctPayRelation>) dataArr[0];
        if (CommonUtil.isEmpty(rels))
        {
            return;
        }
        Map<Long, Set<Long>> splitMap = new HashMap<Long, Set<Long>>();
        RouterCmp routerCmp= context.getCmp(RouterCmp.class);
        for (IAcctPayRelation rel : rels)
        {   
        	Long acctId=routerCmp.queryAcctIdByUserId(rel.getUserId());
            ITableUtil.setValue2ContextHolder(null, acctId, null);
            int operType = rel.getOperType();
            Long prodId = null;
            if (operType == EnumCodeExDefine.OPER_TYPE_INSERT)
            {
                prodId = this.addSplitInfo(rel);
            }
            else if (operType == EnumCodeExDefine.OPER_TYPE_DELETE)
            {
                prodId = this.delSplitInfo(rel);
            }
            else
            {
                prodId = this.changeSplitInfo(rel);
            }
            if (prodId != null)
            {
                createSplitComplex(rel.getPayAcctId(), rel.getUserId(), prodId, splitMap);
            }
        }
        // 处理用户分账路由信息
        context.getCmp(RouterCmp.class).publicSplitRoute(splitMap);

    }

    /*
     * 创建代付信息
     */
    private Long addSplitInfo(IAcctPayRelation rel)
    {
        SplitCmp splitCmp = getContext().getCmp(SplitCmp.class);

        // 创建产品
        CoProd dmProd = splitCmp.insertCoProd(rel);
        // 创建使用关系(给用户代付 还是 给账户代付)
        //String objectType = String.valueOf(splitCmp.getObjectType(rel));
        String objectId = String.valueOf(splitCmp.getObjectId(rel));
        // 创建代付信息
        splitCmp.insertSplitProdCharValueList(dmProd.getProductId(), rel, rel.getOperType(), objectId);
        
        //存在过户的时候同时带代付关系的时候，先将代付关系插在上家的分表
        
        //splitCmp.insertCoSplitPayRel(dmProd.getProductId(), rel, objectType, objectId); 
        // 设置回原来的分表参数
        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), context.getUserId());

        return dmProd.getProductId();
    }

    /*
     * 修改代付信息
     */
    private Long changeSplitInfo(IAcctPayRelation rel)
    {
    	SplitCmp splitCmp = getContext().getCmp(SplitCmp.class);
        splitCmp.updateSplit(rel.getInstId(), rel);
        return rel.getInstId();
    }

    /*
     * 删除代付信息
     */
    private Long delSplitInfo(IAcctPayRelation rel)
    {
        SplitCmp splitCmp = getContext().getCmp(SplitCmp.class);

        RouterCmp baseCmp = context.getCmp(RouterCmp.class);
    	Long acctId=baseCmp.queryAcctIdByUserId(rel.getUserId());
    	ITableUtil.setValue2ContextHolder(null, acctId, null);
        //Long prodId = splitCmp.querySplit(rel);
        // ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
        // CoProd dmProd = prodCmp.queryProd(rel.getProductId());
        // if (dmProd == null)
        // {
        // imsLogger.error("*******product_id ", rel.getProductId(), " not found,query product_id by rule");
        // prodId = splitCmp.querySplit(rel);
        // }


        splitCmp.deleteSplitInfoByProdId(rel, rel.getInstId(), rel.getExpireDate());
        return rel.getInstId();
    }

    /**
     * lijc3 2013-7-17 保存所有的群组和成员信息
     * 
     * @param groupId
     * @param userId
     * @param memberMap
     */
    private void createSplitComplex(Long payAcctId, Long userId, Long prodId, Map<Long, Set<Long>> splitMap)
    {

        Set<Long> userSet = splitMap.get(payAcctId);
        if (userSet == null)
        {
            userSet = new HashSet<Long>();
            splitMap.put(payAcctId, userSet);
        }
        userSet.add(userId);

    }

    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		//发布路由不处理
	}

}
