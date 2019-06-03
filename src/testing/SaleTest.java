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
	private Sale sale2;
	private Sale sale3;
	private Sale sale4;
	private Product product;
	private Product product2;
	private Product product3;

	@Before
	public void setUp() throws Exception {
		product = new Product(1, "Denise Metal Tile", 200, "orange",
	            "Metal Tile", "deniseMetalTile.png");
		product2 = new Product(2, "Queen Metal Tile", 300, "orange",
	            "Metal Tile", "deniseMetalTile.png");
		product3 = new Product(3, "Teenager Metal Tile", 400, "orange",
	            "Metal Tile", "deniseMetalTile.png");
		sale = new Sale(1, new MyDate(25,5,2019), new MyDate(30,5,2019), product2,
	            20);
		sale2 = new Sale(2, new MyDate(26,5,2019), new MyDate(30,5,2019), product2,
	            20);
		sale3 = new Sale(3, new MyDate(27,6,2019), new MyDate(30,6,2019), product,
	            20);
		sale4 = new Sale(3, new MyDate(27,6,2019), new MyDate(30,6,2019), product,
	            20);
	}

	@Test
	public void testGetProduct() {
		assertEquals(product2, sale.getProduct());
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
		assertEquals(300,sale.getPrice(),0);
	}

	@Test
	public void testGetAmount() {
		assertEquals(20,sale.getAmount());
	}


	@Test
	public void testGetID() {
		assertEquals(1,sale.getID());
	}

	@Test
	public void testValidDate() {
		assertEquals(false,sale.validDate());
	}

	@Test
	public void testOverridesOtherSales() {
		assertEquals(true,sale.overridesOtherSalesWithSameProduct(sale2));
		assertEquals(false,sale.overridesOtherSalesWithSameProduct(sale3));
	}

	@SuppressWarnings("static-access")
	@Test
	public void testGetPriceBeforeSaleApplies() {
		assertEquals(200,sale.getPriceBeforeSaleApplies(160, 20),0);
	}

	@SuppressWarnings("static-access")
	@Test
	public void testGetReducedPrice() {
		assertEquals(160,sale.getReducedPrice(200, 20),0);
	}

	@Test
	public void testEqualsObject() {
		assertEquals(sale3,sale4);
	}

}
