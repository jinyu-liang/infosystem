 package com.ailk.ims.xml.idmap;                                                                                                                                                                                                                                                                       

import com.ailk.ims.xml.BaseNode;
/**
 * @Description: 对应ims-conf/idmap.xml中的struct节点                      
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class StructNode extends BaseNode {
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
