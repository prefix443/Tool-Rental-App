package com.demo.toolrental;

import com.demo.toolrental.domain.Checkout;
import com.demo.toolrental.domain.RentalAgreement;
import com.demo.toolrental.repository.*;
import com.demo.toolrental.services.CheckoutService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Test cases listed in instructions
 * 
 * @author Andrew
 */

@SpringBootTest
@Sql(scripts = "classpath:data.sql")
public class ToolRentalTests {

	@InjectMocks
	private CheckoutService checkoutServiceSpy;

	@Autowired
	private ToolRepository toolRepository;

	@Mock
	private CheckoutRepository checkoutRepositoryMock;

	@Mock
	private ToolRepository toolRepositoryMock;

	@Mock
	private RentalAgreementRepository rentalAgreementRepositorMock;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(checkoutServiceSpy, "checkoutRepository", checkoutRepositoryMock );
		ReflectionTestUtils.setField(checkoutServiceSpy, "toolRepository", toolRepositoryMock );
		ReflectionTestUtils.setField(checkoutServiceSpy, "rentalAgreementRepository", rentalAgreementRepositorMock );
	}

	@Test
	public void test1() {
		//Arrange
		LocalDate checkoutDate = LocalDate.of(2015, Month.SEPTEMBER, 3);
		Checkout checkout = new Checkout();
		checkout.setDiscountPercent(new BigDecimal(101));
		checkout.setCheckoutDate(checkoutDate);
		checkout.setTool(toolRepository.findByToolCode("JAKR"));
		checkout.setRentalDayCount(new BigDecimal(5));

		//Assert
		assertThrows(IllegalArgumentException.class, () -> {
			checkoutServiceSpy.checkoutTool(checkout);
		});
	}

	@Test
	public void test2() {
		//Arrange
		LocalDate checkoutDate = LocalDate.of(2020, Month.JULY, 2);
		LocalDate dueDate = LocalDate.of(2020, Month.JULY, 5);
		Checkout checkout = new Checkout();
		checkout.setTool(toolRepository.findByToolCode("LADW"));
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(new BigDecimal(3));
		checkout.setDiscountPercent(new BigDecimal(10));

		//Act
		RentalAgreement agreement = checkoutServiceSpy.checkoutTool(checkout);

		//Assert
		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("1.99"), agreement.getDailyRentalCharge());
		assertEquals(new BigDecimal(2), agreement.getChargeDays());
		assertEquals(new BigDecimal("3.98"), agreement.getPreDiscountCharge());
		assertEquals(new BigDecimal(10), agreement.getDiscountPercent());
		assertEquals(new BigDecimal(".40"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("3.58"), agreement.getFinalCharge());
	}

	@Test
	public void test3() {
		//Arrange
		LocalDate checkoutDate = LocalDate.of(2015, Month.JULY, 2);
		LocalDate dueDate = LocalDate.of(2015, Month.JULY, 7);

		Checkout checkout = new Checkout();
		checkout.setTool(toolRepository.findByToolCode("CHNS"));
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(new BigDecimal(5));
		checkout.setDiscountPercent(new BigDecimal(25));

		//Act
		RentalAgreement agreement = checkoutServiceSpy.checkoutTool(checkout);

		//Assert
		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("1.49"), agreement.getDailyRentalCharge());
		assertEquals(new BigDecimal(3), agreement.getChargeDays());
		assertEquals(new BigDecimal("4.47"), agreement.getPreDiscountCharge());
		assertEquals(new BigDecimal(25), agreement.getDiscountPercent());
		assertEquals(new BigDecimal("1.12"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("3.35"), agreement.getFinalCharge());
	}

	@Test
	public void test4() {
		//Arrange
		LocalDate checkoutDate = LocalDate.of(2015, Month.SEPTEMBER, 3);
		LocalDate dueDate = LocalDate.of(2015, Month.SEPTEMBER, 9);

		Checkout checkout = new Checkout();
		checkout.setTool(toolRepository.findByToolCode("JAKD"));
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(new BigDecimal(6));
		checkout.setDiscountPercent(new BigDecimal(0));

		//Act
		RentalAgreement agreement = checkoutServiceSpy.checkoutTool(checkout);

		//Assert
		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("2.99"), agreement.getDailyRentalCharge());
		assertEquals(new BigDecimal(3), agreement.getChargeDays());
		assertEquals(new BigDecimal("8.97"), agreement.getPreDiscountCharge());
		assertEquals(new BigDecimal(0), agreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("8.97"), agreement.getFinalCharge());
	}

	@Test
	public void test5() {
		//Arrange
		LocalDate checkoutDate = LocalDate.of(2015, Month.JULY, 2);
		LocalDate dueDate = LocalDate.of(2015, Month.JULY, 11);

		Checkout checkout = new Checkout();
		checkout.setTool(toolRepository.findByToolCode("JAKD"));
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(new BigDecimal(9));
		checkout.setDiscountPercent(new BigDecimal(0));

		//Act
		RentalAgreement agreement = checkoutServiceSpy.checkoutTool(checkout);

		//Assert
		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("2.99"), agreement.getDailyRentalCharge());
		assertEquals(new BigDecimal(5), agreement.getChargeDays());
		assertEquals(new BigDecimal("14.95"), agreement.getPreDiscountCharge());
		assertEquals(new BigDecimal(0), agreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("14.95"), agreement.getFinalCharge());
	}

	@Test
	public void test6() {
		//Arrange
		LocalDate checkoutDate = LocalDate.of(2020, Month.JULY, 2);
		LocalDate dueDate = LocalDate.of(2020, Month.JULY, 6);

		Checkout checkout = new Checkout();
		checkout.setTool(toolRepository.findByToolCode("JAKD"));
		checkout.setCheckoutDate(checkoutDate);
		checkout.setRentalDayCount(new BigDecimal(4));
		checkout.setDiscountPercent(new BigDecimal(50));

		//Act
		RentalAgreement agreement = checkoutServiceSpy.checkoutTool(checkout);

		//Assert
		assertEquals(dueDate, agreement.getDueDate());
		assertEquals(new BigDecimal("2.99"), agreement.getDailyRentalCharge());
		assertEquals(new BigDecimal(1), agreement.getChargeDays());
		assertEquals(new BigDecimal("2.99"), agreement.getPreDiscountCharge());
		assertEquals(new BigDecimal(50), agreement.getDiscountPercent());
		assertEquals(new BigDecimal("1.50"), agreement.getDiscountAmount());
		assertEquals(new BigDecimal("1.49"), agreement.getFinalCharge());
	}

}
