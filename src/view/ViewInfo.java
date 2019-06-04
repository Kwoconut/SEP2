package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelInfo;

public class ViewInfo extends View
{
   private MainView view;

   private Scene scene;

   private String title;

   private ViewModelInfo viewModel;

   @FXML
   Button checkOffersButton;

   @FXML
   Button manageSalesButton;


   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      super.setMainView(view);
      this.viewModel = viewModel.getViewModelInfo();
      this.scene = scene;
      this.title = title;

      if (!this.viewModel.getLoginProperty().get().equals("administrator"))
      {
         checkOffersButton.setVisible(false);
         manageSalesButton.setVisible(false);
      }
   }
   
   public ViewInfo()
   {
      
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

   public void onCheckSalesButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("sales");
   }

   public void onInfoButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("info");
   }

   public void onCheckOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("offerlist");
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
