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
@XmlType(propOrder = { "RoleId", "EntId", "EntType", "RoleGrantId", "PrivId", "Notes", "ValidDate", "ExpireDate", "DoneDate",
        "State" })
public class RoleGrandInfo implements IComplexEntity
{

    @XmlElement(name = "RoleId")
    private String RoleId;

    @XmlElement(name = "EntId")
    private String EntId;

    @XmlElement(name = "EntType")
    private String EntType;

    @XmlElement(name = "RoleGrantId")
    private String RoleGrantId;

    @XmlElement(name = "PrivId")
    private String PrivId;

    @XmlElement(name = "Notes")
    private String Notes;

    @XmlElement(name = "ValidDate")
    private String ValidDate;

    @XmlElement(name = "ExpireDate")
    private String ExpireDate;

    @XmlElement(name = "DoneDate")
    private String DoneDate;

    @XmlElement(name = "State")
    private String State;

    public String getRoleId()
    {
        return RoleId;
    }

    public void setRoleId(String roleId)
    {
        RoleId = roleId;
    }

    public String getEntId()
    {
        return EntId;
    }

    public void setEntId(String entId)
    {
        EntId = entId;
    }

    public String getEntType()
    {
        return EntType;
    }

    public void setEntType(String entType)
    {
        EntType = entType;
    }

    public String getRoleGrantId()
    {
        return RoleGrantId;
    }

    public void setRoleGrantId(String roleGrantId)
    {
        RoleGrantId = roleGrantId;
    }

    public String getPrivId()
    {
        return PrivId;
    }

    public void setPrivId(String privId)
    {
        PrivId = privId;
    }

    public String getNotes()
    {
        return Notes;
    }

    public void setNotes(String notes)
    {
        Notes = notes;
    }

    public String getValidDate()
    {
        return ValidDate;
    }

    public void setValidDate(String validDate)
    {
        ValidDate = validDate;
    }

    public String getExpireDate()
    {
        return ExpireDate;
    }

    public void setExpireDate(String expireDate)
    {
        ExpireDate = expireDate;
    }

    public String getDoneDate()
    {
        return DoneDate;
    }

    public void setDoneDate(String doneDate)
    {
        DoneDate = doneDate;
    }

    public String getState()
    {
        return State;
    }

    public void setState(String state)
    {
        State = state;
    }

}