package com.ailk.ims.innerclass;

import com.ailk.ims.define.ConstantDefine;

/**
 * 用于匹配模式的构建对象
 * @Description
 * @author wuyj
 * @Date 2012-12-7
 */
public class PatternMatchBuilder implements IMatchBuilder
{

    private Object customerClass;
    private Object customerType;
    private Object customerSegment;
    
    private Object accountType;
    private Object regionCode;
    
    private Object resType;
    private Object resSegment;
    private Object billingType;
    
    private Object brandId;
    private Object mainPromotion;
   
    /**
     * 把对象解析成固定格式的字符串，用于后续方便匹配。不同值之间用_分割，如果是null则解析成-1，表示通配。
     * 比如一个customerClass=1,
     * customerType=2,
     * customerSegment=3,
     * accountType=-1,
     * regionCode=0571,
     * resType=5,
     * resSegment=-1,
     * billingType=0,
     * mainPromotion=-1
     * 最终会解析成：
     * 1_2_3_-1_0571_5_-1_0_-1,这个顺序必须是固定的。
     * @author wuyj 2012-12-7
     * @return
     */
    public String build(){
        StringBuffer sb = new StringBuffer();
        
        sb.append((customerClass == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : customerClass).append(ConstantDefine.MATCHBUILDER_SEPERATOR)
        .append((customerType == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : customerType).append(ConstantDefine.MATCHBUILDER_SEPERATOR)
        .append((customerSegment == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : customerSegment).append(ConstantDefine.MATCHBUILDER_SEPERATOR)
        .append((accountType == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : accountType).append(ConstantDefine.MATCHBUILDER_SEPERATOR)
        .append((regionCode == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : regionCode).append(ConstantDefine.MATCHBUILDER_SEPERATOR)
        .append((resType == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : resType).append(ConstantDefine.MATCHBUILDER_SEPERATOR)
        .append((resSegment == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : resSegment).append(ConstantDefine.MATCHBUILDER_SEPERATOR)
        .append((billingType == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : billingType).append(ConstantDefine.MATCHBUILDER_SEPERATOR)
        .append((brandId == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : brandId).append(ConstantDefine.MATCHBUILDER_SEPERATOR)
        .append((mainPromotion == null) ? ConstantDefine.MATCHBUILDER_COMMON_MATCH : mainPromotion)
        ;
        
        return sb.toString();
    }
    
    public PatternMatchBuilder brandId(Object brandId){
        this.brandId = brandId;
        return this;
    }
    public PatternMatchBuilder mainPromotion(Object mainPromotion){
        this.mainPromotion = mainPromotion;
        return this;
    }
    public PatternMatchBuilder regionCode(Object regionCode){
        this.regionCode = regionCode;
        return this;
    }
    public PatternMatchBuilder customerClass(Object customerClass){
        this.customerClass = customerClass;
        return this;
    }
    public PatternMatchBuilder customerType(Object customerType){
        this.customerType = customerType;
        return this;
    }
    public PatternMatchBuilder customerSegment(Object customerSegment){
        this.customerSegment = customerSegment;
        return this;
    }
    public PatternMatchBuilder accountType(Object accountType){
        this.accountType = accountType;
        return this;
    }
    public PatternMatchBuilder billingType(Object billingType){
        this.billingType = billingType;
        return this;
    }
    public PatternMatchBuilder resType(Object resType){
        this.resType = resType;
        return this;
    }
    public PatternMatchBuilder resSegment(Object resSegment){
        this.resSegment = resSegment;
        return this;
    }
}
