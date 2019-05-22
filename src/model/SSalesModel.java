package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import viewmodel.ViewModelProduct;

public interface SSalesModel
{
   void requestSales()throws RemoteException;
   
   void addSale(MyDate startDate, MyDate endDate, ViewModelProduct product, int amount);
   
   void removeSale(MyDate startDate, MyDate endDate, ViewModelProduct product, int amount) throws RemoteException;
   
   void addListener(PropertyChangeListener listener);
}