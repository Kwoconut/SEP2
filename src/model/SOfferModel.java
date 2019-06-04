package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
/**
 * SOfferModel defines the interface handling actions related to Offer managing
 * such as their adding and removal
 * @author Group 1
 * @version (Version 1.0, 06/04/2019)
 */
public interface SOfferModel
{/**
	   * Gets all the offers stored in systems database
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */
   void requestOffers() throws RemoteException;
	/**
	   * Sending values of an offer to be added in database,server and all users 
	   * @param name name of the offer to be added
	   * @param phone  phone of the offer to be added
	   * @param email  email of the offer to be added
	   * @param message  message of the offer to be added
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */
   void addOffer(String name, String phone, String email, String message);
   /**
	   * Sending values of an offer to be removed from database,server and all users 
	   * @param id id of the offer to be removed
	   * @param name name of the offer to be removed
	   * @param phone  phone of the offer to be removed
	   * @param email  email of the offer to be removed
	   * @param message  message of the offer to be removed
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */
   void removeOffer(int id, String name, String phone, String email,
         String message) throws RemoteException;
   
   void addListener(PropertyChangeListener listener);

}
