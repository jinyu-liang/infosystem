package com.ailk.ims.common;

import java.util.List;
import com.ailk.easyframe.sal.client.SalClient;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturn;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturnEx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncAllInfo;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.web.common.exception.BusinessException;
import com.ailk.ims.component.MdbSalBuildComponent;
import com.ailk.ims.component.MdbSalComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.mapreduce.ImsSyncMapReduce;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SpringUtil;

/**
 * @Description: 直接使用SAL访问MDB的基准mdbbean
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2012-6-13
 * @Date 2012-07-23 wuyujie :增加doResult回调方法
 * @Date 2012-09-19 yugb :[59009]事务一致性方案
 */
public abstract class BaseSalMdbBean extends BaseMdbBean
{
    /**
     * 创建MdbRdl对象，每一个MdbRdl表示访问一个mdb表，存放着相关的信息
     * 
     * @return
     */
    public abstract List<MdbRdl> createMdbRdl() throws Exception;
    
    /**
     * 2012-07-23 wuyujie :增加doResult回调方法
     * @author wuyj 2012-7-23
     * @param errorMsg
     * @throws Exception
     */
    public void doResult(CBSErrorMsg errorMsg) throws Exception{
        return;
    }
    
    public CBSErrorMsg doSyncronization(Object[] result) throws Exception
    {
        List<MdbRdl> rdls = createMdbRdl();

        SalClient client = SpringUtil.getSalClient();
        MdbSalComponent salCmp = context.getComponent(MdbSalComponent.class);
        CBSErrorMsg errorMsg = null;
        if (CommonUtil.isEmpty(rdls))
            return errorMsg;
        SSyncAllInfo allInfo = salCmp.createSyncAllInfo(rdls);
        imsLogger.dump("******* sync mdb object : ", allInfo);
        try{
        	SReturnEx sReturnEx = new SReturnEx();
        	ImsSyncMapReduce mapReduce = new ImsSyncMapReduce();
//        	mapReduce.mapSyncAllInfo(allInfo);
        	//如果成功则返回0，如果失败则返回1或者其它非0值
        	Object ret =null;
        	if(IMSUtil.isMdbCloud()){
        		ret = client.post(MdbKVDefine.SYNC_ALLINFO, allInfo, sReturnEx,mapReduce);
        	}else{
        		ret = client.post(MdbKVDefine.SYNC_ALLINFO, allInfo, sReturnEx);
        	}
	        imsLogger.dump("******* sync MdbRdl result : ", ret);
	        errorMsg = return2CbsMsg(ret);
	        // 返回的结果对象里有错误码,如果需要中断后续流程，则返回错误对象
	        if (errorMsg != null && errorMsg.get_errorCode() != 0)
	        {
	        	return errorMsg;
	        }
        }catch (Exception e){
        	 imsLogger.error(e, e);
        	 errorMsg = new CBSErrorMsg();
        	 //返回错误对象
        	 if (e instanceof BusinessException)
        	 {
               errorMsg.set_errorCode(((BusinessException) e).getCodeAsInteger());
        	 }
        	 else
        	 {
        		 errorMsg.set_errorCode((int) ErrorCodeDefine.UNKNOWN_ERROR);
        	 }
        	 errorMsg.set_errorMsg(e.getClass().getName() + ":" + e.getLocalizedMessage());
        	 return errorMsg;
        }
        doResult(errorMsg);
        return errorMsg;
    }
    
    
    
    private CBSErrorMsg return2CbsMsg(Object ret){
        if(ret == null){
            return null;
        }
        CBSErrorMsg errorMsg = new CBSErrorMsg();
        errorMsg.set_errorMsg("sync mdb error");
        
        int errorCode = -1;
        if(ret instanceof SReturn){
            SReturn sRet = (SReturn)ret;
            errorCode = sRet.get_ret();
        }else if(ret instanceof SReturnEx){
            SReturnEx sRetEx = (SReturnEx)ret;
            errorCode = sRetEx.get_ret();
        }
        errorMsg.set_errorCode(errorCode);
        
        return errorMsg;
    }
    
    
    public MdbSalBuildComponent getMdbSalBuildComp(){
    	return context.getComponent(MdbSalBuildComponent.class);
    }
    
    public MdbSalComponent getMdbSalComp(){
    	return context.getComponent(MdbSalComponent.class);
    }
}
