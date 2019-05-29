package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SReviewModel
{

   void requestReviews() throws RemoteException;

   void addReview(Review review);

   void removeReview(Review review) throws RemoteException;
   
   void addListener(PropertyChangeListener listener);

}
