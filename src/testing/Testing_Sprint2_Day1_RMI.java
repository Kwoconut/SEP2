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

public class Testing_Sprint2_Day1_RMI
{
   private Client client;
   private Server server;
   private StoreModel model;
   private ServerModel serverModel;
   private ArrayList<Offer> offers;
   private Store store;

   
   
   @Before
   public void setup()
   {
      model = new Store();
      serverModel = new ServerModel();
      offers = new ArrayList<Offer>(5);
      store = new Store();
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
   public void testGetOffers()
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
      offers = model.getOffers();
      assertEquals(offers, serverModel.getOffers());
   }
}
