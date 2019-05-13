package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Offer;
import model.StoreModel;

public class ViewModelManageOffer implements PropertyChangeListener {
	
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
		this.model.addListener(this);
	}
	
	public void removeOffer() 
	{
	   Offer offer = new Offer(name.get(),email.get(),phoneNumber.get(),message.get());
		model.removeOffer(offer);
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
	private void update(Offer offer)
	{
		name.set(offer.getName());
		email.set(offer.getEmail());
		phoneNumber.set(offer.getPhoneNo());
		message.set(offer.getMessage());
	}
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("MANAGEOFFER"))
      {
         Offer offer = (Offer) evt.getNewValue();
         update(offer);
      }
      
   }
}
