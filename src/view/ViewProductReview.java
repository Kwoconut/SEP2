package view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelProductReview;

public class ViewProductReview implements View
{

   @FXML
   Label productID;

   @FXML
   Label productName;

   @FXML
   Label productPrice;

   @FXML
   Label productType;

   @FXML
   Label averageRating;

   @FXML
   ScrollPane commentPane;

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

      productID.textProperty()
            .bind(this.viewModel.getProductIDProperty().asString());
      productName.textProperty().bind(this.viewModel.productNameProperty());
      productPrice.textProperty()
            .bind(this.viewModel.productPrice().asString());
      productType.textProperty().bind(this.viewModel.productTypeProperty());
      averageRating.textProperty()
            .bind(this.viewModel.getAverageReviewProperty().asString());

   }

   public void refresh()
   {
      ObservableList<String> comments = this.viewModel
            .getProductReviewComments();
      ObservableList<TextArea> textBox = FXCollections.observableArrayList();

      GridPane gridPane = new GridPane();
      gridPane.setPadding(new Insets(20, 20, 20, 31));
      gridPane.setVgap(20);
      gridPane.setHgap(43);

      for (int i = 0; i < comments.size(); i++)
      {
         if (!(comments.get(i).equals("")))
         {
            textBox.add(new TextArea(comments.get(i)));
            textBox.get(i).setPrefWidth(400);
            textBox.get(i).setPrefHeight(200);
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

}