package com.ailk.imssh.common.flow.error.bean;

import java.util.Comparator;
import java.util.Date;

import jef.database.DataObject;

import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.DBUtil;

public class CommonCommitDateComparator implements Comparator<ErrorHolder> {

	public int compare(ErrorHolder o1, ErrorHolder o2) {

		DataObject d1 = o1.getDataObject();
		DataObject d2 = o2.getDataObject();

		jef.database.Field dateField1 = DBUtil.getJefField(d1.getClass(), "commitDate");
		jef.database.Field dateField2 = DBUtil.getJefField(d2.getClass(), "commitDate");
		// 活动多用户表没有oper_type
		if (dateField1 == null || dateField2 == null) {
			return 0;
		}
		//降序排列的时候  1比2大 返回-1 相等返回0    升序排列的是相反
		Date value1 = (Date) ClassUtil.getPropertyValue(d1, dateField1.name());
		Date value2 = (Date) ClassUtil.getPropertyValue(d2, dateField2.name());
		if (value1.equals(value2)) {
			jef.database.Field soNbrField1 = DBUtil.getJefField(d1.getClass(), "soNbr");
			jef.database.Field soNbrField2 = DBUtil.getJefField(d2.getClass(), "soNbr");

			Long valueSoNbr1 = (Long) ClassUtil.getPropertyValue(d1, soNbrField1.name());
			Long valueSoNbr2 = (Long) ClassUtil.getPropertyValue(d2, soNbrField2.name());
			if (valueSoNbr1.equals(valueSoNbr2)) {
				return 0;
			} else if (valueSoNbr1.longValue() > valueSoNbr2) {
				return 1;
			} else {
				return -1;
			}
		} else if (value1.after(value2)) {
			return 1;
		} else {
			return -1;
		}

	}

}
