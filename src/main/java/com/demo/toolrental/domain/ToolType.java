package com.demo.toolrental.domain;

import java.math.BigDecimal;

/**
 * Enum of available tool types
 * 
 * @author Andrew
 */
public enum ToolType {

	LADDER(1.99, true, true, false), CHAINSAW(1.49, true, false, true), JACKHAMMER(2.99, true, false, false);

	private BigDecimal dailyCharge;
	private boolean weekdayCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;

	private ToolType(double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
		this.dailyCharge = BigDecimal.valueOf(dailyCharge);
		this.weekdayCharge = weekdayCharge;
		this.weekendCharge = weekendCharge;
		this.holidayCharge = holidayCharge;
	}

	public BigDecimal getDailyCharge() {
		return dailyCharge;
	}

	public boolean isWeekdayCharge() {
		return weekdayCharge;
	}

	public boolean isWeekendCharge() {
		return weekendCharge;
	}

	public boolean isHolidayCharge() {
		return holidayCharge;
	}
}