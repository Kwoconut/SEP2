package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
/**
 * SLoginModel defines the interface handling actions related to login view
 * such as login validation
 * @author Group 1
 * @version (Version 1.0, 06/04/2019)
 */
public interface SLoginModel
{/**
	   * Checks if provided values of username and password are valid
	   *
	   * @param user username to be checked
	   * @param password  password to be checked
	   */
   void validateLogin(String user, String password);
   /**
	   * Gets the valid values of usernames from the systems database
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */
   void requestUsernames() throws RemoteException;
   /**
	   * Gets the valid values of passwords from the systems database
	   * @throws RemoteException if problem occurs on execution of a remote method call  
	   */
   void requestPasswords() throws RemoteException;
   
   void addListener(PropertyChangeListener listener);
}
