package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.Client;

public interface StoreModel
{
   void getProducts(Product[] products);
   
   void setClient(Client client);
   
   void addProduct(Product product);
   
   void addListener(PropertyChangeListener listener);
   
   void requestProducts() throws RemoteException;
   
}
