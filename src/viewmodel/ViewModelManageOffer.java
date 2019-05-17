package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Offer;
import model.SOfferModel;
import model.StoreModel;

public class ViewModelManageOffer implements PropertyChangeListener
{

   private SOfferModel model;
   private IntegerProperty ID;
   private StringProperty name;
   private StringProperty email;
   private StringProperty phoneNumber;
   private StringProperty message;

   public ViewModelManageOffer(SOfferModel model) throws RemoteException
   {
      this.model = model;
      ID = new SimpleIntegerProperty();
      name = new SimpleStringProperty("");
      email = new SimpleStringProperty("");
      phoneNumber = new SimpleStringProperty("");
      message = new SimpleStringProperty("");
      this.model.addListener(this);
   }

   public void removeOffer() throws RemoteException
   {
      model.removeOffer(ID.get(),name.get(), email.get(), phoneNumber.get(),
            message.get());
   }
   
   public IntegerProperty getID()
   {
      return ID;
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

   public void executePropertyChange(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> executePropertyChange(evt));
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
   
   private void update(Offer offer)
   {
      ID.set(offer.getID());
      name.set(offer.getName());
      email.set(offer.getEmail());
      phoneNumber.set(offer.getPhoneNo());
      message.set(offer.getMessage());
   }
   
}
