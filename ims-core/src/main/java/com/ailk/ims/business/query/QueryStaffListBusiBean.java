package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryStaffListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.StaffInfo;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 
 * @author yugb 新增接口
 *
 */

public class QueryStaffListBusiBean extends BaseBusiBean 
{
    private BaseComponent baseCmp;
    private List<StaffInfo> staffInfoList = new ArrayList<StaffInfo>();
   
    @Override
    public void init(Object... input) throws BaseException
    {
        baseCmp = context.getComponent(BaseComponent.class);
    }
    
    @Override
    public void validate(Object... input) throws BaseException
    {
        
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        //查询出员工信息(start和limit要么两个都有值，要么两个都没值)
        List<CmCustomer> customerList = null;
        Integer customerType = SysUtil.getInt(SysCodeDefine.busi.IM_STAFF_TYPE);
        List<Long> custIds = new ArrayList<Long>();
        
        
        customerList = DBUtil.query(CmCustomer.class, new DBCondition(CmCustomer.Field.customerType,customerType));
        if(CommonUtil.isEmpty(customerList))
            return null;
            for(int i=0;i< customerList.size();i+=1000){
                for(int j=i;j<i+1000 && j<customerList.size();j++){
                        custIds.add(customerList.get(j).getCustId());
                }
                if(CommonUtil.isEmpty(custIds))
                    continue;
                List<CaCustomerRel> caCustomerRelList = DBUtil.query(CaCustomerRel.class,
                                                                  new DBCondition(CaCustomerRel.Field.custId, custIds, Operator.IN));
                    if(CommonUtil.isEmpty(caCustomerRelList))
                            continue;
                    //设置返回信息
                    setStaffInfo(caCustomerRelList);
            }
        return new Object[] { staffInfoList };
    }
    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryStaffListResponse resp = new Do_queryStaffListResponse();
        if (!CommonUtil.isEmpty((List<StaffInfo>)result[0])){
            List<StaffInfo> staffInfoList=(List<StaffInfo>)result[0];
            resp.setStaffInfoList(staffInfoList);
        }
        return  resp;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }
    /**
     *  获取userId 对应的billingType
     * @param userId
     * @param cmResList
     * @return
     */
    private Long getBillingType(Long userId,List<CmResource> cmResList){
        if(CommonUtil.isEmpty(cmResList) || userId == null)
            return null;
        for(CmResource cmResource :cmResList ){
            if(cmResource.getResourceId().longValue() == userId.longValue()){
                return CommonUtil.IntegerToLong(cmResource.getBillingType());
            }
        }
        return null;
    }
    private Integer getOwnerType(Long acctId,List<CaAccount> caAccountList){
        if(CommonUtil.isEmpty(caAccountList) || acctId == null)
            return null;
        for(CaAccount caAccount :caAccountList ){
        	//@Date 2012-11-13 yugb :ownerType返回问题
            if(caAccount.getAcctId().longValue() == acctId.longValue()){
                return CommonUtil.long2Int(caAccount.getBalanceType());
            }
        }
        return null;
    }
    private void setStaffInfo(List<CaCustomerRel> caCustomerRelList){
        List<Long> acctIds = new ArrayList<Long>();
        List<Long> userIds = new ArrayList<Long>();
        for(int k=0;k<caCustomerRelList.size();k+=1000){
            for(int l=k;l<k+1000&&l<caCustomerRelList.size();l++){
                    acctIds.add(caCustomerRelList.get(l).getAcctId());
            }
            if(CommonUtil.isEmpty(acctIds))
                continue;
            List<CaAccountRes> caAccountResList = DBUtil.query(CaAccountRes.class,
                    new DBCondition(CaAccountRes.Field.acctId, acctIds, Operator.IN));
            if(CommonUtil.isEmpty(caAccountResList))
                continue;
            List<CaAccount> caAccountList= DBUtil.query(CaAccount.class,
                    new DBCondition(CaAccount.Field.acctId, acctIds, Operator.IN));
            for(int i=0;i<caAccountResList.size();i+=1000){
                for(int j=i;j<i+1000&&j<caAccountResList.size();j++){
                    userIds.add(caAccountResList.get(j).getResourceId());
                }
                List<CmResource> cmResourceList= DBUtil.query(CmResource.class,
                                                  new DBCondition(CmResource.Field.resourceId, userIds, Operator.IN));
                for(int m=0;m<caAccountResList.size();m++){
                    StaffInfo info = new StaffInfo();
                    Long acctId = caAccountResList.get(m).getAcctId();
                    Long userId = caAccountResList.get(m).getResourceId();
                    Long billingType = getBillingType(userId,cmResourceList);
                    Integer ownerType = getOwnerType(acctId,caAccountList);
                    info.setAcct_id(acctId);
                    info.setResource_id(userId);
                    info.setOwner_type(ownerType);
                    info.setBilling_type(billingType);
                    staffInfoList.add(info);
            }
            }
    }
    }

}
