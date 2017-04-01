package com.ailk.imssh.common.flow.scandeal.scan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;

/**
 * @Description:
 * @author liucc
 * @Date 2015-10-15
 */
public class ErrDealScan {
	private ImsLogger imsLogger = new ImsLogger(ErrDealScan.class);
	/**
     * 可以接收一个年月参数，YYYYMM
     */
    public static void main(String[] args)
    {
        ImsLogger imsLogger = new ImsLogger(ErrDealScan.class);
        ITableUtil.startTs();
        
        while (!OLTPServiceContext.isInitSpringFinished())
        {
            try
            {
                imsLogger.debug("***spring context not start,sleep 5 s");
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                imsLogger.error(e.getMessage());
            }
        }
        
        String YYYYMM=null;
        try{
        	YYYYMM = args[0];

        	String regEx = "20[1-9][0-9][01][0-9]";
        	Pattern pattern = Pattern.compile(regEx);
        	Matcher matcher = pattern.matcher(YYYYMM);
        	if(!matcher.matches()){
        		imsLogger.error("================= i_data_index_err subTable Param "+YYYYMM+" is not a valid param, process exits! ===============");
        		return;
        	}
        	
        }catch(ArrayIndexOutOfBoundsException e){
        	Date curDate= new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			YYYYMM = sdf.format(curDate).substring(0,6);
        }finally{
        	try {
        		SpringExUtil.getErrDealService().errDeal(YYYYMM);
        	} catch (Exception e) {
        		imsLogger.error(e,e);
        	}
        }
        
        
    }

}
