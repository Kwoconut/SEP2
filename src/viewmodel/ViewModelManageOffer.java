package viewmodel;

import java.rmi.RemoteException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Offer;
import model.StoreModel;

public class ViewModelManageOffer {
	
	private StoreModel model;
	private StringProperty name;
	private StringProperty email;
	private StringProperty phoneNumber;
	private StringProperty message;

	public ViewModelManageOffer(StoreModel model) throws RemoteException{
		this.model = model;
		name = new SimpleStringProperty("");
		email = new SimpleStringProperty("");
		phoneNumber = new SimpleStringProperty("");
		message = new SimpleStringProperty("");
	}
	
	public Offer getTheOffer()
	{
		return model.getTheOffer();
	}
	
	public void removeOffer() 
	{
		model.removeOffer();
	}
	public StringProperty getName()
	{ 
		return name;
	}
	public StringProperty getEmail()
	{
		return email;
	}
	public StringProperty getPhoneNumber()
	{
		return phoneNumber;
	}
	public StringProperty getMessage()
	{
		return message;
	}
	public void update()
	{
		name.set(model.getTheOffer().getName());
		email.set(model.getTheOffer().getEmail());
		phoneNumber.set(model.getTheOffer().getPhoneNo());
		message.set(model.getTheOffer().getMessage());
	}
}
