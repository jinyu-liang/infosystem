package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.List;

import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmBusiOrder;

/**
 * @Description: 业务记录存放对象                
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-10-24
 */
public class BusiRecord {
	private CmBusi busi;//业务记录总表对象
	private List<CmBusiOrder> busiOrders;//业务记录明细对象
	
	public BusiRecord(CmBusi busi){
		this.setBusi(busi);
	}
	
	public void addBusiOrder(CmBusiOrder busiOrder){
		if(busiOrders == null){
			busiOrders = new ArrayList<CmBusiOrder>();
		}
		busiOrders.add(busiOrder);
	}
	
	public CmBusi getBusi() {
		return busi;
	}
	public void setBusi(CmBusi busi) {
		this.busi = busi;
	}


	public List<CmBusiOrder> getBusiOrders() {
		return busiOrders;
	}

	public void setBusiOrders(List<CmBusiOrder> busiOrders) {
		this.busiOrders = busiOrders;
	}
	
	/*private boolean isBatch = false;//是否批量
	private boolean isNormal = true;//是否正常业务
	private Short busiDirect;//业务方向，1-外系统同步到BOS；2-BOS同步给外系统
	
	private Long parentSoNbr;//父工单编号
	
	private Long custId;//客户id
	private Long acctId;//账户id
	private Long userId;//用户id
	private String phoneId;//手机号码
	
	private Date cancelDate;//撤单时间
	private Long origSoNbr;//撤单对应的原始工单号
	
	private Boolean otcflag;//是否收取一次性费用标识
	private Long otcSoNbr;//收取一次性费用对应的内部so_nbr（帐管的）
	
	private Boolean rewardflag;//是否有做赠送
	private Long rewardSoNbr;//赠送对应的内部so_nbr
	
	private Boolean authflag;//是否有做鉴权
	private Long authSoNbr;//鉴权对应的内部so_nbr
	
	
	//业务记录明细
	public class BusiRecordDetail{
		
	}*/
}
