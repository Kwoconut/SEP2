package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.RIClient;
import model.Offer;
import model.Product;

public class Server implements RIServer
{
   private ServerModel model;
   private ArrayList<RIClient> clients;
   private ArrayList<Offer> offers;

   public Server(ServerModel model) throws RemoteException
   {
      this.model = model;
      clients = new ArrayList<RIClient>();
      offers = new ArrayList<Offer>();
      UnicastRemoteObject.exportObject(this, 0);
   }

   public static void main(String[] args)
   {
      try
      {
         LocateRegistry.createRegistry(1099);
         ServerModel model = new ServerModel();
         RIServer server = new Server(model);
         Naming.rebind("store", server);
         System.out.println("Starting server...");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public void addClient(RIClient client) throws RemoteException
   {
      clients.add(client);
   }

   @Override
   public void getProducts(RIClient sender) throws RemoteException
   {
      sender.getProducts(model.getProducts());

   }

   @Override
   public void sendOffer(Offer offer) throws RemoteException
   {
      offers.add(offer);
      System.out.println(offers);
   }
}
