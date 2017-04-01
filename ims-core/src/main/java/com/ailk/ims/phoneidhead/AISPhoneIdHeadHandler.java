package com.ailk.ims.phoneidhead;

import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.busi.entity.RsSysMnpDef;

/**
 * @Description: TODO(AIS项目使用该规则)
 * 1)非0并且非66开头的8位或者9位号码，直接补0
 * @Date 2012-08-16 wangdw5 : #55441 增加CT号码头,非0但以CT格式号码头不处理
 * 2)66开头的10（2+8）、11（2+9/2+1+8）、12(2+1+9),去掉66头后非0开头再补0
 * 3)13位和以上的号码,验证5位MNP号码头,验证通过后去掉MNP头后非0开头再补0
 * 4)其他情况不处理
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author wangdw5
 * @Date 2012-6-18
 */
public class AISPhoneIdHeadHandler implements IPhoneIdHead {
	public static final String AIS_HEAD_0 = "0";
	public static final String AIS_HEAD_66 = "66";
	public static final String CT_HEAD_99 = "99";
	public static final String CT_HEAD_98 = "98";
	public static final String CT_HEAD_97 = "97";
	public static final String CT_HEAD_96 = "96";
	public static final String CT_HEAD_95 = "95";
	public static final String CT_HEAD_89 = "89";
	public static final String CT_HEAD_88 = "88";
	public void phoneIdCheck(String phoneId) 
	{
	    if (!CommonUtil.isIn(phoneId, new String[] { CT_HEAD_99, CT_HEAD_98, CT_HEAD_97, CT_HEAD_96, CT_HEAD_95, CT_HEAD_89,CT_HEAD_88 })
	            && !(phoneId.startsWith(AIS_HEAD_0) && phoneId.length() > 8))
        {
	        IMSUtil.throwBusiException(ErrorCodeDefine.AIS_PREFIX_PHONEID_NOT_MATCH, phoneId);
	    }
	    
	}
	
	private boolean isCTPhone(String phoneId)
	{
	    return CommonUtil.isIn(phoneId.substring(0, 2), new String[] { CT_HEAD_99, CT_HEAD_98, CT_HEAD_97, CT_HEAD_96, CT_HEAD_95, CT_HEAD_89,CT_HEAD_88 });
        
	}
	
	/**
     * 计费送过来的号码，转换号码头需要判断是手机还是固话（由于增加了CT号码头特殊处理）
     * luojb 2012-10-30
     * @param phoneId
     * @param deviceType
     * @return
     */
    public String phoneIdParse(String phoneId,Short deviceType)
    {
        phoneId = phoneIdParse(phoneId);
        if(deviceType != null && deviceType == EnumCodeDefine.XDR_DEVICE_TYPE_MOBILE && !phoneId.startsWith(AIS_HEAD_0))
        {
            phoneId = AIS_HEAD_0.concat(phoneId);
        }
        return phoneId;
    }
	
	public String phoneIdParse(String phoneId) {
	    //@Date 2012-10-22 yangjh : 过滤phoneId长度为1的情况
        if(!CommonUtil.isValid(phoneId) || phoneId.length() < 2){
            return phoneId;
        }
        if (isCTPhone(phoneId.substring(0, 2)))
        {
            return phoneId;
        }
        else if(!phoneId.startsWith(AIS_HEAD_0) && !phoneId.startsWith(AIS_HEAD_66)
                && (phoneId.length() == 8 || phoneId.length() == 9)){
            phoneId = AIS_HEAD_0.concat(phoneId);
        }
        else if(phoneId.startsWith(AIS_HEAD_66) 
                && (phoneId.length() == 10 || phoneId.length() == 11 || phoneId.length() == 12)){
            phoneId = phoneId.substring(2);
            if(!isCTPhone(phoneId.substring(0, 2)) &&!phoneId.startsWith(AIS_HEAD_0))
                phoneId = AIS_HEAD_0.concat(phoneId);
        }
        else if(phoneId.length() >= 13){
            String mnpPrefix = phoneId.substring(0, 5);
            RsSysMnpDef def = DBConfigInitBean.getSingleCached(RsSysMnpDef.class, new CacheCondition(RsSysMnpDef.Field.mnpPrefix,mnpPrefix));
            if(def == null){
                IMSUtil.throwBusiException(ErrorCodeDefine.MNPPREFIX_NOT_CONFIG,phoneId);
            }else{
                phoneId = phoneId.substring(5);
                if(!phoneId.startsWith(AIS_HEAD_0))
                    phoneId = AIS_HEAD_0.concat(phoneId);
            }
        }
        return phoneId;
    }
}
