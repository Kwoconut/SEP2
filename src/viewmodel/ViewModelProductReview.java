package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.SReviewModel;

public class ViewModelProductReview
{
   private ObservableList<String> productReviewComments;
   private DoubleProperty averageReview;
   private ObjectProperty<ViewModelProduct> product;
   private SReviewModel model;

   public ViewModelProductReview(SReviewModel model,ViewModelProduct product)
   {
      this.model = model;
      productReviewComments = FXCollections.observableArrayList();
      this.product = new SimpleObjectProperty<ViewModelProduct>(product);
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
   
   public ObjectProperty<ViewModelProduct> getProductProperty()
   {
      return product;
   }

}
