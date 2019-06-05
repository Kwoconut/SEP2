package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Product;
import model.Review;

public class ReviewTest {

	private Review review;
	private Review review2;
	private Product product;
	private Product product2;

	@Before
	public void setUp() throws Exception {
		product = new Product(1, "Denise Metal Tile", 200, "orange", "Metal Tile", "deniseMetalTile.png");
		product2 = new Product(2, "Queen Metal Tile", 300, "orange", "Metal Tile", "deniseMetalTile.png");
		review = new Review(1, 5, "Very good roof", product);
		review2 = new Review(2, 4, "Nice big roof", product2);
		
	}

	@Test
	public void testGetRating() {
		assertEquals(5, review.getRating(), 0);
	}

	@Test
	public void testGetMessage() {
		assertEquals("Nice big roof", review2.getMessage());
	}

	@Test
	public void testGetProduct() {
		assertEquals(product2, review2.getProduct());
	}

	@Test
	public void testGetID() {
		assertEquals(2, review2.getID());
	}

}
