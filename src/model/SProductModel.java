package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public interface SProductModel
{
   void requestProducts() throws RemoteException;
   
   void getProductsByType(String type);
   
   void addListener(PropertyChangeListener listener);

}
