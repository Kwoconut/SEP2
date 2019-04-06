package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.Client;

public interface StoreModel
{
   ArrayList<Product> getProducts();
   
   void getProductsFromServer(ArrayList<Product> products);
   
   void setClient(Client client);
   
   void addProduct(Product product);
   
   void addListener(PropertyChangeListener listener);
   
   void requestProducts() throws RemoteException;
   
}
