package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.StoreModel;

public class ViewModelOffer implements PropertyChangeListener
{
   private StringProperty nameProperty;
   private StringProperty phoneProperty;
   private StringProperty emailProperty;
   private StringProperty messageProperty;
   private StringProperty errorProperty;
   private StoreModel model;

   public ViewModelOffer(StoreModel model)
   {
      this.model = model;
      nameProperty = new SimpleStringProperty("");
      phoneProperty = new SimpleStringProperty("");
      emailProperty = new SimpleStringProperty("");
      messageProperty = new SimpleStringProperty("");
      errorProperty = new SimpleStringProperty("");
      this.model.addListener(this);
   }

   public StringProperty getNameProperty()
   {
      return nameProperty;
   }

   public StringProperty getPhoneProperty()
   {
      return phoneProperty;
   }

   public StringProperty getEmailProperty()
   {
      return emailProperty;
   }

   public StringProperty getMessageProperty()
   {
      return messageProperty;
   }

   public StringProperty getErrorProperty()
   {
      return errorProperty;
   }

   public void sendValuesToServer()
   {

      ArrayList<String> offer = new ArrayList<String>();
      offer.add(nameProperty.get());
      offer.add(phoneProperty.get());
      offer.add(emailProperty.get());
      offer.add(messageProperty.get());
      model.sendOfferToServer(offer);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("nameInvalid"))
      {
         errorProperty.set((String) evt.getNewValue());
      } 
      else if (evt.getPropertyName().equals("phoneInvalid"))
      {
         errorProperty.set((String) evt.getNewValue());
      }
      else if (evt.getPropertyName().equals("emailInvalid"))
      {
         errorProperty.set((String) evt.getNewValue());
      }
      else if (evt.getPropertyName().equals("messageInvalid"))
      {
         errorProperty.set((String) evt.getNewValue());
      }
      else if (evt.getPropertyName().equals("clear"))
      {
         errorProperty.set((String) evt.getNewValue());
         nameProperty.set((String) evt.getNewValue());
         phoneProperty.set((String) evt.getNewValue());
         emailProperty.set((String) evt.getNewValue());
         messageProperty.set((String) evt.getNewValue());
      }

   }
}
