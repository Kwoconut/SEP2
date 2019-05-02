package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Offer;
import model.StoreModel;

public class ViewModelRequestOffer implements PropertyChangeListener
{
   private StringProperty nameProperty;
   private StringProperty phoneProperty;
   private StringProperty emailProperty;
   private StringProperty messageProperty;
   private StringProperty errorProperty;
   private StoreModel model;

   public ViewModelRequestOffer(StoreModel model)
   {
      this.model = model;
      nameProperty = new SimpleStringProperty("");
      phoneProperty = new SimpleStringProperty("");
      emailProperty = new SimpleStringProperty("");
      messageProperty = new SimpleStringProperty("");
      errorProperty = new SimpleStringProperty("");
      this.model.addListener(this);
   }

   public ViewModelRequestOffer(StoreModel model, Offer offer)
   {
      this.model = model;
      nameProperty = new SimpleStringProperty(offer.getName());
      phoneProperty = new SimpleStringProperty(offer.getPhoneNo());
      emailProperty = new SimpleStringProperty(offer.getEmail());
      messageProperty = new SimpleStringProperty(offer.getMessage());
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

   public void addOffer()
   {

      Offer offer = new Offer(nameProperty.get(), phoneProperty.get(),
            emailProperty.get(), messageProperty.get());
      model.addOffer(offer);
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

   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof ViewModelRequestOffer))
      {
         return false;
      }
      ViewModelRequestOffer other = (ViewModelRequestOffer) obj;
      return other.getNameProperty().get().equals(nameProperty.get())
            && other.getPhoneProperty().get().equals(phoneProperty.get())
            && other.getEmailProperty().get().equals(emailProperty.get())
            && other.getMessageProperty().get().equals(messageProperty.get())
            && other.getErrorProperty().get().equals(errorProperty.get());
   }
}
