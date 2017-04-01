package com.ailk.ims.xml.dbrel;

import com.ailk.ims.xml.BaseNode;
/**
 * @Description: 对应ims-conf/dbrel.xml中的field节点                  
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class FieldNode extends BaseNode{
	private String root;
	private String rel;
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
}