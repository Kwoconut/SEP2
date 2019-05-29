package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Offer;
import model.Product;
import model.Review;
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
   
   void getReviews(ArrayList<Review> reviews) throws RemoteException;
   
   void addReview(Review review) throws RemoteException;
   
   void removeReview(Review review) throws RemoteException;
   
   void addAvailableSale(Sale sale) throws RemoteException;
   
   void getUsernames(ArrayList<String> usernames) throws RemoteException;
   
   void getPasswords(ArrayList<String> usernames) throws RemoteException;

}
