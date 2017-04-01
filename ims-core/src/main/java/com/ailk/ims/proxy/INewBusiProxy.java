/**
 * 
 */
package com.ailk.ims.proxy;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.IMSContext;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 
 * @author wangdw5
 * @Date 2012-9-26
 */
public interface INewBusiProxy
{
    public void createDoneCode(IMSContext context,Object... input) throws BaseException;
    public List<Object> invokeArguments(IMSContext context,Object... input) throws BaseException;
    public BaseResponse doService(IMSContext context,Object... input) throws BaseException;
}
