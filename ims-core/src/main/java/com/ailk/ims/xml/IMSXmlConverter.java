package com.ailk.ims.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ailk.ims.util.CommonUtil;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/**
 * @Description: 默认的xml转换器              
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-10-1
  * 2012-03-03 wuyujie : 修复没有marshal的时候，没有输出节点对应的text内容
 */
public class IMSXmlConverter implements Converter {
	
	/**
	 * 允许转换的节点
	 */
	public boolean canConvert(Class type) {
		return (BaseNode.class.isAssignableFrom(type));
	}
	
	/**
	 * 把对象转换成流
	 */
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		BaseNode root = (BaseNode)source;
		
		writeAttributes(root,writer);
		
		marshalChildren(root.getChildren(),writer);
		
	}
	
	public void marshalChildren(List<BaseNode> children,HierarchicalStreamWriter writer){
		if(CommonUtil.isEmpty(children))
			return;
		for(BaseNode child : children){
			writer.startNode(child.getTagName());
			
			writeAttributes(child,writer);
			
			//2012-03-03 wuyujie : 修复没有marshal的时候，没有输出节点对应的text内容
			if(CommonUtil.isNotEmpty(child.getChildren())){
				//有子节点就直接处理子节点，忽略当前节点的text
				marshalChildren(child.getChildren(),writer);
			}else if(CommonUtil.isNotEmpty(child.getText())){
				//无子节点且有text值，则输出内容
				writer.setValue(child.getText());
			}
			writer.endNode();
		}
		
	}
	public void writeAttributes(BaseNode node,HierarchicalStreamWriter writer){
		Map<String,String> map = node.getAttributes();
		if(CommonUtil.isEmpty(map))
			return;
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			writer.addAttribute(key, map.get(key));
		}
	}
	
	/**
	 * 流转换成对象
	 */
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		String tagName = reader.getNodeName();
		BaseNode node = new BaseNode(tagName);
		
		Map<String,String> attrs = readAttributes(reader);
		node.setAttributes(attrs);
		
		List<BaseNode> list = unmarshalChild(reader);
		node.addChildren(list);
		
		node.setText(reader.getValue());
		
		return node;
	}
	
	private List<BaseNode> unmarshalChild(HierarchicalStreamReader reader){
		List<BaseNode> result = new ArrayList<BaseNode>();
		while(reader.hasMoreChildren()){
			reader.moveDown();
			BaseNode child = new BaseNode(reader.getNodeName());
			Map<String,String> attrs = readAttributes(reader);
			child.setAttributes(attrs);
			
			List children = unmarshalChild(reader);
    		if(children != null && children.size() > 0){
    			child.addChildren(children);
    		}
    		
    		child.setText(reader.getValue());
    		
    		result.add(child);
			
    		reader.moveUp();
		}
		return result;
	}
	
	private static Map<String,String> readAttributes(HierarchicalStreamReader reader){
		Map<String,String> map = new HashMap<String,String>();
		Iterator it = reader.getAttributeNames();
		while(it.hasNext()){
			String attrName = (String)it.next();
			String value = reader.getAttribute(attrName);
			map.put(attrName, value);
		}
		return map;
	}
	
}
