package com.ailk.imssh.common.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.Bindings;
import javax.script.CompiledScript;

import jef.database.Condition.Operator;
import jef.jre5support.script.LuaEngine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.sys.entity.SysPolicy;

/**
 * @Description 定价计划执行类
 * @author lijc3
 * @Date 2012-10-17
 */
public class LuaConfig
{

    private static final Log logger = LogFactory.getLog(LuaConfig.class);
    private static LuaEngine engine = new LuaEngine();
    private static String LUA_FALSE = "0";

    private static Map<Integer, SysPolicy> map = new HashMap<Integer, SysPolicy>();
    private static Map<Integer, CompiledScript> compiledScriptmap = new HashMap<Integer, CompiledScript>();

    public static SysPolicy getSysPolicy(Integer policyId)
    {
        return map.get(policyId);
    }

    /**
     * 初始化方法，一个进程开始的时候调用一次 lijc3 2012-10-18
     */
    public static void init()
    {
        logger.info("**********begin to init policy list...");
        List<SysPolicy> policyList = queryPolicy();
        if (CommonUtil.isEmpty(policyList))
        {
            logger.warn("no policy used for pricing plan...");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("init sys_policy where policy id in ");
        boolean flag = false;
        for (SysPolicy policy : policyList)
        {
            if (CommonUtil.isEmpty(policy.getPolicyExpr()))
            {
                continue;
            }
            try
            {
                map.put(policy.getPolicyId(), policy);
                CompiledScript script = ((org.luaj.vm2.script.LuaScriptEngine) engine.get()).compile(policy.getPolicyExpr());
                compiledScriptmap.put(policy.getPolicyId(), script);
            }
            catch (Exception e)
            {
                flag = true;
                buffer.append(policy.getPolicyId() + " ,");
            }
        }
        if (flag)
        {
            buffer.append(" is error! no cached");
            logger.error(buffer.toString());
        }
        logger.error("**********end to init policy list... all started!!!");
    }

    public static String executeLuaScript(Integer policyId, Map paramMap)
    {
        CompiledScript compile = compiledScriptmap.get(policyId);
        if (compile == null)
        {
            return LUA_FALSE;
        }
        Bindings bind = createBindings(paramMap);

        return execute(compile, bind);
    }

    /**
     * lijc3 2012-10-18 将传入变量转换成lua使用的参数
     * 
     * @param paramMap
     * @return
     */
    public static Bindings createBindings(Map<String,Object> paramMap)
    {
        Bindings bind = engine.createBindings();
        if (paramMap != null)
        {
//            Set set = paramMap.keySet();
//            for (Object key : set)
//            {
//                bind.put(key.toString(), paramMap.get(key));
//            }           
            for (Map.Entry<String, Object> bindEntry: paramMap.entrySet()) {
				bind.put(bindEntry.getKey().toString(), bindEntry.getValue());
			}
        }
        return bind;
    }

    private static List<SysPolicy> queryPolicy()
    {
        List<SysPolicy> policyList = DBUtil.query(SysPolicy.class, new DBCondition(SysPolicy.Field.useTrigger, new Integer[] {
                11, 10, 45 }, Operator.IN));
        return policyList;
    }

    public static String executeLuaScript(Integer policyId, Bindings bind)
    {
        CompiledScript compile = compiledScriptmap.get(policyId);
        if (compile == null)
        {
            return LUA_FALSE;
        }
        return execute(compile, bind);
    }

    private static String execute(CompiledScript compile, Bindings bind)
    {
        try
        {
            Object paramResult = compile.eval(bind);
            return paramResult.toString();
        }
        catch (Exception e)
        {
            logger.error("caculate lua script exception", e);
            throw IMSUtil.throwBusiException(e);
        }
    }
}
