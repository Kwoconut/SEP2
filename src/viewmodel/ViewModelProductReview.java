package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.SReviewModel;
import model.StoreModel;

public class ViewModelProductReview implements PropertyChangeListener
{
   private ObservableList<String> productReviewComments;
   private DoubleProperty averageReview;
   private IntegerProperty productID;
   private StringProperty productName;
   private StringProperty productType;
   private DoubleProperty productPrice;
   private SReviewModel model;

   public ViewModelProductReview(SReviewModel model)
   {
      this.model = model;
      productReviewComments = FXCollections.observableArrayList();
      this.productID = new SimpleIntegerProperty();
      this.productName = new SimpleStringProperty();
      this.productType = new SimpleStringProperty();
      this.productPrice = new SimpleDoubleProperty();
      this.averageReview = new SimpleDoubleProperty();
   }

   public ObservableList<String> getProductReviewComments()
   {
      return productReviewComments;
   }

   public DoubleProperty getAverageReviewProperty()
   {
      return averageReview;
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

   public DoubleProperty productPrice()
   {
      return productPrice;
   }

   @SuppressWarnings("unchecked")
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("AVERAGERATING"))
      {
         averageReview.set((double) evt.getNewValue());
         Product product = (Product) evt.getOldValue();
         productID.set(product.getID());
         productName.set(product.getName());
         productType.set(product.getType());
         productPrice.set(product.getPrice());
      }
      else if (evt.getPropertyName().equals("COMMENTS"))
      {
         productReviewComments.addAll((ArrayList<String>) evt.getNewValue());
      }
   }

}
