package com.ailk.ims.complex;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.openbilling.persistence.imsintf.entity.FreeResource;
import com.ailk.openbilling.persistence.imsintf.entity.FreeResourceGroup;
import com.ailk.openbilling.persistence.imsintf.entity.FreeResourceGroupList;
import com.ailk.openbilling.persistence.imsintf.entity.FreeResourceList;

/**
 * FreeResource根据groupId和paymentMode分组汇总
 * 供BalanceComponent.queryFreeResList使用
 * @Date 2012-05-21 wangdw5
 * @Date 2012-08-01 wangdw5 :#54215: 查询免费资源，分组错误
 */
public class FreeResourceComplex {
	private Map<FreeResGroup,FreeResourceGroup> map = null;
	
	private Map<FreeResGroup,List<FreeResource>> detailMap = null;
	
	public Map<FreeResGroup, List<FreeResource>> getDetailMap() {
		return detailMap;
	}

	public void setDetailMap(Map<FreeResGroup, List<FreeResource>> detailMap) {
		this.detailMap = detailMap;
	}

	public Map<FreeResGroup, FreeResourceGroup> getMap() {
		return map;
	}

	public void setMap(Map<FreeResGroup, FreeResourceGroup> map) {
		this.map = map;
	}
	
	/**
	 * @Description:接收FreeResource的list,循环分组并返回FreeResourceGroupList
	 * @param list	 
	 * @author: wangdw5
	 * @Date: 2012-7-26
	 */
	public FreeResourceGroupList groupingFreeResourceList(List<FreeResource> list)
	{
		for (FreeResource freeResource : list) {
			this.addFreeResource(freeResource);
		}
		return this.transferMap2GroupList();
	}

	/**
	 * @Description: 单条freeResource处理
	 * @param freeResource	 
	 * @author: wangdw5
	 * @Date: 2012-7-26
	 */
	public void addFreeResource(FreeResource freeResource){
		int groupId = freeResource.getGroup_id().intValue();
		int payMode = freeResource.getPayment_mode().intValue();
		FreeResGroup group = new FreeResGroup(groupId,payMode); 
		if(CommonUtil.isEmpty(map)){
			map = new HashMap<FreeResGroup, FreeResourceGroup>();
		}
		FreeResourceGroup freeResGroup = map.get(group);
		if(freeResGroup == null)
		{
			freeResGroup = new FreeResourceGroup();
		}
		freeResGroup.setResource_group_id(groupId);
		freeResGroup.setMode(payMode);
		freeResGroup.setName(freeResource.getGroup_name());
		//@Date 2012-08-01 wangdw5 :#54215: 查询免费资源，分组错误
//		if(freeResource.getResource_id() != null)
//            freeResGroup.setUnit(freeResource.getResource_id().intValue());
		//@Date 2012-11-06 yugb :On_Site Defect #63897
		freeResGroup.setUnit(CommonUtil.string2Integer(freeResource.getResource_unit()));
		//2012-09-20 linzt  59421 Free Resource is not return amount
        if(freeResource.getResource_amount() != null){
            if(freeResGroup.getAmount()==null){
                freeResGroup.setAmount(0);
            }
            freeResGroup.setAmount(freeResGroup.getAmount() + freeResource.getResource_amount().intValue());
        }
        if(freeResource.getRemain_resource() != null){
            if(freeResGroup.getRemaining()==null){
                freeResGroup.setRemaining(0);
            }
            freeResGroup.setRemaining(freeResGroup.getRemaining() + freeResource.getRemain_resource().intValue());
        }
        
		
		if(freeResource.getVaild_date() != null)
		{
			Date vaild_date = DateUtil.getFormatDate(freeResource.getVaild_date(),DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
			if(freeResGroup.getValid_date() != null)
				vaild_date = DateUtil.compare2Day(vaild_date, freeResGroup.getValid_date())?vaild_date:freeResGroup.getValid_date();
			freeResGroup.setValid_date(vaild_date);
		}
		
		if(freeResource.getExpire_date() != null)
		{
			Date expire_date = DateUtil.getFormatDate(freeResource.getExpire_date(),DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
			if(freeResGroup.getExpired_date() != null)
				expire_date = DateUtil.compare2Day(expire_date, freeResGroup.getExpired_date())?freeResGroup.getExpired_date():expire_date;
			freeResGroup.setExpired_date(expire_date);
		}
		map.put(group, freeResGroup);
		
		if(CommonUtil.isEmpty(detailMap))
		{
			detailMap = new HashMap<FreeResGroup, List<FreeResource>>();
		}
		List<FreeResource> details = detailMap.get(group);
		if(CommonUtil.isEmpty(details))
		{
			details = new ArrayList<FreeResource>();
		}
		details.add(freeResource);
		detailMap.put(group, details);
	}
	
	/**
	 * @Description:对分组后的结果进行组装
	 * @return	 
	 * @author: wangdw5
	 * @Date: 2012-7-26
	 */
	public FreeResourceGroupList transferMap2GroupList()
	{
		FreeResourceGroupList freeResourceGroupList = null;
		if(CommonUtil.isNotEmpty(map))
		{
			freeResourceGroupList = new FreeResourceGroupList();
			List<FreeResourceGroup> groupList = new ArrayList<FreeResourceGroup>();
			Set<FreeResGroup> groups = map.keySet();
			for (FreeResGroup freeResGroup : groups) {
				FreeResourceGroup freeResourceGroup = map.get(freeResGroup);
				List<FreeResource> freeResourceList = detailMap.get(freeResGroup);
				FreeResourceList list = new FreeResourceList();
				list.setFreeResourceList_Item(freeResourceList.toArray(new FreeResource[freeResourceList.size()]));
				freeResourceGroup.setFreeResourceList_Item(list);
				groupList.add(freeResourceGroup);
			}
			if(CommonUtil.isNotEmpty(groupList))
			{
				freeResourceGroupList.setFreeResourceGroup_Item(groupList.toArray(new FreeResourceGroup[groupList.size()]));
			}
		}
		return freeResourceGroupList;
	}
	
	/**
	 * @Description: 分组条件内部实体:group_id,payment_mode
	 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
	 * @Author wangdw5
	 * @Date 2012-7-26
	 */
	private class FreeResGroup {
		public FreeResGroup(){
		}
		public FreeResGroup(Integer group_id,Integer payment_mode){
			setGroup_id(group_id);
			setPayment_mode(payment_mode);
		}
		private int group_id;
		private int payment_mode;
		public int getGroup_id() {
			return group_id;
		}
		public void setGroup_id(Integer group_id) {
			this.group_id = group_id;
		}
		public int getPayment_mode() {
			return payment_mode;
		}
		public void setPayment_mode(Integer payment_mode) {
			this.payment_mode = payment_mode;
		}
		@Override
	    public boolean equals(Object obj)
	    {
	        if (this == obj)
	            return true;
	        if (!(obj instanceof FreeResGroup))
	            return false;
	        FreeResGroup g = (FreeResGroup) obj;
	        return g.getGroup_id() == this.group_id && g.getPayment_mode() == this.payment_mode;
	    }
        @Override
        public int hashCode()
        {
            return group_id;
        }
	}
}
