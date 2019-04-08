package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Product;

public class Testing_Sprint1_Day2_model_Product 
	{
	
	private Product product1;
	private Product product2;
	private Product product3;
	
	@Before
	public void init()
	{
		  product1 = new Product( "Name1", 100, "finish1", "colour1", "type1");
		  product2 = new Product( "Name2", 200, "finish2", "colour2", "type2");
		  product3 = new Product( "Name2", 200, "finish2", "colour2", "type2");
	}
	
	
	@Test
	public void getName()
	   {
	      
	      assertEquals("Name1", product1.getName());
	   }
	@Test
	   public void getPrice()
	   {
	      
	      assertEquals(100, product1.getPrice());
	   }
	@Test
	   public void getFinish()
	   {
	      
	      assertEquals("finish1", product1.getFinish());
	   }
	@Test
	   public void getColour()
	   {
	      
	      assertEquals("colour1", product1.getColour());
	   }

	@Test
	   public void equals()
	   {
	      assertEquals(false, product1.equals(product2));
	      assertEquals(true, product3.equals(product2));
	   }

	@Test
	   public void toStringTest()
	   {
	      assertEquals("Name1" + "\n" + "finish1" +"\n" +"colour1"+"\n" +"type1"+"\n" +"100", product1.toString());
	   }

}
