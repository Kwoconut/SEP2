package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Product;


public class ProductTest {

private Product product;
private Product product2;
private Product product3;

	   @Before
	   public void setUP()
	   {
	      product = new Product(1, "Denise Metal Tile", 250, "orange",
	            "Metal Tile", "deniseMetalTile.png");
	   }

	@Test
	public void testGetID() {
		assertEquals(1, product.getID());
	}
	
	@Test
	public void testGetImageID() {
		assertEquals("deniseMetalTile.png", product.getImageID());
	}
	
	@Test
	public void testSetID() {
		product.setID(2);
		assertEquals(2, product.getID());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Denise Metal Tile", product.getName());
	}
	
	@Test
	public void testGetPrice() {
		assertEquals(250, product.getPrice(),0);
	}
	
	@Test
	public void testSetPrice() {
		product.setPrice(500);
		assertEquals(500, product.getPrice(),0);
	}
	
	@Test
	public void testGetColour() {
		assertEquals("orange", product.getColour());
	}
	
	@Test
	public void testGetType() {
		assertEquals("Metal Tile", product.getType());
	}
	
	@Test
	public void testEquals() {
		product2 = new Product(1, "Denise Metal Tile", 250, "orange",
	            "Metal Tile", "deniseMetalTile.png");
		product3 = new Product(2, "Queen Metal Tile", 250, "orange",
	            "Metal Tile", "deniseMetalTile.png");
	    assertTrue(product2.equals(product));
	    assertFalse(product.equals(product3));
	}
	
	@Test
	public void TestEqualsNullObject()
	{
	   assertEquals(false,product.equals(null));
	}
	
}
