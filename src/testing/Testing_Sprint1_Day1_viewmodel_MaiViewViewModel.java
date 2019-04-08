package testing;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import model.StoreModel;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelStore;

public class Testing_Sprint1_Day1_viewmodel_MaiViewViewModel 
	{
	   private MainViewViewModel mvvm;
	   private ViewModelStore viewModelStore;
	   private StoreModel model;
	   
	@Before
	public void init() throws RemoteException
	{
		this.model = model;
	    viewModelStore = new ViewModelStore(model);
	}
	
	@Test
	public void getViewModelStore() 
	{
		mvvm.getViewModelStore();
	}

}
