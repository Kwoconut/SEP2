package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public interface SLoginModel
{
   void validateLogin(String user, String password);
   
   void requestUsernames() throws RemoteException;
   
   void requestPasswords() throws RemoteException;
   
   void addListener(PropertyChangeListener listener);
}
