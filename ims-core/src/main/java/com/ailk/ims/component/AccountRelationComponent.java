package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.List;
import jef.database.AbstractDbClient;
import jef.database.query.NativeQuery;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.component.billcycle.entity.ChangeBillCycleContext;
import com.ailk.ims.component.billcycle.handler.BillCycleHandler;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hTree;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SAddSubscriber;
import com.ailk.openbilling.persistence.imsintf.entity.SAddSubscriberList;
import com.ailk.openbilling.persistence.imsintf.entity.SubCA;

/**
 * @Description
 * @author luojb
 * @Date 2011-9-27
 * @Date 2012-4-10 wangjt 注释 createCorpGCA，createFamilyGCA
 */
public class AccountRelationComponent extends BaseComponent
{

    public AccountRelationComponent()
    {
    }

    public CaAccount createGroup(long groupId, long acctId, String validDate, String expireDate,short groupType) throws IMSException
    {
    	return createGCA(groupId, acctId, groupType, validDate, expireDate);
    }

    /**
     * 创建GCA
     * 
     * @param acctId：实CA
     * @param accountType：GCA类型
     */
    private CaAccount createGCA(long groupId, long acctId, short accountType, String validDate, String expireDate)
            throws IMSException
    {
        // 创建GCA
        CaAccount ca = context.get3hTree().loadAcct3hBean(acctId).getAccount();
        SAccount account = new SAccount();
        account.setAcct_id(groupId);
        account.setAcct_type(accountType);
        if (!CommonUtil.isEmpty(ca.getName()))
        {
            account.setAcct_name(ca.getName());
        }
        account.setAcct_class(EnumCodeDefine.ACCOUNT_CLASS_GCA);// 指定为虚账户
        if (ca.getAccountSegment() != null)
        {
            account.setAcct_segment(ca.getAccountSegment().shortValue());
        }
        if (ca.getRegionCode() != null)
        {
            account.setRegion_code(ca.getRegionCode().shortValue());
        }
        // if (!CommonUtil.isEmpty(ca.getCompanyId()))
        // {
        // account.setCompany(ca.getCompanyId().shortValue());
        // }
        if (ca.getProvinceId() != null)
        {
            account.setProv_code(ca.getProvinceId().shortValue());
        }
        if (ca.getForceCycle() != null)
        {
            account.setForce_cycle(ca.getForceCycle().shortValue());
        }
        account.setValid_date(validDate);
        account.setExpire_date(expireDate);
        // yanchuan 2012-02-03 增加返回blance_type
        account.setBalance_type(ca.getBalanceType());

        CaAccount gAccount = context.getComponent(AccountComponent.class).createCaAccount(account);

        // 创建CA-GCA关系
        createCAGCARelation(gAccount.getAcctId(), acctId);

        return gAccount;
    }

    /**
     * 创建帐户关系
     * 
     * @param acctId：GCA
     * @param relAcctId：关系CA(GCA或者CA)
     * @param relType：0-内部群关系，1-GCA层级关系，2-CA/GCA关系
     * @param titleRoleId：[GCA层级关系角色类型]
     * @throws IMSException
     */

    private void createGCARelation(long acctId, long relAcctId, int relType, Integer titleRoleId, String validDate,
            String expireDate) throws IMSException
    {
        CaAccountRel caRel = new CaAccountRel();
        caRel.setGroupId(acctId);
        caRel.setRelGroupId(relAcctId);
        caRel.setRelationshipType(relType);
        if (titleRoleId != null)
        {
            caRel.setTitleRoleId(titleRoleId);
        }
        caRel.setValidDate(IMSUtil.formatDate(validDate, context.getRequestDate()));
        caRel.setExpireDate(IMSUtil.formatExpireDate(expireDate));
        caRel.setSoNbr(context.getSoNbr());

        insert(caRel);
    }

    /**
     * 创建ca-gca关系
     */
    public void createCAGCARelation(long acctId, long relAcctId) throws IMSException
    {
        createGCARelation(acctId, relAcctId, EnumCodeDefine.ACCOUNT_CA_GCA_RELATION, null, null, null);
    }

    /**
     * 创建内部群关系
     */
    public void createCUGRelation(long acctId, long relAcctId, String validDate, String expireDate) throws IMSException
    {
        createGCARelation(acctId, relAcctId, EnumCodeDefine.ACCOUNT_BELONG, null, validDate, expireDate);
    }

    /**
     * 创建集团层级关系
     */
    public void createGCARelations(long acctId, long relAcctId, int titleRoleId) throws IMSException
    {
        createGCARelation(acctId, relAcctId, EnumCodeDefine.ACCOUNT_GCA_RELATION, titleRoleId, null, null);
    }

    /**
     * 创建群id与特殊号码虚账户关系 luojb 2011-11-11
     */
    public void createVpnSpecNbrRelation(long groupId, long gcaId) throws IMSException
    {
        createGCARelation(groupId, gcaId, EnumCodeDefine.ACCOUNT_VPN_SPECNBR_GCA_RELATION, null, null, null);
    }

    /**
     * 不需要创建gca 直接建立ca层级关系 luojb 2011-11-8
     */
    public void createAccountRelation(SubCA ca) throws IMSException
    {
        VPNComponent vpc = context.getComponent(VPNComponent.class);
        Long parCa = ca.getParent_acct_id();
        Long subCa = ca.getAcct_id();
        IMS3hTree ims3hTree = context.get3hTree();
        Acct3hBean parentAccountBean = ims3hTree.loadAcct3hBean(parCa);
        ims3hTree.loadAcct3hBean(subCa);
        CaAccount parentAccount = parentAccountBean.getAccount();

        // 创建层级关系
        createGCARelation(parCa, subCa, EnumCodeDefine.ACCOUNT_GCA_RELATION, ca.getTitle_role_id(),
                IMSUtil.formatDate(context.getRequestDate()), IMSUtil.formatDate(IMSUtil.getDefaultExpireDate()));

        // 添加用户
        SAddSubscriberList userList = ca.getUser_list();
        // 自动创建群处理：把账户的所有归属用户和支付关系用户都加入 2012-01-12

        // 查询账户的支付关系用户，和没有其他支付账户的归属用户
        List<Long> parUserIds = vpc.autoQueryUsers(parCa);
        List<Long> subUserIds = vpc.autoQueryUsers(subCa);

        if (userList != null && !CommonUtil.isEmpty(userList.getSAddSubscriber()))
        {
            List<CaAccountRv> addMemList = new ArrayList<CaAccountRv>();
            for (SAddSubscriber user : userList.getSAddSubscriber())
            {
                Long userId = user.getUser_id();
                String phone = user.getPhone_id();
                User3hBean userBean = ims3hTree.loadUser3hBean(userId, phone);
                userId = userBean.getUserId();
                phone = userBean.getPhoneId();
                Integer titleRoleId = user.getTitle_role_id();
                // 去掉自动创建关系用户列表中与业务传入重复的id
                if (subUserIds != null && subUserIds.contains(userId))
                    subUserIds.remove(userId);
                if (parUserIds != null && parUserIds.contains(userId))
                    parUserIds.remove(userId);

                if (vpc.queryRvHierarchy(userId) != null)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.CARELATION_USER_HASBEENIN, userId);
                }
                CaAccountRv rv = vpc.addMemberHierarchy(subCa, userId, phone, titleRoleId);
                addMemList.add(rv);
            }
            insertBatch(addMemList);
        }

        // 去除掉和账户本身已经有关系的用户
        parUserIds = vpc.autoUsersNotInSelf(parCa, parUserIds);
        subUserIds = vpc.autoUsersNotInSelf(subCa, subUserIds);

        // 创建关系
        if (CommonUtil.isNotEmpty(parUserIds))
        {
            context.getComponent(VPNComponent.class).autoJoinMembers(parCa, parUserIds);
        }
        if (CommonUtil.isNotEmpty(subUserIds))
        {
            context.getComponent(VPNComponent.class).autoJoinMembers(subCa, subUserIds);
        }
        // 查询父账户是否强制账期
        Integer forceCycle = parentAccount.getForceCycle();

        if (forceCycle != null)
        {
            if (forceCycle.equals(EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE)
                    || forceCycle.equals(EnumCodeDefine.FORCECYCLE_BY_PARENT_ACCOUNT))
            {
                // do 强制账期
                if (parentAccount.getBillSts() != null && parentAccount.getBillSts().intValue() == EnumCodeDefine.BILL_STS_ACTIVE)
                    this.forceCycle(ca.getParent_acct_id(), ca.getAcct_id());
                else
                    IMSUtil.throwBusiException(ErrorCodeDefine.CARELATION_PARENT_BILLSTS_NOTACTIVE, parentAccount.getAcctId());
            }
        }
    }

    /**
     * luojb 2011-9-26 新加入集团层级的账户及其子账户强制账户账期、账户产品账期、账户所有归属用户产品账期
     * 
     * @param parentAcctId:用于验证是否需要强制账期
     * @param acctId:新加入账户
     * @throws IMSException
     */
    private void forceCycle(Long parentAcctId, Long acctId) throws IMSException
    {
        List<Long> toForceCaList = this.queryAllSubCa(acctId);
        if (!CommonUtil.isEmpty(toForceCaList))
        {
            toForceCaList.add(acctId);
        }
        else
        {
            toForceCaList = new ArrayList<Long>();
            toForceCaList.add(acctId);
        }
        // 强制新加入集团账户级其子账户的账期
        forceSubCaCycle(parentAcctId, toForceCaList);
        // 强制新加入集团账户的帐户级订购产品和所有归属用户的订购产品的账期
        context.getComponent(ProductCycleComponent.class).changeProdBillCycle(acctId);
    }

    private void forceSubCaCycle(Long parentAcctId, List<Long> toForceCaList) throws IMSException
    {
        AccountComponent ac = context.getComponent(AccountComponent.class);
        for (Long acctId : toForceCaList)
        {
            // 强制账户账期
            CaAccount dmAccount = context.get3hTree().loadAcct3hBean(acctId).getAccount();
            //不需要传cust_id了，直接根据帐户id去load yanchuan 2012-05-25
            //Long custId = ac.queryCustIdByAcctId(acctId);
            Long cycleSpecId = ac.queryAcctCycle(parentAcctId);
            Integer billSts = dmAccount.getBillSts();
            if (billSts != null && billSts.intValue() == EnumCodeDefine.BILL_STS_ACTIVE){
                BillCycleHandler billCycleHandler = ChangeBillCycleContext.instanceHandler(dmAccount.getAcctId(), cycleSpecId, null, null, null, context);
                billCycleHandler.changeBillCycle();
            }
            else{
                BillCycleHandler billCycleHandler = ChangeBillCycleContext.instanceHandler(dmAccount.getAcctId(), cycleSpecId, null, null, null, context);
                billCycleHandler.createBillCycle();
            }
            // 修改子账户强制账期标志
            CaAccount newAccount = new CaAccount();
            newAccount.setForceCycle(EnumCodeDefine.FORCECYCLE_BY_PARENT_ACCOUNT);

//            CaAccount where = new CaAccount();
//            where.setAcctId(acctId);
            updateByCondition(newAccount, new DBCondition(CaAccount.Field.acctId,acctId));
        }
    }

    /**
     * 2011-11-08 实账户建立层级关系 修改删除逻辑 luojb 2011-11-8
     * 
     * @param ca
     * @throws IMSException
     */
    public void delAccountRelation(SubCA ca) throws IMSException
    {
        IMS3hTree tree = context.get3hTree();
        Long parAcctId = ca.getParent_acct_id();
        Long subAcctId = ca.getAcct_id();
        tree.loadAcct3hBean(parAcctId);
        tree.loadAcct3hBean(subAcctId);

//        CaAccountRel caRel = new CaAccountRel();
//        caRel.setAcctId(parAcctId);
//        caRel.setRelAcctId(subAcctId);
//        caRel.setRelationshipType(EnumCodeDefine.ACCOUNT_GCA_RELATION);
        deleteByCondition(CaAccountRel.class,new DBCondition(CaAccountRel.Field.groupId,parAcctId),
        		new DBCondition(CaAccountRel.Field.relGroupId,subAcctId),
        		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_GCA_RELATION));
        SAddSubscriberList userList = ca.getUser_list();
        if (userList != null && !CommonUtil.isEmpty(userList.getSAddSubscriber()))
        {
            VPNComponent vpn = context.getComponent(VPNComponent.class);
            for (SAddSubscriber user : userList.getSAddSubscriber())
            {
                Long userId = user.getUser_id();
                String phone = user.getPhone_id();
                User3hBean userBean = tree.loadUser3hBean(userId, phone);
                if (vpn.queryRvHierarchy(subAcctId, userBean.getUserId()) == null)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.CARELATION_USER_NOT_IN, userId);
                }

                vpn.deleteMemberHierarchy(subAcctId, userBean.getUserId(), userBean.getPhoneId());
            }
        }
    }

    /**
     * 判断两个帐户是否有直接集团关系,用于删除两账户层级关系时的判断
     * 
     * @param acctId：上级
     * @param subCAId：下级
     * @return
     * @throws IMSException
     */
    public boolean hasRelation(long acctId, long subCAId) throws IMSException
    {
//        CaAccountRel caRel = new CaAccountRel();
//        caRel.setAcctId(acctId);
//        caRel.setRelAcctId(subCAId);
//        caRel.setRelationshipType(EnumCodeDefine.ACCOUNT_GCA_RELATION);
    	CaAccountRel caRel = querySingle(CaAccountRel.class,new DBCondition(CaAccountRel.Field.groupId,acctId),
        		new DBCondition(CaAccountRel.Field.relGroupId,subCAId),
        		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_GCA_RELATION));
        return caRel != null;
    }

    /**
     * 判断一个CA是否能挂载到parentCA下 luojb 2011-11-26
     * 
     * @param parentAcctId
     * @param acctId
     * @return
     * @throws IMSException
     */
    public void addAllowed(long parentAcctId, long acctId) throws IMSException
    {
        if (parentAcctId == acctId)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CARELATION_PARENT_SUB_SAME_ACCOUNT, parentAcctId, acctId);
        }
        // 查询acctId是否有父账户
//        CaAccountRel caRel = new CaAccountRel();
//        caRel.setRelAcctId(acctId);
//        caRel.setRelationshipType(EnumCodeDefine.ACCOUNT_GCA_RELATION);
        CaAccountRel caRel = querySingle(CaAccountRel.class,new DBCondition(CaAccountRel.Field.relGroupId,acctId),
        		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_GCA_RELATION));
        if (caRel != null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CARELATION_CA_HASBEENIN, acctId);
        }

        // 查询acctId的子账户中是否有parentId，是则报错
        List<Long> subCas = queryAllSubCa(acctId);
        if (CommonUtil.isNotEmpty(subCas) && subCas.contains(parentAcctId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CARELATION_ADD_ERROR_FOR_PARENT_IS_SUB, parentAcctId, acctId);
        }
    }

    /**
     * 查询ca是否在层级关系中,用于管理层级中的用户时判断 luojb 2011-11-18
     * 
     * @param acctId
     * @return
     * @throws IMSException
     */
    public boolean hasBeenIn(long acctId) throws IMSException
    {
        CaAccountRel rel = querySingle(CaAccountRel.class, new DBCondition(CaAccountRel.Field.relationshipType,
                EnumCodeDefine.ACCOUNT_GCA_RELATION), new DBOrCondition(new DBCondition(CaAccountRel.Field.groupId, acctId),
                new DBCondition(CaAccountRel.Field.relGroupId, acctId)));
        return rel != null;
    }

    /**
     * 根据实账户获取所有对应虚账户
     * 
     * @param acctId
     * @return
     * @throws IMSException
     * @author luojb
     */
    public List<Long> getAllGCAId(long acctId) throws IMSException
    {
//        CaAccountRel caRel = new CaAccountRel();
//        caRel.setRelAcctId(acctId);
//        caRel.setRelationshipType(EnumCodeDefine.ACCOUNT_CA_GCA_RELATION);
        List<CaAccountRel> caRelList = query(CaAccountRel.class,new DBCondition(CaAccountRel.Field.relGroupId,acctId),
        		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_CA_GCA_RELATION));
        if (CommonUtil.isEmpty(caRelList))
        {
            return null;
        }
        List<Long> gcaIdList = new ArrayList<Long>();
        for (CaAccountRel ca : caRelList)
        {
            gcaIdList.add(ca.getGroupId());
        }
        return gcaIdList;
    }

    /**
     * 查询父级账户树 luojb 2012-1-11
     * 
     * @param acctId
     * @return
     */
    public List<CaAccountRel> getAllParentAcctTreeByAcctId(Long acctId)
    {
        // 2012-01-09 wuyujie : 改用DateUtil
        String date = DateUtil.currentString19();
        AbstractDbClient db = DBUtil.getDBClient();
        String tableNameOfCaAccountRel = DBUtil.buildTableName(CaAccountRel.class);
        StringBuffer sql = new StringBuffer("");
        sql.append("select * from ");
        sql.append(tableNameOfCaAccountRel);
        sql.append(" where relationship_type = ");
        sql.append(EnumCodeDefine.ACCOUNT_GCA_RELATION);
        sql.append("and to_date ('" + date);
        sql.append("','yyyy-mm-dd hh24:mi:ss') between valid_date and expire_date start with rel_acct_id = ");
        sql.append(acctId);
        sql.append(" connect by prior acct_id = rel_acct_id");

        List<CaAccountRel> caRelList = null;
        try
        {
            NativeQuery<CaAccountRel> queryResult = db.createNativeQuery(sql.toString(), CaAccountRel.class);
            caRelList = queryResult.getResultList();
        }
        catch (Exception e)
        {
            IMSUtil.throwBusiException("query sql error :" + e);
        }
        return caRelList;
    }

    /**
     * 查询子级账户树 luojb 2012-1-11
     * 
     * @param acctId
     * @return
     */
    public List<CaAccountRel> getAllAcctTreeByAcctId(Long acctId, int relType)
    {
        // to_date('','yyyy-mm-dd hh24:mi:ss')
        // 2012-01-09 wuyujie : 改用DateUtil
        String date = DateUtil.currentString19();

        AbstractDbClient db = DBUtil.getDBClient();
        String tableNameOfCaAccountRel = DBUtil.buildTableName(CaAccountRel.class);
        StringBuffer sql = new StringBuffer("");
        sql.append("select * from ");
        sql.append(tableNameOfCaAccountRel);
        sql.append(" where relationship_type = ");
        sql.append(relType);
        sql.append(" and ");
        sql.append("to_date ('" + date);
        sql.append("','yyyy-mm-dd hh24:mi:ss') between valid_date and expire_date start with acct_id = ");
        sql.append(acctId);
        sql.append(" connect by prior rel_acct_id = acct_id");

        List<CaAccountRel> caRelList = null;
        try
        {
            NativeQuery<CaAccountRel> queryResult = db.createNativeQuery(sql.toString(), CaAccountRel.class);
            caRelList = queryResult.getResultList();
        }
        catch (Exception e)
        {
        	imsLogger.error(e,e);
        }
        return caRelList;
    }

    public List<Long> queryAllParentCA(Long parAcctId) throws IMSException
    {
        List<CaAccountRel> caRelList = getAllParentAcctTreeByAcctId(parAcctId);
        if (CommonUtil.isEmpty(caRelList))
        {
            return null;
        }
        // 过滤掉重复的id
        List<Long> caList = new ArrayList<Long>();
        for (CaAccountRel ca : caRelList)
        {
            Long acctId = ca.getGroupId();
            Long relAcctId = ca.getRelGroupId();
            if (!acctId.equals(parAcctId) && !caList.contains(acctId))
            {
                caList.add(acctId);
            }
            if (!relAcctId.equals(parAcctId) && !caList.contains(relAcctId))
            {
                caList.add(relAcctId);
            }
        }
        if (CommonUtil.isEmpty(caList))
        {
            return null;
        }
        return caList;
    }

    /**
     * 层级关系直接用实账户建立，该方法传入实账户，查询子账户 luojb 2011-11-08
     * 
     * @param gcaId
     * @return List<Long> gcaIds
     * @throws Exception
     */
    private List<Long> queryAllSubCA(Long parAcctId, int relType) throws IMSException
    {

        List<CaAccountRel> caRelList = getAllAcctTreeByAcctId(parAcctId, relType);
        if (CommonUtil.isEmpty(caRelList))
        {
            return null;
        }
        // 过滤掉重复的id
        List<Long> caList = new ArrayList<Long>();
        for (CaAccountRel ca : caRelList)
        {
            Long acctId = ca.getGroupId();
            Long relAcctId = ca.getRelGroupId();
            if (!acctId.equals(parAcctId) && !caList.contains(acctId))
            {
                caList.add(acctId);
            }
            if (!relAcctId.equals(parAcctId) && !caList.contains(relAcctId))
            {
                caList.add(relAcctId);
            }
        }
        if (CommonUtil.isEmpty(caList))
        {
            return null;
        }
        return caList;
    }

    /**
     * 根据groupId查询所有内部群CUG
     * 
     * @author luojb
     */
    public List<Long> queryAllCUG(long groupId)
    {
        return queryAllSubCA(groupId, EnumCodeDefine.ACCOUNT_BELONG);
    }

    /**
     * 查找ca的所有子级ca 2011-11-08 实账户建立层级关系
     * 
     * @author luojb
     */
    public List<Long> queryAllSubCa(long acctId) throws IMSException
    {
        return queryAllSubCA(acctId, EnumCodeDefine.ACCOUNT_GCA_RELATION);
    }

    /**
     * 查询账户的父账户 luojb 2011-10-18 2011-11-08 实账户建立层级关系 修改逻辑
     */
    public Long queryFatherAcctId(Long subAcctId) throws IMSException
    {
        CaAccountRel caRel = this.queryCaAccountRel(subAcctId, EnumCodeDefine.ACCOUNT_GCA_RELATION);
        if (caRel == null)
        {
            return null;
        }
        return caRel.getGroupId();
    }

    /**
     * 根据子级账户和关系类型查询关系实体 luojb 2011-11-8
     */
    public CaAccountRel queryCaAccountRel(Long subAcctId, int relType) throws IMSException
    {
//        CaAccountRel where = new CaAccountRel();
//        where.setRelAcctId(subAcctId);
//        where.setRelationshipType(relType);
        return querySingle(CaAccountRel.class,new DBCondition(CaAccountRel.Field.relGroupId,subAcctId),
        		new DBCondition(CaAccountRel.Field.relationshipType,relType));
    }
    /**
     * 根据子级账户和关系类型查询集团层级luojb 2011-11-8
     */

    public CaAccountRel queryAcctRelBySubAcctId(Long subAcctId)
    {
    	 return querySingle(CaAccountRel.class,new DBCondition(CaAccountRel.Field.relGroupId,subAcctId),
         		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_GCA_RELATION));
    }
    
    public List<CaAccountRel> querySubCaRels(Long acctId) throws IMSException
    {
//        CaAccountRel where = new CaAccountRel();
//        where.setAcctId(acctId);
//        where.setRelationshipType(EnumCodeDefine.ACCOUNT_GCA_RELATION);
        return query(CaAccountRel.class,new DBCondition(CaAccountRel.Field.groupId,acctId),
        		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_GCA_RELATION));
    }

    /**
     * 根据实帐户查询虚帐户
     */
    public List<CaAccountRel> queryGCAAccountRel(Long acctId) throws IMSException
    {
//        CaAccountRel where = new CaAccountRel();
//        where.setRelAcctId(acctId);
//        where.setRelationshipType(EnumCodeDefine.ACCOUNT_CA_GCA_RELATION);
        return query(CaAccountRel.class,new DBCondition(CaAccountRel.Field.relGroupId,acctId),
        		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_CA_GCA_RELATION));
    }

    /**
     * @author yanchuan 2012-3-8
     * @describe 查询帐户下的虚帐户
     * @param acctId
     * @return
     * @throws IMSException
     */
    public List<Long> queryGCAAcctIds(Long acctId) throws IMSException
    {
        List<CaAccountRel> acctRels = this.queryGCAAccountRel(acctId);
        List<Long> acctIds = new ArrayList<Long>();
        if (CommonUtil.isEmpty(acctRels))
        {
            return null;
        }
        for (CaAccountRel accountRel : acctRels)
        {
            if (accountRel == null)
                continue;
            acctIds.add(accountRel.getGroupId());
        }
        return acctIds;
    }
}
