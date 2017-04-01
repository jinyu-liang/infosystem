package com.ailk.ims.httpjson.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.ReflectionException;

import jef.common.Entry;
import jef.database.Batch;
import jef.http.server.actions.JsonResponse;
import jef.http.server.actions.ServletExchange;
import jef.tools.JsonUtil;
import jef.tools.StringUtils;
import jef.tools.ThreadUtils;

import org.springframework.web.servlet.ModelAndView;

import com.ailk.easyframe.web.action.ActionDispatcher;
import com.ailk.easyframe.web.common.annotation.Param;
import com.ailk.easyframe.web.common.annotation.ReturnType;
import com.ailk.easyframe.web.common.invoker.ActionInvoker;
import com.ailk.easyframe.web.common.invoker.MethodInfo;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.jd.entity.ImsJsonSoap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 处理http+json请求
 * 
 * @Description
 * @author xieqr
 * @Date 2012-5-10
 * @Date 2012-06-14 wuyujie ：1.5.0框架包invoke方法需要返回ModelAndView
 */
public class ImsActionDispatcher extends ActionDispatcher {
	private final List<ImsJsonSoap> list = new ArrayList<ImsJsonSoap>(1000);
	private static final long serialVersionUID = -7400951984544468679L;
	// private static final String[] IGNORE_LIST = new String[] { "_dc",
	// "_hash", "_callback", "showLoading", "_busi_code" };
	private static final String charset = "UTF-8";
	private static final String jsonObj = "JSON_OBJ_IMS";
	private static final ImsLogger imsLogger = new ImsLogger(ImsActionDispatcher.class);
	// 是否记录成功标示
	private static final boolean isSuccRecord = SysUtil.getBoolean(EnumCodeShDefine.busi.IM_SH_JSON_SOAP_RECORD_SUCC, false);
	// 是否记录失败标示
	private static final boolean isFailRecord = SysUtil.getBoolean(EnumCodeShDefine.busi.IM_SH_JSON_SOAP_RECORD_FAIL, false);
	private static final String serviceName = "com.ailk.openbilling.service.imscnsh.CN_SHMgntServiceImpl";
	private static final int SLEEP_TIME = SysUtil.getInt(EnumCodeShDefine.busi.IM_SH_JSON_SOAP_THREAD_SLEEP_TIME,
			EnumCodeShDefine.INSERT_IMS_JSON_SOAP_SLEEP_TIME_DEFAULT);
	private static final int BATCH_NUMBER = SysUtil.getInt(EnumCodeShDefine.busi.IM_SH_JSON_SOAP_BATCH_NUMBER,
			EnumCodeShDefine.IM_SH_JSON_SOAP_BATCH_NUMBER_DEFAULT);

	/**
	 * 初始化线程
	 */
	public ImsActionDispatcher() {
		Thread t = new Thread() {
			@Override
			public void run() {
				imsLogger.info("##########Thread Run Start##########");
				ThreadUtils.doSleep(240000);
				try {
					if (isFailRecord || isSuccRecord)
						doTask();
				} catch (Exception e) {
					imsLogger.error(e);
				}
				imsLogger.info("##########Batch insert Thread Ends....");
			}

			private void doTask() {
				while (true) {
					List<ImsJsonSoap> todo = null;
					synchronized (list) {
						if (list.size() > 0) {
							todo = new ArrayList<ImsJsonSoap>(list);
							list.clear();
						}
					}
					imsLogger.info("##########Thread batch insert start##########" + (todo == null ? 0 : todo.size()));
					if (todo != null) {
						Batch<ImsJsonSoap> batch;
						try {
							batch = DBUtil.getDBClient().startBatchInsert(todo.get(0));// 这里不要用class，改用数组的第一个要插入的对象即可
							batch.setGroupForPartitionTable(true);
							batch.add(todo);
							try {
								batch.commit();
							} catch (Exception e1) {
								imsLogger.error("Error at batchinsert commit", e1);
								// 解决插入异常的时候 连接无法释放的问题
								batch.cancel();
							}
						} catch (Exception e) {
							imsLogger.error("Error at batchinsert", e);
						}
						imsLogger.info("##########Thread batch insert end##########");
					}
					ThreadUtils.doSleep(SLEEP_TIME);
				}
			}
		};
		t.start();
	}

	@Override
	protected ModelAndView invoke(Object action, String methodName, ServletExchange exchange) {
		long start = System.currentTimeMillis();
		JsonResponse json = null;
		Integer busiCode = null;
		// 成功失败标识 0：成功1：失败 默认是0（成功）
		int flag = 0;
		Entry<Object, ReturnType> iResult = null;
		/**
		 * OperationCode格式：<TLD>.<Domain>.<Application>.<Service>.<Operation> 例
		 * :
		 * com.cmcc.sh.alplatform.uniinterface.forbank.PT-SH-FS-OI1297 ， 其 中
		 * com.cmcc.sh 前缀， alplatform 是亚联平台域，
		 * uniinterface是亚联统一接口系统， forbank是提供给银行的接口集群，PT-SH-FS-OI1297 是具体的业务操作。
		 */
		// String operationCode =
		// exchange.getRequest().getHeader("OperationCode");
		/**
		 * ClientId 格式：<TLD>.<Domain>.<Application> 一般情况下，ClientId
		 * 是服务消费者所属应用标识（点分英文字母数字，区分大小写） 。 例:
		 * com.cmcc.sh.outer.aimt，本文附录中有现在已有系统的定义，如果没有 需要向统一接口管理平台管理小组提出申请新增。
		 */
		String clientId = exchange.getRequest().getHeader("ClientId");

		/**
		 * 用于全路径（调用轨迹）分析。由首个服务消费者创建，后续接力透传。 长度64 字节，区分大小写。要求在各个应用系统、应用域中都具备唯一性。
		 */
		// String transactionId =
		// exchange.getRequest().getHeader("TransactionId");
		/**
		 * 选填
		 */
		// String clientToken = exchange.getRequest().getHeader("ClientToken");

		String data = exchange.getPostdata().getRawDataAsString();
		String objStr;
		try {
			objStr = URLDecoder.decode(data, charset);
		} catch (UnsupportedEncodingException e1) {
			imsLogger.error(e1);
			throw new IMSException(e1);
		}
		String serverName = exchange.getRequest().getLocalAddr();
		int serverPort = exchange.getRequest().getLocalPort();
		String uri = (String) exchange.getRequest().getAttribute("javax.servlet.forward.request_uri");
		try {
			JsonObject objs = JsonUtil.toJsonObject(objStr);

			// 解析json对象效率比较低，解决多次解析的效率问题，现修改成解析一次，之后方request中 xieqr 2012-08-13
			exchange.getRequest().setAttribute(jsonObj, objs);

			busiCode = objs.getAsJsonObject("sOper").getAsInt("busi_code");

			// 设定服务端返回的编码
			exchange.setCharset(charset);
			exchange.setResponseHeader("content-type", "text/html,charset=UTF-8");
			methodName = toMethodName(busiCode);

			ActionInvoker invoker = new ActionInvoker(null) {
				@Override
				protected Object[] buildInvokeParams(Object action, MethodInfo method, ServletExchange request) throws ReflectionException,
						IOException {
					/*
					 * String data = request.getPostdata().getRawDataAsString();
					 * String objStr = URLDecoder.decode(data, charset);
					 * JsonObject objs = JsonUtil.toJsonObject(objStr);
					 */

					JsonObject objs = (JsonObject) request.getRequest().getAttribute(jsonObj);

					int size = method.paramSize();
					Object[] result = new Object[size];
					for (int n = 0; n < size; n++) {
						Param param = method.getNthAnnotation(n);
						Type paramType = method.getParamTypes()[n]; // 要求的参数类型（泛型类型）
						String paramName = param == null ? null : StringUtils.trimToNull(param.value()); // 形参名，可以为null
						JsonElement parameter = objs.get(paramName);
						String paramString = parameter == null ? "{}" : parameter.toString();
						result[n] = JsonUtil.to(paramType, paramString);
					}
					return result;
				}
			};
			json = new JsonResponse();
			try {
				iResult = invoker.invoke(action, methodName, exchange);
			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				sendErrorResponse(exchange, t, json);
				imsLogger.error(e);
				if (isFailRecord) {
					flag = EnumCodeShDefine.HTTP_AND_JSON_FAIL;
					save(busiCode, start, objStr == null ? "" : objStr, serverName, serverPort, clientId, methodName, null, flag, uri,
							t.getMessage());
				}
				return null;
			} catch (Exception e) {
				sendErrorResponse(exchange, e, json);
				imsLogger.error(e);
				if (isFailRecord) {
					flag = EnumCodeShDefine.HTTP_AND_JSON_FAIL;
					save(busiCode, start, objStr == null ? "" : objStr, serverName, serverPort, clientId, methodName, null, flag, uri,
							e.getMessage());
				}
				return null;
			}
			json.setSuccess();
			json.putData("data", iResult.getKey());
			exchange.returnJson(json);
		} finally {
			try {
				if (iResult != null) {
					BaseResponse resp = (BaseResponse) iResult.getKey();
					Long resultCode = resp.getErrorMsg().getResult_code();
					CBSErrorMsg msg = resp.getErrorMsg();
					if (resultCode != 0) {
						if (isFailRecord) {
							flag = EnumCodeShDefine.HTTP_AND_JSON_FAIL;
							save(busiCode, start, objStr == null ? "" : objStr, serverName, serverPort, clientId, methodName, msg, flag,
									uri, null);
						}
					} else {
						if (isSuccRecord) {
							flag = EnumCodeShDefine.HTTP_AND_JSON_SUCC;
							save(busiCode, start, objStr, serverName, serverPort, clientId, methodName, null, flag, uri, null);
						}
					}
				}
			} catch (Exception e) {
				imsLogger.error(e);
			}
		}
		imsLogger.info(start, "*******exit ImsActionDispatcher  ****** succ_flag ", flag, " busi_code ", busiCode, " " + methodName, " ");

		return null;
	}

	/**
	 * 将业务代码 OperationCode转换为实际的方法名
	 * 格式：<TLD>.<Domain>.<Application>.<Service>.<Operation> 例 :
	 * com.cmcc.sh.alplatform.uniinterface.forbank.PT-SH-FS-OI1297 ， 其 中
	 * com.cmcc.sh 前缀， alplatform 是亚联平台域， uniinterface是亚联统一接口系统，
	 * forbank是提供给银行的接口集群，PT-SH-FS-OI1297 是具体的业务操作。
	 */
	private String toMethodName(Integer busiCode) {
		if (busiCode == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "busi_code");
		}
		BaseNode methodNode = BusiUtil.getMethodNode(serviceName, busiCode);
		if (methodNode == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.BUSI_CODE_NOT_EXIST, busiCode);
		}
		// 重新获取method名称
		String newNethod = methodNode.getTagName();
		return newNethod;

	}

	/**
	 * 用来记录HTTPJSON 请求的出入参报文，用于排查问题，系统正常运行后可以通过参数关闭
	 * 
	 * @param busiCode
	 * @param start
	 * @param inputJson
	 * @param outputJson
	 * @param serverName
	 * @param serverPort
	 * @param clientId
	 *            author xieqr
	 *            Date 2012-12-09
	 */
	private void save(Integer busiCode, long start, String inputJson, String serverName, int serverPort, String clientId,
			String methodName, CBSErrorMsg msg, Integer flag, String uri, String frameErr) {
		try {
			long end = System.currentTimeMillis();
			long cost = end - start;
			Date soDate = DateUtil.currentDate();
			String monthStr = DateUtil.getFormatDate(soDate, DateUtil.DATE_FORMAT_YYYYMM);
			ImsJsonSoap soap = new ImsJsonSoap();
			soap.setJsonId(StringUtils.generateGuid());
			soap.setBusiCode(busiCode);
			soap.setInterfaceName(methodName);
			soap.setClientId(clientId);
			soap.setTimeCost(cost);
			soap.setServerName(serverName);
			soap.setServerPort(serverPort);
			soap.setInputSoap(inputJson);
			soap.setSoDate(soDate);
			soap.setSoMonth(CommonUtil.string2Integer(monthStr));
			if (msg != null) {
				// errorCode
				soap.setExt1(msg.getResult_code() + "");
				// errorMes
				String eMsg = msg.getError_msg();
				if (CommonUtil.isNotEmpty(eMsg)) {
					long length = eMsg.length();
					if (length > 1024) {
						String sMsgTmp = eMsg.substring(0, 1023);
						soap.setExt2(sMsgTmp);
					} else {
						soap.setExt2(eMsg);
					}
				}
			} else if (frameErr != null) {
				// framework exception
				if (CommonUtil.isNotEmpty(frameErr)) {
					long length = frameErr.length();
					if (length > 1024) {
						String sMsgTmp = frameErr.substring(0, 1023);
						soap.setExt2(sMsgTmp);
					} else {
						soap.setExt2(frameErr);
					}
				}
			}
			soap.setFlag(flag);
			soap.setExt3(uri);
			doInsert(soap);
		} catch (Exception e) {
			imsLogger.error(e);
		}
	}

	/**
	 * 批量操作
	 * 
	 * @throws SQLException
	 * @throws IMSException
	 */
	private void doInsert(ImsJsonSoap soap) throws IMSException, SQLException {
		List<ImsJsonSoap> todo = null;
		synchronized (list) {
			list.add(soap);
			if (list.size() >= BATCH_NUMBER) {
				todo = new ArrayList<ImsJsonSoap>(list);
				list.clear();
			}
		}
		if (todo != null) {
			imsLogger.info("########## begin：doInsert ##########");
			// DBUtil.insertBatch(todo);
			Batch<ImsJsonSoap> batch = DBUtil.getDBClient().startBatchInsert(todo.get(0));// 这里不要用class，改用数组的第一个要插入的对象即可
			batch.setGroupForPartitionTable(true);
			batch.add(todo);
			try {
				batch.commit();
			} catch (Exception e1) {
				imsLogger.error("Error at batchinsert commit", e1);
				// 解决插入异常的时候 连接无法释放的问题
				batch.cancel();
			}
			imsLogger.info("########## end：doInsert ##########");
		}
	}
}