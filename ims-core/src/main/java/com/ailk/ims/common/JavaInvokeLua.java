package com.ailk.ims.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import jef.jre5support.script.LuaEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ThreadUtil;

/**
 * @Description: lua脚本的java执行引擎类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-9-27
 * @Date 2012-05-23 wukl executeLuaScript方法修改
 */
public class JavaInvokeLua
{
    private static final Log logger = LogFactory.getLog(JavaInvokeLua.class);

    public static final String paramSeperator = "|"; // 参数间隔符

    public static final String kvSeperator = ":"; // key value 间隔符

    public static final String vSeperator = ","; // value 间隔符

    /**
     * 执行lua脚本
     */
    public static HashMap<String, String> executeLuaFile(String fileUrl, Map<String, Object> paramMap)
    {
        ScriptEngineManager sem = new ScriptEngineManager(JavaInvokeLua.class.getClassLoader());
        ScriptEngine se = sem.getEngineByExtension(".lua");
        Iterator<String> it = paramMap.keySet().iterator();
        while (it.hasNext())
        {
            String key = it.next();
            se.put(key, paramMap.get(key));
        }
        // se.put("paramMap", paramMap);
        HashMap<String, String> returnMap = new HashMap<String, String>();
        try
        {
            File file = new File(fileUrl);
            System.out.println(file.getAbsolutePath());
            Reader r = new FileReader(file);
            LuaTable table = (LuaTable) se.eval(r);
            r.close();

            LuaValue k = LuaValue.NIL;
            while (true)
            {
                Varargs n = table.next(k);
                if ((k = n.arg1()).isnil())
                    break;
                LuaValue v = n.arg(2);
                returnMap.put(k.toString(), v.toString());
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (ScriptException e)
        {
            throw new RuntimeException(e);
        }
        return returnMap;
    }

    /**
     * 执行lua语句
     * Lua引擎放在线程变量中
     */
    public static String executeLuaScript(String script, Map paramMap)
    {

        LuaEngine luaEngine = ThreadUtil.getLuaThreadLocal();
        if (luaEngine == null)
        {
            luaEngine = new LuaEngine();
            ThreadUtil.setLuaThreadLocal(luaEngine);
        }
        try
        {
            // logger.debug(script);
            Object paramResult = luaEngine.invoke(script, paramMap);
            return paramResult.toString();
        }
        catch (ScriptException e)
        {
            logger.error("caculate lua script exception", e);
            throw IMSUtil.throwBusiException(e);
        }
        /*
         * try { ScriptEngineManager sem = new ScriptEngineManager(JavaInvokeLua.class.getClassLoader()); ScriptEngine se =
         * sem.getEngineByExtension(".lua"); if (paramMap != null) { Iterator keyIterator = paramMap.keySet().iterator(); while
         * (keyIterator.hasNext()) { String key = keyIterator.next().toString(); Object value = paramMap.get(key); se.put(key,
         * value); } } Compilable compilable = (Compilable) se; CompiledScript compiled = compilable.compile(script); Object
         * paramCodeValue = compiled.eval(); return paramCodeValue.toString(); } catch (Exception e) { // 2011-08-17 zengxr no
         * exception for lua calculate logger.error("caculate lua script exception", e); throw IMSUtil.throwBusiException(e); //
         * return e.getMessage(); }
         */
    }

    /**
     * 转换参数map为字符串
     */
    public static String getStrByMap(HashMap<String, String> paramMap)
    {
        StringBuffer mapStr = new StringBuffer();
        Iterator<String> it = paramMap.keySet().iterator();
        while (it.hasNext())
        {
            String key = it.next();
            Object value = paramMap.get(key);
            mapStr.append(key + kvSeperator);
            mapStr.append(value + paramSeperator);
        }
        return mapStr.toString();
    }

    public static void main(String[] args)
    {
        JavaInvokeLua.executeLuaFile("test.lua", new HashMap<String, Object>());
    }
}
