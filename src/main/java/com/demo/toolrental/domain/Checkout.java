package com.demo.toolrental.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * Representation of the required checkout info
 *
 * @author Andrew
 */

@Entity
@Table(name = "checkouts")
public class Checkout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "tool_id")
	private Tool tool;

	@Column(name = "rental_day_count")
	private BigDecimal rentalDayCount;

	@Column(name = "discount_percent")
	private BigDecimal discountPercent;

	@Column(name = "checkout_date")
	private LocalDate checkoutDate;

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

	public BigDecimal getRentalDayCount() {
		return rentalDayCount;
	}

	public void setRentalDayCount(BigDecimal rentalDayCount) {
		this.rentalDayCount = rentalDayCount;
	}

	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
}