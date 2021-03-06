package view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Product;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelStore;

public class ViewStore extends View
{

   @FXML
   ArrayList<Label> nameLabelList;

   @FXML
   Label newSaleLabel;

   @FXML
   ArrayList<Label> priceLabelList;
   
   @FXML
   Button checkOffersButton;
   
   @FXML
   Button manageSalesButton;

   private ViewModelStore model;

   private Scene scene;

   private String title;

   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.model = viewModel.getViewModelStore();
      super.setMainView(view);
      this.scene = scene;
      this.title = title;
      for (int i = 0; i < 5; i++)
      {
         nameLabelList.get(i).textProperty().bind(model.getNameProperty(i));
         priceLabelList.get(i).textProperty().bind(model.getPriceProperty(i));
      }
      newSaleLabel.textProperty().bind(model.getNotificationProperty());
      if(!model.getLoginProperty().get().equals("administrator"))
      {
         checkOffersButton.setVisible(false);
         manageSalesButton.setVisible(false);
      }
   }

   public void onDetailsLabelPressedClick() throws IOException
   {

      model.getProduct(Product.TYPE_CLICK_SHEET);
      getScene().getWindow().hide();
      super.getMainView().setWindow("productList");
   }

   public void onDetailsLabelPressedMetal() throws IOException
   {
      model.getProduct(Product.TYPE_METAL_SHEET);
      getScene().getWindow().hide();
      super.getMainView().setWindow("productList");
   }

   public void onDetailsLabelPressedPlated() throws IOException
   {
      model.getProduct(Product.TYPE_PLATED_SHEET);
      getScene().getWindow().hide();
      super.getMainView().setWindow("productList");

   }

   public void onDetailsLabelPressedMetalTile() throws IOException
   {
      model.getProduct(Product.TYPE_METAL_TILE);
      getScene().getWindow().hide();
      super.getMainView().setWindow("productList");
   }

   public void onDetailsLabelPressedRain() throws IOException
   {
      model.getProduct(Product.TYPE_RAIN_SYSTEM);
      getScene().getWindow().hide();
      super.getMainView().setWindow("productList");
   }

   public void onCheckOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("offerlist");
   }

   public void onHomeButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("start");
   }

   public void onRequestOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("offer");
   }

   public void onManageSaleButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("saleslist");
   }
   public void onInfoButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("info");
   }

   public void onCheckSalesButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("sales");
   }

   public String getTitle()
   {
      return title;
   }

   public Scene getScene()
   {
      return scene;
   }

}
