package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
   private StringProperty productPrice;
   private StringProperty imageProperty;
   private SReviewModel model;

   public ViewModelProductReview(SReviewModel model) throws RemoteException
   {
      this.model = model;
      productReviewComments = FXCollections.observableArrayList();
      this.productID = new SimpleIntegerProperty();
      this.productName = new SimpleStringProperty();
      this.productType = new SimpleStringProperty();
      this.productPrice = new SimpleStringProperty();
      this.averageReview = new SimpleDoubleProperty();
      this.imageProperty = new SimpleStringProperty();
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
         productPrice.set(Double.toString(product.getPrice()) + "DKK");
         imageProperty.set(product.getImageID());
      }
      else if (evt.getPropertyName().equals("COMMENTS"))
      {
         productReviewComments.clear();
         productReviewComments.addAll((ArrayList<String>) evt.getNewValue());
      }
   }

public void onLeaveReviewButtonPressed() throws RemoteException {
	Image image = new Image(getClass().getResource("/images/"+ imageProperty.getValue()).toExternalForm());
	ImageView imageView = new ImageView(image);
    imageView.setFitWidth(170);
    imageView.setFitHeight(100);
		Alert alert = new Alert(AlertType.NONE);
		alert.setX(790);
		alert.setY(250);
		        alert.setTitle(productName.getValue());
				alert.setHeaderText("Leaving a review");
				alert.setContentText("Choose the amount of stars");
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(
				   getClass().getResource("/view/beautify.css").toExternalForm());
				dialogPane.getStyleClass().add("beautify");
				alert.setGraphic(imageView);
				ButtonType oneStar = new ButtonType("1 Star");
				ButtonType twoStar = new ButtonType("2 Star");
				ButtonType threeStar = new ButtonType("3 Star");
				ButtonType fourStar = new ButtonType("4 Star");
				ButtonType fiveStar = new ButtonType("5 Star");
				ButtonType cancel = new ButtonType("Cancel");
				alert.getButtonTypes().setAll(oneStar,twoStar,threeStar,fourStar,fiveStar,cancel);
				Optional<ButtonType> result = alert.showAndWait();
	      if (result.get() == oneStar) 
	      {
	    	  createReview(1);
	      } else if (result.get() == twoStar)
	      {
	    	createReview(2);
	      }else if (result.get() == threeStar)
	      {
	    	createReview(3);
	      }else if (result.get() == fourStar)
	      {
	    	createReview(4);
	      }else if (result.get() == fiveStar)
	      {
	    	createReview(5);
	      }
	      
}
  public void createReview(double rating) throws RemoteException
  {
	  Image image = new Image(getClass().getResource("/images/"+ imageProperty.getValue()).toExternalForm());
		ImageView imageView = new ImageView(image);
	    imageView.setFitWidth(170);
	    imageView.setFitHeight(100);
	  TextInputDialog dialog = new TextInputDialog();
		dialog.setX(790);
		dialog.setY(250);
		dialog.setGraphic(imageView);
	  dialog.setTitle(productName.getValue());
	  dialog.setHeaderText("Please leave a comment(optional)");	
	  dialog.getDialogPane().setPrefHeight(225);
	  dialog.getDialogPane().setPrefWidth(560);
	  DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getStylesheets().add(
		   getClass().getResource("/view/beautify.css").toExternalForm());
		dialogPane.getStyleClass().add("beautify");
		
	  Optional<String> result = dialog.showAndWait();
	  if (result.isPresent())
	  {
		  model.addReview(rating,result.get(),productID.getValue());
	  }
  }	
}
