package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SReviewModel
{

   void requestReviews() throws RemoteException;

   void removeReviewComment(String text,int productID) throws RemoteException;

   void addReview(double rating, String message, int productID);
   
   double getAverage(int productID);
   
   ArrayList<String> getReviewCommentsByProductID(int productID);
   
   void addListener(PropertyChangeListener listener);

}
