package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SProductModel
{
   void requestProducts() throws RemoteException;
   
   ArrayList<Product> getProductsByType(String type);
   
   double getAverage(int productID);
   
   ArrayList<String> getReviewCommentsByProductID(int productID);
   
   void addListener(PropertyChangeListener listener);

}
