package testing;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import client.Client;
import javafx.beans.property.StringProperty;
import model.Store;
import model.StoreModel;
import viewmodel.ViewModelStore;

public class Testing_Sprint1_Day3_viewmodel_ViewModelStoreTest 
{
	private ViewModelStore vms;
	private StoreModel model;
	private Client client;
	
	@Before
	public void init() throws RemoteException, MalformedURLException, NotBoundException
	{
		this.model = new Store();
		client = new Client(model);
		vms = new ViewModelStore(model);
	}
	
	
	@Test
	public void getNameProperty() {
		StringProperty s = vms.getNameProperty(0);
		System.out.println(s+"ssssssssssssssss");
		assertEquals("s",vms.getNameProperty(0));
	}
	
	@Test
	public void getPriceProperty()
	{
		
	}
	// needs evt
	@Test
	public void updateProducts()
	{
		vms.updateProducts(evt);
	}

}
