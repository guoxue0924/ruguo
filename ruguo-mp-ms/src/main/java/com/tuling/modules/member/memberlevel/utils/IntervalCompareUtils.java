package com.tuling.modules.member.memberlevel.utils;

import java.math.BigDecimal;

public class IntervalCompareUtils {

	/**
	 * Created on 2017年06月19日 Description 校验区间重叠 Copyright tuling (c) 2017 .
	 *
	 * @author wanggang
	 */
	public static boolean IntervalCompare(BigDecimal start1, BigDecimal start2, BigDecimal end1, BigDecimal end2) {
		BigDecimal maxStartResult = null;
		BigDecimal minEndResult = null;
		boolean intervalFlag = true;
		if (start1.compareTo(start2) == 1) {
			maxStartResult = start1;
		} else {
			maxStartResult = start2;
		}
		if (end1.compareTo(end2) == -1) {
			minEndResult = end1;
		} else {
			minEndResult = end2;
		}
		if (maxStartResult.compareTo(minEndResult) == -1 || maxStartResult.compareTo(minEndResult) == 0) {
			intervalFlag = false;
		} 

		return intervalFlag;
	}

}
