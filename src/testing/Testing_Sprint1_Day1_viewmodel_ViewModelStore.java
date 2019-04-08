package testing;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.Client;
import javafx.beans.property.StringProperty;
import model.Product;
import model.Store;
import model.StoreModel;
import server.Server;
import server.ServerModel;
import viewmodel.ViewModelStore;

public class Testing_Sprint1_Day1_viewmodel_ViewModelStore 
	{
	   private StringProperty[] names = new StringProperty[5];
	   private StringProperty[] prices = new StringProperty[5];
	   private ViewModelStore vms;
	   private StoreModel model;
	   private ServerModel serverModel;
	   private ArrayList<Product> products;
	   private Client client;
	   private Server server;
	   private Product product;
	   
	@Before
	public void init() throws RemoteException
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
	      vms = new ViewModelStore(model);
	}
	
	@Test
	public void updateProducts() 
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void getNameProperty() 
	{	
		product = new Product( "Aliosa", 100, "finish1", "colour1", "type1");
		products.add(product);
		names[0].set(products.get(0).getName());
		assertEquals("Aliosa",vms.getNameProperty(0));
	}
	
	@Test
	public void getPriceProperty() 
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void reinitializeArrays() 
	{
		fail("Not yet implemented");
	}
}
