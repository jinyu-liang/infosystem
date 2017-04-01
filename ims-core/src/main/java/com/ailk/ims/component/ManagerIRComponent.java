package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.busi.entity.RsSysElementCallType;
import com.ailk.openbilling.persistence.busi.entity.RsSysIntlOperInfo;
import com.ailk.openbilling.persistence.busi.entity.RsSysIntlOperRel;
import com.ailk.openbilling.persistence.busi.entity.RsSysIntlOperService;
import com.ailk.openbilling.persistence.imsintf.entity.SManagerIRPartyReq;
import com.ailk.openbilling.persistence.imsintf.entity.ServiceRequest;
import com.ailk.openbilling.persistence.pm.entity.PmServiceSpec;

/**
 * @Description关于管理运营商信息的方法可以放在这里
 * @author liuyang8
 * @Date 2011-9-27
 * @Date 2012-05-07 wangdw5:Operator_type=1,Add Or Del RsSysIntlOperRel
 * @Date 2012-06-05 wangdw5:#47389相关业务可参考文档[43153]IR Party增加AIS子公司标识
 * @Date 2012-06-21 wangdw5 : User Story #49004 Manage IR Party 返回参数缺少_sequence_service_id,新增时返回ID
 * @Date 2012-06-22 wangdw5 : User Story #49004 Manage IR Party 返回参数缺少_sequence_service_id,删改时根据ID进行操作
 */
public class ManagerIRComponent extends BaseComponent
{
    public ManagerIRComponent()
    {
    }

    
    public void createIntlServiceInfo(SManagerIRPartyReq mgntIR,Integer relId)
    {
    	ServiceRequest[] serReqs = mgntIR.getServiceRequestOper().getServiceRequestList().getServiceRequest_Item();
    	if(CommonUtil.isEmpty(serReqs)){
    		return;
    	}
    	RsSysIntlOperService addService = null;
        String operatorCode = mgntIR.getOperator_code();
        for (ServiceRequest serReq : serReqs)
        {//@Date 2012-06-05 wangdw5:#47389 relId存入service表
        	addService = setOperService(serReq, mgntIR, operatorCode, relId, EnumCodeDefine.OPER_TYPE_ADD);
            List<RsSysIntlOperService> qryServices = this.queryIntlOperService(serReq, relId);
            if (CommonUtil.isNotEmpty(qryServices))
            {
            	for (RsSysIntlOperService rsSysIntlOperService : qryServices) {
					if(addService.getValidDate().after(rsSysIntlOperService.getExpireDate()) || addService.getExpireDate().before(rsSysIntlOperService.getValidDate()))
					{
						continue;
					}else
						throw IMSUtil.throwBusiException(ErrorCodeDefine.PERIOD_OVERLAP);
				}
            }
            insert(addService);
            //@Date 2012-06-21 wangdw5 : User Story #49004 Manage IR Party 返回参数缺少_sequence_service_id,新增时返回ID
            serReq.setService_sequence_id(addService.getId());
        }
    }
    
    /**
     * 查询运营商签订的服务信息 liuyang8 2011-12-19
     * 
     * @param serReq
     * @return
     */
    public List<RsSysIntlOperService> queryIntlOperService(ServiceRequest serReq, Integer relId)
    {
    	Integer specId = queryServiceId(serReq.getBusi_service_code());
    	return query(RsSysIntlOperService.class,new DBCondition(RsSysIntlOperService.Field.serviceSpecId,specId),
    			new DBCondition(RsSysIntlOperService.Field.relId,relId),
    			new DBCondition(RsSysIntlOperService.Field.callTypeId,-1),
    			new DBCondition(RsSysIntlOperService.Field.camelSupport,serReq.getCamel_support())/*,
    			new DBCondition(RsSysIntlOperService.Field.serviceStatus,serReq.getService_status())*/);
    }
    
    public void modifyIntlServiceInfo(SManagerIRPartyReq mgntIR){
    	ServiceRequest[] serReqs = mgntIR.getServiceRequestOper().getServiceRequestList().getServiceRequest_Item();
    	for (ServiceRequest serviceRequest : serReqs) {
    		RsSysIntlOperService operService = querySingle(RsSysIntlOperService.class, new DBCondition(RsSysIntlOperService.Field.id,serviceRequest.getService_sequence_id()));
			if (operService == null)
	        {
	            throw IMSUtil.throwBusiException(ErrorCodeDefine.RS_SYS_OPER_SERVICE_NOT_EXIST);
	        }
    		RsSysIntlOperService addService = setOperService(serviceRequest, mgntIR, null, operService.getRelId(), EnumCodeDefine.OPER_TYPE_MODIFY);
//    		if(!addService.getValidDate().after(operService.getValidDate()))
//            {
//            	throw IMSUtil.throwBusiException(ErrorCodeDefine.PERIOD_ERROR);
//            }
    		operService = new RsSysIntlOperService();
            operService.setExpireDate(addService.getValidDate());
            //仅更新老记录的失效时间
            DBUtil.updateByCondition(operService, new DBCondition(RsSysIntlOperService.Field.id,serviceRequest.getService_sequence_id()));
         	//新增Modify记录
            DBUtil.insert(addService);
            serviceRequest.setService_sequence_id(addService.getId());
		}
    }

    /**
     * 封装运营商服务信息 liuyang8 2011-9-23
     */
    private RsSysIntlOperService setOperService(ServiceRequest serReq, SManagerIRPartyReq mgntIR, String operatorCode, Integer relId ,Short type)
    {
        RsSysIntlOperService operSer = new RsSysIntlOperService();

        if (serReq.getCamel_support() != null)
        {
            operSer.setCamelSupport((int) serReq.getCamel_support());
        }
        operSer.setId(DBUtil.queryMax(RsSysIntlOperService.class, RsSysIntlOperService.Field.id));
        
        Integer serviceId = this.queryServiceId(serReq.getBusi_service_code());
        if (!CommonUtil.isValid(serviceId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.RS_SYS_OPER_SERVICE_NOT_CONFIG);
        }
        else
        {
            operSer.setServiceSpecId(serviceId);
        }

        if (serReq.getService_status() != null)
        {
            operSer.setServiceStatus((int) serReq.getService_status());
        }
        if (!CommonUtil.isEmpty(serReq.getDescription()))
        {
            operSer.setDescription(serReq.getDescription());
        }
        operSer.setCallTypeId(-1);
        checkCallType(-1);
        if(relId != null)
        	operSer.setRelId(relId);
        operSer.setExpireDate(IMSUtil.formatExpireDate(serReq.getExpire_date()));
        operSer.setValidDate(IMSUtil.formatDate(serReq.getValid_date(), context.getRequestDate()));
        if(operSer.getValidDate().after(operSer.getExpireDate()))
        {
        	throw IMSUtil.throwBusiException(ErrorCodeDefine.PERIOD_ERROR);
        }
        return operSer;
    }

    /**
     * 验证call type liuyang 2012-3-10
     * 
     * @param i
     */
    private void checkCallType(Integer callTypeId)
    {
        RsSysElementCallType callType = this.querySingle(RsSysElementCallType.class, new DBCondition(
                RsSysElementCallType.Field.callTypeId, callTypeId));
        if (callType == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CALL_TYPE_NOT_CONFIG, callTypeId);
        }
    }

    /**
     * 查询服务编号 liuyang 2012-3-10
     * 
     * @param busi_service_code
     * @return
     */
    private Integer queryServiceId(String serviceCode)
    {
        PmServiceSpec serviceSpec = querySingle(PmServiceSpec.class, new DBCondition(PmServiceSpec.Field.name, serviceCode));
        if (serviceSpec != null)
        {
            return serviceSpec.getServiceSpecId();
        }

        return null;
    }

    /**
     * 删除国际运营商信息或者服务信息
     * 
     * @author liuyang8 Date:2011-09-17
     * @Date 2012-06-05 wangdw5:#47389 使用DBUtil的删除并且先删除service表再删除rel表
     * @param mgntIR
     */
    public void deleteIntlOperInfo(SManagerIRPartyReq mgntIR)
    {
    	RsSysIntlOperInfo operInfoQry = this.queryIntlOperInfo(mgntIR);
        if (operInfoQry == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.RS_SYS_OPER_INFO_NOT_EXIST);
        }
        List<RsSysIntlOperRel> relList = query(RsSysIntlOperRel.class,new DBCondition(RsSysIntlOperRel.Field.operatorCode,mgntIR.getOperator_code()));
        if(CommonUtil.isNotEmpty(relList))
        {
            List<Integer> relIds = new ArrayList<Integer>();
        	for (RsSysIntlOperRel rsSysIntlOperRel : relList) {
        		relIds.add(rsSysIntlOperRel.getRelId());
			}
        	List<RsSysIntlOperService> services = query(RsSysIntlOperService.class,new DBCondition(RsSysIntlOperService.Field.relId,relIds,Operator.IN));
        	if(CommonUtil.isNotEmpty(services))
            {
        		throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DEL_INFO_HAS_SERVICE);
            }
        	RsSysIntlOperRel rel = new RsSysIntlOperRel();
        	rel.setExpireDate(context.getRequestDate());
        	DBUtil.updateByCondition(rel,new DBCondition(RsSysIntlOperRel.Field.relId,relIds,Operator.IN));
        }
        operInfoQry = new RsSysIntlOperInfo();
        operInfoQry.setExpireDate(context.getRequestDate());
        DBUtil.updateByCondition(operInfoQry,new DBCondition(RsSysIntlOperInfo.Field.id,mgntIR.getOperator_id()));
    }
    
    public void deleteIntlServiceInfo(SManagerIRPartyReq mgntIR)
    {
    	ServiceRequest[] serviceRequests = mgntIR.getServiceRequestOper().getServiceRequestList().getServiceRequest_Item();
		for (ServiceRequest serviceRequest : serviceRequests)
		{
			RsSysIntlOperService conService = querySingle(RsSysIntlOperService.class, new DBCondition(RsSysIntlOperService.Field.id,serviceRequest.getService_sequence_id()));
			if (conService == null)
	        {
	            throw IMSUtil.throwBusiException(ErrorCodeDefine.RS_SYS_OPER_SERVICE_NOT_EXIST);
	        }
			conService = new RsSysIntlOperService();
			conService.setExpireDate(context.getRequestDate());
			DBUtil.updateByCondition(conService,new DBCondition(RsSysIntlOperService.Field.id,serviceRequest.getService_sequence_id()));
		}
    }

    /**
     * 修改国际运营商信息或者服务信息
     * 
     * @author liuyang8 Date:2011-09-17
     * @param mgntIR
     */
    public void modifyIntlOperInfo(SManagerIRPartyReq mgntIR)
    {
        RsSysIntlOperInfo operInfoQry = this.queryIntlOperInfo(mgntIR);
        if (operInfoQry == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.RS_SYS_OPER_INFO_NOT_EXIST);
        }
        RsSysIntlOperInfo operInfo = setIntlOperInfo(mgntIR);
        operInfoQry = new RsSysIntlOperInfo();
        operInfoQry.setExpireDate(context.getRequestDate());
        DBUtil.updateByCondition(operInfoQry, new DBCondition(RsSysIntlOperInfo.Field.id,mgntIR.getOperator_id()));
        DBUtil.insert(operInfo);
        mgntIR.setOperator_id(operInfo.getId());
    }

    /**
     * 封裝国际运营商信息
     * 
     * @author liuyang8 Date：2011-09-23
     */
    public RsSysIntlOperInfo setIntlOperInfo(SManagerIRPartyReq mgntIR)
    {
        RsSysIntlOperInfo operInfo = new RsSysIntlOperInfo();
        operInfo.setOperatorCode(mgntIR.getOperator_code());

        if (mgntIR.getCountry_code() != null)
        {
            operInfo.setCountryId(mgntIR.getCountry_code().intValue());
        }
        if (!CommonUtil.isEmpty(mgntIR.getEnglish_name()))
        {
            operInfo.setEnglishName(mgntIR.getEnglish_name());
        }
        if (!CommonUtil.isEmpty(mgntIR.getMnc_code()) && !CommonUtil.isEmpty(mgntIR.getMcc_code()))
        {
            operInfo.setImsiHead(mgntIR.getMnc_code().concat(mgntIR.getMcc_code()));
        }
        if (!CommonUtil.isEmpty(mgntIR.getNetwork_statement()))
        {
            operInfo.setNetworkStatement(mgntIR.getNetwork_statement());
        }
        if (!CommonUtil.isEmpty(mgntIR.getTap_release()))
        {
            operInfo.setTapRelease(Integer.parseInt(mgntIR.getTap_release()));
        }
        if (mgntIR.getTap_version() != null)
        {
            operInfo.setTapVersion(mgntIR.getTap_version().intValue());
        }
        if (CommonUtil.isValid(mgntIR.getTime_zone()))
        {
            operInfo.setTimeZoneId(Integer.valueOf(mgntIR.getTime_zone()));
        }

        if (!CommonUtil.isEmpty(mgntIR.getOperator_code()))
        {
            operInfo.setOperatorCode(mgntIR.getOperator_code());
        }

        // 默认值
        operInfo.setReportErrorType(1);
        operInfo.setMeasureTypeId(Integer.valueOf(mgntIR.getCurrency_code()));
        operInfo.setTaxTreatment(1);
        operInfo.setValidDate(DateUtil.currentDate());
        operInfo.setExpireDate(IMSUtil.getDefaultExpireDate());
        operInfo.setId(DBUtil.queryMax(RsSysIntlOperInfo.class, RsSysIntlOperInfo.Field.id));
        operInfo.setModifyDate(DateUtil.currentDate());
        return operInfo;
    }

    public RsSysIntlOperRel setIntlOperRel(SManagerIRPartyReq mgntIR){
    	RsSysIntlOperRel operRel = new RsSysIntlOperRel();
    	operRel.setRelId(DBUtil.queryMax(RsSysIntlOperRel.class, RsSysIntlOperRel.Field.relId));
    	operRel.setOperatorCode(mgntIR.getOperator_code());
    	operRel.setUserOperatorId(Integer.parseInt(mgntIR.getInvoicingCoID()));
    	operRel.setOperRoamType(1);
    	return operRel;
    }
    /**
     * 查询国际运营商信息
     * 
     * @author liuyang8 Date:2011-09-17
     * @param mgntIR
     */
    public RsSysIntlOperInfo queryIntlOperInfo(SManagerIRPartyReq mgntIR)
    {
        RsSysIntlOperInfo operInfo = null;
        if(mgntIR.getOperator_id() != null)
        {
        	operInfo = querySingle(RsSysIntlOperInfo.class, new DBCondition(RsSysIntlOperInfo.Field.id,mgntIR.getOperator_id()));
        }
        else
        {
        	operInfo = querySingle(RsSysIntlOperInfo.class, new DBCondition(RsSysIntlOperInfo.Field.operatorCode,mgntIR.getOperator_code()),
        			new DBCondition(RsSysIntlOperInfo.Field.imsiHead,mgntIR.getMnc_code().concat(mgntIR.getMcc_code())));
        }
        return operInfo;
    }


//    /**
//     * 验证运营商是否存在。 liuyang 2012-2-6
//     * 
//     * @param mgntIR
//     */
//    public String checkOperator(SManagerIRPartyReq mgntIR)
//    {
//    	SysOperatorDef operatorDef = null;
//    	if (CommonUtil.isValid(mgntIR.getOperator_id()))
//        {
//    		operatorDef = this.querySingle(SysOperatorDef.class, new DBCondition(SysOperatorDef.Field.operatorId,
//    				mgntIR.getOperator_id()));
//    		if (operatorDef == null)
//    		{
//    			throw IMSUtil.throwBusiException(ErrorCodeDefine.OPERATOR_MAP_ERROR, mgntIR.getOperator_id());
//    		}
//        }else{
//        	operatorDef = this.querySingle(SysOperatorDef.class, new DBCondition(SysOperatorDef.Field.operatorCode,
//    				mgntIR.getOperator_code()));
//    		if (operatorDef == null)
//    		{
//    			throw IMSUtil.throwBusiException(ErrorCodeDefine.OPERATOR_MAP_ERROR, mgntIR.getOperator_code());
//    		}
//        }
//        
//        mgntIR.setOperator_code(operatorDef.getOperatorCode());
//        mgntIR.setOperator_id(operatorDef.getOperatorId());
//        return mgntIR.getOperator_code();
//    }
    
    /**
     * 验证运营商是否存在。 liuyang 2012-2-6
     * 
     * @param mgntIR
     */
    public void checkOperator(SManagerIRPartyReq mgntIR)
    {
        if(mgntIR.getOperator_id() != null)
        {
        	RsSysIntlOperInfo operInfo = querySingle(RsSysIntlOperInfo.class, new DBCondition(RsSysIntlOperInfo.Field.id,mgntIR.getOperator_id()));
        	if(operInfo == null)
        	{
        		throw IMSUtil.throwBusiException(ErrorCodeDefine.OPERATOR_MAP_ERROR, mgntIR.getOperator_id());
        	}
        	else
        	{
        	    mgntIR.setOperator_code(operInfo.getOperatorCode());
        	    RsSysIntlOperRel operRel = querySingle(RsSysIntlOperRel.class, 
                        new DBCondition(RsSysIntlOperRel.Field.operatorCode,mgntIR.getOperator_code()),
                        new DBCondition(RsSysIntlOperRel.Field.userOperatorId,mgntIR.getInvoicingCoID()));
                if(operRel == null){
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.OPERATOR_MAP_ERROR, mgntIR.getOperator_id());
                }
        	}
        }
    }

}