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
      email = new SimpleStringProperty("");
      phoneNumber = new SimpleStringProperty("");
      message = new SimpleStringProperty("");
   }

   public void removeOffer() throws RemoteException
   {
      model.removeOffer(ID.get(),name.get(), phoneNumber.get(), email.get(),
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
   
   public void setSelected(ViewModelRequestOffer newValue)
   {
      name.set(newValue.getNameProperty().get());
      phoneNumber.set(newValue.getPhoneProperty().get());
      email.set(newValue.getEmailProperty().get());
      message.set(newValue.getMessageProperty().get());
   }
   
}
