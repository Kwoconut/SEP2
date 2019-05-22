package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Offer;
import model.Product;
import model.Sale;

public interface RIClient extends Remote
{
   void getProducts(ArrayList<Product> products) throws RemoteException;
   
   void getOffers(ArrayList<Offer> offers) throws RemoteException;
   
   void getSales(ArrayList<Sale> sale) throws RemoteException;
   
   void addOffer(Offer offer) throws RemoteException;
   
   void removeOffer(Offer offer) throws RemoteException;
   
   void addSale(Sale sale) throws RemoteException;
   
   void removeSale(Sale sale) throws RemoteException;

void saleRemoveUpdate(Sale newValue);

void saleAddUpdate(Sale newValue);

void changeValue(Sale newValue);
}
