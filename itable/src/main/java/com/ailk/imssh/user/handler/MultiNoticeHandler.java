package com.ailk.imssh.user.handler;

import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.MultiNoticeCmp;
import com.ailk.openbilling.persistence.itable.entity.IMultiNotice;

/**
 * 多渠道提醒业务
 * 
 * @author chexd
 * @date 2015-03-26
 */
public class MultiNoticeHandler extends BaseHandler
{

    @Override
    public void handle(List<? extends DataObject>... dataArr) throws IMSException
    {
        List<? extends DataObject> dataList = dataArr[0];
        MultiNoticeCmp multiNoticeCmp = this.getContext().getCmp(MultiNoticeCmp.class);
        for (int j = 0; j < dataList.size(); j++)
        {

            IMultiNotice iMultiNotice = (IMultiNotice) dataList.get(j);
            nullDateDeal(iMultiNotice);
            // 增加分表参数
            ITableUtil.setValue2ContextHolder(null, null, iMultiNotice.getUserId());
            switch (iMultiNotice.getOperType())
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                multiNoticeCmp.addMultiNotice(iMultiNotice);
                break;

            case EnumCodeExDefine.OPER_TYPE_DELETE:
                multiNoticeCmp.deleteMultiNotice(iMultiNotice);
                break;
            default:
                imsLogger.error("I表操作类型不正确。OPER_TYPE:" + String.valueOf(iMultiNotice.getOperType()));
                break;
            }
        }
    }

    /**
     * 日期为空时处理 ..
     * 
     * @param iMultiNotice
     */
    private void nullDateDeal(IMultiNotice iMultiNotice)
    {
        if (iMultiNotice.getValidDate() == null)
        {
            iMultiNotice.setValidDate(context.getCommitDate());
        }
        if (iMultiNotice.getExpireDate() == null)
        {
            iMultiNotice.setExpireDate(DateUtil.getFormatDate("20300101", "yyyyMMdd"));
        }
    }

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {

    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
