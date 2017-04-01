package com.ailk.ims.business.query.ims3h;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ailk.openbilling.persistence.imsinner.entity.AccountRet;
import com.ailk.openbilling.persistence.imsinner.entity.BaseQueryRet;
import com.ailk.openbilling.persistence.imsinner.entity.CustomerRet;
import com.ailk.openbilling.persistence.imsinner.entity.UserRet;

public class Query3hHelper
{
   /**
 * @yanchuan 将客户饭2012-2-27
 * @param <T>
 * @param retList
 * @param ret
 */
  public static <T extends BaseQueryRet> void tranFormCust(List<T> retList,CustomerRet ret)
   {
      List<AccountRet> acctRets = (List<AccountRet>)retList;
       for(AccountRet acctRet:acctRets)
       {
    	   BeanUtils.copyProperties(ret, acctRet);
//           acctRet.setCust_segment(ret.getCust_segment());
//           acctRet.setBirthday(ret.getBirthday());
//           acctRet.setCountry_id(ret.getCountry_id());
//           acctRet.setCust_id(ret.getCust_id());
//           acctRet.setCountry_id(ret.getCountry_id());
//           acctRet.setCust_rank(ret.getCust_rank());
//           acctRet.setCust_segment(ret.getCust_segment());
//           acctRet.setCust_title(obj)
       }
   }
   /**
 * Administrator 2012-2-27
 * @param <T>
 * @param retList
 * @param ret
 */
   public static <T extends BaseQueryRet>  void tranFormAcct(List<T> retList,AccountRet ret)
   {
	   List<UserRet> userRets=(List<UserRet>)retList;
	   for(UserRet userRet:userRets){
		   BeanUtils.copyProperties(ret, userRet);
	   }
   }
}
