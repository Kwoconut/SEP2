package testing;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.Client;
import model.Product;
import model.Store;
import model.StoreModel;
import server.Server;
import server.ServerModel;

public class testing
{
   private Client client;
   private Server server;
   private StoreModel model;
   private ServerModel serverModel;
   private ArrayList<Product> products;
   private Product product1;
   private Product product2;
   private Store store1;
   private Store store2;
   private ArrayList<Product> productsTest;
   
   
   @Before
   public void setup()
   {
	  product1 = new Product( "Name1", 100, "finish1", "colour1", "type1");
	  product2 = new Product( "Name2", 200, "finish2", "colour2", "type2");
      model = new Store();
      store1 = new Store();
      store2 = new Store();
      productsTest = new ArrayList<Product>();
      serverModel = new ServerModel();
      products = new ArrayList<Product>(5);
      try
      {
         client = new Client(model);
      }
      catch (RemoteException | MalformedURLException | NotBoundException e)
      {    
         e.printStackTrace();      
      }
      try
      {
         server = new Server(serverModel);       
      }
      catch (RemoteException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   //Server Client Testings
   @Test
   public void testGetProducts()
   {
      try
      {
         model.requestProducts();
      }
      catch (RemoteException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      products = model.getProducts();
      assertEquals(products, serverModel.getProducts());
   }
   
   // model.Product Testings
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
   }

@Test
   public void toStringTest()
   {
      assertEquals("Name1" + "\n" + "finish1" +"\n" +"colour1"+"\n" +"type1"+"\n" +"100", product1.toString());
   }
   //model.Store Testings
   @Test
	public void addProduct() {
		store1.addProduct(product1);
		store1.addProduct(product2);
		productsTest.add(product1);
		productsTest.add(product2);
		assertEquals(2, store1.getProducts().size());
	}
	
   @Test
   public void getProducts()
   {
	   store1.addProduct(product1);
	   store1.addProduct(product2);
	   productsTest.add(product1);
	   productsTest.add(product2);
	   assertEquals(productsTest, store1.getProducts());
   }
   
   @Test
   public void setClient()
   {
	   assertEquals(null,store1.getClient());
	   store1.setClient(client);   
	   assertNotNull(client);
   }
   
   @Test
   public void getClient()
   {
	  store1.setClient(client); 
	  assertEquals(client,store1.getClient());
   }

}
