package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.RIClient;

public interface RIServerRead extends Remote
{
   void getProducts(RIClient client) throws RemoteException;

   void getOffers(RIClient client) throws RemoteException;

   void getSales(RIClient client) throws RemoteException;
}
