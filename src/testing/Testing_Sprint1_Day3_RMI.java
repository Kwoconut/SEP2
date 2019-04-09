package testing;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.Client;
import model.Offer;
import model.Product;
import model.Store;
import model.StoreModel;
import server.Server;
import server.ServerModel;

public class Testing_Sprint1_Day3_RMI
{
   private Client client;
   private Server server;
   private StoreModel model;
   private ServerModel serverModel;
   private ArrayList<Product> products;
   private Store store;
   private ArrayList<String> offer;

   
   
   @Before
   public void setup()
   {
      model = new Store();
      serverModel = new ServerModel();
      products = new ArrayList<Product>(5);
      store = new Store();
	  offer = new ArrayList<String>(); 
	  offer.add("one");
	  offer.add("two");
	  offer.add("three");
	  offer.add("four");
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
   //Server Client Testings Store.requestProducts(),  Store.getProductsFromServer(ArrayList<Product> values)
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
   
   //Store.setClient(Client client) testing
   @Test
   public void setClient()
   {
	   assertEquals(null,store.getClient());
	   store.setClient(client);   
	   assertNotNull(client);
   }
   
   //Store.getClient() testing
   @Test
   public void getClient()
   {
	  store.setClient(client); 
	  assertEquals(client,store.getClient());
   }
   
   //Store.sendOfferToServer(ArrayList<String> offerInfo) testing
   @Test
   public void  sendOfferToServer() throws RemoteException
   {

	//  model.sendOfferToServer(offer);
	   server.sendOffer(new Offer("gicu","s","d","s"));
	   assertEquals(1, serverModel.getOffers().size());  
   }
}