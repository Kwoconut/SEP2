package server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.RIClient;
import model.Offer;
import model.Review;
import model.Sale;

public class Server implements RIServerWrite, PropertyChangeListener
{
   private ServerModel model;
   private ArrayList<RIClient> clients;

   public Server(ServerModel model) throws RemoteException
   {
      this.model = model;
      clients = new ArrayList<RIClient>();
      this.model.addPropertyChangeListener(this);
      UnicastRemoteObject.exportObject(this, 0);
   }

   public static void main(String[] args)
   {
      try
      {
         LocateRegistry.createRegistry(1099);
         ServerModel model = new ServerModel();
         Server server = new Server(model);
         ServerAccess threadSafeServer = new ThreadSafeServer(server);
         Naming.rebind("store", threadSafeServer);
         System.out.println("Starting server...");
         Thread taskScheduler = new Thread(new TaskScheduler(model));
         taskScheduler.start();
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
   public void getOffers(RIClient sender) throws RemoteException
   {
      sender.getOffers(model.getOffers());
   }

   @Override
   public void sendOffer(Offer offer) throws RemoteException
   {
      model.addOffer(offer);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("OFFERADDED"))
      {
         for (RIClient element : clients)
         {
            try
            {
               element.addOffer((Offer) evt.getNewValue());
            }
            catch (RemoteException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      if (evt.getPropertyName().equals("OFFERREMOVED"))
      {
         for (RIClient element : clients)
         {
            try
            {
               element.removeOffer((Offer) evt.getNewValue());
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
         }
      }
      if (evt.getPropertyName().equals("SALEREMOVED"))
      {
         for (RIClient element : clients)
         {
            try
            {
               element.removeSale((Sale) evt.getNewValue());
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
         }
      }
      if (evt.getPropertyName().equals("SALEAVAILABLE"))
      {
         for (RIClient element : clients)
         {
            try
            {
               element.addAvailableSale((Sale) evt.getNewValue());
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
         }
      }
      if (evt.getPropertyName().equals("SALEADDED"))
      {
         for (RIClient element : clients)
         {
            try
            {
               element.addSale((Sale) evt.getNewValue());
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
         }
      }
      if (evt.getPropertyName().equals("REVIEWADDED"))
      {
         for (RIClient element : clients)
         {
            try
            {
               element.addReview((Review) evt.getNewValue());
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
         }
      }
      if (evt.getPropertyName().equals("COMMENTREMOVED"))
      {
         for (RIClient element : clients)
         {
            try
            {
               element.removeReviewComment((Review) evt.getNewValue());
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
         }
      }
   }

   @Override
   public void removeOffer(Offer offer) throws RemoteException
   {
      model.removeOffer(offer);
   }

   @Override
   public void getSales(RIClient sender) throws RemoteException
   {
      sender.getSales(model.getSales());
   }

   @Override
   public void sendSale(Sale sale) throws RemoteException
   {
      model.addSale(sale);
   }

   @Override
   public void removeSale(Sale sale) throws RemoteException
   {
      model.removeSale(sale);
   }

   @Override
   public void getReviews(RIClient sender) throws RemoteException
   {
      sender.getReviews(model.getReviews());

   }

   @Override
   public void sendReview(Review review) throws RemoteException
   {
      model.addReview(review);

   }

   @Override
   public void removeReviewComment(Review review) throws RemoteException
   {
      model.removeReviewComment(review);
   }

   @Override
   public void getUsernames(RIClient client) throws RemoteException
   {
      client.getUsernames(model.getUsernames());
   }

   @Override
   public void getPasswords(RIClient client) throws RemoteException
   {
      client.getPasswords(model.getPasswords());
   }

}
