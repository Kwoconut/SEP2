package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Offer;
import model.SOfferModel;
import model.StoreModel;

public class ViewModelManageOffer
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
      phoneNumber = new SimpleStringProperty("");
      email = new SimpleStringProperty("");
      message = new SimpleStringProperty("");
   }

   public ViewModelManageOffer(SOfferModel model, Offer offer)
         throws RemoteException
   {
      this.model = model;
      ID = new SimpleIntegerProperty(offer.getID());
      name = new SimpleStringProperty(offer.getName());
      phoneNumber = new SimpleStringProperty(offer.getPhoneNo());
      email = new SimpleStringProperty(offer.getEmail());
      message = new SimpleStringProperty(offer.getMessage());
   }

   public void removeOffer() throws RemoteException
   {
      model.removeOffer(ID.get(), name.get(), phoneNumber.get(), email.get(),
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

   public void setSelected(ViewModelManageOffer newValue)
   {
     try
     {
      ID.set(newValue.getID().get());
      name.set(newValue.getName().get());
      phoneNumber.set(newValue.getPhoneNumber().get());
      email.set(newValue.getEmail().get());
      message.set(newValue.getMessage().get());
     }
     catch (NullPointerException e)
     {
        ID.set(0);
        name.set("");
        phoneNumber.set("");
        email.set("");
        message.set("");
     }
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof ViewModelManageOffer))
      {
         return false;
      }
      ViewModelManageOffer other = (ViewModelManageOffer) obj;
      return other.getID().get() == ID.get()
            && other.getName().get().equals(name.get())
            && other.getPhoneNumber().get().equals(phoneNumber.get())
            && other.getEmail().get().equals(email.get())
            && other.getMessage().get().equals(message.get());
   }

}
