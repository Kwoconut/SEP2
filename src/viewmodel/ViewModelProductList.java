package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.SProductModel;

public class ViewModelProductList implements PropertyChangeListener
{
   private ObservableList<ViewModelProduct> products;
   private SProductModel model;

   public ViewModelProductList(SProductModel model)
   {
      this.model = model;
      this.model.addListener(this);
      this.products = FXCollections.observableArrayList();
   }

   public ObservableList<ViewModelProduct> getProducts()
   {
      return products;
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
         products.clear();
         for (Product element : elements)
         {
            products.add(new ViewModelProduct(model, element));
         }
      }
   }

}