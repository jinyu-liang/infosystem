package com.ailk.ims.xml.dbrel;

import com.ailk.ims.xml.BaseNode;
/**
 * @Description: 对应ims-conf/dbrel.xml中的group节点 
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class GroupNode extends BaseNode{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
