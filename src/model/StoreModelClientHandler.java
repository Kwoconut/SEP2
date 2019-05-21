package model;

import java.util.ArrayList;

import client.Client;

public interface StoreModelClientHandler
{
   void setClient(Client client);
   
   void getProductsFromServer(ArrayList<Product> products);
   
   void getOffersFromServer(ArrayList<Offer> offer);
   
   void getSalesFromServer(ArrayList<Sale> sale);

   void addOfferFromServer(Offer offer);
   
   void removeOfferFromServer(Offer offer);
   
   void addSaleFromServer(Sale sale);
   
   void removeSaleFromServer (Sale sale);
}
