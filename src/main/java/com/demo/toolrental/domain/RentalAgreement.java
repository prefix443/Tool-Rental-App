package com.demo.toolrental.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import com.demo.toolrental.util.DateUtil;
import com.demo.toolrental.util.NumberUtil;

/**
 * Representation of the rental agreement produced after customer performs a
 * checkout
 * 
 * @author Andrew
 */
public class RentalAgreement {

	private String toolCode;
	private ToolType toolType;
	private String toolBrand;
	private int rentalDays;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BigDecimal dailyRentalCharge;
	private int chargeDays;
	private BigDecimal preDiscountCharge;
	private int discountPercent;
	private BigDecimal discountAmount;
	private BigDecimal finalCharge;

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public ToolType getToolType() {
		return toolType;
	}

	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}

	public String getToolBrand() {
		return toolBrand;
	}

	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}

	public int getChargeDays() {
		return chargeDays;
	}

	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}

	public BigDecimal getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getFinalCharge() {
		return finalCharge;
	}

	public void setFinalCharge(BigDecimal finalCharge) {
		this.finalCharge = finalCharge;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> rentalMap = new LinkedHashMap<String, String>();
		rentalMap.put("Tool code" , toolCode);
		rentalMap.put("Tool type" , toolType.name());
		rentalMap.put("Tool brand" , toolBrand);
		rentalMap.put("Rental days" , String.valueOf(rentalDays));
		rentalMap.put("Check out date" , DateUtil.formatDate(checkoutDate));
		rentalMap.put("Due date" , DateUtil.formatDate(dueDate));
		rentalMap.put("Daily rental charge" , NumberUtil.currencyFormat(dailyRentalCharge));
		rentalMap.put("Charge days" , String.valueOf(chargeDays));
		rentalMap.put("Pre-discount charge" , NumberUtil.currencyFormat(preDiscountCharge));
		rentalMap.put("Discount percent" ,  String.valueOf(discountPercent) + "%");
		rentalMap.put("Discount amount" , NumberUtil.currencyFormat(discountAmount));
		rentalMap.put("Final charge" , NumberUtil.currencyFormat(finalCharge));
		
		String rentalInfo = rentalMap.keySet().stream()
			      .map(key -> key + ": " + rentalMap.get(key))
			      .collect(Collectors.joining( "\n\n" ));
		return rentalInfo;
	}

}