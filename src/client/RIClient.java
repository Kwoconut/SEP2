package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Product;

public interface RIClient extends Remote
{
   void getProducts(Product[] products) throws RemoteException;
}
