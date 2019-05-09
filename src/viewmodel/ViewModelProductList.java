package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Offer;
import model.Product;
import model.StoreModel;

public class ViewModelProductList implements PropertyChangeListener
{
   private ObservableList<ViewModelProduct> product;
   private StoreModel model;
   
   public ViewModelProductList (StoreModel model) throws RemoteException
   {
      this.model = model;
      this.model.addListener(this);
      this.product = FXCollections.observableArrayList();

   }
 
   public ObservableList<ViewModelProduct> getProduct()
   {
      return product;
   }
   
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> executePropertyChange(evt));
   }
   
   @SuppressWarnings("unchecked")
   public void executePropertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("DETAILS"))
      {
         ArrayList<Product> elements = new ArrayList<Product>();
         elements = (ArrayList<Product>) evt.getNewValue();
         product.clear();
         for (Product element : elements)
         {
            product.add(new ViewModelProduct(model, element));
         }
      }
}




}