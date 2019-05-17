package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public interface SOfferModel
{
   void requestOffers() throws RemoteException;

   void addOffer(String name, String phone, String email, String message);

   void removeOffer(int id, String name, String phone, String email,
         String message) throws RemoteException;

   void addListener(PropertyChangeListener listener);

}
