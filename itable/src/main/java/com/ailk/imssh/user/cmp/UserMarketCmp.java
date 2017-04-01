/**
 * 
 */
package com.ailk.imssh.user.cmp;

import com.ailk.ims.common.DBCondition;
import com.ailk.openbilling.persistence.cust.entity.CmUserMarket;
import com.ailk.openbilling.persistence.itable.entity.IUserMarket;

/**
 * @Description: 数据业务超时处理
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author songcc
 * @Date 2014-1-13
 */
public class UserMarketCmp extends UserQuery
{

    public UserMarketCmp()
    {

    }

    /**
     * userMarket 插入操作
     * @param iUserMarket
     */
    public void createUserMarket(IUserMarket iUserMarket)
    {

        CmUserMarket cmUserMarket = copyFromIUserMarket(iUserMarket);
        cmUserMarket.setCreateDate(iUserMarket.getCommitDate());
        cmUserMarket.setValidDate(iUserMarket.getValidDate());
        this.insert(cmUserMarket);
    }

    /**
     * UserMarket更新操作
     * @param iUserMarket
     */
    public void updateUserMarket(IUserMarket iUserMarket)
    {

        CmUserMarket cmUserMarket = copyFromIUserMarket(iUserMarket);
      
        this.updateByCondition(cmUserMarket, new DBCondition(
                CmUserMarket.Field.resourceId, cmUserMarket.getResourceId()),new DBCondition(
                        CmUserMarket.Field.productId, iUserMarket.getProductId()));
    }

    /**
     * 刪除操作
     * @param iUserMarket
     */
    public void deleteUserMarket(IUserMarket iUserMarket)
    {

        this.deleteByCondition(CmUserMarket.class, new DBCondition(
                CmUserMarket.Field.resourceId, iUserMarket.getUserId()),new DBCondition(
                        CmUserMarket.Field.productId, iUserMarket.getProductId()));
    }

    /**
     * 公用的IUserMarket到CmUserMarket对象值的设置，避免重复代码编写
     * @param iUserMarket
     * @return
     */
    private CmUserMarket copyFromIUserMarket(IUserMarket iUserMarket)
    {
        CmUserMarket cmUserMarket = new CmUserMarket();
        cmUserMarket.setResourceId(iUserMarket.getUserId());
        cmUserMarket.setProductId(iUserMarket.getProductId());
        cmUserMarket.setBusiType(iUserMarket.getBusiType());
        cmUserMarket.setSpCode(iUserMarket.getSpCode());
        cmUserMarket.setSpType(iUserMarket.getSpType());
        cmUserMarket.setOperatorCode(iUserMarket.getOperatorCode());
        cmUserMarket.setServiceCode(iUserMarket.getServiceCode());
        cmUserMarket.setRegionCode(iUserMarket.getRegionCode());
        cmUserMarket.setExtend1(iUserMarket.getExtend1());
        cmUserMarket.setExtend2(iUserMarket.getExtend2());
        cmUserMarket.setRemark(iUserMarket.getRemark());
        cmUserMarket.setExpireDate(iUserMarket.getExpireDate());
        cmUserMarket.setSoNbr(context.getSoNbr());
        cmUserMarket.setSoDate(iUserMarket.getCommitDate());
        return cmUserMarket;
    }
}
