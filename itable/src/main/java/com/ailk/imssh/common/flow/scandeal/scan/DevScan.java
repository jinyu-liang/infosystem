package com.ailk.imssh.common.flow.scandeal.scan;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import jef.codegen.EntityEnhancer;
import jef.tools.ArrayUtils;
import jef.tools.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ailk.imssh.common.init.CommonInit;

public class DevScan {
	/**
	 * 日志类 .
	 */
	private final static Logger LOG = Logger.getLogger(DevScan.class.getName());

	/**
	 * 开发状下启动监控程序入口 .
	 * 
	 * @throws IOException
	 */
	public final void startup() {
		LOG.debug("start .......................  ");
		entityEnhancer();
		loadSpring();
		
		// 调用主处理程序
		new DataIndexScan().startScanDeal(2);
		LOG.debug("end .......................  ");
	}

	/**
	 * 实体增加 .
	 */
	private void entityEnhancer() {
		LOG.debug("EntityEnhancer start For Dev .....................................................................  ");
		EntityEnhancer en = new EntityEnhancer();
		try {
			for (URL url : ArrayUtils.toIterable(CommonInit.class
					.getClassLoader().getResources(""))) {
				File file = IOUtils.urlToFile(url);
				if (file.isDirectory()) {
					en.setRoot(file);
					en.enhance("com.ailk.openbilling.persistence");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error(e, e);

		}
		LOG.debug("EntityEnhancer end For Dev .....................................................................  ");
	}

	/**
	 * 初始化spring配置
	 */
	private void loadSpring() {
		LOG.debug("Load Spring start For Dev .....................................................................  ");
		new ClassPathXmlApplicationContext(getSpringConfigFile());
		LOG.debug("Load Spring end For Dev .....................................................................  ");
	}

	/**
	 * 获取spring配置文件
	 * 
	 * @return
	 */
	private String[] getSpringConfigFile() {
		return new String[] { "classpath:META-INF/itable_applicationContext.xml" };
	}

	/**
	 * 开发启动入口 .
	 * 
	 * @param args
	 *            .
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		DevScan scan = new DevScan();
		scan.startup();
	}
}
