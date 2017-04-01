package com.ailk.httpjson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jef.tools.Json;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.aspectj.util.FileUtil;

import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.imscnsh.entity.Bill;
import com.ailk.openbilling.persistence.imscnsh.entity.BillExt;
import com.ailk.openbilling.persistence.imscnsh.entity.BillExtIn;
import com.ailk.openbilling.persistence.imscnsh.entity.BillExtOut;
import com.ailk.openbilling.persistence.imscnsh.entity.CaBillItem;
import com.ailk.openbilling.persistence.imscnsh.entity.CaBillProd;
import com.ailk.openbilling.persistence.imscnsh.entity.DerateRecordIn;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_depositResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryBillExtResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryDerateRecordResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryOweBillSumResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryPocketExtResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryPromoAllotInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryUserHasBadDebtResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_unDepositResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.FormatBill;
import com.ailk.openbilling.persistence.imscnsh.entity.OweFeeBillIn;
import com.ailk.openbilling.persistence.imscnsh.entity.PocketExtDetail;
import com.ailk.openbilling.persistence.imscnsh.entity.PocketExtIn;
import com.ailk.openbilling.persistence.imscnsh.entity.PocketExtOut;
import com.ailk.openbilling.persistence.imscnsh.entity.SDoDepositReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SDoUnDepositReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SPromoAllotInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.SQueryPromoAllotInfoReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SQueryUserHasBadDebtReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

public class TestUtil
{
    public static void main(String args[])
    {
//        Do_queryBillExtResponse resp = getBillExtJsonStr();
//         Map map = getBillExtParamList();
//         getOweBillExtParamList();
//         Do_queryOweBillSumResponse resp= getOweBillSumJsonSrt();
        // getDerateRecordParamList();
        // getDerateRecordJsonStr();
        // getCheckBadDebtParamList();
        // getCheckBadDebtJsonStr();
//        getQueryPocketExtParamList();
//        Do_queryPocketExtResponse  resp =getQueryPocketExtJsonStr();
        // getQueryPromoAllotInfoJsonStr();
        // getQueryPromoAllotInfoParamList();
//         Do_depositResponse resp = getDoDepositJsonStr();
//         getDoDepositParamList();
        // getDoUnDepositParamList();
        // getDoUnDepositJsonStr();

//        ObjectMapper mapper = new ObjectMapper();
//        try
//        {   
//            System.out.println(mapper.writeValueAsString(resp));
//            mapper.writeValue(new File("D:\\Json2JsonSchema\\OweBillSumOut.json"), resp);
//            
//            JsonSchema schema = mapper.generateJsonSchema(Do_queryOweBillSumResponse.class);
//            System.out.println(schema.toString());
//        }
//        catch (JsonMappingException e)
//        {
//            
//            e.printStackTrace();
//        }
//        catch (JsonGenerationException e)
//        {
//           
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//           
//            e.printStackTrace();
//        }
    	String[] strs=new String[]{"201206","201207","201208","201209","201210","201211"};
    	for(int i=0;i<50;i++){
    		for(int j=0;j<6;j++){
    			String tmp = "update ad.Ca_Freeres_Month_"+i+"_"+strs[j]+"  a set item_name =(select name from  pd.pm_asset_item where asset_item_id=a.item_code) where exists (select  1 from  pd.pm_asset_item where asset_item_id=a.item_code and name!=a.item_name);";
    			System.out.println(tmp);
    			System.out.println("commit;");
    		}
    	}
//    	long start = System.currentTimeMillis();
//    	System.out.println("aaa");
//    	System.out.println(start);
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	System.out.println("bbb");
//    	start = System.currentTimeMillis();
//    	System.out.println(start);
//    	System.out.println("ccc");
//    	start = System.currentTimeMillis();
//    	System.out.println(start);


//    	BusiUtil.init("CN_SH");
//    	
//    	BaseNode methodNode = BusiUtil.getServiceNode("com.ailk.openbilling.service.imscnsh.CN_SHMgntServiceImpl");
//    	List<BaseNode> list = methodNode.getChildren();
//    	for(BaseNode node:list){
//    		node.getTagName();
//    		System.out.print(node.getAttribute("busi_code")+"         ");
//    		System.out.println(node.getTagName());
//    	}
//		System.out.println("ertyu");
    	
    	
    }
    public static  boolean checkSuccess(String outputJson){
    	
    	BaseResponse resp = Json.to(BaseResponse.class, outputJson);
    	return true;
//    	Long resultCode = resp.getErrorMsg().getResult_code();
//    	if(resultCode == 0){
//    		return true;
//    	}else{
//    		return false;
//    	}
    }
    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public static void httpPostWithJSON(String url, String json) throws Exception
    {
        // 将JSON进行UTF-8编码,以便传输中文
        String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

        StringEntity se = new StringEntity(encoderJson);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(se);
        httpClient.execute(httpPost);
    }

    public static String receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException
    {

        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null)
        {
            sb.append(line);
        }

        // 将资料解码
        String reqBody = sb.toString();
        return URLDecoder.decode(reqBody, HTTP.UTF_8);
    }

    public static String printObj(Object imsObj)
    {
        Class clz = imsObj.getClass();
        // Field[] fields = clz.getDeclaredFields();
        Field[] fields = ClassUtil.getAllFields(clz);
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < fields.length; i++)
        {
            Field field = fields[i];
            if (i == 0)
            {
                bf.append("{\r\n");
            }
            String imsFieldName = field.getName();
            Object imsFieldValue = ConvertUtil.getFieldValue(imsObj, imsFieldName);

            if (field.getType().getName().equals("java.util.List"))
            {
                List imsList = (List) imsFieldValue;
                if (imsList == null || imsList.isEmpty())
                {
                    continue;
                }
                bf.append("\"" + imsFieldName + "\":[");
                for (int j = 0; j < imsList.size(); j++)
                {
                    Object imsObjTemp = imsList.get(j);
                    String objStr = printObj(imsObjTemp);

                    bf.append(objStr);
                    if (j != imsList.size() - 1)
                    {
                        bf.append(",");
                    }
                }

                bf.append("],\r\n");

            }
            else if (IComplexEntity.class.isAssignableFrom(field.getType()))
            {
                if (imsFieldValue == null)
                {
                    continue;
                }
                bf.append("\"" + imsFieldName + "\":");
                String objStr = printObj(imsFieldValue);
                bf.append(objStr);
                bf.append("},\r\n");
            }
            else
            {
                if (i != fields.length - 1)
                {
                    if (imsFieldValue != null)
                    {
                        bf.append("\"" + imsFieldName + "\":\"" + imsFieldValue + "\",\r\n");
                    }
                    else
                    {
                        bf.append("\"" + imsFieldName + "\":\"" + "\",\r\n");
                    }
                }
                else
                {
                    if (imsFieldValue != null)
                    {
                        bf.append("\"" + imsFieldName + "\":\"" + imsFieldValue + "\"\r\n");
                        bf.append("}");
                    }
                    else
                    {
                        bf.append("\"" + imsFieldName + "\":\"" + "\"\r\n");
                        bf.append("}");
                    }
                }

            }

            // if (i == fields.length - 1)
            // {
            // bf = new StringBuffer(bf.substring(0, bf.length() - 3));
            // bf.append("\r\n}");
            // }
        }
        return bf.toString();
    }

    public static Do_queryBillExtResponse getBillExtJsonStr()
    {
        Do_queryBillExtResponse resp = new Do_queryBillExtResponse();
        BillExtOut out = new BillExtOut();
        out.setBill_fee(111L);
        out.setLate_fee(1L);
        out.setPpy_fee(2L);
        out.setReal_time_fee(123L);
        out.setUnpay_fee(333L);
        List<BillExt> listExt = new ArrayList<BillExt>();
        BillExt billExt = new BillExt();
        billExt.setAcct_id(123L);
        billExt.setBegin_date("2012-01-02 12:12:12");
        billExt.setBill_fee(12344L);
        billExt.setBill_month(201201);
        billExt.setBilling_type(1);
        billExt.setDue_date("2012-01-01 12:12:12");
        billExt.setEnd_date("2012-01-01 12:12:12");
        billExt.setIs_real(Short.valueOf("1"));
        billExt.setLate_fee(1232L);
        billExt.setMeasure_id(23232);
        billExt.setPhone_id("12323232323");
        billExt.setPpy_fee(3333L);
        billExt.setResource_id(99999L);
        billExt.setSts(31);
        billExt.setSts_date("2012-01-01 12:12:12");
        billExt.setUnpay_fee(888L);


        List<CaBillItem> billItemList = new ArrayList<CaBillItem>();
        List<CaBillProd> billProdList = new ArrayList<CaBillProd>();
        CaBillItem billItem = new CaBillItem();
        billItem.setAdjust_fee(111L);
        billItem.setBill_fee(333L);
        billItem.setCrc_code(3333L);
        billItem.setWoff_fee(2222L);

        CaBillProd billProd = new CaBillProd();
        billProd.setBill_month(201202);
        billProd.setBill_no(3883838L);
        billProd.setFee_type(33);
        billProd.setPrice_id(33333);
        billProd.setUnit(33L);
        billProdList.add(billProd);
        // billProdList.add(billProd);

        billItem.setListBillProds(billProdList);
        billItemList.add(billItem);

        billExt.setListBillItems(billItemList);

        List<Bill> billList = new ArrayList<Bill>();
        Bill bill = new Bill();
        bill.setAcct_id(1234567L);
        bill.setBegin_date("2012-01-01 12:12:12");
        bill.setBill_fee(33333L);
        bill.setBill_month(201202);
        bill.setSts(12);
        bill.setDue_date("2012-01-01 12:12:12");
        bill.setBill_no(233232L);
        bill.setListBillItems(billItemList);
        billList.add(bill);
        billExt.setListBills(billList);

        listExt.add(billExt);
        List<FormatBill> formatBillList = new ArrayList<FormatBill>();

        FormatBill formatBill = new FormatBill();
        formatBill.setAcct_id(12233L);
        formatBill.setBegin_date("2012-01-01 12:12:12");
        formatBill.setBill_month(201202);
        formatBill.setEnd_date("2012-01-01 12:12:12");

//        List<BillNode> billNodeList = new ArrayList<BillNode>();
//        BillNode billNode = new BillNode();
//        billNode.setBill_fee(122L);
//        billNode.setBill_no(233333L);
//        billNode.setItem_code(122);
//        billNode.setItem_node_level(1);
//        billNode.setItem_node_name("ddddd");
//        billNode.setNode_id(3);
//        billNode.setParent_node_id(54);
//        billNode.setPpy_fee(444L);
//        billNode.setSort_id(333);
//        billNodeList.add(billNode);
////        formatBill.setBillNodeList(billNodeList);
//        formatBillList.add(formatBill);

        out.setHisFormatBillList(formatBillList);
        out.setRealFormatBillList(formatBillList);
        // listExt.add(billExt);
        out.setHistoryBillList(listExt);
        out.setRealTimeBillList(listExt);
        resp.setBillExtOut(out);
        resp.setErrorMsg(getErrorMsg());
//        String aaa = printObj(resp);
        return resp;
    }

    public static Map getBillExtParamList()
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7016);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short) 1);
        sOper.setRegion_code((short) 1);
        sOper.setCounty_code((short) 1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short) 1);
        sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);

        BillExtIn billExtIn = new BillExtIn();
        billExtIn.setAcct_id(186840L);
//        billExtIn.setGroup_id(1l);
        billExtIn.setCust_id(1l);
        billExtIn.setResource_id(1l);
        billExtIn.setPhone_id("1");
        billExtIn.setBill_no(1l);
        billExtIn.setPay_sts("1");
        billExtIn.setBill_sts("1");
        billExtIn.setBegin_month("201203");
        billExtIn.setEnd_month("201203");
        billExtIn.setRet_type((short) 1);
        billExtIn.setReal_type((short) 0);
        billExtIn.setLate_fee_type((short) 0);
//        billExtIn.setQuery_type((short) 0);
        billExtIn.setIs_format(false);
//        billExtIn.setFormat_id(12);

        Map mp = new HashMap();
        mp.put("sOper", sOper);
        mp.put("billExtIn", billExtIn);
        System.out.println(Json.serialize(mp));
        return mp;
    }

    public static void getOweBillExtParamList()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7022);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short) 1);
        sOper.setRegion_code((short) 1);
        sOper.setCounty_code((short) 1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short) 1);
        sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);

        OweFeeBillIn qweFeeBillIn = new OweFeeBillIn();
        qweFeeBillIn.setAcct_id(186840L);
        qweFeeBillIn.setCust_id(11L);
        qweFeeBillIn.setResource_id(1l);
        qweFeeBillIn.setPhone_id("1");
        qweFeeBillIn.setRet_type((short) 1);
        qweFeeBillIn.setReal_type((short) 1);
        qweFeeBillIn.setPay_sts("1");
        qweFeeBillIn.setIs_format(false);
//        qweFeeBillIn.setFormat_id(1);
        
        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("qweFeeBillIn", qweFeeBillIn);
        System.out.println(Json.serialize(map));
//        System.out.println("{\"sOper\":");
//        System.out.println(printObj(sOper));
//        System.out.println(",\"qweFeeBillIn\":");
//        System.out.println(printObj(qweFeeBillIn));
//        System.out.println("}");

    }

    public static Do_queryOweBillSumResponse getOweBillSumJsonSrt()
    {
        Do_queryOweBillSumResponse resp = new Do_queryOweBillSumResponse();
        resp.setPaySts1Sum(123L);
        resp.setPaySts4Sum(434L);
        resp.setErrorMsg(getErrorMsg());
//        String aaa = printObj(resp);
//        System.out.println(aaa);
        return resp;
    }

    /**
     * 4.32 查询减免滞纳金记录参数 jkedy 2012-5-6
     */
    public static void getDerateRecordParamList()
    {

        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7045);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short) 1);
        sOper.setRegion_code((short) 1);
        sOper.setCounty_code((short) 1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short) 1);
        sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);

        DerateRecordIn in = new DerateRecordIn();
        in.setAcct_id(186840L);
        in.setBegin_month("201201");
        in.setEnd_month("201205");

        System.out.println("{\"sOper\":");
        System.out.println(printObj(sOper));
        System.out.println(",\"derateRecordIn\":");
        System.out.println(printObj(in));
        System.out.println("}");

    }

    public static void getDerateRecordJsonStr()
    {
        Do_queryDerateRecordResponse resp = new Do_queryDerateRecordResponse();
        resp.setErrorMsg(getErrorMsg());
        List<com.ailk.openbilling.persistence.imscnsh.entity.DerateRecord> imsList = new ArrayList<com.ailk.openbilling.persistence.imscnsh.entity.DerateRecord>();
        com.ailk.openbilling.persistence.imscnsh.entity.DerateRecord record = new com.ailk.openbilling.persistence.imscnsh.entity.DerateRecord();
        record.setDerate_fee(123L);
        record.setDerate_month("2012-02-02 12:12:12");
        record.setOp_id("20001");
        imsList.add(record);
        resp.setDerateRecords(imsList);
        String aaa = printObj(resp);
        System.out.println(aaa);
    }

    public static void getCheckBadDebtParamList()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7027);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short) 1);
        sOper.setRegion_code((short) 1);
        sOper.setCounty_code((short) 1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short) 1);
        sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);

        SQueryUserHasBadDebtReq req = new SQueryUserHasBadDebtReq();
        List<Long> accts = new ArrayList<Long>();
        accts.add(186840L);
        accts.add(12345L);
        req.setAcctList(accts);

        System.out.println("{\"sOper\":");
        System.out.println(printObj(sOper));
        System.out.println(",\"customerReq\":");
        System.out.println(printObj(req));
        System.out.println("}");

    }

    public static void getCheckBadDebtJsonStr()
    {
        Do_queryUserHasBadDebtResponse resp = new Do_queryUserHasBadDebtResponse();
        resp.setErrorMsg(getErrorMsg());
        resp.setFlag(true);
        String aaa = printObj(resp);
        System.out.println(aaa);
    }

    public static void getQueryPocketExtParamList()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7012);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short) 1);
        sOper.setRegion_code((short) 1);
        sOper.setCounty_code((short) 1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short) 1);
        sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);

        PocketExtIn pocketExtIn = new PocketExtIn();
        pocketExtIn.setQry_type("1");
        pocketExtIn.setAcct_id(186840L);
        pocketExtIn.setResource_id(10957L);
        pocketExtIn.setPocket_items("1");
        pocketExtIn.setPhone_id("1");
        
        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("pocketExtIn", pocketExtIn);
        System.out.println(Json.serialize(map));
        
//        System.out.println("{\"sOper\":");
//        System.out.println(printObj(sOper));
//        System.out.println(",\"pocketExtIn\":");
//        System.out.println(printObj(pocketExtIn));
//        System.out.println("}");
        
        
        
    }

    public static Do_queryPocketExtResponse getQueryPocketExtJsonStr()
    {
        Do_queryPocketExtResponse resp = new Do_queryPocketExtResponse();
        resp.setErrorMsg(getErrorMsg());

        PocketExtOut out = new PocketExtOut();
        out.setAcct_id(186840L);
        out.setAmount(123L);
        out.setExpire_amout(11L);
        out.setReal_fee(1L);
        out.setUnpay_fee(1L);
        out.setUnvalid_amount(1L);
        out.setUsable_balance(120L);

        List<PocketExtDetail> listPocketExtDetail = new ArrayList<PocketExtDetail>();

        PocketExtDetail detail = new PocketExtDetail();
        detail.setAcct_id(186840L);
        detail.setBilling_type(1);
        detail.setExpire_date("2013-01-01 12:12:12");
        detail.setSts(1);
        detail.setUnpay_fee(1L);
        detail.setReal_fee(1L);
        listPocketExtDetail.add(detail);
        out.setListPocketExtDetail(listPocketExtDetail);
        out.setListDepositDetail(listPocketExtDetail);
        resp.setPocketExtOut(out);

//        String aaa = printObj(resp);
//        System.out.println(aaa);
        return resp;
    }

    public static void getQueryPromoAllotInfoJsonStr()
    {
        Do_queryPromoAllotInfoResponse resp = new Do_queryPromoAllotInfoResponse();
        resp.setErrorMsg(getErrorMsg());
        List<SPromoAllotInfo> allotInfos = new ArrayList<SPromoAllotInfo>();
        SPromoAllotInfo info = new SPromoAllotInfo();
        info.setAcct_id(186840L);
        info.setAllot_date("2013-01-01 12:12:12");
        info.setAllot_fee(111L);
        info.setAllot_rule_id(1);
        info.setAllot_sts( 2);
        info.setAlloted_bcycle_count(1);
        info.setAlloted_fee(1233L);
        allotInfos.add(info);
        resp.setAllotInfos(allotInfos);
        String aaa = printObj(resp);
        System.out.println(aaa);

    }

    public static void getQueryPromoAllotInfoParamList()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7030);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short) 1);
        sOper.setRegion_code((short) 1);
        sOper.setCounty_code((short) 1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short) 1);
        sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);

        SQueryPromoAllotInfoReq req = new SQueryPromoAllotInfoReq();
        req.setUser_id(190234L);
        req.setOrder_id(1122L);

        System.out.println("{\"sOper\":");
        System.out.println(printObj(sOper));
        System.out.println(",\"sQueryIn\":");
        System.out.println(printObj(req));
        System.out.println("}");
    }

    public static Do_depositResponse getDoDepositJsonStr()
    {
        Do_depositResponse resp = new Do_depositResponse();
        resp.setAmount(123L);
//        resp.setError_code((short) 0);
        resp.setOut_so_date("2012-02-02 12:12:12");
        resp.setOut_so_nbr("543212433");
        resp.setSo_date("2012-02-02 12:12:12");
        resp.setSo_nbr(12324555L);

        resp.setErrorMsg(getErrorMsg());
//        String aaa = printObj(resp);
//        System.out.println(aaa);
        return resp;
    }

    public static void getDoDepositParamList()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7036);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short) 1);
        sOper.setRegion_code((short) 1);
        sOper.setCounty_code((short) 1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short) 1);
        sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);

        SDoDepositReq req = new SDoDepositReq();
        req.setAcct_id(186840L);
        req.setPhone_id("1");
        req.setCall_number("222");
        req.setCard_no("334555");
        req.setOut_so_date("2012-01-01 12:12:12");
        req.setOut_so_nbr("123456789");
        req.setPay_amount(100L);
        
        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("req", req);
        System.out.println(Json.serialize(map));
//        System.out.println("{\"sOper\":");
//        System.out.println(printObj(sOper));
//        System.out.println(",\"req\":");
//        System.out.println(printObj(req));
//        System.out.println("}");
    }

    public static void getDoUnDepositParamList()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7038);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short) 1);
        sOper.setRegion_code((short) 1);
        sOper.setCounty_code((short) 1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short) 1);
        sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);
        SDoUnDepositReq req = new SDoUnDepositReq();
        // req.setSo_date("2012-01-01 12:12:12");
        req.setSo_nbr("123456789");
        req.setAcct_id(186840L);
        System.out.println("{\"sOper\":");
        System.out.println(printObj(sOper));
        System.out.println(",\"req\":");
        System.out.println(printObj(req));
        System.out.println("}");

    }

    public static CBSErrorMsg getErrorMsg()
    {
        CBSErrorMsg msg = new CBSErrorMsg();
        msg.setFinish_date("2012-01-01 12:12:12");
        msg.setOrig_so_nbr("123456789");
        msg.setResult_code(0L);
        msg.setSo_nbr(8889999L);
        msg.setError_msg("test");
        msg.setHint("test");

        return msg;
    }

    public static void getDoUnDepositJsonStr()
    {
        Do_unDepositResponse resp = new Do_unDepositResponse();
//        resp.setBFlag(true);
        resp.setErrorMsg(getErrorMsg());
        String aaa = printObj(resp);
        System.out.println(aaa);
    }
}
