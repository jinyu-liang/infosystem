package com.ailk.ims.proxy;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.BaseMdbBean;
import com.ailk.ims.common.DBRelRefreshBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.common.InnerClass;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.listener.IDBOperatorListener;
import com.ailk.ims.listener.IMSListenerServiceFlow;
import com.ailk.ims.listener.action.MdbSyncListenerAction;
import com.ailk.ims.listener.action.NotifiListenerAction;
import com.ailk.ims.listener.action.PreorderListenerAction;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmBusiOrder;
import com.ailk.openbilling.persistence.cust.entity.ImsSonbrMapping;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description: 业务流程控制类。此类是单例的，请不要定义任何与当前请求相关的数据！！！
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-8-9 2011-08-16 wuyujie : 增加mdb异常处理，支持异步上发 2011-08-31 wuyujie : 增加onetimefee处理 2012-02-09 wuyujie : 非查询类需要关联更新
 * @Date 2012-3-29 luojb 通知扣费从鉴权组件中提取出来，放到上发mdb之后处理
 * @Date 2012-5-14 tangjl5判断notify_flag参数是否发送告警，默认发送
 * @Date 2012-05-17 wangdw5 [45310]3hbean中查询不到信息需要抛特定异常
 * @Date 2012-05-19 wangdw5 [46164]notification发送和MDB上发顺序优化
 * @Date 2012-06-11 wuyujie : 如果是内部查询且抛出的IMS3hNotFoundException，则需要捕获，不应该再抛出去，否则还是需要抛出
 * @Date 2012-07-16 yangjh : 捕获业务异常增加busiBean.busiFail(e, input,context); 在changeProduct和changeMainProd中会重载这个方法
 * @Date 2012-08-28 wukl 处理账管查询用户相关表时分表出错的问题，待分表参数的新方案改动后，可删除这段代码
 */
@Transactional
public class IMSServiceProxy implements IBusiProxy
{
    ImsLogger logger =new ImsLogger(this.getClass());

    public BaseResponse doService(IMSContext context, Object... input) throws BaseException
    {
        BaseResponse response = null;
        BaseBusiBean busiBean = null;
        //@Date 2012-09-14 yugb : [590009]事务一致性改造
        IDBOperatorListener dbListener = SpringUtil.getDbOperatorListener();
        try
        {
            /*
             * 一个流程对应一个actionContainer，每个container里可以包含多个action 这样保证嵌套的子流程也有自己对应的actionContainer，互不影响
             */
            IMSListenerServiceFlow serviceFlow = new IMSListenerServiceFlow(context);
            dbListener.addActionContainer(serviceFlow);
            context.setListenerServiceFlow(serviceFlow);

            String serviceName = context.getServiceName();
            String methodName = context.getMethodName();

            // 根据busicode获取业务处理类
            busiBean = BusiUtil.getBusiBean(serviceName, methodName);

            if (busiBean == null)
            {
                throw new IMSException("no busi bean is configured with <service: " + serviceName + "> <method: " + methodName
                        + ">");
            }
            busiBean.setContext(context);

            // 业务记录检测
            long start = System.currentTimeMillis();
            boolean busiRecord = IMSUtil.isBusiRecord(context);

            if (busiRecord&&!ProjectUtil.is_CN_SH())
            {
                logger.info("begin to check busi key", context);
                busiBean.checkBusiKey(input);
                logger.info("finish to check busi key", start, context);
            }
            // 初始化工作
            start = System.currentTimeMillis();
            logger.info("begin to init", context);
            busiBean.init(input);
            logger.info("finish to init", start, context);

            // 数据合法性校验
            start = System.currentTimeMillis();
            logger.info("begin to validate", context);
            busiBean.validate(input);
            logger.info("finish to validate", start, context);

            // 创建main3hbean
            logger.info("begin to createMain3hBeanList", context);
            List<IMS3hBean> beans = busiBean.createMain3hBeans(input);
            if (CommonUtil.isNotEmpty(beans))
            {
                for (IMS3hBean bean : beans)
                {
                    if (bean instanceof User3hBean)
                    {
                        IMSUtil.setUserRouterParam(bean.getUserId());
                    }
                    else if(bean instanceof Acct3hBean)
                    {
                        IMSUtil.setAcctRouterParam(bean.getAcctId());
                    }
                    else
                    {
                        IMSUtil.setCustRouterParam(bean.getCustId());
                    }
                }
            }
            context.setMain3hBean(beans);

            logger.info("finish to createMain3hBeanList", start, context);

            // 非内部接口 验证main3hbean业务状态
            // 2012-06-06 wuyujie 暂时注释
            if (!IMSUtil.isInnerSystem(context))
            {
                if (IMSUtil.isNeedCreate3hBean(context) && CommonUtil.isEmpty(context.getMain3hBeanList()))
                {// 检验是否有创建3hbean
                    throw IMSUtil.throwBusiException("createMain3hBeanList not success!");
                }
                //caohm5 上海不需要
                if(!ProjectUtil.is_CN_SH()){
                	logger.info("begin to checkMain3hBeanBusiState", context);
                	context.getComponent(BaseLifeCycleComponent.class).checkMain3hBeanBusiState(context.getMain3hBeanList(),
                			context.getOper().getBusi_code());
                	logger.info("finish to checkMain3hBeanBusiState", context);
                }
            }

            // 执行业务操作
            start = System.currentTimeMillis();
            logger.info("begin to do business", context);
            Object[] busiResult = busiBean.doBusiness(input);
            logger.info("finish to do business", start, context);

            // 创建应答对象
            start = System.currentTimeMillis();
            logger.info("begin to create response", context);
            response = busiBean.createResponse(busiResult);
            logger.info("finish to create response", start, context);

            // 2012-02-09 wuyujie : 非查询类需要关联更新
            if (context.isNotQueryBusi())
            {
                start = System.currentTimeMillis();
                logger.info("begin to refresh relation", context);
                new DBRelRefreshBean(context).refreshRelations();
                logger.info("finish to refresh relation", start, context);
            }
            // 如果有一次性费用，需要收取
            // 2011-08-31 wuyujie : 增加onetimefee处理
            BaseNode methodNode = context.getMethodConfig();
            Boolean serviceOTC = methodNode.getParent().getBooleanAttribute(ConstantDefine.IS_ONE_TIME_FEE, true);
            Boolean methodOTC = methodNode.getBooleanAttribute(ConstantDefine.IS_ONE_TIME_FEE, true);

            if (!serviceOTC || !methodOTC)
            {
                logger.info("no need to charge onetime fee", context);
            }
            else
            {
                start = System.currentTimeMillis();
                logger.info("begin to charge onetime fee", context);
                // 业务中的一次性费用需要把orgin_so_nbr填为SOper中的外围so_nbr
                ImsSonbrMapping otc_mapping = busiBean.chargeOneTimeFee();
                context.addExtendParam(ConstantDefine.ONETIMEFEE_OBJECT, otc_mapping);
                logger.info("finish to charge onetime fee", start, context);
            }

            // 创建业务记录
            if (busiRecord)
            {
                start = System.currentTimeMillis();
                logger.info("begin to create busi record", context);
                createBusiRecord(busiBean, input);
                logger.info("finish to create busi record", start, context);
            }

            // 注册action
            registerListenerActions(serviceFlow, busiBean, input, busiResult, response);

            // 统计业务数量
            // 2012-08-16 linzt 调用框架的方法对业务创建的统计日志进行处理
            InnerClass.WriteCapInfo wCapIf = busiBean.createWriteCapInfo(input);
            start = System.currentTimeMillis();
            if (wCapIf != null)
            {
                try
                {
                    logger.info("begin to count[" + wCapIf.getUri() + "]", context);
                    com.ailk.easyframe.sdl.OLTPServiceContext.writeCap(wCapIf.getUri(), wCapIf.getMap());
                    logger.info("finish to count", start, context);
                }
                catch (Exception e)
                {
                    logger.info("**********************error of count:" + e.getMessage(), e);
                }
            }

            // 数据清理工作
            start = System.currentTimeMillis();
            logger.info("begin to destroy", context);
            busiBean.destroy();
            logger.info("finish to destroy", start, context);
            
            //@Date 2012-08-28 wukl 处理账管查询用户相关表时分表出错的问题，TODO 待分表参数的新方案改动后，可删除这段代码
            //账管自己查询用户有效期表时，由于先调用了查询用户信息的接口，此时session中设置了分表参数ACCT_ID，这就导致账管查另外用户的有效期出现分表错误；
            IMSUtil.removeRouterParam();
        }catch(IMS3hNotFoundException e){
            // 2012-06-11 wuyujie : 如果是内部查询且抛出的IMS3hNotFoundException，则需要捕获，不应该再抛出去，否则还是需要抛出
            if (IMSUtil.isInnerSystem(context) && !context.isNotQueryBusi())
            {
                logger.error(e, e);
            }
            else
            {
                throw e;
            }
        }
        catch (Exception e)
        {
            try
            {
                // 代码优化
                busiBean.busiFail(e, input);
            }
            catch (Exception e2)
            {
                // TODO: handle exception
            	 logger.error(e2, e2);
            }
            IMSUtil.throwBusiException(e);
        }
        return response;
    }

    // private void sendNotification(Object[] input, Object[] busiResult, BaseResponse resp, BaseBusiBean busiBean,
    // IMSContext context)
    // {
    // try
    // {
    // List<IMSNotification> notifications = busiBean.createNotifications(input, busiResult, resp);
    // if (CommonUtil.isEmpty(notifications))
    // return;
    // IMSUtil.doNotification(notifications, context);
    // }
    // catch (Exception e)
    // {
    // logger.error(e, e);// 告警发送失败不影响前面的业务操作
    // }
    // }

    /*
     * private void registerNotifiBean(DbListenerFlow listenerBean,List<IMSNotification> notifications){ IMSDbOperatorListener
     * listener = SpringUtil.getDbOperatorListener(); }
     *//**
     * 注册mdbbean，让mdbbean在数据库commit之后再执行
     * 
     * @param context
     * @return
     */
    /*
     * private void registerMdbBean(DbListenerFlow listenerBean,){ IMSContext context = listenerBean.getContext();
     * listenerBean.setMdbBean(mdbBean); }
     */

    private MdbSyncListenerAction createMdbListenerAction(IMSContext context)
    {
        String serviceName = context.getServiceName();
        String methodName = context.getMethodName();
        BaseMdbBean mdbBean = BusiUtil.getMdbBean(serviceName, methodName);
        if (mdbBean == null)
            return null;
        mdbBean.setContext(context);
        context.setSync(true);// 表示同步，用于区分异步操作

        MdbSyncListenerAction action = new MdbSyncListenerAction();
        action.setMdbBean(mdbBean);
        return action;
    }

    /**
     * @Description: 处理业务记录
     * @author : wuyj
     * @date : 2011-11-1
     * @param busiBean
     * @param input
     */
    private void createBusiRecord(BaseBusiBean busiBean, Object[] input)
    {
        // 创建业务记录总表
        List<CmBusi> busiList = busiBean.createBusiRecord(input);
        if (!CommonUtil.isEmpty(busiList))
        {
            DBUtil.insertBatch(busiList);
        }
        else
        {
            return;// 总表没记录的话，子表不需要再记了.
        }
        // 创建业务记录子表
        List<CmBusiOrder> busiOrderList = busiBean.createBusiOrderRecord(input);
        if (!CommonUtil.isEmpty(busiOrderList))
        {
            DBUtil.insertBatch(busiOrderList);
        }
    }

    /**
     * 一些逻辑需要在数据库commit之后处理，这里创建action并添加到IMSListenerServiceFlow的actionList中
     * 
     * @param serviceFlow
     * @param busiBean
     * @param input
     * @param busiResult
     * @param resp
     */
    public void registerListenerActions(IMSListenerServiceFlow serviceFlow, BaseBusiBean busiBean, Object[] input,
            Object[] busiResult, BaseResponse resp)
    {
        IMSContext context = serviceFlow.getContext();
        // 注册mdb action
        MdbSyncListenerAction mdbAction = createMdbListenerAction(context);
        serviceFlow.setMdbAction(mdbAction);

        //上海工程只用到mdb action，其它action请添加到分支中
        if (!ProjectUtil.is_CN_SH())
        {
            // 注册告警action
            boolean needNotifi = context.getOper().getNotify_flag() == null
                    || context.getOper().getNotify_flag() == EnumCodeDefine.NOTIFY_YES;
            if (needNotifi)
            {
                List<IMSNotification> notifications = busiBean.createNotifications(input, busiResult, resp);
                if (CommonUtil.isNotEmpty(notifications))
                {
                    NotifiListenerAction action = new NotifiListenerAction();
                    action.setNotifications(notifications);
                    serviceFlow.addActionBeforeMdb(action);// 绝大部分的告警在mdb上发之前执行
                }
            }

            // 注册通知扣费action
            if (context.getExtendParam(ConstantDefine.PREORDER_PRODUCT_LIST_MAP) != null)
            {
                serviceFlow.addActionAfterMdb(new PreorderListenerAction());
            }
        }
    }
}
