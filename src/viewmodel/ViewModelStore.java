package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Product;
import model.StoreModel;

public class ViewModelStore implements PropertyChangeListener
{
   private StoreModel model;
   private StringProperty[] names = new StringProperty[5];
   private StringProperty[] prices = new StringProperty[5];

   public ViewModelStore(StoreModel model) throws RemoteException
   {
      this.model = model;
      reinitializeArrays();
      this.model.addListener(this);
      this.model.requestProducts();
   }

   public void updateProducts(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {
         ArrayList<Product> products = ((ArrayList<Product>) evt.getNewValue());
         for (int i = 0; i < 5; i++)
         {
            names[i].set(products.get(i).getName());
            prices[i].set(products.get(i).getPrice() + "DKK");
         }

      });
   }
   public void getProduct(String type) {
      model.getProductsByType(type);
   }

   public StringProperty getNameProperty(int i)
   {
      return names[i];
   }

   public StringProperty getPriceProperty(int i)
   {
      return prices[i];
   }

   private void reinitializeArrays()
   {
      for (int i = 0; i < 5; i++)
      {
         names[i] = new SimpleStringProperty("");
         prices[i] = new SimpleStringProperty("");
      }
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("SEND"))
      {
         updateProducts(evt);
      }
   }
}