
package client;

import java.rmi.RemoteException;

import model.Offer;

public interface IClient
{
   void requestProducts() throws RemoteException;
   
   void requestOffers() throws RemoteException;
   
   void sendOfferToServer(Offer offer) throws RemoteException;
   
   void removeOfferFromServer(Offer offer) throws RemoteException;
}
