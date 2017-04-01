package com.ailk.ims.mapreduce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.easyframe.sal.exchange.SalResponse;
import com.ailk.easyframe.sal.mapreduce.MapReduce;
import com.ailk.easyframe.sal.route.bean.MdbRoute;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAccount;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctBillCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctContact;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctSplitPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SBudgetInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SBudgetInfoDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SCustomer;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SCustomizedList;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SEmailInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SExemption;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroup;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroupExterior;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroupProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroupRela;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SIdentity;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SIdentityBound;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SIdentityPwd;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SImsiSerialNoRel;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromBillCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromCharValue;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromComposite;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromPriceParam;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SRejectList;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SResourceMap;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturnEx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSpecShare;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSpecShareDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncAllInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUsagePay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUser;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctPayDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctRel;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCell;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCustInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserEnterprise;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserGroup;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserGroupTier;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserHome;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserMarket;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserMonitor;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserOrderConfirm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserParam;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserPbx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserPort;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserRela;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserResPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserService;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserServiceStatus;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserShareProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserUserRel;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserVersion;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SValidChange;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlHashMap;
import com.ailk.ims.component.RouterComponent;
import com.ailk.ims.component.helper.GroupHelper;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ImsLogger;

/**
 * @Description
 * @author lijc3
 * @Date 2013-6-28
 */
public class ImsSyncMapReduce<I, O> implements MapReduce<I, O>
{
	
	private ImsLogger imsLogger = new ImsLogger(ImsSyncMapReduce.class);
    // 拆分结果 mdb_key
    private Map<String, SSyncAllInfo> syncAllInfoMap = new HashMap<String, SSyncAllInfo>();
    // mdb_key
    private Map<String, RouteResult> routeRltMap = new HashMap<String, RouteResult>();
    // mdb类型
    private String vertical = null;
    // 过户的时候使用的老账户id
    private Long oldAcctId = null;

    private RouterComponent routCmp = new RouterComponent();

    public void setOldAcctId(Long oldAcctId)
    {
        this.oldAcctId = oldAcctId;
    }

    @Override
    public List<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, I>> mapping(String verticalValue, I in)
    {
        this.vertical = verticalValue;
        SSyncAllInfo sSyncAllInfo = (SSyncAllInfo) in;
        if (oldAcctId == null)
        {
            mapSyncAllInfo(sSyncAllInfo);
        }
        else
        {
            mapSyncAllInfoByChangeOwn(sSyncAllInfo, oldAcctId);
        }
        List<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, I>> list = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, I>>();
        for (Entry<String, SSyncAllInfo> entry : syncAllInfoMap.entrySet())
        {
            String mdbKey = entry.getKey();
            RouteResult result = routeRltMap.get(mdbKey);
            list.add(new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, I>(result, (I) entry.getValue()));
        }
        return list;
    }

    @Override
    public O reduce(I arg0, List<SalResponse<I, O>> arg1)
    {
        SReturnEx sReturnEx = new SReturnEx();
        sReturnEx.set_ret(0);
        
        if (CommonUtil.isEmpty(arg1))
        {	
        	imsLogger.debug("=============================salResponse is null ,return defined sReturn");
            // sReturnEx.set_ret(-1);
            return (O) sReturnEx;
        }
        for (SalResponse resp : arg1)
        {
            if (resp.getException() != null || resp.getOut() == null)
            {	
            	if(resp.getException() != null){
            		imsLogger.debug("============================exception in salResponse");
            		Exception e = resp.getException();
            		imsLogger.debug(e.toString(),e);
            		imsLogger.error(e.toString(),e);
            	}else{
            		imsLogger.debug("============================out in salResponse is null");
            	}
                sReturnEx.set_ret(-1);
                break;
            }
        }
        imsLogger.debug("============================retrun sReturn end");
        return (O) sReturnEx;
    }

    /*
     * public O reduce(List<O> outList) { // SSyncAllInfo sSyncAllInfo = (SSyncAllInfo) this.getInputParam();// use it //
     * List<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, I>> mappingList = super.getMappingResult();// use it // 1、声明返回的大对象
     * SReturnEx sReturnEx = new SReturnEx(); sReturnEx.set_ret(0); for (O ret : outList) { int errorCode = -1; if (ret instanceof
     * SReturn) { SReturn sRet = (SReturn) ret; errorCode = sRet.get_ret(); } else if (ret instanceof SReturnEx) { SReturnEx
     * sRetEx = (SReturnEx) ret; errorCode = sRetEx.get_ret(); } if (errorCode != 0) { sReturnEx.set_ret(errorCode); break; } } //
     * 这里可以继续调用post方法 // 3、 返回合并之后的值 return (O) sReturnEx; }
     */
    /**
     * 过户的时候上发老数据到原来的MDB中
     */
    public void mapSyncAllInfoByChangeOwn(SSyncAllInfo info, Long oldAcctId)
    {
        syncAllInfoMap = new HashMap<String, SSyncAllInfo>();
        syncAllInfoMap.put(getAcctRouteResult(oldAcctId), info);
    }

    public void mapSyncAllInfo(SSyncAllInfo info)
    {
    	mapUseVersion(info.get_userVersion());
        mapAcct(info.get_acctInfo());
        mapAcctBillCycle(info.get_acctBillCycle());
        mapAcctContact(info.get_acctContact());
        mapAcctCycle(info.get_acctCycle());
        mapAcctProd(info.get_acctProm());
        mapAcctSplitPayParam(info.get_acctSplitPay());
        mapBudgetInfoParam(info.get_budgetInfo(), info.get_budgetDtl());
        mapCustomer(info.get_custInfo());
        mapCustomizedParam(info.get_customizedList());
        mapEmailInfo(info.get_emailInfo());
        mapExemption(info.get_exemptInfo());
        mapGroup(info.get_groupInfo());
        mapGroupExterior(info.get_groupExterior());
        mapGroupProd(info.get_groupProm());
        mapGroupRela(info.get_groupRela());
        mapIdentity(info.get_identityInfo());
        mapIdentityBound(info.get_identityBound());
        mapIdentityPwd(info.get_identityPwd());
        mapImsiSerialNoRel(info.get_imsiSerial());
        mapProdCharValue(info.get_promCharValue());
        mapProdPriceParam(info.get_promPrice());
        mapPromBillCycleParam(info.get_promBillCycle());
        mapPromCompositeParam(info.get_promComposite());
        mapSpecShareInfoParam(info.get_specShare(), info.get_specShareDtl());
        mapSRejectListInfoParam(info.get_rejectList());
        mapUsagePayParam(info.get_usagePay());
        mapUser(info.get_userInfo());
        mapUserAcctPayParam(info.get_userAcctPay(), info.get_userAcctPayDtl());
        mapUserAcctRel(info.get_userAcctRel());
        mapUserCell(info.get_userCell());
        mapUserCycle(info.get_userCycle());
        mapUserEnterprise(info.get_userEnterprise());
        mapUserGroup(info.get_userGroupInfo());
        mapUserGroupTier(info.get_userGroupTier());
        mapUserHome(info.get_userHome());
        mapUserMonitor(info.get_userMonitor());
        mapUserOrderConfirm(info.get_userOrderConfirm());
        mapUserProd(info.get_userProm());
        mapUserRela(info.get_userRela());
        mapUserResPayParam(info.get_userResPay());
        mapUserServiceStatus(info.get_userService());
        mapUserShareProd(info.get_userShare());
        mapUserPbx(info.get_userPbx());
        mapUserPort(info.get_userPort());
        mapResourceMap(info.get_resourceMap());
        mapUserMarket(info.get_userMarket());
        mapAcctPromAll(info.get_acctPromAll());
        mapUserPromAll(info.get_userPromAll());
        mapSUserUserRel(info.get_userUserRel());
        mapSUserParam(info.get_userParam());
        mapSUserCustInfo(info.get_userCustInfo());
        mapSUserServiceInfo(info.get_userServiceInfo());
        mapSValidChange(info.get_validChange());
        

        ImsLogger logger = new ImsLogger(getClass());
        logger.dump("aaaaaa", syncAllInfoMap);
    }
    
    private void mapUserPromAll(CsdlHashMap<Long, CsdlArrayList<SUserProm>> userPromAll) {
    	if (CommonUtil.isNotEmpty(userPromAll))
        {
    		for (Entry<Long, CsdlArrayList<SUserProm>> entry : userPromAll.entrySet())
            {
                String mdbKey = getAcctIdByUserId(entry.getKey(),SUserProm.class.getName());
                SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                CsdlHashMap<Long, CsdlArrayList<SUserProm>> tempMap = allInfo.get_userPromAll();
                if (tempMap == null)
                {
                    tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserProm>>(Long.class, CsdlArrayList.class);
                    allInfo.set_userPromAll(tempMap);
                }
                tempMap.put(entry.getKey(), entry.getValue());
                
            }
        }
	}

	private void mapAcctPromAll(CsdlHashMap<Long, CsdlArrayList<SAcctProm>> acctPromAll) {
		if (CommonUtil.isNotEmpty(acctPromAll))
        {
			for (Entry<Long, CsdlArrayList<SAcctProm>> entry : acctPromAll.entrySet())
            {
                SSyncAllInfo allInfo = getSyncAllInfo(getAcctRouteResult(entry.getKey()));
                CsdlHashMap<Long, CsdlArrayList<SAcctProm>> tempMap = allInfo.get_acctPromAll();
                if (tempMap == null)
                {
                    tempMap = new CsdlHashMap<Long, CsdlArrayList<SAcctProm>>(Long.class, CsdlArrayList.class);
                    allInfo.set_acctPromAll(tempMap);
                }
                tempMap.put(entry.getKey(), entry.getValue());
            }
        }
	}

    public SSyncAllInfo getSyncAllInfo(String mdbKey)
    {
        SSyncAllInfo allInfo = syncAllInfoMap.get(mdbKey);
        if (allInfo == null)
        {
            allInfo = new SSyncAllInfo();
            syncAllInfoMap.put(mdbKey, allInfo);
        }
        return allInfo;
    }

    /**
     * 拆分identity lijc3 2013-7-2
     * 
     * @param identityMap
     */
    private void mapIdentity(CsdlHashMap<Long, CsdlArrayList<SIdentity>> identityMap)
    {
        if (CommonUtil.isNotEmpty(identityMap))
        {
            for (Entry<Long, CsdlArrayList<SIdentity>> entry : identityMap.entrySet())
            {
                CsdlArrayList<SIdentity> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SIdentity.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SIdentity>> tempMap = allInfo.get_identityInfo();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SIdentity>>(Long.class, CsdlArrayList.class);
                        allInfo.set_identityInfo(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);

                }
            }
        }
    }

    private void mapIdentityPwd(CsdlHashMap<Long, CsdlArrayList<SIdentityPwd>> identityPwdMap)
    {
        if (CommonUtil.isNotEmpty(identityPwdMap))
        {
            for (Entry<Long, CsdlArrayList<SIdentityPwd>> entry : identityPwdMap.entrySet())
            {
                CsdlArrayList<SIdentityPwd> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SIdentityPwd.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SIdentityPwd>> tempMap = allInfo.get_identityPwd();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SIdentityPwd>>(Long.class, CsdlArrayList.class);
                        allInfo.set_identityPwd(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);

                }
            }
        }
    }

    private void mapIdentityBound(CsdlHashMap<Long, CsdlArrayList<SIdentityBound>> identityBoundMap)
    {
        if (CommonUtil.isNotEmpty(identityBoundMap))
        {
            for (Entry<Long, CsdlArrayList<SIdentityBound>> entry : identityBoundMap.entrySet())
            {
                CsdlArrayList<SIdentityBound> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SIdentityBound.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SIdentityBound>> tempMap = allInfo.get_identityBound();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SIdentityBound>>(Long.class, CsdlArrayList.class);
                        allInfo.set_identityBound(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);

                }
            }
        }
    }

    private void mapUser(CsdlHashMap<Long, CsdlArrayList<SUser>> userMap)
    {
        if (CommonUtil.isNotEmpty(userMap))
        {
            for (Entry<Long, CsdlArrayList<SUser>> entry : userMap.entrySet())
            {
                CsdlArrayList<SUser> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SUser.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUser>> tempMap = allInfo.get_userInfo();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUser>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userInfo(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);

                }
            }
        }
    }

    private CsdlHashMap<Long, CsdlArrayList<SUserVersion>> buildUserVersionMap(Long servId,Long acctId, int syncFlag)
    {
    	RouteResult rt = null ;
    	if (servId != null) {
//    		Long routeAcctId = routCmp.queryAcctIdByUserId(servId);
//    		rt = routCmp.getAcctIdRouteResult(servId, vertical);
    		rt = routCmp.getAcctIdByUserId(servId, vertical);
			imsLogger.debug("==================rt info : userid:" + servId + ";uservsersion : " + rt.getRouteDimension().getResourceVersion() + ",regionCode:"
					+ rt.getRouteDimension().getRegionCode());
			if (rt.getRouteDimension().getResourceVersion() < 1) {
				return null;
			}
		} else{
			imsLogger.debug("===============================================================================rt is null ,stop build CUserVersion,return ");
			return null;
		}
        SUserVersion version = new SUserVersion();
        if(servId != null){
        	version.set_resourceId(servId);
        	version.set_resourceVersion(rt.getRouteDimension().getResourceVersion());
        	version.set_versionType(EnumCodeDefine.MDB_ROUTE_VERSION_TYPE_USER);
        }
        CsdlHashMap<Long, CsdlArrayList<SUserVersion>> versionMap = new CsdlHashMap<Long, CsdlArrayList<SUserVersion>>(
                Long.class, CsdlArrayList.class);
        // 不是全量的情况下全部设置为2
        syncFlag = syncFlag == 0 ? 0 : 2;
        version.set_syncFlag(syncFlag);
        CsdlArrayList<SUserVersion> list = new CsdlArrayList<SUserVersion>(SUserVersion.class);
        list.add(version);
        if(servId != null){
        	versionMap.put(servId, list);
        }
        return versionMap;
    }

    /**
     * lijc3 2013-9-6 这里都是增量的，也就是新的，所以在这里设置版本信息
     * 
     * @param userAcctRelMap
     */
    private void mapUserAcctRel(CsdlHashMap<Long, CsdlArrayList<SUserAcctRel>> userAcctRelMap)
    {
        if (CommonUtil.isNotEmpty(userAcctRelMap))
        {
            // 按照用户进行循环
            for (Entry<Long, CsdlArrayList<SUserAcctRel>> entry : userAcctRelMap.entrySet())
            {
                Long servId = entry.getKey();
                // 增量or全量
                int syncFlag = 0;
                CsdlArrayList<SUserAcctRel> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                	String mdbKey = getAcctIdByUserId(entry.getKey(),SUserAcctRel.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserAcctRel>> tempMap = allInfo.get_userAcctRel();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserAcctRel>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userAcctRel(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);

                    CsdlHashMap<Long, CsdlArrayList<SUserVersion>> versionMap = buildUserVersionMap(servId,null, syncFlag);
                    allInfo.set_userVersion(versionMap);
                   
                }
            }
        }
    }

    private void mapUserCycle(CsdlHashMap<Long, CsdlArrayList<SUserCycle>> userCycleMap)
    {
        if (CommonUtil.isNotEmpty(userCycleMap))
        {
            for (Entry<Long, CsdlArrayList<SUserCycle>> entry : userCycleMap.entrySet())
            {
                CsdlArrayList<SUserCycle> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SUserCycle.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserCycle>> tempMap = allInfo.get_userCycle();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserCycle>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userCycle(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapAcctCycle(CsdlHashMap<Long, CsdlArrayList<SAcctCycle>> acctCycleMap)
    {
        if (CommonUtil.isNotEmpty(acctCycleMap))
        {
            for (Entry<Long, CsdlArrayList<SAcctCycle>> entry : acctCycleMap.entrySet())
            {
                CsdlArrayList<SAcctCycle> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctRouteResult(entry.getKey());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SAcctCycle>> tempMap = allInfo.get_acctCycle();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SAcctCycle>>(Long.class, CsdlArrayList.class);
                        allInfo.set_acctCycle(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapAcctContact(CsdlHashMap<Long, CsdlArrayList<SAcctContact>> acctContactMap)
    {
        if (CommonUtil.isNotEmpty(acctContactMap))
        {
            for (Entry<Long, CsdlArrayList<SAcctContact>> entry : acctContactMap.entrySet())
            {
                CsdlArrayList<SAcctContact> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SAcctContact.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SAcctContact>> tempMap = allInfo.get_acctContact();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SAcctContact>>(Long.class, CsdlArrayList.class);
                        allInfo.set_acctContact(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserServiceStatus(CsdlHashMap<Long, CsdlArrayList<SUserServiceStatus>> userServiceStatusMap)
    {
        if (CommonUtil.isNotEmpty(userServiceStatusMap))
        {
            for (Entry<Long, CsdlArrayList<SUserServiceStatus>> entry : userServiceStatusMap.entrySet())
            {
                CsdlArrayList<SUserServiceStatus> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SUserServiceStatus.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserServiceStatus>> tempMap = allInfo.get_userService();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserServiceStatus>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userService(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapAcct(CsdlHashMap<Long, CsdlArrayList<SAccount>> acctMap)
    {
        if (CommonUtil.isNotEmpty(acctMap))
        {
            for (Entry<Long, CsdlArrayList<SAccount>> entry : acctMap.entrySet())
            {
            	Long acctId = entry.getKey();
            	
            	CsdlHashMap<Long, CsdlArrayList<SUserVersion>> versionMap = buildUserVersionMap(null,acctId, entry.getValue().get(0).get_syncFlag());
            	
                CsdlArrayList<SAccount> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctRouteResult(entry.getKey());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    allInfo.set_userVersion(versionMap);
                    CsdlHashMap<Long, CsdlArrayList<SAccount>> tempMap = allInfo.get_acctInfo();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SAccount>>(Long.class, CsdlArrayList.class);
                        allInfo.set_acctInfo(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapAcctBillCycle(CsdlHashMap<Long, CsdlArrayList<SAcctBillCycle>> acctBillCycleMap)
    {
        if (CommonUtil.isNotEmpty(acctBillCycleMap))
        {
            for (Entry<Long, CsdlArrayList<SAcctBillCycle>> entry : acctBillCycleMap.entrySet())
            {
                CsdlArrayList<SAcctBillCycle> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctRouteResult(entry.getKey());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SAcctBillCycle>> tempMap = allInfo.get_acctBillCycle();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SAcctBillCycle>>(Long.class, CsdlArrayList.class);
                        allInfo.set_acctBillCycle(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapGroupRela(CsdlHashMap<Long, CsdlArrayList<SGroupRela>> groupRelaMap)
    {
        if (CommonUtil.isNotEmpty(groupRelaMap))
        {
            for (Entry<Long, CsdlArrayList<SGroupRela>> entry : groupRelaMap.entrySet())
            {
                CsdlArrayList<SGroupRela> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
//                    String mdbKey = getAcctRouteResult(entry.getKey());
                	//后续版本，service_acct_id为user_id
                	String mdbKey=getAcctIdByUserId(entry.getKey(),SGroupRela.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SGroupRela>> tempMap = allInfo.get_groupRela();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SGroupRela>>(Long.class, CsdlArrayList.class);
                        allInfo.set_groupRela(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapImsiSerialNoRel(CsdlHashMap<Long, CsdlArrayList<SImsiSerialNoRel>> imsiSerialNoRelMap)
    {
        if (CommonUtil.isNotEmpty(imsiSerialNoRelMap))
        {
            for (Entry<Long, CsdlArrayList<SImsiSerialNoRel>> entry : imsiSerialNoRelMap.entrySet())
            {
                CsdlArrayList<SImsiSerialNoRel> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByImsi(entry.getKey());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SImsiSerialNoRel>> tempMap = allInfo.get_imsiSerial();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SImsiSerialNoRel>>(Long.class, CsdlArrayList.class);
                        allInfo.set_imsiSerial(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserMonitor(CsdlHashMap<Long, CsdlArrayList<SUserMonitor>> userMonitorMap)
    {
        if (CommonUtil.isNotEmpty(userMonitorMap))
        {
            for (Entry<Long, CsdlArrayList<SUserMonitor>> entry : userMonitorMap.entrySet())
            {
                CsdlArrayList<SUserMonitor> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SUserMonitor.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserMonitor>> tempMap = allInfo.get_userMonitor();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserMonitor>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userMonitor(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapGroup(CsdlHashMap<Long, CsdlArrayList<SGroup>> groupMap)
    {
        if (CommonUtil.isNotEmpty(groupMap))
        {
            for (Entry<Long, CsdlArrayList<SGroup>> entry : groupMap.entrySet())
            {
                CsdlArrayList<SGroup> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
//                    String mdbKey = getAcctRouteResult(entry.getKey());
                	//后续版本，service_acct_id为user_id
                	String mdbKey=getAcctIdByUserId(entry.getKey(),SGroup.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SGroup>> tempMap = allInfo.get_groupInfo();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SGroup>>(Long.class, CsdlArrayList.class);
                        allInfo.set_groupInfo(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapGroupExterior(CsdlHashMap<Long, CsdlArrayList<SGroupExterior>> groupExteriorMap)
    {
        if (CommonUtil.isNotEmpty(groupExteriorMap))
        {
            for (Entry<Long, CsdlArrayList<SGroupExterior>> entry : groupExteriorMap.entrySet())
            {
                CsdlArrayList<SGroupExterior> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
//                    String mdbKey = getAcctRouteResult(entry.getKey());
                	//后续版本，service_acct_id为user_id
                	String mdbKey=getAcctIdByUserId(entry.getKey(),SGroupExterior.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SGroupExterior>> tempMap = allInfo.get_groupExterior();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SGroupExterior>>(Long.class, CsdlArrayList.class);
                        allInfo.set_groupExterior(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    // TODO
    private void mapUserGroupTier(CsdlHashMap<Long, CsdlArrayList<SUserGroupTier>> userGroupTierMap)
    {
        if (CommonUtil.isNotEmpty(userGroupTierMap))
        {
            for (Entry<Long, CsdlArrayList<SUserGroupTier>> entry : userGroupTierMap.entrySet())
            {
                CsdlArrayList<SUserGroupTier> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
//                    String mdbKey = getAcctRouteResult(entry.getKey());
                	//后续版本，service_acct_id为user_id
                	String mdbKey=getAcctIdByUserId(entry.getKey(),SUserGroupTier.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserGroupTier>> tempMap = allInfo.get_userGroupTier();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserGroupTier>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userGroupTier(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    // 20130905 改造群组成员上发问题
    private void mapUserGroup(CsdlHashMap<Long, CsdlArrayList<SUserGroup>> userGroupMap)
    {
        if (CommonUtil.isNotEmpty(userGroupMap))
        {
            for (Entry<Long, CsdlArrayList<SUserGroup>> entry : userGroupMap.entrySet())
            {
                CsdlArrayList<SUserGroup> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    Map<Long, CsdlArrayList<SUserGroup>> relMap = new HashMap<Long, CsdlArrayList<SUserGroup>>();
                    for (SUserGroup group : sObjectList)
                    {
                        long acctId = group.get_serviceAcctId();
                        // String mdbKey = getAcctRouteResult(acctId);
                        CsdlArrayList<SUserGroup> list = relMap.get(acctId);
                        if (CommonUtil.isEmpty(list))
                        {
                            list = new CsdlArrayList<SUserGroup>(SUserGroup.class);
                            relMap.put(acctId, list);
                        }
                        list.add(group);
                    }

                    for (Entry<Long, CsdlArrayList<SUserGroup>> entry2 : relMap.entrySet())
                    {
//                        String mdbKey = getAcctRouteResult(entry2.getKey());
                    	//后续版本，service_acct_id为user_id
                    	String mdbKey=getAcctIdByUserId(entry2.getKey(),SUserGroup.class.getName());
                        SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                        CsdlHashMap<Long, CsdlArrayList<SUserGroup>> tempMap = allInfo.get_userGroupInfo();
                        if (tempMap == null)
                        {
                            tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserGroup>>(Long.class, CsdlArrayList.class);
                            allInfo.set_userGroupInfo(tempMap);
                        }
                        ////账户编号--list   lijc3 新增  这里是根据账户编号循环的，需要判断用户id是否已经存在了。存在的话就增加到结构里面
                        CsdlArrayList<SUserGroup> userGroupList=tempMap.get(entry.getKey());
                        if(CommonUtil.isEmpty(userGroupList)){
                        	tempMap.put(entry.getKey(), entry2.getValue());
                        }else{
                        	userGroupList.addAll(entry2.getValue());
                        }
                    }
                    /*
                     * SUserGroup prod = sObjectList.get(0); Long acctId = prod.get_serviceAcctId(); SSyncAllInfo allInfo =
                     * getSyncAllInfo(mdbKey); CsdlHashMap<Long, CsdlArrayList<SUserGroup>> tempMap = allInfo.get_userGroupInfo();
                     * if (tempMap == null) { tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserGroup>>(Long.class,
                     * CsdlArrayList.class); allInfo.set_userGroupInfo(tempMap); } tempMap.put(entry.getKey(), sObjectList);
                     */
                }
            }
        }
    }

    private void mapCustomer(CsdlHashMap<Long, CsdlArrayList<SCustomer>> customerMap)
    {
        if (CommonUtil.isNotEmpty(customerMap))
        {
            for (Entry<Long, CsdlArrayList<SCustomer>> entry : customerMap.entrySet())
            {
                CsdlArrayList<SCustomer> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = null;
                    // 单独建立客户的时候，这里没有路由信息，会报错，则跳过不上发
                    try
                    {
                        mdbKey = getAcctIdByCustId(entry.getKey());
                    }
                    catch (Exception e)
                    {
                        mdbKey = null;
                    }
                    if (mdbKey == null)
                    {
                        continue;
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SCustomer>> tempMap = allInfo.get_custInfo();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SCustomer>>(Long.class, CsdlArrayList.class);
                        allInfo.set_custInfo(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);

                }
            }
        }
    }

    private void mapUserRela(CsdlHashMap<Long, CsdlArrayList<SUserRela>> userRelaMap)
    {
        if (CommonUtil.isNotEmpty(userRelaMap))
        {
            for (Entry<Long, CsdlArrayList<SUserRela>> entry : userRelaMap.entrySet())
            {
                CsdlArrayList<SUserRela> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SUserRela.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserRela>> tempMap = allInfo.get_userRela();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserRela>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userRela(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);

                }
            }
        }
    }

    private void mapUserCell(CsdlHashMap<Long, CsdlArrayList<SUserCell>> userCellMap)
    {
        if (CommonUtil.isNotEmpty(userCellMap))
        {
            for (Entry<Long, CsdlArrayList<SUserCell>> entry : userCellMap.entrySet())
            {
                CsdlArrayList<SUserCell> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SUserCell.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserCell>> tempMap = allInfo.get_userCell();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserCell>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userCell(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);

                }
            }
        }
    }

    private void mapUserOrderConfirm(CsdlHashMap<Long, CsdlArrayList<SUserOrderConfirm>> orderConfirmMap)
    {
        if (CommonUtil.isNotEmpty(orderConfirmMap))
        {
            for (Entry<Long, CsdlArrayList<SUserOrderConfirm>> entry : orderConfirmMap.entrySet())
            {
                CsdlArrayList<SUserOrderConfirm> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    String mdbKey = getAcctIdByUserId(entry.getKey(),SUserOrderConfirm.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserOrderConfirm>> tempMap = allInfo.get_userOrderConfirm();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserOrderConfirm>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userOrderConfirm(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserEnterprise(CsdlHashMap<Long, CsdlArrayList<SUserEnterprise>> userEnterpriseMap)
    {
        if (CommonUtil.isNotEmpty(userEnterpriseMap))
        {
            for (Entry<Long, CsdlArrayList<SUserEnterprise>> entry : userEnterpriseMap.entrySet())
            {
                CsdlArrayList<SUserEnterprise> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUserEnterprise prod = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(prod.get_servId(),SUserEnterprise.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserEnterprise>> tempMap = allInfo.get_userEnterprise();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserEnterprise>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userEnterprise(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapCustomizedParam(CsdlHashMap<Long, CsdlArrayList<SCustomizedList>> customizedMap)
    {
        if (CommonUtil.isNotEmpty(customizedMap))
        {
            for (Entry<Long, CsdlArrayList<SCustomizedList>> entry : customizedMap.entrySet())
            {
                CsdlArrayList<SCustomizedList> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SCustomizedList prod = sObjectList.get(0);
                    int objectType = prod.get_objectType();
                    String mdbKey = null;
                    if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                    {
                        mdbKey = getAcctIdByUserId(prod.get_objectId(),SCustomizedList.class.getName());
                    }
                    else
                    {
                        mdbKey = getAcctRouteResult(prod.get_objectId());
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SCustomizedList>> tempMap = allInfo.get_customizedList();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SCustomizedList>>(Long.class, CsdlArrayList.class);
                        allInfo.set_customizedList(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapSRejectListInfoParam(CsdlHashMap<Long, CsdlArrayList<SRejectList>> rejectMap)
    {
        if (CommonUtil.isNotEmpty(rejectMap))
        {
            for (Entry<Long, CsdlArrayList<SRejectList>> entry : rejectMap.entrySet())
            {
                CsdlArrayList<SRejectList> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SRejectList prod = sObjectList.get(0);
                    int objectType = prod.get_objectType();
                    String mdbKey = null;
                    if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                    {
                        mdbKey = getAcctIdByUserId(prod.get_objectId(),SRejectList.class.getName());
                    }
                    else
                    {
                        mdbKey = getAcctRouteResult(prod.get_objectId());
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SRejectList>> tempMap = allInfo.get_rejectList();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SRejectList>>(Long.class, CsdlArrayList.class);
                        allInfo.set_rejectList(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapExemption(CsdlHashMap<Long, CsdlArrayList<SExemption>> exemptionMap)
    {
        if (CommonUtil.isNotEmpty(exemptionMap))
        {
            for (Entry<Long, CsdlArrayList<SExemption>> entry : exemptionMap.entrySet())
            {
                CsdlArrayList<SExemption> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SExemption prod = sObjectList.get(0);
                    int objectType = prod.get_objectType();
                    String mdbKey = null;
                    // 用户级email
                    if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                    {
                        mdbKey = getAcctIdByUserId(prod.get_objectId(),SExemption.class.getName());
                    }
                    else
                    {
                        mdbKey = getAcctRouteResult(prod.get_objectId());
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SExemption>> tempMap = allInfo.get_exemptInfo();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SExemption>>(Long.class, CsdlArrayList.class);
                        allInfo.set_exemptInfo(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapEmailInfo(CsdlHashMap<Long, CsdlArrayList<SEmailInfo>> emailInfoMap)
    {
        if (CommonUtil.isNotEmpty(emailInfoMap))
        {
            for (Entry<Long, CsdlArrayList<SEmailInfo>> entry : emailInfoMap.entrySet())
            {
                CsdlArrayList<SEmailInfo> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SEmailInfo prod = sObjectList.get(0);
                    int objectType = prod.get_objectType();
                    String mdbKey = null;
                    // 用户级email
                    if (objectType == EnumCodeDefine.USER_EMAIL_ADDRESS)
                    {
                        mdbKey = getAcctIdByUserId(prod.get_objectId(),SEmailInfo.class.getName());
                    }
                    else
                    {
                        mdbKey = getAcctRouteResult(prod.get_objectId());
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SEmailInfo>> tempMap = allInfo.get_emailInfo();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SEmailInfo>>(Long.class, CsdlArrayList.class);
                        allInfo.set_emailInfo(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapPromCompositeParam(CsdlHashMap<Long, CsdlArrayList<SPromComposite>> usagePayMap)
    {
        if (CommonUtil.isNotEmpty(usagePayMap))
        {
            for (Entry<Long, CsdlArrayList<SPromComposite>> entry : usagePayMap.entrySet())
            {
                CsdlArrayList<SPromComposite> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SPromComposite prod = sObjectList.get(0);
                    int objectType = prod.get_objectType();
                    String mdbKey = null;
                    if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                    {
                        mdbKey = getAcctIdByUserId(prod.get_objectId(),SPromComposite.class.getName());
                    }
                    else
                    {
                        mdbKey = getAcctRouteResult(prod.get_objectId());
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SPromComposite>> tempMap = allInfo.get_promComposite();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SPromComposite>>(Long.class, CsdlArrayList.class);
                        allInfo.set_promComposite(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUsagePayParam(CsdlHashMap<Long, CsdlArrayList<SUsagePay>> usagePayMap)
    {
        if (CommonUtil.isNotEmpty(usagePayMap))
        {
            for (Entry<Long, CsdlArrayList<SUsagePay>> entry : usagePayMap.entrySet())
            {
                CsdlArrayList<SUsagePay> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUsagePay prod = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(prod.get_payServId(),SUsagePay.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUsagePay>> tempMap = allInfo.get_usagePay();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUsagePay>>(Long.class, CsdlArrayList.class);
                        allInfo.set_usagePay(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapSpecShareInfoParam(CsdlHashMap<Long, CsdlArrayList<SSpecShare>> specShareMap,
            CsdlHashMap<Long, CsdlArrayList<SSpecShareDtl>> specShareDtlMap)
    {
        if (CommonUtil.isNotEmpty(specShareMap))
        {
            for (Entry<Long, CsdlArrayList<SSpecShare>> entry : specShareMap.entrySet())
            {
                CsdlArrayList<SSpecShare> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SSpecShare prod = sObjectList.get(0);
                    String mdbKey = getAcctRouteResult(prod.get_groupId());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SSpecShare>> tempMap = allInfo.get_specShare();
                    CsdlHashMap<Long, CsdlArrayList<SSpecShareDtl>> dtlTempMap = allInfo.get_specShareDtl();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SSpecShare>>(Long.class, CsdlArrayList.class);
                        allInfo.set_specShare(tempMap);
                    }
                    if (dtlTempMap == null)
                    {
                        dtlTempMap = new CsdlHashMap<Long, CsdlArrayList<SSpecShareDtl>>(Long.class, CsdlArrayList.class);
                        allInfo.set_specShareDtl(dtlTempMap);
                    }
                    dtlTempMap.put(entry.getKey(), specShareDtlMap.get(entry.getKey()));
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapBudgetInfoParam(CsdlHashMap<Long, CsdlArrayList<SBudgetInfo>> budgetMap,
            CsdlHashMap<Long, CsdlArrayList<SBudgetInfoDtl>> budgetDtlMap)
    {
        if (CommonUtil.isNotEmpty(budgetMap))
        {
            for (Entry<Long, CsdlArrayList<SBudgetInfo>> entry : budgetMap.entrySet())
            {
                CsdlArrayList<SBudgetInfo> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SBudgetInfo prod = sObjectList.get(0);
                    int objectType = prod.get_budgetObjType();
                    String mdbKey = null;
                    if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                    {
                        mdbKey = getAcctIdByUserId(prod.get_budgetObjId(),SBudgetInfo.class.getName());
                    }
                    else
                    {
                        mdbKey = getAcctRouteResult(prod.get_budgetObjId());
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SBudgetInfo>> tempMap = allInfo.get_budgetInfo();
                    CsdlHashMap<Long, CsdlArrayList<SBudgetInfoDtl>> dtlTempMap = allInfo.get_budgetDtl();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SBudgetInfo>>(Long.class, CsdlArrayList.class);
                        allInfo.set_budgetInfo(tempMap);
                    }
                    if (dtlTempMap == null)
                    {
                        dtlTempMap = new CsdlHashMap<Long, CsdlArrayList<SBudgetInfoDtl>>(Long.class, CsdlArrayList.class);
                        allInfo.set_budgetDtl(dtlTempMap);
                    }
                    dtlTempMap.put(entry.getKey(), budgetDtlMap.get(entry.getKey()));
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapAcctSplitPayParam(CsdlHashMap<Long, CsdlArrayList<SAcctSplitPay>> AcctSplitPayMap)
    {
        if (CommonUtil.isNotEmpty(AcctSplitPayMap))
        {
            for (Entry<Long, CsdlArrayList<SAcctSplitPay>> entry : AcctSplitPayMap.entrySet())
            {
                CsdlArrayList<SAcctSplitPay> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SAcctSplitPay prod = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(prod.get_paidObjId(),SAcctSplitPay.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SAcctSplitPay>> tempMap = allInfo.get_acctSplitPay();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SAcctSplitPay>>(Long.class, CsdlArrayList.class);
                        allInfo.set_acctSplitPay(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserResPayParam(CsdlHashMap<Long, CsdlArrayList<SUserResPay>> promBillCycleMap)
    {
        if (CommonUtil.isNotEmpty(promBillCycleMap))
        {
            for (Entry<Long, CsdlArrayList<SUserResPay>> entry : promBillCycleMap.entrySet())
            {
                CsdlArrayList<SUserResPay> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUserResPay prod = sObjectList.get(0);
                    String mdbKey = getAcctRouteResult(prod.get_payAcctId());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserResPay>> tempMap = allInfo.get_userResPay();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserResPay>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userResPay(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserAcctPayParam(CsdlHashMap<Long, CsdlArrayList<SUserAcctPay>> userAcctPayMap,
            CsdlHashMap<Long, CsdlArrayList<SUserAcctPayDtl>> userAcctPayDtlMap)
    {
        if (CommonUtil.isNotEmpty(userAcctPayMap))
        {
            for (Entry<Long, CsdlArrayList<SUserAcctPay>> entry : userAcctPayMap.entrySet())
            {
                CsdlArrayList<SUserAcctPay> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUserAcctPay prod = sObjectList.get(0);
                    String mdbKey = getAcctRouteResult(prod.get_payAcctId());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserAcctPay>> tempMap = allInfo.get_userAcctPay();
                    CsdlHashMap<Long, CsdlArrayList<SUserAcctPayDtl>> dtlTempMap = allInfo.get_userAcctPayDtl();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserAcctPay>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userAcctPay(tempMap);
                    }
                    if (dtlTempMap == null)
                    {
                        dtlTempMap = new CsdlHashMap<Long, CsdlArrayList<SUserAcctPayDtl>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userAcctPayDtl(dtlTempMap);
                    }
                    dtlTempMap.put(entry.getKey(), userAcctPayDtlMap.get(entry.getKey()));
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapPromBillCycleParam(CsdlHashMap<Long, CsdlArrayList<SPromBillCycle>> promBillCycleMap)
    {
        if (CommonUtil.isNotEmpty(promBillCycleMap))
        {
            for (Entry<Long, CsdlArrayList<SPromBillCycle>> entry : promBillCycleMap.entrySet())
            {
                CsdlArrayList<SPromBillCycle> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SPromBillCycle prod = sObjectList.get(0);
                    int objectType = prod.get_objectType();
                    String mdbKey = null;
                    if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                    {
                        mdbKey = getAcctIdByUserId(prod.get_objectId(),SPromBillCycle.class.getName());
                    }
                    else
                    {
                        mdbKey = getAcctRouteResult(prod.get_objectId());
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SPromBillCycle>> tempMap = allInfo.get_promBillCycle();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SPromBillCycle>>(Long.class, CsdlArrayList.class);
                        allInfo.set_promBillCycle(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserProd(CsdlHashMap<Long, CsdlArrayList<SUserProm>> userPromMap)
    {
        if (CommonUtil.isNotEmpty(userPromMap))
        {
            for (Entry<Long, CsdlArrayList<SUserProm>> entry : userPromMap.entrySet())
            {
                CsdlArrayList<SUserProm> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUserProm prod = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(prod.get_servId(),SUserProm.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserProm>> tempMap = allInfo.get_userProm();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserProm>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userProm(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapAcctProd(CsdlHashMap<Long, CsdlArrayList<SAcctProm>> acctPromMap)
    {
        if (CommonUtil.isNotEmpty(acctPromMap))
        {
            for (Entry<Long, CsdlArrayList<SAcctProm>> entry : acctPromMap.entrySet())
            {
                CsdlArrayList<SAcctProm> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SAcctProm prod = sObjectList.get(0);
                    String mdbKey = getAcctRouteResult(prod.get_acctId());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SAcctProm>> tempMap = allInfo.get_acctProm();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SAcctProm>>(Long.class, CsdlArrayList.class);
                        allInfo.set_acctProm(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapGroupProd(CsdlHashMap<Long, CsdlArrayList<SGroupProm>> groupPromMap)
    {
        if (CommonUtil.isNotEmpty(groupPromMap))
        {
            for (Entry<Long, CsdlArrayList<SGroupProm>> entry : groupPromMap.entrySet())
            {
                CsdlArrayList<SGroupProm> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SGroupProm prod = sObjectList.get(0);
//                    long acctId = prod.get_serviceAcctId();
//                    String mdbKey = null;
                  //if (acctId == 0 && GroupHelper.isGroupPersonProd(prod.get_promType())) {
					//除上海版本外都根据用户存放
				
//                    if (GroupHelper.isGroupPersonProd(prod.get_promType())) 
//                    {
//                        mdbKey = getAcctIdByUserId(prod.get_servId());
//                    }
//                    else
//                    {
//                        mdbKey = getAcctRouteResult(acctId);
//                    	//后续版本，service_acct_id为user_id
//                    	mdbKey=getAcctIdByUserId(acctId);
//                    }
                    long mapId = 0l;
                    if(prod.get_promClass() == EnumCodeDefine.PROD_TYPE_GROUP_1){
                    	mapId = prod.get_servId();
                    }else{
                    	mapId = prod.get_serviceAcctId();
                    }
                    String mdbKey=getAcctIdByUserId(mapId,SGroupProm.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SGroupProm>> tempMap = allInfo.get_groupProm();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SGroupProm>>(Long.class, CsdlArrayList.class);
                        allInfo.set_groupProm(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapProdPriceParam(CsdlHashMap<Long, CsdlArrayList<SPromPriceParam>> promPriceParamMap)
    {
        if (CommonUtil.isNotEmpty(promPriceParamMap))
        {
            for (Entry<Long, CsdlArrayList<SPromPriceParam>> entry : promPriceParamMap.entrySet())
            {
                CsdlArrayList<SPromPriceParam> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SPromPriceParam prod = sObjectList.get(0);
                    int objectType = prod.get_objectType();
                    String mdbKey = null;
                    if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                    {
                        mdbKey = getAcctIdByUserId(prod.get_objectId(),SPromPriceParam.class.getName());
                    }
                    else
                    {
                        mdbKey = getAcctRouteResult(prod.get_objectId());
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SPromPriceParam>> tempMap = allInfo.get_promPrice();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SPromPriceParam>>(Long.class, CsdlArrayList.class);
                        allInfo.set_promPrice(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapProdCharValue(CsdlHashMap<Long, CsdlArrayList<SPromCharValue>> promCharValueMap)
    {
        if (CommonUtil.isNotEmpty(promCharValueMap))
        {
            for (Entry<Long, CsdlArrayList<SPromCharValue>> entry : promCharValueMap.entrySet())
            {
                CsdlArrayList<SPromCharValue> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SPromCharValue prod = sObjectList.get(0);
                    int objectType = prod.get_objectType();
                    String mdbKey = null;
                    if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
                    {
                    	mdbKey = getAcctRouteResult(prod.get_objectId());
                    }
                    else
                    {
                    	mdbKey = getAcctIdByUserId(prod.get_objectId(),SPromCharValue.class.getName());
                    }
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SPromCharValue>> tempMap = allInfo.get_promCharValue();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SPromCharValue>>(Long.class, CsdlArrayList.class);
                        allInfo.set_promCharValue(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserHome(CsdlHashMap<Long, CsdlArrayList<SUserHome>> userHomeMap)
    {
        if (CommonUtil.isNotEmpty(userHomeMap))
        {
            for (Entry<Long, CsdlArrayList<SUserHome>> entry : userHomeMap.entrySet())
            {
                CsdlArrayList<SUserHome> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUserHome prod = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(prod.get_servId(),SUserHome.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserHome>> tempMap = allInfo.get_userHome();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserHome>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userHome(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    // TODO
    private void mapUserShareProd(CsdlHashMap<Long, CsdlArrayList<SUserShareProm>> userHomeMap)
    {
        if (CommonUtil.isNotEmpty(userHomeMap))
        {
            for (Entry<Long, CsdlArrayList<SUserShareProm>> entry : userHomeMap.entrySet())
            {
                CsdlArrayList<SUserShareProm> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUserShareProm prod = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(prod.get_servId(),SUserShareProm.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserShareProm>> tempMap = allInfo.get_userShare();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserShareProm>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userShare(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserPbx(CsdlHashMap<Long, CsdlArrayList<SUserPbx>> userpbxMap)
    {
        if (CommonUtil.isNotEmpty(userpbxMap))
        {
            for (Entry<Long, CsdlArrayList<SUserPbx>> entry : userpbxMap.entrySet())
            {
                CsdlArrayList<SUserPbx> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUserPbx pbx = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(pbx.get_servId(),SUserPbx.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserPbx>> tempMap = allInfo.get_userPbx();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserPbx>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userPbx(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserPort(CsdlHashMap<Long, CsdlArrayList<SUserPort>> userportmMap)
    {
        if (CommonUtil.isNotEmpty(userportmMap))
        {
            for (Entry<Long, CsdlArrayList<SUserPort>> entry : userportmMap.entrySet())
            {
                CsdlArrayList<SUserPort> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUserPort port = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(port.get_servId(),SUserPort.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserPort>> tempMap = allInfo.get_userPort();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserPort>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userPort(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapResourceMap(CsdlHashMap<Long, CsdlArrayList<SResourceMap>> resourceMap)
    {
        if (CommonUtil.isNotEmpty(resourceMap))
        {
            for (Entry<Long, CsdlArrayList<SResourceMap>> entry : resourceMap.entrySet())
            {
                CsdlArrayList<SResourceMap> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SResourceMap sResourceMap = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(sResourceMap.get_servId(),SResourceMap.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SResourceMap>> tempMap = allInfo.get_resourceMap();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SResourceMap>>(Long.class, CsdlArrayList.class);
                        allInfo.set_resourceMap(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }

    private void mapUserMarket(CsdlHashMap<Long, CsdlArrayList<SUserMarket>> sUserMarketMap)
    {
        if (CommonUtil.isNotEmpty(sUserMarketMap))
        {
            for (Entry<Long, CsdlArrayList<SUserMarket>> entry : sUserMarketMap.entrySet())
            {
                CsdlArrayList<SUserMarket> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                    SUserMarket sUserMarket = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(sUserMarket.get_servId(),SUserMarket.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserMarket>> tempMap = allInfo.get_userMarket();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserMarket>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userMarket(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }
    
    private void mapSUserUserRel(CsdlHashMap<Long, CsdlArrayList<SUserUserRel>> sUserUserRelMap)
    {
        if (CommonUtil.isNotEmpty(sUserUserRelMap))
        {
            for (Entry<Long, CsdlArrayList<SUserUserRel>> entry : sUserUserRelMap.entrySet())
            {
                CsdlArrayList<SUserUserRel> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                	SUserUserRel sUserUserRel = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(sUserUserRel.get_servId(),SUserUserRel.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserUserRel>> tempMap = allInfo.get_userUserRel();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserUserRel>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userUserRel(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }
    
    private void mapSUserParam(CsdlHashMap<Long, CsdlArrayList<SUserParam>> sUserParmMap)
    {
        if (CommonUtil.isNotEmpty(sUserParmMap))
        {
            for (Entry<Long, CsdlArrayList<SUserParam>> entry : sUserParmMap.entrySet())
            {
                CsdlArrayList<SUserParam> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                	SUserParam sUserParam = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(sUserParam.get_servId(),SUserParam.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserParam>> tempMap = allInfo.get_userParam();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserParam>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userParam(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }
    
    private void mapSUserCustInfo(CsdlHashMap<Long, CsdlArrayList<SUserCustInfo>> sUserCustInfoMap)
    {
        if (CommonUtil.isNotEmpty(sUserCustInfoMap))
        {
            for (Entry<Long, CsdlArrayList<SUserCustInfo>> entry : sUserCustInfoMap.entrySet())
            {
                CsdlArrayList<SUserCustInfo> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                	SUserCustInfo sUserParam = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(sUserParam.get_servId(),SUserCustInfo.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserCustInfo>> tempMap = allInfo.get_userCustInfo();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserCustInfo>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userCustInfo(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }
    
    private void mapSUserServiceInfo(CsdlHashMap<Long, CsdlArrayList<SUserService>> sUserCustInfoMap)
    {
        if (CommonUtil.isNotEmpty(sUserCustInfoMap))
        {
            for (Entry<Long, CsdlArrayList<SUserService>> entry : sUserCustInfoMap.entrySet())
            {
                CsdlArrayList<SUserService> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                	SUserService sUserParam = sObjectList.get(0);
                    String mdbKey = getAcctIdByUserId(sUserParam.get_servId(),SUserService.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserService>> tempMap = allInfo.get_userServiceInfo();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserService>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userServiceInfo(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);
                }
            }
        }
    }
    
    private void mapSValidChange(CsdlHashMap<Long, CsdlArrayList<SValidChange>> sValidChangeInfoMap){
    	if(CommonUtil.isNotEmpty(sValidChangeInfoMap)){
    		for(Entry<Long, CsdlArrayList<SValidChange>> entry : sValidChangeInfoMap.entrySet()){
    			CsdlArrayList<SValidChange> sObjectList = entry.getValue();
    			if(CommonUtil.isNotEmpty(sObjectList)){
    				SValidChange sValidChange = sObjectList.get(0);
    				String mdbKey = getAcctIdByUserId(sValidChange.get_userId(),SValidChange.class.getName());
    				SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
    				CsdlHashMap<Long, CsdlArrayList<SValidChange>> tempMap = allInfo.get_validChange();
    				if(tempMap == null){
    					tempMap = new CsdlHashMap<Long, CsdlArrayList<SValidChange>>(Long.class,CsdlArrayList.class);
    					allInfo.set_validChange(tempMap);
    				}
    				tempMap.put(entry.getKey(), sObjectList);
    			}
    		}
    	}
    }
    
    public String getAcctIdByUserId(Long userId,String className)
    {
        //RouteResult result = routCmp.getAcctIdByUserId(userId, vertical);
    	imsLogger.debug("==============================query acctid by userid : ",userId,"for mdb : ",className);
    	Long acctId = routCmp.queryAcctIdByUserId(userId);
    	RouteResult result = routCmp.getAcctIdRouteResult(acctId, vertical);
        routeRltMap.put(result.getMdbKey(), result);
        return result.getMdbKey();
    }

    public String getAcctIdByCustId(Long custId)
    {
        RouteResult result = routCmp.getAcctIdByCustId(custId, vertical);
        routeRltMap.put(result.getMdbKey(), result);
        return result.getMdbKey();
    }

    public String getAcctIdByImsi(Long imsi)
    {
        RouteResult result = routCmp.getAcctIdByImsi(imsi, vertical);
        routeRltMap.put(result.getMdbKey(), result); 
        return result.getMdbKey();
    }

    public String getAcctRouteResult(Long acctId)
    {
    	imsLogger.debug("==============================query mdb by acctid : ",acctId);
        RouteResult result = routCmp.getAcctIdRouteResult(acctId, vertical);
        routeRltMap.put(result.getMdbKey(), result);
        return result.getMdbKey();
    }

    private void mapUseVersion(CsdlHashMap<Long, CsdlArrayList<SUserVersion>> versionMap)
    {
        if (CommonUtil.isNotEmpty(versionMap))
        {
            // 按照用户进行循环
            for (Entry<Long, CsdlArrayList<SUserVersion>> entry : versionMap.entrySet())
            {
                Long servId = entry.getKey();
                // 增量or全量
                CsdlArrayList<SUserVersion> sObjectList = entry.getValue();
                if (CommonUtil.isNotEmpty(sObjectList))
                {
                	String mdbKey = getAcctIdByUserId(entry.getKey(),SUserAcctRel.class.getName());
                    SSyncAllInfo allInfo = getSyncAllInfo(mdbKey);
                    CsdlHashMap<Long, CsdlArrayList<SUserVersion>> tempMap = allInfo.get_userVersion();
                    if (tempMap == null)
                    {
                        tempMap = new CsdlHashMap<Long, CsdlArrayList<SUserVersion>>(Long.class, CsdlArrayList.class);
                        allInfo.set_userVersion(tempMap);
                    }
                    tempMap.put(entry.getKey(), sObjectList);

                   
                }
            }
        }
    }

}
