package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 * SReviewModel defines the interface handling actions related to Sales managing
 * 
 * @author Group 1
 * @version (Version 1.0, 06/04/2019)
 */
public interface SSalesModel
{/**
	   * Gets all the sales stored in systems database
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */
   void requestSales() throws RemoteException;
   /**
	   * Sending values of a sale to be added to database,server and all users
	   * @param startDate starting date of the sale
	   * @param endDate ending date of the sale
	   * @param product product that will rechieve the sale
	   * @param amount percentage amount of sale added to the product
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */
   void addSale(MyDate startDate, MyDate endDate, Product product, int amount)
         throws RemoteException;
   /**
	   * Sending values of a sale to be removed from database,server and all users
	   * @param sale sale to be removed
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */
   void removeSale(Sale sale) throws RemoteException;

   void addListener(PropertyChangeListener listener);
   /**
	   * Return all products stored locally in the model
	   */
   ArrayList<Product> getProducts();
}