package com.ailk.ims.business.crminterface.bo;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import jef.codegen.support.NotModified;
import com.ailk.easyframe.web.common.dal.IComplexEntity;

/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "FuncId", "EntClassId", "FuncCode", "Name", "DomainId", "Notes", "ParentId", "FuncLevel", "FuncSeq",
        "ViewName", "DllPath", "FuncImg", "FuncArg", "FuncType", "VerifyMode", "LoginMode", "BusiType", "BusiScene",
        "ModuleType", "ModuleEntId", "HelpUrl", "Entrance", "DispType", "AuditLevel", "AuditFlag", "State", "Ext1", "Ext2" })
public class MenuInfo implements IComplexEntity
{

    @XmlElement(name = "FuncId")
    private String FuncId;

    @XmlElement(name = "EntClassId")
    private String EntClassId;

    @XmlElement(name = "FuncCode")
    private String FuncCode;

    @XmlElement(name = "Name")
    private String Name;

    @XmlElement(name = "DomainId")
    private String DomainId;

    @XmlElement(name = "Notes")
    private String Notes;

    @XmlElement(name = "ParentId")
    private String ParentId;

    @XmlElement(name = "FuncLevel")
    private String FuncLevel;

    @XmlElement(name = "FuncSeq")
    private String FuncSeq;

    @XmlElement(name = "ViewName")
    private String ViewName;

    @XmlElement(name = "DllPath")
    private String DllPath;

    @XmlElement(name = "FuncImg")
    private String FuncImg;

    @XmlElement(name = "FuncArg")
    private String FuncArg;

    @XmlElement(name = "FuncType")
    private String FuncType;

    @XmlElement(name = "VerifyMode")
    private String VerifyMode;

    @XmlElement(name = "LoginMode")
    private String LoginMode;

    @XmlElement(name = "BusiType")
    private String BusiType;

    @XmlElement(name = "BusiScene")
    private String BusiScene;

    @XmlElement(name = "ModuleType")
    private String ModuleType;

    @XmlElement(name = "ModuleEntId")
    private String ModuleEntId;

    @XmlElement(name = "HelpUrl")
    private String HelpUrl;

    @XmlElement(name = "Entrance")
    private String Entrance;

    @XmlElement(name = "DispType")
    private String DispType;

    @XmlElement(name = "AuditLevel")
    private String AuditLevel;

    @XmlElement(name = "AuditFlag")
    private String AuditFlag;

    @XmlElement(name = "State")
    private String State;

    @XmlElement(name = "Ext1")
    private String Ext1;

    @XmlElement(name = "Ext2")
    private String Ext2;

    public String getFuncId()
    {
        return FuncId;
    }

    public void setFuncId(String funcId)
    {
        FuncId = funcId;
    }

    public String getEntClassId()
    {
        return EntClassId;
    }

    public void setEntClassId(String entClassId)
    {
        EntClassId = entClassId;
    }

    public String getFuncCode()
    {
        return FuncCode;
    }

    public void setFuncCode(String funcCode)
    {
        FuncCode = funcCode;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getDomainId()
    {
        return DomainId;
    }

    public void setDomainId(String domainId)
    {
        DomainId = domainId;
    }

    public String getNotes()
    {
        return Notes;
    }

    public void setNotes(String notes)
    {
        Notes = notes;
    }

    public String getParentId()
    {
        return ParentId;
    }

    public void setParentId(String parentId)
    {
        ParentId = parentId;
    }

    public String getFuncLevel()
    {
        return FuncLevel;
    }

    public void setFuncLevel(String funcLevel)
    {
        FuncLevel = funcLevel;
    }

    public String getFuncSeq()
    {
        return FuncSeq;
    }

    public void setFuncSeq(String funcSeq)
    {
        FuncSeq = funcSeq;
    }

    public String getViewName()
    {
        return ViewName;
    }

    public void setViewName(String viewName)
    {
        ViewName = viewName;
    }

    public String getDllPath()
    {
        return DllPath;
    }

    public void setDllPath(String dllPath)
    {
        DllPath = dllPath;
    }

    public String getFuncImg()
    {
        return FuncImg;
    }

    public void setFuncImg(String funcImg)
    {
        FuncImg = funcImg;
    }

    public String getFuncArg()
    {
        return FuncArg;
    }

    public void setFuncArg(String funcArg)
    {
        FuncArg = funcArg;
    }

    public String getFuncType()
    {
        return FuncType;
    }

    public void setFuncType(String funcType)
    {
        FuncType = funcType;
    }

    public String getVerifyMode()
    {
        return VerifyMode;
    }

    public void setVerifyMode(String verifyMode)
    {
        VerifyMode = verifyMode;
    }

    public String getLoginMode()
    {
        return LoginMode;
    }

    public void setLoginMode(String loginMode)
    {
        LoginMode = loginMode;
    }

    public String getBusiType()
    {
        return BusiType;
    }

    public void setBusiType(String busiType)
    {
        BusiType = busiType;
    }

    public String getBusiScene()
    {
        return BusiScene;
    }

    public void setBusiScene(String busiScene)
    {
        BusiScene = busiScene;
    }

    public String getModuleType()
    {
        return ModuleType;
    }

    public void setModuleType(String moduleType)
    {
        ModuleType = moduleType;
    }

    public String getModuleEntId()
    {
        return ModuleEntId;
    }

    public void setModuleEntId(String moduleEntId)
    {
        ModuleEntId = moduleEntId;
    }

    public String getHelpUrl()
    {
        return HelpUrl;
    }

    public void setHelpUrl(String helpUrl)
    {
        HelpUrl = helpUrl;
    }

    public String getEntrance()
    {
        return Entrance;
    }

    public void setEntrance(String entrance)
    {
        Entrance = entrance;
    }

    public String getDispType()
    {
        return DispType;
    }

    public void setDispType(String dispType)
    {
        DispType = dispType;
    }

    public String getAuditLevel()
    {
        return AuditLevel;
    }

    public void setAuditLevel(String auditLevel)
    {
        AuditLevel = auditLevel;
    }

    public String getAuditFlag()
    {
        return AuditFlag;
    }

    public void setAuditFlag(String auditFlag)
    {
        AuditFlag = auditFlag;
    }

    public String getState()
    {
        return State;
    }

    public void setState(String state)
    {
        State = state;
    }

    public String getExt1()
    {
        return Ext1;
    }

    public void setExt1(String ext1)
    {
        Ext1 = ext1;
    }

    public String getExt2()
    {
        return Ext2;
    }

    public void setExt2(String ext2)
    {
        Ext2 = ext2;
    }

}