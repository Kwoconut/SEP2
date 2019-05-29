package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.StoreModel;

public class ViewModelLogin implements PropertyChangeListener
{
   private StoreModel model;
   private StringProperty usernameProperty;
   private StringProperty passwordProperty;
   private StringProperty errorProperty;
   private StringProperty loginProperty;

   public ViewModelLogin(StoreModel model)
   {
      this.model = model;
      usernameProperty = new SimpleStringProperty("");
      passwordProperty = new SimpleStringProperty("");
      errorProperty = new SimpleStringProperty("");
      loginProperty = new SimpleStringProperty("");
      this.model.addListener(this);
   }

   public StringProperty getUsernameProperty()
   {
      return usernameProperty;
   }

   public StringProperty getPasswordProperty()
   {
      return passwordProperty;
   }

   public StringProperty getErrorProperty()
   {
      return errorProperty;
   }

   public StringProperty getLoginProperty()
   {
      return loginProperty;
   }

   public void validateLogin() throws RemoteException
   {
      model.requestUsernames();
      model.requestPasswords();
      model.validateLogin(usernameProperty.get(), passwordProperty.get());
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {

      if (evt.getPropertyName().equals("INVALIDLOGIN"))
      {
         errorProperty.set((String) evt.getOldValue());
      }
      else if (evt.getPropertyName().equals("VALIDLOGIN"))
      {
         loginProperty.set((String) evt.getOldValue());
         errorProperty.set("");
      }
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof ViewModelLogin))
      {
         return false;
      }
      ViewModelLogin other = (ViewModelLogin) obj;
      return other.getUsernameProperty().equals(usernameProperty)
            && other.getPasswordProperty().equals(passwordProperty)
            && other.getErrorProperty().equals(errorProperty)
            && other.getLoginProperty().equals(loginProperty);
   }

}
