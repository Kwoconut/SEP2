package server;

import java.rmi.RemoteException;
import client.RIClient;
import model.Offer;
import model.Review;
import model.Sale;

public interface RIServerWrite extends RIServerRead
{
   void addClient(RIClient client) throws RemoteException;

   void sendOffer(Offer offer) throws RemoteException;

   void removeOffer(Offer offer) throws RemoteException;

   void sendSale(Sale sale) throws RemoteException;

   void removeSale(Sale sale) throws RemoteException;

   void sendReview(Review review) throws RemoteException;

   void removeReview(Review review) throws RemoteException;

}
