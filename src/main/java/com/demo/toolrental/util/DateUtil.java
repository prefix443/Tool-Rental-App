package com.demo.toolrental.util;

import java.time.LocalDate;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;

/**
 * Utility for handling dates 
 * 
 * @author Andrew
 */
public class DateUtil {
	
	/**
	 * Determine if the date is observing independence day (when the 4th falls on
	 * weekends the nearest weekday is selected)
	 * 
	 * @param date specified date
	 * @return True on independence holiday
	 */
	private static boolean isIndependenceDay(LocalDate date) {
		boolean isJuly = date.getMonth() == Month.JULY;
		boolean onJuly3rd = date.getDayOfMonth() == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY;
		boolean onJuly4th = date.getDayOfMonth() == 4 && !isWeekend(date);
		boolean onJuly5th = date.getDayOfMonth() == 5 && date.getDayOfWeek() == DayOfWeek.MONDAY;
		return isJuly && (onJuly3rd || onJuly4th || onJuly5th);
	}

	/**
	 * Determine if the date is observing labor day (first Monday in September)
	 * 
	 * @param date specified date
	 * @return True on labor day
	 */
	private static boolean isLaborDay(LocalDate date) {
		LocalDate firstMonday = date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
		return date.isEqual(firstMonday) && date.getMonth() == Month.SEPTEMBER;
	}
	
	/**
	 * Check if day is a holiday
	 * 
	 * @param date specified date
	 * @return True on a holiday
	 */
	public static boolean isHoliday(LocalDate date) {
		return isIndependenceDay(date) || isLaborDay(date);
	}
	
	/**
	 * Check if day is on weekend
	 * 
	 * @param date specified date
	 * @return True on weekend day
	 */
	public static boolean isWeekend(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}
	
	/**
	 * Date format MM/DD/YYY
	 * 
	 * @param date specified date
	 * @return String of date formated MM/DD/YYY
	 */
	public static String formatDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
	}
	
}