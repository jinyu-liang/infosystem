package com.ailk.imssh.common.manual.handler;

import java.util.List;

import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.user.cmp.UserDepositCmp;
import com.ailk.openbilling.persistence.acct.entity.CaDepositProduct;
import com.ailk.openbilling.persistence.jd.entity.IUserDepositNotify;
import com.ailk.openbilling.persistence.zg.entity.IUserDepositTransfer;

public class UserDepositHandler implements IUserDepositHandler {

	@Override
	public void handleUserDeposit(IUserDepositNotify notify, ITableContext context) throws Exception {
		ImsLogger imsLogger = new ImsLogger(UserDepositHandler.class);
		UserDepositCmp depositCmp = context.getCmp(UserDepositCmp.class);
		Integer operType = notify.getChangeFlag();
		IUserDepositTransfer transfer = depositCmp.queryUserDepositTransfer(notify);
		// 不是删除，在zg表中无数据，则直接返回
		if (transfer == null && operType != EnumCodeExDefine.OPER_TYPE_DELETE) {
			imsLogger.error("changeFlag 是1或者2 ,zg无数据，跳过...");
			return;
		}

		if (operType == EnumCodeExDefine.OPER_TYPE_INSERT) {
			depositCmp.createDepositProdList(notify, transfer);
		} else if (operType == EnumCodeExDefine.OPER_TYPE_UPDATE) {
			if (transfer.getSts() != 1) {// 修改后，不为1，要删除产品，时间要置为处理时间1号0点
				depositCmp.deleteDepositProd(notify);
				return;
			}
			List<CaDepositProduct> deptProdList = depositCmp.queryCaDepositProduct(notify.getServId(), notify.getPromoId(),
					notify.getCondId());
			if (CommonUtil.isEmpty(deptProdList)) {
				// 如果没有创建过产品，则直接创建产品
				depositCmp.createDepositProdList(notify, transfer);
				return;
			}
			boolean changeBusiType = false;
			for (CaDepositProduct product : deptProdList) {
				// busi_type不一致，则要删掉产品，重新创建产品
				if (!product.getBusiType().equals(notify.getBusiType())) {
					changeBusiType = true;
				} 
			}
			if (changeBusiType) {
				// 删除产品，
				depositCmp.deleteDepositProd(notify);
				// 重新创建
				depositCmp.createDepositProdList(notify, transfer);
			} else {
				// busi-type没有变化，则更新时间，更新参数值
				depositCmp.updateDepositProdList(notify, transfer);
			}

		} else {
			depositCmp.deleteDepositProd(notify);
		}
	}

}
