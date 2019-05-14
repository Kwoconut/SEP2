package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


import model.Store;

public class Testing_Sprint2_Day3_model_Store {
	private Store store;
	   
	   @Before
	   public void setup()
	   {
	      store = new Store();
	   }
	
	@Test
	public void testGenerateProductID() {
		assertEquals(1,store.generateProductID());
	}

	@Test
	public void testGenerateOfferID() {
		assertEquals(1,store.generateOfferID());
	}
}
