package viewmodel;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.StoreModel;

public class ViewModelOffer
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

      if (nameProperty.get().equals(""))
      {
         errorProperty.set("Please input a name");
      }
      else if (phoneProperty.get().equals("")
            || phoneProperty.get().length() < 8)
      {
         errorProperty.set("Please input a valid phone number");
      }
      else if (emailProperty.get().equals(""))
      {

         errorProperty.set("Please input a valid email address");
      }
      else if (messageProperty.get().equals(""))
      {
         errorProperty.set("Please leave a message");
      }
      else
      {
         errorProperty.set("");
         ArrayList<String> list = new ArrayList<String>();
         list.add(nameProperty.get());
         list.add(phoneProperty.get());
         list.add(emailProperty.get());
         list.add(messageProperty.get());
         model.sendOfferToServer(list);
         nameProperty.set("");
         phoneProperty.set("");
         emailProperty.set("");
         messageProperty.set("");
      }
   }
}
