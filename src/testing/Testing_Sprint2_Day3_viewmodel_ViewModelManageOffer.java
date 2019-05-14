package testing;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.StringProperty;
import model.Offer;
import model.Store;
import model.StoreModel;
import viewmodel.ViewModelManageOffer;

public class Testing_Sprint2_Day3_viewmodel_ViewModelManageOffer {
	private ViewModelManageOffer vmo;
	private StoreModel model;
	@Before
	public void init() throws RemoteException
	{
		this.model = new Store();
		vmo = new ViewModelManageOffer(model);
	}
	@Test
	public void getEmail() {
		assertEquals("",vmo.getEmail().getValue());
	}
	@Test
	public void getMessage() {
		assertEquals("",vmo.getMessage().getValue());
	}
	@Test
	public void getName() {
		assertEquals("",vmo.getName().getValue());
	}
	@Test
	public void getPhoneNumber() {
		assertEquals("",vmo.getPhoneNumber().getValue());
	}
}
