package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.RIClient;
import model.Product;

public interface RIServer extends Remote
{  
   void addClient(RIClient client) throws RemoteException;
   
   void getProducts(RIClient sender) throws RemoteException;
}
