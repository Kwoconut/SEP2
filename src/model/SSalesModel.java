package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SSalesModel
{
   void requestSales() throws RemoteException;

   void addSale(MyDate startDate, MyDate endDate, Product product, int amount)
         throws RemoteException;

   void removeSale(Sale sale) throws RemoteException;

   void addListener(PropertyChangeListener listener);

   ArrayList<Product> getProducts();
}