package com.blue.ailk.app;

import com.blue.ailk.compare.PdmAndDbCompare;

/**
 * 比较PDM与DB表差异 .
 * 
 * @author ChrisChen
 * @version 1.0 , 2014-1-20
 */
public class CompareStartup {
	/**
	 * 比较Main入口 .
	 * 
	 * @param args
	 *            .
	 */
	public static void main(String[] args) {
		PdmAndDbCompare compare = new PdmAndDbCompare();
		compare.compare();
	}
}
