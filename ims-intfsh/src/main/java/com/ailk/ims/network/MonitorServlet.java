package com.ailk.ims.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.BillExtIn;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryBillExtResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryFreeResourceResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryPocketExtResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.PocketExtIn;
import com.ailk.openbilling.persistence.imscnsh.entity.QryBalance;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.service.imscnsh.CN_SHMgntService;
/**
 * @description:网管监控
 * @author caohm5
 * @date:2012-12-15
 *
 */
public class MonitorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String suss = "SUCCESS";
	private static String fail = "FAILED";

	private static final CN_SHMgntService service = (CN_SHMgntService) SpringUtil
			.getBean("imscnsh_cN_SHMgntService");

	public MonitorServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 拼装返回值
		String resultMsg = "";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String phoneId = request.getParameter("phoneId");
		if (CommonUtil.isEmpty(phoneId)) {
			// 手机号码为空
			resultMsg = "手机号码[phoneId]为空";
		} else {
			//账单
			SOperInfo billOper=new SOperInfo();
			billOper.setBusi_code(7016);
			billOper.setSo_nbr("monitor2012");
			billOper.setSo_mode(new Short("100"));
			billOper.setSo_date(DateUtil.getFormatDate(new Date(),DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
			BillExtIn billExtIn=getBillExtIn(phoneId);
			Do_queryBillExtResponse billRespn=service.do_queryBillExt(billOper, billExtIn);
			//账本
			SOperInfo pocketOper=new SOperInfo();
			pocketOper.setBusi_code(7012);
			pocketOper.setSo_nbr("monitor2012");
			pocketOper.setSo_mode(new Short("100"));
			pocketOper.setSo_date(DateUtil.getFormatDate(new Date(),DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
			PocketExtIn pocketExtIn=getPocketExtIn(phoneId);
			Do_queryPocketExtResponse  pocketRespn=service.do_queryPocketExt(pocketOper, pocketExtIn);
			//免费资源
			SOperInfo freeOper=new SOperInfo();
			freeOper.setBusi_code(7018);
			freeOper.setSo_nbr("monitor2012");
			freeOper.setSo_mode(new Short("100"));
			freeOper.setSo_date(DateUtil.getFormatDate(new Date(),DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
			QryBalance qry=new QryBalance();
			qry.setPhone_id(phoneId);
			qry.setItem_type(EnumCodeShDefine.QUERY_FREERESOURCE_ITEM_TYPE_SUBSCRIBER);
			Do_queryFreeResourceResponse  freeRespn=service.do_queryFreeResource(freeOper, qry);
			Long billFlag=billRespn.getErrorMsg().getResult_code();
			Long pocketFlag=pocketRespn.getErrorMsg().getResult_code();
			Long freFlag=freeRespn.getErrorMsg().getResult_code();
			String billString = "[BUSINISS_NAME]：" + "查询账单" + " " + "[FLAG]:"
					+ checkFlag(billFlag) + " ; ";
			String pocketString = "[BUSINISS_NAME]：" + "查询账本" + " " + "[FLAG]:"
					+ checkFlag(pocketFlag) + " ; ";
			String freeString = "[BUSINISS_NAME]：" + "查询免费资源" + " " + "[FLAG]:"
					+ checkFlag(freFlag);
			resultMsg = billString + pocketString + freeString;
		}
		out.println(resultMsg);
	}

	private String checkFlag(Long flag) {
		String flagTmp = fail;
		if (flag !=0) {
			flagTmp = fail;
		} else  {
			flagTmp = suss;
		}
		return flagTmp;
	}
	private BillExtIn getBillExtIn(String phoneId){
		BillExtIn billExtIn=new BillExtIn();
		billExtIn.setPhone_id(phoneId);
		billExtIn.setIs_format(EnumCodeDefine.BILL_EXT_IN_FORMAT_NO);
        // 查询账单详细
		billExtIn.setRet_type(EnumCodeDefine.BILL_EXT_IN_BILL_DETAIL_NO);
        // 查询实时账单
		billExtIn.setReal_type(EnumCodeDefine.BILL_EXT_IN_HISTORY_AND_REAL_BILL);
        // 不查询滞纳金
		billExtIn.setLate_fee_type(EnumCodeDefine.BILL_EXT_IN_LATE_FEE_NO);
		
		billExtIn.setBegin_month(DateUtil.getFormatDate(new Date(),DateUtil.DATE_FORMAT_YYYYMM));
		
		billExtIn.setEnd_month(DateUtil.getFormatDate(new Date(),DateUtil.DATE_FORMAT_YYYYMM));
		
		return billExtIn;
	}
	
	private  PocketExtIn getPocketExtIn(String phoneId){
		PocketExtIn pocketExtIn=new PocketExtIn();
		pocketExtIn.setPhone_id(phoneId);
		pocketExtIn.setQry_type(EnumCodeDefine.QUERY_POCKET_YAJIN_AND_ZHANGBEN);
		return pocketExtIn;
	}
}
