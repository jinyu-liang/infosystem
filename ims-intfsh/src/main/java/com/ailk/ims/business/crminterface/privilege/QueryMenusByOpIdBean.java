package com.ailk.ims.business.crminterface.privilege;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.crminterface.bo.MenuInfo;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryMenusByOpIdResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 根据操作员编号查询所有菜单权限
 * 
 * @Description
 * @author xieqr
 * @Date 2012-5-7
 */
public class QueryMenusByOpIdBean extends BaseBusiBean
{
    private String opId;

    @Override
    public void init(Object... input) throws BaseException
    {
        opId = (String) input[0];

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (CommonUtil.isEmpty(opId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "opId");
        }

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("OpId", opId);
        long doneCode = this.getContext().getDoneCode();

        List<MenuInfo> menuList = HttpJsonToCrmService.getFunctionsByOpId(String.valueOf(doneCode), opId, busiParams);
        return new Object[] { menuList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryMenusByOpIdResponse resp = new Do_queryMenusByOpIdResponse();
        List<MenuInfo> menuList = (List<MenuInfo>) result[0];
        resp.setMenuList(menuList);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    // /**
    // * 解析json 返回的数据 封装成List<MenuInfo> xieqr 2012-5-18
    // *
    // * @param responseInfo
    // * @return
    // */
    // private List<MenuInfo> getMenuInfoList(ResponseInfo responseInfo)
    // {
    // if (responseInfo == null)
    // {
    // return null;
    // }
    // List<MenuInfo> menuInfoList = null;
    // Map retInfo = responseInfo.getRetInfo();
    // if (retInfo != null && retInfo.get("MenuInfo") != null)
    // {
    // List<Map> infoList = (List<Map>) retInfo.get("MenuInfo");
    // if (infoList != null && infoList.size() > 0)
    // {
    // menuInfoList = new ArrayList<MenuInfo>();
    // for (Map map : infoList)
    // {
    // if (map != null)
    // {
    // MenuInfo info = new MenuInfo();
    // if (map.get("FuncId") != null)
    // info.setFuncId((String) map.get("FuncId"));
    // if (map.get("EntClassId") != null)
    // info.setEntClassId((String) map.get("EntClassId"));
    // if (map.get("FuncCode") != null)
    // info.setFuncCode((String) map.get("FuncCode"));
    // if (map.get("Name") != null)
    // info.setName((String) map.get("Name"));
    // if (map.get("DomainId") != null)
    // info.setDomainId((String) map.get("DomainId"));
    // if (map.get("Notes") != null)
    // info.setNotes((String) map.get("Notes"));
    // if (map.get("ParentId") != null)
    // info.setParentId((String) map.get("ParentId"));
    // if (map.get("FuncLevel") != null)
    // info.setFuncLevel((String) map.get("FuncLevel"));
    // if (map.get("FuncSeq") != null)
    // info.setFuncSeq((String) map.get("FuncSeq"));
    // if (map.get("ViewName") != null)
    // info.setViewName((String) map.get("ViewName"));
    // if (map.get("DllPath") != null)
    // info.setDllPath((String) map.get("DllPath"));
    // if (map.get("FuncImg") != null)
    // info.setFuncImg((String) map.get("FuncImg"));
    // if (map.get("FuncArg") != null)
    // info.setFuncArg((String) map.get("FuncArg"));
    // if (map.get("FuncType") != null)
    // info.setFuncType((String) map.get("FuncType"));
    // if (map.get("VerifyMode") != null)
    // info.setVerifyMode((String) map.get("VerifyMode"));
    // if (map.get("LoginMode") != null)
    // info.setLoginMode((String) map.get("LoginMode"));
    // if (map.get("BusiType") != null)
    // info.setBusiType((String) map.get("BusiType"));
    // if (map.get("BusiScene") != null)
    // info.setBusiScene((String) map.get("BusiScene"));
    // if (map.get("ModuleType") != null)
    // info.setModuleType((String) map.get("ModuleType"));
    // if (map.get("ModuleEntId") != null)
    // info.setModuleEntId((String) map.get("ModuleEntId"));
    // if (map.get("HelpUrl") != null)
    // info.setHelpUrl((String) map.get("HelpUrl"));
    // if (map.get("Entrance") != null)
    // info.setEntrance((String) map.get("Entrance"));
    // if (map.get("DispType") != null)
    // info.setDispType((String) map.get("DispType"));
    // if (map.get("AuditLevel") != null)
    // info.setAuditLevel((String) map.get("AuditLevel"));
    // if (map.get("AuditFlag") != null)
    // info.setAuditFlag((String) map.get("AuditFlag"));
    // if (map.get("State") != null)
    // info.setState((String) map.get("State"));
    // if (map.get("Ext1") != null)
    // info.setExt1((String) map.get("Ext1"));
    // if (map.get("Ext2") != null)
    // info.setExt2((String) map.get("Ext2"));
    //
    // menuInfoList.add(info);
    // }
    // }
    // }
    // }
    //
    // return menuInfoList;
    // }

}
