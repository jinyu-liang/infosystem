package com.ailk.ims.component;

import java.util.List;

import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;

/**
 * @Description: 用户销户操作类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author user
 * @Date 2011-9-27
 */
public class TerminalUserComponent extends BaseComponent
{

    public TerminalUserComponent()
    {
    }
    
    public void terminateSplitAndReguid(Long userId){
    	BaseProductComponent prodCmp =  context.getComponent(BaseProductComponent.class);
    	SplitComponent splitCmp = context.getComponent(SplitComponent.class);
    	ReguideUsageComponent usageCmp = context.getComponent(ReguideUsageComponent.class);
    	ReguideChargeComponent chargeCmp = context.getComponent(ReguideChargeComponent.class);
    	List<CoProd> prodList = prodCmp.queryProdListByUserId(userId);

        // 删除split，regauid
        for (CoProd coPprod : prodList)
        {
            if (coPprod.getBusiFlag() == SpecCodeDefine.SPLIT)
            {
            	splitCmp.deleteSplitInfoByProdId(coPprod.getProductId());
            }
            if(coPprod.getBusiFlag() == SpecCodeDefine.REGUIDE_USAGE)
            {
            	imsLogger.info("delete user split or reguide-usage: productId=" + coPprod.getProductId() + ",busi_flag = "
            			+ coPprod.getBusiFlag());
            	usageCmp.deleteReguideInfoByProdId(coPprod.getProductId());
            }
        }
        
        // 删除用户被代付的产品，下周起生效
        // @Date 2012-3-31 tangjl5 修改删除用户被代付的产品的调用的方法
//        List<CoProd> userSplitProds = splitCmp.queryProdsByUserSplit(userId);
        List<CoProd> userSplitProds = context.getComponent(SplitComponent.class).queryProdsByObjectSplit(userId, Long.valueOf(EnumCodeDefine.PROD_OBJECTTYPE_DEV));

        if (CommonUtil.isNotEmpty(userSplitProds))
        {
            for (CoProd coPprod : userSplitProds)
            {
//                prodCmp.deleteProd(coPprod);
            	splitCmp.deleteSplitInfoByProdId(coPprod.getProductId());
            }
        }
        
        // 删除用户被reguid-charge的产品，下周起失效
        // @Date 2012-3-31 tangjl5 修改删除用户被reguid-charge的产品的调用的方法
        List<CoProd> userRedChargeProds =chargeCmp.queryProdListByBepayed(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        if (CommonUtil.isNotEmpty(userRedChargeProds))
        {
            for (CoProd coPprod : userRedChargeProds)
            {
                imsLogger.info("delete user reguid-charge: productId=" + coPprod.getProductId());
                chargeCmp.deleteReguideInfoByProdId(coPprod.getProductId());
            }
        }

        // 删除用户被reguid-usage的产品，下周起失效
        // @Date 2012-3-31 tangjl5 修改删除用户被reguid-usage的产品的调用的方法
        List<CoProd> userRedUsageProds = usageCmp.queryProdListByBepayedUserId(userId);
        if (CommonUtil.isNotEmpty(userRedUsageProds))
        {
            for (CoProd coPprod : userRedUsageProds)
            {
                imsLogger.info("delete user reguid-usage: productId=" + coPprod.getProductId());
//                prodCmp.deleteProd(coPprod);
                usageCmp.deleteReguideInfoByProdId(coPprod.getProductId());
            }
        }
    }
}
