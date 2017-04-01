package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.sal.client.SalClient;
import com.ailk.easyframe.sal.exceptions.SALException;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryNumBySessionResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Session;
import com.ailk.openbilling.persistence.imscnsh.entity.SessionInfo;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * @description:页面型流量提醒鉴权接口
 * @author caohm5
 * @date：2012-11-01
 *
 */
public class QueryNumBySessionBean extends BaseBusiBean {
	private Session session;

    @Override
    public void init(Object... input) throws BaseException
    {
    	session=(Session)input[0];
    	
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if(null==session){
    		
   		 	throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "Session");
    	}
    	if(CommonUtil.isEmpty(session.getSession_id())){
   		
   		 	throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "session_id");
    	}
    	
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
//    	ISessionClientInt sessionCl=new ISessionClientInt();
//    	CBSErrorMsg cErrorMsg = new CBSErrorMsg();
//    	SQueryNumber sqNumber=new SQueryNumber();
//    	sqNumber.set_sessionId(session.getSession_id());
//    	CsdlArrayList<SQueryNumber> csQuruery=new CsdlArrayList<SQueryNumber>(SQueryNumber.class);
//    	CsdlArrayList<SQueryNumberRet> sRet = new CsdlArrayList<SQueryNumberRet>(SQueryNumberRet.class);
//    	CsdlArrayListHolder<SQueryNumberRet> retHolder=new CsdlArrayListHolder<SQueryNumberRet>(sRet);
//    	List<SessionInfo> sessionList=new ArrayList<SessionInfo>();
//    	try{
//    		sessionCl.do_queryNumBySession(csQuruery, retHolder, cErrorMsg);
//    		CsdlArrayList<SQueryNumberRet> outList =retHolder.get();
//    		if (!CommonUtil.isEmpty(outList))
//            {
//                for (SQueryNumberRet info : outList)
//                {
//                	if(info==null)
//                		continue;
//                	String userNum=info.get_userNum();
//                    if (CommonUtil.isNotEmpty(userNum))
//                    {
//                    	SessionInfo sessionInfo=new SessionInfo();
//                    	sessionInfo.setUser_num(userNum);
//                    	sessionList.add(sessionInfo);
//                    }
//                }
//            }
//    	}catch (OBBufferErrorException e)
//        {
//            IMSUtil.throwBusiException(ErrorCodeDefine.ACCESS_INTERFACE_FAILED, "do_queryPromoAllotInfo");
//        }
//    	return new Object[]{sessionList};
//    	SalClient client = SpringUtil.getSalClient();
    	//c++入参
//    	SQueryNumber sqNumber=new SQueryNumber();
//    	sqNumber.set_sessionId(session.getSession_id());
//    	CsdlArrayList<SQueryNumber> sqNumberList=new CsdlArrayList<SQueryNumber>(SQueryNumber.class);
//    	sqNumberList.add(sqNumber);
//    	//c++出参
//    	CsdlArrayList<SQueryNumberRet> retList=new CsdlArrayList<SQueryNumberRet>(SQueryNumberRet.class);
//    	//c++实际出参
//    	CsdlArrayList<SQueryNumberRet> realRetList=new CsdlArrayList<SQueryNumberRet>(SQueryNumberRet.class);
//    	//java出参
//    	List<SessionInfo> sessionList=new ArrayList<SessionInfo>();
//    	 try
//         {
//    		imsLogger.dumpJsonObject("##############QueryNumBySessionBean:In  ###################", sqNumberList);
//    		realRetList=client.get(MdbKVDefine.QUERY_USER_NUM_SH, sqNumberList, retList);
//    		imsLogger.dumpJsonObject("##############QueryNumBySessionBean:Out  ###################", realRetList);
//     		if (realRetList!=null&&!realRetList.isEmpty()){
//               for (SQueryNumberRet info : realRetList){
//		           if(info==null)
//		           		continue;
//		           String userNum=info.get_userNum();
//		           if (CommonUtil.isNotEmpty(userNum))
//		           {
//			           	SessionInfo sessionInfo=new SessionInfo();
//			           	sessionInfo.setUser_num(userNum);
//			           	sessionList.add(sessionInfo);
//		           }
//               }
//     		}
//         }
//         catch (OBBufferErrorException e)
//         {
//             IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_DATA_FROM_MDB_ERROR);
//         }
//         catch (SALException e)
//         {
//             e.printStackTrace();
//         }
//    	return new Object[]{sessionList};
    	
    	return null;
    }
    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Do_queryNumBySessionResponse respn=new Do_queryNumBySessionResponse();
    	List<SessionInfo> sessionInfoList=(List<SessionInfo>)result[0];
    	respn.setSessionInfoList(sessionInfoList);
    	return respn;
    }
    @Override
    public void destroy()
    {
    }
}
