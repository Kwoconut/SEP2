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
import server.RIServer;
import view.MainView;
import viewmodel.MainViewViewModel;

public class Client implements IClient, RIClient, Serializable
{

   private StoreModelClientHandler model;
   private RIServer server;

   public Client(StoreModelClientHandler model) throws RemoteException, NotBoundException, MalformedURLException
   {
      this.model = model;
      this.model.setClient(this);
      server = (RIServer) Naming.lookup("rmi://localhost/store");
      UnicastRemoteObject.exportObject(this, 0);
      server.addClient(this);
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
      server.getProducts(this);
   }
   
   @Override
   public void requestOffers() throws RemoteException
   {
      server.getOffers(this);
 
   }

   @Override
   public void sendOfferToServer(Offer offer) throws RemoteException
   {
      server.sendOffer(offer);      
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
	  System.out.println("__________");
   }

   @Override
   public void removeOfferFromServer(Offer offer) throws RemoteException {
	  server.removeOffer(offer);
   }

}
