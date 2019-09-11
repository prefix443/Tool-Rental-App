package com.demo.toolrental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.demo.toolrental.domain.Checkout;
import com.demo.toolrental.domain.RentalAgreement;
import com.demo.toolrental.services.CheckoutService;

/**
 * Test cases listed in instructions
 * 
 * @author Andrew
 */

public class ToolRentalTests {

	private CheckoutService checkoutService;

	@Before
	public void setup() {
		checkoutService = new CheckoutService();
	}

	@Test(expected = IllegalArgumentException.class)
	public void test1() {
		LocalDate checkoutDate = LocalDate.of(2015, Month.SEPTEMBER, 3);
		Checkout checkout = new Checkout();
		checkout.setDiscountPercent(101);
		checkout.setCheckoutDate(checkoutDate);
		checkout.setToolCode("JAKR");
		checkout.setRentalDayCount(5);
		checkoutService.checkoutTool(checkout);
	}

	@Test
	public void test2() {

		LocalDate checkoutDate = LocalDate.of(2020, Month.JULY, 2);
		LocalDate dueDate = LocalDate.of(2020, Month.JULY, 5);

		Checkout checkout = new Checkout();
		checkout.setToolCode("LADW");
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(3);
		checkout.setDiscountPercent(10);

		RentalAgreement agreement = checkoutService.checkoutTool(checkout);
		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("1.99"), agreement.getDailyRentalCharge());
		assertEquals(2, agreement.getChargeDays());
		assertEquals(new BigDecimal("3.98"), agreement.getPreDiscountCharge());
		assertEquals(10, agreement.getDiscountPercent());
		assertEquals(new BigDecimal(".40"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("3.58"), agreement.getFinalCharge());
	}

	@Test
	public void test3() {

		LocalDate checkoutDate = LocalDate.of(2015, Month.JULY, 2);
		LocalDate dueDate = LocalDate.of(2015, Month.JULY, 7);

		Checkout checkout = new Checkout();
		checkout.setToolCode("CHNS");
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(5);
		checkout.setDiscountPercent(25);

		RentalAgreement agreement = checkoutService.checkoutTool(checkout);
		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("1.49"), agreement.getDailyRentalCharge());
		assertEquals(3, agreement.getChargeDays());
		assertEquals(new BigDecimal("4.47"), agreement.getPreDiscountCharge());
		assertEquals(25, agreement.getDiscountPercent());
		assertEquals(new BigDecimal("1.12"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("3.35"), agreement.getFinalCharge());
	}

	@Test
	public void test4() {

		LocalDate checkoutDate = LocalDate.of(2015, Month.SEPTEMBER, 3);
		LocalDate dueDate = LocalDate.of(2015, Month.SEPTEMBER, 9);

		Checkout checkout = new Checkout();
		checkout.setToolCode("JAKD");
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(6);
		checkout.setDiscountPercent(0);

		RentalAgreement agreement = checkoutService.checkoutTool(checkout);

		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("2.99"), agreement.getDailyRentalCharge());
		assertEquals(3, agreement.getChargeDays());
		assertEquals(new BigDecimal("8.97"), agreement.getPreDiscountCharge());
		assertEquals(0, agreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("8.97"), agreement.getFinalCharge());

	}

	@Test
	public void test5() {

		LocalDate checkoutDate = LocalDate.of(2015, Month.JULY, 2);
		LocalDate dueDate = LocalDate.of(2015, Month.JULY, 11);

		Checkout checkout = new Checkout();
		checkout.setToolCode("JAKR");
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(9);
		checkout.setDiscountPercent(0);

		RentalAgreement agreement = checkoutService.checkoutTool(checkout);

		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("2.99"), agreement.getDailyRentalCharge());
		assertEquals(5, agreement.getChargeDays());
		assertEquals(new BigDecimal("14.95"), agreement.getPreDiscountCharge());
		assertEquals(0, agreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("14.95"), agreement.getFinalCharge());
	}

	@Test
	public void test6() {

		LocalDate checkoutDate = LocalDate.of(2020, Month.JULY, 2);
		LocalDate dueDate = LocalDate.of(2020, Month.JULY, 6);

		Checkout checkout = new Checkout();
		checkout.setToolCode("JAKR");
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(4);
		checkout.setDiscountPercent(50);

		RentalAgreement agreement = checkoutService.checkoutTool(checkout);
		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("2.99"), agreement.getDailyRentalCharge());
		assertEquals(1, agreement.getChargeDays());
		assertEquals(new BigDecimal("2.99"), agreement.getPreDiscountCharge());
		assertEquals(50, agreement.getDiscountPercent());
		assertEquals(new BigDecimal("1.50"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("1.49"), agreement.getFinalCharge());
	}

}
