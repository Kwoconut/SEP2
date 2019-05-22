package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SProductModel
{
   void requestProducts() throws RemoteException;
   
   ArrayList<Product> getProductsByType(String type);
   
   void addListener(PropertyChangeListener listener);
   
   ArrayList<Product> getProducts();

}
