package com.ailk.imssh.common.mdb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ailk.ims.complex.BaseComplex;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaCredit;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.cust.entity.CaResourceInv;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustGroup;
import com.ailk.openbilling.persistence.cust.entity.CmCustVip;
import com.ailk.openbilling.persistence.cust.entity.CmIdentityVsImei;
import com.ailk.openbilling.persistence.cust.entity.CmMktAct;
import com.ailk.openbilling.persistence.cust.entity.CmResExt;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResService;
import com.ailk.openbilling.persistence.cust.entity.CmResServiceStatus;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CmResourceMonitor;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.cust.entity.CmUserImpu;
import com.ailk.openbilling.persistence.cust.entity.CmUserMap;
import com.ailk.openbilling.persistence.cust.entity.CmUserMarket;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrder;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrderConfirm;
import com.ailk.openbilling.persistence.cust.entity.CmUserParam;
import com.ailk.openbilling.persistence.cust.entity.CmUserPbx;
import com.ailk.openbilling.persistence.cust.entity.CmUserPortability;
import com.ailk.openbilling.persistence.cust.entity.CmUserRelation;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareProd;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareRel;
import com.ailk.openbilling.persistence.cust.entity.CmUserValidchange;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCellInfo;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdMapping;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;

/**
 * 
 * @Description 过户的时候用户信息备份
 * @author lijc3
 * @Date 2013-6-27
 */
public class UserInfoComplex extends BaseComplex {
	private boolean isSameMdb = false;
	private Date commitDate;

	private Long oldAcctId;
	private Long newAcctId;

	private List<CmResource> backUpCmResource;
	private List<CmResIdentity> backUpCmResIdentity;
	private List<CmIdentityVsImei> backUpCmIdentityVsImei;
	private List<CmResLifecycle> backUpCmResLifecycle;
	private List<CmResServCycle> backUpCmResServCycle;
	private List<CmResValidity> backUpCmResValidity;
	private List<CmResExt> backUpCmResExt;
	private List<CoProd> backUpCoProd;
	private List<CoProdBillingCycle> backUpCoProdBillingCycle;
	private List<CoProdPriceParam> backUpCoProdPriceParam;
	private List<CoProdCharValue> backUpCoProdCharValue;
	private List<CoFnCharValue> backUpFnValue;
	private List<CoSplitCharValue> backUpSplitValue;
	private List<CoProdCellInfo> cellInfoValue;
	private List<CoBudgetCharValue> backUpBudgetValue;
	private List<CaResGrpRevs> backUpGrpRes;
	private List<CoSplitPayRel> backUpSplitRel;
	private List<CmResourceMonitor> backUpCmResourceMonitor;
	private List<CmUserEnterprise> backUpCmUserEnterprise;
	private List<CmUserOrderConfirm> backUpCmUserOrderConfirm;
	private List<CaResourceInv> backUpCaResourceInv;
	private List<CmMktAct> backUpCmMktAct;
	private List<CoProdMapping> backUpCoProdMapping;

	private List<CmUserMap> backUpCmUserMap;
	private List<CmUserOrder> backUpCmUserOrder;
	private List<CmUserPbx> backUpCmUserPbx;
	private List<CmUserPortability> backUpCmUserPortability;
	private List<CmUserShareProd> backUpCmUserShareProd;
	private List<CmUserMarket> backUpCmUserMarket;
	private List<CaNotifyExempt> backUpCaNotifyExempt;

	private List<CaAccountGroup> backUpCaAccountGroup;
	private List<CaAccountRv> backUpCaAccountRv;

	private List<CmUserParam> backUpCmUserParam;
	private List<CmUserRelation> backUpCmUserRelation;

	private List<CmCustGroup> backUpCmCustGroup;
	private List<CmCustVip> backUpCmCustVip;
	private List<CmResServiceStatus> backUpCmResServiceStatus;
	private List<CmResService> backUpCmResService;
	private List<CmUserShareRel> backUpCmUserShareRel;
	private List<CmUserImpu> backUpCmUserImpu;
	private List<CmContactMedium> backUpCmContactMedium;// 用户级联系人
	private List<CaCredit> backUpCaCredit;// 用户级信用度

	private List<CaAccount> backUpCaAccount;
	private List<CaBillingPlan> backUpCaBillingPlan;
	
	private List<CmUserValidchange> backUpCmUserValidchange;
	// 用户账户关系，老的用户账户关系，用结构缓存，避免多上发一次mdb
	private List<CaAccountRes> backUpAcctRes;

	public List<CmResource> getBackUpCmResource() {
		return backUpCmResource;
	}

	public void setBackUpCmResource(List<CmResource> backUpCmResource) {
		if (CommonUtil.isNotEmpty(backUpCmResource)) {
			if (isSameMdb) {
				this.backUpCmResource = new ArrayList<CmResource>();
				for (CmResource prod : backUpCmResource) {

					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmResource.add(prod);
					}
				}
			} else {
				this.backUpCmResource = backUpCmResource;
			}
		} else {
			this.backUpCmResource = backUpCmResource;
		}
	}

	public List<CmResIdentity> getBackUpCmResIdentity() {
		return backUpCmResIdentity;
	}

	public void setBackUpCmResIdentity(List<CmResIdentity> backUpCmResIdentity) {
		if (CommonUtil.isNotEmpty(backUpCmResIdentity)) {
			if (isSameMdb) {
				this.backUpCmResIdentity = new ArrayList<CmResIdentity>();
				for (CmResIdentity prod : backUpCmResIdentity) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmResIdentity.add(prod);
					}
				}
			} else {
				this.backUpCmResIdentity = backUpCmResIdentity;
			}
		} else {
			this.backUpCmResIdentity = backUpCmResIdentity;
		}
	}

	public List<CmIdentityVsImei> getBackUpCmIdentityVsImei() {
		return backUpCmIdentityVsImei;
	}

	public void setBackUpCmIdentityVsImei(List<CmIdentityVsImei> backUpCmIdentityVsImei) {
		this.backUpCmIdentityVsImei = backUpCmIdentityVsImei;
	}

	public List<CmResLifecycle> getBackUpCmResLifecycle() {
		return backUpCmResLifecycle;
	}

	public void setBackUpCmResLifecycle(List<CmResLifecycle> backUpCmResLifecycle) {
		if (CommonUtil.isNotEmpty(backUpCmResLifecycle)) {
			if (isSameMdb) {
				this.backUpCmResLifecycle = new ArrayList<CmResLifecycle>();
				for (CmResLifecycle prod : backUpCmResLifecycle) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmResLifecycle.add(prod);
					}
				}
			} else {
				this.backUpCmResLifecycle = backUpCmResLifecycle;
			}
		} else {
			this.backUpCmResLifecycle = backUpCmResLifecycle;
		}
	}

	public List<CmResServCycle> getBackUpCmResServCycle() {
		return backUpCmResServCycle;
	}

	public void setBackUpCmResServCycle(List<CmResServCycle> backUpCmResServCycle) {
		this.backUpCmResServCycle = backUpCmResServCycle;
	}

	public List<CmResValidity> getBackUpCmResValidity() {
		return backUpCmResValidity;
	}

	public void setBackUpCmResValidity(List<CmResValidity> backUpCmResValidity) {
		this.backUpCmResValidity = backUpCmResValidity;
	}

	public List<CmResExt> getBackUpCmResExt() {
		return backUpCmResExt;
	}

	public void setBackUpCmResExt(List<CmResExt> backUpCmResExt) {
		this.backUpCmResExt = backUpCmResExt;
	}

	public List<CoProd> getBackUpCoProd() {
		return backUpCoProd;
	}

	public void setBackUpCoProd(List<CoProd> backUpCoProd) {
		if (CommonUtil.isNotEmpty(backUpCoProd)) {
			if (isSameMdb) {
				this.backUpCoProd = new ArrayList<CoProd>();
				for (CoProd prod : backUpCoProd) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCoProd.add(prod);
					}
				}
			} else {
				this.backUpCoProd = backUpCoProd;
			}
		} else {
			this.backUpCoProd = backUpCoProd;
		}
	}

	public List<CoProdBillingCycle> getBackUpCoProdBillingCycle() {
		return backUpCoProdBillingCycle;
	}

	public void setBackUpCoProdBillingCycle(List<CoProdBillingCycle> backUpCoProdBillingCycle) {
		this.backUpCoProdBillingCycle = backUpCoProdBillingCycle;
	}

	public List<CoProdPriceParam> getBackUpCoProdPriceParam() {
		return backUpCoProdPriceParam;
	}

	public void setBackUpCoProdPriceParam(List<CoProdPriceParam> backUpCoProdPriceParam) {
		if (CommonUtil.isNotEmpty(backUpCoProdPriceParam)) {
			if (isSameMdb) {
				this.backUpCoProdPriceParam = new ArrayList<CoProdPriceParam>();
				for (CoProdPriceParam prod : backUpCoProdPriceParam) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCoProdPriceParam.add(prod);
					}
				}
			} else {
				this.backUpCoProdPriceParam = backUpCoProdPriceParam;
			}
		} else {
			this.backUpCoProdPriceParam = backUpCoProdPriceParam;
		}
	}

	public List<CoProdCharValue> getBackUpCoProdCharValue() {
		return backUpCoProdCharValue;
	}

	public void setBackUpCoProdCharValue(List<CoProdCharValue> backUpCoProdCharValue) {
		if (CommonUtil.isNotEmpty(backUpCoProdCharValue)) {
			if (isSameMdb) {
				this.backUpCoProdCharValue = new ArrayList<CoProdCharValue>();
				for (CoProdCharValue prod : backUpCoProdCharValue) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCoProdCharValue.add(prod);
					}
				}
			} else {
				this.backUpCoProdCharValue = backUpCoProdCharValue;
			}
		} else {
			this.backUpCoProdCharValue = backUpCoProdCharValue;
		}
	}

	public List<CoFnCharValue> getBackUpFnValue() {
		return backUpFnValue;
	}

	public void setBackUpFnValue(List<CoFnCharValue> backUpFnValue) {
		if (CommonUtil.isNotEmpty(backUpFnValue)) {
			if (isSameMdb) {
				this.backUpFnValue = new ArrayList<CoFnCharValue>();
				for (CoFnCharValue prod : backUpFnValue) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpFnValue.add(prod);
					}
				}
			} else {
				this.backUpFnValue = backUpFnValue;
			}
		} else {
			this.backUpFnValue = backUpFnValue;
		}
	}

	public List<CoSplitCharValue> getBackUpSplitValue() {
		return backUpSplitValue;
	}

	public void setBackUpSplitValue(List<CoSplitCharValue> backUpSplitValue) {
		this.backUpSplitValue = backUpSplitValue;
	}

	public List<CoProdCellInfo> getCellInfoValue() {
		return cellInfoValue;
	}

	public void setCellInfoValue(List<CoProdCellInfo> cellInfoValue) {
		if (CommonUtil.isNotEmpty(cellInfoValue)) {
			if (isSameMdb) {
				this.cellInfoValue = new ArrayList<CoProdCellInfo>();
				for (CoProdCellInfo prod : cellInfoValue) {
					if (prod.getValidDate().before(commitDate)) {
						this.cellInfoValue.add(prod);
					}
				}
			} else {
				this.cellInfoValue = cellInfoValue;
			}
		} else {
			this.cellInfoValue = cellInfoValue;
		}
	}

	public List<CoBudgetCharValue> getBackUpBudgetValue() {
		return backUpBudgetValue;
	}

	public void setBackUpBudgetValue(List<CoBudgetCharValue> backUpBudgetValue) {
		if (CommonUtil.isNotEmpty(backUpBudgetValue)) {
			if (isSameMdb) {
				this.backUpBudgetValue = new ArrayList<CoBudgetCharValue>();
				for (CoBudgetCharValue prod : backUpBudgetValue) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpBudgetValue.add(prod);
					}
				}
			} else {
				this.backUpBudgetValue = backUpBudgetValue;
			}
		} else {
			this.backUpBudgetValue = backUpBudgetValue;
		}
	}

	public List<CaResGrpRevs> getBackUpGrpRes() {
		return backUpGrpRes;
	}

	public void setBackUpGrpRes(List<CaResGrpRevs> backUpGrpRes) {
		this.backUpGrpRes = backUpGrpRes;
	}

	public List<CoSplitPayRel> getBackUpSplitRel() {
		return backUpSplitRel;
	}

	public void setBackUpSplitRel(List<CoSplitPayRel> backUpSplitRel) {
		this.backUpSplitRel = backUpSplitRel;
	}

	public List<CmResourceMonitor> getBackUpCmResourceMonitor() {
		return backUpCmResourceMonitor;
	}

	public void setBackUpCmResourceMonitor(List<CmResourceMonitor> backUpCmResourceMonitor) {
		if (CommonUtil.isNotEmpty(backUpCmResourceMonitor)) {
			if (isSameMdb) {
				this.backUpCmResourceMonitor = new ArrayList<CmResourceMonitor>();
				for (CmResourceMonitor prod : backUpCmResourceMonitor) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmResourceMonitor.add(prod);
					}
				}
			} else {
				this.backUpCmResourceMonitor = backUpCmResourceMonitor;
			}
		} else {
			this.backUpCmResourceMonitor = backUpCmResourceMonitor;
		}
	}

	public List<CmUserEnterprise> getBackUpCmUserEnterprise() {
		return backUpCmUserEnterprise;
	}

	public void setBackUpCmUserEnterprise(List<CmUserEnterprise> backUpCmUserEnterprise) {
		if (CommonUtil.isNotEmpty(backUpCmUserEnterprise)) {
			if (isSameMdb) {
				this.backUpCmUserEnterprise = new ArrayList<CmUserEnterprise>();
				for (CmUserEnterprise prod : backUpCmUserEnterprise) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserEnterprise.add(prod);
					}
				}
			} else {
				this.backUpCmUserEnterprise = backUpCmUserEnterprise;
			}
		} else {
			this.backUpCmUserEnterprise = backUpCmUserEnterprise;
		}
	}

	public List<CmUserOrderConfirm> getBackUpCmUserOrderConfirm() {
		return backUpCmUserOrderConfirm;
	}

	public void setBackUpCmUserOrderConfirm(List<CmUserOrderConfirm> backUpCmUserOrderConfirm) {
		if (CommonUtil.isNotEmpty(backUpCmUserOrderConfirm)) {
			if (isSameMdb) {
				this.backUpCmUserOrderConfirm = new ArrayList<CmUserOrderConfirm>();
				for (CmUserOrderConfirm prod : backUpCmUserOrderConfirm) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserOrderConfirm.add(prod);
					}
				}
			} else {
				this.backUpCmUserOrderConfirm = backUpCmUserOrderConfirm;
			}
		} else {
			this.backUpCmUserOrderConfirm = backUpCmUserOrderConfirm;
		}
	}

	public List<CaResourceInv> getBackUpCaResourceInv() {
		return backUpCaResourceInv;
	}

	public void setBackUpCaResourceInv(List<CaResourceInv> backUpCaResourceInv) {
		this.backUpCaResourceInv = backUpCaResourceInv;
	}

	public List<CmMktAct> getBackUpCmMktAct() {
		return backUpCmMktAct;
	}

	public void setBackUpCmMktAct(List<CmMktAct> backUpCmMktAct) {
		this.backUpCmMktAct = backUpCmMktAct;
	}

	public List<CoProdMapping> getBackUpCoProdMapping() {
		return backUpCoProdMapping;
	}

	public void setBackUpCoProdMapping(List<CoProdMapping> backUpCoProdMapping) {
		if (CommonUtil.isNotEmpty(backUpCoProdMapping)) {
			if (isSameMdb) {
				this.backUpCoProdMapping = new ArrayList<CoProdMapping>();
				for (CoProdMapping prod : backUpCoProdMapping) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCoProdMapping.add(prod);
					}
				}
			} else {
				this.backUpCoProdMapping = backUpCoProdMapping;
			}
		} else {
			this.backUpCoProdMapping = backUpCoProdMapping;
		}
	}

	public Long getOldAcctId() {
		return oldAcctId;
	}

	public void setOldAcctId(Long oldAcctId) {
		this.oldAcctId = oldAcctId;
	}

	public Long getNewAcctId() {
		return newAcctId;
	}

	public void setNewAcctId(Long newAcctId) {
		this.newAcctId = newAcctId;
	}

	public List<CaAccountRes> getBackUpAcctRes() {
		return backUpAcctRes;
	}

	public void setBackUpAcctRes(List<CaAccountRes> backUpAcctRes) {
		this.backUpAcctRes = backUpAcctRes;
	}

	public List<CmUserMap> getBackUpCmUserMap() {
		return backUpCmUserMap;
	}

	public void setBackUpCmUserMap(List<CmUserMap> backUpCmUserMap) {
		if (CommonUtil.isNotEmpty(backUpCmUserMap)) {
			if (isSameMdb) {
				this.backUpCmUserMap = new ArrayList<CmUserMap>();
				for (CmUserMap prod : backUpCmUserMap) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserMap.add(prod);
					}
				}
			} else {
				this.backUpCmUserMap = backUpCmUserMap;
			}
		} else {
			this.backUpCmUserMap = backUpCmUserMap;
		}
	}

	public List<CmUserOrder> getBackUpCmUserOrder() {
		return backUpCmUserOrder;
	}

	public void setBackUpCmUserOrder(List<CmUserOrder> backUpCmUserOrder) {
		if (CommonUtil.isNotEmpty(backUpCmUserOrder)) {
			if (isSameMdb) {
				this.backUpCmUserOrder = new ArrayList<CmUserOrder>();
				for (CmUserOrder prod : backUpCmUserOrder) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserOrder.add(prod);
					}
				}
			} else {
				this.backUpCmUserOrder = backUpCmUserOrder;
			}
		} else {
			this.backUpCmUserOrder = backUpCmUserOrder;
		}
	}

	public List<CmUserPbx> getBackUpCmUserPbx() {
		return backUpCmUserPbx;
	}

	public void setBackUpCmUserPbx(List<CmUserPbx> backUpCmUserPbx) {
		if (CommonUtil.isNotEmpty(backUpCmUserPbx)) {
			if (isSameMdb) {
				this.backUpCmUserPbx = new ArrayList<CmUserPbx>();
				for (CmUserPbx prod : backUpCmUserPbx) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserPbx.add(prod);
					}
				}
			} else {
				this.backUpCmUserPbx = backUpCmUserPbx;
			}
		} else {
			this.backUpCmUserPbx = backUpCmUserPbx;
		}
	}

	public List<CmUserPortability> getBackUpCmUserPortability() {
		return backUpCmUserPortability;
	}

	public void setBackUpCmUserPortability(List<CmUserPortability> backUpCmUserPortability) {
		if (CommonUtil.isNotEmpty(backUpCmUserPortability)) {
			if (isSameMdb) {
				this.backUpCmUserPortability = new ArrayList<CmUserPortability>();
				for (CmUserPortability prod : backUpCmUserPortability) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserPortability.add(prod);
					}
				}
			} else {
				this.backUpCmUserPortability = backUpCmUserPortability;
			}
		} else {
			this.backUpCmUserPortability = backUpCmUserPortability;
		}
	}

	public List<CmUserShareProd> getBackUpCmUserShareProd() {
		return backUpCmUserShareProd;
	}

	public void setBackUpCmUserShareProd(List<CmUserShareProd> backUpCmUserShareProd) {
		if (CommonUtil.isNotEmpty(backUpCmUserShareProd)) {
			if (isSameMdb) {
				this.backUpCmUserShareProd = new ArrayList<CmUserShareProd>();
				for (CmUserShareProd prod : backUpCmUserShareProd) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserShareProd.add(prod);
					}
				}
			} else {
				this.backUpCmUserShareProd = backUpCmUserShareProd;
			}
		} else {
			this.backUpCmUserShareProd = backUpCmUserShareProd;
		}
	}

	public List<CmUserMarket> getBackUpCmUserMarket() {
		return backUpCmUserMarket;
	}

	public void setBackUpCmUserMarket(List<CmUserMarket> backUpCmUserMarket) {
		if (CommonUtil.isNotEmpty(backUpCmUserMarket)) {
			if (isSameMdb) {
				this.backUpCmUserMarket = new ArrayList<CmUserMarket>();
				for (CmUserMarket prod : backUpCmUserMarket) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserMarket.add(prod);
					}
				}
			} else {
				this.backUpCmUserMarket = backUpCmUserMarket;
			}
		} else {
			this.backUpCmUserMarket = backUpCmUserMarket;
		}
	}

	public boolean isSameMdb() {
		return isSameMdb;
	}

	public void setSameMdb(boolean isSameMdb) {
		this.isSameMdb = isSameMdb;
	}

	public Date getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}

	public List<CaNotifyExempt> getBackUpCaNotifyExempt() {
		return backUpCaNotifyExempt;
	}

	public void setBackUpCaNotifyExempt(List<CaNotifyExempt> backUpCaNotifyExempt) {
		if (CommonUtil.isNotEmpty(backUpCaNotifyExempt)) {
			if (isSameMdb) {
				this.backUpCaNotifyExempt = new ArrayList<CaNotifyExempt>();
				for (CaNotifyExempt prod : backUpCaNotifyExempt) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCaNotifyExempt.add(prod);
					}
				}
			} else {
				this.backUpCaNotifyExempt = backUpCaNotifyExempt;
			}
		} else {
			this.backUpCaNotifyExempt = backUpCaNotifyExempt;
		}
	}

	public List<CaAccountGroup> getBackUpCaAccountGroup() {
		return backUpCaAccountGroup;
	}

	public void setBackUpCaAccountGroup(List<CaAccountGroup> backUpCaAccountGroup) {
		if (CommonUtil.isNotEmpty(backUpCaAccountGroup)) {
			if (isSameMdb) {
				this.backUpCaAccountGroup = new ArrayList<CaAccountGroup>();
				for (CaAccountGroup prod : backUpCaAccountGroup) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCaAccountGroup.add(prod);
					}
				}
			} else {
				this.backUpCaAccountGroup = backUpCaAccountGroup;
			}
		} else {
			this.backUpCaAccountGroup = backUpCaAccountGroup;
		}
	}

	public List<CaAccountRv> getBackUpCaAccountRv() {
		return backUpCaAccountRv;
	}

	public void setBackUpCaAccountRv(List<CaAccountRv> backUpCaAccountRv) {
		if (CommonUtil.isNotEmpty(backUpCaAccountRv)) {
			if (isSameMdb) {
				this.backUpCaAccountRv = new ArrayList<CaAccountRv>();
				for (CaAccountRv prod : backUpCaAccountRv) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCaAccountRv.add(prod);
					}
				}
			} else {
				this.backUpCaAccountRv = backUpCaAccountRv;
			}
		} else {
			this.backUpCaAccountRv = backUpCaAccountRv;
		}
	}

	public List<CmUserParam> getBackUpCmUserParam() {
		return backUpCmUserParam;
	}

	public void setBackUpCmUserParam(List<CmUserParam> backUpCmUserParam) {
		if (CommonUtil.isNotEmpty(backUpCmUserParam)) {
			if (isSameMdb) {
				this.backUpCmUserParam = new ArrayList<CmUserParam>();
				for (CmUserParam prod : backUpCmUserParam) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserParam.add(prod);
					}
				}
			} else {
				this.backUpCmUserParam = backUpCmUserParam;
			}
		} else {
			this.backUpCmUserParam = backUpCmUserParam;
		}
	}

	public List<CmUserRelation> getBackUpCmUserRelation() {
		return backUpCmUserRelation;
	}

	public void setBackUpCmUserRelation(List<CmUserRelation> backUpCmUserRelation) {
		if (CommonUtil.isNotEmpty(backUpCmUserRelation)) {
			if (isSameMdb) {
				this.backUpCmUserRelation = new ArrayList<CmUserRelation>();
				for (CmUserRelation prod : backUpCmUserRelation) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserRelation.add(prod);
					}
				}
			} else {
				this.backUpCmUserRelation = backUpCmUserRelation;
			}
		} else {
			this.backUpCmUserRelation = backUpCmUserRelation;
		}
	}

	public List<CaAccount> getBackUpCaAccount() {
		return backUpCaAccount;
	}

	public void setBackUpCaAccount(List<CaAccount> backUpCaAccount) {
		this.backUpCaAccount = backUpCaAccount;
	}

	public List<CaBillingPlan> getBackUpCaBillingPlan() {
		return backUpCaBillingPlan;
	}

	public void setBackUpCaBillingPlan(List<CaBillingPlan> backUpCaBillingPlan) {
		this.backUpCaBillingPlan = backUpCaBillingPlan;
	}

	public List<CmCustGroup> getBackUpCmCustGroup() {
		return backUpCmCustGroup;
	}

	public void setBackUpCmCustGroup(List<CmCustGroup> backUpCmCustGroup) {
		if (CommonUtil.isNotEmpty(backUpCmCustGroup)) {
			if (isSameMdb) {
				this.backUpCmCustGroup = new ArrayList<CmCustGroup>();
				for (CmCustGroup prod : backUpCmCustGroup) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmCustGroup.add(prod);
					}
				}
			} else {
				this.backUpCmCustGroup = backUpCmCustGroup;
			}
		} else {
			this.backUpCmCustGroup = backUpCmCustGroup;
		}
	}

	public List<CmCustVip> getBackUpCmCustVip() {
		return backUpCmCustVip;
	}

	public void setBackUpCmCustVip(List<CmCustVip> backUpCmCustVip) {
		if (CommonUtil.isNotEmpty(backUpCmCustVip)) {
			if (isSameMdb) {
				this.backUpCmCustVip = new ArrayList<CmCustVip>();
				for (CmCustVip prod : backUpCmCustVip) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmCustVip.add(prod);
					}
				}
			} else {
				this.backUpCmCustVip = backUpCmCustVip;
			}
		} else {
			this.backUpCmCustVip = backUpCmCustVip;
		}
	}

	public List<CmResServiceStatus> getBackUpCmResServiceStatus() {
		return backUpCmResServiceStatus;
	}

	public void setBackUpCmResServiceStatus(List<CmResServiceStatus> backUpCmResServiceStatus) {
		if (CommonUtil.isNotEmpty(backUpCmResServiceStatus)) {
			if (isSameMdb) {
				this.backUpCmResServiceStatus = new ArrayList<CmResServiceStatus>();
				for (CmResServiceStatus prod : backUpCmResServiceStatus) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmResServiceStatus.add(prod);
					}
				}
			} else {
				this.backUpCmResServiceStatus = backUpCmResServiceStatus;
			}
		} else {
			this.backUpCmResServiceStatus = backUpCmResServiceStatus;
		}
	}

	public List<CmResService> getBackUpCmResService() {
		return backUpCmResService;
	}

	public void setBackUpCmResService(List<CmResService> backUpCmResService) {
		if (CommonUtil.isNotEmpty(backUpCmResService)) {
			if (isSameMdb) {
				this.backUpCmResService = new ArrayList<CmResService>();
				for (CmResService prod : backUpCmResService) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmResService.add(prod);
					}
				}
			} else {
				this.backUpCmResService = backUpCmResService;
			}
		} else {
			this.backUpCmResService = backUpCmResService;
		}
	}

	public List<CmUserShareRel> getBackUpCmUserShareRel() {
		return backUpCmUserShareRel;
	}

	public void setBackUpCmUserShareRel(List<CmUserShareRel> backUpCmUserShareRel) {
		if (CommonUtil.isNotEmpty(backUpCmUserShareRel)) {
			if (isSameMdb) {
				this.backUpCmUserShareRel = new ArrayList<CmUserShareRel>();
				for (CmUserShareRel prod : backUpCmUserShareRel) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserShareRel.add(prod);
					}
				}
			} else {
				this.backUpCmUserShareRel = backUpCmUserShareRel;
			}
		} else {
			this.backUpCmUserShareRel = backUpCmUserShareRel;
		}
	}

	public List<CmUserImpu> getBackUpCmUserImpu() {
		return backUpCmUserImpu;
	}

	public void setBackUpCmUserImpu(List<CmUserImpu> backUpCmUserImpu) {
		if (CommonUtil.isNotEmpty(backUpCmUserImpu)) {
			if (isSameMdb) {
				this.backUpCmUserImpu = new ArrayList<CmUserImpu>();
				for (CmUserImpu prod : backUpCmUserImpu) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmUserImpu.add(prod);
					}
				}
			} else {
				this.backUpCmUserImpu = backUpCmUserImpu;
			}
		} else {
			this.backUpCmUserImpu = backUpCmUserImpu;
		}
	}

	public List<CmContactMedium> getBackUpCmContactMedium() {
		return backUpCmContactMedium;
	}

	public void setBackUpCmContactMedium(List<CmContactMedium> backUpCmContactMedium) {
		if (CommonUtil.isNotEmpty(backUpCmContactMedium)) {
			if (isSameMdb) {
				this.backUpCmContactMedium = new ArrayList<CmContactMedium>();
				for (CmContactMedium prod : backUpCmContactMedium) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCmContactMedium.add(prod);
					}
				}
			} else {
				this.backUpCmContactMedium = backUpCmContactMedium;
			}
		} else {
			this.backUpCmContactMedium = backUpCmContactMedium;
		}
	}
	
	
	
	public List<CmUserValidchange> getBackUpCmUserValidchange() {
		return backUpCmUserValidchange;
	}

	public void setBackUpCmUserValidchange(List<CmUserValidchange> backUpCmUserValidchange) {
		if (CommonUtil.isNotEmpty(backUpCmUserValidchange)) {
			if (isSameMdb) {
				this.backUpCmUserValidchange = new ArrayList<CmUserValidchange>();
				for (CmUserValidchange vaildChange : backUpCmUserValidchange) {
					if (vaildChange.getValidDate().before(commitDate)) {
						this.backUpCmUserValidchange.add(vaildChange);
					}
				}
			} else {
				this.backUpCmUserValidchange = backUpCmUserValidchange;
			}
		} else {
			this.backUpCmUserValidchange = backUpCmUserValidchange;
		}
	}

	public List<CaCredit> getBackUpCaCredit() {
		return backUpCaCredit;
	}

	public void setBackUpCaCredit(List<CaCredit> backUpCaCredit) {
		if (CommonUtil.isNotEmpty(backUpCaCredit)) {
			if (isSameMdb) {
				this.backUpCaCredit = new ArrayList<CaCredit>();
				for (CaCredit prod : backUpCaCredit) {
					if (prod.getValidDate().before(commitDate)) {
						this.backUpCaCredit.add(prod);
					}
				}
			} else {
				this.backUpCaCredit = backUpCaCredit;
			}
		} else {
			this.backUpCaCredit = backUpCaCredit;
		}
	}

}
