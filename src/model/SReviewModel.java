package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SReviewModel
{

   void requestReviews() throws RemoteException;

   void addReview(Review review);

   void removeReview(Review review) throws RemoteException;

   double getAverage(int productID);

   ArrayList<String> getReviewCommentsByProductID(int productID);

}
