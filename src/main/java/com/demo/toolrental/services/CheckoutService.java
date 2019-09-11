package com.demo.toolrental.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import com.demo.toolrental.data.ToolData;
import com.demo.toolrental.domain.Checkout;
import com.demo.toolrental.domain.RentalAgreement;
import com.demo.toolrental.domain.ToolType;
import com.demo.toolrental.domain.Tool;
import com.demo.toolrental.util.DateUtil;
import com.demo.toolrental.util.NumberUtil;

/**
 * Perform a tool checkout for a customer
 *
 * @author Andrew
 * @version 1.0
 */
public class CheckoutService {

	private HashMap<String, Tool> tools;

	public CheckoutService() {
		this.tools = ToolData.getTools();
	}

	/**
	 * Performs the checkout of a rental tool
	 * 
	 * @param checkout user checkout information
	 * @return Rental agreement details
	 * @exception IllegalArgumentException Invalid rental days specified in checkout
	 * @exception IllegalArgumentException Invalid discount percentage specified in
	 *                                     checkout
	 */
	public RentalAgreement checkoutTool(Checkout checkout) throws IllegalArgumentException {
		if (checkout.getRentalDayCount() < 1) {
			throw new IllegalArgumentException("Invalid rental days - rental day count must be greater than 0");
		}
		if (checkout.getDiscountPercent() < 0 || checkout.getDiscountPercent() > 100) {
			throw new IllegalArgumentException("Invalid discount percent - discount percent must be in range 0 to 100");
		}

		Tool tool = this.tools.get(checkout.getToolCode());
		ToolType toolType = tool.getToolType();
		LocalDate dueDate = checkout.getCheckoutDate().plusDays(checkout.getRentalDayCount());

		int daysCharged = 0;
		LocalDate currentDate = checkout.getCheckoutDate();
		while (currentDate.isBefore(dueDate)) {
			currentDate = currentDate.plusDays(1);
			boolean isHolidayCharge = toolType.isHolidayCharge() && DateUtil.isHoliday(currentDate);
			boolean isWeekendCharge = toolType.isWeekendCharge() && DateUtil.isWeekend(currentDate);
			boolean isWeekdayCharge = toolType.isWeekdayCharge() && !DateUtil.isWeekend(currentDate)
					&& !DateUtil.isHoliday(currentDate);
			if (isHolidayCharge || isWeekendCharge || isWeekdayCharge) {
				daysCharged++;
			}
		}
		
		double discountFraction = checkout.getDiscountPercent() / 100.0;
		BigDecimal preDiscountCharge = NumberUtil
				.roundTwoDecimals(daysCharged * tool.getToolType().getDailyCharge().doubleValue());
		BigDecimal discountAmount = NumberUtil.roundTwoDecimals(discountFraction * preDiscountCharge.doubleValue());
		BigDecimal finalCharge = NumberUtil
				.roundTwoDecimals(preDiscountCharge.doubleValue() - discountAmount.doubleValue());

		RentalAgreement agreement = new RentalAgreement();
		agreement.setToolCode(checkout.getToolCode());
		agreement.setToolType(tool.getToolType());
		agreement.setToolBrand(tool.getBrand());
		agreement.setRentalDays(checkout.getRentalDayCount());
		agreement.setCheckoutDate(checkout.getCheckoutDate());
		agreement.setDueDate(dueDate);
		agreement.setDailyRentalCharge(tool.getToolType().getDailyCharge());
		agreement.setChargeDays(daysCharged);
		agreement.setPreDiscountCharge(preDiscountCharge);
		agreement.setDiscountPercent(checkout.getDiscountPercent());
		agreement.setDiscountAmount(discountAmount);
		agreement.setFinalCharge(finalCharge);
		return agreement;
	}

}