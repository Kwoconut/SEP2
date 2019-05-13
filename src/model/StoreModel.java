package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.Client;

public interface StoreModel
{
   ArrayList<Product> getProducts();
   
   ArrayList<Offer> getOffers();
   
   void addProduct(Product product);
   
   void getProductsFromServer(ArrayList<Product> products);
   
   void getOffersFromServer(ArrayList<Offer> offers);
   
   void addOfferFromServer(Offer offer);
   
   void setClient(Client client);
   
   void addListener(PropertyChangeListener listener);
   
   void requestProducts() throws RemoteException;
   
   void requestOffers() throws RemoteException;
   
   Client getClient();
   
   void addOffer(Offer offer);
   
   void saveIndex(int index);
   
   Offer getTheOffer();
   
   void removeOffer();
   
   void removeOfferFromServer(Offer offer);


   
}
