package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.IDGenerator;
import model.Offer;
import model.Product;
import model.Review;
import model.Sale;

public class IDGeneratorTest {
	
	private ArrayList<Sale> sales;
	private Product product;
	private Product product2;
	private Product product3;
	private ArrayList<Product> products;
	   private Offer offer;
	   private Offer offer2;
	   private Offer offer3;
		private ArrayList<Offer> offers;
		private Review review;
		private Review review2;
		private Review review3;
		private ArrayList<Review> reviews;

	@Before
	public void setUp() throws Exception {
		product = new Product(1, "Denise Metal Tile", 200, "orange",
	            "Metal Tile", "deniseMetalTile.png");
		product2 = new Product(2, "Queen Metal Tile", 300, "orange",
	            "Metal Tile", "deniseMetalTile.png");
		product3 = new Product(3, "Teenager Metal Tile", 400, "orange",
	            "Metal Tile", "deniseMetalTile.png");
		products = new ArrayList<Product>();
		sales = new ArrayList<Sale>();
		reviews = new ArrayList<Review>();
		offers = new ArrayList<Offer>();
		offer = new Offer(1, "Jack", "+45748565732131", "jack.jackson@gmail.com",
	            "I want to buy some roof.");
		offer2 = new Offer(2, "Roger", "+4574857123123", "roger.jackson@gmail.com",
	            "i feel freeedom");
		offer3 = new Offer(3, "Michael", "+457485637", "michael.jackson@gmail.com",
	            "Cant touch this");
		review = new Review(1,5,"Very good roof",product);
		review2 = new Review(2,4,"Nice big roof",product2);
		review3 = new Review(3,3,"Bad roof could be better",product3);
		products.add(product);
		products.add(product2);
		products.add(product3);
		offers.add(offer);
		offers.add(offer2);
		offers.add(offer3);
		reviews.add(review);
		reviews.add(review2);
		reviews.add(review3);
	}

	@Test
	public void testGenerateProductID() {
		assertEquals(4,IDGenerator.generateProductID(products));
	}

	@Test
	public void testGenerateOfferID() {
		assertEquals(4,IDGenerator.generateOfferID(offers));
	}

	@Test
	public void testGenerateSaleID() {
		assertEquals(1,IDGenerator.generateSaleID(sales));
	}

	@Test
	public void testGenerateReviewID() {
		assertEquals(4,IDGenerator.generateReviewID(reviews));
	}

}
