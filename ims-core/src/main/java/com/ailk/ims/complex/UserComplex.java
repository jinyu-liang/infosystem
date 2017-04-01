package com.ailk.ims.complex;

import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
/**
 * @Description: 用户相关的复合类      
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-10-4
 */
public class UserComplex extends BaseComplex {
	private CmResource resource;
	private String phoneId;
	private CmResLifecycle lifeCycle;
	private List<CmResServCycle> servCycleList;
	private String imsi;
	
	public CmResource getResource() {
		return resource;
	}
	public CmResLifecycle getLifeCycle() {
		return lifeCycle;
	}
	public void setLifeCycle(CmResLifecycle lifeCycle) {
		this.lifeCycle = lifeCycle;
	}
	public void setResource(CmResource resource) {
		this.resource = resource;
	}
	public String getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}
	
	public List<CmResServCycle> getServCycleList(){
	    if(servCycleList == null){
	        servCycleList = DBUtil.query(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId,resource.getResourceId()));
	    }
	    return servCycleList;
	}
    public String getImsi()
    {
        return imsi;
    }
    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }
	
	
}
