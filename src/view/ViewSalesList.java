package view;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelSale;
import viewmodel.ViewModelSaleList;

public class ViewSalesList implements View
{

   @FXML
   ScrollPane mainPane;

   private String title;
   private Scene scene;
   private MainView view;
   private ViewModelSaleList viewModel;

   @SuppressWarnings("static-access")
   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.viewModel = viewModel.getViewModelSaleList();
      this.view = view;
      this.scene = scene;
      this.title = title;
   }

   public Scene getScene()
   {
      return scene;
   }

   public String getTitle()
   {
      return title;
   }

   public void refresh()
   {
      ObservableList<ViewModelSale> sales = this.viewModel.getSales();
      System.out.println(sales);

      ObservableList<StackPane> stackPane = FXCollections.observableArrayList();
      ObservableList<Label> names = FXCollections.observableArrayList();
      ObservableList<Label> prices = FXCollections.observableArrayList();
      ObservableList<Label> salesLabel = FXCollections.observableArrayList();

      GridPane gridPane = new GridPane();
      gridPane.setPadding(new Insets(20, 20, 20, 31));
      gridPane.setVgap(20);
      gridPane.setHgap(43);

      int x = 0;
      int y = 0;

      for (int i = 0; i < sales.size(); i++)
      {
         names.add(new Label(sales.get(i).getProductProperty().get()
               .getNameProperty().get()));
         prices.add(new Label(Double.toString(sales.get(i).getInitialPriceProperty().get()) + "DKK - " + Integer.toString(
               sales.get(i).getProductProperty().get().getPriceProperty().get())
               + "DKK"));
         salesLabel
               .add(new Label(sales.get(i).getStartDateStringProperty().get()
                     + " - " + sales.get(i).getEndDateStringProperty().get()));
         stackPane.add(new StackPane());
         stackPane.get(i).setPrefWidth(212.8);
         stackPane.get(i).setPrefHeight(393);
         stackPane.get(i).setStyle("-fx-background-color: lightgrey");
         names.get(i).setFont(Font.font(20));
         prices.get(i).setFont(Font.font(20));
         salesLabel.get(i).setFont(Font.font(15));
         names.get(i).setPadding(new Insets(40, 10, 10, 10));
         prices.get(i).setPadding(new Insets(10, 10, 40, 10));
         prices.get(i).setPadding(new Insets(10, 10, 20, 10));
         stackPane.get(i).getChildren().addAll(names.get(i));
         stackPane.get(i).getChildren().addAll(prices.get(i));
         stackPane.get(i).getChildren().addAll(salesLabel.get(i));
         StackPane.setAlignment(names.get(i), Pos.TOP_CENTER);
         StackPane.setAlignment(prices.get(i), Pos.BOTTOM_CENTER);
         StackPane.setAlignment(salesLabel.get(i), Pos.BOTTOM_CENTER);
         GridPane.setConstraints(stackPane.get(i), x, y);
         x++;
         if (x == 4)
         {
            y++;
            x = 0;
         }
      }
      gridPane.getChildren().addAll(stackPane);
      mainPane.setContent(gridPane);
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