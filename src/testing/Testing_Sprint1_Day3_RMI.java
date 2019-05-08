package testing;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import client.Client;
import model.Offer;
import model.Product;
import model.Store;
import model.StoreModel;
import server.Server;
import server.ServerModel;

public class Testing_Sprint1_Day3_RMI
{
   private Server server;
   private StoreModel model;
   private ServerModel serverModel;
   private ArrayList<String> offer;

   
   
   @Before
   public void setup() throws SQLException
   {
      model = new Store();
      serverModel = new ServerModel();
	  offer = new ArrayList<String>(); 
	  offer.add("one");
	  offer.add("two");
	  offer.add("three");
	  offer.add("four");
      try
      {
         server = new Server(serverModel);       
      }
      catch (RemoteException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   //Store.sendOfferToServer(ArrayList<String> offerInfo) testing
   @Test
   public void  sendOfferToServer() throws RemoteException
   {
	   Offer offer = new Offer("gicu","s","d","s");
	   ArrayList<Offer> offers = new ArrayList<Offer>();
	   offers.add(offer);
	   server.sendOffer(new Offer("gicu","s","d","s"));
	   assertEquals(1, serverModel.getOffers().size());  
	   assertEquals(offers,serverModel.getOffers());
   }
}