 package com.ailk.ims.xml.idmap;                                                                                                                                                                                                                                                                       

import com.ailk.ims.xml.BaseNode;
       

/**
 * @Description: 对应ims-conf/idmap.xml中的maps节点             
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class MapNode extends BaseNode {
	private String inner;
	private String outer;
	private Boolean isPrimary;//是否主键映射。默认为false.为true的时候说明是主键值，则会创建映射关系
	private int type;//1-cust,2-acct,3-group,90-user
	private Boolean nullable;//该映射是否允许全部为空。默认为false.为true的时候则至少有一个不能为空
	private Boolean createMapping;//是否需要创建内外id的匹配记录；只对isPrimary=true的基础上有效，默认true
	private String dbfield;
	
	public String getDbfield()
    {
        return dbfield;
    }
    public void setDbfield(String dbfield)
    {
        this.dbfield = dbfield;
    }
    public String getInner() {
		return inner;
	}
	public void setInner(String inner) {
		this.inner = inner;
	}
	public String getOuter() {
		return outer;
	}
	public void setOuter(String outer) {
		this.outer = outer;
	}
	public Boolean getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isPrimary(){
		return isPrimary != null && isPrimary.booleanValue();
	}
	public Boolean getNullable() {
		return nullable;
	}
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}
	public boolean isNullable() {
		return nullable != null && nullable.booleanValue();
	}
	public Boolean getCreateMapping() {
		return createMapping;
	}
	public void setCreateMapping(Boolean createMapping) {
		this.createMapping = createMapping;
	}
	public boolean isCreateMapping(){
		return createMapping == null || createMapping.booleanValue();
	}
}
