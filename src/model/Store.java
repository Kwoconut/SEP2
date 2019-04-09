package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.Client;

public class Store implements StoreModel, Serializable
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
   public ArrayList<Product> getProducts()
   {
      return products;
   }

   @Override
   public void getProductsFromServer(ArrayList<Product> values)
   {
      for (Product product : values)
      {
         System.out.println(product);
      }
      products = values;
      support.firePropertyChange("SEND", "", values);
   }

   @Override
   public void setClient(Client client)
   {
      this.client = client;
   }

   @Override
   public Client getClient()
   {
      return client;
   }

   @Override
   public void requestProducts() throws RemoteException
   {
      client.requestProducts();
   }

   @Override
   public void addListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }

   @Override
   public void sendOfferToServer(ArrayList<String> offerInfo)
   {
      Offer offer = new Offer(offerInfo.get(0), offerInfo.get(1),
            offerInfo.get(2), offerInfo.get(3));
      try
      {
         client.sendOfferToServer(offer);
      }
      catch (RemoteException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
