package testing;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import model.Product;
import model.Store;

public class Testing_Sprint1_Day1_model_Store 
{
	   private Store store;
	   private Product product1;
	   private Product product2;
	   private ArrayList<Product> productsTest;
	   
	   @Before
	   public void setup()
	   {
		  product1 = new Product( "Name1", 100, "finish1", "colour1", "type1");
		  product2 = new Product( "Name2", 200, "finish2", "colour2", "type2");
	      store = new Store();
	      productsTest = new ArrayList<Product>();
	   }

	@Test
	public void addProduct() {
		store.addProduct(product1);
		store.addProduct(product2);
		productsTest.add(product1);
		productsTest.add(product2);
		assertEquals(2, store.getProducts().size());
	}
	
   @Test
   public void getProducts()
   {
	   store.addProduct(product1);
	   store.addProduct(product2);
	   productsTest.add(product1);
	   productsTest.add(product2);
	   assertEquals(productsTest, store.getProducts());
   }
}
