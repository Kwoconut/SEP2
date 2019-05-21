package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public interface SSalesModel
{
   void requestSales()throws RemoteException;
   
   void addSale(MyDate startDate, MyDate endDate, Product product, int amount);
   
   void removeSale(MyDate startDate, MyDate endDate, Product product, int amount) throws RemoteException;
   
   void addListener(PropertyChangeListener listener);
}
