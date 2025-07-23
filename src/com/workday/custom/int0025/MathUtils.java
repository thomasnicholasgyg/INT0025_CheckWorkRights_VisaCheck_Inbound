package com.workday.custom.int0025;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
	
	public static BigDecimal cloneBigDecimal(BigDecimal value) {
		return cloneBigDecimal(value, RoundingMode.HALF_UP);
	}

	public static BigDecimal cloneBigDecimal(BigDecimal value, RoundingMode roundingMode) {
		if (value == null) {
			return null;
		} else {
			return new BigDecimal(value.toPlainString()).setScale(value.scale(), roundingMode);
		}
	}
}
