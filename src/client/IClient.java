
package client;

import java.rmi.RemoteException;

import model.Offer;
import model.Review;
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
   
   void requestReviews() throws RemoteException;
   
   void sendReviewToServer(Review review) throws RemoteException;
   
   void removeReviewFromServer(Review review) throws RemoteException;
}
