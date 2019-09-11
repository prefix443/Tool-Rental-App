package com.demo.toolrental.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Utility for calculations on numbers
 * 
 * @author Andrew
 */
public class NumberUtil {
	/**
	 * Round up two decimal places
	 * 
	 * @param value specified value
	 * @return rounded value
	 */
	public static BigDecimal roundTwoDecimals(double value) {
		BigDecimal roundedValue = new BigDecimal(value);
		return roundedValue.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Format BigDecimal in to monetary value ex. Currency $9,999.99. 
	 * 
	 * @param value specified value
	 * @return money formated value
	 */
	public static String currencyFormat(BigDecimal value) {		
		 return NumberFormat.getCurrencyInstance().format(value);
	}

}