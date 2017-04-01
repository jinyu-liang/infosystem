package com.ailk.ims.httpjson.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.tools.Json;
import jef.tools.JsonUtil;
import com.ailk.ims.business.crminterface.bo.IBOCaPosRecValue;
import com.ailk.ims.business.crminterface.bo.MenuInfo;
import com.ailk.ims.business.crminterface.bo.RoleGrandInfo;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.handler.JsonSendHandler;
import com.ailk.ims.httpjson.request.PubInfo;
import com.ailk.ims.httpjson.request.RequestInfo;
import com.ailk.ims.httpjson.request.RequestInfoExt;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imscnsh.entity.AuthInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.ClassLogicInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.CustInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryBillInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryEchannelBillOrderInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryEchannelBillSendInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryGroupAccountInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryPhoneIdByImsiResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryServiceHistoryResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryTieTongInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.GroupAccountInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.GroupCustomerInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.ModifyPwdInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.OpInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.OpSpecRole;
import com.ailk.openbilling.persistence.imscnsh.entity.SBindActiveInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.SGuarantInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.SOperValuedCardStatusRst;
import com.ailk.openbilling.persistence.imscnsh.entity.SUserStopInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.SensitiveRecord;
import com.ailk.openbilling.persistence.imscnsh.entity.ShortInterRoamingInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.UpOrgInfo;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @Description:封装crm提供的方法
 * @author wangjt
 * @Date 2012-3-28
 */
public final class HttpJsonToCrmService{  
    
    private static ImsLogger imsLogger = new ImsLogger(HttpJsonToCrmService.class);

    /**
     * 根据操作员和权限编号，判断该操作员是否具备这个实体权限 xieqr 2012-5-7
     */
    public static String checkEntityPermission(String transactionId, String opId, Map<String, String> busiParams)
    {

        String busiCode = "C_QX_PDCZYQX";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String state = "";
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                state = retInfo.getAsString("State");
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }

        return state;
    }

    /**
     * 根据操作员编号查询所有菜单权限 xieqr 2012-5-7
     */
    public static List<MenuInfo> getFunctionsByOpId(String transactionId, String opId, Map<String, String> busiParams)
    {

        String busiCode = "C_QX_CXSYCDQX";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        List<MenuInfo> infoList = null;
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                if(retInfo.get("MenuInfo")!=null){
                    String menuInfo = retInfo.get("MenuInfo").toString();
                    infoList = Json.toList(menuInfo, MenuInfo.class);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return infoList;
    }

    /**
     * 查询用户硬捆绑活动信息 xieqr 2012-7-2
     */
    public static List<SBindActiveInfo> getBindActiveInfo(String transactionId, Map<String, String> busiParams)
    {

        String busiCode = "PT-SH-FS-OI1989";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        List<SBindActiveInfo> infoList = new ArrayList<SBindActiveInfo>();
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                JsonElement jsonElement = retInfo.get("bindList");
                if (jsonElement != null)
                {
                    String bindList = jsonElement.toString();
                    infoList = Json.toList(bindList, SBindActiveInfo.class);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return infoList;
    }

    /**
     * @description:根据权限编号查询拥有该权限的所有人员
     * @author caohm5
     * @date:2012-07-13
     */
    public static List<OpInfo> getPermissions(String transactionId, Map<String, String> busiParams)
    {
        String busiCode = "PT-SH-FS-OI2022";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        List<OpInfo> opInfoList = null;
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                JsonElement jsonElement = retInfo.get("seqsecopentpriv");
                if (jsonElement != null)
                {
                    String seqSecOpEntPriv = jsonElement.toString();
                    opInfoList = Json.toList(seqSecOpEntPriv, OpInfo.class);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return opInfoList;
    }
    /**
     * @description:根据权限编号查询拥有该权限的所有人员
     * @author caohm5
     * @date:2012-07-13
     */
    public static List<OpInfo> getPermissionsExt(String transactionId, Map<String, String> busiParams)
    {
        String busiCode = "L_GR_GETOPERINFO";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        List<OpInfo> opInfoList = null;
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                JsonElement jsonElement = retInfo.get("seqsecopentpriv");
                if (jsonElement != null)
                {
                    String seqSecOpEntPriv = jsonElement.toString();
                    opInfoList = Json.toList(seqSecOpEntPriv, OpInfo.class);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return opInfoList;
    }

    /**
     * @description:加强BOSS应用系统客户信息记录的优化需求
     * @author caohm5
     * @date:2012-07-26
     */
    public static void insertServiceRecord(String transactionId, Map<String, String> busiParams)
    {
        String busiCode = "PT-SH-FS-OI2019";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse;
        try
        {
            jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

            JsonObject jsonObj = Json.toJsonObject(jsonResponse);

            String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
            String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
            if (code == null ||   !code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
            {
                imsLogger.debug("########记录敏感信息出错      insertServiceRecord########");
                imsLogger.error("[", code, "]", msg);
                imsLogger.debug("###############################");
                // IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
            }
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }
    }

    /**
     * @description:插入敏感信息
     * @author wangyh3
     * @date:2012-08-15
     */
    public static List<SensitiveRecord> insertSensitiveRecord(String transactionId, Map<String, String> busiParams)
    {
        String busiCode = "PT-SH-FS-OI2030";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse;
        try
        {
            jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

            List<SensitiveRecord> recordList = null;

            JsonObject jsonObj = Json.toJsonObject(jsonResponse);

            String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
            String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
            if (code == null || !code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
            {
                imsLogger.debug("########记录敏感信息出错   insertSensitiveRecord########");
                imsLogger.debug("[", code + "]", msg);
                imsLogger.debug("###############################");
                // IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
                return null;
            }
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                JsonElement jsonElement = retInfo.get("FunAuditlist");
                if (jsonElement != null)
                {
                    String funAuditlist = jsonElement.toString();
                    recordList = Json.toList(funAuditlist, SensitiveRecord.class);
                }
            }

            return recordList;
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            return null;
        }
    }
    
    
    /**
     * @description:插入敏感信息
     * @author liudf
     * @date:2013-10-15
     */
    public static void insertSensitiveRecordNew(String transactionId, Map<String, String> busiParams){
        String busiCode = "PT-SH-FS-OI3201";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "06", busiCode, busiParams);
        String jsonResponse;
        try {
            jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

            JsonObject jsonObj = Json.toJsonObject(jsonResponse);

            String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
            String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
            if (code == null ||  !code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
            {
                imsLogger.debug("########记录敏感信息出错   insertSensitiveRecord########");
                imsLogger.debug("[", code + "]", msg);
                imsLogger.debug("###############################");
                IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
            }

        } catch (Exception e) {
            imsLogger.error(e, e);
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE);
        }
    }
    

    /**
     * @description: 有价卡状态更新接口 wangyh3 2012-8-9
     */
    public static List<SOperValuedCardStatusRst> operValuedCardStatus(String transactionId, Object req)
    {
        String busiCode = "PT-SH-FS-OI0632";
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("BusiParams", req);
        reqMap.put("BusiCode", busiCode);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Request", reqMap);
        map.put("PubInfo", genPubInfo(transactionId, "15", "2"));

        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(map), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code == null ||  !code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
        if (retInfo != null)
        {
            JsonElement jsonElement = retInfo.get("listOperResult");
            if (jsonElement != null)
            {
                String seqSecOpEntPriv = jsonElement.toString();
                return Json.toList(seqSecOpEntPriv, SOperValuedCardStatusRst.class);
            }
        }
        return null;
    }

    /**
     * @description:营业员给营业员插一条提醒记录
     * @author caohm5
     * @date:2012-09-20
     */
    public static void insertRemindTask(String transactionId, Map<String, String> busiParams)
    {
        String busiCode = "L_QD_TXYYYLSGZ";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse;
        try
        {
            jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            return;
        }
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code == null ||  !code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            imsLogger.debug("########营业员给营业员插一条提醒记录     insertRemindTask  ########");
            imsLogger.debug("[", code, "]", msg);
            imsLogger.debug("###############################");
        }
    }
    /**
     * 
     * @description:判断用户是否修改初始密码
     * @author caohm5
     * @date:2012-10-17
     */
    public static ModifyPwdInfo checkUserHasModifyPassword(String transactionId, Map<String, String> busiParams){


        String busiCode = "PT-SH-FS-OI2199";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        ModifyPwdInfo info=new ModifyPwdInfo();
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                info.setFlag(retInfo.getAsString("isModify"));
                info.setUser_pwd(retInfo.getAsString("userPwd"));
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }

        return info;
    
    }
    /**
     * @description:VC充值绿色通道搭建
     * @author caohm5
     * @date:2012-11-13
     */
    public static void builGreenChannel(String transactionId, Map<String, String> busiParams){
        String busiCode = "L_GR_VCJJTKJ";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse;
        try
        {
            jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            return;
        }
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code == null || !code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            imsLogger.debug("######## VC充值绿色通道搭建    builGreenChannel   ########");
            imsLogger.debug("[", code, "]", msg);
            imsLogger.debug("###############################");
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
    }
    /**
     * @description:从crm获取授权码
     * @author caohm5
     * @date:2012-11-23
     */
    public static List<AuthInfo> getAuthIdByPhoneIdFromCrm(String transactionId, Map<String, String[]> busiParams)
    {
        String busiCode = "L_GR_GETAUTHCODE";
        RequestInfoExt requestInfo = new RequestInfoExt(transactionId, "96", "2", busiCode, busiParams);
        String jsonResponse;
        
        try
        {
            jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            return null;
        }
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code == null ||  !code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
        if (retInfo != null)
        {
            JsonElement jsonElement = retInfo.get("returnList");
            if (jsonElement != null)
            {
                String seqSecOpEntPriv = jsonElement.toString();
                return Json.toList(seqSecOpEntPriv, AuthInfo.class);
            }
        }
        return null;
    }
    /**
     * @description:客服操作员登录，获取的是当前操作员所在的特殊角色
     * @author caohm5
     * @date:2012-11-29
     */
    public static OpSpecRole getOpSpecRole(String transactionId, Map<String, String> busiParams){
        
        String busiCode = "L_GR_GETSPID";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        OpSpecRole info=new OpSpecRole();
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                info.setSpec_role_id(retInfo.getAsString("playId"));
                info.setName(retInfo.getAsString("name"));
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return info;

    }
    /**
     * @description:营业操作员登录，获取的是当前操作员对应的上级组织
     * @author caohm5
     * @date:2012-11-29
     */
    public static UpOrgInfo getUpOrgInfo(String transactionId, Map<String, String> busiParams){
        String busiCode = "L_GR_GETPOIBORGID";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        UpOrgInfo info=new UpOrgInfo();
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                info.setName(retInfo.getAsString("fatherOrgName"));
                info.setUp_org_id(retInfo.getAsString("fatherOrgId"));
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return info;
    }
    
    /**
     * @description:从crm获取班组信息
     * @author zhangzj3
     * @date:2012-12-4
     */
    public static List<ClassLogicInfo> getClassLogicInfoFromCrm(String transactionId, Map<String, String> busiParams)
    {
        String busiCode = "L_GR_GETBZINFO";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "2", busiCode, busiParams);
        String jsonResponse;
        try
        {
            jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            return null;
        }
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code == null || !code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
        if (retInfo != null)
        {
            JsonElement jsonElement = retInfo.get("IBOSecPlayerValueList");
            if (jsonElement != null)
            {
                String seqSecOpEntPriv = jsonElement.toString();
                return Json.toList(seqSecOpEntPriv, ClassLogicInfo.class);
            }
        }
        return null;
    }
    
    /**
     * @description:修改银行代扣为现金支付方式接口
     * @author caohm5
     * @date:2012-12-12
     */
    public static String changePayChannelByCrm(String transactionId, Map<String, String> busiParams)
    {
        String busiCode = "L_GR_QXYHDK";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String soNbr = "";
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                soNbr = retInfo.getAsString("DoneCode");
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }

        return soNbr;
    
    }

    /**
     * @description:查询渠道类型
     * @author xieqr
     * @date:2012-12-30
     */
    public static String getChannelByOpId(String transactionId, Map<String, String> busiParams)
    {
        String busiCode = "L_QX_CXCZYQDLX";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String channelId = "";
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                channelId = retInfo.getAsString("ChannelId");
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }

        return channelId;
    
    }  
    
    /**
     * @description:通过企业编号到CRM获取账户信息
     * @author zhangzj3
     * @date:2013-2-25
     */
    public static CaAccount getAcctInfoFromCRM(String transactionId, Map<String, String> busiParams){
        String busiCode = "L_JT_CXJTZH";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        CaAccount account=new CaAccount();
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {   
                if(CommonUtil.isValid(retInfo.getAsString("acctId"))){
                    account.setAcctId(CommonUtil.string2Long(retInfo.getAsString("acctId")));
                }
                if(CommonUtil.isValid(retInfo.getAsString("acctType"))){
                    account.setCreditSignControl(CommonUtil.string2Integer(retInfo.getAsString("acctType")));
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return account;

    }
    
    /**
     * 刷卡记录查询的接口
     */
    public static List<IBOCaPosRecValue> getPosRecByOpId(String transactionId,Map<String, String> busiParams)
    {

        String busiCode = "L_GR_GETPOSRECORD";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        List<IBOCaPosRecValue> posRecList = null;
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                if(retInfo.get("IBOCaPosRecValue")!=null){
                    String posRecListString = retInfo.get("IBOCaPosRecValue").toString();
                    posRecList = Json.toList(posRecListString, IBOCaPosRecValue.class);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return posRecList;
    }
    /**
     * 短期国际漫游产品接口
     */
    public static List<ShortInterRoamingInfo> checkShortInterRoaming(String transactionId,Map<String, String> busiParams)
    {

        String busiCode = "PT-SH-FS-OI2282";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        List<ShortInterRoamingInfo> offinfoList = null;
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                if(retInfo.get("offinfo")!=null){
                    String offinfoString = retInfo.get("offinfo").toString();
                    offinfoList = Json.toList(offinfoString, ShortInterRoamingInfo.class);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return offinfoList;
    }
    
    /**
     *  服务-2013-2261 关于优化CRM系统的账单查询功能的需求
     *  luowq
     */
    public static Do_queryBillInfoResponse getBillInfoForCrm(String transactionId,Map<String, String> busiParams)
    {

        String busiCode = "PT-SH-FS-OI3081";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        Do_queryBillInfoResponse bean = new Do_queryBillInfoResponse();
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                if(retInfo.get("flag")!=null){
                    String offinfoString = retInfo.getAsString("flag");
                    bean.setFlag(offinfoString);
                }
                if(retInfo.get("offer_name")!=null){
                    String offinfoString = retInfo.getAsString("offer_name");
                    bean.setOffer_name(offinfoString);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return bean;
    }
    
    
    /**
     *  E渠道详单订购情况查询
     *  luowq
     */
    public static Do_queryEchannelBillOrderInfoResponse getEchannelBillOrderInfoForCrm(String transactionId,Map<String, String> busiParams)
    {

        String busiCode = "PT-SH-FS-OI3143";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        Do_queryEchannelBillOrderInfoResponse bean = new Do_queryEchannelBillOrderInfoResponse();
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                if(retInfo.get("flag")!=null){
                    String offinfoString = retInfo.getAsString("flag");
                    bean.setFlag(Boolean.valueOf(offinfoString) );
                }
                if(retInfo.get("e_mail")!=null){
                    String offinfoString = retInfo.getAsString("e_mail");
                    bean.setEmail(offinfoString);
                }
                if(retInfo.get("billType")!=null){
                    String offinfoString = retInfo.getAsString("billType");
                    bean.setBill_type(offinfoString);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return bean;
    }
    
    /**
     *  E渠道电子帐单详单发送情况查询
     *  luowq
     */
    public static Do_queryEchannelBillSendInfoResponse getEchannelBillInfoSendForCrm(String transactionId,Map<String, String> busiParams)
    {

        String busiCode = "PT-SH-FS-OI3144";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        Do_queryEchannelBillSendInfoResponse bean = new Do_queryEchannelBillSendInfoResponse();
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {   
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            
            if (retInfo != null)
            {
                if(retInfo.get("flag")!=null){
                    String offinfoString = retInfo.getAsString("flag");
                    bean.setFlag(Boolean.valueOf(offinfoString) );
                }
                if(retInfo.get("sendDate")!=null){ 
                    String offinfoString = retInfo.getAsString("sendDate");
                    bean.setSend_date(DateUtil.getFormatDate(offinfoString,DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                }
                if(retInfo.get("sendAddr")!=null){
                    String offinfoString = retInfo.getAsString("sendAddr");
                    bean.setSend_addr(offinfoString);
                }
                if(retInfo.get("billType")!=null){
                    String offinfoString = retInfo.getAsString("billType");
                    bean.setBill_type(offinfoString);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return bean;
    }
    
    /**
     *  查询该号码是否是铁通8+11类型的号码，正式对应关系，状态为U
     *  luowq
     */
    public static Do_queryTieTongInfoResponse getTieTongInfoForCrm(String transactionId,Map<String, String> busiParams)
    {

        String busiCode = "PT-SH-FS-OI3237";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        Do_queryTieTongInfoResponse bean = new Do_queryTieTongInfoResponse();
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                if(retInfo.get("flag")!=null){
                    String offinfoString = retInfo.getAsString("flag");
                    bean.setFlag(Integer.valueOf(offinfoString)   );
                }
                if(retInfo.get("it_bill_id")!=null){
                    String offinfoString = retInfo.getAsString("it_bill_id");
                    bean.setIt_bill_id(offinfoString);
                    
                }
                if(retInfo.get("bill_id")!=null){
                    String offinfoString = retInfo.getAsString("bill_id");
                    bean.setBill_id(offinfoString);
                }
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return bean;
    }
    
    
    /**
     *  写入CRM库中：写一条副号码订购历史到CRM业务历史记录表
     *  luowq
     */
    public static Do_queryServiceHistoryResponse getServiceHistoryForCrm(String transactionId,Map<String, String> busiParams)
    {

        String busiCode = "PT-SH-FS-OI3272";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        Do_queryServiceHistoryResponse bean = new Do_queryServiceHistoryResponse();
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {   
            bean.setFlag(0);
        }else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return bean;
    }
    
    private static PubInfo genPubInfo(String transactionId, String interfaceId, String interfaceType)
    {
        PubInfo pubInfo = new PubInfo();

        pubInfo.setTransactionId(transactionId);
        pubInfo.setInterfaceId(interfaceId);
        pubInfo.setInterfaceType(interfaceType);
        pubInfo.setOpId("2");
        pubInfo.setCountyCode("571001");
        pubInfo.setOrgId("2");
        pubInfo.setClientIP("10.70.128.132");
        pubInfo.setTransactionTime(DateUtil.formatDate(new Date(), "yyyyMMdd HHmmss"));
        pubInfo.setRegionCode("571");

        return pubInfo;
    }
    
    /**
     * 查询集团信息
     * @author zhangzhj 2013-7-19
     * @param transactionId
     * @param busiParams
     * @return
     */
    public static GroupCustomerInfo queryGroupInfo(String transactionId,Map<String, String> busiParams){
        //先查询出集团客户编号 
        JsonObject jsonObject = invokeCrmInterfaceToJsonObject("PT-SH-FS-OI3377",transactionId,busiParams);
        String custId = null;
        String msg = "找不到对应的集团客户编号";
        String code = "259999";
        if(jsonObject != null){
            if(jsonObject.getAsString("cust_id") != null){
                custId = jsonObject.getAsString("cust_id");
            }
            if(custId == null){
                IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg); 
            }
            Map<String, String> temParam = new HashMap<String, String>();
            temParam.put("cust_id", custId);
            return invokeCrmInterfaceToClass("PT-SH-FS-OI3369",transactionId,temParam,GroupCustomerInfo.class);
        }else{
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg); 
        }
        return null;
    }
    
    /**
     * 查询集团联系信息（客户经理信息）
     * @author wujb  2013-7-29
     * @param transactionId
     * @param busiParams
     * @return
     */
    public static List<CustInfo> queryGroupContactInfo(String transactionId,Map<String, String> busiParams){
         List<CustInfo> result = new ArrayList<CustInfo>();
         String busiCode = "PT-SH-FS-OI3399";
         RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
         String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
         JsonObject jsonObj = Json.toJsonObject(jsonResponse);

         String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
         String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
         CustInfo cust = new CustInfo();
         if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
         {   
             JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
             
             if (retInfo != null)
             {
            	 JsonObject custInfo = null;
            	 if(!retInfo.get("CustInfo").isJsonNull()){
            		 custInfo = retInfo.getAsJsonObject("CustInfo");
            	 }
            	 if(custInfo != null){
            		 if(custInfo.getAsString("ManagerId") != null){
            			 cust.setManagerId(custInfo.getAsString("ManagerId"));
            		 }
            		 if(custInfo.getAsString("CityId") != null){
            			 cust.setCityId(custInfo.getAsString("CityId"));
            		 }
            		 if(custInfo.getAsString("Occupation") != null){
            			 cust.setOccupation(custInfo.getAsString("Occupation"));
            		 }
            		 if(custInfo.getAsString("ManagerName") != null){
            			 cust.setManagerName(custInfo.getAsString("ManagerName"));
            		 }
            		 if(custInfo.getAsString("Gender") != null){
            			 cust.setGender(custInfo.getAsString("Gender"));
            		 }
            		 if(custInfo.getAsString("ManagerEmail") != null){
            			 cust.setManagerEmail(custInfo.getAsString("ManagerEmail"));
            		 }
            		 if(custInfo.getAsString("ManagerPhone") != null){
            			 cust.setManagerPhone(custInfo.getAsString("ManagerPhone"));
            		 }
            		 if(custInfo.getAsString("CustName") != null){
            			 cust.setCustName(custInfo.getAsString("CustName"));
            		 }
            		 result.add(cust);
            	 }
             }
         }
         else
         {
             IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
         }
         return result;
    }
    
    /**
     * @根据用户编号查询担保人信息 @ xieqr 2012-5-7
     * 2017-07-22 zhangzj3改为公用方法
     */
    public static SGuarantInfo getGuarantInfo(String transactionId, Map<String, String> busiParams){   
        return invokeCrmInterfaceToClass("PT-SH-FS-OI1988",transactionId,busiParams,SGuarantInfo.class);
    }
    
    /**
     * 根据IMSI号码查询手机号码 add by wujb 2013-8-20
     * @param transactionId
     * @param busiParams
     * @return
     */
    public static Do_queryPhoneIdByImsiResponse getPhoneIdByImsi(String transactionId, Map<String, String> busiParams){
    	JsonObject jsonObject = invokeCrmInterfaceToJsonObject("PT-SH-FS-OI3436",transactionId,busiParams);
    	Do_queryPhoneIdByImsiResponse respn = new Do_queryPhoneIdByImsiResponse();
        if(jsonObject != null){
            if(jsonObject.getAsString("phoneNum") != null){
                respn.setPhoneNum(jsonObject.getAsString("phoneNum"));
            }
        }
        return respn;
    }
    
    /**
    * 查询集团账户信息
    * @author zhangzhj 2013-7-19
    * @param transactionId
    * @param busiParams
    * @return
    */
   public static Do_queryGroupAccountInfoResponse queryGroupAccountInfo(String transactionId,Map<String, String> busiParams){
        JsonObject jsonObject = invokeCrmInterfaceToJsonObject("PT-SH-FS-OI3397",transactionId,busiParams);
        Do_queryGroupAccountInfoResponse respn = new Do_queryGroupAccountInfoResponse();
        if(jsonObject != null){
            if(jsonObject.getAsString("flag") != null){
                respn.setFlag(Integer.valueOf(jsonObject.getAsString("flag")));
            }
            if(jsonObject.get("accountInfo") != null){
                List<GroupAccountInfo> groupAccountList = Json.toList(jsonObject.get("accountInfo").toString(),GroupAccountInfo.class);
                respn.setGroupAcctList(groupAccountList);
            }
        }
        return respn;
   }
   /**
    * 根据操作员查询所有实体权限信息 xieqr 2012-5-7
    * 2017-07-22 zhangzj3改为公用方法
    */
   public static List<RoleGrandInfo> getEntitysByOpId(String transactionId, String opId, Map<String, String> busiParams){  
       return invokeCrmInterfaceToList("C_QX_CXCZYQX",transactionId,busiParams,"RoleGrandInfo",RoleGrandInfo.class);
   }
   
   
    /**
     * 调用CRM接口，并转为信管相应的接口。List类型
     * @author zhangzhj 2013-7-20
     * @param <T>
     * @param busiCode  调用方法编码
     * @param transactionId
     * @param busiParams 参数
     * @param pnodeName  取参数的名称
     * @param clazz      转换的类名
     * @return
     */
    private static <T>List<T> invokeCrmInterfaceToList(String busiCode, String transactionId, Map<String, String> busiParams, String pnodeName,Class<T> clazz){
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE)){
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null){
                String str = retInfo.get(pnodeName).toString();
                List<T> list = Json.toList(str,clazz);
                return list;
            }
        }else{
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return null;
    }
    /**
     * 调用CRM方法，并把对应的结果转为相应的实体
     * @author zhangzhj 2013-7-22
     * @param <T>
     * @param busiCode CRM方法编码
     * @param transactionId 
     * @param busiParams 参数
     * @param clazz   转化类
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <T>T invokeCrmInterfaceToClass(String busiCode, String transactionId, Map<String, String> busiParams,Class<T> clazz){
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE)){
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null){
                return  (T)Json.to(clazz,retInfo);
            }
        }else{
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return null;
    }
    private HttpJsonToCrmService(){
        
    }
    /**
     * 接受CRM返回结构，让后续调用者按自己需求进行分解
     * @author zhangzhj 2013-7-24
     * @param busiCode    方法编号
     * @param transactionId
     * @param busiParams 参数
     * @return
     */
    private static JsonObject invokeCrmInterfaceToJsonObject(String busiCode, String transactionId, Map<String, String> busiParams){
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "22", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE)){
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null){
                return  retInfo;
            }
        }else{
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return null;
    }
    
    /**
     * 查询用户激活信息和停机原因
     * @param transactionId
     * @param busiParams
     * @return
     * SUserStopInfo
     */
    public static SUserStopInfo getUserStopInfo(String transactionId, Object req)
    {

        String busiCode = "PT-SH-FS-OI0411";
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("BusiParams", req);
        reqMap.put("BusiCode", busiCode);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Request", reqMap);
        map.put("PubInfo", genAnotherPubInfo(transactionId, "12", "06"));
        
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(map), busiCode, transactionId);

        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        SUserStopInfo sUserStopInfo = new SUserStopInfo();
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
                sUserStopInfo = Json.to(SUserStopInfo.class,retInfo);
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }
        return sUserStopInfo;
    }
    
    private static PubInfo genAnotherPubInfo(String transactionId, String interfaceId, String interfaceType)
    {
        PubInfo pubInfo = new PubInfo();

        pubInfo.setTransactionId(transactionId);
        pubInfo.setInterfaceId(interfaceId);
        pubInfo.setInterfaceType(interfaceType);
        pubInfo.setOpId("999990077");
        pubInfo.setCountyCode("");
        pubInfo.setOrgId("");
        pubInfo.setClientIP("10.70.128.132");
        pubInfo.setTransactionTime(DateUtil.formatDate(new Date(), "yyyyMMdd HHmmss"));
        pubInfo.setRegionCode("210");

        return pubInfo;
    }
    
    /**
     * 查询手机号码是否归属于指定营业厅接口
     * @param transactionId
     * @param busiParams
     * @return
     * String
     */
    public static String checkPhoneOrganize(String transactionId, Map<String, String> busiParams)
    {
        String busiCode = "PT-SH-FS-OI3470";
        RequestInfo requestInfo = new RequestInfo(transactionId, "96", "06", busiCode, busiParams);
        String jsonResponse = JsonSendHandler.sendRequest(JsonUtil.serialize(requestInfo), busiCode, transactionId);
        JsonObject jsonObj = Json.toJsonObject(jsonResponse);

        String returnCode = "";
        String code = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Code");
        String msg = jsonObj.getAsJsonObject("Response").getAsJsonObject("ErrorInfo").getAsString("Message");
        if (code != null && code.equals(EnumCodeDefine.CRM_SERVICE_RIGHT_CODE))
        {
            JsonObject retInfo = jsonObj.getAsJsonObject("Response").getAsJsonObject("RetInfo");
            if (retInfo != null)
            {
            	returnCode = retInfo.getAsString("returnCode");
            }
        }
        else
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CRM_SERVICE_ERROR_CODE, code, msg);
        }

        return returnCode;
    
    }
    
}
