package com.demo.toolrental.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Tool types used for rental
 *
 * @author Andrew
 */

@Entity
@Table(name = "tool_types")
public class ToolType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "tool_type")
	private String toolType;

	@Column(name = "daily_charge")
	private BigDecimal dailyCharge;

	@Column(name = "weekday_charge")
	private boolean weekdayCharge;

	@Column(name = "weekend_charge")
	private boolean weekendCharge;

	@Column(name = "holiday_charge")
	private boolean holidayCharge;

	public String getToolType() {
		return toolType;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	public BigDecimal getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(BigDecimal dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	public boolean isWeekdayCharge() {
		return weekdayCharge;
	}

	public void setWeekdayCharge(boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}

	public boolean isWeekendCharge() {
		return weekendCharge;
	}

	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}

	public boolean isHolidayCharge() {
		return holidayCharge;
	}

	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}

}
