package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Offer;
import model.Product;

public interface RIClient extends Remote
{
   void getProducts(ArrayList<Product> products) throws RemoteException;
   
   void getOffers(ArrayList<Offer> offers) throws RemoteException;
}
