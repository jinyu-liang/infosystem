package com.ailk.ims.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>Title: CBOSS </p>
 * <p>Description: 描述该类用途 </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Asiainfo Technologies （China）,Inc. Hangzhou</p>
 * Created on 2008-9-8 下午05:24:05
 *
 * @author zengxr
 * FileUtil
 * 2008-09-08 zengxr f.mkdir to f.mkdirs
 * 2012-04-19 wuyujie : 新增isFileExist
 */
public class FileUtil {
    public FileUtil() {
    }

    public static final String FILE_ENCODING = getFileEncoding();
    public static final String RELATIVE_FLAG = "//";
    
    private static ImsLogger logger = new ImsLogger(FileUtil.class);

    /**
     * 得到文件系统的字符集描述，若该文件系统的字符集描述为GBK则返回GB2312, 若字符集描述为ISO8859_1则返回ISO-8859-1。
     *
     * @return 字符集描述
     */
    public static String getFileEncoding() {
        String str = System.getProperty("file.encoding");
        if (null == str) {
            return "";
        }
        str = str.trim().toUpperCase();
        if (str.equals("GBK")) {
            str = "GB2312";
        } else if (str.equals("ISO8859_1")) {
            str = "ISO-8859-1";
        }
        // CBossLogUtil.getLogger("").debug(str);
        return str;
    }

    public static String[] getFileList(String sDir, final String filterName) {
        File fileDir = new File(sDir);
        return fileDir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String rr = new File(name).toString();
                return rr.endsWith(filterName);
            }
        });
    }

    /**
     * 新建目录
     *
     * @param folderPath String 如 c:/fqf
     * @return boolean
     */
    public static void newFolder(String folderPath) throws Exception {
        java.io.File myFilePath = new java.io.File(folderPath);
        if (!myFilePath.exists()) {
            myFilePath.mkdir();
        }
    }

    /**
     * 新建文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @return boolean
     */
    public static void newFile(String filePathAndName) throws Exception {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
        } catch (Exception e) {
            throw new Exception("No such file or directory[" + filePathAndName + "]", e);
        }
    }

    /**
     * 新建文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @param fileContent     String 文件内容
     */
    public static void newFile(String filePathAndName, String fileContent) throws Exception {
        FileWriter resultFile = null;
        PrintWriter myFile = null;
        try {
            File myFilePath = new File(filePathAndName);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            resultFile = new FileWriter(myFilePath);
            myFile = new PrintWriter(resultFile);
            myFile.print(fileContent);
            resultFile.close();
        } finally {
            try {
                if (resultFile != null) resultFile.close();
            } catch (Exception e) {
            	logger.error(e,e);
            }
            try {
                if (myFile != null) myFile.close();
            } catch (Exception e) {
            	logger.error(e,e);
            }
        }
    }

    /**
     * 新建文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @param fileContent     String 文件内容
     * @return boolean
     */
    public static void newFile(String filePathAndName, byte[] fileContent) throws Exception {
        FileOutputStream resultFile = null;
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            resultFile = new FileOutputStream(myFilePath);
            //PrintWriter myFile = new PrintWriter(resultFile);
            resultFile.write(fileContent);
            resultFile.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage() + "[" + filePathAndName + "]", e);
        } finally {
            try {
                resultFile.close();
            } catch (Exception ex) {
            	logger.error(ex,ex);
                // do nothing
            }
        }
    }

    /**
     * 删除文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @return boolean
     */
    public static void delFile(String filePathAndName) throws Exception {

            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();
        
    }

    /**
     * 删除文件夹
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
        	logger.error(e,e);
        	//SWTUtil.alert(e.getMessage() + "[" + folderPath + "]");
        }
    }

    /**
     * 删除文件夹里面的所有文件
     *
     * @param path String 文件夹路径 如 c:/fqf
     */
    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
            }
        }
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    //System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
        	logger.error(e,e);
        	//SWTUtil.alert(e.getMessage() + "[" + oldPath + "," + newPath + "]");
        }
    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
        	logger.error(e,e);
        	//SWTUtil.alert(e.getMessage() + "[" + oldPath + "," + newPath + "]");
        }
    }

    /**
     * 批量移动文件
     *
     * @param oldFiles
     * @param srcPath
     */
    public static void moveFile(String[] oldFiles, String srcPath, String destPath) {
        if (oldFiles == null
                || oldFiles.length == 0
                || srcPath == null
                || srcPath.trim().length() == 0
                || destPath == null
                || destPath.trim().length() == 0) return;
        for (int i = 0; i < oldFiles.length; i++) {
            String srcFile = null;
            if (srcPath.endsWith(File.separator))
                srcFile = srcPath + oldFiles[i];
            else
                srcFile = srcPath + oldFiles[i];
            String destFile = null;
            if (destPath.endsWith(File.separator))
                destFile = destPath + oldFiles[i];
            else
                destFile = destPath + File.separator + oldFiles[i];
            moveFile(srcFile, destFile);
        }
    }

    /**
     * 批量移动文件
     *
     * @param oldFiles
     * @param destPath
     */
    public static void moveFile(String[] oldFiles, String destPath) {
        if (oldFiles == null
                || oldFiles.length == 0
                || destPath == null
                || destPath.trim().length() == 0) return;
        for (int i = 0; i < oldFiles.length; i++) {
            String tmp = oldFiles[i].substring(oldFiles[i].lastIndexOf(System.getProperty("file.seperator")) + 1);
            String destFile = null;
            if (destPath.endsWith(File.separator))
                destFile = destPath + tmp;
            else
                destFile = destPath + File.separator + tmp;
            moveFile(oldFiles[i], destFile);
        }
    }

    /**
     * 移动文件到指定目录
     *
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void moveFile(String oldPath, String newPath) {
        File f = new File(oldPath);
        f.renameTo(new File(newPath));
        // copyFile(oldPath, newPath);
        // delFile(oldPath);
    }

    /**
     * 移动文件到指定目录
     *
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }

    public static void main(String[] args) throws Exception {
//		FileUtil f = new FileUtil();
//		f.moveFile("E:\\ftp_down\\DELAY_STA20060409791.999",
//				"E:\\history\\DELAY_STA20060409791.999");
        FileUtil.newFile("C:\\test_test_test.txt", "");
    }

    /**
     * 得到指定目录下以指定字符串开头的文件名列表 taojc add 2006-1-26
     *
     * @param sDir        String
     * @param filterBegin String
     * @return String
     */
    public static String[] getFileListStartsWithFilter(String sDir,
                                                       final String filterBegin) {
        File fileDir = new File(sDir);
        return fileDir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String rr = new File(name).toString();
                return (rr.startsWith(filterBegin));
            }
        });
    }

    public static String getFileContent(String fileName, String charset) throws Exception {
        try {
            return new String(getFileContentBytes(fileName), charset);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + "[" + fileName + "]", ex);
        }
    }

    /**
     * @param filePathAndName String
     * @return long
     * @throws Exception
     */
    public static long getFileSize(String filePathAndName) throws Exception {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File file = new java.io.File(filePath);
            return file.length();
        } catch (Exception e) {
            throw new Exception(e.getMessage() + "[" + filePathAndName + "]", e);
        }
    }

    public static String getFileContent(String fileName) throws Exception {
        return new String(getFileContentBytes(fileName));
    }

    public static String getFileContent(File fileName) throws Exception {
        try {
            return new String(getFileContentBytes(new FileInputStream(fileName)));
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + "[" + fileName + "]", ex);
        }
    }

    public static byte[] getFileContentBytes(String fileName) throws Exception {
        try {
            return getFileContentBytes(new FileInputStream(fileName));
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + "[" + fileName + "]", ex);
        }
    }

    public static byte[] getFileContentBytes(InputStream is) throws Exception {
        try {
            ByteArrayOutputStream byOut = new ByteArrayOutputStream();
            byte[] datas = new byte[1024];
            int len = -1;
            while ((len = is.read(datas)) > 0) {
                byOut.write(datas, 0, len);
            }
            return byOut.toByteArray();
        } finally {
            is.close();
        }
    }

    public static File checkDirectoryExists(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new Exception("路径" + path + "不存在!");
        }
        return file;
    }

    public static void appendFile(String fileName, String content) throws Exception {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName, true);
            fileWriter.write(content);
            fileWriter.flush();
        } catch (java.lang.Exception e) {
            throw new Exception(e.getMessage() + "[" + fileName + "]", e);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                    fileWriter = null;
                }
            } catch (Exception e) {
                // do nothing
            	logger.error(e,e);
            }
        }
    }
    public static void writeFile(String fileName, String content) throws Exception {
        if (StringUtils.isBlank(fileName) || content == null)
        {
            return;
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(content);
            fileWriter.flush();
        } catch (java.lang.Exception e) {
            throw new Exception(e.getMessage() + "[" + fileName + "]", e);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                    fileWriter = null;
                }
            } catch (Exception e) {
            	logger.error(e,e);
            }
            
        }
    }
    

    public static void makeDir(String dir) throws Exception {
        try {
            File f = new File(dir);
            //2008-09-08 zengxr f.mkdir to f.mkdirs
            f.mkdirs();
        } catch (java.lang.Exception e) {
            throw new Exception(e.getMessage() + "[" + dir + "]", e);
        }
    }

    /*public static InputStream getStream(String fileName) throws Exception {
        InputStream in;
        try {
            in = new FileInputStream(fileName);
        } catch (FileNotFoundException f) {
        	;//SWTUtil.print("找不到文件[" + fileName + "],使用class.getResourceAsStream的方式查询文件");
            in = FileUtil.class.getResourceAsStream("/" + fileName);
        }
        if (in == null) throw new Exception("使用两种方式查询文件都找不到文件[" + fileName + "]");
        return in;
    }*/

    public static String getAbsolutePath(String baseDir, String path) {
        if (baseDir == null) baseDir = System.getProperty("user.dir");
        if (path.startsWith(RELATIVE_FLAG)) {
            return baseDir + File.separator + path.substring(RELATIVE_FLAG.length());
        }
        return path;
    }

    public static Properties loadInitProperties(String fileName) throws Exception {
        Properties p = new Properties();
        InputStream in = null;
        try {
            in = FileUtil.getInputStream(fileName);
            p.load(in);
        }  finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // do nothing
                	logger.error(e,e);
                }
            }
        }
        return p;
    }
    public static File createFile(String filePath) throws Exception{
		filePath = filePath.replace("\\", "/");
		String dir = filePath.substring(0, filePath.lastIndexOf("/"));
		File fold = new File(dir);
		File file = new File(filePath);
		if (!fold.exists()) {
			fold.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}
    public static boolean isFile(String path){
    	File file = new File(path);
    	return file.isFile();
    }
    public static InputStream getInputStream(String path) throws FileNotFoundException{
    	boolean isLocation = Pattern.matches("\\w:.*", path);
    	InputStream is = null;
		if(isLocation){
			is = new FileInputStream(path);
		}else{
			is = FileUtil.class.getResourceAsStream(path);
		}
		return is;
    }
    
    public static String getClassRes(String res) throws Exception{
    	
    	return new String(FileUtil.getFileContentBytes(FileUtil.getInputStream(res)));
    }
    
    /**
     * @Description: 资源是否存在
      * @author : wuyj
      * @date : 2012-4-19  
      * @param path,资源路径，可以是绝对路径(以C: D:等开头)，也可以是相对于classpath的相对路径
      * @return
     */
    public static boolean isFileExist(String path){
    	boolean result = false;
    	try {
    		result = getInputStream(path) != null;
		} catch (FileNotFoundException e) {
		    logger.error(e, e);
		}
		return result;
    }
}
