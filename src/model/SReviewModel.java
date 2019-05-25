package model;

import java.rmi.RemoteException;

public interface SReviewModel {
	
	void requestReviews() throws RemoteException;
	
	void addReview(Review review);
	
	void removeReview(Review review) throws RemoteException;

}
