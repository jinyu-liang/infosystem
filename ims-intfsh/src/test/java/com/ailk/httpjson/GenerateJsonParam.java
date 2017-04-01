package com.ailk.httpjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.tools.Json;
import com.ailk.openbilling.persistence.imscnsh.entity.DerateRecordIn;
import com.ailk.openbilling.persistence.imscnsh.entity.ExtDataDef;
import com.ailk.openbilling.persistence.imscnsh.entity.OweFeeBillIn;
import com.ailk.openbilling.persistence.imscnsh.entity.QryBalance;
import com.ailk.openbilling.persistence.imscnsh.entity.QryPaymentHisIn;
import com.ailk.openbilling.persistence.imscnsh.entity.SCheckUserSignInOutReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SDoDepositReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SDoTransferAmount;
import com.ailk.openbilling.persistence.imscnsh.entity.SDoUnDepositReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SQueryPromoAllotInfoReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SQueryUserHasBadDebtReq;
import com.ailk.openbilling.persistence.imscnsh.entity.ScheckDepositResultReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SdoPayFee;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

public class GenerateJsonParam
{

    public static String getQryPaymentHisparamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7017);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        // sOper.setCharge_flag((short) 1);
        // sOper.setCust_id(1L);
        // sOper.setAcct_id(1L);
        // sOper.setUser_id(1L);
        // sOper.setPhone_id("1");
        // sOper.setOp_id(1);
        // sOper.setProv_code((short) 1);
        // sOper.setRegion_code((short) 1);
        // sOper.setCounty_code((short) 1);
        // sOper.setOrg_id(1);
        // sOper.setRemark("1");
        // sOper.setRso_nbr("1");
        // sOper.setIs_monitor((short) 1);
        // sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        // sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);

        QryPaymentHisIn qryPaymentHisIn = new QryPaymentHisIn();
        qryPaymentHisIn.setAcct_id(186840L);
        qryPaymentHisIn.setChannel_id(2);
        qryPaymentHisIn.setPhone_id("");
        qryPaymentHisIn.setPayment_method((short) -1);
        qryPaymentHisIn.setStart_date("");
        qryPaymentHisIn.setEnd_date("");

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("qryPaymentHisIn", qryPaymentHisIn);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getQryFreeResparamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7018);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        QryBalance qryBalance = new QryBalance();
        qryBalance.setAcct_id(186840L);
        qryBalance.setResource_id(10957L);
        qryBalance.setPhone_id("");

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("qryBalance", qryBalance);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getQryOwnBillParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7022);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        OweFeeBillIn qweFeeBillIn = new OweFeeBillIn();
        qweFeeBillIn.setAcct_id(186840L);
        qweFeeBillIn.setRet_type((short) 1);
        qweFeeBillIn.setReal_type((short) 1);

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("qweFeeBillIn", qweFeeBillIn);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getQryBadDebtParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7027);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        SQueryUserHasBadDebtReq customerReq = new SQueryUserHasBadDebtReq();
        List<Long> acctIds = new ArrayList<Long>();
        acctIds.add(186840L);
        acctIds.add(123L);

        customerReq.setAcctList(acctIds);

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("customerReq", customerReq);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getDoDepositParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7036);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        SDoDepositReq req = new SDoDepositReq();
        req.setAcct_id(186840L);
        req.setPay_amount(1L);
        req.setPhone_id("15088888888");
        req.setOut_so_nbr("223456824");
        req.setOut_so_date("2012-05-28 17:27:27");
        req.setCall_number("12232");
        req.setCard_no("33333");

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("req", req);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getDoUnDepositParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7038);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        SDoUnDepositReq req = new SDoUnDepositReq();
        req.setAcct_id(186840L);
        req.setIn_out_flag((short) 1);
        req.setPhone_id("");
        req.setPhone_id("15088888888");
        req.setSo_nbr("223456824");
        // req.setSo_date("2012-05-28 17:27:27");

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("req", req);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getDoTransferParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7039);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        SDoTransferAmount req = new SDoTransferAmount();
        req.setOrg_acct_id(186840L);
        req.setOrg_phone_id("");
        req.setTarget_acct_id(1111L);
        req.setTarget_phone_id("");
        req.setOut_so_date("2012-02-02 12:12:12");
        req.setOut_so_nbr("111111111");

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("req", req);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getCheckDepositResultParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7040);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        ScheckDepositResultReq req = new ScheckDepositResultReq();
        req.setOrg_acct_id(186840L);
        req.setOrg_phone_id("");

        req.setSo_date("2012-02-02 12:12:12");
        // req.setSo_nbr(11111L);

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("req", req);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getDoPayFeeParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7041);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        SdoPayFee req = new SdoPayFee();
        req.setBank_id(123);
        req.setAcct_id(186840L);
        req.setPhone_id("");
        req.setAmount(1L);
        req.setBill_month("201205");
        req.setOut_so_date("2012-02-02 12:12:12");
        req.setOut_so_nbr("111111111");
        req.setQuery_blance_flag((short) 0);

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("req", req);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getInsertOneTimeFeeParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7043);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        ExtDataDef extDataDef = new ExtDataDef();
        extDataDef.setAcct_id(1111L);
        extDataDef.setBill_fee(111L);
        extDataDef.setDiscount_fee(33L);
        extDataDef.setItem_code(111);
        extDataDef.setMeasure_id(10403);
        extDataDef.setPay_acct_id(3333L);
        extDataDef.setResource_id(1111L);

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("extDataDef", extDataDef);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getQueryDerateRecordParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7045);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        DerateRecordIn derateRecordIn = new DerateRecordIn();
        derateRecordIn.setAcct_id(186840L);
        derateRecordIn.setBegin_month("201204");
        derateRecordIn.setEnd_month("201206");

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("derateRecordIn", derateRecordIn);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getQrySignInOutParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7051);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        SCheckUserSignInOutReq sCheckUserSignInOutReq = new SCheckUserSignInOutReq();
        sCheckUserSignInOutReq.setAcct_id(186940L);
        sCheckUserSignInOutReq.setParam_type((short) 0);

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("sCheckUserSignInOutReq", sCheckUserSignInOutReq);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static String getQueryPromoAllotInfoParamJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7030);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");

        sOper.setStep_id((short) 1);

        sOper.setNotify_flag((short) 1);

        SQueryPromoAllotInfoReq sQueryIn = new SQueryPromoAllotInfoReq();

        sQueryIn.setAcct_id(186840L);
        sQueryIn.setOrder_id(111L);

        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("sQueryIn", sQueryIn);
        String ret = Json.serialize(map);
        System.out.println(ret);
        return ret;
    }

    public static void main(String args[])
    {
        // getQryPaymentHisparamJsonStr();
        // getQryFreeResparamJsonStr();
        // getQryOwnBillParamJsonStr();
        // getQryBadDebtParamJsonStr();
        // getDoDepositParamJsonStr();
        // getDoUnDepositParamJsonStr();
        // getDoTransferParamJsonStr();
        // getCheckDepositResultParamJsonStr();
        // getDoPayFeeParamJsonStr();
        // getInsertOneTimeFeeParamJsonStr();
        // getQueryDerateRecordParamJsonStr();
        // getQrySignInOutParamJsonStr();
        getQueryPromoAllotInfoParamJsonStr();
    }
}
