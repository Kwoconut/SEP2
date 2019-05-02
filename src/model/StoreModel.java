package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.Client;

public interface StoreModel
{
   ArrayList<Product> getProducts();
   
   ArrayList<Offer> getOffers();
   
   void getProductsFromServer(ArrayList<Product> products);
   
   void getOffersFromServer(ArrayList<Offer> offers);
   
   void setClient(Client client);
   
   void addProduct(Product product);
   
   void addListener(PropertyChangeListener listener);
   
   void requestProducts() throws RemoteException;
   
   Client getClient();
   
   void sendOfferToServer(ArrayList<String> offerInfo);


   
}
