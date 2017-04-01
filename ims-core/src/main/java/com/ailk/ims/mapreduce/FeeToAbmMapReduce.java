package com.ailk.ims.mapreduce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.tools.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ailk.easyframe.route.entity.dto.RouteDimension;
import com.ailk.easyframe.route.entity.dto.RouteDimension.Key;
import com.ailk.easyframe.sal.exchange.SalResponse;
import com.ailk.easyframe.sal.mapreduce.Entry;
import com.ailk.easyframe.sal.mapreduce.MapReduce;
import com.ailk.easyframe.sal.route.bean.MdbRoute;
import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SAbmArAsset;
import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SAbmArUpdateIncrRet;
import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SAbmArUpdateIncrUp;
import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SOtFreeRes;
import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SVersion;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.web.common.exception.BusinessException;
import com.ailk.ims.abmtransfer.commom.ErrorCode;
import com.ailk.ims.abmtransfer.commom.SdlUtil;
import com.ailk.ims.component.RouterComponent;

public class FeeToAbmMapReduce implements MapReduce<SAbmArUpdateIncrUp, SAbmArUpdateIncrRet>
{

    /**
     * 日志记录
     */
    private static final Log log = LogFactory.getLog(FeeToAbmMapReduce.class);

    private RouterComponent routCmp = new RouterComponent();

    public List<Entry<MdbRoute, SAbmArUpdateIncrUp>> mapping(String verticalValue, SAbmArUpdateIncrUp in)
    {
        List<Entry<MdbRoute, SAbmArUpdateIncrUp>> list = new ArrayList<Entry<MdbRoute, SAbmArUpdateIncrUp>>();
        SAbmArUpdateIncrUp updateUp = (SAbmArUpdateIncrUp) in;

        // 先按帐户、用户编号分组
        Map<Long, SAbmArUpdateIncrUp> mapUp = new HashMap<Long, SAbmArUpdateIncrUp>();
        if (updateUp != null)
        {
            for (SAbmArAsset asset : updateUp.get_arAssetList())
            {

                // 将一次性免费资源的列表otfreeresList分配到对应的ownerId对应的对象中
                if (null != asset.get_otFreeresList() && asset.get_otFreeresList().size() > 0)
                {
                    Map<Long, CsdlArrayList<SOtFreeRes>> sFreeResTmpMap = new HashMap<Long, CsdlArrayList<SOtFreeRes>>();
                    for (SOtFreeRes sOtFreeRes : asset.get_otFreeresList())
                    {
                        if (SdlUtil.OWNER_TEYP_USER != sOtFreeRes.get_objectType()
                                && SdlUtil.OWNER_TEYP_ACCT != sOtFreeRes.get_objectType()
                                && SdlUtil.OWNER_TEYP_GROUP != sOtFreeRes.get_objectType())
                        {
                            throw new BusinessException(ErrorCode.COMMON_OWNER_TYPE_INVALID, String.valueOf(sOtFreeRes
                                    .get_objectType()));
                        }
                        if (sOtFreeRes.get_objectId() <= 0)
                        {
                            throw new BusinessException(ErrorCode.COMMON_OWNER_ID_INVALID, String.valueOf(sOtFreeRes
                                    .get_objectId()));
                        }
                        long key = SdlUtil.genKey(sOtFreeRes.get_objectType(), sOtFreeRes.get_objectId());
                        CsdlArrayList<SOtFreeRes> sFreeResTmpList = sFreeResTmpMap.containsKey(key) ? sFreeResTmpMap.get(key)
                                : new CsdlArrayList<SOtFreeRes>(SOtFreeRes.class);
                        sFreeResTmpList.add(sOtFreeRes);
                        sFreeResTmpMap.put(key, sFreeResTmpList);
                    }
                    assignOtFreeResList(mapUp, sFreeResTmpMap);
                }
            }
        }

        // 再查询mdb路由，按mdbKey分组
        Map<String, SAbmArUpdateIncrUp> mdbKeyMap = new HashMap<String, SAbmArUpdateIncrUp>();
        Map<String, MdbRoute> routeMap = new HashMap<String, MdbRoute>();
        if (mapUp.size() > 0)
        {
            for (Map.Entry<Long, SAbmArUpdateIncrUp> entry : mapUp.entrySet())
            {
                int ownerType = SdlUtil.getKeyType(entry.getKey());
                long ownerId = SdlUtil.getKeyId(entry.getKey());
                MdbRoute mdbRoute = null;
                RouteDimension dim = new RouteDimension();
                if (ownerType == SdlUtil.OWNER_TEYP_ACCT || ownerType == SdlUtil.OWNER_TEYP_GROUP)
                {
                    dim.setValue(Key.ACCT_ID, String.valueOf(ownerId));
                }
                else if (ownerType == SdlUtil.OWNER_TEYP_USER)
                {
                    dim.setValue(Key.RESOURCE_ID, String.valueOf(ownerId));
                }
                else
                {
                    throw new BusinessException(ErrorCode.COMMON_OWNER_TYPE_INVALID, String.valueOf(ownerType));
                }
                mdbRoute = routCmp.queryRoute(verticalValue, ownerType, ownerId);
                String mdbKey = mdbRoute.getMdbKey();
                if (mdbKeyMap.containsKey(mdbKey))
                {
                    SAbmArUpdateIncrUp tmpFreezeUp = mdbKeyMap.get(mdbKey);
                    tmpFreezeUp.get_arAssetList().addAll(entry.getValue().get_arAssetList());
                    tmpFreezeUp.get_sessioninfoList().addAll(entry.getValue().get_sessioninfoList());
                    if (ownerType == SdlUtil.OWNER_TEYP_USER)
                    {
                        SVersion sVersion = new SVersion();
                        sVersion.set_versionKey(mdbRoute.getResourceId());// 用户编号
                        sVersion.set_versionSeq(mdbRoute.getVersion());
                        tmpFreezeUp.get_versionList().add(sVersion);
                    }
                }
                else
                {
                    if (ownerType == SdlUtil.OWNER_TEYP_USER)
                    {
                        SVersion sVersion = new SVersion();
                        sVersion.set_versionKey(mdbRoute.getResourceId());// 用户ID
                        sVersion.set_versionSeq(mdbRoute.getVersion());
                        entry.getValue().get_versionList().add(sVersion);
                    }
                    mdbKeyMap.put(mdbKey, entry.getValue());
                    routeMap.put(mdbKey, mdbRoute);
                }
            }
        }
        else
        {
            throw new BusinessException(ErrorCode.NO_ASSETS_TO_DEAL);
        }
        // 组织数据传给框架分发
        int mdbCountFree = 0;
        for (Map.Entry<String, SAbmArUpdateIncrUp> entry : mdbKeyMap.entrySet())
        {
            if (!routeMap.containsKey(entry.getKey()))
            {
                throw new BusinessException(ErrorCode.ROUTE_MAP_NOT_MATCH, String.valueOf(entry.getKey()));
            }
            if (havFreeRes(entry.getValue()))
            {
                mdbCountFree++;
                // 校验免费资源操作mdb的个数，若存在两个或两个以上的mdb免费资源事务，需要报错提示
                if (mdbCountFree > 1)
                {
                    throw new BusinessException(ErrorCode.MORE_THAN_ONE_MDB);
                }
            }
            list.add(new Entry<MdbRoute, SAbmArUpdateIncrUp>(routeMap.get(entry.getKey()), entry.getValue()));
        }
        if (log.isDebugEnabled())
        {
            log.debug("mapping result :" + JsonUtil.serialize(list));
        }

        return list;
    }

    /**
     * 判断该上发资产中是否包含免费资源或一次性免费资源
     * 
     * @param updateIncrUp
     * @return
     */
    private boolean havFreeRes(SAbmArUpdateIncrUp updateIncrUp)
    {
        if (null != updateIncrUp)
        {
            for (SAbmArAsset sAbmArAsset : updateIncrUp.get_arAssetList())
            {
                if (!sAbmArAsset.get_freeresList().isEmpty() || !sAbmArAsset.get_otFreeresList().isEmpty())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public SAbmArUpdateIncrRet reduce(SAbmArUpdateIncrUp orgIn, List<SalResponse<SAbmArUpdateIncrUp, SAbmArUpdateIncrRet>> outList)
    {
        SAbmArUpdateIncrRet updateRet = new SAbmArUpdateIncrRet();
        int i = 0;
        for (SalResponse<SAbmArUpdateIncrUp, SAbmArUpdateIncrRet> response : outList)
        {
            // 2、 每个小对象合并到大对象中[这个业务主要处理大对象中的m_iRet字段即可]
            SAbmArUpdateIncrRet tmp = response.getOut();
            ++i;
            if (tmp == null)
            {
                log.error("index of [" + i + "] List<SAbmArUpdateIncrRet> return is null:", response.getException());
                updateRet.set_succFlag(Integer.valueOf(ErrorCode.MDB_SERVICE_RETURN_ERROR));
                break;
            }
            else if (tmp.get_succFlag() != 0)
            {
                log.error("index of [" + i + "] List<SAbmArUpdateIncrRet> return is failure!");
                updateRet.set_succFlag(tmp.get_succFlag());
                break;
            }
            else if (tmp.get_succFlag() == 0)
            {
                if (i == 1)
                {
                    updateRet.set_succFlag(tmp.get_succFlag());
                    updateRet.set_batchNo(tmp.get_batchNo());
                }
            }
        }
        if (updateRet.get_succFlag() != 0)
        {
            throw new BusinessException(String.valueOf(updateRet.get_succFlag()));
        }
        // 免费资源，业务侧只会有一个mdb操作，不会存在一个成功，一个失败的情况；
        // 帐本和信用度，在其中一个mdb更新失败时，则返回失败，帐管侧，帐本和信用度，在更新失败时，会插上发表，保证资金一致性。
        // 综上，多个mdb调用，存在某个mdb失败，不需要调用反更新，直接返回更新失败即可。
        // rollBackUpdate(outList, allFreezeSucc);
        return updateRet;
    }

    // private void rollBackUpdate(List<SAbmArUpdateIncrRet> outList,
    // boolean allFreezeSucc) {
    // int i;
    // if(!allFreezeSucc){
    // //post反操作，反更新已更新部分
    // List<Entry<MdbRoute, SAbmArUpdateIncrUp>> inList = super.getMappingResult();
    // if(inList.size() != outList.size()){
    // throw new BusinessException("inList's size not equal outList's size !");
    // }
    // i = 0;
    // List<Entry<MdbRoute, SAbmArUpdateIncrUp>> unUpdateUpInList = new ArrayList<Entry<MdbRoute, SAbmArUpdateIncrUp>>();
    // for(SAbmArUpdateIncrRet t : outList){
    // i++;
    // if(t.get_succFlag() == 0){
    // //反更新逻辑
    // SAbmArUpdateIncrUp unUpdateUp = new SAbmArUpdateIncrUp();
    // for(SAbmArAsset asset : inList.get(i).getValue().get_arAssetList()){
    // SAbmArAsset sAbmArAsset = new SAbmArAsset();
    // for(SPocket a : asset.get_pocketList()){
    // a.set_amount(0-a.get_amount());
    // sAbmArAsset.get_pocketList().add(a);
    // }
    // for(SCredit a : asset.get_creditList()){
    // a.set_amount(0-a.get_amount());
    // sAbmArAsset.get_creditList().add(a);
    // }
    // for(SFreeRes a : asset.get_freeresList()){
    // a.set_amount(0-a.get_amount());
    // sAbmArAsset.get_freeresList().add(a);
    // }
    // for(SOtFreeRes a : asset.get_otFreeresList()){
    // a.set_amount(0-a.get_amount());
    // sAbmArAsset.get_otFreeresList().add(a);
    // }
    // unUpdateUp.get_arAssetList().add(sAbmArAsset);
    // }
    // unUpdateUp.get_sessioninfoList().addAll(inList.get(i).getValue().get_sessioninfoList());
    // MdbRoute mdbRoute = inList.get(i).getKey();
    // unUpdateUpInList.add(new Entry<MdbRoute, SAbmArUpdateIncrUp>(mdbRoute, unUpdateUp));
    // }
    // }
    // SalClient client = salClientFactory.getSalClient();
    // SAbmTransferUnFreezeRet unFreezeRet = new SAbmTransferUnFreezeRet();
    // List<SAbmTransferUnFreezeRet> outUnFreezeList = client.post(UpdateAbmTableIncr, unUpdateUpInList, unFreezeRet);
    // log.error("update fail, prepare to rollback the success data.");
    // for(SAbmTransferUnFreezeRet tmp : outUnFreezeList){
    // if(tmp.get_succFlag() != 0){
    // log.error("rollback the success data occur a failure!");
    // }
    // }
    // }
    // }

    // private void assignCreditList(Map<Long, SAbmArUpdateIncrUp> mapUp,
    // Map<Long, CsdlArrayList<SCredit>> tmpMap) {
    //
    // for(Map.Entry<Long, CsdlArrayList<SCredit>> entry : tmpMap.entrySet()){
    // long key = entry.getKey();
    // if (mapUp.containsKey(key)){
    // mapUp.get(key).get_arAssetList().get(0).get_creditList().addAll(entry.getValue());
    // } else {
    // SAbmArUpdateIncrUp oneFreezeUp = new SAbmArUpdateIncrUp();
    // SAbmArAsset oneAsset = new SAbmArAsset();
    // oneAsset.get_creditList().addAll(entry.getValue());
    // if(SdlUtil.getKeyType(key) == SdlUtil.OWNER_TEYP_ACCT){
    // oneAsset.set_acctId(SdlUtil.getKeyId(key));
    // }
    // oneFreezeUp.get_arAssetList().add(oneAsset);
    // mapUp.put(key, oneFreezeUp);
    // }
    // }
    // }
    //
    // private void assignPocketList(Map<Long, SAbmArUpdateIncrUp> mapUp,
    // Map<Long, CsdlArrayList<SPocket>> tmpMap) {
    //
    // for(Map.Entry<Long, CsdlArrayList<SPocket>> entry : tmpMap.entrySet()){
    // long key = entry.getKey();
    // if (mapUp.containsKey(key)){
    // mapUp.get(key).get_arAssetList().get(0).get_pocketList().addAll(entry.getValue());
    // } else {
    // SAbmArUpdateIncrUp oneFreezeUp = new SAbmArUpdateIncrUp();
    // SAbmArAsset oneAsset = new SAbmArAsset();
    // oneAsset.get_pocketList().addAll(entry.getValue());
    // if(SdlUtil.getKeyType(key) == SdlUtil.OWNER_TEYP_ACCT){
    // oneAsset.set_acctId(SdlUtil.getKeyId(key));
    // }
    // oneFreezeUp.get_arAssetList().add(oneAsset);
    // mapUp.put(key, oneFreezeUp);
    // }
    // }
    // }
    //
    // private void assignFreeResList(Map<Long, SAbmArUpdateIncrUp> mapUp,
    // Map<Long, CsdlArrayList<SFreeRes>> sFreeResTmpMap) {
    //
    // for(Map.Entry<Long, CsdlArrayList<SFreeRes>> entry : sFreeResTmpMap.entrySet()){
    // long key = entry.getKey();
    // if (mapUp.containsKey(key)){
    // mapUp.get(key).get_arAssetList().get(0).get_freeresList().addAll(entry.getValue());
    // } else {
    // SAbmArUpdateIncrUp oneFreezeUp = new SAbmArUpdateIncrUp();
    // SAbmArAsset oneAsset = new SAbmArAsset();
    // oneAsset.get_freeresList().addAll(entry.getValue());
    // if(SdlUtil.getKeyType(key) == SdlUtil.OWNER_TEYP_ACCT){
    // oneAsset.set_acctId(SdlUtil.getKeyId(key));
    // }
    // oneFreezeUp.get_arAssetList().add(oneAsset);
    // mapUp.put(key, oneFreezeUp);
    // }
    // }
    // }

    private void assignOtFreeResList(Map<Long, SAbmArUpdateIncrUp> mapUp, Map<Long, CsdlArrayList<SOtFreeRes>> sFreeResTmpMap)
    {

        for (Map.Entry<Long, CsdlArrayList<SOtFreeRes>> entry : sFreeResTmpMap.entrySet())
        {
            long key = entry.getKey();
            if (mapUp.containsKey(key))
            {
                mapUp.get(key).get_arAssetList().get(0).get_otFreeresList().addAll(entry.getValue());
            }
            else
            {
                SAbmArUpdateIncrUp oneFreezeUp = new SAbmArUpdateIncrUp();
                SAbmArAsset oneAsset = new SAbmArAsset();
                oneAsset.get_otFreeresList().addAll(entry.getValue());
                if (SdlUtil.getKeyType(key) == SdlUtil.OWNER_TEYP_ACCT)
                {
                    oneAsset.set_acctId(SdlUtil.getKeyId(key));
                }
                oneFreezeUp.get_arAssetList().add(oneAsset);
                mapUp.put(key, oneFreezeUp);
            }
        }
    }

}