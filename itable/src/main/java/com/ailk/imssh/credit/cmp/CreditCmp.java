package com.ailk.imssh.credit.cmp;

import java.util.ArrayList;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAsset;
import com.ailk.openbilling.persistence.acct.entity.CaAssetSyn;
import com.ailk.openbilling.persistence.acct.entity.CaBatchSyn;
import com.ailk.openbilling.persistence.acct.entity.CaCredit;
import com.ailk.openbilling.persistence.itable.entity.ICredit;

/**
 * @Description:信用度I表同步，分为新增与删除两部分
 * @author caohm5
 * @Date 2012-5-17
 */
public class CreditCmp extends BaseCmp
{
    /**
     * 信管信用度上发:金额+实时累计+历史累计
     */
    public static final int SYNC_FLAG = 7;
    /**
     * 信管信用度上发:触发信控
     */
    public static final int NOTIFY_FLAG = 1;

    /**
     * 信管信用度上发:不触发信控
     */
    public static final int UNNOTICED_FLAG = 0;
    /**
     * 信管信用度上发:信用度变更
     */
    public static final int SYNC_TYPE = 5;
    
    public static final int SYNC_TYPE_CUST = 11;
    
    public static final int SYNC_TYPE_USER = 12;
    
    public static final int SYNC_TYPE_CREDIT_SERV = 14;
    
    public static final int SYNC_TYPE_CREDIT_SERV_SPEC = 13;
    
    public static final int CREDIT_SERV_7901 = 400007901;
    public static final int CREDIT_SERV_7913 = 400007913;
    
    public static final String BIZE_INFO = "from Credit";

    /**
     * 创建一条信用度记录 .
     * 
     * @param iCredit
     */
    private CaCredit createCaCredit(ICredit iCredit)
    {
        CaAccount account = getCaAccount(iCredit.getAcctId());
        CaCredit caCredit = new CaCredit();
        caCredit.setAcctId(iCredit.getAcctId());
        caCredit.setAssetId(DBUtil.getSequence(CaAsset.class));
        caCredit.setBillingType(1);
        caCredit.setCreateDate(iCredit.getCommitDate());
        caCredit.setCreditAmount(iCredit.getCredit());
        caCredit.setCreditItem(CommonUtil.long2Int(iCredit.getCreditItemId()));
        caCredit.setExpireDate(iCredit.getExpireDate());
        caCredit.setMeasureId(account.getMeasureId());
        caCredit.setSoDate(iCredit.getCommitDate());
        caCredit.setSoNbr(context.getSoNbr());
        caCredit.setValidDate(iCredit.getValidDate());
        caCredit.setResourceId(iCredit.getUserId());
        caCredit.setCreditFlag(iCredit.getCreditFlag());
        return caCredit;
        // this.insert(caCredit);
    }

    /**
     * 信用度上发.
     * 
     * @param iCredit
     */
    private void insertCaAssetSyn(ICredit iCredit)
    {
        CaBatchSyn caAssetSyn = new CaBatchSyn();
        caAssetSyn.setSynId(DBUtil.getSequence(CaAssetSyn.class)); // 获取表的Sequence
     
        caAssetSyn.setAcctId(iCredit.getAcctId());
        caAssetSyn.setAcctFlag(0);
		caAssetSyn.setBizeType(0);
		caAssetSyn.setBizeInfo(CreditCmp.BIZE_INFO);
        
        caAssetSyn.setCreateDate(context.getCommitDate());
        caAssetSyn.setValidDate(iCredit.getValidDate());
        caAssetSyn.setExpireDate(iCredit.getExpireDate());
        caAssetSyn.setSts(0);
        caAssetSyn.setStsDate(context.getCommitDate());
        caAssetSyn.setSoNbr(context.getSoNbr());
        caAssetSyn.setSyncFlag(CreditCmp.SYNC_FLAG);
        //信用度上发触发信控 江西也需要触发信控 
        caAssetSyn.setNotifyFlag(CreditCmp.NOTIFY_FLAG);
        
        caAssetSyn.setSyncType(CreditCmp.SYNC_TYPE);
        caAssetSyn.setSoDate(context.getCommitDate());
        // 获取账户信息，以便获取地区编号
        CaAccount account = getCaAccount(iCredit.getAcctId());
        caAssetSyn.setRegionCode(account.getRegionCode());
        this.insert(caAssetSyn);
    }

    /**
     * 通过账户编号获取账户记录 .
     * 
     * @param acctId 账户编号 .
     * @return 账户记录 .
     */
    private CaAccount getCaAccount(Long acctId)
    {
        return context.get3hTree().loadAcct3hBean(acctId).getAccount();
    }

    public void operateCredit(List<ICredit> creditList)
    {
        List<ICredit> addList = new ArrayList<ICredit>();
        List<ICredit> deleteList = new ArrayList<ICredit>();
        // Map<Short, Map<Long, List<ICredit>>> map = new HashMap<Short, Map<Long, List<ICredit>>>();

        for (ICredit iCredit : creditList)
        {
            switch (iCredit.getOperType())
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                addList.add(iCredit);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                deleteList.add(iCredit);
                break;
            default:
                imsLogger.error("信用度处理，I表操作类型不正确。OPER_TYPE:" + String.valueOf(iCredit.getOperType()));
                break;
            }
        }
        if (validate(addList, deleteList))
        {
        	RouterCmp routeCmp = context.getCmp(RouterCmp.class);
            if (addList.size() > 0)
            {
                ICredit iCredit = addList.get(0);
                if (iCredit.getUserId() != null && iCredit.getUserId() != 0)
                {
                	
                	Long acctId=routeCmp.queryAcctIdByUserId(iCredit.getUserId());
                    ITableUtil.setValue2ContextHolder(null, acctId,null);
                }
                else
                {
                    ITableUtil.setValue2ContextHolder(null, iCredit.getAcctId(), null);
                }
                //deleteCreditDb(iCredit.getAcctId(), iCredit.getUserId(), iCredit.getCreditItemId());
                List<CaCredit> list = new ArrayList<CaCredit>();
                for (int i = 0; i < addList.size(); i++)
                {
                    list.add(createCaCredit(addList.get(i)));
                }
                if (list.size() > 0)
                {
                    this.insertBatch(list);
                    insertCaAssetSyn(iCredit);
                }
            }

            if (deleteList.size() > 0)
            {
                ICredit iCredit = deleteList.get(0);
                if (iCredit.getUserId() != null && iCredit.getUserId() != 0)
                {
                	Long acctId=routeCmp.queryAcctIdByUserId(iCredit.getUserId());
                    ITableUtil.setValue2ContextHolder(null, acctId, null);
                }
                else
                {
                    ITableUtil.setValue2ContextHolder(null, iCredit.getAcctId(), null);
                }
                
                deleteCreditDb(iCredit.getAcctId(), iCredit.getUserId(), iCredit.getCreditItemId());
                insertCaAssetSyn(iCredit);
            }
        }
    }

    private void deleteCreditDb(Long acctId, Long resourceId, Long creditItem)
    {
        if (resourceId != null && resourceId != 0)
        {
            this.deleteByCondition(CaCredit.class, new DBCondition(CaCredit.Field.resourceId, resourceId), new DBCondition(
                    CaCredit.Field.creditItem, creditItem));
        }
        else
        {
            this.deleteByCondition(CaCredit.class, new DBCondition(CaCredit.Field.acctId, acctId), new DBCondition(
                    CaCredit.Field.creditItem, creditItem));

        }

    }

    public boolean validate(List<ICredit> addlist, List<ICredit> deleteList)
    {

        if (deleteList.size() > 1)
        {
            imsLogger.error("信用度处理，同一个so_nbr中不会出现两个以上删除操作。deleteList.size" + deleteList.size());
            return false;
        }
        if (addlist.size() > 0 && deleteList.size() > 0)
        {
            imsLogger.error("信用度处理，同一个so_nbr不会同时出现新增和删除操作。deleteList.size" + deleteList.size() + "addList.size" + addlist.size());
            return false;
        }
        if (addlist.size() > 0)
        {
            Long creidtItemId = addlist.get(0).getCreditItemId();
            for (int i = 0; i < addlist.size(); i++)
            {
                ICredit iCredit = addlist.get(i);
                if (creidtItemId.longValue() != iCredit.getCreditItemId().longValue())
                {
                    imsLogger.error("信用度处理，同一个so_nbr下，新增creditItemId必须相同");
                    return false;
                }

                if (FlowConst.FOREVER_CREDIT_ITEM_ID == iCredit.getCreditItemId())
                {
                    if (addlist.size() == 1 && iCredit.getAcctId() != null && iCredit.getAcctId().longValue() != 0)
                    {
                    	//永久信用度，需要更新账户等级
                    	dealAcctSegment(iCredit);
                        return true;
                    }
                    else
                    {
                        imsLogger.error("信用度处理，同一个so_nbr下，永久信用度只能为账户级，且只有一个");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private void dealAcctSegment(ICredit iCredit){
    	CaAccount acctInfo=getCaAccount(iCredit.getAcctId());
    	Integer orgAcctSeg = acctInfo.getAccountSegment();
    	Integer creditLevel=iCredit.getCreditLevel();
    	if(creditLevel != null){
    		if (orgAcctSeg == null || orgAcctSeg.intValue() != creditLevel.intValue())
    		{
    			// 更新 信用等级
    			CaAccount updateAccount = new CaAccount();
    			updateAccount.setAccountSegment(creditLevel);
    			context.getCmp(BaseCmp.class).updateDirectByCondition(updateAccount, new DBCondition(CaAccount.Field.acctId, iCredit.getAcctId()),
    					new DBCondition(CaAccount.Field.expireDate, context.getCommitDate(),Operator.GREAT));
    		}
    	}
    }
}
