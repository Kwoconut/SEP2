package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Store;
import model.StoreModel;
import viewmodel.ViewModelRequestOffer;

public class Testing_Sprint1_Day3_viewmodel_ViewModelOffer 
{
	
	private ViewModelRequestOffer vmo;
	private StoreModel model;
	@Before
	public void init()
	{
		this.model = new Store();
		vmo = new ViewModelRequestOffer(model);
	}

	@Test
	public void getNameProperty() {
		StringProperty s = new SimpleStringProperty("");
		System.out.println(vmo.getNameProperty());
		System.out.println(s);
		assertEquals(s.getValue(),vmo.getNameProperty().getValue());

	}
	
	@Test
	public void getPhoneProperty()
	{
		StringProperty s = new SimpleStringProperty("");
		assertEquals( s.getValue(),vmo.getPhoneProperty().getValue());
	}
	
	@Test
	public void getEmailProperty()
	{
		StringProperty s = new SimpleStringProperty("");
		assertEquals(s.getValue(),vmo.getEmailProperty().getValue());
	}
	
	@Test
	public void getMessageProperty()
	{
		StringProperty s = new SimpleStringProperty("");
		assertEquals(s.getValue(),vmo.getMessageProperty().getValue());
	}

	@Test
	public void getErrorProperty()
	{
		StringProperty s = new SimpleStringProperty("");
		StringProperty d = vmo.getErrorProperty();
		assertEquals(s.getValue(),d.getValue());
	}
	
	@Test
	public void sendValuesToServer()
	{
		StringProperty s = new SimpleStringProperty("Please input a name");
		vmo.addOffer();
		assertEquals(s.getValue(),vmo.getErrorProperty().getValue());
	}
}
