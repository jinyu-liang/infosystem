package com.ailk.imssh.user.cmp;

import java.util.Date;
import java.util.List;




import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.StringUtils;

import com.ailk.easyframe.route.entity.br.RouterResource;
import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.credit.cmp.CreditCmp;
import com.ailk.imssh.user.handler.UserAcctRelHandler;
import com.ailk.imssh.user.handler.UserHandler;
import com.ailk.openbilling.persistence.acct.entity.CaAssetSyn;
import com.ailk.openbilling.persistence.acct.entity.CaBatchSyn;
import com.ailk.openbilling.persistence.cust.entity.CmIdentityVsImei;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CmRouteIdentity;
import com.ailk.openbilling.persistence.itable.entity.IIdentityBound;
import com.ailk.openbilling.persistence.itable.entity.IUser;
import com.ailk.openbilling.persistence.itable.entity.IUserAcctRel;

/**
 * 用户资料相关i表 与模型表之间的转化
 * 
 * @author wukl
 * @Date 2012-5-12
 * @Date 2012-09-04 wukl User Story (#57245): 关键人物属性的资料同步'
 * @Date 2012-11-05 wukl 只有用户进行品牌更换，才需要考虑用户有效期的变更
 * @2012-12-04 wukl 当imsi为空或者0时，将手机号码左去0填入该字段
 * @Date 2012-12-27 wukl 增加这层路由查询判断，是为了避免开户时用户路由没发布导致的，下面三个操作的全表扫描
 */
public class UserCmp extends UserQuery
{

	private String specialTableName="CD.CM_ROUTE_IDENTITY";
	private Long getNewRegAcctId(Long userId){
		Long acctId = null;
    	List<? extends DataObject>[] arr = context.getIDataObjectList(UserAcctRelHandler.class);
    	if (CommonUtil.isNotEmpty(arr)){
    		List<IUserAcctRel> relList = (List<IUserAcctRel>) arr[0];
    		if(CommonUtil.isNotEmpty(relList)){
    			for(IUserAcctRel rel : relList){
    				if(rel.getUserId() == userId.longValue()){
    					acctId = rel.getAcctId();
    					break;
    				}
    			}
    		}
    	}
    	return acctId;
	}
    /**
     * lijc3 2012-10-9 发布用户路由
     * 
     * @param iUser
     */
    private Integer createUserRouter(IUser iUser)
    {
    	if(StringUtils.isNotBlank(iUser.getPhoneId())){
    		iUser.setPhoneId(iUser.getPhoneId().replaceAll("^(0+)", ""));
    	}
        RouterCmp routCmp = context.getCmp(RouterCmp.class);
        // 如果启用路由mdb，则这里需要修改成新模式
        RouteResult rlt = null;
        try
        {
            rlt = routCmp.queryUserRouter(iUser.getUserId());
        }
        catch (Exception e)
        {
            rlt = null;
        }
        if (rlt == null)
        {
        	
        	Long acctId = getNewRegAcctId(iUser.getUserId());
            if (acctId == null)
            {
                routCmp.createUserRouter(iUser.getUserId(), context.getAcctId(), null,null,
                        iUser.getValidDate(), iUser.getExpireDate());
            }
            else
            {
                routCmp.createUserRouter(iUser.getUserId(), acctId, null,null,
                        iUser.getValidDate(), iUser.getExpireDate());
            }
        }
        return rlt == null ? null : rlt.getRouteDimension().getRegionCode();
    }

    /**
     * 用户资料表I_USER 数据通过逻辑转换，保存到数据库对应表中<br>
     * wukl 2012-5-12
     * 
     * @param iUser
     */
    public void createUser(IUser iUser)
    {
        // 发布路由信息
        //Integer regionCode = createUserRouter(iUser);
        this.insertCmResource(iUser);
        // 2012-12-27 gaoqc5 #62685 手机号码存放在 identity字段 ,smsi 存放在 resIdentity字段
        //insertCmResIdentity(iUser);
        // 创建用户扩展信息：目前只有入网方式
        // this.insertCmResExt(iUser);
        this.insertCmRouteIdentity(iUser);
       
    }

    /**
     * 用户资料表I_USER 数据通过逻辑转换，从数据库对应表中删除<br>
     * wukl 2012-5-12
     * 
     * @param iUser
     */
    public void deleteUser(IUser iUser)
    {
    	Date expireDate = iUser.getExpireDate();
		expireDate = jef.tools.DateUtils.monthBegin(expireDate);
		jef.tools.DateUtils.addMonth(expireDate, 1);
        this.deleteCmResource(iUser,expireDate);
        // 删除 资源属性表 ，包括手机号码、Imsi
        //this.deleteCmResIdentity(iUser);
        // this.deleteCmResExt(iUser);
        int partitionId = (int)(iUser.getUserId()%10000);
        this.deleteCmRouteIdentity(iUser,expireDate,partitionId);
    }

    /**
     * 用户资料表I_USER 数据通过逻辑转换，修改到数据库对应表中<br>
     * wukl 2012-5-12
     * 
     * @param iUser
     */
    public void modifyUser(IUser iUser)
    {
    	if(StringUtils.isNotBlank(iUser.getPhoneId())){
    		iUser.setPhoneId(iUser.getPhoneId().replaceAll("^(0+)", ""));
    	}
        this.updateCmResource(iUser);
    
        // 修改手机号码标识
        //this.updateCmResIdentity(iUser);
        //更新 CmRouteIdentity
        this.updateCmRouteIdentity(iUser);
    }

    private Date getRelExpireDate(IUser iUser)
    {
        Date expireDate = iUser.getExpireDate();
        List<? extends DataObject>[] userArr = context.getIDataObjectList(UserHandler.class);
        if (CommonUtil.isNotEmpty(userArr) && CommonUtil.isNotEmpty(userArr[0]))
        {
            List<IUser> userList = (List<IUser>) userArr[0];
            for (IUser user : userList)
            {
                if (user.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT)
                {
                    expireDate = user.getExpireDate();
                    break;
                }
            }
        }
        return expireDate;
    }
    
    
    /**
     * 
     * 2016-09-06
     * 信控新增
     * @param iUser
     */
    private CmRouteIdentity createCmRouteIdentity(IUser iUser){
    	
    	CmRouteIdentity  cmRouteIdentity = new CmRouteIdentity();
    	cmRouteIdentity.setCountyCode(iUser.getCountyCode());
        // 客户标识（用户的客户）
     	cmRouteIdentity.setCustId(iUser.getCustId());
	   	cmRouteIdentity.setValidDate(iUser.getValidDate());   	
    	cmRouteIdentity.setExpireDate(iUser.getExpireDate());
    	cmRouteIdentity.setIdentityId(iUser.getPhoneId());
    	cmRouteIdentity.setIdType(EnumCodeExDefine.CM_ROUTE_IDENTITY_ID_TYPE);
    	cmRouteIdentity.setRegionCode(iUser.getRegionCode());
    	cmRouteIdentity.setPrepayTag(String.valueOf(iUser.getPaymentMode()));
    	cmRouteIdentity.setResourceId(iUser.getUserId());
    	cmRouteIdentity.setPartitionId((int)(iUser.getUserId()%10000));
    	return cmRouteIdentity;
    }
    
 
    private void insertCmRouteIdentity(IUser iUser){
    	CmRouteIdentity cmRouteIdentity = this.createCmRouteIdentity(iUser);
    	//this.insert(cmRouteIdentity);
    	DBUtil.insertWithName(cmRouteIdentity, dbkey[0], specialTableName);
    	DBUtil.insertWithName(cmRouteIdentity, dbkey[1], specialTableName);
    }
    
    private void updateCmRouteIdentity(IUser iUser){
    	CmRouteIdentity cmRouteIdentity = this.createCmRouteIdentity(iUser);
    	//this.updateByCondition(cmRouteIdentity, new DBCondition(CmRouteIdentity.Field.resourceId,iUser.getUserId()));
    	updateByNameWithNoCache(iUser, cmRouteIdentity,specialTableName);
    }
    
 
	private void updateByNameWithNoCache(IUser iUser, CmRouteIdentity cmRouteIdentity,String specialTableName ) {
		this.updateWithInsertBykey(cmRouteIdentity, null, null, dbkey[0], specialTableName, new DBCondition(CmRouteIdentity.Field.resourceId,iUser.getUserId()),
				new DBCondition(CmRouteIdentity.Field.partitionId,cmRouteIdentity.getPartitionId()));
    	this.updateWithInsertBykey(cmRouteIdentity, null, null, dbkey[1], specialTableName, new DBCondition(CmRouteIdentity.Field.resourceId,iUser.getUserId()),
    			new DBCondition(CmRouteIdentity.Field.partitionId,cmRouteIdentity.getPartitionId()));
	}
    
   
    private void deleteCmRouteIdentity(IUser iUser,Date expireDate,int partitionId)
    {
    	//this.deleteByCondition(CmRouteIdentity.class, new DBCondition(CmRouteIdentity.Field.resourceId,iUser.getUserId()));
    	this.deleteByName_noInsert(CmRouteIdentity.class, dbkey[0], specialTableName, expireDate,  
    			new DBCondition(CmRouteIdentity.Field.resourceId,iUser.getUserId()),
    			new DBCondition(CmRouteIdentity.Field.partitionId,partitionId));
    	this.deleteByName_noInsert(CmRouteIdentity.class, dbkey[1], specialTableName, expireDate,  
    			new DBCondition(CmRouteIdentity.Field.resourceId,iUser.getUserId()),
    			new DBCondition(CmRouteIdentity.Field.partitionId,partitionId));
    }


    /**
     * 创建用户终端资料<br>
     * wukl 2012-5-17
     * 
     * @param iIdentityBound
     */
    public void createUserBound(IIdentityBound iIdentityBound)
    {
        this.insertCmIdentityVsImei(iIdentityBound);

    }

    /**
     * 修改用户终端资料<br>
     * wukl 2012-5-17
     * 
     * @param iIdentityBound
     */
    public void modifyUserBound(IIdentityBound iIdentityBound)
    {
        this.updateCmIdentityVsImei(iIdentityBound);
    }

    /**
     * 删除用户终端资料<br>
     * wukl 2012-5-17
     * 
     * @param iIdentityBound
     */
    public void deleteUserBound(IIdentityBound iIdentityBound)
    {
        this.deleteCmIdentityVsImei(iIdentityBound);

    }

    private void deleteCmIdentityVsImei(IIdentityBound iIdentityBound)
    {
        super.deleteByCondition(CmIdentityVsImei.class,
                new DBCondition(CmIdentityVsImei.Field.resourceId, iIdentityBound.getUserId()));

    }

    /**
     * 修改用户终端资料信息<br>
     * wukl 2012-5-19
     * 
     * @param iIdentityBound
     */
    private void updateCmIdentityVsImei(IIdentityBound iIdentityBound)
    {
        CmIdentityVsImei cmIdentityVsImei = new CmIdentityVsImei();
        cmIdentityVsImei.setResourceId(iIdentityBound.getUserId());
        // cmIdentityVsImei.setValidDate(iIdentityBound.getValidDate());
        cmIdentityVsImei.setExpireDate(iIdentityBound.getExpireDate());
        cmIdentityVsImei.setSoNbr(context.getSoNbr());
        // cmIdentityVsImei.setCreateDate(context.getCreateDate());
        cmIdentityVsImei.setSoDate(context.getCommitDate());
        cmIdentityVsImei.setIdentity(iIdentityBound.getIdentity());
        cmIdentityVsImei.setIdentityType(iIdentityBound.getIdentityType());
        cmIdentityVsImei.setImei(iIdentityBound.getImei());
        cmIdentityVsImei.setForceBinding(iIdentityBound.getForceBinding());
        super.updateByCondition(cmIdentityVsImei, new DBCondition(CmIdentityVsImei.Field.resourceId, iIdentityBound.getUserId()));
    }

    /**
     * 新增用户终端资料信息<br>
     * wukl 2012-5-19
     * 
     * @param iIdentityBound
     */
    private void insertCmIdentityVsImei(IIdentityBound iIdentityBound)
    {
        CmIdentityVsImei cmIdentityVsImei = new CmIdentityVsImei();
        cmIdentityVsImei.setResourceId(iIdentityBound.getUserId());
        cmIdentityVsImei.setValidDate(iIdentityBound.getValidDate());
        cmIdentityVsImei.setExpireDate(iIdentityBound.getExpireDate());
        cmIdentityVsImei.setSoNbr(context.getSoNbr());
        cmIdentityVsImei.setCreateDate(context.getCommitDate());
        cmIdentityVsImei.setSoDate(context.getCommitDate());
        cmIdentityVsImei.setIdentity(iIdentityBound.getIdentity());
        cmIdentityVsImei.setIdentityType(iIdentityBound.getIdentityType());
        cmIdentityVsImei.setImei(iIdentityBound.getImei());
        cmIdentityVsImei.setForceBinding(iIdentityBound.getForceBinding());
        super.insert(cmIdentityVsImei);
    }

    /**
     * 删除用户的资源标识，包括手机号、imsi <br>
     * wukl 2012-5-17
     * 
     * @Date 2012-11-02 gaoqc5 只删除该用户的主号码
     * @param iUser
     */
    private void deleteCmResIdentity(IUser iUser)
    {
        this.deleteByCondition(CmResIdentity.class, iUser.getExpireDate(),
                new DBCondition(CmResIdentity.Field.resourceId, iUser.getUserId()), new DBCondition(
                        CmResIdentity.Field.identityAttr, EnumCodeExDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER));

    }

    // /**
    // * 删除用户的imsi信息 <br>
    // * wukl 2012-5-17
    // *
    // * @param iUser
    // */
    // private void deleteCmResIdentityByImsi(IUser iUser)
    // {
    // this.deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId, iUser.getUserId()),
    // new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_IMSI));
    // }

    
    private CmResource convertUser(IUser iUser){
    	CmResource cmResource = new CmResource();
         cmResource.setCreateDate(context.getCommitDate());
         cmResource.setSoDate(context.getCommitDate());
         cmResource.setResourceId(iUser.getUserId());
         cmResource.setResourceSpecId(iUser.getUserType());
         cmResource.setBillingType(EnumCodeDefine.BILLING_TYPE_END_MONTH);
         cmResource.setActiveDate(iUser.getActiveDate());
         cmResource.setBrandId(iUser.getBrand());
         // 携号跨区特殊处理
         // if (regionCode != null)
         // {
         // cmResource.setRegionCode(regionCode);
         // }
         // else
         // {
         cmResource.setRegionCode(iUser.getRegionCode());
         // }
         cmResource.setCountyCode(iUser.getCountyCode());
         cmResource.setReadingLanguage(iUser.getSmsLanguage());
         cmResource.setListeningLanguage(iUser.getIvrLanguage());
         cmResource.setWritingLanguage(iUser.getUssdLanguage());
         cmResource.setValidDate(iUser.getValidDate());
         cmResource.setExpireDate(iUser.getExpireDate());
         // cmResource.setRemarks(iUser.getRemarks());
         cmResource.setSoNbr(context.getSoNbr());
         cmResource.setResSegment(-1); // CRM新增的时候不带用户星级 

         // cmResource.setDeductFlag(iUser.getDeductFlag());
         // cmResource.setFirstBillDate(iUser.getFirstBillDate());
         cmResource.setUserCode(iUser.getUserCode());
         if (iUser.getIsSendCard() != null)
             cmResource.setIsSendCard(iUser.getIsSendCard());
         else
             cmResource.setIsSendCard(0);

         if (iUser.getResClass() != null)
             cmResource.setResClass(iUser.getResClass());
         else
             cmResource.setResClass(0);
         /*
          * if (SysUtil.getInt(SysCodeDefine.busi.DEFAULT_CONTINUE_FLAG) != null)
          * cmResource.setContinueFlag(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_CONTINUE_FLAG)); else
          * cmResource.setContinueFlag(0); if (SysUtil.getInt(SysCodeDefine.busi.DEFAULT_FEEL_LIKE_HOME_FLAG) != null)
          * cmResource.setFlhFlag(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_FEEL_LIKE_HOME_FLAG)); else cmResource.setFlhFlag(0);
          */
         // 广西存储：
         // i_user表的is_test_number -> 对应用户MDB字段：mint2 m_nFlhFlag;
         // i_user的user_type ->对应用户MDB字段： mint2 user_type
         cmResource.setContinueFlag(iUser.getUserType());
         cmResource.setFlhFlag(iUser.getUserCode());
         cmResource.setPaymentMode(iUser.getPaymentMode());
         cmResource.setDummyTag(iUser.getDummyTag());
         cmResource.setSerialNumber(iUser.getSerialNumber());
         cmResource.setChangeuserDate(iUser.getChangeuserDate());
         // cmResource.setEmail(iUser.getEmail());
         return cmResource;
    }
    /**
     * 新增用户信息<br>
     * wukl 2012-5-17
     * 
     * @param iUser
     */
    private void insertCmResource(IUser iUser)
    {
    	CmResource cmResource = convertUser(iUser);
        super.insertWithAllCache(cmResource,new DBCondition(CmResource.Field.resourceId,cmResource.getResourceId()));

    }

    /**
     * 删除用户资料信息 <br>
     * wukl 2012-5-17
     * 
     * @param iUser
     */
    private void deleteCmResource(IUser iUser,Date expireDate)
    {
        super.deleteMode1(CmResource.class,expireDate,new DBCondition(CmResource.Field.resourceId, iUser.getUserId()));
    }

    /**
     * 删除用户imsi信息 <br>
     * wukl 2012-5-17
     * 
     * @param iUser
     */
    // private void updateCmResIdentityByImsi(IUser iUser)
    // {
    // this.updateCmResIdentity(iUser, iUser.getImsi(), EnumCodeDefine.RESOURCE_IDENTITYTYPE_IMSI);
    //
    // }

    /**
     * 删除用户手机号码信息 <br>
     * wukl 2012-5-17
     * 
     * @param iUser
     */
    // private void updateCmResIdentityByPhone(IUser iUser)
    // {
    // this.updateCmResIdentity(iUser, iUser.getPhoneId(), EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);
    //
    // }

    /**
     * 修改用户信息 <br>
     * wukl 2012-5-17
     * 
     * @param iUser
     * @param identity
     * @param identityType
     */
    private void updateCmResIdentity(IUser iUser)
    {
        CmResIdentity cmResIdentity = new CmResIdentity();
        // cmResIdentity.setCreateDate(iUser.getCommitDate());
        cmResIdentity.setSoDate(context.getCommitDate());
        cmResIdentity.setResourceId(iUser.getUserId());
        cmResIdentity.setIdentity(iUser.getPhoneId());
        // 2012-11-02 gaoqc5 #62685 对于上海项目该表中，只会存在IDENTITY_TYPE=0的数据记录
        cmResIdentity.setIdentityType(0);
        // cmResIdentity.setIdentityAttr(iUser.getIdentityAttr());
        // cmResIdentity.setResourcePasswd(iUser.getResourcePasswd());
        // cmResIdentity.setValidDate(iUser.getValidDate());
        cmResIdentity.setExpireDate(iUser.getExpireDate());
        cmResIdentity.setSoNbr(context.getSoNbr());
        // 2012-11-02 gaoqc5 imsi 放在 relIdentity 字段
        /*
         * if (CommonUtil.isNotEmpty(iUser.getImsi()) && !"0".equals(iUser.getImsi().trim())) {
         * cmResIdentity.setRelIdentity(iUser.getImsi()); } else { // @2012-12-04 wukl 当imsi为空或者0时，将手机号码左去0填入该字段 long numIden =
         * Long.parseLong(iUser.getPhoneId()); cmResIdentity.setRelIdentity(String.valueOf(numIden)); }
         */
        cmResIdentity.setRelIdentity(getImsi(iUser));

        super.updateByCondition(cmResIdentity, new DBCondition(CmResIdentity.Field.resourceId, iUser.getUserId()),
                new DBCondition(CmResIdentity.Field.identityAttr, EnumCodeExDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER));
    }

    /**
     * 修改用户资料表 <br>
     * wukl 2012-5-17
     * 
     * @param iUser
     */
    private void updateCmResource(IUser iUser)
    {

        CmResource cmResource = convertUser(iUser);
        this.updateMode1(cmResource, new DBCondition(CmResource.Field.resourceId,iUser.getUserId()));
    }

    /**
     * 增加用户手机号码资料<br>
     * wukl 2012-5-17
     * 
     * @param iUser
     */
    // private void insertCmResIdentityByPhone(IUser iUser)
    // {
    // this.insertCmResIdentity(iUser, iUser.getPhoneId(), EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);
    // }

    /**
     * 增加用户imsi资料 <br>
     * wukl 2012-5-17
     * 
     * @param iUser
     */
    // private void insertCmResIdentityByImsi(IUser iUser)
    // {
    // this.insertCmResIdentity(iUser, iUser.getImsi(), EnumCodeDefine.RESOURCE_IDENTITYTYPE_IMSI);
    // }

    /**
     * 增加用户资源标识属性表 <br>
     * wukl 2012-5-17 2012-11-02 gaoqc5 imsi 放在 relIdentity 字段
     */
    private void insertCmResIdentity(IUser iUser)
    {
        CmResIdentity cmResIdentity = new CmResIdentity();
        cmResIdentity.setCreateDate(iUser.getCommitDate());
        cmResIdentity.setSoDate(iUser.getCommitDate());
        cmResIdentity.setResourceId(iUser.getUserId());
        cmResIdentity.setIdentity(iUser.getPhoneId());
        // 2012-11-02 gaoqc5 #62685 对于上海项目该表中，只会存在IDENTITY_TYPE=0的数据记录
        cmResIdentity.setIdentityType(0);
        cmResIdentity.setIdentityAttr(EnumCodeExDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER);
        // cmResIdentity.setResourcePasswd(iUser.getResourcePasswd());
        cmResIdentity.setValidDate(iUser.getValidDate());
        cmResIdentity.setExpireDate(iUser.getExpireDate());
        cmResIdentity.setSoNbr(context.getSoNbr());
        // 2012-11-02 gaoqc5 imsi 放在 relIdentity 字段
        /*
         * if (CommonUtil.isNotEmpty(iUser.getImsi()) && !"0".equals(iUser.getImsi().trim())) {
         * cmResIdentity.setRelIdentity(iUser.getImsi()); } else { // @2012-12-04 wukl 当imsi为空或者0时，将手机号码左去0填入该字段 long numIden =
         * Long.parseLong(iUser.getPhoneId()); cmResIdentity.setRelIdentity(String.valueOf(numIden)); }
         */
        cmResIdentity.setRelIdentity(getImsi(iUser));
        super.insert(cmResIdentity);
    }

    
    public String getImsi(IUser iUser)
    {
        	// 左边去0
        String imsi = StringUtils.ltrim(iUser.getPhoneId(), new char[] { '0' });
        return imsi;
    }
	
    /**
     * 该方法主要针对下周期生效的数据未生效前又进行变更的场景；此时先删除未来生效的数据
     * 
     * @param dataList
     * @Author: wukl
     * @Date: 2012-9-6
     */
    public void beforeHandle(List<? extends DataObject> dataList)
    {


        // 过户时索引中的账户为过户下家，这样就会错误的从新的分表中查询，所以先清一把SESSION中的分表参数
        ITableUtil.cleanRequestParamter();

        // 将用户相关的三张表中未来生效数据置为失效
        IUser iUser = (IUser) dataList.get(0);
        // @Date 2012-12-27 wukl 增加这层路由查询判断，是为了避免开户时用户路由没发布导致的，下面三个操作的全表扫描
        try
        {
            boolean isExist = context.getCmp(RouterCmp.class).isUserRouted(iUser.getUserId());
            if (!isExist)
            {
                return;
            }
        }
        catch (Exception e)
        {
            // 此处抛异常属于正常，不用处理
            return;
        }
        
        Date expireDate = context.getCommitDate();
        delete_noValid(CmResource.class, new DBCondition(CmResource.Field.resourceId, iUser.getUserId()), new DBCondition(
                CmResource.Field.validDate, expireDate, Operator.GREAT), new DBCondition(CmResource.Field.expireDate, expireDate,
                Operator.GREAT));

    }

    /**
     * 判断是否I_USER记录由账管插入，如果是只更新字段User_Segment(本字段广西代表用户级) .
     * 
     * @return true代表记录由账管插入，只更新User_Segment星级值 .
     */
    public final boolean isOnlyUpdateUserSegment()
    {
        /**
    	if (context.getBusiCode() != null && context.getBusiCode() == EnumCodeExDefine.USER_SEG_BUSI_CODE)
        {
            return true;
        }
        */
    	String soNbr = StringUtils.toString(context.getSoNbr());
    	if(soNbr.substring(soNbr.length()-2, soNbr.length()-1).equals(ConstantDefine.CC_UPDATE)){
    		return true;
    	}
        return false;
    }
    /**
     * 更新用户星级值
     * 
     * @param iUser
     */

    public final void updateCmResourceResSegment(final IUser iUser)
    {

        CmResource cmResource = new CmResource();

        cmResource.setResSegment(iUser.getUserSegment());
        cmResource.setSoNbr(context.getSoNbr());

        List<CmResource> updateList = updateMode1Segment(cmResource, new DBCondition(CmResource.Field.resourceId, iUser.getUserId()));
        if(CommonUtil.isNotEmpty(updateList)){
        	createCaAssetSyn(iUser);
        }
        
    }
    
    private void createCaAssetSyn(IUser iUser){

 
         RouterCmp routerCmp = context.getCmp(RouterCmp.class);
	     RouterResource resource = routerCmp.querySegAcctByUser(iUser.getUserId());
        	 CaBatchSyn caAssetSyn = new CaBatchSyn();
             caAssetSyn.setSynId(DBUtil.getSequence(CaAssetSyn.class)); 
             caAssetSyn.setAcctId(resource.getAcctId());
             caAssetSyn.setAcctFlag(0);
     		caAssetSyn.setBizeType(0);
     		caAssetSyn.setBizeInfo(CreditCmp.BIZE_INFO);
           
             caAssetSyn.setCreateDate(context.getCommitDate());
             caAssetSyn.setValidDate(iUser.getValidDate());
             caAssetSyn.setExpireDate(iUser.getExpireDate());
             caAssetSyn.setSts(0);
             caAssetSyn.setStsDate(context.getCommitDate());
             caAssetSyn.setSoNbr(context.getSoNbr());
             caAssetSyn.setSyncFlag(CreditCmp.SYNC_FLAG);
                 
                 //信用度上发触发信控 江西也需要触发信控 
             caAssetSyn.setNotifyFlag(CreditCmp.NOTIFY_FLAG);
             caAssetSyn.setSyncType(CreditCmp.SYNC_TYPE_USER);
             caAssetSyn.setSoDate(context.getCommitDate());

             caAssetSyn.setRegionCode(EnumCodeExDefine.TJ_REGION_CODE);
             super.insert(caAssetSyn);
         
    }

}
