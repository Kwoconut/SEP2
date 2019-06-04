package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 * SProductModel defines the interface handling actions related to Product managing
 * 
 * @author Group 1
 * @version (Version 1.0, 06/04/2019)
 */
public interface SProductModel
{/**
	   * Gets all the products stored in systems database
	   */
   void requestProducts() throws RemoteException;
   /**
	   * Returns all products of a specific type 
	   * @param type type of the products to be returned
	   */
   ArrayList<Product> getProductsByType(String type);

   double getAverage(int productID);
  
   ArrayList<String> getReviewCommentsByProductID(int productID);
   
   void addListener(PropertyChangeListener listener);

}
