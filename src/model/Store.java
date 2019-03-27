package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.Client;

public class Store implements StoreModel,Serializable
{
   private ArrayList<Product> products;
   private Client client;
   private PropertyChangeSupport support = new PropertyChangeSupport(this);

   public Store()
   {
      products = new ArrayList<Product>();
   }

   public void addProduct(Product product)
   {
      products.add(product);
   }

   @Override
   public void setClient(Client client)
   {
      this.client = client;
   }

   @Override
   public void getProducts(Product[] values)
   {
      products = new ArrayList<Product>();
      for (int i = 0 ;i < values.length;i++)
      {
         products.add(values[i]);
         System.out.println(values[i].getName());
      }
      support.firePropertyChange("SEND", "", products);
   }

   @Override
   public void addListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }

   @Override
   public void requestProducts() throws RemoteException
   {
      client.requestProducts();
   }
}
