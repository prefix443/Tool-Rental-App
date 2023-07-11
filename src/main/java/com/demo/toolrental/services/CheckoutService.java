package com.demo.toolrental.services;

import com.demo.toolrental.domain.*;
import com.demo.toolrental.repository.CheckoutRepository;
import com.demo.toolrental.repository.RentalAgreementRepository;
import com.demo.toolrental.repository.ToolRepository;
import com.demo.toolrental.util.DateUtil;
import com.demo.toolrental.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Perform a tool checkout for a customer
 *
 * @author Andrew
 * @version 1.0
 */

@Service
public class CheckoutService {

	@Autowired
	private CheckoutRepository checkoutRepository;

	@Autowired
	private RentalAgreementRepository rentalAgreementRepository;

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
		if (checkout.getRentalDayCount().intValue() < 1) {
			throw new IllegalArgumentException("Invalid rental days - rental day count must be greater than 0");
		}
		if (checkout.getDiscountPercent().intValue() < 0 || checkout.getDiscountPercent().intValue() > 100) {
			throw new IllegalArgumentException("Invalid discount percent - discount percent must be in range 0 to 100");
		}

		checkoutRepository.save(checkout);
		Tool tool =  checkout.getTool();
		ToolType toolType = checkout.getTool().getToolType();
		LocalDate dueDate = checkout.getCheckoutDate().plusDays(checkout.getRentalDayCount().intValue());

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
		
		double discountFraction = checkout.getDiscountPercent().doubleValue() / 100.0;
		BigDecimal preDiscountCharge = NumberUtil
				.roundTwoDecimals(daysCharged * tool.getToolType().getDailyCharge().doubleValue());
		BigDecimal discountAmount = NumberUtil.roundTwoDecimals(discountFraction * preDiscountCharge.doubleValue());
		BigDecimal finalCharge = NumberUtil
				.roundTwoDecimals(preDiscountCharge.doubleValue() - discountAmount.doubleValue());

		RentalAgreement agreement = new RentalAgreement();
		agreement.setTool(tool);
		agreement.setToolType(tool.getToolType());
		agreement.setToolBrand(tool.getBrand());
		agreement.setRentalDays(checkout.getRentalDayCount());
		agreement.setCheckoutDate(checkout.getCheckoutDate());
		agreement.setDueDate(dueDate);
		agreement.setDailyRentalCharge(tool.getToolType().getDailyCharge());
		agreement.setChargeDays(new BigDecimal(daysCharged));
		agreement.setPreDiscountCharge(preDiscountCharge);
		agreement.setDiscountPercent(checkout.getDiscountPercent());
		agreement.setDiscountAmount(discountAmount);
		agreement.setFinalCharge(finalCharge);
		rentalAgreementRepository.save(agreement);
		return agreement;
	}

}