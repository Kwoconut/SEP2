package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.SProductModel;
import model.SSalesModel;
import model.Sale;
import model.StoreModel;

public class ViewModelManageSalesList implements PropertyChangeListener
{
   private ObservableList<ViewModelProduct> products;
   private ObservableList<ViewModelSale> sales;
   private ObjectProperty<ViewModelSale> selectedSale;
   private ObjectProperty<ViewModelProduct> selectedProduct;
   private SSalesModel model;

   public ViewModelManageSalesList(SSalesModel model, StoreModel model2)
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

   public void setSelectedSale(ViewModelSale sale)
   {
      selectedSale = new SimpleObjectProperty<ViewModelSale>(sale);
   }

   public void setSelectedProduct(ViewModelProduct product)
   {
      selectedProduct = new SimpleObjectProperty<ViewModelProduct>(product);
   }

   public ObjectProperty<ViewModelSale> getSelectedSale()
   {
      return selectedSale;
   }

   public ObjectProperty<ViewModelProduct> getSelectedProduct()
   {
      return selectedProduct;
   }

   public void removeSale() throws RemoteException
   {
      model.removeSale(selectedSale);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> executePropertyChange(evt));
   }

   @SuppressWarnings("unchecked")
   public void executePropertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("SALESLIST"))
      {
         ArrayList<Sale> elements = new ArrayList<Sale>();
         elements = (ArrayList<Sale>) evt.getNewValue();
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
      else if (evt.getPropertyName().equals("EDITSALE"))
      {
         Sale element = (Sale) evt.getNewValue();
         for(int i=0;i<sales.size();i++)
         {
        	 if(sales.get(i).getIDProperty().getValue() == element.getID())
        	 {
        		 sales.set(i, new ViewModelSale(model,element));
        		 break;
        	 }
         }

   }
}
}
