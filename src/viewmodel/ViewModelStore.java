package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Product;
import model.SProductModel;
import model.Sale;

public class ViewModelStore implements PropertyChangeListener
{
   private SProductModel model;
   private StringProperty[] names;
   private StringProperty[] prices;
   private StringProperty notificationProperty;

   public ViewModelStore(SProductModel model) throws RemoteException
   {
      this.model = model;
      names = new StringProperty[5];
      prices = new StringProperty[5];
      notificationProperty = new SimpleStringProperty();
      reinitializeArrays();
      this.model.addListener(this);
      this.model.requestProducts();   }

   @SuppressWarnings("unchecked")
   public void updateProducts(PropertyChangeEvent evt)
   {
      ArrayList<Product> products = ((ArrayList<Product>) evt.getNewValue());
      for (int i = 0; i < 5; i++)
      {
         names[i].set(products.get(i).getName());
         prices[i].set(products.get(i).getPrice() + "DKK");
      }
   }

   public void getProduct(String type)
   {
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

   public StringProperty getNotificationProperty()
   {
      return notificationProperty;
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
      Platform.runLater(() -> executePropertyChange(evt));
   }

   public void executePropertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("SEND"))
      {
         updateProducts(evt);
      }
      if (evt.getPropertyName().equals("SALEPRODUCTPRICEUPDATE"))
      {

         Sale sale = (Sale) evt.getNewValue();
         for (int i = 0; i < names.length; i++)
         {
            if (names[i].get().equals(sale.getProduct().getName()))
            {
               prices[i].set(sale.getPrice() + "DKK");
            }
         }
         notificationProperty.set(sale.getProduct().getName()
               + " is on sale! You can check by clicking Check Sales!");
      }
      if (evt.getPropertyName().equals("SALEPRODUCTPRICEREVERT"))
      {
         Sale sale = (Sale) evt.getNewValue();
         for (int i = 0; i < names.length; i++)
         {
            if (names[i].get().equals(sale.getProduct().getName()))
            {
               prices[i].set(sale.getPrice() + "DKK");
            }
         }
         notificationProperty.set("");
      }
   }
}