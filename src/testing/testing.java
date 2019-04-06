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
   
   
   @Before
   public void setup()
   {
      model = new Store();
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

}
