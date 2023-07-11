package com.demo.toolrental.domain;

import com.demo.toolrental.util.DateUtil;
import com.demo.toolrental.util.NumberUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import javax.persistence.*;

/**
 * Representation of the rental agreement produced after customer performs a
 * checkout
 * 
 * @author Andrew
 */

@Entity
@Table(name = "rental_agreements")
public class RentalAgreement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "tool_id")
	private Tool tool;

	@ManyToOne
	@JoinColumn(name = "tool_type_id")
	private ToolType toolType;

	@Column(name = "tool_brand")
	private String toolBrand;

	@Column(name = "rental_days")
	private BigDecimal rentalDays;

	@Column(name = "checkout_date")
	private LocalDate checkoutDate;

	@Column(name = "due_date")
	private LocalDate dueDate;

	@Column(name = "daily_rental_charge")
	private BigDecimal dailyRentalCharge;

	@Column(name = "charge_days")
	private BigDecimal chargeDays;

	@Column(name = "pre_discount_charge")
	private BigDecimal preDiscountCharge;

	@Column(name = "discount_percent")
	private BigDecimal discountPercent;

	@Column(name = "discount_amount")
	private BigDecimal discountAmount;

	@Column(name = "final_charge")
	private BigDecimal finalCharge;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
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

	public BigDecimal getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(BigDecimal rentalDays) {
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

	public BigDecimal getChargeDays() {
		return chargeDays;
	}

	public void setChargeDays(BigDecimal chargeDays) {
		this.chargeDays = chargeDays;
	}

	public BigDecimal getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}

	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(BigDecimal discountPercent) {
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
		rentalMap.put("Tool code" , tool.getToolCode());
		rentalMap.put("Tool type" , toolType.getToolType());
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