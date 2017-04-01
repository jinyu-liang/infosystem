package com.ailk.imssh.prod.handler;

import java.util.ArrayList;
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
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCellInfo;
import com.ailk.openbilling.persistence.itable.entity.IProdCellInfo;

/**
 * @Description 创建动态小区信息
 * @author lijc3
 * @Date 2012-5-25
 */
public class ProdCellInfoHandler extends BaseHandler
{

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        baseCmp = context.getCmp(BaseCmp.class);
        List<IProdCellInfo> infoList = (List<IProdCellInfo>) dataArr[0];
        Date validDate = DateUtils.monthBegin(context.getCommitDate());
        Set<Long> prodIdList = new HashSet<Long>();
        if (CommonUtil.isNotEmpty(infoList))
        {
            for (IProdCellInfo info : infoList)
            {
                prodIdList.add(info.getProductId());
                if (info.getValidDate().before(validDate))
                {
                    info.setValidDate(validDate);
                }
            }
        }
        else
        {
            return;
        }
        ITableUtil.setValue2ContextHolder(context.getCustId(), context.getAcctId(), context.getUserId());
        baseCmp.deleteByCondition_noInsert(CoProdCellInfo.class, validDate, new DBCondition(CoProdCellInfo.Field.productId,
                prodIdList, Operator.IN), new DBCondition(CoFnCharValue.Field.expireDate, validDate, Operator.GREAT));
    }

    private BaseCmp baseCmp;

    @Override
    public void handle(List<? extends DataObject>... dataArr) throws IMSException
    {
        baseCmp = context.getCmp(BaseCmp.class);

        List<IProdCellInfo> infoList = (List<IProdCellInfo>) dataArr[0];
        List<CoProdCellInfo> cellInfoList = new ArrayList<CoProdCellInfo>();
        boolean flag = false;
        for (IProdCellInfo info : infoList)
        {
            int operType = info.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                cellInfoList.add(createProdCellInfo(info));
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                // no update
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                // 这里会全量删除所有小区信息,当前次新增的小区信息不能删除
                if (!flag)
                {
                    flag = true;
                    baseCmp.deleteByCondition(CoProdCellInfo.class,
                            new DBCondition(CoProdCellInfo.Field.productId, info.getProductId()));
                }
                break;
            default:
                break;
            }
        }
        if (CommonUtil.isNotEmpty(cellInfoList))
        {
            baseCmp.insertBatch(cellInfoList);
        }
    }

    /**
     * lijc3 2012-5-25 创建CO_PROD_CELL_INFO表
     */
    private CoProdCellInfo createProdCellInfo(IProdCellInfo iProdCellInfo)
    {
        CoProdCellInfo cellInfo = new CoProdCellInfo();
        cellInfo.setExpireDate(iProdCellInfo.getExpireDate());
        cellInfo.setLacId(iProdCellInfo.getLacId());
        cellInfo.setModifyDate(context.getCommitDate());
        cellInfo.setProductId(iProdCellInfo.getProductId());
        cellInfo.setSacId(iProdCellInfo.getSacId());
        cellInfo.setSoDate(context.getCommitDate());
        cellInfo.setValidDate(iProdCellInfo.getValidDate());
        cellInfo.setSoNbr(context.getSoNbr());
        CoProd dmProd = getCoProd(iProdCellInfo.getProductId());
        cellInfo.setObjectId(dmProd.getObjectId());
        cellInfo.setObjectType(dmProd.getObjectType());
        return cellInfo;
    }

    private Map<Long, CoProd> map = new HashMap<Long, CoProd>();

    private CoProd getCoProd(Long productId)
    {
        CoProd dmProd = map.get(productId);
        if (dmProd == null)
        {
            dmProd = context.getCmp(ProdCmp.class).loadProd(productId, context.getUserId(), context.getAcctId());
            map.put(productId, dmProd);
        }
        return dmProd;
    }

    /**
     * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<? extends jef.database.DataObject>[])
     */
    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}
}
