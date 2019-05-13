package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.RIClient;
import model.Offer;
import model.Product;

public interface RIServer extends Remote
{  
   void addClient(RIClient client) throws RemoteException;
   
   void getProducts(RIClient sender) throws RemoteException;
   
   void getOffers(RIClient sender) throws RemoteException;
   
   void sendOffer(Offer offer) throws RemoteException;
   
   void removeOffer(Offer offer) throws RemoteException;
}
