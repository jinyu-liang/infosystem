package com.ailk.ims.xml.dbrel;

import java.util.ArrayList;
import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.xml.BaseNode;
/**
 * @Description: 对应ims-conf/dbrel.xml中的table节点       
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class TableNode extends BaseNode{
	public static final String TYPE_ONE_2_ONE = "one-2-one";
	public static final String TYPE_ONE_2_MANY = "one-2-many";
	public static final String TYPE_MANY_2_ONE = "many-2-one";
	public static final String TYPE_MANY_2_MANY = "many-2-many";
	
	private String root;
	private String rel;
	private String type;
	
	private Class<? extends DataObject> rootClass;
	private Class<? extends DataObject> relClass;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Class<? extends DataObject> getRootClass() {
		return rootClass;
	}
	public void setRootClass(Class<? extends DataObject> rootClass) {
		this.rootClass = rootClass;
	}
	public Class<? extends DataObject> getRelClass() {
		return relClass;
	}
	public void setRelClass(Class<? extends DataObject> relClass) {
		this.relClass = relClass;
	}
	public TableNode swap(){
		TableNode newTable = new TableNode();
		newTable.setRoot(this.rel);
		newTable.setRootClass(this.relClass);
		
		newTable.setRel(this.root);
		newTable.setRelClass(this.rootClass);
		
		if(!CommonUtil.isEmpty(this.getChildren())){
			List<FieldNode> fields = this.getChildren();
			List<FieldNode> newFields = new ArrayList<FieldNode>();
			for(FieldNode f : fields){
				FieldNode newField = new FieldNode();
				newField.setRel(f.getRoot());
				newField.setRoot(f.getRel());
				newFields.add(newField);
			}
			newTable.setChildren(newFields);
		}
		if(TYPE_MANY_2_MANY.equals(this.type) || TYPE_ONE_2_ONE.equals(this.type)){
			newTable.setType(this.type);
		}else if(TYPE_MANY_2_ONE.equals(this.type)){
			newTable.setType(TYPE_ONE_2_MANY);
		}else if(TYPE_ONE_2_MANY.equals(this.type)){
			newTable.setType(TYPE_MANY_2_ONE);
		}
		
		return newTable;
	}
	
	public String toString(){
		return new StringBuffer(rootClass.getName()).append("\n").append(relClass.getName()).toString();
	}
	
}
