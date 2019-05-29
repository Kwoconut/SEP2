package view;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
	   viewModel.onLeaveReviewButtonPressed();
   }

}