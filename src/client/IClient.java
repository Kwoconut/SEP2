
package client;

import java.rmi.RemoteException;

import model.Offer;
import model.Sale;

public interface IClient
{
   void requestProducts() throws RemoteException;
   
   void requestOffers() throws RemoteException;
   
   void requestSales() throws RemoteException;
   
   void sendOfferToServer(Offer offer) throws RemoteException;
   
   void removeOfferFromServer(Offer offer) throws RemoteException;
   
   void sendSaleToServer(Sale sale) throws RemoteException;
   
   void removeSaleFromServer(Sale sale) throws RemoteException;
}
