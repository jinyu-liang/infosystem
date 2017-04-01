package com.ailk.ims.universeplatform;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import jef.tools.XMLUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.imscnsh.entity.CommitRefundOut;
import com.ailk.openbilling.persistence.imscnsh.entity.CommitRefundReq;
import com.ailk.openbilling.persistence.imscnsh.entity.ECPayChannelInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.GetPPDV2OrderInfoOut;
import com.ailk.openbilling.persistence.imscnsh.entity.GetPPDV2OrderInfoReq;
import com.ailk.openbilling.persistence.imscnsh.entity.PPDV2SubOrderInfoOut;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryTransRecOut;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryTransRecReq;
import com.ailk.openbilling.persistence.imscnsh.entity.Transfer;
import com.ailk.openbilling.persistence.imscnsh.entity.TransferOut;

public final class IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client {

    private static ImsLogger imsLogger = new ImsLogger(
            IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client.class);

    private static final QName SERVICE_NAME = new QName(
            "http://soap.integration.ecpay.ailk.com", "IServiceMgmtSoapService");
    private static IServiceMgmtSoapService ss = null;
    private static IServiceMgmtSoapServicePortType port = null;

    private IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client() {
    }

    /**
     * 交易请求提交接口
     */
    public static TransferOut invokeCommitTransInfoCUP(Transfer transer) throws Exception {
        String serviceIn = beanToxml(transer,false);
        String serviceReturn = invokeUniversePlatMethod(serviceIn);
        TransferOut out = getTransferOutFromXML(serviceReturn);
        return out;
    }
    
    /**
     * 交易请求提交接口 commitTransInfoUnBind_CUP  非绑定扣款接口
     */
    public static TransferOut invokecommitTransInfoUnBindCUP(Transfer transer) throws Exception {
        String serviceIn = beanToxml(transer,true);
        String serviceReturn = invokeUniversePlatMethod(serviceIn);
        TransferOut out = getTransferOutFromXML(serviceReturn);
        return out;
    }
    
    /**
     * 交易记录查询（统一支付平台）
     */
    public static List<QueryTransRecOut> invokeGetTransInfoECPay(QueryTransRecReq req) throws Exception {
        String serviceIn = beanToXml(req);
        String serviceReturn = invokeUniversePlatMethod(serviceIn);
        List<QueryTransRecOut> outList = getQueryTransRecOutListFromXML(serviceReturn);
        return outList;
    }
    /**
     * 退费交易提交接口
     */
    public static CommitRefundOut invokeCommitRefundCUP(CommitRefundReq req) throws Exception {
        String serviceIn = beanToXml(req);
        String serviceReturn = invokeUniversePlatMethod(serviceIn);
        CommitRefundOut out = getCommitRefundOutFromXML(serviceReturn);
        return out;
    }
    /**
     * 手机支付本地订单信息查询
     */
    public static GetPPDV2OrderInfoOut invokeGetPPDV2OrderInfo(GetPPDV2OrderInfoReq req) throws Exception {
        String serviceIn = beanToXml(req);
        String serviceReturn = invokeUniversePlatMethod(serviceIn);
        GetPPDV2OrderInfoOut out =null;
        try{
        	out = getGetPPDV2OrderInfoFromXML(serviceReturn);
        }catch(Exception e){
        	imsLogger.error(e);
        }
        return out;
    }
    /**
     * 手机支付本地订单信息查询接口出参
     */
    private static GetPPDV2OrderInfoOut getGetPPDV2OrderInfoFromXML(String xmlString)
            throws Exception {
        if (CommonUtil.isEmpty(xmlString)) {
            return null;
        }
        GetPPDV2OrderInfoOut out = new GetPPDV2OrderInfoOut();
        Document doc=XMLUtils.loadDocumentByString(xmlString);
        for(Element element:XMLUtils.toElementList(XMLUtils.getElementsByTagNames(doc.getDocumentElement(), "return"))){
            Map<String,String> map=XMLUtils.getAttributesMap(element, true);
            imsLogger.dumpJsonObject("############### 手机支付本地订单信息查询接口出参 ##################", map);
            out.setAmount((String)map.get("Amount"));
            out.setBank_abbr((String)map.get("BankAbbr"));
            out.setBank_id((String)map.get("BankId"));
            out.setCurrency((String)map.get("Currency"));
            out.setExpand((String)map.get("Expand"));
            out.setMer_ac_date((String)map.get("MerAcDate"));
            out.setOrder_date((String)map.get("OrderDate"));
            out.setOrder_id((String)map.get("OrderId"));
            out.setPay_date((String)map.get("PayDate"));
            out.setPay_plat_id((String)map.get("PayPlatId"));
            out.setPay_status((String)map.get("PayStatus"));
            out.setPay_type((String)map.get("PayType"));
            out.setUser_token((String)map.get("UserToken"));
            break;
        }
        List<PPDV2SubOrderInfoOut> subOrderList = new ArrayList<PPDV2SubOrderInfoOut>();
        for(Element element:XMLUtils.toElementList(XMLUtils.getElementsByTagNames(doc.getDocumentElement(), "Order"))){
            Map<String,String> map=XMLUtils.getAttributesMap(element, true);
            PPDV2SubOrderInfoOut subOrder = new PPDV2SubOrderInfoOut();
            subOrder.setSub_order_id((String)map.get("SubOrderId"));
            subOrder.setBill_id((String)map.get("BillId"));
            subOrder.setReal_fee((String)map.get("RealFee"));
            subOrder.setBill_month((String)map.get("BillMonth"));
            subOrderList.add(subOrder);
        }
        out.setSub_order_info(subOrderList);
        return out;
    }
    private static String beanToXml(GetPPDV2OrderInfoReq req) {
        StringBuffer xmlString = new StringBuffer();
        xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlString.append("<file>");
        xmlString.append("<head>");
        xmlString.append("<Interfaceid>300000</Interfaceid>");
        xmlString.append("<Interfacename>getPPDV2OrderInfo</Interfacename>");
        xmlString.append("<Infotype>select</Infotype>");
        xmlString.append("</head>");
        
        xmlString.append("<body>");
        
        xmlString.append("<OrderId>");
        if(CommonUtil.isValid(req.getOrder_id())){
            xmlString.append(req.getOrder_id());
        }
        xmlString.append("</OrderId>");
        
        xmlString.append("<OrderDate>");
        if(CommonUtil.isValid(req.getOrder_date())){
            xmlString.append(req.getOrder_date());
        }
        xmlString.append("</OrderDate>");
        
        xmlString.append("<PayPlatId>");
        if(CommonUtil.isValid(req.getPay_plat_id())){
            xmlString.append(req.getPay_plat_id());
        }
        xmlString.append("</PayPlatId>");
        
        xmlString.append("<BusiCode>");
        if(CommonUtil.isValid(req.getBusi_code())){
            xmlString.append(req.getBusi_code());
        }
        xmlString.append("</BusiCode>");
        
        xmlString = addEcpayChannelInfo(xmlString,req.getECPayChannelInfo());
        
        xmlString.append("</body>");
        xmlString.append("</file>");
        
        return xmlString.toString();
    }

    /**
     * 交易请求提交接口入参
     * @param req
     * @param flag  true:非绑定请求提交接口;false:交易请求提交接口
     * @return
     * String
     */
    private static String beanToxml(Transfer req,boolean flag) {
        StringBuffer xmlString = new StringBuffer();
        xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlString.append("<file>");
        xmlString.append("<head>");
        xmlString.append("<Interfaceid>200000</Interfaceid>");
        if(flag){
        	xmlString.append("<Interfacename>commitTransInfoUnBind_CUP</Interfacename>");
        }else{
        	xmlString.append("<Interfacename>commitTransInfo_CUP</Interfacename>");
        }
        xmlString.append("<Infotype>commit</Infotype>");
        xmlString.append("</head>");
        
        xmlString.append("<body>");
        
        xmlString.append("<BankId>");
        if(CommonUtil.isValid(req.getBank_id())){
            xmlString.append(req.getBank_id());
        }
        xmlString.append("</BankId>");
        
        xmlString.append("<PlatId>");
        if(CommonUtil.isValid(req.getPlat_id())){
            xmlString.append(req.getPlat_id());
        }
        xmlString.append("</PlatId>");
        
        //非绑定请求接口没有此字段
        if(!flag){
        	xmlString.append("<UserId>");
        	if(CommonUtil.isValid(req.getUser_id())){
        		xmlString.append(req.getUser_id());
        	}
        	xmlString.append("</UserId>");
        }
        
        xmlString.append("<ChName>");
        if(CommonUtil.isValid(req.getCh_name())){
            xmlString.append(req.getCh_name());
        }
        xmlString.append("</ChName>");
        
        xmlString.append("<CardType>");
        if(CommonUtil.isValid(req.getCard_type())){
            xmlString.append(req.getCard_type());
        }
        xmlString.append("</CardType>");
        
        xmlString.append("<IdCard>");
        if(CommonUtil.isValid(req.getId_card())){
            xmlString.append(req.getId_card());
        }
        xmlString.append("</IdCard>");
        
        xmlString.append("<PanType>");
        if(CommonUtil.isValid(req.getPan_type())){
            xmlString.append(req.getPan_type());
        }
        xmlString.append("</PanType>");
        
        xmlString.append("<Pan>");
        if(CommonUtil.isValid(req.getPan())){
            xmlString.append(req.getPan());
        }
        xmlString.append("</Pan>");
        
        xmlString.append("<EntryCode>");
        if(CommonUtil.isValid(req.getEntry_code())){
            xmlString.append(req.getEntry_code());
        }
        xmlString.append("</EntryCode>");
        
        xmlString.append("<TranAmt>");
        if(CommonUtil.isValid(req.getTran_amt())){
            xmlString.append(req.getTran_amt());
        }
        xmlString.append("</TranAmt>");
        
        //非绑定请求接口没有此字段
        if(!flag){
        	xmlString.append("<Charge>");
        	if(CommonUtil.isValid(req.getCharge())){
        		xmlString.append(req.getCharge());
        	}
        	xmlString.append("</Charge>");
        }
        
        xmlString.append("<CurType>");
        if(CommonUtil.isValid(req.getCur_type())){
            xmlString.append(req.getCur_type());
        }
        xmlString.append("</CurType>");
        
        xmlString.append("<CVN2>");
        if(CommonUtil.isValid(req.getCvn2())){
            xmlString.append(req.getCvn2());
        }
        xmlString.append("</CVN2>");
        
        xmlString.append("<ValidDate>");
        if(CommonUtil.isValid(req.getValid_date())){
            xmlString.append(req.getValid_date());
        }
        xmlString.append("</ValidDate>");
        
        //非绑定请求接口没有此字段
        if(!flag){
        	xmlString.append("<PaymentDeays>");
        	if(CommonUtil.isValid(req.getPayment_deays())){
        		xmlString.append(req.getPayment_deays());
        	}
        	xmlString.append("</PaymentDeays>");
        	
        	xmlString.append("<SignType>");
        	if(CommonUtil.isValid(req.getSign_type())){
        		xmlString.append(req.getSign_type());
        	}
        	xmlString.append("</SignType>");
        }
        
        xmlString.append("<PayerTel>");
        if(CommonUtil.isValid(req.getPayer_tel())){
            xmlString.append(req.getPayer_tel());
        }
        xmlString.append("</PayerTel>");
        
        xmlString.append("<IssBin>");
        if(CommonUtil.isValid(req.getIss_bin())){
            xmlString.append(req.getIss_bin());
        }
        xmlString.append("</IssBin>");
        
        xmlString.append("<ChannelId>");
        if(CommonUtil.isValid(req.getChannel_id())){
            xmlString.append(req.getChannel_id());
        }
        xmlString.append("</ChannelId>");
        
        xmlString.append("<ChannelPriv>");
        if(CommonUtil.isValid(req.getChannel_priv())){
            xmlString.append(req.getChannel_priv());
        }
        xmlString.append("</ChannelPriv>");
        
        xmlString.append("<Expand>");
        if(CommonUtil.isValid(req.getExpand())){
            xmlString.append(req.getExpand());
        }
        xmlString.append("</Expand>");
        
        xmlString = addEcpayChannelInfo(xmlString,req.getECPayChannelInfo());
        
        xmlString.append("</body>");
        xmlString.append("</file>");
        
        return xmlString.toString();
    }

    /**
     * 交易请求提交接口出参
     */
    private static TransferOut getTransferOutFromXML(String xmlString)
            throws Exception {
        if (CommonUtil.isEmpty(xmlString)) {
            return null;
        }
        TransferOut out = new TransferOut();
        Document doc=XMLUtils.loadDocumentByString(xmlString);
        //只有一个
        Element element=XMLUtils.toElementList(XMLUtils.getElementsByTagNames(doc.getDocumentElement(), "ECPayReturnValue")).get(0);
        Map<String,String> map=XMLUtils.getAttributesMap(element, true);
        imsLogger.dumpJsonObject("############### 交易请求提交接口出参 ##################", map);
        out.setResp_code(map.get("RespCode"));
        out.setResp_desc(map.get("RespDesc"));
        out.setMer_order_num(map.get("MerOrderNum"));
        out.setChannel_priv(map.get("ChannelPriv"));
        out.setConver_rate(map.get("ConverRate"));
        out.setSetl_amt(map.get("SetlAmt"));
        out.setSetl_currency(map.get("SetlCurrency"));
        out.setSettle_date(map.get("SettleDate"));
        out.setTran_date_time(map.get("TranDateTime"));
        return out;
    }
    /**
     * 交易记录查询（统一支付平台）接口入参
     */
    private static String beanToXml(QueryTransRecReq req){
        StringBuffer xmlString = new StringBuffer();
        xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlString.append("<file>");
        
        xmlString.append("<head>");
        xmlString.append("<Interfaceid>200000</Interfaceid>");
        xmlString.append("<Interfacename>getTransInfo_ECPay</Interfacename>");
        xmlString.append("<Infotype>select</Infotype>");
        xmlString.append("</head>");
        
        xmlString.append("<body>");
        
        xmlString.append("<BankId>");
        if(CommonUtil.isValid(req.getBank_id())){
            xmlString.append(req.getBank_id());
        }
        xmlString.append("</BankId>");
        
        xmlString.append("<PlatId>");
        if(CommonUtil.isValid(req.getPlat_id())){
            xmlString.append(req.getPlat_id());
        }
        xmlString.append("</PlatId>");
        
        xmlString.append("<UserId>");
        if(CommonUtil.isValid(req.getUser_id())){
            xmlString.append(req.getUser_id());
        }
        xmlString.append("</UserId>");
        
        xmlString.append("<PayerTel>");
        if(CommonUtil.isValid(req.getPayer_tel())){
            xmlString.append(req.getPayer_tel());
        }
        xmlString.append("</PayerTel>");
        
        xmlString.append("<StartDate>");
        if(CommonUtil.isValid(req.getStart_date())){
            xmlString.append(req.getStart_date());
        }
        xmlString.append("</StartDate>");
        
        xmlString.append("<EndDate>");
        if(CommonUtil.isValid(req.getEnd_date())){
            xmlString.append(req.getEnd_date());
        }
        xmlString.append("</EndDate>");
        
        xmlString.append("<OrgTranStatus>");
        if(CommonUtil.isValid(req.getOrg_tran_status())){
            xmlString.append(req.getOrg_tran_status());
        }
        xmlString.append("</OrgTranStatus>");
        
        xmlString.append("<ChannelId>");
        if(CommonUtil.isValid(req.getChannel_id())){
            xmlString.append(req.getChannel_id());
        }
        xmlString.append("</ChannelId>");
        
        xmlString.append("<ChannelPriv>");
        if(CommonUtil.isValid(req.getChannel_priv())){
            xmlString.append(req.getChannel_priv());
        }
        xmlString.append("</ChannelPriv>");
        
        xmlString.append("<Expand>");
        if(CommonUtil.isValid(req.getExpand())){
            xmlString.append(req.getExpand());
        }
        xmlString.append("</Expand>");
        
        xmlString = addEcpayChannelInfo(xmlString, req.getECPayChannelInfo());
        
        xmlString.append("</body>");
        xmlString.append("</file>");
        return xmlString.toString();
    }
    /**
     * 交易记录查询（统一支付平台）接口出参
     */
    private static List<QueryTransRecOut> getQueryTransRecOutListFromXML(String xmlString)
            throws Exception {
        if (CommonUtil.isEmpty(xmlString)) {
            return null;
        }
        List<QueryTransRecOut> outList = new ArrayList<QueryTransRecOut>();
        Document doc=XMLUtils.loadDocumentByString(xmlString);
        for(Element element:XMLUtils.toElementList(XMLUtils.getElementsByTagNames(doc.getDocumentElement(), "ECPayTransModel"))){
            Map<String,String> map=XMLUtils.getAttributesMap(element, true);
            imsLogger.dumpJsonObject("############### 交易请求提交接口出参 ##################", map);
            QueryTransRecOut out=new QueryTransRecOut();
            out.setResp_code((String)map.get("RespCode"));
            out.setPlat_id((String)map.get("PlatId"));
            out.setTran_amt((String)map.get("TranAmt"));
            out.setTran_status((String)map.get("OrgTranStatus"));
            out.setResp_desc((String)map.get("RespDesc"));
            out.setPayer_tel((String)map.get("PayerTel"));
            out.setOrder_id((String)map.get("MerOrderNum"));
            out.setTran_date((String)map.get("TranDateTime"));
            outList.add(out);
        }
        return outList;
    }
    /**
     * 退费交易提交接口入参
     */
    private static String beanToXml(CommitRefundReq req){
        StringBuffer xmlString = new StringBuffer();
        xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlString.append("<file>");
        xmlString.append("<head>");
        xmlString.append("<Interfaceid>200000</Interfaceid>");
        xmlString.append("<Interfacename>commitRefund_CUP</Interfacename>");
        xmlString.append("<Infotype>commit</Infotype>");
        xmlString.append("</head>");
        
        xmlString.append("<body>");
        
        xmlString.append("<BankId>");
        if(CommonUtil.isValid(req.getBank_id())){
            xmlString.append(req.getBank_id());
        }
        xmlString.append("</BankId>");
        
        xmlString.append("<PlatId>");
        if(CommonUtil.isValid(req.getPlat_id())){
            xmlString.append(req.getPlat_id());
        }
        xmlString.append("</PlatId>");
        
        xmlString.append("<UserId>");
        if(CommonUtil.isValid(req.getUser_id())){
            xmlString.append(req.getUser_id());
        }
        xmlString.append("</UserId>");
        
        xmlString.append("<TranType>");
        if(CommonUtil.isValid(req.getTran_type())){
            xmlString.append(req.getTran_type());
        }
        xmlString.append("</TranType>");
        
        xmlString.append("<OrgOrderNum>");
        if(CommonUtil.isValid(req.getOrg_order_num())){
            xmlString.append(req.getOrg_order_num());
        }
        xmlString.append("</OrgOrderNum>");
        
        xmlString.append("<OrgTranDateTime>");
        if(CommonUtil.isValid(req.getOrg_tran_date())){
            xmlString.append(req.getOrg_tran_date());
        }
        xmlString.append("</OrgTranDateTime>");
        
        xmlString.append("<OrgTranAmt>");
        if(CommonUtil.isValid(req.getOrg_tran_amt())){
            xmlString.append(req.getOrg_tran_amt());
        }
        xmlString.append("</OrgTranAmt>");
        
        xmlString.append("<TranAmt>");
        if(CommonUtil.isValid(req.getTran_amt())){
            xmlString.append(req.getTran_amt());
        }
        xmlString.append("</TranAmt>");
        
        xmlString.append("<Charge>");
        if(CommonUtil.isValid(req.getCharge())){
            xmlString.append(req.getCharge());
        }
        xmlString.append("</Charge>");
        
        xmlString.append("<ReturnType>");
        if(CommonUtil.isValid(req.getReturn_type())){
            xmlString.append(req.getReturn_type());
        }
        xmlString.append("</ReturnType>");
        
        xmlString.append("<ChName>");
        if(CommonUtil.isValid(req.getCh_name())){
            xmlString.append(req.getCh_name());
        }
        xmlString.append("</ChName>");
        
        xmlString.append("<PayerTel>");
        if(CommonUtil.isValid(req.getPayer_tel())){
            xmlString.append(req.getPayer_tel());
        }
        xmlString.append("</PayerTel>");
        
        xmlString.append("<CardType>");
        if(CommonUtil.isValid(req.getCard_type())){
            xmlString.append(req.getCard_type());
        }
        xmlString.append("</CardType>");
        
        xmlString.append("<IdCard>");
        if(CommonUtil.isValid(req.getId_card())){
            xmlString.append(req.getId_card());
        }
        xmlString.append("</IdCard>");
        
        xmlString.append("<PanType>");
        if(CommonUtil.isValid(req.getPan_type())){
            xmlString.append(req.getPan_type());
        }
        xmlString.append("</PanType>");
        
        xmlString.append("<Pan>");
        if(CommonUtil.isValid(req.getPan())){
            xmlString.append(req.getPan());
        }
        xmlString.append("</Pan>");
        
        xmlString.append("<CVN2>");
        if(CommonUtil.isValid(req.getCvn2())){
            xmlString.append(req.getCvn2());
        }
        xmlString.append("</CVN2>");
        
        xmlString.append("<ValidDate>");
        if(CommonUtil.isValid(req.getValid_date())){
            xmlString.append(req.getValid_date());
        }
        xmlString.append("</ValidDate>");
        
        xmlString.append("<ChannelId>");
        if(CommonUtil.isValid(req.getChannel_id())){
            xmlString.append(req.getChannel_id());
        }
        xmlString.append("</ChannelId>");
        
        xmlString.append("<ChannelPriv>");
        if(CommonUtil.isValid(req.getChannel_priv())){
            xmlString.append(req.getChannel_priv());
        }
        xmlString.append("</ChannelPriv>");
        
        xmlString.append("<Expand>");
        if(CommonUtil.isValid(req.getExpand())){
            xmlString.append(req.getExpand());
        }
        xmlString.append("</Expand>");
        
        xmlString = addEcpayChannelInfo(xmlString, req.getECPayChannelInfo());
        
        xmlString.append("</body>");
        xmlString.append("</file>");
        return xmlString.toString();
    }
    /**
     * 退费交易提交接口出参
     */
    private static CommitRefundOut getCommitRefundOutFromXML(String xmlString)
            throws Exception {
        if (CommonUtil.isEmpty(xmlString)) {
            return null;
        }
        CommitRefundOut out = new CommitRefundOut();
        Document doc=XMLUtils.loadDocumentByString(xmlString);
        //返回只有一个
        Element element=XMLUtils.toElementList(XMLUtils.getElementsByTagNames(doc.getDocumentElement(), "ECPayReturnValue")).get(0);
        Map<String,String> map=XMLUtils.getAttributesMap(element, true);
        imsLogger.dumpJsonObject("############### 交易请求提交接口出参 ##################", map);
        out.setRespn_code((String)map.get("RespCode"));
        out.setRespn_desc((String)map.get("RespDesc"));
        out.setChannel_priv((String)map.get("ChannelPriv"));
        out.setOrder_num((String)map.get("MerOrderNum"));
        out.setTran_date((String)map.get("TranDateTime"));
        out.setSettle_date((String)map.get("SettleDate"));
        out.setSettle_amt((String)map.get("SetlAmt"));
        out.setSettle_currency((String)map.get("SetlCurrency"));
        out.setConver_rate((String)map.get("ConverRate"));
        return out;
    }
    /**
     * 组装公共结构ECPayChannelInfo
     */
   public static StringBuffer addEcpayChannelInfo(StringBuffer xmlString,ECPayChannelInfo info){
       xmlString.append("<ECPayChannelInfo>");
       xmlString.append("<ChannelIP>");
       if(info != null && CommonUtil.isValid(info.getChannel_ip())){
           xmlString.append(info.getChannel_ip());
       }
       xmlString.append("</ChannelIP>");
       
       xmlString.append("<ChannelId>");
       if(info != null && CommonUtil.isValid(info.getChannel_id())){
           xmlString.append(info.getChannel_id());
       }
       xmlString.append("</ChannelId>");
       
       xmlString.append("<ChannelName>");
       if(info != null && CommonUtil.isValid(info.getChannel_name())){
           xmlString.append(info.getChannel_name());
       }
       xmlString.append("</ChannelName>");
       
       xmlString.append("<CallMethodInfo>");
       if(info != null && CommonUtil.isValid(info.getCall_method_info())){
           xmlString.append(info.getCall_method_info());
       }
       xmlString.append("</CallMethodInfo>");
       
       xmlString.append("<CallPageInfo>");
       if(info != null && CommonUtil.isValid(info.getCall_page_info())){
           xmlString.append(info.getCall_page_info());
       }
       xmlString.append("</CallPageInfo>");
       
       xmlString.append("</ECPayChannelInfo>");
       return xmlString;
   }
   /**
    * 
    * @author zhangzhj 2013-7-31
    * @param serviceIn 请求入参串
    * @return
    */
   public static String invokeUniversePlatMethod(String serviceIn){
       if (port == null) {
           URL wsdlURL = IServiceMgmtSoapService.WSDL_LOCATION;
           ss = new IServiceMgmtSoapService(wsdlURL, SERVICE_NAME);
           port = ss.getIServiceMgmtSoapServiceHttpPort();
       }
       imsLogger.dumpJsonObject("*********统一支付平台wsdl地址：",IServiceMgmtSoapService.WSDL_LOCATION);
       System.out.println("serviceIn::::"+serviceIn);
       imsLogger.dumpJsonObject("*********统一支付平台xml入参：", serviceIn);
       long start = System.currentTimeMillis();
       String serviceReturn = port.service(serviceIn);
       imsLogger.info(start, "##############CALL UNIVERSE COMMITTRANSINFO_CUP################");
       imsLogger.dumpJsonObject("*********统一支付平台xml出参：",serviceReturn);
       return serviceReturn;
   }

}
