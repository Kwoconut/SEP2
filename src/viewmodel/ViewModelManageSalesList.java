package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.SProductModel;
import model.SSalesModel;
import model.Sale;

public class ViewModelManageSalesList implements PropertyChangeListener
{
   private ObservableList<ViewModelProduct> products;
   private ObservableList<ViewModelSale> sales;
   private SSalesModel model;

   public ViewModelManageSalesList(SSalesModel model, SProductModel model2)
         throws RemoteException
   {
      this.model = model;
      this.products = FXCollections.observableArrayList();
      this.sales = FXCollections.observableArrayList();
      ArrayList<Product> elements = this.model.getProducts();
      for (Product element : elements)
      {
         products.add(new ViewModelProduct(model2, element));
      }
      this.model.addListener(this);
      this.model.requestSales();
   }

   public ObservableList<ViewModelSale> getSales()
   {
      return sales;
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
      if (evt.getPropertyName().equals("SALELIST"))
      {
         ArrayList<Sale> elements = new ArrayList<Sale>();
         elements = (ArrayList<Sale>) evt.getNewValue();
         sales.clear();
         for (Sale element : elements)
         {
            sales.add(new ViewModelSale(model, element));
         }
      }
      else if (evt.getPropertyName().equals("NEWSALE"))
      {
         Sale element = (Sale) evt.getNewValue();
         sales.add(new ViewModelSale(model, element));
      }
      else if (evt.getPropertyName().equals("MINUSSALE"))
      {
         Sale element = (Sale) evt.getNewValue();
         sales.remove(new ViewModelSale(model, element));
      }

   }
}
