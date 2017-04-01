package com.ailk.httpjson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.tools.Json;
import com.ailk.ims.business.cbossinterface.bo.BankPayRsp;
import com.ailk.ims.business.cbossinterface.bo.PaymentRequest;
import com.ailk.ims.business.cbossinterface.bo.PaymentRsp;
import com.ailk.ims.business.cbossinterface.bo.SignInfoRsp;
import com.ailk.ims.business.crminterface.bo.MenuInfo;
import com.ailk.ims.business.crminterface.bo.RoleGrandInfo;
import com.ailk.ims.httpjson.service.HttpJsonToCbossService;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.PrintUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imscnsh.entity.AuthInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.BankPayReq;
import com.ailk.openbilling.persistence.imscnsh.entity.ClassLogicInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.OpInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.PostPaidPayReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SBindActiveInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.SGuarantInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.SignInfoReq;

/**
 * @Description:模拟具体业务中，需要调用crm服务的情况
 * @author wangjt
 * @Date 2012-3-28
 */
public class TestSend2Crm
{
    public static void main(String[] args)
    {
        // 设置请求参数到map中，key value都必须是String型
        // Map<String, String> busiParams = new HashMap<String, String>();
        // busiParams.put("ServiceNum", "13812345678");
        // busiParams.put("中文key", "中文value");
        // 调用具体crm的服务
        // String doneCode = "AX11111111111111";
        // ResponseInfo responseInfo = HttpJsonService.serviceNumberOne(doneCode, busiParams);
        // responseInfo.display();
         //String opId = "8888"; // 1066
        // String entId="10000031";
        // String privId="10000282";
         //getEntitysByOpId(opId);
        // getGuarantInfo(40010000002660L);
        // getBindActiveInfo(40010000003936l,"2011-01-01 01:01:01","2099-12-12 01:01:01");
        // insertServiceRecord();
        // queryPermission();
         //getMenusByOpId(opId);
        // checkEntityPermission(opId,entId,privId);
        // query_abcBind();
         //doBankPay();
//        insertRemind();
    	//getAuthInfoByPhoneIdsFromCrm();
       // getAcctInfoByCRM();
        //postPaidPay();
        getGroupInfo();
    }

    public static void getMenusByOpId(String opId)
    {
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("OpId", opId);
        String doneCode = "AX11111111111111";

        List<MenuInfo> menuList = HttpJsonToCrmService.getFunctionsByOpId(doneCode, opId, busiParams);
        PrintUtil.print("MenuInfo", menuList);
    }

    public static void checkEntityPermission(String opId, String entId, String privId)
    {
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("OpId", opId);
        busiParams.put("EntId", entId);
        busiParams.put("PrivId", privId);
        String doneCode = "AX11111111111111";
        String state = HttpJsonToCrmService.checkEntityPermission(doneCode, opId, busiParams);
        System.out.println(state);
    }

    public static void getEntitysByOpId(String opId)
    {
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("OpId", opId);
        String doneCode = "AX11111111111111";

        List<RoleGrandInfo> infoList = HttpJsonToCrmService.getEntitysByOpId(doneCode, opId, busiParams);
        PrintUtil.print("RoleGrandInfo", infoList);

    }

    public static void getGuarantInfo(Long resourceId)
    {
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("resource_id", resourceId.toString());
        String doneCode = "AX11111111111111";
        SGuarantInfo info = HttpJsonToCrmService.getGuarantInfo(doneCode, busiParams);
        PrintUtil.print("RoleGrandInfo", info);
    }

    public static void getBindActiveInfo(Long resourceId, String beginDate, String endDate)
    {
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("resource_id", resourceId.toString());
        busiParams.put("begin_date", beginDate);
        busiParams.put("end_date", endDate);
        String doneCode = "AX11111111111111";
        List<SBindActiveInfo> info = HttpJsonToCrmService.getBindActiveInfo(doneCode, busiParams);
        PrintUtil.print("SBindActiveInfo", info);
    }

    public static void insertServiceRecord()
    {
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("qryType", "1");
        busiParams.put("qryNum", "1888888888");
        busiParams.put("domanId", "1");
        busiParams.put("serviceStatus", "2");
        busiParams.put("serviceSource", "0");
        busiParams.put("Remark", "中国1111");

        String doneCode = "AX11111111111111";
        System.out.println("开始调用........");
        HttpJsonToCrmService.insertServiceRecord(doneCode, busiParams);
        System.out.println("结束调用........");
    }

    public static void queryPermission()
    {
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("entId", "60007003");
        busiParams.put("privId", "60007004");

        String doneCode = "AX11111111111111";
        System.out.println("开始调用........");
        List<OpInfo> opInfo = HttpJsonToCrmService.getPermissions(doneCode, busiParams);
        PrintUtil.print("opInfo", opInfo);
        System.out.println("结束调用........");
    }

    public static void insertSensitiveRecord()
    {

        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("entId", "60007003");
        busiParams.put("privId", "60007004");

        String doneCode = "AX11111111111111";
        System.out.println("开始调用........");
        List<OpInfo> opInfo = HttpJsonToCrmService.getPermissions(doneCode, busiParams);
        PrintUtil.print("opInfo", opInfo);
        System.out.println("结束调用........");
    }

    /**
     * 查询农行绑定关系
     * xieqr 2012-9-6
     */
    public static void query_abcBind()
    {
        SignInfoReq req = new SignInfoReq();
        req.setMerchantID("chinamobilesh");
        req.setMerchantTranDate("20120906");
        req.setMerchantTranTime("143700");
        req.setMerchantTranLogNo("12345671");
        req.setMobileNo("18217314818");
        req.setContractNo("18217314818031110031");
        req.setTranCode("ABQY");
        SignInfoRsp signInfoRsp = HttpJsonToCbossService.queryBingRelation(CommonUtilSh.genCbossBusiJson("SignInfoReq", req));
        System.out.println(Json.serialize(signInfoRsp));
    }
    
    
    public static void doBankPay(){
        BankPayReq req = new BankPayReq();
        req.setBankCode("ABC");
        req.setSeqNo("533209");
        req.setTranDate("20120913144633");
        req.setAuthId("18721990249031109301");
        req.setMobileNo("18721990249");
        req.setFeeAmount("50.00");
        req.setCurrType("RMB");
        req.setPassword("123321");
        BankPayRsp aaa = HttpJsonToCbossService.doBankPay(CommonUtilSh.genCbossBusiJson("BankPayReq", req));
    }
    public static void insertRemind()
    {
        Map<String, String> busiParams = new HashMap<String, String>();
        
    	busiParams.put("StaffCode", "12");
    	busiParams.put("SenderCode", "2");
    	busiParams.put("RemainGrade", "2");
    	busiParams.put("RemindTitle", "临时有事；帮忙挡着!");
    	busiParams.put("RemindContent", "吃香喝辣");
    	busiParams.put("RemindTime", "20120920 155900");
    	busiParams.put("RemindId", "3");
    	
        String doneCode = "AX121208A990111";
        System.out.println("开始调用........");
        HttpJsonToCrmService.insertRemindTask(doneCode, busiParams);
        System.out.println("结束调用........");
    }
    public static List<AuthInfo> getAuthInfoByPhoneIdsFromCrm(){
    	String doneCode = "AX20111220000018";
        System.out.println("开始调用........");
        Map<String, String[]> busiParams = new HashMap<String, String[]>();
        String[] ids = {"13818115245","1255262627"};
        busiParams.put("phoneIdList",ids);
        List<AuthInfo> authInfoList=HttpJsonToCrmService.getAuthIdByPhoneIdFromCrm(doneCode, busiParams);
        System.out.println("结束调用........");
        System.out.print("######授权码########:"+authInfoList);
        return authInfoList;
    }
    
    public static List<ClassLogicInfo> getClassLogicInfoFromCrm(){
        String doneCode = "AX20111220000018";
        System.out.println("开始调用........");
        Map<String, String> busiParams = new HashMap<String, String>();
        List<ClassLogicInfo> authInfoList=HttpJsonToCrmService.getClassLogicInfoFromCrm(doneCode, busiParams);
        System.out.println("结束调用........");
        System.out.print("######授权码########:"+authInfoList);
        return authInfoList;
    }
    public static CaAccount getAcctInfoByCRM(){
        String doneCode = "AX20111220000018";
        System.out.println("开始调用........");
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("ServiceNum","13901268220");
        CaAccount account=HttpJsonToCrmService.getAcctInfoFromCRM(doneCode, busiParams);
        System.out.println("结束调用........");
        System.out.print("######号码:########:"+account.getAcctId()+"类型：：："+account.getCreditSignControl());
        return account;
    }
    public static PaymentRsp postPaidPay(){
        PostPaidPayReq req = new PostPaidPayReq(); 
        req.setBip_code("BIP1A160");
        PaymentRequest request = new PaymentRequest();
        request.setActionDate("20130312");
        request.setCnlTyp("00");
        request.setIDType("01");
        request.setIDValue("15821644516");
        request.setPayed(500l);
        request.setPayedType("02");
        request.setSubID("95555123");
        request.setTransactionID("755201303120909091231234567890");
        req.setPaymentRequest(request);
        System.out.println("开始调用.......;.");
        //PaymentRsp pay=HttpJsonService.postPaidPay("BIP1A160",CommonUtilSh.genCbossBusiJson("PaymentReq", req.getPaymentRequest()));
        System.out.println("结束调用........");
        return null;
    }
    public static void getGroupInfo(){
        String doneCode = "AX20111220000018";
        System.out.println("开始调用........");
        
       
    }
}
