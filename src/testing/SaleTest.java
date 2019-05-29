package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.MyDate;
import model.Product;
import model.Sale;

public class SaleTest {
	private Sale sale;
	private Product product;

	@Before
	public void setUp() throws Exception {
		product = new Product(1, "Denise Metal Tile", 250, "orange",
	            "Metal Tile", "deniseMetalTile.png");
		sale = new Sale(1, new MyDate(25,5,2019), new MyDate(30,5,2019), product,
	            20);
	}

	@Test
	public void testGetProduct() {
		assertEquals(product, sale.getProduct());
	}

	@Test
	public void testGetStartDate() {
		assertEquals(new MyDate(25,5,2019),sale.getStartDate());
	}

	@Test
	public void testGetEndDate() {
		assertEquals(new MyDate(30,5,2019),sale.getEndDate());
	}

	@Test
	public void testGetPrice() {
		assertEquals(250,sale.getPrice(),0);
	}

	@Test
	public void testGetAmount() {
		assertEquals(20,sale.getAmount());
	}

	@Test
	public void testGetIsChangedValue() {
		assertEquals(false,sale.getIsChangedValue());
	}

	@Test
	public void testGetID() {
		assertEquals(1,sale.getID());
	}

	@Test
	public void testSetIsChangedValue() {
		assertEquals(false,sale.getIsChangedValue());
		sale.setIsChangedValue();
		assertEquals(true,sale.getIsChangedValue());
	}

	@Test
	public void testValidDate() {
		assertEquals(false,sale.validDate());
	}

	@Test
	public void testOverridesOtherSales() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPriceAfterSaleApplied() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInitialPrice() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPriceBeforeSaleApplies() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReducedPrice() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetChangedValue() {
		fail("Not yet implemented");
	}

}
