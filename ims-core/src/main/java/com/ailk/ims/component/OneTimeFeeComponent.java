package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.ImsSonbrMapping;

/**
 * @Description:一次性费用组件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-9-27
 * @author wukl 2012-03-08 hybrid用户支持下OneTimeFee业务，需要根据配置的billingType进行
 * @Date 2012-4-4 luojb deductFlag=true不抛错也不验证返回结构
 * @Date 2012-4-17 tangjl5 若账户不存在主用户时，bill_type设置为后付费
 * @Date 2012-3-23 tangjl5 在收取一次性费用时，传入操作次数 User Story #41727
 * @Date 2012-6-27 tanjl5 Bug #48452 一次性增加三个fn号码，但是还是扣除一次一次性费用
 * @Date 2012-6-28 tangjl5 Bug #49608 一次性费用接口返回成功但是没有扣费成功.事件改为0
 * @Date 2012-07-25 wuyujie : 一个业务中目前支持对一个3hbean对象进行收取一次性费用
 * @date 2012-10-05 luojb On_Site Defect #60464
 */
public class OneTimeFeeComponent extends BaseComponent
{
    public OneTimeFeeComponent()
    {
    }



    /**
     * @Description: 根据CRM传入的soNbr获取账管的soNbr及soDate
     * @param orgSoNbr
     * @return
     * @author wukl 2011-10-11
     */
    public List<ImsSonbrMapping> queryOnetimeFee(String orgSoNbr, Long oneTimeSeq)
    {
//        ImsSonbrMapping nbrMap = new ImsSonbrMapping();
        List<DBCondition> cond  = new ArrayList<DBCondition>();
        cond.add(new DBCondition(ImsSonbrMapping.Field.outSoNbr,orgSoNbr));
//        nbrMap.setOutSoNbr(orgSoNbr);
        if (oneTimeSeq != null)
        {
//            nbrMap.setBosSoNbr(oneTimeSeq);
            cond.add(new DBCondition(ImsSonbrMapping.Field.bosSoNbr,oneTimeSeq));
        }

        List<ImsSonbrMapping> list = query(ImsSonbrMapping.class,cond.toArray(new DBCondition[cond.size()]));
        if (CommonUtil.isEmpty(list))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHARGE_ITEM_NOT_EXIST);
        }

        return list;
    }

}
