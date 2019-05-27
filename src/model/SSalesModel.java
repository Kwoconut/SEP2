package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;
import viewmodel.ViewModelProduct;
import viewmodel.ViewModelSale;

public interface SSalesModel
{
   void requestSales() throws RemoteException;

   void addSale(MyDate startDate, MyDate endDate, ViewModelProduct product,
         int amount) throws RemoteException;

   void removeSale(ObjectProperty<ViewModelSale> selectedSale) throws RemoteException;

   void addListener(PropertyChangeListener listener);

   ArrayList<Product> getProducts();

   ArrayList<Sale> getSales();
}