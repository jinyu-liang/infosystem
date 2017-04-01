package com.blue.ailk.testscript;

import com.blue.core.lang.io.TextUtils;

public class SQLCreator {
	public static void main(String[] args) {

		SQLCreator creator = new SQLCreator();
		creator.setPtRegionCodePart("0774");
		creator.setPtDatePart("201502");
		creator.setSeqId(String.valueOf(System.currentTimeMillis()));
		creator.setCustId("3201502010001");
		creator.setAcctId("2201502010001");
		creator.setUserId("1201502010001");
		creator.createUser();
		// creator.changeUserToOtherAcct();

	}

	public void createSysTrans() {
		StringBuffer sql = new StringBuffer();
		for (int a = 0; a <= 6; a++) {
			for (int b = 0; b <= 23; b++) {
				for (int c = 0; c <= 2; c++) {
					sql.append("create table ZD.SYS_TRANS_"
							+ a
							+ "_"
							+ b
							+ "_"
							+ c
							+ "( SESSION_ID  NUMBER(25) not null, SEQUENCE_ID NUMBER(10) not null, TRANS_TYPE  NUMBER(1), TRANS_TIME  DATE default SYSDATE not null, HOST  VARCHAR2(128), REQUEST     BLOB, PROCESS_ID  NUMBER(10), STATUS NUMBER(1), URI   VARCHAR2(256), INTERFACE   VARCHAR2(256), SESSION_INFO VARCHAR2(4000), PARAM1 VARCHAR2(4000), PARAM2 VARCHAR2(4000), PARAM3 VARCHAR2(4000), PARAM4 VARCHAR2(4000), PARAM5 VARCHAR2(4000), PARAM6 VARCHAR2(4000), PARAM7 VARCHAR2(4000), PARAM8 VARCHAR2(4000), PARAM9 VARCHAR2(4000), PARAM10     VARCHAR2(4000), UPLOAD_QUEUE_NAME VARCHAR2(128), BUSI_PARAM  VARCHAR2(1024)	);");
					sql.append("\n");
				}
			}
		}
		TextUtils.write("d:/sql.txt", sql.toString(), true);
		System.out.println(sql.toString());

	}

	public void createIndex() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_data_index (CUST_ID, ACCT_ID, USER_ID, UP_FIELD, REGION_CODE, COUNTY_CODE, COMMIT_DATE, SO_NBR, REMARK, DONE_CODE, BUSI_CODE)values ("
				+ this.getCustId()
				+ ", "
				+ this.getAcctId()
				+ ", "
				+ this.getUserId()
				+ ", '0000000000000000000001', 770, 770, to_date('30-05-2014', 'dd-mm-yyyy'), "
				+ this.getSeqId() + ", '', " + this.getSeqId() + ", 0);");
		System.out.println(sql.toString());
	}

	// 分账脚本
	public void split() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'0000000000001',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId()
				+ "," + this.getSeqId() + ",0,0);");
		sql.append("\n");
		sql.append("insert into jd.i_acct_pay_relation_"
				+ this.getPtDatePart()
				+ "(PAY_ACCT_ID, USER_ID, ACCT_ID, REGION_CODE, PAY_RULE_ID, PRODUCT_ID, PAY_TYPE, PAY_NUMERATOR, PAY_DENOMINATOR, PAY_THRESHOLD, PAY_METHOD, PRIORITY, VALID_DATE, EXPIRE_DATE, OPER_TYPE, SO_NBR, COMMIT_DATE, MAIN_USER_ID)values ("
				+ this.getAcctId()
				+ ", 14099712, 14099712, 770, null, 0, 1, 1, 2, 500, 1, 500, sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'), 1, "
				+ this.getSeqId() + ", sysdate, null);");
		System.out.println(sql.toString());
	}

	/**
	 * 新增亲情号码
	 */
	public void fnProduct() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'000000000011',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId()
				+ "," + this.getSeqId() + ",0,0);");
		sql.append("\n");
		sql.append("insert into jd.i_Product_"
				+ this.getPtDatePart()
				+ "(product_id,user_id,acct_id,offer_id,product_type,product_status,parent_product_id,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getSeqId()
				+ ","
				+ this.getUserId()
				+ ","
				+ this.getAcctId()
				+ ",90940001,0,1,null,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");
		sql.append("\n");
		sql.append("insert into jd.I_USER_RELATION_"
				+ this.getPtDatePart()
				+ "(PRODUCT_ID, USER_ID, RELATION_TYPE, OPP_NUMBER, OPP_REGION_CODE, VALID_DATE, EXPIRE_DATE, OPER_TYPE, SO_NBR, COMMIT_DATE,  OPP_ATTR)values ("
				+ this.getSeqId()
				+ ", "
				+ this.getUserId()
				+ ", 1, '13429666666', 771, to_date('01-05-2014', 'dd-mm-yyyy'), to_date('31-03-2015', 'dd-mm-yyyy'), 1, "
				+ this.getSeqId()
				+ ", to_date('23-05-2014', 'dd -mm-yyyy'), '');");

		System.out.println(sql.toString());
	}

	/**
	 * 携号跨区
	 */
	public void portability() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'00000000000000000000001',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId() + "," + this.getSeqId() + ",0,0);");
		sql.append("\n");
		sql.append("insert into JD.I_USER_PORTABILITY_"
				+ this.getPtDatePart()
				+ "(USER_ID ,OUT_REGION ,IN_REGION,REGION_CODE,VALID_DATE,EXPIRE_DATE,OPER_TYPE,SO_NBR,COMMIT_DATE)values("
				+ this.getUserId()
				+ ",770,771,770,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");
		System.out.println(sql.toString());
	}

	/**
	 * 过户
	 */
	public void changeUserToOtherAcct() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'0000001',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId()
				+ ","
				+ this.getSeqId()
				+ ",0,0);");
		sql.append("\n");
		sql.append("insert into JD.I_USER_ACCT_REL_"
				+ this.getPtDatePart()
				+ "(USER_ID,ACCT_ID,OLD_ACCT_ID,RELATIONSHIP_TYPE,TITLE_ROLE_ID,VALID_DATE,EXPIRE_DATE,OPER_TYPE,SO_NBR,COMMIT_DATE)values("
				+ this.getUserId()
				+ ",2201406040004,"
				+ this.getAcctId()
				+ ", 1, 1, sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'), 1,"
				+ this.getSeqId() + ", sysdate);");
		sql.append("\n");
		sql.append("insert into JD.I_USER_ACCT_REL_"
				+ this.getPtDatePart()
				+ "(USER_ID,ACCT_ID,OLD_ACCT_ID,RELATIONSHIP_TYPE,TITLE_ROLE_ID,VALID_DATE,EXPIRE_DATE,OPER_TYPE,SO_NBR,COMMIT_DATE)values("
				+ this.getUserId() + ",2201406040004," + this.getAcctId()
				+ ", 1, 1, sysdate,sysdate, 3," + this.getSeqId()
				+ ", sysdate);");
		System.out.println(sql.toString());
	}

	/**
	 * 重开户
	 */
	public void openUserStatus() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'000001',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId()
				+ ","
				+ this.getSeqId()
				+ ",0,0);");

		sql.append("\n");
		sql.append("insert into jd.i_User_Status_"
				+ this.getPtDatePart()
				+ "(user_id,sts,os_sts,os_sts_dtl,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getUserId()
				+ ",1001,10,'0',sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),2,"
				+ this.getSeqId() + ",sysdate);");
		System.out.println(sql.toString());
	}

	/**
	 * 预销户
	 */
	public void preCancleUserStatus() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'000001',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId()
				+ ","
				+ this.getSeqId()
				+ ",0,0);");

		sql.append("\n");
		sql.append("insert into jd.i_User_Status_"
				+ this.getPtDatePart()
				+ "(user_id,sts,os_sts,os_sts_dtl,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getUserId()
				+ ",1008,12,'0000000000000000100000000000000000000000000000000000000000000000',sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),2,"
				+ this.getSeqId() + ",sysdate);");
		System.out.println(sql.toString());
	}

	/**
	 * 新增产品
	 */
	public void addProduct() {
		StringBuffer sql = new StringBuffer();

		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'00000000001',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId()
				+ ","
				+ this.getSeqId()
				+ ",0,0);");
		sql.append("\n");
		sql.append("insert into jd.i_Product_"
				+ this.getPtDatePart()
				+ "(product_id,user_id,acct_id,offer_id,product_type,product_status,parent_product_id,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getSeqId()
				+ ","
				+ this.getUserId()
				+ ","
				+ this.getAcctId()
				+ ",45003650,0,1,null,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");
		System.out.println(sql.toString());
	}

	/**
	 * 取消产品
	 */
	public void deleteProduct() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'00000000001',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId()
				+ ","
				+ this.getSeqId()
				+ ",0,0);");
		sql.append("\n");
		sql.append("insert into jd.i_Product_"
				+ this.getPtDatePart()
				+ "(product_id,user_id,acct_id,offer_id,product_type,product_status,parent_product_id,valid_date,expire_date,oper_type,so_nbr,commit_date)values(1401087167004,"
				+ this.getUserId()
				+ ","
				+ this.getAcctId()
				+ ",45003650,0,1,null,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),3,"
				+ this.getSeqId() + ",sysdate);");
		sql.append("\n");
		System.out.println(sql.toString());
	}

	/**
	 * 变套餐，改品牌
	 */
	public void changeSuite() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'00001000001',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId()
				+ ","
				+ this.getSeqId()
				+ ",0,0);");
		sql.append("\n");
		sql.append("insert into jd.i_User_"
				+ this.getPtDatePart()
				+ "(user_id,user_type,payment_mode,phone_id,is_test_number,brand,user_segment,imsi,region_code,county_code,sms_language,ivr_language,ussd_language,active_date,is_send_card,valid_date,expire_date,oper_type,so_nbr,commit_date,res_class)values("
				+ this.getUserId()
				+ ",1,0,'15195991320',0,2222,99,'15195991320',"
				+ this.getPtRegionCodePart()
				+ ",0,2,2,2,sysdate,0,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),2,"
				+ this.getSeqId() + ",sysdate,0);");
		sql.append("\n");
		sql.append("insert into jd.i_Product_"
				+ this.getPtDatePart()
				+ "(product_id,user_id,acct_id,offer_id,product_type,product_status,parent_product_id,valid_date,expire_date,oper_type,so_nbr,commit_date)values(1401087167004,"
				+ this.getUserId()
				+ ","
				+ this.getAcctId()
				+ ",45003650,0,1,null,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),3,"
				+ this.getSeqId() + ",sysdate);");
		sql.append("\n");
		sql.append("insert into jd.i_Product_"
				+ this.getPtDatePart()
				+ "(product_id,user_id,acct_id,offer_id,product_type,product_status,parent_product_id,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getSeqId()
				+ ","
				+ this.getUserId()
				+ ","
				+ this.getAcctId()
				+ ",45003650,0,1,null,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");
		System.out.println(sql.toString());
	}

	/**
	 * 开户
	 */
	public void createUser() {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jd.i_Data_Index_sub_2(seq_id,cust_id,acct_id,user_id,up_field,region_code,county_code,commit_date,done_code,so_nbr,busi_code,db_succ_sts)values("
				+ this.getSeqId()
				+ ","
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getUserId()
				+ ",'1111111000100000000000000000000010000000000000000000000000000000',"
				+ this.getPtRegionCodePart()
				+ ","
				+ this.getPtRegionCodePart()
				+ ",sysdate,"
				+ this.getSeqId()
				+ ","
				+ this.getSeqId()
				+ ",0,0);");
		sql.append("\n");
		sql.append("insert into jd.i_Customer_"
				+ this.getPtDatePart()
				+ "(cust_id,group_id,cust_type,sub_cust_type,cust_segment,cust_name,birthday,gender,language,prov_code,region_code,county_id,reg_type,reg_nbr,valid_date,expire_date,oper_type,so_nbr,commit_date,real_name_sts)values("
				+ this.getCustId()
				+ ",0,0,1,0,'测试用户',to_date('1988-07-12','yyyy-mm-dd'),1,2,"
				+ this.getPtRegionCodePart()
				+ ",0,0,0,'123456789',sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate,0);");

		sql.append("\n");
		sql.append("insert into jd.i_Account_"
				+ this.getPtDatePart()
				+ "(cust_id,acct_id,acct_type,acct_class,acct_segment,acct_name,status,measure_id,bill_dispatching,bill_language_id,prov_code,county_code,region_code,credit_sign_control,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ",101,1,0,'测试用户',1,1,'00000000000000000000000000000000',2,0,0,"
				+ this.getPtRegionCodePart()
				+ ",0,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");
		sql.append("\n");
		sql.append("insert into jd.i_Acct_Bill_Cycle_"
				+ this.getPtDatePart()
				+ "(acct_id,cycle_type,cycle_unit,first_bill_day,first_bill_type,effective_type,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getAcctId()
				+ ",5,1,1,0,0,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");
		sql.append("\n");
		sql.append("insert into jd.i_Contact_"
				+ this.getPtDatePart()
				+ "(contact_id,contact_type,cust_id,acct_id,contact_name,mobile_phone,home_phone,country_id,prov_code,office_phone,fax,email,post_code,address,oper_type,so_nbr,commit_date,valid_date,expire_date)values("
				+ this.getSeqId()
				+ ",4,"
				+ this.getCustId()
				+ ","
				+ this.getAcctId()
				+ ",'测试用户','15195991320','07284517171',0,0,'07284517171','0728','scc@qq.com','310000','hangzhou',1,"
				+ this.getSeqId()
				+ ",sysdate,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'));");

		sql.append("\n");
		sql.append("insert into jd.i_User_"
				+ this.getPtDatePart()
				+ "(user_id,user_type,payment_mode,phone_id,is_test_number,brand,user_segment,imsi,region_code,county_code,sms_language,ivr_language,ussd_language,active_date,is_send_card,valid_date,expire_date,oper_type,so_nbr,commit_date,res_class)values("
				+ this.getUserId()
				+ ",1,0,'15195991320',0,1111,99,'15195991320',"
				+ this.getPtRegionCodePart()
				+ ",0,2,2,2,sysdate,0,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate,0);");
		sql.append("\n");
		sql.append("insert into jd.i_user_acct_rel_"
				+ this.getPtDatePart()
				+ "(user_id,acct_id,old_acct_id,relationship_type,title_role_id,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getUserId()
				+ ","
				+ this.getAcctId()
				+ ","
				+ this.getAcctId()
				+ ",0,1000,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");
		sql.append("\n");
		sql.append("insert into jd.i_User_Status_"
				+ this.getPtDatePart()
				+ "(user_id,sts,os_sts,os_sts_dtl,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getUserId()
				+ ",1001,10,'0',sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");
		sql.append("\n");
		sql.append("insert into jd.i_Acct_Pay_Channel_"
				+ this.getPtDatePart()
				+ "(acct_id,payment_type,bank_id,bank_acct_nbr,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getAcctId()
				+ ",1,'1111','1111111111111',sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");

		// sql.append("\n");
		// sql.append("insert into jd.i_Fee_"
		// + this.getPtDatePart()
		// +
		// "(user_id,acct_id,fee_type,fee_item_id,amount,measure_id,valid_date,expire_date,oper_type,so_nbr,commit_date,busi_spec_id,src_fee_item_id)values("
		// + this.getUserId()
		// + ","
		// + this.getAcctId()
		// +
		// ",1,6000032,100,1,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
		// + this.getSeqId() + ",sysdate,0,1);");
		sql.append("\n");
		sql.append("insert into jd.i_Product_"
				+ this.getPtDatePart()
				+ "(product_id,user_id,acct_id,offer_id,product_type,product_status,parent_product_id,valid_date,expire_date,oper_type,so_nbr,commit_date)values("
				+ this.getSeqId()
				+ ","
				+ this.getUserId()
				+ ","
				+ this.getAcctId()
				+ ",45003648,0,1,null,sysdate,to_date('2099-12-31 00:00','YYYY-MM-DD HH24:MI'),1,"
				+ this.getSeqId() + ",sysdate);");
		System.out.println(sql.toString());

	}

	String custId;
	String acctId;
	String userId;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPtDatePart() {
		return ptDatePart;
	}

	public void setPtDatePart(String ptDatePart) {
		this.ptDatePart = ptDatePart;
	}

	String seqId;

	public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	String ptDatePart;
	String ptRegionCodePart;

	public String getPtRegionCodePart() {
		return ptRegionCodePart;
	}

	public void setPtRegionCodePart(String ptRegionCodePart) {
		this.ptRegionCodePart = ptRegionCodePart;
	}
}
