package com.ailk.ims.util;

import com.ailk.ims.define.ConstantDefine;

/**
 * @Description: 版本控制信息
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2012-2-10
 * 修改is_CN_SH方法，调用IS_CN方法，将SH变成基本版本，然后将广西的配置改成CN_GX
 */
public class ProjectUtil
{
   
	/**
	 * 是否国内版本，根据国家码CN来判断
	 * @return
	 */
	public static boolean is_CN(){
		String proj = IMSUtil.getProject();
		String countryCode = proj.split("_")[0];
		return "CN".equalsIgnoreCase(countryCode);
	}
	
	/**
	 * 是否海外版本，is_CN（）的非结果
	 * @return
	 */
	public static boolean is_Overseas(){
		return !is_CN();
	}
	
	/**
     * @Description: 是否是泰国版本
     * @author : wuyj
     * @date : 2012-2-10
     * @date : 版本只分国内、海外
     * @return
     */
    public static boolean is_TH_AIS()
    {
        return is_Overseas();
    }

    /**
     * @Description:是否国内版本
     * 该方法与is_CN()方法现在含义变成一致
     * @author : wuyj
     * @date : 2012-2-10
     * @return
     */
    public static boolean is_CN_SH()
    {
//        return IMSUtil.getProject().equals(ConstantDefine.PROJECT_CN_SH);
    	return is_CN();
    }
    
    public static boolean is_CN_XZ()
    {
        return IMSUtil.getProject().equals(ConstantDefine.PROJECT_CN_XZ);
    }
    
    public static boolean is_CN_NM()
    {
        return IMSUtil.getProject().equals(ConstantDefine.PROJECT_CN_NM);
    }
    
    public static boolean is_CN_JX()
    {
        return IMSUtil.getProject().equals(ConstantDefine.PROJECT_CN_JX);
    }
    public static boolean is_CN_GX()
    {
        return IMSUtil.getProject().equals(ConstantDefine.PROJECT_CN_GX);
    }

    /**
     * 传入基本配置文件路径和项目名称，返回分版本的配置文件路径 luojb 2012-2-22
     */
    public static String parseProjectConfPath(String baseConfPath, String project)
    {
        if(!CommonUtil.isValid(project))
            return null;
        int offset = baseConfPath.lastIndexOf(".");
        return baseConfPath.substring(0, offset) + "_" + ConstantDefine.PROJECT_CN_SH + baseConfPath.substring(offset);
    }

}
