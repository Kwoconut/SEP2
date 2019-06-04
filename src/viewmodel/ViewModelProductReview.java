package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.SReviewModel;

public class ViewModelProductReview implements PropertyChangeListener
{
   private ObservableList<String> productReviewComments;
   private DoubleProperty averageReview;
   private IntegerProperty productID;
   private StringProperty productName;
   private StringProperty productType;
   private StringProperty productPrice;
   private StringProperty imageProperty;
   private StringProperty loginProperty;
   private SReviewModel model;

   public ViewModelProductReview(SReviewModel model) throws RemoteException
   {
      this.model = model;
      this.productReviewComments = FXCollections.observableArrayList();
      this.productID = new SimpleIntegerProperty();
      this.productName = new SimpleStringProperty();
      this.productType = new SimpleStringProperty();
      this.productPrice = new SimpleStringProperty();
      this.averageReview = new SimpleDoubleProperty();
      this.imageProperty = new SimpleStringProperty();
      this.loginProperty = new SimpleStringProperty("");
      this.model.requestReviews();
      this.model.addListener(this);
   }

   public ObservableList<String> getProductReviewComments()
   {
      return productReviewComments;
   }

   public DoubleProperty getAverageReviewProperty()
   {
      return averageReview;
   }

   public StringProperty getImageProperty()
   {
      return imageProperty;
   }

   public IntegerProperty getProductIDProperty()
   {
      return productID;
   }

   public StringProperty productNameProperty()
   {
      return productName;
   }

   public StringProperty productTypeProperty()
   {
      return productType;
   }

   public StringProperty productPrice()
   {
      return productPrice;
   }

   public StringProperty getLoginProperty()
   {
      return loginProperty;
   }

   public void deleteComment(int id) throws RemoteException
   {
      model.removeReviewComment(productReviewComments.get(id), productID.get());
   }

   public void refreshComments()
   {
      model.getReviewCommentsByProductID(productID.get());
   }

   public void leaveReview(double rating, String message) throws RemoteException
   {

      model.addReview(rating, message, productID.getValue());
      model.getAverage(productID.get());
      model.getReviewCommentsByProductID(productID.get());
   }

   @Override
   @SuppressWarnings("unchecked")

   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("AVERAGERATING"))
      {
         averageReview.set((double) evt.getNewValue());
         Product product = (Product) evt.getOldValue();
         productID.set(product.getID());
         productName.set(product.getName());
         productType.set(product.getType());
         productPrice.set(Double.toString(product.getPrice()) + "DKK");
         imageProperty.set(product.getImageID());
      }
      else if (evt.getPropertyName().equals("COMMENTS"))
      {
         productReviewComments.clear();
         productReviewComments.addAll((ArrayList<String>) evt.getNewValue());
      }
      else if (evt.getPropertyName().equals("VALIDLOGIN"))
      {
         loginProperty.set((String) evt.getOldValue());
      }
   }

}
