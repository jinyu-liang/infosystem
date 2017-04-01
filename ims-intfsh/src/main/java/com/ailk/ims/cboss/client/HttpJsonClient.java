package com.ailk.ims.cboss.client;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import com.ailk.ims.business.cbossinterface.bo.PaymentRequest;
import com.ailk.ims.cboss.service.impl.Cboss2CrmSVImpl;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.imscnsh.entity.PostPaidPayReq;

public class HttpJsonClient {

    //private final static String SERVER_URL = "http://10.10.143.144:18086/n2ncboss/realtimecenter.up";//"http://10.10.143.144:18082/cboss/realtimecenter.up";
     
	private static HttpClient client = new HttpClient();
	private static ImsLogger imsLogger = new ImsLogger(HttpJsonClient.class);

	/**
	 * HTTP请求
	 */
	public static String httpPost(String serverUrl,String json,String bipCode,String activityCode) {

		if (CommonUtil.isEmpty(serverUrl)) {
			IMSUtil.throwBusiException(
					ErrorCodeDefine.NOT_CONFIG_IN_SYS_PARAMETER,
					"IM_SH_CBOSS_HTTP_JSON_SERVER_ADDR");
		}
		//链接超时:连接一个url的连接等待时间; 单位毫秒 
		client.getHttpConnectionManager().getParams().setConnectionTimeout(EnumCodeShDefine.CONNECTION_TIME_OUT);
		//读取超时:连接上一个url，获取response的返回等待时间; 单位毫秒
		client.getHttpConnectionManager().getParams().setSoTimeout(EnumCodeShDefine.SO_TIME_OUT);
		// 设置HTTP服务调用地址
		PostMethod method = new PostMethod(serverUrl);
		// 设置HTTP请求格式
		method.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		method.addRequestHeader("Connection", "keep-alive");
		// 传入HTTP请求参数
		method.addParameter("bipCode", bipCode);
		method.addParameter("activityCode", activityCode);
		method.addParameter("busiData", json);
		try {
			client.executeMethod(method);
			return method.getResponseBodyAsString();
		} catch (Exception e) {
			imsLogger.error(e, e);
			throw new IMSException(ErrorCodeDefine.INVOKE_CBOSS_METHOD_ERROR,e.getMessage());
		}
	}
	
	

	/**
	 * 拼装入参json报文
	 */
	public static String getJsonAsbytes(Map map) throws IMSException {
		StringBuffer sb = new StringBuffer();
		if (map != null && !map.isEmpty()) {
			String idType = map.get("IDType") + "";
			String idValue = map.get("IDValue") + "";
			String seq = map.get("Seq") + "";
			String actionTime = map.get("ActionTime") + "";
			String payed = map.get("Payed") + "";
			String discount = map.get("Discount") + "";
			String payway = map.get("Payway") + "";
			String cnlTyp = map.get("CnlTyp") + "";
			String payedMobile = map.get("PayedMobile") + "";

			sb.append("{\"PaymentReq\":");
			sb.append("{");
			sb.append("\"IDType\":\"").append(idType).append("\",");
			sb.append("\"IDValue\":\"").append(idValue).append("\",");
			sb.append("\"Seq\":\"").append(seq).append("\",");
			sb.append("\"ActionTime\":\"").append(actionTime).append("\",");
			sb.append("\"Payed\":").append(payed).append(",");
			sb.append("\"Discount\":").append(discount).append(",");
			sb.append("\"Payway\":\"").append(payway).append("\",");
			sb.append("\"CnlTyp\":\"").append(cnlTyp).append("\",");
			sb.append("\"PayedMobile\":\"").append(payedMobile).append("\"");
			sb.append("}");
			sb.append("}");
		} else {
			throw IMSUtil.throwBusiException(
					ErrorCodeDefine.COMMON_PARAM_ISNULL,
					"DoPaymentByPhoneIdBean.map");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
	    postPaidPay();
	}
	public static void payForPhone(){
	    Map map = new HashMap();
        String idType = "01";
        String idValue = "18888888888";
        String seq = "XXXXXXXX";
        String actionTime = "20110318093900";
        String payed = "90000";
        String discount = "99";
        String payway = "0";
        String cnlTyp = "100863";
        String payedMobile = "18888888889";
        map.put("IDType", idType);
        map.put("IDValue", idValue);
        map.put("Seq", seq);
        map.put("ActionTime", actionTime);
        map.put("Payed", payed);
        map.put("Discount", discount);
        map.put("Payway", payway);
        map.put("CnlTyp", cnlTyp);
        map.put("PayedMobile", payedMobile);
        try {
            Map outMap = Cboss2CrmSVImpl.payAtCurrentTimeByBillId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void postPaidPay(){
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
        System.out.println(CommonUtilSh.genCbossBusiJson("PaymentReq", req.getPaymentRequest()));
       // Map pay=Cboss2CrmSVImpl.postPaidPay("BIP1A160",CommonUtilSh.genCbossBusiJson("PaymentReq", req.getPaymentRequest()));
        System.out.println("结束调用........");
	}
}
