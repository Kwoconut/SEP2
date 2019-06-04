package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 * SReviewModel defines the interface handling actions related to Review managing
 * 
 * @author Group 1
 * @version (Version 1.0, 06/04/2019)
 */
public interface SReviewModel
{/**
	   * Gets all the reviews stored in systems database
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */

   void requestReviews() throws RemoteException;
   /**
	   * Remove a specific comment of a product
	   * @param text comment to be removed from the product
	   * @param productID id of the product whose comment will be removed
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */
   void removeReviewComment(String text,int productID) throws RemoteException;
   /**
	   * Sending values of a review to be added to database,server and all users
	   * @param rating rating of the review to be added
	   * @param message message of the review to be added
	   * @param productID id of the product that will recieve the review
	   * @throws RemoteException if problem occurs on execution of a remote method call 
	   */
   void addReview(double rating, String message, int productID);
   
   double getAverage(int productID);
   
   ArrayList<String> getReviewCommentsByProductID(int productID);
   
   void addListener(PropertyChangeListener listener);

}
