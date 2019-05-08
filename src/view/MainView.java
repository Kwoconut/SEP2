package view;

import java.io.IOException;
import java.rmi.RemoteException;

import viewmodel.MainViewViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView
{
   private Stage primaryStage;
   private ViewStore viewStore;
   private ViewRequestOffer viewRequestOffer;
   private ViewOfferList viewOfferList;
   private String currentViewID;

   private MainViewViewModel viewModel;

   public MainView(MainViewViewModel viewModel)
   {
      this.viewModel = viewModel;
   }

   public void start(Stage primaryStage) throws IOException
   {
      this.primaryStage = primaryStage;
      setWindow("START");
   }

   public void setWindow(String id) throws IOException
   {
      currentViewID = id;
      switch (id)
      {
         case "START":
            loadStartWindow("Store", "ViewStore.fxml", 1200, 700);
            break;
         case "OFFER":
            loadOfferWindow("Offer", "ViewRequestOffer.fxml", 1200, 700);
            break;
         case "OFFERLIST":
            loadOfferListWindow("OfferList", "ViewOfferList.fxml",1200, 700);
            break;
      }
   }

   private void loadStartWindow(String title, String fxmlFile, double width,
         double height)
   {
      if (viewStore == null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            viewStore = loader.getController();
            viewStore.init(viewModel.getViewModelStore(), this, scene, title);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      primaryStage.setScene(viewStore.getScene());
      primaryStage.setTitle(viewStore.getTitle());
      primaryStage.show();
   }

   private void loadOfferWindow(String title, String fxmlFile, double width,
         double height)
   {
      if (viewRequestOffer == null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            viewRequestOffer = loader.getController();
            viewRequestOffer.init(viewModel.getViewModelRequestOffer(), this, scene, title);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      primaryStage.setScene(viewRequestOffer.getScene());
      primaryStage.setTitle(viewRequestOffer.getTitle());
      primaryStage.show();
   }
   
   private void loadOfferListWindow(String title, String fxmlFile, double width,
         double height) throws RemoteException
   {
      if (viewOfferList == null)
      {
         try
         {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            viewOfferList = loader.getController();
            viewOfferList.init(viewModel.getViewModelOfferList(), this, scene, title);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      
      primaryStage.setScene(viewOfferList.getScene());
      primaryStage.setTitle(viewOfferList.getTitle());
      primaryStage.show();
   }

}
