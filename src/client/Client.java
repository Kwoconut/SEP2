package client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.Offer;
import model.Product;
import model.Store;
import model.StoreModel;
import model.StoreModelClientHandler;
import server.RIServerRead;
import server.RIServerWrite;
import server.ServerAccess;
import view.MainView;
import viewmodel.MainViewViewModel;

public class Client implements IClient, RIClient, Serializable
{

   private StoreModelClientHandler model;
   private ServerAccess access;

   public Client(StoreModelClientHandler model) throws RemoteException, NotBoundException, MalformedURLException
   {
      this.model = model;
      this.model.setClient(this);
      access = (ServerAccess) Naming.lookup("rmi://localhost:1099/store");
      UnicastRemoteObject.exportObject(this, 0);
      RIServerWrite server = access.acquireWrite();
      server.addClient(this);
      access.releaseWrite();
   }

   @Override
   public void getProducts(ArrayList<Product> products) throws RemoteException
   {
      model.getProductsFromServer(products);
   }
   
   @Override
   public void getOffers(ArrayList<Offer> offers) throws RemoteException
   {
	   model.getOffersFromServer(offers);
   }

   @Override
   public void requestProducts() throws RemoteException
   {
	  RIServerRead server = access.acquireRead();
      server.getProducts(this);
      access.releaseRead();
   }
   
   @Override
   public void requestOffers() throws RemoteException
   {
	  RIServerRead server = access.acquireRead();
      server.getOffers(this);
      access.releaseRead();
 
   }

   @Override
   public void sendOfferToServer(Offer offer) throws RemoteException
   {
	  RIServerWrite server = access.acquireWrite();
      server.sendOffer(offer); 
      access.releaseWrite();
   }

   @Override
   public void addOffer(Offer offer) throws RemoteException
   {
      model.addOfferFromServer(offer);
   }
   
   @Override
   public void removeOffer(Offer offer) throws RemoteException 
   {
	  model.removeOfferFromServer(offer);
   }

   @Override
   public void removeOfferFromServer(Offer offer) throws RemoteException 
   {
	  RIServerWrite server = access.acquireWrite();
	  server.removeOffer(offer);
	  access.releaseWrite();
   }

}
