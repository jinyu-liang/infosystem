package com.blue.ailk.creator;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.blue.ailk.db.IParameter;
import com.blue.ailk.db.IParser;
import com.blue.ailk.db.ParserFactory;
import com.blue.ailk.db.Sequnce;
import com.blue.ailk.util.Log;
import com.blue.core.lang.io.TextUtils;

public class SeqSQLCreator {

	public void createSQL() {
		Log.info("开始生成sequence");
		StringBuffer sb = new StringBuffer();
		IParser parser = ParserFactory.getFactory(this.getAdapterClzz());
		sb.append("/*==============================================================*/\r\n");
		sb.append("/* Created on:     "
				+ DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss")
				+ "                          */\r\n");
		sb.append("/*==============================================================*/\r\n");
		List<Sequnce> list = parser.getSequnces(this.getParameter());
		for (Sequnce sequnce : list) {
			sb.append("--drop sequence " + sequnce.getSchem() + "."
					+ sequnce.getName());
			sb.append(";\r\n\r\n");
			sb.append("create sequence " + sequnce.getSchem() + "."
					+ sequnce.getName());
			sb.append("\r\n");
			if (StringUtils.isNotEmpty(sequnce.getPhysicalOptions())) {
				sb.append(sequnce.getPhysicalOptions());
			}
			sb.append(";\r\n\r\n");
		}
		TextUtils.write(this.getScriptFilename(), sb.toString(), "UTF-8");
		Log.info("生成sequence结束");
	}

	String adapterClzz;
	IParameter parameter;
	String scriptFilename;

	public String getAdapterClzz() {
		return adapterClzz;
	}

	public void setAdapterClzz(String adapterClzz) {
		this.adapterClzz = adapterClzz;
	}

	public IParameter getParameter() {
		return parameter;
	}

	public void setParameter(IParameter parameter) {
		this.parameter = parameter;
	}

	public String getScriptFilename() {
		return scriptFilename;
	}

	public void setScriptFilename(String scriptFilename) {
		this.scriptFilename = scriptFilename;
	}
}
