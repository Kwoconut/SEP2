package client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.Offer;
import model.Product;
import model.Sale;
import model.StoreModelClientHandler;
import server.RIServerRead;
import server.RIServerWrite;
import server.ServerAccess;


public class Client implements IClient, RIClient, Serializable
{

   private static final long serialVersionUID = 1L;
   private StoreModelClientHandler model;
   private ServerAccess access;

   public Client(StoreModelClientHandler model) throws RemoteException, NotBoundException, MalformedURLException
   {
      this.model = model;
      this.model.setClient(this);
      access = (ServerAccess) Naming.lookup("rmi://10.152.202.74:1099/store");
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

   @Override
   public void getSales(ArrayList<Sale> sale) throws RemoteException
   {
      model.getSalesFromServer(sale);
      
   }

   @Override
   public void addSale(Sale sale) throws RemoteException
   {
      model.addSaleFromServer(sale);
      
   }

   @Override
   public void removeSale(Sale sale) throws RemoteException
   {
      model.removeSaleFromServer(sale);    
   }

   @Override
   public void requestSales() throws RemoteException
   {
      RIServerRead server = access.acquireRead();
      server.getSales(this);
      access.releaseRead();
   }

   @Override
   public void sendSaleToServer(Sale sale) throws RemoteException
   {
      RIServerWrite server = access.acquireWrite();
      server.sendSale(sale); 
      access.releaseWrite();
   }

   @Override
   public void removeSaleFromServer(Sale sale) throws RemoteException
   {
      RIServerWrite server = access.acquireWrite();
      server.removeSale(sale);
      access.releaseWrite();
   }

@Override
public void saleRemoveUpdate(Sale sale) throws RemoteException 
{		
	RIServerWrite server = access.acquireWrite();
	server.saleRemoveUpdate(sale);

}

@Override
public void saleAddUpdate(Sale sale) throws RemoteException{
	RIServerWrite server = access.acquireWrite();	
	server.saleAddUpdate(sale);
	access.releaseWrite();
	
}

@Override
public void changeValue(Sale sale) throws RemoteException{
	RIServerWrite server = access.acquireWrite();
	server.changeValue(sale);
	access.releaseWrite();
}
}