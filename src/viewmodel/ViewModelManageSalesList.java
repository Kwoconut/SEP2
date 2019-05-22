package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.SSalesModel;

public class ViewModelManageSalesList
{
   ObservableList<ViewModelProduct> products;
   ObservableList<ViewModelSale> sales;
   SSalesModel model;
   
  public ViewModelManageSalesList (SSalesModel model) {
     this.model=model;
     this.products = FXCollections.observableArrayList();
     this.sales = FXCollections.observableArrayList();
  }
  
  public ObservableList<ViewModelSale> getSales(){
     return sales;
  }
  
  public ObservableList<ViewModelProduct> getProducts(){
     return products;
  }
  
}
   