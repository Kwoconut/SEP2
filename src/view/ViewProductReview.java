package view;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelProductReview;

public class ViewProductReview implements View
{

   @FXML
   Label productName;

   @FXML
   Label productPrice;

   @FXML
   Label averageRating;

   @FXML
   ScrollPane commentPane;

   @FXML
   StackPane imagePane;

   private ViewModelProductReview viewModel;
   private MainView view;
   private Scene scene;
   private String title;

   @Override
   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.viewModel = viewModel.getViewModelProductReview();
      this.view = view;
      this.scene = scene;
      this.title = title;

      productName.textProperty().bind(this.viewModel.productNameProperty());
      productPrice.textProperty()
            .bindBidirectional(this.viewModel.productPrice());
      averageRating.textProperty()
            .bind(this.viewModel.getAverageReviewProperty().asString());

   }

   public void refresh()
   {
      ObservableList<String> comments = this.viewModel
            .getProductReviewComments();
      ObservableList<TextArea> textBox = FXCollections.observableArrayList();

      imagePane.getChildren().clear();
      ImageView productImage = new ImageView(
            new Image("images/" + this.viewModel.getImageProperty().get()));
      productImage.setFitWidth(250);
      productImage.setFitHeight(350);

      imagePane.getChildren().add(productImage);

      GridPane gridPane = new GridPane();
      gridPane.setPadding(new Insets(20, 20, 20, 31));
      gridPane.setVgap(20);
      gridPane.setHgap(43);

      for (int i = 0; i < comments.size(); i++)
      {
         if (!(comments.get(i).equals("")))
         {
            textBox.add(new TextArea(comments.get(i)));
            textBox.get(i).setPrefWidth(520);
            textBox.get(i).setPrefHeight(200);
            textBox.get(i).setEditable(false);
            textBox.get(i).setStyle("-fx-opacity: 100;");
            GridPane.setConstraints(textBox.get(i), 0, i);
         }
      }

      gridPane.getChildren().addAll(textBox);
      commentPane.setContent(gridPane);
   }

   @Override
   public Scene getScene()
   {
      return scene;
   }

   @Override
   public String getTitle()
   {
      return title;
   }

   public void onCheckOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("offerlist");
   }

   public void onHomeButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("start");
   }

   public void onRequestOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("offer");
   }

   public void onManageSaleButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("saleslist");
   }

   public void onCheckSalesButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("sales");
   }

   public void onLeaveReviewButtonPressed() throws RemoteException
   {
	   Image image = new Image(getClass().getResource("/images/"+ viewModel.getImageProperty().getValue()).toExternalForm());
		ImageView imageView = new ImageView(image);
	    imageView.setFitWidth(170);
	    imageView.setFitHeight(100);
			Alert alert = new Alert(AlertType.NONE);
			alert.setX(790);
			alert.setY(250);
			        alert.setTitle(viewModel.productNameProperty().getValue());
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
	  Image image = new Image(getClass().getResource("/images/"+ viewModel.getImageProperty().getValue()).toExternalForm());
		ImageView imageView = new ImageView(image);
	    imageView.setFitWidth(170);
	    imageView.setFitHeight(100);
	  TextInputDialog dialog = new TextInputDialog();
		dialog.setX(790);
		dialog.setY(250);
		dialog.setGraphic(imageView);
	  dialog.setTitle(viewModel.productNameProperty().getValue());
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
		  viewModel.leaveReview(rating,result.get());
		  refresh();
	  }
}	

}