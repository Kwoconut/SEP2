package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.MyDate;
import model.Offer;
import model.Product;
import model.Review;
import model.Sale;
import model.Store;

public class StoreTest {
		private Store store;
		private Sale sale;
		private Sale sale2;
		private Sale sale3;
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
			private ArrayList<String> usernames;
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
		sale = new Sale(1, new MyDate(25,5,2019), new MyDate(30,5,2019), product2,
	            20);
		sale2 = new Sale(2, new MyDate(26,5,2019), new MyDate(30,5,2019), product3,
	            20);
		sale3 = new Sale(3, new MyDate(27,6,2019), new MyDate(30,6,2019), product,
	            20);
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
			store = new Store();
			usernames = new ArrayList<String>();
			usernames.add("Gicu");
			usernames.add("Fab");
	}

	@Test
	public void testAddProduct() {
		assertEquals(0,store.getProducts().size());
		store.addProduct(product);
		assertEquals(product,store.getProducts().get(0));
		assertEquals(1,store.getProducts().size());
	}
	
	@Test
	public void testAddOffer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProducts() {
		store.addProduct(product);
		store.addProduct(product2);
		store.addProduct(product3);
		assertEquals(products,store.getProducts());
	}

	@Test
	public void testGetOffers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSales() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReviews() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveOffer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProductsByType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProductsFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOffersFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddOfferFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveOfferFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestOffers() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestProducts() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestSales() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddSale() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveSale() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSalesFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddSaleFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveSaleFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAvailableSaleFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestReviews() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReviewsFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddReviewFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveReviewFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUsernamesFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPasswordsFromServer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestUsernames() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestPasswords() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAverage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReviewCommentsByProductID() {
		fail("Not yet implemented");
	}

}
