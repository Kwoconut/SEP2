package model;

import java.util.ArrayList;

import client.Client;

public interface StoreModelClientHandler
{
   void setClient(Client client);
   
   void getProductsFromServer(ArrayList<Product> products);
   
   void getOffersFromServer(ArrayList<Offer> offer);

   void addOfferFromServer(Offer offer);
   
   void removeOfferFromServer(Offer offer);
}
