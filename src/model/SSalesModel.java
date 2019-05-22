package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import viewmodel.ViewModelProduct;

public interface SSalesModel
{
   void requestSales()throws RemoteException;
   
   void addSale(MyDate startDate, MyDate endDate, ViewModelProduct product, int amount);
   
   void removeSale(int ID,MyDate startDate, MyDate endDate, ViewModelProduct product, int amount) throws RemoteException;
   
   void addListener(PropertyChangeListener listener);
   
   void removeSaleFromServer(Sale sale);
   
   void addSaleFromServer(Sale sale);
   
   void getSalesFromServer(ArrayList<Sale> sale);
   

   ArrayList<Product> getProducts();
}