package com.ailk.ims.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jef.tools.StringUtils;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;

/**
 * @Description:直接封装帐管接口帮助类
 * @author wangjt
 * @Date 2012-3-3
 * @xieqr 2012-3-29 convertImsObj2AmsObj 增加 string 2 date;date 2 string 转换
 */

public class PackingUtil
{

    

    
    
    
    
    public static List<CaFreeres> mergeFreeResByItems(List<CaFreeres> list,List<String>items){
            List<CaFreeres> result=new ArrayList<CaFreeres>();
            for(String group:items){
                CaFreeres res=mergeFreeResByItems(list,group);
                if(null!=res){
                  result.add(res);
                }
            }
            return result;
    }
    
    /**
     * 免费资源 按科目编号合并汇总
     * 
     * @param list
     * @return
     */
    public static CaFreeres mergeFreeResByItems(List<CaFreeres> list, String items)
    {
        CaFreeres res=null;
         if(list.size()>0){
             for(int index=0;index<list.size();index++){
                 CaFreeres temp=list.get(index);
                 if(items.contains(String.valueOf(temp.getItemCode()))){
                     {
                         if(null==res){
                             res=temp;
                         }else{                         
                         res.setUnit(temp.getUnit()+res.getUnit());
                         res.setRealUnit(temp.getRealUnit()+res.getRealUnit());
                         }
                     }
                 }
                 
             }
         }
         return res;
    }
    
    /**
     * 免费资源 按科目编号合并汇总 xieqr 2012-7-6
     * 
     * @param list
     * @return
     */
    
    public static List<com.ailk.openbilling.persistence.imscnsh.entity.FreeResource> mergeFreeResByItemCode(
            List<com.ailk.openbilling.persistence.imscnsh.entity.FreeResource> list)
    {
        if (list == null || list.isEmpty())
        {
            return null;
        }
        List<com.ailk.openbilling.persistence.imscnsh.entity.FreeResource> imsList = new ArrayList<com.ailk.openbilling.persistence.imscnsh.entity.FreeResource>();
        Map map = new HashMap();
        for (com.ailk.openbilling.persistence.imscnsh.entity.FreeResource res : list)
        {
            Integer itemCode = res.getItem_code();
            if (map.get(itemCode) != null)
            {
                com.ailk.openbilling.persistence.imscnsh.entity.FreeResource tmp = (com.ailk.openbilling.persistence.imscnsh.entity.FreeResource) map
                        .get(itemCode);
                tmp.setAmount((tmp.getAmount() != null ? tmp.getAmount() : 0) + (res.getAmount() != null ? res.getAmount() : 0));
                tmp.setUsed_value((tmp.getUsed_value() != null ? tmp.getUsed_value() : 0)
                        + (res.getUsed_value() != null ? res.getUsed_value() : 0));
                tmp.setRemain_value((tmp.getRemain_value() != null ? tmp.getRemain_value() : 0)
                        + (res.getRemain_value() != null ? res.getRemain_value() : 0));
                tmp.setProduct_id(null);
                tmp.setOffer_id(null);
                map.put(itemCode, tmp);
            }
            else
            {
                map.put(itemCode, res);
            }
        }

        Collection<com.ailk.openbilling.persistence.imscnsh.entity.FreeResource> c = map.values();
        Iterator it = c.iterator();
        for (; it.hasNext();)
        {
            imsList.add((com.ailk.openbilling.persistence.imscnsh.entity.FreeResource) it.next());
        }
        return imsList;
    }

    /**
     * 把字符串转换成MD5加密串 xieqr 2012-7-12
     * 
     * @param source
     * @return
     */
    public static String getMd5Str(String source)
    {
        if (CommonUtil.isEmpty(source))
        {
            return "";
        }
        String ret = StringUtils.getMD5(source);
        return ret;
    }

    

   

    /*
    public static com.ailk.openbilling.persistence.imscnsh.entity.BillTaxItem copyFromBillTaxItem(BillTaxItem bean)
    {
        com.ailk.openbilling.persistence.imscnsh.entity.BillTaxItem billTaxItem = new com.ailk.openbilling.persistence.imscnsh.entity.BillTaxItem();
        billTaxItem.setExt1(bean.getExt1());
        billTaxItem.setExt2(bean.getExt2());
        billTaxItem.setFee_before_tax(bean.getFee_before_tax());
        billTaxItem.setItem_fee(bean.getItem_fee());
        billTaxItem.setItem_id(bean.getItem_id());
        billTaxItem.setItem_name(bean.getItem_name());
        
        List<BillTaxItem> list16 = bean.getSecondItemList();
        if (list16 != null && list16.size() > 0)
        {

            List<com.ailk.openbilling.persistence.imscnsh.entity.BillTaxItem> imsList16 = new ArrayList<com.ailk.openbilling.persistence.imscnsh.entity.BillTaxItem>(
                    list16.size());
            for (int i = 0; i < list16.size(); i++)
            {

                imsList16.add(copyFromBillTaxItem(list16.get(i)));
            }
            billTaxItem.setSecondItemList(imsList16);
        }
        billTaxItem.setTax_fee(bean.getTax_fee());
        billTaxItem.setTax_percent(bean.getTax_percent());
        return billTaxItem;
    }
    */
   
    
   
}
