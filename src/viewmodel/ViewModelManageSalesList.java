package viewmodel;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.SProductModel;
import model.SSalesModel;

public class ViewModelManageSalesList
{
   private ObservableList<ViewModelProduct> products;
   private ObservableList<ViewModelSale> sales;
   private SSalesModel model;

   public ViewModelManageSalesList(SSalesModel model,SProductModel model2) throws RemoteException
   {
      this.model = model;
      this.products = FXCollections.observableArrayList();
      this.sales = FXCollections.observableArrayList();
      ArrayList<Product> elements = this.model.getProducts();
      for (Product element : elements)
      {
         products.add(new ViewModelProduct(model2, element));
      }
   }

   public ObservableList<ViewModelSale> getSales()
   {
      return sales;
   }

   public ObservableList<ViewModelProduct> getProducts()
   {
      return products;
   }

}
